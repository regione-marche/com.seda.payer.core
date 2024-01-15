/**
 * 
 */
package com.seda.j2ee5.maf.taglibs;

import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.tagext.BodyContent;
import javax.servlet.jsp.tagext.BodyTagSupport;

import com.seda.commons.logger.CustomLoggerManager;
import com.seda.commons.logger.LoggerWrapper;
/**
 * @author f.ricci
 *
 */
public abstract class LogSupport extends BodyTagSupport {

	private static final LoggerWrapper logger =  CustomLoggerManager.get(LogSupport.class);

	protected Level level;
	
	private String message;
	private String messageBody;
	private String category;
	private Throwable exception;
	
	
	protected abstract void setLevel() ;

	public final void setMessage(String message) {
		this.message = message;
	}

	public final void setCategory(String category) {
		this.category = category;
	}

	public final void setException(Throwable exception) {
		this.exception = exception;
	}

	public final int doStartTag() throws JspTagException {
		setLevel();
        return EVAL_BODY_BUFFERED;
    }
	
	public final int doAfterBody() throws JspTagException {
		BodyContent bc = getBodyContent();
		messageBody = bc.getString();
		if (messageBody == null || messageBody.trim().length()==0) {
			messageBody="";			
		}
		return skipBody(bc);
	}	
	
	public final int doEndTag() throws JspTagException {
		if (message==null) message=messageBody;
		
		switch (level) {
		case TRACE:
			if (exception==null) logger.debug(message);
			else logger.error(message,exception);
			break;
		case INFO:
			if (exception==null) logger.info(message);
			else logger.error(message,exception);			
			break;
		case WARN:
			if (exception==null) logger.warn(message);
			else logger.warn(message,exception);			
			break;
		case ERROR:
			if (exception==null) logger.error(message);
			else logger.error(message,exception);			
			break;			
		case DEBUG:
		default:
			if (exception==null) logger.debug(message);
			else logger.error(message,exception);
			break;
		}
		
		recycle();
		
   		return EVAL_PAGE;
    }
	
	private void recycle() {
		level=null;
		message=null;
		messageBody=null;
		category=null;
		exception=null;
	}

	public int skipBody(BodyContent bc) {
		bc.clearBody();
		return SKIP_BODY;		
	}		

	
	protected enum Level {
		TRACE,
		DEBUG,
		INFO,
		WARN,
		ERROR
	}
}
