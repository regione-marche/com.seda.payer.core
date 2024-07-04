package com.seda.payer.core.handler.rest.routine;

import java.util.HashMap;
import java.util.Map;

public class SDDAUSP_SEL extends ARestRoutine {
    @Override
    protected String routine() {
        return "SDDAUSP_SEL";
    }

    @Override
    protected Map<Integer, String> inParameterMap() {

        int p = 1;

        Map<Integer, String> inParameterMap = new HashMap<Integer, String>();

        inParameterMap.put(p++, "I_DAU_CUTECUTE");
        inParameterMap.put(p++, "I_DAU_CDCSCSIA");
        inParameterMap.put(p++, "I_DAU_CDAUTPAU");
        inParameterMap.put(p++, "I_DAU_CDAUCOAU");
        inParameterMap.put(p++, "I_DAU_CDVIVOCI");
        inParameterMap.put(p++, "I_DAU_FDAUSTAT");
        inParameterMap.put(p++, "O_NUMROWS");
        inParameterMap.put(p++, "O_RETCODE");
        inParameterMap.put(p++, "O_RETMESSAGE");

        return inParameterMap;
    }

    @Override
    protected Map<Integer, String> outParameterMap() {

        int p = 1;

        Map<Integer, String> outParameterMap = new HashMap<Integer, String>();

        outParameterMap.put(p++, "O_NUMROWS");
        outParameterMap.put(p++, "O_RETCODE");
        outParameterMap.put(p++, "O_RETMESSAGE");

        return outParameterMap;
    }

    @Override
    protected Map<Integer, Map<Integer, String>> resultSetsMap() {

        int p = 1;

        Map<Integer,String> resultSetMap1 = new HashMap<Integer, String>();

        resultSetMap1.put(p++, "DAU_CUTECUTE");
        resultSetMap1.put(p++, "DAU_CDCSCSIA");
        resultSetMap1.put(p++, "DAU_CDAUTPAU");
        resultSetMap1.put(p++, "DAU_CDAUCOAU");
        resultSetMap1.put(p++, "DAU_FDAUSTAT");
        resultSetMap1.put(p++, "DAU_CDAUCFIS");
        resultSetMap1.put(p++, "DAU_CDAUCFII");
        resultSetMap1.put(p++, "DAU_CDAUCPCC");
        resultSetMap1.put(p++, "DAU_CDAUCINT");
        resultSetMap1.put(p++, "DAU_CDAUCABI");
        resultSetMap1.put(p++, "DAU_CDAUCCAB");
        resultSetMap1.put(p++, "DAU_CDAUCNUM");
        resultSetMap1.put(p++, "DAU_CDAUCCIN");
        resultSetMap1.put(p++, "DAU_GDAUPREV");
        resultSetMap1.put(p++, "DAU_CDAUCPRO");
        resultSetMap1.put(p++, "DAU_CDAUTIPP");
        resultSetMap1.put(p++, "DAU_CDAUDOCO");
        resultSetMap1.put(p++, "DAU_GDAUDINV");
        resultSetMap1.put(p++, "DAU_GDAUDFNV");
        resultSetMap1.put(p++, "DAU_GDAUREVO");
        resultSetMap1.put(p++, "DAU_CDAUTPAG");
        resultSetMap1.put(p++, "DAU_CDVIVOCI");
        resultSetMap1.put(p++, "DAU_CDAUIDEB");
        resultSetMap1.put(p++, "DAU_CDVCABIA");
        resultSetMap1.put(p++, "DAU_FDAUSOSP");
        resultSetMap1.put(p++, "DAU_GDAUSOSP");
        resultSetMap1.put(p++, "DAU_CDAUOPES");
        resultSetMap1.put(p++, "DAU_GDAUREVS");
        resultSetMap1.put(p++, "DAU_CDAUOPER");
        resultSetMap1.put(p++, "DAU_FDAUFRIM");
        resultSetMap1.put(p++, "DAU_IDAUIMPP");
        resultSetMap1.put(p++, "DAU_CDAUCBIC");
        resultSetMap1.put(p++, "DAU_FDAUCCON");
        resultSetMap1.put(p++, "DAU_GDAUALLI");
        resultSetMap1.put(p++, "DAU_FDAUFALL");
        resultSetMap1.put(p++, "DAU_GDAUESIC");
        resultSetMap1.put(p++, "DAU_CDAUTPMA");
        resultSetMap1.put(p++, "DAU_CDAUDTMA");
        resultSetMap1.put(p++, "DAU_CDAUTPIN");
        resultSetMap1.put(p++, "DAU_CDAUOPEI");
        resultSetMap1.put(p++, "DAU_GDAUINSE");
        resultSetMap1.put(p++, "DAU_CDAUOPEV");
        resultSetMap1.put(p++, "DAU_GDAUVARI");
        resultSetMap1.put(p++, "DAU_CDAUMAIL");
        resultSetMap1.put(p++, "DENO_SOTTOS");
        resultSetMap1.put(p++, "DENO_INTEST");
        resultSetMap1.put(p++, "DESC_VOCEINCASSO");
        resultSetMap1.put(p++, "DAU_GDAUSOTT");
        resultSetMap1.put(p++, "DOCMR");
        resultSetMap1.put(p++, "DAU_FDAUFVAR");

        Map<Integer, Map<Integer,String>> resultSetsMap = new HashMap<Integer, Map<Integer,String>>();
        resultSetsMap.put(0, resultSetMap1);

        return resultSetsMap;
    }
}
