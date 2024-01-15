/**
 * 
 */
package com.seda.j2ee5.maf.core.action.impl;

import javax.servlet.http.HttpServletRequest;

import com.seda.j2ee5.maf.core.action.ActionException;
import com.seda.j2ee5.maf.core.action.HtmlAction;

/**
 * @author f.ricci
 *
 */
public class DummyAction extends HtmlAction {

	/* (non-Javadoc)
	 * @see com.seda.j2ee5.maf.core.action.ActionService#service(javax.servlet.http.HttpServletRequest)
	 */
	public Object service(HttpServletRequest request) throws ActionException {
		return null;
	}

}
