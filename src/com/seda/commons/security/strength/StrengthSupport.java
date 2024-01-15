/**
 * 
 */
package com.seda.commons.security.strength;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author f.ricci
 *
 */
public abstract class StrengthSupport {

	private Pattern pattern;
	private String regex;
	
	public abstract int score(String value);
	
	public String getRegex(){
		return this.regex;
	}
	public Pattern pattern() {
		return this.pattern;
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

	protected void compile(String regex) {
		this.regex=regex;
		this.pattern=Pattern.compile(regex);
	}
}
