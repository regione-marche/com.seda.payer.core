package com.seda.payer.commons.facade.access;

import javax.naming.Context;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
/**
 * Utility class for Facade.
 * @author Marco Montisano
 */
public class RemoteFactoryLocator {

	protected static Log log = LogFactory.getLog(RemoteFactoryLocator.class);

	/** Cached remote home (EJBHome). Uses lazy loading to obtain its value (loaded by getHome() methods). */
	private static javax.ejb.EJBHome cachedRemoteHome = null;

	private static Object lookupHome(java.util.Hashtable environment, String jndiName, Class narrowTo) throws javax.naming.NamingException {
		// Obtain initial context
		javax.naming.InitialContext initialContext = new javax.naming.InitialContext(environment);
		try {
			Object objRef = initialContext.lookup(jndiName);
			// only narrow if necessary
			if (java.rmi.Remote.class.isAssignableFrom(narrowTo))
				return javax.rmi.PortableRemoteObject.narrow(objRef, narrowTo);
			else return objRef;
		} finally {
			initialContext.close();
		}
	}
    /**
     * Obtain remote home interface from parameterised initial context
     * @param environment Parameters to use for creating initial context
     * @return Home interface for Facade. Lookup using JNDI_NAME
     */
	public static synchronized javax.ejb.EJBHome getHome(java.util.Hashtable environment, String jndiName, Class home) throws javax.naming.NamingException {
		return (javax.ejb.EJBHome) lookupHome(environment, jndiName, home);
	}
    /**
     * Obtain remote home interface from parameterised initial context
     * @param environment Parameters to use for creating initial context
     * @return Home interface for Facade. Lookup using JNDI_NAME
     */
	// public static synchronized javax.ejb.EJBHome getRemoteHome(java.util.Hashtable environment, String jndiName, Class home) throws javax.naming.NamingException, com.seda.j2ee5.maf.components.servicelocator.ServiceLocatorException {
	// 	if (cachedRemoteHome == null) {
	// 		log.debug("cached.HOME - cached");
	// 		cachedRemoteHome = (javax.ejb.EJBHome) com.seda.j2ee5.maf.components.servicelocator.ServiceLocator.getInstance().getRemoteHome(
	// 				(String) environment.get(Context.PROVIDER_URL), (String) environment.get(Context.INITIAL_CONTEXT_FACTORY),
	// 				null, null, jndiName, home);
	// 	} else log.debug("cached.HOME - is was cached");
	// 	return cachedRemoteHome;
	// }
}