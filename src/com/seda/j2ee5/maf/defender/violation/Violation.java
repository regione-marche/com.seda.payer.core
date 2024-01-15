/**
 * 
 */
package com.seda.j2ee5.maf.defender.violation;

import com.seda.j2ee5.maf.defender.rule.Category;

/**
 * @author Seda Lab
 *
 */
public class Violation {

	private Category c = null;
    
    public Violation(Category c) {
        this.c = c;
    }
    
    public Category getCategory() {
        return c;
    }

	@Override
	public String toString() {
		return "Violation [c=" + c + "]";
	}
	
}
