/**
 * 
 */
package com.seda.j2ee5.maf.core.action;

import java.io.Serializable;
import java.rmi.RemoteException;

import javax.servlet.http.HttpServletRequest;

/**
 * The base interface to service a web request
 * @author Sed Lab
 *
 */
public interface ActionService extends Serializable {

	/**
	 * Performs the action service
	 * 
	 * @param request the HttpServletRequest of the action
	 * @return <code>Object</code> a reserved event Object
	 * @throws ActionException if an error occurred
	 */	
	public Object service(HttpServletRequest request) throws ActionException;
	
}
