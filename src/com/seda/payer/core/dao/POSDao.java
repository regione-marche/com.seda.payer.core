package com.seda.payer.core.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;

import com.seda.data.dao.DAOHelper;
import com.seda.data.helper.HelperException;
import com.seda.payer.core.bean.POS;
import com.seda.payer.core.exception.DaoException;
import com.seda.payer.core.handler.BaseDaoHandler;
import com.seda.payer.core.messages.Messages;

public class POSDao extends BaseDaoHandler {

	public POSDao(Connection connection, String schema) {
		super(connection, schema);
	}

	public void insertTransazionePOS(POS pos) throws DaoException {
		CallableStatement callableStatement = null;
		try	{
			if (pos.getChiaveTransazione() == null || pos.getChiaveTransazione().length() == 0) 
				throw new IllegalArgumentException(Messages.INVALID_PARAMETER.format("POS.chiaveTransazione"));
			
			callableStatement = prepareCall(Routines.POS_DOINSERT.routine());
			callableStatement.setString(1, pos.getChiaveTransazione());
			callableStatement.setString(2, pos.getTerminalID());
			callableStatement.setString(3, pos.getAcquirerID());
			callableStatement.setString(4, pos.getAuthorizationCode());
			callableStatement.setString(5, pos.getOperationNumber());
			callableStatement.setString(6, pos.getInformazioniAll());
			callableStatement.setString(7, pos.getOperatoreAggiornamento());
				
			callableStatement.execute();
			
		} catch (SQLException x) {
			throw new DaoException(x);
		} catch (IllegalArgumentException x) {
			throw new DaoException(x);
		} catch (HelperException x) {
			throw new DaoException(x);
		}
		finally {
			//inizio LP PG21XX04 Leak
			//if (callableStatement != null)
			//	DAOHelper.closeIgnoringException(callableStatement);
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