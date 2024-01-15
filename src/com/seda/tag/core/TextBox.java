package com.seda.tag.core;

import java.io.IOException;

import javax.servlet.jsp.JspWriter;

public class TextBox implements TagRenderInterface {
	private String text;
	private String label;
	private String cssclass;
	private String cssclasslabel;
	private boolean bPassword;
	private boolean bmodify = true;
	private boolean bDisable = false;
	private boolean bReadonly = false;
	private String name;
	private String sHtml;
	private int maxlenght;
	private String sValidator;
	private String sTabIndex;
	private int iSize = 0;
	private String sMessage;
	private String type;
	private String autocomplete="";
	
	public String getCssclass() {
		return cssclass;
	}

	public void setCssclass(String cssclass) {
		this.cssclass = cssclass;
	}
	
	public String getCssclasslabel() {
		return cssclasslabel;
	}

	public void setCssclasslabel(String cssclasslabel) {
		this.cssclasslabel = cssclasslabel;
	}
	
	public String getsMessage() {
		return sMessage;
	}

	public void setsMessage(String sMessage) {
		this.sMessage = sMessage;
	}

	public boolean isBDisable() {
		return bDisable;
	}

	public void setBDisable(boolean disable) {
		bDisable = disable;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public boolean isBReadonly() {
		return bReadonly;
	}

	public void setBReadonly(boolean readonly) {
		bReadonly = readonly;
	}

	public int getISize() {
		return iSize;
	}

	public void setISize(int size) {
		iSize = size;
	}

	public boolean showRequired = false;

	public void setShowRequired(boolean showRequired) {
		this.showRequired = showRequired;
	}

	public String getSTabIndex() {
		return sTabIndex;
	}

	public void setSTabIndex(String tabIndex) {
		sTabIndex = tabIndex;
	}

	public boolean hasTabIndex() {
		return (sTabIndex != null && sTabIndex.trim().length() > 0);
	}

	public String getSValidator() {
		return sValidator;
	}

	public void setSValidator(String validator) {
		sValidator = validator;
	}

	public void setName(String value) {
		name = value;

	}

	public String getName() {
		return (name);

	}

	public void setBmodify(boolean value) {
		bmodify = value;

	}

	public boolean getBmodify() {
		return (bmodify);

	}

	public void setLabel(String value) {
		label = value;

	}

	public String getLabel() {
		return (label);

	}

	public void setText(String value) {
		text = value;

	}

	public String getText() {
		return (text);

	}

	public void setMaxlenght(int value) {
		maxlenght = value;

	}

	public int getMaxlenght() {
		return (maxlenght);

	}

	public boolean isBPassword() {
		return bPassword;
	}

	public void setBPassword(boolean password) {
		bPassword = password;
	}


	public void setAutocomplete(String value) {
		autocomplete = value;
	}
	
	public String getAutocomplete() {
		return (autocomplete);

	}
	
	public boolean hasName() {
		return (getName() != null && getName().length() > 0);
	}
	
	public boolean hasType() {
		return (getType() != null && getType().length() > 0);
	}

	public boolean hasText() {
		return (getText() != null && getText().length() > 0);
	}

	public boolean hasMaxLenght() {
		return (getMaxlenght() != 0 && getMaxlenght() > 0);
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

	public String hasCssLabel() {
		if (cssclasslabel != null && cssclasslabel.trim() != "")
			return " " + cssclasslabel;
		else
			return "";
	}

	public String render() {
		try {

			if (hasName()) {
				this.sHtml = " <label ";

				this.sHtml += "class=\"seda-ui-label" + hasCssLabel() + "\" ";

				this.sHtml += " for=\"" + name + "\" ";

				this.sHtml += " >" + label;

				if (showRequired && HtmlUtil.isRequired(sValidator)) {
					this.sHtml += HtmlUtil.getRequired();
				}

				this.sHtml += "</label> ";

				this.sHtml += "<input id=\"" + name + "\" name=\"" + name
						+ "\"  class=\"seda-ui-textarea" + hasCss() + " \"";
				if (hasMaxLenght())
					this.sHtml += " maxlength=\"" + maxlenght + "\" ";
				if (hasType())
					this.sHtml += " type=\"" + type + "\" ";

				if (bDisable) {
					this.sHtml += " disabled=\"disabled\"";
				}

				if (bReadonly) {
					this.sHtml += " readonly=\"readonly\"";
				}
				
				if (hasAutocomplete()) {
					sHtml += "autocomplete=\"" + autocomplete + "\"";
				}
				
//				eventuale necessità per bloccare settaggio password da brower: settare bmodify ="false" e usare il un comando simile al seguente
//				if (hasAutocomplete()) {
//					sHtml += "autocomplete=\"" + autocomplete + "\"" + " onfocus=\"this.removeAttribute('readonly');\" ";
//				}
//				
				
				

				if (!bmodify) {
					this.sHtml += " readonly=\"readonly\"";
				}
				if (hasTabIndex()) {
					sHtml += " tabindex=\"" + sTabIndex + "\" ";
				}

				if (iSize > 0)
					sHtml += " size=\"" + iSize + "\" ";
				if (isBPassword())
					this.sHtml += " type=\"password\"";
				if (hasText()) {

					this.sHtml += " value=\"" + text + "\"";

				}

				this.sHtml += "/> ";
			} else
				this.sHtml = "<div class=\"seda-ui-error\" >la text area non è ben definita</div>";

		} catch (Exception ex) {
			this.sHtml = "<div class=\"seda-ui-error\" >la text area non è ben definita</div>";
		}

		return this.sHtml;
	}

	public void render(JspWriter writer) throws IOException {

		writer.print(render());

	}


}
