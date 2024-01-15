/**
 * 
 */
package com.seda.j2ee5.maf.taglibs;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
//import java.util.Iterator;
import java.util.Map;

import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.tagext.BodyContent;
import javax.servlet.jsp.tagext.BodyTagSupport;

//import org.apache.commons.codec.binary.Base64;

import com.seda.commons.logger.CustomLoggerManager;
import com.seda.commons.logger.LoggerWrapper;
import com.seda.j2ee5.maf.components.defender.csrf.CSRFContext;
import com.seda.j2ee5.maf.components.defender.csrf.CSRFUtil;
import com.seda.j2ee5.maf.components.validation.ValidationContext;
import com.seda.j2ee5.maf.components.validation.ValidationField;
import com.seda.j2ee5.maf.components.validation.ValidationForm;
import com.seda.j2ee5.maf.components.validation.ValidationSubmit;
import com.seda.j2ee5.maf.components.validation.ValidationUtil;
//import com.seda.j2ee5.maf.util.MAFContext;
import com.seda.j2ee5.maf.util.MAFLogger;

/**
 * Html form tag. Use this with input tag, select tag, check tag, radio tag, and validation manager filter 
 * @author Seda Lab
 *
 */
public class FormTag extends BodyTagSupport {

	private static final long serialVersionUID = 1L;

	private static final LoggerWrapper logger =  CustomLoggerManager.get(FormTag.class);

	
	Map<String, ValidationField> validationFields;
	List<ValidationSubmit> validationSubmits;
	HashMap<String, String> validationUserMessages;

	StringBuffer javaScript;

	private String name;
	private String action;
	private String method;
	private String cssClass;    
	private String formHTML;
	private boolean validation=false;
    
    public void putValidationField(String fieldName, String fieldLabel, String fieldValidation, String message) {
        validationFields.put(fieldName, new ValidationField(fieldName, fieldLabel, fieldValidation));
        if (message!=null && message.trim().length()>0) {
        	validationUserMessages.put(fieldName, message);
        }
    }
    
    public void putValidationSubmit(String submitName, String submitValue, boolean submitValidate) {
        validationSubmits.add(new ValidationSubmit(submitName, submitValue, submitValidate));
    }
    
    public void putJavaScript(StringBuffer javaScript) {
        this.javaScript.append(javaScript);
    }    

    public void putJavaScript(String javaScript) {
        this.javaScript.append(javaScript);
    }    
    
    public void setName(String name) { this.name = name; }
    public String getName() {return name;}
    
    public void setAction(String action) { this.action = action; }

    public void setMethod(String method) { this.method = method; }

    public void setCssClass(String cssClass) { this.cssClass = cssClass; }
    
    public void setValidation(boolean validation) { this.validation = validation; }
    public boolean isValidation() {return this.validation;}
    
    public int doStartTag() throws JspTagException {
    	validationFields = new HashMap<String, ValidationField>();
    	validationSubmits = new ArrayList<ValidationSubmit>();
    	javaScript = new StringBuffer();
        return EVAL_BODY_BUFFERED;
    }

    public int doAfterBody() throws JspTagException {
        BodyContent bc = getBodyContent();
        formHTML = bc.getString();
        bc.clearBody();
        return SKIP_BODY;
    }

    public int doEndTag() throws JspTagException {
    	try {
    		StringBuffer html = new StringBuffer();

    		if (validation) {
    			ValidationUtil.setValidationForm(pageContext.getSession(), new ValidationForm(name, validationFields, validationSubmits, validationUserMessages));
    		}
    		
    		html.append(javaScript.toString());
    		
    		html.append("<form");
    		html.append(" name=\"" + name +"\"");
    		html.append(" id=\"" + name +"\"");
    		html.append(" action=\"" + action + "\"");
    		html.append(" method=\"" + method + "\"");
    		html.append(cssClass != null ? (" class=\"" + cssClass + "\"") : "");            
    		html.append(">");
    		
    		if (CSRFContext.getInstance().isActive()) {
    			String inputHidden = CSRFUtil.getInputHidden(pageContext.getSession());
    			html.append(inputHidden);
    		}
    		
    		if (ValidationContext.getInstance().isActive()) {
    			String inputHidden = ValidationUtil.getInputNameHidden(name);
    			html.append(inputHidden);
    		}

    		html.append(formHTML);
    		html.append("</form>");
    		pageContext.getOut().print(html.toString());
    		return EVAL_PAGE;
    	}
    	catch (IOException e) {
    		logger.error(MAFLogger.getMessage("generic_exception"), e);
    		throw new JspTagException(e.getMessage());
    	}
    }	
	
	public void recycle() {
		name=null;
		action=null;
		method=null;
		cssClass=null;    
		formHTML=null;
		validation=false;
	}
}
