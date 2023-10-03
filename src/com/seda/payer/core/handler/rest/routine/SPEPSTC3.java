package com.seda.payer.core.handler.rest.routine;

import java.util.HashMap;
import java.util.Map;

public class SPEPSTC3 extends ARestRoutine {

	@Override
	protected String routine() {
		
		return "SPEPSTC3";
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
		inParameterMap.put(p++, "data_in_documen_in");
		inParameterMap.put(p++, "data_in_f_pcoocoob_in");
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
		outParameterMap.put(p++, "data_in_documen_out");
		outParameterMap.put(p++, "data_in_f_pcoocoob_out");
		outParameterMap.put(p++, "data_in_pcoocoob_out");
		outParameterMap.put(p++, "data_ou_retcode_out");
		outParameterMap.put(p++, "data_ou_message_out");
		
		return outParameterMap;
	}

	@Override
	protected Map<Integer, Map<Integer, String>> resultSetsMap() {
		
		int p = 1;
		
		Map<Integer,String> resultSetMap1 = new HashMap<Integer, String>();
		
		resultSetMap1.put(p++, "STC3_CUTECUTE");
		resultSetMap1.put(p++, "STC3_CODIENTE");
		resultSetMap1.put(p++, "STC3_CODIFISC");
		resultSetMap1.put(p++, "STC3_TIPOSERV");
		resultSetMap1.put(p++, "STC3_TIPORICH");
		resultSetMap1.put(p++, "STC3_IDDOCUME");
		resultSetMap1.put(p++, "STC3_PROGRAT");
		resultSetMap1.put(p++, "8");
		resultSetMap1.put(p++, "9");
		resultSetMap1.put(p++, "STC3_LUOMOV");
		resultSetMap1.put(p++, "11");
		resultSetMap1.put(p++, "12");
		resultSetMap1.put(p++, "13");
		resultSetMap1.put(p++, "14");
		resultSetMap1.put(p++, "15");
		resultSetMap1.put(p++, "STC3_FLAGEPGF");
		resultSetMap1.put(p++, "STC3_DIPENDEN");
		resultSetMap1.put(p++, "STC3_CFOPERAZ");
		resultSetMap1.put(p++, "STC3_DEOPERAZ");
		
		Map<Integer, Map<Integer,String>> resultSetsMap = new HashMap<Integer, Map<Integer,String>>();
		resultSetsMap.put(0, resultSetMap1);
		
		return resultSetsMap;
	}

}
