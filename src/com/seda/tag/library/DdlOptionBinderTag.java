/**
 * 
 */
package com.seda.tag.library;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.tagext.BodyTagSupport;
import javax.servlet.jsp.tagext.Tag;

import com.seda.tag.core.DdlOption;

/**
 * @author f.ricci
 *
 */
public class DdlOptionBinderTag extends BodyTagSupport {
	private static final long serialVersionUID = 1L;

	String id = null;
	ArrayList<DdlOption> options;

    public void setId(String id) { this.id = id; }
	public void setOptions(ArrayList<DdlOption> options) { this.options = options; }
    
    public int doStartTag() throws JspTagException {
        return EVAL_BODY_BUFFERED;
    }
    
    @SuppressWarnings("unchecked")
	public int doEndTag() throws JspTagException {
    	try {
    		Tag t = DdlOptionBinderTag.this.getParent();

    		if(t.getClass() == com.seda.tag.library.DropDownListTag.class ) {

    			HttpServletRequest request = ((HttpServletRequest) pageContext.getRequest());
    			// get the array list of OptionData from the request
    			ArrayList<DdlOption> reqOptions=null;
    			if (id!=null) reqOptions = (ArrayList<DdlOption>)request.getAttribute(id);	

    			if(reqOptions!=null) setOptions(reqOptions);

    			if (options!=null) {
    				com.seda.tag.library.DropDownListTag tr = (com.seda.tag.library.DropDownListTag)t ;
    				for (DdlOption ddlOption : options) {
    					tr.Add(ddlOption);
    				}
    			}
    		}
            recycle();
            
            return EVAL_PAGE;
    	} catch (Exception e) {
    		e.printStackTrace();
    		throw new JspTagException(e.getMessage());
    	}
    }
    
    public void recycle() {
    	this.id = null;
        this.options=null;
    }    
    
    
}
