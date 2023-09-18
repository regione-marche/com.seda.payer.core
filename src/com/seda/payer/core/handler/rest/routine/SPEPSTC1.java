package com.seda.payer.core.handler.rest.routine;

import java.util.HashMap;
import java.util.Map;

public class SPEPSTC1 extends ARestRoutine {

	@Override
	protected String routine() {
		
		return "SPEPSTC1";
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
		inParameterMap.put(p++, "data_in_filtri_in");
		inParameterMap.put(p++, "data_in_documento_in");
		inParameterMap.put(p++, "data_in_bollettino_in");
		inParameterMap.put(p++, "data_in_raccomandata_in");
		inParameterMap.put(p++, "data_in_cronologico_in");
		inParameterMap.put(p++, "data_in_idbarcode_in");
		inParameterMap.put(p++, "data_in_idprocedura_in");
		inParameterMap.put(p++, "data_in_limit_ecced_in");
		inParameterMap.put(p++, "data_in_ufficiale_in");
		inParameterMap.put(p++, "data_in_pcoocoob_in");
		inParameterMap.put(p++, "data_in_tipoufficiale_in");
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
		outParameterMap.put(p++, "data_in_filtri_out");
		outParameterMap.put(p++, "data_in_documento_out");
		outParameterMap.put(p++, "data_in_bollettino_out");
		outParameterMap.put(p++, "data_in_raccomandata_out");
		outParameterMap.put(p++, "data_in_cronologico_out");
		outParameterMap.put(p++, "data_in_idbarcode_out");
		outParameterMap.put(p++, "data_in_idprocedura_out");
		outParameterMap.put(p++, "data_in_limit_ecced_out");
		outParameterMap.put(p++, "data_in_ufficiale_out");
		outParameterMap.put(p++, "data_in_pcoocoob_out");
		outParameterMap.put(p++, "data_in_tipoufficiale_out");
		outParameterMap.put(p++, "data_ou_retcode_out");
		outParameterMap.put(p++, "data_ou_message_out");
		
		return outParameterMap;
	}

	@Override
	protected Map<Integer, Map<Integer, String>> resultSetsMap() {
		
		int p = 1;
		
		Map<Integer,String> resultSetMap1 = new HashMap<Integer, String>();
		
		resultSetMap1.put(p++, "STC1_CUTECUTE");
		resultSetMap1.put(p++, "STC1_CODIENTE");
		resultSetMap1.put(p++, "STC1_CODIFISC");
		resultSetMap1.put(p++, "STC1_TIPOSERV");
		resultSetMap1.put(p++, "STC1_TIPORICH");
		resultSetMap1.put(p++, "STC1_ANNOEMIS");
		resultSetMap1.put(p++, "STC1_IDDOCUME");
		resultSetMap1.put(p++, "STC1_IDCARTEL");
		resultSetMap1.put(p++, "STC1_DESCIMSE");
		resultSetMap1.put(p++, "STC1_TIPODOCU");
		resultSetMap1.put(p++, "STC1_STATODOC");
		resultSetMap1.put(p++, "STC1_DOCUDERI");
		resultSetMap1.put(p++, "STC1_TPDOCDER");
		resultSetMap1.put(p++, "STC1_NUMERAVV");
		resultSetMap1.put(p++, "STC1_DEMOMCBT");
		resultSetMap1.put(p++, "STC1_CODIIMSE");
		resultSetMap1.put(p++, "STC1_DESCENTE");
		resultSetMap1.put(p++, "STC1_DATANOTI");
		resultSetMap1.put(p++, "STC1_IMPOINIZ");
		resultSetMap1.put(p++, "STC1_IMPOPAGA");
		resultSetMap1.put(p++, "STC1_IMPORESI");
		resultSetMap1.put(p++, "STC1_ITRIBINI");
		resultSetMap1.put(p++, "STC1_ITRIBPAG");
		resultSetMap1.put(p++, "STC1_ITRIBRES");
		resultSetMap1.put(p++, "STC1_IDNOTINI");
		resultSetMap1.put(p++, "STC1_IDNOTPAG");
		resultSetMap1.put(p++, "STC1_IDNOTRES");
		resultSetMap1.put(p++, "STC1_ICMPIINI");
		resultSetMap1.put(p++, "STC1_ICMPIPAG");
		resultSetMap1.put(p++, "STC1_ICMPIRES");
		resultSetMap1.put(p++, "STC1_IINTMORA");
		resultSetMap1.put(p++, "STC1_IALTSPES");
		resultSetMap1.put(p++, "STC1_ITOTINIZ");
		resultSetMap1.put(p++, "STC1_ITOTPAGA");
		resultSetMap1.put(p++, "STC1_ITOTRESI");
		resultSetMap1.put(p++, "STC1_IMORINIZ");
		resultSetMap1.put(p++, "STC1_IASPINIZ");
		resultSetMap1.put(p++, "STC1_IMORPAGA");
		resultSetMap1.put(p++, "STC1_IASPPAGA");
		resultSetMap1.put(p++, "STC1_FLAGEPGF");
		resultSetMap1.put(p++, "STC1_FLAGEPMR");
		resultSetMap1.put(p++, "STC1_FLAGECCE");
		resultSetMap1.put(p++, "STC1_FLAGSANZ");
		resultSetMap1.put(p++, "STC1_TIPSERVI");
		resultSetMap1.put(p++, "STC1_BOLLFREC");
		resultSetMap1.put(p++, "STC1_CTIPODOC");
		resultSetMap1.put(p++, "STC1_ICO2INIZ");
		resultSetMap1.put(p++, "STC1_ICO2PAGA");
		resultSetMap1.put(p++, "STC1_ICO2RESI");
		resultSetMap1.put(p++, "STC1_FLAGPOAB");
		resultSetMap1.put(p++, "STC1_TIPODATO");
		resultSetMap1.put(p++, "STC1_FLAGEVID");
		resultSetMap1.put(p++, "STC1_PRIMARAT");
		resultSetMap1.put(p++, "STC1_FLATTCON");
		resultSetMap1.put(p++, "STC1_PCOOCOOB");
		resultSetMap1.put(p++, "STC1_CFDEBPRI");
		resultSetMap1.put(p++, "STC1_DEDEBPRI");
		resultSetMap1.put(p++, "STC1_FLAGSOSP");
		resultSetMap1.put(p++, "STC1_CAUSALE");
		resultSetMap1.put(p++, "STC1_DATAPAGA");
		resultSetMap1.put(p++, "STC1_IBANP");
		resultSetMap1.put(p++, "STC1_IBANB");
		resultSetMap1.put(p++, "STC1_TASSONOMIA");
		
		Map<Integer, Map<Integer,String>> resultSetsMap = new HashMap<Integer, Map<Integer,String>>();
		resultSetsMap.put(0, resultSetMap1);
		
		return resultSetsMap;
	}

}
