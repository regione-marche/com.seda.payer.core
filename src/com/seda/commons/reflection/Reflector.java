/**
 * 
 */
package com.seda.commons.reflection;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.ReflectPermission;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import com.seda.commons.reflection.method.GetPerformer;
import com.seda.commons.reflection.method.MethodPerformer;
import com.seda.commons.reflection.method.Performer;
import com.seda.commons.reflection.method.SetPerformer;
import com.seda.commons.reflection.property.PropertyParser;
/**
 * This class represents a cached set of class definition information that
 * allows for easy mapping between property names and getter/setter methods.
 * @author f.ricci
 */
public class Reflector {

	private static final Map<Class<?>, Reflector> reflectors = Collections.synchronizedMap(new HashMap<Class<?>, Reflector>());
	
	private Class<?> type;
	private String[] readablePropertyNames = new String[0];
	private String[] writeablePropertyNames = new String[0];
	private Map<String, Performer> setMethods = new HashMap<String, Performer>();
	private Map<String, Performer> getMethods = new HashMap<String, Performer>();
	private Map<String, Class<?>> setTypes = new HashMap<String, Class<?>>();
	private Map<String, Class<?>> getTypes = new HashMap<String, Class<?>>();
	private Constructor<?> defaultConstructor;
	
	public static final String ATTRIBUTES_MODEL = "com.seda.commons.data.AttributesModel";	
	private boolean attributes=false;

	private Map<String, String> caseInsensitivePropertyMap = new HashMap<String, String>();
	
	private Reflector(Class<?> type) {
		this.type = type;
		try {
			if (Class.forName(ATTRIBUTES_MODEL).isAssignableFrom(type)) attributes=true;
		} catch (ClassNotFoundException e) {
			// ignore, no AttributesModel in the class path
		}		
		addDefaultConstructor(type);
		addMethods(type);
		addFields(type);
		readablePropertyNames = getMethods.keySet().toArray(new String[getMethods.keySet().size()]);
		writeablePropertyNames = setMethods.keySet().toArray(new String[setMethods.keySet().size()]);
		for (String propName : readablePropertyNames) {
			caseInsensitivePropertyMap.put(propName.toUpperCase(Locale.ENGLISH), propName);
		}
		for (String propName : writeablePropertyNames) {
			caseInsensitivePropertyMap.put(propName.toUpperCase(Locale.ENGLISH), propName);
		}
	}
	
	private void addDefaultConstructor(Class<?> type) {
		Constructor<?>[] consts = type.getDeclaredConstructors();
		for (Constructor<?> constructor : consts) {
			if (constructor.getParameterTypes().length == 0) {
				if (canAccessPrivateMethods()) {
					try {
						constructor.setAccessible(true);
					} catch (Exception e) {}
				}
				if (constructor.isAccessible()) {
					this.defaultConstructor = constructor;
				}
			} else {
				// what can i do with a no default constructor
			}
		}
	}
	
	private void addMethods(Class<?> type) {
		Method[] methods = getClassMethods(type);
		Map<String, List<Method>> conflictingSetters = new HashMap<String, List<Method>>();
		for (Method method : methods) {
			String name = method.getName();
			if (attributes) {
				if (name.equals("setAttribute") && (method.getParameterTypes().length == 2)) {
					Class<?>[] parameters = method.getParameterTypes();
					if (parameters[0].equals(java.lang.String.class) && 
							parameters[1].equals(java.lang.Object.class) &&
							method.getReturnType().equals(Void.TYPE)) {
						name = PropertyParser.getPropertyFromMethod(name);
						addSetMethod(name, method);
					}
					continue;
				} else if (name.equals("getAttribute")) {
					Class<?>[] parameters = method.getParameterTypes();
					if (parameters[0].equals(java.lang.String.class) &&
							method.getReturnType().equals(java.lang.Object.class)) {
						name = PropertyParser.getPropertyFromMethod(name);
						addGetMethod(name, method);
					}					
					continue;
				} else if (name.equals("getAttributeType")) {
					Class<?>[] parameters = method.getParameterTypes();
					if (parameters[0].equals(java.lang.String.class) &&
							method.getReturnType().equals(java.lang.Class.class)) {
						name = PropertyParser.getPropertyFromMethod(name);
						addGetMethod(name, method);
					}					
					continue;
				}
			}
			if (((name.startsWith("get") || name.startsWith("has")) && name.length() > 3) ||
					(name.startsWith("is") && name.length() > 2)){
				if (method.getParameterTypes().length == 0) {
					name = PropertyParser.getPropertyFromMethod(name);
					addGetMethod(name, method);
				}
				continue;
			} 
			if (name.startsWith("set") && name.length() > 3) {
				if (method.getParameterTypes().length == 1) {
					name = PropertyParser.getPropertyFromMethod(name);
					addSetterConflict(conflictingSetters, name, method);
				}
			}
		}
		resolveSetterConflicts(conflictingSetters);
	}

