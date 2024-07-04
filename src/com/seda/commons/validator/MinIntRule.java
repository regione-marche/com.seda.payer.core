/**
 * 
 */
package com.seda.commons.validator;

/**
 * @author dbadm
 *
 */
public class MinIntRule extends RuleOptionsHandler {

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
		if ((value < option) || error) {
			if(getLocale().getCountry().equals("IT")) {
			  throw new ValidationException(RulesNames.MIN_INT,Messages.MININTIT.format(getOptions()[0]));
			}else {
			  throw new ValidationException(RulesNames.MIN_INT,Messages.MININT.format(getOptions()[0]));
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

	public MinIntRule() {}	
	
	public MinIntRule(String value) {
		setValue(value);
	}

	public MinIntRule(String value, String min) throws ValidationException {
		setValue(value);
		setOptions(new String[]{min});
	}	
	
}
