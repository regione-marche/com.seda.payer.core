/**
 * 
 */
package com.seda.commons.validator;

import java.util.Locale;

/**
 * @author dbadm
 *
 */
public abstract class RuleHandler implements RuleInterface {
	private String value;
private Locale locale;	//04082016 GG
	
	/* (non-Javadoc)
	 * @see com.seda.commons.validator.RuleInterface#apply()
	 */
	public abstract boolean apply() throws ValidationException;

	public final boolean apply(String value,Locale locale) throws ValidationException {
		setValue(value);
		setLocale(locale);
		return apply();
	}
	/* (non-Javadoc)
	 * @see com.seda.commons.validator.RuleInterface#getValue()
	 */
	public final String getValue() {
		return this.value;
	}
	/* (non-Javadoc)
	 * @see com.seda.commons.validator.RuleInterface#setValue(java.lang.String)
	 */
	public final void setValue(String value) {
		this.value=value;
	}

//04082016 GG - inizio
	public final Locale getLocale() {
		return this.locale;
	}
	
	public final void setLocale(Locale locale) {
		this.locale=locale;
	}
	//04082016 GG - fine

}
