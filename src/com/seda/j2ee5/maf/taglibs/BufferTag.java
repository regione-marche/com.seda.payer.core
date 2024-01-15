/**
 * 
 */
package com.seda.j2ee5.maf.taglibs;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.BodyContent;
import javax.servlet.jsp.tagext.BodyTagSupport;

import com.seda.commons.logger.CustomLoggerManager;
import com.seda.commons.logger.LoggerWrapper;
import com.seda.j2ee5.maf.util.MAFLogger;

/**
 * This tag allows the user to buffer string information and set the duration of the buffer slot 
 * 
 * @author Seda Lab
 *
 */
public class BufferTag extends BodyTagSupport {

	private static final long serialVersionUID = 1L;
	
	private static final LoggerWrapper logger =  CustomLoggerManager.get(BufferTag.class);
	
	private String scope;
    private String name;
    private long duration;
    private Slot slot;
    
    public void setName(String n) { name = n; }

    public void setDuration(long d) { duration = d; }

    public int doStartTag() throws JspTagException {
        slot = getSlot();
        if ((slot != null) && slot.isExpired()) {
            slot = null;
            removeSlot();
        }
        return (slot == null) ? EVAL_BODY_BUFFERED : SKIP_BODY;
    }
    
    private String getKey() {
            HttpServletRequest req = 
            ((HttpServletRequest) pageContext.getRequest());
            return  (req.getRequestURL().toString() 
                            + '#' + name
                            + '?' + req.getQueryString());
    }
    
    private Slot getSlot() {
        String key = getKey();
         if ("context".equals(scope)) {
                    return (Slot)pageContext.getServletContext().getAttribute(key);
          } else if ("session".equals(scope)) {
                    return (Slot)pageContext.getSession().getAttribute(key);
         } else if ("request".equals(scope)) {
                    return (Slot)pageContext.getRequest().getAttribute(key);
         } else if ("page".equals(scope)) {
                    return (Slot)pageContext.getAttribute(key);
         }
        return null;
    }
    
    private void removeSlot() {
        String key = getKey();
         if ("context".equals(scope)) {
                   pageContext.getServletContext().removeAttribute(key);
          }else if ("session".equals(scope)) {
                   pageContext.getSession().removeAttribute(key);
         } else if ("request".equals(scope)) {
                    pageContext.getRequest().removeAttribute(key);
         } else if ("page".equals(scope)) {
                    pageContext.removeAttribute(key);
         }
    }

    public int doEndTag() throws JspTagException {
        if (slot == null) {
            BodyContent bc = getBodyContent();
            if (bc != null) {
                String content = bc.getString();
                slot = new Slot(content, duration);
                if ("context".equals(scope)) {
                    pageContext.getServletContext().setAttribute(getKey(), slot);
                }else if ("session".equals(scope)) {
                    pageContext.getSession().setAttribute(getKey(), slot);
                } else if ("request".equals(scope)) {
                    pageContext.getRequest().setAttribute(getKey(), slot);
                } else if ("page".equals(scope)) {
                    pageContext.setAttribute(getKey(), slot);
                }
                
                try {
                    JspWriter out = bc.getEnclosingWriter();
                    out.print(content);
                } catch (IOException ioe) {
                    logger.error(MAFLogger.getMessage("generic_exception"),ioe);
                }
            }
        } else {
            try {
                JspWriter out = pageContext.getOut();
                out.print(slot.getContent());
            } catch (IOException ioe) {
            	logger.error(MAFLogger.getMessage("generic_exception"),ioe);
            }
        }
        // reset everything
        scope = null;
        name = null;
        duration = 0;
        slot = null;

        return EVAL_PAGE;
    }    
    
    /*
     * An slot in the buffer.
     */
    class Slot {

        String content;
        long timestamp;
        long duration;

        public Slot(String content, long duration) {
            this.content = content;
            timestamp = System.currentTimeMillis();
            this.duration = duration;
        }

        public String getContent() { return content; }

        public boolean isExpired() {
            long currentTime = System.currentTimeMillis();
            return ((currentTime - timestamp) > duration);
        }
    }    
	
}
