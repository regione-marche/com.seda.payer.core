/**
 * 
 */
package com.seda.j2ee5.maf.components.defender.csrf;

import java.security.NoSuchAlgorithmException;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.seda.commons.security.TokenGenerator;
import com.seda.j2ee5.maf.core.security.SignOnKeys;
import com.seda.j2ee5.maf.util.MAFRequest;
import com.seda.j2ee5.maf.util.MAFUtil;

/**
 * @author Seda Lab
 *
 */
public class CSRFDefender {

	private CSRFDefender() {}
	
	public static void enforceRequest(ServletRequest request, boolean enforceCsrf) throws CSRFException {
		if (!CSRFContext.getInstance().isActive()) return; 
		if(request instanceof HttpServletRequest) {
			HttpServletRequest hreq = (HttpServletRequest)request;
			MAFRequest mafRequest = new MAFRequest(hreq);
			
			HttpSession session = hreq.getSession(false);
			
			if(session != null) {
				if (mafRequest.isRootPath() && CSRFContext.getInstance().isIgnoreRoot()) {
					if (CSRFContext.getInstance().isIgnoreRootRegenerate()) {
						// force the session to be regenerated
						MAFUtil.regenerateSession(hreq,true);						
					}
					//ignore uri that match the application root
					return;
				}
				
				String sToken = getTokenFromSession(session);
	        	String rToken = getTokenFromRequest(hreq);
	        	
	        	if (sToken==null || sToken.isEmpty()) {
	        		// check for a not synchronized token
	        		if (rToken!=null && enforceCsrf) {
		        		if (CSRFContext.getInstance().isDenied()) nullToken(session);
		        		throw new CSRFException("CSRF attack from " + request.getRemoteAddr() + " sToken="+sToken+", rToken="+rToken);
	        		}
		        	// generate the next new token
	        		// setToken(session);
	        	} else if (!sToken.equals(rToken) && !mafRequest.getTargetURL().equals(SignOnKeys.FORM_SSO_URL)  && enforceCsrf) {
	        		/**
	        		 * Remove any token that may exist in the session.
	        		 * Note: Failure to due so may result in an infinite loop
	        		 * (i.e. not invalidating session, redirect to protected page - token will always be wrong for subsequent requests)
	        		 */
	        		if (CSRFContext.getInstance().isDenied()) nullToken(session);
	        		throw new CSRFException("CSRF attack from " + request.getRemoteAddr() + " sToken="+sToken+", rToken="+rToken);	        			
	        	} else {
	        		// generate the next new token
	        		setToken(session);
	        	}
			}
		}
	}
	
	public static void enforceResponse(ServletRequest request) {
		if (!CSRFContext.getInstance().isActive()) return;
		if(request instanceof HttpServletRequest) {
			HttpServletRequest hreq = (HttpServletRequest)request;
			
			HttpSession session = hreq.getSession(false);
		
			// if(session != null) {
			// 	if (isRedirect(request)) {
			// 		// restore previous token because the page was not refreshed
			// 		nullToken(session);
			// 	} else if (isWriter(request)) {
			// 		// restore previous token because the page was not refreshed
			// 		restoreSavedToken(session);
			// 	} else {
			// 		String token = getTokenFromSession(session);
			// 		// questo ha senso solo con un response wrapper e 
			// 		// rielaborazione del buffer di output con parser html
			// 		if(token == null || token.length() == 0) {
			// 			setToken(session);
			// 		}
			// 	} 
			// }
		}
	}
	
	public static void enforceSession(HttpSession session) {
		if (!CSRFContext.getInstance().isActive()) return;
		setToken(session);
	}
	
	public static void setRedirect(HttpServletRequest request) {
		if (!CSRFContext.getInstance().isActive()) return;
		request.setAttribute(CSRFContext.CSRF_REDIRECT, new Boolean(true));
	}
	
	public static void setRedirect(ServletRequest request) {
		setRedirect((HttpServletRequest)request);
	}
	
	public static void setWriter(HttpServletRequest request) {
		if (!CSRFContext.getInstance().isActive()) return;
		request.setAttribute(CSRFContext.CSRF_WRITER, new Boolean(true));
	}
	
	public static void setWriter(ServletRequest request) {
		setWriter((HttpServletRequest)request);
	}
	
	public static boolean isRedirect(ServletRequest request) {
		return isRedirect((HttpServletRequest)request);
	}
	public static boolean isWriter(ServletRequest request) {
		return isWriter((HttpServletRequest)request);
	}
	public static boolean isRedirect(HttpServletRequest request) {
		boolean redirect=false;
		if (request.getAttribute(CSRFContext.CSRF_REDIRECT)!=null)
			redirect = (Boolean)request.getAttribute(CSRFContext.CSRF_REDIRECT);
		return redirect;
	}
	public static boolean isWriter(HttpServletRequest request) {
		boolean writer=false;
		if (request.getAttribute(CSRFContext.CSRF_WRITER)!=null)
			writer = (Boolean)request.getAttribute(CSRFContext.CSRF_WRITER);
		return writer;
	}	
	
	private static String getTokenFromSession(HttpSession session) {
		return (String)session.getAttribute(CSRFContext.getInstance().getTokenName());
	}
	
	private static String getTokenFromRequest(HttpServletRequest request) {
		// get token from request parameter
		String token = (String)request.getParameter(CSRFContext.getInstance().getTokenName());
		if (token==null) 
			// try to find it in the request attributes
			token = (String)request.getAttribute(CSRFContext.getInstance().getTokenName());

		return token;
    }
	
	private static void setToken(HttpSession session) 	{
		if (!CSRFContext.getInstance().isActive()) return;
		try { 
			String token = null;
			String tokenProvider = CSRFContext.getInstance().getTokenProvider();
			if (tokenProvider.equals(TokenGenerator.PROVIDER_INTERNAL)) {
				token = TokenGenerator.generateInternalToken(CSRFContext.getInstance().getTokenLength());
			} else if (tokenProvider.equals(TokenGenerator.PROVIDER_SECURE_RANDOM)) {
				token = TokenGenerator.generateSecureToken(TokenGenerator.RNG, CSRFContext.getInstance().getTokenLength());
			} else if (tokenProvider.equals(TokenGenerator.PROVIDER_UUID)) {
				token = TokenGenerator.generateUUIDToken(true);
			} else {
				token = TokenGenerator.generateInternalToken(CSRFContext.getInstance().getTokenLength());
			}
			String tokenToSave = (String)session.getAttribute(CSRFContext.getInstance().getTokenName());
			if (tokenToSave==null)
				tokenToSave=token;
			// session.removeAttribute(CSRFContext.getInstance().getTokenNameSave());
			// session.setAttribute(CSRFContext.getInstance().getTokenNameSave(), tokenToSave);
			// session.removeAttribute(CSRFContext.getInstance().getTokenName());
			session.setAttribute(CSRFContext.getInstance().getTokenName(), token);
    	} catch (NoSuchAlgorithmException x) {
    		x.printStackTrace();
    	}
	}

	private static void restoreSavedToken(HttpSession session) 	{
		if (!CSRFContext.getInstance().isActive()) return;
		String saveToken = (String)session.getAttribute(CSRFContext.getInstance().getTokenNameSave());
		if (saveToken!=null)
			session.setAttribute(CSRFContext.getInstance().getTokenName(), saveToken);
	}
	private static void nullToken(HttpSession session) 	{
		session.removeAttribute(CSRFContext.getInstance().getTokenName());
	}
}
