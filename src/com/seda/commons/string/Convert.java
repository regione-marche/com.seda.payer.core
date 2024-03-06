/**
 * 
 */
package com.seda.commons.string;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.io.Reader;
import java.io.Serializable;
import java.io.StringReader;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.sql.Clob;
import java.sql.SQLException;
import java.text.BreakIterator;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Arrays;
import java.util.Locale;
import java.util.Vector;

import javax.sql.rowset.WebRowSet;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.apache.commons.codec.binary.Base64;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import com.sun.rowset.WebRowSetImpl;

/**
 * @author Seda Lab
 *
 */
public class Convert {
	
	/**
	 * This class provides a central location to do specialized formatting in both a default and a locale specific manner
	 * @author Seda Lab
	 *
	 */
	public final static class I18n {
		/**
		 * Converts a string based locale into a Locale Object. The String must be in the form:<br>
		 * <i>language_contry_variant</i>
		 * 
		 * @param localeString <code>String</code> containing locale based form
		 * @return <code>Locale</code> the {@link Locale} object 
		 */
		public static Locale stringToLocale(String localeString) {
	        if (localeString == null) return null;
	        if (localeString.toLowerCase().equals("default")) return Locale.getDefault();
	        int languageIndex = localeString.indexOf('_');
	        if (languageIndex  == -1) return null;
	        int countryIndex = localeString.indexOf('_', languageIndex +1);
	        String country = null;
	        if (countryIndex  == -1) {
	            if (localeString.length() > languageIndex) {
	                country = localeString.substring(languageIndex +1, localeString.length());
	            } else {
	                return null;
	            }
	        }
	        int variantIndex = -1;
	        if (countryIndex != -1) countryIndex = localeString.indexOf('_', countryIndex +1);
	        String language = localeString.substring(0, languageIndex);
	        String variant = null;
	        if (variantIndex  != -1) {
	            variant = localeString.substring(variantIndex +1, localeString.length());
	        }
	        if (variant != null) {
	            return new Locale(language, country, variant);
	        } else {
	            return new Locale(language, country);
	        }
	    }	
		/**
		 * Returns a Vector of scanned words from a string in according to the default locale characters boundary 
		 * @param text the text to be tokenized
		 * @return <code>Vector</code> the word container
		 */
		public static Vector<String> stringToWords(String text){
			return stringToWords(text,Locale.getDefault());
		}
		
		/**
		 * Returns a Vector of scanned words from a string in according to the provided locale characters boundary
		 * @param text the string to be scan
		 * @param locale the Locale used for the break boundaries 
		 * @return <code>Vector</code> the word container
		 */
		public static Vector<String> stringToWords(String text, Locale locale){
			if (text != null){
	            Vector<String> keywords = new Vector<String>();
	            BreakIterator breakIt = BreakIterator.getWordInstance(locale);
	            int index=0;
	            int previousIndex =0;
	            breakIt.setText(text);
	            while(index < text.length()){
	                previousIndex = index;
	                index = breakIt.next();
	                String word = text.substring(previousIndex, index);
	                if (!word.trim().equals("")) keywords.addElement(word);
	            }
	            return keywords;
	            
	        }
	        return null;		
		}
		
		/**
		 * Returns a <code>String</code> representation of the provided amount in according to the specified locale and pattern
		 * @param amount the currency amount to be formatted
		 * @param precision the precision that will be used
		 * @param pattern the pattern to be applied
		 * @param locale the locale used by the formatter
		 * @return <code>String</code> the formatted currency string
		 */
		public static String currencyToString(double amount, int precision, String pattern, Locale locale){
	        NumberFormat nf = NumberFormat.getCurrencyInstance(locale);
	        DecimalFormat df = (DecimalFormat)nf;
	        df.setMinimumFractionDigits(precision);
	        df.setMaximumFractionDigits(precision);
	        df.setDecimalSeparatorAlwaysShown(true);
	        df.applyPattern(pattern);
	        return df.format(amount);
	    }
		/**
		 * Returns a <code>String</code> representation of the provided number in according to the specified locale and pattern
		 * @param number the number to be formatted
		 * @param precision the precision that will be used
		 * @param pattern the pattern to be applied
		 * @param locale the locale used by the formatter
		 * @return <code>String</code> the formatted number string
		 */
	    public static String numberToString(double number, int precision, String pattern, Locale locale){
	        NumberFormat nf = NumberFormat.getNumberInstance(locale);
	        DecimalFormat df = (DecimalFormat)nf;
	        df.setMinimumFractionDigits(precision);
	        df.setMaximumFractionDigits(precision);
	        df.setDecimalSeparatorAlwaysShown(true);
	        df.applyPattern(pattern);
	        return df.format(number);
	    }

