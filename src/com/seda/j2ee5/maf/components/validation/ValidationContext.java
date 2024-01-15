/**
 * 
 */
package com.seda.j2ee5.maf.components.validation;

import java.io.Serializable;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;

import com.seda.commons.logger.CustomLoggerManager;
import com.seda.commons.logger.LoggerWrapper;
import com.seda.j2ee5.maf.util.MAFContext;
import com.seda.j2ee5.maf.util.MAFLogger;
/**
 * @author f.ricci
 *
 */
public class ValidationContext {

	private static final LoggerWrapper logger =  CustomLoggerManager.get(ValidationContext.class);

	private Map<String, Serializable> context;
	
	private static ValidationContext me;
	
	static {
    	try {
    		me = new ValidationContext();
    	} catch(Exception se) {
    		logger.error(MAFLogger.getMessage("generic_exception"), se);
    	}
    }
	
	private ValidationContext() {	
		context = Collections.synchronizedMap(new HashMap<String, Serializable>());
	}

	
	public static ValidationContext getInstance() {
		return me;
	}

	public String getValidationKey() {
		return (String)context.get(MAFContext.VALIDATION_KEY_CONTEXT);
	}
	
	public String getValidationFormName() {
		return (String)context.get(MAFContext.VALIDATION_FORM_CONTEXT);
	}
	
	public String getValidationMessage() {
		return (String)context.get(MAFContext.VALIDATION_MESSAGE_CONTEXT);
	}
	@Deprecated
	public boolean isValidationBase64() {
		return (Boolean)context.get(MAFContext.VALIDATION_BASE64_CONTEXT);
	}
	
	public boolean isActive() {
		return (Boolean)context.get(MAFContext.VALIDATION_CONTEXT);
	}
	
	public void initialize(FilterConfig filterConfig) {
		initialize(filterConfig.getServletContext());
	}
	
	public void initialize(ServletContext context) {
		String validationKey=MAFContext.VALIDATION_KEY_DEFAULT;
		String initValidationKey = context.getInitParameter(MAFContext.VALIDATION_KEY_CONTEXT);
		if (initValidationKey!=null)
			validationKey=initValidationKey;
		
		String validationFormName=MAFContext.VALIDATION_FORM_DEFAULT;
		String initValidationFormName = context.getInitParameter(MAFContext.VALIDATION_FORM_CONTEXT);
		if (initValidationFormName!=null)
			validationFormName=initValidationFormName;		
		
		String validationMessage=MAFContext.VALIDATION_MESSAGE_DEFAULT;
		String initValidationMessage = context.getInitParameter(MAFContext.VALIDATION_MESSAGE_CONTEXT);
		if (initValidationMessage!=null)
			validationMessage=initValidationMessage;

		boolean validationBase64=MAFContext.VALIDATION_BASE64_DEFAULT;
		String initValidationBase64 = context.getInitParameter(MAFContext.VALIDATION_BASE64_CONTEXT);
		if (initValidationBase64!=null)
			validationBase64=Boolean.parseBoolean(initValidationBase64);
		
		boolean validation=MAFContext.VALIDATION_DEFAULT;
		String initValidation = context.getInitParameter(MAFContext.VALIDATION_CONTEXT);
		if (initValidation!=null)
			validation=Boolean.parseBoolean(initValidation);
		
		this.context.clear();
		this.context.put(MAFContext.VALIDATION_KEY_CONTEXT,validationKey);
		this.context.put(MAFContext.VALIDATION_FORM_CONTEXT,validationFormName);
		this.context.put(MAFContext.VALIDATION_MESSAGE_CONTEXT,validationMessage);
		this.context.put(MAFContext.VALIDATION_BASE64_CONTEXT,validationBase64);
		this.context.put(MAFContext.VALIDATION_CONTEXT,validation);
	}
	
	
}
