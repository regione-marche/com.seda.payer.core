/**
 * 
 */
package com.seda.j2ee5.maf.core.action;

import java.util.HashMap;

/**
 * @author f.ricci
 *
 */
public class ComplexActionTable {

	private HashMap<String, String> translation = new HashMap<String, String>();
	
	public void add(String value, String actionurl) {
		translation.put(value, actionurl);
	}
	
	public String get(String value) {
		if (translation.containsKey(value))
			return translation.get(value);
		return value;
	}
	
	public boolean contains(String value) {
		return translation.containsKey(value);
	}	

}
