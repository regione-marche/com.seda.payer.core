/**
 * 
 */
package com.seda.j2ee5.maf.core.flow;

import java.io.Serializable;
import java.util.HashMap;

import com.seda.commons.string.Convert;
import com.seda.j2ee5.maf.core.screen.ScreenRedirectData;
import com.seda.j2ee5.maf.core.screen.ScreenWriterData;
import com.seda.j2ee5.maf.util.URLUtil;

/**
 * @author f.ricci
 *
 */
public class FlowData implements Serializable {
	
	private static final long serialVersionUID = -6821168775130099289L;
	
	private String screen;
	private String applid;
	private HashMap<String, String> parameters;
	
	private ScreenWriterData screenWriterData;
	private ScreenRedirectData screenRedirectData;
	
	public String getScreen() {
		if (screen!=null)
			return screen.startsWith("/")?screen:"/"+screen;
		return screen;
	}
	public void setScreen(String screen) {
		if (screen!=null) {
			parameters=URLUtil.getParameterMap(screen);
			if (!parameters.isEmpty()) {
				this.screen=URLUtil.removeAfterQMark(screen);
			} else {
				this.screen=screen;
			}
		}
	}
	public HashMap<String, String> getParameters(){
		return parameters;
	}
	public boolean hasParameters(){
		return parameters.size()>0;
	}
	public String getApplid() {
		return applid;
	}
	public void setApplid(String applid) {
		this.applid = applid;
	}
	public ScreenWriterData getScreenWriterData() {
		return screenWriterData;
	}
	public void setScreenWriterData(ScreenWriterData screenWriterData) {
		this.screenWriterData = screenWriterData;
	}
	public ScreenRedirectData getScreenRedirectData() {
		return screenRedirectData;
	}
	public void setScreenRedirectData(ScreenRedirectData screenRedirectData) {
		this.screenRedirectData = screenRedirectData;
	}

	public boolean isScreen() {
		return screen!=null;
	}
	public boolean isWriter() {
		return screenWriterData!=null;
	}
	public boolean isRedirect() {
		return screenRedirectData!=null;
	}
	@Override
	public String toString() {
		return "FlowData [applid=" + applid + ", parameters=" + parameters
				+ ", screen=" + screen + ", screenRedirectData="
				+ screenRedirectData + ", screenWriterData=" + screenWriterData
				+ "]";
	}
}
