package com.seda.payer.core.handler.rest.routine;

import java.util.HashMap;
import java.util.Map;

public class SDDAUSP_INS extends ARestRoutine {
    @Override
    protected String routine() {
        return "SDDAUSP_INS";
    }

    @Override
    protected Map<Integer, String> inParameterMap() {

        int p = 1;

        Map<Integer, String> inParameterMap = new HashMap<Integer, String>();

        inParameterMap.put(p++, "I_DAU_CUTECUTE");
        inParameterMap.put(p++, "I_DAU_CDCSCSIA");
        inParameterMap.put(p++, "I_DAU_CDAUTPAU");
        inParameterMap.put(p++, "I_DAU_CDAUCOAU");
        inParameterMap.put(p++, "I_DAU_FDAUSTAT");
        inParameterMap.put(p++, "I_DAU_CDAUCFIS");
        inParameterMap.put(p++, "I_DAU_CDAUCFII");
        inParameterMap.put(p++, "I_DAU_CDAUCPCC");
        inParameterMap.put(p++, "I_DAU_CDAUCINT");
        inParameterMap.put(p++, "I_DAU_CDAUCABI");
        inParameterMap.put(p++, "I_DAU_CDAUCCAB");
        inParameterMap.put(p++, "I_DAU_CDAUCNUM");
        inParameterMap.put(p++, "I_DAU_CDAUCCIN");
        inParameterMap.put(p++, "I_DAU_GDAUPREV");
        inParameterMap.put(p++, "I_DAU_CDAUCPRO");
        inParameterMap.put(p++, "I_DAU_CDAUTIPP");
        inParameterMap.put(p++, "I_DAU_CDAUDOCO");
        inParameterMap.put(p++, "I_DAU_GDAUDINV");
        inParameterMap.put(p++, "I_DAU_GDAUDFNV");
        inParameterMap.put(p++, "I_DAU_GDAUREVO");
        inParameterMap.put(p++, "I_DAU_CDAUTPAG");
        inParameterMap.put(p++, "I_DAU_CDVIVOCI");
        inParameterMap.put(p++, "I_DAU_CDAUIDEB");
        inParameterMap.put(p++, "I_DAU_CDVCABIA");
        inParameterMap.put(p++, "I_DAU_FDAUSOSP");
        inParameterMap.put(p++, "I_DAU_GDAUSOSP");
        inParameterMap.put(p++, "I_DAU_CDAUOPES");
        inParameterMap.put(p++, "I_DAU_GDAUREVS");
        inParameterMap.put(p++, "I_DAU_CDAUOPER");
        inParameterMap.put(p++, "I_DAU_FDAUFRIM");
        inParameterMap.put(p++, "I_DAU_IDAUIMPP");
        inParameterMap.put(p++, "I_DAU_CDAUCBIC");
        inParameterMap.put(p++, "I_DAU_FDAUCCON");
        inParameterMap.put(p++, "I_DAU_GDAUALLI");
        inParameterMap.put(p++, "I_DAU_FDAUFALL");
        inParameterMap.put(p++, "I_DAU_GDAUESIC");
        inParameterMap.put(p++, "I_DAU_CDAUTPMA");
        inParameterMap.put(p++, "I_DAU_CDAUDTMA");
        inParameterMap.put(p++, "I_DAU_CDAUTPIN");
        inParameterMap.put(p++, "I_DAU_CDAUOPEI");
        inParameterMap.put(p++, "I_DAU_GDAUINSE");
        inParameterMap.put(p++, "I_DAU_CDAUOPEV");
        inParameterMap.put(p++, "I_DAU_GDAUVARI");
        inParameterMap.put(p++, "I_DAU_GDAUSOTT");
        inParameterMap.put(p++, "I_DAU_CDAUMIGR");
        inParameterMap.put(p++, "I_DAU_FDAUFVAR");
        inParameterMap.put(p++, "I_DAU_CDAUMAIL");
        //inParameterMap.put(p++, "O_HV_SQLCODE");

        return inParameterMap;
    }

    @Override
    protected Map<Integer, String> outParameterMap() {

        int p = 1;

        Map<Integer, String> outParameterMap = new HashMap<Integer, String>();

        /*
        * outParameterMap.put(p++, "data_ou_hvsqlcode_out");                  //HV_SQLCODE
        * */
        outParameterMap.put(p++, "O_HV_SQLCODE");                  //HV_SQLCODE

        return outParameterMap;
    }

    @Override
    protected Map<Integer, Map<Integer, String>> resultSetsMap() {

        //NB non restituisce nulla

        return null;
    }
}
