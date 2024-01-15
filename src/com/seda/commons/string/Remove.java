/**
 * 
 */
package com.seda.commons.string;

/**
 * @author dbadm
 *
 */
public class Remove {

	public final static String charArray(String s, char[] data) {
		if (s==null || s.length()==0) return new String(s);
		String datas = String.valueOf(data);
		StringBuilder sb = new StringBuilder();
		try {
			for (int i=0, j=s.length(); i<j; i++) {
				if (datas.contains(String.valueOf(s.charAt(i)))) continue;
				sb.append(s.charAt(i));
			}
			return new String(sb.toString());			
		} finally {
			datas=null;			
			sb=null;
		}
	}
}
