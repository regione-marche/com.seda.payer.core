package com.seda.payer.core.handler.rest.routine;

import java.util.HashMap;
import java.util.Map;

public class SPISOGG5 extends ARestRoutine {

	@Override
	protected String routine() {
		
		return "SPISOGG5";
	}

	@Override
	protected Map<Integer, String> inParameterMap() {
		
		int p = 1;
		
		Map<Integer, String> inParameterMap = new HashMap<Integer, String>();
		
		inParameterMap.put(p++, "data_in_coduten_in");
		inParameterMap.put(p++, "data_in_codente_in");
		inParameterMap.put(p++, "data_in_codimse_in");
		inParameterMap.put(p++, "data_in_contrib_in");
		inParameterMap.put(p++, "data_ou_flagrid_in");
		inParameterMap.put(p++, "data_ou_autorid_in");
		inParameterMap.put(p++, "data_ou_codirid_in");
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
		outParameterMap.put(p++, "data_in_codimse_out");
		outParameterMap.put(p++, "data_in_contrib_out");
		outParameterMap.put(p++, "data_ou_flagrid_out");
		outParameterMap.put(p++, "data_ou_autorid_out");
		outParameterMap.put(p++, "data_ou_codirid_out");
		outParameterMap.put(p++, "data_ou_retcode_out");
		outParameterMap.put(p++, "data_ou_message_out");
		
		return outParameterMap;
	}

	@Override
	protected Map<Integer, Map<Integer, String>> resultSetsMap() {
		
		return null;
	}

}