	    /**
	     * Returns a <code>String</code> representation of the provided amount in according to the specified locale
	     * @param amount the currency amount to be formatted
	     * @param precision the precision that will be used
	     * @param locale the locale used by the formatter
	     * @return <code>String</code> the formatted currency string
	     */
	    public static String currencyToString(double amount, int precision, Locale locale){
	        NumberFormat nf = NumberFormat.getCurrencyInstance(locale);
	        nf.setMinimumFractionDigits(precision);
	        nf.setMaximumFractionDigits(precision);
	        return nf.format(amount);
	    }
	    /**
	     * Returns a <code>String</code> representation of the provided number in according to the specified locale
	     * @param number the number to be formatted
	     * @param precision the precision that will be used
	     * @param locale the locale used by the formatter
	     * @return <code>String</code> the formatted number string
	     */
	    public static String numberToString(double number, int precision, Locale locale){
	        NumberFormat nf = NumberFormat.getNumberInstance(locale);
	        nf.setMinimumFractionDigits(precision);
	        nf.setMaximumFractionDigits(precision);
	        return nf.format(number);
	    }		
		
	}

	public final static String reverse(String source) {
	    int i, len = source.length();
	    StringBuffer dest = new StringBuffer(len);
	    for (i = (len - 1); i >= 0; i--)
	      dest.append(source.charAt(i));
	    return dest.toString();
	  }
	
	public final static String webRowSetToString(WebRowSet webRowset) throws SQLException {
		StringWriter writer = new StringWriter();
		// this methods set the cursor position after the last row of the web row set
		webRowset.writeXml(writer);
		try {
			return new String(writer.toString());
		} finally {
			writer=null;
		}
	}
	
	public final static WebRowSet stringToWebRowSet(String xmlRowSet) throws SQLException, IOException {
		WebRowSet wrow=new WebRowSetImpl();
        wrow.readXml(new StringReader(xmlRowSet));
		return wrow;
	}
	
	public final static String streamToString(InputStream is) throws IOException {
		/*
		 * To convert the InputStream to String we use the BufferedReader.readLine()
		 * method. We iterate until the BufferedReader return null which means
		 * there's no more data to read. Each line will appended to a StringBuffer
		 * and returned as String.
		 */
		BufferedReader reader = new BufferedReader(new InputStreamReader(is));
		StringBuffer sb = new StringBuffer();
		
		String line = null;
		try {
			while ((line = reader.readLine()) != null) {
				sb.append(line);
			}
			return new String(sb.toString());			
		} finally {
			is.close();
			line=null;
			sb=null;
		}
	}
	
	public final static String serializableToStringBase64(Serializable serializable) throws IOException {
		Base64 codec = new Base64();
		return new String(codec.encode(serializableToBytes(serializable)));
	}
	
	public final static byte[] serializableToBytes(Serializable serializable) throws IOException {
		ByteArrayOutputStream bos=null;
		ObjectOutput out=null;
		try {
            bos = new ByteArrayOutputStream();
            out = new ObjectOutputStream(bos);
            out.writeObject(serializable);
            out.close();
            return bos.toByteArray();
		} finally {
			if (out!=null) {
				try {
					out.close();
				} catch (IOException e) {
					//ignore
				} finally {
					out=null;
				}
			}
			bos=null;
		}
	}	
	
	
	public final static Object stringBase64ToObject(String o) throws IOException, ClassNotFoundException {
		return bytesToObject(Base64.decodeBase64(o.getBytes()));
	}
	public final static Object bytesToObject(byte[] bytes) throws IOException, ClassNotFoundException {
		ByteArrayInputStream bis=null;
		ObjectInputStream ois=null;
		Object o=null;
		try {
			bis = new ByteArrayInputStream(bytes);
			ois= new ObjectInputStream(bis);
			o = ois.readObject();
			return o;
		} finally {
			if (ois!=null) {
				try {
					ois.close();
				} catch(IOException e) {
					//ignore
				} finally {
					ois=null;
				}
			}
			bis=null;
		}
	}
	
	
	public final static Reader stringToCharacterStream(String s) {
		Reader r = new StringReader(s); 
		return r;
	}
	public final static InputSource stringToInputSource(String s) {
		return new InputSource(stringToStream(s));
	}

	public final static String clobToString(Clob clob) {
		String s=null;
		try {
			s = streamToString(clob.getAsciiStream());				
		} catch (Exception x) {
			x.printStackTrace();
		}
		return s;
	}
	
