/**
 * 
 */
package com.seda.j2ee5.maf.core.application;

import javax.servlet.ServletContext;

/**
 * This class is the base interface to load an application at the ServeletContext creation event
 * 
 * @author Seda Lab
 *
 */
public interface ApplicationStarter {

    public Object start(ServletContext context) throws ApplicationStarterException;
	
}
