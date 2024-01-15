package com.seda.tag.core;

import java.io.IOException;

import javax.servlet.jsp.JspWriter;


public class Table  implements TagRenderInterface{
 public Tbody tBody;
 public Theader tHead;
 public String sCss;
 public String sHtml="";
 public String id="";
 public	int iBorder =1;
 public int iCellPadding;
 public int iCellSpacing;
 
	
 public String getId() {
	return id;
}

public void setId(String id) {
	this.id = id;
}

public String getSCss() {
	return sCss;
}

public void setSCss(String css) {
	sCss = css;
}

public Theader getTHead() {
		return tHead;
	}

	public void setTHead(Theader head) {
		tHead = head;
	}
 public int getICellSpacing() {
	return iCellSpacing;
}

public void setICellSpacing(int cellSpacing) {
	iCellSpacing = cellSpacing;
}

public int getICellPadding() {
	return iCellPadding;
}

public void setICellPadding(int cellPadding) {
	iCellPadding = cellPadding;
}

public int getIBorder() {
	return iBorder;
}

public void setIBorder(int border) {
	iBorder = border;
}

public void setTBody(Tbody tbody){
	 tBody =tbody;
 }
 
 public Tbody getTBody() {
		return tBody;
	}
 public boolean hasCellPadding()
 {
	 return(iCellPadding != 0 );
	 
 }
 public boolean hasCellSpacing()
 {
	 return(iCellSpacing != 0 );
	 
 }
 public boolean consistency(){
		return (tBody != null && tHead != null);
		
	}
	 
	 
 public String hasCss()
 {
 	if (sCss != null && sCss.trim()!="")
 		return " "+sCss;
 		else
 			return"";
 }
 public String checkId(){
	 if(id != null && id.trim() != "")	
	 	return " id=\""+id+"\"";
	 else
	 	return"";

	 }


 

public String render() {
	
	 sHtml=" <table class=\"seda-ui-table"+hasCss()+" \"";
	 sHtml+=" border=\""+iBorder +"\"" ;
	 sHtml+=checkId();
	 if(hasCellPadding())
		 sHtml+=" cellpadding =\""+iCellPadding +"\"" ; 
	 if(hasCellSpacing())
		 sHtml+=" cellspacing =\""+iCellSpacing +"\"" ; 
	 sHtml +="> ";
	 if(consistency()) //la tabella non deve essere renderizzata se manca l head o il body
	 {
	 sHtml+=tHead.render();
	 sHtml+= tBody.render();
	 sHtml+=" </table> ";
	 }
	 else {sHtml="<div class=\"seda-ui-error\">Tabella non ben formattata </div>";   }
	 
	 return sHtml;
}


public void render(JspWriter writer) throws IOException {

	 writer.println(render());
	
}

 }