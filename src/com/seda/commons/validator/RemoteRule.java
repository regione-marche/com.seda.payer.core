/**
 * 
 */
package com.seda.commons.validator;

/**
 * @author dbadm
 *
 */
public class RemoteRule extends RuleOptionsHandler {
	private final int optionsExpected=1;
	/* (non-Javadoc)
	 * @see com.seda.commons.validator.RuleBoundaryHandler#apply()
	 */
	@Override
	public boolean apply() throws ValidationException {
		//checkOptionsExpected();
		if(getLocale().getCountry().equals("IT")) {
			throw new ValidationException(RulesNames.REMOTE,Messages.notSupportedIT.format());
		}else {
		   throw new ValidationException(RulesNames.REMOTE,Messages.notSupported.format());
		}
		//return true;		
	}

	/* (non-Javadoc)
	 * @see com.seda.commons.validator.RuleBoundaryHandler#getBoundariesExpected()
	 */
	@Override
	public int optionsExpected() {
		return this.optionsExpected;
	}

	public RemoteRule() {}	
	
	public RemoteRule(String value) {
		setValue(value);
	}

	public RemoteRule(String value, String remote) throws ValidationException {
		setValue(value);
		setOptions(new String[]{remote});
	}	
}
