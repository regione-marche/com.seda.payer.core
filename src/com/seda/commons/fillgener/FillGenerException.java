/**
 * 
 */
package com.seda.commons.fillgener;

/**
 * @author dbadm
 *
 */
public class FillGenerException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6253995948124907947L;

	/**
	 * @param message
	 */
	public FillGenerException(String message) {
		super(message);
	}

	/**
	 * @param cause
	 */
	public FillGenerException(Throwable cause) {
		super(cause);
	}

	/**
	 * @param message
	 * @param cause
	 */
	public FillGenerException(String message, Throwable cause) {
		super(message, cause);
	}

}
