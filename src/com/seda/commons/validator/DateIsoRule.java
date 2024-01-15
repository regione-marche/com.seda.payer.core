package com.seda.commons.validator;

import java.text.ParseException;
import java.text.SimpleDateFormat;

public class DateIsoRule extends RuleHandler {

	@Override
	public boolean apply() throws ValidationException {
		if (getValue()==null) {
			throw new ValidationException(RulesNames.DATE_ISO,Messages.DATEISO.format());			
		}
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			sdf.setLenient(false);
			sdf.parse(getValue());
		} catch (ParseException e) {
			throw new ValidationException(RulesNames.DATE_ISO,Messages.DATEISO.format());
		}
//		if (RegexManager.dateISO.doesntMatch(getValue()))
//			throw new ValidationException(Messages.DATEISO.format());
//		
//		try {
//			Date.valueOf(getValue());
//		} catch (IllegalArgumentException x) {
//			throw new ValidationException(Messages.DATEISO.format());
//		}
		return true;
	}

	public DateIsoRule() {}	
	
	public DateIsoRule(String value) {
		setValue(value);
	}	

}
