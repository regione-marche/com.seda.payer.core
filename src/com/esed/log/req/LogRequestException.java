package com.esed.log.req;

import com.seda.commons.logger.CustomLoggerManager;
import com.seda.commons.logger.LoggerWrapper;

public class LogRequestException extends Exception {
	 
	private static final LoggerWrapper logger =  CustomLoggerManager.get(LogRequestException.class);

	//private static final long serialVersionUID = 5532002375566753262L;
	private static final long serialVersionUID = 1L;

	public LogRequestException(String messaggiErrore) {
		super(messaggiErrore);
		logger.info(messaggiErrore);
		
	}

}
