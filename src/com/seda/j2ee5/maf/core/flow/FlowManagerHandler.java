/**
 * 
 */
package com.seda.j2ee5.maf.core.flow;

import java.util.Collections;
import java.util.Enumeration;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;

import com.seda.commons.string.Convert;

/**
 * @author f.ricci
 *
 */
public abstract class FlowManagerHandler implements FlowManager {

	private static final long serialVersionUID = 1L;
	
	private Properties settings;
	private Properties properties;
    
	protected Properties getSettings() {
		if (settings==null) settings = new Properties();
		return settings;
	}	
	
	protected Properties getProperties() {
		if (properties==null) properties = new Properties();
		return properties;
	}
	
    protected final String getProperty(String key) {
        return getProperties().getProperty(key);
    }
    
    protected final boolean getPropertyAsBoolean(String key) {
        return Boolean.parseBoolean(getProperties().getProperty(key));
    }
    
    protected final String[] getPropertyAsStringArray(String key, char sep) {
        return Convert.stringToArray(getProperties().getProperty(key),sep);
    }        
    
    public final void addProperty(String key, String value) {
    	getProperties().put(key, value);
    }
    
    
    @SuppressWarnings("unchecked")
	protected final Enumeration getPropertyNames() {
        return Collections.enumeration(getSettings().keySet());
    }	
    
    
    protected final String getSetting(String key) {
        return getSettings().getProperty(key);
    }    
    
    protected final boolean getSettingAsBoolean(String key) {
        return Boolean.parseBoolean(getSettings().getProperty(key));
    }
    
    protected final String[] getSettingAsStringArray(String key, char sep) {
        return Convert.stringToArray(getSettings().getProperty(key),sep);
    }        
    
    public final void addSetting(String key, String value) {
    	getSettings().put(key, value);
    }    
    
    @SuppressWarnings("unchecked")
	protected final Enumeration getSettingNames() {
        return Collections.enumeration(getSettings().keySet());
    }	
	
	public abstract void end(HttpServletRequest request) ;

	public abstract String process(HttpServletRequest request) throws FlowException ;

	public abstract void start(HttpServletRequest request) ;

}
