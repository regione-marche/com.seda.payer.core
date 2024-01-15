/**
 * 
 */
package com.seda.commons.maskedit;

/**
 * @author dbadm
 *
 */
public class MaskEditException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3460855558535774148L;

	/**
	 * @param message
	 */
	public MaskEditException(String message) {
		super(message);
	}

	/**
	 * @param cause
	 */
	public MaskEditException(Throwable cause) {
		super(cause);
	}

	/**
	 * @param message
	 * @param cause
	 */
	public MaskEditException(String message, Throwable cause) {
		super(message, cause);
	}

}
