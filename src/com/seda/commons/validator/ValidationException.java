/**
 * 
 */
package com.seda.commons.validator;

/**
 * @author dbadm
 *
 */
public class ValidationException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2710957028550897679L;

	protected String key;
	
	
	public String getKey() {
		return key;
	}

	/**
	 * @param message
	 */
	public ValidationException(String key, String message) {
		super(message);
		this.key=key;
	}

}
