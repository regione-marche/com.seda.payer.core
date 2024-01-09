/**
 * 
 */
package com.seda.commons.properties.bundle;

import java.text.MessageFormat;
import java.util.ResourceBundle;
/**
 * @author dbadm
 *
 */
public enum Messages {
	PROPERTIES_NOT_FOUND
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
