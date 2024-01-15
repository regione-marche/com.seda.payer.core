/**
 * 
 */
package com.seda.j2ee5.maf.core.application;

import javax.servlet.ServletContext;

/**
 * @author Seda Lab
 *
 */
public interface ApplicationLifeCycle {

	/**
     * This method is called when context is created
     */
	public Object initialized(ServletContext context) throws ApplicationLifeCycleException;
	
	/**
     * This method is called when context is destroyed
     */  
	public Object destroyed(ServletContext context) throws ApplicationLifeCycleException;
	
}
