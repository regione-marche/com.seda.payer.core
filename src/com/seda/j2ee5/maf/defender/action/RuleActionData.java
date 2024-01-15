/**
 * 
 */
package com.seda.j2ee5.maf.defender.action;

import java.util.Collections;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Seda Lab
 *
 */
public class RuleActionData {

	private Map<String, String> parameters = new HashMap<String, String>();
	private String actionClass;
	
	public String getParameter(String name) {
        return parameters.get(name);
    }
    
    public void addParameter(String name, String value) {
        parameters.put(name, value);
    }
    
    @SuppressWarnings("unchecked")
	public Enumeration getParameterNames() {
        return Collections.enumeration(parameters.keySet());
    }

	public String getActionClass() {
		return actionClass;
	}

	public RuleActionData(Map<String, String> parameters, String actionClass) {
		this.parameters = parameters;
		this.actionClass = actionClass;
	}

	@Override
	public String toString() {
		return "ActionData [actionClass=" + actionClass + ", parameters="
				+ parameters + "]";
	}
	
}
