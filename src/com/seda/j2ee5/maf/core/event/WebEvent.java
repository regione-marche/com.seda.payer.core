/**
 * 
 */
package com.seda.j2ee5.maf.core.event;

/**
 * This class determines the required methods for a specific web event that 
 * will be processed in the web tier
 * 
 * @author Seda Lab
 *
 */
public abstract class WebEvent implements Event {

	private String webClassName = null;
	/**
	 * Returns the name of the web tier class that will be fired 
	 */
	public String getWebClassName() {
		return webClassName;
	}

	protected void setWebClassName(String webClassName) {
		this.webClassName=webClassName;
	}

	public abstract String getEventName();
}
