/**
 * 
 */
package com.seda.commons.cache;

import java.util.ArrayList;
import java.util.List;

/**
 * @author f.ricci
 *
 */
public class CacheKey {

	public static final CacheKey NULL_RESULT_KEY = new CacheKey();

	private static final int DEFAULT_MULTIPLYER = 47;
	private static final int DEFAULT_HASHCODE = 19;

	private int multiplier;
	private int hashcode;
	private long checksum;
	private int count;
	private List dataList;

	public CacheKey() {
		this.hashcode = DEFAULT_HASHCODE;
		this.multiplier = DEFAULT_MULTIPLYER;
		this.count = 0;
		this.dataList = new ArrayList();
	}

	public CacheKey(Object[] objects) {
		this();
		addAll(objects);
	}

	public int getDataCount() {
		return dataList.size();
	}

	public void add(Object object) {
		int baseHashCode = object == null ? 1 : object.hashCode();

		count++;
		checksum += baseHashCode;
		baseHashCode *= count;

		hashcode = multiplier * hashcode + baseHashCode;

		dataList.add(object);
	}

	public void addAll(Object[] objects) {
		for (Object o : objects) {
			add(o);
		}
	}

	public boolean equals(Object object) {
		if (this == object) return true;
		if (!(object instanceof CacheKey)) return false;

		final CacheKey nestedResultKey = (CacheKey) object;

		if (hashcode != nestedResultKey.hashcode) return false;
		if (checksum != nestedResultKey.checksum) return false;
		if (count != nestedResultKey.count) return false;

		for (int i = 0; i < dataList.size(); i++) {
			Object thisObject = dataList.get(i);
			Object thatObject = nestedResultKey.dataList.get(i);
			if (thisObject == null) {
				if (thatObject != null) return false;
			} else {
				if (!thisObject.equals(thatObject)) return false;
			}
		}
		return true;
	}

	public int hashCode() {
		return hashcode;
	}

	public String toString() {
		StringBuffer returnValue = new StringBuffer().append(hashcode).append(':').append(checksum);
		for (int i = 0; i < dataList.size(); i++) {
			returnValue.append(':').append(dataList.get(i));
		}

		return returnValue.toString();
	}


}
