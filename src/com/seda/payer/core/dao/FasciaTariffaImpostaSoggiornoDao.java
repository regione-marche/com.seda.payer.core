package com.seda.payer.core.dao;

import java.lang.reflect.UndeclaredThrowableException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

import com.seda.data.dao.DAOHelper;
import com.seda.data.helper.HelperException;
import com.seda.payer.core.bean.FasciaTariffaImpostaSoggiorno;
import com.seda.payer.core.bean.TariffaImpostaSoggiorno;
import com.seda.payer.core.exception.DaoException;
import com.seda.payer.core.handler.BaseDaoHandler;
import com.seda.payer.core.messages.Messages;

public class FasciaTariffaImpostaSoggiornoDao extends BaseDaoHandler {

	public FasciaTariffaImpostaSoggiornoDao(Connection connection, String schema) {
		super(connection, schema);
	}

	private void closeConnection(CallableStatement callableStatement)
	{
		if (callableStatement != null)
			DAOHelper.closeIgnoringException(callableStatement);
	}
	
	public FasciaTariffaImpostaSoggiorno doDetail_Web(String chiaveTariffa, String codice) throws DaoException {
		CallableStatement callableStatement = null;
		ResultSet data = null;
		try	{
			callableStatement = prepareCall(Routines.SFI_DODETAIL_WEB.routine());
			callableStatement.setString(1, chiaveTariffa);
			callableStatement.setString(2, codice);
			
			if (callableStatement.execute()) {
				data = callableStatement.getResultSet();
				if (data.next())
					return new FasciaTariffaImpostaSoggiorno(data);
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

	public FasciaTariffaImpostaSoggiorno doDetail(Long chiaveFasciaTariffa) throws DaoException 
	{
		CallableStatement callableStatement = null;
		//inizio LP PG21XX04 Leak
		ResultSet data = null;
		//fine LP PG21XX04 Leak
		try	
		{
			Integer p = new Integer(chiaveFasciaTariffa.toString());
			callableStatement = prepareCall(Routines.SFI_DODETAIL.routine());
			callableStatement.setInt(1, p.intValue());			

			if (callableStatement.execute()) 
			{
				//inizio LP PG21XX04 Leak
				//ResultSet data = callableStatement.getResultSet();
				data = callableStatement.getResultSet();
				//fine LP PG21XX04 Leak
				if (data.next())
					return new FasciaTariffaImpostaSoggiorno(data);
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

	
	public void doRowSets(FasciaTariffaImpostaSoggiorno fasciaTariffa, String ordine) throws DaoException {
		doRowSets(fasciaTariffa, ordine, 0, 0);
	}

	public void doRowSets(FasciaTariffaImpostaSoggiorno fasciaTariffa, String ordine, int rowsPerPage, int pageNumber) throws DaoException {
		CallableStatement callableStatement = null;
		if (rowsPerPage <= 0)
			throw new IllegalArgumentException(Messages.INVALID_PARAMETER.format("rowsPerPage"));

		if (pageNumber <= 0)
			throw new IllegalArgumentException(Messages.INVALID_PARAMETER.format("pageNumber"));

		try	{

			callableStatement = prepareCall(Routines.SFI_DOLIST.routine());
			int ik = 0;
			callableStatement.setString(++ik, fasciaTariffa.getChiaveTariffa());
			
			callableStatement.setString(++ik, ordine);
			callableStatement.setInt(++ik, rowsPerPage);
			callableStatement.setInt(++ik, pageNumber);
			/* we register row start */
			int ki = ik;
			callableStatement.registerOutParameter(++ik, Types.INTEGER);
			/* we register row end */
			callableStatement.registerOutParameter(++ik, Types.INTEGER);
			/* we register total rows */
			callableStatement.registerOutParameter(++ik, Types.INTEGER);
			/* we register total pages */
			callableStatement.registerOutParameter(++ik, Types.SMALLINT);
			/* we execute procedure */
			if (callableStatement.execute()) {
				this.loadWebRowSets(callableStatement);
				/* we register page info */
				registerPageInfo(rowsPerPage, pageNumber, callableStatement.getInt(++ki), callableStatement.getInt(++ki), 
								 callableStatement.getInt(++ki), callableStatement.getInt(++ki));
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
	

	public int doSave(FasciaTariffaImpostaSoggiorno fasciaTariffa) throws DaoException 
	{
		int code = 0;
		CallableStatement callableStatement = null;
		try	{
				callableStatement = prepareCall(Routines.SFI_DOINSERT.routine());
				callableStatement.setString(1, fasciaTariffa.getChiaveTariffa());
				callableStatement.setString(2, fasciaTariffa.getCodice());
				callableStatement.setString(3, fasciaTariffa.getDescrizione());
				callableStatement.setBigDecimal(4, fasciaTariffa.getImportoTariffa());
				callableStatement.setString(5, fasciaTariffa.getOperatoreUltimoAggiornamento());				
				callableStatement.registerOutParameter(6, Types.INTEGER);
				callableStatement.registerOutParameter(7, Types.INTEGER);				

				callableStatement.execute();
                int ret = callableStatement.getInt(6);
    			Integer p = new Integer(ret);
                
				fasciaTariffa.setChiaveFasciaTariffa(p.longValue());
				code = callableStatement.getInt(7);
			
		} catch (SQLException x) {
			if(x.getErrorCode()== -803){
				throw new DaoException(55,"Esiste già una fascia tariffa con lo stesso codice inserito.");
			}
			throw new DaoException(x);
		//inizio LP 20240811  - PGNTCORE-24
		} catch (UndeclaredThrowableException x) {
			DaoException.makeIfDuplicateKeyError(x, 55, "esiste già una fascia tariffa con lo stesso codice inserito.");
		//fine LP 20240811  - PGNTCORE-24
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

	
	public int doUpdate(FasciaTariffaImpostaSoggiorno fasciaTariffa) throws DaoException 
	{
		int code = 0;
		CallableStatement callableStatement = null;
		try	
		{
			Integer p = new Integer(fasciaTariffa.getChiaveFasciaTariffa().toString());
			callableStatement = prepareCall(Routines.SFI_DOUPDATE.routine());
			callableStatement.setInt(1, p.intValue());
			callableStatement.setString(2, fasciaTariffa.getDescrizione());
			callableStatement.setBigDecimal(3, fasciaTariffa.getImportoTariffa());
			callableStatement.setString(4, fasciaTariffa.getOperatoreUltimoAggiornamento());				
			callableStatement.registerOutParameter(5, Types.INTEGER);
		
			callableStatement.execute();
			code = callableStatement.getInt(5);
				
		} catch (SQLException x) {
			throw new DaoException(x);
		} catch (IllegalArgumentException x) {
			System.out.println("doUpdate failed generic error due to: " + x.getMessage());
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
	

	public int doDelete(FasciaTariffaImpostaSoggiorno fasciaTariffa) throws DaoException {
		int code = 0;
		CallableStatement callableStatement = null;
		try	{
			Integer p = new Integer(fasciaTariffa.getChiaveFasciaTariffa().toString());
			callableStatement = prepareCall(Routines.SFI_DODELETE.routine());
			callableStatement.setInt(1, p.intValue());
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
	public int doCountFasciaTariffa_Comunicazioni(Long chiaveFasciaTariffa) throws DaoException
	{
		int count = -1;
		
		CallableStatement callableStatement = null;
		try	{
			Integer p = new Integer(chiaveFasciaTariffa.toString());
			//A T T E N Z I O N E  A T T E N Z I O N E  A T T E N Z I O N E
			//
			//TODO: SP da da definire
			//
			//A T T E N Z I O N E  A T T E N Z I O N E  A T T E N Z I O N E
			callableStatement = prepareCall(Routines.STF_DOCOUNT_SCT.routine()); //cancellare
			/*
			callableStatement = prepareCall(Routines.SFI_DOCOUNT_SCT.routine());
			callableStatement.setLong(1, p.intValue());
			
			callableStatement.registerOutParameter(2, Types.INTEGER);
			callableStatement.execute();
			
			count = callableStatement.getInt(2);
			*/
			count = 0;
			
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
			//A T T E N Z I O N E  A T T E N Z I O N E  A T T E N Z I O N E
			//
			//TODO: SP da da definire
			//
			//A T T E N Z I O N E  A T T E N Z I O N E  A T T E N Z I O N E
			callableStatement = prepareCall(Routines.STF_DOLIST_CSV.routine());
			/*
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
				
				while(data.next()) {
					sb.append(data.getString("RECORD"));
					sb.append("\r\n");
				}
				
				resultsAvailable = callableStatement.getMoreResults();
			}
			
			return sb.toString();
			*/
			return "";
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

	public void doList(String chiaveTariffa) throws DaoException {
		CallableStatement callableStatement = null;
		try	
		{
			callableStatement = prepareCall(Routines.SFI_DOLIST_KEY.routine());
			callableStatement.setString(1, chiaveTariffa);
			if (callableStatement.execute())
			{
				this.loadWebRowSets(callableStatement);
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
}