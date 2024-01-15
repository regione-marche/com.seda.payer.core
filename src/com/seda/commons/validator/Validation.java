/**
 * 
 */
package com.seda.commons.validator;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import com.seda.commons.string.Convert;
import com.seda.commons.string.Pad;

/**
 * @author Seda Lab
 *
 */
public class Validation {

	public static final char rulesSeparator = ';'; 
	public static final char validationBufferSeparator = '£';	
	public static final char validationSeparator = '§';
	public static final String suffixRules = ".rules";	
	public static final String suffixLabel = ".label";	
	
	public static final String suffixDay="_day";
	public static final String suffixMonth="_month";
	public static final String suffixYear="_year";
	
	private String rulesBuffer;
	private String[] rules;	
	private String value;
	private Locale locale;	//04082016 GG
	
	private Validator validator;
	
	private HashMap<String, String> userMessages;
	
	public void setUserMessages(HashMap<String, String> userMessages) {
		this.userMessages=userMessages;
	}
	public HashMap<String, String> getUserMessages() {
		if (userMessages==null) {
			userMessages = new HashMap<String, String>();   
		}
		return userMessages;
	}
	public void setRules(String[] rules) {
		this.rules=rules;
		this.rulesBuffer=Convert.arrayToString(rules,rulesSeparator);
	}
	public String[] getRules() {
		return this.rules;
	}
	public void setRulesBuffer(String rulesBuffer) {
		this.rulesBuffer=rulesBuffer;
		this.rules=Convert.stringToArray(rulesBuffer,rulesSeparator);
	}
	public String getRulesBuffer() {
		return this.rulesBuffer;
	}	
	public void setValue(String value) {
		this.value=value;
	}
	public String getValue() {
		return this.value;
	}

	//04082016 GG - inizio
	public void setLocale(Locale locale) {
		this.locale=locale;
	}
	//04082016 GG - fine
	
	public Validator getValidator() throws ValidationException {
		if (validator==null)
			validator = new Validator();
		return this.validator;
	}
	
	public Validation() {}
	
	public Validation(String value) {
		setValue(value);
	}
	public Validation(String value, String rulesBuffer) {
		this(value);
		setRulesBuffer(rulesBuffer);
	}	
	public Validation(String value, String[] rules) {
		this(value);
		setRules(rules);
	}
//
//	public ArrayList<ValidationMessage> validateBufferBase64(String base64Buffer, Map<String,String> values) throws ValidationException  {
//		String decodedString=null;
//		// decode base 64 buffer
//		if (base64Buffer!=null) {
//			Base64 codec = new Base64();
//			byte[] decoded = codec.decode(base64Buffer.getBytes());
//			decodedString = new String(decoded);			
//		}
//		return validateBuffer(decodedString, values);
//	}
	
	public ArrayList<ValidationMessage> validateBuffer(String buffer, Map<String,String> values) throws ValidationException  {
		ArrayList<ValidationMessage> messages= new ArrayList<ValidationMessage>();
		String dataNames="";
		// if we have a valid values map and buffer
		if (values!=null && buffer!=null) {
			String[] couples = Convert.stringToArray(buffer, validationBufferSeparator);
			// loop through the couple validation instructions
			for (int i=0, j=couples.length; i < j; i++) {
				String[] data = Convert.stringToArray(couples[i],validationSeparator);
				// if this array size is not equal 2 continue loop
				if (data.length==2) {
					String name=null, rules=null, label=null;
					// the first data contains the rules, the second the label
					String[] ruleData = getArrayData(data[0]);
					if (ruleData!=null) {
						name = ruleData[0];
						rules = ruleData[1];
						String[] labelData = getArrayData(data[1]);
						if (labelData!=null && (labelData[0].equals(name))) {
							label = labelData[1];						
						} else label=name;
						String value=null;
						try {
							if (rules.contains("date") || rules.contains("dateISO") || rules.contains("dateEUR")) {
//								String dataValue = getDataValue(name, values);
								value=getDataValue(name, values,rules);
								if (!dataNames.contains(name)) {
									dataNames+=name + " ";
								if (locale!=null)
										validate(value,rules,locale);
									else 
									validate(value,rules);
//									validate(dataValue,rules);									
								}
							} else {
								if (values.containsKey(name)) {
									value=values.get(name);
								if (locale!=null) 
										validate(value,rules,locale);
									else 
									validate(value,rules);
								}									
							}							
						} catch (ValidationException x) {
							messages.add(resolveMessage(name,label,value,x));
						}
					}
				}
			}			
		}
		return messages;
	}		
	
