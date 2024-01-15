package com.seda.j2ee5.maf.components.encoding;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.ThreadContext;

import com.seda.commons.logger.CustomLoggerManager;
import com.seda.commons.logger.LoggerWrapper;
import com.seda.commons.security.TokenGenerator;
import com.seda.commons.string.Pad;
import com.seda.commons.validator.Validation;
import com.seda.j2ee5.maf.core.application.ApplicationsData;
import com.seda.j2ee5.maf.defender.http.HttpDefenseRequest;
import com.seda.j2ee5.maf.util.MAFAttributes;
import com.seda.j2ee5.maf.util.MAFDump;
import com.seda.j2ee5.maf.util.MAFLogger;
import com.seda.j2ee5.maf.util.MAFRequest;
/**
 * Servlet Filter implementation class EncodingFilter.
 * Set the set character encoding
 * Encoding of type application/ x-www-form-urlencoded 
 */
public class EncodingManager implements Filter {
	@SuppressWarnings("unused")
	private static final LoggerWrapper logger =  CustomLoggerManager.get(EncodingManager.class);
	private String defaultIgnoredExt="";
	private String defaultIgnoredSubcontext="";
    // for the encode parameter pattern
	public final static String URLENCODED = "application/x-www-form-urlencoded";	
	// for date encoding managemt
	private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	// Encoding configuration context
	EncodingContext encodingContext = EncodingContext.getInstance();
    /**
     * Default constructor. 
     */
    public EncodingManager() {
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
	
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		MAFRequest mafRequest = new MAFRequest((HttpServletRequest)request);
    	// pass the request along the filter chain, if this is a not filtered request
        if (mafRequest.getExtension()!=null && defaultIgnoredExt.contains(mafRequest.getExtension())) {
    		chain.doFilter(request, response);
    		return;
        }
        String subContextCheck=null;
        if (mafRequest.getSubcontext()==null || mafRequest.getSubcontext().trim().length()==0) {
        	subContextCheck=mafRequest.getHttpServletRequest().getServletPath();
        } else {
        	subContextCheck=mafRequest.getSubcontext();
        }

        if (subContextCheck!=null && subContextCheck.trim().length()>0 && !subContextCheck.trim().equals("/")) {
        	if (defaultIgnoredSubcontext.contains(subContextCheck)) {
        		chain.doFilter(request, response);
        		return;
        	}
        }

		// place your code here
		HttpDefenseRequest hreq=mafRequest.getHttpDefenseRequest();
//		HttpServletRequest hreq = (HttpServletRequest)request;
		HttpSession session = mafRequest.getHttpSession(false);
		if (session!=null) {
			ThreadContext.put(MAFLogger.MDC_CTX, session.getServletContext().getServletContextName());	
		} else {
			ThreadContext.put(MAFLogger.MDC_CTX, "ctx_nosid");
		}
		
		hreq.setCharacterEncoding(encodingContext.getCharacterEncoding());
		String contentType = request.getContentType();
		// if encode parameter manager is active, call paramsToAttrbitutes
//		if (encodingContext.isEncodeParameter()) {
			if ((contentType == null) || contentType.equalsIgnoreCase(URLENCODED)) {
				HashMap<String, Object> encodedParameters = paramsToAttributes(hreq);
				hreq.setEncodedParameters(encodedParameters);
			}
//		}
		chain.doFilter(hreq, response);
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		encodingContext.initialize(fConfig);
		sdf.setLenient(false); // date management
		ServletContext context = fConfig.getServletContext();
		// get application data from this installazione context
		ApplicationsData applicationsData = (ApplicationsData)context.getAttribute(MAFAttributes.APPLICATIONS);
		if (applicationsData!=null) {
			defaultIgnoredExt=applicationsData.getDefaultIgnoredExt();
			defaultIgnoredSubcontext=applicationsData.getDefaultIgnoredSubcontext();
		}
	}

	/**
	 * Encoding of type application/ x-www-form-urlencoded 
	 */
	@SuppressWarnings("unchecked")
	private HashMap<String, Object> paramsToAttributes(HttpDefenseRequest request) {
		HashMap<String, Object> encodedParameters= new HashMap<String,Object>();
		
		Enumeration paramNames = request.getParameterNames();
		final Set<String> dateNames = new HashSet<String>();
//		String dateNames="";
		while (paramNames.hasMoreElements())     {
			String paramName = (String)	paramNames.nextElement();
			String dateName = getNameDate(paramName);
			if (dateName!=null && !dateNames.contains(dateName)) {
				dateNames.add(dateName);
				dateHandler(dateName,request);
			}

			String [] values = request.getParameterValues(paramName);
			if (values.length == 1) {
				if (encodingContext.isEncodeParameter()) 
					request.setAttribute(paramName, values[0]);
				encodedParameters.put(paramName, values[0]);				
			} else {
				if (encodingContext.isEncodeParameter())
					request.setAttribute(paramName, values);
				encodedParameters.put(paramName, values);				
			}
			if (encodingContext.isEncodeParameter() && 
					encodingContext.isEncodeDelete()) request.removeParameter(paramName);
		}
		return encodedParameters;
	}

	public static void dateHandler(String dateName, HttpDefenseRequest request) {
		String[] dateValueArray = getDateValueArray(dateName, request);
		String dateValue = getDateValueString(dateValueArray);
		try {
			sdf.parse(dateValue);
			Calendar date = Calendar.getInstance();
			date.set(Integer.valueOf(dateValueArray[0]), Integer.valueOf(dateValueArray[1])-1, Integer.valueOf(dateValueArray[2]));
			request.setAttribute(dateName, date);
			if (EncodingContext.getInstance().isEncodeDelete()) removeDateParameter(request, dateName);
		} catch (ParseException e) {}
	}

	public static void removeDateParameter(HttpDefenseRequest request, String dateName) {
		request.removeParameter(dateName.concat(Validation.suffixDay));
		request.removeParameter(dateName.concat(Validation.suffixMonth));
		request.removeParameter(dateName.concat(Validation.suffixYear));			
	}
	
	public static String[] getDateValueArray(String dateName, HttpDefenseRequest request) {
		String gg = request.getParameter(dateName.concat(Validation.suffixDay));
		String mm = request.getParameter(dateName.concat(Validation.suffixMonth));
		String ssaa = request.getParameter(dateName.concat(Validation.suffixYear));
		return new String[]{ssaa,mm,gg};	
	}
	
	public static String getDateValueString(String[] dateValueArray) {
		return dateValueArray[0].concat("-").concat(Pad.left(dateValueArray[1],2,'0')).concat("-").concat(Pad.left(dateValueArray[2],2,'0'));
	}
	
	public static String getNameDate(String paramName) {
		if (paramName.endsWith(Validation.suffixDay)) {
			return paramName.substring(0, paramName.indexOf(Validation.suffixDay));
		} else if (paramName.endsWith(Validation.suffixMonth)) {
			return paramName.substring(0, paramName.indexOf(Validation.suffixMonth));
		}else if (paramName.endsWith(Validation.suffixYear)) {
			return paramName.substring(0, paramName.indexOf(Validation.suffixYear));
		} else {
			return null;
		}
		
	}

}
