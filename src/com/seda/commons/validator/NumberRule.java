/**
 * 
 */
package com.seda.commons.validator;

import com.seda.commons.regex.RegexManager;

/**
 * @author dbadm
 *
 */
public class NumberRule extends RuleHandler {

	/* (non-Javadoc)
	 * @see com.seda.commons.validator.RuleHandler#apply()
	 */
	@Override
	public boolean apply() throws ValidationException {
		if (RegexManager.number.doesntMatch(getValue()))
			throw new ValidationException(RulesNames.NUMBER,Messages.NUMBER.format());
		
		return true;		
	}

	public NumberRule() {}	
	
	public NumberRule(String value) {
		setValue(value);
	}
}
