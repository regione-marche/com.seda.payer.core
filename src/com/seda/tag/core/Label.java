package com.seda.tag.core;

import java.io.IOException;


import javax.servlet.jsp.JspWriter;

public class Label implements TagRenderInterface{
   public String sText;
   public String sName;
   public String sHtml;
   public String sCss;
   private String sTitle;


	public void setName(String value){
		sName=value;
		
	}
	public String getName(){
		return(sName);
		
	}
	
	public String getSCss() {
		return sCss;
	}
	public void setSCss(String css) {
		sCss = css;
	}
	public void setText(String value){
		sText=value;
		
	}
	public String getText(){
		return(sText);
		
	}
	public String hasCss()
	{
		if (sCss != null && sCss.trim()!="")
			return " "+sCss;
			else
				return"";
	}
	
	public void setTitle(String sTitle) {
		this.sTitle = sTitle;
	}
	public String getTitle() {
		return sTitle;
	}
	
	private boolean hasTitle()
	{
		return (sTitle != null) && (sTitle.trim().length() > 0);
	}

	public String render() {
	try{	this.sHtml="<span ";
		this.sHtml +="class=\"seda-ui-span"+hasCss()+"\"";
		this.sHtml += hasTitle() ? " title=\"" + getTitle() + "\"" : "";
		this.sHtml += " >";
		this.sHtml += sText+ "</span>";
	}
	catch(Exception ex){
		sHtml="<div class=\"seda-ui-error\" >il tag label non è ben definito</div>"	;
	}
	return this.sHtml;
	}
	
	public void render(JspWriter writer) throws IOException {
		
		writer.print(render());
    	
	}
	
}
