package com.seda.tag.core;

import java.io.IOException;

import javax.servlet.jsp.JspWriter;

public class Hyperlink implements TagRenderInterface {
	String sTitle;
	String sHref;
	String sId;
	String sCss;
	String sAlt;

	String sCoords;
	String sText;
	String sHtml;
	String src;
	boolean bMenu = false;
	String sTabIndex;
	String onclick;
	String sTarget;
	boolean bDisablecsrf = false;

	public String getOnclick() {
		return onclick;
	}

	public void setOnclick(String onclick) {
		this.onclick = onclick;
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

	public String getSAlt() {
		return sAlt;
	}

	public void setSAlt(String alt) {
		sAlt = alt;
	}

	public String getSrc() {
		return src;
	}

	public void setSrc(String src) {
		this.src = src;
	}

	public String getSCss() {
		return sCss;
	}

	public void setSCss(String css) {
		sCss = css;
	}

	public boolean isBMenu() {
		return bMenu;
	}

	public void setBMenu(boolean menu) {
		bMenu = menu;
	}

	public java.lang.String getSText() {
		return sText;
	}

	public void setSText(java.lang.String text) {
		sText = text;
	}

	public java.lang.String getSCoords() {
		return sCoords;
	}

	public void setSCoords(java.lang.String coords) {
		sCoords = coords;
	}

	public java.lang.String getSTitle() {
		return sTitle;
	}

	public void setSTitle(java.lang.String title) {
		sTitle = title;
	}

	public java.lang.String getSHref() {
		return sHref;
	}

	public void setSHref(java.lang.String href) {
		sHref = href;
	}

	public java.lang.String getSId() {
		return sId;
	}

	public void setSId(java.lang.String id) {
		sId = id;
	}

	public void setSTarget(String target) {
		sTarget = target;
	}
	
	public boolean isbDisablecsrf() {
		return bDisablecsrf;
	}

	public void setbDisablecsrf(boolean bDisablecsrf) {
		this.bDisablecsrf = bDisablecsrf;
	}
	
	public boolean hasOnclick() {
		return (this.onclick != null && this.onclick.trim().length() != 0);
	}
	
	public boolean hasSId() {
		return (sId != null && sId.trim().length() != 0);
	}

	public boolean hasSHref() {
		return (sHref != null && sHref.trim().length() != 0);
	}

	public boolean hasSTitle() {
		return (sTitle != null && sTitle.trim().length() != 0);
	}

	public boolean hasSCoords() {
		return (sCoords != null && sCoords.trim().length() != 0);
	}

	public boolean hasSrc() {
		return (src != null && src.trim().length() != 0);
	}

	public boolean hasSText() {

		return (sText != "");
	}

	public boolean hasAlt() {
		return (sAlt != null && sAlt.trim().length() != 0);
	}
	
	public boolean hasTarget() {
		return (sTarget != null && sTarget.trim().length() != 0);
	}

	public String hasCss() {
		if (sCss != null && sCss.trim() != "")
			return " " + sCss;
		else
			return " ";
	}

	public String render() {
		try {
			sHtml = "";
			if (isBMenu())
				sHtml += "<li> <a class=\"seda-ui-hlnkmenu" + hasCss() + "\"";
			else
				sHtml += "<a class=\"seda-ui-hlnk" + hasCss() + "\"";
			
			if (hasSId()) {
				sHtml += " id=\"" + sId + "\"";
			}
			if (hasSId()) {
				sHtml += " name=\"" + sId + "\"";
			}
			if (hasTabIndex()) {
				sHtml += " tabindex=\"" + sTabIndex + "\"";
			}
			if (hasSHref()) {

				sHtml += " href=\"" +normalizeHref(sHref) + "\"";
			}
			
			if (hasAlt())
			{
				sHtml += " title=\"" + sAlt+ "\"" ;
			
			}
			
			if (hasTarget())
			{
//				sHtml += " target=\"" + sTarget+ "\"" ;
				sHtml += "onClick=\"window.open(this.getAttribute('href'), '"+sTarget+"'); return false;\"" ; 
			
			}
			
			if (hasSCoords()) {
				sHtml += " coords=\"" + sCoords + "\"";
			}
			if (hasOnclick()) {
				sHtml += " onclick=\"" + this.onclick + "\"";
			}
			
			sHtml += " >";

			if (hasSrc()) {
				sHtml += "<span class=\"seda-ui-lnkspan \">" + sText
						+ "</span> <img class=\"seda-ui-lnkimg\"";
				if (hasAlt()) {
					sHtml += " alt=\"" + sAlt + "\" src=\"" + src + "\" > ";
				} else {
					sHtml += " alt=\"" + sText + "\" src=\"" + src + " \" > ";
				}

			} else if (hasSText()) {
				sHtml += sText;
			}

			sHtml += "</a>";
			if (isBMenu())
				sHtml += "</li>";
		} catch (Exception ex) {
			sHtml = "<div class=\"seda-ui-error\" >il tag hyperlink non è ben definito</div>";
		}
		return sHtml;
	}

	public void render(JspWriter writer) throws IOException {

		writer.print(render());

	}
	
	public String normalizeHref(String sHref)
	{
		
		
		sHref =sHref.replaceAll("&amp;","&");
		sHref = sHref.replaceAll("&", "&amp;");
		
		return sHref;
		
	}
	
}