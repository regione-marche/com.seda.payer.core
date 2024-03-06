/**
 * 
 */
package com.seda.commons.validator;

import java.io.Serializable;

/**
 * @author f.ricci
 *
 */
public class ValidationMessage implements Serializable, Comparable<ValidationMessage> {
	
	private String name;
	private String label;
	private String message;
	private String badvalue;	
	private boolean usermessage=false;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getBadvalue() {
		return badvalue;
	}
	public void setBadvalue(String badvalue) {
		this.badvalue = badvalue;
	}
	
	public boolean isUsermessage() {
		return usermessage;
	}
	public void setUsermessage(boolean usermessage) {
		this.usermessage = usermessage;
	}
	public ValidationMessage() {}
	public ValidationMessage(String name, String label, String message) {
		this.name = name;
		this.label = label;
		this.message = message;
	}
	public ValidationMessage(String name, String label, String message, boolean usermessage) {
		this(name,label,message);
		this.usermessage=usermessage;
	}
	public ValidationMessage(String name, String label, String message, String badValue) {
		this(name,label,message);
		this.badvalue = badValue;
	}		
	public ValidationMessage(String name, String label, String message, String badValue, boolean usermessage) {
		this(name,label,message,badValue);
		this.usermessage=usermessage;
	}	
	@Override
	public String toString() {
		return label + ": " + message;
	}
	
	public int compareTo(ValidationMessage o) {
		return name.compareTo(o.name);
	}
}
