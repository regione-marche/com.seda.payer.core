package com.seda.payer.core.handler.rest.routine;

import java.util.HashMap;
import java.util.Map;

public class SPISOGG4 extends ARestRoutine {

	@Override
	protected String routine() {
		
		return "SPISOGG4";
	}

	@Override
	protected Map<Integer, String> inParameterMap() {
		
		int p = 1;
		
		Map<Integer, String> inParameterMap = new HashMap<Integer, String>();
		
		inParameterMap.put(p++, "data_in_coduten_in");
		inParameterMap.put(p++, "data_in_codente_in");
		inParameterMap.put(p++, "data_in_codimse_in");
		inParameterMap.put(p++, "data_in_codfunz_in");
		inParameterMap.put(p++, "data_io_datiniz_in");
		inParameterMap.put(p++, "data_io_datfine_in");

		return inParameterMap;
	}

	@Override
	protected Map<Integer, String> outParameterMap() {
		
		int p = 1;
		
		Map<Integer, String> outParameterMap = new HashMap<Integer, String>();
		
		outParameterMap.put(p++, "data_in_coduten_out");
		outParameterMap.put(p++, "data_in_codente_out");
		outParameterMap.put(p++, "data_in_codimse_out");
		outParameterMap.put(p++, "data_in_codfunz_out");
		outParameterMap.put(p++, "data_io_datiniz_out");
		outParameterMap.put(p++, "data_io_datfine_out");
		outParameterMap.put(p++, "data_ou_retcode_out");
		outParameterMap.put(p++, "data_ou_message_out");

		return outParameterMap;
	}

	@Override
	protected Map<Integer, Map<Integer, String>> resultSetsMap() {
		
		return null;
	}

}
