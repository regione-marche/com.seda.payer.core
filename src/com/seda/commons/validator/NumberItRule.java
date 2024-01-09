/**
 * 
 */
package com.seda.commons.validator;

import com.seda.commons.regex.RegexManager;

/**
 * @author dbadm
 *
 */
public class NumberItRule extends RuleHandler {

	/* (non-Javadoc)
	 * @see com.seda.commons.validator.RuleHandler#apply()
	 */
	@Override
	public boolean apply() throws ValidationException {
		if (RegexManager.numberIT.doesntMatch(getValue()))
			throw new ValidationException(RulesNames.NUMBER_IT,Messages.NUMBERIT.format());
		
		return true;
	}

	public NumberItRule() {}	
	
	public NumberItRule(String value) {
		setValue(value);
	}
}