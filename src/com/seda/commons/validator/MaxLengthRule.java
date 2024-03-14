package com.seda.commons.validator;

public class MaxLengthRule extends RuleOptionsHandler {

	private final int optionsExpected=1;
	
	@Override
	public boolean apply() throws ValidationException {
		checkOptionsExpected();
		int max=0;
		try {
			max = Integer.parseInt(getOptions()[0]);			
		} catch (NumberFormatException x) {
			if(getLocale().getCountry().equals("IT")) {
				throw new ValidationException(RulesNames.MAX_LENGTH,Messages.integerExpectedIT.format(max));
			}else {
			   throw new ValidationException(RulesNames.MAX_LENGTH,Messages.integerExpected.format(max));
			}
		}
		if (getValue().length() > max) {
			if(getLocale().getCountry().equals("IT")) {
			  throw new ValidationException(RulesNames.MAX_LENGTH,Messages.MAXLENGTHIT.format(max));
			}else {
			  throw new ValidationException(RulesNames.MAX_LENGTH,Messages.MAXLENGTH.format(max));
			}
		}

		return true;		
	}

	@Override
	public int optionsExpected() {
		return this.optionsExpected;
	}		
	
	public MaxLengthRule() {}	
	
	public MaxLengthRule(String value) {
		setValue(value);
	}

	public MaxLengthRule(String value, int maxlength) throws ValidationException {
		setValue(value);
		setOptions(new String[]{String.valueOf(maxlength)});
	}
}