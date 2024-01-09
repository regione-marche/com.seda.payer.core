/**
 * 
 */
package com.seda.j2ee5.maf.template.tags;

import java.text.MessageFormat;
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.tagext.TagSupport;

import com.seda.commons.logger.CustomLoggerManager;
import com.seda.commons.logger.LoggerWrapper;
import com.seda.j2ee5.maf.template.TemplateParameter;
import com.seda.j2ee5.maf.template.TemplateScreen;
import com.seda.j2ee5.maf.util.MAFAttributes;
//import com.seda.j2ee5.maf.util.Utils;
import com.seda.j2ee5.maf.util.MAFLogger;

/**
 * @author Seda Lab
 *
 */
public class IncludeTag extends TagSupport {

	private static final LoggerWrapper logger =  CustomLoggerManager.get(IncludeTag.class);
	
	private static final long serialVersionUID = 1L;
	
    private boolean directInclude = false;
    private String parameter = null;
    private TemplateParameter templateParameter = null;
    private TemplateScreen templateScreen = null;
    /**
     * default constructor
     */
    public IncludeTag() {
        super();
    }

    public void setParameter(String parameter){
        this.parameter = parameter;
    }

	public int doStartTag() throws JspTagException {
    	try{
    		pageContext.getOut().flush();
        } catch (Exception e){
             // do nothing
        }
        // load the ScreenFlow
        try {
        	templateScreen = (TemplateScreen)pageContext.getRequest().getAttribute(MAFAttributes.CURRENT_SCREEN);
        } catch (NullPointerException e){
            throw new JspTagException(MAFLogger.getMessage("include_tag_extracting_screen ") + e);
        }
        if ((templateScreen != null) && (parameter != null)) {
            templateParameter = (TemplateParameter)templateScreen.getParameter(parameter);
        } else {
        	logger.error(MAFLogger.getMessage("include_tag_template_screen_null"));
        }
        
        if (templateParameter != null) {
        	directInclude = templateParameter.isDirect();
        }
        return SKIP_BODY;
    }

    public int doEndTag() throws JspTagException {
        try {
            if (directInclude && templateParameter != null) {
            	String bundleName = (String)pageContext.getRequest().getAttribute(MAFAttributes.RESOURCE_BUNDLE);
            	if (bundleName!=null) {
                	Locale locale = (Locale)pageContext.getRequest().getAttribute(MAFAttributes.LOCALE);
            		if (locale==null) {
            			locale=Locale.getDefault();
            		}
            		String key=templateParameter.getValue();
            		String message="";
            		try {
                		ResourceBundle bundle = ResourceBundle.getBundle(bundleName, locale);
            			message = bundle.getString(key);
            			String args = templateParameter.getArgs();
            			if (args==null) args=key;
            			Object[] arguments = (Object[])pageContext.getRequest().getAttribute(args);
            			if (arguments!= null) {
            				message = MessageFormat.format(message, arguments);
            			}
            		} catch (MissingResourceException x) {
            			logger.warn(MAFLogger.getMessage("include_tag_bundle_key_not_found", key));
            			message=key;
            		}
        			pageContext.getOut().print(message);
            	} else {
                    pageContext.getOut().print(templateParameter.getValue());            		
            	}
            } else {
            	if (templateParameter != null)  {        	
            		if (templateParameter.getValue() != null) 
            			pageContext.getRequest().getRequestDispatcher(templateParameter.getValue()).include(pageContext.getRequest(), pageContext.getResponse());
            	}            	
            }
         } catch (Exception ex) {
        	 logger.error(MAFLogger.getMessage("generic_exception"), ex);
        }
        return EVAL_PAGE;
    }	

}
