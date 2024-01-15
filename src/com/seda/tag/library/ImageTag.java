package com.seda.tag.library;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.JspWriter;

import com.seda.tag.core.Image;


public class ImageTag extends javax.servlet.jsp.tagext.BodyTagSupport{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	Image img = new Image();

	public void setSrc(String src){
		try {
			img.setSSrc(Utility.buildURL(src, (HttpServletRequest)pageContext.getRequest(), (HttpServletResponse)pageContext.getResponse(), true));
		} catch (JspTagException e) {
			e.printStackTrace();
		}
	}
	public void setAlt(String alt){img.setSAlt(alt);}
	public void setWidth(int width){img.setIWidth(width);}
	public void setHeight(int height){img.setIHeight(height);}
	public void setCssclass(String value){ img.setSCss(value);}
	public int doStartTag() {
		return SKIP_BODY;
	}

	public int doEndTag(){
		JspWriter writer = pageContext.getOut();
		try {
			img.render(writer);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return EVAL_BODY_INCLUDE;
	}



}

