package com.seda.payer.core.dao;

import java.lang.reflect.Constructor;
import java.sql.Connection;

import javax.sql.DataSource;

import com.seda.payer.core.exception.DaoException;

public class UfficiDAOFactory {

	private static String UFFICI_CLASS;

	static {
		UFFICI_CLASS=UfficiDaoImpl.class.getName();
	}
	  
	//inizio LP PG21XX04 Leak
	@Deprecated
	//fine LP PG21XX04 Leak
	@SuppressWarnings("unchecked")
	public static UfficiDao getUffici(DataSource datasource, String schema) throws DaoException {
		UfficiDao ufficiDao= null;
		try {
			Class<UfficiDao> clazz = (Class<UfficiDao>)Class.forName(UFFICI_CLASS);
			Constructor<UfficiDao> constructor = clazz.getConstructor(new Class[] {javax.sql.DataSource.class, java.lang.String.class});
			ufficiDao = constructor.newInstance(datasource,schema);
		} catch (Exception x) {
			throw new DaoException(x);
		}
		return ufficiDao;
	}

	//inizio LP PG21XX04 Leak
	@SuppressWarnings("unchecked")
	public static UfficiDao getUffici(Connection connection, String schema) throws DaoException {
		UfficiDao ufficiDao= null;
		try {
			Class<UfficiDao> clazz = (Class<UfficiDao>)Class.forName(UFFICI_CLASS);
			Constructor<UfficiDao> constructor = clazz.getConstructor(new Class[] {java.sql.Connection.class, java.lang.String.class});
			ufficiDao = constructor.newInstance(connection, schema);
		} catch (Exception x) {
			throw new DaoException(x);
		}
		return ufficiDao;
	}
	//fine LP PG21XX04 Leak
}
