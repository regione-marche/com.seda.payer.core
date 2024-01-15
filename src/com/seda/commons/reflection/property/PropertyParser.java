/**
 * 
 */
package com.seda.commons.reflection.property;

import java.util.Locale;

import com.seda.commons.reflection.ReflectorException;
/**
 * @author f.ricci
 *
 */
public class PropertyParser {

	public static String getPropertyFromMethod(String name) {
		if (name.startsWith("is")) {
			name = name.substring(2);
		} else if (name.startsWith("get") || name.startsWith("has") || name.startsWith("set")) {
			name = name.substring(3);
		} else {
			throw new ReflectorException("Error parsing property name '" + name + "'.  Didn't start with 'is', 'has', 'get' or 'set'.");
		}

		if (name.length() == 1 || (name.length() > 1 && Character.isLowerCase(name.charAt(1)))) {
			name = name.substring(0, 1).toLowerCase(Locale.US) + name.substring(1);
		}

		return name;
	}

	public static boolean isValidPropertyName(String name) {
		return !(name.startsWith("$") || "serialVersionUID".equals(name) || "class".equals(name));
	}
	
	public static boolean isProperty(String name) {
		return name.startsWith("get") || name.startsWith("set") || name.startsWith("is") || name.startsWith("has");
	}

	public static boolean isGetter(String name) {
		return name.startsWith("get") || name.startsWith("is") || name.startsWith("has");
	}

	public static boolean isSetter(String name) {
		return name.startsWith("set");
	}

}
