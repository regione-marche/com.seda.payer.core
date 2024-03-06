/**
 * 
 */
package com.seda.j2ee5.maf.core.flow;

import java.io.Serializable;

import javax.servlet.http.HttpServletRequest;

/**
 * This is the base interface to flow handlers on the web tier
 * @author Seda Lab
 *
 */
public interface FlowManager extends Serializable {

	public void start(HttpServletRequest request);
    public String process(HttpServletRequest request) throws FlowException;
    public void end(HttpServletRequest request);	
	
}
