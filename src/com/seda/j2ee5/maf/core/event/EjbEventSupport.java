/**
 * 
 */
package com.seda.j2ee5.maf.core.event;

/**
 * This is the base class for all ejb events used by the application.
 * This class only implements Serializable to ensure that
 * all events may be sent the the EJB container via RMI-IIOP.
 * 
 * @author Seda Lab
 */
public class EjbEventSupport implements EjbEvent {

	private static final long serialVersionUID = 1L;
	
	private String ejbActionClass;
	
	/* (non-Javadoc)
	 * @see com.seda.j2ee5.maf.core.event.EjbEvent#getEjbActionClass()
	 */
	public String getEjbActionClass() {
		return this.ejbActionClass;
	}

	/* (non-Javadoc)
	 * @see com.seda.j2ee5.maf.core.event.EjbEvent#setEjbActionClass(java.lang.String)
	 */
	public void setEjbActionClass(String ejbActionClass) {
		this.ejbActionClass=ejbActionClass;
	}

	/* (non-Javadoc)
	 * @see com.seda.j2ee5.maf.core.event.Event#getEventName()
	 */
	public String getEventName() {
		return null;
	}

}
