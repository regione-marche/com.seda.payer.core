/**
 * 
 */
package com.seda.tag.library;

import java.io.IOException;

import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.tagext.BodyTagSupport;

/**
 * @author lmontesi
 *
 */
public class EmptyCellTag extends BodyTagSupport {

	public static String DEFAULT_VALUE="&nbsp;";
	
	private String cellValue;
	private String defaultValue=DEFAULT_VALUE;
	
	public void setValue(String value) {
		this.cellValue=value;
	}
	
	public int doEndTag() throws JspTagException {
		String modifyValue=verifyValue();
		try {
			pageContext.getOut().print(modifyValue.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
		cellValue=null;
		defaultValue=DEFAULT_VALUE;
		return EVAL_PAGE;
	}
	
	private String verifyValue() {
		String modifyValue;
		if (cellValue==null || cellValue.trim().length()==0) {
			modifyValue=defaultValue;
		} else {
			modifyValue=cellValue.trim();
		}
		return modifyValue;
	}
	
}
