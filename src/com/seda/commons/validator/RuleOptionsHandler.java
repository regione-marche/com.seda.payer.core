/**
 * 
 */
package com.seda.commons.validator;

import java.util.Locale;

/**
 * @author dbadm
 *
 */
public abstract class RuleOptionsHandler extends RuleHandler implements
		RuleOptionsInterface {

	private String[] options;
	
	/* (non-Javadoc)
	 * @see com.seda.commons.validator.RuleHandler#getBoundariesExpected()
	 */	
	public abstract int optionsExpected();
	/* (non-Javadoc)
	 * @see com.seda.commons.validator.RuleHandler#checkBoundariesExpected()
	 */	
	public final void checkOptionsExpected() {
		if (getOptions().length==optionsExpected())
			return;
		throw new RuntimeException(Messages.unExpectedOptions.format(getLocale(), optionsExpected(),getOptions().length));	
	}
	/* (non-Javadoc)
	 * @see com.seda.commons.validator.RuleHandler#apply()
	 */
	@Override
	public abstract boolean apply() throws ValidationException;

	public final boolean apply(String value, String[] options) throws ValidationException {
		setValue(value);
		setOptions(options);
		return apply();
	}
	/* (non-Javadoc)
	 * @see com.seda.commons.validator.RuleBoundaryInterface#getBoundary()
	 */
	public final String[] getOptions() {
		return this.options;
	}

	/* (non-Javadoc)
	 * @see com.seda.commons.validator.RuleBoundaryInterface#setBoundary(java.lang.String[])
	 */
	public final void setOptions(String[] options) {
		this.options=options;
	}

}
