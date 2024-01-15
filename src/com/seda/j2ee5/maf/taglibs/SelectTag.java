/**
 * 
 */
package com.seda.j2ee5.maf.taglibs;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import javax.servlet.jsp.JspTagException;

import com.seda.commons.logger.CustomLoggerManager;
import com.seda.commons.logger.LoggerWrapper;
import com.seda.j2ee5.maf.util.MAFLogger;
/**
 * HTML select tag
 * @author Seda Lab
 *
 */
public class SelectTag extends HTMLSelectSupport {

	private static final long serialVersionUID = 1L;

	private static final LoggerWrapper logger =  CustomLoggerManager.get(PagingTag.class);
	
	private String action;
	private boolean onchange=false;
	
	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}	
	
	public boolean isOnchange() {
		return onchange;
	}

	public void setOnchange(boolean onchange) {
		this.onchange = onchange;
	}

	public void putOption(String value, String text) {
		putOption(new OptionData(value, text));
	}
	
	public void putOption(OptionData option) {
		getOptions().add(option);
	}	

	public void clearOptions() {
		getOptions().clear();
	}
	public int doStartTag() throws JspTagException {
		setOptions(new ArrayList<OptionData>());
		return EVAL_BODY_BUFFERED;
	}

	/* (non-Javadoc)
	 * @see javax.servlet.jsp.tagext.BodyTagSupport#doEndTag()
	 */
	public int doEndTag() throws JspTagException {
		try {
			String baseHtml = getBaseHTML();
			
			StringBuffer html = new StringBuffer();
			StringBuffer htmlHidden = new StringBuffer();
			String roValue=null;
			String roText=null;
			if (isReadonly()) {
				roValue = getSelectedValue();
				roText = getSelectedText(roValue);
				htmlHidden.append("<input type=\"hidden\" ");
				htmlHidden.append(baseHtml);				
				if (roText!=null && getLabel()==null) {
					setLabel(roText);
					roText=null;
				}
				htmlHidden.append(" value=\""+roValue+"\"/>");
			} else {			
				html.append("<select ");
				html.append(baseHtml);
				// check for on change event in a form
				if (isOnchange()) {
					String fn=JSUtil.getSubmitFormCode(null);;
					FormTag formTag	= (FormTag) findAncestorWithClass(this, FormTag.class);
					if (formTag!=null && action!=null) {
						fn = JSUtil.getFunctionName("change", formTag.getName(), getName());
						String js = null;
//						if (formTag.isValidation())
//							js =JSUtil.getSubmitValidationFunctionScript(fn, action, formTag.getName());
//						else 
							js = JSUtil.getSubmitFunctionScript(fn, action, formTag.getName());
						formTag.putJavaScript(js);
						fn+="();";
					}
					html.append(" onchange=\"" + fn + "\" ");					
				}				

				html.append(">");
				for (Iterator<OptionData> iterator = getOptions().iterator(); iterator.hasNext();) {
					OptionData o =  iterator.next();                	
					html.append(o.getHTML(getSelectedValue()));
				}
				html.append("</select>");
			}
			
			
			if (isReadonly()) {
				html.append("<input type=\"text\" readonly=\"readonly\"");
				html.append(isDisabled() ? " disabled=\"disabled\"":"");
				html.append(getCssClass() != null ? (" class=\"" + getCssClass() + "\"") : "");
				html.append(" value=\"" + (roText==null?roValue:roText) + "\"");
				html.append(" size=\""+((roText==null?roValue:roText).length()+3)+"\"");
				html.append(" name=\"" + getName() + "_text\"/>");
			}
			
			setHTMLLabel(html,getHtmlid(),false);
			
			if (isReadonly()) {
				html.insert(0, htmlHidden);
			}
			pageContext.getOut().print(html.toString());

			recycle();		
			
			return EVAL_PAGE;
		} catch (IOException e) {
			logger.error(MAFLogger.getMessage("generic_exception"), e);
			throw new JspTagException(e.getMessage());
		}
	}

	@Override
	public void recycle() {
		super.recycle();
		action=null;
		onchange=false;
	}	


	
}