	private void addGetMethod(String name, Method method) {
		if (PropertyParser.isValidPropertyName(name)) {
			getMethods.put(name, new MethodPerformer(method));
			getTypes.put(name, method.getReturnType());
		}
	}	
	
	private void addSetterConflict(Map<String, List<Method>> conflictingSetters, String name, Method method) {
		List<Method> list = conflictingSetters.get(name);
		if (list == null) {
			list = new ArrayList<Method>();
			conflictingSetters.put(name, list);
		}
		list.add(method);
	}

	private void resolveSetterConflicts(Map<String, List<Method>> conflictingSetters) {
		for (String propName : conflictingSetters.keySet()) {
			List<Method> setters = conflictingSetters.get(propName);
			Method firstMethod = setters.get(0);
			if (setters.size() == 1) {
				addSetMethod(propName, firstMethod);
			} else {
				Class<?> expectedType = getTypes.get(propName);
				if (expectedType == null) {
					throw new ReflectorException("Ambiguous setter type for property "
							+ propName + " in class " + firstMethod.getDeclaringClass() + ".  This can cause unpredicatble results.");
				} else {
					Iterator<Method> methods = setters.iterator();
					Method setter = null;
					while (methods.hasNext()) {
						Method method = methods.next();
						if (method.getParameterTypes().length == 1
								&& expectedType.equals(method.getParameterTypes()[0])) {
							setter = method;
							break;
						}
					}
					if (setter == null) {
						throw new ReflectorException("Ambiguous setter type for property "
								+ propName + " in class " + firstMethod.getDeclaringClass() + ".  This can cause unpredicatble results.");
					}
					addSetMethod(propName, setter);
				}
			}
		}
	}	
	
	private void addSetMethod(String name, Method method) {
		if (PropertyParser.isValidPropertyName(name)) {
			setMethods.put(name, new MethodPerformer(method));
			setTypes.put(name, method.getParameterTypes()[0]);
		}
	}	
	
	private void addFields(Class<?> type) {
		Field[] fields = type.getDeclaredFields();
		for (Field field : fields) {
			if (canAccessPrivateMethods()) {
				try {
					field.setAccessible(true);
				} catch (Exception e) {	}
			}
			if (field.isAccessible()) {
				if (!setMethods.containsKey(field.getName())) {
					if (!Modifier.isFinal(field.getModifiers())) {
						addSetField(field);
					}
				}
				if (!getMethods.containsKey(field.getName())) {
					addGetField(field);
				}
			}
		}
		if (type.getSuperclass() != null) {
			addFields(type.getSuperclass());
		}
	}

	private void addSetField(Field field) {
		if (PropertyParser.isValidPropertyName(field.getName())) {
			setMethods.put(field.getName(), new SetPerformer(field));
			setTypes.put(field.getName(), field.getType());
		}
	}

	private void addGetField(Field field) {
		if (PropertyParser.isValidPropertyName(field.getName())) {
			getMethods.put(field.getName(), new GetPerformer(field));
			getTypes.put(field.getName(), field.getType());
		}
	}	
	
	/**
	 * This method returns an array containing all methods
	 * declared in this class and any superclass.
	 * We use this method, instead of the simpler Class.getMethods(),
	 * because we want to look for private methods as well.
	 *
	 * @param type The class
	 * @return An array containing all methods in this class
	 */
	private Method[] getClassMethods(Class<?> type) {
		HashMap<String, Method> uniqueMethods = new HashMap<String, Method>();
		Class<?> currentClass = type;
		while (currentClass != null) {
			addUniqueMethods(uniqueMethods, currentClass.getDeclaredMethods());

			// we also need to look for interface methods - 
			// because the class may be abstract
			Class<?>[] interfaces = currentClass.getInterfaces();
			for (Class<?> anInterface : interfaces) {
				addUniqueMethods(uniqueMethods, anInterface.getMethods());
			}

			currentClass = currentClass.getSuperclass();
		}

		Collection<Method> methods = uniqueMethods.values();

		return methods.toArray(new Method[methods.size()]);
	}

