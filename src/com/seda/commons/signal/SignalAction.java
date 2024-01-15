/**
 * 
 */
package com.seda.commons.signal;

import sun.misc.Signal;

/**
 * This is the action done after system signal event
 * 
 * @author f.ricci
 *
 */
public interface SignalAction {

	/**
	 * Action method
	 */
	public void work(Signal signal);

	/**
	 * 
	 * @return true if the system signal event bus must be run the hold handler
	 */
	public boolean runOldHandler();	 

}
