package com.seda.tag.core;

import java.io.IOException;
import java.util.ArrayList;




import javax.servlet.jsp.JspWriter;

import com.seda.commons.validator.ValidationMessage;


public class Form implements TagRenderInterface {
	
	protected String name = "";
	protected String cssclass = "";
	
	protected String sHtml = "";
	protected boolean bBtn1 = false;
	protected boolean bBtn2 = false;
	protected boolean bBtn3 = false;
	protected String sBtn1OnClick = "";
	protected String sBtn2OnClick = "";
	protected String sBtn3OnClick = "";
	protected String sBtn1Text = "";
	protected String sBtn2Text = "";
	protected String sBtn3Text = "";
	protected String sMethod = "";
	protected String sAction = "";
	protected String sContent;
	protected String CSRFInputHidden="";
	protected String ValidationInputHidden="";
	protected String autocomplete="";
	
	public String getValidationInputHidden() {
		return ValidationInputHidden;
	}

	public void setValidationInputHidden(String validationInputHidden) {
		ValidationInputHidden = validationInputHidden;
	}

	public void setCSRFInputHidden(String cSRFInputHidden) {
		CSRFInputHidden = cSRFInputHidden;
	}

	public String getCSRFInputHidden() {
		return CSRFInputHidden;
	}

	public String getSContent() {
		return sContent;
	}

	public void setSContent(String content) {
		sContent = content;
	}

	protected String sEnctype;

	public String getSEnctype() {
		return sEnctype;
	}

	public void setSEnctype(String enctype) {
		sEnctype = enctype;
	}

	
	public ArrayList<ValidationMessage> ValidatorMessage;

	public boolean hasBtn1Text() {
		return (getBtn1text() != null && getBtn1text().length() > 0);
	}

	public boolean hasBtn2Text() {
		return (getBtn2text() != null && getBtn2text().length() > 0);
	}

	public boolean hasBtn3Text() {
		return (getBtn3text() != null && getBtn3text().length() > 0);
	}

	public void setName(String value) {
		name = value;

	}

	public void setAutocomplete(String value) {
		autocomplete = value;
	}

	public ArrayList<ValidationMessage> getValidatorMessage() {
		return ValidatorMessage;
	}

	public void setValidatorMessage(ArrayList<ValidationMessage> validatorMessage) {
		ValidatorMessage = validatorMessage;
	}

	public String getCssclass() {
		return cssclass;
	}

	public void setCssclass(String cssclass) {
		this.cssclass = cssclass;
	}

	public String getName() {
		return (name);

	}

	public String getAutocomplete() {
		return (autocomplete);

	}
	public void setHasbtn1(boolean value) {
		bBtn1 = value;

	}

	public boolean getHasbtn1() {
		return (bBtn1);

	}

	public void setHasbtn2(boolean value) {
		bBtn2 = value;

	}

	public boolean getHasbtn2() {
		return (bBtn2);

	}

	public void setHasbtn3(boolean value) {
		bBtn3 = value;

	}

	public boolean getHasbtn3() {
		return (bBtn3);

	}

	public void setBtn1onclick(String value) {
		sBtn1OnClick = value;

	}

	public String getBtn1onclick() {
		return (sBtn1OnClick);

	}

	public void setBtn2onclick(String value) {
		sBtn2OnClick = value;
	}

	public String getBtn2onclick() {
		return (sBtn2OnClick);

	}

	public void setBtn3onclick(String value) {
		sBtn3OnClick = value;

	}

	public String getBtn3onclick() {
		return (sBtn3OnClick);

	}

	public void setBtn1text(String value) {
		sBtn1Text = value;

	}

	public String getBtn1text() {
		return (sBtn1Text);

	}

	public void setBtn2text(String value) {
		sBtn2Text = value;

	}

	public String getBtn2text() {
		return (sBtn2Text);

	}

	public void setBtn3text(String value) {
		sBtn3Text = value;

	}

	public String getBtn3text() {
		return (sBtn3Text);

	}

	public void setMethod(String value) {
		sMethod = value;

	}

	public String getMethod() {
		return (sMethod);

	}

