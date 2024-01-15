package com.seda.tag.core;

import java.io.IOException;

import javax.servlet.jsp.JspWriter;

public class Image implements TagRenderInterface {
	String sHtml;
	public String sSrc;
	public String sAlt;
	public String sCss;
	public int iWidth;
	public int iHeight;

	public String getSCss() {
		return sCss;
	}

	public void setSCss(String css) {
		sCss = css;
	}

	public String getSSrc() {
		return sSrc;
	}

	public void setSSrc(String src) {
		sSrc = src;
	}

	public String getSAlt() {
		return sAlt;
	}

	public void setSAlt(String alt) {
		sAlt = alt;
	}

	public int getIWidth() {
		return iWidth;
	}

	public void setIWidth(int width) {
		iWidth = width;
	}

	public int getIHeight() {
		return iHeight;
	}

	public void setIHeight(int height) {
		iHeight = height;
	}

	public boolean hasIheight() {
		return (iHeight > 0);
	}

	public boolean hasIwidth() {
		return (iWidth > 0);
	}

	public String hasCss() {
		if (sCss != null && sCss.trim() != "")
			return sCss;
		else
			return " ";
	}

	public String render() {
		try {
			sHtml = "<img class=\"seda-ui-image " + sCss + "\" ";
			if (hasIheight())
				sHtml += "height=\"" + iHeight + "\" ";
			if (hasIwidth())
				sHtml += "width=\"" + iWidth + "\" ";
			sHtml += "src=\"" + sSrc + "\" ";
			sHtml += "alt=\"" + sAlt + "\" title=\"" + sAlt + "\" >";
		} catch (Exception ex) {
			sHtml = "<div class=\"seda-ui-error\" >il tag image non è ben definito</div>";
		}
		return sHtml;
	}

	public void render(JspWriter writer) throws IOException {
		writer.print(render());

	}
}