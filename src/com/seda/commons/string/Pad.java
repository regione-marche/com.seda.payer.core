package com.seda.commons.string;

/**
 * @author SEDA Lab
 */
public class Pad {
	/**
	 * Fills with space character from the left if len > length of the initial String, otherwise calls substring.
	 * @param string - The initial String.
	 * @param len - Number of space characters to pass.
	 * @return <code>String</code> - String with space characters + The initial String.
	 */
	public final static String left(String string, int len) {
		return Pad.left(string, len, ' ');
	}
	/**
	 * Fills with zero character from the left if len > length of the initial integer, otherwise calls substring.
	 * @param integer - The initial integer.
	 * @param len - Number of space characters to pass.
	 * @return <code>String</code> - String with zero characters + The initial String.
	 */
	public final static String left(int integer, int len) {
		return Pad.left(integer, len, '0');
	}	
	/**
	 * Fills with space character from the right if len > length of the initial String, otherwise calls substring.
	 * @param string - The initial String.
	 * @param len - Number of space characters to pass.
	 * @return <code>String</code> - The initial String + String with space characters.
	 */
	public final static String right(String string, int len) {
		return Pad.right(string, len, ' ');
	}		
	/**
	 * Fills with char c character from the left if len > length of the initial String, otherwise calls substring.
	 * @param string - The initial String.
	 * @param len - Number of char c characters to pass.
	 * @param c - The character char c to pass.
	 * @return <code>String</code> - String with char c characters + The initial String.
	 */
	public final static String left(String string, int len, char c) {
		if (string == null) return null;
		if (len == string.length()) return string;
		if (len < string.length()) return string.substring(0, len);
		return String.copyValueOf(Pad.makeCharArray(c,len), 0, len - string.length()) + string;
	}
	/**
	 * Fills with char c character from the left if len > length of the initial integer, otherwise calls substring.
	 * @param integer - The initial integer number.
	 * @param len - Number of char c characters to pass.
	 * @param c - The character char c to pass.
	 * @return <code>String</code> - String with char c characters + The initial String.
	 */
	public final static String left(int integer, int len, char c) {
		return left(new Integer(integer).toString(), len, c);
	}	
	/**
	 * Fills with char c character from the right if len > length of the initial String otherwise, calls substring.
	 * @param string - The initial String.
	 * @param len - Number of char c characters to pass.
	 * @param c - The character char c to pass.
	 * @return <code>String</code> - The initial String + String with char c characters.
	 */
	public final static String right(String string, int len, char c) {
		if (string == null) return null;
		if (len == string.length()) return string;
		if (len < string.length()) return string.substring(string.length()-len);
		return string + String.copyValueOf(Pad.makeCharArray(c,len), 0, len - string.length());
	}	
	/**
	 * Attach sequence of {@link CharSequence} for repetition times.
	 * @param sequence - The {@link CharSequence} to be repeated.
	 * @param repetition - Number of {@link CharSequence} to be repeated.
	 * @return <code>String</code> - Returns the concatenated sequence of {@link CharSequence}.
	 */
	public final static String literal(CharSequence sequence, int repetition) {
		return Pad.literal(sequence, repetition, false);
	}	
	/**
	 * Attach sequence of {@link CharSequence} for repetition times. A boolean removes or not the last.
	 * @param sequence - The {@link CharSequence} to be repeated.
	 * @param repetition - Number of {@link CharSequence} to be repeated.
	 * @param removeAtLast - Removes or not the last character.
	 * @return <code>String</code> - Returns the concatenated sequence of {@link CharSequence}.
	 */
	public final static String literal(CharSequence sequence, int repetition, boolean removeAtLast ) {
		if (sequence==null) return "";
		if (repetition<=0) return "";
		StringBuffer sb = new StringBuffer();
		for (int i=0;i<repetition;i++) {
			sb.append(sequence);
		}
		return removeAtLast?sb.deleteCharAt(sb.length()-1).toString():sb.toString();
	}
	/**
	 * Returns a char array of len positions fill with char c.
	 * @param c - The char c to pass.
	 * @param len - The length len of the array.
	 * @return <code>char</code> - The array of chars.
	 */
	public final static char[] makeCharArray(char c, int len) {
		char[] charArray = new char[len];
		for (int i=0, j=len; i<j; i++) charArray[i] = c;
		return charArray;
	}
	/**
	 * Returns a String with the specified length filled with char c
	 * @param c - The char c to pass.
	 * @param len - The length len of the array.
	 * @return <code>String</code> - The String with all chars c.
	 */	
	public final static String makeCharArrayToString(char c, int len) {
		return String.valueOf(makeCharArray(c, len));
	}
}
