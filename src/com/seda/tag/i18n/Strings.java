package com.seda.tag.i18n;

import java.text.MessageFormat;
import java.util.ResourceBundle;

public enum Strings {
	MESSAGESCLASS,
	MONTHCLASS,
	DEFAULTPREFIX,
	DEFAULTLOCALE,
	DEFAULTMONTHID,
	DEFAULTYEARID,
	DEFAULTDAYID,
	DEFAULTSUFFIXMONTH,
	DEFAULTSUFFIXDAY,
	DEFAULTSUFFIXYEAR,
	MONTHID,
	YEARID,
	DAYID,
	YEARBEGIN,
	YEAREND,
	MONTHBEGIN,
	MONTHEND,
	VALIDATIONMESSAGE
	;
	
    private static ResourceBundle rb;

    public String format( Object... args ) {
        synchronized(Strings.class) {
            if(rb==null)
                rb = ResourceBundle.getBundle(Strings.class.getName());
            return MessageFormat.format(rb.getString(name()),args);
        }
    }
}
