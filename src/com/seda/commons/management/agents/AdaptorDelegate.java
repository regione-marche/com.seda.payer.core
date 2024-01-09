/**
 * 
 */
package com.seda.commons.management.agents;

import javax.management.MBeanServer;


/**
 * @author f.ricci
 *
 */
public interface AdaptorDelegate {

	public boolean isActive();

	public void setPort(int port);
	public int getPort();

	public String getClientURL();

	public void setServer(MBeanServer server) throws AdaptorDelegateException;
	
	public void stop() throws AdaptorDelegateException;
	public void start() throws AdaptorDelegateException;
	
	@SuppressWarnings("serial")
	public class AdaptorDelegateException extends Exception {

		public AdaptorDelegateException(String message, Throwable cause) {
			super(message, cause);
		}
		public AdaptorDelegateException(String message) {
			super(message);
		}
		public AdaptorDelegateException(Throwable cause) {
			super(cause);
		}

	}
}
