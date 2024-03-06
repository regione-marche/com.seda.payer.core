/**
 * 
 */
package com.seda.j2ee5.maf.core.event;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author f.ricci
 *
 */
public interface EventController {

	public EventResult handleEvent(String applId, Event ev, HttpServletRequest request, HttpServletResponse response) throws EventException;
	
}
