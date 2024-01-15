package com.seda.tag.core;

import java.io.IOException;

import javax.servlet.jsp.JspWriter;

public class MenuCachedItem implements TagRenderInterface {
	java.lang.String sTitle;
	java.lang.String sHref;
	java.lang.String sId;
	String sCss;
	java.lang.String sCoords;
    java.lang.String sText;
	java.lang.String sHtml;
	String src;
    boolean bMenu;



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

	public boolean hasSId(){
	return(sId!=null && sId.trim().length() !=0);
	}
	
	public boolean hasSHref(){
		return(sHref!=null&&sHref.trim().length()!=0);
		}
	
	public boolean hasSCss(){
		return(sCss!=null&&sCss.trim().length()!=0);
		}
	
	public boolean hasSTitle(){
		return(sTitle!=null&&sTitle.trim().length()!=0);
		}
	
	
	public boolean hasSCoords(){
		return(sCoords!=null&&sCoords.trim().length()!=0);
		}
	public boolean hasSrc(){
		return(src!=null&&src.trim().length()!=0);
		}
	
	public boolean hasSText(){
		
		return(sText!="");
	}

	public String hasCss()
	{
		if (sCss != null && sCss.trim()!="")
			return " " +sCss;
			else
				return" ";
	}
	
	
	public String render() {
		try{sHtml="";
		
			sHtml+="<li class=\"seda-ui-menuitem" + hasCss()+"\"><a class=\"seda-ui-hlnkmenu"+hasCss()+"\"";
		
		
		
		if(hasSHref())
		{sHtml += " href=\""+sHref+"\"";}
		if(hasSCoords())
		{sHtml += " coords=\""+sCoords+"\"";}
		sHtml +=" >" ;
		
		if(hasSrc())
		{
		sHtml+="<span class=\"seda-ui-lnkspan \">"+sText+ "</span> <img class=\"seda-ui-lnkimg\" alt=\""+sText+"\" src=\""+src+" \" /> " ;
			
		}
		else
		if(hasSText()){
		sHtml += sText;
		}
		
		sHtml +="</a> ";
		
			sHtml+="</li>";
		}
		catch(Exception ex){
			sHtml="<div class=\"seda-ui-error\" >il tag hyperlink non è ben definito</div>"	;
		}
		
		String sEnd = sHtml;
	
	return sEnd;
	
	}


	
	public void render(JspWriter writer) throws IOException {
	
		writer.println(render());
		
	}

}
