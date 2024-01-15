/**
 * 
 */
package com.seda.j2ee5.maf.core.security;

/**
 * This class represents the exception of a sign on action. 
 * 
 * Set the valid state of this exception (<code>STATE_NOT_AUTHENTICATED</code> is the default) using the following rules:</br>
 * <table border="0">
 * <tr><td><b>State</b><td><td><b>Reason</b></td></tr>
 * <tbody>
 * <tr><td>STATE_ERROR<td><td>in case of a general failure</td></tr>
 * <tr><td>STATE_INVALID<td><td>in case of a not existing account</td></tr>
 * <tr><td>STATE_NOT_AUTHENTICATED<td><td>in case of a bad user name or password</td></tr>
 * </tbody>
 * </table>
 *  
 * 
 * @author Seda Lab
 *
 */
public class SignOnException extends Exception {

	public static final int STATE_NOT_FOUND=1;
	public static final int STATE_ERROR=3;
	public static final int STATE_NOT_AUTHENTICATED=7;
	
	private static final long serialVersionUID = 1L;
	
	private int state=STATE_NOT_AUTHENTICATED;
	
	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	/**
	 * @param message
	 */
	public SignOnException(String message) {
		this(message,STATE_NOT_AUTHENTICATED);
	}
	/**
	 * @param message
	 * @param state
	 */
	public SignOnException(String message, int state) {
		super(message);
		this.state=state;
	}

	/**
	 * @param cause
	 */
	public SignOnException(Throwable cause) {
		this(cause,STATE_NOT_AUTHENTICATED);
	}
	/**
	 * @param cause
	 * @param state
	 */
	public SignOnException(Throwable cause, int state) {
		super(cause);
		this.state=state;
	}
	/**
	 * @param message
	 * @param cause
	 */
	public SignOnException(String message, Throwable cause) {
		this(message, cause, STATE_NOT_AUTHENTICATED);
	}
	
	/**
	 * @param message
	 * @param cause
	 * @param state
	 */
	public SignOnException(String message, Throwable cause, int state) {
		super(message, cause);
		this.state=state;
	}

}
