/**
 * 
 */
package com.seda.j2ee5.maf.components.encoding;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.servlet.http.HttpServletRequest;

import com.seda.commons.logger.CustomLoggerManager;
import com.seda.commons.logger.LoggerWrapper;
import com.seda.j2ee5.maf.util.MAFAttributes;
/**
 * @author Seda Lab
 *
 */
public class ParameterDiscovery {
	
	private static final LoggerWrapper logger =  CustomLoggerManager.get(ParameterDiscovery.class);
	
	private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	
	public static String getEncodedParameter(HttpServletRequest request, String paramName) {
		String parameter=null;
		Object object = null;
		if (EncodingContext.getInstance().isEncodeParameter()) {
			object = request.getAttribute(paramName);
			if (object!=null) {
				if (object instanceof String) {
					parameter = (String)object;				
				} else {
					logger.warn("Encoded attribute '"+paramName+"' is not a java.lang.String class: " +object.getClass().getName() + ", toString=" +object);
				}				
			} 
		} else {
			parameter = (String)request.getParameter(paramName);	
		}
		return parameter;
	}
	
	public static String getSafeEncodedParameter(HttpServletRequest request, String paramName, String defaultValue) {
		String value=defaultValue;		
		Boolean rejected = (Boolean)request.getAttribute(MAFAttributes.VALIDATION_REJECTED);
		if (rejected!=null && rejected) {
			String paramValue=getEncodedParameter(request, paramName);
			if (paramValue!=null)
				value=paramValue;
		}
		//if (rejected==null || !rejected) {
		//	String paramValue=getEncodedParameter(request, paramName);
		//	value=(paramValue==null?defaultRejectValue:paramValue);
		//} 		
		return value;
	}	
	
	public static String[] getSafeEncodedParameterValues(HttpServletRequest request, String paramName, String[] defaultValue) {
		String[] value=defaultValue;		
		Boolean rejected = (Boolean)request.getAttribute(MAFAttributes.VALIDATION_REJECTED);
		if (rejected!=null && rejected) {
			String[] paramValue=getEncodedParameterValues(request, paramName);
			if (paramValue!=null) 
				value=paramValue;
		} 		
		//if (rejected==null || !rejected) {
		//	String[] paramValue=getEncodedParameterValues(request, paramName);
		//	value=(paramValue==null?defaultValue:paramValue);
		//} 		
		return value;
	}	
	
	public static String[] getEncodedParameterValues(HttpServletRequest request, String paramName) {
		String[] parameterValues=null;
		Object object=null;
		if (EncodingContext.getInstance().isEncodeParameter()) {
			object = request.getAttribute(paramName);
			if (object!=null) {
				if (object instanceof String[]) {
					parameterValues = (String[])object;		
				} else {
					// try to find a single string (especially for a checkbox group)
					if (object instanceof String) {
						parameterValues = new String[]{(String) object};
					} else {
						logger.warn("Encoded attribute '"+paramName+"' is not a [Ljava.lang.String neither java.lang.String class: " +object.getClass().getName() + ", toString=" +object);						
					}
				}
			}
		} else {
			parameterValues = (String[])request.getParameterValues(paramName);	
		}
		return parameterValues;
	}
	
	public static Calendar getEncodedCalendar(HttpServletRequest request, String paramName) {
		Calendar parameter=null;
		if (EncodingContext.getInstance().isEncodeParameter()) {
			parameter = (Calendar)request.getAttribute(paramName);
		}
		return parameter;
	}
	
	public static String getEncodedCalendarISO(HttpServletRequest request, String paramName) {
		String dateISO=null;
		Calendar parameter=getEncodedCalendar(request, paramName);
		if (parameter!=null) {
			dateISO = sdf.format(parameter) ;
		}
		return dateISO;
	}
	
	public static String getSafeEncodedCalendarISO(HttpServletRequest request, String paramName, String defaultCalendar) {
		String dateISO=defaultCalendar;
		Boolean rejected = (Boolean)request.getAttribute(MAFAttributes.VALIDATION_REJECTED);
		
		if (rejected!=null && rejected) {		
			Calendar parameter=getEncodedCalendar(request, paramName);
			if (parameter!=null) {
				dateISO = sdf.format(parameter) ;
			} else {
				dateISO=defaultCalendar;
			}
		}
		
		//if (rejected==null || !rejected) {		
		//	Calendar parameter=getEncodedCalendar(request, paramName);
		//	if (parameter!=null) {
		//		dateISO = sdf.format(parameter) ;
		//	} else {
		//		dateISO=defaultCalendar;
		//	}
		//}
		return dateISO;
	}		
}
