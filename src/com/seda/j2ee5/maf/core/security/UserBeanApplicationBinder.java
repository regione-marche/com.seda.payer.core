/**
 * 
 */
package com.seda.j2ee5.maf.core.security;

import java.io.Serializable;

import javax.servlet.http.HttpServletRequest;

/**
 * Provides the interface to retrieve the role associated to the user name
 * for a specific application. In most case, this binder enriched the user bean
 * with additional information.
 * 
 * @author Seda Lab
 *
 */
public interface UserBeanApplicationBinder extends Serializable {

	/**
	 * Initializes the role binder support support
	 * 
	 * @param request the HttpServletRequest
	 */
	public void init(HttpServletRequest request);
	
	/**
	 * Returns the roles for the input user bean and enriched the user bean
	 * @throws UserBeanApplicationBinderException in case of user error
	 */
	public String bind(UserBeanSupport userBean);
	
	/**
	 * Terminate the binder support 
	 */
	public void end();
}
