/**
 * 
 */
package com.seda.j2ee5.maf.core.screen;

/**
 * @author f.ricci
 *
 */
public class ScreenRedirectData {

	private String id = null;
    private String url = null;
    
	public String getId() {
		return id;
	}
	
	public String getURL() {
		return url;
	}

	public ScreenRedirectData(String id, String url) {		
		this.id = id;
		this.url = url;
	}

	public boolean isInScope() {
		return url==null;
	}
	
	@Override
	public String toString() {
		return "ScreenRedirectData [id=" + id + ", url=" + url + "]";
	}
	
}
