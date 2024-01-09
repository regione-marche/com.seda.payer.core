/**
 * 
 */
package com.seda.j2ee5.maf.core.security;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Properties;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * 
 * @author Seda Lab
 *
 */
public abstract class UserBeanSupport implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private static final String APPLICATION_PROPERTIES_ROLES = "application_properties_roles";
	private static final String APPLICATION_PROPERTIES_TEMPLATES = "application_properties_templates";	
	
	private static final String USER_BEAN_REGISTRY = "user_bean_registry";
	
	public static final String REGISTRY_NAME = "registry_name";
	public static final String REGISTRY_SESSION = "registry_session";
	public static final String REGISTRY_REMOTEIP = "registry_remoteip";	
	public static final String REGISTRY_PROFILE = "registry_profile";
	
	private HashMap<String, Serializable> hashMap = new HashMap<String, Serializable>();
	
	// *****************************************
	// For general support
	// *****************************************	
	public final Object get(String key) {
		return hashMap.get(key);
	}

	public final void put(String key, Serializable object) {
		if (hashMap.containsKey(key)) {
			hashMap.remove(key);
		}
		hashMap.put(key, object);
	}
	
	public final boolean contains(String key) {
		return hashMap.containsKey(key);
	}
	// *****************************************
	// For general io in http session
	// *****************************************	
	public final static UserBeanSupport getUserBeanSupport(ServletRequest request) {
		return getUserBeanSupport((HttpServletRequest)request);
	}
	public final static UserBeanSupport getUserBeanSupport(HttpServletRequest request) {
		return getUserBeanSupport(request.getSession(false));
	}	
	public final static UserBeanSupport getUserBeanSupport(HttpSession session) {
		if (session==null) return null;
		return (UserBeanSupport)session.getAttribute(SignOnKeys.USER_BEAN);
	}	
	
	// *****************************************
	// For the user bean registry
	// *****************************************
	public final String getRegistry(String key) {
		return getProperties(USER_BEAN_REGISTRY).getProperty(key, null);
	}
	
	public final String getRegistry(String key, String defaultValue) {
		return getProperties(USER_BEAN_REGISTRY).getProperty(key, defaultValue);
	}	

	public final void setRegistry(String key, String value) {
		getProperties(USER_BEAN_REGISTRY).setProperty(key, value);
	}
	public final void setRemoteIp(String remoteIp) {
		setRegistry(REGISTRY_REMOTEIP, remoteIp);
	}			
	public final void setSession(String sessionId) {
		setRegistry(REGISTRY_SESSION, sessionId);
	}		
	public final void setProfile(String profile) {
		setRegistry(REGISTRY_PROFILE, profile);
	}	
	public final void setName(String name) {
		setRegistry(REGISTRY_NAME, name);
	}	
	//-------------------------------------------
	public final String registry(String key) {
		return getRegistry(key, null);
	}
	public final String registry(String key, String defaultValue) {
		return getProperty(key, defaultValue);
	}
	//------------------------------------------- The user Name	
	public final String getName() {
		return getProperties(USER_BEAN_REGISTRY).getProperty(REGISTRY_NAME);
	}
	public final String name() {
		return getName();
	}
	//------------------------------------------- The profile Name	
	public final String getProfile() {
		return getProperties(USER_BEAN_REGISTRY).getProperty(REGISTRY_PROFILE);
	}
	public final String profile() {
		return getProfile();
	}
	//------------------------------------------- The user Session after login	
	public final String getSession() {
		return getProperties(USER_BEAN_REGISTRY).getProperty(REGISTRY_SESSION);
	}
	public final String session() {
		return getSession();
	}
	//------------------------------------------- The user Remote Ip after login	
	public final String getRemoteIp() {
		return getProperties(USER_BEAN_REGISTRY).getProperty(REGISTRY_REMOTEIP);
	}
	public final String remoteIp() {
		return getRemoteIp();
	}	
	// *****************************************
	// For the user bean application roles
	// *****************************************
	public final void setRole(String applicationName, String role) {
		setProperty(APPLICATION_PROPERTIES_ROLES,applicationName, role);
	}
	public final String getRole(String applicationName) {
		return getProperty(APPLICATION_PROPERTIES_ROLES,applicationName);
	}
	// *****************************************
	// For the user bean application templates
	// *****************************************
	public final void setTemplate(String applicationName, String templateName) {
		if (templateName!=null) 
			setProperty(APPLICATION_PROPERTIES_TEMPLATES,applicationName, templateName);
		else {
			getProperties(APPLICATION_PROPERTIES_TEMPLATES).remove(applicationName);
		}
	}
	public final String getTemplate(String applicationName) {
		return getProperty(APPLICATION_PROPERTIES_TEMPLATES,applicationName);
	}	
	// *****************************************
	// For specific properties support
	// *****************************************
	public final void putProperties(String key, Properties value) {
		hashMap.put(key, value);
	}
	
	public final Properties getProperties(String key) {
		Properties properties = (Properties)hashMap.get(key);
		if (properties==null) {
			properties = new Properties();
			putProperties(key,properties);
		}
		
		//System.out.println(properties);
		return properties;
	}

	public final String getProperty(String propertiesKey, String key) {
		return getProperties(propertiesKey).getProperty(key, null);
	}
	
	public final String getProperty(String propertiesKey, String key, String defaultValue) {
		return getProperties(propertiesKey).getProperty(key, defaultValue);
	}	
	
	public final void setProperty(String propertiesKey, String key, String value) {
		getProperties(propertiesKey).setProperty(key, value);
	}	

	public final String property(String propertiesKey, String key) {
		return getProperty(propertiesKey, key, null);
	}
	
	public final String property(String propertiesKey, String key, String defaultValue) {
		return getProperty(propertiesKey, key, defaultValue);
	}	
	
	// Destroy this 
	public final void destroy() {
		this.hashMap = null;
	}

	@Override
	public String toString() {
		return "UserBeanSupport [hashMap=" + hashMap + "]";
	}

		
	
}
