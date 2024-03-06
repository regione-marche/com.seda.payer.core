package com.seda.commons.event;

/**
 * Registration objects returned when an event handler is bound (e.g. via
 * {@link EventBus#addHandler}), used to deregister.
 * <p>
 * A tip: to make a handler deregister itself try something like the following:
 * <code><pre>new MyHandler() {
 *  HandlerRegistration reg = MyEvent.register(eventBus, this);
 * 
 *  public void onMyThing(MyEvent event) {
 *    {@literal /}* do your thing *{@literal /}
 *    reg.removeHandler();
 *  }
 * };
 * </pre></code>
 */
public interface HandlerRegistration {

	/**
	 * Deregisters the handler associated with this registration object if the
	 * handler is still attached to the event source. If the handler is no longer
	 * attached to the event source, this is a no-op.
	 */
	void removeHandler();
}