/**
 * 
 */
package com.seda.j2ee5.maf.taglibs;

import java.util.Collections;
import java.util.Enumeration;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.seda.commons.logger.CustomLoggerManager;
import com.seda.commons.logger.LoggerWrapper;
import com.seda.j2ee5.maf.components.encoding.EncodingContext;

/**
 * @author f.ricci
 *
 */
public class PagingFilter {

	private static final LoggerWrapper logger =  CustomLoggerManager.get(PagingFilter.class);
	
	private Map<String, String> filters;

	private int pageNumber;
	private int pageSize;
	
	public int getPage() {
		return pageNumber;
	}
	public int getPageSize() {
		return pageSize;
	}
	private Map<String, String> getFilters() {
		if (filters==null) 
			filters=new LinkedHashMap<String, String>();
		return filters;
	}
	/**
	 */
	public String getValue(String filter) {
		return getFilters().get(filter);
	}

	private String putValue(String filter, String filterValue) {
		return getFilters().put(filter,filterValue);
	}	
	/**
	 * Costruttore di default
	 */
	@SuppressWarnings("unchecked")
	public PagingFilter(HttpServletRequest request, String suffix, int pageSizeDef){
		boolean encodingParameter = EncodingContext.getInstance().isEncodeParameter();
		if (encodingParameter) {
			Enumeration<String> attrKeys = request.getAttributeNames();
			while (attrKeys.hasMoreElements()) {
				String attrKey = (String) attrKeys.nextElement();
				if (attrKey.endsWith(suffix)) {
					int sfxpos = attrKey.lastIndexOf(suffix);
//					request.removeAttribute(attrKey);
					String keyValue = attrKey.substring(0,sfxpos);
					Object o = request.getAttribute(attrKey);
					if (o instanceof java.lang.String) {
						putValue(keyValue,(String)o);						
					} else {
						logger.warn("Encoded parameter key '" + attrKey+ "' reference to a not java.lang.String object: " + o.getClass().getName() + " > " + o.toString());
					}
				}
			}			
		} else {
			Map parametersMap = request.getParameterMap();
			Enumeration paramKeys = Collections.enumeration(parametersMap.keySet());
			while (paramKeys.hasMoreElements()) {
				String key = (String)paramKeys.nextElement();
				if (key.endsWith(suffix)) {
					int sfxpos = key.lastIndexOf(suffix);
					String keyValue = key.substring(0,sfxpos);
					putValue(keyValue,request.getParameter(key));
				}
			}			
		}
		PagingManager pagingManager = new PagingManager(request,pageSizeDef);
		this.pageNumber=pagingManager.getPageNumber();
		this.pageSize=pagingManager.getPageSize();
		pagingManager=null;
	}
	
	public String toString() {
		StringBuffer buffer = new StringBuffer();
		buffer.append(" PagingFilter [");
		buffer.append(" pageNumber=").append(pageNumber);
		buffer.append(" ,pageSize=").append(pageSize).append(", ");
		// loops through the columns
		Enumeration<String> enumKeys = Collections.enumeration(getFilters().keySet());
		while (enumKeys.hasMoreElements())  {
        	String filterame=enumKeys.nextElement();
        	String filtervalue=getValue(filterame);
        	buffer.append("[filter=").append(filterame).append(", value=").append(filtervalue).append("], ");
        }    			
        buffer.deleteCharAt(buffer.lastIndexOf(","));
        buffer.append("]");
		return buffer.toString();
	} 				

}
