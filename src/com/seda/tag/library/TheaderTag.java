package com.seda.tag.library;

import java.util.ArrayList;

import javax.servlet.jsp.tagext.BodyTagSupport;
import javax.servlet.jsp.tagext.Tag;

import com.seda.tag.core.Row;
import com.seda.tag.core.Theader;
import com.seda.tag.library.RowTag;

public class TheaderTag extends BodyTagSupport {
	
 /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void setId(String id)
	{
		tHead.setId(id);
	}


ArrayList <RowTag> Rows = new ArrayList<RowTag>(); 
 
 

 Theader tHead = new Theader();
 public void Add(Row tr)
 {tHead.Add(tr);

 }
  
 	 public int doStartTag() {
 		tHead.alTrs.clear();
 		tHead.sContent="";
 	    return EVAL_BODY_INCLUDE;
 	    }

 	    public int doEndTag(){
 	    	
 	    	if(!tHead.alTrs.isEmpty())
 	    	{Tag t = TheaderTag.this.getParent();
 	    	  if(t.getClass() == com.seda.tag.library.TableTag.class )
 	   	   { com.seda.tag.library.TableTag table= (com.seda.tag.library.TableTag)t ;
 	   	    table.setTHead(tHead);
 	   	   }}
 	    	  return EVAL_PAGE;
 	 }
}