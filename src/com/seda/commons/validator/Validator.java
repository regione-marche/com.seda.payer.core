/**
 * 
 */
package com.seda.commons.validator;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import com.seda.commons.string.Convert;
import com.seda.commons.string.Remove;

/**
 * @author Seda Lab
 *
 */
public class Validator {

	public static final char optionIndicator = '=';
	public static final char optionSeparator = ',';
	public static final char[] optionsDelimiter = {'[',']'};

	public static final String IGNORE = "ignore";
	
	private String value;
	private String[] rules;
private Locale locale;	//04082016 GG
	
	private Map<String, RuleHandler> rulesMap;
	
	public void setValue(String value) {
		this.value=value;
	}
	public String getValue() {
		return this.value;
	}
	public void setRules(String[] rules) {
		this.rules=rules;
	}
	public String[] getRules() {
		return this.rules;
	}
//04082016 GG - inizio
	public Locale getLocale() {
		return this.locale;
	}
	public void setLocale(Locale locale) {
		this.locale = locale;
	}
	//04082016 GG - fine
	
	//=============================================
	private Map<String, RuleHandler> getRulesMap() {
		if (rulesMap==null) {
			rulesMap= new HashMap<String, RuleHandler>();
		}
		return rulesMap;
	}
	
	//====================================
	
	public Validator() throws ValidationException {
		for (Rules rule : Rules.values()) {
			try {
				getRulesMap().put(rule.key(), (RuleHandler)Class.forName(rule.className()).newInstance());
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		}			

	}
	public Validator(String value) throws ValidationException  {
		this();
		setValue(value);
	}	
	
	public boolean validateRules(String rulesBuffer) throws ValidationException {
		return validateRules(Convert.stringToArray(rulesBuffer, Validation.rulesSeparator));
	}
	
	public boolean validateRules(String... rules) throws ValidationException {
		setRules(rules);		
		// no rule! return.
		if (getRules().length==0) return false;
		// check for ignore keyword
		for (int i=0, j=getRules().length; i < j; i++) {
			// keyword ignore found
			if (getRules()[i].equals(IGNORE)) {
				// check for empty string
				if (getValue().trim().length()==0)
					return false;
			}
		}
		// loop through the rules
		for (int i=0, j=getRules().length; i < j; i++) {
			// validate the rules
			if (!getRules()[i].equals(IGNORE)) {
				validate(
						/* Convert.stringToArray(getRules()[i],optionIndicator)*/
						getSingleRule(getRules()[i])
						);				
			}
		}
		return true;
	}
	
	private String[] getSingleRule(String rule) {
		int posix = rule.indexOf(optionIndicator);
		if (posix!=-1) {
			return new String[]{rule.substring(0,posix),rule.substring(posix+1)};
		}
		return new String[]{rule};
	}
	
	public boolean validate() throws ValidationException {
		return validateRules(getRules());
	}
	//====================================	
	private void validate(String[] rule) throws ValidationException {
		// check the length; if has length 1, this may be a boolean rules such as required, or email 
		switch (rule.length) {
		case 1:
			// case of boolean rules, like "required", or "email"
			validate(rule[0],Boolean.TRUE.toString());
			break;
		case 2:
			// case of rules with options, such as "range=[AA,BBB]", or "minlength=3", or boolean option like email=true 
			validate(rule[0],rule[1]);			
			break;			
		default:
			throw new RuntimeException(Messages.notSupportedRule.format(locale, Convert.arrayToString(rule, optionIndicator)));
		}
	}

	private void validate(String key, String options) throws ValidationException {
		// check for the existence in rules realm
		exists(key);
		// check for mutually exclusive rules
		exclusives(key);
		// validate the rule
		validateRule(key,options);
	}
	
	private void exists(String key) throws ValidationException {
		// find for the rule in the realm rules
		if (Rules.getRulesByKey(key)==null)
			throw new RuntimeException(Messages.notSupportedRule.format(locale, key));
	}
	
	private void exclusives(String key) throws ValidationException {
		// get the exclusive rule list of the rule defined by the key
		String[] exclusives = Rules.getRulesByKey(key).exclusives();
		// loop the exclusive rule and try to find it in the map rules
		for (int i=0, j=exclusives.length; i < j; i++) {
			// if exclusive rule was found
			for (int k=0, z=getRules().length; k < z; k++) {
				//loop through the rules for find another exclusive rule
				if (getRules()[k].equals(exclusives[i])) {
					// if found, throw error
					throw new RuntimeException(Messages.exclusivesRules.format(locale,key,exclusives[i]));
				}				
			}
		}
		exclusives = null;
	}	
	
	private void validateRule(String key, String options) throws ValidationException {
		if (Rules.getRulesByKey(key).hasOptions()) {
			RuleOptionsHandler optionsHandler = (RuleOptionsHandler) getRulesMap().get(key);			
//04082016 GG - inizio
			if (locale!=null)
				optionsHandler.setLocale(locale);
			else 
				optionsHandler.setLocale(Locale.getDefault());
			//04082016 GG - fine
			String[] o = checkOptions(key, options, optionsHandler.optionsExpected(), Rules.getRulesByKey(key).hasIntegerOptions());
			optionsHandler.apply(getValue(),o);
			optionsHandler=null;
		} else {
			RuleHandler ruleHandler = getRulesMap().get(key);			
//04082016 GG - inizio
			if (locale!=null)
				ruleHandler.setLocale(locale);
			else 
				ruleHandler.setLocale(Locale.getDefault());
			//04082016 GG - fine
			checkBool(key, options);
			if (Boolean.parseBoolean(options)) {
				ruleHandler.apply(getValue(),locale);
			}
			ruleHandler=null;
		}
	}
	
	private String[] checkOptions(String key, String options, int numParams, boolean integer) throws ValidationException {
		// split string into array
		String[] parameters;
		if (key.equals(RulesNames.ACCEPT)) {
			parameters = new String[]{options};
		} else {
			parameters = Convert.stringToArray(cleanValue(key, options, numParams), optionSeparator);	
		}
		
		if (parameters.length == numParams) {
			// loop through the parameters and check for an integer in according to the boolean integer
			if (integer) {			
				for (int i=0, j=parameters.length; i < j; i++) {
					try {
						// check for integer data
						Integer.parseInt(parameters[i]);						
					} catch (NumberFormatException x) {
						throw new RuntimeException(Messages.notNumeric.format(locale, key + optionIndicator + options,i+1));						
					}					
				}
			}
			return parameters;
		} else {
			throw new RuntimeException(Messages.unExpectedOptionsRule.format(locale, key, parameters.length, numParams));
		}
	}
	
	private void checkBool(String key, String options) throws ValidationException {
		// check if the value is a boolean specification
		if (options.equals(Boolean.TRUE.toString()) || options.equals(Boolean.FALSE.toString())) {
			return;
		}
		throw new RuntimeException(Messages.notBoolean.format(locale, key));			
	}
	
	private String cleanValue(String key, String value, int number) throws ValidationException {
		// temporary string to manipulate the original value
		String v;
		// Switch the processing activity in according to the number of parameters
		switch (number) {
		case 1:
			v = new String(value);
			break;
		default:
			// check for multiple integer definition 
			if (value.startsWith("[") && value.endsWith("]" )) {
				v = Remove.charArray(value, optionsDelimiter);
			} else {
				throw new RuntimeException(Messages.badOptionsFormat.format(locale, key));				
			}
			break;
		}
		return v;
	}
	
	public void finalize() {
		destroy();
	}
	
	public void destroy() {
		this.rulesMap=null;
	}
}
