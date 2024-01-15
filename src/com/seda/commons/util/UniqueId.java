/**
 * 
 */
package com.seda.commons.util;

import java.math.BigInteger;
import java.rmi.server.UID;
import java.security.SecureRandom;
import java.util.UUID;

import com.seda.commons.string.Pad;

/**
 * @author dbadm
 *
 */
public class UniqueId {
    private static char[] digits = {'0', '1', '2', '3', '4','5','6','7','8','9','a','b','c','d','e','f'};
    
	/**
	  * Sometimes bytes does not have a nice textual representation
	  * so some form of encoding is usually performed.
	  *
	  * This implementation follows the example of David Flanagan's book
	  * "Java In A Nutshell", and converts a byte array into a String
	  * of hex characters.
	  *
	  * Another popular alternative is to use a "Base64" encoding.
	  */
	  public static String hexEncode( byte[] aInput){
	    StringBuilder result = new StringBuilder();
	    for ( int idx = 0; idx < aInput.length; ++idx) {
	      byte b = aInput[idx];
	      result.append( UniqueId.digits[ (b&0xf0) >> 4 ] );
	      result.append( UniqueId.digits[ b&0x0f] );
	    }
	    return result.toString();
	  }
	
	  /**
	   * Build some UID random string. The string is unique only in the host where generated.
	   */
	   public static String getUniqueUID() {
	       return new UID().toString();
	   }
	  /**
	   * Build some UUID random number.
	   */
	   public static String getUniqueUUID() {
	       return UUID.randomUUID().toString();
	   }
	   /**
	    * Build some random string from SecureRandom
	    */
	   public static String getUnique(int len) {
		   return Pad.right(new BigInteger(len*5, new SecureRandom()).toString(32),len,'f');
	   }		   

}
