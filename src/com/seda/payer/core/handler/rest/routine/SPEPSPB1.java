package com.seda.payer.core.handler.rest.routine;

import java.util.HashMap;
import java.util.Map;

public class SPEPSPB1 extends ARestRoutine {

	@Override
	protected String routine() {
		
		return "SPEPSPB1";
	}

	@Override
	protected Map<Integer, String> inParameterMap() {
		
		int p = 1;
		
		Map<Integer, String> inParameterMap = new HashMap<Integer, String>();
		
		inParameterMap.put(p++, "data_in_coduten_in");
		inParameterMap.put(p++, "data_in_codente_in");
		inParameterMap.put(p++, "data_in_numboll_in.data_in_numboll_text_in");
		inParameterMap.put(p++, "data_ou_retcode_in");
		inParameterMap.put(p++, "data_ou_message_in");
		
		return inParameterMap;
	}

	@Override
	protected Map<Integer, String> outParameterMap() {
		
		int p = 1;
		
		Map<Integer, String> outParameterMap = new HashMap<Integer, String>();
		
		outParameterMap.put(p++, "data_in_coduten_out");
		outParameterMap.put(p++, "data_in_codente_out");
		outParameterMap.put(p++, "data_in_numboll_out.data_in_numboll_text_out");
		outParameterMap.put(p++, "data_ou_retcode_out");
		outParameterMap.put(p++, "data_ou_message_out.filler1_out");
		
		return outParameterMap;
	}

	@Override
	protected Map<Integer, Map<Integer, String>> resultSetsMap() {
		
		int p = 1;
		
		Map<Integer,String> resultSetMap1 = new HashMap<Integer, String>();
		
		resultSetMap1.put(p++, "SPB1_CUTECUTE");
		resultSetMap1.put(p++, "SPB1_CODIENTE");
		resultSetMap1.put(p++, "SPB1_NUMEBOLL");
		resultSetMap1.put(p++, "4");
		resultSetMap1.put(p++, "SPB1_ITOTPAGA");
		resultSetMap1.put(p++, "SPB1_LUOMOV");
		resultSetMap1.put(p++, "SPB1_FLAGEPGF");
		
		Map<Integer, Map<Integer,String>> resultSetsMap = new HashMap<Integer, Map<Integer,String>>();
		resultSetsMap.put(0, resultSetMap1);
		
		return resultSetsMap;
	}
	
}