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
public class ProtectedApplication implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String applid;
	private ArrayList<String> profiles;
	
	public ProtectedApplication(String applid, ArrayList<String> profiles) {
		super();
		this.applid = applid;
		this.profiles = profiles;
	}
	public String getApplid() {
		return applid;
	}
	public void setApplid(String applid) {
		this.applid = applid;
	}
	public ArrayList<String> getProfiles() {
		return profiles;
	}
	public void setProfiles(ArrayList<String> profiles) {
		this.profiles = profiles;
	}
	
	@Override
	public String toString() {
		return "ProtectedApplication [applid=" + applid + ", profiles="
				+ profiles + "]";
	}
	
}
