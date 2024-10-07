package com.seda.payer.core.dao;

import java.lang.reflect.UndeclaredThrowableException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

import com.seda.data.helper.HelperException;
import com.seda.payer.commons.bean.TypeRequest;
import com.seda.payer.core.bean.TipologiaStrutturaRicettiva;
import com.seda.payer.core.exception.DaoException;
import com.seda.payer.core.handler.BaseDaoHandler;
import com.seda.payer.core.messages.Messages;

public class TipologiaStrutturaRicettivaDao extends BaseDaoHandler {

	public TipologiaStrutturaRicettivaDao(Connection connection, String schema) {
		super(connection, schema);
	}

	//inizio LP PG21XX04 Leak
	//private void closeConnection(CallableStatement callableStatement)
	//{
	//	if (callableStatement != null)
	//		DAOHelper.closeIgnoringException(callableStatement);
	//}
	//fine LP PG21XX04 Leak
	
	public TipologiaStrutturaRicettiva doDetail(String chiaveTipologiaStruttura) throws DaoException
	{
		CallableStatement callableStatement = null;
		ResultSet data = null;
		try	{
			callableStatement = prepareCall(Routines.SSR_DODETAIL.routine());
			callableStatement.setString(1, chiaveTipologiaStruttura);
			
			if (callableStatement.execute()) {
				data = callableStatement.getResultSet();
				if (data.next())
					return new TipologiaStrutturaRicettiva(data);
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
	
	
	public void doListAll() throws DaoException {
		CallableStatement callableStatement = null;
		try	
		{
			callableStatement = prepareCall(Routines.SSR_DOLIST_ALL.routine());				
			if (callableStatement.execute()) 
				this.loadWebRowSets(callableStatement);	
			
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
	
	public void doSave(TipologiaStrutturaRicettiva tipoStruttRic,String codOp) throws DaoException {
		CallableStatement callableStatement = null;
		try	{
			if (tipoStruttRic.getChiaveTipologiaStruttura() == null || tipoStruttRic.getChiaveTipologiaStruttura().length() == 0)
				throw new IllegalArgumentException(Messages.INVALID_PARAMETER.format("tipoStruttRic.chiaveRange"));
			if (tipoStruttRic.getDescrizioneTipologiaStruttura() == null || tipoStruttRic.getDescrizioneTipologiaStruttura().length() == 0)
				throw new IllegalArgumentException(Messages.INVALID_PARAMETER.format("tipoStruttRic.descrStruttura"));
			
//			if (anagEnte.getAnagProvCom() == null || anagEnte.getAnagProvCom().getCodiceBelfiore() == null || anagEnte.getAnagProvCom().getCodiceBelfiore().length() == 0)
//				throw new IllegalArgumentException(Messages.INVALID_PARAMETER.format("anagEnte.codiceBelfiore"));			
	/*		if (                            anagEnte.getAnagProvCom().getCompany() == null || anagEnte.getAnagProvCom().getCompany().getCompanyCode()== null ||  anagEnte.getAnagProvCom().getCompany().getCompanyCode().length()==0)
				throw new IllegalArgumentException(Messages.INVALID_PARAMETER.format("anagEnte.companyCode")); */			
			//TipologiaStrutturaRicettiva data = doDetail(tipoStruttRic.getChiaveTipologiaStruttura());
			//if ((data != null) && codOp!=null && codOp.compareTo(TypeRequest.ADD_SCOPE.scope())==0) throw new IllegalArgumentException(Messages.INVALID_PARAMETER.format("tipoStruttRic.saveadd.error"));
			if (codOp.equals(TypeRequest.EDIT_SCOPE.scope())) {
				callableStatement = prepareCall(Routines.SSR_DOUPDATE.routine());
				tipoStruttRic.update(callableStatement);
			}
			else {
				callableStatement = prepareCall(Routines.SSR_DOINSERT.routine());
				tipoStruttRic.save(callableStatement);
			}
			callableStatement.execute();
			//commit(); 
		} catch (SQLException x) {
			if(x.getErrorCode()== -803){
				throw new DaoException(55,"esiste già una tipologia Struttura per i parametri selezionati");
			}
			throw new DaoException(x);
		//inizio LP 20240811 - PGNTCORE-24
		} catch (UndeclaredThrowableException x) {
			DaoException.makeIfDuplicateKeyError(x, 55, "Esiste già una tipologia Struttura per i parametri selezionati");
		//fine LP 20240811 - PGNTCORE-24
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
	
	public void doDelete(TipologiaStrutturaRicettiva tipoStruttRic) throws DaoException {
		CallableStatement callableStatement = null;
		try	{
			callableStatement = prepareCall(Routines.SSR_DODELETE.routine());
			if (tipoStruttRic.getChiaveTipologiaStruttura() == null || tipoStruttRic.getChiaveTipologiaStruttura().length() == 0)
				throw new IllegalArgumentException(Messages.INVALID_PARAMETER.format("tipoStruttRic.chiaveRange"));

			callableStatement.setString(1, tipoStruttRic.getChiaveTipologiaStruttura());
			callableStatement.execute();
			//commit();
			
			
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
	}	
	
	public void doRowSets(TipologiaStrutturaRicettiva tipoStruttRic) throws DaoException {
		rowSets(tipoStruttRic, 0, 0);
	}

	public void doRowSets(TipologiaStrutturaRicettiva tipoStruttRic, int rowsPerPage, int pageNumber) throws DaoException {

			if (rowsPerPage <= 0)
				throw new IllegalArgumentException(Messages.INVALID_PARAMETER.format("rowsPerPage"));

			if (pageNumber <= 0)
				throw new IllegalArgumentException(Messages.INVALID_PARAMETER.format("pageNumber"));
 
			rowSets(tipoStruttRic, rowsPerPage, pageNumber);

	}

	public void rowSets(TipologiaStrutturaRicettiva tipoStruttRic, int rowsPerPage, int pageNumber) throws DaoException {
		CallableStatement callableStatement = null;
		try	{
			callableStatement = prepareCall(Routines.SSR_DOLIST.routine());
			callableStatement.setString(1, tipoStruttRic.getDescrizioneTipologiaStruttura());
			//callableStatement.setString(2, anagEnte.getAnagProvCom().getCompany().getCompanyCode());
			callableStatement.setInt(2, rowsPerPage);
			callableStatement.setInt(3, pageNumber);
			/* we register row start */
			callableStatement.registerOutParameter(4, Types.INTEGER);
			/* we register row end */
			callableStatement.registerOutParameter(5, Types.INTEGER);
			/* we register total rows */
			callableStatement.registerOutParameter(6, Types.INTEGER);
			/* we register total pages */
			callableStatement.registerOutParameter(7, Types.SMALLINT);
			/* we execute procedure */
			if (callableStatement.execute()) {
				this.loadWebRowSets(callableStatement);
				/* we register page info */
				registerPageInfo(rowsPerPage, pageNumber, callableStatement.getInt(4), callableStatement.getInt(5), 
								 callableStatement.getInt(6), callableStatement.getInt(7));
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
	
	public String doListCsv(TipologiaStrutturaRicettiva tipoStruttRic) throws DaoException {
		CallableStatement callableStatement = null;
		ResultSet data = null;
		
		try	{

			callableStatement = prepareCall(Routines.SSR_DOLIST_CSV.routine());
			callableStatement.setString(1, tipoStruttRic.getDescrizioneTipologiaStruttura());

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
