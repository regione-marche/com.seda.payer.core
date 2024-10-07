package com.seda.payer.core.dao;

import java.lang.reflect.Constructor;
import java.sql.Connection;
import javax.sql.DataSource;
import com.seda.payer.core.exception.DaoException;

public class AnagraficaBollettinoDAOFactory {
	private static String ANABOLL_CLASS;

	static {
		ANABOLL_CLASS=AnagraficaBollettinoDAOImpl.class.getName();
	}
	//inizio LP PG21XX04 Leak
	@Deprecated
	//fine LP PG21XX04 Leak
	@SuppressWarnings("unchecked")
	public static AnagraficaBollettinoDAO getAnagraficaBollettinoDAO(DataSource datasource, String schema) throws DaoException {
		AnagraficaBollettinoDAO anagraficaBollettinoDAO = null;
		try {
			Class<AnagraficaBollettinoDAO> clazz = (Class<AnagraficaBollettinoDAO>)Class.forName(ANABOLL_CLASS);
			Constructor<AnagraficaBollettinoDAO> constructor = clazz.getConstructor(new Class[] {javax.sql.DataSource.class, java.lang.String.class});
			anagraficaBollettinoDAO = constructor.newInstance(datasource,schema);
		} catch (Exception x) {
			throw new DaoException(x);
		} 
		return anagraficaBollettinoDAO;
	}

	@SuppressWarnings("unchecked")
	public static AnagraficaBollettinoDAO getAnagraficaBollettinoDAO(Connection connection, String schema) throws DaoException {
		AnagraficaBollettinoDAO anagraficaBollettinoDAO = null;
		try {
			Class<AnagraficaBollettinoDAO> clazz = (Class<AnagraficaBollettinoDAO>) Class.forName(ANABOLL_CLASS);
			Constructor<AnagraficaBollettinoDAO> constructor = clazz.getConstructor(new Class[] {java.sql.Connection.class, java.lang.String.class});
			anagraficaBollettinoDAO = constructor.newInstance(connection, schema);
		} catch (Exception x) {
			throw new DaoException(x);
		} 
		return anagraficaBollettinoDAO;
	}
	//fine LP PG21XX04 Leak
}
