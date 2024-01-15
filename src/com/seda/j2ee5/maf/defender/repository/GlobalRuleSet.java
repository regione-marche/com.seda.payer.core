/**
 * 
 */
package com.seda.j2ee5.maf.defender.repository;

/**
 * @author Seda Lab
 *
 */
public class GlobalRuleSet {

	RegexSet regexSet;
	RuleSet ruleSet;
	public RegexSet getRegexSet() {
		return regexSet;
	}
	public RuleSet getRuleSet() {
		return ruleSet;
	}
	public GlobalRuleSet(RegexSet regexSet, RuleSet ruleSet) {
		this.regexSet = regexSet;
		this.ruleSet = ruleSet;
	}

	@Override
	public String toString() {
		return "GlobalRuleSet [regexSet=" + regexSet + ", ruleSet=" + ruleSet
				+ "]";
	}
	
}
