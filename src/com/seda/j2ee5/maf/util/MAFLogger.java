/**
 * 
 */
package com.seda.j2ee5.maf.util;

import java.text.MessageFormat;
import java.util.ResourceBundle;

/**
 * @author Seda Lab
 *
 */
public class MAFLogger {

	public static final String BASE_LOG_MESSAGES="com.seda.j2ee5.maf.util.LogMessages";
	public static final String MDC_CTX="ctx"; 
	public static final String MDC_APP="app";
	
	//private static Hierarchy hierarchy;	
    private static ResourceBundle bundle=ResourceBundle.getBundle(BASE_LOG_MESSAGES);	
	
//    static {
//    	Properties log4jConfiguration = new Properties();
//    	try {
//			log4jConfiguration.load(MAFLogger.class.getResourceAsStream("log4j.properties"));
//			//LoggerHierarchyServer loggerHierarchyServer = new LoggerHierarchyServer();
//			//hierarchy = loggerHierarchyServer.configure(log4jConfiguration);			
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//    }
    
    // public static Logger getLogger(String name) {
    //     //return hierarchy.getLogger(name);
    // 	return Logger.getLogger(name);

    // }

    public static String formatApplid(String applid) {
    	return applid==null?applid:"[".concat(applid).concat("]");
    }
    
    public static String getMessage(String key) {
        return getMessage(key, (Object[])null);
    }

    /**
     * This method uses the default message strings property file to resolve
     * resultant string to show to an end user
     * @param key to use in Messages.properties file
     *
     * @return Formatted message for external display
     */
    public static String getMessage(String key, Object... arguments) {
        String message=null;
        // get resource bundle and retrieve message
        message=bundle().getString(key);
        
        // see if the message needs to be formatted
        if(arguments != null) {
            // format message
        	message=MessageFormat.format(message, arguments);
        }
        return message;
    }
    
    private static ResourceBundle bundle() {
		if (bundle==null) bundle=ResourceBundle.getBundle(BASE_LOG_MESSAGES);
		return bundle;
	}
}
