/**
 * 
 */
package com.seda.commons.security.strength;

import java.util.regex.Matcher;

/**
 * @author f.ricci
 *
 */
public class StrengthNumber extends StrengthSupport {

	public StrengthNumber() {
		compile(".??[0-9]");
	}
	
	public int score(String value) {
		int score=0;
		int numbers=0;
		if (value == null)
			return score;		
		Matcher m = matcher(value);
		while (m.find()) {
			// [verified] at least one number
			numbers += 1;
		}
		if (numbers > 0) {
			score = 5; //5 points
			if (numbers > 1) {
				score += 2; //2 points for at least two numbers
				if (numbers > 2) {
					score += 3; //3 points for at least three numbers
				}
			}
		}
		return score;
	}

}
