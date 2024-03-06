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
	RANGELENGTH,
	RANGE,
	MAX,
	MIN,
	RANGEINT,
	MAXINT,
	MININT,	
	TIME,
	TIME_SHORT,
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
	integerExpected
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
