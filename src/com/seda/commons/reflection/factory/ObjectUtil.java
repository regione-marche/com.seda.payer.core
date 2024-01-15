/**
 * 
 */
package com.seda.commons.reflection.factory;

import java.util.List;

/**
 * @author f.ricci
 *
 */
public class ObjectUtil {
	
	static String getTypes(List<Class> types) {
		StringBuilder argTypes = new StringBuilder();
		if (types != null) {
			for (Class argType : types) {
				argTypes.append(argType.getSimpleName());
				argTypes.append(",");
			}
		}
		return argTypes.toString();
	}
	
	static String getArgs(List<Object> args) {
		StringBuilder argValues = new StringBuilder();
		if (args != null) {
			for (Object argValue : args) {
				argValues.append(String.valueOf(argValue));
				argValues.append(",");
			}
		}
		return argValues.toString();
	}
	
}
