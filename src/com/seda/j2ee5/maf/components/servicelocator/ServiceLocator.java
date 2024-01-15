/**
 * 
 */
package com.seda.j2ee5.maf.components.servicelocator;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.ejb.EJBHome;
import javax.ejb.EJBLocalHome;
import javax.jms.Destination;
import javax.jms.Queue;
import javax.jms.QueueConnectionFactory;
import javax.jms.Topic;
import javax.jms.TopicConnectionFactory;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.rmi.PortableRemoteObject;
import javax.sql.DataSource;

import com.seda.commons.logger.CustomLoggerManager;
import com.seda.commons.logger.LoggerWrapper;
import com.seda.j2ee5.maf.components.jms.pooling.QueueSenderManager;
import com.seda.j2ee5.maf.util.MAFLogger;

/**
 * This class is an implementation of the Service Locator pattern. The Service Locator pattern centralizes distributed service object lookups, 
 * provides a centralized point of control, and may act as a cache that eliminates redundant lookups. 
 * It also encapsulates any vendor-specific features of the lookup process. 
 * This implementation uses the "singleton" strategy and also the "caching" strategy.
 * This implementation is intended to be used on the web tier and not on the ejb tier.  
 * @author Seda Lab
 *
 */
public class ServiceLocator {
	
	private static final LoggerWrapper logger =  CustomLoggerManager.get(ServiceLocator.class);
	
    //private Map cache; //used to hold references for re-use
	private Map<String, InitialContext> contextCache;	
    private Map<String, EJBLocalHome> ejbLocalHomeCache; 
    private Map<String, EJBHome> ejbHomeCache;
    private Map<String, Destination> destinationCache;
    private Map<String, QueueConnectionFactory> queueConnectionFactoryCache;
    private Map<String, Queue> queueCache;    
    private Map<String, TopicConnectionFactory> topicConnectionFactoryCache;
    private Map<String, Topic> topicCache;
    private Map<String, DataSource> dataSourceCache;
    private static ServiceLocator me;

    static {
    	try {
    		me = new ServiceLocator();
    	} catch(ServiceLocatorException se) {
    		logger.error(MAFLogger.getMessage("generic_exception"), se);
    	}
    }

    private ServiceLocator() throws ServiceLocatorException  {
    	try {
    		contextCache = Collections.synchronizedMap(new HashMap<String, InitialContext>());
    		
    		ejbLocalHomeCache = Collections.synchronizedMap(new HashMap<String, EJBLocalHome>());
    		ejbHomeCache = Collections.synchronizedMap(new HashMap<String, EJBHome>());
    		
    		destinationCache = Collections.synchronizedMap(new HashMap<String, Destination>());
    		
    		queueConnectionFactoryCache = Collections.synchronizedMap(new HashMap<String, QueueConnectionFactory>());
    		queueCache = Collections.synchronizedMap(new HashMap<String, Queue>());
    		
    		topicConnectionFactoryCache = Collections.synchronizedMap(new HashMap<String, TopicConnectionFactory>());
    		topicCache = Collections.synchronizedMap(new HashMap<String, Topic>());
    		
    		dataSourceCache = Collections.synchronizedMap(new HashMap<String, DataSource>());
    	} catch (Exception e) {
    		throw new ServiceLocatorException(e);
    	}
    }

