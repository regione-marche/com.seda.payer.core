/**
 * 
 * 
 */
package com.seda.commons.management;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import javax.management.Attribute;
import javax.management.AttributeList;
import javax.management.AttributeNotFoundException;
import javax.management.DynamicMBean;
import javax.management.InstanceAlreadyExistsException;
import javax.management.InstanceNotFoundException;
import javax.management.IntrospectionException;
import javax.management.InvalidAttributeValueException;
import javax.management.ListenerNotFoundException;
import javax.management.MBeanAttributeInfo;
import javax.management.MBeanConstructorInfo;
import javax.management.MBeanException;
import javax.management.MBeanInfo;
import javax.management.MBeanNotificationInfo;
import javax.management.MBeanOperationInfo;
import javax.management.MBeanParameterInfo;
import javax.management.MBeanRegistration;
import javax.management.MBeanRegistrationException;
import javax.management.MBeanServer;
import javax.management.Notification;
import javax.management.NotificationBroadcaster;
import javax.management.NotificationBroadcasterSupport;
import javax.management.NotificationFilter;
import javax.management.NotificationListener;
import javax.management.ObjectName;
import javax.management.ReflectionException;
import javax.management.RuntimeErrorException;

/**
 * This pattern describes the use of a facade and provides three key benefits:<br/>
 * <ul>
 * <li>Metadata creation is hidden behind the facade, resulting in cleaner, more readable code.</li>
 * <li>Metadata creation is less error-prone for attributes and operations because the reflection API is used 
 * to introspect the managed resource class to ensure correct data types.</li>  
 * <li>The number of lines of code to be maintained in your application is reduced because the 
 * DynamicMBean implementation is hidden behind the facade class and can be reused for every 
 * MBean in your application.</li>
 * </ul>  
 * @author f.ricci
 *
 */
public class DynamicMBeanFacade implements DynamicMBean, NotificationBroadcaster, MBeanRegistration {

	final List<MBeanAttributeInfo> _attributesInfo = Collections.synchronizedList(
		new ArrayList<MBeanAttributeInfo>());

	final List<MBeanOperationInfo> _operationsInfo = Collections.synchronizedList(
		new ArrayList<MBeanOperationInfo>());
	
	final List<MBeanNotificationInfo> _notificationsInfo = Collections.synchronizedList(
		new ArrayList<MBeanNotificationInfo>());

	final List<MBeanConstructorInfo> _constructorsInfo = Collections.synchronizedList(
			new ArrayList<MBeanConstructorInfo>());
	
	NotificationBroadcasterSupport _broadcaster = new NotificationBroadcasterSupport();


	protected String _description;
	protected Object _resource;
	protected ObjectName _objectName;
	protected MBeanServer _server;
	private MBeanInfo _mbi;

	private Introspection _introspection=null;

	public Introspection getIntrospection() {
		return _introspection;
	}
	
	public synchronized ObjectName preRegister(MBeanServer mbs, ObjectName objectName) throws InstanceAlreadyExistsException {
		if (_objectName != null) {
			throw new InstanceAlreadyExistsException("Already registered as: " + objectName);
		}
		_server = mbs;
		_objectName = objectName;
		return _objectName;
	}

	public void postRegister(Boolean registrationDone) {
	}

	public synchronized void preDeregister() {
		_server = null;
		_objectName = null;
	}

	public void postDeregister() {
	}
	/*
	 * It is ugly for the managedObject to contain explicit logic to unregister its MBean. Much better is for it to contain logic that allows its users to register a callback that is invoked when the cache is destroyed
	 */
	protected synchronized void unregisterMe() {
		if (_objectName != null) {
			try {
				if (_server.isRegistered(_objectName))
					_server.unregisterMBean(_objectName);
			} catch (InstanceNotFoundException e) {
				// The MBean specified is not registered in the MBean server
			} catch (MBeanRegistrationException e) {
				// The preDeregister ((MBeanRegistration interface) method of the MBean has thrown an exception
			}
		}
	}

	/**
	 * The callback that must be invoked when the managedObject is destroyed 
	 */
	public final Runnable destroyHandler = new Runnable() {
		public void run() {
			unregisterMe();
		}
	};

	
	/**
	 * This constructor is provided so that this class can be subclassed.
	 */
	public DynamicMBeanFacade() {
		_resource = this;
		_description = this.getClass().getName();
		_introspection=Introspection.forClass(_resource.getClass());
		// Create an empty MBeanInfo object so we're not constantly
		/// checking "if != null" while dynamically building the
		/// management interface. . .
		_mbi=createMbeanInfo();
	}
	/** 
	 * Creates a new instance of BaseDynamicMBean
	 */
	public DynamicMBeanFacade(Object managedResource) throws ManagementException {
		this(managedResource, managedResource.getClass().getName());
	}
	
