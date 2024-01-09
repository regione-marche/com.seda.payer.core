/**
 * 
 */
package com.seda.commons.properties.tree;

/**
 * @author dbadm
 *
 */
public class PropertiesNodeException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = -643305370457228330L;

	public PropertiesNodeException(String message, Throwable cause) {
		super(message, cause);
	}

	public PropertiesNodeException(String message) {
		super(message);
	}

	public PropertiesNodeException(Throwable cause) {
		super(cause);
	}
}
