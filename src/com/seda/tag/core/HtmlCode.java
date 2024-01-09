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
			case '¡': filtered.append("&iexcl;"); break; /*inverted exclamation mark*/
			case '¢': filtered.append("&cent;"); break; /*cent*/
			case '£': filtered.append("&pound;"); break; /*pound*/
			case '¤': filtered.append("&curren;"); break; /*currency*/
			case '¥': filtered.append("&yen;"); break; /*yen*/
			case '¦': filtered.append("&brvbar;"); break; /*broken vertical bar*/
			case '§': filtered.append("&sect;"); break; /*section*/
			case '¨': filtered.append("&uml;"); break; /*spacing diaeresis*/
			case '©': filtered.append("&copy;"); break; /*copyright*/
			case 'ª': filtered.append("&ordf;"); break; /*feminine ordinal indicator*/
			case '«': filtered.append("&laquo;"); break; /*angle quotation mark 
(left)*/
			case '¬': filtered.append("&not;"); break; /*negation*/
			case '­': filtered.append("&shy;"); break; /*soft hyphen*/
			case '®': filtered.append("&reg;"); break; /*registered trademark*/
			case '¯': filtered.append("&macr;"); break; /*spacing macron*/
			case '°': filtered.append("&deg;"); break; /*degree*/
			case '±': filtered.append("&plusmn;"); break; /*plus-or-minus*/
			case '²': filtered.append("&sup2;"); break; /*superscript 2*/
			case '³': filtered.append("&sup3;"); break; /*superscript 3*/
			case '´': filtered.append("&acute;"); break; /*spacing acute*/
			case 'µ': filtered.append("&micro;"); break; /*micro*/
			case '¶': filtered.append("&para;"); break; /*paragraph*/
			case '•': filtered.append("&middot;"); break; /*middle dot*/
			case '¸': filtered.append("&cedil;"); break; /*spacing cedilla*/
			case '¹': filtered.append("&sup1;"); break; /*superscript 1*/
			case 'º': filtered.append("&ordm;"); break; /*masculine ordinal indicator*/
			case '»': filtered.append("&raquo;"); break; /*angle quotation mark 
(right)*/
			case '¼': filtered.append("&frac14;"); break; /*fraction 1/4*/
			case '½': filtered.append("&frac12;"); break; /*fraction 1/2*/
			case '¾': filtered.append("&frac34;"); break; /*fraction 3/4*/
			case '¿': filtered.append("&iquest;"); break; /*inverted question mark*/
			case '×': filtered.append("&times;"); break; /*multiplication*/
			case '÷': filtered.append("&divide;"); break; /*division*/
			/* Characters */
			case 'À': filtered.append("&Agrave;"); break; /*capital a, grave accent*/
			case 'Á': filtered.append("&Aacute;"); break; /*capital a, acute accent*/
			case 'Â': filtered.append("Acirc;"); break; /*capital a, circumflex 
accent*/
			case 'Ã': filtered.append("&Atilde;"); break; /*capital a, tilde*/
			case 'Ä': filtered.append("&Auml;"); break; /*capital a, umlaut mark*/
			case 'Å': filtered.append("&Aring;"); break; /*capital a, ring*/
			case 'Æ': filtered.append("&AElig;"); break; /*capital ae*/
			case 'Ç': filtered.append("&Ccedil;"); break; /*capital c, cedilla*/
			case 'È': filtered.append("&Egrave;"); break; /*capital e, grave accent*/
			case 'É': filtered.append("&Eacute;"); break; /*capital e, acute accent*/
			case 'Ê': filtered.append("&Ecirc;"); break; /*capital e, circumflex 
accent*/
			case 'Ë': filtered.append("&Euml;"); break; /*capital e, umlaut mark*/
			case 'Ì': filtered.append("&Igrave;"); break; /*capital i, grave accent*/
			case 'Í': filtered.append("&Iacute;"); break; /*capital i, acute accent*/
			case 'Î': filtered.append("&Icirc;"); break; /*capital i, circumflex 
accent*/
			case 'Ï': filtered.append("&Iuml;"); break; /*capital i, umlaut mark*/
			case 'Ð': filtered.append("&ETH;"); break; /*capital eth, Icelandic*/
			case 'Ñ': filtered.append("&Ntilde;"); break; /*capital n, tilde*/
			case 'Ò': filtered.append("&Ograve;"); break; /*capital o, grave accent*/
			case 'Ó': filtered.append("&Oacute;"); break; /*capital o, acute accent*/
			case 'Ô': filtered.append("&Ocirc;"); break; /*capital o, circumflex 
accent*/
			case 'Õ': filtered.append("&Otilde;"); break; /*capital o, tilde*/
			case 'Ö': filtered.append("&Ouml;"); break; /*capital o, umlaut mark*/
			case 'Ø': filtered.append("&Oslash;"); break; /*capital o, slash*/
			case 'Ù': filtered.append("&Ugrave;"); break; /*capital u, grave accent*/
			case 'Ú': filtered.append("&Uacute;"); break; /*capital u, acute accent*/
			case 'Û': filtered.append("&Ucirc;"); break; /*capital u, circumflex 
accent*/
			case 'Ü': filtered.append("&Uuml;"); break; /*capital u, umlaut mark*/
			case 'Ý': filtered.append("&Yacute;"); break; /*capital y, acute accent*/
			case 'Þ': filtered.append("&THORN;"); break; /*capital THORN, Icelandic*/
			case 'ß': filtered.append("&szlig;"); break; /*small sharp s, German*/
			case 'à': filtered.append("&agrave;"); break; /*small a, grave accent*/
			case 'á': filtered.append("&aacute;"); break; /*small a, acute accent*/
			case 'â': filtered.append("&acirc;"); break; /*small a, circumflex accent*/
			case 'ã': filtered.append("&atilde;"); break; /*small a, tilde*/
			case 'ä': filtered.append("&auml;"); break; /*small a, umlaut mark*/
			case 'å': filtered.append("&aring;"); break; /*small a, ring*/
			case 'æ': filtered.append("&aelig;"); break; /*small ae*/
			case 'ç': filtered.append("&ccedil;"); break; /*small c, cedilla*/
			case 'è': filtered.append("&egrave;"); break; /*small e, grave accent*/
			case 'é': filtered.append("&eacute;"); break; /*small e, acute accent*/
			case 'ê': filtered.append("&ecirc;"); break; /*small e, circumflex accent*/
			case 'ë': filtered.append("&euml;"); break; /*small e, umlaut mark*/
			case 'ì': filtered.append("&igrave;"); break; /*small i, grave accent*/
			case 'í': filtered.append("&iacute;"); break; /*small i, acute accent*/
			case 'î': filtered.append("&icirc;"); break; /*small i, circumflex accent*/
			case 'ï': filtered.append("&iuml;"); break; /*small i, umlaut mark*/
			case 'ð': filtered.append("&eth;"); break; /*small eth, Icelandic*/
			case 'ñ': filtered.append("&ntilde;"); break; /*small n, tilde*/
			case 'ò': filtered.append("&ograve;"); break; /*small o, grave accent*/
			case 'ó': filtered.append("&oacute;"); break; /*small o, acute accent*/
			case 'ô': filtered.append("&ocirc;"); break; /*small o, circumflex accent*/
			case 'õ': filtered.append("&otilde;"); break; /*small o, tilde*/
			case 'ö': filtered.append("&ouml;"); break; /*small o, umlaut mark*/
			case 'ø': filtered.append("&oslash;"); break; /*small o, slash*/
			case 'ù': filtered.append("&ugrave;"); break; /*small u, grave accent*/
			case 'ú': filtered.append("&uacute;"); break; /*small u, acute accent*/
			case 'û': filtered.append("&ucirc;"); break; /*small u, circumflex accent*/
			case 'ü': filtered.append("&uuml;"); break; /*small u, umlaut mark*/
			case 'ý': filtered.append("&yacute;"); break; /*small y, acute accent*/
			case 'þ': filtered.append("&thorn;"); break; /*small thorn, Icelandic*/
			case 'ÿ': filtered.append("&yuml;"); break; /*small y, umlaut mark*/
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
