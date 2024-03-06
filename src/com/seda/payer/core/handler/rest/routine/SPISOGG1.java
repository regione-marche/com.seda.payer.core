package com.seda.payer.core.handler.rest.routine;

import java.util.HashMap;
import java.util.Map;

public class SPISOGG1 extends ARestRoutine {

	@Override
	protected String routine() {
		
		return "SPISOGG1";
	}

	@Override
	protected Map<Integer, String> inParameterMap() {
		
		int p = 1;
		
		Map<Integer, String> inParameterMap = new HashMap<Integer, String>();
		
		inParameterMap.put(p++, "data_in_coduten_in");
		inParameterMap.put(p++, "data_in_codente_in");
		inParameterMap.put(p++, "data_in_codimse_in");
		inParameterMap.put(p++, "data_in_request_in");
		inParameterMap.put(p++, "data_in_operato_in");
		inParameterMap.put(p++, "data_in_codbelf_in");
		inParameterMap.put(p++, "data_in_idautho_in");
		inParameterMap.put(p++, "data_in_cofpiva_in");
		inParameterMap.put(p++, "data_in_ragsocs_in");
		inParameterMap.put(p++, "data_in_indiriz_in");
		inParameterMap.put(p++, "data_in_dcomune_in");
		inParameterMap.put(p++, "data_in_codprov_in");
		inParameterMap.put(p++, "data_in_codicap_in");
		inParameterMap.put(p++, "data_io_contrib_in");
		inParameterMap.put(p++, "data_in_insegna_in");
		inParameterMap.put(p++, "data_in_titolar_in");
		inParameterMap.put(p++, "data_in_cofitit_in");
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
		outParameterMap.put(p++, "data_in_request_out");
		outParameterMap.put(p++, "data_in_operato_out");
		outParameterMap.put(p++, "data_in_codbelf_out");
		outParameterMap.put(p++, "data_in_idautho_out");
		outParameterMap.put(p++, "data_in_cofpiva_out");
		outParameterMap.put(p++, "data_in_ragsocs_out");
		outParameterMap.put(p++, "data_in_indiriz_out");
		outParameterMap.put(p++, "data_in_dcomune_out");
		outParameterMap.put(p++, "data_in_codprov_out");
		outParameterMap.put(p++, "data_in_codicap_out");
		outParameterMap.put(p++, "data_io_contrib_out");
		outParameterMap.put(p++, "data_in_insegna_out");
		outParameterMap.put(p++, "data_in_titolar_out");
		outParameterMap.put(p++, "data_in_cofitit_out");
		outParameterMap.put(p++, "data_ou_retcode_out");
		outParameterMap.put(p++, "data_ou_message_out");
		
		return outParameterMap;
	}

	@Override
	protected Map<Integer, Map<Integer, String>> resultSetsMap() {
		
		return null;
	}

}
