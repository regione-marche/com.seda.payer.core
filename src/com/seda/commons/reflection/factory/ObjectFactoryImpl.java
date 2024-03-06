/**
 * 
 */
package com.seda.commons.reflection.factory;

import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import com.seda.commons.reflection.ReflectorException;
/**
 * @author f.ricci
 *
 */
public class ObjectFactoryImpl implements ObjectFactory {

	public Object create(Class type) {
		return create(type, null, null);
	}

	public Object create(Class type, List types, List args) {
		Class classToCreate = resolveCollectionInterface(type);
	    return instantiateClass(classToCreate, types, args);
	}

	public void setProperties(Properties properties) {
		// nothing to do
	}
	private Object instantiateClass(Class type, List<Class> types, List<Object> args) {

		try {
			Constructor constructor;
			if (types == null || args == null) {
				constructor = type.getDeclaredConstructor();
				if (!constructor.isAccessible()) {
					constructor.setAccessible(true);
				}
				return constructor.newInstance();
			} else {
				constructor = type.getDeclaredConstructor(types.toArray(new Class[types.size()]));
				if (!constructor.isAccessible()) {
					constructor.setAccessible(true);
				}
				return constructor.newInstance(args.toArray(new Object[args.size()]));
			}
		} catch (Exception e) {
			throw new ReflectorException("Error instantiating " + type + " with invalid types (" + ObjectUtil.getTypes(types) + ") or values (" + ObjectUtil.getArgs(args) + "). Cause: " + e, e);
		}
	}

	private Class resolveCollectionInterface(Class type) {
		Class classToCreate;
		if (type == List.class || type == Collection.class) {
			classToCreate = ArrayList.class;
		} else if (type == Map.class) {
			classToCreate = HashMap.class;
		} else if (type == Set.class) {
			classToCreate = HashSet.class;
		} else {
			classToCreate = type;
		}
		return classToCreate;
	}
		  
}
