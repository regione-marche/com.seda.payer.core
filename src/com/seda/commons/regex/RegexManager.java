/**
 * 
 */
package com.seda.commons.regex;

import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
/**
 * @author dbadm
 *
 */
public enum RegexManager {
	email("email",true), 
	email_light("email_light",true), 
	email_simple("email_simple",true),
	url("url",true), 
	url_base("url_base",true),
	dateISO("dateISO",true), 
	dateEUR("dateEUR",true),
	time("time",true),
	time_short("time_short",true),
	number("number",true),
	numberIT("numberIT",true),
	digits("digits",true),
	safe_text("safe_text",true),
	jsessionid("jsessionid",true),
	creditcard("creditcard",true),
	;
	
	public final static String regexLibrary = "com.seda.commons.regex.regexLibrary";
	
	private static ResourceBundle rb = ResourceBundle.getBundle(regexLibrary);

	private Pattern pattern;
	private String regex;
	private boolean sensitive;
	
	public String getRegex(){
		return this.regex;
	}
	public Pattern pattern() {
		return this.pattern;
	}
	public boolean sensitive() {
		return this.sensitive;
	}
	
	public Matcher matcher(String input) {
		return pattern().matcher(input);
	}

	public boolean matches(String input) {
		return matcher(input).matches();
	}
	
	public boolean doesntMatch(String input) {
		return matches(input)==true?false:true;
	}	
	
	RegexManager(String regex, boolean sensitive) {
		this.regex=regex();
		this.sensitive=sensitive;
		if (sensitive)
			pattern=Pattern.compile(this.regex);
		else 
			pattern=Pattern.compile(this.regex,Pattern.CASE_INSENSITIVE);			
	}
	
	private String regex() {
		return bundle().getString(name());
    }	
	
	private ResourceBundle bundle() {
		if (rb==null) rb=ResourceBundle.getBundle(regexLibrary);
		return rb;
	}
	
}