    public static ServiceLocator getInstance() {
      return me;
    }
    /**
     *  
     */
    public InitialContext getInitialContext(String url, String initial, 
    		String principal, String credentials) throws ServiceLocatorException {
    	InitialContext ic = null;
    	String key = null;
    	try {
    		key = getInitialContextKey(url, initial, principal, credentials);
        	if (contextCache.containsKey(key)) {
        		ic = contextCache.get(key); 
        	} else {
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
        		contextCache.put(key, ic);
        		env=null;
        	}    		
    	} catch (NamingException e) {
	    	throw new ServiceLocatorException(e);
    	} catch (Exception e) {
	    	throw new ServiceLocatorException(e);
    	}
    	return ic;
	}
    private String getInitialContextKey(String url, String initial, 
    		String principal, String credentials) {
    	return url + "#" + initial + "#" + principal + "#" + credentials;
    }
    /**
     * will get the ejb Local home factory. If this ejb home factory has already been
     * clients need to cast to the type of EJBHome they desire
     *
     * @return the EJB Home corresponding to the homeName
     */
    public EJBLocalHome getLocalHome(String jndiHomeName) throws ServiceLocatorException {
    	return getLocalHome(null,null,null,null,jndiHomeName);
    }       
    public EJBLocalHome getLocalHome(String initial, String jndiHomeName) throws ServiceLocatorException {
    	return getLocalHome(null,initial,null,null,jndiHomeName);
    	
    }    
    public EJBLocalHome getLocalHome(String url, String initial, String jndiHomeName) throws ServiceLocatorException {
    	return getLocalHome(url,initial,null,null,jndiHomeName);
    }
    public EJBLocalHome getLocalHome(String url, String initial,  
    		String principal, String credentials, String jndiHomeName) throws ServiceLocatorException {
    	EJBLocalHome home = null;
    	String contexKey = getInitialContextKey(url, initial, principal, credentials);
    	String cacheKey=contexKey+":"+jndiHomeName;
	    try {
	    	if (ejbLocalHomeCache.containsKey(cacheKey)) {
	    		home = ejbLocalHomeCache.get(cacheKey);
	        } else {
	        	home = (EJBLocalHome) getInitialContext(url, initial, 
	        			principal, credentials).lookup(jndiHomeName);
	            ejbLocalHomeCache.put(cacheKey, home);
	        }
	    } catch (NamingException ne) {
	    	throw new ServiceLocatorException(ne);
	    } catch (Exception e) {
	    	throw new ServiceLocatorException(e);
	    }
	    return home;
    }

   /**
     * will get the ejb Remote home factory. If this ejb home factory has already been
     * clients need to cast to the type of EJBHome they desire
     *
     * @return the EJB Home corresponding to the homeName
     */
    @SuppressWarnings("unchecked")
	public EJBHome getRemoteHome(String jndiHomeName, Class className) throws ServiceLocatorException {
    	return getRemoteHome(null, null, null,null,jndiHomeName,className); 
    }
    @SuppressWarnings("unchecked")
	public EJBHome getRemoteHome(String initial,
    		String jndiHomeName, Class className) throws ServiceLocatorException {
    	return getRemoteHome(null, initial, null,null,jndiHomeName,className); 
    }    
    @SuppressWarnings("unchecked")
	public EJBHome getRemoteHome(String url, String initial,
    		String jndiHomeName, Class className) throws ServiceLocatorException {
    	return getRemoteHome(url, initial, null,null,jndiHomeName,className); 
    }
    @SuppressWarnings("unchecked")
	public EJBHome getRemoteHome(String url, String initial,  
    		String principal, String credentials,
    		String jndiHomeName, Class className) throws ServiceLocatorException {
    	EJBHome home = null;
    	String contexKey = getInitialContextKey(url, initial, principal, credentials);
    	String cacheKey=contexKey+":"+jndiHomeName;    	
    	try {
    		if (ejbHomeCache.containsKey(cacheKey)) {
    			home = ejbHomeCache.get(cacheKey);
    		} else {
    			Object objref = getInitialContext(url, initial, 
	        			principal, credentials).lookup(jndiHomeName);
    			Object obj = PortableRemoteObject.narrow(objref, className);
    			home = (EJBHome)obj;
    			ejbHomeCache.put(cacheKey, home);
    		}
    	} catch (NamingException ne) {
    		throw new ServiceLocatorException(ne);
    	} catch (Exception e) {
    		throw new ServiceLocatorException(e);
    	}

    	return home;
    }


