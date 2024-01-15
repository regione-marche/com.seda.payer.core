/**
 * 
 */
package com.seda.j2ee5.maf.core.event;

import java.io.Serializable;

/**
 * @author Seda Lab
 *
 */
public interface Event extends Serializable {

	/**
	 * Returns the name of this event
	 */
	public String getEventName();
}
