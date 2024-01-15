/**
 * 
 */
package com.seda.j2ee5.maf.core.screen;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Properties;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.seda.commons.logger.CustomLoggerManager;
import com.seda.commons.logger.LoggerWrapper;
import com.seda.j2ee5.maf.components.defender.csrf.CSRFDefender;
import com.seda.j2ee5.maf.components.validation.ValidationUtil;
import com.seda.j2ee5.maf.core.action.ActionData;
import com.seda.j2ee5.maf.core.action.ComplexActionTable;
import com.seda.j2ee5.maf.core.flow.FlowData;
import com.seda.j2ee5.maf.core.flow.FlowManager;
import com.seda.j2ee5.maf.core.flow.FlowManagerHandler;
import com.seda.j2ee5.maf.util.MAFAttributes;
import com.seda.j2ee5.maf.util.MAFLogger;
import com.seda.j2ee5.maf.util.MAFRequest;
import com.seda.j2ee5.maf.util.MAFUtil;
import com.seda.j2ee5.maf.util.URLUtil;
/**
 * This class looks at the request URL and maps the request
 * to the page for the web template manager
 * 
 * @author Seda Lab
 *
 */
public class ScreenManager {

	private static final LoggerWrapper logger =  CustomLoggerManager.get(ScreenManager.class);
	
    private HashMap<String, ActionData> actions;
    private ComplexActionTable complexActionTable;
    private ExceptionManager exceptionManager;    

    public ScreenManager() {}

    public void init(ExceptionManager exceptionManager, HashMap<String, ActionData> actions,
    		ComplexActionTable complexActionTable) {    
    	this.actions = actions;
    	this.complexActionTable=complexActionTable;
    	if (exceptionManager!=null) this.exceptionManager = exceptionManager;
    }
    /**
     * The ActionData object contains information that will match
     * a url to a mapping object that contains information about
     * the current screen, the Action that is needed to
     * process a request, and the WebAction that is needed
     * to insure that the proper screen is displayed.
    */
    private ActionData getActionData(String action) {
        if ((actions != null) && actions.containsKey(action)) {
            return actions.get(action);
        } else {
            return null;
        }
    }

