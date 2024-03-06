/**
 * 
 */
package com.seda.j2ee5.maf.core.application;

/**
 * @author Seda Lab
 *
 */
public class ApplicationStarterException extends Exception {

	private static final long serialVersionUID = 1L;

	/**
	 * @param message
	 */
	public ApplicationStarterException(String message) {
		super(message);
	}

	/**
	 * @param cause
	 */
	public ApplicationStarterException(Throwable cause) {
		super(cause);
	}

	/**
	 * @param message
	 * @param cause
	 */
	public ApplicationStarterException(String message, Throwable cause) {
		super(message, cause);
	}

}
