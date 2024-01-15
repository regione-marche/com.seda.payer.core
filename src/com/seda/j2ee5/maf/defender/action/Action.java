/**
 * 
 */
package com.seda.j2ee5.maf.defender.action;

import com.seda.j2ee5.maf.defender.http.HttpDefenseRequest;
import com.seda.j2ee5.maf.defender.violation.Violation;
/**
 * @author Seda Lab
 *
 */
public interface Action {

	public final static int HALT = -1;
    public final static int CONTINUE = 0;
	
	public int process(HttpDefenseRequest request, Violation violation);
	
}
