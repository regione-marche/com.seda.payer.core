/**
 * 
 */
package com.seda.j2ee5.maf.core.security;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * @author Seda Lab
 *
 */
public class ProtectedAction implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String actionURL;
	private ArrayList<String> roles;
	
	public ProtectedAction(String actionURL, ArrayList<String> roles) {
		this.actionURL = actionURL;
		this.roles = roles;
	}
	public String getActionURL() {
		return actionURL;
	}
	public void setActionURL(String actionURL) {
		this.actionURL = actionURL;
	}
	public ArrayList<String> getRoles() {
		return roles;
	}
	public void setRoles(ArrayList<String> roles) {
		this.roles = roles;
	}

	@Override
	public String toString() {
		return "ProtectedApplication [actionURL=" + actionURL + ", roles="
				+ roles + "]";
	}

}
