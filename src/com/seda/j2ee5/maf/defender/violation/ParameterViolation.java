/**
 * 
 */
package com.seda.j2ee5.maf.defender.violation;

import com.seda.j2ee5.maf.defender.rule.Category;
/**
 * @author Seda Lab
 *
 */
public class ParameterViolation extends Violation {

	private String name = null;
    private String value = null;
    private String pattern = null;
    private String context = null;
    private String target = null;
    
    public ParameterViolation(String name, String value, String pattern, String context, String target, Category c) {
        super(c);
        
        this.name = name;
        this.value = value;
        this.pattern = pattern;
        this.context = context;
        this.target=target;
    }
    
    public String getName() {return name;}
    public String getValue() {return value;}
    public String getPattern() {return pattern;}
    public String getContext() {return context;}
    public String getTarget() {return target;}

	@Override
	public String toString() {
		return "ParameterViolation [context=" + context + ", name=" + name
				+ ", pattern=" + pattern + ", target=" + target + ", value="
				+ value + "]";
	}
}
