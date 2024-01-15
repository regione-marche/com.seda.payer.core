/**
 * 
 */
package com.seda.commons.validator;

import com.seda.commons.regex.RegexManager;

/**
 * @author dbadm
 *
 */
public class EmailRule extends RuleHandler {
	/* (non-Javadoc)
	 * @see com.seda.commons.validator.RuleHandler#apply()
	 */
	@Override
	public boolean apply() throws ValidationException {
		if (RegexManager.email.doesntMatch(getValue()))
			throw new ValidationException(RulesNames.EMAIL,Messages.EMAIL.format());
		return true;
	}
	
	public EmailRule() {}	
	
	public EmailRule(String value) {
		setValue(value);
	}	
}
