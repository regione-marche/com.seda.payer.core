package com.seda.payer.core.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

import com.seda.data.helper.HelperException;
import com.seda.payer.commons.bean.TypeRequest;
import com.seda.payer.core.bean.TotemTipologiaImposta;
import com.seda.payer.core.exception.DaoException;
import com.seda.payer.core.handler.BaseDaoHandler;
import com.seda.payer.core.messages.Messages;

public class TotemTipologiaImpostaDao extends BaseDaoHandler {

	public TotemTipologiaImpostaDao(Connection connection, String schema) {
		super(connection, schema);
	}

	public void doSave(TotemTipologiaImposta totem,String codOp) throws DaoException {
		CallableStatement callableStatement = null;
		try	{
			if (checkNullOrEmpty(totem.getCodiceEnte())) {
				throw new IllegalArgumentException(Messages.INVALID_PARAMETER.format("codice ente"));
			}
			
			if (checkNullOrEmpty(totem.getImpostaServizio())) {
				throw new IllegalArgumentException(Messages.INVALID_PARAMETER.format("imposta servizio"));
			}
			
			if (checkNullOrEmpty(totem.getTipologiaImposta())) {
				throw new IllegalArgumentException(Messages.INVALID_PARAMETER.format("tipologia imposta"));
			}
			
			TotemTipologiaImposta data = doSelect(totem.getCodiceEnte(), totem.getImpostaServizio());
			if ((data != null) && codOp!=null && codOp.compareTo(TypeRequest.ADD_SCOPE.scope()) == 0) throw new IllegalArgumentException(Messages.INVALID_PARAMETER.format("totem.saveadd.error"));
			
			if (data != null){
				callableStatement = prepareCall(Routines.TIT_DOUPDATE.routine());
				totem.update(callableStatement);
			} else {
				callableStatement = prepareCall(Routines.TIT_DOINSERT.routine());
				totem.save(callableStatement);
			}			
			callableStatement.execute();
		} catch (SQLException e) { 
			throw new DaoException(e);
		} catch (IllegalArgumentException e) {
			throw new DaoException(e);
		} catch (HelperException e) {
			throw new DaoException(e);
		}   finally {	
			if(callableStatement != null) {
				try {
					callableStatement.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	public void deleteRecord(TotemTipologiaImposta totem) throws DaoException {
		CallableStatement callableStatement = null;
		try	{
			callableStatement = prepareCall(Routines.TIT_DODELETE.routine());
			if (checkNullOrEmpty(totem.getCodiceEnte())) { 
				throw new IllegalArgumentException(Messages.INVALID_PARAMETER.format("codice ente")); 
			}
			
			if (checkNullOrEmpty(totem.getImpostaServizio())) { 
				throw new IllegalArgumentException(Messages.INVALID_PARAMETER.format("imposta servizio")); 
			}
			
			callableStatement.setString(1, totem.getCodiceEnte());
			callableStatement.setString(2, totem.getImpostaServizio());
			callableStatement.execute();
		} catch (SQLException x) {
			throw new DaoException(x.getErrorCode(),x.getMessage(),x);
		} catch (IllegalArgumentException x) {
			throw new DaoException(Integer.parseInt(Messages.ILL_ARG_ERR_CODE.format()),x.getMessage(),x);
		} catch (HelperException x) {
			throw new DaoException(Integer.parseInt(Messages.HELPER_ERR_CODE.format()),x.getMessage(),x);
		} finally {	
			if(callableStatement != null) {
				try {
					callableStatement.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	public void doRowSets(String codEnte, String impostaServizio, String tipologiaImposta) throws DaoException {
		rowSets(codEnte, impostaServizio, tipologiaImposta, 0, 0);
	}

	public void doRowSets(String codEnte, String impostaServizio, String tipologiaImposta, int rowsPerPage, int pageNumber) throws DaoException {
			if (rowsPerPage <= 0)
				throw new IllegalArgumentException(Messages.INVALID_PARAMETER.format("rowsPerPage"));
			if (pageNumber <= 0)
				throw new IllegalArgumentException(Messages.INVALID_PARAMETER.format("pageNumber"));
 
			this.rowSets(codEnte, impostaServizio, tipologiaImposta, rowsPerPage, pageNumber);
	}
	
	private void rowSets(String codEnte, String impostaServizio, String tipologiaImposta, int rowsPerPage, int pageNumber) throws DaoException {
		CallableStatement callableStatement = null;
		try	{
			callableStatement = prepareCall(Routines.TIT_RECUPERA_LISTA.routine());
			callableStatement.setString(1, codEnte);
			callableStatement.setString(2, impostaServizio);
			callableStatement.setString(3, tipologiaImposta);
			callableStatement.setInt(4, rowsPerPage);
			callableStatement.setInt(5, pageNumber);
			
			callableStatement.registerOutParameter(6, Types.INTEGER);
			callableStatement.registerOutParameter(7, Types.INTEGER);
			callableStatement.registerOutParameter(8, Types.INTEGER);
			callableStatement.registerOutParameter(9, Types.SMALLINT);
			if (callableStatement.execute()) {
				this.loadWebRowSets(callableStatement);
				registerPageInfo(rowsPerPage, pageNumber, callableStatement.getInt(6), callableStatement.getInt(7), 
								 callableStatement.getInt(8), callableStatement.getInt(9));
			}
		} catch (SQLException e) {
			throw new DaoException(e);
		} catch (IllegalArgumentException e) {
			throw new DaoException(e);
		} catch (HelperException e) {
			throw new DaoException(e);
		} finally {	
			if(callableStatement != null) {
				try {
					callableStatement.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	public TotemTipologiaImposta doSelect(String codiceEnte, String impostaServizio) throws DaoException {
		CallableStatement callableStatement = null;
		ResultSet data = null;

		try	{
			callableStatement = prepareCall(Routines.TIT_DOSELECT.routine());
			callableStatement.setString(1, codiceEnte);
			callableStatement.setString(2, impostaServizio);
			
			if (callableStatement.execute()) {
				data = callableStatement.getResultSet();				
				if (data.next())
					return new TotemTipologiaImposta(data);
			} return null;
		} catch (SQLException e) {
			throw new DaoException(e);
		} catch (IllegalArgumentException e) {
			throw new DaoException(e);
		} catch (HelperException e) {
			throw new DaoException(e);
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
	}

	private boolean checkNullOrEmpty(String str) {
		return str == null || str.length() == 0;
	}

}
