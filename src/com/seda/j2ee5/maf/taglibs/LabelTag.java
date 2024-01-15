/**
 * 
 */
package com.seda.j2ee5.maf.taglibs;

import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.tagext.BodyContent;
import javax.servlet.jsp.tagext.BodyTagSupport;

/**
 * @author Seda Lab
 *
 */
public class LabelTag extends BodyTagSupport {

	private static final long serialVersionUID = 1L;

	private String labelClass;
	private String labelPosition="left";
	private boolean labelPrint=true;

	public void setCssClass(String labelClass) {
		this.labelClass = labelClass;
	}
	public void setPosition(String labelPosition) {
		this.labelPosition = labelPosition;
	}
	public void setPrint(boolean labelPrint) {
		this.labelPrint = labelPrint;
	}

	public int doAfterBody() throws JspTagException {
		BodyContent bc = getBodyContent();
		String body = bc.getString();		
		// find the parent input tag or select tag
		HTMLLabelSupport labeledTag = (HTMLLabelSupport) findAncestorWithClass(this, HTMLInputSupport.class);
		if (labeledTag==null) {
			// find the parent text area
			labeledTag = (HTMLTextareaSupport) findAncestorWithClass(this, HTMLTextareaSupport.class);
		}
		if (labeledTag==null) {
			//find the parent select
			labeledTag = (HTMLSelectSupport) findAncestorWithClass(this, HTMLSelectSupport.class);
		}

		if (labeledTag!=null) {
			if (body != null && !body.trim().equals("")) {
				labeledTag.setLabel(body);
				labeledTag.setLabelClass(labelClass);
				labeledTag.setPosition(labelPosition);			
				labeledTag.setLabelPrint(labelPrint);
			}			
		}
		bc.clearBody();
		return SKIP_BODY;
	}
	
}
