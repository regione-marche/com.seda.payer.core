/**
 * 
 */
package com.seda.j2ee5.maf.taglibs;

import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.tagext.BodyContent;
import javax.servlet.jsp.tagext.BodyTagSupport;

import com.seda.commons.string.HTMLCodec;
import com.seda.j2ee5.maf.util.MAFAttributes;

/**
 * The content of a textarea
 * @author Seda Lab
 *
 */
public class ContentTag extends BodyTagSupport {

	private static final long serialVersionUID = 1L;

	private boolean encodeHTML;	
	
	public boolean isEncodeHTML() {
		return encodeHTML;
	}
	public void setEncode(String encodeHTML) {
		this.encodeHTML = Boolean.parseBoolean(encodeHTML);
	}
	
	public int doAfterBody() throws JspTagException {
		HTMLTextareaSupport tag = (HTMLTextareaSupport) findAncestorWithClass(this, HTMLTextareaSupport.class);
		BodyContent bc = getBodyContent();
		String content = bc.getString();
		
		Boolean rejected = (Boolean)pageContext.getRequest().getAttribute(MAFAttributes.VALIDATION_REJECTED);
		if (rejected!=null && rejected) {
			String val = pageContext.getRequest().getParameter(tag.getName());
			if (val!=null) {
				content=val;
			}
		}
		if (content != null) {
			content=content.trim();
			if (isEncodeHTML())
				content = HTMLCodec.encodeHtml(content);
			tag.setContent(content);			
		}		
		
		bc.clearBody();
		return SKIP_BODY;
	}	
	
	
}
