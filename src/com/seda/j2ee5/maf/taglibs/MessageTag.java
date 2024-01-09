/**
 * 
 */
package com.seda.j2ee5.maf.taglibs;

import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.tagext.TagSupport;

import com.seda.commons.logger.CustomLoggerManager;
import com.seda.commons.logger.LoggerWrapper;
import com.seda.j2ee5.maf.util.MAFAttributes;
import com.seda.j2ee5.maf.util.MAFLogger;

/**
 * Simple message handler to display localized message from a resource bundle based on jsp request
 * 
 * @author Seda Lab
 *
 */
public class MessageTag extends TagSupport {

	private static final long serialVersionUID = 1L;
	
	private static final LoggerWrapper logger =  CustomLoggerManager.get(MessageTag.class);
	
	private String key	      = null;
    private String bundleName = null;
    private String language   = null;
    private String country    = null;
    private String variant    = null;

    /**
	 * Set the user preferred language
	 */
	public void setLanguage(String lang) {
        this.language = lang;
	}

    /**
	 * Get the user preferred language
	 */
	public String getLanguage() {
		return language;
	}

	/**
	 * Set the user preferred country
	 */
	public void setCountry(String country) {
		this.country = country;
	}

    /**
	 * Get the user preferred country
	 */
	public String getCountry() {
		return country;
    }

    /**
	 * Set the user preferred variant
	 */
    public void setVariant(String variant) {
	    this.variant = variant;
	}

    /**
	 * Get the user preferred variant
	 */
	public String getVariant() {
	    return variant;
    }

    /**
	 * Set the user preferred resource bundle name
	 */
	public void setName (String name) {
		this.bundleName = name;
	}

    /**
	 * Set the message key required to retrieve message from the resource bundle
	 */
    public void setKey(String key) {
        this.key = key;
    }

    /**
	 * Get the message key required to retrieve message from the resource bundle
	 */
    public String getKey() {
        return key;
    }

    /**
	 * Gets the user preferred resource bundle name
	 */
	public String getName() {
		return bundleName;
	}

    /**
	 * Will be called by the JSP Engine when it encounters the start of the tag
	 */
    public int doStartTag() throws JspTagException {
        return EVAL_BODY_INCLUDE;
    }

    /**
	 * Will be called by the JSP Engine when it encounters the end of the tag
	 */
    public int doEndTag() throws JspTagException {
    	HttpServletRequest request = 
            ((HttpServletRequest) pageContext.getRequest());
		String useBundle=bundleName;
        try {
        	Locale locale = (Locale)request.getAttribute(MAFAttributes.LOCALE);
    		if (locale==null) {
    			locale=Locale.getDefault();
    		}         	
 			if(language != null) {
				if( country == null)
					country = "";
				if(variant == null)
					variant = "";
				locale = new Locale(language, country, variant);
			}
 			// try to find bundle in the page scope
        	if (useBundle==null || useBundle.trim().length()==0) {
        		String bundlePage = (String)pageContext.getAttribute(MAFAttributes.PAGE_BUNDLE);
       			useBundle=bundlePage;
        	}
 			// try to find bundle from template manager        	
        	if (useBundle==null || useBundle.trim().length()==0) {
        		String bundleTemplate = (String)request.getAttribute(MAFAttributes.RESOURCE_BUNDLE);
       			useBundle=bundleTemplate;
        	}
        	String message;
        	if (useBundle==null || useBundle.trim().length()==0) {
        		message = key;
        	} else {
    			try {
    				ResourceBundle bundle = ResourceBundle.getBundle(useBundle, locale);
        			message = bundle.getString(key);            			
        		} catch (MissingResourceException x) {
        			logger.error(MAFLogger.getMessage("message_tag_key_not_found", key, useBundle));
        			message=key;
        		}    			
        	}
			pageContext.getOut().write(message);
		} catch(Exception e) {
			logger.error(MAFLogger.getMessage("generic_exception"), e);
    		throw new JspTagException(e.getMessage());
        }
		recycle();		
        return EVAL_PAGE;
    }    
    
    private void recycle() {
    	key	      = null;
        bundleName = null;
        language   = null;
        country    = null;
        variant    = null;    	
    }
}
