/**
 * 
 */
package com.seda.j2ee5.maf.core.session;

import java.io.Serializable;

import javax.servlet.http.HttpSessionEvent;

/**
 * @author dbadm
 *
 */
public class SessionHandler implements SessionSupport, Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * Creation session event
	 * 
	 */
	public SessionHandler (HttpSessionEvent se) throws SessionHandlerException {}
	
	/* (non-Javadoc)
	 * @see com.seda.j2ee5.maf.core.session.SessionSupport#activate(javax.servlet.http.HttpSessionEvent)
	 */
	public Object activate(HttpSessionEvent se) throws SessionHandlerException{
		return null;
	}

	/* (non-Javadoc)
	 * @see com.seda.j2ee5.maf.core.session.SessionSupport#destroyed(javax.servlet.http.HttpSessionEvent)
	 */
	public Object destroyed(HttpSessionEvent se) throws SessionHandlerException{
		return null;
	}

	/* (non-Javadoc)
	 * @see com.seda.j2ee5.maf.core.session.SessionSupport#passivate(javax.servlet.http.HttpSessionEvent)
	 */
	public Object passivate(HttpSessionEvent se) throws SessionHandlerException {
		return null;
	}

}