	public DynamicMBeanFacade(Object resource, String description) throws ManagementException{
		if (resource == null)
			throw new ManagementException("The resource reference cannot be null!");

		_resource = resource;
		_description = description!=null?description:resource.getClass().getName();
		_introspection=Introspection.forClass(_resource.getClass());
		
		_mbi=createMbeanInfo();
	}

	public void addROAttribute(String name, String description) throws ManagementException {
		if (name == null || name.equals(""))
			throw new ManagementException("Name cannot be null!");
		
		if (!_introspection._attributesName.contains(name)) 
			throw new ManagementException("Attribute \'" + name + "\' not found.");

		
		Method readMethod = _introspection._getters.get(name);
		
        MBeanAttributeInfo info = null;
        
        try {
			info = new MBeanAttributeInfo(
			        name,
			        description,
			        readMethod,
			        null);
		} catch (IntrospectionException e) {
			throw new ManagementException(e);
		}
		
		if (_attributesInfo.contains(info)) 
			throw new ManagementException("Duplicate attributes definition " + name);
		
		_attributesInfo.add(info);
		
		updateMbeanInfo();
	}
	
	public synchronized void addAttribute(String name, String description) throws ManagementException {
		if (name == null || name.equals(""))
			throw new ManagementException("Name cannot be null!");
		
		if (!_introspection._attributesName.contains(name)) 
			throw new ManagementException("Attribute \'" + name + "\' not found.");

		
		Method readMethod = _introspection._getters.get(name);
        Method writeMethod = _introspection._setters.get(name);
        boolean readable = null != readMethod;
        boolean writable = null != writeMethod;
        
        MBeanAttributeInfo info = null;
        
        try {
			info = new MBeanAttributeInfo(
			        name,
			        description,
			        readable ? readMethod : null,
			        writable ? writeMethod : null);
		} catch (IntrospectionException e) {
			throw new ManagementException(e);
		}
		
		if (_attributesInfo.contains(info)) 
			throw new ManagementException("Duplicate attributes definition " + name);
		
		_attributesInfo.add(info);
		
		updateMbeanInfo();
	}
	
	public synchronized void removeAttribute(String name) throws ManagementException {
		if (name == null || name.equals(""))
			throw new ManagementException("Name cannot be null!");
		
		Iterator<MBeanAttributeInfo> iterator = _attributesInfo.iterator();
		while (iterator.hasNext()) {
			MBeanAttributeInfo mBeanAttributeInfo = (MBeanAttributeInfo) iterator.next();
			if (mBeanAttributeInfo.getName().equals(name)) {
				iterator.remove();
				break;
			}
		}
		
		updateMbeanInfo();
	}

	public synchronized void addOperation(String name, String description) throws ManagementException {
		addOperation(name, description, Impact.UNKNOWN.impactValue, null);
	}
	
	
	public synchronized void addOperation(String name, String description, int impactValue, MBeanParameterInfo[] parametersInfo) throws ManagementException {
		if (name == null || name.equals(""))
			throw new ManagementException("Name cannot be null!");
		
		MBeanOperationInfo opInfo = null;
		
		for (Method operation : _introspection._operations) {
			if (operation.getName().equals(name)) {
				opInfo = new MBeanOperationInfo(
		                name,
		                description,
		                parametersInfo==null?createBasicParameterInfo(operation):parametersInfo,
		                operation.getReturnType().getName(),
		                impactValue);
				
				if (_operationsInfo.contains(opInfo)) 
					throw new ManagementException("Duplicate operation definition " + name);
				break;
			}
		}
		
		if (opInfo==null)
			throw new ManagementException("Operation '" + name + "' not found.");
		
		_operationsInfo.add(opInfo);
		
		updateMbeanInfo();
		
	}
	
	public synchronized void removeOperation(String name) throws ManagementException {
		if (name == null || name.equals(""))
			throw new ManagementException("Name cannot be null!");
		
		Iterator<MBeanOperationInfo> iterator = _operationsInfo.iterator();
		while (iterator.hasNext()) {
			MBeanOperationInfo mBeanOperationInfo = (MBeanOperationInfo) iterator.next();
			if (mBeanOperationInfo.getName().equals(name)) {
				iterator.remove();
				break;
			}
		}
		
		updateMbeanInfo();
	}
	
