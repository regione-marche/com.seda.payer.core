package com.seda.payer.core.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

import com.seda.payer.commons.bean.TypeRequest;
import com.seda.payer.core.bean.ApplProf;
import com.seda.payer.core.exception.DaoException;
import com.seda.payer.core.handler.BaseDaoHandler;
import com.seda.payer.core.messages.Messages;
import com.seda.data.helper.HelperException;
/**
 * 
 * @author mmontisano
 *
 */
public class ApplProfDao extends BaseDaoHandler {
	
	public ApplProfDao(Connection connection, String schema) {
		super(connection, schema);
	}

	public ApplProf doDetail(String chiaveProfilo) throws DaoException {
		//inizio LP PG21XX04 Leak
		CallableStatement callableStatement = null;
		ResultSet data =  null;
		//fine LP PG21XX04 Leak
		try	{
			//inizio LP PG21XX04 Leak
			//CallableStatement callableStatement = prepareCall(Routines.PRF_DODETAIL.routine());
			callableStatement = prepareCall(Routines.PRF_DODETAIL.routine());
			//fine LP PG21XX04 Leak
			callableStatement.setString(1, chiaveProfilo);
			if (callableStatement.execute()) {
				//inizio LP PG21XX04 Leak
				//ResultSet data = callableStatement.getResultSet();
				data = callableStatement.getResultSet();
				//fine LP PG21XX04 Leak
				if (data.next())
					return new ApplProf(data);
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
			if (data != null) {
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

	public void doRowSets(ApplProf applProf) throws DaoException {
		rowSets(applProf, 0, 0);
	}

	public void doRowSets(ApplProf applProf, int rowsPerPage, int pageNumber) throws DaoException {
		
			if (rowsPerPage <= 0)
				throw new IllegalArgumentException(Messages.INVALID_PARAMETER.format("rowsPerPage"));

			if (pageNumber <= 0)
				throw new IllegalArgumentException(Messages.INVALID_PARAMETER.format("pageNumber"));
 
			rowSets(applProf, rowsPerPage, pageNumber);

	}
	public void rowSets(ApplProf applProf, int rowsPerPage, int pageNumber) throws DaoException {
		//inizio LP PG21XX04 Leak
		CallableStatement callableStatement = null;
		//fine LP PG21XX04 Leak
		try	{
			//inizio LP PG21XX04 Leak
			//CallableStatement callableStatement = prepareCall(Routines.PRF_DOLIST.routine());
			callableStatement = prepareCall(Routines.PRF_DOLIST.routine());
			//fine LP PG21XX04 Leak
			callableStatement.setString(1, applProf.getChiaveProfilo());
			callableStatement.setString(2, applProf.getCodiceProfilo());
			callableStatement.setString(3, applProf.getDescrizioneProfilo());			

			callableStatement.setString(4, "");
			callableStatement.setInt(5, rowsPerPage);
			callableStatement.setInt(6, pageNumber);
			/* we register row start */
			callableStatement.registerOutParameter(7, Types.INTEGER);
			/* we register row end */
			callableStatement.registerOutParameter(8, Types.INTEGER);
			/* we register total rows */
			callableStatement.registerOutParameter(9, Types.INTEGER);
			/* we register total pages */
			callableStatement.registerOutParameter(10, Types.SMALLINT);
			/* we execute procedure */
			if (callableStatement.execute()) {
				this.loadWebRowSets(callableStatement);
				/* we register page info */
				registerPageInfo(rowsPerPage, pageNumber, callableStatement.getInt(7), callableStatement.getInt(8), 
								 callableStatement.getInt(9), callableStatement.getInt(10));
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

	public void doSave(ApplProf applProf,String codOp) throws DaoException {
	    CallableStatement callableStatement=null;
		try	{
			if ((applProf.getChiaveProfilo() == null || applProf.getChiaveProfilo().length() == 0) && codOp.compareTo(TypeRequest.EDIT_SCOPE.scope())==0)
				throw new IllegalArgumentException(Messages.INVALID_PARAMETER.format("applProf.chiaveProfilo"));
		
			ApplProf data = doDetail(applProf.getChiaveProfilo());
			if ((data != null) && codOp!=null && codOp.compareTo(TypeRequest.ADD_SCOPE.scope())==0) throw new IllegalArgumentException(Messages.INVALID_PARAMETER.format("applProf.saveadd.error"));
			if (data != null) {
				callableStatement = prepareCall(Routines.PRF_DOUPDATE.routine());
				applProf.update(callableStatement);
			}
			else {
				callableStatement = prepareCall(Routines.PRF_DOINSERT.routine());
				applProf.save(callableStatement);
			}
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

	public void doDelete(ApplProf applProf) throws DaoException {
		//inizio LP PG21XX04 Leak
		CallableStatement callableStatement = null;
		//fine LP PG21XX04 Leak
		try	{
			//inizio LP PG21XX04 Leak
			//CallableStatement callableStatement = prepareCall(Routines.PRF_DODELETE.routine());
			callableStatement = prepareCall(Routines.PRF_DODELETE.routine());
			//fine LP PG21XX04 Leak
			if ((applProf.getChiaveProfilo() == null || applProf.getChiaveProfilo().length() == 0))
				throw new IllegalArgumentException(Messages.INVALID_PARAMETER.format("applProf.chiaveProfilo"));
			
			callableStatement.setString(1, applProf.getChiaveProfilo());
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