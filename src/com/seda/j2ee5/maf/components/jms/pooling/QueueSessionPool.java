/**
 * 
 */
package com.seda.j2ee5.maf.components.jms.pooling;

import java.util.LinkedList;

import javax.jms.JMSException;
import javax.jms.Session;

/**
 * This class implements a base queue session pool for JMS senders. A JMS session is not thread-secure. 
 * This means that a session cannot be used simultaneously by several threads. The JMS specification 
 * refers specifically to this situation. One of the main reasons for this is that a session is the one 
 * unit in a message system that support transactions (to the extent that the message system supports 
 * transactions at all). It is difficult to implement transactions that are multithreaded. If a session 
 * is nonetheless used simultaneously by several threads, then the behavior of the JMS provider is uncertain, 
 * and it is quite likely that serious errors will arise. The fact that a JMS session is not thread-secure is 
 * particularly critical in applications that use many threads. A typical example is that of Internet 
 * applications. They are simultaneously used by many users. Within the web container process there is thus 
 * at least one active thread for each parallel access. If many users and thus many threads are active 
 * simultaneously, then many JMS connections and JMS sessions will be constantly opened and then closed. 
 * To leave the JMS session open and close it only at program termination would lead to a JMS session using 
 * several threads simultaneously. Such problems are as a rule solved through the use of thread-secure pools. 
 * <br/><br/>
 * If significantly more threads than there are available JMS sessions are continually active, the pool can 
 * develop a bottleneck, since threads will be constantly blocked. The problem can be solved by increasing the 
 * number of available JMS sessions in the pool.
 * 
 * @see QueueSenderManager
 * @author Seda Lab
 *
 */
public class QueueSessionPool {
	
	private LinkedList<Session> sessions;
	private int size;

	/**
	 * 
	 * @param size define the maximum pool size
	 */
	public QueueSessionPool(int size) {
		sessions = new LinkedList<Session>();
		this.size = size;
	}
	/**
	 * Adds a new session in the pool
	 * @throws QueueSessionPoolException 
	 */
	public synchronized void addSession(Session s) throws QueueSessionPoolException {
		if(!sessions.contains(s)) {
			sessions.addLast(s);
		} else {
			throw new QueueSessionPoolException ("session already in use!");
		}
	}
	/**
	 * Returns available session. If all available sessions are occupied by 
	 * other threads, then the method puts a block on the threads via a call 
	 * to the method <code>wait</code>.
	 */
	public synchronized Session getSession() {
		Session ret = null;
		while(sessions.isEmpty()) {
			try {
				this.wait();
			} catch(InterruptedException ex) {}
		}
		ret = (Session)sessions.removeFirst();
		return ret;
	}
	/**
	 * Returns the number of available session in the pool
	 */
	public int getAvailable() {
		return sessions.size();
	}
	/**
	 * Release the session in the pool. All threads blocked by the method <code>wait</code> 
	 * being released by <code>notify</code>.
	 * @throws QueueSessionPoolException 
	 */
	public synchronized void releaseSession(Session s) throws QueueSessionPoolException {
		if(sessions.size() >= size) {
			throw new QueueSessionPoolException("pool is exceeding initial size");
		}
		if(sessions.contains(s)) {
			throw new QueueSessionPoolException("session is available");
		}
		sessions.addLast(s);
		this.notify();
	}
	/**
	 * Closes all session in the pool
	 */
	public synchronized void destroy() {
		for(int i=0;i< sessions.size(); i++) {
			try {
				((Session)sessions.get(i)).close();
			} catch(JMSException jmsex) {
				jmsex.printStackTrace();
			}
		}
		sessions.clear();
		sessions = null;
	}
}
