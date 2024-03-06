package com.seda.tag.library;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.Tag;
import javax.sql.rowset.CachedRowSet;

import com.seda.commons.string.Convert;
import com.seda.j2ee5.maf.components.encoding.ParameterDiscovery;
import com.seda.tag.core.DdlOption;
import com.seda.tag.core.DropDownList;

public class DropDownListTag extends javax.servlet.jsp.tagext.BodyTagSupport {

	private static final long serialVersionUID = 1L;
	DropDownList ddl = new DropDownList();

	
	// public void setText(String sText){
	// ddl.setSText(sText);}
	// public void setValue(String sValue){
	// ddl.setSValue(sValue); }
	
	public void setShowrequired(boolean showRequired) {
		ddl.setShowRequired(showRequired);
	}	
	
	
	public void setLabel(String sLabel) {
		ddl.setSLabel(sLabel);
	}

	public void setTextselected(String valueselected) {
		ddl.setSSelectedText(valueselected);
	}
	public void setReadonly(boolean value)
	{
		ddl.setBReadonly(value);
	}
	public void setValueselected(String valueselected) {
		ddl.setSSelectedValue(valueselected);
	}
	public void setTabindex(String value){ddl.setSTabIndex(value);}
	public void setName(String sId) {
		ddl.setSName(sId);
	}

	public void setUsexml(boolean value) {
		ddl.setBUsexml(value);
	}

	public void setCachedrowset(String cachedRowSet) {
		ddl.setCachedRowSet(cachedRowSet);

	}

	public void setSize(int Size) {
		ddl.setISize(Size);
	}

	public void setDisable(boolean disable) {
		ddl.setBDisable(disable);
	}

	public void setMultiple(boolean multiple) {
		ddl.setBMultiple(multiple);
	}

	public void setCssclass(String value) {
		ddl.setSCss(value);
	}

	public void setCssclasslabel(String value) {
		ddl.setSCssLabel(value);
	}
	public void setOnchange(String value) {
		ddl.setSOnChange(value);
	}
	
	public void setValidator(String value) {
		ddl.setSValidator(value);
	}
	
	public void setMessage(String value) {
		ddl.setsMessage(value);
	}

	public int doEndTag() {
		JspWriter writer = pageContext.getOut();
		
		if (ddl.getSValidator()!=null && ddl.getSValidator().trim()!="")
		{
		
			Tag t = findAncestorWithClass(this, com.seda.tag.library.FormTag.class);
			if (t != null) {
				com.seda.tag.library.FormTag tr = (com.seda.tag.library.FormTag) t;
				tr.putValidatedField(ddl.getSName(), ddl.getSLabel(), ddl.getSValidator(), ddl.getsMessage());
			}
		}
		
		String selectedValue=ParameterDiscovery.getSafeEncodedParameter((HttpServletRequest)pageContext.getRequest(), ddl.getSName(),ddl.getSSelectedValue());
		if (selectedValue!=null && selectedValue.trim().length() > 0) {
			ddl.setSSelectedValue(selectedValue);
		}
		
		/*
		 Boolean rejected = (Boolean)pageContext.getRequest().getAttribute(MAFAttributes.VALIDATION_REJECTED);
	        if (rejected!=null && rejected) {
	                        String val=ParameterDiscovery.getEncodedParameter((HttpServletRequest) pageContext.getRequest(), ddl.getSName());
	                        if (val!=null) {
	                        	ddl.setSSelectedValue(val);
	                        }
	        }
		*/
		
		try {
			if (ddl.getCachedRowSet() != null
					&& ddl.getCachedRowSet().trim() != "") {
				if (ddl.isBUsexml()) {
					if (pageContext.getRequest().getAttribute(
							ddl.getCachedRowSet()) != null)
						ddl.setCRowSet(Convert
								.stringToWebRowSet((String) pageContext
										.getAttribute(ddl.getCachedRowSet(),
												PageContext.REQUEST_SCOPE)));
				} else if (pageContext.getRequest().getAttribute(
						ddl.getCachedRowSet()) != null) {
					ddl.setCRowSet((CachedRowSet) pageContext.getRequest()
							.getAttribute(ddl.getCachedRowSet()));
				} else if (pageContext.getSession().getAttribute(
						ddl.getCachedRowSet()) != null) {
					ddl.setCRowSet((CachedRowSet) pageContext.getSession()
							.getAttribute(ddl.getCachedRowSet()));
				}
			}

			ddl.render(writer);
		} catch (Exception e) {
			try {
				ddl.render(writer);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
				System.out.println((String) pageContext.getAttribute(ddl
						.getCachedRowSet(), PageContext.REQUEST_SCOPE));

			}
			e.printStackTrace();
			System.out.println((String) pageContext.getAttribute(ddl
					.getCachedRowSet(), PageContext.REQUEST_SCOPE));
		}
		ddl = new DropDownList();
		return EVAL_PAGE;
	}

	public void Add(DdlOption dg2) {
		ddl.addOption(dg2);
	}

}