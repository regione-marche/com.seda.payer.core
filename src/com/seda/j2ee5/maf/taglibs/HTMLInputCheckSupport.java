/**
 * 
 */
package com.seda.j2ee5.maf.taglibs;

/**
 * @author Seda Lab
 *
 */
public class HTMLInputCheckSupport extends HTMLInputSupport {

	private static final long serialVersionUID = 1L;

    private boolean checked = false;
    private boolean required = false;
	private String validation;    
	private String message;
    
	public boolean isChecked() {
		return checked;
	}
	public void setChecked(boolean checked) {
		this.checked = checked;
	}
	public boolean isRequired() {
		return required;
	}
	public void setRequired(boolean required) {
		this.required = required;
	}

	public String getValidation() {
		return validation==null?"":validation;
	}
	
	public void setValidation(String validation) {
		this.validation = validation;
	}	

	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public void putValidation(FormTag formTag) {
		if (formTag!=null) {
			StringBuffer validationBuffer = new StringBuffer();
			if (isRequired() && !getValidation().contains("retuired"))
				validationBuffer.append("required;");

			if (validationBuffer.length()>0) {
    			setValidation(validationBuffer.toString());
    			formTag.putValidationField(getName(), getLabel()==null?getName():getLabel(), getValidation(), getMessage());
			}
		}		
	}
	
	@Override
	public void recycle() {
		super.recycle();
		setValidation(null);
		setMessage(null);
		setChecked(false);
	    setRequired(false);
	}	
	
}
