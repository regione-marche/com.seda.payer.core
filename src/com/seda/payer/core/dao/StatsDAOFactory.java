package com.seda.payer.core.dao;

import java.lang.reflect.Constructor;

import javax.sql.DataSource;

import com.seda.payer.core.exception.DaoException;

public class StatsDAOFactory {

	private static String STATS_CLASS;

	static {
		STATS_CLASS=StatsDaoImpl.class.getName();
	}
	  
	@SuppressWarnings("unchecked")
	public static StatsDao getStats(DataSource datasource, String schema) throws DaoException {
		StatsDao statsDao= null;
		try {
			Class<ConfigurazioneBlackBoxDao> clazz = (Class<ConfigurazioneBlackBoxDao>)Class.forName(STATS_CLASS);
			Constructor<ConfigurazioneBlackBoxDao> constructor = clazz.getConstructor(new Class[] {javax.sql.DataSource.class, java.lang.String.class});
			statsDao = (StatsDao) constructor.newInstance(datasource,schema);
		} catch (Exception x) {
			throw new DaoException(x);
		}
		return statsDao;
	}
}
