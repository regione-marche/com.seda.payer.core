package com.seda.payer.core.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

import com.seda.data.helper.HelperException;
import com.seda.payer.commons.bean.TypeRequest;
import com.seda.payer.core.bean.Bollettino;
import com.seda.payer.core.exception.DaoException;
import com.seda.payer.core.handler.BaseDaoHandler;
import com.seda.payer.core.messages.Messages;

public class BollettinoDao extends BaseDaoHandler{
	

	/**
	 * Costruttore
	 * @param connection
	 * @param schema
	 */
	public BollettinoDao(Connection connection, String schema) {
		super(connection, schema);
	}

	//inizio LP 20240909 - PGNTBOLDER-1
	public Bollettino doDetail(String tipoBollettino) throws DaoException {
		return doDetailTail(true, tipoBollettino);
	}

	public Bollettino doDetailTail(boolean bFlagUpdateAutocomit, String tipoBollettino) throws DaoException {
	//fine LP 20240909 - PGNTBOLDER-1
		//inizio LP PG21XX04 Leak
		CallableStatement callableStatement = null;
		ResultSet data = null;
		//fine LP PG21XX04 Leak
		try	{
			//inizio LP PG21XX04 Leak
			//CallableStatement callableStatement = prepareCall(Routines.BOL_DODETAIL2.routine());
			//inizio LP 20240909 - PGNTBOLDER-1
			//callableStatement = prepareCall(Routines.BOL_DODETAIL2.routine());
			callableStatement = prepareCall(bFlagUpdateAutocomit, Routines.BOL_DODETAIL2.routine());
			//fine LP 20240909 - PGNTBOLDER-1
			//fine LP PG21XX04 Leak
			callableStatement.setString(1, tipoBollettino);
			if (callableStatement.execute()) {
				//inizio LP PG21XX04 Leak
				//ResultSet data = callableStatement.getResultSet();
				data = callableStatement.getResultSet();
				//fine LP PG21XX04 Leak
				if (data.next())
					return new Bollettino(data);
			}
			return null;
		} catch (SQLException x) {
			throw new DaoException(x);
		} catch (IllegalArgumentException x) {
			throw new DaoException(x);
		} catch (HelperException x) {
			throw new DaoException(x);
		//inizio LP PG21XX04 Leak
		//}
		} finally {	
			if(data != null) {
				try {
					data.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(callableStatement != null) {
				try {
					callableStatement.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		//fine LP PG21XX04 Leak
	}
	
	/*public boolean insRecord (Bollettino b)throws DaoException
	{
		CallableStatement callableStatement = null;
		try{
				callableStatement = prepareCall(Routines.BOL_DOINSERT.routine());
				callableStatement.setString(1, b.getTipoBollettino());
				callableStatement.setString(2, b.getDesCompBollettino());
				callableStatement.setString(3, b.getModCompBollettino());
				callableStatement.setString(4, b.getDesCompBollettino());
				callableStatement.setString(5, b.getTipoFlusso());
				callableStatement.setString(6, b.getOpertoreUltimoAggiornamento());
				callableStatement.registerOutParameter(7, Types.INTEGER);
				callableStatement.executeUpdate();
				int numrighe =  callableStatement.getInt(7);
				if (numrighe == 1) return true;
				else return false;
		} catch (SQLException x) {
			throw new DaoException(x);
		} catch (IllegalArgumentException x) {
			throw new DaoException(x);
		} catch (HelperException x) {
			throw new DaoException(x);
		}
		finally
		{
			DAOHelper.closeIgnoringException(callableStatement);
		}
	}*/
	
	/*public boolean updateRecord (Bollettino b, String codOp)throws DaoException
	{
		CallableStatement callableStatement = null;
		try{
				callableStatement = prepareCall(Routines.BOL_DOUPDATE.routine());
				callableStatement.setString(1, b.getTipoBollettino());
				callableStatement.setString(2, b.getDesCompBollettino());
				callableStatement.setString(3, b.getModCompBollettino());
				callableStatement.setString(4, b.getDesCompBollettino());
				callableStatement.setString(5, b.getTipoFlusso());
				callableStatement.setString(6, codOp);
				callableStatement.registerOutParameter(7, Types.INTEGER);
				callableStatement.executeUpdate();
				int numrighe =  callableStatement.getInt(7);
				if (numrighe == 1) return true;
				else return false;
		} catch (SQLException x) {
			throw new DaoException(x);
		} catch (IllegalArgumentException x) {
			throw new DaoException(x);
		} catch (HelperException x) {
			throw new DaoException(x);
		}
		finally
		{
			DAOHelper.closeIgnoringException(callableStatement);
		}
	}*/
	
	/*public void deleteRecord (String tipoBollettino)throws DaoException
	{
		CallableStatement callableStatement = null;
		try{
				callableStatement = prepareCall(Routines.BOL_DODELETE.routine());
				callableStatement.setString(1, tipoBollettino);
				callableStatement.executeUpdate();
		} catch (SQLException x) {
			throw new DaoException(x);
		} catch (IllegalArgumentException x) {
			throw new DaoException(x);
		} catch (HelperException x) {
			throw new DaoException(x);
		}
		finally
		{
			DAOHelper.closeIgnoringException(callableStatement);
		}
	}*/

	public void doRowSets(String tipoBollettino, String anaTipoBollettino) throws DaoException {
		rowSets(tipoBollettino, anaTipoBollettino, 0, 0);
	}

	public void doRowSets(String tipoBollettino, String desTipoBollettino, int rowsPerPage, int pageNumber) throws DaoException {
		if (rowsPerPage <= 0)
			throw new IllegalArgumentException(Messages.INVALID_PARAMETER.format("rowsPerPage"));
		if (pageNumber <= 0)
			throw new IllegalArgumentException(Messages.INVALID_PARAMETER.format("pageNumber"));
		rowSets(tipoBollettino, desTipoBollettino, rowsPerPage, pageNumber);
	}

	private void rowSets(String tipoBollettino, String desTipoBollettino, int rowsPerPage, int pageNumber) throws DaoException {
		//inizio LP PG21XX04 Leak
		CallableStatement callableStatement = null;
		//fine LP PG21XX04 Leak
		try	{
			//inizio LP PG21XX04 Leak
			//CallableStatement callableStatement = prepareCall(Routines.BOL_RETURN_LIST.routine());
			callableStatement = prepareCall(Routines.BOL_RETURN_LIST.routine());
			//inizio LP PG21XX04 Leak
			callableStatement.setString(1, tipoBollettino);
			callableStatement.setString(2, desTipoBollettino);
			callableStatement.setString(3, "");
			callableStatement.setInt(4, rowsPerPage);
			callableStatement.setInt(5, pageNumber);
			/* we register row start */
			callableStatement.registerOutParameter(6, Types.INTEGER);
			/* we register row end */
			callableStatement.registerOutParameter(7, Types.INTEGER);
			/* we register total rows */
			callableStatement.registerOutParameter(8, Types.INTEGER);
			/* we register total pages */
			callableStatement.registerOutParameter(9, Types.SMALLINT);
			/* we execute procedure */
			if (callableStatement.execute()) {
				this.loadWebRowSets(callableStatement);
				/* we register page info */
				registerPageInfo(rowsPerPage, pageNumber, callableStatement.getInt(6), callableStatement.getInt(7), 
								 callableStatement.getInt(8), callableStatement.getInt(9));
			}
		} catch (SQLException x) {
			throw new DaoException(x);
		} catch (IllegalArgumentException x) {
			throw new DaoException(x);
		} catch (HelperException x) {
			throw new DaoException(x);
		//inizio LP PG21XX04 Leak
		//}
		} finally {	
			if(callableStatement != null) {
				try {
					callableStatement.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		//fine LP PG21XX04 Leak
	}
	
	/*public void getBollettiniXml(
			String tipoBollettino,
			String desTipoBollettino,
			int rowsPerPage, 
			int pageNumber) throws DaoException
	{
		CallableStatement callableStatement;
		try
		{
			callableStatement = prepareCall(Routines.BOL_RETURN_LIST.routine());
			callableStatement.setString(1, tipoBollettino);
			callableStatement.setString(2, desTipoBollettino);
			callableStatement.setString(3, "");
			callableStatement.setInt(4, rowsPerPage);
			callableStatement.setInt(5, pageNumber);
			 we register row start 
			callableStatement.registerOutParameter(6, Types.INTEGER);
			 we register row end 
			callableStatement.registerOutParameter(7, Types.INTEGER);
			 we register total rows 
			callableStatement.registerOutParameter(8, Types.INTEGER);
			 we register total pages 
			callableStatement.registerOutParameter(9, Types.SMALLINT);
			 we execute procedure 
			if (callableStatement.execute()) {
				this.loadWebRowSets(callableStatement);
				 we register page info 
				registerPageInfo(rowsPerPage, pageNumber, callableStatement.getInt(6), callableStatement.getInt(7), 
								 callableStatement.getInt(8), callableStatement.getInt(9));
			}
		} catch (SQLException x) {
			throw new DaoException(x);
		} catch (IllegalArgumentException x) {
			throw new DaoException(x);
		} catch (HelperException x) {
			throw new DaoException(x);
		}
	}
	*/
	//inizio LP 20240909 - PGNTBOLDER-1
	public void doSave(Bollettino bollettino, String codOp) throws DaoException {
		doSaveTail(true, bollettino, codOp);
	}

	public void doSaveTail(boolean bFlagUpdateAutocomit, Bollettino bollettino, String codOp) throws DaoException {
	//fine LP 20240909 - PGNTBOLDER-1
		CallableStatement callableStatement=null;
		try	{
			if (bollettino.getTipoBollettino() == null || bollettino.getTipoBollettino().length() == 0)
				throw new IllegalArgumentException(Messages.INVALID_PARAMETER.format("bollettino_bollettinoType"));
			//inizio LP 20240909 - PGNTBOLDER-1
			//Bollettino data = doDetail(bollettino.getTipoBollettino());
			Bollettino data = doDetailTail(bFlagUpdateAutocomit, bollettino.getTipoBollettino());
			//fine LP 20240909 - PGNTBOLDER-1
			if ((data != null) && codOp != null && codOp.compareTo(TypeRequest.ADD_SCOPE.scope()) == 0)
				throw new IllegalArgumentException(Messages.INVALID_PARAMETER.format("bollettino.saveadd.error"));
			if (data != null) {	
				//inizio LP 20240909 - PGNTBOLDER-1
				//callableStatement = prepareCall(Routines.BOL_DOUPDATE.routine());
			    callableStatement = prepareCall(bFlagUpdateAutocomit, Routines.BOL_DOUPDATE.routine());
				//fine LP 20240909 - PGNTBOLDER-1
			} else {
				//inizio LP 20240909 - PGNTBOLDER-1
				//callableStatement = prepareCall(Routines.BOL_DOINSERT.routine());
				callableStatement = prepareCall(bFlagUpdateAutocomit, Routines.BOL_DOINSERT.routine());
				//fine LP 20240909 - PGNTBOLDER-1
			}
			bollettino.save(callableStatement);
			callableStatement.execute();
			//commit();
		} catch (SQLException x) {
			throw new DaoException(x);
		} catch (IllegalArgumentException x) {
			throw new DaoException(x);
		} catch (HelperException x) {
			throw new DaoException(x);
		//inizio LP PG21XX04 Leak
		//}
		} finally {	
			if(callableStatement != null) {
				try {
					callableStatement.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		//fine LP PG21XX04 Leak
	}

	//inizio LP 20240909 - PGNTBOLDER-1
	public void deleteRecord(Bollettino bollettino) throws DaoException {
		deleteRecordTail(true, bollettino);
	}

	public void deleteRecordTail(boolean bFlagUpdateAutocomit, Bollettino bollettino) throws DaoException {
	//fine LP 20240909 - PGNTBOLDER-1
		//inizio LP PG21XX04 Leak
		CallableStatement callableStatement = null;
		//fine LP PG21XX04 Leak
		try	{
			//inizio LP PG21XX04 Leak
			//CallableStatement callableStatement = prepareCall(Routines.BOL_DODELETE.routine());
			//inizio LP 20240909 - PGNTBOLDER-1
			//callableStatement = prepareCall(Routines.BOL_DODELETE.routine());
			callableStatement = prepareCall(bFlagUpdateAutocomit, Routines.BOL_DODELETE.routine());
			//fine LP 20240909 - PGNTBOLDER-1
			//fine LP PG21XX04 Leak
			if (bollettino.getTipoBollettino().length() == 0 || bollettino.getTipoBollettino() == null)
				throw new IllegalArgumentException(Messages.INVALID_PARAMETER.format("bollettino.TipoBollettino()"));
			callableStatement.setString(1, bollettino.getTipoBollettino());
			callableStatement.execute();
			//commit();
		} catch (SQLException x) {
			throw new DaoException(x.getErrorCode(),x.getMessage(),x);
		} catch (IllegalArgumentException x) {
			throw new DaoException(Integer.parseInt(Messages.ILL_ARG_ERR_CODE.format()),x.getMessage(),x);
		} catch (HelperException x) {
			throw new DaoException(Integer.parseInt(Messages.HELPER_ERR_CODE.format()),x.getMessage(),x);
		//inizio LP PG21XX04 Leak
		//}
		} finally {	
			if(callableStatement != null) {
				try {
					callableStatement.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		//fine LP PG21XX04 Leak
	}
}
