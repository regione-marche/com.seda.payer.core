/**
 * 
 */
package com.seda.commons.maskedit;

/**
 * @author dbadm
 *
 */
public interface MaskMetaData {

	public static final String ALPHA_LOWER = "a,b,c,d,e,f,g,h,i,j,k,l,m,n,o,p,q,r,s,t,u,v,w,x,y,z";
	public static final String ALPHA_UPPER = "A,B,C,D,E,F,G,H,I,J,K,L,M,N,O,P,Q,R,S,T,U,V,W,X,Y,Z";
	public static final String ALPHA = MaskMetaData.ALPHA_LOWER + "," + MaskMetaData.ALPHA_UPPER;
	
	public static final String NUMERIC = "0,1,2,3,4,5,6,7,8,9";
	public static final String ALPHANUMERIC = MaskMetaData.ALPHA + "," + MaskMetaData.NUMERIC;
	
	public static final char MASK_ALPHA = 'a';
	public static final char MASK_NUMERIC = '9';
	public static final char MASK_ALPHANUMERIC = '*';	
	
	public static final char[] CHARS_OUT_DEFINITION = {',',';','[',']','{','}','"','°','\''};

	public static final char BLANK=' ';
	
}
