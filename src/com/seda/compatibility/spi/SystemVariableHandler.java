package com.seda.compatibility.spi;

/**
 * @author SEDA Lab
 */
public abstract class SystemVariableHandler implements SystemVariableReadable {

	/**
	 * The constructor.
	 */
	protected SystemVariableHandler(){
	};
	
	public abstract String getSystemVariableValue(String key);
}
