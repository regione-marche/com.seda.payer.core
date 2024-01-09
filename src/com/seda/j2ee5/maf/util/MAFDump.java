/**
 * 
 */
package com.seda.j2ee5.maf.util;

import java.util.Enumeration;

import javax.servlet.ServletContext;
import javax.servlet.ServletRequest;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.seda.commons.logger.CustomLoggerManager;
import com.seda.commons.logger.LoggerWrapper;

/**
 * @author f.ricci
 *
 */
public class MAFDump {
	
	private static final LoggerWrapper logger =  CustomLoggerManager.get(MAFDump.class);

	
	public static void dumpHeader(ServletRequest request, String unique) {
		String title="Request Header Dump";
		if (request instanceof HttpServletRequest) {
			HttpServletRequest hreq = (HttpServletRequest)request;
			Enumeration headerKeys = hreq.getHeaderNames();
			dumpStart(title, unique);		
			while (headerKeys.hasMoreElements()) {
				String key = (String) headerKeys.nextElement();
				Object attribute = hreq.getHeader(key);
				dump(title, key, attribute, unique);
			}
			dumpEnd(title, unique);			
		}
	}	
	
	public static void dumpCookie(ServletRequest request, String unique) {
		String title="Request Cookies Dump";		
		if (request instanceof HttpServletRequest) {
			HttpServletRequest hreq = (HttpServletRequest)request;			
			Cookie[] cookies = hreq.getCookies();
			dumpStart(title, unique);			
			for (int i = 0; i < cookies.length; i++) {
				Cookie cookie = cookies[i];
				dump(title, cookie.getName(), cookie.getValue(), unique);
			}
			dumpEnd(title, unique);			
		}
	}
	
	public static void dumpRequest(ServletRequest request, String unique) {
		String title="Request Dump";
		dumpStart(title, unique);
		Enumeration attributeKeys = request.getAttributeNames();
		while (attributeKeys.hasMoreElements()) {
			String key = (String) attributeKeys.nextElement();
			Object attribute = request.getAttribute(key);
			dump("Request Dump attribute", key, attribute, unique);
		}
		Enumeration parameterKeys = request.getParameterNames();
		while (parameterKeys.hasMoreElements()) {
			String key = (String) parameterKeys.nextElement();
			Object parameter = request.getParameter(key);
			dump("Request Dump parameter ", key, parameter, unique);			
		}
		dumpEnd(title, unique);		
	}


	public static void dumpSession(HttpServletRequest request, String unique) {
		String title="Session Dump";
		HttpSession session = request.getSession(false);
		if (session!=null) {
			Enumeration<String> sessionKeys = session.getAttributeNames();
			dumpStart(title, unique);		
			while (sessionKeys.hasMoreElements()) {
				String key = (String) sessionKeys.nextElement();
				Object attribute = session.getAttribute(key);
				dump(title, key, attribute, unique);
			}
		} else {
		}		
		dumpEnd(title, unique);		
	}	
	
	public static void dumpApplication(ServletContext context, String unique) {
		String title="Application Dump";		
		Enumeration<String> applicationKeys = context.getAttributeNames();
		dumpStart(title, unique);		
		while (applicationKeys.hasMoreElements()) {
			String key = (String) applicationKeys.nextElement();
			Object attribute = context.getAttribute(key);
			dump(title, key, attribute, unique);
		}		
		dumpEnd(title, unique);	
	}
	
	private static void dumpStart(String title, String unique) {
		dumpRef(title,"starts",unique);
	}
	private static void dumpEnd(String title, String unique) {
		dumpRef(title,"ends",unique);
	}		
	
	private static void dumpRef(String title, String action, String unique) {
		logger.info("**************** "+title+" "+action+", uniqueId="+unique);
	}
	
	private static void dump(String title, String key, Object value, String unique) {
		logger.info(unique + " "+title+" <"+key+">=<"+value+">");		
	}
}
