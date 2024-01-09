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
public class TextTag extends BodyTagSupport {

	private static final long serialVersionUID = 1L;
	
	public int doAfterBody() throws JspTagException {
		BodyContent bc = getBodyContent();
		String text = bc.getString();
		// Anchor support
		HTMLAnchorSupport anchorTag = (HTMLAnchorSupport) findAncestorWithClass(this, HTMLAnchorSupport.class);
		if (anchorTag!=null) {
			if (text != null && text.trim().length()>0) {
				anchorTag.setText(text.trim());
			}
		}
		bc.clearBody();
		return SKIP_BODY;		
	}
}
