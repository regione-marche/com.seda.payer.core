/**
 * 
 */
package com.seda.commons.validator;

/**
 * @author dbadm
 *
 */
public class MinLengthRule extends RuleOptionsHandler {

	private final int optionsExpected=1;
	
	@Override
	public boolean apply() throws ValidationException {
		checkOptionsExpected();
		int min=0;
		try {
			min = Integer.parseInt(getOptions()[0]);			
		} catch (NumberFormatException x) {
			throw new ValidationException(RulesNames.MIN_LENGTH, Messages.integerExpected.format(min));
		}
		if (getValue().length() < min) 
			throw new ValidationException(RulesNames.MIN_LENGTH, Messages.MINLENGTH.format(min));
		
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

}
