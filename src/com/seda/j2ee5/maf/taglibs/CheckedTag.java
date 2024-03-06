/**
 * 
 */
package com.seda.j2ee5.maf.taglibs;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.tagext.BodyContent;
import javax.servlet.jsp.tagext.BodyTagSupport;

import com.seda.j2ee5.maf.components.encoding.ParameterDiscovery;
import com.seda.j2ee5.maf.util.MAFAttributes;

/**
 * @author Seda Lab
 *
 */
public class CheckedTag extends BodyTagSupport {

	private static final long serialVersionUID = 1L;
	
	public int doAfterBody() throws JspTagException {
		InputCheckboxTag tag = (InputCheckboxTag) findAncestorWithClass(this, InputCheckboxTag.class);
		BodyContent bc = getBodyContent();
		String checkedString = bc.getString();
		if (checkedString != null) {
			checkedString=ParameterDiscovery.getSafeEncodedParameter((HttpServletRequest) pageContext.getRequest(), tag.getName(),checkedString);
			tag.setChecked(Boolean.parseBoolean(checkedString.trim()));			
		}

		bc.clearBody();
		return SKIP_BODY;
	}
	
}
