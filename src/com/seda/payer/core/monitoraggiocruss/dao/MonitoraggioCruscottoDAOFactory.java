package com.seda.payer.core.monitoraggiocruss.dao;

import java.lang.reflect.Constructor;
import java.sql.Connection;

import javax.sql.DataSource;
import com.seda.payer.core.exception.DaoException;

public class MonitoraggioCruscottoDAOFactory {
	private static String MCRUSS_CLASS;
	
	static {
		MCRUSS_CLASS=MonitoraggioCruscottoDAOImpl.class.getName();
	}
	
	//inizio LP PG21XX04 Leak
	@Deprecated
	//fine LP PG21XX04 Leak
	@SuppressWarnings("unchecked")
	public static MonitoraggioCruscottoDAO getMonitoraggioCruscottoDAO(DataSource datasource, String schema) throws DaoException {
		MonitoraggioCruscottoDAO monitoraggioCruscottoDAO = null;
		try {
			Class<MonitoraggioCruscottoDAO> clazz = (Class<MonitoraggioCruscottoDAO>)Class.forName(MCRUSS_CLASS);
			Constructor<MonitoraggioCruscottoDAO> constructor = clazz.getConstructor(new Class[] {javax.sql.DataSource.class, java.lang.String.class});
			monitoraggioCruscottoDAO = constructor.newInstance(datasource,schema);
		} catch (Exception x) {
			System.out.println("errore getMonitoraggioCruscottoDAO = " + x.getMessage());
			throw new DaoException(x);
		} 
		return monitoraggioCruscottoDAO;
	}

	//inizio LP PG21XX04 Leak
	@SuppressWarnings("unchecked")
	public static MonitoraggioCruscottoDAO getMonitoraggioCruscottoDAO(Connection connection, String schema) throws DaoException {
		MonitoraggioCruscottoDAO monitoraggioCruscottoDAO = null;
		try {
			Class<MonitoraggioCruscottoDAO> clazz = (Class<MonitoraggioCruscottoDAO>)Class.forName(MCRUSS_CLASS);
			Constructor<MonitoraggioCruscottoDAO> constructor = clazz.getConstructor(new Class[] {java.sql.Connection.class, java.lang.String.class});
			monitoraggioCruscottoDAO = constructor.newInstance(connection, schema);
		} catch (Exception x) {
			System.out.println("errore getMonitoraggioCruscottoDAO = " + x.getMessage());
			throw new DaoException(x);
		} 
		return monitoraggioCruscottoDAO;
	}
	//fine LP PG21XX04 Leak
}
