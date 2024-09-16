package com.seda.payer.core.handler.rest.routine;

import java.util.HashMap;
import java.util.Map;

public class SPEPBOLL extends ARestRoutine {

	@Override
	protected String routine() {
		
		return "SPEPBOLL";
	}

	@Override
	protected Map<Integer, String> inParameterMap() {
		
		int p = 1;
		
		Map<Integer, String> inParameterMap = new HashMap<Integer, String>();
		
		inParameterMap.put(p++, "data_in_coduten_in");
		inParameterMap.put(p++, "data_in_codente_in");
		inParameterMap.put(p++, "data_io_numerav_in");
		inParameterMap.put(p++, "data_in_tipodato_in");
		inParameterMap.put(p++, "data_in_bolpagopa_in");
		inParameterMap.put(p++, "data_in_codifisc_in");
		inParameterMap.put(p++, "data_ou_imporrav_in");
		inParameterMap.put(p++, "data_io_numdoc_in");
		inParameterMap.put(p++, "data_ou_numrata_in");
		inParameterMap.put(p++, "data_ou_datascad_in");
		inParameterMap.put(p++, "data_ou_denom_in");
		inParameterMap.put(p++, "data_ou_codfisc_in");
		inParameterMap.put(p++, "data_ou_indir_in");
		inParameterMap.put(p++, "data_ou_citta_in");
		inParameterMap.put(p++, "data_io_ente_in");
		inParameterMap.put(p++, "data_io_imse_in");
		inParameterMap.put(p++, "data_ou_fl_oner_in");
		inParameterMap.put(p++, "data_ou_dt_inte_in");
		inParameterMap.put(p++, "data_ou_fl_mora_in");
		inParameterMap.put(p++, "data_ou_im_mora_in");
		inParameterMap.put(p++, "data_ou_fl_spese_in");
		inParameterMap.put(p++, "data_ou_im_spese_in");
		inParameterMap.put(p++, "data_ou_dt_calc_in");
		inParameterMap.put(p++, "data_ou_cc_in");
		inParameterMap.put(p++, "data_ou_change_imp_in");
		inParameterMap.put(p++, "data_ou_flag_ac_in");
		inParameterMap.put(p++, "data_ou_flag_sanz_in");
		inParameterMap.put(p++, "data_io_cbarcode_in");
		inParameterMap.put(p++, "data_ou_flag_multi_in");
		inParameterMap.put(p++, "data_ou_flag_mulcon_in");
		inParameterMap.put(p++, "data_ou_cod_freccia_in");
		inParameterMap.put(p++, "data_ou_mot_pagamen_in");
		inParameterMap.put(p++, "data_ou_anno_documento_in");
		inParameterMap.put(p++, "data_ou_ibanp_in");
		inParameterMap.put(p++, "data_ou_ibanb_in");
		inParameterMap.put(p++, "data_ou_tassonomia_in");
		inParameterMap.put(p++, "data_ou_causale_in");
		inParameterMap.put(p++, "data_ou_message_in");
		
		return inParameterMap;
	}

