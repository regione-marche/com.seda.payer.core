package com.seda.tag.library;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.jsp.JspTagException;

import com.seda.j2ee5.maf.core.application.ApplicationData;
import com.seda.j2ee5.maf.core.application.ApplicationsData;
import com.seda.j2ee5.maf.util.MAFAttributes;

public class Utility {

	public static final String VALID_SCHEME_CHARS =	"abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789+.-";

	static String buildURL (String inputURL, HttpServletRequest request, HttpServletResponse response) throws JspTagException {
		return buildURL(inputURL, request, response, false);
	}
	static String buildURL (String inputURL, HttpServletRequest request, HttpServletResponse response, boolean image) throws JspTagException {


		/*Enumeration headerNames = request.getHeaderNames();
		    while(headerNames.hasMoreElements()) {
		      String headerName = (String)headerNames.nextElement();
		      System.out.println(headerName + ": " + request.getHeader(headerName));
		    }*/
		// if the URL is an empty url
		if (inputURL==null || inputURL.trim().length()==0) 
			return "";
		
		String resultURL = resolveUrl(inputURL,null,request, image);
		
		// if the URL is relative, rewrite it
		if (!isAbsoluteUrl(resultURL)) {
	        resultURL = response.encodeURL(resultURL);
		}
		return resultURL;

		//if (absoluteURL.indexOf("http") == -1 && absoluteURL.trim().length() > 0) {

		/*
				String[] absoluteURLSplit = absoluteURL.split("\\?");
				String app="";

				for (int i = 0; i < absoluteURLSplit.length; i++) {

					if (i == 0) {
						app=absoluteURLSplit[i].replace("../", "/").replace("./", "/");

						String currentApplicationName = (String)request.getAttribute(MAFAttributes.CURRENT_APPLICATION);
						ApplicationsData applicazioni = (ApplicationsData) request.getSession().getServletContext().getAttribute(MAFAttributes.APPLICATIONS);
						ApplicationData currentApplication = applicazioni.getApplication(currentApplicationName);

						if(app.indexOf(currentApplication.getSubcontext())==-1)
							app=currentApplication.getSubcontext() + "/" + app;
						if (absoluteURLSplit.length > 1)
							app=app+"?";
					}
					else
						app=app+absoluteURLSplit[i];
				}

				absoluteURL=app;
		 */
		/*				
				absoluteURL=absoluteURL.replace("../", "/").replace("./", "/");

				if (!absoluteURL.startsWith("/")) {
					String currentApplicationName = (String)request.getAttribute(MAFAttributes.CURRENT_APPLICATION);
					ApplicationsData applicazioni = (ApplicationsData) request.getSession().getServletContext().getAttribute(MAFAttributes.APPLICATIONS);
					ApplicationData currentApplication = applicazioni.getApplication(currentApplicationName);

					absoluteURL=currentApplication.getSubcontext() + "/" + absoluteURL;
				}


				if (absoluteURL.indexOf(request.getContextPath())==-1) {
					absoluteURL=request.getContextPath() + absoluteURL;
				}

				StringBuffer URL = request.getRequestURL();

				Integer port=80;
				String protocol="";
				try {
					URL url=new URL(URL.toString());
					port=url.getPort();
					protocol=url.getProtocol();
					url=null;
				} catch (MalformedURLException e) {
					e.printStackTrace();
				}



				//if (request.isSecure()) {
				if (protocol!=null && (protocol.startsWith("HTTPS") || protocol.startsWith("https"))) {
					//absoluteURL = "https://" + request.getServerName() + ":" + request.getServerPort() + absoluteURL;
					absoluteURL = "https://" + request.getServerName() + ":" + port + absoluteURL;
				} else {
					//absoluteURL = "http://" + request.getServerName() + ":" + request.getServerPort() + absoluteURL;
					absoluteURL = "http://" + request.getServerName() + ":" + port + absoluteURL;
				}

				URL=null;
				port=null;
				protocol=null;
			}
		 */		

//		return absoluteURL;
	}


