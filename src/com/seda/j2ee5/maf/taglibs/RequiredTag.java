/**
 * 
 */
package com.seda.j2ee5.maf.taglibs;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.tagext.BodyContent;
import javax.servlet.jsp.tagext.BodyTagSupport;

import com.seda.j2ee5.maf.components.encoding.ParameterDiscovery;

/**
 * @author Seda Lab
 *
 */
public class RequiredTag extends BodyTagSupport {

	private static final long serialVersionUID = 1L;

	public int doAfterBody() throws JspTagException {
		HTMLInputTextSupport tag = (HTMLInputTextSupport) findAncestorWithClass(this, HTMLInputTextSupport.class);
		BodyContent bc = getBodyContent();
		String requiredString = bc.getString();
		if (requiredString != null) {
			// override selected value for rejected validation
			requiredString=ParameterDiscovery.getSafeEncodedParameter((HttpServletRequest) pageContext.getRequest(), tag.getName(), requiredString);
			tag.setRequired(Boolean.parseBoolean(requiredString.trim()));			
		}

		bc.clearBody();
		return SKIP_BODY;
	}
	
}
