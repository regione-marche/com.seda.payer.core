package com.seda.tag.library;
 
import java.io.IOException;

import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.BodyTagSupport;

import com.seda.tag.core.Menu;


public class MenuTag extends BodyTagSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
  Menu mMenu = new Menu();
  

  public void setName(String sId){
	  mMenu.setSId(sId);
  }
  public void setCssclass(String value){ mMenu.setSCss(value);}
  public void setVertical(boolean bVertical)
  {mMenu.setBVertical(bVertical);}

  public int doStartTag() {
	  
	     JspWriter writer = pageContext.getOut();
	     mMenu.start=true;
	     try {
			mMenu.render(writer);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	   	return EVAL_BODY_INCLUDE;
	   	
	   }

	   public int doEndTag(){
	   	JspWriter writer = pageContext.getOut();
	   	mMenu.start=false;
	   	try {
				mMenu.render(writer);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	   
		  return EVAL_PAGE;
	}



	}
  
  