	private void addUniqueMethods(HashMap<String, Method> uniqueMethods, Method[] methods) {
		for (Method currentMethod : methods) {
			if (!currentMethod.isBridge()) {
				String signature = getSignature(currentMethod);
				// check to see if the method is already known
				// if it is known, then an extended class must have
				// overridden a method
				if (!uniqueMethods.containsKey(signature)) {
					if (canAccessPrivateMethods()) {
						try {
							currentMethod.setAccessible(true);
						} catch (Exception e) {
							// Ignored. This is only a final precaution, nothing we can do.
						}
					}

					uniqueMethods.put(signature, currentMethod);
				}
			}
		}
	}

	private String getSignature(Method method) {
		StringBuffer sb = new StringBuffer();
		sb.append(method.getName());
		Class<?>[] parameters = method.getParameterTypes();
		for (int i = 0; i < parameters.length; i++) {
			if (i == 0) {
				sb.append(':');
			} else {
				sb.append(',');
			}
			sb.append(parameters[i].getName());
		}
		return sb.toString();
	}


	
	private static boolean canAccessPrivateMethods() {
		try {
			SecurityManager securityManager = System.getSecurityManager();
			if (null != securityManager) {
				securityManager.checkPermission(new ReflectPermission("suppressAccessChecks"));
			}
		} catch (SecurityException e) {
			return false;
		}
		return true;
	}		
	
	/**
	 * Gets the name of the class the instance provides information for
	 *
	 * @return The class name
	 */
	public Class<?> getType() {
		return type;
	}

	public boolean hasAttributes() {
		return attributes;
	}
	
	public Constructor<?> getDefaultConstructor() {
		if (defaultConstructor != null) {
			return defaultConstructor;
		} else {
			throw new ReflectorException("There is no default constructor for " + type);
		}
	}

	public Performer getSetPerformer(String propertyName) {
		Performer method = setMethods.get(propertyName);
		if (method == null) {
			throw new ReflectorException("There is no setter for property named '" + propertyName + "' in '" + type + "'");
		}
		return method;
	}

	public Performer getGetPerformer(String propertyName) {
		Performer method = getMethods.get(propertyName);
		if (method == null) {
			throw new ReflectorException("There is no getter for property named '" + propertyName + "' in '" + type + "'");
		}
		return method;
	}

	/**
	 * Gets the type for a property setter
	 *
	 * @param propertyName - the name of the property
	 * @return The Class of the property setter
	 */
	public Class<?> getSetterType(String propertyName) {
		Class<?> typeClass = setTypes.get(propertyName);
		if (typeClass == null) {
			throw new ReflectorException("There is no setter for property named '" + propertyName + "' in '" + type + "'");
		}
		return typeClass;
	}

	/**
	 * Gets the type for a property getter
	 *
	 * @param propertyName - the name of the property
	 * @return The Class of the property getter
	 */
	public Class<?> getGetterType(String propertyName) {
		Class<?> classType = getTypes.get(propertyName);
		if (classType == null) {
			throw new ReflectorException("There is no getter for property named '" + propertyName + "' in '" + type + "'");
		}
		return classType;
	}

	/**
	 * Gets an array of the readable properties for an object
	 *
	 * @return The array
	 */
	public String[] getGetablePropertyNames() {
		return readablePropertyNames;
	}

	/**
	 * Gets an array of the writable properties for an object
	 *
	 * @return The array
	 */
	public String[] getSetablePropertyNames() {
		return writeablePropertyNames;
	}

	/**
	 * Check to see if a class has a writable property by name
	 *
	 * @param propertyName - the name of the property to check
	 * @return True if the object has a writable property by the name
	 */
	public boolean hasSetter(String propertyName) {
		return setMethods.keySet().contains(propertyName);
	}

	/**
	 * Check to see if a class has a readable property by name
	 *
	 * @param propertyName - the name of the property to check
	 * @return True if the object has a readable property by the name
	 */
	public boolean hasGetter(String propertyName) {
		return getMethods.keySet().contains(propertyName);
	}

	public String findPropertyName(String name) {
		return caseInsensitivePropertyMap.get(name.toUpperCase(Locale.ENGLISH));
	}

	/**
	 * Gets an instance of Reflector for the specified class.
	 *
	 * @param type The class for which to lookup the method cache.
	 * @return The method cache for the class
	 */
	public static Reflector forClass(Class<?> type) {
		synchronized (type) {
			Reflector cached = reflectors.get(type);
			if (cached == null) {
				cached = new Reflector(type);
				reflectors.put(type, cached);
			}
			return cached;
		}
	}	


}
