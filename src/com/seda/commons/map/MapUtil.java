package com.seda.commons.map;

import java.util.HashMap;
import java.util.Map;

public class MapUtil {

    public static Map<String, Object> mergeMaps(Map<String, Object> target, Map<String, Object> source) {
        // Copia map1 per evitare di modificare l'originale
        Map<String, Object> mergedMap = new HashMap<>(target);

        // Itera su tutte le chiavi di map2
        for (String key : source.keySet()) {
            Object value1 = mergedMap.get(key);
            Object value2 = source.get(key);

            // Se il valore associato alla chiave è una mappa, esegui la merge ricorsiva
            if (value1 instanceof Map && value2 instanceof Map) {
                mergedMap.put(key, mergeMaps((Map<String, Object>) value1, (Map<String, Object>) value2));
            } else {
                // Altrimenti, sovrascrivi il valore di map1 con il valore di map2
                mergedMap.put(key, value2);
            }
        }

        return mergedMap;
    }
}
