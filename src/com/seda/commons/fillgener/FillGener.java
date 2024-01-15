/**
 * 
 */
package com.seda.commons.fillgener;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

/**
 * @author dbadm
 *
 */
public class FillGener {

	private String value;
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	
	private List<FillDefinition> definitions = new LinkedList<FillDefinition>();	
	private List<FillDefinition> getDefinitions() {
		return this.definitions;
	}
	
	public boolean addFillDefinition(FillDefinition fillDefinition) throws FillGenerException {
    	if (fillDefinition==null) throw new FillGenerException(Messages.DEFINITION_NULL.format());
    	return getDefinitions().add(fillDefinition);
	}
	
	public boolean addFillDefinition(Collection<FillDefinition> definitions) throws FillGenerException {
    	return addFillDefinition(definitions, true);
	}
	
	public boolean addFillDefinition(Collection<FillDefinition> definitions, boolean clear) throws FillGenerException {
		if (clear) definitions.clear();
    	return definitions.addAll(definitions);
	}		
	
	public FillGener() {}
	
	public FillGener(String value) {
 		setValue(value);
 	}
	
	public FillGener(String value, String maskDefinition) throws FillGenerException {
 		setValue(value);
 		loadDefinition(maskDefinition);
 	}	
	
	public void loadDefinition(String maskDefinition) throws FillGenerException {
		// if passed definition is null
		if (maskDefinition == null) {
			// throw exception
			throw new FillGenerException(Messages.MASKDEFINITION_NULL.format()); 
		}
		// clear all definitions rules
		getDefinitions().clear();
		// load new definition rules
		getDefinitions().addAll(FillGenerParser.parse(maskDefinition));
	}
	
	public String generate() throws FillGenerException {
		return generate(getValue(), false);
	}	
	
	public String generate(String value) throws FillGenerException {
		return generate(value, true);
	}

	public String generate(String value, String maskDefinition) throws FillGenerException {
		loadDefinition(maskDefinition);
		return generate(value, true);
	}
	
	public String generate(String value, boolean replace) throws FillGenerException {
		// override existing value and mask
		if (replace) setValue(value);
		// apply filler generation rule
		return applyFillDefinition(value);
	}	
	
	private String applyFillDefinition(String value) throws FillGenerException {
		// if the filler generator doesn't have rules return it with no actions
		if (getDefinitions().isEmpty()) return value;
		// prepare the string builder object will contain the new string
		StringBuffer sb = new StringBuffer();
		// loop through the filler definition and make the new string
		for (FillDefinition fillDefinition : getDefinitions()) {
			sb.append(fillDefinition.apply(value));
		}
		return sb.toString();
	}
}
