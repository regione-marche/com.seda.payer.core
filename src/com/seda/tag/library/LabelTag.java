package com.seda.tag.library;

import java.io.IOException;

import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.BodyTagSupport;


import com.seda.tag.core.Label;;
public class LabelTag extends BodyTagSupport  {

	private static final long serialVersionUID = 1L;
	Label stdLabel = new Label(); 
	
	public void setName(String value){
		stdLabel.sName=value;
		
	}
	public void setCssclass(String value){ stdLabel.setSCss(value);}
	
	public void setText(String value){
		stdLabel.sText=value;
	}
	
	public void setTitle(String value){
		stdLabel.setTitle(value);
	}
	
	public int doStartTag() {
		
		return SKIP_BODY;
	}
	
	public int doEndTag(){
		
		JspWriter out = pageContext.getOut();
		   try {
			
			stdLabel.render(out);
		} catch (IOException e) {
			e.printStackTrace();
		}
	return EVAL_PAGE;	
	}
}

