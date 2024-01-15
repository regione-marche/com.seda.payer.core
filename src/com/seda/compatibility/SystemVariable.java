package com.seda.compatibility;

import com.seda.compatibility.spi.SystemVariableHandler;

/**
 * @author SEDA Lab
 */
public final class SystemVariable extends SystemVariableHandler {
	/**
	 * Reads operating system's ambient variable values. Passing the variable (key) we take the value.
	 * 
	 * @param key - The key (variable) that we want to read.
	 * @return <code>String</code> or <code>null</code> value of the key.
	 */
	public String getSystemVariableValue(String key) {
		return System.getenv(key);
	}
}
