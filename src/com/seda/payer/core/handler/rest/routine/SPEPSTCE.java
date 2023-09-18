package com.seda.payer.core.handler.rest.routine;

import java.util.HashMap;
import java.util.Map;

public class SPEPSTCE extends ARestRoutine {

	@Override
	protected String routine() {
		
		return "SPEPSTCE";
	}

	@Override
	protected Map<Integer, String> inParameterMap() {
		
		int p = 1;
		
		Map<Integer, String> inParameterMap = new HashMap<Integer, String>();
		
		inParameterMap.put(p++, "data_in_coduten_in");
		inParameterMap.put(p++, "data_in_tiporich_in");
		inParameterMap.put(p++, "data_in_codiente_in");
		inParameterMap.put(p++, "data_in_codiserv_in");
		inParameterMap.put(p++, "data_in_codidocu_in");
		inParameterMap.put(p++, "data_io_codifisc_in");
		inParameterMap.put(p++, "data_in_pcoocoob_in");
		inParameterMap.put(p++, "data_io_flagssms_in");
		inParameterMap.put(p++, "data_io_numecell_in");
		inParameterMap.put(p++, "data_io_flagmail_in");
		inParameterMap.put(p++, "data_io_indimail_in");
		inParameterMap.put(p++, "data_io_flagmpec_in");
		inParameterMap.put(p++, "data_io_indimpec_in");
		inParameterMap.put(p++, "data_io_codicsdi_in");
		inParameterMap.put(p++, "data_ou_enteda5_in");
		inParameterMap.put(p++, "data_ou_dtemiss_in");
		inParameterMap.put(p++, "data_ou_causale_in");
		inParameterMap.put(p++, "data_ou_importo_in");
		inParameterMap.put(p++, "data_ou_datscad_in");
		inParameterMap.put(p++, "data_ou_retcode_in");
		inParameterMap.put(p++, "data_ou_message_in");
		
		return inParameterMap;
	}

	@Override
	protected Map<Integer, String> outParameterMap() {
		
		int p = 1;
		
		Map<Integer, String> outParameterMap = new HashMap<Integer, String>();
		
		outParameterMap.put(p++, "data_in_coduten_out");
		outParameterMap.put(p++, "data_in_tiporich_out");
		outParameterMap.put(p++, "data_in_codiente_out");
		outParameterMap.put(p++, "data_in_codiserv_out");
		outParameterMap.put(p++, "data_in_codidocu_out");
		outParameterMap.put(p++, "data_io_codifisc_out");
		outParameterMap.put(p++, "data_in_pcoocoob_out");
		outParameterMap.put(p++, "data_io_flagssms_out");
		outParameterMap.put(p++, "data_io_numecell_out");
		outParameterMap.put(p++, "data_io_flagmail_out");
		outParameterMap.put(p++, "data_io_indimail_out");
		outParameterMap.put(p++, "data_io_flagmpec_out");
		outParameterMap.put(p++, "data_io_indimpec_out");
		outParameterMap.put(p++, "data_io_codicsdi_out");
		outParameterMap.put(p++, "data_ou_enteda5_out");
		outParameterMap.put(p++, "data_ou_dtemiss_out");
		outParameterMap.put(p++, "data_ou_causale_out");
		outParameterMap.put(p++, "data_ou_importo_out");
		outParameterMap.put(p++, "data_ou_datscad_out");
		outParameterMap.put(p++, "data_ou_retcode_out");
		outParameterMap.put(p++, "data_ou_message_out.filler1_out");
		
		return outParameterMap;
	}

	@Override
	protected Map<Integer, Map<Integer, String>> resultSetsMap() {
		
		return null;
	}
	
}
