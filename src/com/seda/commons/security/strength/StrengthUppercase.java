package com.seda.commons.security.strength;

import java.util.regex.Matcher;

public class StrengthUppercase extends StrengthSupport {
	
	public StrengthUppercase() {
		compile(".??[A-Z]");
	}
	
	public int score(String value) {
		int score=0;
		int upper=0;
		if (value == null)
			return score;		
		Matcher m = matcher(value);
		while (m.find()){
			 // [verified] at least one upper case letter
			upper += 1;
		}
		if (upper > 0) {
			score = 5; //5 point for an upper case character
		}
		return score;
	}
	
}
