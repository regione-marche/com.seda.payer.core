/**
 * 
 */
package com.seda.commons.validator;

import com.seda.commons.regex.RegexManager;
/**
 * @author dbadm
 *
 */
public class CreditCardRule extends RuleHandler {

	/* (non-Javadoc)
	 * @see com.seda.commons.validator.RuleHandler#apply()
	 */
	@Override
	public boolean apply() throws ValidationException {
		if (RegexManager.creditcard.doesntMatch(getValue())) {
		   if(getLocale().getCountry().equals("IT")) {
			  throw new ValidationException(RulesNames.CREDIT_CARD,Messages.CREDITCARDIT.format());
			 }else {
			  throw new ValidationException(RulesNames.CREDIT_CARD,Messages.CREDITCARD.format());
			 }
		} 
		
		if (checkLuhn()) {
			return true;
		}else {
			   if(getLocale().getCountry().equals("IT")) {
					  throw new ValidationException(RulesNames.CREDIT_CARD,Messages.CREDITCARDIT.format());
					 }else {
					  throw new ValidationException(RulesNames.CREDIT_CARD,Messages.CREDITCARD.format());
					 }
		}
	}

	public CreditCardRule() {}
	
	public CreditCardRule(String value) {
		setValue(value);
	}	

	private boolean checkLuhn() {
		// based on http://en.wikipedia.org/wiki/Luhn		
		String ccard = new String(getValue());
		// accept only digits and dashes		
		if (ccard.matches("[^0-9-]+")) 
			return false;
		// remove dashes
		ccard.replace("/\\D/g", "");
		// work areas
		boolean even=false;
		char c;
	    int n, nSum=0;		
		// run the Luhn algorithm
		for (int i=ccard.length()-1; i >= 0; i--) {
			c = ccard.charAt(i);
			n = (int) c;
			if (even) {
				if ((n*=2)>9)
					n-=9;
			}
			nSum+=n;
			even=!even;
		}
		return (nSum%10)==0;
	}

}