    /**
     * @return the destination for the message factory
     */    
    public Destination getDestination(String destinationName) throws ServiceLocatorException {
    	return getDestination(null, null, null, null, destinationName);
    }
    public Destination getDestination(String initial, 
    		String destinationName) throws ServiceLocatorException {
    	return getDestination(null, initial, null, null, destinationName);
    }
    public Destination getDestination(String url, String initial, 
    		String destinationName) throws ServiceLocatorException {
    	return getDestination(url, initial, null, null, destinationName);
    }
    public Destination getDestination(String url, String initial,  
    		String principal, String credentials, String destinationName) throws ServiceLocatorException {

    	Destination destination = null;
    	String contexKey = getInitialContextKey(url, initial, principal, credentials);
    	String cacheKey=contexKey+":"+destinationName;    	
    	try {
    		if (destinationCache.containsKey(cacheKey)) {
    			destination = (Destination) destinationCache.get(cacheKey);
    		} else {
    			destination = (Destination) getInitialContext(url, initial, 
    					principal, credentials).lookup(destinationName);
    			destinationCache.put(cacheKey, destination);
    		}
    	} catch (NamingException ne) {
    		throw new ServiceLocatorException(ne);
    	} catch (Exception e) {
    		throw new ServiceLocatorException(e);
    	}
    	return destination;    	
    	
    }    
    
    /**
     * @return the factory for the factory to get queue connections from
     */
    public QueueConnectionFactory getQueueConnectionFactory(String qConnFactoryName) throws ServiceLocatorException {
    	return getQueueConnectionFactory(null, null, null, null, qConnFactoryName);
    }       
    public QueueConnectionFactory getQueueConnectionFactory(String initial,  
    		String qConnFactoryName) throws ServiceLocatorException {
    	
    	return getQueueConnectionFactory(null, initial, null, null, qConnFactoryName);
    }    
    public QueueConnectionFactory getQueueConnectionFactory(String url, String initial,  
    		String qConnFactoryName) throws ServiceLocatorException {
    	
    	return getQueueConnectionFactory(url, initial, null, null, qConnFactoryName);
    }
    public QueueConnectionFactory getQueueConnectionFactory(String url, String initial,  
    		String principal, String credentials,String qConnFactoryName) 
    	throws ServiceLocatorException {
    	QueueConnectionFactory factory = null;
    	String contexKey = getInitialContextKey(url, initial, principal, credentials);
    	String cacheKey=contexKey+":"+qConnFactoryName;    	
    	try {
    		if (queueConnectionFactoryCache.containsKey(cacheKey)) {
    			factory = (QueueConnectionFactory) queueConnectionFactoryCache.get(cacheKey);
    		} else {
    			factory = (QueueConnectionFactory) getInitialContext(url, initial, 
    					principal, credentials).lookup(qConnFactoryName);
    			queueConnectionFactoryCache.put(cacheKey, factory);
    		}
    	} catch (NamingException ne) {
    		throw new ServiceLocatorException(ne);
    	} catch (Exception e) {
    		throw new ServiceLocatorException(e);
    	}
    	return factory;
    }
    /**
     * @return the Queue Destination to send messages to
     */
    public  Queue getQueue(String queueName) throws ServiceLocatorException {
    	return getQueue(null, null, null, null, queueName);
    }        
    public  Queue getQueue(String initial,  
    		String queueName) throws ServiceLocatorException {
    	return getQueue(null, initial, null, null, queueName);
    }    
    public  Queue getQueue(String url, String initial,  
    		String queueName) throws ServiceLocatorException {
    	return getQueue(url, initial, null, null, queueName);
    }
    public  Queue getQueue(String url, String initial,  
    		String principal, String credentials, String queueName) throws ServiceLocatorException {
    	Queue queue = null;    	
    	String contexKey = getInitialContextKey(url, initial, principal, credentials);
    	String cacheKey=contexKey+":"+queueName;      	
    	try {
    		if (queueCache.containsKey(cacheKey)) {
    			queue = (Queue) queueCache.get(cacheKey);
    		} else {
    			queue =(Queue)getInitialContext(url, initial, 
    					principal, credentials).lookup(queueName);
    			queueCache.put(cacheKey, queue);
    		}
    	} catch (NamingException ne) {
    		throw new ServiceLocatorException(ne);
    	} catch (Exception e) {
    		throw new ServiceLocatorException(e);
    	}
    	return queue;
    }


