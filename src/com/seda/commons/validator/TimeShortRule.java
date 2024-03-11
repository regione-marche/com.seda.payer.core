/**
 * 
 */
package com.seda.commons.validator;

import com.seda.commons.regex.RegexManager;

/**
 * @author dbadm
 *
 */
public class TimeShortRule extends RuleHandler {

	/* (non-Javadoc)
	 * @see com.seda.commons.validator.RuleHandler#apply()
	 */
	@Override
	public boolean apply() throws ValidationException {
		if (RegexManager.time_short.doesntMatch(getValue())) {
			if(getLocale().getCountry().equals("IT")) {
				throw new ValidationException(RulesNames.TIME_SHORT,Messages.TIME_SHORTIT.format());
			}
			    throw new ValidationException(RulesNames.TIME_SHORT,Messages.TIME_SHORT.format());
		}
		return true;
	}

	public TimeShortRule() {}	
	
	public TimeShortRule(String value) {
		setValue(value);
	}
}
