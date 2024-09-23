package com.seda.payer.core.dao;

import java.lang.reflect.Constructor;
import java.sql.Connection;

import com.seda.payer.core.exception.DaoException;

public class ScadenzaUrlNotificaDAOFactory {

	private static String SCADENZAURLNOTIFICA_CLASS;

	static {
		SCADENZAURLNOTIFICA_CLASS = ScadenzaUrlNotificaDaoImpl.class.getName();
	}
	
	@SuppressWarnings("unchecked")
	public static ScadenzaUrlNotificaDao getScadenzaUrlNotifica(Connection connection, String schema) throws DaoException {
		ScadenzaUrlNotificaDao scadenzaUrlNotificaDao = null;
		try {
			Class<ScadenzaUrlNotificaDao> clazz = (Class<ScadenzaUrlNotificaDao>)Class.forName(SCADENZAURLNOTIFICA_CLASS);
			Constructor<ScadenzaUrlNotificaDao> constructor = clazz.getConstructor(new Class[] {java.sql.Connection.class, java.lang.String.class});
			scadenzaUrlNotificaDao = constructor.newInstance(connection, schema);
		} catch (Exception x) {
			throw new DaoException(x);
		}
		return scadenzaUrlNotificaDao;
	}
	
	
}
