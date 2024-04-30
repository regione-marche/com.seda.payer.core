package com.seda.payer.core.handler.rest.routine;

import java.util.Map;

public interface IRestRoutine {

	String getRoutine();
	Map<Integer, String> getInParameterMap();
	Map<Integer, String> getOutParameterMap();
	Map<Integer, Map<Integer, String>> getResultSetsMap();
	
}
