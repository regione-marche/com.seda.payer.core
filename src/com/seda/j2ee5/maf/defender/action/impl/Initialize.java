/**
 * 
 */
package com.seda.j2ee5.maf.defender.action.impl;

import com.seda.j2ee5.maf.defender.action.AbstractAction;
import com.seda.j2ee5.maf.defender.http.HttpDefenseRequest;
import com.seda.j2ee5.maf.defender.violation.ParameterViolation;
import com.seda.j2ee5.maf.defender.violation.Violation;

/**
 * @author Seda Lab
 *
 */
public class Initialize extends AbstractAction {

	public static final String INIT="init";
	
	/* (non-Javadoc)
	 * @see com.seda.j2ee5.maf.defender.action.AbstractAction#process(com.seda.j2ee5.maf.defender.http.HttpDefenseRequest, com.seda.j2ee5.maf.defender.violation.Violation)
	 */
	@Override
	public int process(HttpDefenseRequest request, Violation violation) {
		
		if (violation instanceof ParameterViolation) {
            ParameterViolation pv = (ParameterViolation)violation;
            String name = pv.getName();
            String newValue = getParameter(INIT);
            if (newValue!=null) {
                request.setParameter(name, newValue);            	
            } else {
            	request.removeParameter(name);
            }
        }

		return CONTINUE;
	}

}
