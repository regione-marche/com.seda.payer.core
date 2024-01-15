/**
 * 
 */
package com.seda.commons.management.agents;

import javax.management.ObjectName;

/**
 * @author f.ricci
 *
 */
public class RMIAdaptorDelegate extends AbtractAdaptorDelegate {

	private RMIAdaptorConnector _rmiAdaptorConnector;
	private String _clientURL=null;
	
    public boolean isActive() {
    	return _rmiAdaptorConnector.isActive();
    }
    
    public String getClientURL() {
    	return _clientURL;
    }

	public RMIAdaptorDelegate(String domain) {
		super(domain);
	}    
    
	public void start() throws AdaptorDelegateException {
		startConnector();
	}
    
	public void stop() throws AdaptorDelegateException {
		stopConnector();
	}

	private void startConnector() throws AdaptorDelegateException {
		if (_server==null) {
			throw new AdaptorDelegateException("MBeanServer null");
		}
		
		if (_rmiAdaptorConnector!=null) {
			stopConnector();
		}
		_clientURL="service:jmx:rmi:///jndi/rmi://"+getIp()+":"+_port+"/jmxrmi";
		try {
			_rmiAdaptorConnector = new RMIAdaptorConnector("jmxrmi");
			_rmiAdaptorConnector.setPort(_port);
			_adaptorName = new ObjectName(_domain+":name=rmi,port="+_port);
			_server.registerMBean(_rmiAdaptorConnector, _adaptorName );
			_rmiAdaptorConnector.start();
		} catch (Exception e) {
			stopConnector();
			throw new AdaptorDelegateException(e);
		}  
	}

	private void stopConnector() throws AdaptorDelegateException {
		try {
			if (_rmiAdaptorConnector!=null && _rmiAdaptorConnector.isActive()) {
				_rmiAdaptorConnector.stop();					
			}			
		} catch (Exception x) {
			throw new AdaptorDelegateException(x);
		} finally {
			try {
				unregister();
			} catch (Exception e) {
				// we cannot do anything is this case
			} 
			_rmiAdaptorConnector=null;
		}
	}

}
