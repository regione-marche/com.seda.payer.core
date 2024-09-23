package com.seda.payer.core.dao;

import java.lang.reflect.Constructor;
import java.sql.Connection;

import com.seda.payer.core.exception.DaoException;

public class LogSepaDAOFactory {

	private static String SEPALOG_CLASS;

	static {
		SEPALOG_CLASS=LogSepaDAOImpl.class.getName();
	}
	@SuppressWarnings("unchecked")
	public static LogSepaDao getLogSepaDAO(Connection connection, String schema) throws DaoException {
		LogSepaDao logSepaDao = null;
		try {
			Class<LogSepaDao> clazz = (Class<LogSepaDao>)Class.forName(SEPALOG_CLASS);
			Constructor<LogSepaDao> constructor = clazz.getConstructor(new Class[] {java.sql.Connection.class, java.lang.String.class});
			logSepaDao = constructor.newInstance(connection,schema);
		} catch (Exception x) {
			throw new DaoException(x);
		} 
		return logSepaDao;
	}

}
