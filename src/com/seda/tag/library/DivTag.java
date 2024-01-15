package com.seda.tag.library;
import java.io.IOException;

import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.BodyContent;
import javax.servlet.jsp.tagext.BodyTagSupport;

import com.seda.tag.core.Div;
public class DivTag extends BodyTagSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Div div = new Div();
	
	public void setName(String name){
		div.setSName(name);
		
		
	}
	 public void setCssclass(String value){ div.setSCss(value);}
   	public int doAfterBody()
	  {
	    try 
	    { String      bodyString =""; 
	      BodyContent bodyContent = super.getBodyContent();
	      if(bodyContent!= null)
	        bodyString  = bodyContent.getString();
	    
	   
	    	  div.sContent = bodyString;
	     
	    	
	     
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
	JspWriter writer = pageContext.getOut();
	try {
		div.render(writer);
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}

  return EVAL_PAGE;
}
	
}
