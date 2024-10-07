package com.seda.payer.core.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import com.seda.data.dao.DAOHelper;
import com.seda.data.helper.HelperException;
import com.seda.payer.core.bean.Stats;
import com.seda.payer.core.exception.DaoException;
import com.seda.payer.core.handler.BaseDaoHandler;

public class StatsDaoImpl extends BaseDaoHandler implements StatsDao   {
	private static final long serialVersionUID = 1L;

	public StatsDaoImpl(DataSource dataSource, String schema) throws SQLException {
		super(dataSource.getConnection(), schema);
	}

	public StatsDaoImpl(Connection connection, String schema) throws SQLException {
		super(connection, schema);
	}

	@Override
	public Integer update(Stats stats) throws DaoException {
		CallableStatement callableStatement = null;
		Connection connection = null;
		int ret=0;
		try {
			connection = getConnection();
			//inizio LP 20240919 - PGNTCORE-24 
			//callableStatement = Helper.prepareCall(connection, getSchema(), Routines.PYSTSSP_UPD.routine());
            callableStatement = prepareCall(Routines.PYSTSSP_UPD.routine());
			//fine LP 20240919 - PGNTCORE-24 
			callableStatement.setLong(1, stats.getId());
			callableStatement.setLong(2, stats.getPagamentoNbollettino());
			callableStatement.setLong(3, stats.getPagamentoNavviso());
			callableStatement.execute();
			ret=1;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DaoException(e);
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
			throw new DaoException(e);
		} catch (HelperException e) {
			e.printStackTrace();
			throw new DaoException(e);
		} finally {
			DAOHelper.closeIgnoringException(callableStatement); //LP 20240919 - PGNTCORE-24
			DAOHelper.closeIgnoringException(connection);
		}
		return ret;
	}
}