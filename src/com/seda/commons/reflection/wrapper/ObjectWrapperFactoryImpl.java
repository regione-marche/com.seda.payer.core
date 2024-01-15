package com.seda.commons.reflection.wrapper;

import com.seda.commons.reflection.MetaObject;

public class ObjectWrapperFactoryImpl implements ObjectWrapperFactory {

	public boolean hasWrapperFor(Object object) {
		return false;
	}

	public ObjectWrapper getWrapperFor(MetaObject metaObject, Object object) {
		throw new RuntimeException("The DefaultObjectWrapperFactory should never be called to provide an ObjectWrapper.");
	}

}
