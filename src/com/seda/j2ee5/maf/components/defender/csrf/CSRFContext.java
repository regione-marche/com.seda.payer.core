/**
 * 
 */
package com.seda.j2ee5.maf.components.defender.csrf;

import java.io.Serializable;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;

import com.seda.commons.logger.CustomLoggerManager;
import com.seda.commons.logger.LoggerWrapper;
import com.seda.commons.security.TokenGenerator;
import com.seda.j2ee5.maf.util.MAFContext;
import com.seda.j2ee5.maf.util.MAFLogger;

/**
 * @author f.ricci
 *
 */
public class CSRFContext {

	private static final LoggerWrapper logger =  CustomLoggerManager.get(CSRFContext.class);
	
	public static final String CSRF_REDIRECT = "CSRF_REDIRECT";
	public static final String CSRF_WRITER = "CSRF_WRITER";

	private Map<String, Serializable> context;
	
	private static CSRFContext me;
	
	static {
    	try {
    		me = new CSRFContext();
    	} catch(Exception se) {
    		logger.error(MAFLogger.getMessage("generic_exception"), se);
    	}
    }
	
	private CSRFContext() {	
		context = Collections.synchronizedMap(new HashMap<String, Serializable>());
	}

	
	public static CSRFContext getInstance() {
		return me;
	}

	public String getTokenName() {
		return (String)context.get(MAFContext.CSRFTOKEN_NAME_CONTEXT);
	}
	
	public String getTokenNameSave() {
		return (String)context.get(MAFContext.CSRFTOKEN_NAME_CONTEXT+"Save");
	}
	
	public int getTokenLength() {
		return (Integer)context.get(MAFContext.CSRFTOKEN_LEN_CONTEXT);
	}
	
	public String getTokenProvider() {
		return (String)context.get(MAFContext.CSRFTOKEN_PROVIDER_CONTEXT);
	}
	
	public boolean isActive() {
		return (Boolean)context.get(MAFContext.CSRFTOKEN_DEFENDER_CONTEXT);
	}
	
	public boolean isDenied() {
		return (Boolean)context.get(MAFContext.CSRFTOKEN_DENY_CONTEXT);
	}
	
	public boolean isIgnoreRoot() {
		return (Boolean)context.get(MAFContext.CSRFTOKEN_IGNORE_ROOT_CONTEXT);
	}
	
	public boolean isIgnoreRootRegenerate() {
		return (Boolean)context.get(MAFContext.CSRFTOKEN_IGNORE_ROOT_REGENERATE_CONTEXT);
	}			
	
	public void initialize(FilterConfig filterConfig) {
		initialize(filterConfig.getServletContext());
	}
	
	public void initialize(ServletContext context) {
		String csrfTokenName=MAFContext.CSRFTOKEN_NAME_DEFAULT;
		String initCsrfTokenName = context.getInitParameter(MAFContext.CSRFTOKEN_NAME_CONTEXT);
		if (initCsrfTokenName!=null)
			csrfTokenName=initCsrfTokenName;
		String csrfTokenNameSave=csrfTokenName+"_save";
		
		int csrfTokenLength=MAFContext.CSRFTOKEN_LEN_DEFAULT;
		String initCsrfTokenLength = context.getInitParameter(MAFContext.CSRFTOKEN_LEN_CONTEXT);
		if (initCsrfTokenLength!=null) {
			try {
				csrfTokenLength = Integer.parseInt(initCsrfTokenLength);
			} catch (NumberFormatException x) {
				csrfTokenLength= MAFContext.CSRFTOKEN_LEN_DEFAULT;
			}
		}

		String csrfTokenProvider=MAFContext.CSRFTOKEN_PROVIDER_DEFAULT;
		String initCsrfTokenProvider = context.getInitParameter(MAFContext.CSRFTOKEN_PROVIDER_CONTEXT);
		if (initCsrfTokenProvider!=null) {
			if (!initCsrfTokenProvider.equals(TokenGenerator.PROVIDER_SECURE_RANDOM) && 
					!initCsrfTokenProvider.equals(TokenGenerator.PROVIDER_UUID) &&
					!initCsrfTokenProvider.equals(TokenGenerator.PROVIDER_INTERNAL))
				csrfTokenProvider=initCsrfTokenProvider;
		}
		
		boolean csrfDefender =MAFContext.CSRFTOKEN_DEFENDER_DEFAULT;
		String initCsrfDefender = context.getInitParameter(MAFContext.CSRFTOKEN_DEFENDER_CONTEXT);
		if (initCsrfDefender!=null) {
			csrfDefender = Boolean.parseBoolean(initCsrfDefender);
		}
		
		boolean csrfDeny =MAFContext.CSRFTOKEN_DENY_DEFAULT;
		String initCsrfDeny = context.getInitParameter(MAFContext.CSRFTOKEN_DENY_CONTEXT);
		if (initCsrfDeny!=null) {
			csrfDeny = Boolean.parseBoolean(initCsrfDeny);
		}
		
		boolean ignoreRoot = MAFContext.CSRFTOKEN_IGNORE_ROOT_DEFAULT;
		String initIgnoreRoot = context.getInitParameter(MAFContext.CSRFTOKEN_IGNORE_ROOT_CONTEXT);
		if (initIgnoreRoot!=null) {
			ignoreRoot = Boolean.parseBoolean(initIgnoreRoot);
		}
		boolean ignoreRootRegenerate = MAFContext.CSRFTOKEN_IGNORE_ROOT_REGENERATE_DEFAULT;
		String initIgnoreRootRegenerate = context.getInitParameter(MAFContext.CSRFTOKEN_IGNORE_ROOT_REGENERATE_CONTEXT);
		if (initIgnoreRootRegenerate!=null) {
			ignoreRootRegenerate = Boolean.parseBoolean(initIgnoreRootRegenerate);
		}		
		
		this.context.clear();
		this.context.put(MAFContext.CSRFTOKEN_NAME_CONTEXT,csrfTokenName);
		this.context.put(MAFContext.CSRFTOKEN_NAME_CONTEXT+"Save",csrfTokenNameSave);
		this.context.put(MAFContext.CSRFTOKEN_LEN_CONTEXT,csrfTokenLength);
		this.context.put(MAFContext.CSRFTOKEN_PROVIDER_CONTEXT,csrfTokenProvider);
		this.context.put(MAFContext.CSRFTOKEN_DEFENDER_CONTEXT,csrfDefender);
		this.context.put(MAFContext.CSRFTOKEN_DENY_CONTEXT,csrfDeny);
		this.context.put(MAFContext.CSRFTOKEN_IGNORE_ROOT_CONTEXT,ignoreRoot);
		this.context.put(MAFContext.CSRFTOKEN_IGNORE_ROOT_REGENERATE_CONTEXT,ignoreRootRegenerate);
		
	}
}
