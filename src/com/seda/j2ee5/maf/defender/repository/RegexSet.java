/**
 * 
 */
package com.seda.j2ee5.maf.defender.repository;

import java.util.Collections;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import com.seda.j2ee5.maf.defender.rule.Regex;
/**
 * @author Seda Lab
 *
 */
public class RegexSet {
	
	private Map<String, Regex> sets = null;
    
	private Map<String, Regex> getSets() {
		if (sets==null) sets = new HashMap<String, Regex>();
		return sets;
	}
	
    public Enumeration<String> getRegexSetKeys() {
        return Collections.enumeration(getSets().keySet());
    }

    public void addRegex(Regex regex) {
    	getSets().put(regex.getName(), regex);
    }
    
    public void addRegex(String name, String pattern, String description) {
    	getSets().put(name, new Regex(name,pattern,description));
    }
 
    public Regex getRegex(String name) {
    	return getSets().get(name);
    }
    
    public boolean contains(String name) {
		return getSets().containsKey(name);
	}
}
