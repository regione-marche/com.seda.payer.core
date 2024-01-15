/**
 * 
 */
package com.seda.commons.reflection.method;

import java.lang.reflect.InvocationTargetException;

/**
 * @author f.ricci
 *
 */
public interface Performer {

	  Object invoke(Object target, Object[] args) throws IllegalAccessException, InvocationTargetException;

	  Class<?> getType();
	
}
