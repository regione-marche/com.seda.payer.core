package com.seda.payer.core.dao;

import java.lang.reflect.Constructor;
import java.sql.Connection;

import javax.sql.DataSource;

import com.seda.payer.core.exception.DaoException;
import com.seda.payer.core.wallet.dao.ConfigurazioneSollecitiDAO;
import com.seda.payer.core.wallet.dao.ConfigurazioneSollecitiDAOImpl;

public class ConfigurazioneModello3DAOFactory {

	private static String CONFIGURAZIONEMODELLO3_CLASS;

	static {
		CONFIGURAZIONEMODELLO3_CLASS=ConfigurazioneModello3DaoImpl.class.getName();
	}

	//inizio LP PG21XX04 Leak
	@Deprecated
	//fine LP PG21XX04 Leak
	@SuppressWarnings("unchecked")
	public static ConfigurazioneModello3Dao getConfigurazioneModello3(DataSource datasource, String schema) throws DaoException {
		ConfigurazioneModello3Dao configurazioneModello3Dao= null;
		try {
			Class<ConfigurazioneModello3Dao> clazz = (Class<ConfigurazioneModello3Dao>)Class.forName(CONFIGURAZIONEMODELLO3_CLASS);
			Constructor<ConfigurazioneModello3Dao> constructor = clazz.getConstructor(new Class[] {javax.sql.DataSource.class, java.lang.String.class});
			configurazioneModello3Dao = constructor.newInstance(datasource,schema);
		} catch (Exception x) {
			throw new DaoException(x);
		}
		return configurazioneModello3Dao;
	}

	//inizio LP PG21XX04 Leak
	@SuppressWarnings("unchecked")
	public static ConfigurazioneModello3Dao getConfigurazioneModello3(Connection connection, String schema) throws DaoException {
		ConfigurazioneModello3Dao configurazioneModello3Dao= null;
		try {
			Class<ConfigurazioneModello3Dao> clazz = (Class<ConfigurazioneModello3Dao>)Class.forName(CONFIGURAZIONEMODELLO3_CLASS);
			Constructor<ConfigurazioneModello3Dao> constructor = clazz.getConstructor(new Class[] {java.sql.Connection.class, java.lang.String.class});
			configurazioneModello3Dao = constructor.newInstance(connection, schema);
		} catch (Exception x) {
			throw new DaoException(x);
		}
		return configurazioneModello3Dao;
	}
	//fine LP PG21XX04 Leak
}
