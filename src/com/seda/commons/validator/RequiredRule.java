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
			
			if(getLocale().getCountry().equals("DE")) {
				throw new ValidationException(RulesNames.REQUIRED, Messages.REQUIREDDE.format());
			}else {
				if(getLocale().getCountry().equals("IT")) {
					throw new ValidationException(RulesNames.MAX, Messages.REQUIREDIT.format());
				}else {
			        throw new ValidationException(RulesNames.MAX, Messages.REQUIRED.format());
				}
			}
		}
		
		return true;		
	}

	public RequiredRule() {}	
	
	public RequiredRule(String value) {
		setValue(value);
	}
}
