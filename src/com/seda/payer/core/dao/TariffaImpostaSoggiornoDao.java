package com.seda.payer.core.dao;

import java.lang.reflect.UndeclaredThrowableException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.seda.data.helper.HelperException;
import com.seda.payer.core.bean.TariffaImpostaSoggiorno;
import com.seda.payer.core.exception.DaoException;
import com.seda.payer.core.handler.BaseDaoHandler;
import com.seda.payer.core.messages.Messages;

public class TariffaImpostaSoggiornoDao extends BaseDaoHandler {

	public TariffaImpostaSoggiornoDao(Connection connection, String schema) {
		super(connection, schema);
	}

	//inizio LP PG21XX04 Leak
	//private void closeConnection(CallableStatement callableStatement)
	//{
	//	if (callableStatement != null)
	//		DAOHelper.closeIgnoringException(callableStatement);
	//}
	//fine LP PG21XX04 Leak
	
	public TariffaImpostaSoggiorno doDetail_Web(String codiceBelfiore, String chiaveTipologisStruttura, Date dataFinePeriodo) throws DaoException {
		CallableStatement callableStatement = null;
		ResultSet data = null;
		try	{
			callableStatement = prepareCall(Routines.STF_DODETAIL_WEB.routine());
			callableStatement.setString(1, codiceBelfiore);
			callableStatement.setString(2, chiaveTipologisStruttura);
			callableStatement.setDate(3, new java.sql.Date(dataFinePeriodo.getTime()));
			
			if (callableStatement.execute()) {
				data = callableStatement.getResultSet();
				if (data.next()) {
					return new TariffaImpostaSoggiorno(data);
				}
			}
			return null;
		} catch (SQLException x) {
			throw new DaoException(x);
		} catch (IllegalArgumentException x) {
			throw new DaoException(x);
		} catch (HelperException x) {
			throw new DaoException(x);
		}
		finally {
			//inizio LP PG21XX04 Leak
			//closeConnection(callableStatement);
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

// --------------------------------	
	
	public TariffaImpostaSoggiorno doDetail(String chiaveTariffa) throws DaoException 
	{
		CallableStatement callableStatement = null;
		//inizio LP PG21XX04 Leak
		ResultSet data = null;
		//fine LP PG21XX04 Leak
		try	
		{
			callableStatement = prepareCall(Routines.STF_DODETAIL.routine());
			callableStatement.setString(1, chiaveTariffa);			

			if (callableStatement.execute()) 
			{
				//inizio LP PG21XX04 Leak
				//ResultSet data = callableStatement.getResultSet();
				data = callableStatement.getResultSet();
				//fine LP PG21XX04 Leak
				if (data.next()) {
					return new TariffaImpostaSoggiorno(data);
				}
			}
			return null;
		} 
		catch (SQLException x) {
			throw new DaoException(x);
		} 
		catch (IllegalArgumentException x) {
			throw new DaoException(x);
		} 
		catch (HelperException x) {
			throw new DaoException(x);
		} 
		finally {
			//inizio LP PG21XX04 Leak
			//closeConnection(callableStatement);
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

	
	public void doRowSets(TariffaImpostaSoggiorno tariffa, String ordine) throws DaoException {
		doRowSets(tariffa, ordine, 0, 0);
	}

	public void doRowSets(TariffaImpostaSoggiorno tariffa, String ordine, int rowsPerPage, int pageNumber) throws DaoException {
		CallableStatement callableStatement = null;
		if (rowsPerPage <= 0)
			throw new IllegalArgumentException(Messages.INVALID_PARAMETER.format("rowsPerPage"));

		if (pageNumber <= 0)
			throw new IllegalArgumentException(Messages.INVALID_PARAMETER.format("pageNumber"));

		try	{

			callableStatement = prepareCall(Routines.STF_DOLIST.routine());
			callableStatement.setString(1, tariffa.getDescrizioneComune());
			callableStatement.setString(2, tariffa.getDescrizioneTipologiaRicettiva());
			callableStatement.setString(3, tariffa.getRicercaDataDa());
			callableStatement.setString(4, tariffa.getRicercaDataA());
			callableStatement.setString(5, tariffa.getCodiceBelfiore());
			callableStatement.setString(6, tariffa.getChiaveTipologiaStruttura());

			
			callableStatement.setString(7, ordine); //5
			callableStatement.setInt(8, rowsPerPage);
			callableStatement.setInt(9, pageNumber);
			/* we register row start */
			callableStatement.registerOutParameter(10, Types.INTEGER);
			/* we register row end */
			callableStatement.registerOutParameter(11, Types.INTEGER);
			/* we register total rows */
			callableStatement.registerOutParameter(12, Types.INTEGER);
			/* we register total pages */
			callableStatement.registerOutParameter(13, Types.SMALLINT);
			/* we execute procedure */
			if (callableStatement.execute()) {
				this.loadWebRowSets(callableStatement);
				/* we register page info */
				registerPageInfo(rowsPerPage, pageNumber, callableStatement.getInt(10), callableStatement.getInt(11), 
								 callableStatement.getInt(12), callableStatement.getInt(13));
			}
		} catch (SQLException x) {
			throw new DaoException(x);
		} catch (IllegalArgumentException x) {
			throw new DaoException(x);
		} catch (HelperException x) {
			throw new DaoException(x);
		} finally {
			//inizio LP PG21XX04 Leak
			//closeConnection(callableStatement);
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
	

	public int doSave(TariffaImpostaSoggiorno tariffa) throws DaoException 
	{
		int code = 0;
		CallableStatement callableStatement = null;
		try	{
				callableStatement = prepareCall(Routines.STF_DOINSERT.routine());
				callableStatement.setString(1, tariffa.getCodiceBelfiore());
				callableStatement.setString(2, tariffa.getChiaveTipologiaStruttura());
				callableStatement.setDate(3, new java.sql.Date(tariffa.getDataValiditaTariffa().getTime()));
				callableStatement.setBigDecimal(4, tariffa.getImportoTariffa());
				callableStatement.setString(5, tariffa.getOperatoreUltimoAggiornamento());				
				callableStatement.registerOutParameter(6, Types.CHAR);
				callableStatement.registerOutParameter(7, Types.INTEGER);				

				callableStatement.execute();

				tariffa.setChiaveTariffa(callableStatement.getString(6));
				code = callableStatement.getInt(7);
			
		} catch (SQLException x) {
			if(x.getErrorCode()== -803){
				throw new DaoException(55,"esiste già una tariffa per i parametri selezionati");
			}
			throw new DaoException(x);
		//inizio LP 20240811 - PGNTCORE-24
		} catch (UndeclaredThrowableException x) {
			DaoException.makeIfDuplicateKeyError(x, 55, "Esiste già una tariffa per i parametri selezionati");
		//fine LP 20240811 - PGNTCORE-24
		} catch (IllegalArgumentException x) {
			System.out.println("doSave failed generic error due to: " + x.getMessage());
			throw new DaoException(101, x.getMessage());
		} catch (HelperException x) {
			throw new DaoException(x);
		} finally {
			//inizio LP PG21XX04 Leak
			//closeConnection(callableStatement);
			if (callableStatement != null) {
				try {
					callableStatement.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			//fine LP PG21XX04 Leak
		}
		return code;
	}

	
	public int doUpdate(TariffaImpostaSoggiorno tariffa) throws DaoException 
	{
		int code = 0;
		CallableStatement callableStatement = null;
		try	
		{
/*
 		IN I_STF_KSTFKSTF CHAR(10),
		IN I_STF_CANEBELF CHAR(4),
		IN I_STF_CSTFCSSR CHAR(3),
		IN I_STF_GSTFGVAL DATE,
		IN I_STF_ISTFITAR DECIMAL(15 , 2),
		IN I_STF_CSTFCOPE VARCHAR(50),
		OUT O_CODE INTEGER

 */
		callableStatement = prepareCall(Routines.STF_DOUPDATE.routine());
		callableStatement.setString(1, tariffa.getChiaveTariffa());
		callableStatement.setString(2, tariffa.getCodiceBelfiore());
		callableStatement.setString(3, tariffa.getChiaveTipologiaStruttura());
		callableStatement.setDate(4, new java.sql.Date(tariffa.getDataValiditaTariffa().getTime()));
		callableStatement.setBigDecimal(5, tariffa.getImportoTariffa());
		callableStatement.setString(6, tariffa.getOperatoreUltimoAggiornamento());				
		callableStatement.registerOutParameter(7, Types.INTEGER);
	
		callableStatement.execute();
		code = callableStatement.getInt(7);
			
		} catch (SQLException x) {
			throw new DaoException(x);
		} catch (IllegalArgumentException x) {
			System.out.println("doSave failed generic error due to: " + x.getMessage());
			throw new DaoException(101, x.getMessage());
		} catch (HelperException x) {
			throw new DaoException(x);
		} finally {
			//inizio LP PG21XX04 Leak
			//closeConnection(callableStatement);
			if (callableStatement != null) {
				try {
					callableStatement.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			//fine LP PG21XX04 Leak
		}
		return code;
	}
	

	public int doDelete(TariffaImpostaSoggiorno tariffa) throws DaoException {
		int code = 0;
		CallableStatement callableStatement = null;
		try	{
			callableStatement = prepareCall(Routines.STF_DODELETE.routine());
			callableStatement.setString(1, tariffa.getChiaveTariffa());
			callableStatement.registerOutParameter(2, Types.INTEGER);
			callableStatement.execute();
			code = callableStatement.getInt(2);
		} catch (SQLException x) {
			if(x.getErrorCode()== -532){
				throw new DaoException(55,"Impossibile effettuare la cancellazione, sono presenti una o più informazioni correlate");
			}
			throw new DaoException(x.getErrorCode(),x.getMessage(),x);
		} catch (IllegalArgumentException x) {
			throw new DaoException(Integer.parseInt(Messages.ILL_ARG_ERR_CODE.format()),x.getMessage(),x);
		} catch (HelperException x) {
			throw new DaoException(Integer.parseInt(Messages.HELPER_ERR_CODE.format()),x.getMessage(),x);
		} finally {
			//inizio LP PG21XX04 Leak
			//closeConnection(callableStatement);
			if (callableStatement != null) {
				try {
					callableStatement.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			//fine LP PG21XX04 Leak
		}
		return code;
	}

	/**
	 * Calcola il numero delle comunicazioni non annullate, che utilizzano la tariffa passata come parametro 
	 * @param chiaveTariffa
	 * @return
	 * @throws DaoException
	 */
	public int doCountTariffa_Comunicazioni(String chiaveTariffa) throws DaoException
	{
		int count = -1;
		
		CallableStatement callableStatement = null;
		try	{
			callableStatement = prepareCall(Routines.STF_DOCOUNT_SCT.routine());
			callableStatement.setString(1, chiaveTariffa);
			
			callableStatement.registerOutParameter(2, Types.INTEGER);
			callableStatement.execute();
			
			count = callableStatement.getInt(2);
			
		} catch (SQLException x) {
			throw new DaoException(x);
		} catch (IllegalArgumentException x) {
			throw new DaoException(x);
		} catch (HelperException x) {
			throw new DaoException(x);
		} finally {
			//inizio LP PG21XX04 Leak
			//closeConnection(callableStatement);
			if (callableStatement != null) {
				try {
					callableStatement.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			//fine LP PG21XX04 Leak
		}
		
		return count;
		
	}
	
	public String doListCsv(TariffaImpostaSoggiorno tariffa) throws DaoException {
		CallableStatement callableStatement = null;
		ResultSet data = null;
		
		try	{

			callableStatement = prepareCall(Routines.STF_DOLIST_CSV.routine());
			callableStatement.setString(1, tariffa.getDescrizioneComune());
			callableStatement.setString(2, tariffa.getDescrizioneTipologiaRicettiva());
			callableStatement.setString(3, tariffa.getRicercaDataDa());
			callableStatement.setString(4, tariffa.getRicercaDataA());
			callableStatement.setString(5, tariffa.getCodiceBelfiore());
			callableStatement.setString(6, tariffa.getChiaveTipologiaStruttura());

			StringBuffer sb = new StringBuffer();
			boolean resultsAvailable = callableStatement.execute();
			
			while (resultsAvailable) {
			
				data = callableStatement.getResultSet();
				
				while(data != null && data.next()) {
					sb.append(data.getString("RECORD"));
					sb.append("\r\n");
				}
				
				resultsAvailable = callableStatement.getMoreResults();
			}
			
			return sb.toString();
			
		} catch (SQLException x) {
			throw new DaoException(x);
		} catch (IllegalArgumentException x) {
			throw new DaoException(x);
		} catch (HelperException x) {
			throw new DaoException(x);
		} finally {
			//inizio LP PG21XX04 Leak
			//closeConnection(callableStatement);
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
