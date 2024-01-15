/**
 * 
 */
package com.seda.commons.validator;

/**
 * @author dbadm
 *
 */
public interface RuleInterface {

	
	public String getValue();
	
	public void setValue(String value);
	
	public boolean apply() throws ValidationException;
}
