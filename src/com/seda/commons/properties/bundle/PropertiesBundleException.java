package com.seda.commons.properties.bundle;

/**
 * @author SEDA Lab
 */
public class PropertiesBundleException extends Exception {
	/**
	 * Compiler creates it.
	 */
	private static final long serialVersionUID = -123298998530754472L;
	/**
	 * Constructor of PropertiesBundleException.
	 * @param message - The message that we pass in case of this exception.
	 * @param cause - The {@link Throwable} class is the superclass of all errors and exceptions in the Java language.
	 */
	public PropertiesBundleException(String message, Throwable cause) {
		super(message, cause);
	}
	/**
	 * Constructor of PropertiesBundleException.
	 * @param message - The message that we pass in case of this exception.
	 */
	public PropertiesBundleException(String message) {
		super(message);
	}
	/**
	 * Constructor of PropertiesBundleException.
	 * @param cause  - The {@link Throwable} class is the superclass of all errors and exceptions in the Java language.
	 */
	public PropertiesBundleException(Throwable cause) {
		super(cause);
	}

}
