package com.seda.commons.reflection.wrapper;

import com.seda.commons.reflection.ExceptionUtil;
import com.seda.commons.reflection.MetaClass;
import com.seda.commons.reflection.MetaObject;
import com.seda.commons.reflection.ReflectorException;
import com.seda.commons.reflection.factory.ObjectFactory;
import com.seda.commons.reflection.method.Performer;
import com.seda.commons.reflection.property.PropertyTokenizer;

public class BeanWrapper extends BaseWrapper {

	private Object object;
	private MetaClass metaClass;
	
	public BeanWrapper(MetaObject metaObject, Object object) {
		super(metaObject);
		this.object = object;
		this.metaClass = MetaClass.forClass(object.getClass());
	}
	
	public boolean hasAttributes() {
		return metaClass.hasAttributes();
	}
	
	public Object get(PropertyTokenizer prop) {
		if (prop.getIndex() != null) {
			Object collection = resolveCollection(prop, object);
			return getCollectionValue(prop, collection);
		} else {
			return getBeanProperty(prop, object);
		}
	}
	
	public Object getAttribute(String attr) {
		if (hasAttributes()) return getBeanAttribute(object,attr);
		return null;
	}		
	
	public void set(PropertyTokenizer prop, Object value) {
		if (prop.getIndex() != null) {
			Object collection = resolveCollection(prop, object);
			setCollectionValue(prop, collection, value);
		} else {
			setBeanProperty(prop, object, value);
		}
	}

	public void setAttribute(String attr, Object value) {
		if (hasAttributes()) setBeanAttribute(object, attr,value);
	}		
	
	public String findProperty(String name) {
		return metaClass.findProperty(name);
	}

	public String[] getGetterNames() {
		return metaClass.getGetterNames();
	}

	public String[] getSetterNames() {
		return metaClass.getSetterNames();
	}

	public Class getSetterType(String name) {
		PropertyTokenizer prop = new PropertyTokenizer(name);
		if (prop.hasNext()) {
			MetaObject metaValue = metaObject.metaObjectForProperty(prop.getIndexedName());
			if (metaValue == MetaObject.NULL_META_OBJECT) {
				return metaClass.getSetterType(name);
			} else {
				return metaValue.getSetterType(prop.getChildren());
			}
		} else {
			return metaClass.getSetterType(name);
		}
	}

	public Class getGetterType(String name) {
		PropertyTokenizer prop = new PropertyTokenizer(name);
		if (prop.hasNext()) {
			MetaObject metaValue = metaObject.metaObjectForProperty(prop.getIndexedName());
			if (metaValue == MetaObject.NULL_META_OBJECT) {
				return metaClass.getGetterType(name);
			} else {
				return metaValue.getGetterType(prop.getChildren());
			}
		} else {
			return metaClass.getGetterType(name);
		}
	}

	public boolean hasSetter(String name) {
		PropertyTokenizer prop = new PropertyTokenizer(name);
		if (prop.hasNext()) {
			if (metaClass.hasSetter(prop.getIndexedName())) {
				MetaObject metaValue = metaObject.metaObjectForProperty(prop.getIndexedName());
				if (metaValue == MetaObject.NULL_META_OBJECT) {
					return metaClass.hasSetter(name);
				} else {
					return metaValue.hasSetter(prop.getChildren());
				}
			} else {
				return false;
			}
		} else {
			return metaClass.hasSetter(name);
		}
	}

	public boolean hasGetter(String name) {
		PropertyTokenizer prop = new PropertyTokenizer(name);
		if (prop.hasNext()) {
			if (metaClass.hasGetter(prop.getIndexedName())) {
				MetaObject metaValue = metaObject.metaObjectForProperty(prop.getIndexedName());
				if (metaValue == MetaObject.NULL_META_OBJECT) {
					return metaClass.hasGetter(name);
				} else {
					return metaValue.hasGetter(prop.getChildren());
				}
			} else {
				return false;
			}
		} else {
			return metaClass.hasGetter(name);
		}
	}

	public MetaObject instantiatePropertyValue(String name, PropertyTokenizer prop, ObjectFactory objectFactory) {
		MetaObject metaValue;
		Class type = getSetterType(prop.getName());
		try {
			Object newObject = objectFactory.create(type);
			metaValue = MetaObject.forObject(newObject, metaObject.getObjectFactory(), metaObject.getObjectWrapperFactory());
			set(prop, newObject);
		} catch (Exception e) {
			throw new ReflectorException("Cannot set value of property '" + name + "' because '" + name + "' is null and cannot be instantiated on instance of " + type.getName() + ". Cause:" + e.toString(), e);
		}
		return metaValue;
	}

	private Object getBeanProperty(PropertyTokenizer prop, Object object) {
		try {
			Performer method = metaClass.getGetPerformer(prop.getName());
			try {
				return method.invoke(object, NO_ARGUMENTS);
			} catch (Throwable t) {
				throw ExceptionUtil.unwrapThrowable(t);
			}
		} catch (RuntimeException e) {
			throw e;
		} catch (Throwable t) {
			throw new ReflectorException("Could not get property '" + prop.getName() + "' from " + object + ".  Cause: " + t.toString(), t);
		}
	}

	private void setBeanProperty(PropertyTokenizer prop, Object object, Object value) {
		try {
			Performer method = metaClass.getSetPerformer(prop.getName());
			Object[] params = {value};
			try {
				method.invoke(object, params);
			} catch (Throwable t) {
				throw ExceptionUtil.unwrapThrowable(t);
			}
		} catch (Throwable t) {
			throw new ReflectorException("Could not set property '" + prop.getName() + "' of '" + object + "' with value '" + value + "' Cause: " + t.toString(), t);
		}
	}

	private void setBeanAttribute(Object object, String attr, Object value) {
		try {
			Performer method = metaClass.getSetPerformer("attribute");
			Object[] params = {attr, value};
			try {
				method.invoke(object, params);
			} catch (Throwable t) {
				throw ExceptionUtil.unwrapThrowable(t);
			}
		} catch (Throwable t) {
			throw new ReflectorException("Could not set attribute model '" + attr + "' of '" + object + "' with value '" + value + "' Cause: " + t.toString(), t);
		}
	}
	
	private Object getBeanAttribute(Object object, String attr) {
		try {
			Performer method = metaClass.getGetPerformer("attribute");
			Object[] params = {attr};
			try {
				return method.invoke(object, params);
			} catch (Throwable t) {
				throw ExceptionUtil.unwrapThrowable(t);
			}
		} catch (Throwable t) {
			throw new ReflectorException("Could not get attribute model '"+attr+"' from " + object + ".  Cause: " + t.toString(), t);
		}
	}

	public Class<?> getAttributeType(String attr) {
		try {
			Performer method = metaClass.getGetPerformer("attributeType");
			Object[] params = {attr};
			try {
				return method.invoke(object, params).getClass();
			} catch (Throwable t) {
				throw ExceptionUtil.unwrapThrowable(t);
			}
		} catch (Throwable t) {
			throw new ReflectorException("Could not get attribute model type '"+attr+"' from " + object + ".  Cause: " + t.toString(), t);
		}
	}
}
