/**
 * 
 */
package com.seda.commons.maskedit;

import java.text.MessageFormat;
import java.util.ResourceBundle;
/**
 * @author dbadm
 *
 */
public enum Messages {
	DEFINITION_KEY_LENGTH, 
	MASKRULE_EMPTY, 
	MASKRULE_FORMAT, 
	MASKRULE_BRACKETS, 
	DEFINITION_NULL, BASE_DEFINITION_ERROR
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
