package com.seda.tag.library;

import java.io.IOException;

import com.seda.tag.core.ImageButton;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.BodyTagSupport;
public class ImageButtonTag extends BodyTagSupport {

	private static final long serialVersionUID = 1L;
	ImageButton ibtn = new ImageButton();

	public void setOnclick(String onclick) {
		ibtn.setSOnclick(onclick);
	}
	public void setAlt(String alt) {
		ibtn.setSAlt(alt);
	}
	public void setName(String Name) {
		ibtn.setId(Name);
	}
	public void setImageurl(String imageurl) {
		try {
			ibtn.setSImageUrl(Utility.buildURL(imageurl,(HttpServletRequest)pageContext.getRequest(),(HttpServletResponse)pageContext.getResponse(),true));
		} catch (JspTagException e) {
			e.printStackTrace();
		}
	}

	public void setTabindex(String value){ibtn.setSTabIndex(value);}
	public void setCssclass(String value){ ibtn.setSCss(value);}



	public int doStartTag() {

		return SKIP_BODY;
	}

	public int doEndTag(){

		JspWriter out = pageContext.getOut();
		try {

			ibtn.render(out);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return EVAL_PAGE;}

}
