/**
 * 
 */
package com.seda.commons.management.agents;

import java.net.InetAddress;
import java.net.UnknownHostException;

import javax.management.InstanceNotFoundException;
import javax.management.MBeanRegistrationException;
import javax.management.MBeanServer;
import javax.management.ObjectName;

import com.seda.commons.management.DynamicMBeanFacade;
import com.seda.commons.management.util.SocketUtil;

/**
 * @author f.ricci
 *
 */
public abstract class AbtractAdaptorDelegate extends DynamicMBeanFacade implements AdaptorDelegate {

	protected int _port=SocketUtil.findFreePort(); // is right a random port?
	
	protected ObjectName _adaptorName = null;
	protected MBeanServer _server=null;
	protected String _domain=null;

	public int getPort() {
		return _port;
	}
	public void setPort(int port) {
		_port=port;
	}

	public ObjectName getObjectName() {
		return _adaptorName;
	}
	
	public String getDomain() {
		return _domain;
	}
	
	public AbtractAdaptorDelegate(String domain) {
		_domain=domain;
	}

	public void setServer(MBeanServer server) throws AdaptorDelegateException {
		if (server==null) {
			throw new AdaptorDelegateException("MBeanServer null");
		}
		_server=server;
	}
	
	protected String getIp() {
        String ipAddrStr = "";
    	try {
	        byte[] ipAddr = InetAddress.getLocalHost().getAddress();
	        for (int i=0; i<ipAddr.length; i++) {
	            if (i > 0) ipAddrStr += ".";
	            ipAddrStr += ipAddr[i]&0xFF;
	        }
	    } catch (UnknownHostException e) {   }
	    
	    return ipAddrStr;
	}

	
	protected void unregister() throws MBeanRegistrationException, InstanceNotFoundException {
		if (_adaptorName!=null) {
			if (_server!=null && _server.isRegistered(_adaptorName)) {
				_server.unregisterMBean(_adaptorName);
			}
		}
	}
	
	public abstract String getClientURL();

	public abstract void start() throws AdaptorDelegateException;

	public abstract void stop() throws AdaptorDelegateException;;
	
	public abstract boolean isActive();
	
	
}
