/**
 * 
 */
package com.seda.j2ee5.maf.components.defender;

import java.util.Enumeration;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.ServletRequest;

import com.seda.commons.validator.Validation;
import com.seda.commons.validator.ValidationException;
import com.seda.j2ee5.maf.defender.action.AbstractAction;
import com.seda.j2ee5.maf.defender.action.RuleActionData;
import com.seda.j2ee5.maf.defender.http.HttpDefenseRequest;
import com.seda.j2ee5.maf.defender.repository.GlobalRuleSet;
import com.seda.j2ee5.maf.defender.repository.RegexSet;
import com.seda.j2ee5.maf.defender.repository.RuleSet;
import com.seda.j2ee5.maf.defender.rule.Regex;
import com.seda.j2ee5.maf.defender.rule.Rule;
import com.seda.j2ee5.maf.defender.rule.Severity;
import com.seda.j2ee5.maf.defender.violation.FatalViolationException;
import com.seda.j2ee5.maf.defender.violation.ParameterViolation;
import com.seda.j2ee5.maf.defender.violation.Violation;
/**
 * @author Seda Lab
 *
 */
public class Defender {

	
	private GlobalRuleSet globalRuleSet;
	private RuleSet specificRuleSet;
	
	
	public Defender(GlobalRuleSet globalRuleSet, RuleSet specificRuleSet) {
		this.globalRuleSet=globalRuleSet;
		this.specificRuleSet=specificRuleSet;
	}
	
	
	public void enforce(String subcontext, String action, HttpDefenseRequest request
		) throws FatalViolationException {
	
		// if null context, wait for a valid uri request
		if (subcontext==null || action==null)
			return;

		// if no configuration was made
		if (globalRuleSet==null || specificRuleSet==null)
			return;
		// enforce request
		List<Violation> violations = new LinkedList<Violation>();
		try {
			List<Violation> parameterViolations = doParameterEnforcement(request, globalRuleSet, specificRuleSet, subcontext, action);  
			violations.addAll(parameterViolations);
			doActions(request, violations);
		} catch (FatalViolationException x) {
			Violation violation = x.getViolation();
			// execute only fatal violation actions
			violations.clear();
			violations.add(violation);
			doActions(request, violations);
			throw x;
		}

	}
	
	
	private List<Violation> doParameterEnforcement(ServletRequest request, 
			GlobalRuleSet globalRuleSet,
			RuleSet ruleSet, String subcontext, String action) throws FatalViolationException {
		
		List<Violation> violations = new LinkedList<Violation>();

		RuleSet globalSet = globalRuleSet.getRuleSet();
		RegexSet regexSet = globalRuleSet.getRegexSet();
		// enforce extra
		// TO DO
		
		// enforce global		
		if (globalSet!=null) {
			List<Violation> globalViolations = enforceRuleSet(request,globalSet,regexSet, subcontext, action);
			if (globalViolations!=null) violations.addAll(globalViolations);			
		}
		// enforce target
		if (ruleSet!=null) {
			List<Violation> targetViolations = enforceRuleSet(request,ruleSet,regexSet,subcontext, action);
			if (targetViolations!=null) violations.addAll(targetViolations);					
		}
		// return violations, if exists
		return violations;

	}
	
	private List<Violation> enforceRuleSet(ServletRequest request,
			RuleSet ruleSet, RegexSet regexSet, String subcontext, String action) 
			throws FatalViolationException {
		
		List<Violation> ruleViolations = new LinkedList<Violation>();
		
		if (ruleSet!=null) {
			// checks for global rule set
			Enumeration<String> e = ruleSet.getRuleSetKeys();
			
			while(e.hasMoreElements()) {
				String name = (String)e.nextElement();
				String value = request.getParameter(name);
				Rule rule = ruleSet.getRule(name);
				// missing
				if(value == null || value.length() == 0) {
					ParameterViolation parameterViolation = new ParameterViolation(name, value, null, 
							subcontext, action, rule.getMissing());

					Severity severity = parameterViolation.getCategory().getSeverity();
					if (severity.isFatal()) {
						throw new FatalViolationException(parameterViolation);
					} else {
						ruleViolations.add(parameterViolation);
					}
				} else {
					// checks for malformed values
					Violation violation = enforceRule(rule, value, regexSet, subcontext, action);
					if (violation!=null) ruleViolations.add(violation);
				}
			}
		}
		
		return ruleViolations;
	}

	private Violation enforceRule(Rule rule, String value, RegexSet regexSet, 
			String subcontext, String action) throws FatalViolationException {
		ParameterViolation parameterViolation=null;
		String regexRule = rule.getRegex();
		String validationRule = rule.getValidation();
		if (regexRule!=null && regexRule.trim().length()>0) {
			if (regexSet!=null) {
				if (regexSet.contains(regexRule)) {
					Regex regex = regexSet.getRegex(regexRule);
					if (!regex.matches(value)) {
						parameterViolation = new ParameterViolation(rule.getName(), value, regex.getRegex(), 
								subcontext, action, rule.getMalformed());

					}
				} else {
					parameterViolation = new ParameterViolation(rule.getName(), value, "->"+regexRule+"<-", 
							subcontext, action, rule.getMalformed());				
				}
			} else {
				parameterViolation = new ParameterViolation(rule.getName(), value, "+>"+regexRule+"<+", 
						subcontext, action, rule.getMalformed());				
			}
		}
		if (validationRule!=null && validationRule.trim().length()>0) {
			Validation validationManager = new Validation();
			try {
				validationManager.validate(value, validationRule);
			} catch (ValidationException e) {
				parameterViolation = new ParameterViolation(rule.getName(), value, 
						validationRule+"["+e.getMessage()+"]", 
						subcontext, action, rule.getMalformed());
			} finally {
				validationManager=null;
			}
		}
		if (parameterViolation!=null) {
			Severity severity = parameterViolation.getCategory().getSeverity();
			if(severity.isFatal()) {
                throw new FatalViolationException(parameterViolation);
            }
		}
		return parameterViolation;
	}

	@SuppressWarnings("unchecked")
	private void doActions(HttpDefenseRequest request,
			List<Violation> violations) {

		Iterator<Violation> iv = violations.iterator();
		while(iv.hasNext()) {
            Violation v = iv.next();
            List<RuleActionData> actions = v.getCategory().getActions();
            Iterator<RuleActionData> ia = actions.iterator();
            while(ia.hasNext()) {
            	RuleActionData ruleActionData = ia.next();
                AbstractAction action = getActionInstance(ruleActionData.getActionClass());

                if (action!=null) {
                	
                	Enumeration e = ruleActionData.getParameterNames();
                    
                    while(e.hasMoreElements()) {
                        String name = (String)e.nextElement();
                        String value = ruleActionData.getParameter(name);
                        
                        action.addParameter(name, value);
                    }
                	
                	action.process(request, v);
                	action=null;
                }
            }
		}
		
	}
	
	@SuppressWarnings("unchecked")
	public AbstractAction getActionInstance(String clazz) {
        AbstractAction action = null;
        try {
            Class c = Class.forName(clazz);
            Object o = c.newInstance();
            
            if(o instanceof AbstractAction) {
                action = (AbstractAction)o;
            }
        } catch (ClassNotFoundException x) {
            x.printStackTrace();
        } catch (IllegalAccessException x) {
            x.printStackTrace();
        } catch (InstantiationException x) {
            x.printStackTrace();
        }
        
        return action;
	}
}
