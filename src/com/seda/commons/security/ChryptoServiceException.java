/**
 * 
 */
package com.seda.commons.security;

/**
 * @author dbadm
 *
 */
public class ChryptoServiceException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8772895854116348167L;

	/**
	 * @param message
	 */
	public ChryptoServiceException(String message) {
		super(message);
	}

	/**
	 * @param cause
	 */
	public ChryptoServiceException(Throwable cause) {
		super(cause);
	}

	/**
	 * @param message
	 * @param cause
	 */
	public ChryptoServiceException(String message, Throwable cause) {
		super(message, cause);
	}

}
