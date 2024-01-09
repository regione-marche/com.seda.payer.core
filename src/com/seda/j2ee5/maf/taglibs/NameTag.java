/**
 * 
 */
package com.seda.j2ee5.maf.taglibs;

import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.tagext.BodyContent;
import javax.servlet.jsp.tagext.BodyTagSupport;

/**
 * The name attribute for input tag
 * @author Seda Lab
 *
 */
public class NameTag extends BodyTagSupport {

	private static final long serialVersionUID = 1L;
	
	public int doAfterBody() throws JspTagException {
		
		BodyContent bc = getBodyContent();
		String body = bc.getString();
		if (body != null && !(body.trim().length()==0)) {
			HTMLInputSupport inputTag = (HTMLInputSupport) findAncestorWithClass(this, HTMLInputSupport.class);
			if (inputTag!=null) {
				inputTag.setName(body.trim());
				return skipBody(bc);
			}
			ViewStateTag viewStateTag = (ViewStateTag) findAncestorWithClass(this, ViewStateTag.class);
			if (viewStateTag!=null) {
				viewStateTag.setName(body.trim());
				return skipBody(bc);
			}			
		}
		return skipBody(bc);
	}
	
	public int skipBody(BodyContent bc) {
		bc.clearBody();
		return SKIP_BODY;		
	}
}
