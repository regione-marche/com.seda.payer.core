/**
 * 
 */
package com.seda.commons.security;

import java.io.Serializable;
import java.net.InetAddress;
import java.util.HashMap;
import java.util.UUID;

/**
 * @author f.ricci
 *
 */
public class UUIDGenerator implements Serializable {

	private static final long serialVersionUID = 1L;
	private String sep = "";
	private String bit_format = "";

	public static final String BIT_FRMT_4 = "4";
	public static final String BIT_FRMT_8 = "8";
	public static final String BIT_FRMT_12 = "12";
	public static final String BIT_FRMT_16 = "16";
	public static final String BIT_FRMT_32 = "32";

	private static final int IP;

	static {
		int ipadd;
		try {
			ipadd = toInt( InetAddress.getLocalHost().getAddress() );
		} catch (Exception e) {
			ipadd = 0;
		}
		IP = ipadd;
	}

	private static short counter = (short) 0;

	private static final int JVM = (int) ( System.currentTimeMillis() >>> 8 );

	public UUIDGenerator(String bit_format) {
		this.bit_format = bit_format;
	}
	
	public UUIDGenerator(String bit_format, String sep) {
		this.bit_format = bit_format;
		this.sep=sep;
	}

	public static int toInt( byte[] bytes ) {
		int result = 0;
		for (int i=0; i<4; i++) {
			result = ( result << 8 ) - Byte.MIN_VALUE + (int) bytes[i];
		}
		return result;
	}
	/**
	 * Unique across JVMs on this machine (unless they load this class
	 * in the same quater second - very unlikely)
	 */
	 protected int getJVM() {
		return JVM;
	}
	/**
	 * Unique in a millisecond for this JVM instance (unless there
	 * are > Short.MAX_VALUE instances created in a millisecond)
	 */
	 protected short getCount() {
		 synchronized(UUIDGenerator.class) {
			 if (counter<0) counter=0;
			 return counter++;
		 }
	 }
	 /**
	  * Unique in a local network
	  */
	 protected int getIP() {
		 return IP;
	 }

	 /**
	  * Unique down to millisecond
	  */
	 protected short getHiTime() {
		 return (short) ( System.currentTimeMillis() >>> 32 );
	 }
	 /**
	  * @return
	  */
	 protected int getLoTime() {
		 return (int) System.nanoTime();
	 }
	 /**
	  * @param intval
	  * @return
	  */
	 protected String format(int intval) {
		 String formatted = Integer.toHexString(intval);
		 StringBuffer buf = new StringBuffer("00000000");
		 buf.replace( 8-formatted.length(), 8, formatted );
		 return buf.toString();
	 }

	 /**
	  * @param shortval
	  * @return
	  */
	 protected String format(short shortval) {
		 String formatted = Integer.toHexString(shortval);
		 StringBuffer buf = new StringBuffer("0000");
		 buf.replace( 4-formatted.length(), 4, formatted );
		 return buf.toString();
	 }

	 /**
	  * generateSecureToken
	  */
	 public Serializable generateSecureToken() {
		 if (bit_format == null)
			 bit_format = BIT_FRMT_32;

		 switch (Integer.parseInt(bit_format)) {
		 case 4 :
			 return new StringBuffer(4)
			 .append( format( getCount() ) )
			 .toString();
		 case 8 :
			 return new StringBuffer(8)
			 .append( format( getLoTime() ) ).append(sep)
			 .toString();
		 case 12 :
			 return new StringBuffer(12)
			 .append( format( getLoTime() ) ).append(sep)
			 .append( format( getCount() ) )
			 .toString();
		 case 16 :
			 return new StringBuffer(16)
			 .append( format( getIP() ) ).append(sep)
			 .append( format( getJVM() ) ).append(sep)
			 .toString();
		 case 32 :
			 return new StringBuffer(32)
			 .append( format( getIP() ) ).append(sep)
			 .append( format( getJVM() ) ).append(sep)
			 .append( format( getHiTime() ) ).append(sep)
			 .append( format( getLoTime() ) ).append(sep)
			 .append( format( getCount() ) )
			 .toString();
		 default :
			 return new StringBuffer(32)
			 .append( format( getIP() ) ).append(sep)
			 .append( format( getJVM() ) ).append(sep)
			 .append( format( getHiTime() ) ).append(sep)
			 .append( format( getLoTime() ) ).append(sep)
			 .append( format( getCount() ) )
			 .toString();
		 }
	 }
	 /**
	  * @param args
	  */
	 public static void main(String[] args) {
		 try {
			 int loops=20;
			 if (args.length==1)
				 loops=Integer.parseInt(args[0]);
			 
			 HashMap<Serializable, String> hashMap = new HashMap<Serializable, String>();
			 System.out.println(new java.sql.Timestamp(System.currentTimeMillis()));
			 UUIDGenerator generator = new UUIDGenerator(BIT_FRMT_32,"-");
			 for (int i = 0; i < loops; i++) {
				 hashMap.put(generator.generateSecureToken(), "...");
				 System.out.println(generator.generateSecureToken());
			 }
			 System.out.println(hashMap.size());
			 System.out.println(new java.sql.Timestamp(System.currentTimeMillis()));
		 } catch (Exception e) {
			 e.printStackTrace();
		 }
	 }
}