    public ExceptionData getExceptionData(String exceptionClassName) {
    	if (exceptionManager!=null) 
    		return exceptionManager.getExceptionData(exceptionClassName);
    	return null;
    }
    /**
     * Using the information we have in the request along with
     * The url map for the current url we will insure that the
     * proper current screen is selected based on the settings
     * in both the maf-screens.xml and maf-actions.xml files.
    */
    public void forwardToNextScreen(HttpServletRequest request, HttpServletResponse response)
              throws java.io.IOException, javax.servlet.ServletException {
    	String application = (String) request.getAttribute(MAFAttributes.CURRENT_APPLICATION);
        // gets the uri
        String fullURL = request.getRequestURI();
//        String selectedURL = URLUtil.getUrlKey(fullURL,defaultScreen);
        String selectedURL = URLUtil.getTargetUrl(fullURL);
        if (complexActionTable.contains(selectedURL))
        	selectedURL=complexActionTable.get(selectedURL);
        
        String nextApplication = application;
        String nextTo = null;
        ScreenWriterData currentScreenWriterData = null;
        ScreenRedirectData currentScreenRedirectData = null;
        ActionData actionData = getActionData(selectedURL);
        if (actionData != null) {
        	if (actionData.hasFlow()) {
        		// load the flow Manager for this action
                FlowManager flowManager = null;
                String flowManagerClass = actionData.getFlowClass();
                try {
                	Object flowObject = getClass().getClassLoader().loadClass(flowManagerClass).newInstance();
                	flowManager = (FlowManager) flowObject;
                	if (FlowManagerHandler.class.isAssignableFrom(flowObject.getClass())) {
            			FlowManagerHandler flowManagerHandler = (FlowManagerHandler) flowObject;
                		Properties flowProps = actionData.getFlowProperties();
                		Properties flowSets = actionData.getFlowSettings();
                		if (flowProps!=null) {
                			Enumeration enumKeys = flowProps.keys(); 
                    		while (enumKeys.hasMoreElements()) {
								String key = (String) enumKeys.nextElement();
								flowManagerHandler.addProperty(key, flowProps.getProperty(key));
							}                			
                		}
                		if (flowSets!=null) {
                			Enumeration enumKeys = flowSets.keys(); 
                    		while (enumKeys.hasMoreElements()) {
								String key = (String) enumKeys.nextElement();
								flowManagerHandler.addSetting(key, flowSets.getProperty(key));
							}                			
                		}                		
                	}
                    // invoke the processFlow(HttpServletRequest)
                	flowManager.start(request);
                    String flowResult = flowManager.process(request);
                    flowManager.end(request);
                    FlowData flowData = actionData.getResultScreen(flowResult); 
                    if (flowData==null) {
                        // if there were no screens by the id then assume that the result was
                        // the screen itself
                    	nextTo = flowResult;
                    } else {
                    	if (flowData.isScreen()) {
                    		nextTo = flowData.getScreen();
                    		nextApplication = flowData.getApplid();
                    		// reply parameter found in the result screen attribute
                    		if (flowData.hasParameters()) {
                    			MAFUtil.applyParameters(request, flowData.getParameters());
                    		}
                    	} else if (flowData.isWriter()) {
                    		currentScreenWriterData=flowData.getScreenWriterData();
                    	} else if (flowData.isRedirect()) {
                    		currentScreenRedirectData=flowData.getScreenRedirectData();
                    	} else {
                    		nextTo = flowResult;
                    	}
                    }
               } catch (Exception ex) {
            	   logger.error(MAFLogger.getMessage("generic_application_exception", actionData.getApplid()),ex);
               }
        	} else if (actionData.useWriter()) {
        		currentScreenWriterData=actionData.getWriterData();
        	} else if (actionData.useRedirect()) {
        		currentScreenRedirectData=actionData.getRedirectData();
        	} else {
        		nextTo = actionData.getScreen();
        	}
        }

        if (currentScreenWriterData!=null) {
        	// tell to the CSRDefender to reuse the previous token
    		CSRFDefender.setWriter(request);
    		// restore validation information
    		ValidationUtil.restoreValidation(new MAFRequest(request));
    		ScreenWriter screenWriter = null;
    		Throwable t=null;
    		try {
    			screenWriter = new ScreenWriter();
    			screenWriter.init(currentScreenWriterData, actionData.getApplid());
    			screenWriter.write(request, response);
    		} catch (Exception ex) {
    			t = ex;
    			logger.error(MAFLogger.getMessage("screen_manager_writer_exception", actionData.getApplid()),ex);
            } finally {
            	if (screenWriter!=null) screenWriter.end(t);
            	screenWriter=null;
            }
        	return;
        }
        
        if (currentScreenRedirectData!=null) {
    		// tell to the CSRDefender to reuse the previous token
    		CSRFDefender.setWriter(request);
    		ScreenRedirect screenRedirect = null;
    		try {
    			screenRedirect = new ScreenRedirect();
    			screenRedirect.init(currentScreenRedirectData, actionData.getApplid());
    			screenRedirect.redirect(request, response);
    			screenRedirect.end();
    		} catch (Exception ex) {
    			logger.error(String.format("[{0}] RedirectManager caught exception", actionData.getApplid()) ,ex);
            } finally {
            	if (screenRedirect!=null) screenRedirect.end();
            	screenRedirect=null;
            }        	
        	return;
        }
        
        if (nextTo == null) {
        	String message = MAFLogger.getMessage("screen_manager_screen_not_found", application, selectedURL);
        	logger.error(message);
        	throw new RuntimeException(message);
        }
        
        if (!application.equals(nextApplication)) {
        	request.setAttribute(MAFAttributes.CURRENT_APPLICATION,nextApplication);
        }

        ServletContext context = request.getSession().getServletContext();
        context.getRequestDispatcher(nextTo).forward(request, response);        	
    }
    
    /**
     * go through the list and use the Class.isAssignableFrom(Class method)
     * to see it is a subclass of one of the exceptions
     */
    @SuppressWarnings("unchecked")
	public ExceptionData getExceptionData(Throwable e, String application) {
    	if (exceptionManager==null) return null;
        Iterator<String> it = exceptionManager.getExceptions().keySet().iterator();
        while (it.hasNext()) {
            String exceptionClassName = it.next();
            logger.warn(MAFLogger.getMessage("screen_manager_checking_exception", application, exceptionClassName, e.getClass().getName()));
            
            Class targetExceptionClass = null;
            try {
                targetExceptionClass = this.getClass().getClassLoader().loadClass(exceptionClassName);
            } catch (ClassNotFoundException cnfe) {
            	logger.error(MAFLogger.getMessage("screen_manager_exception_not_load", application, exceptionClassName));
            }
            // check if the exception is a sub class of matches the exception
            boolean assignableFrom = false;
            if (targetExceptionClass != null) {
            	assignableFrom = targetExceptionClass.isAssignableFrom(e.getClass());	
            }
			if ((targetExceptionClass != null) && assignableFrom) {
            	logger.info(MAFLogger.getMessage("screen_manager_screen_found_for_ex", application, exceptionManager.getExceptionData(exceptionClassName), exceptionClassName));				
                return exceptionManager.getExceptionData(exceptionClassName);
            }            
        }
        return null;
    }

    /**
     * Returns the current screen
     */
    public String getCurrentScreen(HttpSession session)  {
        return (String)session.getAttribute(MAFAttributes.CURRENT_SCREEN);
    }	

}
