/**
 * 
 */
package com.seda.j2ee5.maf.util;

import java.io.Serializable;
import java.util.Collections;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

/**
 * @author f.ricci
 *
 */
public class MAFSessionAttributesCache {

	private static Map<String, Map<String, Serializable>> attributesCache;

	static {
		attributesCache = Collections.synchronizedMap(new HashMap<String, Map<String, Serializable>> ());
	}
	
	public static void saveAttributes(String key, HttpSession session) {
		Map<String, Serializable> mapToCache = new HashMap<String, Serializable>();
		Enumeration<?> keyAttributes =session.getAttributeNames();
		while (keyAttributes.hasMoreElements()) {
			String keyAttribute = (String) keyAttributes.nextElement();
			Serializable object = (Serializable)session.getAttribute(keyAttribute);
			mapToCache.put(keyAttribute, object);
		}
		attributesCache.put(key, mapToCache);
	}
	public static void restoreAttributes(String key, HttpSession session) {
		Map<String, Serializable> mapFromCache = attributesCache.get(key);
		if (mapFromCache!=null) {
			Enumeration<String> keyAttributes=Collections.enumeration(mapFromCache.keySet());
			while (keyAttributes.hasMoreElements()) {
				String keyAttribute = keyAttributes.nextElement();
				Serializable object = mapFromCache.get(keyAttribute);
				session.setAttribute(keyAttribute, object);
			}
			attributesCache.remove(key);
		}
	}
}