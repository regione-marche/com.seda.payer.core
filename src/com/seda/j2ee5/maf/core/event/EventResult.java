/**
 * 
 */
package com.seda.j2ee5.maf.core.event;

/**
 * This interface maps a action event
 * 
 * @author Seda Lab
 *
 */
public interface EventResult {

	/**
	 *   Specifies a logical event result
	 */
	public String getResult();
	
	/**
	 * The unexpected throwable class
	 */
	public void setThrowable(Throwable t);
}
