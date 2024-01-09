package com.seda.tag.library;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.BodyTagSupport;
import javax.servlet.jsp.tagext.Tag;

import com.seda.j2ee5.maf.components.encoding.ParameterDiscovery;
import com.seda.tag.core.TextBox;

public class TextBoxTag extends BodyTagSupport {

	private static final long serialVersionUID = 1L;

	TextBox tb = new TextBox();

	public void setName(String value) {
		tb.setName(value);
	}
	 public void setTabindex(String value)
	 {tb.setSTabIndex(value);
	 
	 }
	public void setBmodify(boolean value) {
		tb.setBmodify(value);

	}
	public void setBpassword(boolean value) {
		tb.setBPassword(value);

	}
	public void setSize_mobile(int size) {
		tb.setISize(size);

	}

	public void setLabel(String value) {
		tb.setLabel(value);

	}

	public void setCssclasslabel(String value) {
		tb.setCssclasslabel(value);
	}

	public void setShowrequired(boolean showRequired) {
		tb.setShowRequired(showRequired);
	}	
	
	public void setCssclass(String value) {
		tb.setCssclass(value);

	}

	public void setValidator(String value) {
		tb.setSValidator(value);
	}

	public void setText(String value) {
		tb.setText(value);

	}
	public void setBdisable(boolean value)
	{
		tb.setBDisable(value);
	}
	public void setReadonly(boolean value)
	{
		tb.setBReadonly(value);
	}

	public void setMaxlenght(int value) {
		tb.setMaxlenght(value);

	}
	
	public void setMessage(String value) {
		tb.setsMessage(value);
	}
	
	public void setType(String value) {
		tb.setType(value);
	}
	
	public void setAutocomplete(String value) {
		tb.setAutocomplete(value);

	}
	
	public int doStartTag() {

		return EVAL_BODY_INCLUDE;
	}

	public int doEndTag() {
		JspWriter out = pageContext.getOut();
		
		if (tb.getSValidator()!=null && tb.getSValidator().trim()!="")
		{
		
			Tag t = findAncestorWithClass(this, com.seda.tag.library.FormTag.class);
			if (t != null) {
				com.seda.tag.library.FormTag tr = (com.seda.tag.library.FormTag) t;
				tr.putValidatedField(tb.getName(), tb.getLabel(), tb.getSValidator(), tb.getsMessage());
			}
		}
		
		String selectedValue=ParameterDiscovery.getSafeEncodedParameter((HttpServletRequest)pageContext.getRequest(), tb.getName(),tb.getText());
		if (selectedValue!=null && selectedValue.trim().length() > 0) {
			tb.setText(selectedValue);
		}
		
		/*
        Boolean rejected = (Boolean)pageContext.getRequest().getAttribute(MAFAttributes.VALIDATION_REJECTED);
        if (rejected!=null && rejected) {
                        String val=ParameterDiscovery.getEncodedParameter((HttpServletRequest) pageContext.getRequest(), tb.getName());
                        if (val!=null) {
                                        tb.setText(val);
                        }
        } */             

		try {
			tb.render(out);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			tb = new TextBox();
		}
		return SKIP_BODY;
	}
}