	private ValidationMessage resolveMessage(String name, String label, String value, ValidationException x) {
		String message=null;
		String userMessage=null;
		boolean isUserMessage=false;
		HashMap<String, String> messageMap=null;
		if (getUserMessages().containsKey(name)) {
			isUserMessage=true;
			userMessage=getUserMessages().get(name);
			if (userMessage.startsWith("[") &&
					userMessage.endsWith("]")) {
				messageMap=resolveMessageMap(userMessage);
				if (messageMap.containsKey(x.getKey()))
					message=messageMap.get(x.getKey());
				else
				{
					isUserMessage=false;
					message=x.getMessage();
				}
			} else {
				message=userMessage;
			}
		} else {
			message=x.getMessage();
		}
		return new ValidationMessage(name, label, message, value, isUserMessage);
	}
	
	private HashMap<String, String> resolveMessageMap(String userMessage) {
		String[] couples = Convert.stringToArray(userMessage.substring(1,userMessage.length()-1), validationBufferSeparator);
		HashMap<String, String> messageMap = new HashMap<String, String>(couples.length);
		int equ=-1;
		for (int i=0, j=couples.length; i < j; i++) {
			String composite = couples[i]; 
			// trovo il primo =
			 equ=composite.indexOf('=');
			 if (equ!=-1) {
				 // messaggio valido, lo spacco
				 String key = composite.substring(0,equ);
				 String value = composite.substring(equ+1);
				 messageMap.put(key, value);
			 }
		}
		return messageMap;
	}
	private String getDataValue(String nameData, Map<String, String> values, String rules) {
		String gg = values.get(nameData.concat(suffixDay));
		String mm = values.get(nameData.concat(suffixMonth));
		String ssaa = values.get(nameData.concat(suffixYear));

		if (gg==null && mm==null && ssaa == null) {
			return values.get(nameData);
		} else {
			if (gg.trim().length() == 0 || mm.trim().length() == 0 || ssaa.trim().length() == 0){
				return "";
			}
			if  (rules.contains("dateEUR")) {
				return Pad.left(gg,2,'0').concat(".").concat(Pad.left(mm,2,'0')).concat(".").concat(Pad.left(ssaa,4,'0'));
			}
			return Pad.left(ssaa,4,'0').concat("-").concat(Pad.left(mm,2,'0')).concat("-").concat(Pad.left(gg,2,'0'));	
		}
		 
	}
	public boolean validate() throws ValidationException  {
		getValidator().setValue(getValue());
		getValidator().setRules(getRules());
		return getValidator().validate();
	}
	
	
	public boolean validate(String value, String rules) throws ValidationException  {
		getValidator().setValue(value);
		getValidator().setRules(Convert.stringToArray(rules,rulesSeparator));
		return getValidator().validate();
	}	

	//04082016 GG - inizio
	public boolean validate(String value, String rules, Locale locale) throws ValidationException  {
		getValidator().setValue(value);
		getValidator().setRules(Convert.stringToArray(rules,rulesSeparator));
		getValidator().setLocale(locale);
		return getValidator().validate();
	}	
	//04082016 GG - fine

	
	private String[] getArrayData(String data) {
		String[] array=null;
		// find the first equal sign of the data
		int firstEqual = data.indexOf('=');
		if (firstEqual!=-1) {
			// get the left and right part
			String left = data.substring(0,firstEqual);
			String right = data.substring(firstEqual+1);
			// check for a valid suffix
			if (left.endsWith(suffixRules) || left.endsWith(suffixLabel)) {
				String name = Convert.stringToArray(left,'.')[0];
				// get the name and the rules
				array=new String[]{name,right};				
			}
		}
		return array;
	}
}
