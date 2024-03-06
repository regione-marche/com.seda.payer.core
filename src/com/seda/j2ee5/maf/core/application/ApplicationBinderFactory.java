/**
 * 
 */
package com.seda.j2ee5.maf.core.application;

import java.lang.reflect.Field;
import java.util.Collections;
import java.util.Enumeration;
import java.util.HashMap;

import com.seda.commons.logger.CustomLoggerManager;
import com.seda.commons.logger.LoggerWrapper;
import com.seda.j2ee5.maf.core.security.UserBeanApplicationBinder;

/**
 * @author f.ricci
 *
 */
public class ApplicationBinderFactory {

	private static final LoggerWrapper logger =  CustomLoggerManager.get(ApplicationBinderFactory.class);

	private HashMap<String, Class<UserBeanApplicationBinder>> applicationBinderMap;
	
	private static ApplicationBinderFactory me;
	
	static {
		me = new ApplicationBinderFactory();
	}
	
	public static ApplicationBinderFactory instance(){return me;}
	
	public void loadBinderMap(HashMap<String,String> binderMap) {
		applicationBinderMap=new HashMap<String, Class<UserBeanApplicationBinder>>(binderMap.size());
		Enumeration binderKeys = Collections.enumeration(binderMap.keySet());
		while (binderKeys.hasMoreElements()) {
			String applid = (String) binderKeys.nextElement();
			String className = binderMap.get(applid);
			if (className==null || className.trim().length()==0) continue;
			Class<UserBeanApplicationBinder> binderClass;
			try {
				binderClass = (Class<UserBeanApplicationBinder>) Class.forName(className);
				applicationBinderMap.put(applid, binderClass);
			} catch (ClassNotFoundException e) {
				logger.error("Unable to load binder class "+className,e);
			}
		}
	}
	
	public ApplicationBinder getApplicationBinder() {
		return applyMap(new ApplicationBinder());
	}

	private ApplicationBinder applyMap(ApplicationBinder object) {
		Class<?> currentClass = object.getClass();
		while (currentClass != null) {
			// we also need to look for interface methods - 
			// because the class may be abstract
			Field[] arrFields = currentClass.getDeclaredFields();
			for (int i = 0; i < arrFields.length; i++) {
				if (arrFields[i].getName().equals("applicationBinderMap")) {
					if (!arrFields[i].isAccessible()) arrFields[i].setAccessible(true);
					try {
						arrFields[i].set(object, applicationBinderMap);
					} catch (Exception e) {
						logger.error("Unable to set 'applicationBinderMap' in object " + object.getClass().getName() + ", current class " + currentClass,e);
					} 
					break;
				}
			}
			currentClass = currentClass.getSuperclass();
		}
		return object;
	}
	
}
