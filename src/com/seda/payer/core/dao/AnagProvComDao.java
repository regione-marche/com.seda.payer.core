package com.seda.payer.core.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

import com.seda.payer.commons.bean.TypeRequest;
import com.seda.payer.core.bean.AnagProvCom;
import com.seda.payer.core.exception.DaoException;
import com.seda.payer.core.handler.BaseDaoHandler;
import com.seda.payer.core.messages.Messages;
import com.seda.data.helper.HelperException;
/**
 * 
 * @author mmontisano
 *
 */
public class AnagProvComDao extends BaseDaoHandler {
	
	public AnagProvComDao(Connection connection, String schema) {
		super(connection, schema);
	}

	public AnagProvCom doDetail(String codiceBelfiore) throws DaoException {
		//inizio LP PG21XX04 Leak
		CallableStatement callableStatement = null;
		ResultSet data = null;
		//fine LP PG21XX04 Leak
		try	{
			//inizio LP PG21XX04 Leak
			//CallableStatement callableStatement = prepareCall(Routines.APC_DODETAIL.routine());
			callableStatement = prepareCall(Routines.APC_DODETAIL.routine());
			//fine LP PG21XX04 Leak
			callableStatement.setString(1,codiceBelfiore);
			if (callableStatement.execute()) {
				//inizio LP PG21XX04 Leak
				//ResultSet data = callableStatement.getResultSet();
				data = callableStatement.getResultSet();
				//fine LP PG21XX04 Leak
				if (data.next())
					return new AnagProvCom(data);
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
	
	public void doRowSets(AnagProvCom anag) throws DaoException {
		rowSets(anag, 0, 0);
	}

	public void doRowSets(AnagProvCom anag, int rowsPerPage, int pageNumber) throws DaoException {

			if (rowsPerPage <= 0)
				throw new IllegalArgumentException(Messages.INVALID_PARAMETER.format("rowsPerPage"));

			if (pageNumber <= 0)
				throw new IllegalArgumentException(Messages.INVALID_PARAMETER.format("pageNumber"));
 
			rowSets(anag, rowsPerPage, pageNumber);

	}
	
	public void rowSets(AnagProvCom anag, int rowsPerPage, int pageNumber) throws DaoException {
		//inizio LP PG21XX04 Leak
		CallableStatement callableStatement = null;
		//fine LP PG21XX04 Leak
		try	{
			//inizio LP PG21XX04 Leak
			//CallableStatement callableStatement = prepareCall(Routines.APC_DOLIST.routine());
			callableStatement = prepareCall(Routines.APC_DOLIST.routine());
			//fine LP PG21XX04 Leak
			//callableStatement.setString(1, anag.getCompany().getCompanyCode());
			callableStatement.setString(1, anag.getCodiceBelfiore());
			callableStatement.setString(2, anag.getCodiceProvincia());
			callableStatement.setString(3, anag.getCodiceComune());
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
		}
		//inizio LP PG21XX04 Leak
		finally {
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

	public void doSave(AnagProvCom anag,String codOp) throws DaoException {
		CallableStatement callableStatement=null;
		try	{
			if (anag.getCodiceBelfiore() == null || anag.getCodiceBelfiore().length() == 0)
				throw new IllegalArgumentException(Messages.INVALID_PARAMETER.format("anagProvCom.codiceBelfiore"));

			/*if (anag.getCompany() == null || anag.getCompany().getCompanyCode() == null || anag.getCompany().getCompanyCode().length() == 0)
				throw new IllegalArgumentException(Messages.INVALID_PARAMETER.format("anagProvCom.company.companyCode"));*/

			AnagProvCom data = doDetail(anag.getCodiceBelfiore());
			if ((data != null) && codOp!=null && codOp.compareTo(TypeRequest.ADD_SCOPE.scope())==0) throw new IllegalArgumentException(Messages.INVALID_PARAMETER.format("anagProvCom.saveadd.error"));
			if (data != null) 
				callableStatement = prepareCall(Routines.APC_DOUPDATE.routine());
			else callableStatement = prepareCall(Routines.APC_DOINSERT.routine());

			anag.save(callableStatement);
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

	public void doDelete(AnagProvCom anag) throws DaoException {
		//inizio LP PG21XX04 Leak
		CallableStatement callableStatement = null;
		//fine LP PG21XX04 Leak
		try	{
			//inizio LP PG21XX04 Leak
			//CallableStatement callableStatement = prepareCall(Routines.APC_DODELETE.routine());
			callableStatement = prepareCall(Routines.APC_DODELETE.routine());
			//fine LP PG21XX04 Leak
			if (anag.getCodiceBelfiore() == null || anag.getCodiceBelfiore().length() == 0)
				throw new IllegalArgumentException(Messages.INVALID_PARAMETER.format("anagProvCom.codiceBelfiore"));

			/*if (anag.getCompany() == null || anag.getCompany().getCompanyCode() == null || anag.getCompany().getCompanyCode().length() == 0)
				throw new IllegalArgumentException(Messages.INVALID_PARAMETER.format("anagProvCom.company.companyCode"));
*/
			callableStatement.setString(1, anag.getCodiceBelfiore());
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