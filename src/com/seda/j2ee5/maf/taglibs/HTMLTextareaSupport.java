/**
 * 
 */
package com.seda.j2ee5.maf.taglibs;

/**
 * @author Seda Lab
 *
 */
public class HTMLTextareaSupport extends HTMLLabelSupport {

	private static final long serialVersionUID = 1L;
	
	private String content = null;
	
    private String id;
	private String name = null;
	private String cssClass;
	
	private int rows;
	private int cols;

	private int maxlength;
	
	private boolean required;
	private String validation;	
	private String message;	
	
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getHtmlid() {
		return id;
	}
	public void setHtmlid(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCssClass() {
		return cssClass;
	}
	public void setCssClass(String cssClass) {
		this.cssClass = cssClass;
	}
	
	public int getRows() {
		return rows;
	}
	public void setRows(int rows) {
		this.rows = rows;
	}
	public int getCols() {
		return cols;
	}
	public void setCols(int cols) {
		this.cols = cols;
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
	public int getMaxlength() {
		return maxlength;
	}
	
	public void setMaxlength(int maxlength) {
		this.maxlength = maxlength;
	}
	
	public String getBaseHTML() {
    	if (id==null) id=name;
    	return " name=\"" + name + "\"" +
		" id=\"" + id + "\"" +
		(cssClass != null ? (" class=\"" + cssClass + "\"") : "") + 
		(rows > 0 ? (" rows=\"" + rows + "\"") : "") + 
		(cols > 0 ? (" cols=\"" + cols + "\"") : "") 		
		;
    }
	
	public void putValidation(FormTag formTag) {
		if (formTag!=null) {
			StringBuffer validationBuffer = new StringBuffer();
			if (getMaxlength()>0 && !getValidation().contains("maxlenght"))
				validationBuffer.append("maxlength="+getMaxlength()+";");    			
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
	
	public void recycle() {
		super.recycle();
		setContent(null);
		setHtmlid(null);
		setName(null);
		setCssClass(null);
		setRows(0);
		setCols(0);
		setRequired(false);
		setValidation(null);
		setMessage(null);
	}	
	
}
