package com.seda.payer.core.handler.rest.routine;

import java.util.HashMap;
import java.util.Map;

public class SPEPSTC7 extends ARestRoutine {

	@Override
	protected String routine() {
		
		return "SPEPSTC7";
	}

	@Override
	protected Map<Integer, String> inParameterMap() {
		
		int p = 1;
		
		Map<Integer, String> inParameterMap = new HashMap<Integer, String>();
		
		inParameterMap.put(p++, "data_in_coduten_in");
		inParameterMap.put(p++, "data_in_codente_in");
		inParameterMap.put(p++, "data_in_codfisc_in");
		inParameterMap.put(p++, "data_in_entrate_in");
		inParameterMap.put(p++, "data_in_tiporic_in");
		inParameterMap.put(p++, "data_in_documento_in");
		inParameterMap.put(p++, "data_in_flgaccesso_in");
		inParameterMap.put(p++, "data_in_pcoocoob_in");
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
		outParameterMap.put(p++, "data_in_tiporic_out");
		outParameterMap.put(p++, "data_in_documento_out");
		outParameterMap.put(p++, "data_in_flgaccesso_out");
		outParameterMap.put(p++, "data_in_pcoocoob_out");
		outParameterMap.put(p++, "data_ou_retcode_out");
		outParameterMap.put(p++, "data_ou_message_out.filler1_out");
		
		return outParameterMap;
	}

	@Override
	protected Map<Integer, Map<Integer, String>> resultSetsMap() {
		
		int p = 1;
		
		Map<Integer,String> resultSetMap1 = new HashMap<Integer, String>();
		
		resultSetMap1.put(p++, "STC7_ANNOBCODE");
		resultSetMap1.put(p++, "2");
		resultSetMap1.put(p++, "STC7_BARCODE");
		resultSetMap1.put(p++, "STC7_TPSBTDOC");
		resultSetMap1.put(p++, "STC7_VISIBILITA");
		resultSetMap1.put(p++, "6");
		
		Map<Integer, Map<Integer,String>> resultSetsMap = new HashMap<Integer, Map<Integer,String>>();
		resultSetsMap.put(0, resultSetMap1);
		
		return resultSetsMap;
	}
	
}
