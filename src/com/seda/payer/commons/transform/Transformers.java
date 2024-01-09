package com.seda.payer.commons.transform;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
/**
 * 
 * @author mmontisano
 *
 */
public class Transformers implements Serializable {

	private static final long serialVersionUID = 1L;
	/**
	 * 
	 * @param object
	 * @param clazz
	 * @return
	 * @throws Exception
	 */
	public static List listToBean(List object, Class to) throws Exception {
		if (object == null || object.isEmpty())
			return null;

		List trgtList = new ArrayList();
		for (Iterator iter = object.iterator(); iter.hasNext();) {
			Object nextElement = iter.next();
			try {
				Object new_instance = to.newInstance();
				if (new_instance instanceof TransformersIf) {
					TransformersIf new_name = (TransformersIf) new_instance;
					new_name.beanToBean(nextElement);
					trgtList.add(new_name);
				}
			} catch (InstantiationException e) {
				System.out.println("method beanToBean(...) not found, class " + to + " extends class Transformers");
				throw e;
			}
		}
		return trgtList;
	}
}