package com.seda.payer.core.sosta.dao;

import java.lang.reflect.Constructor;
import java.sql.Connection;

import javax.sql.DataSource;
import com.seda.payer.core.exception.DaoException;

public class SostaDAOFactory {

	private static String SOSTADAO_CLASS;

	static {
		SOSTADAO_CLASS=SostaDAOImpl.class.getName();
	}
	//inizio LP PG21XX04 Leak
	@Deprecated
	//fine LP PG21XX04 Leak
	@SuppressWarnings("unchecked")
	public static SostaDAO getSostaDAO(DataSource datasource, String schema) throws DaoException {
		SostaDAO sostaDAO = null;
		try {
			Class<SostaDAO> clazz = (Class<SostaDAO>)Class.forName(SOSTADAO_CLASS);
			Constructor<SostaDAO> constructor = clazz.getConstructor(new Class[] {javax.sql.DataSource.class, java.lang.String.class});
			sostaDAO = constructor.newInstance(datasource,schema);
		} catch (Exception x) {
			throw new DaoException(x);
		} 
		return sostaDAO;
	}

	//inizio LP PG21XX04 Leak
	@SuppressWarnings("unchecked")
	public static SostaDAO getSostaDAO(Connection connection, String schema) throws DaoException {
		SostaDAO sostaDAO = null;
		try {
			Class<SostaDAO> clazz = (Class<SostaDAO>)Class.forName(SOSTADAO_CLASS);
			Constructor<SostaDAO> constructor = clazz.getConstructor(new Class[] {java.sql.Connection.class, java.lang.String.class});
			sostaDAO = constructor.newInstance(connection,schema);
		} catch (Exception x) {
			throw new DaoException(x);
		} 
		return sostaDAO;
	}
	//fine LP PG21XX04 Leak
}
