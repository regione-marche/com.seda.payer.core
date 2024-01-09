/**
 * 
 */
package com.seda.j2ee5.maf.taglibs;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.tagext.BodyContent;
import javax.servlet.jsp.tagext.BodyTagSupport;

import com.seda.j2ee5.maf.components.encoding.ParameterDiscovery;
//import com.seda.j2ee5.maf.util.MAFAttributes;
/**
 * This is a value attribute for input tag
 * @author Seda Lab
 *
 */
public class ValueTag extends BodyTagSupport {

	private static final long serialVersionUID = 1L;
	
	private boolean trim=true;
	
	public boolean isTrim() {
		return trim;
	}

	public void setTrim(boolean trim) {
		this.trim = trim;
	}

	public int doAfterBody() throws JspTagException {
		HTMLInputSupport inputTag = (HTMLInputSupport) findAncestorWithClass(this, HTMLInputSupport.class);
		DateSelectorTag dateTag = (DateSelectorTag) findAncestorWithClass(this, DateSelectorTag.class);
		
		BodyContent bc = getBodyContent();
		String selectedValue = bc.getString();
		if (selectedValue != null) {
			if (isTrim()) selectedValue=selectedValue.trim();
			// override selected value for rejected validation			
			if (inputTag!=null) {
				selectedValue =ParameterDiscovery.getSafeEncodedParameter((HttpServletRequest) pageContext.getRequest(), inputTag.getName(),selectedValue);
				inputTag.setValue(selectedValue);				
			} else if (dateTag!=null) {
				selectedValue=ParameterDiscovery.getSafeEncodedCalendarISO((HttpServletRequest) pageContext.getRequest(), dateTag.getName(),selectedValue);
				dateTag.setValue(selectedValue);				
			} 
		}
		bc.clearBody();
		trim=true;
		
		return SKIP_BODY;
	}	
	
}
