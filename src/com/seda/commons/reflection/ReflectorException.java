/**
 * 
 */
package com.seda.commons.reflection;

/**
 * @author f.ricci
 *
 */
public class ReflectorException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	/**
	 * @param message
	 */
	public ReflectorException(String message) {
		super(message);
	}

	public ReflectorException(String message, Throwable cause) {
		super(message, cause);
	}

}
