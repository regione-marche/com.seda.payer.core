/**
 * 
 */
package com.seda.j2ee5.maf.components.defender.csrf;

import javax.servlet.http.HttpSession;
/**
 * @author Seda Lab
 *
 */
public class CSRFUtil {

	
	public static final String getTokenFromSession(HttpSession session, String tokenName) {
		return (String)session.getAttribute(tokenName);
	}
	
	public static final String getTokenFromSession(HttpSession session) {
		String tokenName = CSRFContext.getInstance().getTokenName();
		String tokenValue = getTokenFromSession(session,tokenName);
		return tokenValue ;
	}
	
	public static final String getInputHidden(HttpSession session) {
		StringBuffer inputHidden = new StringBuffer();
		inputHidden.append("<input type=\"hidden\"");
		inputHidden.append(getInputValue(session));
		inputHidden.append("/>");
		return inputHidden.toString();
	}
	
	public static final String getInputDisplayNone(HttpSession session) {
		StringBuffer inputDisplayNone = new StringBuffer();
		inputDisplayNone.append("<input type=\"text\"");
		inputDisplayNone.append(getInputValue(session));
		inputDisplayNone.append(" style=\"display: none;\"/>");
		return inputDisplayNone.toString();
	}	
	
	private static final String getInputValue(HttpSession session) {
		StringBuffer valueBuffer = new StringBuffer();
		String tokenName = CSRFContext.getInstance().getTokenName();
		valueBuffer.append( tokenName!=null? " name=\""+tokenName+"\"":"");
		String tokenValue = getTokenFromSession(session,tokenName);
		valueBuffer.append( tokenValue!=null? " value=\""+tokenValue+"\"":"");
		return valueBuffer.toString();
	}
	
	public static final String getAugmentedUrl(HttpSession session, String baseUrl) {
		StringBuffer augmentedUrl = new StringBuffer();
		if (baseUrl!=null) {
			augmentedUrl.append(baseUrl);
			if (baseUrl.indexOf('?')==-1) 
				augmentedUrl.append("?");
			else 
				augmentedUrl.append("&");
			
			String tokenName = CSRFContext.getInstance().getTokenName();
			String tokenValue = getTokenFromSession(session,tokenName);
			augmentedUrl.append(tokenName);
			augmentedUrl.append("=");
			augmentedUrl.append(tokenValue);
		}
		return augmentedUrl.toString();
	}
}
