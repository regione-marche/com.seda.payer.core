/**
 * 
 */
package com.seda.j2ee5.maf.core.action;

import java.io.Serializable;

import javax.servlet.ServletContext;
/**
 * The base interface to initialize an action
 * @author Seda Lab
 *
 */
public interface ActionInitialize extends Serializable {

	/**
	 * Performs the initialization of the action
	 * 
	 * @param context the {@link ServletContext} for the application
	 * @exception ActionException in case of error
	 */
	public void init(ServletContext context) throws ActionException;	

	
	/**
	 * Performs the ends of the action
	 * 
	 */
	public void end();	
}
