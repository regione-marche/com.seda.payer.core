package com.seda.payer.core.handler.rest.routine;

import java.util.HashMap;
import java.util.Map;

public class SPEPWSTA extends ARestRoutine {

	@Override
	protected String routine() {
		return "SPEPWSTA";
	}

	@Override
	protected Map<Integer, String> inParameterMap() {
		
		int p = 1;
		
		Map<Integer, String> inParameterMap = new HashMap<Integer, String>();
		
		inParameterMap.put(p++, "data_in_coduten_in");
		inParameterMap.put(p++, "data_in_tiposer_in");
		inParameterMap.put(p++, "data_in_cente_in");
		inParameterMap.put(p++, "data_in_codimse_in");
		inParameterMap.put(p++, "data_in_docu_in");
		inParameterMap.put(p++, "data_in_codfisc_in");
		inParameterMap.put(p++, "data_ou_impdocu_in");
		inParameterMap.put(p++, "data_ou_tipostam_in");
		inParameterMap.put(p++, "data_ou_dataforn_in");
		inParameterMap.put(p++, "data_ou_descserv_in");
		inParameterMap.put(p++, "data_ou_denom_in");
		inParameterMap.put(p++, "data_ou_indifis_in");
		inParameterMap.put(p++, "data_ou_cap_in");
		inParameterMap.put(p++, "data_ou_comune_in");
		inParameterMap.put(p++, "data_ou_prov_in");
		inParameterMap.put(p++, "data_ou_cfisente_in");
		inParameterMap.put(p++, "data_ou_denoente_in");
		inParameterMap.put(p++, "data_ou_codinte_in");
		inParameterMap.put(p++, "data_ou_numecc_in");
		inParameterMap.put(p++, "data_ou_desccc_in");
		inParameterMap.put(p++, "data_ou_autccp_in");
		
		return inParameterMap;
	}

	@Override
	protected Map<Integer, String> outParameterMap() {
		
		int p = 1;
		
		Map<Integer, String> outParameterMap = new HashMap<Integer, String>();
		
		outParameterMap.put(p++, "data_in_coduten_out");
		outParameterMap.put(p++, "data_in_tiposer_out");
		outParameterMap.put(p++, "data_in_cente_out");
		outParameterMap.put(p++, "data_in_codimse_out");
		outParameterMap.put(p++, "data_in_docu_out");
		outParameterMap.put(p++, "data_in_codfisc_out");
		outParameterMap.put(p++, "data_ou_impdocu_out");
		outParameterMap.put(p++, "data_ou_tipostam_out");
		outParameterMap.put(p++, "data_ou_dataforn_out");
		outParameterMap.put(p++, "data_ou_descserv_out");
		outParameterMap.put(p++, "data_ou_denom_out");
		outParameterMap.put(p++, "data_ou_indifis_out");
		outParameterMap.put(p++, "data_ou_cap_out");
		outParameterMap.put(p++, "data_ou_comune_out");
		outParameterMap.put(p++, "data_ou_prov_out");
		outParameterMap.put(p++, "data_ou_cfisente_out");
		outParameterMap.put(p++, "data_ou_denoente_out");
		outParameterMap.put(p++, "data_ou_codinte_out");
		outParameterMap.put(p++, "data_ou_numecc_out");
		outParameterMap.put(p++, "data_ou_desccc_out");
		outParameterMap.put(p++, "data_ou_autccp_out");
		
		return outParameterMap;
	}

	@Override
	protected Map<Integer, Map<Integer, String>> resultSetsMap() {
		
		int p = 1;
		
		Map<Integer,String> resultSetMap1 = new HashMap<Integer, String>();
		
		resultSetMap1.put(p++, "WSTA_PROGRAT");
		resultSetMap1.put(p++, "WSTA_SCADRAT");
		resultSetMap1.put(p++, "WSTA_NUMEBOLL");
		resultSetMap1.put(p++, "WSTA_DESCON60");
		resultSetMap1.put(p++, "WSTA_QRCODE");
		resultSetMap1.put(p++, "WSTA_BARCODE");
		resultSetMap1.put(p++, "WSTA_CODIUV");
		resultSetMap1.put(p++, "WSTA_NUMEAVVI");
		resultSetMap1.put(p++, "WSTA_AUTCCP");
		resultSetMap1.put(p++, "WSTA_IMPORATA");
		
		Map<Integer, Map<Integer,String>> resultSetsMap = new HashMap<Integer, Map<Integer,String>>();
		resultSetsMap.put(0, resultSetMap1);
		
		return resultSetsMap;
	}

}
