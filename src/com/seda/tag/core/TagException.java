package com.seda.tag.core;
/**
 * @author dbadm
 *
 */
public class TagException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 8127524373638783603L;

	/**
	 * @param message
	 */
	public TagException(String message) {
		super(message);
	}

	/**
	 * @param cause
	 */
	public TagException(Throwable cause) {
		super(cause);
	}

	/**
	 * @param message
	 * @param cause
	 */
	public TagException(String message, Throwable cause) {
		super(message, cause);
	}

}