/**
 * 
 */
package com.seda.j2ee5.maf.components.jms.pooling;

/**
 * @author dbadm
 *
 */
public class QueueSessionPoolException extends Exception {

	private static final long serialVersionUID = 1L;

	/**
	 * @param message
	 */
	public QueueSessionPoolException(String message) {
		super(message);
	}

	/**
	 * @param cause
	 */
	public QueueSessionPoolException(Throwable cause) {
		super(cause);
	}

	/**
	 * @param message
	 * @param cause
	 */
	public QueueSessionPoolException(String message, Throwable cause) {
		super(message, cause);
	}

}
