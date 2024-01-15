/**
 * 
 */
package com.seda.j2ee5.maf.util;

import java.lang.reflect.Field;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.seda.commons.logger.CustomLoggerManager;
import com.seda.commons.logger.LoggerWrapper;
import com.seda.commons.string.Convert;
/**
 * @author f.ricci
 *
 */
public class MAFCookies {

	private static final LoggerWrapper logger =  CustomLoggerManager.get(MAFCookies.class);

	public static final int DEFAULT_AGE = 2678400;

	private HttpServletRequest request;
	private HttpServletResponse response;

	public MAFCookies(HttpServletRequest request, HttpServletResponse response) {
		this.request=request;
		this.response=response;
	}

	public final void writeCookie(String cookieName, String cookieValue) {
		writeCookie(cookieName, cookieValue, DEFAULT_AGE);
	}
	
	public final void writeCookie(String cookieName, String cookieValue, int maxAge) {
		Cookie cookie = new Cookie(cookieName, cookieValue);
		cookie.setMaxAge(maxAge);
		writeCookie(cookie);
	}	

	public final void writeCookie(String cookieName, String cookieValue, String path) {
		writeCookie(cookieName, cookieValue, DEFAULT_AGE, path);	
	}		
	
	public final void writeCookie(String cookieName, String cookieValue, int maxAge, String path) {
		Cookie cookie = new Cookie(cookieName, cookieValue);
		cookie.setMaxAge(maxAge);
		cookie.setPath(path);
		writeCookie(cookie);	
	}		
	
	public final void writeCookie(Cookie cookie) {
		response.addCookie(cookie);
	}


	public final String readCookieValue(String cookieName) {
		Cookie cookie = readCookie(cookieName);
		if (cookie!=null) return cookie.getValue();
		return null;
	}	

	public final Cookie readCookie(String cookieName) {
		Cookie[] cookies = request.getCookies();
		if (cookies != null) {
			String inCookie;
			for (int loop=0, loopMax=cookies.length; loop < loopMax; loop++) {
				inCookie = cookies[loop].getName();
				if (cookieName.equals(inCookie)) {
					return cookies[loop];
				}					
			}
		}
		return null;
	}

	public final void deleteCookies(String... cookieNames) {
		String bufferName = Convert.arrayToString(cookieNames);  
		Cookie[] cookies = request.getCookies();
		if (cookies != null) {
			String cookieName;
			for (int loop=0, loopMax=cookies.length; loop < loopMax; loop++) {
				if (bufferName!=null) {
					cookieName = cookies[loop].getName();
					if (bufferName.contains(cookieName)) {
						deleteCookie(cookies[loop]);
					}					
				}
			}
		}	
	}

	public final void deleteCookie(String name) {
		Cookie[] cookies = request.getCookies();
		if (cookies != null) {
			String cookieName;
			for (int loop=0, loopMax=cookies.length; loop < loopMax; loop++) {
				cookieName = cookies[loop].getName();
				if (name.contains(cookieName)) {
					deleteCookie(cookies[loop]);
				}					
			}
		}	
	}	

	public final void deleteCookie(Cookie cookie) {
		cookie.setMaxAge(0);
		response.addCookie(cookie);
	}

	public static final void applyCookiesSupport(Object object,
			HttpServletRequest request, HttpServletResponse response) {

		if (object==null)
			return;
		
		Class<?> currentClass = object.getClass();
		while (currentClass != null) {
			// we also need to look for interface methods - 
			// because the class may be abstract
			Field[] arrFields = currentClass.getDeclaredFields();
			for (int i = 0; i < arrFields.length; i++) {
				if (arrFields[i].getName().equals("cookiesSupport")) {
					if (!arrFields[i].isAccessible()) arrFields[i].setAccessible(true);
					try {
						arrFields[i].set(object, new MAFCookies(request, response));
					} catch (Exception e) {
						logger.error("Unable to set field 'cookiesSupport' in object " + object.getClass().getName() + ", current class " + currentClass,e);
					} 
					return;
				}
			}
			currentClass = currentClass.getSuperclass();
		}		
	}
}
