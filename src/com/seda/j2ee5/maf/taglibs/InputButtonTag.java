/**
 * 
 */
package com.seda.j2ee5.maf.taglibs;

import java.io.IOException;

import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.tagext.BodyContent;

import com.seda.commons.logger.CustomLoggerManager;
import com.seda.commons.logger.LoggerWrapper;
import com.seda.j2ee5.maf.components.defender.csrf.CSRFContext;
import com.seda.j2ee5.maf.components.defender.csrf.CSRFUtil;
import com.seda.j2ee5.maf.components.validation.ValidationContext;
import com.seda.j2ee5.maf.components.validation.ValidationUtil;
import com.seda.j2ee5.maf.util.MAFLogger;
/**
 * @author Seda Lab
 *
 */
public class InputButtonTag extends HTMLInputSupport {

	private static final long serialVersionUID = 1L;

	private static final LoggerWrapper logger =  CustomLoggerManager.get(InputButtonTag.class);
	
	private String action;
	private boolean validate=true;
	private String onclick;
	
	public String getAction() {
		return action;
	}
	public void setAction(String action) {
		this.action = action;
	}

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
		setType(HTMLInpuTypes.BUTTON);    	
    	try {
    		FormTag formTag	= (FormTag) findAncestorWithClass(this, FormTag.class);
    		
    		// if is without a parent form change the type from button to submit. This avoid additional javascript.
    		if (formTag==null) 
    			setType(HTMLInpuTypes.SUBMIT);
    		
    		String baseHtml = getBaseHTML();
    		
    		StringBuffer html = new StringBuffer();
    		
    		StringBuffer input = new StringBuffer();
    		input.append("<input ");
    		input.append(baseHtml);
    		input.append(isReadonly() ? (" readonly=\"readonly\"") : "");
    		input.append(isDisabled() ? (" disabled=\"disabled\"") : "");
    		html.append(getOnclick() != null ? (" onclick=\"" + onclick + "\"") : "");    		
    		// if this is out of a form
    		if (formTag==null) {
    			// create the wrapper form
    			StringBuffer form = new StringBuffer();
    			form.append("<form");
    			form.append(" name=\"" + getName().toLowerCase() +"Form\"");
    			form.append(" action=\"" + action + "\"");
    			form.append(" method=\"post\"");
    			form.append(">");
    			
    			if (CSRFContext.getInstance().isActive()) {
        			String inputHidden = CSRFUtil.getInputHidden(pageContext.getSession());
        			form.append(inputHidden);
        		}
    			
        		if (ValidationContext.getInstance().isActive()) {
        			String inputHidden = ValidationUtil.getInputNameHidden(getName().toLowerCase());
        			form.append(inputHidden);
        		}
    			
    			form.append(input).append("/>");  // close input tag
    			form.append("</form>");
    			html.append(form);
    		} else {
    			formTag.putValidationSubmit(getName(), getValue(), isValidate());
    			
    			String functionName = JSUtil.getFunctionName("click", formTag.getName(), getName());
				String js = null;
				js = JSUtil.getSubmitFunctionScript(functionName, action, formTag.getName());
				formTag.putJavaScript(js);
				functionName+="();";
				input.append(" onclick=\"" + functionName + "\" ");
				
				html.append(input).append("/>"); // close input tag
    		}
     			
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
		// TODO Auto-generated method stub
		super.recycle();
		setValidate(true);
	}
	
	
}
