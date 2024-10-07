package com.seda.payer.core.handler.rest.routine;

import java.util.HashMap;
import java.util.Map;

public class SPISOGG7 extends ARestRoutine {

	@Override
	protected String routine() {
		
		return "SPISOGG7";
	}

	@Override
	protected Map<Integer, String> inParameterMap() {
		
		int p = 1;
		
		Map<Integer, String> inParameterMap = new HashMap<Integer, String>();
		
		inParameterMap.put(p++, "data_in_coduten_in");
		inParameterMap.put(p++, "data_in_numerav_in");
		inParameterMap.put(p++, "data_in_flag_zero_in");
		inParameterMap.put(p++, "data_ou_imporrav_in");
		inParameterMap.put(p++, "data_ou_cod_freccia_in");
		inParameterMap.put(p++, "data_ou_descente_in");
		inParameterMap.put(p++, "data_ou_tipocc_in");
		inParameterMap.put(p++, "data_ou_numecc_in");
		inParameterMap.put(p++, "data_ou_desccc_in");
		inParameterMap.put(p++, "data_ou_autccp_in");
		inParameterMap.put(p++, "data_ou_cofiente_in");
		inParameterMap.put(p++, "data_ou_codcbill_in");
		inParameterMap.put(p++, "data_ou_barcode_in");
		inParameterMap.put(p++, "data_ou_qrcode_in");
		inParameterMap.put(p++, "data_ou_causale_in");
		inParameterMap.put(p++, "data_ou_descuffi_in");
		inParameterMap.put(p++, "data_ou_desctser_in");
		inParameterMap.put(p++, "data_ou_descserv_in");
		inParameterMap.put(p++, "data_ou_numeavvi_in");
		inParameterMap.put(p++, "data_ou_codiiuv_in");
		inParameterMap.put(p++, "data_ou_retcode_in");
		inParameterMap.put(p++, "data_ou_message_in");
		
		return inParameterMap;
	}

	@Override
	protected Map<Integer, String> outParameterMap() {
		
		int p = 1;
		
		Map<Integer, String> outParameterMap = new HashMap<Integer, String>();
		
		outParameterMap.put(p++, "data_in_coduten_out");
		outParameterMap.put(p++, "data_in_numerav_out");
		outParameterMap.put(p++, "data_in_flag_zero_out");
		outParameterMap.put(p++, "data_ou_imporrav_out");
		outParameterMap.put(p++, "data_ou_cod_freccia_out");
		outParameterMap.put(p++, "data_ou_descente_out");
		outParameterMap.put(p++, "data_ou_tipocc_out");
		outParameterMap.put(p++, "data_ou_numecc_out");
		outParameterMap.put(p++, "data_ou_desccc_out");
		outParameterMap.put(p++, "data_ou_autccp_out");
		outParameterMap.put(p++, "data_ou_cofiente_out");
		outParameterMap.put(p++, "data_ou_codcbill_out");
		outParameterMap.put(p++, "data_ou_barcode_out");
		outParameterMap.put(p++, "data_ou_qrcode_out");
		outParameterMap.put(p++, "data_ou_causale_out");
		outParameterMap.put(p++, "data_ou_descuffi_out");
		outParameterMap.put(p++, "data_ou_desctser_out");
		outParameterMap.put(p++, "data_ou_descserv_out");
		outParameterMap.put(p++, "data_ou_numeavvi_out");
		outParameterMap.put(p++, "data_ou_codiiuv_out");
		outParameterMap.put(p++, "data_ou_retcode_out");
		outParameterMap.put(p++, "data_ou_message_out");
		
		return outParameterMap;
	}

	@Override
	protected Map<Integer, Map<Integer, String>> resultSetsMap() {
		
		return null;
	}

}
