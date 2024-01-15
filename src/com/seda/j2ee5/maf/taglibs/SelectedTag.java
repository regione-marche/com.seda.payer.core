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
 * Defines the selected value for the selected tag
 * @author Seda Lab
 *
 */
public class SelectedTag extends BodyTagSupport {

	private static final long serialVersionUID = 1L;

	private boolean trim=true;
	
	public boolean isTrim() {
		return trim;
	}

	public void setTrim(boolean trim) {
		this.trim = trim;
	}
	
	public int doAfterBody() throws JspTagException {
		SelectTag tag = (SelectTag) findAncestorWithClass(this, SelectTag.class);
		BodyContent bc = getBodyContent();
		String selectedValue = bc.getString();
		if (selectedValue != null) {
			if (isTrim()) selectedValue=selectedValue.trim();
			// override selected value for rejected validation			
			selectedValue=ParameterDiscovery.getSafeEncodedParameter((HttpServletRequest) pageContext.getRequest(), tag.getName(),selectedValue);
			tag.setSelectedValue(selectedValue);			
		}
//		if (selectedValue!=null && selectedValue.length()>0)
//			tag.setSelectedValue(selectedValue);
		
		bc.clearBody();
		return SKIP_BODY;
	}

}
