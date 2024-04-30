package com.seda.payer.core.handler.rest.routine;

import java.util.HashMap;
import java.util.Map;

public class SPISOGG2 extends ARestRoutine {

	@Override
	protected String routine() {
		
		return "SPISOGG2";
	}

	@Override
	protected Map<Integer, String> inParameterMap() {
		
		int p = 1;
		
		Map<Integer, String> inParameterMap = new HashMap<Integer, String>();
		
		inParameterMap.put(p++, "data_in_coduten_in");
		inParameterMap.put(p++, "data_in_codente_in");
		inParameterMap.put(p++, "data_in_codimse_in");
		inParameterMap.put(p++, "data_in_contrib_in");
		inParameterMap.put(p++, "data_in_annotri_in");
		inParameterMap.put(p++, "data_in_codtrib_in");
		inParameterMap.put(p++, "data_in_insegna_in");
		inParameterMap.put(p++, "data_in_indiriz_in");
		inParameterMap.put(p++, "data_in_dcomune_in");
		inParameterMap.put(p++, "data_in_codprov_in");
		inParameterMap.put(p++, "data_in_codicap_in");
		inParameterMap.put(p++, "data_in_importo_in");
		inParameterMap.put(p++, "data_in_datasca_in");
		inParameterMap.put(p++, "data_in_operato_in");
		inParameterMap.put(p++, "data_in_codbelf_in");
		inParameterMap.put(p++, "data_in_idautho_in");
		inParameterMap.put(p++, "data_in_cofpiva_in");
		inParameterMap.put(p++, "data_in_keyksan_in");
		inParameterMap.put(p++, "data_in_keyksct_in");
		inParameterMap.put(p++, "data_in_periodo_in");
		inParameterMap.put(p++, "data_in_tiporic_in");
		inParameterMap.put(p++, "data_in_notaaut_in");
		inParameterMap.put(p++, "data_in_notalib_in");
		inParameterMap.put(p++, "data_in_annodoc_in");
		inParameterMap.put(p++, "data_in_datacon_in");
		inParameterMap.put(p++, "data_in_datalim_in");
		inParameterMap.put(p++, "data_ou_ccoddoc_in");
		inParameterMap.put(p++, "data_ou_ccodfca_in");
		inParameterMap.put(p++, "data_ou_codirid_in");
		inParameterMap.put(p++, "data_ou_codirav_in");
		inParameterMap.put(p++, "data_ou_bollfrec_in");
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
		outParameterMap.put(p++, "data_in_codente_out");
		outParameterMap.put(p++, "data_in_codimse_out");
		outParameterMap.put(p++, "data_in_contrib_out");
		outParameterMap.put(p++, "data_in_annotri_out");
		outParameterMap.put(p++, "data_in_codtrib_out");
		outParameterMap.put(p++, "data_in_insegna_out");
		outParameterMap.put(p++, "data_in_indiriz_out");
		outParameterMap.put(p++, "data_in_dcomune_out");
		outParameterMap.put(p++, "data_in_codprov_out");
		outParameterMap.put(p++, "data_in_codicap_out");
		outParameterMap.put(p++, "data_in_importo_out");
		outParameterMap.put(p++, "data_in_datasca_out");
		outParameterMap.put(p++, "data_in_operato_out");
		outParameterMap.put(p++, "data_in_codbelf_out");
		outParameterMap.put(p++, "data_in_idautho_out");
		outParameterMap.put(p++, "data_in_cofpiva_out");
		outParameterMap.put(p++, "data_in_keyksan_out");
		outParameterMap.put(p++, "data_in_keyksct_out");
		outParameterMap.put(p++, "data_in_periodo_out");
		outParameterMap.put(p++, "data_in_tiporic_out");
		outParameterMap.put(p++, "data_in_notaaut_out");
		outParameterMap.put(p++, "data_in_notalib_out");
		outParameterMap.put(p++, "data_in_annodoc_out");
		outParameterMap.put(p++, "data_in_datacon_out");
		outParameterMap.put(p++, "data_in_datalim_out");
		outParameterMap.put(p++, "data_ou_ccoddoc_out");
		outParameterMap.put(p++, "data_ou_ccodfca_out");
		outParameterMap.put(p++, "data_ou_codirid_out");
		outParameterMap.put(p++, "data_ou_codirav_out");
		outParameterMap.put(p++, "data_ou_bollfrec_out");
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
