package com.seda.payer.commons.utility;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
/**
 * 
 * <p>Title:		 StringUtility</p>
 * <p>Description:	 </p>
 * <p>Copyright:	 Copyright (c) 01-gen-2000</p>
 * <p>Creation date: 10-giu-2010</p>
 * <p>Company:		 SEDA</p>
 * @author Marco Montisano
 *
 */
public class StringUtility implements Serializable {

	
	private static final long serialVersionUID = 1L;
	public final static String EMPTY = "";
	public final static String NULLABLE = "null";
	/** string special <i>space</i>  */
	public final static String SPACE = " ";
	/** string special <i>carriage-return</i>  */
	public final static String CARRIAGE_RETURN = "\r";
	/** string special <i>tab</i>  */
	public final static String TAB = "\t";
	/** string special <i>form-feed</i>  */
	public final static String FORM_FEED = "\f";
	/** string special <i>new line</i>  */
	public final static String NEW_LINE = "\n";
	/** string html <i>new line</i>  */
	public final static String NEW_LINE_HTML = "<br/>";
	/** string special <i>windows new line</i>  */
	public final static String WINOWS_NEW_LINE = "\r\n";
	/** string character's */
	public static HashMap<String, String> SPECIAL_CHARS = new HashMap<String, String>();
	static {
		SPECIAL_CHARS.put("�","&euro;");
		SPECIAL_CHARS.put("�","&iexcl;");
		SPECIAL_CHARS.put("�","&cent;");
		SPECIAL_CHARS.put("�","&pound;");
		SPECIAL_CHARS.put("�","&brvbar;");
		SPECIAL_CHARS.put("�","&sect;");
		SPECIAL_CHARS.put("�","&uml;");
		SPECIAL_CHARS.put("�","&copy;");
		SPECIAL_CHARS.put("�","&ordf;");
		SPECIAL_CHARS.put("�","&laquo;");
		SPECIAL_CHARS.put("�","&reg;");
		SPECIAL_CHARS.put("�","&macr;");
		SPECIAL_CHARS.put("�","&ndash;");
		SPECIAL_CHARS.put("�","&deg;");
		SPECIAL_CHARS.put("�","&plusmn;");
		SPECIAL_CHARS.put("�","&sup2;");
		SPECIAL_CHARS.put("�","&sup3;");
		SPECIAL_CHARS.put("�","&acute;");
		SPECIAL_CHARS.put("�","&rsquo;");
		SPECIAL_CHARS.put("�","&lsquo;");
		SPECIAL_CHARS.put("�","&micro;");
		SPECIAL_CHARS.put("�","&para;");
		SPECIAL_CHARS.put("�","&middot;");
		SPECIAL_CHARS.put("�","&cedil;");
		SPECIAL_CHARS.put("�","&sup1;");
		SPECIAL_CHARS.put("�","&ordm;");
		SPECIAL_CHARS.put("�","&raquo;");
		SPECIAL_CHARS.put("�","&frac14;");
		SPECIAL_CHARS.put("�","&frac12;");
		SPECIAL_CHARS.put("�","&frac34;");
		SPECIAL_CHARS.put("�","&iquest;");
		SPECIAL_CHARS.put("�","&Agrave;");
		SPECIAL_CHARS.put("�","&Aacute;");
		SPECIAL_CHARS.put("�","&Acirc;");
		SPECIAL_CHARS.put("�","&Atilde;");
		SPECIAL_CHARS.put("�","&Auml;");
		SPECIAL_CHARS.put("�","&Aring;");
		SPECIAL_CHARS.put("�","&AElig;");
		SPECIAL_CHARS.put("�","&Ccedil;");
		SPECIAL_CHARS.put("�","&Egrave;");
		SPECIAL_CHARS.put("�","&Eacute;");
		SPECIAL_CHARS.put("�","&Ecirc;");
		SPECIAL_CHARS.put("�","&Euml;");
		SPECIAL_CHARS.put("�","&Igrave;");
		SPECIAL_CHARS.put("�","&Iacute;");
		SPECIAL_CHARS.put("�","&Icirc;");
		SPECIAL_CHARS.put("�","&Iuml;");
		SPECIAL_CHARS.put("�","&Ntilde;");
		SPECIAL_CHARS.put("�","&Ograve;");
		SPECIAL_CHARS.put("�","&Oacute;");
		SPECIAL_CHARS.put("�","&Ocirc;");
		SPECIAL_CHARS.put("�","&Otilde;");
		SPECIAL_CHARS.put("�","&Ouml;");
		SPECIAL_CHARS.put("�","&times;");
		SPECIAL_CHARS.put("�","&Ugrave;");
		SPECIAL_CHARS.put("�","&Uacute;");
		SPECIAL_CHARS.put("�","&Ucirc;");
		SPECIAL_CHARS.put("�","&Uuml;");
		SPECIAL_CHARS.put("�","&Yacute;");
		SPECIAL_CHARS.put("�","&agrave;");
		SPECIAL_CHARS.put("�","&aacute;");
		SPECIAL_CHARS.put("�","&acirc;");
		SPECIAL_CHARS.put("�","&atilde;");
		SPECIAL_CHARS.put("�","&auml;");
		SPECIAL_CHARS.put("�","&aring;");
		SPECIAL_CHARS.put("�","&aelig;");
		SPECIAL_CHARS.put("�","&ccedil;");
		SPECIAL_CHARS.put("�","&egrave;");
		SPECIAL_CHARS.put("�","&eacute;");
		SPECIAL_CHARS.put("�","&ecirc;");
		SPECIAL_CHARS.put("�","&euml;");
		SPECIAL_CHARS.put("�","&igrave;");
		SPECIAL_CHARS.put("�","&iacute;");
		SPECIAL_CHARS.put("�","&icirc;");
		SPECIAL_CHARS.put("�","&iuml;");
		SPECIAL_CHARS.put("�","&ntilde;");
		SPECIAL_CHARS.put("�","&ograve;");
		SPECIAL_CHARS.put("�","&oacute;");
		SPECIAL_CHARS.put("�","&ocirc;");
		SPECIAL_CHARS.put("�","&otilde;");
		SPECIAL_CHARS.put("�","&ouml;");
		SPECIAL_CHARS.put("�","&divide;");
		SPECIAL_CHARS.put("�","&ugrave;");
		SPECIAL_CHARS.put("�","&uacute;");
		SPECIAL_CHARS.put("�","&ucirc;");
		SPECIAL_CHARS.put("�","&uuml;");
		SPECIAL_CHARS.put("�","&yacute;");
		SPECIAL_CHARS.put("�","&yuml;");
		SPECIAL_CHARS.put("�","&Yuml;");
		SPECIAL_CHARS.put("�","&fnof;");
		SPECIAL_CHARS.put("�","&OElig;");
		SPECIAL_CHARS.put("�","&oelig;");
		SPECIAL_CHARS.put("�","&circ;");
		SPECIAL_CHARS.put("�","&tilde;");
	}
	/**
	 * @param string
	 * @param specialChars
	 * @return
	 */
	public static String replaceSpecialCharapter(String string) {
		return rplSpecialChars(string, SPECIAL_CHARS, null);
	}
	/**
	 * @param string
	 * @param specialChars
	 * @return
	 */
	public static String replaceSpecialCharapter(String string, HashMap<String, String> specialChars) {
		return rplSpecialChars(string, specialChars, null);
	}
	/**
	 * @param string
	 * @param specialChars
	 * @return
	 */
	public static String replaceSpecialCharapter(String string, HashMap<String, String> specialChars, String encodingCharset) {
		return rplSpecialChars(string, specialChars, encodingCharset);
	}
	/**
	 * @param string
	 * @param specialChars
	 * @param encodingCharset
	 * @return
	 */
	@SuppressWarnings("unchecked")
	private static String rplSpecialChars(String string, HashMap<String, String> specialChars, String encodingCharset) {
		if (isEmpty(string))
			return string;

		String outData = string;
		try {
			if (specialChars == null)
				specialChars = SPECIAL_CHARS;

			Set keys = specialChars.keySet();
			Iterator keyList = keys.iterator();
			if (keyList != null && keyList.hasNext()) {
				do {
					String nextElement = (String) keyList.next();
					if (outData.indexOf(nextElement) > -1)
						outData = outData.replaceAll(nextElement, (String) specialChars.get(nextElement));
				} while (keyList.hasNext());

				/* Force replace special ISO-8859-1 (8859_1) characters (Ex. �, �, etc.) */
				if (!isEmpty(encodingCharset)) {
					try {
						keys = specialChars.keySet();
						keyList = keys.iterator();
						String __tmp_outData = new String(outData.getBytes(encodingCharset));
						do {
							String nextElement = (String) keyList.next();
							if (__tmp_outData.indexOf(nextElement) > -1)
								__tmp_outData = __tmp_outData.replaceAll(nextElement, (String) specialChars.get(nextElement));
						} while (keyList.hasNext());
		
						outData = __tmp_outData;
					} catch (Exception e) {
						System.out.println("WARN - replaceSpecialCharapter failed, generic error due to: " + e.getMessage());
					}
				}
			}
		} catch (Exception exc) {
			exc.printStackTrace();
			outData = string;
		}
		return outData;
	}
	/**
	 * @param string
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static String reactiveSpecialCharapter(String string) {
		if (isEmpty(string))
			return string;

		String outData = string;
		try {
			Set keys = SPECIAL_CHARS.keySet();
			Iterator keyList = keys.iterator();
			if (keyList != null && keyList.hasNext())
				do {
					String nextKey = (String) keyList.next(); 
					String nextElement = (String) SPECIAL_CHARS.get(nextKey);
					if (outData.indexOf(nextElement) > -1)
						outData = outData.replaceAll(nextElement, nextKey);
				} while (keyList.hasNext());
		} catch (Exception exc) {
			exc.printStackTrace();
			outData = string;
		}
		return outData;
	}
	/**
	 * reactiveSpecialCharapter
	 * 
	 * @param string
	 * @param specialChars
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static String reactiveSpecialCharapter(String string, HashMap<?, ?> specialChars) {
		if (isEmpty(string))
			return string;

		String outData = string;
//		StringBuffer __tmp_outData = new StringBuffer(string); 
		try {
			Set keys = specialChars.keySet();
			Iterator keyList = keys.iterator();
			if (keyList != null && keyList.hasNext())
				do {
					String nextKey = (String) keyList.next(); 
					String nextElement = (String) specialChars.get(nextKey);
//					System.out.println(nextKey + " = " + nextElement);
					if (outData.indexOf(nextElement) > -1) {
						System.out.println("Founded " + nextElement);
						outData = outData.replaceAll(nextElement, nextKey);
					}
				} while (keyList.hasNext());
		} catch (Exception exc) {
			exc.printStackTrace();
			outData = string;
		}
		return outData;
	}
	/**
	 * restituisce la stringa ottenuta da una stessa <code>inValue</code>
	 * dopo aver sostituito tutte le occorrenze, se <code>replaceAll</code>
	 * � true altrimenti solo la prima, di <code>oldValue</code> con
	 * <code>newValue</code>.
	 *
	 * @param   inValue
	 *			la stringa di riferimento
	 * @param   oldValue
	 *			la stringa da sostituire
	 * @param   newValue
	 *			la stringa di sostituzione
	 * @param   replaceAll
	 *			se true indica che la sostituzione � per tutte le occorrenze, altromneti solo per la prima
	 * @return  la stringa ottenuta da una stessa <code>inValue</code>
	 *			dopo aver sostituito tutte le occorrenze, se
	 *			<code>replaceAll</code> � true altrimenti solo la prima,
	 *			di <code>oldValue</code> con <code>newValue</code>
	 * @throws  java.lang.Exception
	 *			nel caso di anomalie
	 */
	public static String replace(String inValue,
								 String oldValue,
								 String newValue,
								 boolean replaceAll) {
		if (inValue == null)
			return null;
		if (inValue.trim().length() == 0)
			return "";
		if ((oldValue == null) || (oldValue.length() == 0))
			return inValue;
		if (newValue == null)
			newValue = "";

		try {
			String outValue = inValue;
			Pattern pattern = Pattern.compile(oldValue); 
			Matcher match = pattern.matcher(outValue);
			if (match.find()) {
				if (replaceAll)
					outValue = match.replaceAll(newValue);
				else outValue = match.replaceFirst(newValue);
			} else return inValue;
			return outValue;
		} catch (Exception e) {
		}
		return inValue;
	}
	/**
	 * @param object
	 * @return
	 */
	public static String getValue(Object object) {
		if (object == null)
			return "";

		return object.toString();
	}
	/**
	 * @param object
	 * @return
	 */
	public static boolean isEmpty(String object) {
		if (object == null || object.length() == 0 || NULLABLE.equals(object.toLowerCase()))
			return true;

		for (int i = 0; i < object.length(); i++)
			if (!Character.isWhitespace(object.charAt(i)))
				return false;

		return true;
	}
	/**
	 * @param object
	 * @return
	 */
	public static boolean isEmpty(Object object) {
		if (object == null || object.hashCode() == 0)
			return true;

		if ((object instanceof String) && NULLABLE.equals(((String) object).toLowerCase()))
			return true;

		if (object instanceof String) {
			String obj = (String) object;
			for (int i = 0; i < obj.length(); i++)
				if (!Character.isWhitespace(obj.charAt(i)))
					return false;
		}
		return true;
	}
}