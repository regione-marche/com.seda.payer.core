/**
 * 
 */
package com.seda.j2ee5.maf.core.session;

import java.io.Serializable;
import java.lang.reflect.Constructor;
import java.util.HashMap;
import java.util.Iterator;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSessionEvent;

import com.seda.commons.logger.CustomLoggerManager;
import com.seda.commons.logger.LoggerWrapper;
import com.seda.j2ee5.maf.core.application.ApplicationsData;
import com.seda.j2ee5.maf.util.MAFAttributes;
import com.seda.j2ee5.maf.util.MAFLogger;
/**
 * @author Seda Lab
 *
 */
public class SessionClassManager implements Serializable {

	private static final long serialVersionUID = 1L;

	public final int EVENT_ACTIVATE = 1;
	public final int EVENT_PASSIVATE = 0;
	public final int EVENT_CREATED = -2;	
	public final int EVENT_DESTROYED = -1;	
	
	private static final LoggerWrapper logger =  CustomLoggerManager.get(SessionClassManager.class);
	
	private HashMap<String, String> sessionClasses;
	private HashMap<String, Serializable> sessionHandlers;	
	
	public SessionClassManager(HttpSessionEvent se) {
		loadSessionClasses(se.getSession().getServletContext());
		loadSessionHandlers(se);		
	}

	public void activate(HttpSessionEvent se) {
		fireEvent(se,EVENT_ACTIVATE);
	}
	
	public void passivate(HttpSessionEvent se) {
		fireEvent(se,EVENT_PASSIVATE);
	}
	
	public void destroyed(HttpSessionEvent se) {
		fireEvent(se,EVENT_DESTROYED);
	}	
	
	private void fireEvent(HttpSessionEvent se, int type) {
		if (sessionHandlers!=null) {
    		// loop through the classes and creates the object
    		Iterator<String> iterator = sessionHandlers.keySet().iterator(); 
            // load application if required
            while (iterator.hasNext (  )  )  {
            	String className = iterator.next();
            	SessionHandler handler = (SessionHandler)sessionHandlers.get(className);
            	try {
                	if (handler!=null) {
                		switch (type) {
    					case EVENT_ACTIVATE:
    						handler.activate(se);
    						break;
    					case EVENT_PASSIVATE:
    						handler.passivate(se);
    						break;
    					case EVENT_DESTROYED:
    						handler.destroyed(se);
    						break;						
                		}
                	}                		
            	} catch (SessionHandlerException x) {
        			logger.error(MAFLogger.getMessage("session_class_manager_caught", type, className),x);            		
            	}
            }
		}
	}
	
	/**
	 * Find in the context configuration the session class handlers 
	 */
	private void loadSessionClasses(ServletContext context) {
        ApplicationsData applicationsData = (ApplicationsData) context.getAttribute(MAFAttributes.APPLICATIONS);
        // if no applications was found, we are in a stopped state 
        // Warning: the applications must be instantiated from the application loader at the context created event        
        if (applicationsData==null) {
        	logger.error(MAFLogger.getMessage("application_data_missing"));
        } else {
        	this.sessionClasses=applicationsData.getSessionClasses();
        }		
	}

	/**
	 * Loop through the session class and create the session handler object
	 * 
	 */
    private void loadSessionHandlers(HttpSessionEvent se) {
    	if (sessionClasses!=null) {
    		sessionHandlers = new HashMap<String, Serializable>();
    		// loop through the classes and creates the object
    		Iterator<String> iterator = sessionClasses.keySet().iterator(); 
            // load application if required
            while (iterator.hasNext (  )  )  {
            	String applicationName = iterator.next();
            	String className = sessionClasses.get(applicationName);
            	if (className!=null && className.trim().length()>0) {
            		try {
            			SessionHandler sessionHandler = loadSessionClass(se, className);
            			sessionHandlers.put(className, sessionHandler);
            		} catch (Exception x) {
            			logger.error(MAFLogger.getMessage("session_class_manager_loading_ex", applicationName,className),x);
            		}
            	}
            }     		
    	}
	}	
	/**
     * This method load the necessary Session Handler class
	 * @throws Exception 
     */
    @SuppressWarnings("unchecked")
	private SessionHandler loadSessionClass(HttpSessionEvent se, String className) throws Exception {
    	SessionHandler sessionHandler = null;

    	Class<SessionHandler> clazz = (Class<SessionHandler>)Class.forName(className);
    	Constructor<SessionHandler> constructor = clazz.getConstructor(new Class[] {javax.servlet.http.HttpSessionEvent.class});
    	sessionHandler = constructor.newInstance(se);    		

    	return sessionHandler;
    }   	
}
