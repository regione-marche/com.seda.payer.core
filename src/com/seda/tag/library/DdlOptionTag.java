package com.seda.tag.library;

import javax.servlet.jsp.tagext.BodyTagSupport;
import javax.servlet.jsp.tagext.Tag;

import com.seda.tag.core.DdlOption;

public class DdlOptionTag extends BodyTagSupport {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	DdlOption ddlo = new DdlOption();
	
  public void setText(String value){   ddlo.setSText(value);}
  public void setValue(String value){ddlo.setSValue(value);  }
  	
  public int doEndTag(){
  	Tag t = DdlOptionTag.this.getParent();
  	
  	if(t.getClass() == com.seda.tag.library.DropDownListTag.class )
 	   {
  		com.seda.tag.library.DropDownListTag tr = (com.seda.tag.library.DropDownListTag)t ;
 	    tr.Add(ddlo);
 	   }
  	
  	  ddlo = new DdlOption();
	  return EVAL_PAGE;
	}
}