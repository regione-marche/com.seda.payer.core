/**
 * 
 */
package com.seda.j2ee5.maf.taglibs;

/**
 * @author Seda Lab
 *
 */
public class HTMLInputSupport extends HTMLLabelSupport {

	private static final long serialVersionUID = 1L;
	// base input support
	private String name;
    private String id;
    private String type;
    private String cssClass;
	private String value;
	
	private String title;

    public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getHtmlid() {
		return id;
	}
	public void setHtmlid(String id) {
		this.id = id;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getCssClass() {
		return cssClass;
	}
	public void setCssClass(String cssClass) {
		this.cssClass = cssClass;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}

	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}	
	
	public String getBaseHTML() {
    	if (id==null) id=name;
    	return " type=\"" + type + "\"" + 
		" name=\"" + name + "\"" +
		" id=\"" + id + "\"" +
		(title != null ? (" title=\"" + title + "\"") : "") +
		(cssClass != null ? (" class=\"" + cssClass + "\"") : "") +
		(value != null ? (" value=\"" + value + "\"") : "") +
		(getTabindex() >= 0 ? (" tabindex=\"" + getTabindex() + "\"") : "")
		;
    }
	@Override
	public void recycle() {
		super.recycle();
		
		setName(null);
		setHtmlid(null);
		setType(null);
		setCssClass(null);
		setValue(null);
		setTitle(null);
	}
    
	
	
}
