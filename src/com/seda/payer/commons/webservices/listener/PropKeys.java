package com.seda.payer.commons.webservices.listener;

import java.text.MessageFormat;
import java.util.ResourceBundle;

public enum PropKeys {
	DEFAULT_NODE,
	JINDI_CONTEXT,
	JINDI_PROVIDER,
	GATEWAY_ENDPOINTURL,
	NOTIFICHE_ENDPOINTURL,
	INTEGRAENTE_ENDPOINTURL,
	DBSCHEMACODSOCIETA
	;
	
    private static ResourceBundle rb;

    public String format( Object... args ) {
        synchronized(PropKeys.class) {
            if(rb==null)
            	rb = ResourceBundle.getBundle(PropKeys.class.getName());
            return MessageFormat.format(rb.getString(name()),args);
        }
    }
}