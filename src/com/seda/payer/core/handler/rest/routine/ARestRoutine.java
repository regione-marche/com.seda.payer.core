package com.seda.payer.core.handler.rest.routine;

import java.util.Map;

public abstract class ARestRoutine implements IRestRoutine {

	protected final String ROUTINE;
	protected final Map<Integer, String> IN_PARAMETER_MAP;
	protected final Map<Integer, String> OUT_PARAMETER_MAP;
	protected final Map<Integer, Map<Integer, String>> RESULT_SETS_MAP;
	
	public ARestRoutine() {
		
		ROUTINE = routine();
		IN_PARAMETER_MAP = inParameterMap();
		OUT_PARAMETER_MAP = outParameterMap();
		RESULT_SETS_MAP = resultSetsMap();
	}
	
	protected abstract String routine();
	protected abstract Map<Integer, String> inParameterMap();
	protected abstract Map<Integer, String> outParameterMap();
	protected abstract Map<Integer, Map<Integer, String>> resultSetsMap();

	@Override
	public final String getRoutine() {
		
		return ROUTINE;
	}

	@Override
	public final Map<Integer, String> getInParameterMap() {
		
		return IN_PARAMETER_MAP;
	}

	@Override
	public final Map<Integer, String> getOutParameterMap() {
		
		return OUT_PARAMETER_MAP;
	}

	@Override
	public final Map<Integer, Map<Integer, String>> getResultSetsMap() {
		
		return RESULT_SETS_MAP;
	}

}
