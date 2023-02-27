package com.seda.payer.core.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;


import com.seda.data.dao.DAOHelper;
import com.seda.data.helper.HelperException;
import com.seda.data.spi.PageInfo;
import com.seda.payer.core.bean.TransazioneIV;
import com.seda.payer.core.exception.DaoException;
import com.seda.payer.core.handler.BaseDaoHandler;


public class TxTransazioniIVDao   extends BaseDaoHandler {

	private PageInfo pageInfo = null;

	public TxTransazioniIVDao(Connection connection, String schema) {
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
	
	public void insertTransazioneIV(TransazioneIV transazione) throws DaoException, SQLException {
		CallableStatement callableStatement = null;
		try
		{
			callableStatement = prepareCall(Routines.TDT_DOINSERT.routine());
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
	
	//PG130130 GG - inizio
	public void updateTransazioneIV(TransazioneIV transazione) throws DaoException, SQLException {
		//boolean bResultVal = false;
		CallableStatement callableStatement = null;
		try
		{
			callableStatement = prepareCall(Routines.PYTDTSP_UPD.routine());
			callableStatement.setNull(1, Types.VARCHAR);
			callableStatement.setString(2, transazione.getChiaveTransazioneGenerale());
			callableStatement.setNull(3, Types.CHAR);
			callableStatement.setNull(4, Types.CHAR);
			callableStatement.setNull(5, Types.CHAR);
			callableStatement.setNull(6, Types.CHAR);
			//callableStatement.setString(7, transazione.getCodiceTipologiaServizio());
			callableStatement.setNull(7, Types.CHAR);
			//callableStatement.setString(8, transazione.getCodiceImpostaServizio());
			callableStatement.setNull(8, Types.CHAR);
			//callableStatement.setString(9, transazione.getCodiceServizio());
			callableStatement.setNull(9, Types.CHAR);
			//callableStatement.setString(10, transazione.getTipoBollettino());
			callableStatement.setNull(10, Types.CHAR);
			//callableStatement.setString(11, transazione.getNumeroContoCorrente());	//TODO da verificare se può essere modificato in aggiorna estremi transazione
			callableStatement.setNull(11, Types.VARCHAR);	
			callableStatement.setNull(12, Types.VARCHAR);
			callableStatement.setNull(13, Types.VARCHAR);
			callableStatement.setNull(14, Types.CHAR);
			callableStatement.setNull(15, Types.VARCHAR);
			callableStatement.setNull(16, Types.CHAR);
			//callableStatement.setNull(17, Types.INTEGER);
			callableStatement.setInt(17, transazione.getProgressivoRata());
			callableStatement.setNull(18, Types.TIMESTAMP);
			callableStatement.setNull(19, Types.VARCHAR);
			if (transazione.getDenominazione() != null) 
				callableStatement.setString(20, transazione.getDenominazione());
			else 
				callableStatement.setNull(20, Types.VARCHAR);
			callableStatement.setNull(21, Types.VARCHAR);
			callableStatement.setNull(22, Types.CHAR);
			callableStatement.setNull(23, Types.VARCHAR);
			callableStatement.setNull(24, Types.CHAR);
			callableStatement.setNull(25, Types.CHAR);
			if (transazione.getDataSanzione() != null)
				callableStatement.setTimestamp(26, new java.sql.Timestamp(transazione.getDataSanzione().getTime()));
			else 
				callableStatement.setNull(26, Types.TIMESTAMP);
			if (transazione.getTarga() != null)
				callableStatement.setString(27, transazione.getTarga());
			else
				callableStatement.setNull(27, Types.VARCHAR);
			callableStatement.setNull(28, Types.VARCHAR);
			if (transazione.getImportoTotaleBollettino() != null) 
				callableStatement.setBigDecimal(29, transazione.getImportoTotaleBollettino());
			else
				callableStatement.setNull(29, Types.DECIMAL);
			callableStatement.setNull(30, Types.DECIMAL);
			callableStatement.setNull(31, Types.DECIMAL);
			callableStatement.setNull(32, Types.DECIMAL);
			callableStatement.setNull(33, Types.DECIMAL);
			callableStatement.setNull(34, Types.DECIMAL);
			callableStatement.setNull(35, Types.DECIMAL);
			callableStatement.setNull(36, Types.DECIMAL);
			callableStatement.setNull(37, Types.DECIMAL);
			callableStatement.setNull(38, Types.CHAR);
			callableStatement.setNull(39, Types.CHAR);
			callableStatement.setNull(40, Types.CHAR);
			callableStatement.setNull(41, Types.CHAR);
			callableStatement.setNull(42, Types.CHAR);
			callableStatement.setNull(43, Types.VARCHAR);
			callableStatement.setNull(44, Types.CHAR);
			callableStatement.setNull(45, Types.BIGINT);
			callableStatement.setNull(46, Types.CHAR);
			callableStatement.setString(47, transazione.getOpertoreUltimoAggiornamento());
			callableStatement.setNull(48, Types.CHAR);
			callableStatement.setNull(49, Types.VARCHAR);	//PG170070 GG
//PG170300 - 30/1/2018 - INIZIO - Aggiunte informazioni relative la marca da bollo
			if (transazione.getCodiceIUR() != null) {
				callableStatement.setString(50, transazione.getCodiceIUR());
			} else {
				callableStatement.setNull(50,Types.CHAR);
			}
//			callableStatement.registerOutParameter(50, Types.INTEGER);
			callableStatement.registerOutParameter(51, Types.INTEGER);
//PG170300 - 30/1/2018 - FINE
			
			callableStatement.execute();
			/*
			int numrighe =  callableStatement.getInt(48);
			if (numrighe == 1)
				bResultVal = true;
			else bResultVal = false;
			*/	
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
		//return bResultVal;	//TODO da verificare se serve restituzione del risultato delle righe aggiornate
	}
	//PG130130 GG - fine
	//PG170300 - 30/1/2018 - INIZIO - Aggiunte informazioni relative la marca da bollo
	public void update_IUR(String codiceTransazione,int progBollettino,String codiceIUR,Long progQuadratura) throws DaoException, SQLException {
		CallableStatement callableStatement = null;
		try
		{

			System.out.println("marini = " + Routines.PYTDTSP_UPD_IUR);
			System.out.println("codiceTransazione:" + codiceTransazione);
			System.out.println("progBoll:" + progBollettino);
			System.out.println("codiceIUR:" + codiceIUR);
			System.out.println("progQuadratura:" + progQuadratura);
			callableStatement = prepareCall(Routines.PYTDTSP_UPD_IUR.routine());
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
