/**
 * 
 */
package com.seda.j2ee5.maf.taglibs;

import javax.servlet.jsp.tagext.BodyTagSupport;

/**
 * @author Seda Lab
 *
 */
public class HTMLBaseSupport extends BodyTagSupport{

	private static final long serialVersionUID = 1L;

	private boolean readonly;
	private boolean disabled;
	private int tabindex;
	
	public boolean isReadonly() {
		return readonly;
	}
	public void setReadonly(boolean readonly) {
		this.readonly = readonly;
	}
	public boolean isDisabled() {
		return disabled;
	}
	public void setDisabled(boolean disabled) {
		this.disabled = disabled;
	}
	public int getTabindex() {
		return tabindex;
	}
	public void setTabindex(int tabindex) {
		this.tabindex = tabindex;
	}
	public void recycle() {
		setReadonly(false);
		setDisabled(false);		
		setTabindex(0);
	}
}
