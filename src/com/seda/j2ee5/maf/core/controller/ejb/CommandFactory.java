/**
 * 
 */
package com.seda.j2ee5.maf.core.controller.ejb;

import java.io.Serializable;

/**
 * This class is a responsible for processing Events generated
 * by web actions. The Command Factory ties all EJB components together dynamically at
 * runtime thus providing support for reusable components. The mapping of the event names 
 * to handlers is managed by the JNDI key contained in the Event:getEventName() which is looked up from
 * an environment entry located in the EJB Deployment descriptor of the
 * EjbClientController.
 * 
 * @author Seda Lab
 *
 */
public class CommandFactory implements Serializable {

	private static final long serialVersionUID = 1L;

	
	
	
}
