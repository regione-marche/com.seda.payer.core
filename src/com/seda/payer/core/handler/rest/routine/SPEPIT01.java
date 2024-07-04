package com.seda.payer.core.handler.rest.routine;

import java.util.HashMap;
import java.util.Map;

public class SPEPIT01 extends ARestRoutine {

	@Override
	protected String routine() {
		
		return "SPEPIT01";
	}

	@Override
	protected Map<Integer, String> inParameterMap() {
		
		int p = 1;
		
		Map<Integer, String> inParameterMap = new HashMap<Integer, String>();
		
		inParameterMap.put(p++, "data_in_cutecute_in");
		inParameterMap.put(p++, "data_in_cente_in");
		inParameterMap.put(p++, "data_in_codfisc_in");
		inParameterMap.put(p++, "data_in_datainiz_in");
		inParameterMap.put(p++, "data_in_datafine_in");
		inParameterMap.put(p++, "data_ou_retcode_in");
		inParameterMap.put(p++, "data_ou_messaggio_in");
		
		return inParameterMap;
	}

	@Override
	protected Map<Integer, String> outParameterMap() {
		
		int p = 1;
		
		Map<Integer, String> outParameterMap = new HashMap<Integer, String>();
		
		outParameterMap.put(p++, "data_in_cutecute_out");
		outParameterMap.put(p++, "data_in_cente_out");
		outParameterMap.put(p++, "data_in_codfisc_out");
		outParameterMap.put(p++, "data_in_datainiz_out");
		outParameterMap.put(p++, "data_in_datafine_out");
		outParameterMap.put(p++, "data_ou_retcode_out");
		outParameterMap.put(p++, "data_ou_messaggio_out");
		
		return outParameterMap;
	}

	@Override
	protected Map<Integer, Map<Integer, String>> resultSetsMap() {
		
		int p = 1;
		
		Map<Integer,String> resultSetMap1 = new HashMap<Integer, String>();
		
		resultSetMap1.put(p++, "TSES_CUTECUTE");
		resultSetMap1.put(p++, "TSES_CENTE");
		resultSetMap1.put(p++, "TSES_CODFISC");
		resultSetMap1.put(p++, "TSES_DATAPAG");
		resultSetMap1.put(p++, "TSES_DESIMPO");
		resultSetMap1.put(p++, "TSES_ANNIMPO");
		resultSetMap1.put(p++, "TSES_QUOTACS");
		resultSetMap1.put(p++, "TSES_RATAACS");
		resultSetMap1.put(p++, "TSES_IMPVERS");
		resultSetMap1.put(p++, "TSES_FLAGPAG");
		resultSetMap1.put(p++, "TSES_KEYPAGA");
		resultSetMap1.put(p++, "TSES_DENOMIN");
		resultSetMap1.put(p++, "TSES_INDRESI");
		resultSetMap1.put(p++, "TSES_COMRESI");
		resultSetMap1.put(p++, "TSES_CAPRESI");
		resultSetMap1.put(p++, "TSES_CAPIMMO");
		resultSetMap1.put(p++, "TSES_COMIMMO");
		resultSetMap1.put(p++, "TSES_IMPTERR");
		resultSetMap1.put(p++, "TSES_IMPARFA");
		resultSetMap1.put(p++, "TSES_IMPABPR");
		resultSetMap1.put(p++, "TSES_IMPALFA");
		resultSetMap1.put(p++, "TSES_IMPDETC");
		resultSetMap1.put(p++, "TSES_IMPDETS");
		resultSetMap1.put(p++, "TSES_IMPFARU");
		resultSetMap1.put(p++, "TSES_IMPIMPR");
		resultSetMap1.put(p++, "TSES_LUOPAGA");
		resultSetMap1.put(p++, "TSES_NUMFABB");
		resultSetMap1.put(p++, "TSES_RAVOPER");
		resultSetMap1.put(p++, "TSES_NUMSANZ");
		resultSetMap1.put(p++, "TSES_DATSANZ");
		resultSetMap1.put(p++, "TSES_IMPIMPO");
		resultSetMap1.put(p++, "TSES_IMPSOPR");
		resultSetMap1.put(p++, "TSES_IMPPENP");
		resultSetMap1.put(p++, "TSES_IMPINTE");
		resultSetMap1.put(p++, "TSES_IMPIMVE");
		
		Map<Integer, Map<Integer,String>> resultSetsMap = new HashMap<Integer, Map<Integer,String>>();
		resultSetsMap.put(0, resultSetMap1);
		
		return resultSetsMap;
	}

}
