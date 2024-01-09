package com.seda.commons.cache.impl;

import java.util.concurrent.locks.ReadWriteLock;

import com.seda.commons.cache.Cache;
import com.seda.commons.logger.CustomLoggerManager;
import com.seda.commons.logger.LoggerWrapper;

public class LoggingCache implements Cache {

  private LoggerWrapper log =  CustomLoggerManager.get(LoggingCache.class);


  private Cache delegate;
  protected int requests = 0;
  protected int hits = 0;

  public LoggingCache(Cache delegate) {
    this.delegate = delegate;
  }

  public String getId() {
    return delegate.getId();
  }

  public int getSize() {
    return delegate.getSize();
  }

  public void putObject(Object key, Object object) {
    delegate.putObject(key, object);
  }

  public Object getObject(Object key) {
    requests++;
    final Object value = delegate.getObject(key);
    if (value != null) {
      hits++;
    }
if (log.isDebugEnabled()) {
    log.debug("Cache Hit Ratio [" + getId() + "]: " + getHitRatio());
}
    return value;
  }

  public Object removeObject(Object key) {
    return delegate.removeObject(key);
  }

  public void clear() {
    delegate.clear();
  }

  public ReadWriteLock getReadWriteLock() {
    return delegate.getReadWriteLock();
  }

  public int hashCode() {
    return delegate.hashCode();
  }

  public boolean equals(Object obj) {
    return delegate.equals(obj);
  }

  private double getHitRatio() {
    return (double) hits / (double) requests;
  }

}
