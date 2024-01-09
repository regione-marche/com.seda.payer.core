package com.seda.tag.core;

import java.io.IOException;

import javax.servlet.jsp.JspWriter;

public class Button implements TagRenderInterface {
	String sHtml;
	String sText;
	boolean Disable;
	String sType;
	String sOnClick;
	String sId;
	String sCss;
	String sTabIndex;
	String sTitle;
	boolean validate = true;
	String sName;

	public String getSTitle() {
		return sTitle;
	}

	public void setSTitle(String title) {
		sTitle = title;
	}

	public boolean isValidate() {
		return validate;
	}

	public void setValidate(boolean validate) {
		this.validate = validate;
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

	public String getSText() {
		return sText;
	}

	public void setSText(String text) {
		sText = text;
	}

	public String getSOnClick() {
		return sOnClick;
	}

	public void setSOnClick(String onClick) {
		sOnClick = onClick;
	}

	public String getSId() {
		return sId;
	}

	public void setSId(String id) {
		sId = id;
	}

	public boolean isDisable() {
		return Disable;
	}

	public void setDisable(boolean disable) {
		Disable = disable;
	}

	public String getSType() {
		return sType;
	}

	public void setSType(String type) {
		sType = type;
	}

	public String getSCss() {
		return sCss;
	}

	public void setSCss(String css) {
		sCss = css;
	}

	public String getSName() {
		return sName;
	}
	
	public void setSName(String name) {
		sName = name;
	}
	
	public String hasCss() {
		if (sCss != null && sCss.trim() != "")
			return " " + sCss;
		else
			return "";
	}
	

	public String hasTitle() {
		if (sTitle != null && sTitle.trim() != "")
			return " title=\"" + sTitle+"\"";
		else
			return "";
	}
	
	public String hasName() {
		if (sName != null && sName.trim() != "")
			return " name=\"" + sName+"\" ";
		else
			return " name=\"" + sId+"\" ";
	}

	public String render() {

		try {
			sHtml = "<button class=\"seda-ui-button" + hasCss() + "\"";
			if (isDisable())
//				sHtml += " disabled=\"true\"";
				sHtml += " disabled=\"disabled\"";				

			if (hasTabIndex()) {
				sHtml += " tabindex=\"" + sTabIndex + "\" ";
			}

			sHtml += "  id=\"" + sId + "\"" +hasName() + hasTitle()+" value=\"" + sText + "\" onclick=\""
					+ sOnClick + "\"  type=\"" + sType + "\"> " + sText
					+ "</button>";

			if ((sId != null && (sType.equalsIgnoreCase("submit")
					|| sType.equalsIgnoreCase("reset") || sType
					.equalsIgnoreCase("button")))) {
			} else
				sHtml = "<div class=\"seda-ui-error\" >il tag button non è ben definito</div>";
		} catch (Exception ex) {
			sHtml = "<div class=\"seda-ui-error\" >il tag button non è ben definito</div>";
		}

		return sHtml;
	}

	public void render(JspWriter writer) throws IOException {
		writer.print(render());

	}

}
