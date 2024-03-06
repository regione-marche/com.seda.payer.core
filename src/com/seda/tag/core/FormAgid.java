package com.seda.tag.core;

import com.seda.commons.validator.ValidationMessage;

public class FormAgid extends Form {
	
	protected String language = "";
	
	public String getLanguage() {
		return (language);
	}
	
	public void setLanguage(String value) {
		language = value;
	}
	
	@Override
	public String render() {
		
		try {
			sHtml = "";
			
			String validatorMessageHtml="";
				
			
			if (ValidatorMessage != null && ValidatorMessage.size() > 0) {
				for (ValidationMessage message : ValidatorMessage) {
					if (message.getName().equals(""))
						validatorMessageHtml += "<li>" + message.getMessage() + "</li>";
					else
						if (message.isUsermessage())
							validatorMessageHtml += "<li>" + message.getMessage() + "</li>";
						else
							validatorMessageHtml += "<li>" + message.getLabel().replace(":", "") + ": " + message.getMessage() + "</li>";
				}
				sHtml = "<div class=\"callout danger\">"
							+ "<div class=\"callout-title\" style=\"top: -1.1em!important;\">"
								+ "<span style=\"border: 0.0629rem solid #d9364f;"
									+ "border-radius: 3.145rem;"
									+ "padding: 0.7548rem;"
									+ "max-height: 1.3838rem;"
									+ "display: inline-block;"
									+ "position: relative;"
									+ "top: 0.28rem;"
									+ "left: -0.1rem;\">"
								+ "</span>"
								+ "<span style=\"position: relative;"
									+ "left: -1.1951rem;"
									+ "top: -0.1887rem;\">"
										+ "x"
								+ "</span>"
								+ "<span style=\""
									+ "position: relative;"
									+ "top: -0.18rem;\">";
				if (language != null && language.equals("de_DE")) {
					sHtml += "Autorisierungsfehler";
				} else {
					sHtml += "Errore di validazione";
				}
				sHtml += "</span>"
							+ "</div>"
							+ validatorMessageHtml 
						+ "</div>";
			}
			
//			if (ValidatorMessage != null && ValidatorMessage.size() > 0) {
//				for (ValidationMessage message : ValidatorMessage) {
//					if (message.getName().equals(""))
//						validatorMessageHtml += message.getMessage() + "<br>";
//					else
//						if (message.isUsermessage())
//							validatorMessageHtml += message.getMessage() + "<br>";
//						else
//							validatorMessageHtml += message.getLabel().replace(":", "") + ": " + message.getMessage() + "<br>";
//				}
//				sHtml = "<div class=\"seda-ui-divvalidator\">"
//						+ "<span class=\"seda-ui-spanvalidator\">"
//						+ validatorMessageHtml + "</span></div>";
//			}

			sHtml += " <form class=\"" + hasCss() + "\" ";

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
				sHtml += " <div class=\"row\" > ";
				if (bBtn1) {
					sHtml += " <button class=\"btn\" name=\"btn1\" onclick=\""
							+ sBtn1OnClick + "\" type=\"button\">";

					if (hasBtn1Text()) {
						sHtml += sBtn1Text + "</button>";
					} else
						sHtml += "Salva</button >";
				}
				if (bBtn2) {
					sHtml += " <button class=\"btn\"  name=\"btn2\"  onclick=\""
							+ sBtn2OnClick + "\" type=\"button\">";
					if (hasBtn2Text()) {
						sHtml += sBtn2Text + "</button>";
					} else
						sHtml += "Annulla</button> ";

				}
				if (bBtn3) {
					sHtml += " <button class=\"btn\"  name=\"btn3\"  onclick=\""
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
			this.sHtml = "<div class=\"callout danger\" >la Form  non è ben definita</div>";
		}

		return sHtml;

	}
}
