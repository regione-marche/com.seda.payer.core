/**
 * 
 */
package com.seda.j2ee5.maf.core.session;

import java.io.Serializable;

/**
 * @author Seda Lab
 *
 */
public class SessionHandlerException extends Exception implements Serializable {

	private static final long serialVersionUID = 1L;

	public SessionHandlerException(String message, Throwable cause) {
		super(message, cause);
	}

	public SessionHandlerException(String message) {
		super(message);
	}

	public SessionHandlerException(Throwable cause) {
		super(cause);
	}

}
