package com.seda.compatibility.spi;

/**
 * @author SEDA Lab
 */
public interface SystemVariableReadable {
	/**
	 * Reads ambient variable's values (operating system's or websphere's variables). Passing the variable (key) we take the value.
	 * 
	 * @param key - The key (variable) that we want to read.
	 * @return <code>String</code> or <code>null</code> value of the key.
	 */
	public String getSystemVariableValue(String key);
}
