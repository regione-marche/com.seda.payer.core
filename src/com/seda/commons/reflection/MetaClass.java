/**
 * 
 */
package com.seda.commons.reflection;

import com.seda.commons.reflection.method.Performer;
import com.seda.commons.reflection.property.PropertyTokenizer;

/**
 * @author f.ricci
 *
 */
public class MetaClass {

	private Reflector reflector;

	private MetaClass(Class<?> type) {
		this.reflector = Reflector.forClass(type);
	}

	public static MetaClass forClass(Class<?> type) {
		return new MetaClass(type);
	}

	public MetaClass metaClassForProperty(String name) {
		Class<?> propType = reflector.getGetterType(name);
		return MetaClass.forClass(propType);
	}

	public boolean hasAttributes() {
		return reflector.hasAttributes();
	}	
	
	public String findProperty(String name) {
		StringBuilder prop = buildProperty(name, new StringBuilder());
		return prop.length() > 0 ? prop.toString() : null;
	}

	public String[] getGetterNames() {
		return reflector.getGetablePropertyNames();
	}

	public String[] getSetterNames() {
		return reflector.getSetablePropertyNames();
	}

	public Class<?> getSetterType(String name) {
		PropertyTokenizer prop = new PropertyTokenizer(name);
		if (prop.hasNext()) {
			MetaClass metaProp = metaClassForProperty(prop.getName());
			return metaProp.getSetterType(prop.getChildren());
		} else {
			return reflector.getSetterType(prop.getName());
		}
	}

	public Class<?> getGetterType(String name) {
		PropertyTokenizer prop = new PropertyTokenizer(name);
		if (prop.hasNext()) {
			MetaClass metaProp = metaClassForProperty(prop.getName());
			return metaProp.getGetterType(prop.getChildren());
		} else {
			return reflector.getGetterType(prop.getName());
		}
	}

	public boolean hasSetter(String name) {
		PropertyTokenizer prop = new PropertyTokenizer(name);
		if (prop.hasNext()) {
			if (reflector.hasSetter(prop.getName())) {
				MetaClass metaProp = metaClassForProperty(prop.getName());
				return metaProp.hasSetter(prop.getChildren());
			} else {
				return false;
			}
		} else {
			return reflector.hasSetter(prop.getName());
		}
	}

	public boolean hasGetter(String name) {
		PropertyTokenizer prop = new PropertyTokenizer(name);
		if (prop.hasNext()) {
			if (reflector.hasGetter(prop.getName())) {
				MetaClass metaProp = metaClassForProperty(prop.getName());
				return metaProp.hasGetter(prop.getChildren());
			} else {
				return false;
			}
		} else {
			return reflector.hasGetter(prop.getName());
		}
	}

	public Performer getGetPerformer(String name) {
		return reflector.getGetPerformer(name);
	}

	public Performer getSetPerformer(String name) {
		return reflector.getSetPerformer(name);
	}

	private StringBuilder buildProperty(String name, StringBuilder builder) {
		PropertyTokenizer prop = new PropertyTokenizer(name);
		if (prop.hasNext()) {
			String propertyName = reflector.findPropertyName(prop.getName());
			if (propertyName != null) {
				builder.append(propertyName);
				builder.append(".");
				MetaClass metaProp = metaClassForProperty(propertyName);
				metaProp.buildProperty(prop.getChildren(), builder);
			}
		} else {
			String propertyName = reflector.findPropertyName(name);
			if (propertyName != null) {
				builder.append(propertyName);
			}
		}
		return builder;
	}	
	
}
