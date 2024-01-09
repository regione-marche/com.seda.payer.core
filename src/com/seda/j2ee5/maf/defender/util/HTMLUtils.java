/**
 * 
 */
package com.seda.j2ee5.maf.defender.util;

/**
 * @author Seda Lab
 *
 */
public class HTMLUtils {

	public static String sanitize(String s) {
		StringBuffer sb = new StringBuffer();

		for (int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);

			if (c >= 'a' && c <= 'z' || c >= 'A' && c <= 'Z' || c >= '0' && c <= '9') {
				sb.append(c);
			}
		}

		return sb.toString();
	}

	public static String entityEncode(String s) {

		if(s == null) { return null; }

		StringBuffer sb = new StringBuffer();

		for (int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);
			if (c >= 'a' && c <= 'z' || c >= 'A' && c <= 'Z' || c >= '0' && c <= '9') {
				sb.append(c);
			} else {
				sb.append("&#" + (int) c + ";");
			}
		}

		return sb.toString();
	}
}
