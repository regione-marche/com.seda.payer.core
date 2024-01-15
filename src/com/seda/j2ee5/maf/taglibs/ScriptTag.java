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
public class ScriptTag extends BodyTagSupport {

	private static final long serialVersionUID = 1L;

	private static final LoggerWrapper logger =  CustomLoggerManager.get(PagingTag.class);
	
	private String src;
	private String type;

	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}	
	
	public int doStartTag() throws JspTagException {
        return EVAL_BODY_BUFFERED;
    }
	public int doAfterBody() throws JspTagException {
		BodyContent bc = getBodyContent();
		String hrefString = bc.getString();
		if (hrefString != null && hrefString.trim().length()>0) {
			src=hrefString;			
		}

		return skipBody(bc);
	}	
	public int doEndTag() throws JspTagException {
    	try {
    		String baseHtml = "<script type=\"".concat(getType()).concat("\" src=\"").concat(src==null?"#":src).concat("\"></script>");
    		pageContext.getOut().print(baseHtml);
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
