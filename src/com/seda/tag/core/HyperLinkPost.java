package com.seda.tag.core;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.jsp.JspWriter;

public class HyperLinkPost implements TagRenderInterface{
	
	protected ArrayList<HyperLinkPostParam> alHlpp = new ArrayList<HyperLinkPostParam>();
	protected String text = "";
	protected String href = "";
	protected String tabIndex = "";
	protected String cssClass = "";
	protected String imgSrc = "";
	protected String alt = "";
	protected String name = "";
	protected String onclick = "";
	protected String span="";
	protected String cssSpan = "";
	protected String target="";
	protected Boolean disabled=false;
	
	
	protected String sHtml = "";
	
	protected String CSRFInputHidden="";
	protected String ValidationInputHidden="";
	

	public String getTarget() {
		return target;
	}
	public String getCssSpan() {
		return cssSpan;
	}
	
	public String getSpan() {
		return span;
	}
	
	public String getText() {
		return text;
	}

	public String getHref() {
		return href;
	}

	public String getTabIndex() {
		return tabIndex;
	}

	public String getCssClass() {
		return cssClass;
	}


	public String getImgSrc() {
		return imgSrc;
	}


	public String getAlt() {
		return alt;
	}


	public String getName() {
		return name;
	}


	public String getOnclick() {
		return onclick;
	}
	
	public Boolean getDisabled() {
		return disabled;
	}
	
	public void setTarget(String target) {
		this.target = target;
	}
	
	public void setCssSpan(String cssSpan) {
		this.cssSpan = cssSpan;
	}
	
	public void setSpan(String span) {
		this.span = span;
	}

	public void setText(String text) {
		this.text = text;
	}


	public void setHref(String href) {
		this.href = href;
	}


	public void setTabIndex(String tabIndex) {
		this.tabIndex = tabIndex;
	}


	public void setCssClass(String cssClass) {
		this.cssClass = cssClass;
	}


	public void setImgSrc(String imgSrc) {
		this.imgSrc = imgSrc;
	}


	public void setAlt(String alt) {
		this.alt = alt;
	}


	public void setName(String name) {
		this.name = name;
	}


	public void setOnclick(String onclick) {
		this.onclick = onclick;
	}

	public void setDisabled(Boolean disabled) {
		this.disabled=disabled;
	}

	public void Add(HyperLinkPostParam param){
		alHlpp.add(param);
	}
	
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
	
	
	public String render() {
		
		try {
			sHtml = "";
			sHtml += " <form class=\"seda-ui-form\" ";
			sHtml += "method=\"post\" ";
			
			if (hasTarget()) {
				sHtml += "target=\"" + target + "\" ";
			}
			

			if (hasName()) {
				sHtml += "id=\"frm_" + name + "\" ";
			}else {
				throw new Exception("valorizzare l'attributo name [HyperLinkPost]");
			}
			if (hasHref()) {
				sHtml += "action=\"" +normalizeHref( href) + "\" ";
			}else {
				throw new Exception("valorizzare l'attributo href [HyperLinkPost]");
			}
			sHtml += "style=\"display:inline;\" >";
			
			sHtml += getCSRFInputHidden();
			
			sHtml += getValidationInputHidden();
			
			
			if(alHlpp!=null && alHlpp.size()>0) {
				
				for(HyperLinkPostParam param : alHlpp) {
					
					sHtml += "<input type=\"hidden\" ";
					if (hasParamName(param.getName())) {
						sHtml += "name=\"" + param.getName() + "\" ";
					}else {
						throw new Exception("valorizzare l'attributo name [HyperLinkPostParam]");
					}
					sHtml += "value=\"" + hasParamValue(param.getValue()) + "\" />";
					
				}
				
			}else {
				throw new Exception("Parametri <hlpostparam> obbligatori");
			}
			
			sHtml += "<button type=\"submit\" ";
			sHtml += "id=\""+ name +"\" ";
			sHtml += "class=\"" + hasCss() + "\" ";
		
			
			if (hasOnclick()) {
				sHtml += "onclick=\"" + onclick + "\" ";
			}

			if (hasTabIndex()) {
				sHtml += "tabindex=\"" + tabIndex + "\" ";
			}
			
			if (hasAlt()) {
				sHtml += "title=\"" + alt + "\" ";
			}
			
			if (getDisabled()) {
				sHtml += "disabled ";
			}
			
			sHtml += " >";
			
			if(hasSpan()) {
				sHtml += "<span ";
				
				sHtml += "class=\""+ hasSpanCss() +"\" >";
				
				sHtml += span + "</span> ";
			}
			
			
			if(hasImgSrc()) {
				
				sHtml += "<img src=\""+ imgSrc +"\" ";
				
				if(hasAlt()) {
					sHtml += "alt=\""+ alt +"\" />";
				}
			
			}
			if (hasText()) {
				sHtml +=  text ;
			}
			
			sHtml += "</button>";
			sHtml += "</form>";
			
			
			
		}catch(Exception e) {
			if(!sHtml.startsWith("<div"))
				sHtml = "<div class=\"seda-ui-error\" >Il componente non è ben definito: "+ e.getMessage()+"</div>";
		}
		
		return sHtml;
	}

	private String hasSpanCss() {
		if (cssSpan != null && cssSpan.trim() != "")
			return " " + cssSpan;
		else
			return "";
	}



	public String hasCss() {
		if (cssClass != null && cssClass.trim() != "")
			return " " + cssClass;
		else
			return "";
	}
	
	public boolean hasHref() {
		return (getHref() != null && getHref().length() > 0);
	}

	public boolean hasParamName(String name) {
		return (name != null && name.length() > 0);
	}

	public String hasParamValue(String value) {
		return (value == null ? "" : value);
	}

	public boolean hasName() {
		return (getName() != null && getName().length() > 0);
	}
	
	public boolean hasOnclick() {
		return (getOnclick() != null && getOnclick().length() > 0);
	}
	
	public boolean hasTabIndex() {
		return (getTabIndex() != null && getTabIndex().length() > 0);
	}
	
	public boolean hasText() {
		return (getText() != null && getText().length() > 0);
	}
	
	public boolean hasImgSrc() {
		return (getImgSrc() != null && getImgSrc().length() > 0);
	}
	
	public boolean hasAlt() {
		return (getAlt() != null && getAlt().length() > 0);
	}
	

	public boolean hasSpan() {
		return (getSpan() != null && getSpan().length() > 0);
	}
	
	public boolean hasTarget() {
		return (getTarget() != null && getTarget().length() > 0);
	}
	
	
	public void render(JspWriter writer) throws IOException {

		 writer.println(render());
		
	}
	
	
	public String normalizeHref(String sHref)
	{
		
		
		sHref =sHref.replaceAll("&amp;","&");
		sHref = sHref.replaceAll("&", "&amp;");
		
		return sHref;
		
	}
	
}
