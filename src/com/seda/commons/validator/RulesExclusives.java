/**
 * 
 */
package com.seda.commons.validator;

/**
 * @author dbadm
 *
 */
public class RulesExclusives {
	public final static String[] REQUIRED={};
	public final static String[] REMOTE={};
	public final static String[] MIN_LENGTH={};
	public final static String[] MAX_LENGTH={};
	public final static String[] RANGE_LENGTH={};
	public final static String[] MIN={};
	public final static String[] MAX={};
	public final static String[] RANGE={};	
	public final static String[] MIN_INT={"min","max"};
	public final static String[] MAX_INT={"min","max"};	
	public final static String[] RANGE_INT={"range"};
	public final static String[] EMAIL={"url","date","dateISO","dateEUR","number","numberIT","digits","creditcard","accept","time","timeShort"};
	public final static String[] URL={"email","date","dateISO","dateEUR","number","numberIT","digits","creditcard","accept","time","timeShort"};
	public final static String[] DATE={"email","url","dateISO","dateEUR","number","numberIT","digits","creditcard","accept","time","timeShort"};
	public final static String[] DATE_ISO={"email","url","date","dateEUR","number","numberIT","digits","creditcard","accept","time","timeShort"};
	public final static String[] DATE_EUR={"email","url","dateISO","number","digits","numberIT","creditcard","accept","time","timeShort"};
	public final static String[] TIME={"email","url","dateISO","number","digits","numberIT","creditcard","accept","timeShort"};	
	public final static String[] TIME_SHORT={"email","url","dateISO","number","digits","numberIT","creditcard","accept","time"};	
	public final static String[] NUMBER={"email","url","date","dateISO","dateEUR","numberIT","creditcard","accept","time","timeShort"};
	public final static String[] NUMBER_IT={"email","url","date","dateISO","dateEUR","number","creditcard","accept","time","timeShort"};	
	public final static String[] DIGITS={"email","url","date","dateISO","dateEUR","number","numberIT","creditcard","accept","time","timeShort"};
	public final static String[] CREDIT_CARD={"email","url","date","dateISO","dateEUR","number","numberIT","digits","accept","time","timeShort"};
	public final static String[] ACCEPT={"email","url","date","dateISO","dateEUR","number","numberIT","digits","creditcard","time","timeShort"};
	public final static String[] EQUALTO={};
}
