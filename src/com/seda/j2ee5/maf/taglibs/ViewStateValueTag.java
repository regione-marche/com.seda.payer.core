/**
 * 
 */
package com.seda.j2ee5.maf.taglibs;

import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.tagext.TagSupport;

/**
 * 
 * A Parameter Value for the ViewStateTag
 * 
 * @author Seda Lab
 *
 */
public class ViewStateValueTag extends TagSupport {

	private static final long serialVersionUID = 1L;
	
	String key= null;
    String value = null;

    public void setKey(String key) {
        this.key = key;
    }
    public void setValue(String value) {
        this.value = value;
    }
    
    public int doStartTag() throws JspTagException {
        return SKIP_BODY;
    }
    
    public int doEndTag() throws JspTagException {
        ViewStateTag viewStateTag  =
                  (ViewStateTag) findAncestorWithClass(this, ViewStateTag.class);
        viewStateTag.putParameter(key, value);
        key = null;
        value = null;
        return EVAL_PAGE;
    }	

}
