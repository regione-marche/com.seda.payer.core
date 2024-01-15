package com.seda.tag.core;

import java.io.IOException;

import javax.servlet.jsp.JspWriter;

public class ImageButton implements TagRenderInterface {

	String sOnclick;
	String sImageUrl;
	String sAlt;
	String sHtml;
	String Id;
	String sCss;
	int iHeight;
	int iWidth;
	String sTabIndex;

	public String getSTabIndex() {
		return sTabIndex;
	}

	public void setSTabIndex(String tabIndex) {
		sTabIndex = tabIndex;
	}

	public boolean hasTabIndex() {
		return (sTabIndex != null && sTabIndex.trim().length() > 0);
	}

	public String getSCss() {
		return sCss;
	}

	public void setSCss(String css) {
		sCss = css;
	}

	public String getSOnclick() {
		return sOnclick;
	}

	public void setSOnclick(String onclick) {
		sOnclick = onclick;
	}

	public String getId() {
		return Id;
	}

	public void setId(String id) {
		Id = id;
	}

	public int getIHeight() {
		return iHeight;
	}

	public void setIHeight(int height) {
		iHeight = height;
	}

	public int getIWidth() {
		return iWidth;
	}

	public void setIWidth(int width) {
		iWidth = width;
	}

	public String getSImageUrl() {
		return sImageUrl;
	}

	public void setSImageUrl(String imageUrl) {
		sImageUrl = imageUrl;
	}

	public String getSAlt() {
		return sAlt;
	}

	public void setSAlt(String alt) {
		sAlt = alt;
	}

	public String hasCss() {
		if (sCss != null && sCss.trim() != "")
			return " " + sCss;
		else
			return "";
	}

	public String render() {
		try {

			sHtml = " <input  id=\"" + Id + "\"  name=\"" + Id + "\" ";

			if (hasTabIndex()) {
				sHtml += "tabindex=\"" + sTabIndex + "\" ";
			}

			sHtml += "class=\"seda-ui-imagebutton" + hasCss()
					+ "\" type=\"image\" onclick=\"" + sOnclick + "\" src=\""
					+ sImageUrl + "\" alt=\"" + sAlt + "\" title=\"" + sAlt + "\" /> ";
		} catch (Exception ex) {
			sHtml = " <div class=\"seda-ui-error\" >il tag Image Button non è ben definito</div> ";
		}
		return sHtml;
	}

	public void render(JspWriter writer) throws IOException {
		writer.print(render());
	}
}