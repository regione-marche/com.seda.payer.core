package com.seda.commons.security.strength;

import java.util.regex.Matcher;

public class StrengthLowcase extends StrengthSupport {

	public StrengthLowcase() {
		compile(".??[a-z]");
	}
	
	public int score(String value) {
		int score=0;
		int lower=0;
		if (value == null)
			return score;		
		Matcher m = matcher(value);
		while (m.find()){
			 // [verified] at least one lower case letter
			lower += 1;
		}
		if (lower > 0) {
			score = 1; //1 point for a lower case character
		}
		return score;
	}

}
