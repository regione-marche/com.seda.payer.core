/**
 * 
 */
package com.seda.commons.security;

/**
 * @author dbadm
 *
 */
public interface ChryptoServiceHandler {

	public abstract byte[] encrypt(String value) throws ChryptoServiceException;
	public abstract byte[] decrypt(String value) throws ChryptoServiceException;	

}
