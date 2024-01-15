/**
 * 
 */
package com.seda.j2ee5.maf.core.security;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

//import org.apache.log4j.Logger;

import com.seda.j2ee5.maf.core.action.ActionData;
//import com.seda.j2ee5.maf.util.MAFLogger;

/**
 * @author Seda Lab
 *
 */
public class RealmRolesManager {

//	private static final Logger logger = MAFLogger.getLogger("RealmRolesManager");
	
	private HashMap<String, HashMap<String, ProtectedAction>> realm;
	
	private HashMap<String, HashMap<String, ProtectedAction>> getRealm() {
		if (realm==null)
			realm = new HashMap<String, HashMap<String, ProtectedAction>>();
		return realm;
	}

	public RealmRolesManager() {}
	
	public void putRelamActionsData(String applid, HashMap<String, ActionData> actionsData) {
		HashMap<String, ProtectedAction> protectedActions = new HashMap<String, ProtectedAction>();
		Set<String> actionsSet = actionsData.keySet();
		for (Iterator<String> iterator = actionsSet.iterator(); iterator.hasNext();) {
			String actionURL = iterator.next();
			ActionData action = actionsData.get(actionURL);
			ArrayList<String> roles = action.getRoles();
			if (roles!= null && roles.size()>0) {
				protectedActions.put(actionURL,new ProtectedAction(actionURL, roles));
			}
		}
		putRelamItem(applid, protectedActions);
	}	
	
	private void putRelamItem(String applid, HashMap<String, ProtectedAction> protectedActions) {
		if (protectedActions!=null && protectedActions.size()>0) {
			getRealm().put(applid, protectedActions);
		}
	}
 /**
     * Return true if the profile is in the resource profile set
     */
    public boolean checkPassThrough(String applid, String actionURL, String role) {
    	boolean passThrough=false;
    	HashMap<String, ProtectedAction> protectedActions = getProtectedActions(applid);
    	ArrayList<String> roles=null;
    	if (protectedActions== null || protectedActions.size()==0) {
    		// if no protected action associated to the provided application
    		passThrough=true;
    	} else {
    		ProtectedAction protectedAction = protectedActions.get(actionURL);
    		if (protectedAction!=null) {
        		roles = protectedAction.getRoles();
        		if (roles==null || roles.size()==0) {
                	// if no roles associated to the provided actionURL
            		passThrough=true;
            	} else if (role==null || role.trim().length()==0) {
                	// if the provided profile is null or an empty string and the resource has roles
//            		System.out.println(String.format("[%s] A - Request access to %s using %s is out of realm: %s",applid,actionURL,role,roles));
            		passThrough=false;
            	} else {
            		// loop through the profiles and check for authorization
            		String realmRole;
            		for (Iterator<String> iterator = roles.iterator(); iterator.hasNext();) {
        				realmRole = iterator.next();
//        				System.out.println(String.format("[%s] checking for realmrole(%s) role(%s)",applid,realmRole,role));
        				if (realmRole.equals(role)) {
        					passThrough=true;
        					break;
        				}
        			}
            	}    			
    		} else {
    			// if the action is not in the protected action list
//    			System.out.println(String.format("[%s] C - Request access to %s using %s has a null realm",applid,actionURL,role));
    			passThrough=false;    			
    		}
    	}
//    	if (!passThrough)
//			System.out.println(String.format("[%s] X - Request access to %s using %s is out of realm: %s",applid,actionURL,role,roles));
    	return passThrough;
    }	
	
    /**
     * Return the protected action related to the target applid or null if there is not related protected action
     */    
	private HashMap<String, ProtectedAction> getProtectedActions(String applid) {
		HashMap<String, ProtectedAction> protectedActions = null;
		 if (realm!=null) {
			 protectedActions=realm.get(applid);
		 }
		 return protectedActions;
	}

	@Override
	public String toString() {
		return "RealmRolesManager [realm=" + realm + "]";
	}

	
	
}
