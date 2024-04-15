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

        inParameterMap.put(p++, "data_in_cutecute_in");                  //DAU_CUTECUTE
        inParameterMap.put(p++, "data_in_codicesia_in");                 //DAU_CDCSCSIA
        inParameterMap.put(p++, "data_in_tipoautorizzazione_in");        //DAU_CDAUTPAU
        inParameterMap.put(p++, "data_in_codiceAutorizzazione_in");      //DAU_CDAUCOAU
        inParameterMap.put(p++, "data_ou_codiceVoceIncasso_in");         //DVI_CDVIVOCI
        inParameterMap.put(p++, "data_ou_codiceAbiCCAllineamento_in");   //DCS_CDVCABIA
        inParameterMap.put(p++, "data_ou_message_in");                   //message

        return inParameterMap;
    }

    @Override
    protected Map<Integer, String> outParameterMap() {

        int p = 1;

        Map<Integer, String> outParameterMap = new HashMap<Integer, String>();

        outParameterMap.put(p++, "data_ou_codiceVoceIncasso_out");         //O_DVI_CDVIVOCI
        outParameterMap.put(p++, "data_ou_codiceAbiCCAllineamento_out");   //O_DCS_CDVCABIA
        outParameterMap.put(p++, "data_ou_message_out");                   //message

        return outParameterMap;
    }

    @Override
    protected Map<Integer, Map<Integer, String>> resultSetsMap() {

        int p = 1;

        Map<Integer,String> resultSetMap1 = new HashMap<Integer, String>();

        resultSetMap1.put(p++, "DAU_CDCSCSIA");
        resultSetMap1.put(p++, "DAU_CDAUTPAU");
        resultSetMap1.put(p++, "DAU_CDAUCOAU");
        resultSetMap1.put(p++, "DAU_FDAUSTAT");
        resultSetMap1.put(p++, "DAU_GDAUVARI");

        Map<Integer, Map<Integer,String>> resultSetsMap = new HashMap<Integer, Map<Integer,String>>();
        resultSetsMap.put(0, resultSetMap1);

        return resultSetsMap;
    }
}
