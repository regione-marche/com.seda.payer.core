/**
 * 
 */
package com.seda.j2ee5.maf.defender.action.impl;

import com.seda.commons.string.Pad;
import com.seda.j2ee5.maf.defender.action.AbstractAction;
import com.seda.j2ee5.maf.defender.http.HttpDefenseRequest;
import com.seda.j2ee5.maf.defender.violation.ParameterViolation;
import com.seda.j2ee5.maf.defender.violation.Violation;

/**
 * @author Seda Lab
 *
 */
public class Blank extends AbstractAction {

	public static final String REPEAT="repeat";
	/* (non-Javadoc)
	 * @see com.seda.j2ee5.maf.defender.action.AbstractAction#process(com.seda.j2ee5.maf.defender.http.HttpDefenseRequest, com.seda.j2ee5.maf.defender.violation.Violation)
	 */
	@Override
	public int process(HttpDefenseRequest request, Violation violation) {
		if (violation instanceof ParameterViolation) {
            ParameterViolation pv = (ParameterViolation)violation;
            String name = pv.getName();
            int repeat=0;
            String repeatString = getParameter(REPEAT);
            if (repeatString!=null){
            	try {
            		repeat = Integer.parseInt(repeatString);
            	} catch (NumberFormatException x) {
            		repeat=0;
            	}
            }
            String newValue=Pad.literal(" ", repeat);
            request.setParameter(name, newValue);
		}
		return CONTINUE;
	}

}
