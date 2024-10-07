package com.seda.payer.core.handler.rest;

import java.sql.SQLException;

public class RestSQLException extends SQLException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6740148359625506800L;

	public RestSQLException(String reason, Throwable cause) {
		super(reason, cause);
	}

	public RestSQLException(String reason) {
		super(reason);
	}

}
