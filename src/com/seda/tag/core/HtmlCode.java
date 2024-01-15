package com.seda.tag.core;

public class HtmlCode {
	/**
	 * HTML ISO-8859-1 encoding
	 * @param buffer
	 * @return the encoded string
	 */
	private HtmlCode() {

	};

	public final static String encodeHtml(String buffer) {
		StringBuffer filtered = new StringBuffer(buffer.length());
		char c;
		for(int i=0, j=buffer.length(); i<j; i++) {
			c = buffer.charAt(i);
			switch(c) {
			/*Special Characters */
			case '"': filtered.append("&quot;"); break; /*quotation mark*/
			case '\'': filtered.append("&apos;"); break; /*(does not work in IE) 
apostrophe*/
			case '&': filtered.append("&amp;"); break; /*ampersand*/
			case '<': filtered.append("&lt;"); break; /*less-than*/
			case '>': filtered.append("&gt;"); break; /*greater-than*/
			/* Symbols */
			// case ' ': filtered.append("&nbsp;"); break; /*non-breaking space*/
			case '�': filtered.append("&iexcl;"); break; /*inverted exclamation mark*/
			case '�': filtered.append("&cent;"); break; /*cent*/
			case '�': filtered.append("&pound;"); break; /*pound*/
			case '�': filtered.append("&curren;"); break; /*currency*/
			case '�': filtered.append("&yen;"); break; /*yen*/
			case '�': filtered.append("&brvbar;"); break; /*broken vertical bar*/
			case '�': filtered.append("&sect;"); break; /*section*/
			case '�': filtered.append("&uml;"); break; /*spacing diaeresis*/
			case '�': filtered.append("&copy;"); break; /*copyright*/
			case '�': filtered.append("&ordf;"); break; /*feminine ordinal indicator*/
			case '�': filtered.append("&laquo;"); break; /*angle quotation mark 
(left)*/
			case '�': filtered.append("&not;"); break; /*negation*/
			case '�': filtered.append("&shy;"); break; /*soft hyphen*/
			case '�': filtered.append("&reg;"); break; /*registered trademark*/
			case '�': filtered.append("&macr;"); break; /*spacing macron*/
			case '�': filtered.append("&deg;"); break; /*degree*/
			case '�': filtered.append("&plusmn;"); break; /*plus-or-minus*/
			case '�': filtered.append("&sup2;"); break; /*superscript 2*/
			case '�': filtered.append("&sup3;"); break; /*superscript 3*/
			case '�': filtered.append("&acute;"); break; /*spacing acute*/
			case '�': filtered.append("&micro;"); break; /*micro*/
			case '�': filtered.append("&para;"); break; /*paragraph*/
			case '�': filtered.append("&middot;"); break; /*middle dot*/
			case '�': filtered.append("&cedil;"); break; /*spacing cedilla*/
			case '�': filtered.append("&sup1;"); break; /*superscript 1*/
			case '�': filtered.append("&ordm;"); break; /*masculine ordinal indicator*/
			case '�': filtered.append("&raquo;"); break; /*angle quotation mark 
(right)*/
			case '�': filtered.append("&frac14;"); break; /*fraction 1/4*/
			case '�': filtered.append("&frac12;"); break; /*fraction 1/2*/
			case '�': filtered.append("&frac34;"); break; /*fraction 3/4*/
			case '�': filtered.append("&iquest;"); break; /*inverted question mark*/
			case '�': filtered.append("&times;"); break; /*multiplication*/
			case '�': filtered.append("&divide;"); break; /*division*/
			/* Characters */
			case '�': filtered.append("&Agrave;"); break; /*capital a, grave accent*/
			case '�': filtered.append("&Aacute;"); break; /*capital a, acute accent*/
			case '�': filtered.append("Acirc;"); break; /*capital a, circumflex 
accent*/
			case '�': filtered.append("&Atilde;"); break; /*capital a, tilde*/
			case '�': filtered.append("&Auml;"); break; /*capital a, umlaut mark*/
			case '�': filtered.append("&Aring;"); break; /*capital a, ring*/
			case '�': filtered.append("&AElig;"); break; /*capital ae*/
			case '�': filtered.append("&Ccedil;"); break; /*capital c, cedilla*/
			case '�': filtered.append("&Egrave;"); break; /*capital e, grave accent*/
			case '�': filtered.append("&Eacute;"); break; /*capital e, acute accent*/
			case '�': filtered.append("&Ecirc;"); break; /*capital e, circumflex 
accent*/
			case '�': filtered.append("&Euml;"); break; /*capital e, umlaut mark*/
			case '�': filtered.append("&Igrave;"); break; /*capital i, grave accent*/
			case '�': filtered.append("&Iacute;"); break; /*capital i, acute accent*/
			case '�': filtered.append("&Icirc;"); break; /*capital i, circumflex 
accent*/
			case '�': filtered.append("&Iuml;"); break; /*capital i, umlaut mark*/
			case '�': filtered.append("&ETH;"); break; /*capital eth, Icelandic*/
			case '�': filtered.append("&Ntilde;"); break; /*capital n, tilde*/
			case '�': filtered.append("&Ograve;"); break; /*capital o, grave accent*/
			case '�': filtered.append("&Oacute;"); break; /*capital o, acute accent*/
			case '�': filtered.append("&Ocirc;"); break; /*capital o, circumflex 
accent*/
			case '�': filtered.append("&Otilde;"); break; /*capital o, tilde*/
			case '�': filtered.append("&Ouml;"); break; /*capital o, umlaut mark*/
			case '�': filtered.append("&Oslash;"); break; /*capital o, slash*/
			case '�': filtered.append("&Ugrave;"); break; /*capital u, grave accent*/
			case '�': filtered.append("&Uacute;"); break; /*capital u, acute accent*/
			case '�': filtered.append("&Ucirc;"); break; /*capital u, circumflex 
accent*/
			case '�': filtered.append("&Uuml;"); break; /*capital u, umlaut mark*/
			case '�': filtered.append("&Yacute;"); break; /*capital y, acute accent*/
			case '�': filtered.append("&THORN;"); break; /*capital THORN, Icelandic*/
			case '�': filtered.append("&szlig;"); break; /*small sharp s, German*/
			case '�': filtered.append("&agrave;"); break; /*small a, grave accent*/
			case '�': filtered.append("&aacute;"); break; /*small a, acute accent*/
			case '�': filtered.append("&acirc;"); break; /*small a, circumflex accent*/
			case '�': filtered.append("&atilde;"); break; /*small a, tilde*/
			case '�': filtered.append("&auml;"); break; /*small a, umlaut mark*/
			case '�': filtered.append("&aring;"); break; /*small a, ring*/
			case '�': filtered.append("&aelig;"); break; /*small ae*/
			case '�': filtered.append("&ccedil;"); break; /*small c, cedilla*/
			case '�': filtered.append("&egrave;"); break; /*small e, grave accent*/
			case '�': filtered.append("&eacute;"); break; /*small e, acute accent*/
			case '�': filtered.append("&ecirc;"); break; /*small e, circumflex accent*/
			case '�': filtered.append("&euml;"); break; /*small e, umlaut mark*/
			case '�': filtered.append("&igrave;"); break; /*small i, grave accent*/
			case '�': filtered.append("&iacute;"); break; /*small i, acute accent*/
			case '�': filtered.append("&icirc;"); break; /*small i, circumflex accent*/
			case '�': filtered.append("&iuml;"); break; /*small i, umlaut mark*/
			case '�': filtered.append("&eth;"); break; /*small eth, Icelandic*/
			case '�': filtered.append("&ntilde;"); break; /*small n, tilde*/
			case '�': filtered.append("&ograve;"); break; /*small o, grave accent*/
			case '�': filtered.append("&oacute;"); break; /*small o, acute accent*/
			case '�': filtered.append("&ocirc;"); break; /*small o, circumflex accent*/
			case '�': filtered.append("&otilde;"); break; /*small o, tilde*/
			case '�': filtered.append("&ouml;"); break; /*small o, umlaut mark*/
			case '�': filtered.append("&oslash;"); break; /*small o, slash*/
			case '�': filtered.append("&ugrave;"); break; /*small u, grave accent*/
			case '�': filtered.append("&uacute;"); break; /*small u, acute accent*/
			case '�': filtered.append("&ucirc;"); break; /*small u, circumflex accent*/
			case '�': filtered.append("&uuml;"); break; /*small u, umlaut mark*/
			case '�': filtered.append("&yacute;"); break; /*small y, acute accent*/
			case '�': filtered.append("&thorn;"); break; /*small thorn, Icelandic*/
			case '�': filtered.append("&yuml;"); break; /*small y, umlaut mark*/
			default:
				int intVal = (int)c;
				if ( intVal > 128 ) {
					// extended ascii Codes
					filtered.append( "&#" ).append(intVal).append(";");
				} else {
					filtered.append(c);
				}
			}
		}
		return filtered.toString();
	}

}
