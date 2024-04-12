package com.seda.payer.core.handler.rest.routine;

import java.util.HashMap;
import java.util.Map;

public class SDDAUSP_SEL_BRS extends ARestRoutine {
    @Override
    protected String routine() {
        return "SDDAUSP_SEL_BRS";
    }

    @Override
    protected Map<Integer, String> inParameterMap() {

        int p = 1;

        Map<Integer, String> inParameterMap = new HashMap<Integer, String>();


        //aggiungere la mappatura di tutti i campi di input
        //...
        inParameterMap.put(p++, "");
        inParameterMap.put(p++, "");


        return inParameterMap;
    }

    @Override
    protected Map<Integer, String> outParameterMap() {
        //aggiungere la mappatura di tutti i campi di output

        int p = 1;

        Map<Integer, String> outParameterMap = new HashMap<Integer, String>();

        outParameterMap.put(p++, "");
        outParameterMap.put(p++, "");

        return outParameterMap;
    }

    @Override
    protected Map<Integer, Map<Integer, String>> resultSetsMap() {

        int p = 1;

        Map<Integer,String> resultSetMap1 = new HashMap<Integer, String>();

        resultSetMap1.put(p++, "");

        resultSetMap1.put(p++, "1");
        //...


        p = 1;
        //da capire la 2° mappa se serve

        Map<Integer,String> resultSetMap2 = new HashMap<Integer, String>();

        resultSetMap2.put(p++, "");
        resultSetMap2.put(p++, "");


        p = 1;

        //non so se la 3° mappa serve

        Map<Integer,String> resultSetMap3 = new HashMap<Integer, String>();

        // TODO

        Map<Integer, Map<Integer,String>> resultSetsMap = new HashMap<Integer, Map<Integer,String>>();
        resultSetsMap.put(0, resultSetMap1);
        resultSetsMap.put(1, resultSetMap2);
        resultSetsMap.put(2, resultSetMap3);

        return resultSetsMap;
    }
}
