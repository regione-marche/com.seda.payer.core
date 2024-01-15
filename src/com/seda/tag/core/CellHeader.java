package com.seda.tag.core;

import java.io.IOException;

import javax.servlet.jsp.JspWriter;




public class CellHeader implements TagRenderInterface  {
	 public String sContent ="";
	  public String sHtml="";
	  public String id;
	  public int iCol=1;
	  public int iRow=1;
	  public int iWidth;
	  public int iHeight;
	  public String sCss; 
	    

		
	    
	public String getSCss() {
		return sCss;
	}

	public void setSCss(String css) {
		sCss = css;
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

	public boolean hasHeight(){
		return(getIHeight() >0 );

	}
	public boolean hasWidth(){
		return(getIWidth() >0 );
		
	}
	public String hasCss()
	{
		if (sCss != null && sCss.trim()!="")
			return sCss;
			else
				return" ";
	}


	public String checkId(){
	if(id != null && id.trim() != "")	
		return " id=\""+id+"\"";
	else
		return"";

	}
	
	public void createTag() {
	    this.sHtml=" <th class=\"seda-ui-headercell "+hasCss() +"\"  colspan=\""+iCol +"\" rowspan=\""+ iRow + " \"";
	    this.sHtml+=checkId();
	    if(hasWidth())
		{this.sHtml+= "width=\""+this.iWidth+"\"";  }
		if(hasHeight())
		{this.sHtml+= "height=\""+this.iHeight+"\" ";  }
		;
		this.sHtml +=">" + this.sContent +"&nbsp;";
		this.sHtml+=" </th> ";

	}


		
		



	public String render() {	return this.sHtml;}


	public void render(JspWriter writer) throws IOException {writer.print(this.sHtml);}



	}
