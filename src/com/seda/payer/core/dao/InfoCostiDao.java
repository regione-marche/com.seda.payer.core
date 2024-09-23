package com.seda.payer.core.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.seda.data.helper.HelperException;
import com.seda.payer.core.bean.InfoCosti;
import com.seda.payer.core.bean.InfoCostiTransazione;
import com.seda.payer.core.exception.DaoException;
import com.seda.payer.core.handler.BaseDaoHandler;

public class InfoCostiDao extends BaseDaoHandler {
	
	public InfoCostiDao(Connection connection, String schema) {
		super(connection, schema);
	}
	
	public InfoCosti doList(String codSocieta, String codUtente, String canalePagamento) throws DaoException {
		CallableStatement callableStatement = null;
		ResultSet data = null;
		ResultSet rsCosti = null;
		try	{
			callableStatement = prepareCall(Routines.CST_DOLIST.routine());
			callableStatement.setString(1, codSocieta);
			callableStatement.setString(2, codUtente);
			callableStatement.setString(3, canalePagamento);
			
			if (callableStatement.execute()) 
			{
				InfoCosti res = new InfoCosti();
				
				//resultset 1
				data = callableStatement.getResultSet();
				if (data.next())
				{
					res = new InfoCosti(data);
				}
				
				//resultset 2: costi transazione/banca (relativi al gateway -- posso avere più di un record per ogni gateway)
				if (callableStatement.getMoreResults())
				{
					rsCosti = callableStatement.getResultSet();
					InfoCostiTransazione costiTrans;
					while (rsCosti.next())
					{
						costiTrans = new InfoCostiTransazione(rsCosti);
						res.getListInfoCostiTrans().add(costiTrans);
					}
				}
				
				return res;
				
			}	
			return null;
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
			//if (rsCosti != null)
			//	DAOHelper.closeIgnoringException(rsCosti);
			if (rsCosti != null) {
				try {
					rsCosti.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
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
