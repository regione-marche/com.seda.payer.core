/**
 * 
 */
package com.seda.j2ee5.maf.taglibs;

import java.io.IOException;

import javax.servlet.jsp.JspTagException;

import com.seda.commons.logger.CustomLoggerManager;
import com.seda.commons.logger.LoggerWrapper;
import com.seda.j2ee5.maf.util.MAFLogger;
/**
 * @author Seda Lab
 *
 */
public class TextareaTag extends HTMLTextareaSupport {

	private static final long serialVersionUID = 1L;
	
	private static final LoggerWrapper logger =  CustomLoggerManager.get(PagingTag.class);
	
	public int doStartTag() throws JspTagException {
		return EVAL_BODY_BUFFERED;
	}

	public int doEndTag() throws JspTagException {
		try {
			FormTag formTag	= (FormTag) findAncestorWithClass(this, FormTag.class);
    		if (!(getMaxlength()>0)) {
    			setMaxlength(getRows()*getCols());
    		}
    		
    		String baseHtml = getBaseHTML();
    		
			StringBuffer html = new StringBuffer();
			html.append("<textarea ");
    		html.append(baseHtml);
    		html.append(isReadonly() ? (" readonly=\"readonly\"") : "");
    		html.append(isDisabled() ? (" disabled=\"disabled\"") : "");    		
    		html.append("/>");
    		
    		html.append(getContent()!=null?getContent():"");
    		
    		html.append("</textarea>");
    		
    		setHTMLLabel(html,getHtmlid(),isRequired());

    		putValidation(formTag);    		

    		pageContext.getOut().print(html.toString());
    		
    		recycle();	
    		
			return EVAL_PAGE;
		}
		catch (IOException e) {
			logger.error(MAFLogger.getMessage("generic_exception"), e);
			throw new JspTagException(e.getMessage());
		}
	}		
}
