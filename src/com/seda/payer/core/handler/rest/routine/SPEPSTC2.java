package com.seda.payer.core.handler.rest.routine;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class SPEPSTC2 extends ARestRoutine {

	@Override
	protected String routine() {
		
		return "SPEPSTC2";
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
		inParameterMap.put(p++, "data_in_documen_in");
		inParameterMap.put(p++, "data_in_pcoocoob_in");
		inParameterMap.put(p++, "data_ou_tipserv_in");
		inParameterMap.put(p++, "data_ou_flagpoab_in");
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
		outParameterMap.put(p++, "data_in_documen_out");
		outParameterMap.put(p++, "data_in_pcoocoob_out");
		outParameterMap.put(p++, "data_ou_tipserv_out");
		outParameterMap.put(p++, "data_ou_flagpoab_out");
		outParameterMap.put(p++, "data_ou_retcode_out");
		outParameterMap.put(p++, "data_ou_message_out.filler1_out");
		
		return outParameterMap;
	}

	@Override
	protected Map<Integer, Map<Integer, String>> resultSetsMap() {
		
		return Optional.of(new HashMap<Integer, Map<Integer,String>>())
			.map(rsm -> {
				int p = 1;
				Map<Integer,String> resultSetMap = new HashMap<Integer, String>();

				resultSetMap.put(p++, "STC2_CUTECUTE");
				resultSetMap.put(p++, "STC2_CODIENTE");
				resultSetMap.put(p++, "STC2_CODIFISC");
				resultSetMap.put(p++, "STC2_TIPOSERV");
				resultSetMap.put(p++, "STC2_TIPORICH");
				resultSetMap.put(p++, "STC2_IDDOCUME");
				resultSetMap.put(p++, "STC2_PROGRATA");
				resultSetMap.put(p++, "STC2_DATASCAD");
				resultSetMap.put(p++, "STC2_NUMERAVV");
				resultSetMap.put(p++, "STC2_IRATINIZ");
				resultSetMap.put(p++, "STC2_IRATRESI");
				resultSetMap.put(p++, "STC2_INOTRESI");
				resultSetMap.put(p++, "STC2_ICMPRESI");
				resultSetMap.put(p++, "STC2_IMORRESI");
				resultSetMap.put(p++, "STC2_ISPERESI");
				resultSetMap.put(p++, "STC2_ITOTRESI");
				resultSetMap.put(p++, "STC2_TIPORATA");
				resultSetMap.put(p++, "STC2_FLAGEPGF");
				resultSetMap.put(p++, "STC2_FLAGSANZ");
				resultSetMap.put(p++, "STC2_BOLLFREC");
				resultSetMap.put(p++, "STC2_CTIPODOC");
				resultSetMap.put(p++, "STC2_TIPODATO");
				resultSetMap.put(p++, "STC2_PROGOPZP");
				resultSetMap.put(p++, "STC2_DESCOPZP");
				resultSetMap.put(p++, "STC2_MAXPROPZ");
				resultSetMap.put(p++, "STC2_NUMERATE");
				resultSetMap.put(p++, "STC2_FRATUNIC");
				resultSetMap.put(p++, "STC2_DSTATRAT");
				resultSetMap.put(p++, "STC2_FRATTARD");
				resultSetMap.put(p++, "STC2_FRATPAGA");
				resultSetMap.put(p++, "STC2_IRATPAGA");
				resultSetMap.put(p++, "STC2_ITOTCARI");
				resultSetMap.put(p++, "STC2_IDDOCMUL");
		
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
