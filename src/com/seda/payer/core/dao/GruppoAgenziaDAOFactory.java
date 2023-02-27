package com.seda.payer.core.dao;

import java.lang.reflect.Constructor;
import java.sql.Connection;

import javax.sql.DataSource;

import com.seda.payer.core.exception.DaoException;

public class GruppoAgenziaDAOFactory {

	private static String GRUPPOAGENZIA_CLASS;

	static {
		GRUPPOAGENZIA_CLASS=GruppoAgenziaDaoImpl.class.getName();
	}
	
	//inizio LP PG21XX04 Leak
	@Deprecated
	//fine LP PG21XX04 Leak
	@SuppressWarnings("unchecked")
	public static GruppoAgenziaDao getGruppoAgenzia(DataSource datasource, String schema) throws DaoException {
		GruppoAgenziaDao gruppoAgenziaDao= null;
		try {
			Class<GruppoAgenziaDao> clazz = (Class<GruppoAgenziaDao>)Class.forName(GRUPPOAGENZIA_CLASS);
			Constructor<GruppoAgenziaDao> constructor = clazz.getConstructor(new Class[] {javax.sql.DataSource.class, java.lang.String.class});
			gruppoAgenziaDao = constructor.newInstance(datasource,schema);
		} catch (Exception x) {
			throw new DaoException(x);
		}
		return gruppoAgenziaDao;
	}

	//inizio LP PG21XX04 Leak
	@SuppressWarnings("unchecked")
	public static GruppoAgenziaDao getGruppoAgenzia(Connection connection, String schema) throws DaoException {
		GruppoAgenziaDao gruppoAgenziaDao= null;
		try {
			Class<GruppoAgenziaDao> clazz = (Class<GruppoAgenziaDao>)Class.forName(GRUPPOAGENZIA_CLASS);
			Constructor<GruppoAgenziaDao> constructor = clazz.getConstructor(new Class[] {java.sql.Connection.class, java.lang.String.class});
			gruppoAgenziaDao = constructor.newInstance(connection, schema);
		} catch (Exception x) {
			throw new DaoException(x);
		}
		return gruppoAgenziaDao;
	}
	//fine LP PG21XX04 Leak
}