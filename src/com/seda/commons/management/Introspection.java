/**
 * 
 */
package com.seda.commons.management;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

/**
 * This class represents a cached set of class definition information that
 * allows for easy mapping available attributes and operations that can be expose
 * with a DynamicMBean. 
 * @author f.ricci
 *
 */
public class Introspection {
	
	private static final Map<Class<?>, Introspection> _introspectors = Collections.synchronizedMap(new HashMap<Class<?>, Introspection>());
	
	private Class<?> _type;
	
	final public Set<String> _attributesName;
	final public Map<String,Method> _getters;
    final public Map<String,Method> _setters;
    final public Set<Method> _operations;
	
    private Introspection(Class<?> type) {
		_type=type;
		_attributesName = new HashSet<String>();
		_getters = new LinkedHashMap<String,Method>();
        _setters = new LinkedHashMap<String,Method>();
        _operations = new LinkedHashSet<Method>();
        initialize();
	}
	
    private void initialize() {
        final ArrayList<Method> ops = new ArrayList<Method>();
        for (Method m: _type.getMethods()) {
            if (m.getDeclaringClass().equals(Object.class)) continue;
            if (m.getDeclaringClass().equals(DynamicMBeanFacade.class)) continue;
            if (m.getName().startsWith("get") &&
                    !m.getName().equals("get") &&
                    !m.getName().equals("getClass") &&
                    m.getParameterTypes().length == 0 &&
                    m.getReturnType() != void.class) {
                _getters.put(m.getName().substring(3),m);
            } else if (m.getName().startsWith("is") &&
                    !m.getName().equals("is") &&
                    m.getParameterTypes().length == 0 &&
                    m.getReturnType() == boolean.class) {
                _getters.put(m.getName().substring(2),m);
            } else if (m.getName().startsWith("set") &&
                    !m.getName().equals("set") &&
                    m.getParameterTypes().length == 1 &&
                    m.getReturnType().equals(void.class)) {
                _setters.put(m.getName().substring(3),m);
            } else {
                ops.add(m);
            }
        }
        
        _attributesName.addAll(_getters.keySet());
        _attributesName.addAll(_setters.keySet());
        
        Iterator<String> names = _attributesName.iterator();
        while (names.hasNext()) {
			String attrName = (String) names.next();
			Method get = _getters.get(attrName);
            Method set = _setters.get(attrName);
            if (get!=null && set!=null) {
            	if (get.getReturnType() != set.getParameterTypes()[0]) {
            		set = null;
                    ops.add(_setters.remove(attrName));
            	}
            	continue;
            } 
            
            if (get==null && set==null) {
            	names.remove();
            }
		}
        
        for (Method m:ops) {
        	if (m.getDeclaringClass()==Object.class) continue;
            _operations.add(m);
        }
    }
	
    /**
	 * Gets an instance of Introspection for the specified class.
	 *
	 * @param type The class for which to lookup for attributes and operations cache.
	 * @return The cache for the class
	 */
	public static Introspection forClass(Class<?> type) {
		synchronized (type) {
			Introspection cached = _introspectors.get(type);
			if (cached == null) {
				cached = new Introspection(type);
				_introspectors.put(type, cached);
			}
			return cached;
		}
	}	
    
}
