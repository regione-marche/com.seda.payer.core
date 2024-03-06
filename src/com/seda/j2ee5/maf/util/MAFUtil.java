/**
 * 
 */
package com.seda.j2ee5.maf.util;

import java.io.Closeable;
import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Collections;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.seda.commons.logger.CustomLoggerManager;
import com.seda.commons.logger.LoggerWrapper;
import com.seda.commons.string.Convert;
import com.seda.j2ee5.maf.components.encoding.EncodingContext;
import com.seda.j2ee5.maf.components.encoding.EncodingManager;
import com.seda.j2ee5.maf.components.validation.ValidationContext;
import com.seda.j2ee5.maf.core.application.WebData;
import com.seda.j2ee5.maf.core.security.SignOnKeys;
import com.seda.j2ee5.maf.defender.http.HttpDefenseRequest;
import com.seda.j2ee5.maf.taglibs.ViewStateManager;
import com.seda.j2ee5.maf.template.TemplateManager;
/**
 * @author Seda Lab
 *
 */
public class MAFUtil {
    
	private static final LoggerWrapper logger =  CustomLoggerManager.get(MAFUtil.class);
	
    /** Creates a new instance of MAFUtil */
    private MAFUtil() {
    }
    
    public static void closeIgnoringException(Closeable c) {
        if (c != null) {
            try {
                c.close();
            } catch (IOException ex) {
                // There is nothing we can do if close fails
            }
        }
    }
    
    public static String encodeJSONString(String json) {
        json=json.replaceAll("'", "\\\\'");
        json=json.replaceAll("\"", "\\\\\\\"");    
        return json;
    }	

    public static String getStackTrace(Throwable t) {
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        if (t!=null)
        	t.printStackTrace(pw);
        closeIgnoringException(pw);
        closeIgnoringException(sw);
        return sw.getBuffer().toString();
    }
	
    public static final void showException(Throwable t) {
    	showException(t,System.err);
	}
	
	public static final void showException(Throwable t, PrintStream printer) {
		while (t!=null) {
			t.printStackTrace(printer);
			t=t.getCause();
		}
	}	
	
	public static final void rejectRequest(HttpServletRequest hreq, HttpServletResponse hres, boolean replyLastViewstate, boolean replaceEncoded) throws ServletException, IOException {
		// get the last viewstate id
		if (replyLastViewstate) {
			String viewStateId = (String)hreq.getSession().getAttribute(MAFAttributes.VIEWSTATE_ID);
			ViewStateManager viewStateManager = new ViewStateManager(hreq,viewStateId);
			// put in the previous request attributes
			try {
				viewStateManager.replyAttributes(ValidationContext.getInstance().getValidationMessage());
			} catch (ClassNotFoundException e) {
				logger.error(MAFLogger.getMessage("validation_manager_reply_error", viewStateId),e);
			}			
		}
		// return to the previous screen		
		String screenSuffix=TemplateManager.DEFAULT_SCREEN_SFX;
		WebData webData = (WebData)hreq.getSession().getServletContext().getAttribute(MAFAttributes.WEB);
		if (webData!=null) {
			screenSuffix=URLUtil.getUrlExtension(webData.getTemplateURL());
		}
		screenSuffix=screenSuffix.startsWith(".")?screenSuffix:"."+screenSuffix;
		String previousScreen = MAFAttributes.PREVIOUS.concat(screenSuffix);
		String previousApplication = (String) hreq.getSession().getAttribute(MAFAttributes.PREVIOUS_SCREEN_APPLICATION);
		hreq.setAttribute(MAFAttributes.CURRENT_APPLICATION, previousApplication);
		hreq.setAttribute(MAFAttributes.VALIDATION_REJECTED, new Boolean(true));
		//
		if (replaceEncoded) {
			if (hreq instanceof HttpDefenseRequest) {
				HttpDefenseRequest hdreq = 	(HttpDefenseRequest)hreq;
				replyEncodedParameters(hdreq);
				hreq.getRequestDispatcher(previousScreen).forward(hdreq, hres);
				return;
			} 
		} 
		hreq.getRequestDispatcher(previousScreen).forward(hreq, hres);			
	}

