package com.seda.commons.cache.impl;


import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import com.seda.commons.cache.Cache;
import com.seda.commons.cache.CacheException;

public class PerpetualCache implements Cache {

	private String id;

	private Map cache = new HashMap();

	private ReadWriteLock readWriteLock = new ReentrantReadWriteLock();

	public PerpetualCache(String id) {
		this.id = id;
	}

	public String getId() {
		return id;
	}

	public int getSize() {
		return cache.size();
	}

	public void putObject(Object key, Object value) {
		cache.put(key, value);
	}

	public Object getObject(Object key) {
		return cache.get(key);
	}

	public Object removeObject(Object key) {
		return cache.remove(key);
	}

	public void clear() {
		cache.clear();
	}

	public ReadWriteLock getReadWriteLock() {
		return readWriteLock;
	}

	public boolean equals(Object o) {
		if (getId() == null) throw new CacheException("Cache instances require an ID.");
		if (this == o) return true;
		if (!(o instanceof Cache)) return false;

		Cache otherCache = (Cache) o;
		return getId().equals(otherCache.getId());
	}

	public int hashCode() {
		if (getId() == null) throw new CacheException("Cache instances require an ID.");
		return getId().hashCode();
	}

}
