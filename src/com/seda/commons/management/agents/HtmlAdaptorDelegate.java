/**
 * 
 */
package com.seda.commons.management.agents;

import javax.management.ObjectName;

// import org.apache.log4j.Logger;

import com.sun.jdmk.comm.HtmlAdaptorServer;

/**
 * @author f.ricci
 *
 */
public class HtmlAdaptorDelegate extends AbtractAdaptorDelegate {

	// protected Logger logger = Logger.getLogger(HtmlAdaptorDelegate.class);

	private String _clientURL;
	protected HtmlAdaptorServer _htmlAdaptorServer = null;

	public HtmlAdaptorDelegate(String domain) {
		super(domain);
	}
	
	@Override
	public boolean isActive() {
		if (_htmlAdaptorServer==null) return false;
		return _htmlAdaptorServer.isActive();
	}

	@Override
	public String getClientURL() {
		return _clientURL;
	}

	@Override
	public void start() throws AdaptorDelegateException {
		startHTMLAdapter();
	}

	@Override
	public void stop() throws AdaptorDelegateException {
		stopHTMLAdapter();
	}
	
	private void startHTMLAdapter() throws AdaptorDelegateException {
		if (_server==null) {
			throw new AdaptorDelegateException("MBeanServer null");
		}
		if (_htmlAdaptorServer!=null) {
			stopHTMLAdapter();
		}
		_htmlAdaptorServer = new HtmlAdaptorServer();
		try	{
			//create the HTML adapter
			_htmlAdaptorServer.setPort(_port);
			_adaptorName = new ObjectName( _domain+":name=html,port="+_port );
			_server.registerMBean( _htmlAdaptorServer, _adaptorName );
			_htmlAdaptorServer.start();
			_clientURL="http://"+getIp()+":"+_port+"/";
		} catch(Exception e) {
			_port=-1;
			_htmlAdaptorServer=null;
			throw new AdaptorDelegateException(e);
		}
	}	
	
	private void stopHTMLAdapter() throws AdaptorDelegateException {
		try {
			if (_htmlAdaptorServer!=null && _htmlAdaptorServer.isActive()) {
				_htmlAdaptorServer.stop();					
			}			
		} catch (Exception x) {
			throw new AdaptorDelegateException(x);
		} finally {
			try {
				unregister();
			} catch (Exception e) {
				// we cannot do anything is this case
			} 
			_htmlAdaptorServer=null;
		}

	}

}
