package com.seda.payer.core.dao;

import java.math.BigInteger;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.sql.Types;
import java.util.Calendar;

import com.seda.data.dao.DAOHelper;
import com.seda.data.helper.HelperException;
import com.seda.data.spi.PageInfo;
import com.seda.payer.core.handler.BaseDaoHandler;
import com.seda.payer.core.bean.InvalidateLog;
import com.seda.payer.core.bean.Log;
import com.seda.payer.core.bean.LogPage;
import com.seda.payer.core.bean.ResponseData;
import com.seda.payer.core.exception.DaoException;

/**
 * Questa classe viene utilizzata per l'alimentazione e l'interrogazione
 * delle tabelle di log (PYLOGTB e lo storico PYLOSTB) 
 * dell'applicazione "org.seda.payer.manager.web" 
 * che tengono traccia dell'attivita degli utenti.
 * 
 * @author f.vadicamo
 *
 */
public class ManagerLogDao  extends BaseDaoHandler {

	public ManagerLogDao(Connection connection, String schema) {
		super(connection, schema);
	}
	
	/**
	 * Questo metodo viene utilizzato dal filtro "LoggingManager" e dal listener "SessionManager"
	 * di "org.seda.payer.manager.web" per inserire nuovi record  
	 * e per fare l'update di quelli già esistenti nella tabella di log "PYLOGTB".
	 * 
	 * @param userToken - Il token utente nello UserBean
	 * @param sessionId
	 * @param userName
	 * @param userProfile
	 * @param codiceSocieta
	 * @param codiceUtente
	 * @param chiaveEnte
	 * @param canalePagamento
	 * @param indirizzoIP
	 * @param applicazione - La CURRENT_APPLICATION del MAF
	 * @param endSession - "Y" se si tratta di loggare la fine sessione, "" altrimenti
	 * @return
	 * @throws DaoException
	 */
	public boolean log
	(
			String userToken,
			String sessionId,
			String userName,
			String userProfile,
			String codiceSocieta,
			String codiceUtente,
			String chiaveEnte,
			String canalePagamento,
			String indirizzoIP,
			String applicazione,
			String endSession
	) throws DaoException
	{
		CallableStatement callableStatement = null;
		try {
			callableStatement = prepareCall(Routines.LOG.routine());
			callableStatement.setString(1, userToken);
			callableStatement.setString(2, sessionId);
			callableStatement.setString(3, userName);
			callableStatement.setString(4, userProfile);
			if(codiceSocieta == null) callableStatement.setNull(5 ,Types.VARCHAR);
				else callableStatement.setString(5, codiceSocieta);
			if(codiceUtente == null) callableStatement.setNull(6 ,Types.VARCHAR);
				else callableStatement.setString(6, codiceUtente);
			if(chiaveEnte == null) callableStatement.setNull(7 ,Types.VARCHAR);
				else callableStatement.setString(7, chiaveEnte);
			callableStatement.setString(8, canalePagamento);
			if(indirizzoIP == null) callableStatement.setNull(9 ,Types.VARCHAR);
				else callableStatement.setString(9, indirizzoIP);
			if(applicazione == null) callableStatement.setNull(10 ,Types.VARCHAR);
				else callableStatement.setString(10, applicazione);
			callableStatement.setString(11, endSession);
			callableStatement.registerOutParameter(12, Types.INTEGER);
			callableStatement.executeUpdate();
			return (callableStatement.getInt(12) == 1);
		} catch (IllegalArgumentException e) {
			throw new DaoException(e);
		} catch (SQLException e) {
			throw new DaoException(e);
		} catch (HelperException e) {
			throw new DaoException(e);
		}
		finally
		{
			//inizio LP PG21XX04 Leak
			//DAOHelper.closeIgnoringException(callableStatement);
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

	/**
	 * Questo metodo restituisce una istanza di LogPage
	 * 
	 * @param pageNumber
	 * @param rowsPerPage
	 * @param order
	 * @param userName
	 * @param indirizzoIp
	 * @param applicazione
	 * @param userProfile
	 * @param canale
	 * @param dataInizioSessioneDa
	 * @param dataInizioSessioneA
	 * @param codiceSocieta
	 * @param siglaProvincia
	 * @param codiceUtente
	 * @param chiaveEnte
	 * @return LogPage
	 * @throws DaoException
	 */
	public LogPage getlogPage
	(
		int pageNumber,
		int rowsPerPage,
		String order,
		String userName,
		String indirizzoIp,
		String applicazione,
		String userProfile,
		String canale,
		Calendar dataInizioSessioneDa,
		Calendar dataInizioSessioneA,
		String codiceSocieta,
		String siglaProvincia,
		String codiceUtente,
		String chiaveEnte
		
	) throws DaoException
	{
		CallableStatement callableStatement = null;
		PageInfo pageInfo = new PageInfo();
		LogPage logPage = new LogPage();
		logPage.setPageInfo(pageInfo);
		ResultSet data = null;
		try {
			callableStatement = prepareCall(Routines.LOG_LST.routine());
			callableStatement.setInt(1, pageNumber);
			callableStatement.setInt(2, rowsPerPage);
			callableStatement.setString(3, order == null ? "" : order);
			callableStatement.setString(4, userName == null ? "" : userName);
			callableStatement.setString(5, indirizzoIp == null ? "" : indirizzoIp);
			callableStatement.setString(6, applicazione == null ? "" : applicazione);
			callableStatement.setString(7, userProfile == null ? "" : userProfile);
			callableStatement.setString(8, canale == null ? "" : canale);
			if (dataInizioSessioneDa == null) callableStatement.setNull(9, Types.TIMESTAMP);
			else callableStatement.setTimestamp(9, new Timestamp(dataInizioSessioneDa.getTimeInMillis()));
			if (dataInizioSessioneA == null) callableStatement.setNull(10, Types.TIMESTAMP);
			else callableStatement.setTimestamp(10, new Timestamp(dataInizioSessioneA.getTimeInMillis()));
			callableStatement.setString(11, codiceSocieta == null ? "" : codiceSocieta);
			callableStatement.setString(12, siglaProvincia == null ? "" : siglaProvincia);
			callableStatement.setString(13, codiceUtente == null ? "" : codiceUtente);
			callableStatement.setString(14, chiaveEnte == null ? "" : chiaveEnte);
			callableStatement.registerOutParameter(15, Types.INTEGER);
			callableStatement.registerOutParameter(16, Types.INTEGER);
			callableStatement.registerOutParameter(17, Types.INTEGER);
			callableStatement.registerOutParameter(18, Types.SMALLINT);
			callableStatement.registerOutParameter(19, Types.VARCHAR);
			if (callableStatement.execute()) 
			{
				data = callableStatement.getResultSet();
				loadWebRowSet(data);
				logPage.setListaXml(getWebRowSetXml());
				/*
				 * Se ci sono dati valorizzo pageInfo
				 */
				if (callableStatement.getInt(17) > 0)
				{
					pageInfo.setFirstRow(callableStatement.getInt(15));
					pageInfo.setLastRow(callableStatement.getInt(16));
					pageInfo.setNumPages(callableStatement.getInt(18));
					pageInfo.setNumRows(callableStatement.getInt(17));
					pageInfo.setPageNumber(pageNumber);
					pageInfo.setRowsPerPage(rowsPerPage);
				}
				if(callableStatement.getMoreResults())
				{
					//inizio LP PG21XX04 Leak
					if (data != null) {
						try {
							data.close();
						} catch (SQLException e) {
							e.printStackTrace();
						}
					}
					//fine LP PG21XX04 Leak
					data = callableStatement.getResultSet();
					loadWebRowSet(data);
					logPage.setListaRiepilogoXml(getWebRowSetXml());
				}
			}
			return logPage;
		} catch (IllegalArgumentException e) {
			throw new DaoException(e);
		} catch (SQLException e) {
			throw new DaoException(e);
		} catch (HelperException e) {
			throw new DaoException(e);
		}
		finally
		{
			//inizio LP PG21XX04 Leak
            //DAOHelper.closeIgnoringException(data);
			//DAOHelper.closeIgnoringException(callableStatement);
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
	
	public InvalidateLog invalidateLog(String userName) throws DaoException
	{
		CallableStatement callableStatement = null;
		InvalidateLog response = new InvalidateLog();
		try {
			callableStatement = prepareCall(Routines.LOG_INVALIDATE.routine());
			callableStatement.setString(1, userName);
			callableStatement.registerOutParameter(2, Types.VARCHAR);
			callableStatement.registerOutParameter(3, Types.VARCHAR);
		
			callableStatement.executeUpdate();			
			response.setRetCode(callableStatement.getString(2));
			response.setRetMessage(callableStatement.getString(3));
			
			return response;
		} catch (IllegalArgumentException e) {
			throw new DaoException(e);
		} catch (SQLException e) {
			throw new DaoException(e);
		} catch (HelperException e) {
			throw new DaoException(e);
		}
		finally
		{
			//inizio LP PG21XX04 Leak
			//DAOHelper.closeIgnoringException(callableStatement);
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
	
	public Log getLogBySessionId(String sessionId) throws DaoException
	{
		CallableStatement callableStatement = null;
		Log response = null;
		ResultSet data = null;
		try {
			callableStatement = prepareCall(Routines.LOG_DETAIL.routine());
			callableStatement.setString(1, sessionId);
			
			if (callableStatement.execute()) 
			{
				data = callableStatement.getResultSet();
				if (data.next())
					response = new Log(data);
			}
			return response;
		} catch (IllegalArgumentException e) {
			throw new DaoException(e);
		} catch (SQLException e) {
			throw new DaoException(e);
		} catch (HelperException e) {
			throw new DaoException(e);
		}
		finally
		{
			//inizio LP PG21XX04 Leak
			//DAOHelper.closeIgnoringException(callableStatement);
			if (callableStatement != null) {
				try {
					callableStatement.close();
					data.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			//fine LP PG21XX04 Leak
		}
	}
}
