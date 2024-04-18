package com.seda.commons.validator;

import java.text.MessageFormat;
import java.util.ResourceBundle;

/**
 * @author dbadm
 *
 */
public enum Messages {
	// Messages from rule's apply method
	REQUIRED,
	REQUIREDDE,
	REMOTE,
	EMAIL,
	URL,
	DATE,
	DATEISO,
	DATEEUR,
	NUMBER,
	NUMBERIT,
	DIGITS,
	CREDITCARD,
	EQUALTO,
	ACCEPT,
	MAXLENGTH,
	MINLENGTH,
	MINLENGTHDE,
	RANGELENGTH,
	RANGE,
	MAX,
	MIN,
	RANGEINT,
	MAXINT,
	MININT,	
	TIME,
	TIME_SHORT,
	// IT
	REQUIREDIT,
	REMOTEIT,
	EMAILIT,
	URLIT,
	DATEIT,
	DATEISOIT,
	DATEEURIT,
	TIMEIT,
	TIME_SHORTIT,
	NUMBERITA,
	NUMBERITVAL,
	DIGITSIT,
	CREDITCARDIT,
	EQUALTOIT,
	ACCEPTIT,
	MAXLENGTHIT,
	MINLENGTHIT,
	RANGELENGTHIT,
	RANGEIT,
	MAXIT,
	MINIT,
	RANGEINTIT,
	MAXINTIT,
	MININTIT,
	// Error messages
	notSupported,
	unExpectedOptions, 
	notSupportedRule, 
	exclusivesRules, 
	internalUnMatchedCode, 
	notNumeric, 
	unExpectedOptionsRule, 
	notBoolean, 
	badOptionsFormat,
	integerExpected,
	//IT
	notSupportedIT,
	unExpectedOptionsIT,
	unExpectedOptionsRuleIT,
	notSupportedRuleIT,
	exclusivesRulesIT,
	internalUnMatchedCodeIT,
	notNumericIT,
	notBooleanIT,
	badOptionsFormatIT,
	integerExpectedIT
    ;

    private static ResourceBundle rb;

    public String format( Object... args ) {
        synchronized(Messages.class) {
            if(rb==null)
                rb = ResourceBundle.getBundle(Messages.class.getName());
            return MessageFormat.format(rb.getString(name()),args);
        }
    }
}
