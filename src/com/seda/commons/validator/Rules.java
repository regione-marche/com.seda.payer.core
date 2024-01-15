/**
 * 
 */
package com.seda.commons.validator;

/**
 * @author Seda Lab
 *
 */
public enum Rules {
			REQUIRED(RulesNames.REQUIRED,RulesClasses.REQUIRED,RulesCodes.REQUIRED,false,false,RulesExclusives.REQUIRED),
			REMOTE(RulesNames.REMOTE,RulesClasses.REMOTE,RulesCodes.REMOTE,true,false,RulesExclusives.REMOTE),
			MIN_LENGTH(RulesNames.MIN_LENGTH,RulesClasses.MIN_LENGTH,RulesCodes.MIN_LENGTH,true,true,RulesExclusives.MIN_LENGTH),
			MAX_LENGTH(RulesNames.MAX_LENGTH,RulesClasses.MAX_LENGTH,RulesCodes.MAX_LENGTH,true,true,RulesExclusives.MAX_LENGTH),
			RANGE_LENGTH(RulesNames.RANGE_LENGTH,RulesClasses.RANGE_LENGTH,RulesCodes.RANGE_LENGTH,true,true,RulesExclusives.RANGE_LENGTH),
			MIN(RulesNames.MIN,RulesClasses.MIN,RulesCodes.MIN,true,false,RulesExclusives.MIN),
			MAX(RulesNames.MAX,RulesClasses.MAX,RulesCodes.MAX,true,false,RulesExclusives.MAX),
			RANGE(RulesNames.RANGE,RulesClasses.RANGE,RulesCodes.RANGE,true,false,RulesExclusives.RANGE),
			MIN_INT(RulesNames.MIN_INT,RulesClasses.MIN_INT,RulesCodes.MIN_INT,true,false,RulesExclusives.MIN_INT),
			MAX_INT(RulesNames.MAX_INT,RulesClasses.MAX_INT,RulesCodes.MAX_INT,true,false,RulesExclusives.MAX_INT),
			RANGE_INT(RulesNames.RANGE_INT,RulesClasses.RANGE_INT,RulesCodes.RANGE_INT,true,false,RulesExclusives.RANGE_INT),			
			EMAIL(RulesNames.EMAIL,RulesClasses.EMAIL,RulesCodes.EMAIL,false,false,RulesExclusives.EMAIL),
			URL(RulesNames.URL,RulesClasses.URL,RulesCodes.URL,false,false,RulesExclusives.URL),
			DATE(RulesNames.DATE,RulesClasses.DATE,RulesCodes.DATE,false,false,RulesExclusives.DATE),
			DATE_ISO(RulesNames.DATE_ISO,RulesClasses.DATE_ISO,RulesCodes.DATE_ISO,false,false,RulesExclusives.DATE_ISO),
			DATE_EUR(RulesNames.DATE_EUR,RulesClasses.DATE_EUR,RulesCodes.DATE_EUR,false,false,RulesExclusives.DATE_EUR),
			TIME(RulesNames.TIME,RulesClasses.TIME,RulesCodes.TIME,false,false,RulesExclusives.TIME),
			TIME_SHORT(RulesNames.TIME_SHORT,RulesClasses.TIME_SHORT,RulesCodes.TIME_SHORT,false,false,RulesExclusives.TIME_SHORT),
			NUMBER(RulesNames.NUMBER,RulesClasses.NUMBER,RulesCodes.NUMBER,false,false,RulesExclusives.NUMBER),
			NUMBER_IT(RulesNames.NUMBER_IT,RulesClasses.NUMBER_IT,RulesCodes.NUMBER_IT,false,false,RulesExclusives.NUMBER_IT),			
			DIGITS(RulesNames.DIGITS,RulesClasses.DIGITS,RulesCodes.DIGITS,false,false,RulesExclusives.DIGITS),
			CREDIT_CARD(RulesNames.CREDIT_CARD,RulesClasses.CREDIT_CARD,RulesCodes.CREDIT_CARD,false,false,RulesExclusives.CREDIT_CARD),
			ACCEPT(RulesNames.ACCEPT,RulesClasses.ACCEPT,RulesCodes.ACCEPT,true,false,RulesExclusives.ACCEPT),
			EQUALTO(RulesNames.EQUALTO,RulesClasses.EQUALTO,RulesCodes.EQUALTO,true,false,RulesExclusives.EQUALTO)
			;

	private String key;
	private int code;
	private boolean options;
	private boolean integerOptions;	
	private String[] exclusives;
	private String className;
	
	public String key() {return this.key;}
	public int code() {return this.code;}
	public boolean hasOptions() {return this.options;}
	public boolean hasIntegerOptions() {return this.integerOptions;}
	public String[] exclusives() {return this.exclusives;}
	public String className() {return this.className;}
	
	Rules(String key, String className, int code, boolean options, boolean integerOptions, String... exclusives) {
		this.key=key;
		this.className=className;		
		this.code=code;
		this.options=options;
		this.integerOptions=integerOptions;		
		this.exclusives=exclusives;
	}
	
	public static Rules getRulesByName(String name) {
		for (Rules c : Rules.values()) {
			if ( c.name().equals(name)) return c;
		}
		return null;
	}

	public static Rules getRulesByKey(String key) {
		for (Rules c : Rules.values()) {
			if ( c.key().equals(key)) return c;
		}
		return null;
	}		
	
	public static Rules getRulesByCode(int code) {
		for (Rules c : Rules.values()) {
			if ( c.code() == code) return c;
		}
		return null;
	}	
	
}
