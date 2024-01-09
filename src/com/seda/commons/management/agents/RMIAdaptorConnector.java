/**
 * 
 */
package com.seda.commons.management.agents;

import java.io.IOException;
import java.rmi.NoSuchObjectException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

import javax.management.remote.JMXConnectorServer;
import javax.management.remote.JMXConnectorServerFactory;
import javax.management.remote.JMXServiceURL;

import com.seda.commons.management.DynamicMBeanFacade;
import com.seda.commons.management.ManagementException;
import com.seda.commons.management.annotations.Description;
import com.seda.commons.management.annotations.ManagedAttribute;
import com.seda.commons.management.annotations.ManagedOperation;
import com.seda.commons.management.util.AnnotationParameterizer;

/**
 * @author f.ricci
 *
 */
public class RMIAdaptorConnector extends DynamicMBeanFacade {

	public static String JAVA_VERSION = System.getProperty("java.specification.version");

	private Registry _registry;

	private JMXServiceURL _serviceURL=null;
	private JMXConnectorServer _connectorServer =null;
	private String _jmxContext=null;
	private int _port=2099;
	private boolean _active;

	public RMIAdaptorConnector(String jmxContext) {
		_description="JMX RMI connector";
		try {
			AnnotationParameterizer.parameterize(this);
		} catch (ManagementException e) {
		}
		_jmxContext=jmxContext;
	}    

	@ManagedAttribute @Description("Connector port")
	public int getPort() {
		return _port;
	}

	public void setPort(int port) {
		_port=port;
	}
	
	@ManagedAttribute @Description("Connector context in the jndi service url for remote connection")
	public String getJMXContext() {
		return _jmxContext;
	}
 
	public void setJMXContext(String jmxContext) {
		_jmxContext=jmxContext;
	}

	@ManagedAttribute @Description("Whether this connector is started")
	public boolean isActive() {
		return _active;
	}

	@ManagedOperation @Description("Start or restart this connector depending on its status")
	public void start() throws RMIConnectorException {
		startConnector();
	}
	@ManagedOperation @Description("Stop this connector")
	public void stop() throws RMIConnectorException {
		stopConnector();
	}

	private void startConnector() throws RMIConnectorException {
		if (_active) {
			stop();
		}
		try {
			createRMIRegistry();
			_serviceURL = new JMXServiceURL("service:jmx:rmi:///jndi/rmi://:"+_port+"/"+_jmxContext);
			_connectorServer = JMXConnectorServerFactory.newJMXConnectorServer(_serviceURL, null, _server);
			_connectorServer.start();
			_active=true;
		} catch (Exception e) {
			stopConnector();
			throw new RMIConnectorException(e);
		}  
	}

	private void createRMIRegistry() throws RemoteException {
		switch (JAVA_VERSION.charAt(2)) {
		case '3':
		case '4':
		case '5':
		case '6':
		case '7':
		default:
			createRegistry();
		}
	}

	private void createRegistry() throws RemoteException {
		_registry = LocateRegistry.createRegistry(_port);
	}

	private void stopConnector() throws RMIConnectorException {
		try {
			if (_connectorServer!=null && _connectorServer.isActive()) {
				_connectorServer.stop();
			}
		} catch (IOException e) {
			throw new RMIConnectorException(e);
		} finally {
			destroyRegistry();
			_active=false;
			_connectorServer=null;
		}
	}

	private void destroyRegistry() {
		if (_registry!=null) {
			try {
				UnicastRemoteObject.unexportObject(_registry, true);
			} catch (NoSuchObjectException e) {
				// nothing we can do here
			} finally {
				_registry=null;
			}
		}
	}


	@SuppressWarnings("serial")
	public class RMIConnectorException extends Exception {

		public RMIConnectorException(String message, Throwable cause) {
			super(message, cause);
		}

		public RMIConnectorException(String message) {
			super(message);
		}

		public RMIConnectorException(Throwable cause) {
			super(cause);
		}

	}
}
