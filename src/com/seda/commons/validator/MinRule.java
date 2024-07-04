/**
 * 
 */
package com.seda.commons.validator;

import java.util.Locale;

/**
 * @author dbadm
 *
 */
public class MinRule extends RuleOptionsHandler {

	private final int optionsExpected=1;
	
	/* (non-Javadoc)
	 * @see com.seda.commons.validator.RuleBoundaryHandler#apply()
	 */
	@Override
	public boolean apply() throws ValidationException {
		checkOptionsExpected();
		if (getValue().compareTo(getOptions()[0]) < 0) {
			if(getLocale().getCountry().equals("DE")) {
				throw new ValidationException(RulesNames.MIN,Messages.MINLENGTHDE.format(getOptions()[0]));
			}else {
				if(getLocale().getCountry().equals("IT")) {
					throw new ValidationException(RulesNames.MAX, Messages.MINIT.format(getOptions()[0]));
				}else {
			        throw new ValidationException(RulesNames.MAX, Messages.MIN.format(getOptions()[0]));
				}
			}
		}else {
		  return true;
		}
	}

	/* (non-Javadoc)
	 * @see com.seda.commons.validator.RuleBoundaryHandler#getBoundariesExpected()
	 */
	@Override
	public int optionsExpected() {
		return this.optionsExpected;
	}

	public MinRule() {}	
	
	public MinRule(String value) {
		setValue(value);
	}

	public MinRule(String value, String min) throws ValidationException {
		setValue(value);
		setOptions(new String[]{min});
	}
	
	public MinRule(String value, String min,Locale locale) throws ValidationException {
		setValue(value);
		setOptions(new String[]{min});
		setLocale(locale);
	}
	
}
