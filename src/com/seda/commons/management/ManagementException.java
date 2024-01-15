/*
 * DynamicMBeanFacadeException.java
 *
 * Created on August 16, 2002, 11:34 AM
 */

package com.seda.commons.management;

/**
 *
 * @author  f.ricci
 */
@SuppressWarnings("serial")
public class ManagementException extends java.lang.Exception {
    
    /**
     * Creates a new instance of <code>DynamicMBeanFacadeException</code> without detail message.
     */
    public ManagementException() {
    }
    
    
    /**
     * Constructs an instance of <code>DynamicMBeanFacadeException</code> with the specified detail message.
     * @param msg the detail message.
     */
    public ManagementException(String msg) {
        super(msg);
    }


	public ManagementException(String message, Throwable cause) {
		super(message, cause);
	}


	public ManagementException(Throwable cause) {
		super(cause);
	}
    
    
}
