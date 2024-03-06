package com.seda.commons.security.strength;

import java.util.regex.Matcher;

public class StrengthSpecial extends StrengthSupport {

	public StrengthSpecial() {
		compile(".??[:,!,@,#,$,%,^,&,*,?,_,~]");
	}
	
	public int score(String value) {
		int score=0;
		int special=0;
		if (value == null)
			return score;		
		Matcher m = matcher(value);
		while (m.find()) {
			// [verified] at least one special character
			special += 1;
		}
		if (special > 0) {
			score += 5; //5 points for a special character;
			if (special > 1) {
				score += 5; //5 points for at least two special characters;
			}
		}
		return score;
	}

}
