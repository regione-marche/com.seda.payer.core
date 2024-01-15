/**
 * 
 */
package com.seda.j2ee5.maf.core.flow.impl;

import javax.servlet.http.HttpServletRequest;

import com.seda.j2ee5.maf.core.flow.FlowException;

/**
 * @author f.ricci
 *
 */
public interface InterceptorChoose {

	public String intercept(String discoveredChoose, HttpServletRequest request) throws FlowException;
	
}
