package com.seda.tag.library;

import java.io.IOException;

import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.BodyTagSupport;
import com.seda.tag.core.Button;

public class ButtonTag extends BodyTagSupport {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	Button btn = new Button();

	public void setText(String Value) {
		btn.setSText(Value);
	}
	public void setTitle(String Value) {
		btn.setSTitle(Value);
	}

	
	public void setOnclick(String Value) {
		btn.setSOnClick(Value);
	}

	public void setId(String Value) {
		btn.setSId(Value);
	}

	public void setDisable(boolean Value) {
		btn.setDisable(Value);
	}

	public void setType(String Value) {
		btn.setSType(Value);
	}

	public void setCssclass(String value) {
		btn.setSCss(value);
	}

	public void setTabindex(String value) {
		btn.setSTabIndex(value);
	}
	
	public void setValidate(boolean Value) {
		btn.setValidate(Value);
	}

	public void setName(String name) {
		btn.setSName(name);
	}
	
	public int doStartTag() {

		return SKIP_BODY;
	}

	public int doEndTag() {

		JspWriter out = pageContext.getOut();
		try {

			FormTag formTag    = (FormTag) findAncestorWithClass(this, FormTag.class);
            if (formTag!=null) {
                formTag.putValidationSubmit(btn.getSId(), btn.getSText(), btn.isValidate());
            }

			
			btn.render(out);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return EVAL_PAGE;
	}

}