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
public class Null extends AbstractAction {

	/* (non-Javadoc)
	 * @see com.seda.j2ee5.maf.defender.action.AbstractAction#process(com.seda.j2ee5.maf.defender.http.HttpDefenseRequest, com.seda.j2ee5.maf.defender.violation.Violation)
	 */
	@Override
	public int process(HttpDefenseRequest request, Violation violation) {
		if (violation instanceof ParameterViolation) {
            ParameterViolation pv = (ParameterViolation)violation;
            String name = pv.getName();
            request.removeParameter(name);
        }
		return CONTINUE;
	}

}
