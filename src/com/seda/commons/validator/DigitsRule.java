/**
 * 
 */
package com.seda.commons.validator;

import com.seda.commons.regex.RegexManager;

/**
 * @author dbadm
 *
 */
public class DigitsRule extends RuleHandler {

	/* (non-Javadoc)
	 * @see com.seda.commons.validator.RuleHandler#apply()
	 */
	@Override
	public boolean apply() throws ValidationException {
		if (RegexManager.digits.doesntMatch(getValue()))
			throw new ValidationException(RulesNames.DIGITS,Messages.DIGITS.format());
		return true;
	}

	public DigitsRule() {}	
	
	public DigitsRule(String value) {
		setValue(value);
	}
}
