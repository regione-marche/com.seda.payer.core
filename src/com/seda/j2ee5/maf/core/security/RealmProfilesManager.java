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
public class RealmProfilesManager {

	
	private HashMap<String, ProtectedApplication> realm;
	
	public RealmProfilesManager() {}
	
	public void init(HashMap<String, ProtectedApplication> realm) {
    	this.realm=realm;
    }
	 /**
     * Return true if the profile is in the resource profile set
     */
    public boolean checkPassThrough(String applid, String profile) {
    	boolean passThrough=false;
    	ArrayList<String> profiles = getProfiles(applid);

    	if (profiles==null || profiles.size()==0) {
        	// if no profiles associated to the provided resource
    		passThrough=true;
    	} else if (profile==null || profile.trim().length()==0) {
        	// if the provided profile is null or an empty string and the resource has roles
    		passThrough=false;
    	} else {
    		// loop through the profiles and check for authorization
    		String realmProfile;
    		for (Iterator<String> iterator = profiles.iterator(); iterator.hasNext();) {
				realmProfile = iterator.next();
				if (realmProfile.equals(profile)) {
					passThrough=true;
					break;
				}
			}
    	}

    	return passThrough;
    }	
	
	 /**
     * Return the profiles associated to the target applid or null if there is not related protected application
     */
    public ArrayList<String> getProfiles(String applid) {
    	ArrayList<String> profiles=null;
        if (realm != null)  {
        	ProtectedApplication resource = realm.get(applid);
        	if (resource!=null) 
        		profiles=resource.getProfiles();
        } 
    	return profiles;
    }

	@Override
	public String toString() {
		return "RealmProfilesManager [realm=" + realm + "]";
	}	
	
    
    
}
