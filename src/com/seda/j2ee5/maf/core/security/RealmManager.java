/**
 * 
 */
package com.seda.j2ee5.maf.core.security;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

/**
 * @author Seda Lab
 *
 */
public class RealmManager {

	
	private HashMap<String, ProtectedResource> realm;
	
	public RealmManager() {}
	
	public void init(HashMap<String, ProtectedResource> realm) {
    	this.realm=realm;
    }
	
	 /**
     * Return true if the role is in the resource role set
     */
    public boolean checkPassThrough(String resource, String role) {
    	boolean passThrough=false;
    	ArrayList<String> roles = getRoles(resource);

    	if (roles.size()==0) {
        	// if no roles associated to the provided resource
    		passThrough=true;
    	} else if (role==null || role.trim().length()==0) {
        	// if the provided role is null or is an empty string and the resource has roles
    		passThrough=false;
    	} else {
    		// loop through the roles and check for authorization
    		String realmRole;
    		for (Iterator<String> iterator = roles.iterator(); iterator.hasNext();) {
				realmRole = iterator.next();
				if (realmRole.equals(role)) {
					passThrough=true;
					break;
				}
			}
    	}
//    	if (!passThrough)
//    		System.out.println(String.format(" RealmManager Request access to %s using %s is out of realm: %s",resource, role, roles));
    	return passThrough;
    }	
	
	 /**
     * Return the roles associated to the target url or null if there is not related protected resource
     */
    public ArrayList<String> getRoles(String targetUrl) {
    	ArrayList<String> roles=new ArrayList<String>();
        if (realm != null)  {
        	ProtectedResource resource = getProtectedResource(targetUrl);
        	if (resource!=null) 
        		roles=resource.getRoles();
        } 
    	return roles;
    }	
	
    /**
     * Returns the ProtectedResource object associated to the target url or null if not found
     * 
     */
	public ProtectedResource getProtectedResource(String targetUrl) {
		ProtectedResource resource  = null;		
		 // find out if the patterns match the target URL
        Iterator<String> it = realm.keySet().iterator();
        while (it.hasNext()) {
            String protectedName = (String)it.next();
            resource  = (ProtectedResource)realm.get(protectedName);
            String urlPattern = resource.getURLPattern();
            // now check agains the targetURL
            if (urlPattern.equals(targetUrl)) {
            	return resource;
            }
        }
        return resource;
	}

	@Override
	public String toString() {
		return "RealmManager [realm=" + realm + "]";
	}
	
	
}
