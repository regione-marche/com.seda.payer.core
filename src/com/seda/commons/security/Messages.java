/**
 * 
 */
package com.seda.commons.security;

import java.text.MessageFormat;
import java.util.ResourceBundle;
/**
 * @author dbadm
 *
 */
public enum Messages {
	SECURITY_KEY, VALUE_NULL
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
