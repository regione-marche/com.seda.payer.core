/**
 * 
 */
package com.seda.j2ee5.maf.util;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.seda.j2ee5.maf.defender.http.HttpDefenseRequest;

/**
 * @author Seda Lab
 *
 */
public class MAFRequest {

	private String currentURL;
	private String currentContext;
	private String realContext;
	private String afterContext;
	private String subcontext;        
	private String targetURL;
	private String extension;

	private HttpDefenseRequest defenseRequest;	
    
	public HttpServletRequest getHttpServletRequest() {
		return defenseRequest;
	}
	public HttpSession getHttpSession() {
		return defenseRequest.getSession(false);
	}
	public HttpSession getHttpSession(boolean create) {
		return defenseRequest.getSession(create);
	}
	public String getRemoteAddr() {
		return defenseRequest.getRemoteAddr();
	}
	public String getParameter(String key) {
		return defenseRequest.getParameter(key);
	}
	public Object getAttribute(String key) {
		return defenseRequest.getAttribute(key);
	}

	public void setAttribute(String key, Object value) {
		defenseRequest.setAttribute(key, value);
	}
	
	public HttpDefenseRequest getHttpDefenseRequest() {
		return defenseRequest;
	}	
	
	public Map getAttributeMap() {
		Enumeration attributeNames = defenseRequest.getAttributeNames();
		HashMap attributeMap = new HashMap();
		while (attributeNames.hasMoreElements()) {
			String name = (String) attributeNames.nextElement();
			attributeMap.put(name, defenseRequest.getAttribute(name));
		}
		return attributeMap;
	}	
	
	public String getCharacterEncoding() {
		return defenseRequest.getCharacterEncoding();
	}
	public String getContextPath() {
		return defenseRequest.getContextPath();
	}
	
	public String getCurrentURL() {
		return currentURL;
	}
	public String getCurrentContext() {
		return currentContext;
	}

	public String getRealContext() {
		return realContext;
	}
	public String getAfterContext() {
		return afterContext;
	}
	public String getSubcontext() {
		return subcontext;
	}
	public String getTargetURL() {
		return targetURL;
	}
	public String getExtension() {
		return extension;
	}

	public boolean isRootPath() {
		return (subcontext==null || subcontext.trim().length()==0) && (targetURL == null || targetURL.trim().length()==0) && realContext.equals(currentContext);
	}
	
	public MAFRequest(ServletRequest request) {
		this((HttpServletRequest)request);
	}	
	
	public MAFRequest(HttpServletRequest request) {
		if (request instanceof HttpDefenseRequest)
			defenseRequest=(HttpDefenseRequest)request;
		else
			defenseRequest=new HttpDefenseRequest(request);
		
		currentURL = request.getRequestURI();
        currentContext = URLUtil.getContext(currentURL);
        realContext=request.getContextPath();
        afterContext = URLUtil.getAfterContext(currentURL);
        subcontext = URLUtil.getSubcontext(afterContext);        
        targetURL = URLUtil.getTargetUrl(afterContext);
        extension = URLUtil.getUrlExtension(currentURL);
	}
	
}
