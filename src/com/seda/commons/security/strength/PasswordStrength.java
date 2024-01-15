/**
 * 
 */
package com.seda.commons.security.strength;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
/**
 * @author f.ricci
 *
 */
public class PasswordStrength {

	/*
	 * Implementare anche con la gestione della parametrizzazione esterna o la possibilità di aggiungere
	 * delle regole proprie.
	 * 
	 * Ora le combo sono fisse ma andrebbero rese dinamiche (funzione ricorsiva?)
	 * 
	 * Implementare anche delle regole base di policy della password, anche da configurazione esterna:
	 * PASSWORD_MIN_LENGTH
	 * PASSWORD_MAX_LENGTH
	 * PASSWORD_NUMERIC?
	 * PASSWORD_MIXED_CASE
	 * PASSWORD_STRENGTH minimum value
	 * PASSWORD_SPECIAL
	 * 
	 */
	
	private static PasswordStrength me;
	
	public static final int VERY_WEAK=15;
	public static final int WEAK=24;
	public static final int MEDIOCRE=34;
	public static final int STRONG=44;
	public static final int VERY_STRONG=45;	
	
	private Map<String, StrengthSupport> strengthMap;
	
	static {
		me = new PasswordStrength();
	}
	
	private PasswordStrength(){
		// serve salvarsi una mappa?
		strengthMap=Collections.synchronizedMap(new HashMap<String, StrengthSupport>(StrengthFactors.values().length));
		for (StrengthFactors strength : StrengthFactors.values()) {
			strengthMap.put(strength.name(), strength.getStrengthSupport());
		}
	}
	
	public static PasswordStrength instance() {
		return me;
	}
	
	public static String verdictToString(int verdict) {
		switch (verdict) {
		case VERY_WEAK: return "very weak";
		case WEAK: return "weak";
		case MEDIOCRE: return "mediocre";
		case STRONG: return "strong";
		case VERY_STRONG: return "very strong";
		default: return "unknown";
		}
	}
	
	public int check(String value) {
		int totalScore=0, partialScore=0;
		// per le combo da fare in modo dinamico su regole mappate
		int upper = 0, lower = 0, numbers = 0, special = 0, length = 0;  
		for (StrengthFactors strength : StrengthFactors.values()) {
			partialScore = strength.getStrengthSupport().score(value);
			switch (strength) {
			case LENGTH: length=partialScore;
				break;
			case LOW_CASE: lower=partialScore;
				break;
			case NUMBER: numbers=partialScore;
				break;
			case SPECIAL: special=partialScore;
				break;
			case UPPER_CASE: upper=partialScore;
				break;					
			}
			totalScore+=partialScore;
		}
		
		// COMBOS da fare dinamiche in base a regole
		if (upper > 0 && lower > 0)	{
			 // [verified] both upper and lower case
			totalScore += 2; //2 combo points for upper and lower letters;
		}
		if ((upper > 0 || lower > 0) && numbers > 0) {
			// [verified] both letters and numbers			
			totalScore += 2; //2 combo points for letters and numbers;
		}
		if ((upper > 0 || lower > 0) && numbers > 0 && special > 0) {
			// [verified] letters, numbers, and special characters
			totalScore += 2; //2 combo points for letters, numbers and special chars;
		}
		if (upper > 0 && lower > 0 && numbers > 0 && special > 0) {
			// [verified] upper, lower, numbers, and special characters
			totalScore += 2; //2 combo points for upper and lower case letters, numbers and special chars;
		}
		
		return evaluate(totalScore);
	}
	
	private int evaluate(int score) {
		int verdict=0;
		if (score < 16) {
			verdict = VERY_WEAK; //"very weak"
		} else if (score > 15 && score < 25) {
			verdict = WEAK; //"weak"
		} else if (score > 24 && score < 35) {
			verdict = MEDIOCRE; //"mediocre"
		} else if (score > 34 && score < 45) {
			verdict = STRONG; //"strong"
		} else {
			verdict = VERY_STRONG; //"very strong"
		}
		return verdict;
	}
	
	
}
