/**
 * 
 */
package com.seda.j2ee5.maf.defender.violation;

/**
 * @author Seda Lab
 *
 */
public class FatalViolationException extends Exception {

	private static final long serialVersionUID = 1L;

	private Violation violation = null;
    
    public FatalViolationException(Violation violation) {
        super();
        this.violation = violation;
    }
    
    public Violation getViolation() {
        return violation;
    }
	
}
