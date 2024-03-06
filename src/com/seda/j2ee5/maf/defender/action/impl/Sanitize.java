/**
 * 
 */
package com.seda.j2ee5.maf.defender.action.impl;

import com.seda.j2ee5.maf.defender.action.AbstractAction;
import com.seda.j2ee5.maf.defender.http.HttpDefenseRequest;
import com.seda.j2ee5.maf.defender.util.HTMLUtils;
import com.seda.j2ee5.maf.defender.violation.ParameterViolation;
import com.seda.j2ee5.maf.defender.violation.Violation;
/**
 * @author Seda Lab
 *
 */
public class Sanitize extends AbstractAction {

	public int process(HttpDefenseRequest request, Violation violation) {
		
		if (violation instanceof ParameterViolation) {
            ParameterViolation pv = (ParameterViolation)violation;
            String name = pv.getName();
            String oldValue = request.getParameter(name);
            String newValue = HTMLUtils.sanitize(oldValue);

            request.setParameter(name, newValue);
        }
		return CONTINUE;
	}


}
