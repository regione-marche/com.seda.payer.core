/**
 * 
 */
package com.seda.j2ee5.maf.taglibs;

import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.tagext.TagSupport;

import com.seda.j2ee5.maf.util.MAFAttributes;
/**
 * Set the name of the message class bundle
 * @author Seda Lab
 *
 */
public class MessageBundleTag extends TagSupport {

	private static final long serialVersionUID = 1L;

	 private String bundleName = null;
	
	 /**
	  * Set the user preferred resource bundle name
	  */
	 public void setName (String name) {
		 this.bundleName = name;
	 }

	 public int doStartTag() throws JspTagException {
		 return EVAL_BODY_INCLUDE;
	 }

	 public int doEndTag() throws JspTagException {
		 pageContext.setAttribute(MAFAttributes.PAGE_BUNDLE,bundleName);
		 return EVAL_PAGE;
	 }    
}
