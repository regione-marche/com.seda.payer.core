package com.seda.payer.core.handler.rest.routine;

import java.util.HashMap;
import java.util.Map;

public class SPEPSTE1 extends ARestRoutine {

	@Override
	protected String routine() {
		
		return "SPEPSTE1";
	}

	@Override
	protected Map<Integer, String> inParameterMap() {
		
		int p = 1;
		
		Map<Integer, String> inParameterMap = new HashMap<Integer, String>();
		
		inParameterMap.put(p++, "data_in_coduten_in");
		inParameterMap.put(p++, "data_in_codfisc_in");
		inParameterMap.put(p++, "data_in_entrate_in");
		inParameterMap.put(p++, "data_in_tiporic_in");
		inParameterMap.put(p++, "data_ou_retcode_in");
		inParameterMap.put(p++, "data_ou_message_in");
		
		return inParameterMap;
	}

	@Override
	protected Map<Integer, String> outParameterMap() {
		
		int p = 1;
		
		Map<Integer, String> outParameterMap = new HashMap<Integer, String>();
		
		outParameterMap.put(p++, "data_in_coduten_out");
		outParameterMap.put(p++, "data_in_codfisc_out");
		outParameterMap.put(p++, "data_in_entrate_out");
		outParameterMap.put(p++, "data_in_tiporic_out");
		outParameterMap.put(p++, "data_ou_retcode_out");
		outParameterMap.put(p++, "data_ou_message_out");
		
		return outParameterMap;
	}

	@Override
	protected Map<Integer, Map<Integer, String>> resultSetsMap() {
		
		int p = 1;
		
		Map<Integer,String> resultSetMap1 = new HashMap<Integer, String>();
		
		resultSetMap1.put(p++, "STE1_CUTECUTE");
		resultSetMap1.put(p++, "STE1_CODIENTE");
		resultSetMap1.put(p++, "STE1_TENTUFFI");
		resultSetMap1.put(p++, "STE1_CENTUFFI");
		
		Map<Integer, Map<Integer,String>> resultSetsMap = new HashMap<Integer, Map<Integer,String>>();
		resultSetsMap.put(0, resultSetMap1);
		
		return resultSetsMap;
	}

}
