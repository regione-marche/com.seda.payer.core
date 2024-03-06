/**
 * 
 */
package com.seda.j2ee5.maf.components.servicelocator.ejb;

import java.util.Properties;

import javax.ejb.EJBHome;
import javax.ejb.EJBLocalHome;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.rmi.PortableRemoteObject;
import javax.sql.DataSource;

import com.seda.j2ee5.maf.components.servicelocator.ServiceLocatorException;

/**
 * This class is an implementation of the Service Locator pattern.
 * This implementation is intended to be used on the ejb tier.
 * 
 * @author Seda Lab
 *
 */
public class ServiceLocator {

	private transient InitialContext ic;
    
    public ServiceLocator() throws ServiceLocatorException  {
    	this(null,null,null,null);
    }
    
    public ServiceLocator(String initial) throws ServiceLocatorException {
    	this(null,initial,null,null);
    }
    
    public ServiceLocator(String url, String initial) throws ServiceLocatorException {
    	this(url,initial,null,null);
    }
    
    public ServiceLocator(String url, String initial, 
    		String principal, String credentials) throws ServiceLocatorException {
    	try {
    		Properties env = new Properties();
    		// check for initial context    	
    		if (initial!=null)
    			env.put(InitialContext.INITIAL_CONTEXT_FACTORY, initial);
    		// check for remote jndi directory			
    		if (url!=null) {
    			env.put(InitialContext.PROVIDER_URL, url);			
    		}    	
    		// check for credentials
    		if (principal!=null && credentials!=null) {
    			env.put(InitialContext.SECURITY_PRINCIPAL, principal);  
    			env.put(InitialContext.SECURITY_CREDENTIALS, credentials);			
    		}
    		ic = new InitialContext(env);
    		env=null;
    	} catch (NamingException e) {
	    	throw new ServiceLocatorException(e);
    	} catch (Exception e) {
	    	throw new ServiceLocatorException(e);
    	}

	}

    /**
     * Will get the ejb Local home factory.
     * It need to cast to the type of EJBHome they desire
     *
     * @return the Local EJB Home corresponding to the homeName
     */
    public EJBLocalHome getLocalHome(String jndiHomeName) throws ServiceLocatorException {
        try {
            return (EJBLocalHome) ic.lookup(jndiHomeName);
        } catch (Exception e) {
            throw new ServiceLocatorException(e);
        }
    }
    
    /**
     * will get the ejb Remote home factory.
     * clients need to cast to the type of EJBHome they desire
     *
     * @return the EJB Home corresponding to the homeName
     */
    @SuppressWarnings("unchecked")
	public EJBHome getRemoteHome(String jndiHomeName, Class className) throws ServiceLocatorException {
        try {
            Object objref = ic.lookup(jndiHomeName);
            return (EJBHome) PortableRemoteObject.narrow(objref, className);
        } catch (Exception e) {
            throw new ServiceLocatorException(e);
        }
    }
    
    /**
     * @return the factory for the factory to get JMS connections from
     */
    public  ConnectionFactory getJMSConnectionFactory(String jmsConnFactoryName)
    throws ServiceLocatorException {
        try {
            return (ConnectionFactory) ic.lookup(jmsConnFactoryName);
        } catch (Exception e) {
            throw new ServiceLocatorException(e);
        }
    }
    
    /**
     * @return the JMS Destination to send messages to
     */
    public Destination getJMSDestination(String destName) throws ServiceLocatorException {
        try {
            return (Destination)ic.lookup(destName);
        } catch (Exception e) {
            throw new ServiceLocatorException(e);
        }
    }
        
    /**
     * This method obtains the datasource itself for a caller
     * @return the DataSource corresponding to the name parameter
     */
    public DataSource getDataSource(String dataSourceName) throws ServiceLocatorException {
        try {
            return (DataSource)ic.lookup(dataSourceName);
        } catch (Exception e) {
            throw new ServiceLocatorException(e);
        }
    }
	
	
}
