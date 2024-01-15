/**
 * 
 */
package com.seda.commons.reflection.wrapper;

import com.seda.commons.reflection.MetaObject;
import com.seda.commons.reflection.factory.ObjectFactory;
import com.seda.commons.reflection.property.PropertyTokenizer;

/**
 * @author f.ricci
 *
 */
public interface ObjectWrapper {

	Object get(PropertyTokenizer prop);

	void set(PropertyTokenizer prop, Object value);

	String findProperty(String name);

	String[] getGetterNames();

	String[] getSetterNames();

	Class getSetterType(String name);

	Class getGetterType(String name);

	boolean hasSetter(String name);

	boolean hasGetter(String name);

	MetaObject instantiatePropertyValue(String name, PropertyTokenizer prop, ObjectFactory objectFactory);	
	
	boolean hasAttributes() ;
	
	Object getAttribute(String attr);
	
	Class<?> getAttributeType(String attr) ;
	
	void setAttribute(String attr, Object value) ;
}