	static String getAbsoluteImageURL (String relativeImageURL, HttpServletRequest request, HttpServletResponse response) {

		String absoluteImageURL=relativeImageURL;
		/*			
			if (absoluteImageURL.indexOf("http") == -1 && absoluteImageURL.trim().length() > 0) {

				absoluteImageURL=absoluteImageURL.replace("../", "/").replace("./", "/");

				if (absoluteImageURL.indexOf(request.getContextPath())==-1) {
					absoluteImageURL=request.getContextPath() + absoluteImageURL;
				}

				StringBuffer URL = request.getRequestURL();

				Integer port=80;
				String protocol="";
				try {
					URL url=new URL(URL.toString());
					port=url.getPort();
					protocol=url.getProtocol();
					url=null;
				} catch (MalformedURLException e) {
					e.printStackTrace();
				}

				//if (request.isSecure()) {
				if (protocol!=null && (protocol.startsWith("HTTPS") || protocol.startsWith("https"))) {
					absoluteImageURL = "https://" + request.getServerName() + ":" + port + absoluteImageURL;
				} else {
					absoluteImageURL = "http://" + request.getServerName() + ":" + port + absoluteImageURL;
				}

				URL=null;
				port=null;
				protocol=null;

			}
		 */			

		return absoluteImageURL;
	}

	private static String resolveUrl(String url, String context, HttpServletRequest request, boolean image) throws JspTagException {
		// don't touch absolute URLs
		if (isAbsoluteUrl(url))
			return url;

		if (url.startsWith("../"))
			url=url.substring(2);
		if (url.startsWith("./"))
			url=url.substring(1);

		// FIX-ME:  this not work properly; sometimes may be there an url that specifies another 
		//          MAF application sub context and then the final url will be corrupted.
		//          The best way is that the programmer specify always the MAF application sub context
		//          or the tag provide a way to resolve it by the application id (another tag attribute?)
		String currentApplicationName = (String)request.getAttribute(MAFAttributes.CURRENT_APPLICATION);
		ApplicationsData applicazioni = (ApplicationsData) request.getSession().getServletContext().getAttribute(MAFAttributes.APPLICATIONS);
		ApplicationData currentApplication = applicazioni.getApplication(currentApplicationName);		
	
		if (!image && 
				!url.startsWith("/") && 
				!url.contains(currentApplication.getSubcontext()+"/")) {
			url=currentApplication.getSubcontext()+(url.startsWith("/")?url:"/"+url);
		}
		// FIX-ME 
		
		// normalize relative URLs against a context root
		if (context == null) {
			if (url.startsWith("/")) {
				if (url.startsWith(request.getContextPath()+"/")) return url; 
				return (request.getContextPath() + url);
			} else{
				return url;
			}
		} else {
			if (!context.startsWith("/") || !url.startsWith("/")) {
				throw new JspTagException("ActionTag bad relative path");
			}
			if (context.equals("/")) {
				// Don't produce string starting with '//', many
				// browsers interpret this as host name, not as
				// path on same host.
				return url;
			} else {
				return (context + url);
			}
		}
	}

	/**
	 * Returns <tt>true</tt> if our current URL is absolute,
	 * <tt>false</tt> otherwise.
	 */
	public static boolean isAbsoluteUrl(String url) {
		// a null URL is not absolute, by our definition
		if (url == null)
			return false;

		// do a fast, simple check first
		int colonPos;
		if ((colonPos = url.indexOf(":")) == -1)
			return false;

		// if we DO have a colon, make sure that every character
		// leading up to it is a valid scheme character
		for (int i = 0; i < colonPos; i++)
			if (VALID_SCHEME_CHARS.indexOf(url.charAt(i)) == -1)
				return false;

		// if so, we've got an absolute url
		return true;
	}
	
}
