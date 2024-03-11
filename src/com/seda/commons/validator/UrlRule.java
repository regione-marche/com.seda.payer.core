/**
 * 
 */
package com.seda.commons.validator;

import com.seda.commons.regex.RegexManager;
/**
 * @author Seda Lab
 *
 */
public class UrlRule extends RuleHandler {

	/* (non-Javadoc)
	 * @see com.seda.commons.validator.RuleHandler#apply()
	 */
	@Override
	public boolean apply() throws ValidationException {
		if (RegexManager.url.doesntMatch(getValue())) {
			if(getLocale().getCountry().equals("IT")) {
				throw new ValidationException(RulesNames.URL, Messages.URLIT.format());
			}else {
			    throw new ValidationException(RulesNames.URL, Messages.URL.format());
			}
		}
			
		return true;			
	}
	
	public UrlRule() {}	
	
	public UrlRule(String value) {
		setValue(value);
	}	
}
