/**
 * 
 */
package com.seda.commons.validator;

import java.sql.Date;

/**
 * @author dbadm
 *
 */
public class DateRule extends RuleHandler {

	/* (non-Javadoc)
	 * @see com.seda.commons.validator.RuleHandler#apply()
	 */
	@SuppressWarnings("deprecation")
	@Override
	public boolean apply() throws ValidationException {
		if (getValue()==null) {
			throw new ValidationException(RulesNames.DATE,Messages.DATEISO.format());			
		}
		try {
			Date.parse(getValue());
		} catch (IllegalArgumentException x) {
			throw new ValidationException(RulesNames.DATE,Messages.DATE.format());
		}
		return true;
	}

	public DateRule() {}	
	
	public DateRule(String value) {
		setValue(value);
	}		

}
