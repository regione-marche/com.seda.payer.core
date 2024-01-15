package com.seda.tag.library;

import java.io.IOException;

import javax.servlet.jsp.tagext.BodyContent;
import javax.servlet.jsp.tagext.BodyTagSupport;
import javax.servlet.jsp.tagext.Tag;

public class CellHeaderTag extends BodyTagSupport {
    
    
	private static final long serialVersionUID = 1L;

	String sHtml="";
    
    com.seda.tag.core.CellHeader Cell = new com.seda.tag.core.CellHeader(); 
	
    
    
	
	public void setId(String name){
		Cell.setId(name) ;
	}
	
	public void setIrow(int iRspan){
		Cell.setIrow(iRspan);
	}
	
	public void setIcol(int icol){
		Cell.setIcol(icol);
	}
	public void setWidth(int Width){
		Cell.setWidth(Width);
	}
	public void setHeight(int Height){
		Cell.setHeight(Height);
	}
	 public void setCssclass(String value){ Cell.setSCss(value);}
	public int doAfterBody()
	  {
	    try 
	    {    
	      BodyContent bodyContent = super.getBodyContent();
	      String      bodyString  = bodyContent.getString();
	    

	     Cell.sContent = bodyString;

	     
	      bodyContent.clear(); // empty buffer for next evaluation
	    }
	    catch (IOException e) 
	    {
	     
	      e.printStackTrace();
	    } // end of catch

	    int retValue = SKIP_BODY;
	    
	   return retValue;
	  }

	



public int doEndTag(){
	Tag t = CellHeaderTag.this.getParent();
	  if(t.getClass() == com.seda.tag.library.RowTag.class )
	   { com.seda.tag.library.RowTag tr = (com.seda.tag.library.RowTag)t ;
	   Cell.createTag();
	   tr.Add(Cell);
	   }
	
	

  return EVAL_PAGE;
}
}

