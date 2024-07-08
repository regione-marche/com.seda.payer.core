/**
 * 
 */
package com.seda.commons.validator;

import java.util.Locale;

/**
 * 
 * @author dbadm
 *
 */
public interface RuleOptionsInterface {

	public int optionsExpected();
	public void checkOptionsExpected() throws ValidationException;	
	
	public abstract String[] getOptions();
	public abstract void setOptions(String[] boundaries) throws ValidationException;
	
	
	public default boolean apply(Locale locale,String value) throws ValidationException {
		
		checkOptionsExpected();
		int min=0;
		try {
			min = Integer.parseInt(getOptions()[0]);			
		} catch (NumberFormatException x) {
			throw new ValidationException(RulesNames.MIN_LENGTH, Messages.integerExpected.format(min));
		}
		if (value.length() < min) {
			if(locale.equals(new Locale("de_DE"))) {
				throw new ValidationException(RulesNames.MIN_LENGTH, Messages.MINLENGTHDE.format(min));
			}else {
			throw new ValidationException(RulesNames.MIN_LENGTH, Messages.MINLENGTH.format(min));
			}
		}
		
		return true;	
	}
	
}
