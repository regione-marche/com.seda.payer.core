/**
 * 
 */
package com.seda.j2ee5.maf.defender.rule;

import java.util.regex.Pattern;
/**
 * @author Seda Lab
 *
 */
public class Regex {

	private String name;
	private String regex;
	private String description;
	
	private Pattern pattern;

	public boolean matches(String input) {
		if (input==null) return false;
		return pattern==null?true:pattern.matcher(input).matches();
	}
	
	public Regex(String name, String regex, String description) {
		super();
		this.name = name;
		this.regex = regex;
		this.description = description;
		if (regex!=null) {
			this.pattern = Pattern.compile(regex);
		}
	}

	public String getName() {
		return name;
	}

	public String getRegex() {
		return regex;
	}

	public String getDescription() {
		return description;
	}

	@Override
	public String toString() {
		return "Regex [description=" + description + ", name=" + name
				+ ", regex=" + regex + "]";
	}
	
}
