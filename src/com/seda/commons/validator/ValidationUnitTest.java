/**
 * 
 */
package com.seda.commons.validator;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.HashMap;
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.Map;
//
//import org.apache.commons.codec.binary.Base64;

import com.seda.commons.string.Convert;

/**
 * @author Seda Lab
 *
 */
public class ValidationUnitTest {

	private static final String message = "rule={0}, code={1}, options={2}, numoptions={3}, integer={4}, exclusive={5}"; 
	private static final String[] tests = new String[]{
		"RICCI;"
		,"RICCI="}; 
	private static final String[] rules = new String[]{
		"accept=[\\w\\t\\?\\+\\(\\)\\.\\,\\x3B\\'\\*\\$%@\\x3D-]{0,256}"
		,"accept=[\\w\\-\\=]{0,256}"};
			
	public static void main(String[] args)  {
		new ValidationUnitTest(args);
	}

	public ValidationUnitTest(String[] args)  {
		printAll();
		Validation validation = new Validation();
		

//		//values.put("data_day", "01");
//		//values.put("data_month", "13");		
//		//values.put("data_year", "1999");
//		values.put("STA_CCODSERV", "'0");
		StringBuffer buffer = new StringBuffer();
//		buffer.append("STA_CCODSERV.rules=accept=^[a-zA-Z0-9]{0,2}$§STA_CCODSERV.label=Codice Servizio£");
//		//buffer.append("data.rules=required;dateISO§data.label=Data Inserimento£");
//		//buffer.append("data_month.rules=required;dateISO§data_month.label=Data Inserimento£");
//		//buffer.append("data_year.rules=required;dateISO§data_year.label=Data Inserimento");		
//		//buffer.append("dep.rules=required;maxlength=3§dep.label=Unita' operativa");		
//		try {
//			ArrayList<String> messages = validation.validateBuffer(buffer.toString(), values);
//			System.err.println(messages.toString());
//		} catch (ValidationException e) {
//			e.printStackTrace();
//		}
//		try {
//			Base64 codec = new Base64();
//			String base64 = new String(codec.encode(buffer.toString().getBytes()));
//			ArrayList<String> messages = validation.validateBufferBase64(base64, values);
//			System.err.println(messages.toString());
//		} catch (ValidationException e) {
//			e.printStackTrace();
//		}
		for (int i = 0; i < tests.length; i++) {
			try {
				System.out.print("Esito test '" + tests[i] + "'@'" + rules[i]+"' "); 
				validation.validate(tests[i], rules[i]);
				System.out.println(" passed");
			} catch (ValidationException e) {
				System.out.println(e.getMessage());
			}
			
		}
			
		/*
		validation.setValue("bbb");
		System.out.println("Valida " + validation.getValue());
		
		validation.setRulesBuffer("required;range=[aaa,bba]");
		try {
			validation.validate();
		} catch (ValidationException e) {
			e.printStackTrace();
			System.out.println();
		}
		
		validation.setValue("01.01.2010");
		System.out.println("Valida " + validation.getValue());
		validation.setRulesBuffer("required;dateEUR");
		try {
			validation.validate();
		} catch (ValidationException e) {
			e.printStackTrace();
			System.out.println();
		}
		
		validation.setValue("17:01");
		System.out.println("Valida " + validation.getValue());
		validation.setRulesBuffer("required;timeShort");
		try {
			validation.validate();
		} catch (ValidationException e) {
			e.printStackTrace();
		}
		*/
		HashMap<String, String> values = new HashMap<String, String>();
		HashMap<String, String> userMessages = new HashMap<String, String>();
		userMessages.put("dep", "[required=Il valore = o £maxlength=Deve esser almeno lungo 3]");
		validation.setUserMessages(userMessages);
		values.put("name", "flavio");
		values.put("surname", "ricci");		
		values.put("email", "f.ricci@seda.it");
		values.put("dep", "");		
		buffer = new StringBuffer();
		buffer.append("name.rules=required;maxlength=6§name.label=Nome£");
		buffer.append("surname.rules=required;maxlength=10§surname.label=Cognome£");
		buffer.append("email.rules=required;email;maxlength=20§email.label=E Mail£");		
		buffer.append("dep.rules=required;maxlength=3§dep.label=Unita' operativa");
		ArrayList<ValidationMessage> messages=null;
		try {
			messages = validation.validateBuffer(buffer.toString(), values);
			System.err.println(messages.toString());
		} catch (ValidationException e) {
			e.printStackTrace();
		}
		
		validation=null;
	}
	
	public void printAll() {
		// Print validation attributes
		for (Rules r : Rules.values()) {
			String key = r.key();
			int code=r.code();
			boolean options=r.hasOptions();
			int numOptions=0;
			boolean integerOptions=r.hasIntegerOptions();	
			String[] exclusives=r.exclusives();
			String excl="";
			if (exclusives.length>0) excl=Convert.arrayToString(exclusives,',');  
			String className=r.className();
			if (options) {
				try {
					RuleOptionsInterface rule = (RuleOptionsInterface)Class.forName(className).newInstance();
					numOptions=rule.optionsExpected();
				} catch (Exception e) {
					e.printStackTrace();
				}

			}
			System.out.println(MessageFormat.format(message, new Object[]{key,code,options,numOptions, integerOptions, excl}));
		}		
	}
}
