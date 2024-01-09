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
public class AnchorTag extends HTMLAnchorSupport {

	private static final long serialVersionUID = 1L;

	private static final LoggerWrapper logger =  CustomLoggerManager.get(AnchorTag.class);
	
	public int doStartTag() throws JspTagException {
        return EVAL_BODY_BUFFERED;
    }

	public int doEndTag() throws JspTagException {
    	try {
    		// for link button support
    		FormTag formTag	= (FormTag) findAncestorWithClass(this, FormTag.class);
    		
    		String baseHtml = "";
    		
    		StringBuffer html = new StringBuffer();
    		// if the link is disable set the action to the pound symbol
    		if (formTag!=null) {
        		html.append("<a ");    			
    			String savedAction = getAction();
    			if (savedAction!=null) {
            		if (isAutojs()) {
            			setAction("javascript:{}");
            			baseHtml=getBaseHTML();
                		html.append(baseHtml);
                		String functionName = JSUtil.getFunctionName("click", formTag.getName(), getName());
        				String js = JSUtil.getSubmitFunctionScript(functionName, savedAction, formTag.getName());
        				formTag.putJavaScript(js);
        				functionName+="();";
        				html.append(" onclick=\"" + functionName + "\" ");    				
            		} else {
            			if (getOnclick()!=null) {
            				setAction("javascript:{}");
            				baseHtml=getBaseHTML(false);
            				html.append(baseHtml);
            			} else {
            				baseHtml=getBaseHTML(false);
            				html.append(baseHtml);
            			}
            		}
    			} else {
    				setAction("#");
    				baseHtml=getBaseHTML(true);
            		html.append(baseHtml); 
    			}
    		} else {
        		html.append("<a ");    			
        		if (isDisabled()) {
        			if (getOnclick()!=null) setAction("javascript:{}");
        			else setAction("#");
        			baseHtml=getBaseHTML(false);
        		} else {
        			baseHtml=getBaseHTML(true);
        		}
        		html.append(baseHtml); 
    		}
   		
    		html.append("/>");
    		html.append(getText());
    		html.append("</a>");
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
