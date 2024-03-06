/**
 * 
 */
package com.seda.j2ee5.maf.taglibs;

import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.tagext.BodyContent;
import javax.servlet.jsp.tagext.BodyTagSupport;

/**
 * Support for the form action. Use with jstl core url tag.
 * @author Seda Lab
 *
 */
public class ActionTag extends BodyTagSupport {

	private static final long serialVersionUID = 1L;

	public int doAfterBody() throws JspTagException {
		BodyContent bc = getBodyContent();
		String actionURL = bc.getString();
		// button tag support
		InputButtonTag buttonTag = (InputButtonTag) findAncestorWithClass(this, InputButtonTag.class);
		if (buttonTag!=null) {
			if (actionURL != null && actionURL.trim().length()>0) 
				buttonTag.setAction(actionURL.trim());
			return skipBody(bc);
		}
		// anchor tag support
		AnchorTag anchorTag = (AnchorTag) findAncestorWithClass(this, AnchorTag.class);
		if (anchorTag!=null) {
			if (actionURL != null && actionURL.trim().length()>0) 
				anchorTag.setAction(actionURL.trim());
			return skipBody(bc);
		}
		
		// css tag support
//		CssTag cssTag = (CssTag) findAncestorWithClass(this, CssTag.class);
//		if (cssTag!=null) {
//			if (actionURL != null && actionURL.trim().length()>0) 
//				cssTag.setAction(actionURL.trim());
//			return skipBody(bc);
//		}
		// css tag support
//		ScriptTag scriptTag = (ScriptTag) findAncestorWithClass(this, ScriptTag.class);
//		if (scriptTag!=null) {
//			if (actionURL != null && actionURL.trim().length()>0) 
//				scriptTag.setAction(actionURL.trim());
//			return skipBody(bc);
//		}
		// Paging tag support
		PagingTag pagingTag = (PagingTag) findAncestorWithClass(this, PagingTag.class);
		if (pagingTag!=null) {
			if (actionURL != null && actionURL.trim().length()>0) 
				pagingTag.setAction(actionURL.trim());
			return skipBody(bc);
		}
		
		// Select tag support
		SelectTag selectTag = (SelectTag) findAncestorWithClass(this, SelectTag.class);
		if (selectTag!=null) {
			if (actionURL != null && actionURL.trim().length()>0) 
				selectTag.setAction(actionURL.trim());
			return skipBody(bc);
		}
		
		// Checkbox tag support
		InputCheckboxTag inputCheckboxTagTag = (InputCheckboxTag) findAncestorWithClass(this, InputCheckboxTag.class);
		if (inputCheckboxTagTag!=null) {
			if (actionURL != null && actionURL.trim().length()>0) 
				inputCheckboxTagTag.setAction(actionURL.trim());
			return skipBody(bc);
		}				

		// form tag support
		FormTag formTag = (FormTag) findAncestorWithClass(this, FormTag.class);
		if (formTag!=null) {
			if (actionURL != null && actionURL.trim().length()>0) 
				formTag.setAction(actionURL.trim());
			return skipBody(bc);
		}		
		
		// anchor tag support
//		BrowserClosedNotifierTag browserClosedNotifierTag = (BrowserClosedNotifierTag) findAncestorWithClass(this, BrowserClosedNotifierTag.class);
//		if (browserClosedNotifierTag!=null) {
//			if (actionURL != null && actionURL.trim().length()>0) 
//				browserClosedNotifierTag.setAction(actionURL.trim());			
//		}
		
		return skipBody(bc);
	}	
	
	public int skipBody(BodyContent bc) {
		bc.clearBody();
		return SKIP_BODY;		
	}
}
