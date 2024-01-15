/**
 * 
 */
package com.seda.commons.security;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Random;
import java.util.UUID;

import org.apache.commons.codec.binary.Base64;

/**
 * @author Seda Lab
 *
 */
public class TokenGenerator {
	
	public final static String RNG = "SHA1PRNG"; 
	
	public final static String PROVIDER_SECURE_RANDOM = "SECR";
	public final static String PROVIDER_UUID = "UUID";
	public final static String PROVIDER_INTERNAL = "INTR";
	private static Random rnd = new Random();
	private static String VALID_NUMBER = "0123456789";
	private static String VALID_L_CHARACTER = "abcdefghijklmnopqrstuvwxyz";	
	private static String VALID_U_CHARACTER = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	private static String VALID_CHARACTER = VALID_NUMBER + VALID_L_CHARACTER + VALID_U_CHARACTER;	
	
	public static String generateSecureRandomToken(String prng, int length) throws NoSuchAlgorithmException {
		SecureRandom sr = SecureRandom.getInstance(prng);
		byte[] bytes = new byte[length];
		
		sr.nextBytes(bytes);
		byte[] encodedBytes = new Base64().encode(bytes);
		String encoded = new String(encodedBytes);
		return encoded;
	}
	
	public static String generateUUIDToken() throws NoSuchAlgorithmException {
		return generateUUIDToken(false);
	}
	
	public static String generateUUIDToken(boolean replace) throws NoSuchAlgorithmException {
		return generateUUIDToken(replace,'f');
	}	
	
	public static String generateUUIDToken(boolean replace, char c) throws NoSuchAlgorithmException {
		String s = UUID.randomUUID().toString();
		if (replace) {
			return s.replace('-', c);
		} else {
			return s;
		}
	}
	
	public static String generateInternalToken(int length) {
		StringBuilder sb = new StringBuilder( length ); 
		for( int i = 0; i < length; i++ ) {
			synchronized (rnd) {
				sb.append( VALID_CHARACTER.charAt(rnd.nextInt(VALID_CHARACTER.length())));				
			}
		}
		return sb.toString();
	}

	public static String generateUInternalToken(int length) {
		StringBuilder sb = new StringBuilder( length ); 
		for( int i = 0; i < length; i++ ) {
			synchronized (rnd) {
				sb.append( VALID_U_CHARACTER.charAt(rnd.nextInt(VALID_U_CHARACTER.length())));				
			}
		}
		return sb.toString();
	}
	
	public static String generateLInternalToken(int length) {
		StringBuilder sb = new StringBuilder( length ); 
		for( int i = 0; i < length; i++ ) {
			synchronized (rnd) {
				sb.append( VALID_L_CHARACTER.charAt(rnd.nextInt(VALID_L_CHARACTER.length())));				
			}
		}
		return sb.toString();
	}
	
	public static String generateSecureToken(String prng, int length) throws NoSuchAlgorithmException {
		String s = generateSecureRandomToken(prng, length);
		
		return secureNormalization(s, prng, true);
	}	
	
	public static String generateSecureToken(int length) throws NoSuchAlgorithmException {
		return generateSecureToken(RNG, length);
	}	
	
	/**
	 * Make sure the token only has letters and numbers
	 * @param s
	 */
	public static String normalize(String s, String prng) throws NoSuchAlgorithmException {
		return secureNormalization(s, prng, false);
	}
	
	/**
	 * Make sure the token only has letters and numbers.
	 * If we catch a bad char, we optionally replace it
	 * with an 'a'
	 * @param s
	 * @param replace
	 */
	public static String secureNormalization(String s, String prng, boolean replace) throws NoSuchAlgorithmException {
		StringBuffer sb = new StringBuffer();
		int len = (s == null ? -1 : s.length());
		
		for(int i=0; i<len; i++) {
			char c = s.charAt(i);
			
			if((c >= 'a' && c <='z') || (c >= 'A' && c <='Z') || (c >= '0' && c <= '9')) {
				sb.append(c);
			} else if(replace) {
				char rnd = generateRandomChar(prng);
				sb.append(rnd);
			}
		}
		
		return sb.toString();
	}
	
	public static char generateRandomChar(String prng) throws NoSuchAlgorithmException {
		synchronized (rnd) {
			return (char)(rnd.nextInt(VALID_L_CHARACTER.length())+97);
		}
	}
	
}
