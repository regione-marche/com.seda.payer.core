package com.seda.data.type;

import java.math.BigDecimal;
import java.util.*;
import java.sql.ResultSet;

import com.seda.commons.resource.ResourceManager;

public class AliasRegistry {

	private final HashMap<String, Class<?>> TYPE_ALIASES = new HashMap<String, Class<?>>();

	public AliasRegistry() {
		registerAlias("string", String.class);

		registerAlias("byte", Byte.class);
		registerAlias("long", Long.class);
		registerAlias("short", Short.class);
		registerAlias("int", Integer.class);
		registerAlias("integer", Integer.class);
		registerAlias("double", Double.class);
		registerAlias("float", Float.class);
		registerAlias("boolean", Boolean.class);

		registerAlias("byte[]", Byte[].class);
		registerAlias("long[]", Long[].class);
		registerAlias("short[]", Short[].class);
		registerAlias("int[]", Integer[].class);
		registerAlias("integer[]", Integer[].class);
		registerAlias("double[]", Double[].class);
		registerAlias("float[]", Float[].class);
		registerAlias("boolean[]", Boolean[].class);

		registerAlias("_byte", byte.class);
		registerAlias("_long", long.class);
		registerAlias("_short", short.class);
		registerAlias("_int", int.class);
		registerAlias("_integer", int.class);
		registerAlias("_double", double.class);
		registerAlias("_float", float.class);
		registerAlias("_boolean", boolean.class);

		registerAlias("_byte[]", byte[].class);
		registerAlias("_long[]", long[].class);
		registerAlias("_short[]", short[].class);
		registerAlias("_int[]", int[].class);
		registerAlias("_integer[]", int[].class);
		registerAlias("_double[]", double[].class);
		registerAlias("_float[]", float[].class);
		registerAlias("_boolean[]", boolean[].class);

		registerAlias("date", Date.class);
		registerAlias("decimal", BigDecimal.class);
		registerAlias("bigdecimal", BigDecimal.class);
		registerAlias("object", Object.class);

		registerAlias("date[]", Date[].class);
		registerAlias("decimal[]", BigDecimal[].class);
		registerAlias("bigdecimal[]", BigDecimal[].class);
		registerAlias("object[]", Object[].class);

		registerAlias("map", Map.class);
		registerAlias("hashmap", HashMap.class);
		registerAlias("list", List.class);
		registerAlias("arraylist", ArrayList.class);
		registerAlias("collection", Collection.class);
		registerAlias("iterator", Iterator.class);

		registerAlias("ResultSet", ResultSet.class);
	}

	public Class<?> resolveAlias(String string) {
		try {
			if (string == null) return null;
			String key = string.toLowerCase();
			Class<?> value;
			if (TYPE_ALIASES.containsKey(key)) {
				value = TYPE_ALIASES.get(key);
			} else {
				value = ResourceManager.classForName(string);
			}
			return value;
		} catch (ClassNotFoundException e) {
			throw new TypeException("Could not resolve type alias '" +string+ "'.  Cause: " + e, e);
		}
	}

	public void registerAlias(Class<?> type) {
		registerAlias(type.getSimpleName(), type.getName());
	}

	public void registerAlias(String alias, Class<?> value) {
		assert alias != null;
		String key = alias.toLowerCase();
		if (TYPE_ALIASES.containsKey(key) && !TYPE_ALIASES.get(key).equals(value.getName()) && TYPE_ALIASES.get(alias) != null) {
			if (!value.equals(TYPE_ALIASES.get(alias))) {
				throw new TypeException("The alias '" + alias + "' is already registered to the value '" + TYPE_ALIASES.get(alias).getName() + "'.");
			}
		}
		TYPE_ALIASES.put(key, value);
	}

	public void registerAlias(String alias, String value) {
		try {
			registerAlias(alias, ResourceManager.classForName(value));
		} catch (ClassNotFoundException e) {
			throw new TypeException("Error registering type alias "+alias+" for "+value+". Cause: " + e, e);
		}
	}

}
