/**
 * 
 */
package com.seda.j2ee5.maf.defender.rule;

import java.util.List;

import com.seda.j2ee5.maf.defender.action.RuleActionData;
/**
 * @author Seda Lab
 *
 */
public class Category {

    private final static String MISSING = "missing";
    private final static String MALFORMED = "malformed";
    
    private String type = null;
    
    private Severity severity = null;
    
    private List<RuleActionData> actions = null;
    
    public Category(String type, Severity severity, List<RuleActionData> actions) {
    	if(MISSING.equalsIgnoreCase(type)) {
            this.type = MISSING;
        } else if(MALFORMED.equalsIgnoreCase(type)) {
            this.type = MALFORMED;
        }
        
        this.severity = severity;
        this.actions = actions;
    }
    
    public String getType() {
        return type;
    }
    
    public Severity getSeverity() {
        return severity;
    }
    
    public List<RuleActionData> getActions() {
        return actions;
    }

	@Override
	public String toString() {
		return "Category [actions=" + actions + ", severity=" + severity
				+ ", type=" + type + "]";
	}	
	
}
