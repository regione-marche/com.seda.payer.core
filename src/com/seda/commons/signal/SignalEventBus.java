/**
 * 
 */
package com.seda.commons.signal;

import java.util.HashMap;

import com.seda.commons.logger.CustomLoggerManager;
import com.seda.commons.logger.LoggerWrapper;
import com.seda.commons.params.DefaultParameters;

import sun.misc.Signal;
import sun.misc.SignalHandler;

/**
 * System signal event bus
 * 
 * @author f.ricci
 *
 */
public class SignalEventBus implements SignalHandler {

	private static final LoggerWrapper LOGGER =  CustomLoggerManager.get(SignalEventBus.class);
	
	private HashMap<String, SignalHandler> oldHandlers = new HashMap<String, SignalHandler>();
	private HashMap<String, SignalAction> actions = new HashMap<String, SignalAction>();
	
	private static SignalEventBus eventBus;
	
	static {
		eventBus=new SignalEventBus();
	}

	private SignalEventBus() {}
	
	public static SignalEventBus instance() {
		return eventBus;
	}
	
	public void install(String name, SignalAction action) {
		Signal diagSignal = new Signal(name);
        SignalHandler oldHandler = Signal.handle(diagSignal, eventBus);
        oldHandlers.put(name, oldHandler);
        actions.put(name, action);
	}
	
	public void handle(Signal signal) {
		String name = signal.getName(); 
if (LOGGER.isDebugEnabled()) {
		LOGGER.debug("action receveid '" + name + "'");
}
		SignalAction action = actions.get(name);
		SignalHandler oldHandler = oldHandlers.get(name);
		if (action!=null) {
			action.work(signal);
			// Chain back to previous handler, if one exists
			if (action.runOldHandler() && oldHandler != SIG_DFL && oldHandler != SIG_IGN) {
                oldHandler.handle(signal);
			}
		}
	}

}