	public final static InputStream stringToStream(String s) {
		return new ByteArrayInputStream(s.getBytes());
	}
	public final static InputStream stringToStream(String s, String encoding) throws UnsupportedEncodingException {
		return new ByteArrayInputStream(s.getBytes(encoding));
	}
	public final static String documentToString(Document d) throws TransformerException {
		return documentToString(d, true);
	}
	public final static String documentToString(Document d, boolean maintainXmlTag) throws TransformerException {
		DOMSource domSource = new DOMSource(d);
	    StringWriter stringWriter = new StringWriter();
	    StreamResult streamResult = new StreamResult(stringWriter);
	    TransformerFactory transformerFactory;
	    Transformer transformer;	    
		try {
		    transformerFactory = TransformerFactory.newInstance();
		    transformer = transformerFactory.newTransformer();
		    transformer.transform(domSource, streamResult);
		    String xmlString = new String(stringWriter.toString());
		    if (!maintainXmlTag && xmlString.startsWith("<?xml")) {
		    	if (xmlString.startsWith("<?xml")) {
		    		int index = xmlString.indexOf("?>");
		    		return xmlString.substring(index + 2, xmlString.length());
		    	}
		    }
		    return xmlString;			
		} finally {
			domSource=null;
			stringWriter=null;
			streamResult=null;			
			transformerFactory=null;
			transformer=null;
		}
	}
	public final static Document stringToDocument(String s) throws SAXException, IOException, ParserConfigurationException {
		return DocumentBuilderFactory
		.newInstance()
		.newDocumentBuilder()
		.parse(stringToInputSource(s));
	}
	public final static String arrayToString(String[] s) {
		return arrayToString(s,' ');
	}		
	public final static String arrayToString(String[] s, char c) {
		if (s==null) return null;
		StringBuffer sb = new StringBuffer();
		try {
			for (int i=0, j=s.length; i < j; i++) {
				sb.append(s[i]).append(c);
			}
			return new String(sb.deleteCharAt(sb.length()-1).toString());			
		} finally {
			sb=null;
		}
	}			
	public final static String[] stringToArray(String s) {
		return stringToArray(s, ' ');
	}	
	public final static String[] stringToArray(String s, char c) {
		String regex=null;
		switch (c) {
		case '.':
		case '|':			
			regex="\\"+c;
			break;
		default:
			regex=String.valueOf(c);
			break;
		}
		return stringToArray(s, regex);
	}
	public final static String[] stringToArray(String s, String regex) {
		return stringToArray(s,regex,true);		
	}
	public final static String[] stringToArray(String s, String regex, boolean trim) {
		if (s==null || s.length() == 0) return new String[0];
		String[] r = s.split(regex);
		if (trim) {
			for (int i = 0, j=r.length; i < j; i++) {
				r[i]=r[i].trim();
			}			
		}
		return r;		
	}
	public final static String eurToIso(String dateEur) {
		String[] d = dateEur.split("\\.");
		try {
			return new String(d[2]+"-"+d[1]+"-"+d[0]);			
		} finally {
			d=null;
		}
	}
	
	public final static String coalesce(String value, String defaultValue) {
		return value==null?defaultValue:value;
	}
	
	public final static String millisTimeToString(long t) {
		final String FORMAT_NUMB = String.format("%%0%dd", 2);  //set the string format value  

		String elapsedString = Long.toString(t); // convert elapsed to string
		
		String millis;
		if (elapsedString.length()>3) {
			millis = elapsedString.substring(elapsedString.length()-3); // save millis for time less the one second			
		} else {
			millis = elapsedString; // save millis for time less the one second			
		}
		
		long t1 = t / 1000;  // ignore millis
		long seconds = t1 % 60; //get seconds 
		long minutes = (t1 % 3600) / 60; // get minutes
		long hours = t1 / 3600; //get hours
		
		StringBuffer time = new StringBuffer();
		
		if (hours==0 && minutes==0 && seconds==0) {
			time.append(millis);
			time.append("(ms)");
		} else {
			if (hours>0) {
				time.append(String.format(FORMAT_NUMB,hours));
				time.append('h');
			}
			if (minutes>=0 && hours>0) {
				time.append(String.format(FORMAT_NUMB,minutes));
				time.append('\'');
			} else {
				if (minutes>0) {
					time.append(String.format(FORMAT_NUMB,minutes));
					time.append('\'');					
				}
			}
			
			time.append(String.format(FORMAT_NUMB,seconds));
			time.append('"');
		}

		return time.toString(); 		
	}


}
