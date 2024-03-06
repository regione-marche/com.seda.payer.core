/**
 * 
 */
package com.seda.tag.core;

import java.lang.reflect.Method;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * @author provvedi
 *
 */
public class PageWrapper {

	public final static String PAGEV1_CLASSNAME = "com.seda.data.spi.PageInfo";
	public final static String PROBE_METHOD_1="getNumRows";
	
	public final static String PAGEV2_CLASSNAME = "com.seda.data.page.PageMetaData";
	public final static String PROBE_METHOD_2="getTotalRows";

	public final static Class<?>[] EMPTY_SIGNATURE=new Class<?>[0];	
	public final static Object[] EMPTY_ARGUMENTS=new Object[0];
	
	public final static String FIRSTROW="firstrow";
	public final static String LASTROW="lastrow";
	public final static String NUMROWS="numrows";
	public final static String NUMPAGES="numpages";
	public final static String PAGENUMBER="pagenumber";
	public final static String ROWSPERPAGE="rowsperpage";

	private int CURRENT_PAGE_VERSION ;
	private Class<?> CURRENT_CLASS_VERSION;
	private String CURRENT_CLASS_VERSION_NAME;
	
	private HashMap<String,Method> methodMap; 
	
	private static Map<String,HashMap<String, Method>> methodsBuffer=Collections.synchronizedMap(new HashMap<String,HashMap<String, Method>>());
	
	private Object paging;
	
	private void discoverFrameWork() {
		if (paging==null) return;
		CURRENT_CLASS_VERSION=paging.getClass();
		CURRENT_CLASS_VERSION_NAME=CURRENT_CLASS_VERSION.getName();
		if (methodsBuffer.containsKey(CURRENT_CLASS_VERSION_NAME)) {
			methodMap=methodsBuffer.get(CURRENT_CLASS_VERSION_NAME);
		} else {
			Method probe=discoverMethod(PROBE_METHOD_2);
			if (probe==null) {
				probe=discoverMethod(PROBE_METHOD_1);
				if (probe!=null) {
					CURRENT_PAGE_VERSION=1;
				}
			} else {
				CURRENT_PAGE_VERSION=2;
			}
			
			if (CURRENT_PAGE_VERSION>0) {
				methodMap=new HashMap<String, Method>(6);
				methodMap.put(FIRSTROW, discoverMethod("getFirstRow"));
				methodMap.put(LASTROW, discoverMethod("getLastRow"));
				methodMap.put(NUMROWS, discoverMethod(CURRENT_PAGE_VERSION==2?"getTotalRows":"getNumRows"));
				methodMap.put(NUMPAGES, discoverMethod(CURRENT_PAGE_VERSION==2?"getTotalPages":"getNumPages"));
				methodMap.put(PAGENUMBER, discoverMethod("getPageNumber"));
				methodMap.put(ROWSPERPAGE, discoverMethod(CURRENT_PAGE_VERSION==2?"getPageSize":"getRowsPerPage"));
				methodsBuffer.put(CURRENT_CLASS_VERSION_NAME, methodMap);
			} else {
				System.err.println("Seda FrameWork not recognized");
				CURRENT_PAGE_VERSION=0;
			}
		}
	}
	

	public PageWrapper(Object paging) {
		this.paging=paging;
		discoverFrameWork();
	}
	
	public int getFirstRow() {
		return executeMethd(methodMap.get(FIRSTROW));
	}
	public int getLastRow() {
		return executeMethd(methodMap.get(LASTROW));
	}
	public int getNumRows() {
		return executeMethd(methodMap.get(NUMROWS));
	}
	public int getNumPages() {
		return executeMethd(methodMap.get(NUMPAGES));
	}
	public int getPageNumber() {
		return executeMethd(methodMap.get(PAGENUMBER));
	}
	public int getRowsPerPage() {
		return executeMethd(methodMap.get(ROWSPERPAGE));
	}
	
	private Method discoverMethod(String name) {
		Method m=null;
		try {
			m=CURRENT_CLASS_VERSION.getMethod(name, EMPTY_SIGNATURE);
		} catch (Exception ignore) {}
		return m;
	}
	
	private int executeMethd(Method m) {
		try {
			return (Integer)m.invoke(paging, EMPTY_ARGUMENTS);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return -1;
	}
	
}

