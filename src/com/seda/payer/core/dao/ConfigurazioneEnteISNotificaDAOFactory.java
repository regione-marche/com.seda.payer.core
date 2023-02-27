package com.seda.payer.core.dao;

import java.lang.reflect.Constructor;
import java.sql.Connection;

import javax.sql.DataSource;

import com.seda.payer.core.exception.DaoException;

public class ConfigurazioneEnteISNotificaDAOFactory {

	private static String CONFIGURAZIONEENTEISNOTIFICA_CLASS;

	static {
		CONFIGURAZIONEENTEISNOTIFICA_CLASS = ConfigurazioneEnteISNotificaDaoImpl.class.getName();
	}
	
	//inizio LP PG21XX04 Leak
	@Deprecated
	//fine LP PG21XX04 Leak
	@SuppressWarnings("unchecked")
	public static ConfigurazioneEnteISNotificaDao getConfigurazioneEnteISNotifica(DataSource datasource, String schema) throws DaoException {
		ConfigurazioneEnteISNotificaDao configurazioneEnteISNotificaDao= null;
		try {
			Class<ConfigurazioneEnteISNotificaDao> clazz = (Class<ConfigurazioneEnteISNotificaDao>)Class.forName(CONFIGURAZIONEENTEISNOTIFICA_CLASS);
			Constructor<ConfigurazioneEnteISNotificaDao> constructor = clazz.getConstructor(new Class[] {javax.sql.DataSource.class, java.lang.String.class});
			configurazioneEnteISNotificaDao = constructor.newInstance(datasource,schema);
		} catch (Exception x) {
			throw new DaoException(x);
		}
		return configurazioneEnteISNotificaDao;
	}

	//inizio LP PG21XX04 Leak
	@SuppressWarnings("unchecked")
	public static ConfigurazioneEnteISNotificaDao getConfigurazioneEnteISNotifica(Connection connection, String schema) throws DaoException {
		ConfigurazioneEnteISNotificaDao configurazioneEnteISNotificaDao= null;
		try {
			Class<ConfigurazioneEnteISNotificaDao> clazz = (Class<ConfigurazioneEnteISNotificaDao>)Class.forName(CONFIGURAZIONEENTEISNOTIFICA_CLASS);
			Constructor<ConfigurazioneEnteISNotificaDao> constructor = clazz.getConstructor(new Class[] {java.sql.Connection.class, java.lang.String.class});
			configurazioneEnteISNotificaDao = constructor.newInstance(connection, schema);
		} catch (Exception x) {
			throw new DaoException(x);
		}
		return configurazioneEnteISNotificaDao;
	}
	//fine LP PG21XX04 Leak
}
