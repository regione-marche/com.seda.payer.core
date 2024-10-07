package com.seda.payer.core.handler.rest.routine;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

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

		return Optional.of(new HashMap<Integer, Map<Integer,String>>())
			.map(rsm -> {
				int p = 1;
				Map<Integer,String> resultSetMap = new HashMap<Integer, String>();
				resultSetMap.put(p++, "STC1_CUTECUTE");
				resultSetMap.put(p++, "STC1_CODIENTE");
				resultSetMap.put(p++, "STC1_CODIFISC");
				resultSetMap.put(p++, "STC1_TIPOSERV");
				resultSetMap.put(p++, "STC1_TIPORICH");
				resultSetMap.put(p++, "STC1_ANNOEMIS");
				resultSetMap.put(p++, "STC1_IDDOCUME");
				resultSetMap.put(p++, "STC1_IDCARTEL");
				resultSetMap.put(p++, "STC1_DESCIMSE");
				resultSetMap.put(p++, "STC1_TIPODOCU");
				resultSetMap.put(p++, "STC1_STATODOC");
				resultSetMap.put(p++, "STC1_DOCUDERI");
				resultSetMap.put(p++, "STC1_TPDOCDER");
				resultSetMap.put(p++, "STC1_NUMERAVV");
				resultSetMap.put(p++, "STC1_DEMOMCBT");
				resultSetMap.put(p++, "STC1_CODIIMSE");
				resultSetMap.put(p++, "STC1_DESCENTE");
				resultSetMap.put(p++, "STC1_DATANOTI");
				resultSetMap.put(p++, "STC1_IMPOINIZ");
				resultSetMap.put(p++, "STC1_IMPOPAGA");
				resultSetMap.put(p++, "STC1_IMPORESI");
				resultSetMap.put(p++, "STC1_ITRIBINI");
				resultSetMap.put(p++, "STC1_ITRIBPAG");
				resultSetMap.put(p++, "STC1_ITRIBRES");
				resultSetMap.put(p++, "STC1_IDNOTINI");
				resultSetMap.put(p++, "STC1_IDNOTPAG");
				resultSetMap.put(p++, "STC1_IDNOTRES");
				resultSetMap.put(p++, "STC1_ICMPIINI");
				resultSetMap.put(p++, "STC1_ICMPIPAG");
				resultSetMap.put(p++, "STC1_ICMPIRES");
				resultSetMap.put(p++, "STC1_IINTMORA");
				resultSetMap.put(p++, "STC1_IALTSPES");
				resultSetMap.put(p++, "STC1_ITOTINIZ");
				resultSetMap.put(p++, "STC1_ITOTPAGA");
				resultSetMap.put(p++, "STC1_ITOTRESI");
				resultSetMap.put(p++, "STC1_IMORINIZ");
				resultSetMap.put(p++, "STC1_IASPINIZ");
				resultSetMap.put(p++, "STC1_IMORPAGA");
				resultSetMap.put(p++, "STC1_IASPPAGA");
				resultSetMap.put(p++, "STC1_FLAGEPGF");
				resultSetMap.put(p++, "STC1_FLAGEPMR");
				resultSetMap.put(p++, "STC1_FLAGECCE");
				resultSetMap.put(p++, "STC1_FLAGSANZ");
				resultSetMap.put(p++, "STC1_TIPSERVI");
				resultSetMap.put(p++, "STC1_BOLLFREC");
				resultSetMap.put(p++, "STC1_CTIPODOC");
				resultSetMap.put(p++, "STC1_ICO2INIZ");
				resultSetMap.put(p++, "STC1_ICO2PAGA");
				resultSetMap.put(p++, "STC1_ICO2RESI");
				resultSetMap.put(p++, "STC1_FLAGPOAB");
				resultSetMap.put(p++, "STC1_TIPODATO");
				resultSetMap.put(p++, "STC1_FLAGEVID");
				resultSetMap.put(p++, "STC1_PRIMARAT");
				resultSetMap.put(p++, "STC1_FLATTCON");
				resultSetMap.put(p++, "STC1_PCOOCOOB");
				resultSetMap.put(p++, "STC1_CFDEBPRI");
				resultSetMap.put(p++, "STC1_DEDEBPRI");
				resultSetMap.put(p++, "STC1_FLAGSOSP");
				resultSetMap.put(p++, "STC1_CAUSALE");
				resultSetMap.put(p++, "STC1_DATAPAGA");
				resultSetMap.put(p++, "STC1_IBANP");
				resultSetMap.put(p++, "STC1_IBANB");
				resultSetMap.put(p++, "STC1_TASSONOMIA");
				resultSetMap.put(p++, "STC1_FLMANAGER");
				resultSetMap.put(p++, "STC1_NOTEDISC");
				rsm.put(0, resultSetMap);
				return rsm;						
			})
			.map(rsm -> {
				int p = 1;
				Map<Integer,String> resultSetMap = new HashMap<Integer, String>();
				resultSetMap.put(p++, "BEN_CUTECUTE");
				resultSetMap.put(p++, "BEN_CENTENTE");
				resultSetMap.put(p++, "BEN_TENTUFFI");
				resultSetMap.put(p++, "BEN_CENTUFFI");
				resultSetMap.put(p++, "BEN_CTIPOSER");
				resultSetMap.put(p++, "BEN_SETTORE");
				resultSetMap.put(p++, "BEN_NUMEDOCU");
				resultSetMap.put(p++, "BEN_NUMERAV");
				resultSetMap.put(p++, "BEN_IDDOMINIO");
				resultSetMap.put(p++, "BEN_IMPORTO");
				resultSetMap.put(p++, "BEN_IBANBANC");
				resultSetMap.put(p++, "BEN_IBANPOST");
				resultSetMap.put(p++, "BEN_TIPOSERV");
				rsm.put(1, resultSetMap);
				return rsm;						
			}).get();
		
	}

}
