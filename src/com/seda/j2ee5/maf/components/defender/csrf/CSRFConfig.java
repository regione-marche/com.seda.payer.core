/**
 * 
 */
package com.seda.j2ee5.maf.components.defender.csrf;

import javax.servlet.ServletContext;

import com.seda.j2ee5.maf.util.MAFContext;
/**
 * This class is deprecated. Use CSRFContext instead.
 * @author Seda Lab
 *
 */
@Deprecated
public class CSRFConfig {

	public static final String CSRF_REDIRECT = "CSRF_REDIRECT";
	public static final String CSRF_WRITER = "CSRF_WRITER";
	@Deprecated
	public static final String getTokenName(ServletContext context) {
		String csrfTokenName=MAFContext.CSRFTOKEN_NAME_DEFAULT;
		String initCsrfTokenName = context.getInitParameter(MAFContext.CSRFTOKEN_NAME_CONTEXT);
		if (initCsrfTokenName!=null)
			csrfTokenName=initCsrfTokenName;
		return csrfTokenName;
	}
	@Deprecated
	public static final String getTokenNameSave(ServletContext context) {
		String csrfTokenName=MAFContext.CSRFTOKEN_NAME_DEFAULT;
		String initCsrfTokenName = context.getInitParameter(MAFContext.CSRFTOKEN_NAME_CONTEXT);
		if (initCsrfTokenName!=null)
			csrfTokenName=initCsrfTokenName;
		return csrfTokenName+"_save";
	}
	@Deprecated
	public static final int getTokenLength(ServletContext context) {
		int csrfTokenLength=MAFContext.CSRFTOKEN_LEN_DEFAULT;
		String initCsrfTokenLength = context.getInitParameter(MAFContext.CSRFTOKEN_LEN_CONTEXT);
		if (initCsrfTokenLength!=null) {
			try {
				csrfTokenLength = Integer.parseInt(initCsrfTokenLength);
			} catch (NumberFormatException x) {
				csrfTokenLength= MAFContext.CSRFTOKEN_LEN_DEFAULT;
			}
		}
		return csrfTokenLength;
	}
	@Deprecated
	public static final String getTokenProvider(ServletContext context) {
		String csrfTokenProvider=MAFContext.CSRFTOKEN_PROVIDER_DEFAULT;
		String initCsrfTokenProvider = context.getInitParameter(MAFContext.CSRFTOKEN_PROVIDER_CONTEXT);
		if (initCsrfTokenProvider!=null) {
			try {
				csrfTokenProvider = initCsrfTokenProvider;
			} catch (NumberFormatException x) {
				csrfTokenProvider= MAFContext.CSRFTOKEN_PROVIDER_DEFAULT;
			}
		}
		return csrfTokenProvider;
	}
	@Deprecated
	public static final boolean isActive(ServletContext context) {
		boolean csrfDefender =MAFContext.CSRFTOKEN_DEFENDER_DEFAULT;
		String initCsrfDefender = context.getInitParameter(MAFContext.CSRFTOKEN_DEFENDER_CONTEXT);
		if (initCsrfDefender!=null) {
			csrfDefender = Boolean.parseBoolean(initCsrfDefender);
		}
		return csrfDefender;
	}
	@Deprecated
	public static final boolean isDenied(ServletContext context) {
		boolean csrfDeny =MAFContext.CSRFTOKEN_DENY_DEFAULT;
		String initCsrfDeny = context.getInitParameter(MAFContext.CSRFTOKEN_DENY_CONTEXT);
		if (initCsrfDeny!=null) {
			csrfDeny = Boolean.parseBoolean(initCsrfDeny);
		}
		return csrfDeny;
	}
	
}
