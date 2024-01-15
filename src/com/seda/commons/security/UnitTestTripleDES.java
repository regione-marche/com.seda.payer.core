/**
 * 
 */
package com.seda.commons.security;

import java.io.UnsupportedEncodingException;

/**
 * @author dbadm
 *
 */
public class UnitTestTripleDES {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		//String s = "2009-12-15 09.15.09*TO   *QUARESIMA*AMMI";
		String s = "2009-09-15 10.15.09|TO   |SC02P5|AMMI";
		String e=null;
		String d=null;		
		
		TripleDESChryptoService des = new TripleDESChryptoService();
		try {
			des.setIv("12345678");			
			des.setKeyValue("123456789987654321123456");			
		} catch (UnsupportedEncodingException x) {
			x.printStackTrace();
		}

		try {
			print("StringOld " + des.encryptBase64(s));
			print("String " + s);
			e = des.encryptBASE64(s);
			print("Encrypt   " + e);
			d = des.decryptBase64(e);
			print("Decrypt " + d);			
		} catch (ChryptoServiceException e1) {
			e1.printStackTrace();
		}
		des = null;
	}

	private static void print(String message) {
		System.out.println(message);
	}
}
