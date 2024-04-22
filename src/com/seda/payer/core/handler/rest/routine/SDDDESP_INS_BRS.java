package com.seda.payer.core.handler.rest.routine;

import java.util.HashMap;
import java.util.Map;

public class SDDDESP_INS_BRS  extends ARestRoutine {
    @Override
    protected String routine() {
        return "SDDDESP_INS_BRS";
    }

    @Override
    protected Map<Integer, String> inParameterMap() {

        int p = 1;

        Map<Integer, String> inParameterMap = new HashMap<Integer, String>();

        inParameterMap.put(p++, "I_DDE_CUTECUTE");
        inParameterMap.put(p++, "I_ECS_CDCSCODE");
        inParameterMap.put(p++, "I_BRS_KBRSKBRS");
        inParameterMap.put(p++, "I_DDE_CDDEOPEI");
       // inParameterMap.put(p++, "O_CODSEPA");
       // inParameterMap.put(p++, "O_SQLCODE");
       // inParameterMap.put(p++, "O_MESSAGE");


        return inParameterMap;
    }

    @Override
    protected Map<Integer, String> outParameterMap() {

        int p = 1;

        Map<Integer, String> outParameterMap = new HashMap<Integer, String>();

        outParameterMap.put(p++, "O_CODSEPA");
        outParameterMap.put(p++, "O_SQLCODE");
        outParameterMap.put(p++, "O_MESSAGE");

        return outParameterMap;
    }

    @Override
    protected Map<Integer, Map<Integer, String>> resultSetsMap() {
        //non ritorna nessun resultset essendo una insert
        return null;
    }
}
