package com.seda.payer.core.dao;

import java.lang.reflect.Constructor;
import java.sql.Connection;

import javax.sql.DataSource;

import com.seda.payer.core.exception.DaoException;

public class EasyBridgeDAOFactory {

	private static String EASYBRIDGE_CLASS;

	static {
		EASYBRIDGE_CLASS=ConfigurazioneEasyBridgeDaoImpl.class.getName();
	}
	
	
	//inizio LP PG21XX04 Leak
	@Deprecated
	//fine LP PG21XX04 Leak
	@SuppressWarnings("unchecked")
	public static ConfigurazioneEasyBridgeDao getEasyBridge(DataSource datasource, String schema) throws DaoException {
		ConfigurazioneEasyBridgeDao configurazioneEasyBridgeDao= null;
		try {
			Class<ConfigurazioneEasyBridgeDao> clazz = (Class<ConfigurazioneEasyBridgeDao>)Class.forName(EASYBRIDGE_CLASS);
			Constructor<ConfigurazioneEasyBridgeDao> constructor = clazz.getConstructor(new Class[] {javax.sql.DataSource.class, java.lang.String.class});
			configurazioneEasyBridgeDao = constructor.newInstance(datasource,schema);
		} catch (Exception x) {
			throw new DaoException(x);
		}
		return configurazioneEasyBridgeDao;
	}

	//inizio LP PG21XX04 Leak
	@SuppressWarnings("unchecked")
	public static ConfigurazioneEasyBridgeDao getEasyBridge(Connection connection, String schema) throws DaoException {
		ConfigurazioneEasyBridgeDao configurazioneEasyBridgeDao= null;
		try {
			Class<ConfigurazioneEasyBridgeDao> clazz = (Class<ConfigurazioneEasyBridgeDao>)Class.forName(EASYBRIDGE_CLASS);
			Constructor<ConfigurazioneEasyBridgeDao> constructor = clazz.getConstructor(new Class[] {java.sql.Connection.class, java.lang.String.class});
			configurazioneEasyBridgeDao = constructor.newInstance(connection, schema);
		} catch (Exception x) {
			throw new DaoException(x);
		}
		return configurazioneEasyBridgeDao;
	}
	//fine LP PG21XX04 Leak
}
