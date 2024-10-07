package com.seda.payer.core.handler.rest.routine;

import java.util.HashMap;
import java.util.Map;

public class SPEPSTC6 extends ARestRoutine {

	@Override
	protected String routine() {
		
		return "SPEPSTC6";
	}

	@Override
	protected Map<Integer, String> inParameterMap() {
		
		int p = 1;
		
		Map<Integer, String> inParameterMap = new HashMap<Integer, String>();
		
		inParameterMap.put(p++, "data_in_coduten_in");
		inParameterMap.put(p++, "data_in_documento_in");
		inParameterMap.put(p++, "data_in_bollettino_in");
		inParameterMap.put(p++, "data_in_raccomandata_in");
		inParameterMap.put(p++, "data_in_cronologico_in");
		inParameterMap.put(p++, "data_in_idbarcode_in");
		inParameterMap.put(p++, "data_in_idprocedura_in");
		inParameterMap.put(p++, "data_in_pcoocoob_in");
		inParameterMap.put(p++, "data_ou_codifisc_in");
		inParameterMap.put(p++, "data_ou_retcode_in");
		inParameterMap.put(p++, "data_ou_message_in");
		
		return inParameterMap;
	}

	@Override
	protected Map<Integer, String> outParameterMap() {
		
		int p = 1;
		
		Map<Integer, String> outParameterMap = new HashMap<Integer, String>();
		
		outParameterMap.put(p++, "data_in_coduten_out");
		outParameterMap.put(p++, "data_in_documento_out");
		outParameterMap.put(p++, "data_in_bollettino_out");
		outParameterMap.put(p++, "data_in_raccomandata_out");
		outParameterMap.put(p++, "data_in_cronologico_out");
		outParameterMap.put(p++, "data_in_idbarcode_out");
		outParameterMap.put(p++, "data_in_idprocedura_out");
		outParameterMap.put(p++, "data_in_pcoocoob_out");
		outParameterMap.put(p++, "data_ou_codifisc_out");
		outParameterMap.put(p++, "data_ou_retcode_out");
		outParameterMap.put(p++, "data_ou_message_out");
		
		return outParameterMap;
	}

	@Override
	protected Map<Integer, Map<Integer, String>> resultSetsMap() {
		
		return null;
	}
	
}
