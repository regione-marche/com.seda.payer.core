/**
 * 
 */
package com.seda.j2ee5.maf.components.validation;

import javax.servlet.ServletContext;

import com.seda.j2ee5.maf.util.MAFContext;

/**
 * @author Seda Lab
 *
 */
@Deprecated
public class ValidationConfig {

	private ValidationConfig(){}
	
	public static String getValidationKey(ServletContext context) {
		String validationKey=MAFContext.VALIDATION_KEY_DEFAULT;
		String initValidationKey = context.getInitParameter(MAFContext.VALIDATION_KEY_CONTEXT);
		if (initValidationKey!=null)
			validationKey=initValidationKey;
		return validationKey;
	}
	
	public static String getValidationMessage(ServletContext context) {
		String validationMessage=MAFContext.VALIDATION_MESSAGE_DEFAULT;
		String initValidationMessage = context.getInitParameter(MAFContext.VALIDATION_MESSAGE_CONTEXT);
		if (initValidationMessage!=null)
			validationMessage=initValidationMessage;
		return validationMessage;
	}	
	
	public static boolean isValidationBase64(ServletContext context) {
		boolean validationBase64=MAFContext.VALIDATION_BASE64_DEFAULT;
		String initValidationBase64 = context.getInitParameter(MAFContext.VALIDATION_BASE64_CONTEXT);
		if (initValidationBase64!=null)
			validationBase64=Boolean.parseBoolean(initValidationBase64);
		return validationBase64;
	}
	
	public static boolean isValidation(ServletContext context) {
		boolean validation=MAFContext.VALIDATION_DEFAULT;
		String initValidation = context.getInitParameter(MAFContext.VALIDATION_CONTEXT);
		if (initValidation!=null)
			validation=Boolean.parseBoolean(initValidation);
		return validation;
	}
}
