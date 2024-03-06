/**
 * 
 */
package com.seda.j2ee5.maf.taglibs;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.seda.j2ee5.maf.components.encoding.EncodingContext;

/**
 * @author f.ricci
 *
 */
public class PagingManager {

	public static final String PAGE_SIZE_NAME="pageSize";
	public static final String PAGE_NUMBER_NAME="pageNumber";
	public static final String PAGE_NUMBER_TARGET_NAME="pageNumberTarget";
	public static final int PAGE_SIZE_DEFAULT = 15;
	public static final int PAGE_NUMBER_DEFAULT = 1;	
	
	private int pageSizeDefault = PAGE_SIZE_DEFAULT;
	private int pageNumberDefault = PAGE_NUMBER_DEFAULT;	
	private String pageSizeName = PAGE_SIZE_NAME;
	private String pageNumberName = PAGE_NUMBER_NAME;
	private String pageNumberTargetName = PAGE_NUMBER_TARGET_NAME;
	private Map<String,Integer> pageInfo = new HashMap<String, Integer>(2);
	
	
	public int getPageSizeDefault() {return pageSizeDefault;}
	public int getPageNumberDefault() {return pageNumberDefault;}	
	public String getPageSizeName() {return pageSizeName;}
	public String getPageNumberName() {return pageNumberName;}
	public String getPageNumberTargetName() {return pageNumberTargetName;}

	public int getPageSize() {return pageInfo.get(getPageSizeName());}
	public int getPageNumber() {return pageInfo.get(getPageNumberName());}
	
	public PagingManager(HttpServletRequest request) {
		buildPageInfo(request);
	}
	
	public PagingManager(HttpServletRequest request,int pageSizeDefault) {
		this.pageSizeDefault=pageSizeDefault;
		buildPageInfo(request);
	}
	
	public PagingManager(HttpServletRequest request,String pageSizeName, String pageNumberName, String pageNumberTargetName) {
		this.pageSizeName=pageSizeName;
		this.pageNumberName=pageNumberName;
		this.pageNumberTargetName=pageNumberTargetName;
		buildPageInfo(request);		
	}
	
	private void buildPageInfo(HttpServletRequest request) {
		String pageSizeString = null;		
		String pageNumberString = null;		
		String pageNumberTargetString = null;
		
		boolean encodeParameter = EncodingContext.getInstance().isEncodeParameter();
		if (encodeParameter) {
			pageSizeString = String.valueOf(request.getAttribute(getPageSizeName()));		
			pageNumberString = String.valueOf(request.getAttribute(getPageNumberName()));		
			pageNumberTargetString = String.valueOf(request.getAttribute(getPageNumberTargetName()));
			request.removeAttribute(getPageNumberTargetName());			
		} else {
			pageSizeString = request.getParameter(getPageSizeName());		
			pageNumberString = request.getParameter(getPageNumberName());		
			pageNumberTargetString = request.getParameter(getPageNumberTargetName());
		}
		
		if (pageNumberTargetString!=null && !"null".equals(pageNumberTargetString)) {
			pageNumberString=pageNumberTargetString;
		}
		int pageNumber = 1;		
		if (pageNumberString!=null && pageNumberString.trim().length()>0 && !"null".equals(pageNumberString)) {
			try {
				pageNumber=Integer.parseInt(pageNumberString);
			} catch (NumberFormatException x) {
				pageNumber=getPageNumberDefault();
			}
		}
		pageInfo.put(getPageNumberName(), pageNumber);
		int pageSize=getPageSizeDefault();
		if (pageSizeString!=null && pageSizeString.trim().length()>0 && !"null".equals(pageSizeString)) {
			try {
				pageSize=Integer.parseInt(pageSizeString);
			} catch (NumberFormatException x) {
				pageSize=getPageSizeDefault();
			} 
		}
		pageInfo.put(getPageSizeName(), pageSize); 
		request.setAttribute(getPageSizeName(), String.valueOf(pageSize));
		request.setAttribute(getPageNumberName(), String.valueOf(pageNumber));
	}
	
}
