/**
 * 
 */
package com.seda.j2ee5.maf.taglibs;

import java.util.Iterator;
import java.util.List;

/**
 * @author Seda Lab
 *
 */
public class HTMLSelectSupport extends HTMLLabelSupport {

	private static final long serialVersionUID = 1L;
	
	private List<OptionData> options;
	private int size = 0;
	
	private String selectedValue = null;
	
    private String id;
	private String name = null;
	private String cssClass;
	public List<OptionData> getOptions() {
		return options;
	}
	public void setOptions(List<OptionData> options) {
		this.options = options;
	}
	public int getSize() {
		return size;
	}
	public void setSize(int size) {
		this.size = size;
	}
	public String getSelectedValue() {
		return selectedValue;
	}
	public void setSelectedValue(String selectedValue) {
		this.selectedValue = selectedValue;
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

	public String getBaseHTML() {
    	if (id==null) id=name;
    	return " name=\"" + name + "\"" +
		" id=\"" + id + "\"" +
		(cssClass != null ? (" class=\"" + cssClass + "\"") : "") +
		(size > 0 ? (" size=\"" + size + "\"") : "") +
		(getTabindex() >= 0 ? (" tabindex=\"" + getTabindex() + "\"") : "")
		;
    }
	
	public String getSelectedText(String selectedValue) {
		String text=null;
		for (Iterator<OptionData> iterator = options.iterator(); iterator.hasNext();) {
			OptionData o = iterator.next();
			String value = o.getValue();
			if (value.equals(selectedValue)) {
				text=o.getText();
				break;
			}
		}
		return text;
	}
	
	public void recycle() {
		super.recycle();
		setOptions(null);
		setSize(0);
		setSelectedValue(null);
	    setId(null);
		setName(null);
		setCssClass(null);
	}
}
