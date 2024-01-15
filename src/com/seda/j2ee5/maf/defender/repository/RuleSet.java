/**
 * 
 */
package com.seda.j2ee5.maf.defender.repository;

import java.util.Collections;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import com.seda.j2ee5.maf.defender.rule.Category;
import com.seda.j2ee5.maf.defender.rule.Rule;
/**
 * @author Seda Lab
 *
 */
public class RuleSet {

	private Map<String, Rule> sets = null;
	
	private Map<String, Rule> getSets() {
		if (sets==null) sets = new HashMap<String, Rule>();
		return sets;
	}
	
	public Enumeration<String> getRuleSetKeys() {
        return Collections.enumeration(getSets().keySet());
    }
	
	public void addRule(Rule rule) {
		getSets().put(rule.getName(), rule);
	}
	
	public void addRule(String name, String regex, String validation, Category missing,
			Category malformed) {
		getSets().put(name, new Rule(name, regex, validation, missing, malformed));
	}
	
	public boolean contains(String name) {
		return getSets().containsKey(name);
	}
	
	public Rule getRule(String name) {
		return getSets().get(name);
	}
}
