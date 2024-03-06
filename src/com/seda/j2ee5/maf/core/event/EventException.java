/**
 * 
 */
package com.seda.j2ee5.maf.core.event;

/**
 * @author dbadm
 *
 */
public class EventException extends Exception {

	private static final long serialVersionUID = 1L;

	public EventException(String message, Throwable cause) {
		super(message, cause);
	}

	public EventException(String message) {
		super(message);
	}

	public EventException(Throwable cause) {
		super(cause);
	}

}