   /**
     * This method helps in obtaining the topic factory
     * @return the factory for the factory to get topic connections from
     */
    public  TopicConnectionFactory getTopicConnectionFactory(String topicConnFactoryName) throws ServiceLocatorException {
    	return getTopicConnectionFactory(null, null, null, null, topicConnFactoryName);
    }        
    public  TopicConnectionFactory getTopicConnectionFactory(String initial,  
    		String topicConnFactoryName) throws ServiceLocatorException {
    	return getTopicConnectionFactory(null, initial, null, null, topicConnFactoryName);
    }    
    public  TopicConnectionFactory getTopicConnectionFactory(String url, String initial,  
    		String topicConnFactoryName) throws ServiceLocatorException {
    	return getTopicConnectionFactory(url, initial, null, null, topicConnFactoryName);
    }
    public  TopicConnectionFactory getTopicConnectionFactory(String url, String initial,  
    		String principal, String credentials, String topicConnFactoryName) throws ServiceLocatorException {
    	TopicConnectionFactory factory = null;
    	String contexKey = getInitialContextKey(url, initial, principal, credentials);
    	String cacheKey=contexKey+":"+topicConnFactoryName;      	
    	try {
    		if (topicConnectionFactoryCache.containsKey(cacheKey)) {
    			factory = (TopicConnectionFactory) topicConnectionFactoryCache.get(cacheKey);
    		} else {
    			factory = (TopicConnectionFactory) getInitialContext(url, initial, 
    					principal, credentials).lookup(topicConnFactoryName);
    			topicConnectionFactoryCache.put(cacheKey, factory);
    		}
    	} catch (NamingException ne) {
    		throw new ServiceLocatorException(ne);
    	} catch (Exception e) {
    		throw new ServiceLocatorException(e);
    	}
    	return factory;
    }

    /**
     * This method obtains the topic itself for a caller
     * @return the Topic Destination to send messages to
     */
    public  Topic getTopic(String topicName) throws ServiceLocatorException {
    	return getTopic(null, null, null, null, topicName);
    }        
    public  Topic getTopic(String initial,  
    		String topicName) throws ServiceLocatorException {
    	return getTopic(null, initial, null, null, topicName);
    }    
    public  Topic getTopic(String url, String initial,  
    		String topicName) throws ServiceLocatorException {
    	return getTopic(url, initial, null, null, topicName);
    }
    public  Topic getTopic(String url, String initial,  
    		String principal, String credentials, String topicName) throws ServiceLocatorException {
    	Topic topic = null;
    	String contexKey = getInitialContextKey(url, initial, principal, credentials);
    	String cacheKey=contexKey+":"+topicName;      	    	
    	try {
    		if (topicCache.containsKey(cacheKey)) {
    			topic = (Topic) topicCache.get(cacheKey);
    		} else {
    			topic = (Topic) getInitialContext(url, initial, 
    					principal, credentials).lookup(topicName);
    			topicCache.put(cacheKey, topic);
    		}
    	} catch (NamingException ne) {
    		throw new ServiceLocatorException(ne);
    	} catch (Exception e) {
    		throw new ServiceLocatorException(e);
    	}
    	return topic;
    }

    /**
     * This method obtains the datasource itself for a caller
     * @return the DataSource corresponding to the name parameter
     */
    public DataSource getDataSource(String dataSourceName) throws ServiceLocatorException {
    	return getDataSource(null, null, null, null, dataSourceName);
    }    
    public DataSource getDataSource(String initial,  
    		String dataSourceName) throws ServiceLocatorException {
    	return getDataSource(null, initial, null, null, dataSourceName);
    }    
    public DataSource getDataSource(String url, String initial,  
    		String dataSourceName) throws ServiceLocatorException {
    	return getDataSource(url, initial, null, null, dataSourceName);
    }
    public DataSource getDataSource(String url, String initial,  
    		String principal, String credentials, String dataSourceName) throws ServiceLocatorException {
    	DataSource dataSource = null;
    	String contexKey = getInitialContextKey(url, initial, principal, credentials);
    	String cacheKey=contexKey+":"+dataSourceName;      	    	    	
    	try {
    		if (dataSourceCache.containsKey(cacheKey)) {
    			dataSource = (DataSource) dataSourceCache.get(cacheKey);
    		} else {
    			dataSource = (DataSource) getInitialContext(url, initial, 
    					principal, credentials).lookup(dataSourceName);
    			dataSourceCache.put(cacheKey, dataSource );
    		}
    	} catch (NamingException ne) {
    		throw new ServiceLocatorException(ne);
    	} catch (Exception e) {
    		throw new ServiceLocatorException(e);
    	}
    	return dataSource;
    }

}
