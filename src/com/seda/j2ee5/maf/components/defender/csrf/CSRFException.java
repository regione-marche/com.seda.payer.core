/**
 * 
 */
package com.seda.j2ee5.maf.components.defender.csrf;

/**
 * @author Seda Lab
 *
 */
public class CSRFException extends Exception {

	private static final long serialVersionUID = 1L;

	public CSRFException(String message) {super(message);}

	public CSRFException(Throwable cause) {	super(cause);}

	public CSRFException(String message, Throwable cause) {	super(message, cause);}

}
