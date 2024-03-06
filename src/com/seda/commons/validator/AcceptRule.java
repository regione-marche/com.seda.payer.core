/**
 * 
 */
package com.seda.commons.validator;

import java.util.regex.PatternSyntaxException;

/**
 * @author dbadm
 *
 */
public class AcceptRule extends RuleOptionsHandler {
	private final int boundariesExpected=1;
	/* (non-Javadoc)
	 * @see com.seda.commons.validator.RuleBoundaryHandler#apply()
	 * PatternSyntaxException - if the regular expression's syntax is invalid
	 */
	@Override
	public boolean apply() throws ValidationException, PatternSyntaxException {
		checkOptionsExpected();
		if (getValue().matches(getOptions()[0]))
			return true;
		
		throw new ValidationException(RulesNames.ACCEPT,Messages.ACCEPT.format());
	}
	/* (non-Javadoc)
	 * @see com.seda.commons.validator.RuleBoundaryHandler#getBoundariesExpected()
	 */
	@Override
	public int optionsExpected() {
		return boundariesExpected;
	}

	public AcceptRule() {}
	
	public AcceptRule(String value) {
		setValue(value);
	}

	public AcceptRule(String value, String regex) throws ValidationException {
		setValue(value);
		setOptions(new String[]{regex});
	}	
}
