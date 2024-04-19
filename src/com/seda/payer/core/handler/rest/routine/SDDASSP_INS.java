package com.seda.payer.core.handler.rest.routine;

import java.util.HashMap;
import java.util.Map;

public class SDDASSP_INS extends ARestRoutine {
    @Override
    protected String routine() {
        return "SDDASSP_INS";
    }

    @Override
    protected Map<Integer, String> inParameterMap() {

        //Rif AnagraficaSoggettoSEPA

        int p = 1;

        Map<Integer, String> inParameterMap = new HashMap<Integer, String>();

        inParameterMap.put(p++, "I_DAS_CUTECUTE");
        inParameterMap.put(p++, "I_DAS_CDASCFIS");
        inParameterMap.put(p++, "I_DAS_CDASSTAT");
        inParameterMap.put(p++, "I_DAS_DDASDENO");
        inParameterMap.put(p++, "I_DAS_DDASINDI");
        inParameterMap.put(p++, "I_DAS_CDASCCAP");
        inParameterMap.put(p++, "I_DAS_DDASLOCA");
        inParameterMap.put(p++, "I_DAS_CDASPROV");
        inParameterMap.put(p++, "I_DAS_CDASNTEL");
        inParameterMap.put(p++, "I_DAS_CDASMAIL");
        inParameterMap.put(p++, "I_DAS_CDASOPEI");
        inParameterMap.put(p++, "I_DAS_GDASINSE");
        inParameterMap.put(p++, "I_DAS_CDASOPEV");
        inParameterMap.put(p++, "I_DAS_GDASVARI");
       // inParameterMap.put(p++, "O_HV_SQLCODE");

        return inParameterMap;
    }

    @Override
    protected Map<Integer, String> outParameterMap() {

        int p = 1;

        Map<Integer, String> outParameterMap = new HashMap<Integer, String>();

        outParameterMap.put(p++, "O_HV_SQLCODE");

        return outParameterMap;
    }

    @Override
    protected Map<Integer, Map<Integer, String>> resultSetsMap() {

        //non ritorna nessun result set essendo una insert
        return null;
    }
}
