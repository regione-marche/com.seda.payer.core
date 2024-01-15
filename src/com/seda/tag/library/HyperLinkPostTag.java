package com.seda.tag.library;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.BodyTagSupport;

import com.seda.j2ee5.maf.components.defender.csrf.CSRFContext;
import com.seda.j2ee5.maf.components.defender.csrf.CSRFUtil;
import com.seda.j2ee5.maf.components.validation.ValidationContext;
import com.seda.j2ee5.maf.components.validation.ValidationUtil;
import com.seda.tag.core.HyperLinkPost;
import com.seda.tag.core.HyperLinkPostParam;

public class HyperLinkPostTag extends BodyTagSupport{
	
	private static final long serialVersionUID = 1L;
	
	HyperLinkPost hlp = new HyperLinkPost();
	
	public ArrayList<HyperLinkPostParam> alHlpp = new ArrayList<HyperLinkPostParam>();
	
	public void setText(String value) {
		hlp.setText(value);
	}
	
	public void setHref(String value) {
		try {
			hlp.setHref(Utility.buildURL(value,(HttpServletRequest)pageContext.getRequest(),(HttpServletResponse)pageContext.getResponse()));
		} catch (JspTagException e) {
			e.printStackTrace();
		}
	}
	
	public void setTabindex(String value) {
		hlp.setTabIndex(value);
	}
	
	public void setTarget(String value) {
		hlp.setTarget(value);
	}
	
	public void setCssSpan(String value) {
		hlp.setCssSpan(value);
	}
	
	public void setSpan(String value) {
		hlp.setSpan(value);
	}
	
	public void setCssclass(String value) {
		hlp.setCssClass(value);
	}
	
	public void setImgsrc(String value) {
		hlp.setImgSrc(value);
	}
	
	public void setAlt(String value) {
		hlp.setAlt(value);
	}
	
	public void setName(String value) {
		hlp.setName(value);
	}
	
	public void setOnclick(String value) {
		hlp.setOnclick(value);
	}
	
	public void setDisabled(Boolean value) {
		hlp.setDisabled(value);
	}
	
	public void Add(HyperLinkPostParam param){
		hlp.Add(param);
	}
	
	 public int doEndTag(){
	    	JspWriter writer = pageContext.getOut();
	    	
	    	if (CSRFContext.getInstance().isActive()) {
				String inputHidden = CSRFUtil.getInputHidden(pageContext.getSession());
				hlp.setCSRFInputHidden(inputHidden);
			}
			
			if (ValidationContext.getInstance().isActive()) {
	            String inputHidden = ValidationUtil.getInputNameHidden(hlp.getName());
	            hlp.setValidationInputHidden(inputHidden);
	        }
	    	
	    	try {
	    		hlp.render(writer);
			} catch (IOException e) {
				e.printStackTrace();
			}
	    	hlp = new HyperLinkPost();
	    	
		  return EVAL_PAGE;
	 }
}
