/**
 * 
 */
package com.seda.j2ee5.maf.components.defender.bfl;

/**
 * @author Seda Lab
 *
 */
public class BFLLockedException extends Exception {

	private static final long serialVersionUID = 1L;

	public BFLLockedException() {
		super();
	}

	public BFLLockedException(String message, Throwable cause) {
		super(message, cause);
	}

	public BFLLockedException(String message) {
		super(message);
	}

	public BFLLockedException(Throwable cause) {
		super(cause);
	}
	
}
