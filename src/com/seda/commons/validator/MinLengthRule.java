/**
 * 
 */
package com.seda.commons.validator;

import java.util.Locale;

/**
 * @author dbadm
 *
 */
public class MinLengthRule extends RuleOptionsHandler {

	private final int optionsExpected=1;
	
	@Override
	public boolean apply() throws ValidationException {
		    //applyDe(getValue());
			checkOptionsExpected();
			int min=0;
			try {
				min = Integer.parseInt(getOptions()[0]);			
			} catch (NumberFormatException x) {
				throw new ValidationException(RulesNames.MIN_LENGTH, Messages.integerExpected.format(min));
			}
			if (getValue().length() < min) {
				if(getLocale().getCountry().equals("DE")) {
					throw new ValidationException(RulesNames.MIN_LENGTH, Messages.MINLENGTHDE.format(min));
				}else {
					if(getLocale().getCountry().equals("IT")) {
						throw new ValidationException(RulesNames.MIN_LENGTH, Messages.MINLENGTHIT.format(min));
					}else {
				        throw new ValidationException(RulesNames.MIN_LENGTH, Messages.MINLENGTH.format(min));
					}
				}
			}
		
		
		return true;		
	}
	
	private boolean applyDe(String value) throws ValidationException {
		
		checkOptionsExpected();
		int min=0;
		try {
			min = Integer.parseInt(getOptions()[0]);			
		} catch (NumberFormatException x) {
			throw new ValidationException(RulesNames.MIN_LENGTH, Messages.integerExpected.format(min));
		}
		if (value.length() < min) {
			if(getLocale().getCountry().equals("DE")) {
				throw new ValidationException(RulesNames.MIN_LENGTH, Messages.MINLENGTHDE.format(min));
			}else {
			throw new ValidationException(RulesNames.MIN_LENGTH, Messages.MINLENGTH.format(min));
			}
		}
		
		return true;	
	}

	@Override
	public int optionsExpected() {
		return this.optionsExpected;
	}		
	
	public MinLengthRule() {}	
	
	public MinLengthRule(String value) {
		setValue(value);
	}

	public MinLengthRule(String value, int minlength) throws ValidationException {
		setValue(value);
		setOptions(new String[]{String.valueOf(minlength)});
	}
	
	
	public String getTemplateFromFrontend(String template) {
		return getTemplateFromFrontendPrivate(template);
	}
	
	
	private String getTemplateFromFrontendPrivate(String template) {
		return template;
	}
	
	
	public static void main(String[] args) {
		MinLengthRule minRule = new MinLengthRule();
		
		minRule.setValue("12");
		minRule.setOptions(new String[] {"18"});
		minRule.setLocale(new Locale("de_DE"));
		
		try {
			minRule.apply();
		} catch (ValidationException e) {
			e.printStackTrace();
		}
	}

}
