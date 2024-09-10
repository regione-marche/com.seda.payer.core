package com.seda.payer.core.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.seda.data.helper.HelperException;
import com.seda.data.spi.PageInfo;
import com.seda.payer.core.bean.TransazioneIV;
import com.seda.payer.core.bean.TransazioneIV_Dett;
import com.seda.payer.core.exception.DaoException;
import com.seda.payer.core.handler.BaseDaoHandler;


public class TxTransazioniIV_DettDao   extends BaseDaoHandler {

	private PageInfo pageInfo = null;

	public TxTransazioniIV_DettDao(Connection connection, String schema) {
		super(connection, schema);
	}
	
	public PageInfo getPageInfo() {
		return pageInfo;
	}
	//inizio LP PG21XX04 Leak
	//private void closeCallableStatement(CallableStatement callableStatement)
	//{
	//	if (callableStatement != null)
	//		DAOHelper.closeIgnoringException(callableStatement);
	//}
	//fine LP PG21XX04 Leak
	//inizio LP 20240909 - PGNTBOLDER-1
	public void insertTransazioneIV_Dett(TransazioneIV_Dett transazione) throws DaoException, SQLException {
		//
		// Nota:
		//		Qui rispetto allo "standard" il metodo con firma 
		//		"pre postgres" viene usato con bFlagUpdateAutocomit == false
		//
		insertTransazioneIV_DettTail(false, transazione);
	}

	public void insertTransazioneIV_DettTail(boolean bFlagUpdateAutocomit, TransazioneIV_Dett transazione) throws DaoException, SQLException {
	//fine LP 20240909 - PGNTBOLDER-1
		CallableStatement callableStatement = null;
		try {
			//inizio LP 20240909 - PGNTBOLDER-1
			callableStatement = prepareCall(bFlagUpdateAutocomit, Routines.DMD_DOINSERT.routine());
			//fine LP 20240909 - PGNTBOLDER-1
			transazione.save(callableStatement);
			callableStatement.execute();
		} catch (IllegalArgumentException x) {
			throw new DaoException(x);
		} catch (HelperException x) {
			throw new DaoException(x);
		} finally {
			//inizio LP PG21XX04 Leak
			//closeCallableStatement(callableStatement);
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
	
	public TransazioneIV getTransazioneIVFromKey(String sChiaveTransazione) throws DaoException
	{
		TransazioneIV transazioneIVBean= null;
		CallableStatement callableStatement = null;
		//inizio LP PG21XX04 Leak
		ResultSet data = null;
		//fine LP PG21XX04 Leak
		try {
		    callableStatement = prepareCall(Routines.TDT_LISTA.routine());
			callableStatement.setString(1, sChiaveTransazione);
			
			if(callableStatement.execute())
			{
				//inizio LP PG21XX04 Leak
				//ResultSet data = callableStatement.getResultSet();
				data = callableStatement.getResultSet();
				//fine LP PG21XX04 Leak
				if (data.next())
				{
					transazioneIVBean = TransazioneIV.getBean(data);
				}
			}
			

		} catch (IllegalArgumentException e) {
			throw new DaoException(e);
		} catch (SQLException e) {
			throw new DaoException(e);
		} catch (HelperException e) {
			throw new DaoException(e);
		} finally {
			//inizio LP PG21XX04 Leak
			//closeCallableStatement(callableStatement);
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
		return transazioneIVBean;
	}
	

}
