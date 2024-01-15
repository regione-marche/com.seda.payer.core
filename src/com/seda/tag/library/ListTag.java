package com.seda.tag.library;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.BodyTagSupport;
import javax.servlet.jsp.tagext.Tag;

import com.seda.j2ee5.maf.components.encoding.ParameterDiscovery;
import com.seda.tag.core.List;

public class ListTag extends BodyTagSupport {

	private static final long serialVersionUID = 1L;
	List lElement = new List();
	String sGroupName;
	String sName;
	String sValue;
	String sOnCLick;
	boolean bRadio;
	Boolean bChecked;

	public void setOnclick(String onClick) {
		lElement.setSOnClick(onClick);
	}

	public void setCssclass(String value) {
		lElement.setSCss(value);
	}

	public void setCssclasslabel(String value) {
		lElement.setSCssLabel(value);
	}

	public void setGroupname(String Value) {
		lElement.setSGroupName(Value);
	}

	public void setName(String Value) {
		lElement.setName(Value);
	}

	public void setText(String Value) {
		lElement.setSText(Value);
	}

	public void setValue(String Value) {
		lElement.setSValue(Value);
	}

	public void setBradio(boolean Value) {
		lElement.setBRadio(Value);
	}

	public void setDisable(boolean Value) {
		lElement.setBDisable(Value);
	}

	public void setValidator(String value) {
		lElement.setSValidator(value);
	}

	public void setBchecked(boolean Value) {
		lElement.setBChecked(Value);
	}

	public void setTabindex(String value) {
		lElement.setSTabIndex(value);
	}

	public void setMessage(String value) {
		lElement.setsMessage(value);
	}
	
	public int doStartTag() {

		return SKIP_BODY;

	}

	public int doEndTag() {
		JspWriter writer = pageContext.getOut();
		try {

			if (lElement.getSValidator() != null
					&& lElement.getSValidator().trim() != "") {

				Tag t = findAncestorWithClass(this,
						com.seda.tag.library.FormTag.class);
				if (t != null) {
					com.seda.tag.library.FormTag tr = (com.seda.tag.library.FormTag) t;
					tr.putValidatedField(lElement.getSGroupName(), lElement.getSText(), lElement.getSValidator(), lElement.getsMessage());
				}
			}

			if (lElement.getBChecked() == null) {
				if (bRadio) {
					String selectedValue = ParameterDiscovery
							.getSafeEncodedParameter(
									(HttpServletRequest) pageContext
											.getRequest(), lElement
											.getSGroupName(), null);
					if (selectedValue != null
							&& selectedValue.trim().length() > 0) {
						lElement.setBChecked(lElement.getSValue().equals(
								selectedValue));
					} else {
						lElement.setBChecked(false);
					}
				} else {
					String[] selectedValues = ParameterDiscovery
							.getSafeEncodedParameterValues(
									(HttpServletRequest) pageContext
											.getRequest(), lElement
											.getSGroupName(), null);
					lElement.setBChecked(false);
					if (selectedValues != null && selectedValues.length > 0) {
						for (int i = 0; i < selectedValues.length; i++) {
							if (lElement.getSValue().equals(selectedValues[i])) {
								lElement.setBChecked(true);
								break;
							}
						}
					}
				}
			}
			// String
			// selectedValue=ParameterDiscovery.getSafeEncodedParameter((HttpServletRequest)pageContext.getRequest(),
			// lElement.getSGroupName(), lElement.isBChecked()?"Y":"");
			// if (selectedValue!=null && selectedValue.trim().length() > 0) {
			// lElement.setBChecked(true);
			// }

			/*
			 * Boolean rejected =
			 * (Boolean)pageContext.getRequest().getAttribute(
			 * MAFAttributes.VALIDATION_REJECTED); if (rejected!=null &&
			 * rejected) { String
			 * val=ParameterDiscovery.getEncodedParameter((HttpServletRequest)
			 * pageContext.getRequest(), lElement.getSGroupName()); if
			 * (val!=null) { lElement.setBChecked(true); }
			 * 
			 * }
			 */

			lElement.render(writer);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			lElement = new List();
		}

		return EVAL_PAGE;
	}

}