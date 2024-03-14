/**
 * 
 */
package com.seda.commons.validator;

/**
 * @author dbadm
 *
 */
public class MaxRule extends RuleOptionsHandler {
	
	private final int optionsExpected=1;
	/* (non-Javadoc)
	 * @see com.seda.commons.validator.RuleBoundaryHandler#apply()
	 */
	@Override
	public boolean apply() throws ValidationException {
		checkOptionsExpected();
		if (getValue().compareTo(getOptions()[0]) > 0) {
			if(getLocale().getCountry().equals("DE")) {
				throw new ValidationException(RulesNames.MIN,Messages.MINLENGTHDE.format(getOptions()[0]));
			}else {
				if(getLocale().getCountry().equals("IT")) {
					throw new ValidationException(RulesNames.MAX, Messages.MAXIT.format(getOptions()[0]));
				}else {
			        throw new ValidationException(RulesNames.MAX, Messages.MAX.format(getOptions()[0]));
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

	public MaxRule() {}	
	
	public MaxRule(String value) {
		setValue(value);
	}

	public MaxRule(String value, String max) throws ValidationException {
		setValue(value);
		setOptions(new String[]{max});
	}	

}
