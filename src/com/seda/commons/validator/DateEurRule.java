/**
 * 
 */
package com.seda.commons.validator;

import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * @author Seda Lab
 *
 */
public class DateEurRule extends RuleHandler {

	/* (non-Javadoc)
	 * @see com.seda.commons.validator.RuleHandler#apply()
	 */
	@Override
	public boolean apply() throws ValidationException {
		if (getValue()==null) {
			throw new ValidationException(RulesNames.DATE_EUR,Messages.DATEEUR.format());			
		}		
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
			sdf.setLenient(false);
			sdf.parse(getValue());
		} catch (ParseException e) {
			throw new ValidationException(RulesNames.DATE_EUR,Messages.DATEEUR.format());
		}
//		if (RegexManager.dateEUR.doesntMatch(getValue()))
//			throw new ValidationException(Messages.DATEEUR.format());
//		
//		try {
//			Date.valueOf(Convert.eurToIso(getValue()));
//		} catch (IllegalArgumentException x) {
//			throw new ValidationException(Messages.DATEEUR.format());
//		}
		return true;
	}

	public DateEurRule() {}	
	
	public DateEurRule(String value) {
		setValue(value);
	}	

}
