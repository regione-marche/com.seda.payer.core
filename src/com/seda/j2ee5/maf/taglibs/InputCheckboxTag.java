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
 * HTML checkbox tag. Use with CheckedTag. 
 * @author dbadm
 *
 */
public class InputCheckboxTag extends HTMLInputCheckSupport {

	private static final long serialVersionUID = 1L;

	private static final LoggerWrapper logger =  CustomLoggerManager.get(InputCheckboxTag.class);
	
	private String action;
	boolean onclick=false;
	
	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}		
	
	public boolean isOnclick() {
		return onclick;
	}

	public void setOnclick(boolean onclick) {
		this.onclick = onclick;
	}	
	
    public int doStartTag() throws JspTagException {
        return EVAL_BODY_BUFFERED;
    }

    public int doEndTag() throws JspTagException {
    	setType(HTMLInpuTypes.CHECKBOX);    	
    	try {
    		FormTag formTag = (FormTag) findAncestorWithClass(this, FormTag.class);

    		String baseHtml = getBaseHTML();

    		StringBuffer html = new StringBuffer();
    		html.append("<input ");
    		html.append(baseHtml);
    		html.append(isReadonly() ? (" readonly=\"readonly\"") : "");
    		html.append(isDisabled() ? (" disabled=\"disabled\"") : "");
    		html.append(isChecked() ? (" checked=\"checked\"") : "");
    		
    		// check for on change event in a form
			if (!isReadonly() && isOnclick()) {
				String fn=JSUtil.getSubmitFormCode(null);;
				if (formTag!=null && action!=null) {
					fn = JSUtil.getFunctionName("click", formTag.getName(), getName());
					String js = null;
//					if (formTag.isValidation())
//						js =JSUtil.getSubmitValidationFunctionScript(fn, action, formTag.getName());
//					else 
						js = JSUtil.getSubmitFunctionScript(fn, action, formTag.getName());
					formTag.putJavaScript(js);
					fn+="();";
				} 
				html.append(" onclick=\"" + fn + "\" ");					
			}	

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

	@Override
	public void recycle() {
		super.recycle();
		action=null;
		onclick=false;
	}	
	
    
}
