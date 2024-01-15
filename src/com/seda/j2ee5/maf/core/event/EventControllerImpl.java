/**
 * 
 */
package com.seda.j2ee5.maf.core.event;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.seda.commons.logger.CustomLoggerManager;
import com.seda.commons.logger.LoggerWrapper;
import com.seda.j2ee5.maf.util.MAFCookies;
import com.seda.j2ee5.maf.util.MAFLogger;

/**
 * @author f.ricci
 *
 */
public class EventControllerImpl implements EventController {

	private static final LoggerWrapper logger =  CustomLoggerManager.get(EventControllerImpl.class);
	
	public EventResult handleEvent(String applid, Event ev, HttpServletRequest request, HttpServletResponse response)  {
		EventResult result =null;		
		if (ev instanceof WebEvent) {
			WebEvent webEvent = (WebEvent) ev;
			EventActionBase webAction =null;
			try {
				webAction = getWebEventClass(applid, webEvent);
				MAFCookies.applyCookiesSupport(webAction, request, response);
				result = webAction.processEvent(request, webEvent);				
			} catch (Throwable t) {
				result = new EventResultImpl();
				result.setThrowable(t);
			} finally {
				webAction=null;
			}
		}
		return result;
	}
	
	public EventActionBase getWebEventClass(String applid, WebEvent webEvent) throws EventException {
		if (webEvent.getWebClassName()==null) {
			String message = String.format("[%s] Web event %s has a null web event class",
        			MAFLogger.formatApplid(applid),webEvent.getClass().getName());
			logger.error(message);
        	throw new EventException(message);
		}
		EventActionBase action=null;
		try {
			action = (EventActionBase)getClass().getClassLoader().loadClass(webEvent.getWebClassName()).newInstance();
        } catch (Exception ex) {
        	String message = String.format("[%s] Error loading web event action class %s",
        			MAFLogger.formatApplid(applid),webEvent.getWebClassName());
        	logger.error(message);
        	throw new EventException(message,ex);
        }
        return action;
	}
}
