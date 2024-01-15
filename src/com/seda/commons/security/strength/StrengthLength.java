/**
 * 
 */
package com.seda.commons.security.strength;

/**
 * @author f.ricci
 *
 */
public class StrengthLength extends StrengthSupport {

	/* (non-Javadoc)
	 * @see com.seda.commons.security.strength.StrengthSupport#score(java.lang.String)
	 */
	public int score(String value) {
		int score=0;
		if (value == null)
			return score;
		// PASSWORD LENGTH
		int length = value.length();
		if (length < 5)	{
			// length 4 or less
			score = 3; //3 points;
		} else if (length > 4 && length < 8){
			 // length between 5 and 7
			score = 6; //6 points;
		} else if (length > 7 && length < 16) {
			// length between 8 and 15
			score=12; //12 points
		} else if (length > 15)	{
			 // length 16 or more
			score=18; //18 point 
		}
		return score;
	}

}
