/**
 * 
 */
package com.seda.j2ee5.maf.core.action;

import java.lang.reflect.Field;
import java.util.HashMap;

//import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.seda.commons.logger.CustomLoggerManager;
import com.seda.commons.logger.LoggerWrapper;
import com.seda.j2ee5.maf.components.captcha.CaptchaManager;
import com.seda.j2ee5.maf.components.validation.ValidationContext;
import com.seda.j2ee5.maf.core.event.Event;
import com.seda.j2ee5.maf.core.event.EventController;
import com.seda.j2ee5.maf.core.event.EventControllerImpl;
import com.seda.j2ee5.maf.core.event.EventException;
import com.seda.j2ee5.maf.core.event.EventResult;
import com.seda.j2ee5.maf.defender.repository.RuleSet;
import com.seda.j2ee5.maf.util.MAFCookies;
import com.seda.j2ee5.maf.util.MAFLogger;
import com.seda.j2ee5.maf.util.URLUtil;
/**
 * This class is responsible for processing all requests received from web client
 * and generating necessary events to modify data which are sent to WebController.
 *  
 * @author Seda Lab
 *
 */
public class ActionManager implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	private static final LoggerWrapper logger =  CustomLoggerManager.get(ActionManager.class);
	
	private HashMap<String, ActionData> actionsData;
	private ComplexActionTable complexActionTable;
	private EventController eventController;
	private CaptchaManager captchaManager;

    public ActionManager() {}

    public void init(HashMap<String, ActionData> actionsData, ComplexActionTable complexActionTable, CaptchaManager captchaManager) {
    	this.actionsData=actionsData;
    	this.eventController = new EventControllerImpl();
    	this.complexActionTable=complexActionTable;
    	this.captchaManager=captchaManager;
    }
    
    /**
     * The ActionData object contains information about 
     * a url and the object that contains information about
     * the current screen, the HTMLAction that is needed to
     * process, and the HTMLAction that is needed
     * to insure that the proper screen is displayed.
     */
    public ActionData getActionData(String url) {
        if ((actionsData != null) && actionsData.containsKey(url)) {
            return (ActionData)actionsData.get(url);
        } else {
            return null;
        }
    }
    
    /**
     * This method load the necessary HtmlAction class necessary to process a the
     * request for the specified URL.
     */
    private Class<HtmlAction> loadActionClass(ActionData actionData) {
    	Class<HtmlAction> handler = null;
        String actionClass = null;
        if (actionData != null) {
            actionClass = actionData.getActionClass();
            if (actionData.isAction()) {
                try {
                    handler = (Class<HtmlAction>)getClass().getClassLoader().loadClass(actionClass);
                } catch (Exception ex) {
                	logger.error(MAFLogger.getMessage("action_manager_loading_error",
                			actionData.getApplid(),actionClass),ex);
                }
            }
        }
        return handler;
    }    
     
    /**
     * This method is the core of the ControllerManager. It receives all requests
     *  and generates the necessary events.
     */
     public void process(HttpServletRequest request, HttpServletResponse response) throws ActionException, EventException, ServletException {
    	 // gets the uri
         String fullURL = request.getRequestURI();
         String selectedURL = URLUtil.getTargetUrl(fullURL);
         if (complexActionTable.contains(selectedURL))
        	 selectedURL=complexActionTable.get(selectedURL);
         ActionData actionData = getActionData(selectedURL);
         Class<HtmlAction> actionClass = loadActionClass(actionData);
         if (actionClass != null) {
        	 HtmlAction action=istantiateActionObject(actionData.getApplid(),actionClass);
        	 if (action!=null) {
        		 MAFCookies.applyCookiesSupport(action, request, response);
        		 CaptchaManager.applyCaptchaManager(action, captchaManager);
                 action.init(request.getSession().getServletContext());
                 Object event = action.service(request);
                 if (event instanceof Event) {
                	 EventResult eventResult = eventController.handleEvent(actionData.getApplid(), (Event)event, request, response);
                	 action.end(eventResult);
                 } else {
                     action.end();            	 
                 }        		 
        	 }
         }
     }
     
	private HtmlAction istantiateActionObject(String applid, Class<HtmlAction> actionClass) {
    	 HtmlAction action=null;
    	 try {
    		 action = actionClass.newInstance();
    	 } catch (Exception x) {
    		 logger.error(String.format("[%s] Error instantiating action object '%s'",
         			applid,actionClass),x);
    	 }
    	 return action;
     }

	/**
      * Returns the defense rule set for a specified url action
      */
     public RuleSet getRuleSet(String url) {
    	 ActionData actionData = getActionData(url);
    	 if (actionData!=null)
    		 return actionData.getRuleSet();
    	 return null;
     }

     @Override
     public String toString() {
    	 return "ActionManager [actionsData=" + actionsData + "]";
     }    
    
}
