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
public class InputPasswordTag extends HTMLInputTextSupport {

	private static final long serialVersionUID = 1L;

	private static final LoggerWrapper logger =  CustomLoggerManager.get(InputPasswordTag.class);

	
	public int doStartTag() throws JspTagException {

        return EVAL_BODY_BUFFERED;
    }
	
	public int doEndTag() throws JspTagException {
		setType(HTMLInpuTypes.PASSWORD);    	
    	try {
    		FormTag formTag	= (FormTag) findAncestorWithClass(this, FormTag.class);
    		
    		String baseHtml = getBaseHTML();
    		
    		StringBuffer html = new StringBuffer();
    		html.append("<input ");
    		html.append(baseHtml);
    		html.append(getWidth() != null ? (" width=\"" + getWidth() + "\"") : "");
    		
    		html.append(getSize() > 0 ? (" size=\"" + getSize() + "\"") : "");
//    		html.append(getValue() != null ? (" value=\"" + getValue() + "\"") : "");
    		html.append(getMaxlength() > 0 ? (" maxlength=\"" + getMaxlength() + "\"") : "");
    		html.append(isReadonly() ? (" readonly=\"readonly\"") : "");
    		html.append(isDisabled() ? (" disabled=\"disabled\"") : "");    		
    		html.append("/>");
    		
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
