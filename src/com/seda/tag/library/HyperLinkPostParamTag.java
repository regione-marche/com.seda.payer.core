package com.seda.tag.library;

import javax.servlet.jsp.tagext.BodyTagSupport;

import com.seda.tag.core.HyperLinkPostParam;

public class HyperLinkPostParamTag extends BodyTagSupport{
	
	private static final long serialVersionUID = 1L;
	
	
	HyperLinkPostParam hlpp = new HyperLinkPostParam();
	
	public void setName(String value) {
		hlpp.setName(value);
	}
	
	public void setValue(String value) {
		hlpp.setValue(value);
	}
	
	public int doEndTag() {
		
		HyperLinkPostTag t = (HyperLinkPostTag) HyperLinkPostParamTag.this.getParent();
//		if (t.getClass() == com.seda.tag.library.HyperLinkPostTag.class) {
//			com.seda.tag.library.HyperLinkPostTag hlp = (com.seda.tag.library.HyperLinkPostTag) t;
//			 t.Add(hlpp);
//		}
		HyperLinkPostParam x = new HyperLinkPostParam();
		x.setName(hlpp.getName());
		x.setValue(hlpp.getValue());
		t.Add(x);
		
		return EVAL_PAGE;
	}

}
