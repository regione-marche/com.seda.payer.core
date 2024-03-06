package com.seda.tag.library;

import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.tagext.BodyContent;
import javax.servlet.jsp.tagext.BodyTagSupport;

/**
 * Support for the form action. Use with jstl core url tag.
 * @author Seda Lab
 *
 */
public class ActionTag extends BodyTagSupport {

	private static final long serialVersionUID = 1L;

	public int doAfterBody() throws JspTagException {
		BodyContent bc = getBodyContent();
		String actionURL = bc.getString();
		// form tag support
		DatagridTag datagridTag = (DatagridTag) findAncestorWithClass(this, DatagridTag.class);
		if (datagridTag!=null) {
			if (actionURL != null && actionURL.trim().length()>0) 
				datagridTag.setAction(actionURL.trim());			
		}

		bc.clearBody();
		return SKIP_BODY;
	}	
	
}
