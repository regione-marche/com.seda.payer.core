package com.seda.j2ee5.maf.core.session;

import java.io.Serializable;

import javax.servlet.http.HttpSessionActivationListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import org.apache.logging.log4j.ThreadContext;

// import org.apache.log4j.ThreadContext;

import com.seda.commons.logger.CustomLoggerManager;
import com.seda.commons.logger.LoggerWrapper;
import com.seda.j2ee5.maf.components.defender.csrf.CSRFDefender;
import com.seda.j2ee5.maf.core.security.SignOnKeys;
import com.seda.j2ee5.maf.taglibs.PagingTag;
import com.seda.j2ee5.maf.util.MAFAttributes;
import com.seda.j2ee5.maf.util.MAFLogger;
/**
 * Application Lifecycle Listener implementation class SessionManager
 *
 */
public class SessionManager implements Serializable, HttpSessionListener, HttpSessionActivationListener {
	
	private static final long serialVersionUID = 1L;

	private static final LoggerWrapper logger =  CustomLoggerManager.get(SessionManager.class);
	
    /**
     * Default constructor. 
     */
    public SessionManager() {}

	/**
     * @see HttpSessionActivationListener#sessionDidActivate(HttpSessionEvent)
     */
    public void sessionDidActivate(HttpSessionEvent e) {
    	ThreadContext.put(MAFLogger.MDC_CTX, e.getSession().getServletContext().getServletContextName());    	
    	// Get this SessionClassManager
    	SessionClassManager sessionClassManager = (SessionClassManager)e.getSession().getAttribute(MAFAttributes.SESSION_MANAGER);
    	if (sessionClassManager!=null) {
    		// fire the destroyed event
    		sessionClassManager.activate(e);
    	}    	
    	String message = "Session activate for".concat(" id: ").concat(e.getSession().getId()).toString( );
    	logger.info(message);
    	ThreadContext.remove(MAFLogger.MDC_CTX);    	
    }

	/**
     * @see HttpSessionActivationListener#sessionWillPassivate(HttpSessionEvent)
     */
    public void sessionWillPassivate(HttpSessionEvent e) {
    	ThreadContext.put(MAFLogger.MDC_CTX, e.getSession().getServletContext().getServletContextName());    	
    	// Get this SessionClassManager
    	SessionClassManager sessionClassManager = (SessionClassManager)e.getSession().getAttribute(MAFAttributes.SESSION_MANAGER);
    	if (sessionClassManager!=null) {
    		// fire the destroyed event
    		sessionClassManager.passivate(e);
    	}
    	String message = "Session will passivate for".concat(" id: ").concat(e.getSession().getId()).toString( );
    	logger.info(message);
    	ThreadContext.remove(MAFLogger.MDC_CTX);    	
    }

	/**
     * @see HttpSessionListener#sessionCreated(HttpSessionEvent)
     */
    public void sessionCreated(HttpSessionEvent e) {
    	ThreadContext.put(MAFLogger.MDC_CTX, e.getSession().getServletContext().getServletContextName());
    	// save the session id 
    	e.getSession().setAttribute(SignOnKeys.SESSION_ID, e.getSession().getId());
    	// Create a new SessionClassManager programmatically
    	SessionClassManager sessionClassManager = new SessionClassManager(e);
    	// store it in the session
    	e.getSession().setAttribute(MAFAttributes.SESSION_MANAGER, sessionClassManager);
    	String message = "Session created with".concat(" id: ").concat(e.getSession().getId());
    	logger.info(message);
    	// csrf session created event management
    	CSRFDefender.enforceSession(e.getSession());
    	// remove log4j mdc
    	ThreadContext.remove(MAFLogger.MDC_CTX);    	
    }

	/**
     * @see HttpSessionListener#sessionDestroyed(HttpSessionEvent)
     */
    public void sessionDestroyed(HttpSessionEvent e) {
    	ThreadContext.put(MAFLogger.MDC_CTX, e.getSession().getServletContext().getServletContextName());    	
    	// Get this SessionClassManager
    	SessionClassManager sessionClassManager = (SessionClassManager)e.getSession().getAttribute(MAFAttributes.SESSION_MANAGER);
    	if (sessionClassManager!=null) {
    		// fire the destroyed event
    		sessionClassManager.destroyed(e);
    	}
    	// remove the session managers
    	e.getSession().removeAttribute(MAFAttributes.SESSION_MANAGER);
    	String message = "Session destroyed for".concat(" id: ").concat(e.getSession().getId()).toString( );
    	logger.info(message);
    	ThreadContext.remove(MAFLogger.MDC_CTX);    	
    }
   
}
