package com.seda.tag.library;

import java.io.IOException;

import com.seda.j2ee5.maf.components.encoding.ParameterDiscovery;
import com.seda.tag.core.TextArea;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.BodyTagSupport;
import javax.servlet.jsp.tagext.Tag;

public class TextAreaTag extends BodyTagSupport {

	private static final long serialVersionUID = 1L;

	TextArea tb = new TextArea();

	public void setTabindex(String value) {
		tb.setSTabIndex(value);
	}

	public void setName(String value) {
		tb.setName(value);
	}

	public void setBmodify(boolean value) {
		tb.setBmodify(value);

	}

	public void setBdisabled(boolean value) {
		tb.setBDisabled(value);
	}

	public void setReadonly(boolean value) {
		tb.setBReadonly(value);
	}

	public void setLabel(String value) {
		tb.setLabel(value);

	}

	public void setCssclasslabel(String value) {
		tb.setCssclasslabel(value);

	}

	public void setCssclass(String value) {
		tb.setCssclass(value);

	}

	public void setShowrequired(boolean showRequired) {
		tb.setShowRequired(showRequired);
	}

	public void setValidator(String value) {
		tb.setSValidator(value);
	}

	public void setText(String value) {
		tb.setText(value);

	}

	public void setcol(int value) {
		tb.setCol(value);

	}

	public void setrow(int value) {
		tb.setRow(value);

	}
	
	public void setMessage(String value) {
		tb.setsMessage(value);
	}

	public int doStartTag() {

		return EVAL_BODY_INCLUDE;
	}

	public int doEndTag() {
		JspWriter out = pageContext.getOut();

		if (tb.getSValidator() != null && tb.getSValidator().trim() != "") {

			Tag t = findAncestorWithClass(this,
					com.seda.tag.library.FormTag.class);
			if (t != null) {
				com.seda.tag.library.FormTag tr = (com.seda.tag.library.FormTag) t;
				tr.putValidatedField(tb.getName(), tb.getLabel(), tb
						.getSValidator(), tb.getsMessage());
			}
		}

		String selectedValue = ParameterDiscovery.getSafeEncodedParameter(
				(HttpServletRequest) pageContext.getRequest(), tb.getName(), tb
						.getText());
		if (selectedValue != null && selectedValue.trim().length() > 0) {
			tb.setText(selectedValue);
		}

		try {
			tb.render(out);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return SKIP_BODY;
	}
}
