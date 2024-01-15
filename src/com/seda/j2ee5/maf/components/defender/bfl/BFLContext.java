/**
 * 
 */
package com.seda.j2ee5.maf.components.defender.bfl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.seda.commons.logger.CustomLoggerManager;
import com.seda.commons.logger.LoggerWrapper;
import com.seda.j2ee5.maf.core.security.SignOnRules;
import com.seda.j2ee5.maf.util.MAFLogger;
/**
 * @author f.ricci
 *
 */
public class BFLContext {

	private static final LoggerWrapper logger =  CustomLoggerManager.get(BFLContext.class);

	// Logins cache
	private Map<String, BFLUser> loginAttempts;
	
	//
	private static BFLContext me;
	private int maxAttempts;
	private long lockTimeout;
	
	static {
    	try {
    		me = new BFLContext();
    	} catch(Exception se) {
    		logger.error(MAFLogger.getMessage("generic_exception"), se);
    	}
    }
	
	private BFLContext() {
   		loginAttempts = Collections.synchronizedMap(new HashMap<String, BFLUser>());
    }

	public static BFLContext getInstance() {
		return me;
	}
	// -------------
	public void initialize(SignOnRules signOnRules) {
		if (signOnRules==null) {
			initialize(SignOnRules.ATTEMPTS,SignOnRules.LOCKED_TIMEOUT);
		} else {
			initialize(signOnRules.getLoginAttempts(), signOnRules.getLoginLockedTimeout());			
		}
	}
	public void initialize(int maxAttempts, int lockTimeout) {
		loginAttempts.clear();
		this.maxAttempts=maxAttempts;
		this.lockTimeout=lockTimeout*60*1000;
	}
	/**
	 * Removes the provided <code>userName</code> from the attempts buffer 
	 */
	public boolean remove(String userName) {
		return loginAttempts.remove(userName)!=null;
	}
	/**
	 * Returns a BFLUser clone related to the <code>userName</code> 
	 */
	public BFLUser show(String userName) {
		if (loginAttempts.containsKey(userName)) {
			return loginAttempts.get(userName).clone();
		}
		return null;
		
	}
	
	/**
	 * Returns the list of clone for all BFLUser in the attempts buffer 
	 */
	public List<BFLUser> show() {
		List<BFLUser> bflUserList = new ArrayList<BFLUser>();
		// checks for global rule set
		Enumeration<String> e = Collections.enumeration(loginAttempts.keySet());
		while (e.hasMoreElements()) {
			String userName = (String) e.nextElement();
			bflUserList.add(loginAttempts.get(userName).clone());
		}
		return bflUserList;
	}

	/**
	 * Locks the provided <code>userName</code> 
	 */
	public boolean lock(String userName) {
		boolean b=false;
		if (loginAttempts.containsKey(userName)) {
			loginAttempts.get(userName).lock();
			b=true;
		}
		return b;
	}
	/**
	 * Resets the provided <code>userName</code> 
	 */	
	public boolean reset(String userName) {
		boolean b=false;
		if (loginAttempts.containsKey(userName)) {
			loginAttempts.get(userName).reset();
			b=true;
		}
		return b;
	}
	/**
	 * Checks for a new attempt of the provided <code>userName</code> and <code>ip</code> 
	 */	
	public void doAttempt(String userName, String fromIP) throws BFLLockedException {
		BFLUser bflUser = loginAttempts.get(userName);
		if (bflUser==null) {
			bflUser = new BFLUser(userName, fromIP, lockTimeout, maxAttempts);
			loginAttempts.put(userName,bflUser);
		}
		bflUser.upAttempts(fromIP);
	}
}
