/**
 * 
 */
package com.seda.j2ee5.maf.components.validation;

import java.io.Serializable;

/**
 * @author Seda Lab
 *
 */
public class ValidationField implements Serializable {

	private static final long serialVersionUID = 1579332980077696434L;
	
	private String name;
	private String label;
	private String rules;
	
	public ValidationField(String name, String label, String rules) {
		this.name=name;
		this.label=label;
		this.rules=rules;
	}

	public String getFieldRules() {
		return name+".rules="+rules+"§"+name+".label="+label;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public String getRules() {
		return rules;
	}

	public void setRules(String rules) {
		this.rules = rules;
	}

	@Override
	public String toString() {
		return "ValidationField [label=" + label + ", name=" + name
				+ ", rules=" + rules + "]";
	}


}
