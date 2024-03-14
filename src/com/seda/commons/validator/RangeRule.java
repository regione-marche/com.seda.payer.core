/**
 * 
 */
package com.seda.commons.validator;

/**
 * @author dbadm
 *
 */
public class RangeRule extends RuleOptionsHandler {
	private final int optionsExpected=2;
	/* (non-Javadoc)
	 * @see com.seda.commons.validator.RuleBoundaryHandler#apply()
	 */
	@Override
	public boolean apply() throws ValidationException {
		checkOptionsExpected();
		if (getValue().compareTo(getOptions()[0]) < 0 || 
				getValue().compareTo(getOptions()[1]) > 0) {
			if(getLocale().getCountry().equals("IT")) {
			  throw new ValidationException(RulesNames.RANGE, Messages.RANGEIT.format(getOptions()[0],getOptions()[1]));
			}else {
			  throw new ValidationException(RulesNames.RANGE, Messages.RANGE.format(getOptions()[0],getOptions()[1]));
			}
		}
		
		return true;		
	}

	/* (non-Javadoc)
	 * @see com.seda.commons.validator.RuleBoundaryHandler#getBoundariesExpected()
	 */
	@Override
	public int optionsExpected() {
		return this.optionsExpected;
	}

	public RangeRule() {}	
	
	public RangeRule(String value) {
		setValue(value);
	}

	public RangeRule(String value, String min, String max) throws ValidationException {
		setValue(value);
		setOptions(new String[]{min,max});
	}	

}
