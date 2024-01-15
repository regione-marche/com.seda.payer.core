/**
 * 
 */
package com.seda.commons.maskedit;

/**
 * @author dbadm
 *
 */
public class MaskBaseDefinitions {

	private char alpha = MaskMetaData.MASK_ALPHA;
	private char numeric = MaskMetaData.MASK_NUMERIC;
	private char alphaNumeric = MaskMetaData.MASK_ALPHANUMERIC;
	
	public char getAlpha() {
		return alpha;
	}
	public void setAlpha(char alpha) {
		this.alpha = alpha;
	}
	public char getNumeric() {
		return numeric;
	}
	public void setNumeric(char numeric) {
		this.numeric = numeric;
	}
	public char getAlphaNumeric() {
		return alphaNumeric;
	}
	public void setAlphaNumeric(char alphaNumeric) {
		this.alphaNumeric = alphaNumeric;
	}
	
	
	
	
	
}
