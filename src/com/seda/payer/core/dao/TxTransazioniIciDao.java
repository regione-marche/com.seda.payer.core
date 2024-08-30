package com.seda.payer.core.dao;

import java.math.BigDecimal;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

import com.seda.data.dao.DAOHelper;
import com.seda.data.helper.HelperException;
import com.seda.data.spi.PageInfo;
import com.seda.payer.core.bean.TransazioneIci;
import com.seda.payer.core.exception.DaoException;
import com.seda.payer.core.handler.BaseDaoHandler;


public class TxTransazioniIciDao   extends BaseDaoHandler {

	private PageInfo pageInfo = null;

	public TxTransazioniIciDao(Connection connection, String schema) {
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
	
	
	public void insertTransazioneICI(TransazioneIci transazione) throws DaoException, SQLException {
		CallableStatement callableStatement = null;
		try
		{
			callableStatement = prepareCall(Routines.TIC_DOINSERT.routine(), false);
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
	

	public TransazioneIci getTransazioneICIFromKey(String sChiaveTransazione) throws DaoException
	{
		TransazioneIci transazioneICIBean= null;
		CallableStatement callableStatement = null;
		//inizio LP PG21XX04 Leak
		ResultSet data = null;
		//fine LP PG21XX04 Leak
		try {
		    callableStatement = prepareCall(Routines.TIC_LISTA.routine());
			callableStatement.setString(1, sChiaveTransazione);
			
			if(callableStatement.execute())
			{
				//inizio LP PG21XX04 Leak
				//ResultSet data = callableStatement.getResultSet();
				data = callableStatement.getResultSet();
				//fine LP PG21XX04 Leak
				if (data.next())
				{
					transazioneICIBean = TransazioneIci.getBean(data);
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
		return transazioneICIBean;
	}
	
	
//PG170300 - 30/1/2018 - INIZIO - Aggiunte informazioni relative la marca da bollo
	public void update_IUR(String codiceTransazione,int progBollettino,String codiceIUR,Long progQuadratura) throws DaoException, SQLException {
		CallableStatement callableStatement = null;
		try
		{
			callableStatement = prepareCall(Routines.PYTICSP_UPD_IUR.routine());
			callableStatement.setString(1, codiceTransazione);
			callableStatement.setInt(2, progBollettino);
			callableStatement.setString(3, codiceIUR);
			callableStatement.setLong(4, progQuadratura);
			callableStatement.registerOutParameter(5, Types.INTEGER);

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
//PG170300 - 30/1/2018 - FINE

	
	
}