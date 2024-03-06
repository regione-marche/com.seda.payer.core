/**
 * 
 */
package com.seda.j2ee5.maf.core.flow;

/**
 * This exception will be thrown when there is an error processing a flow class
 * @author Seda Lab
 *
 */
public class FlowException extends Exception {

	private static final long serialVersionUID = 1L;

	/**
	 * @param message
	 */
	public FlowException(String message) {
		super(message);
	}

	/**
	 * @param cause
	 */
	public FlowException(Throwable cause) {
		super(cause);
	}

	/**
	 * @param message
	 * @param cause
	 */
	public FlowException(String message, Throwable cause) {
		super(message, cause);
	}

}
