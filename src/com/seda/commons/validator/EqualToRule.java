/**
 * 
 */
package com.seda.commons.validator;
/**
 * @author Seda Lab
 *
 */
public class EqualToRule extends RuleOptionsHandler {
	private final int optionsExpected=1;
	/* (non-Javadoc)
	 * @see com.seda.commons.validator.RuleBoundaryHandler#apply()
	 */
	@Override
	public boolean apply() throws ValidationException {
		checkOptionsExpected();
		if (getValue().equalsIgnoreCase(getOptions()[0])) {
			return true;
		}else {
			if(getLocale().getCountry().equals("IT")) {
			 throw new ValidationException(RulesNames.ACCEPT,Messages.ACCEPTIT.format());
			}
			 throw new ValidationException(RulesNames.ACCEPT,Messages.ACCEPT.format());
		}
		
		
	}
	/* (non-Javadoc)
	 * @see com.seda.commons.validator.RuleBoundaryHandler#getBoundariesExpected()
	 */
	@Override
	public int optionsExpected() {
		return optionsExpected;
	}

	public EqualToRule() {}	
	
	public EqualToRule(String value) {
		setValue(value);
	}

	public EqualToRule(String value, String anotherString) throws ValidationException {
		setValue(value);
		setOptions(new String[]{anotherString});
	}	

}
