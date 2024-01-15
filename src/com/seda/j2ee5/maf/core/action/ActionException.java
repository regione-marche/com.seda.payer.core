/**
 * 
 */
package com.seda.j2ee5.maf.core.action;

import java.io.Serializable;

/**
 * Base class for all the action runtime exceptions
 * @author Seda Lab
 *
 */
public class ActionException extends Exception implements Serializable {

	public ActionException(String message, Throwable cause) {
		super(message, cause);
	}

	public ActionException(String message) {
		super(message);
	}

	public ActionException(Throwable cause) {
		super(cause);
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 7945873376779328446L;

}