	private MBeanParameterInfo[] createBasicParameterInfo(Method method) {
		MBeanParameterInfo[] parameters = new MBeanParameterInfo[method.getParameterTypes().length];
        for (int parameterIndex = 0; parameterIndex < parameters.length; parameterIndex++) {
            final String pType = method.getParameterTypes()[parameterIndex].getName();
            final String pName = "Parameter-" + (parameterIndex + 1); // 1 .. n
            final String pDesc = "unknown";
            parameters[parameterIndex] = new MBeanParameterInfo(pName, pType, pDesc);
        }
        return parameters;
	}

	public synchronized void addNotification(String type, String description) throws ManagementException {
		if (type == null || type.equals(""))
			throw new ManagementException("Type cannot be null!");

		if (!(_resource instanceof NotificationBroadcaster))
			throw new ManagementException(
			"Managed resource does not implement NotificationBroadcaster");

		MBeanNotificationInfo notif = new MBeanNotificationInfo(
				new String[] {type},
				"javax.management.Notification",
				description);

		if (_notificationsInfo.contains(notif)) 
			throw new ManagementException("Duplicate notification " + type);
		
		_notificationsInfo.add(notif);
		
		updateMbeanInfo();
	}
	
	public synchronized void removeNotification(String type) throws ManagementException {
		if (type == null || type.equals(""))
			throw new ManagementException("Type cannot be null!");

		Iterator<MBeanNotificationInfo> iterator = _notificationsInfo.iterator();
		while (iterator.hasNext()) {
			MBeanNotificationInfo mBeanNotificationInfo = (MBeanNotificationInfo) iterator.next();
			// copy in all metadata except what we're looking for. . .
			String[] types = mBeanNotificationInfo.getNotifTypes();
			if (!(types[0].equals(type))) {
				iterator.remove();
				break;
			}
		}
		
		updateMbeanInfo();
	}
	
	private void updateMbeanInfo() {
		synchronized (_mbi) {
			_mbi=createMbeanInfo();
		}
	}
	
	private MBeanInfo createMbeanInfo() {
        return new MBeanInfo(
                _resource.getClass().getName(),
                _description,
                _attributesInfo.toArray(new MBeanAttributeInfo[_attributesInfo.size()]),
                _constructorsInfo.toArray(new MBeanConstructorInfo[_constructorsInfo.size()]),
                _operationsInfo.toArray(new MBeanOperationInfo[_operationsInfo.size()]),
                _notificationsInfo.toArray(new MBeanNotificationInfo[_notificationsInfo.size()]));
    }
	
	public Object getAttribute(String attribute) throws AttributeNotFoundException, MBeanException, ReflectionException {
		final Method get = _introspection._getters.get(attribute);
		if (get == null) throw new AttributeNotFoundException(attribute);
		try {
			return get.invoke(_resource);
		} catch (IllegalArgumentException ex) {
			throw new ReflectionException(ex);
		} catch (InvocationTargetException ex) {
			final Throwable cause = ex.getCause();
			if (cause instanceof Exception) 
				throw new MBeanException((Exception)cause);
			throw new RuntimeErrorException((Error)cause);
		} catch (IllegalAccessException ex) {
			throw new ReflectionException(ex);
		}
	}

	public void setAttribute(Attribute attribute) throws AttributeNotFoundException, InvalidAttributeValueException, 
	MBeanException, ReflectionException {
		final Method set = _introspection._setters.get(attribute.getName());
		if (set == null) 
			throw new AttributeNotFoundException(attribute.getName());
		try {
			set.invoke(_resource,attribute.getValue());
		} catch (IllegalArgumentException ex) {
			throw new ReflectionException(ex);
		} catch (InvocationTargetException ex) {
			final Throwable cause = ex.getCause();
			if (cause instanceof Exception) 
				throw new MBeanException((Exception)cause);
			throw new RuntimeErrorException((Error)cause);
		} catch (IllegalAccessException ex) {
			throw new ReflectionException(ex);
		}
	}

	public AttributeList getAttributes(String[] attributes) {
		if (attributes == null) return new AttributeList();
		AttributeList result = new AttributeList();
		for (String attr : attributes) {
			try {
				result.add(new Attribute(attr,getAttribute(attr)));
			} catch (Exception x) {
				continue;
			}
		}
		return result;
	}

