/**
 * 
 */
package com.seda.commons.reflection;

import java.util.Map;

import com.seda.commons.data.AttributesModel;
import com.seda.commons.reflection.factory.ObjectFactory;
import com.seda.commons.reflection.factory.ObjectFactoryImpl;
import com.seda.commons.reflection.property.PropertyTokenizer;
import com.seda.commons.reflection.wrapper.BeanWrapper;
import com.seda.commons.reflection.wrapper.MapWrapper;
import com.seda.commons.reflection.wrapper.ObjectWrapper;
import com.seda.commons.reflection.wrapper.ObjectWrapperFactory;
import com.seda.commons.reflection.wrapper.ObjectWrapperFactoryImpl;

/**
 * @author f.ricci
 *
 */
public class MetaObject {

	public static final ObjectFactory DEFAULT_OBJECT_FACTORY = new ObjectFactoryImpl();
	public static final ObjectWrapperFactory DEFAULT_OBJECT_WRAPPER_FACTORY = new ObjectWrapperFactoryImpl();
	public static final MetaObject NULL_META_OBJECT = new MetaObject(NullObject.class, DEFAULT_OBJECT_FACTORY, DEFAULT_OBJECT_WRAPPER_FACTORY);
	
	private Object originalObject;
	private ObjectWrapper objectWrapper;
	private ObjectFactory objectFactory;
	private ObjectWrapperFactory objectWrapperFactory;

	private MetaObject(Object object, ObjectFactory objectFactory, ObjectWrapperFactory objectWrapperFactory) {
		this.originalObject = object;
		this.objectFactory = objectFactory;
		this.objectWrapperFactory = objectWrapperFactory;
		
		if (object instanceof ObjectWrapper) {
			this.objectWrapper = (ObjectWrapper) object;
		} else if (objectWrapperFactory.hasWrapperFor(object)) {
			this.objectWrapper = objectWrapperFactory.getWrapperFor(this, object);
		} else if (object instanceof Map) {
			this.objectWrapper = new MapWrapper(this, (Map) object);
		} else {
			this.objectWrapper = new BeanWrapper(this, object);
		}
	}

	public static MetaObject forObject(Object object, ObjectFactory objectFactory, ObjectWrapperFactory objectWrapperFactory) {
		if (object == null) {
			return NULL_META_OBJECT;
		} else {
			return new MetaObject(object, objectFactory, objectWrapperFactory);
		}
	}

	public static MetaObject forObject(Object object) {
		return forObject(object, DEFAULT_OBJECT_FACTORY, DEFAULT_OBJECT_WRAPPER_FACTORY);
	}

	public ObjectFactory getObjectFactory() {
		return objectFactory;
	}

	public ObjectWrapperFactory getObjectWrapperFactory() {
		return objectWrapperFactory;
	}

	public Object getOriginalObject() {
		return originalObject;
	}

	public String findProperty(String propName) {
		return objectWrapper.findProperty(propName);
	}

	public String[] getGetterNames() {
		return objectWrapper.getGetterNames();
	}

	public String[] getSetterNames() {
		return objectWrapper.getSetterNames();
	}

	public Class getSetterType(String name) {
		return objectWrapper.getSetterType(name);
	}

	public Class getGetterType(String name) {
		return objectWrapper.getGetterType(name);
	}

	public boolean hasSetter(String name) {
		return objectWrapper.hasSetter(name);
	}

	public boolean hasGetter(String name) {
		return objectWrapper.hasGetter(name);
	}

	public Object getValue(String name) {
		PropertyTokenizer prop = new PropertyTokenizer(name);
		if (prop.hasNext()) {
			MetaObject metaValue = metaObjectForProperty(prop.getIndexedName());
			if (metaValue == MetaObject.NULL_META_OBJECT) {
				return null;
			} else {
				return metaValue.getValue(prop.getChildren());
			}
		} else {
			return objectWrapper.get(prop);
		}
	}

	public void setValue(String name, Object value) {
		PropertyTokenizer prop = new PropertyTokenizer(name);
		if (prop.hasNext()) {
			MetaObject metaValue = metaObjectForProperty(prop.getIndexedName());
			if (metaValue == MetaObject.NULL_META_OBJECT) {
				if (value == null && prop.getChildren() != null) {
					return; // don't instantiate child path if value is null
				} else {
					metaValue = objectWrapper.instantiatePropertyValue(name, prop, objectFactory);
				}
			}
			metaValue.setValue(prop.getChildren(), value);
		} else {
			objectWrapper.set(prop, value);
		}
	}

	public boolean hasAttributes() {
		return objectWrapper.hasAttributes();
	}	

	public Object getAttribute(String attr) {
		return objectWrapper.getAttribute(attr);
	}
	
	public Class<?> getAttributeType(String attr) {
		return objectWrapper.getAttributeType(attr);
	}
	
	public void setAttribute(String attr, Object value) {
		objectWrapper.setAttribute(attr, value)	;
	}
	
	public MetaObject metaObjectForProperty(String name) {
		Object value = getValue(name);
		return MetaObject.forObject(value,objectFactory,objectWrapperFactory);
	}

	public ObjectWrapper getObjectWrapper() {
		return objectWrapper;
	}

	private static class NullObject {
	}	

}
