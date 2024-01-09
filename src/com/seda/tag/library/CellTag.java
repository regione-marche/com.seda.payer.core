package com.seda.tag.library;


import java.io.IOException;

import javax.servlet.jsp.tagext.BodyContent;
import javax.servlet.jsp.tagext.BodyTagSupport;
import javax.servlet.jsp.tagext.Tag;




    public class CellTag extends BodyTagSupport {
        
       
		private static final long serialVersionUID = 1L;

		String sHtml="";
        
        com.seda.tag.core.Cell Cell = new com.seda.tag.core.Cell(); 
		
        
        public void setEncode(boolean encode) {
			Cell.encode = encode;
		}
    	
    	public void setId(String name){
    		Cell.id = name;
    	}
    	 public void setCssclass(String value){ Cell.setSCss(value);}
    	public void setIrow(int iRspan){
    		Cell.iRow = iRspan;
    	}
    	public void setIcol(int icol){
    		Cell.iCol = icol;
    	}
    	public void setWidth(int Width){
    		Cell.iWidth = Width;
    	}
    	public void setHeight(int Height){
    		Cell.iHeight = Height;
    	}
    	public void setTextalign(String textalign){
    	Cell.sTextAlign = textalign;	
    	}
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
    	Tag t = CellTag.this.getParent();
    	  if(t.getClass() == com.seda.tag.library.RowTag.class )
   	   { com.seda.tag.library.RowTag tr = (com.seda.tag.library.RowTag)t ;
   	   Cell.createTag();
   	   tr.Add(Cell);
   	   }
    	
    	
    
	  return EVAL_PAGE;
 }
    }
   
    