	public AttributeList setAttributes(AttributeList attributes) {
		if (attributes == null) return new AttributeList();
		AttributeList result = new AttributeList();
		for (Object item : attributes) {
			final Attribute attr = (Attribute)item;
			final String name = attr.getName();
			final Method get = _introspection._setters.get(name);
			try {
				setAttribute(attr);
				final Object newval = (get==null)?attr.getValue():getAttribute(name);
				result.add(new Attribute(name,newval));
			} catch (Exception x) {
				continue;
			}
		}
		return result;
	}

	public Object invoke(String actionName, Object[] params, String[] signature) 
	throws MBeanException, ReflectionException {
		Method toInvoke = null;
		if (params == null) params = new Object[0];
		if (signature == null) signature = new String[0];
		for (Method m : _introspection._operations) {
			if (!m.getName().equals(actionName)) continue;
			final Class<?>[] sig = m.getParameterTypes();
			if (sig.length == params.length) {
				if (sig.length == 0) toInvoke=m;
				else if (signature.length == sig.length) {
					toInvoke = m;
					for (int i=0;i<sig.length;i++) {
						if (!sig[i].getName().equals(signature[i])) {
							toInvoke = null; 
							break;
						}
					}
				}
			}
			if (toInvoke != null) break;
		}
		if (toInvoke == null) 
			throw new ReflectionException(new NoSuchMethodException(actionName));
		try {
			return toInvoke.invoke(_resource,params);
		} catch (IllegalArgumentException ex) {
			throw new ReflectionException(ex);
		} catch (InvocationTargetException ex) {
			final Throwable cause = ex.getCause();
			if (cause instanceof Exception) 
				throw new MBeanException((Exception)cause);
			throw new RuntimeErrorException((Error)cause);
		} catch (IllegalAccessException ex) {
			throw new ReflectionException(ex);
		}
	}

	public MBeanInfo getMBeanInfo() {
		return _mbi;
	}

	/**
	 * Adds a listener.
	 *
	 * @param listener The listener to receive notifications.
	 * @param filter The filter object. If filter is null, no filtering will be performed before handling notifications.
	 * @param handback An opaque object to be sent back to the listener when a notification is emitted. This object
	 * cannot be used by the Notification broadcaster object. It should be resent unchanged with the notification
	 * to the listener.
	 *
	 * @exception IllegalArgumentException thrown if the listener is null.
	 *
	 * @see #removeNotificationListener
	 */
	public void addNotificationListener(NotificationListener listener,
			NotificationFilter filter,
			Object handback) {

		_broadcaster.addNotificationListener(listener, filter, handback);
	}

	public void removeNotificationListener(NotificationListener listener) throws ListenerNotFoundException {

		_broadcaster.removeNotificationListener(listener);
	}

	public MBeanNotificationInfo[] getNotificationInfo() {
		return _mbi.getNotifications();
	}

	/**
	 * Sends a notification.
	 *   
	 * @param notification The notification to send.
	 */
	public void sendNotification(Notification notification) {
		_broadcaster.sendNotification(notification);
	}

	
	/**
	 * This class allow fast programmatically parameterization
	 * @author f.ricci
	 *
	 * @param <T>
	 */
	public static class Parametrizer<T extends DynamicMBeanFacade> {

		private T  dmbean;
		private String defaultDescription = "-missing description-";

		public Parametrizer(T resource) throws ManagementException {
			dmbean = resource;
		}

		public Parametrizer<T> addAttribute(String name) throws ManagementException {
			return addAttribute(name, defaultDescription);
		}
		public Parametrizer<T> addAttribute(String name, String description) throws ManagementException {
			dmbean.addAttribute(name, description);
			return Parametrizer.this;
		}
		public Parametrizer<T> addOperation(String name) throws ManagementException {
			return addOperation(name, defaultDescription);
		}
		public Parametrizer<T> addOperation(String name, String description) throws ManagementException {
			dmbean.addOperation(name, description);
			return Parametrizer.this;
		}
		public Parametrizer<T> addNotification(String type) throws ManagementException {
			return addNotification(type, defaultDescription);
		}
		public Parametrizer<T> addNotification(String type, String description) throws ManagementException {
			dmbean.addNotification(type, description);
			return Parametrizer.this;
		}

		public T getDynamicMBeanFacade() {
			return dmbean;
		}
	}

	
	
} 
