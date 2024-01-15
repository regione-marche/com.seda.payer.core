/**
 * 
 */
package com.seda.j2ee5.maf.core.application;

import java.util.ArrayList;
import java.util.List;

/**
 * @author f.ricci
 *
 */
public class WebServlet {

	private String description;
	private String displayName;
	private String servletName;
	private String servletClass;
	private int loadOnStartup;
	
	private List<String> urlPatternList;
	
	
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getDisplayName() {
		return displayName;
	}
	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}
	public String getServletName() {
		return servletName;
	}
	public void setServletName(String servletName) {
		this.servletName = servletName;
	}
	public String getServletClass() {
		return servletClass;
	}
	public void setServletClass(String servletClass) {
		this.servletClass = servletClass;
	}
	public int getLoadOnStartup() {
		return loadOnStartup;
	}
	public void setLoadOnStartup(int loadOnStartup) {
		this.loadOnStartup = loadOnStartup;
	}
	
	public List<String> getUrlPatternList() {
		return urlPatternList;
	}
	public void setUrlPatternList(List<String> urlPatternList) {
		this.urlPatternList = urlPatternList;
	}
	
	
	public void addUrlPattern(String urlPattern) {
		urlPatternList.add(urlPattern);
	}
	
	public WebServlet() {
		super();
	}
	
	public WebServlet(String description, String displayName,
			String servletName, String servletClass, int loadOnStartup) {
		super();
		this.description = description;
		this.displayName = displayName;
		this.servletName = servletName;
		this.servletClass = servletClass;
		this.loadOnStartup = loadOnStartup;
		urlPatternList=new ArrayList<String>();
	}

	@Override
	public String toString() {
		return "WebServlet [description=" + description + ", displayName="
				+ displayName + ", servletName=" + servletName
				+ ", servletClass=" + servletClass + ", loadOnStartup="
				+ loadOnStartup + ", urlPatternList=" + urlPatternList + "]";
	}
	
}
