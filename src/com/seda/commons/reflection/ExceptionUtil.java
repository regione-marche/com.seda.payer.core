/**
 * 
 */
package com.seda.commons.reflection;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.UndeclaredThrowableException;
/**
 * 
 * @author f.ricci
 *
 */
public class ExceptionUtil {

	public static Throwable unwrapThrowable(Throwable wrapped) {
		Throwable unwrapped = wrapped;
		while (true) {
			if (unwrapped instanceof InvocationTargetException) {
				unwrapped = ((InvocationTargetException) wrapped).getTargetException();
			} else if (unwrapped instanceof UndeclaredThrowableException) {
				unwrapped = ((UndeclaredThrowableException) wrapped).getUndeclaredThrowable();
			} else {
				return unwrapped;
			}
		}
	}

}
