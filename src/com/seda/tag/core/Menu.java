package com.seda.tag.core;

import java.io.IOException;

import javax.servlet.jsp.JspWriter;


public class Menu implements TagRenderInterface {

	String sHtml;
	String sId;
	String sCss;
	
	public boolean bVertical;
	public boolean isBVertical() {
		return bVertical;
	}
	public void setBVertical(boolean vertical) {
		bVertical = vertical;
	}

	public boolean start;

	
	
	

	public String getSId() {
		return sId;
	}
	public String getSCss() {
		return sCss;
	}
	public void setSCss(String css) {
		sCss = css;
	}
	public void setSId(String id) {
		sId = id;
	}
	
	public String hasCss()
	{
		if (sCss != null && sCss.trim()!="")
			return " "+sCss;
			else
				return"";
	}




	public String render() {
	try{	
		if(start)
			{sHtml= " <ul ";
			if (bVertical)
				sHtml+= "class=\"seda-ui-menuv"+hasCss()+"\" >";
			else
					sHtml+= "class=\"seda-ui-menuo"+hasCss()+"\" >";
	}
		else
	sHtml = "</ul> ";
	
	}
	catch(Exception ex){
		sHtml="<div class=\"seda-ui-error\" >il tag menù non è ben definito</div>"	;
	}
	return sHtml;
	}

	public void render(JspWriter writer) throws IOException {
		writer.print(render());
		
	}

}