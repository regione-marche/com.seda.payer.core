/**
 * 
 */
package com.seda.j2ee5.maf.components.validation;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * @author Seda Lab
 *
 */
public class ValidationForm implements Serializable {

	private static final long serialVersionUID = 5221872748019551962L;
	
	private String name;
	private Map<String, ValidationField> validationFields;
	private List<ValidationSubmit> validationSubmits;
	private HashMap<String, String> userMessages;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Map<String, ValidationField> getValidationFields() {
		return validationFields;
	}
	public void setValidationFields(Map<String, ValidationField> validationFields) {
		this.validationFields = validationFields;
	}
	
	public HashMap<String, String> getUserMessages() {
		return userMessages;
	}
	public void setUserMessages(HashMap<String, String> userMessages) {
		this.userMessages = userMessages;
	}
	public List<ValidationSubmit> getValidationSubmits() {
		return validationSubmits;
	}
	public void setValidationSubmits(List<ValidationSubmit> validationSubmits) {
		this.validationSubmits = validationSubmits;
	}	
	
	public String getValidationBuffer() {
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
	
	public ValidationForm(String name,
			Map<String, ValidationField> validationFields,
			List<ValidationSubmit> validationSubmits
			) {
		this.name = name;
		this.validationFields = validationFields;
		this.validationSubmits = validationSubmits;
	}

	public ValidationForm(String name,
			Map<String, ValidationField> validationFields,
			List<ValidationSubmit> validationSubmits,
			HashMap<String, String> userMessages
			) {
		this.name = name;
		this.validationFields = validationFields;
		this.validationSubmits = validationSubmits;
		this.userMessages=userMessages;
	}

	@Override
	public String toString() {
		return "ValidationForm [name=" + name + ", userMessages="
				+ userMessages + ", validationFields=" + validationFields
				+ ", validationSubmits=" + validationSubmits + "]";
	}	

}
