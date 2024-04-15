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

        inParameterMap.put(p++, "data_in_cutecute_in");        //IN DDE_CUTECUTE
        inParameterMap.put(p++, "data_in_ECS_CDCSCODE_in");    // IN ECS_CDCSCODE
        inParameterMap.put(p++, "data_in_BRS_KBRSKBRS_in");    // IN BRS_KBRSKBRS
        inParameterMap.put(p++, "data_in_DDE_CDDEOPEI_in");     //IN DDE_CDDEOPEI
        inParameterMap.put(p++, "data_ou_codicesepa_in");     //OUT O_CODSEPA
        inParameterMap.put(p++, "data_ou_sqlcode_in");   //OUT O_SQLCODE
        inParameterMap.put(p++, "data_ou_message_in");   //OUT O_MESSAGE

        return inParameterMap;
    }

    @Override
    protected Map<Integer, String> outParameterMap() {

        int p = 1;

        Map<Integer, String> outParameterMap = new HashMap<Integer, String>();

        //non sicuro

        outParameterMap.put(p++, "data_out_codicesepa_out");
        outParameterMap.put(p++, "data_out_sqlcode_out");
        outParameterMap.put(p++, "data_out_message_out");

        return null;
    }

    @Override
    protected Map<Integer, Map<Integer, String>> resultSetsMap() {
        //non ritorna nessun resultset essendo una insert
        return null;
    }
}
