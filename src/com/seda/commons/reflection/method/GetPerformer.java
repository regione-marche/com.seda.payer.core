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
public class GetPerformer implements Performer {

	private Field field;
	
	public GetPerformer(Field field) {
		this.field = field;
	}	

	public Class<?> getType() {
		return field.getType();
	}

	public Object invoke(Object target, Object[] args)
			throws IllegalAccessException, InvocationTargetException {
		return field.get(target);
	}

}
