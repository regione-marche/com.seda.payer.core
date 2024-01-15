package com.seda.tag.library;


import javax.servlet.jsp.tagext.BodyTagSupport;
import javax.servlet.jsp.tagext.Tag;
import com.seda.tag.core.Tbody;
import com.seda.tag.core.Row;

public class TbodyTag extends BodyTagSupport {

	private static final long serialVersionUID = 1L;
Tbody tBody = new Tbody();


public void setId(String id)
{
	tBody.setId(id);
}


public void Add(Row tr)
{tBody.Add(tr);

}
 
	 public int doStartTag() {
		 tBody.alTrs.clear();
		 tBody.sContent="";
	    return EVAL_BODY_INCLUDE;
	    }

	    public int doEndTag(){
	    	
	    	if(!tBody.alTrs.isEmpty())
	    	{	Tag t = TbodyTag.this.getParent();
	    	  if(t.getClass() == com.seda.tag.library.TableTag.class )
	   	   { com.seda.tag.library.TableTag table= (com.seda.tag.library.TableTag)t ;
	   	    table.setTBody(tBody);
	   	   }
	    	}
	    return EVAL_PAGE;
	 }
}