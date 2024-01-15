/**
 * 
 */
package com.seda.j2ee5.maf.components.jms.pooling;

import javax.jms.JMSException;
import javax.jms.Queue;
import javax.jms.QueueConnection;
import javax.jms.QueueConnectionFactory;
import javax.jms.QueueSender;
import javax.jms.QueueSession;
import javax.jms.Session;
import javax.jms.TextMessage;

import com.seda.commons.logger.CustomLoggerManager;
import com.seda.commons.logger.LoggerWrapper;
import com.seda.j2ee5.maf.components.encoding.ParameterDiscovery;
import com.seda.j2ee5.maf.components.servicelocator.ServiceLocator;
import com.seda.j2ee5.maf.components.servicelocator.ServiceLocatorException;
import com.seda.j2ee5.maf.util.MAFLogger;

/**
 * This class implements the singleton pattern to send message through JMS API using
 * a queue session pool. This singleton must be instantiate and destroyed during the application 
 * life cycle. Remember to call the shutdown method however all JMS session will remain active.   
 *  
 * @see QueueSessionPool
 * @see com.seda.j2ee5.maf.components.servicelocator.ServiceLocator
 * @author Seda Lab
 *
 */
public class QueueSenderManager {

	private static final LoggerWrapper logger =  CustomLoggerManager.get(QueueSenderManager.class);
	
	ServiceLocator serviceLocator = ServiceLocator.getInstance();

	QueueConnectionFactory connectionFactory  = null;
	QueueConnection queueConnection = null;
	Queue queue = null;
	
	QueueSessionPool queueSessionPool = null;
	
	private static QueueSenderManager me;
	
	static {
    	try {
    		me = new QueueSenderManager();
    	} catch(Exception se) {
    		se.printStackTrace();
    	}
    }

	/**
	 * Return the instance of this queue sender manager
	 */
	public static QueueSenderManager getInstance() {
		return me;
	}
	
	private QueueSenderManager(){}
	/**
	 * Configure the queue connection and the target message listener identified by <code>qConnFactoryName</code> and <code>qName</code>
	 */
	public void configure(String url, String initial, String principal, 
			String credentials, String qConnFactoryName, String qName) throws QueueSessionPoolException {
		logger.info("Request pooling for " + url + "/" + initial + "/"+principal);
		try {
			connectionFactory=serviceLocator.getQueueConnectionFactory(url, initial, principal, credentials, qConnFactoryName);
			logger.info("Connection created for " +qConnFactoryName);			
			queue=serviceLocator.getQueue(url, initial, principal, credentials, qName);
			logger.info("Queue requested for " +qName);
		} catch (ServiceLocatorException e) {
			throw new QueueSessionPoolException(e.getMessage(),e);
		}
	}

	/**
	 * Send message and return the message id
	 */
	public String send(String message) throws QueueSessionPoolException {
		QueueSession queueSession    = null;
		QueueSender  queueSender = null;
		String messageId =null;
		try {
			if (queueSessionPool!=null) {
				queueSession = (QueueSession)queueSessionPool.getSession();
				queueSender = queueSession.createSender(queue);
				TextMessage textMessage = queueSession.createTextMessage();
				messageId = textMessage.getJMSMessageID();
				textMessage.setText(message);
				queueSender.send(textMessage);				
			} else {
				String msg = "Empty session pool. Initialize this manager";
				logger.warn(msg);
				throw new QueueSessionPoolException(msg);
			}
		} catch (JMSException e) {
			throw new QueueSessionPoolException(e.getMessage(),e);
		} catch (Exception e) {
			throw new QueueSessionPoolException(e.getMessage(),e);
		} finally {
			try {queueSender.close();} catch(Exception ex) {}
//			try { queueSession.close(); } catch(Exception ex) {}
			queueSessionPool.releaseSession(queueSession);
		}
		return messageId;
	}
	
	/**
	 * Generate the session pool in according to the pool size
	 */
	public void init(int poolSize) throws QueueSessionPoolException {
		logger.info("Pool initializing starts " + poolSize);
		try {
			queueConnection = connectionFactory.createQueueConnection();
			QueueSessionPool sp  = new QueueSessionPool(poolSize);
			for(int i = 0; i < poolSize; i++) {
				logger.debug("Creating session " + i);
				QueueSession queueSession = queueConnection.createQueueSession(false, Session.AUTO_ACKNOWLEDGE);
				sp.addSession(queueSession);
			}
			queueSessionPool = sp;			
		} catch (JMSException e) {
			throw new QueueSessionPoolException(e.getMessage(),e);
		} catch (Exception e) {
			throw new QueueSessionPoolException(e.getMessage(),e);
		}
		logger.info("Pool initializing ends " + poolSize);		
	}
	/**
	 * Close QueueConnection and destroy the session pool
	 */
	public void shutdown() throws QueueSessionPoolException {
		//Termination of the Session Pool and closing of
		//the session when the process is ended
		if (queueSessionPool!=null) queueSessionPool.destroy();			

		if (queueConnection!=null) {
			try {
				queueConnection.close();
			} catch (JMSException e) {
				throw new QueueSessionPoolException(e.getMessage(),e);
			}			
		}
	}
	
	
	
}
