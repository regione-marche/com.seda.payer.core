/**
 * 
 */
package com.seda.commons.reflection.wrapper;

import com.seda.commons.reflection.MetaObject;


/**
 * @author f.ricci
 *
 */
public interface ObjectWrapperFactory {

	boolean hasWrapperFor(Object object);

	ObjectWrapper getWrapperFor(MetaObject metaObject, Object object);

}
