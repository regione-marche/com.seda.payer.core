/**
 * 
 */
package com.seda.j2ee5.maf.core.security;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

/**
 * The base interface to provide a sign on from a request 
 *  
 * @author Seda Lab
 *
 */
public interface SecuritySSO {

	/**
	 * Initializes the sign on support
	 * 
	 * @param context the ServletContext
	 * @throws SignOnException in case of unrecoverable error
	 */
	public void init(ServletContext context) throws SignOnException;
	
	/**
	 * Validates the authority from external request
	 * 
	 * @param request the http request containing sign on credentials 
	 * @return <code>UserBeanSupport</code> the user bean for the provided credentials
	 * @throws SignOnException in case of unrecoverable error 
	 */
	public UserBeanSupport authenticate(HttpServletRequest request) throws SignOnException;
	
	/**
	 * Ends the sign on support
	 * @throws SignOnException in case of unrecoverable error 
	 */
	public void term();
}
