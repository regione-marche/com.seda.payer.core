package com.seda.tag.core;

import java.io.IOException;

import javax.servlet.jsp.JspWriter;


public class Cell implements TagRenderInterface   {
  
  public boolean encode =false; 
  public String sContent ="";
  public String sHtml="";
  public String sTextAlign="";
  public String id;
  public String sCss;
  public int iCol=1;
  public int iRow=1;
  public int iWidth;
  public int iHeight;
  
    
    

	
  public String getSTextAlign() {
		return sTextAlign;
	}

	public void setSTextAlign(String textAlign) {
		sTextAlign = textAlign;
	}
   
public int getIHeight() {
	return iHeight;
}

public int getIWidth() {
	return iWidth;
}
	
	public void setId(String name){
		this.id = name;
	}
	
	public void setIrow(int iRspan){
		this.iRow = iRspan;
	}
	public void setIcol(int icol){
		this.iCol = icol;
	}
	public void setWidth(int Width){
		this.iWidth = Width;
	}
	public void setHeight(int Height){
		this.iHeight = Height;
	}
	
	public String getId(){
	return this.id;
	}

	public int getIrow(){
	return this.iRow;
	}
public int getiIcol(){
	return this.iCol;
	}

public String getSCss() {
	return sCss;
}

public void setSCss(String css) {
	sCss = css;
}

public boolean hasHeight(){
	return(getIHeight() >0 );

}
public boolean hasWidth(){
	return(getIWidth() >0 );

}
public boolean hasTextAlign(){
	return(!(sTextAlign.trim().length()==0));
}

public String hasCss()
{
	if (sCss != null && sCss.trim()!="")
		return " "+sCss;
		else
			return"";
}

public String CheckTextAlign(){
if(sTextAlign.toLowerCase().trim()=="center")
	return " center";
else if(sTextAlign.toLowerCase().trim()=="right")
	return " right";
else
	return " left";
}

public String checkId(){
if(id != null && id.trim() != "")	
	return " id=\""+id+"\"";
else
	return"";

}



public void createTag() {
	
    this.sHtml=" <td class=\"seda-ui-cell "+hasCss()+CheckTextAlign()+"\"   colspan=\""+iCol +"\" rowspan=\""+ iRow + " \"";
    this.sHtml+=checkId();
  
    if(hasWidth())
	{this.sHtml+= "width=\""+this.iWidth+"\"";  }
	if(hasHeight())
	{this.sHtml+= "height=\""+this.iHeight+"\" ";  }
	

	this.sHtml +=">" + (encode?encode(this.sContent):this.sContent);
	this.sHtml+=" </td> ";

}


private String encode(String sContent) {
    String appoggio = sContent;
    sContent = HtmlCode.encodeHtml(appoggio);
    return sContent;
}

public String render() {	return this.sHtml;}


public void render(JspWriter writer) throws IOException {writer.print(this.sHtml);}



}