package com.seda.payer.core.handler.rest.routine;

import java.util.HashMap;
import java.util.Map;

public class SPEPSTC8 extends ARestRoutine {

	@Override
	protected String routine() {
		
		return "SPEPSTC8";
	}

	@Override
	protected Map<Integer, String> inParameterMap() {
		
		int p = 1;
		
		Map<Integer, String> inParameterMap = new HashMap<Integer, String>();
		
		inParameterMap.put(p++, "data_in_coduten_in");
		inParameterMap.put(p++, "data_in_codente_in");
		inParameterMap.put(p++, "data_in_codfisc_in");
		inParameterMap.put(p++, "data_in_entrate_in");
		inParameterMap.put(p++, "data_in_datiniz_in");
		inParameterMap.put(p++, "data_in_datfine_in");
		inParameterMap.put(p++, "data_in_filtri_in");
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
		outParameterMap.put(p++, "data_in_codfisc_out");
		outParameterMap.put(p++, "data_in_entrate_out");
		outParameterMap.put(p++, "data_in_datiniz_out");
		outParameterMap.put(p++, "data_in_datfine_out");
		outParameterMap.put(p++, "data_in_filtri_out");
		outParameterMap.put(p++, "data_ou_retcode_out");
		outParameterMap.put(p++, "data_ou_message_out.filler1_out");
		
		return outParameterMap;
	}

	@Override
	protected Map<Integer, Map<Integer, String>> resultSetsMap() {
		
		int p = 1;
		
		Map<Integer,String> resultSetMap1 = new HashMap<Integer, String>();
		
		resultSetMap1.put(p++, "STC8_CUTECUTE");
		resultSetMap1.put(p++, "2");
		resultSetMap1.put(p++, "STC8_ANNOEMIS");
		resultSetMap1.put(p++, "STC8_DOCUMENT");
		resultSetMap1.put(p++, "STC8_DESCIMSE");
		resultSetMap1.put(p++, "STC8_DESCENTE");
		resultSetMap1.put(p++, "7");
		resultSetMap1.put(p++, "STC8_LUOGOMOV");
		resultSetMap1.put(p++, "STC8_IMPOMOVI");
		
		Map<Integer, Map<Integer,String>> resultSetsMap = new HashMap<Integer, Map<Integer,String>>();
		resultSetsMap.put(0, resultSetMap1);
		
		return resultSetsMap;
	}
	
}