	@Override
	protected Map<Integer, String> outParameterMap() {
		
		int p = 1;
		
		Map<Integer, String> outParameterMap = new HashMap<Integer, String>();
		
		outParameterMap.put(p++, "data_in_coduten_out");
		outParameterMap.put(p++, "data_in_codente_out");
		outParameterMap.put(p++, "data_io_numerav_out");
		outParameterMap.put(p++, "data_in_tipodato_out");
		outParameterMap.put(p++, "data_in_bolpagopa_out");
		outParameterMap.put(p++, "data_in_codifisc_out");
		outParameterMap.put(p++, "data_ou_imporrav_out");
		outParameterMap.put(p++, "data_io_numdoc_out");
		outParameterMap.put(p++, "data_ou_numrata_out");
		outParameterMap.put(p++, "data_ou_datascad_out");
		outParameterMap.put(p++, "data_ou_denom_out");
		outParameterMap.put(p++, "data_ou_codfisc_out");
		outParameterMap.put(p++, "data_ou_indir_out");
		outParameterMap.put(p++, "data_ou_citta_out");
		outParameterMap.put(p++, "data_io_ente_out");
		outParameterMap.put(p++, "data_io_imse_out");
		outParameterMap.put(p++, "data_ou_fl_oner_out");
		outParameterMap.put(p++, "data_ou_dt_inte_out");
		outParameterMap.put(p++, "data_ou_fl_mora_out");
		outParameterMap.put(p++, "data_ou_im_mora_out");
		outParameterMap.put(p++, "data_ou_fl_spese_out");
		outParameterMap.put(p++, "data_ou_im_spese_out");
		outParameterMap.put(p++, "data_ou_dt_calc_out");
		outParameterMap.put(p++, "data_ou_cc_out");
		outParameterMap.put(p++, "data_ou_change_imp_out");
		outParameterMap.put(p++, "data_ou_flag_ac_out");
		outParameterMap.put(p++, "data_ou_flag_sanz_out");
		outParameterMap.put(p++, "data_io_cbarcode_out");
		outParameterMap.put(p++, "data_ou_flag_multi_out");
		outParameterMap.put(p++, "data_ou_flag_mulcon_out");
		outParameterMap.put(p++, "data_ou_cod_freccia_out");
		outParameterMap.put(p++, "data_ou_mot_pagamen_out");
		outParameterMap.put(p++, "data_ou_anno_documento_out");
		outParameterMap.put(p++, "data_ou_ibanp_out");
		outParameterMap.put(p++, "data_ou_ibanb_out");
		outParameterMap.put(p++, "data_ou_tassonomia_out");
		outParameterMap.put(p++, "data_ou_causale_out");
		outParameterMap.put(p++, "data_ou_pagopa_out"); //SB PGNTCORE-49
		outParameterMap.put(p++, "data_ou_message_out");
		
		return outParameterMap;
	}

	@Override
	protected Map<Integer, Map<Integer, String>> resultSetsMap() {
		
		int p = 1;
		
		Map<Integer,String> resultSetMap1 = new HashMap<Integer, String>();
		
		resultSetMap1.put(p++, "1");
		resultSetMap1.put(p++, "BOL_ENTE");
		resultSetMap1.put(p++, "BOL_IMSE");
		resultSetMap1.put(p++, "BOL_NUMDOC");
		resultSetMap1.put(p++, "BOL_NUMRATA");
		resultSetMap1.put(p++, "6");
		resultSetMap1.put(p++, "BOL_IMPORRAV");
		resultSetMap1.put(p++, "BOL_FL_ONER");
		resultSetMap1.put(p++, "9");
		resultSetMap1.put(p++, "BOL_FL_MORA");
		resultSetMap1.put(p++, "BOL_IM_MORA");
		resultSetMap1.put(p++, "BOL_FL_SPESE");
		resultSetMap1.put(p++, "BOL_IM_SPESE");
		resultSetMap1.put(p++, "14");
		resultSetMap1.put(p++, "BOL_CHANGE_IMP");
		resultSetMap1.put(p++, "BOL_FLAG_AC");
		resultSetMap1.put(p++, "BOL_FLAG_SANZ");
		
		p = 1;
		
		Map<Integer,String> resultSetMap2 = new HashMap<Integer, String>();

		resultSetMap2.put(p++, "BEN_IDDOMINIO");
		resultSetMap2.put(p++, "BEN_IMPORTO");
		resultSetMap2.put(p++, "BEN_IBANBANC");
		resultSetMap2.put(p++, "BEN_IBANPOST");
		resultSetMap2.put(p++, "BEN_TIPOSERV");
		resultSetMap2.put(p++, "BEN_KEYTRIBU");
		resultSetMap2.put(p++, "BEN_VALTRIBU");
		resultSetMap2.put(p++, "BEN_TASSONOM");

		p = 1;
		
		Map<Integer,String> resultSetMap3 = new HashMap<Integer, String>();
		
		// TODO
		
		Map<Integer, Map<Integer,String>> resultSetsMap = new HashMap<Integer, Map<Integer,String>>();
		resultSetsMap.put(0, resultSetMap1);
		resultSetsMap.put(1, resultSetMap2);
		resultSetsMap.put(2, resultSetMap3);
		
		return resultSetsMap;
	}
	
}
