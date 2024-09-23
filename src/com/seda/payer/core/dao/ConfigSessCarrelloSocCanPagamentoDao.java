package com.seda.payer.core.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

import com.seda.payer.commons.bean.TypeRequest;
import com.seda.payer.core.bean.ConfigSessCarrelloSocCanPagamento;
import com.seda.payer.core.exception.DaoException;
import com.seda.payer.core.handler.BaseDaoHandler;
import com.seda.payer.core.messages.Messages;
import com.seda.data.helper.HelperException;


public class ConfigSessCarrelloSocCanPagamentoDao extends BaseDaoHandler {
	
	public ConfigSessCarrelloSocCanPagamentoDao(Connection connection, String schema) {
		super(connection, schema);
	}

	public ConfigSessCarrelloSocCanPagamento doDetail(String companyCode, String chiaveCanalePagamento) throws DaoException {
		//inizio LP PG21XX04 Leak
		CallableStatement callableStatement = null;
		ResultSet data = null;
		//fine LP PG21XX04 Leak
		try	{
			//inizio LP PG21XX04 Leak
			//CallableStatement callableStatement = prepareCall(Routines.CAS_DODETAIL.routine());
			callableStatement = prepareCall(Routines.CAS_DODETAIL.routine());
			//fine LP PG21XX04 Leak
			callableStatement.setString(1, companyCode);
			callableStatement.setString(2, chiaveCanalePagamento);
			if (callableStatement.execute()) {
				//inizio LP PG21XX04 Leak
				//ResultSet data = callableStatement.getResultSet();
				data = callableStatement.getResultSet();
				//fine LP PG21XX04 Leak
				if (data.next())
					return new ConfigSessCarrelloSocCanPagamento(data);
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

	public void doRowSets(ConfigSessCarrelloSocCanPagamento configSessCarrelloSocCanPagamento) throws DaoException {
		rowSets(configSessCarrelloSocCanPagamento, 0, 0);
	}

	public void doRowSets(ConfigSessCarrelloSocCanPagamento configSessCarrelloSocCanPagamento, int rowsPerPage, int pageNumber) throws DaoException {
		if (rowsPerPage <= 0)
			throw new IllegalArgumentException(Messages.INVALID_PARAMETER.format("rowsPerPage"));
		if (pageNumber <= 0)
			throw new IllegalArgumentException(Messages.INVALID_PARAMETER.format("pageNumber"));
		rowSets(configSessCarrelloSocCanPagamento, rowsPerPage, pageNumber);
	}

	public void rowSets(ConfigSessCarrelloSocCanPagamento configSessCarrelloSocCanPagamento, int rowsPerPage, int pageNumber) throws DaoException {
		//inizio LP PG21XX04 Leak
		CallableStatement callableStatement = null;
		//fine LP PG21XX04 Leak
		try	{
			//inizio LP PG21XX04 Leak
			//CallableStatement callableStatement = prepareCall(Routines.CAS_DOLIST.routine());
			callableStatement = prepareCall(Routines.CAS_DOLIST.routine());
			//fine LP PG21XX04 Leak
			callableStatement.setString(1, "");
			callableStatement.setString(2, "");
			callableStatement.setString(3, configSessCarrelloSocCanPagamento.getCompany().getCompanyCode());
			callableStatement.setString(4, configSessCarrelloSocCanPagamento.getCanalePagamento().getChiaveCanalePagamento());
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

	public void doSave(ConfigSessCarrelloSocCanPagamento configSessCarrelloSocCanPagamento, String codOp) throws DaoException {
		CallableStatement callableStatement = null;
		try	{
			if (configSessCarrelloSocCanPagamento.getCompany().getCompanyCode() == null || configSessCarrelloSocCanPagamento.getCompany().getCompanyCode().length()==0)
				throw new IllegalArgumentException(Messages.INVALID_PARAMETER.format("configSessCarrelloSocCanPagamento.companyCode"));
			if (configSessCarrelloSocCanPagamento.getCanalePagamento().getChiaveCanalePagamento()== null || configSessCarrelloSocCanPagamento.getCanalePagamento().getChiaveCanalePagamento().length()==0)
				throw new IllegalArgumentException(Messages.INVALID_PARAMETER.format("configSessCarrelloSocCanPagamento.chiaveCanalePagamento"));
			System.out.println("Company"+ configSessCarrelloSocCanPagamento.getCompany().getCompanyCode());
			System.out.println("Can Pag"+ configSessCarrelloSocCanPagamento.getCanalePagamento().getChiaveCanalePagamento());
			ConfigSessCarrelloSocCanPagamento data = doDetail(configSessCarrelloSocCanPagamento.getCompany().getCompanyCode(), configSessCarrelloSocCanPagamento.getCanalePagamento().getChiaveCanalePagamento());
			if ((data != null) && codOp != null && codOp.compareTo(TypeRequest.ADD_SCOPE.scope()) == 0)
				throw new IllegalArgumentException(Messages.INVALID_PARAMETER.format("sesscarrello.saveadd.error"));
			if (data != null) {
				callableStatement = prepareCall(Routines.CAS_DOUPDATE.routine());
				configSessCarrelloSocCanPagamento.update(callableStatement);
			} else {
				callableStatement = prepareCall(Routines.CAS_DOINSERT.routine());
				configSessCarrelloSocCanPagamento.save(callableStatement);
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

	/*
		CallableStatement callableStatement = null;
		try	{
			if (configSessCarrelloSocCanPagamento.getCompany().getCompanyCode() == null || configSessCarrelloSocCanPagamento.getCompany().getCompanyCode().length()==0)
				throw new IllegalArgumentException(Messages.INVALID_PARAMETER.format("configSessCarrelloSocCanPagamento.companyCode"));
			if (configSessCarrelloSocCanPagamento.getCanalePagamento().getChiaveCanalePagamento()== null || configSessCarrelloSocCanPagamento.getCanalePagamento().getChiaveCanalePagamento().length()==0)
				throw new IllegalArgumentException(Messages.INVALID_PARAMETER.format("configSessCarrelloSocCanPagamento.chiaveCanalePagamento"));
			ConfigSessCarrelloSocCanPagamento data = doDetail(configSessCarrelloSocCanPagamento.getCompany().getCompanyCode(), configSessCarrelloSocCanPagamento.getCanalePagamento().getChiaveCanalePagamento());
			if (data == null)  throw new IllegalArgumentException(Messages.INVALID_PARAMETER.format("configSessCarrelloSocCanPagamento.saveadd.error"));
			if (data != null) {
				callableStatement = prepareCall(Routines.CAS_DOUPDATE.routine());
				configSessCarrelloSocCanPagamento.update(callableStatement);
			}
			else {
				callableStatement = prepareCall(Routines.CAS_DOINSERT.routine());
				configSessCarrelloSocCanPagamento.save(callableStatement);
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
	}*/

	public void doDelete(ConfigSessCarrelloSocCanPagamento configSessCarrelloSocCanPagamento) throws DaoException {
		//inizio LP PG21XX04 Leak
		CallableStatement callableStatement = null;
		//fine LP PG21XX04 Leak
		try	{
			//inizio LP PG21XX04 Leak
			//CallableStatement callableStatement = prepareCall(Routines.CAS_DODELETE.routine());
			callableStatement = prepareCall(Routines.CAS_DODELETE.routine());
			//fine LP PG21XX04 Leak
			if (configSessCarrelloSocCanPagamento.getCompany().getCompanyCode() == null)
				throw new IllegalArgumentException(Messages.INVALID_PARAMETER.format("configSessCarrelloSocCanPagamento.companyCode"));
			if (configSessCarrelloSocCanPagamento.getCanalePagamento().getChiaveCanalePagamento() == null)
				throw new IllegalArgumentException(Messages.INVALID_PARAMETER.format("configSessCarrelloSocCanPagamento.chiaveCanalePagamento"));
			callableStatement.setString(1, configSessCarrelloSocCanPagamento.getCompany().getCompanyCode());
			callableStatement.setString(2, configSessCarrelloSocCanPagamento.getCanalePagamento().getChiaveCanalePagamento());
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