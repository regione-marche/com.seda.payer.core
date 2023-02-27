package com.seda.payer.core.riconciliazionemt.dao;

import java.lang.reflect.Constructor;
import java.sql.Connection;

import javax.sql.DataSource;

import com.seda.payer.core.exception.DaoException;

public class GiornaleDiCassaDAOFactory {
	private static String GDC_CLASS;

	static {
		GDC_CLASS=GiornaleDiCassaDAOImpl.class.getName();
	}
	//inizio LP PG21XX04 Leak
	@Deprecated
	//fine LP PG21XX04 Leak
	@SuppressWarnings("unchecked")
	public static GiornaleDiCassaDAO getGiornaleDiCassaDAO(DataSource datasource, String schema) throws DaoException {
		GiornaleDiCassaDAO gdcDAO = null;
		try {
			Class<GiornaleDiCassaDAO> clazz = (Class<GiornaleDiCassaDAO>)Class.forName(GDC_CLASS);
			Constructor<GiornaleDiCassaDAO> constructor = clazz.getConstructor(new Class[] {javax.sql.DataSource.class, java.lang.String.class});
			gdcDAO = constructor.newInstance(datasource,schema);
		} catch (Exception x) {
			throw new DaoException(x);
		} 
		return gdcDAO;
	}

	//inizio LP PG21XX04 Leak
	@SuppressWarnings("unchecked")
	public static GiornaleDiCassaDAO getGiornaleDiCassaDAO(Connection connection, String schema) throws DaoException {
		GiornaleDiCassaDAO gdcDAO = null;
		try {
			Class<GiornaleDiCassaDAO> clazz = (Class<GiornaleDiCassaDAO>)Class.forName(GDC_CLASS);
			Constructor<GiornaleDiCassaDAO> constructor = clazz.getConstructor(new Class[] {java.sql.Connection.class, java.lang.String.class});
			gdcDAO = constructor.newInstance(connection,schema);
		} catch (Exception x) {
			throw new DaoException(x);
		} 
		return gdcDAO;
	}
	//fine LP PG21XX04 Leak
}
