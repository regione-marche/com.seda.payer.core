/**
 * 
 */
package com.seda.j2ee5.maf.taglibs;

import java.util.Enumeration;

import javax.servlet.ServletRequest;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.BodyTagSupport;

import com.seda.commons.logger.CustomLoggerManager;
import com.seda.commons.logger.LoggerWrapper;
import com.seda.commons.security.TokenGenerator;
import com.seda.j2ee5.maf.components.encoding.EncodingContext;

/**
 * @author f.ricci
 *
 */
public class LogDump extends BodyTagSupport {

	boolean encodingParameter = EncodingContext.getInstance().isEncodeParameter();	
	boolean encodingDelete = EncodingContext.getInstance().isEncodeDelete();	
	
	private static final LoggerWrapper logger =  CustomLoggerManager.get(LogDump.class);
	
	private String unique = TokenGenerator.generateLInternalToken(16);
	
	public static final String PAGE="page";
	public static final String HEADER="header";
	public static final String REQUEST="request";
	public static final String COOKIE="cookie";
	public static final String SESSION="session";
	public static final String APPLICATION="application";
	
	private static final String ALL_SCOPE = PAGE +";"+HEADER+";"+REQUEST+";"+COOKIE+";"+SESSION+";"+APPLICATION;
	
	private String scope;

	public void setScope(String scope) {
		this.scope = scope;
	}
	
	public final int doStartTag() throws JspTagException {
		if (scope==null)
			scope=REQUEST;
        return EVAL_BODY_BUFFERED;
    }
	
	public final int doEndTag() throws JspTagException {
		if (!ALL_SCOPE.contains(scope)) scope=REQUEST;
		if (scope.equals(PAGE)) {
			dumpPage();
		} else if (scope.equals(HEADER)) {
			dumpHeader();			
		} else if (scope.equals(REQUEST)) {
			dumpRequest();
		} else if (scope.equals(COOKIE)) {
			dumpCookie();			
		} else if (scope.equals(SESSION)) {
			dumpSession();
		} else {
			dumpApplication();					
		}

		recycle();
		
   		return EVAL_PAGE;
    }
	
	private void dumpPage() {
		String title="Page Dump";
		Enumeration<String> pageKeys = pageContext.getAttributeNamesInScope(PageContext.PAGE_SCOPE);
		dumpStart(title);
		while (pageKeys.hasMoreElements()) {
			String key = (String) pageKeys.nextElement();
			Object attribute = pageContext.getAttribute(key);
			dump(title, key, attribute);
		}
		dumpEnd(title);		
	}

	private void dumpHeader() {
		String title="Request Header Dump";
		ServletRequest request = pageContext.getRequest();
		if (request instanceof HttpServletRequest) {
			HttpServletRequest hreq = (HttpServletRequest)request;
			Enumeration headerKeys = hreq.getHeaderNames();
			dumpStart(title);		
			while (headerKeys.hasMoreElements()) {
				String key = (String) headerKeys.nextElement();
				Object attribute = hreq.getHeader(key);
				dump(title, key, attribute);
			}
			dumpEnd(title);			
		}
	}	
	
	private void dumpCookie(){
		String title="Request Cookies Dump";		
		ServletRequest request = pageContext.getRequest();
		if (request instanceof HttpServletRequest) {
			HttpServletRequest hreq = (HttpServletRequest)request;			
			Cookie[] cookies = hreq.getCookies();
			dumpStart(title);			
			for (int i = 0; i < cookies.length; i++) {
				Cookie cookie = cookies[i];
				dump(title, cookie.getName(), cookie.getValue());
			}
			dumpEnd(title);			
		}
	}
	
	private void dumpRequest() {
		String title="Request Dump";
		ServletRequest request = pageContext.getRequest();
		dumpStart(title);
		Enumeration attributeKeys = request.getAttributeNames();
		while (attributeKeys.hasMoreElements()) {
			String key = (String) attributeKeys.nextElement();
			Object attribute = request.getAttribute(key);
			dump("Request Dump attribute", key, attribute);
		}
		Enumeration parameterKeys = request.getParameterNames();
		while (parameterKeys.hasMoreElements()) {
			String key = (String) parameterKeys.nextElement();
			Object parameter = request.getParameter(key);
			dump("Request Dump parameter ", key, parameter);			
		}
		dumpEnd(title);		
	}


	private void dumpSession() {
		String title="Session Dump";		
		Enumeration<String> sessionKeys = pageContext.getAttributeNamesInScope(PageContext.SESSION_SCOPE);
		dumpStart(title);		
		while (sessionKeys.hasMoreElements()) {
			String key = (String) sessionKeys.nextElement();
			Object attribute = pageContext.getAttribute(key,PageContext.SESSION_SCOPE);
			dump(title, key, attribute);
		}		
		dumpEnd(title);		
	}	
	
	private void dumpApplication() {
		String title="Application Dump";		
		Enumeration<String> applicationKeys = pageContext.getAttributeNamesInScope(PageContext.APPLICATION_SCOPE);
		dumpStart(title);		
		while (applicationKeys.hasMoreElements()) {
			String key = (String) applicationKeys.nextElement();
			Object attribute = pageContext.getAttribute(key,PageContext.APPLICATION_SCOPE);
			dump(title, key, attribute);
		}		
		dumpEnd(title);	
	}
	
	private void dumpStart(String title) {
		dumpRef(title,"starts");
	}
	private void dumpEnd(String title) {
		dumpRef(title,"ends");
	}		
	
	private void dumpRef(String title, String action) {
		logger.info("**************** "+title+" "+action+", uniqueId="+unique);
	}
	
	private void dump(String title, String key, Object value) {
		logger.info(unique + " "+title+" <"+key+">=<"+value+">");		
	}
	
	private void recycle() {
		scope=null;
	}	
	
}
