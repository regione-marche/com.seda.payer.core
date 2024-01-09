/**
 * 
 */
package com.seda.j2ee5.maf.taglibs;

import java.io.Serializable;

/**
 * This class represents an option for a select tag
 * @author Seda Lab
 *
 */
public class OptionData implements Serializable {

	private static final long serialVersionUID = 1L;
	public String value;
	public String text;
	public String title;
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}

	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public OptionData(String value, String text) {
		this(value,text,text);
	}
	
	public OptionData(String value, String text, String title) {
		this.value = value==null?"n/a":value;
		this.text = text==null?value:text;
		this.title = title==null?text:title;
	}
	
	public String getHTML(String selectedValue) {
		return "<option value=\"" + value + "\"" + 
		(" title=\""+title+"\"") +
		(value.equals(selectedValue)?" selected>":">") +
		text + "</option>";
	}
	@Override
	public String toString() {
		return "OptionData [text=" + text + ", title=" + title + ", value="
				+ value + "]";
	}
	
}
