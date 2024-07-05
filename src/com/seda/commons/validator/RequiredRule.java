/**
 * 
 */
package com.seda.commons.validator;

import com.seda.commons.string.Pad;
/**
 * @author dbadm
 *
 */
public class RequiredRule extends RuleHandler {

	@Override
	public boolean apply() throws ValidationException {
		if (getValue()==null || 
				getValue().length()==0 || 
				getValue().equals(Pad.makeCharArrayToString(' ', getValue().length()))) {
			throw new ValidationException(RulesNames.REQUIRED, Messages.REQUIRED.format()); 
		}
		
		return true;		
	}

	public RequiredRule() {}	
	
	public RequiredRule(String value) {
		setValue(value);
	}
}
