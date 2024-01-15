/**
 * 
 */
package com.seda.j2ee5.maf.core.flow.impl;

import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;

import com.seda.commons.logger.CustomLoggerManager;
import com.seda.commons.logger.LoggerWrapper;
import com.seda.j2ee5.maf.components.encoding.EncodingContext;
import com.seda.j2ee5.maf.core.flow.FlowException;
import com.seda.j2ee5.maf.core.flow.FlowManagerHandler;

/**
 * @author f.ricci
 *
 */
public class FlowChoosing extends FlowManagerHandler {

	private static final LoggerWrapper logger =  CustomLoggerManager.get(FlowChoosing.class);
	
	public static final String CHOOSE_KEY = "choose";
	public static final String DEFAULT_CHOOSE = "default";
	public static final String INTERCEPTOR_CHOOSE = "interceptor";	
	public static final String UNKNOWN_CHOOSE = "unknown";
	
	public static final String SETTINGS = CHOOSE_KEY + ";" + INTERCEPTOR_CHOOSE + ";" + DEFAULT_CHOOSE + ";" + UNKNOWN_CHOOSE;  
	
	boolean encodingParameter = EncodingContext.getInstance().isEncodeParameter();	
	boolean encodingDelete = EncodingContext.getInstance().isEncodeDelete();
	
	private String chooseKey;
	private Class interceptorClass;	
	private InterceptorChoose interceptorChoose;
	private String defaultChoose=DEFAULT_CHOOSE;
	private String unknownChoose=UNKNOWN_CHOOSE;
	
	@Override
	public String process(HttpServletRequest request) throws FlowException {
		String choose=null;

		if (chooseKey==null) {
			logger.info("Missing '"+CHOOSE_KEY+"' setting");
			return null;
		}
		Object chooseObject;
		if (encodingParameter) chooseObject= request.getAttribute(chooseKey);
		else chooseObject= request.getParameter(chooseKey);

		if (chooseObject!=null && !(chooseObject instanceof java.lang.String)){
			logger.info("'"+CHOOSE_KEY+"' request " +(encodingParameter?"attribute":"parameter")+ " is not a java.lang.String parameter: "+
					chooseObject.getClass().getName());
			return null;
		}
		choose=chooseObject==null?defaultChoose:(String)chooseObject;			

		if (interceptorChoose!=null) {
			String modifiedChoose=interceptorChoose.intercept(new String(choose),request);
			if (modifiedChoose!=null)
				choose=modifiedChoose;
		}			

		if (choose!=null) {
			if (!getProperties().containsKey(choose)) {
				return unknownChoose;	
			}
			return getProperties().getProperty(choose);
		}
		return unknownChoose;

	}

	@Override
	public void start(HttpServletRequest request) {
		Enumeration setKeys = getSettingNames();
		while (setKeys.hasMoreElements()) {
			String setKey = (String) setKeys.nextElement();
			if (!SETTINGS.contains(setKey)) {
				logger.warn("'"+setKey+"' is unknown setting");				
			}
		}
		chooseKey=getSetting(CHOOSE_KEY);
		defaultChoose=getSetting(DEFAULT_CHOOSE);
		unknownChoose=getSetting(UNKNOWN_CHOOSE);
		
		if (defaultChoose==null || defaultChoose.trim().length()==0)
			defaultChoose=DEFAULT_CHOOSE;
		
		if (unknownChoose==null || unknownChoose.trim().length()==0)
			unknownChoose=UNKNOWN_CHOOSE;
		
		String interceptorClassName=getSetting(INTERCEPTOR_CHOOSE);
		if (interceptorClassName!=null) {
			try {
				Class interceptorClass = Class.forName(interceptorClassName);
				interceptorChoose = (InterceptorChoose) interceptorClass.newInstance();
			} catch (Exception e) {
				logger.error("Unable to load interceptor class '"+interceptorClassName+"': " + e.getMessage(),e);
			}
		}
	}
	
	@Override
	public void end(HttpServletRequest request) {
	}	
	

}