	public static void replyEncodedParameters(HttpDefenseRequest httpDefenseRequest) {
		boolean encoded = EncodingContext.getInstance().isEncodeParameter();
		boolean delete = EncodingContext.getInstance().isEncodeDelete();
		
		HashMap<String, Object> encodedParameters = httpDefenseRequest.getEncodedParameters();
		Enumeration<String> paramKeys = Collections.enumeration(encodedParameters.keySet());
		String dateNames="";
		while (paramKeys.hasMoreElements()) {
			String paramKey = paramKeys.nextElement();
			String dateName = EncodingManager.getNameDate(paramKey);
			if (dateName!=null && !dateNames.contains(dateName)) {
				dateNames+=dateName + " ";
				EncodingManager.dateHandler(dateName,httpDefenseRequest);
			}
			Object value = encodedParameters.get(paramKey);
			if (encoded) {
				httpDefenseRequest.setAttribute(paramKey, value);
			}
			if ((encoded && !delete) || !encoded) {
				if (value instanceof String[])
					httpDefenseRequest.setParameters(paramKey, (String[])value);
				else if (value instanceof String)
					httpDefenseRequest.setParameter(paramKey, (String)value);
				else 
					logger.warn("Reply encoded parameters: key " + paramKey + " out of bound " + value.getClass());
			}
		}
	}
	/**
	 * This method works related to the <code>sfixDestroy</code> initial parameter
	 * 
	 */
	public static final void regenerateSession(HttpServletRequest request) {
		boolean sfxDestroy = MAFContext.SESSIONFIXATION_DESTROY_DEFAULT;
		String sfxDestroyString = request.getSession().getServletContext().getInitParameter(MAFContext.SESSIONFIXATION_DESTROY_CONTEXT);
		if (sfxDestroyString!=null && sfxDestroyString.trim().length()>0) {
			sfxDestroy=Boolean.parseBoolean(sfxDestroyString);
		}
		regenerateSession(request, sfxDestroy);
    }
    
	public static final void regenerateSession(HttpServletRequest request, boolean destroyAttributes) {
    	HttpSession session = request.getSession(false);

    	if (session==null) {
    		request.getSession(true);
    		return;
    	}
    	
    	Map<String, Object> oldAttributes = null;
        Locale locale = null;
        if (!destroyAttributes) {
        	oldAttributes = new HashMap<String, Object>();
        	// Save session data
        	Enumeration<?> names = session.getAttributeNames();
        	while (names.hasMoreElements()) {
        		String name = (String)names.nextElement();
        		// don't save the previous session manager
        		if (name.equals(MAFAttributes.SESSION_MANAGER)) continue;
        		// don't save information of the previous user bean        		
        		if (name.equals(SignOnKeys.USER_BEAN)) continue;        		
        		if (name.equals(SignOnKeys.SIGNED_ON_USER)) continue;
        		// add previous attribute
        		oldAttributes.put(name, session.getAttribute(name));
			}
        } else {
        	locale = (Locale)session.getAttribute(MAFAttributes.LOCALE);
        }
        try {
            session.invalidate();        	
        } catch (IllegalStateException e) {
			// ignore this state
		}
        session = null;
        session = request.getSession(true);
        if (!destroyAttributes) {
        	// Restore session data
        	Enumeration<String> names = Collections.enumeration(oldAttributes.keySet());
        	while (names.hasMoreElements()) {
        		String name = names.nextElement();
        		session.setAttribute(name, oldAttributes.get(name));
			}
        } else {
        	session.setAttribute(MAFAttributes.LOCALE, locale);
        }
    }	
	
	public static final void deleteCookie(HttpServletRequest request, HttpServletResponse response, String...strings) {
		String buffer = Convert.arrayToString(strings);  
		// see if the cookie exists and remove accordingly
		Cookie[] cookies = request.getCookies();
		if (cookies != null) {
			String cookieName;
			for (int loop=0, loopMax=cookies.length; loop < loopMax; loop++) {
				if (buffer!=null) {
					cookieName = cookies[loop].getName();
					if (buffer.contains(cookieName)) {
						cookies[loop].setMaxAge(0);
						response.addCookie(cookies[loop]);
					}					
				}
			}
		}
	}
	
	public static final void applyParameters(HttpServletRequest request, HashMap<String, String> parameters) {
		HttpDefenseRequest defenseRequest=null;
		if (request instanceof HttpDefenseRequest && parameters!=null) {
			defenseRequest = (HttpDefenseRequest)request;
		}
		boolean encodingParameter = EncodingContext.getInstance().isEncodeParameter();	
		boolean encodingDelete = EncodingContext.getInstance().isEncodeDelete();
		Enumeration<String> paramKeys = Collections.enumeration(parameters.keySet());
		while (paramKeys.hasMoreElements()) {
			String key = (String) paramKeys.nextElement();
			String value = parameters.get(key);
			if (encodingParameter) 
				request.setAttribute(key, value);
			if ((defenseRequest!=null && !encodingDelete) || (!encodingParameter)) 
				defenseRequest.setParameter(key, value);			
		}
	}

}
