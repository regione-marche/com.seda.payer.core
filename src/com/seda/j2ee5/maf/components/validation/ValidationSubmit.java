/**
 * 
 */
package com.seda.j2ee5.maf.components.validation;

import java.io.Serializable;

/**
 * @author f.ricci
 *
 */
public class ValidationSubmit implements Serializable {

	private static final long serialVersionUID = -3665904820372527829L;

	private String name;
	private String value;
    private boolean validate;
    
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public boolean isValidate() {
		return validate;
	}
	public void setValidate(boolean validate) {
		this.validate = validate;
	}
	
	public ValidationSubmit(String name, String value, boolean validate) {
		super();
		this.name = name;
		this.value = value;
		this.validate = validate;
	}
	
	@Override
	public String toString() {
		return "ValidationSubmit [name=" + name + ", validate=" + validate
				+ ", value=" + value + "]";
	}
	
}
