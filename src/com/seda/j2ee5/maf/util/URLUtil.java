/**
 * 
 */
package com.seda.j2ee5.maf.util;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;

/**
 * Gives the utility to manage URL
 * @author Seda Lab
 *
 */
public class URLUtil {

	public static final String urlRedirect(String ctx, String url) {
		String urlRedirect=null;
		if (ctx!=null&&url!=null) {
			// this url follows the action or screen pattern
        	if (url.indexOf(".")==-1) {
        		// yes, add last slash
//    			urlRedirect = setLastSlash(setFirstSlash(ctx)+setFirstSlash(url));
        		if (ctx.endsWith("/") && url.startsWith("/")) {
        			urlRedirect = setFirstSlash(ctx) + setLastSlash(url.substring(1)); 
        		}
        		if (ctx.endsWith("/") && !url.startsWith("/")) {
        			urlRedirect = setFirstSlash(ctx) + setLastSlash(url);
        		} 
        		if (!ctx.endsWith("/") && url.startsWith("/")) {
        			urlRedirect = setFirstSlash(ctx) + setLastSlash(url);
        		}
        		if (!ctx.endsWith("/") && !url.startsWith("/")) {
        			urlRedirect = setFirstSlash(ctx) + setLastSlash(setFirstSlash(url));
        		}
        	} else {
        		// no, remove the last slash
        		if (ctx.endsWith("/") && url.startsWith("/")) {
        			urlRedirect = setFirstSlash(ctx) + url.substring(1); 
        		}
        		if (ctx.endsWith("/") && !url.startsWith("/")) {
        			urlRedirect = setFirstSlash(ctx) + url;
        		} 
        		if (!ctx.endsWith("/") && url.startsWith("/")) {
        			urlRedirect = setFirstSlash(ctx) + url;
        		}
        		if (!ctx.endsWith("/") && !url.startsWith("/")) {
        			urlRedirect = setFirstSlash(ctx) + setFirstSlash(url);
        		}
        	}
		} else if (ctx!=null&&url==null) {
			urlRedirect=setLastSlash(setFirstSlash(ctx));
		} 
		return urlRedirect;
	} 
	
	public static final String addParameter(String url, String paramName, String paramValue) {
		String newUrl=null;
		if (url!=null) {
			newUrl=url;
			if (url.indexOf('?')==-1) newUrl = url.concat("?");
			else newUrl=url.concat("&");
			newUrl = newUrl.concat(paramName).concat("=").concat(paramValue);
		}
		return newUrl;
	}
	
	public static final String setLastSlash(String url) {
		return url.endsWith("/")?url:url+"/";
	}
	
	public static final String setFirstSlash(String url) {
		return url.startsWith("/")?url:"/"+url;
	}	

	public static final String setBothSlash(String url) {
		return setLastSlash(setFirstSlash(url));
	}
	
	public static final String getContext(String currentURL) {
        String context = null;
        if (currentURL.startsWith("/")) {
        	// find the next slash
        	int nextSlash = currentURL.indexOf("/",1); // skip the starting slash
            if (nextSlash != -1) {
            	context = currentURL.substring(0, nextSlash);
            } else {
            	context=currentURL;
            }
            	
        }
        return context;
	}
	
	public static final String getAfterContext(String currentURL) {
        String targetURL = null;
		int firstSlash = currentURL.indexOf("/",1); // skip the starting slash
//        if (firstSlash != -1) targetURL = currentURL.substring(firstSlash + 1, currentURL.length());
        if (firstSlash != -1) targetURL = currentURL.substring(firstSlash, currentURL.length());
        return removeAfterQMark(targetURL);
	}
	
	public static final String getSubcontext(String afterContext) {
		if (afterContext==null) return null;		
        String targetctx = null;
        int lastSlash = -1;     
        if (!afterContext.startsWith("/")) {
        	// this is not a valid subcontext
        	return targetctx;
        }
        if (afterContext.endsWith("/")) {
        	targetctx = afterContext.substring(0,afterContext.length()-1);
        	return targetctx;
        }
        // find the position of the last slash
        lastSlash = afterContext.substring(1).lastIndexOf("/");
        lastSlash++;
        // if no more slash
        if (lastSlash==-1) {
            // this after context follows the action pattern with a dot (this is a not valid subcontext pattern)
        	if (afterContext.indexOf(".")==-1) {
        		// if not return the passed value
        		targetctx = afterContext;
        	}
        } else {
        	targetctx = afterContext.substring(0, lastSlash);
        }
        return targetctx;
	}		
	
	public static final String getTargetUrl(String fullURL) {
		if (fullURL==null) return null;		
        String selectedURL = null;
        int lastPathSeparator = lastIndexSlash(fullURL) + 1;
        if (lastPathSeparator != -1) {
        	selectedURL = fullURL.substring(lastPathSeparator, fullURL.length());
        }
        return removeAfterQMark(selectedURL);
	}	
	
	public static final String getUrlKey(String fullURL) {
        return getUrlKey(fullURL, null);
	}
	
	public static final String getUrlKey(String fullURL, String defaultValue) {
		if (fullURL==null) return null;
        String urlKey = defaultValue;
        int lastPathSeparator = lastIndexSlash(fullURL) + 1;
        int lastDot = lastDot(fullURL);
        if (validLast(lastPathSeparator, lastDot)) {
        	urlKey = fullURL.substring(lastPathSeparator, lastDot);
        }
        return urlKey;
	}

	public static final String getUrlExtension(String fullURL) {
		String ext=null;
		if (fullURL==null) return null;		
        int lastDot = lastDot(fullURL);
        int lastPathSeparator = lastIndexSlash(fullURL) + 1;        
        if (validLast(lastPathSeparator, lastDot)) {
        	ext=fullURL.substring(lastDot);
        }
        return removeAfterQMark(ext);
	}
	
	public static final String getBeforeLastDot(String s) {
		if (s==null) return null;
		int lastDot = lastDot(s);
		if (lastDot > 0) {
			return s.substring(0, lastDot);
		}
		return null;
	}	
	
	private static int lastIndexSlash(String fullURL) {
		if (fullURL==null) return -1;		
		return fullURL.lastIndexOf("/");
	}
	
	private static int lastDot(String fullURL) {
		if (fullURL==null) return -1;		
		return fullURL.lastIndexOf(".");
	}
	
	private static boolean validLast(int lastPathSeparator, int lastDot) {
		return (lastPathSeparator != -1 && lastDot != -1 && lastDot > lastPathSeparator); 
	}
	
	public static String removeAfterLastSlash(String fullURL) {
		if (fullURL==null) return null;		
		int lastPathSeparator = lastIndexSlash(fullURL);
		if (lastPathSeparator==fullURL.length()-1) {
			return fullURL.substring(0, lastPathSeparator);
		}
		return fullURL;
	}
	
	public static String removeAfterQMark(String url) {
		if (url==null) return null;		
		int firstMark = url.indexOf("?");
        if (firstMark!= -1) return url.substring(0,firstMark++);
		return url;
	}
	
	public static HashMap<String, String> getParameterMap(String completeURL) {
		HashMap<String, String> parametersMap = new HashMap<String,String>();
		int qm = completeURL.indexOf("?");
		if (qm > -1) {
			String searchURL = completeURL.substring(qm + 1);
			String params[] = searchURL.split("&");
			for (String param : params) {
				String pair[] = param.split("=");
				try {
					parametersMap.put(pair[0], java.net.URLDecoder.decode(pair[1], "UTF-8"));
				} catch (UnsupportedEncodingException e) {
					//ignore
				}
			}
		}
		return parametersMap;
	}
}
