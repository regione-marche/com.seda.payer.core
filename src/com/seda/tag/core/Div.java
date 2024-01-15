package com.seda.tag.core;

import java.io.IOException;

import javax.servlet.jsp.JspWriter;

public class Div implements TagRenderInterface {
 String sHtml;
 public String sName;
 public String sContent ="" ;
 public String sCss;
 
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
public String hasCss()
{
	if (sCss != null && sCss.trim()!="")
		return " "+sCss;
		else
			return"";
}


	public String render() {
		try{
		sHtml=" <div class=\"seda-ui-div"+hasCss()+"\" id=\""+ sName +"\" > ";
		
		sHtml+=sContent;
		sHtml+=" </div> ";
		}
		catch(Exception ex){
			sHtml=" <div class=\"seda-ui-error\" >il tag div non è ben definito</div> "	;
		}

		return sHtml;
	}


	public void render(JspWriter writer) throws IOException {
	 writer.print(render());
		
	}

}
