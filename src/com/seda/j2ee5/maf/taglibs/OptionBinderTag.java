/**
 * 
 */
package com.seda.j2ee5.maf.taglibs;

import java.util.ArrayList;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.tagext.BodyTagSupport;

import com.seda.commons.logger.CustomLoggerManager;
import com.seda.commons.logger.LoggerWrapper;
import com.seda.j2ee5.maf.util.MAFLogger;

/**
 * This tag define a binder for the select tag and use the OptionData class found in the request 
 * @author Seda Lab
 *
 */
public class OptionBinderTag extends BodyTagSupport {

	private static final long serialVersionUID = 1L;

	private static final LoggerWrapper logger =  CustomLoggerManager.get(OptionBinderTag.class);
	
	String id = "";
	ArrayList<OptionData> options;
	
    public void setId(String id) { this.id = id; }
    public void setOptions(ArrayList<OptionData> options) { this.options = options; }
    
    public int doStartTag() throws JspTagException {
        return EVAL_BODY_BUFFERED;
    }

    @SuppressWarnings("unchecked")
	public int doEndTag() throws JspTagException {
    	try {
            SelectTag tag = (SelectTag) findAncestorWithClass(this, SelectTag.class);
            HttpServletRequest request = ((HttpServletRequest) pageContext.getRequest());
            // get the array list of OptionData from the request
            ArrayList<OptionData> reqOptions=null;
            if (id!=null) reqOptions = (ArrayList<OptionData>)request.getAttribute(id);	
            
            if(reqOptions!=null) setOptions(reqOptions);
            
            if (options!=null) {
            	for (Iterator<OptionData> iterator = options.iterator(); iterator.hasNext();) {
					OptionData o =  iterator.next();                	
					tag.putOption(o);
				}
            }
            
            recycle();
            
            return EVAL_PAGE;
    	} catch (Exception e) {
    		logger.error(MAFLogger.getMessage("generic_exception"), e);
    		throw new JspTagException(e.getMessage());
    	}
    }
    
    public void recycle() {
    	this.id = null;
        this.options=null;
    }
}
