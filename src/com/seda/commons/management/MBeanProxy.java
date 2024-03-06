/**
 * 
 */
package com.seda.commons.management;

import javax.management.MBeanServerConnection;
import javax.management.MBeanServerInvocationHandler;
import javax.management.ObjectName;

/**
 * For classic MBean
 * @author f.ricci
 *
 */
public class MBeanProxy {
	
	public static <T> T newProxy(MBeanServerConnection connection,
			ObjectName objectName,
			Class<T> interfaceClass) {
		return newProxy(connection, objectName, interfaceClass, false);
	}

	@SuppressWarnings("unchecked")
	public static <T> T newProxy(MBeanServerConnection connection,
			ObjectName objectName,
			Class<T> interfaceClass,
			boolean notificationBroadcaster) {
		return (T) MBeanServerInvocationHandler.newProxyInstance(
				connection,
				objectName,
				interfaceClass,
				notificationBroadcaster);
	}

}
