package com.seda.tag.core;

import java.io.IOException;

import javax.servlet.jsp.JspWriter;

public class Submit implements TagRenderInterface {
	String sHtml;
	String sText;
	boolean Disable;
	String sOnClick;
	String sId;
	String sCss;
	String sTabIndex;
	boolean validate = true;

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

	public String getSCss() {
		return sCss;
	}

	public void setSCss(String css) {
		sCss = css;
	}

	public boolean hasCss() {
		if (sCss != null && sCss.trim().length() > 0)
			return true;
		else
			return false;
	}

	public String render() {

		try {
			sHtml = "<input type=\"submit\" class=\"";
			sHtml += (hasCss()?getSCss():"seda-ui-button");
			sHtml += "\"";
			if (isDisable())
				sHtml += " disabled=\"true\"";

			if (hasTabIndex()) {
				sHtml += " tabindex=\"" + sTabIndex + "\" ";
			}

			sHtml += "  id=\"" + sId + "\" name=\"" + sId + "\" value=\"" + sText + "\" onclick=\""
					+ sOnClick + "\"/>";

		} catch (Exception ex) {
			sHtml = "<div class=\"seda-ui-error\" >il tag submit non è ben definito</div>";
		}

		return sHtml;
	}

	public void render(JspWriter writer) throws IOException {
		writer.println(render());
	}

}
