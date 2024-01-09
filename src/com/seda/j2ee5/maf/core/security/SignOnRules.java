/**
 * 
 */
package com.seda.j2ee5.maf.core.security;

import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.seda.j2ee5.maf.util.MAFLogger;

/**
 * @author Seda Lab
 *
 */
public class SignOnRules {

	public static final int ATTEMPTS = 3;
	public static final int LOCKED_TIMEOUT = 10;
	
	public static final String MSG_INVALID="entry_manager_signon_usr_psw";
	public static final String MSG_LOCKED="entry_manager_signon_bf_user";
	public static final String MSG_NOTFOUND="entry_manager_signon_bf_not_found";
	
	public static final String MSG_NOTSIGNEDON="entry_manager_not_signed_on";	
	public static final String MSG_LOWPROFILE="entry_manager_profile_error";	
	public static final String MSG_LOWROLE="entry_manager_role_error";	
	
	private int loginAttempts;
    private int loginLockedTimeout;

    private String usrRegex;
    private String pswRegex;
    
    private Pattern usrPattern;
    private Pattern pswPattern;
    
    private Properties userMessages = new Properties();
    
    public void addUserMessage(String key, String value){
    	userMessages.put(key, value);
    }
    
    public String getInvalidMessage(){
    	String message = userMessages.getProperty(MSG_INVALID);
    	if (message==null)
    		message=MAFLogger.getMessage(MSG_INVALID);
    	return message;
    }
    
    public String getLockedMessage(){
    	String message = userMessages.getProperty(MSG_LOCKED);
    	if (message==null)
    		message=MAFLogger.getMessage(MSG_LOCKED);
    	return message;
    }    
    
    public String getNotfoundMessage(){
    	String message = userMessages.getProperty(MSG_NOTFOUND);
    	if (message==null)
    		message=MAFLogger.getMessage(MSG_NOTFOUND);
    	return message;
    }
    
    public String getNotSignedonMessage(){
    	String message = userMessages.getProperty(MSG_NOTSIGNEDON);
    	if (message==null)
    		message=MAFLogger.getMessage(MSG_NOTSIGNEDON);
    	return message;
    }
    
    public String getLowProfileMessage(){
    	String message = userMessages.getProperty(MSG_LOWPROFILE);
    	if (message==null)
    		message=MAFLogger.getMessage(MSG_LOWPROFILE);
    	return message;
    }
    public String getLowRoleMessage(){
    	String message = userMessages.getProperty(MSG_LOWROLE);
    	if (message==null)
    		message=MAFLogger.getMessage(MSG_LOWROLE);
    	return message;
    }                 
    
    public void setInvalidMessage(String value){
    	userMessages.setProperty(MSG_INVALID,value);
    }
    
    public void setLockedMessage(String value){
    	userMessages.setProperty(MSG_LOCKED,value);
    }    
    
    public void setNotfoundMessage(String value){
    	userMessages.setProperty(MSG_NOTFOUND,value);
    }    

    public void setNotSignedonMessage(String value){
    	userMessages.setProperty(MSG_NOTSIGNEDON,value);
    }
    public void setLowProfileMessage(String value){
    	userMessages.setProperty(MSG_LOWPROFILE,value);
    }
    public void setLowRoleMessage(String value){
    	userMessages.setProperty(MSG_LOWROLE,value);
    }             
    
    public int getLoginAttempts() {
		return loginAttempts;
	}
	public int getLoginLockedTimeout() {
		return loginLockedTimeout;
	}
	public String getUsrRegex() {
		return usrRegex;
	}
	public String getPswRegex() {
		return pswRegex;
	}

	public boolean pswMatches(String input) {
		if (pswPattern!=null)
			return pswMatcher(input).matches();
		else
			return true;
	}

	private Matcher pswMatcher(String input) {
		return pswPattern.matcher(input);
	}
	
	public boolean usrMatches(String input) {
		if (usrPattern!=null)
			return usrMatcher(input).matches();
		else
			return true;
	}
	
	private Matcher usrMatcher(String input) {
		return usrPattern.matcher(input);
	}
	
	public SignOnRules(int loginAttempts, int loginLockedTimeout,
			String usrRegex, String pswRegex) {
		super();
		this.loginAttempts = loginAttempts;
		this.loginLockedTimeout = loginLockedTimeout;
		this.usrRegex = usrRegex;
		if (usrRegex!=null) {
			usrPattern=Pattern.compile(usrRegex);
		}
		this.pswRegex = pswRegex;
		if (pswRegex!=null) {
			pswPattern=Pattern.compile(pswRegex);
		}
	}

	@Override
	public String toString() {
		return "SignOnRules [loginAttempts=" + loginAttempts
				+ ", loginLockedTimeout=" + loginLockedTimeout + ", pswRegex="
				+ pswRegex + ", usrRegex=" + usrRegex + "]";
	}
    
}
