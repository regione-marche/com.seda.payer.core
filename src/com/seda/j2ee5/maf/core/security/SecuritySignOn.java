/**
 * 
 */
package com.seda.j2ee5.maf.core.security;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
/** 
 * The base interface that provides a sign on from a form authentication request 
 *  
 * @author Seda Lab
 *
 */
public interface SecuritySignOn {

	
	/**
	 * Initializes the sign on support
	 * 
	 * @param context the ServletContext
	 * @throws SignOnException in case of unrecoverable error
	 */
	public void init(ServletContext context) throws SignOnException;
	
	/**
	 * Validates the authority for a provided Executes the sign on from external request
	 * 
	 * @param username the user name
	 * @param password the user name credentials
	 * @param request the current http request
	 * @return <code>UserBeanSupport</code> the user bean for the provided credentials
	 * @throws SignOnException in case of unrecoverable error
	 */
	public UserBeanSupport authenticate(String username, String password, HttpServletRequest request) throws SignOnException;
	
	/**
	 * Ends the sign on support
	 */
	public void term();
}
