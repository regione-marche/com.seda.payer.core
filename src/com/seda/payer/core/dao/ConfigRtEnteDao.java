package com.seda.payer.core.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import com.seda.data.helper.HelperException;
import com.seda.payer.commons.bean.TypeRequest;
import com.seda.payer.core.bean.ConfigRtEnte;
import com.seda.payer.core.exception.DaoException;
import com.seda.payer.core.handler.BaseDaoHandler;
import com.seda.payer.core.messages.Messages;


public class ConfigRtEnteDao extends BaseDaoHandler {
	
	public ConfigRtEnteDao(Connection connection, String schema) {
		super(connection, schema);
	}

	public ConfigRtEnte doDetail(String codSocieta, String userCode, String chiaveEnte, String codiceIdpa) throws DaoException {
		//inizio LP PG21XX04 Leak
		CallableStatement callableStatement = null;
		ResultSet data = null;
		//fine LP PG21XX04 Leak
		try	{
			//inizio LP PG21XX04 Leak
			//CallableStatement callableStatement = prepareCall(Routines.PYCFTSP_SEL.routine());
			callableStatement = prepareCall(Routines.PYCFTSP_SEL.routine());
			//fine LP PG21XX04 Leak
			callableStatement.setString(1, codSocieta);
			callableStatement.setString(2, userCode);
			callableStatement.setString(3, chiaveEnte);
			callableStatement.setString(4, codiceIdpa);
			if (callableStatement.execute()) {
				//inizio LP PG21XX04 Leak
				//ResultSet data = callableStatement.getResultSet();
				data = callableStatement.getResultSet();
				//fine LP PG21XX04 Leak
				if (data.next())
					return new ConfigRtEnte(data);
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

	public void doRowSets(ConfigRtEnte configRtEnte) throws DaoException {
		rowSets(configRtEnte.getCodiceSocieta(),configRtEnte.getCuteCute(),configRtEnte.getChiaveEnte(), configRtEnte.getCodiceIdpaEnte(), 0, 0);
	}

	public void doRowSets(ConfigRtEnte configRtEnte, int rowsPerPage, int pageNumber) throws DaoException {
		if (rowsPerPage <= 0)
			throw new IllegalArgumentException(Messages.INVALID_PARAMETER.format("rowsPerPage"));
		if (pageNumber <= 0)
			throw new IllegalArgumentException(Messages.INVALID_PARAMETER.format("pageNumber"));
		rowSets(configRtEnte.getCodiceSocieta(), configRtEnte.getCuteCute(),configRtEnte.getChiaveEnte(), configRtEnte.getCodiceIdpaEnte(), rowsPerPage, pageNumber);
	}

	private void rowSets(String companyCode, String userCode, String chiaveEnte, String codIdpa, int rowsPerPage, int pageNumber) throws DaoException {
		//inizio LP PG21XX04 Leak
		CallableStatement callableStatement = null;
		//fine LP PG21XX04 Leak
		try	{
			//inizio LP PG21XX04 Leak
			//CallableStatement callableStatement = prepareCall(Routines.PYCFTSP_LST.routine());
			callableStatement = prepareCall(Routines.PYCFTSP_LST.routine());
			//fine LP PG21XX04 Leak
			callableStatement.setString(1, companyCode);
			callableStatement.setString(2, userCode);
			callableStatement.setString(3, chiaveEnte);
			callableStatement.setString(4, codIdpa);
			callableStatement.setString(5, "");
			callableStatement.setInt(6, rowsPerPage);
			callableStatement.setInt(7, pageNumber);
			/* we register row start */
			callableStatement.registerOutParameter(8, Types.INTEGER);
			/* we register row end */
			callableStatement.registerOutParameter(9, Types.INTEGER);
			/* we register total rows */
			callableStatement.registerOutParameter(10, Types.INTEGER);
			/* we register total pages */
			callableStatement.registerOutParameter(11, Types.SMALLINT);
			/* we execute procedure */
			if (callableStatement.execute()) {
				this.loadWebRowSets(callableStatement);
				/* we register page info */
				registerPageInfo(rowsPerPage, pageNumber, callableStatement.getInt(8), callableStatement.getInt(9), 
								 callableStatement.getInt(10), callableStatement.getInt(11));
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

	public void doSave(ConfigRtEnte configRtEnte,String codOp) throws DaoException {
		CallableStatement callableStatement=null;
		try	{
			if (configRtEnte.getCodiceSocieta() == null || configRtEnte.getCodiceSocieta().length() == 0)
				throw new IllegalArgumentException(Messages.INVALID_PARAMETER.format("configRtEnte.companyCode"));
			if (configRtEnte.getCuteCute() == null || configRtEnte.getCuteCute().length() == 0)
				throw new IllegalArgumentException(Messages.INVALID_PARAMETER.format("configRtEnte.userCode"));
			if (configRtEnte.getChiaveEnte() == null || configRtEnte.getChiaveEnte().length() == 0)
				throw new IllegalArgumentException(Messages.INVALID_PARAMETER.format("configRtEnte.chiaveEnte"));
			ConfigRtEnte data = doDetail(configRtEnte.getCodiceSocieta(), configRtEnte.getCuteCute(), configRtEnte.getChiaveEnte(), configRtEnte.getCodiceIdpaEnte());
			if ((data != null) && codOp!=null && codOp.compareTo(TypeRequest.ADD_SCOPE.scope())==0) throw new IllegalArgumentException(Messages.INVALID_PARAMETER.format("confRtEnte.saveadd.error"));
			if (data != null)	
			    callableStatement = prepareCall(Routines.PYCFTSP_UPD.routine());
			else callableStatement = prepareCall(Routines.PYCFTSP_INS.routine());
			configRtEnte.save(callableStatement);
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

	public void doDelete(ConfigRtEnte configRtEnte) throws DaoException {
		//inizio LP PG21XX04 Leak
		CallableStatement callableStatement = null;
		//fine LP PG21XX04 Leak
		try	{
			//inizio LP PG21XX04 Leak
			//CallableStatement callableStatement = prepareCall(Routines.PYCFTSP_DEL.routine());
			callableStatement = prepareCall(Routines.PYCFTSP_DEL.routine());
			//fine LP PG21XX04 Leak
			if (configRtEnte.getCodiceSocieta() == null || configRtEnte.getCodiceSocieta().length() == 0)
				throw new IllegalArgumentException(Messages.INVALID_PARAMETER.format("configRtEnte.codiceSocieta"));
			if (configRtEnte.getCuteCute() == null || configRtEnte.getCuteCute().length() == 0)
				throw new IllegalArgumentException(Messages.INVALID_PARAMETER.format("configRtEnte.userCode"));
			if (configRtEnte.getChiaveEnte() == null || configRtEnte.getChiaveEnte().length() == 0)
				throw new IllegalArgumentException(Messages.INVALID_PARAMETER.format("configRtEnte.chiaveEnte"));
			if (configRtEnte.getCodiceIdpaEnte() == null || configRtEnte.getCodiceIdpaEnte().length() == 0)
				throw new IllegalArgumentException(Messages.INVALID_PARAMETER.format("configRtEnte.codiceIdpa"));
			callableStatement.setString(1, configRtEnte.getCodiceSocieta());
			callableStatement.setString(2, configRtEnte.getCuteCute());
			callableStatement.setString(3, configRtEnte.getChiaveEnte());
			callableStatement.setString(4, configRtEnte.getCodiceIdpaEnte());
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