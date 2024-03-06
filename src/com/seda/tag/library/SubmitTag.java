package com.seda.tag.library;

import java.io.IOException;

import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.BodyTagSupport;
import com.seda.tag.core.Submit;

public class SubmitTag extends BodyTagSupport {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	Submit submit = new Submit();

	public void setText(String Value) {
		submit.setSText(Value);
	}

	public void setOnclick(String Value) {
		submit.setSOnClick(Value);
	}

	public void setId(String Value) {
		submit.setSId(Value);
	}

	public void setDisable(boolean Value) {
		submit.setDisable(Value);
	}

	public void setCssclass(String value) {
		submit.setSCss(value);
	}

	public void setTabindex(String value) {
		submit.setSTabIndex(value);
	}
	
	public void setValidate(boolean Value) {
		submit.setValidate(Value);
	}

	public int doStartTag() {

		return SKIP_BODY;
	}

	public int doEndTag() {

		JspWriter out = pageContext.getOut();
		try {

			FormTag formTag    = (FormTag) findAncestorWithClass(this, FormTag.class);
            if (formTag!=null) {
                formTag.putValidationSubmit(submit.getSId(), submit.getSText(), submit.isValidate());
            }

            submit.render(out);
            submit = new Submit();            
		} catch (IOException e) {
			e.printStackTrace();
		}
		return EVAL_PAGE;
	}

}