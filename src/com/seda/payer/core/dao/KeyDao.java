package com.seda.payer.core.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.seda.data.helper.HelperException;
import com.seda.payer.core.exception.DaoException;
import com.seda.payer.core.handler.BaseDaoHandler;
import java.sql.Types;


public class KeyDao extends BaseDaoHandler {

	public KeyDao(Connection connection, String schema) {
		super(connection, schema);
	}

	public String getKeyBigint(String chiave) throws DaoException {
		CallableStatement callableStatement = null;
		ResultSet data = null;

		try	{
			callableStatement = prepareCall(Routines.PYKEYSP_BIGINT.routine());
			callableStatement.setString(1, chiave);
			callableStatement.registerOutParameter(2, Types.BIGINT);
			
			callableStatement.execute(); 
			
			Long key=callableStatement.getLong(2);
			
			return String.valueOf(key);
			
		} catch (SQLException x) {
			throw new DaoException(x);
		} catch (IllegalArgumentException x) {
			throw new DaoException(x);
		} catch (HelperException x) {
			throw new DaoException(x);
		} finally {
			//inizio LP PG21XX04 Leak
			//if (callableStatement != null)
			//	DAOHelper.closeIgnoringException(callableStatement);
			//if (data != null)
			//	DAOHelper.closeIgnoringException(data);
			if (data != null) {
				try {
					data.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (callableStatement != null) {
				try {
					callableStatement.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			//fine LP PG21XX04 Leak
		}
	}
	
}