	public void setAction(String value) {
		sAction = value;

	}

	public String getAction() {
		return (sAction);

	}

	public boolean hasMethod() {
		return (getMethod() != null && getMethod().length() > 0);
	}

	public boolean hasEnctype() {
		return (getSEnctype() != null && getSEnctype().length() > 0);
	}

	public boolean hasAction() {
		return (getAction() != null && getAction().length() > 0);
	}

	public boolean hasName() {
		return (getName() != null && getName().length() > 0);
	}
	
	public boolean hasAutocomplete() {
		return (getAutocomplete() != null && getAutocomplete().length() > 0);
	}
	

	

	public String hasCss() {
		if (cssclass != null && cssclass.trim() != "")
			return " " + cssclass;
		else
			return "";
	}

	
	public String render() {
		try {
			sHtml = "";
			
			String validatorMessageHtml="";
						
			if (ValidatorMessage != null && ValidatorMessage.size() > 0) {
				for (ValidationMessage message : ValidatorMessage) {
					if (message.getName().equals(""))
						validatorMessageHtml += message.getMessage() + "<br>";
					else
						if (message.isUsermessage())
							validatorMessageHtml += message.getMessage() + "<br>";
						else
							validatorMessageHtml += message.getLabel().replace(":", "") + ": " + message.getMessage() + "<br>";
				}
				sHtml = "<div class=\"seda-ui-divvalidator\">"
						+ "<span class=\"seda-ui-spanvalidator\">"
						+ validatorMessageHtml + "</span></div>";
			}

			sHtml += " <form class=\"seda-ui-form" + hasCss() + "\" ";

			if (hasMethod()) {
				sHtml += "method=\"" + sMethod + "\" ";
			}
			if (hasName()) {
				sHtml += "id=\"" + name + "\" ";
			}
			if (hasEnctype()) {
				sHtml += "enctype=\"" + sEnctype + "\" ";
			}
			if (hasAction()) {
				sHtml += "action=\"" +normalizeHref( sAction) + "\" ";
			}
			if (hasAutocomplete()) {
				sHtml += "autocomplete=\"" + autocomplete + "\" ";
			}
			sHtml += " >";
			
			sHtml += getCSRFInputHidden();
			
			sHtml += getValidationInputHidden();
			
			sHtml += "<div> " + sContent;

			if (bBtn1 || bBtn2 || bBtn3) {
				sHtml += " <div class=\"seda-ui-divbutton\" > ";
				if (bBtn1) {
					sHtml += " <button class=\"seda-ui-button\" name=\"btn1\" onclick=\""
							+ sBtn1OnClick + "\" type=\"button\">";

					if (hasBtn1Text()) {
						sHtml += sBtn1Text + "</button>";
					} else
						sHtml += "Salva</button >";
				}
				if (bBtn2) {
					sHtml += " <button class=\"seda-ui-button\"  name=\"btn2\"  onclick=\""
							+ sBtn2OnClick + "\" type=\"button\">";
					if (hasBtn2Text()) {
						sHtml += sBtn2Text + "</button>";
					} else
						sHtml += "Annulla</button> ";

				}
				if (bBtn3) {
					sHtml += " <button class=\"seda-ui-button\"  name=\"btn3\"  onclick=\""
							+ sBtn3OnClick + "\" type=\"button\">";

					if (hasBtn3Text()) {
						sHtml += sBtn3Text + "</button>";
					} else
						sHtml += "Reset</button> ";

				}
				sHtml += " </div> ";
			}
			sHtml += " </div> </form> ";

		} catch (Exception ex) {
			this.sHtml = "<div class=\"seda-ui-error\" >la Form  non è ben definita</div>";
		}

		return sHtml;

	}

	public void render(JspWriter writer) throws IOException {
		
		Class<? extends Form> clazz = this.getClass();
		
		writer.print(render());

	}
	public String normalizeHref(String sHref)
	{
		
		
		sHref =sHref.replaceAll("&amp;","&");
		sHref = sHref.replaceAll("&", "&amp;");
		
		return sHref;
		
	}
}