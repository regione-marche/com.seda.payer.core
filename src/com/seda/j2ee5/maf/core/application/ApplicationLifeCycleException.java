/**
 * 
 */
package com.seda.j2ee5.maf.core.application;

/**
 * @author Seda Lab
 *
 */
public class ApplicationLifeCycleException extends Exception {


	private static final long serialVersionUID = 1L;

	public ApplicationLifeCycleException(String message, Throwable cause) {
		super(message, cause);
	}

	public ApplicationLifeCycleException(String message) {
		super(message);
	}

	public ApplicationLifeCycleException(Throwable cause) {
		super(cause);
	}

}
