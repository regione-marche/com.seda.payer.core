/**
 * 
 */
package com.seda.j2ee5.maf.components.validation;

import java.util.Collections;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.commons.codec.binary.Base64;

import com.seda.j2ee5.maf.util.MAFContext;
import com.seda.j2ee5.maf.util.MAFRequest;

/**
 * @author Seda Lab
 *
 */
public class ValidationUtil {

	private ValidationUtil(){}
	@Deprecated
	public static String getValidationBuffer(Map<String, ValidationField> validationFields) {
		StringBuffer validationBuffer = new StringBuffer();
		if (validationFields!=null) {
			for (Iterator<String> it = validationFields.keySet().iterator(); it.hasNext(); ) {
				String fieldName = it.next();
				ValidationField validation = validationFields.get(fieldName);
				validationBuffer.append(validation.getFieldRules()).append("£");
			}
			if (validationBuffer.length()>0) {
				validationBuffer.deleteCharAt(validationBuffer.length()-1);    				
			}
		}
		return validationBuffer.toString();
	}

	@Deprecated
	public static void setValidationBuffer(HttpSession session, ValidationForm form) {
		String validationBuffer;
		if (ValidationContext.getInstance().isValidationBase64()) {
			validationBuffer = getValidationBufferBase64(form.getValidationFields());
		} else {
			validationBuffer = getValidationBuffer(form.getValidationFields());
		}

		session.setAttribute(form.getName() + ValidationContext.getInstance().getValidationKey()
				, validationBuffer);
	}
	@Deprecated
	private static String getValidationBufferBase64(Map<String, ValidationField> validationFields) {
		String validationBuffer = getValidationBuffer(validationFields);
		String validationBufferBase64 = getValidationBufferBase64(validationBuffer);
		return validationBufferBase64;
	}
	@Deprecated	
	private static String getValidationBufferBase64(String validationBuffer) {
		Base64 codec = new Base64();
		String validationBufferBase64 = new String(codec.encode(validationBuffer.toString().getBytes()));
		return validationBufferBase64;
	}

	public static void cleanValidation(MAFRequest mafRequest, boolean backup) {
		Map<String, Object> validationBackup=null;		
		if (mafRequest.getHttpSession()!=null) {
			if (backup) validationBackup=new HashMap<String, Object>();
			HttpSession session = mafRequest.getHttpSession();
			Enumeration<?> sessionKeys = session.getAttributeNames();
			while (sessionKeys.hasMoreElements()) {
				  String sessionKey = (String)sessionKeys.nextElement();
				  if (sessionKey.endsWith(ValidationContext.getInstance().getValidationKey())) {
					  if (backup) validationBackup.put(sessionKey, session.getAttribute(sessionKey));
					// remove last validation rule for this form
					  session.removeAttribute(sessionKey);
				  }
			}
			if (backup)
				mafRequest.setAttribute(MAFContext.VALIDATION_BACKUP_ATTRIBUTE, validationBackup);
		}
	}
	
	public static void restoreValidation(MAFRequest mafRequest) {
		Map<String, Object> validationBackup=(Map<String, Object>) mafRequest.getAttribute(MAFContext.VALIDATION_BACKUP_ATTRIBUTE);
		if (mafRequest.getHttpSession()!=null && validationBackup!=null) {
			HttpSession session = mafRequest.getHttpSession();
			Enumeration<?> sessionKeys = Collections.enumeration(validationBackup.keySet());
			while (sessionKeys.hasMoreElements()) {
				  String sessionKey = (String)sessionKeys.nextElement();
				  session.setAttribute(sessionKey, validationBackup.get(sessionKey));
			}
		}
	}	

	public static void setValidationForm(HttpSession session, ValidationForm form) {
		session.setAttribute(form.getName() + ValidationContext.getInstance().getValidationKey()
				, form);
	}
	
	public static final String getInputNameHidden(String formName) {
		StringBuffer inputHidden = new StringBuffer();
		inputHidden.append("<input type=\"hidden\"");
		inputHidden.append(getInputValue(formName));
		inputHidden.append("/>");
		return inputHidden.toString();
	}	

	public static final String getInputNameDisplayNone(String formName) {
		StringBuffer inputDisplayNone = new StringBuffer();
		inputDisplayNone.append("<input type=\"text\"");
		inputDisplayNone.append(getInputValue(formName));
		inputDisplayNone.append(" style=\"display: none;\"/>");
		return inputDisplayNone.toString();
	}	
	
	private static final String getInputValue(String formName) {
		return " name=\""+ValidationContext.getInstance().getValidationFormName()+"\"" + 
		(formName!=null? " value=\""+formName+"\"":"");
	}
	
}
