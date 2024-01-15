/**
 * 
 */
package com.seda.commons.validator;
/**
 * 
 * @author dbadm
 *
 */
public interface RuleOptionsInterface {

	public int optionsExpected();
	public void checkOptionsExpected() throws ValidationException;	
	
	public abstract String[] getOptions();
	public abstract void setOptions(String[] boundaries) throws ValidationException;
	
}
