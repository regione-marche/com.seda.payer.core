/**
 * 
 */
package com.seda.j2ee5.maf.taglibs;

import com.seda.j2ee5.maf.components.defender.csrf.CSRFContext;
import com.seda.j2ee5.maf.components.defender.csrf.CSRFUtil;

/**
 * @author Seda Lab
 *
 */
public class HTMLAnchorSupport extends HTMLBaseSupport {

	private static final long serialVersionUID = 1L;

	private String action;
	private String target;
	private String title;	

	private String htmlId;
	private String name;
	private String cssClass;
	
	private String text;

	private String onclick;
	private boolean autojs=true;
	
	public String getAction() {
		return action;
	}
	public void setAction(String action) {
		this.action = action;
	}
	public String getTarget() {
		return target;
	}
	public void setTarget(String target) {
		this.target = target;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getHtmlid() {
		return htmlId;
	}
	public void setHtmlid(String id) {
		this.htmlId = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCssClass() {
		return cssClass;
	}
	public void setCssClass(String cssClass) {
		this.cssClass = cssClass;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	
	public String getOnclick() {
		return onclick;
	}
	public void setOnclick(String onclick) {
		this.onclick = onclick;
	}
	
	public boolean isAutojs() {
		return autojs;
	}
	public void setAutojs(boolean autojs) {
		this.autojs = autojs;
	}
	@Override
	public void recycle() {
		super.recycle();

		setAction(null);
		setTarget(null);
		setTitle(null);	
		setHtmlid(null);
		setName(null);
		setCssClass(null);
		setText(null);
		setOnclick(null);
		setAutojs(true);
	}
	
	public String getBaseHTML(boolean isCsfr) {
    	if (htmlId==null) htmlId=name;
    	String savedAction=action;
    	if (savedAction!=null && isCsfr) {
    		if (CSRFContext.getInstance().isActive()) {
    			String safeURL = CSRFUtil.getAugmentedUrl(pageContext.getSession(), savedAction);
    			savedAction=safeURL;
    		}
    	}
    	return (name!=null?(" name=\"" + name + "\""):"") +
		(htmlId!=null?(" id=\"" + htmlId + "\""):"") +
		(cssClass != null ? (" class=\"" + cssClass + "\"") : "") +
		(savedAction != null ? (" href=\"" + savedAction + "\"") : "") +
		(target != null ? (" target=\"" + target + "\"") : "") + 
		(title != null ? (" title=\"" + title + "\"") : "") +
		(onclick != null ? (" onclick=\"" + onclick + "\"") : "") +
		(getTabindex() >= 0 ? (" tabindex=\"" + getTabindex() + "\"") : "")
		;
    }		
	
	public String getBaseHTML() {
    	return getBaseHTML(false);
    }	
	
}
