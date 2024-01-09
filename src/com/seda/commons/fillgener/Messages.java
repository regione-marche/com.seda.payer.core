/**
 * 
 */
package com.seda.commons.fillgener;

import java.text.MessageFormat;
import java.util.ResourceBundle;
/**
 * @author dbadm
 *
 */
public enum Messages {
	INVALID_BYTE, 
	INVALID_VALUE, 
	BYTE_NOT_ALLOWED, 
	DEFINITION_NULL, 
	VALUE_NULL, 
	INPUT_STRING_NULL, 
	BYTE_NOT_INCLUDED, 
	MASKDEFINITION_NULL, 
	INVALID_MASK,
	INVALID_SUBMASKDEFINITION
	
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
