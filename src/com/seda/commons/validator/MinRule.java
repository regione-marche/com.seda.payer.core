/**
 * 
 */
package com.seda.commons.validator;

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
		if (getValue().compareTo(getOptions()[0]) < 0) 
			throw new ValidationException(RulesNames.MIN,Messages.MIN.format(getOptions()[0]));
		
		return true;		
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
	
}
