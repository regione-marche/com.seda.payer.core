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
			if(getLocale().getCountry().equals("IT")) {
				throw new ValidationException(RulesNames.DATE,Messages.DATEISOIT.format());
			}else {
			    throw new ValidationException(RulesNames.DATE,Messages.DATEISO.format());
			}
		}
		try {
			Date.parse(getValue());
		} catch (IllegalArgumentException x) {
			if(getLocale().getCountry().equals("IT")) {
				throw new ValidationException(RulesNames.DATE,Messages.DATEISOIT.format());
			}else {
			    throw new ValidationException(RulesNames.DATE,Messages.DATEISO.format());
			}
		}
		return true;
	}

	public DateRule() {}	
	
	public DateRule(String value) {
		setValue(value);
	}		

}
