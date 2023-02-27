package com.seda.payer.core.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

import com.seda.data.helper.HelperException;
import com.seda.payer.commons.bean.TypeRequest;
import com.seda.payer.core.bean.CostiNotifica;
import com.seda.payer.core.exception.DaoException;
import com.seda.payer.core.handler.BaseDaoHandler;
import com.seda.payer.core.messages.Messages;

public class CostiNotificaDao extends BaseDaoHandler {
	
	public CostiNotificaDao(Connection connection, String schema) {
		super(connection, schema);
	}

	public CostiNotifica doDetail(String companyCode, String userCode) throws DaoException {
		//inizio LP PG21XX04 Leak
		CallableStatement callableStatement = null;
		ResultSet data = null;
		//fine LP PG21XX04 Leak
		try	{
			//inizio LP PG21XX04 Leak
			//CallableStatement callableStatement = prepareCall(Routines.CSN_DODETAIL.routine());
			callableStatement = prepareCall(Routines.CSN_DODETAIL.routine());
			//fine LP PG21XX04 Leak
			callableStatement.setString(1, companyCode);
			callableStatement.setString(2, userCode);
			if (callableStatement.execute()) {
				//inizio LP PG21XX04 Leak
				//ResultSet data = callableStatement.getResultSet();
				data = callableStatement.getResultSet();
				//fine LP PG21XX04 Leak
				if (data.next())
					return new CostiNotifica(data);
			}
			return null;
		} catch (SQLException x) {
			throw new DaoException(x);
		} catch (IllegalArgumentException x) {
			throw new DaoException(x);
		} catch (HelperException x) {
			throw new DaoException(x);
		}
		//inizio LP PG21XX04 Leak
		finally {
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
		}
		//fine LP PG21XX04 Leak
	}

	public void doRowSets(CostiNotifica costiNotifica) throws DaoException {
		rowSets(costiNotifica, 0, 0);
	}

	public void doRowSets(CostiNotifica costiNotifica, int rowsPerPage, int pageNumber) throws DaoException {

			if (rowsPerPage <= 0)
				throw new IllegalArgumentException(Messages.INVALID_PARAMETER.format("rowsPerPage"));

			if (pageNumber <= 0)
				throw new IllegalArgumentException(Messages.INVALID_PARAMETER.format("pageNumber"));
 
			rowSets(costiNotifica, rowsPerPage, pageNumber);

	}

	public void rowSets(CostiNotifica costiNotifica, int rowsPerPage, int pageNumber) throws DaoException {
		//inizio LP PG21XX04 Leak
		CallableStatement callableStatement = null;
		//fine LP PG21XX04 Leak
		try	{
			//inizio LP PG21XX04 Leak
			//CallableStatement callableStatement = prepareCall(Routines.CSN_DOLIST.routine());
			callableStatement = prepareCall(Routines.CSN_DOLIST.routine());
			//fine LP PG21XX04 Leak
			callableStatement.setString(1,costiNotifica.getUser().getCompany().getCompanyCode());
			callableStatement.setString(2,costiNotifica.getUser().getUserCode());
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
		}
		//inizio LP PG21XX04 Leak
		finally {
			if (callableStatement != null) {
				try {
					callableStatement.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		//fine LP PG21XX04 Leak
	}

	public void doSave(CostiNotifica costiNotifica, String codOp) throws DaoException {
		CallableStatement callableStatement = null;
		try	{
			if (costiNotifica.getUser().getCompany().getCompanyCode() == null || costiNotifica.getUser().getCompany().getCompanyCode().length()==0)
				throw new IllegalArgumentException(Messages.INVALID_PARAMETER.format("costiNotifica.companyCode"));
			if (costiNotifica.getUser().getUserCode() == null ||costiNotifica.getUser().getUserCode().length() ==0)
				throw new IllegalArgumentException(Messages.INVALID_PARAMETER.format("costiNotifica.chiaveCanalePagamento"));						
			CostiNotifica data = doDetail(costiNotifica.getUser().getCompany().getCompanyCode(), costiNotifica.getUser().getUserCode());
			if (data != null && codOp!=null && codOp.compareTo(TypeRequest.ADD_SCOPE.scope())==0)  throw new IllegalArgumentException(Messages.INVALID_PARAMETER.format("costiNotifica.saveadd.error"));
			if (data != null) {
				callableStatement = prepareCall(Routines.CSN_DOUPDATE.routine());
				costiNotifica.update(callableStatement);
			}
			else {
				callableStatement = prepareCall(Routines.CSN_DOINSERT.routine());
				costiNotifica.save(callableStatement);
			}
			callableStatement.execute();
			//commit(); 
		} catch (SQLException x) {
			throw new DaoException(x);
		} catch (IllegalArgumentException x) {
			throw new DaoException(x);
		} catch (HelperException x) {
			throw new DaoException(x);
		}
		//inizio LP PG21XX04 Leak
		finally {
			if (callableStatement != null) {
				try {
					callableStatement.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		//fine LP PG21XX04 Leak
	}

	public void doDelete(CostiNotifica costiNotifica) throws DaoException {
		//inizio LP PG21XX04 Leak
		CallableStatement callableStatement = null;
		//fine LP PG21XX04 Leak
		try	{
			//inizio LP PG21XX04 Leak
			//CallableStatement callableStatement = prepareCall(Routines.CSN_DODELETE.routine());
			callableStatement = prepareCall(Routines.CSN_DODELETE.routine());
			//fine LP PG21XX04 Leak
			if (costiNotifica.getUser().getCompany().getCompanyCode() == null)
				throw new IllegalArgumentException(Messages.INVALID_PARAMETER.format("costiNotifica.companyCode"));
			if (costiNotifica.getUser().getUserCode() == null)
				throw new IllegalArgumentException(Messages.INVALID_PARAMETER.format("coostiNotifica.chiaveCanalePagamento"));

			callableStatement.setString(1, costiNotifica.getUser().getCompany().getCompanyCode());
			callableStatement.setString(2, costiNotifica.getUser().getUserCode());
			callableStatement.execute();
			//commit();
			
			
		} catch (SQLException x) {
			throw new DaoException(x.getErrorCode(),x.getMessage(),x);
		} catch (IllegalArgumentException x) {
			throw new DaoException(Integer.parseInt(Messages.ILL_ARG_ERR_CODE.format()),x.getMessage(),x);
		} catch (HelperException x) {
			throw new DaoException(Integer.parseInt(Messages.HELPER_ERR_CODE.format()),x.getMessage(),x);
		}
		//inizio LP PG21XX04 Leak
		finally {
			if (callableStatement != null) {
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