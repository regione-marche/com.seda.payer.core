/**
 * 
 */
package com.seda.commons.validator;

/**
 * @author dbadm
 *
 */
public class RangeIntRule extends RuleOptionsHandler {
	private final int optionsExpected=2;
	/* (non-Javadoc)
	 * @see com.seda.commons.validator.RuleBoundaryHandler#apply()
	 */
	@Override
	public boolean apply() throws ValidationException {
		checkOptionsExpected();
		
		int value=0;
		int minoption=0;
		int maxoption=0;
		boolean error=false;
		try {
			value=Integer.parseInt(getValue());
			minoption=Integer.parseInt(getOptions()[0]);
			maxoption=Integer.parseInt(getOptions()[1]);
		} catch (NumberFormatException x) {
			error=true;
		}
		if ((value > maxoption) || (value < minoption) || error) {
			if(getLocale().getCountry().equals("IT")) {
			  throw new ValidationException(RulesNames.RANGE_INT,Messages.RANGEINTIT.format(getOptions()[0],getOptions()[1]));
			}else {
			  throw new ValidationException(RulesNames.RANGE_INT,Messages.RANGEINT.format(getOptions()[0],getOptions()[1]));
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

	public RangeIntRule() {}	
	
	public RangeIntRule(String value) {
		setValue(value);
	}

	public RangeIntRule(String value, String min, String max) throws ValidationException {
		setValue(value);
		setOptions(new String[]{min,max});
	}	

}
