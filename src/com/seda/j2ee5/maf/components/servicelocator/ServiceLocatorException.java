/**
 * 
 */
package com.seda.j2ee5.maf.components.servicelocator;

/**
 * @author Seda Lab
 *
 */
public class ServiceLocatorException extends Exception {

	private static final long serialVersionUID = 1L;

	/**
	 * @param message
	 */
	public ServiceLocatorException(String message) {
		super(message);
	}

	/**
	 * @param cause
	 */
	public ServiceLocatorException(Throwable cause) {
		super(cause);
	}

	/**
	 * @param message
	 * @param cause
	 */
	public ServiceLocatorException(String message, Throwable cause) {
		super(message, cause);
	}

}
