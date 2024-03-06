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
import com.seda.j2ee5.maf.taglibs.ViewStateManager;

/**
 * @author f.ricci
 *
 */
public class FlowForwarding extends FlowManagerHandler {

	private static final LoggerWrapper logger =  CustomLoggerManager.get(FlowForwarding.class);

	
	public static final String VIEW_KEY = "view";

	public static final String REPLY_PARAMETERS = "reply_parameters";
	public static final String REPLACE_PARAMETERS = "replace_parameters";
	public static final String IGNORE_PARAMETERS = "ignore_parameters";
	
	public static final String REPLY_ATTRIBUTES = "reply_attributes";	
	public static final String REPLACE_ATTRIBUTES = "replace_attributes";
	public static final String IGNORE_ATTRIBUTES = "ignore_attributes";	
	public static final char IGNORE_SEPARATOR = ';';
	
	public static final String SETTINGS = VIEW_KEY + ";" + REPLY_PARAMETERS + ";" +  
	REPLACE_PARAMETERS + ";" + IGNORE_PARAMETERS + ";" +REPLY_ATTRIBUTES + ";" + 
	REPLACE_ATTRIBUTES + ";" + IGNORE_ATTRIBUTES;	
 
	
	boolean encodingParameter = EncodingContext.getInstance().isEncodeParameter();	
	boolean encodingDelete = EncodingContext.getInstance().isEncodeDelete();
	
	private String viewKey;
	
	private boolean replyParameters=false;
	private boolean replaceParameters=false;
	private String[] ignoreParameters;
	
	private boolean replyAttributes=false;
	private boolean replaceAttributes=false;
	private String[] ignoreAttributes;	
	
	
	@Override
	public String process(HttpServletRequest request) throws FlowException {
		if (viewKey==null) {
			logger.info("Missing '"+VIEW_KEY+"' setting");
			return null;
		}
		Object viewStateIdObject =null;
		
		if (encodingParameter)
			viewStateIdObject= request.getAttribute(viewKey);
		
		// try to find it in the parameter map also
		if (!encodingParameter || viewStateIdObject==null)
			viewStateIdObject= request.getParameter(viewKey);
		
		if (!(viewStateIdObject instanceof java.lang.String)){
			logger.info("'"+VIEW_KEY+"' request " +(encodingParameter?"attribute":"parameter")+ " is not a java.lang.String parameter: "+
					(viewStateIdObject==null?"null":viewStateIdObject.getClass().getName()));
			return null;
		}
		
		String viewStateId= (String)viewStateIdObject;

		ViewStateManager viewStateManager = new ViewStateManager(request, viewStateId);
		try {
			if (replyAttributes)
				viewStateManager.replyAttributes(replaceAttributes,ignoreAttributes);
			if (replyParameters)
				viewStateManager.replyParameters(replaceParameters,ignoreParameters);			
		} catch (Exception e) {
			throw new FlowException("Error encountered during reply a previous view state id '" +viewStateId+ "'", e);
		}
		
		return getProperties().containsKey(viewStateId)?getProperties().getProperty(viewStateId):viewStateId;
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
		
		viewKey=getSetting(VIEW_KEY);
		replyParameters=getSettingAsBoolean(REPLY_PARAMETERS);
		replaceParameters=getSettingAsBoolean(REPLACE_PARAMETERS);
		ignoreParameters=getSettingAsStringArray(IGNORE_PARAMETERS,IGNORE_SEPARATOR);
		replyAttributes=getSettingAsBoolean(REPLY_ATTRIBUTES);
		replaceAttributes=getSettingAsBoolean(REPLACE_ATTRIBUTES);
		ignoreAttributes=getSettingAsStringArray(IGNORE_ATTRIBUTES,IGNORE_SEPARATOR);
	}
	
	@Override
	public void end(HttpServletRequest request) {
	}	
	
}
