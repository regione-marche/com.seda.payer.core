/**
 * 
 */
package com.seda.commons.reflection.factory;

import java.util.List;
import java.util.Properties;

/**
 * @author f.ricci
 *
 */
public interface ObjectFactory {

	  Object create(Class type);

	  Object create(Class type, List<Class> types, List<Object> args);

	  void setProperties(Properties properties);	
	
}
