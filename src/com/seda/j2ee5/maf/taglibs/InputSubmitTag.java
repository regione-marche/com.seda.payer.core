/**
 * 
 */
package com.seda.j2ee5.maf.taglibs;

import java.io.IOException;

import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.tagext.BodyContent;

import com.seda.commons.logger.CustomLoggerManager;
import com.seda.commons.logger.LoggerWrapper;
import com.seda.j2ee5.maf.util.MAFLogger;

/**
 * @author Seda Lab
 *
 */
public class InputSubmitTag extends HTMLInputSupport {

	private static final long serialVersionUID = 1L;

	private static final LoggerWrapper logger =  CustomLoggerManager.get(InputSubmitTag.class);
	
	private boolean validate=true;
	private String onclick;
	
	public boolean isValidate() {
		return validate;
	}

	public void setValidate(boolean validate) {
		this.validate = validate;
	}

	public String getOnclick() {
		return onclick;
	}

	public void setOnclick(String onclick) {
		this.onclick = onclick;
	}

	public int doStartTag() throws JspTagException {
        return EVAL_BODY_BUFFERED;
    }

	public int doAfterBody() throws JspTagException {
		// save the content that will be used as value
		BodyContent bc = getBodyContent();
		String value = bc.getString();
		if (value != null && !value.trim().equals("")) {
			setValue(value.trim());
		}
		bc.clearBody();
		return SKIP_BODY;
	}	
	
    public int doEndTag() throws JspTagException {
		setType(HTMLInpuTypes.SUBMIT);    	
    	try {
    		FormTag formTag	= (FormTag) findAncestorWithClass(this, FormTag.class);
    		if (formTag!=null) {
    			formTag.putValidationSubmit(getName(), getValue(), isValidate());
    		}
    			
    		
    		String baseHtml = getBaseHTML();
    		
    		StringBuffer html = new StringBuffer();
    		html.append("<input ");
    		html.append(baseHtml);
    		html.append(getValue() != null ? (" value=\"" + getValue() + "\"") : "");
    		html.append(isReadonly() ? (" readonly=\"readonly\"") : "");
    		html.append(isDisabled() ? (" disabled=\"disabled\"") : "");
    		html.append(getOnclick() != null ? (" onclick=\"" + onclick + "\"") : "");
    		html.append("/>");

    		pageContext.getOut().print(html.toString());
    		
    		recycle();	
    		
    		return EVAL_PAGE;
    	}
    	catch (IOException e) {
    		logger.error(MAFLogger.getMessage("generic_exception"), e);
    		throw new JspTagException(e.getMessage());
    	}	
    }

	@Override
	public void recycle() {
		super.recycle();
		setValidate(true);
	}
    
    
}
