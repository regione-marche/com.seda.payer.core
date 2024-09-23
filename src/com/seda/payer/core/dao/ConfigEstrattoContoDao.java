package com.seda.payer.core.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import com.seda.data.helper.HelperException;
import com.seda.payer.commons.bean.TypeRequest;
import com.seda.payer.core.bean.ConfigEstrattoConto;
import com.seda.payer.core.exception.DaoException;
import com.seda.payer.core.handler.BaseDaoHandler;
import com.seda.payer.core.messages.Messages;


public class ConfigEstrattoContoDao extends BaseDaoHandler {
	
	public ConfigEstrattoContoDao(Connection connection, String schema) {
		super(connection, schema);
	}

	public ConfigEstrattoConto doDetail(String userCode, String chiaveEnte) throws DaoException {
		//inizio LP PG21XX04 Leak
		CallableStatement callableStatement = null;
		ResultSet data = null;
		//fine LP PG21XX04 Leak
		try	{
			//inizio LP PG21XX04 Leak
			//CallableStatement callableStatement = prepareCall(Routines.CEC_DODETAIL.routine());
			callableStatement = prepareCall(Routines.CEC_DODETAIL.routine());
			//fine LP PG21XX04 Leak
			callableStatement.setString(1, userCode);
			callableStatement.setString(2, chiaveEnte);
			if (callableStatement.execute()) {
				//inizio LP PG21XX04 Leak
				//ResultSet data = callableStatement.getResultSet();
				data = callableStatement.getResultSet();
				//fine LP PG21XX04 Leak
				if (data.next())
					return new ConfigEstrattoConto(data);
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

	public void doRowSets(ConfigEstrattoConto configEstrattoConto) throws DaoException {
		rowSets(configEstrattoConto.getEnte().getUser().getCompany().getCompanyCode(),configEstrattoConto.getEnte().getUser().getUserCode(),configEstrattoConto.getEnte().getAnagEnte().getChiaveEnte(), 0, 0);
	}

	public void doRowSets(ConfigEstrattoConto configEstrattoConto, int rowsPerPage, int pageNumber) throws DaoException {
		if (rowsPerPage <= 0)
			throw new IllegalArgumentException(Messages.INVALID_PARAMETER.format("rowsPerPage"));
		if (pageNumber <= 0)
			throw new IllegalArgumentException(Messages.INVALID_PARAMETER.format("pageNumber"));
		rowSets(configEstrattoConto.getEnte().getUser().getCompany().getCompanyCode(),configEstrattoConto.getEnte().getUser().getUserCode(),configEstrattoConto.getEnte().getAnagEnte().getChiaveEnte() , rowsPerPage, pageNumber);
	}

	private void rowSets(String companyCode, String userCode, String chiaveEnte,int rowsPerPage, int pageNumber) throws DaoException {
		//inizio LP PG21XX04 Leak
		CallableStatement callableStatement = null;
		//fine LP PG21XX04 Leak
		try	{
			//inizio LP PG21XX04 Leak
			//CallableStatement callableStatement = prepareCall(Routines.CEC_DOLIST.routine());
			callableStatement = prepareCall(Routines.CEC_DOLIST.routine());
			//fine LP PG21XX04 Leak
			callableStatement.setString(1, companyCode);
			callableStatement.setString(2, userCode);
			callableStatement.setString(3, chiaveEnte);
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

	public void doSave(ConfigEstrattoConto configEstrattoConto,String codOp) throws DaoException {
		CallableStatement callableStatement=null;
		try	{
			if (configEstrattoConto.getEnte().getUser().getCompany().getCompanyCode() == null || configEstrattoConto.getEnte().getUser().getCompany().getCompanyCode().length() == 0)
				throw new IllegalArgumentException(Messages.INVALID_PARAMETER.format("configEstrattoConto.companyCode"));
			if (configEstrattoConto.getEnte().getUser().getUserCode() == null || configEstrattoConto.getEnte().getUser().getUserCode().length() == 0)
				throw new IllegalArgumentException(Messages.INVALID_PARAMETER.format("configEstrattoConto.userCode"));
			if (configEstrattoConto.getEnte().getAnagEnte().getChiaveEnte() == null || configEstrattoConto.getEnte().getAnagEnte().getChiaveEnte().length() == 0)
				throw new IllegalArgumentException(Messages.INVALID_PARAMETER.format("configEstrattoConto.chiaveEnte"));
			ConfigEstrattoConto data = doDetail(configEstrattoConto.getEnte().getUser().getUserCode(), configEstrattoConto.getEnte().getAnagEnte().getChiaveEnte());
			if ((data != null) && codOp!=null && codOp.compareTo(TypeRequest.ADD_SCOPE.scope())==0) throw new IllegalArgumentException(Messages.INVALID_PARAMETER.format("confEstrattoConto.saveadd.error"));
			if (data != null)	
			    callableStatement = prepareCall(Routines.CEC_DOUPDATE.routine());
			else callableStatement = prepareCall(Routines.CEC_DOINSERT.routine());
			
			configEstrattoConto.save(callableStatement);
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

	public void doDelete(ConfigEstrattoConto configEstrattoConto) throws DaoException {
		//inizio LP PG21XX04 Leak
		CallableStatement callableStatement = null;
		//fine LP PG21XX04 Leak
		try	{
			//inizio LP PG21XX04 Leak
			//CallableStatement callableStatement = prepareCall(Routines.CEC_DODELETE.routine());
			callableStatement = prepareCall(Routines.CEC_DODELETE.routine());
			//fine LP PG21XX04 Leak
			if (configEstrattoConto.getEnte().getUser().getUserCode() == null || configEstrattoConto.getEnte().getUser().getUserCode().length() == 0)
				throw new IllegalArgumentException(Messages.INVALID_PARAMETER.format("configEstrattoConto.userCode"));
			if (configEstrattoConto.getEnte().getAnagEnte().getChiaveEnte() == null || configEstrattoConto.getEnte().getAnagEnte().getChiaveEnte().length() == 0)
				throw new IllegalArgumentException(Messages.INVALID_PARAMETER.format("configEstrattoConto.chiaveEnte"));
	
			callableStatement.setString(1, configEstrattoConto.getEnte().getUser().getUserCode());
			callableStatement.setString(2, configEstrattoConto.getEnte().getAnagEnte().getChiaveEnte());
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