package com.seda.payer.core.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import com.seda.data.dao.DAOHelper;
import com.seda.data.helper.Helper;
import com.seda.data.helper.HelperException;
import com.seda.data.procedure.reflection.MetaProcedure;
import com.seda.data.procedure.reflection.ProcedureReflectorException;
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
		CallableStatement callableStatement=null;
		Connection connection = null;
		int ret=0;
		try {
			connection = getConnection();
			//inizio LP PGNTCORE-24 
			//callableStatement = Helper.prepareCall(connection, getSchema(), Routines.PYSTSSP_UPD.routine());
            callableStatement = MetaProcedure.prepareCall(connection, getSchema(), Routines.PYSTSSP_UPD.routine());
			//fine LP PGNTCORE-24 
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
		//inizio LP PGNTCORE-24 
		//} catch (HelperException e) {
		//	e.printStackTrace();
		//	throw new DaoException(e);
		//} 
		} catch (ProcedureReflectorException e) {
			e.printStackTrace();
			throw new DaoException(e);
		//fine LP PGNTCORE-24 
		} finally {
			DAOHelper.closeIgnoringException(connection);
		}
		return ret;
	}
}