/**
 * 
 */
package com.seda.j2ee5.maf.taglibs;

import java.io.IOException;

import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.tagext.BodyContent;
import javax.servlet.jsp.tagext.BodyTagSupport;

import com.seda.commons.logger.CustomLoggerManager;
import com.seda.commons.logger.LoggerWrapper;
import com.seda.j2ee5.maf.components.captcha.CaptchaManager;
import com.seda.j2ee5.maf.components.defender.csrf.CSRFContext;
import com.seda.j2ee5.maf.components.defender.csrf.CSRFUtil;
import com.seda.j2ee5.maf.util.MAFLogger;
import com.seda.j2ee5.maf.util.URLUtil;
/**
 * @author f.ricci
 *
 */
public class CaptchaTag extends BodyTagSupport {

	private static final long serialVersionUID = 1L;

	private static final LoggerWrapper logger =  CustomLoggerManager.get(CaptchaTag.class);
	
	private String href;
	private String height;
	private String width;
	private String cssClass;	

	public void setHeight(String height) {
		this.height = height;
	}

	public void setWidth(String width) {
		this.width = width;
	}

	public void setCssClass(String cssClass) {
		this.cssClass = cssClass;
	}

	public int doStartTag() throws JspTagException {
        return EVAL_BODY_BUFFERED;
    }
	
	public int doAfterBody() throws JspTagException {
		BodyContent bc = getBodyContent();
		String hrefString = bc.getString();
		if (hrefString != null && hrefString.trim().length()>0) {
			href=hrefString.replace('\n', ' ').replace('\t', ' ').trim();			
		}

		return skipBody(bc);
	}	
	
	public int doEndTag() throws JspTagException {
		if (CSRFContext.getInstance().isActive()) {
			href = CSRFUtil.getAugmentedUrl(pageContext.getSession(),href);
		}		
    	try {
    		StringBuffer baseHtml = new StringBuffer("<img alt=\"captcha image\"") ;
    		if (height!=null) {
        		baseHtml.append(" height=\""+height+"\"");
        		href=URLUtil.addParameter(href, CaptchaManager.CAPTCHA_HEIGHT, height);
    		}
    		if(width!=null) {
        		baseHtml.append(" width=\""+width+"\"");
        		href=URLUtil.addParameter(href, CaptchaManager.CAPTCHA_WIDTH, width);        		
    		}
    		if(cssClass!=null) {
        		baseHtml.append(" class=\""+cssClass+"\"");
    		}    		
    		baseHtml.append(" src=\"".concat(href==null?"#":href).concat("\""));
    		baseHtml.append("/>");
    		pageContext.getOut().print(baseHtml);
    		href=null;
    		return EVAL_PAGE;
    	}
    	catch (IOException e) {
    		logger.error(MAFLogger.getMessage("generic_exception"), e);
    		throw new JspTagException(e.getMessage());
    	}	
    }
	
	public int skipBody(BodyContent bc) {
		bc.clearBody();
		return SKIP_BODY;		
	}	
	
}
