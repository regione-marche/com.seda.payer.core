package com.seda.data.type;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.*;

public class SimpleRegistry {

	private static final Set<Class<?>> SIMPLE_TYPE_SET = new HashSet<Class<?>>();

	static {
		SIMPLE_TYPE_SET.add(String.class);
		SIMPLE_TYPE_SET.add(Byte.class);
		SIMPLE_TYPE_SET.add(Short.class);
		SIMPLE_TYPE_SET.add(Character.class);
		SIMPLE_TYPE_SET.add(Integer.class);
		SIMPLE_TYPE_SET.add(Long.class);
		SIMPLE_TYPE_SET.add(Float.class);
		SIMPLE_TYPE_SET.add(Double.class);
		SIMPLE_TYPE_SET.add(Boolean.class);
		SIMPLE_TYPE_SET.add(Date.class);
		SIMPLE_TYPE_SET.add(Class.class);
		SIMPLE_TYPE_SET.add(BigInteger.class);
		SIMPLE_TYPE_SET.add(BigDecimal.class);

		SIMPLE_TYPE_SET.add(Collection.class);
		SIMPLE_TYPE_SET.add(Set.class);
		SIMPLE_TYPE_SET.add(Map.class);
		SIMPLE_TYPE_SET.add(List.class);
		SIMPLE_TYPE_SET.add(HashMap.class);
		SIMPLE_TYPE_SET.add(TreeMap.class);
		SIMPLE_TYPE_SET.add(ArrayList.class);
		SIMPLE_TYPE_SET.add(LinkedList.class);
		SIMPLE_TYPE_SET.add(HashSet.class);
		SIMPLE_TYPE_SET.add(TreeSet.class);
		SIMPLE_TYPE_SET.add(Vector.class);
		SIMPLE_TYPE_SET.add(Hashtable.class);
		SIMPLE_TYPE_SET.add(Enumeration.class);
	}

	/**
	 * Tells us if the class passed in is a knwon common type
	 *
	 * @param clazz The class to check
	 * @return True if the class is known
	 */
	public static boolean isSimpleType(Class<?> type) {
		if (SIMPLE_TYPE_SET.contains(type)) {
			return true;
		} else if (Collection.class.isAssignableFrom(type)) {
			return true;
		} else if (Map.class.isAssignableFrom(type)) {
			return true;
		} else if (List.class.isAssignableFrom(type)) {
			return true;
		} else if (Set.class.isAssignableFrom(type)) {
			return true;
		} else if (Iterator.class.isAssignableFrom(type)) {
			return true;
		} else {
			return false;
		}
	}

}
