/**
 * 
 */
package com.seda.j2ee5.maf.core.screen;

/**
 * @author Seda Lab
 *
 */
public class ScreenWriterData {

	private String id = null;
    private String scope = null;    
    private boolean inScope = false;
    private String contentType = null;
    private String attachId=null;    
    
	public String getId() {
		return id;
	}
	public String getScope() {
		return scope;
	}
	public String getContentType() {
		return contentType;
	}
	
	public boolean isInScope() {
		return inScope;
	}

	public String getAttachId() {
		return attachId;
	}
	public ScreenWriterData(String id, String scope, String contentType, boolean inScope, String attachId) {		
		this.id = id;
		this.scope = scope;
		this.contentType = contentType;
		this.inScope=inScope;
		this.attachId=attachId;
	}
	@Override
	public String toString() {
		return "ScreenWriterData [attachId=" + attachId + ", contentType="
				+ contentType + ", id=" + id + ", inScope=" + inScope
				+ ", scope=" + scope + "]";
	}  
}
