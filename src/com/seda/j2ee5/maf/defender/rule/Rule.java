/**
 * 
 */
package com.seda.j2ee5.maf.defender.rule;

/**
 * @author Seda Lab
 *
 */
public class Rule {
	
	private String name;
	private String regex;
	private String validation;
	private Category missing;
	private Category malformed;
	
	public String getName() {
		return name;
	}
	public String getRegex() {
		return regex;
	}
	public String getValidation() {
		return validation;
	}
	public Category getMissing() {
		return missing;
	}
	public Category getMalformed() {
		return malformed;
	}
	public Rule(String name, String regex, String validation, Category missing,
			Category malformed) {
		super();
		this.name = name;
		this.regex = regex;
		this.validation = validation;
		this.missing = missing;
		this.malformed = malformed;
	}
	@Override
	public String toString() {
		return "Rule [malformed=" + malformed + ", missing=" + missing
				+ ", name=" + name + ", regex=" + regex + ", validation="
				+ validation + "]";
	}

}
