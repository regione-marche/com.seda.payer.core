package com.seda.payer.core.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import com.seda.data.helper.HelperException;
import com.seda.payer.commons.bean.TypeRequest;
import com.seda.payer.core.bean.ConfigBancaDati;
import com.seda.payer.core.exception.DaoException;
import com.seda.payer.core.handler.BaseDaoHandler;
import com.seda.payer.core.messages.Messages;


public class ConfigBancaDatiDao extends BaseDaoHandler {
	
	public ConfigBancaDatiDao(Connection connection, String schema) {
		super(connection, schema);
	}

	public ConfigBancaDati doDetail(String userCode, long idBancaDati) throws DaoException {
		//inizio LP PG21XX04 Leak
		CallableStatement callableStatement = null;
		ResultSet data = null;
		//fine LP PG21XX04 Leak
		try	{
			//inizio LP PG21XX04 Leak
			//CallableStatement callableStatement = prepareCall(Routines.CBD_DODETAIL.routine());
			callableStatement = prepareCall(Routines.CBD_DODETAIL.routine());
			//fine LP PG21XX04 Leak
			callableStatement.setString(1, userCode);
			callableStatement.setLong(2, idBancaDati);
			if (callableStatement.execute()) {
				//inizio LP PG21XX04 Leak
				//ResultSet data = callableStatement.getResultSet();
				data = callableStatement.getResultSet();
				//fine LP PG21XX04 Leak
				if (data.next())
					return new ConfigBancaDati(data);
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

	public void doRowSets(ConfigBancaDati configBancaDati) throws DaoException {
		rowSets(configBancaDati.getCompanyCode(),configBancaDati.getCuteCute(),configBancaDati.getIdBancaDati(), configBancaDati.getName(), configBancaDati.getUrl(), 0, 0);
	}

	public void doRowSets(ConfigBancaDati configBancaDati, int rowsPerPage, int pageNumber) throws DaoException {

			if (rowsPerPage <= 0)
				throw new IllegalArgumentException(Messages.INVALID_PARAMETER.format("rowsPerPage"));

			if (pageNumber <= 0)
				throw new IllegalArgumentException(Messages.INVALID_PARAMETER.format("pageNumber"));
 
			rowSets(configBancaDati.getCompanyCode(),configBancaDati.getCuteCute(),configBancaDati.getIdBancaDati(), configBancaDati.getName(), configBancaDati.getUrl(), rowsPerPage, pageNumber);

	}
//	private void rowSets(String companyCode, String userCode, long idBancaDati,int rowsPerPage, int pageNumber) throws DaoException {
	private void rowSets(String companyCode, String userCode, long idBancaDati, String name, String url, int rowsPerPage, int pageNumber) throws DaoException {
		//inizio LP PG21XX04 Leak
		CallableStatement callableStatement = null;
		//fine LP PG21XX04 Leak
		try	{
			//inizio LP PG21XX04 Leak
			//CallableStatement callableStatement = prepareCall(Routines.CBD_DOLIST.routine());
			callableStatement = prepareCall(Routines.CBD_DOLIST.routine());
			//fine LP PG21XX04 Leak
			callableStatement.setString(1, companyCode);
			callableStatement.setString(2, userCode);
			callableStatement.setLong(3, idBancaDati);
			callableStatement.setString(4, name);
			callableStatement.setString(5, url);
			callableStatement.setString(6, "");
			callableStatement.setInt(7, rowsPerPage);
			callableStatement.setInt(8, pageNumber);
			/* we register row start */
			callableStatement.registerOutParameter(9, Types.INTEGER);
			/* we register row end */
			callableStatement.registerOutParameter(10, Types.INTEGER);
			/* we register total rows */
			callableStatement.registerOutParameter(11, Types.INTEGER);
			/* we register total pages */
			callableStatement.registerOutParameter(12, Types.SMALLINT);
			/* we execute procedure */
			if (callableStatement.execute()) {
				this.loadWebRowSets(callableStatement);
				/* we register page info */
				registerPageInfo(rowsPerPage, pageNumber, callableStatement.getInt(9), callableStatement.getInt(10), 
								 callableStatement.getInt(11), callableStatement.getInt(12));
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

	public void doSave(ConfigBancaDati configBancaDati,String codOp) throws DaoException {
		CallableStatement callableStatement=null;
		try	{
			if (configBancaDati.getCompanyCode() == null || configBancaDati.getCompanyCode().length() == 0)
				throw new IllegalArgumentException(Messages.INVALID_PARAMETER.format("configBancaDati.companyCode"));
			if (configBancaDati.getCuteCute() == null || configBancaDati.getCuteCute().length() == 0)
				throw new IllegalArgumentException(Messages.INVALID_PARAMETER.format("configBancaDati.userCode"));
//			if ( configBancaDati.getIdBancaDati().length() == 0)
//				throw new IllegalArgumentException(Messages.INVALID_PARAMETER.format("configBancaDati.chiaveEnte"));
			ConfigBancaDati data = doDetail(configBancaDati.getCuteCute(), configBancaDati.getIdBancaDati());
			if ((data != null) && codOp!=null && codOp.compareTo(TypeRequest.ADD_SCOPE.scope())==0) throw new IllegalArgumentException(Messages.INVALID_PARAMETER.format("confBancaDati.saveadd.error"));
			if (data != null) {
			    callableStatement = prepareCall(Routines.CBD_DOUPDATE.routine());
			    configBancaDati.update(callableStatement);
			}
			else {
				callableStatement = prepareCall(Routines.CBD_DOINSERT.routine());
				configBancaDati.save(callableStatement);
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

	public void doDelete(ConfigBancaDati configBancaDati) throws DaoException {
		//inizio LP PG21XX04 Leak
		CallableStatement callableStatement = null;
		//fine LP PG21XX04 Leak
		try	{
			//inizio LP PG21XX04 Leak
			//CallableStatement callableStatement = prepareCall(Routines.CBD_DODELETE.routine());
			callableStatement = prepareCall(Routines.CBD_DODELETE.routine());
			//fine LP PG21XX04 Leak
			if (configBancaDati.getCuteCute() == null || configBancaDati.getCuteCute().length() == 0)
				throw new IllegalArgumentException(Messages.INVALID_PARAMETER.format("configBancaDati.userCode"));
//			if (configBancaDati.getIdBancaDati().length() == 0)
//				throw new IllegalArgumentException(Messages.INVALID_PARAMETER.format("configBancaDati.chiaveEnte"));
	
			callableStatement.setString(1, configBancaDati.getCuteCute());
			callableStatement.setLong(2, configBancaDati.getIdBancaDati());
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