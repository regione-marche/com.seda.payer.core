/**
 * 
 */
package com.seda.commons.validator;

/**
 * @author dbadm
 *
 */
public class MaxIntRule extends RuleOptionsHandler {
	
	private final int optionsExpected=1;
	/* (non-Javadoc)
	 * @see com.seda.commons.validator.RuleBoundaryHandler#apply()
	 */
	@Override
	public boolean apply() throws ValidationException {
		checkOptionsExpected();
		int value=0;
		int option=0;
		boolean error=false;
		try {
			value=Integer.parseInt(getValue());
			option=Integer.parseInt(getOptions()[0]);
		} catch (NumberFormatException x) {
			error=true;
		}
		if ((value > option) || error) 
			throw new ValidationException(RulesNames.MAX_INT,Messages.MAXINT.format(getOptions()[0]));
		
		return true;		
	}

	/* (non-Javadoc)
	 * @see com.seda.commons.validator.RuleBoundaryHandler#getBoundariesExpected()
	 */
	@Override
	public int optionsExpected() {
		return this.optionsExpected;
	}

	public MaxIntRule() {}	
	
	public MaxIntRule(String value) {
		setValue(value);
	}

	public MaxIntRule(String value, String max) throws ValidationException {
		setValue(value);
		setOptions(new String[]{max});
	}	

}
