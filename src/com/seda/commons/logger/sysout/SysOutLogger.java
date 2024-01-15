/**
 * 
 */
package com.seda.commons.logger.sysout;

import java.text.DateFormat;
import java.util.Date;
/**
 * @author dbadm
 *
 */
public class SysOutLogger {
	public final static String DEBUG = "DEBUG";	
	public final static String INFO = "INFO ";
	public final static String WARN = "WARN ";	
	public final static String ERROR = "ERROR";	
	public final static String FATAL = "FATAL";	
	/**
	 * Prints debug message.
	 * @param message - Debug message.
	 */
	public static void debug(String message) {
		debug(message,null);
	}
	/**
	 * Prints debug message.
	 * @param message - Debug message.
	 */
	public static void debug(String message, Throwable t) {
		SysOutLogger.print(SysOutLogger.DEBUG, message, t);
	}		
	/**
	 * Prints info message.
	 * @param message - Info message.
	 */
	public static void info(String message) {
		info(message,null);
	}
	/**
	 * Prints info message.
	 * @param message - Info message.
	 */
	public static void info(String message, Throwable t) {
		SysOutLogger.print(SysOutLogger.INFO, message, t);
	}	
	/**
	 * Prints warn message.
	 * @param message - Warn message.
	 */
	public static void warn(String message) {
		warn(message);
	}
	/**
	 * Prints warn message.
	 * @param message - Warn message.
	 */
	public static void warn(String message, Throwable t) {
		SysOutLogger.print(SysOutLogger.WARN, message, t);
	}		
	/**
	 * Prints error message.
	 * @param message - Error message.
	 */
	public static void error(String message) {
		error(message,null);
	}
	/**
	 * Prints error message.
	 * @param message - Error message.
	 */
	public static void error(String message, Throwable t) {
		SysOutLogger.print(SysOutLogger.ERROR, message, t);
	}	
	/**
	 * Prints fatal message.
	 * @param message - Fatal message.
	 */
	public static void fatal(String message) {
		fatal(message, null);
	}

	/**
	 * Prints fatal message.
	 * @param message - Fatal message.
	 */
	public static void fatal(String message, Throwable t) {
		SysOutLogger.print(SysOutLogger.FATAL, message, t);
	}		

	/**
	 * The method, that using println prints directly the message. If the t object is not null, prints the message from the t.getMessage(). 
	 * @param type - The type: INFO or WARN or ERROR or CRIT.
	 * @param message - The message to print.
	 * @param t - The throwable object.
	 */
	private static void print(String type, String message, Throwable t) {
		System.out.println(DateFormat.getInstance().format(new Date().getTime()) + " [" + type + "] " + message);
		if (validThrowable(t)) System.out.println(t.getMessage());		
	}	
	
	private static boolean validThrowable(Throwable t) {
		return t==null?false:true;
	}		
}
