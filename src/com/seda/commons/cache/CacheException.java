/**
 * 
 */
package com.seda.commons.cache;

/**
 * @author f.ricci
 *
 */
public class CacheException extends RuntimeException {

	public CacheException(String message) {
		super(message);
	}

	public CacheException(String message, Throwable cause) {
		super(message, cause);
	}

}
