/**
 * 
 */
package com.seda.j2ee5.maf.core.screen;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.seda.commons.logger.CustomLoggerManager;
import com.seda.commons.logger.LoggerWrapper;
/**
 * @author f.ricci
 *
 */
public class ScreenRedirect {

	
	private static final LoggerWrapper logger =  CustomLoggerManager.get(ScreenRedirect.class);
	
	private ScreenRedirectData redirectData;
	private String applid;
	
	public void init(ScreenRedirectData redirectData, String applid) {
		this.redirectData=redirectData;
		this.applid=applid;
	}

	public void end() {
		this.redirectData=null;
	}	
	
	public void redirect(HttpServletRequest request, HttpServletResponse response)
    	throws java.io.IOException {
		
		if (redirectData==null) return;
		
		String urlToRedirect=null;
		if (redirectData.isInScope()) {
			urlToRedirect=(String)request.getAttribute(redirectData.getId());
		} else {
			urlToRedirect=redirectData.getURL();
		}
		
		if (urlToRedirect==null) {
			logger.error(String.format("[%s] Url to redirect not found for %s", applid, redirectData.toString()));
			return;
		}
		
		response.sendRedirect(urlToRedirect);
	}
	
	
	
}
