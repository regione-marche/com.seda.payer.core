/**
 * 
 */
package com.seda.j2ee5.maf.core.event;

import javax.servlet.http.HttpServletRequest;

/**
 * @author f.ricci
 *
 */
public interface EventAction {

	public EventResult processEvent(HttpServletRequest request, Event ev) throws EventException; 
}
