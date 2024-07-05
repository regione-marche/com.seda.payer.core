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
		SPECIAL_CHARS.put("€","&euro;");
		SPECIAL_CHARS.put("¡","&iexcl;");
		SPECIAL_CHARS.put("¢","&cent;");
		SPECIAL_CHARS.put("£","&pound;");
		SPECIAL_CHARS.put("¦","&brvbar;");
		SPECIAL_CHARS.put("§","&sect;");
		SPECIAL_CHARS.put("¨","&uml;");
		SPECIAL_CHARS.put("©","&copy;");
		SPECIAL_CHARS.put("ª","&ordf;");
		SPECIAL_CHARS.put("«","&laquo;");
		SPECIAL_CHARS.put("®","&reg;");
		SPECIAL_CHARS.put("¯","&macr;");
		SPECIAL_CHARS.put("–","&ndash;");
		SPECIAL_CHARS.put("°","&deg;");
		SPECIAL_CHARS.put("±","&plusmn;");
		SPECIAL_CHARS.put("²","&sup2;");
		SPECIAL_CHARS.put("³","&sup3;");
		SPECIAL_CHARS.put("´","&acute;");
		SPECIAL_CHARS.put("’","&rsquo;");
		SPECIAL_CHARS.put("‘","&lsquo;");
		SPECIAL_CHARS.put("µ","&micro;");
		SPECIAL_CHARS.put("¶","&para;");
		SPECIAL_CHARS.put("·","&middot;");
		SPECIAL_CHARS.put("¸","&cedil;");
		SPECIAL_CHARS.put("¹","&sup1;");
		SPECIAL_CHARS.put("º","&ordm;");
		SPECIAL_CHARS.put("»","&raquo;");
		SPECIAL_CHARS.put("¼","&frac14;");
		SPECIAL_CHARS.put("½","&frac12;");
		SPECIAL_CHARS.put("¾","&frac34;");
		SPECIAL_CHARS.put("¿","&iquest;");
		SPECIAL_CHARS.put("À","&Agrave;");
		SPECIAL_CHARS.put("Á","&Aacute;");
		SPECIAL_CHARS.put("Â","&Acirc;");
		SPECIAL_CHARS.put("Ã","&Atilde;");
		SPECIAL_CHARS.put("Ä","&Auml;");
		SPECIAL_CHARS.put("Å","&Aring;");
		SPECIAL_CHARS.put("Æ","&AElig;");
		SPECIAL_CHARS.put("Ç","&Ccedil;");
		SPECIAL_CHARS.put("È","&Egrave;");
		SPECIAL_CHARS.put("É","&Eacute;");
		SPECIAL_CHARS.put("Ê","&Ecirc;");
		SPECIAL_CHARS.put("Ë","&Euml;");
		SPECIAL_CHARS.put("Ì","&Igrave;");
		SPECIAL_CHARS.put("Í","&Iacute;");
		SPECIAL_CHARS.put("Î","&Icirc;");
		SPECIAL_CHARS.put("Ï","&Iuml;");
		SPECIAL_CHARS.put("Ñ","&Ntilde;");
		SPECIAL_CHARS.put("Ò","&Ograve;");
		SPECIAL_CHARS.put("Ó","&Oacute;");
		SPECIAL_CHARS.put("Ô","&Ocirc;");
		SPECIAL_CHARS.put("Õ","&Otilde;");
		SPECIAL_CHARS.put("Ö","&Ouml;");
		SPECIAL_CHARS.put("×","&times;");
		SPECIAL_CHARS.put("Ù","&Ugrave;");
		SPECIAL_CHARS.put("Ú","&Uacute;");
		SPECIAL_CHARS.put("Û","&Ucirc;");
		SPECIAL_CHARS.put("Ü","&Uuml;");
		SPECIAL_CHARS.put("Ý","&Yacute;");
		SPECIAL_CHARS.put("à","&agrave;");
		SPECIAL_CHARS.put("á","&aacute;");
		SPECIAL_CHARS.put("â","&acirc;");
		SPECIAL_CHARS.put("ã","&atilde;");
		SPECIAL_CHARS.put("ä","&auml;");
		SPECIAL_CHARS.put("å","&aring;");
		SPECIAL_CHARS.put("æ","&aelig;");
		SPECIAL_CHARS.put("ç","&ccedil;");
		SPECIAL_CHARS.put("è","&egrave;");
		SPECIAL_CHARS.put("é","&eacute;");
		SPECIAL_CHARS.put("ê","&ecirc;");
		SPECIAL_CHARS.put("ë","&euml;");
		SPECIAL_CHARS.put("ì","&igrave;");
		SPECIAL_CHARS.put("í","&iacute;");
		SPECIAL_CHARS.put("î","&icirc;");
		SPECIAL_CHARS.put("ï","&iuml;");
		SPECIAL_CHARS.put("ñ","&ntilde;");
		SPECIAL_CHARS.put("ò","&ograve;");
		SPECIAL_CHARS.put("ó","&oacute;");
		SPECIAL_CHARS.put("ô","&ocirc;");
		SPECIAL_CHARS.put("õ","&otilde;");
		SPECIAL_CHARS.put("ö","&ouml;");
		SPECIAL_CHARS.put("÷","&divide;");
		SPECIAL_CHARS.put("ù","&ugrave;");
		SPECIAL_CHARS.put("ú","&uacute;");
		SPECIAL_CHARS.put("û","&ucirc;");
		SPECIAL_CHARS.put("ü","&uuml;");
		SPECIAL_CHARS.put("ý","&yacute;");
		SPECIAL_CHARS.put("ÿ","&yuml;");
		SPECIAL_CHARS.put("Ÿ","&Yuml;");
		SPECIAL_CHARS.put("ƒ","&fnof;");
		SPECIAL_CHARS.put("Œ","&OElig;");
		SPECIAL_CHARS.put("œ","&oelig;");
		SPECIAL_CHARS.put("ˆ","&circ;");
		SPECIAL_CHARS.put("˜","&tilde;");
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

				/* Force replace special ISO-8859-1 (8859_1) characters (Ex. €, ƒ, etc.) */
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
	 * è true altrimenti solo la prima, di <code>oldValue</code> con
	 * <code>newValue</code>.
	 *
	 * @param   inValue
	 *			la stringa di riferimento
	 * @param   oldValue
	 *			la stringa da sostituire
	 * @param   newValue
	 *			la stringa di sostituzione
	 * @param   replaceAll
	 *			se true indica che la sostituzione è per tutte le occorrenze, altromneti solo per la prima
	 * @return  la stringa ottenuta da una stessa <code>inValue</code>
	 *			dopo aver sostituito tutte le occorrenze, se
	 *			<code>replaceAll</code> è true altrimenti solo la prima,
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