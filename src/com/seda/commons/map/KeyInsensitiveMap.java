package com.seda.commons.map;

import java.util.HashMap;
import java.util.Map;

public class KeyInsensitiveMap<K,V> extends HashMap<String,V> {


    public KeyInsensitiveMap() {
        super();
    }   

    public KeyInsensitiveMap(Map <K,V> map) {
        map.entrySet().forEach(m -> put((String)m.getKey(), m.getValue()));
    }   

    @Override
    public V get(Object key) {
        return super.get( ((String)key).toLowerCase() );
    }

    @Override
    public V put(String key, V value) {
        return super.put(key.toLowerCase(), value);
    }

    
    
}
