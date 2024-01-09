/**
 * 
 */
package com.seda.commons.management.agents;

import java.lang.management.ManagementFactory;
import java.util.ArrayList;
import java.util.Set;

import javax.management.MBeanServer;
import javax.management.MBeanServerFactory;
import javax.management.ObjectName;

import com.seda.commons.management.agents.AdaptorDelegate.AdaptorDelegateException;
/**
 * @author f.ricci
 *
 */
public class ServiceAgent {

	protected MBeanServer _server = null;

	protected ArrayList<AdaptorDelegate> adaptors = new ArrayList<AdaptorDelegate>();
	
	protected String _domain = null;

	public MBeanServer getMBeanServer() {
		return _server;
	}

	public ServiceAgent(String domain){
		this(domain,true);
	}
	
	public ServiceAgent(String domain,boolean createServer){
		_domain=domain;
		_server = (createServer?MBeanServerFactory.createMBeanServer(_domain):ManagementFactory.getPlatformMBeanServer());
	}

	public void registerAdaptor(AdaptorDelegate adaptor) throws ServiceAgentException {
		if (adaptors.contains(adaptor)) {
			throw new ServiceAgentException("Adaptor " + adaptor + " already registered");
		}
		try {
			adaptor.setServer(_server);
		} catch (AdaptorDelegateException e) {
			throw new ServiceAgentException(e);
		}
		adaptors.add(adaptor);
	}
	
	public void start() throws ServiceAgentException {
		for (AdaptorDelegate adaptor : adaptors) {
			try {
				adaptor.start();
			} catch (AdaptorDelegateException e) {
				throw new ServiceAgentException(e);
			}
		}
	}

	public void stop() throws ServiceAgentException {
		for (AdaptorDelegate adaptor : adaptors) {
			try {
				adaptor.stop();
			} catch (AdaptorDelegateException e) {
				throw new ServiceAgentException(e);
			}
		}
	}

	public void addResource(ObjectName objName, Object resource) throws ServiceAgentException {
		if (_server!=null) {
			try {
				_server.registerMBean(resource, objName);
			} catch (Exception e) {
				throw new ServiceAgentException("General registration exception: " + e.getMessage());
			}			
		}
	}

	public void removeResource(ObjectName objectName) throws ServiceAgentException {
		if (_server!=null) {
			try {
				if (_server.isRegistered(objectName)) {
					_server.unregisterMBean(objectName);
				}
			} catch (Exception e) {
				throw new ServiceAgentException("General unregistration exception: " + e.getMessage());
			}
		}
	}

	public Set getResources(ObjectName objNameFilter) throws ServiceAgentException {
		Set resultSet = null;
		try	{
			resultSet = _server.queryMBeans(objNameFilter, null);
		} catch (Exception e) {
			throw new ServiceAgentException("Invalid object name:"
					+ e.getMessage());
		}
		return resultSet;
	}
	
	@SuppressWarnings("serial")
	public class ServiceAgentException extends Exception {

		public ServiceAgentException(String message, Throwable cause) {
			super(message, cause);
		}
		public ServiceAgentException(String message) {
			super(message);
		}
		public ServiceAgentException(Throwable cause) {
			super(cause);
		}
	}

}
