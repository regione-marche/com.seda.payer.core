package com.seda.payer.core.handler.rest.routine;

import java.util.HashMap;
import java.util.Map;

public class SPEPSTC4 extends ARestRoutine {

	@Override
	protected String routine() {
		
		return "SPEPSTC4";
	}

	@Override
	protected Map<Integer, String> inParameterMap() {
		
		int p = 1;
		
		Map<Integer, String> inParameterMap = new HashMap<Integer, String>();
		
		inParameterMap.put(p++, "data_in_coduten_in");
		inParameterMap.put(p++, "data_in_codente_in");
		inParameterMap.put(p++, "data_in_codfisc_in");
		inParameterMap.put(p++, "data_in_entrate_in");
		inParameterMap.put(p++, "data_in_documen_in");
		inParameterMap.put(p++, "data_in_tiporav_in");
		inParameterMap.put(p++, "data_in_numrat3_in");
		inParameterMap.put(p++, "data_in_flaglimt_in");
		inParameterMap.put(p++, "data_in_tributi_in");
		inParameterMap.put(p++, "data_in_oneri_in");
		inParameterMap.put(p++, "data_in_pcoocoob_in");
		inParameterMap.put(p++, "data_in_codfunz_in");
		inParameterMap.put(p++, "data_ou_nome_in");
		inParameterMap.put(p++, "data_ou_cognome_in");
		inParameterMap.put(p++, "data_ou_cc_in");
		inParameterMap.put(p++, "data_ou_numerav_in");
		inParameterMap.put(p++, "data_ou_autccp_in");
		inParameterMap.put(p++, "data_ou_dtnasc_in");
		inParameterMap.put(p++, "data_ou_sesso_in");
		inParameterMap.put(p++, "data_ou_comunasc_in");
		inParameterMap.put(p++, "data_ou_provnasc_in");
		inParameterMap.put(p++, "data_ou_desconto_in");
		inParameterMap.put(p++, "data_ou_tipocc_in");
		inParameterMap.put(p++, "data_ou_descente_in");
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
		outParameterMap.put(p++, "data_in_codente_out");
		outParameterMap.put(p++, "data_in_codfisc_out");
		outParameterMap.put(p++, "data_in_entrate_out");
		outParameterMap.put(p++, "data_in_documen_out");
		outParameterMap.put(p++, "data_in_tiporav_out");
		outParameterMap.put(p++, "data_in_numrat3_out");
		outParameterMap.put(p++, "data_in_flaglimt_out");
		outParameterMap.put(p++, "data_in_tributi_out");
		outParameterMap.put(p++, "data_in_oneri_out");
		outParameterMap.put(p++, "data_in_pcoocoob_out");
		outParameterMap.put(p++, "data_in_codfunz_out");
		outParameterMap.put(p++, "data_ou_nome_out");
		outParameterMap.put(p++, "data_ou_cognome_out");
		outParameterMap.put(p++, "data_ou_cc_out");
		outParameterMap.put(p++, "data_ou_numerav_out");
		outParameterMap.put(p++, "data_ou_autccp_out");
		outParameterMap.put(p++, "data_ou_dtnasc_out");
		outParameterMap.put(p++, "data_ou_sesso_out");
		outParameterMap.put(p++, "data_ou_comunasc_out");
		outParameterMap.put(p++, "data_ou_provnasc_out");
		outParameterMap.put(p++, "data_ou_desconto_out");
		outParameterMap.put(p++, "data_ou_tipocc_out");
		outParameterMap.put(p++, "data_ou_descente_out");
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
		outParameterMap.put(p++, "data_ou_message_out.filler1_out");
		
		return outParameterMap;
	}

	@Override
	protected Map<Integer, Map<Integer, String>> resultSetsMap() {
		
		int p = 1;
		
		Map<Integer,String> resultSetMap1 = new HashMap<Integer, String>();
		
		resultSetMap1.put(p++, "STC4_CUTECUTE");
		resultSetMap1.put(p++, "STC4_TIPOTRIB");
		resultSetMap1.put(p++, "STC4_CODTRIB");
		resultSetMap1.put(p++, "STC4_CENTBELL");
		resultSetMap1.put(p++, "STC4_RATEAZ");
		resultSetMap1.put(p++, "STC4_ANNORIF");
		resultSetMap1.put(p++, "STC4_IMPOPAGA");
		
		Map<Integer, Map<Integer,String>> resultSetsMap = new HashMap<Integer, Map<Integer,String>>();
		resultSetsMap.put(0, resultSetMap1);
		
		return resultSetsMap;
	}

}
