package com.seda.tag.core;

import java.io.IOException;

import javax.servlet.jsp.JspWriter;

public class List implements TagRenderInterface {

	String sHtml;
	String sGroupName;
	String sName;
	String sValue;
	String sText;
	String sCss;
	String sCssLabel;
	String sOnClick;
	boolean bDisable;
	boolean bRadio;
	Boolean bChecked;
	private String sValidator;
	String sTabIndex;
	private String sMessage;
	
	

	public String getsMessage() {
		return sMessage;
	}

	public void setsMessage(String sMessage) {
		this.sMessage = sMessage;
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

	public String getSOnClick() {
		return sOnClick;
	}

	public void setSOnClick(String onChange) {
		sOnClick = onChange;
	}

	public String getSValidator() {
		return sValidator;
	}

	public void setSValidator(String validator) {
		sValidator = validator;
	}

	public String getSCss() {
		return sCss;
	}

	public void setSCss(String css) {
		sCss = css;
	}

	public String getSCssLabel() {
		return sCssLabel;
	}

	public void setSCssLabel(String cssLabel) {
		sCssLabel = cssLabel;
	}

	public String getSGroupName() {
		return sGroupName;
	}

	public void setSGroupName(String groupName) {
		sGroupName = groupName;
	}

	public String getName() {
		return sName;
	}

	public void setName(String name) {
		sName = name;
	}

	public boolean isBDisable() {
		return bDisable;
	}

	public void setBDisable(boolean disable) {
		bDisable = disable;
	}

	public String getSValue() {
		return sValue;
	}

	public void setSValue(String value) {
		sValue = value;
	}

	public boolean isBRadio() {
		return bRadio;
	}

	public void setBRadio(boolean radio) {
		bRadio = radio;
	}

	public boolean isBChecked() {
		return bChecked == null ? false : bChecked;
	}

	public Boolean getBChecked() {
		return bChecked;
	}

	public void setBChecked(Boolean checked) {
		bChecked = checked;
	}

	public String getSText() {
		return sText;
	}

	public void setSText(String text) {
		sText = text;
	}

	public boolean hasName() {
		return (sName != null && sName.trim().length() > 0);
	}

	public String hasCss() {
		if (sCss != null && sCss.trim() != "")
			return " " + sCss;
		else
			return "";
	}

	public String hasCssLabel() {
		if (sCssLabel != null && sCssLabel.trim() != "")
			return " " + sCssLabel;
		else
			return "";
	}

	public String hasOnClick() {
		if (sOnClick != null && sOnClick.trim() != "")
			return " onclick=\" " + sOnClick + "\" ";
		else
			return "";
	}

	public String render() {

		try {
			if (hasName()) {
				sHtml = "<input class=\"seda-ui-inputlist" + hasCss() + "\"";
				if (hasName())
					sHtml += " id=\"" + sName + "\"";
				if (isBRadio()) {
					sHtml += " type=\"radio\" ";
				} else {
					sHtml += " type=\"checkbox\" ";
				}
				if (isBDisable()) {
					sHtml += " disabled=\"disabled\" ";
				}
				if (hasTabIndex()) {
					sHtml += " tabindex=\"" + sTabIndex + "\" ";
				}

				sHtml += hasOnClick();

				if (isBChecked()) {
					sHtml += " checked=\"checked\" ";
				}
				sHtml += " name=\"" + sGroupName + "\" value=\"" + sValue
				+ "\" />";
				this.sHtml += "<label ";

				this.sHtml += "class=\"seda-ui-label" + hasCssLabel() + "\"";

				this.sHtml += " for=\"" + sName + "\" ";

				this.sHtml += " >" + sText + "</label> ";
			}

			else
				sHtml = "<div class=\"seda-ui-error\" >il tag List non è ben definito</div>";
		} catch (Exception ex) {
			sHtml = "<div class=\"seda-ui-error\" >il tag List non è ben definito</div>";
		}
		return sHtml;

	}

	public void render(JspWriter writer) throws IOException {
		writer.print(render());
	}

}
