package com.seda.tag.library;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.*;

public class FormatDateTag extends BodyTagSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	Calendar value;
	String pattern;
	
	
	public void setValue(Calendar value) {
		this.value = value;
	}
	
	public void setPattern(String pattern) {
		this.pattern = pattern;
	}

	@Override
	public int doStartTag() throws JspException {
		return SKIP_BODY;
	}
	
	@Override
	public int doEndTag() throws JspException {
		
		JspWriter out = pageContext.getOut();
		Date date;
		if (value!=null) {
			date=value.getTime();
		} else {
			date = new Date(System.currentTimeMillis());
		}
		
		if (pattern==null) {
			pattern="yyyy-MM-dd";
		}
		
		SimpleDateFormat dateFormat =new SimpleDateFormat(pattern);
		String formattedDate=null;
		try {
			formattedDate=dateFormat.format(date);
			out.println(formattedDate);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			recycle();
		}
		
		return EVAL_PAGE;
	}

	private void recycle() {
		value=null;
		pattern=null;
	}
	
	
	
	
}
