/**
 * 
 */
package com.seda.j2ee5.maf.core.menu;

import java.io.Serializable;

/**
 * @author Seda Lab
 *
 */
public class MenuItemData implements Serializable {

	private static final long serialVersionUID = 1L;

	private String url;
	private String text;
	private String title;	
	private boolean enabled=true;
	private boolean selected=false;
	
	private String applid;
	private String action;
	
	public MenuItemData(String url, String text, String title, boolean enabled,
			String applid, String action) {
		this.url = url;
		this.text = text;
		this.title = title;
		this.enabled = enabled;
		this.applid = applid;
		this.action = action;
	}

	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}

	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}

	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}

	public boolean isEnabled() {
		return enabled;
	}
	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public String getApplid() {
		return applid;
	}
	public void setApplid(String applid) {
		this.applid = applid;
	}

	public String getAction() {
		return action;
	}
	public void setAction(String action) {
		this.action = action;
	}
	public boolean isSelected() {
		return selected;
	}

	public void setSelected(boolean selected) {
		this.selected = selected;
	}
	@Override
	public String toString() {
		return "MenuItemData [action=" + action + ", applid=" + applid
				+ ", enabled=" + enabled + ", selected=" + selected + ", text="
				+ text + ", title=" + title + ", url=" + url + "]";
	}
	
}
