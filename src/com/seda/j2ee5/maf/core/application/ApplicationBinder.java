/**
 * 
 */
package com.seda.j2ee5.maf.core.application;

import java.util.Collections;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;

import com.seda.commons.logger.CustomLoggerManager;
import com.seda.commons.logger.LoggerWrapper;
import com.seda.j2ee5.maf.core.action.ActionsXmlDao;
import com.seda.j2ee5.maf.core.security.UserBeanApplicationBinder;
import com.seda.j2ee5.maf.core.security.UserBeanSupport;
import com.seda.j2ee5.maf.util.MAFLogger;

/**
 * @author f.ricci
 *
 */
public class ApplicationBinder {

	private static final LoggerWrapper logger =  CustomLoggerManager.get(ApplicationBinder.class);
	
	private HashMap<String, Class<UserBeanApplicationBinder>> applicationBinderMap;
	
	
	public void bind(HttpServletRequest hreq, UserBeanSupport userBean) {
		Enumeration binderKey = Collections.enumeration(applicationBinderMap.keySet());
		while (binderKey.hasMoreElements()) {
			String applid = (String) binderKey.nextElement();
			Class<UserBeanApplicationBinder> binderClass = applicationBinderMap.get(applid); 
			UserBeanApplicationBinder binder;
			try {
				binder = (UserBeanApplicationBinder)binderClass.newInstance();
				binder.init(hreq);
				String role = binder.bind(userBean);
				binder.end();
				userBean.setRole(applid, role==null?"":role);
			} catch (Exception x) {
				logger.error(MAFLogger.getMessage("entry_manager_binder_error",binderClass,applid),x);
			} finally {
				binder=null;
			}
		}
       
	}
	
}
