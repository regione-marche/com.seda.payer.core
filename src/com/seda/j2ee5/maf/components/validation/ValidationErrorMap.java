/**
 * 
 */
package com.seda.j2ee5.maf.components.validation;

import java.io.Serializable;
import java.util.ArrayList;

import com.seda.commons.validator.ValidationMessage;

/**
 * @author Seda Lab
 *
 */
public class ValidationErrorMap implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String form;
	private ArrayList<ValidationMessage> messages;
	public String getForm() {
		return form;
	}
	public void setForm(String form) {
		this.form = form;
	}
	public ArrayList<ValidationMessage> getMessages() {
		return messages;
	}
	public void setMessages(ArrayList<ValidationMessage> messages) {
		this.messages = messages;
	}
	public ValidationErrorMap() {}
	public ValidationErrorMap(String form, ArrayList<ValidationMessage> messages) {
		this.form = form;
		this.messages = messages;
	}
	public ValidationErrorMap(String form, ValidationMessage message) {
		this.form = form;
		this.messages = new ArrayList<ValidationMessage>(1);
		messages.add(message);
	}
	
	@Override
	public String toString() {
		return "ValidationErrorMap [form=" + form + ", messages=" + messages
				+ "]";
	}
}
