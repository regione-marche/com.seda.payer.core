package com.seda.payer.core.handler.rest.routine;

import java.util.Map;

public enum ERestRoutine {
	
	EC_DOLIST(new SPEPSTC1()), //OK
	ECS_DOLIST(new SPEPSTC2()), //OK
	ECM_DOLIST(new SPEPSTC3()), //OK
	EC_DETBOL(new SPEPSTC4()), //OK
	// SPEPSTC5 non viene più usata
	EC_CODFISC(new SPEPSTC6()), //OK
	EC_ATTICONT_DOLIST(new SPEPSTC7()), //OK
	EC_PAG_DOLIST(new SPEPSTC8()), //OK
	BOL_DODETAIL(new SPEPBOLL()), // manca ResultSet 1 e 2
	CDS_DODETAIL(new SPEPWCDS()), //OK
	
	PGBHOST(new SPEPSPB1()), //OK
	SPEPIT01(new SPEPIT01()), //OK
	ALL_ANA_CONTATTO(new SPEPSTCE()), //OK
	SPEPSTE1(new SPEPSTE1()), //OK
	// SPEPIT02 non esiste su entrate quindi non dovremmo chiamarla da pagonet
	SPEPWCAR(null), //in attesa di specifiche
	SPEPWCAN(new SPEPWCAN()), //OK
	SPEPWSTA(new SPEPWSTA()), //OK
	IS_ANAG_DOSAVE(new SPISOGG1()), //OK
	IS_COMUNICAZIONE_DOSAVE(new SPISOGG2()), //OK
	
	IS_IMPSOGG_DOSAVE(new SPISOGG3()), //OK
	SELECT_PAG_HOST_ENTEIS(new SPISOGG4()), // TODO: manca esempio GET per il ResultSet
	IS_RID_DODETAIL(new SPISOGG5()), //OK
	DO_BOLLETTINO_CUMULATIVO(new SPISOGG6()), // TODO: solo DAO
	DO_DATI_BOLLETTINO(new SPISOGG7()), // TODO: solo DAO
	;
	
	private final IRestRoutine restRoutine;
	
	private ERestRoutine(IRestRoutine restRoutine) {
		
		this.restRoutine = restRoutine;
	}
	
	public String getRoutine() {
		
		return restRoutine.getRoutine();
	}
	
	public Map<Integer, String> getInParameterMap() {
		
		return restRoutine.getInParameterMap();
	}
	
	public Map<Integer, String> getOutParameterMap() {
		
		return restRoutine.getOutParameterMap();
	}
	
	public Map<Integer, Map<Integer, String>> getResultSetsMap() {
		
		return restRoutine.getResultSetsMap();
	}
	
	public static ERestRoutine fromRoutineName(String routine) {
		
		for (ERestRoutine restRoutine : ERestRoutine.values()) {
			
			if (restRoutine.getRoutine().equals(routine))
				return restRoutine;
		}
		
		return null;
	}
	
}
