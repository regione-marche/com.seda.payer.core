/**
 * 
 */
package com.seda.j2ee5.maf.core.event;

/**
 * @author f.ricci
 *
 */
public class EventResultImpl implements EventResult {

	private Throwable t;
	
	public String getResult() {
		return null;
	}

	public void setThrowable(Throwable t) {
		this.t = t;
	}

	public Throwable getThrowable() {
		return t;
	}
}
