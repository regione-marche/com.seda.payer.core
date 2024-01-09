/**
 * 
 */
package com.seda.j2ee5.maf.core.action;

import java.util.Collections;
import java.util.Enumeration;
import java.util.Properties;

import javax.servlet.ServletContext;

import com.seda.j2ee5.maf.components.captcha.CaptchaManager;
import com.seda.j2ee5.maf.core.event.EventResult;
import com.seda.j2ee5.maf.util.MAFCookies;
/**
 * This is the default implementation of a base web Action
 * @author Seda Lab
 *
 */
public abstract class HtmlAction implements ActionInitialize, ActionService {

	private static final long serialVersionUID = 1L;

	private MAFCookies cookiesSupport;
	private CaptchaManager captchaManager;
	
    private Properties settings;
    
	private Properties getSettings() {
		if (settings==null) settings = new Properties();
		return settings;
	}
	
	protected final String getSetting(String key) {
        return getSettings().getProperty(key);
    }
    
	public final void addSetting(String key, String value) {
    	getSettings().put(key, value);
    }
    
	protected MAFCookies getCookiesSupport() {
		return cookiesSupport;
	}
	
	protected CaptchaManager getCaptchaManager() {
		return captchaManager;
	}	

    @SuppressWarnings("unchecked")
	protected final Enumeration getSettingNames() {
        return Collections.enumeration(getSettings().keySet());
    }		
	
	/**
	 * The saved application context 
	 */
	protected ServletContext context;	
	
	/**
	 * The initialize method of the action
	 * @see ActionInitialize#init(ServletContext)
	 * @param context the ServletContext of the action
	 * @exception in case of error
	 */	
	public void init(ServletContext context) throws ActionException {
		this.context  = context;
	}

	/**
	 * The finalize method of the action without event
	 */
	public void end() {
	}
	
	/**
	 * The finalize method in case of an event thrown by the service
	 */
	public void end(EventResult eventResult) {
	}
	
	
	
}
