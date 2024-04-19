package com.seda.payer.core.handler.rest;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import com.seda.data.helper.HelperException;
import com.seda.payer.core.handler.BaseDaoHandler;
import com.seda.payer.core.handler.rest.routine.ERestRoutine;

public abstract class RestBaseDaoHandler extends BaseDaoHandler {

	private final boolean isRest;
	private final String baseUrl;
	
	public RestBaseDaoHandler(Connection connection, String schema) {
		
		this(connection, schema, false, null);
	}
	
	public RestBaseDaoHandler(Connection connection, String schema, boolean isRest, String baseUrl) {
		
		super(connection, schema);
		
		this.isRest = isRest;
		this.baseUrl = baseUrl;
	}

	@Override
	protected CallableStatement prepareCall(String routine) throws IllegalArgumentException, SQLException, HelperException {
		
		ERestRoutine restRoutine = ERestRoutine.fromRoutineName(routine);
		
		if (isRest && restRoutine != null) {
			
			return new RestCallableStatement(baseUrl, getSchema(), restRoutine);
		}
		
		return prepareCall(routine, -1);
	}

	@Override
	protected CallableStatement prepareCall(String routine, String methodRest, String restService) throws IllegalArgumentException, SQLException, HelperException {

		ERestRoutine restRoutine = ERestRoutine.fromRoutineName(routine);

		if (isRest && restRoutine != null) {

			return new RestCallableStatement(baseUrl, getSchema(), restRoutine, methodRest, restService);
		}

		return prepareCall(routine, -1);
	}


	
}
