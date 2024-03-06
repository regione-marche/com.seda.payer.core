/**
 * 
 */
package com.seda.j2ee5.maf.taglibs;

/**
 * @author Seda Lab
 *
 */
public class HTMLInputTextSupport extends HTMLInputSupport {

	private static final long serialVersionUID = 1L;

	private int size;
	private String width;
 
	// support to validation
	private int maxlength;
	private int minlength;
	private boolean required;
	private String validation;
	private String message;
	private String mask;
	
	public int getSize() {
		return size;
	}
	public void setSize(int size) {
		this.size = size;
	}
	public String getWidth() {
		return width;
	}
	public void setWidth(String width) {
		this.width = width;
	}
	public int getMaxlength() {
		return maxlength;
	}
	public void setMaxlength(int maxlength) {
		this.maxlength = maxlength;
	}
	public int getMinlength() {
		return minlength;
	}
	public void setMinlength(int minlength) {
		this.minlength = minlength;
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
	public String getMask() {
		return mask;
	}
	public void setMask(String mask) {
		this.mask = mask;
	}
	
	public void putValidation(FormTag formTag) {
		if (formTag!=null) {
			StringBuffer validationBuffer = new StringBuffer();
			if (getMaxlength()>0 && !getValidation().contains("maxlength"))
				validationBuffer.append("maxlength="+getMaxlength()+";");
			if (getMinlength()>0 && !getValidation().contains("minlength"))
				validationBuffer.append("minlength="+getMinlength()+";");    			
			if (isRequired() && !getValidation().contains("required"))
				validationBuffer.append("required;");
//			if (getValidation()!=null)
			validationBuffer.append(getValidation());

			if (validationBuffer.length()>0) {
    			setValidation(validationBuffer.toString());
    			formTag.putValidationField(getName(), getLabel()==null?getName():getLabel(), getValidation(), getMessage());
			}
		}		
	}
	@Override
	public void recycle() {
		super.recycle();
		setSize(0);
		setWidth(null);		
		setMaxlength(0);
		setMinlength(0);
		setRequired(false);
		setValidation(null);
		setMessage(null);
		setMask(null);
	}
}
