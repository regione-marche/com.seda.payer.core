/**
 * 
 */
package com.seda.commons.validator;

/**
 * @author dbadm
 *
 */
public class RangeLengthRule extends RuleOptionsHandler {

	private final int optionsExpected=2;
	
	@Override
	public boolean apply() throws ValidationException {
		checkOptionsExpected();
		int min=0,max=0;
		try {
			min = Integer.parseInt(getOptions()[0]);	
			max = Integer.parseInt(getOptions()[1]);			
		} catch (NumberFormatException x) {
			if(getLocale().getCountry().equals("IT")) {
			  throw new ValidationException(RulesNames.RANGE_LENGTH,Messages.integerExpectedIT.format(min + " " + max));
			}else {
			  throw new ValidationException(RulesNames.RANGE_LENGTH,Messages.integerExpected.format(min + " " + max));
			}
		}
		if (getValue().length() < min || getValue().length() > max) {
			if(getLocale().getCountry().equals("IT")) {
				throw new ValidationException(RulesNames.RANGE_LENGTH,Messages.MAXLENGTHIT.format(min,max));
			}else {
			  throw new ValidationException(RulesNames.RANGE_LENGTH,Messages.MAXLENGTH.format(min,max));
			}
		}
		
		return true;		
	}

	@Override
	public int optionsExpected() {
		return this.optionsExpected;
	}		
	
	public RangeLengthRule() {}
	
	public RangeLengthRule(String value) {
		setValue(value);
	}

	public RangeLengthRule(String value, int minlength, int maxlength) throws ValidationException {
		setValue(value);
		setOptions(new String[]{String.valueOf(minlength),String.valueOf(maxlength)});
	}
}