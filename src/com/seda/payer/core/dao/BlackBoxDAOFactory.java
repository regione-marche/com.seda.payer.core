package com.seda.payer.core.dao;

import java.lang.reflect.Constructor;
import java.sql.Connection;

import javax.sql.DataSource;

import com.seda.payer.core.exception.DaoException;

public class BlackBoxDAOFactory {

	private static String BLACKBOX_CLASS;

	static {
		BLACKBOX_CLASS=ConfigurazioneBlackBoxDaoImpl.class.getName();
	}
	
	//inizio LP PG21XX04 Leak
	@Deprecated
	//fine LP PG21XX04 Leak
	@SuppressWarnings("unchecked")
	public static ConfigurazioneBlackBoxDao getBlackBox(DataSource datasource, String schema) throws DaoException {
		ConfigurazioneBlackBoxDao configurazioneBlackBoxDao= null;
		try {
			Class<ConfigurazioneBlackBoxDao> clazz = (Class<ConfigurazioneBlackBoxDao>)Class.forName(BLACKBOX_CLASS);
			Constructor<ConfigurazioneBlackBoxDao> constructor = clazz.getConstructor(new Class[] {javax.sql.DataSource.class, java.lang.String.class});
			configurazioneBlackBoxDao = constructor.newInstance(datasource,schema);
		} catch (Exception x) {
			throw new DaoException(x);
		}
		return configurazioneBlackBoxDao;
	}

	//inizio LP PG21XX04 Leak
	@SuppressWarnings("unchecked")
	public static ConfigurazioneBlackBoxDao getBlackBox(Connection connection, String schema) throws DaoException {
		ConfigurazioneBlackBoxDao configurazioneBlackBoxDao= null;
		try {
			Class<ConfigurazioneBlackBoxDao> clazz = (Class<ConfigurazioneBlackBoxDao>)Class.forName(BLACKBOX_CLASS);
			Constructor<ConfigurazioneBlackBoxDao> constructor = clazz.getConstructor(new Class[] {java.sql.Connection.class, java.lang.String.class});
			configurazioneBlackBoxDao = constructor.newInstance(connection, schema);
		} catch (Exception x) {
			throw new DaoException(x);
		}
		return configurazioneBlackBoxDao;
	}
	//fine LP PG21XX04 Leak
}