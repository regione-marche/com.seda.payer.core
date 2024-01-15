package com.seda.payer.commons.utility;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import com.seda.commons.string.Pad;

public class LogUtility
{

	public static void writeLog(String mess)
	{
		Date d = new Date();
    	GregorianCalendar gcalCurrenDate = new GregorianCalendar();
    	gcalCurrenDate.setTime(d);
    	String appo = "" + gcalCurrenDate.get(Calendar.YEAR);
    	appo += "-" + Pad.left(gcalCurrenDate.get(Calendar.MONTH) + 1,2,'0');
    	appo += "-" + Pad.left(gcalCurrenDate.get(Calendar.DATE),2,'0');
    	appo += " " + Pad.left(gcalCurrenDate.get(Calendar.HOUR_OF_DAY),2,'0');
    	appo += ":" + Pad.left(gcalCurrenDate.get(Calendar.MINUTE),2,'0');
    	appo += ":" + Pad.left(gcalCurrenDate.get(Calendar.SECOND),2,'0');
    	appo += "," + Pad.left(gcalCurrenDate.get(Calendar.MILLISECOND),3,'0') + " ";
		System.out.println(appo + mess);
	}

	public static void writeLog(String mess, Exception ex)
	{
		Date d = new Date();
    	GregorianCalendar gcalCurrenDate = new GregorianCalendar();
    	gcalCurrenDate.setTime(d);
    	String appo = "" + gcalCurrenDate.get(Calendar.YEAR);
    	appo += "-" + Pad.left(gcalCurrenDate.get(Calendar.MONTH) + 1,2,'0');
    	appo += "-" + Pad.left(gcalCurrenDate.get(Calendar.DATE),2,'0');
    	appo += " " + Pad.left(gcalCurrenDate.get(Calendar.HOUR_OF_DAY),2,'0');
    	appo += ":" + Pad.left(gcalCurrenDate.get(Calendar.MINUTE),2,'0');
    	appo += ":" + Pad.left(gcalCurrenDate.get(Calendar.SECOND),2,'0');
    	appo += "," + Pad.left(gcalCurrenDate.get(Calendar.MILLISECOND),3,'0') + " ";
    	if(mess.trim().length() > 0) {
    		mess = mess.trim();
    		if(mess.lastIndexOf(" ") != (mess.length() - 1))
    			System.out.println(appo + mess + " " + ex.getMessage());
    		else
    			System.out.println(appo + mess + ex.getMessage());
    	} else
    		System.out.println(appo + ex.getMessage());
	}

	public static void writeLog(String mess, Throwable ex)
	{
		Date d = new Date();
    	GregorianCalendar gcalCurrenDate = new GregorianCalendar();
    	gcalCurrenDate.setTime(d);
    	String appo = "" + gcalCurrenDate.get(Calendar.YEAR);
    	appo += "-" + Pad.left(gcalCurrenDate.get(Calendar.MONTH) + 1,2,'0');
    	appo += "-" + Pad.left(gcalCurrenDate.get(Calendar.DATE),2,'0');
    	appo += " " + Pad.left(gcalCurrenDate.get(Calendar.HOUR_OF_DAY),2,'0');
    	appo += ":" + Pad.left(gcalCurrenDate.get(Calendar.MINUTE),2,'0');
    	appo += ":" + Pad.left(gcalCurrenDate.get(Calendar.SECOND),2,'0');
    	appo += "," + Pad.left(gcalCurrenDate.get(Calendar.MILLISECOND),3,'0') + " ";
    	if(mess.trim().length() > 0) {
    		mess = mess.trim();
    		if(mess.lastIndexOf(" ") != (mess.length() - 1))
    			System.out.println(appo + mess + " " + ex.getMessage());
    		else
    			System.out.println(appo + mess + ex.getMessage());
    	} else
    		System.out.println(appo + ex.getMessage());
	}

	public static void writeLog(String mess, boolean isWS)
	{
		if(isWS)
    		System.out.println(mess);
		else
			writeLog(mess);
	}

	public static void writeLog(String mess, Exception ex, boolean isWS)
	{
		if(isWS) {
	    	if(mess.trim().length() > 0) {
	    		mess = mess.trim();
	    		if(mess.lastIndexOf(" ") != (mess.length() - 1))
	    			System.out.println(mess + " " + ex.getMessage());
	    		else
	    			System.out.println(mess + ex.getMessage());
	    	} else
	    		System.out.println(ex.getMessage());
		} else
			writeLog(mess, ex);
	}

	public static void writeLog(String mess, Throwable ex, boolean isWS)
	{
		if(isWS) {
	    	if(mess.trim().length() > 0) {
	    		mess = mess.trim();
	    		if(mess.lastIndexOf(" ") != (mess.length() - 1))
	    			System.out.println(mess + " " + ex.getMessage());
	    		else
	    			System.out.println(mess + ex.getMessage());
	    	} else
	    		System.out.println(ex.getMessage());
		} else
			writeLog(mess, ex);
	}
}