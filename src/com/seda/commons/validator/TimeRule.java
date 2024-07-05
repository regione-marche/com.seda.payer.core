/**
 * 
 */
package com.seda.commons.validator;

import com.seda.commons.regex.RegexManager;

/**
 * @author dbadm
 *
 */
public class TimeRule extends RuleHandler {

	/* (non-Javadoc)
	 * @see com.seda.commons.validator.RuleHandler#apply()
	 */
	@Override
	public boolean apply() throws ValidationException {
		if (RegexManager.time.doesntMatch(getValue()))
			throw new ValidationException(RulesNames.TIME, Messages.TIME.format());
		return true;
	}

	public TimeRule() {}	
	
	public TimeRule(String value) {
		setValue(value);
	}
}
