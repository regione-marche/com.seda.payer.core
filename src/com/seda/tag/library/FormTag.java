package com.seda.tag.library;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.BodyContent;
import javax.servlet.jsp.tagext.BodyTagSupport;

import com.seda.j2ee5.maf.components.defender.csrf.CSRFContext;
import com.seda.j2ee5.maf.components.defender.csrf.CSRFUtil;
import com.seda.j2ee5.maf.components.validation.ValidationContext;
import com.seda.j2ee5.maf.components.validation.ValidationErrorMap;
import com.seda.j2ee5.maf.components.validation.ValidationField;
import com.seda.j2ee5.maf.components.validation.ValidationForm;
import com.seda.j2ee5.maf.components.validation.ValidationSubmit;
import com.seda.j2ee5.maf.components.validation.ValidationUtil;
import com.seda.tag.core.Form;

public class FormTag extends BodyTagSupport {

	protected static final long serialVersionUID = 1L;
	protected Map<String, ValidationField> validationFields; 
	protected HashMap<String, String> validationUserMessages;
    
	protected List<ValidationSubmit> validationSubmits;
	
	protected Form getForm() {
		return new Form();
	}
	
	protected Form Form = getForm();
	
	public void putValidatedField(String fieldName, String fieldLabel, String fieldValidation, String userValidationMessage) {
		validationFields.put(fieldName, new ValidationField(fieldName, fieldLabel, fieldValidation));

		if (userValidationMessage!=null && userValidationMessage.trim().length()>0) {
            validationUserMessages.put(fieldName, userValidationMessage);
        }

	}
	
	 public void putValidationSubmit(String submitName, String submitValue, boolean submitValidate) {
	        validationSubmits.add(new ValidationSubmit(submitName, submitValue, submitValidate));
	    }


	public void setName(String value) {
		Form.setName(value);

	}

	public String getName() {
		return (Form.getName());

	}

	public void setHasbtn1(boolean value) {
		Form.setHasbtn1(value);

	}

	public boolean getHasbtn1() {
		return (Form.getHasbtn1());

	}

	public void setHasbtn2(boolean value) {
		Form.setHasbtn2(value);

	}

	public boolean getHasbtn2() {
		return (Form.getHasbtn2());

	}

	public void setHasbtn3(boolean value) {
		Form.setHasbtn3(value);

	}

	public boolean getHasbtn3() {
		return (Form.getHasbtn3());

	}

	public void setBtn1onclick(String value) {
		Form.setBtn1onclick(value);

	}

	public String getBtn1onclick() {
		return (Form.getBtn1onclick());

	}

	public void setBtn2onclick(String value) {
		Form.setBtn2onclick(value);
	}

	public String getBtn2onclick() {
		return (Form.getBtn2onclick());

	}

	public void setBtn3onclick(String value) {
		Form.setBtn3onclick(value);

	}

	public String getBtn3onclick() {
		return (Form.getBtn3onclick());

	}

	public void setBtn1text(String value) {
		Form.setBtn1text(value);

	}

	public String getsetBtn1text() {
		return (Form.getBtn1text());

	}

	public void setBtn2text(String value) {
		Form.setBtn2text(value);

	}

	public String getsetBtn2text() {
		return (Form.getBtn2text());

	}

	public void setBtn3text(String value) {
		Form.setBtn3text(value);

	}

	public String getsetBtn3text() {
		return (Form.getBtn3text());

	}
	
	public void setAutocomplete(String value) {
		Form.setAutocomplete(value);

	}

	public String getAutocomplete() {
		return (Form.getAutocomplete());

	}

	public void setMethod(String value) {
		Form.setMethod(value);

	}

	public void setEnctype(String value) {
		Form.setSEnctype(value);
	}

	public String getMethod() {
		return (Form.getMethod());

	}

	public void setAction(String value) {
		
		try {
			Form.setAction(Utility.buildURL(value,(HttpServletRequest)pageContext.getRequest(),(HttpServletResponse)pageContext.getResponse()));
		} catch (JspTagException e) {
			e.printStackTrace();
		}

	}

	public String getAction() {
		return (Form.getAction());

	}

	public void setCssclass(String value) {
		Form.setCssclass(value);
	}
	
	public int doStartTag() throws JspTagException {
		validationFields = new HashMap<String, ValidationField>();
        validationSubmits = new ArrayList<ValidationSubmit>();
        validationUserMessages = new HashMap<String, String>();
        return EVAL_BODY_BUFFERED;
	}

	public int doAfterBody() {
		try {
			BodyContent bodyContent = super.getBodyContent();
			String bodyString = bodyContent.getString();

			Form.setSContent(bodyString);

			bodyContent.clear(); // empty buffer for next evaluation
		} catch (IOException e) {

			e.printStackTrace();
		} // end of catch

		int retValue = SKIP_BODY;

		return retValue;
	}

	public int doEndTag() {

		//ValidationUtil.setValidationBuffer(pageContext.getSession(), new ValidationForm(getName(),validationFields));
		
		ValidationUtil.setValidationForm(pageContext.getSession(), new ValidationForm(getName(), validationFields, validationSubmits, validationUserMessages ));
		
		// request per capire se il submit della form è valido ;
		
		if (pageContext.getRequest().getAttribute(ValidationContext.getInstance().getValidationMessage()) != null) 
		{
			Object object= pageContext.getRequest().getAttribute(ValidationContext.getInstance().getValidationMessage());
			
			if (object instanceof ValidationErrorMap)
			{
				ValidationErrorMap vem=(ValidationErrorMap)pageContext.getRequest().getAttribute(ValidationContext.getInstance().getValidationMessage());
				if (getName().equals(vem.getForm()))
				{
					Form.setValidatorMessage(vem.getMessages());
				}
			}
			else
				System.out.println(object.getClass().getName());
			
		}
		
		if (CSRFContext.getInstance().isActive()) {
			String inputHidden = CSRFUtil.getInputHidden(pageContext.getSession());
			Form.setCSRFInputHidden(inputHidden);
		}
		
		if (ValidationContext.getInstance().isActive()) {
            String inputHidden = ValidationUtil.getInputNameHidden(getName());
            Form.setValidationInputHidden(inputHidden);
        }

		
		/*if (CSRFConfig.isActive(pageContext.getServletContext())) {
			String inputHidden = CSRFUtil.getInputHidden(pageContext.getSession());
			Form.setCSRFInputHidden(inputHidden);
		}*/

		JspWriter out = pageContext.getOut();
		try {
			Form.render(out);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		Form = getForm();
		
		return EVAL_PAGE;
	}
	
}
