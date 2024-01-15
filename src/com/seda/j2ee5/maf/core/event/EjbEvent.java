/**
 * 
 */
package com.seda.j2ee5.maf.core.event;

/**
 * This interface determines the required methods for a specific web event that will be processed in the ejb tier
 * 
 * @author Seda Lab
 *
 */
public interface EjbEvent extends Event {

	/**
	 * Specifies a class name of the ejb action used to process this event in the ejb tier.
	 */
	public void setEjbActionClass(String ejbActionClass);
	/**
	 * Returns the name of the ejb tier action class event processor 
	 */
	public String getEjbActionClass();
	
}
