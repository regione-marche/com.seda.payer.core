/**
 * 
 */
package com.seda.j2ee5.maf.core.session;

import java.io.Serializable;

import javax.servlet.http.HttpSessionEvent;

/**
 * This class defines the method used by the session handler 
 * @author Seda Lab
 *
 */
public interface SessionSupport extends Serializable {

	/**
	 * Activate event. Use this in case server clustering. 
	 */
	public Object activate(HttpSessionEvent se) throws SessionHandlerException;
	
	/**
	 * Passivate event. Use this in case server clustering. 
	 */	
	public Object passivate(HttpSessionEvent se) throws SessionHandlerException;
	
	/**
	 * Destroyed session event 
	 */
	public Object destroyed(HttpSessionEvent se) throws SessionHandlerException;
	
}
