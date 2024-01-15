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
 * @author Seda Lab
 *
 */
public class ThenTag extends BodyTagSupport {

	private static final long serialVersionUID = 1L;

	private static final LoggerWrapper logger =  CustomLoggerManager.get(PagingTag.class);
	
	private String thenHTML;
	
	public int doStartTag() throws JspTagException {
		return EVAL_BODY_BUFFERED;
	}

	public int doAfterBody() throws JspTagException {
		BodyContent bc = getBodyContent();
		thenHTML = bc.getString();
		bc.clearBody();
		return SKIP_BODY;
	}


	public int doEndTag() throws JspTagException {
		IfTag ifTag = (IfTag) findAncestorWithClass(this, IfTag.class);
		
		try {
			if (ifTag!=null && ifTag.getTest()) {
				pageContext.getOut().print(thenHTML.toString());				
			}			
			return EVAL_PAGE;
		}
		catch (IOException e) {
			logger.error(MAFLogger.getMessage("generic_exception"), e);
			throw new JspTagException(e.getMessage());
		}
	}		
	
	
}
