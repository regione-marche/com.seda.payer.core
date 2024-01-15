/**
 * 
 */
package com.seda.j2ee5.maf.defender.action;

import java.util.Collections;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import com.seda.j2ee5.maf.defender.http.HttpDefenseRequest;
import com.seda.j2ee5.maf.defender.violation.Violation;

/**
 * @author Seda Lab
 *
 */
public abstract class AbstractAction implements Action {

	private Map<String, String> parameters;
    
	private Map<String, String> getParameters() {
		if (parameters==null) parameters = new HashMap<String, String>();
		return parameters;
	}
	
    public final String getParameter(String name) {
        return getParameters().get(name);
    }
    
    public final void addParameter(String name, String value) {
    	getParameters().put(name, value);
    }
    
    @SuppressWarnings("unchecked")
	public final Enumeration getParameterNames() {
        return Collections.enumeration(getParameters().keySet());
    }	
	
	public abstract int process(HttpDefenseRequest request, Violation violation);

}
