/**
 * 
 */
package com.seda.commons.reflection.method;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

/**
 * @author f.ricci
 *
 */
public class SetPerformer implements Performer {
	private Field field;

	public SetPerformer(Field field) {
		this.field = field;
	}

	public Object invoke(Object target, Object[] args) throws IllegalAccessException, InvocationTargetException {
		field.set(target, args[0]);
		return null;
	}

	public Class<?> getType() {
		return field.getType();
	}
}
