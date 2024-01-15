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
public class Substr extends AbstractAction {

	public static final String BEGIN="begin";
	public static final String END="end";
	
	@Override
	public int process(HttpDefenseRequest request, Violation violation) {
		
		if (violation instanceof ParameterViolation) {
            ParameterViolation pv = (ParameterViolation)violation;
            String name = pv.getName();
            String oldValue = request.getParameter(name);
            String newValue=null;
            if (oldValue!=null) {
                int begin=0, end=0;
                String beginString = getParameter(BEGIN);
                if (beginString!=null){
                	try {
                		begin = Integer.parseInt(beginString);
                	} catch (NumberFormatException x) {
                		begin=0;
                	}
                }
                if (begin>oldValue.length()) begin=0;
                
                String endString = getParameter(END);
                if (endString!=null){
                	try {
                		end = Integer.parseInt(endString);
                	} catch (NumberFormatException x) {
                		end=oldValue.length();
                	}
                }
                if (end>oldValue.length()) end=oldValue.length();
                
                if (begin==end) {
                	newValue=oldValue.substring(begin);
                } else {
                	newValue=oldValue.substring(begin,end);
                }
            }

            if (newValue!=null) {
                request.setParameter(name, newValue);            	
            } else {
            	request.removeParameter(name);
            }
        }
		
		return CONTINUE;
	}
}
