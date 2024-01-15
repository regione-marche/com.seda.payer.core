package com.seda.tag.library;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.BodyContent;
import javax.servlet.jsp.tagext.BodyTagSupport;
import javax.sql.rowset.CachedRowSet;

import com.seda.commons.string.Convert;
import com.seda.tag.core.DgColumn;
import com.seda.tag.core.IfSeda;
import com.seda.tag.core.Repeater;

public class RepeaterTag extends BodyTagSupport  {
	Repeater rpt = new Repeater();
	public void setCachedrowset(String value) {
		rpt.setRsQuery(value);

	}
	public void setUsexml(boolean value) {
		rpt.setBUsexml(value);
	}
	
	public void setStartrow(int value)
	{
	
	rpt.setIStartRow(value);
	}
	
	public void setNumrows(int value)
	{
	rpt.setINumRow(value);	
	}
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	
	public int doEndTag() {	
		BodyContent bodyContent = super.getBodyContent();
		String bodyString = "";
		String Prova = "";
	
	if (bodyContent != null)
		bodyString = bodyContent.getString();
	else
		bodyString = " ";

	Prova = bodyString;

	rpt.sContent= Prova;
	

	try {
		
	 
	
	
	if (rpt.bUseXml) {

			rpt.cRowSet = Convert.stringToWebRowSet((String) pageContext
					.getAttribute(rpt.getRsQuery(),
							PageContext.REQUEST_SCOPE));
		 
		
	}

	else if (pageContext.getRequest().getAttribute(rpt.getRsQuery()) != null) {
		rpt.cRowSet = (CachedRowSet) pageContext.getRequest()
				.getAttribute(rpt.getRsQuery());
	} else if (pageContext.getSession().getAttribute(
			rpt.getRsQuery()) != null) {
		rpt.cRowSet = (CachedRowSet) pageContext.getSession()
				.getAttribute(rpt.getRsQuery());

	}
	JspWriter out = pageContext.getOut();
	rpt.render(out);
	}
	catch (Exception e) {
		// TODO: handle exception
	}
	
	
	rpt.IfTags= new ArrayList<IfSeda>();
	
	rpt = new Repeater();
	return EVAL_PAGE;
	}
	public void Add(IfSeda IfTag)
	{
		rpt.AddIf(IfTag);
		
		
	}	
}
