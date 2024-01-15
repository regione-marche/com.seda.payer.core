/**
 * 
 */
package com.seda.j2ee5.maf.taglibs;

import java.io.IOException;

import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.tagext.BodyContent;
import javax.servlet.jsp.tagext.BodyTagSupport;

import com.seda.commons.logger.CustomLoggerManager;
import com.seda.commons.logger.LoggerWrapper;
import com.seda.j2ee5.maf.util.MAFLogger;
/**
 * @author f.ricci
 *
 */
public class CssTag extends BodyTagSupport {

	private static final long serialVersionUID = 1L;

	private static final LoggerWrapper logger =  CustomLoggerManager.get(CssTag.class);

	
	private String href;
	
	public int doStartTag() throws JspTagException {
        return EVAL_BODY_BUFFERED;
    }
	
	public int doAfterBody() throws JspTagException {
		BodyContent bc = getBodyContent();
		String hrefString = bc.getString();
		if (hrefString != null && hrefString.trim().length()>0) {
			href=hrefString;			
		}

		return skipBody(bc);
	}	
	
	public int doEndTag() throws JspTagException {
    	try {
    		String baseHtml = "<link rel=\"stylesheet\" type=\"text/css\" href=\"".concat(href==null?"#":href).concat("\"/>");
    		pageContext.getOut().print(baseHtml);
    		href=null;
    		return EVAL_PAGE;
    	}
    	catch (IOException e) {
    		logger.error(MAFLogger.getMessage("generic_exception"), e);
    		throw new JspTagException(e.getMessage());
    	}	
    }
	
	public int skipBody(BodyContent bc) {
		bc.clearBody();
		return SKIP_BODY;		
	}	
	
}
