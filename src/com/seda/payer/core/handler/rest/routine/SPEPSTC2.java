package com.seda.payer.core.handler.rest.routine;

import java.util.HashMap;
import java.util.Map;

public class SPEPSTC2 extends ARestRoutine {

	@Override
	protected String routine() {
		
		return "SPEPSTC2";
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
		inParameterMap.put(p++, "data_in_pcoocoob_in");
		inParameterMap.put(p++, "data_ou_tipserv_in");
		inParameterMap.put(p++, "data_ou_flagpoab_in");
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
		outParameterMap.put(p++, "data_in_pcoocoob_out");
		outParameterMap.put(p++, "data_ou_tipserv_out");
		outParameterMap.put(p++, "data_ou_flagpoab_out");
		outParameterMap.put(p++, "data_ou_retcode_out");
		outParameterMap.put(p++, "data_ou_message_out.filler1_out");
		
		return outParameterMap;
	}

	@Override
	protected Map<Integer, Map<Integer, String>> resultSetsMap() {
		
		int p = 1;
		
		Map<Integer,String> resultSetMap1 = new HashMap<Integer, String>();
		
		resultSetMap1.put(p++, "STC2_CUTECUTE");
		resultSetMap1.put(p++, "STC2_CODIENTE");
		resultSetMap1.put(p++, "STC2_CODIFISC");
		resultSetMap1.put(p++, "STC2_TIPOSERV");
		resultSetMap1.put(p++, "STC2_TIPORICH");
		resultSetMap1.put(p++, "STC2_IDDOCUME");
		resultSetMap1.put(p++, "STC2_PROGRATA");
		resultSetMap1.put(p++, "STC2_DATASCAD");
		resultSetMap1.put(p++, "STC2_NUMERAVV");
		resultSetMap1.put(p++, "STC2_IRATINIZ");
		resultSetMap1.put(p++, "STC2_IRATRESI");
		resultSetMap1.put(p++, "STC2_INOTRESI");
		resultSetMap1.put(p++, "STC2_ICMPRESI");
		resultSetMap1.put(p++, "STC2_IMORRESI");
		resultSetMap1.put(p++, "STC2_ISPERESI");
		resultSetMap1.put(p++, "STC2_ITOTRESI");
		resultSetMap1.put(p++, "STC2_TIPORATA");
		resultSetMap1.put(p++, "STC2_FLAGEPGF");
		resultSetMap1.put(p++, "STC2_FLAGSANZ");
		resultSetMap1.put(p++, "STC2_BOLLFREC");
		resultSetMap1.put(p++, "STC2_CTIPODOC");
		resultSetMap1.put(p++, "STC2_TIPODATO");
		resultSetMap1.put(p++, "STC2_PROGOPZP");
		resultSetMap1.put(p++, "STC2_DESCOPZP");
		resultSetMap1.put(p++, "STC2_MAXPROPZ");
		resultSetMap1.put(p++, "STC2_NUMERATE");
		resultSetMap1.put(p++, "STC2_FRATUNIC");
		resultSetMap1.put(p++, "STC2_DSTATRAT");
		resultSetMap1.put(p++, "STC2_FRATTARD");
		resultSetMap1.put(p++, "STC2_FRATPAGA");
		resultSetMap1.put(p++, "STC2_IRATPAGA");
		resultSetMap1.put(p++, "STC2_ITOTCARI");
		
		Map<Integer, Map<Integer,String>> resultSetsMap = new HashMap<Integer, Map<Integer,String>>();
		resultSetsMap.put(0, resultSetMap1);
		
		return resultSetsMap;
	}

}
