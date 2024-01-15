/**
 * 
 */
package com.seda.j2ee5.maf.taglibs;

import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.tagext.BodyContent;
import javax.servlet.jsp.tagext.BodyTagSupport;

/**
 * The option tag for the select tag
 * @author Seda Lab
 *
 */
public class OptionTag extends BodyTagSupport {

	private static final long serialVersionUID = 1L;

    private String value = "";
    private String title = "";

    public void setValue(String value) { this.value = value; }
    public void setTitle(String title) { this.title=title; }

    private boolean trim=true;
	
	public boolean isTrim() {
		return trim;
	}

	public void setTrim(boolean trim) {
		this.trim = trim;
	}    
    
    public int doAfterBody() throws JspTagException {
    	SelectTag selectTag = (SelectTag) findAncestorWithClass(this, SelectTag.class);
    	BodyContent bc = getBodyContent();
    	String textValue = bc.getString();
    	
    	if (textValue != null) {
			if (isTrim())
				textValue=textValue.trim();
		}
    	String text="n/a";
    	if (textValue!=null && textValue.length()>0)
    		text=textValue;
    	
    	if (selectTag!=null) {
    		selectTag.putOption(new OptionData(value, text, title));
    	}

    	bc.clearBody();
    	recycle();
    	return SKIP_BODY;
    }
	
    private void recycle() {
    	value="";
    	trim=true;
    }
}
