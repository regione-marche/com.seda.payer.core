/**
 * 
 */
package com.seda.j2ee5.maf.taglibs;

import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.tagext.BodyContent;
import javax.servlet.jsp.tagext.BodyTagSupport;

import com.seda.commons.logger.CustomLoggerManager;
import com.seda.commons.logger.LoggerWrapper;
/**
 * @author f.ricci
 *
 */
public class CssClassTag extends BodyTagSupport {

	private static final long serialVersionUID = 1L;

	private static final LoggerWrapper logger =  CustomLoggerManager.get(CssClassTag.class);
	
	public int doStartTag() throws JspTagException {
        return EVAL_BODY_BUFFERED;
    }
	
	public int doAfterBody() throws JspTagException {
		BodyContent bc = getBodyContent();
		String cssClass = bc.getString();

		if (cssClass != null && cssClass.trim().length()>0) {
			// button tag support
			HTMLAnchorSupport acnhorTag = (HTMLAnchorSupport) findAncestorWithClass(this, HTMLAnchorSupport.class);
			if (acnhorTag!=null) {
				acnhorTag.setCssClass(cssClass);
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
