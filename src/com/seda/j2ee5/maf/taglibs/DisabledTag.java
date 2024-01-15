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
public class DisabledTag extends BodyTagSupport {

	private static final long serialVersionUID = 1L;
	public int doAfterBody() throws JspTagException {
		HTMLBaseSupport tag = (HTMLBaseSupport) findAncestorWithClass(this, HTMLBaseSupport.class);
		BodyContent bc = getBodyContent();
		String readOnlyValue = bc.getString();
		if (readOnlyValue != null) 
			tag.setDisabled(Boolean.parseBoolean(readOnlyValue.trim()));

		bc.clearBody();
		return SKIP_BODY;
	}
}
