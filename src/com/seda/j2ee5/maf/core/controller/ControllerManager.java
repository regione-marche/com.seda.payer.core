package com.seda.j2ee5.maf.core.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Locale;

import javax.servlet.GenericServlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.ThreadContext;

import com.seda.commons.logger.CustomLoggerManager;
import com.seda.commons.logger.LoggerWrapper;
import com.seda.commons.string.Convert;
import com.seda.j2ee5.maf.core.action.ActionManager;
import com.seda.j2ee5.maf.core.application.ApplicationsData;
import com.seda.j2ee5.maf.core.application.ApplicationsXmlDao;
import com.seda.j2ee5.maf.core.screen.ExceptionData;
import com.seda.j2ee5.maf.core.screen.ScreenManager;
import com.seda.j2ee5.maf.core.screen.ScreenWriter;
import com.seda.j2ee5.maf.util.MAFAttributes;
import com.seda.j2ee5.maf.util.MAFLogger;
/**
 * Servlet implementation class Controller. This servlet reflects the FrontController pattern
 */
public class ControllerManager extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private static final LoggerWrapper logger =  CustomLoggerManager.get(ControllerManager.class);

    private ServletContext context;
    private Locale defaultLocale = null;
    
    private HashMap<String, ActionManager> actionManagers;
    private HashMap<String, ScreenManager> screenManagers;    
    private HashMap<String, String> controllerServletMap;    
    private boolean dormant;    
    
    protected void setDormant(boolean dormant) {this.dormant=dormant;}
    protected void putActionManager(String application, ActionManager actionManager) {
    	if (actionManagers==null)
    		actionManagers=new HashMap<String, ActionManager>();
    	actionManagers.put(application, actionManager);
    }   

    protected void putScreenManager(String application, ScreenManager screenManager) {
    	if (screenManagers==null)
    		screenManagers=new HashMap<String, ScreenManager>();
    	screenManagers.put(application, screenManager);
    }

    protected void putControllerServletMap(String application, String controllerURL) {
    	if (controllerServletMap==null)
    		controllerServletMap=new HashMap<String, String>();
    	controllerServletMap.put(application, controllerURL);
    }      
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ControllerManager() {
        super();
    }
	/**
	 * @see GenericServlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		ThreadContext.put(MAFLogger.MDC_CTX, config.getServletContext().getServletContextName());		
		// get the default locale of the application
		String defaultLocaleString = config.getInitParameter("default_locale");
        defaultLocale = Convert.I18n.stringToLocale(defaultLocaleString);
        // store the general servlet context 
        this.context = config.getServletContext();
		
        ApplicationsData applicationsData = (ApplicationsData) context.getAttribute(MAFAttributes.APPLICATIONS);
        // if no applications was found, we are in a stopped state 
        // Warning: the applications must be instantiated from the application loader at the context created event        
        if (applicationsData==null) {
        	logger.error(MAFLogger.getMessage("application_data_missing"));
        	dormant=true;
        } else {
        	dormant=false;
        	this.controllerServletMap=applicationsData.getControllerServletMap();
        	this.actionManagers=applicationsData.getActionManagers();
            this.screenManagers=applicationsData.getScreenManagers();               	
        }
	}
	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected final void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// set the locale of the user to default if not set
        if (request.getSession().getAttribute(MAFAttributes.LOCALE) == null) {
            request.getSession().setAttribute(MAFAttributes.LOCALE, defaultLocale);
        }		
		super.service(request, response);
	}
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected final void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		process(request, response);		
	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected final void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		process(request, response);		
	}

	private final void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (dormant) {
        	String message = MAFLogger.getMessage("dormant_state");
        	logger.error(message);
			ScreenWriter.writeError(null, MAFLogger.getMessage("apologies"), message, response);        	
			return;
		}
		// get the application name
		// Warning: the applications must be instantiated from the application manager at filtering request
        String application = (String)request.getAttribute(MAFAttributes.CURRENT_APPLICATION);
        if (application==null) {
        	// if no current application was found in the request
        	String message = MAFLogger.getMessage("application_missing");
        	logger.error(message);
			ScreenWriter.writeError(null, MAFLogger.getMessage("apologies"), message, response);         	
        	return;
        }
        // process the request
        // checks for an owned application controller servlet
        String controllerServlet = controllerServletMap.get(application);
        // if none exists
        if (controllerServlet==null) {
        	// get the action manager        
            ActionManager actionManager = null;
            ScreenManager screenManager = null;        
            try {
            	actionManager = getActionManager(application);
            	if (actionManager==null) {
            		logger.warn(MAFLogger.getMessage("controller_manager_actionmanager_not_found",application));        			
            	} else {
            		// process the request
            		actionManager.process(request, response);        			
            	}
            	// process the screen dependency or the direct writer
            	screenManager = getScreenManager(application);  
            	if (screenManager==null) {
            		logger.warn(MAFLogger.getMessage("controller_manager_screenmanager_not_found",application));            		
            	} else {
            		screenManager.forwardToNextScreen(request,response);        			
            	}
            } catch (Throwable t) {
            	// get the class name of the exception; it would be used to forward to the right error page 
            	String tClassName = t.getClass().getName();
            	ExceptionData exceptionData = null;
            	if (screenManager==null) {
            		// this means that the exception was in the action manager
            		screenManager = getScreenManager(application);  
                	if (screenManager==null) {
                		logger.warn(MAFLogger.getMessage("controller_manager_screenmanager_not_found",application));
                	}
            	}
            	if (screenManager!=null) {
            		exceptionData = screenManager.getExceptionData(t,application);        			
            	}
            	// put the exception in the request
            	request.setAttribute("maf_controller_exception", t);
            	// if no error screen exists for the exception class
            	if (exceptionData == null || exceptionData.getScreen()==null || exceptionData.getScreen().trim().length()==0) {
            		// log the stacktrace
                	logger.error(MAFLogger.getMessage("controller_manager_stacktrace",application,t.getMessage()), t);            		
            		// print the error to the general error screen
//            		t.printStackTrace();
            		// throws ServletException with full information
            		String message = MAFLogger.getMessage("controller_manager_unknown_exception",application,tClassName);
            		logger.warn(message);
            		logger.warn(MAFLogger.getMessage("controller_manager_unknown_exception1",application,request.getRequestURI()));
//            		logger.warn(MAFLogger.getMessage("controller_manager_unknown_exception2",application,actionManager));
            		throw new ServletException(message);
            	}
            	// switch to the correct application
            	if (!application.equals(exceptionData.getApplid())) {
                	request.setAttribute(MAFAttributes.CURRENT_APPLICATION,exceptionData.getApplid());
                }
            	context.getRequestDispatcher(exceptionData.getScreen()).forward(request, response);
            } finally {
            	actionManager=null;
            	screenManager=null;
            	application=null;        		
            }        	
        } else {
        	// forward the request to the own application controller
        	context.getRequestDispatcher(controllerServlet).forward(request, response);
        }
        
    }

	private ActionManager getActionManager(String applicationName) {
		ActionManager actionManager=null;
		if (actionManagers!=null)
			actionManager = actionManagers.get(applicationName);
		return actionManager;
	}

	private ScreenManager getScreenManager(String applicationName) {
		ScreenManager screenManager = null;
		if (screenManagers!=null) {
			screenManager = screenManagers.get(applicationName);	
		}
		return screenManager;
	}		
	
}
