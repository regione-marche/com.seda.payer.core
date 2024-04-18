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
		if (RegexManager.email.doesntMatch(getValue())) {
			if(getLocale().getCountry().equals("IT")) {
			   throw new ValidationException(RulesNames.EMAIL,Messages.EMAILIT.format());
			}else {
				throw new ValidationException(RulesNames.EMAIL,Messages.EMAIL.format());
			}
		}else {
		  return true;
		}
	}
	
	public EmailRule() {}	
	
	public EmailRule(String value) {
		setValue(value);
	}	
}
