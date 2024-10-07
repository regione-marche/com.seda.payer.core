package com.seda.payer.core.dao;

import java.lang.reflect.UndeclaredThrowableException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import com.seda.payer.commons.bean.TypeRequest;
import com.seda.payer.core.bean.AssocBen;
import com.seda.payer.core.exception.DaoException;
import com.seda.payer.core.handler.BaseDaoHandler;
import com.seda.payer.core.messages.Messages;
import com.seda.data.helper.HelperException;


public class AssocBenDao extends BaseDaoHandler {

	public AssocBenDao(Connection connection, String schema) {
		super(connection, schema);
	}
	
	public AssocBen doDetail(AssocBen associazione) throws DaoException {
		CallableStatement callableStatement = null;
		//inizio LP PG21XX04 Leak
		ResultSet data = null;
		//fine LP PG21XX04 Leak
		try	{
			
			callableStatement = prepareCall(Routines.CBP_DODETAIL.routine());
			callableStatement.setString(1, associazione.getUser().getCompany().getCompanyCode());
			callableStatement.setString(2, associazione.getUser().getUserCode());
			callableStatement.setString(3, associazione.getAnagBeneficiario().getChiaveEnte());		
			callableStatement.setString(4, associazione.getDataValidita());		

			if (callableStatement.execute()) {
				//inizio LP PG21XX04 Leak
				//ResultSet data = callableStatement.getResultSet();
				data = callableStatement.getResultSet();
				//inizio LP PG21XX04 Leak
				if (data.next())
					return new AssocBen(data);
			}
			return null;
		} catch (SQLException x) {
			throw new DaoException(x);
		} catch (IllegalArgumentException x) {
			throw new DaoException(x);
		} catch (HelperException x) {
			throw new DaoException(x);
		} finally {
			//inizio LP PG21XX04 Leak
			//closeConnection(callableStatement);
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
			//fine LP PG21XX04 Leak
		}
	}

	public void doRowSets(AssocBen associazione, String ordine) throws DaoException {
		doRowSets(associazione, ordine, 0, 0);
	}

	public void doRowSets(AssocBen associazione, String ordine, int rowsPerPage, int pageNumber) throws DaoException {
		if (rowsPerPage <= 0)
			throw new IllegalArgumentException(Messages.INVALID_PARAMETER.format("rowsPerPage"));
		if (pageNumber <= 0)
			throw new IllegalArgumentException(Messages.INVALID_PARAMETER.format("pageNumber"));
		CallableStatement callableStatement = null;
		try	{
			/*
	        IN I_SOC_CSOCCSOC CHAR(5),
		    IN I_APC_CAPCSIGL CHAR(2),
	  	    IN I_UTE_CUTECUTE CHAR(5),
	  	    IN I_CBP_KANEKENT_BEN CHAR(10),
	  	    IN I_CBP_GCBPGDAT CHAR(10),
	  	    IN I_CBP_NCBPANNO_DA CHAR(4),
	  	    IN I_CBP_NCBPANNO_A CHAR(4),
			*/
			callableStatement = prepareCall(Routines.CBP_DOLIST.routine());
			callableStatement.setString(1, associazione.getUser().getCompany().getCompanyCode());
			callableStatement.setString(2, associazione.getAnagBeneficiario().getAnagProvCom().getCodiceProvincia());
			callableStatement.setString(3, associazione.getUser().getUserCode());
			callableStatement.setString(4, associazione.getAnagBeneficiario().getChiaveEnte());		
			callableStatement.setString(5, associazione.getDataValidita());		
			callableStatement.setString(6, associazione.getAnnoRifDa());		
			callableStatement.setString(7, associazione.getAnnoRifA());		
			callableStatement.setString(8, ordine);
			callableStatement.setInt(9, rowsPerPage);
			callableStatement.setInt(10, pageNumber);
			/* we register row start */
			callableStatement.registerOutParameter(11, Types.INTEGER);
			/* we register row end */
			callableStatement.registerOutParameter(12, Types.INTEGER);
			/* we register total rows */
			callableStatement.registerOutParameter(13, Types.INTEGER);
			/* we register total pages */
			callableStatement.registerOutParameter(14, Types.SMALLINT);
			/* we execute procedure */
			if (callableStatement.execute()) {
				this.loadWebRowSets(callableStatement);
				/* we register page info */
				registerPageInfo(rowsPerPage, pageNumber, callableStatement.getInt(11), callableStatement.getInt(12), 
								 callableStatement.getInt(13), callableStatement.getInt(14));
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
			if(callableStatement != null) {
				try {
					callableStatement.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			//fine LP PG21XX04 Leak
		}
	}

	public int doSave(AssocBen associazione,String codOp) throws DaoException {
		CallableStatement callableStatement = null;
		int code = 0;
		try	{
			if (codOp.equals(TypeRequest.EDIT_SCOPE.scope())) {
				callableStatement = prepareCall(Routines.CBP_DOUPDATE.routine());
			} else {
				callableStatement = prepareCall(Routines.CBP_DOINSERT.routine());
			}
			associazione.save(callableStatement);
			callableStatement.execute();
			code = callableStatement.getInt(11);
		} catch (SQLException x) {
			if (x.getErrorCode()== -803 || x.getErrorCode()== 1062) 
				throw new DaoException(x.getErrorCode(), Messages.UNIQUE_CONSTRAINT_VIOLATION.format("Associazione"));
			else
				throw new DaoException(x.getErrorCode(),x.getMessage());
		//inizio LP 20240811  - PGNTCORE-24 	
		} catch (UndeclaredThrowableException x) {
			DaoException.makeIfDuplicateKeyError(x, Messages.UNIQUE_CONSTRAINT_VIOLATION.format("Associazione"));
		//fine LP 20240811  - PGNTCORE-24 	
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
		return code;
	}
	
	public int doDelete(AssocBen associazione) throws DaoException {
		int code = 0;
		CallableStatement callableStatement = null;
		try	{
			callableStatement = prepareCall(Routines.CBP_DODELETE.routine());
			/*	IN I_CBP_CSOCCSOC CHAR(5),
			IN I_CBP_CUTECUTE CHAR(5),
			IN I_CBP_KANEKENT_BEN CHAR(10),
			IN I_CBP_GCBPGDAT CHAR(10),
			OUT O_CODE INTEGER
			*/
			associazione.delete(callableStatement);
			callableStatement.execute();
			code = callableStatement.getInt(5);
		} catch (SQLException x) {
			throw new DaoException(x.getErrorCode(),x.getMessage(),x);
		} catch (IllegalArgumentException x) {
			throw new DaoException(Integer.parseInt(Messages.ILL_ARG_ERR_CODE.format()),x.getMessage(),x);
		} catch (HelperException x) {
			throw new DaoException(Integer.parseInt(Messages.HELPER_ERR_CODE.format()),x.getMessage(),x);
		} finally {
			//inizio LP PG21XX04 Leak
			//closeConnection(callableStatement);
			if(callableStatement != null) {
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
	
//	private void closeConnection(CallableStatement callableStatement)
//	{
//		if (callableStatement != null)
//			DAOHelper.closeIgnoringException(callableStatement);
//	}

}