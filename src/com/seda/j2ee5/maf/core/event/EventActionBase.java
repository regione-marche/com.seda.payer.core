/**
 * 
 */
package com.seda.j2ee5.maf.core.event;

import javax.servlet.http.HttpServletRequest;

import com.seda.j2ee5.maf.util.MAFCookies;

/**
 * @author f.ricci
 *
 */
public abstract class EventActionBase implements EventAction {
	
	private MAFCookies cookiesSupport;
    
	protected MAFCookies getCookiesSupport() {
		return cookiesSupport;
	}
	
	public abstract EventResult processEvent(HttpServletRequest request, Event ev) throws EventException;

}
