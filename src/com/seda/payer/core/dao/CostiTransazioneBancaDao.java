package com.seda.payer.core.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

import com.seda.data.helper.HelperException;
import com.seda.payer.commons.bean.TypeRequest;
import com.seda.payer.core.bean.CostiTransazioneBanca;
import com.seda.payer.core.exception.DaoException;
import com.seda.payer.core.handler.BaseDaoHandler;
import com.seda.payer.core.messages.Messages;

public class CostiTransazioneBancaDao extends BaseDaoHandler {
	
	public CostiTransazioneBancaDao(Connection connection, String schema) {
		super(connection, schema);
	}

	public CostiTransazioneBanca doDetail(String chiaveFasciaCosto) throws DaoException {
		//inizio LP PG21XX04 Leak
		CallableStatement callableStatement = null;
		ResultSet data = null;
		//fine LP PG21XX04 Leak
		try	{
			//inizio LP PG21XX04 Leak
			//CallableStatement callableStatement = prepareCall(Routines.CST_DODETAIL.routine());
			callableStatement = prepareCall(Routines.CST_DODETAIL.routine());
			//fine LP PG21XX04 Leak
			callableStatement.setString(1, chiaveFasciaCosto);
			if (callableStatement.execute()) {
				//inizio LP PG21XX04 Leak
				//ResultSet data = callableStatement.getResultSet();
				data = callableStatement.getResultSet();
				//fine LP PG21XX04 Leak
				if (data.next())
					return new CostiTransazioneBanca(data);
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

	public void doRowSets(String descrizioneSocieta, String descrizioneUtente, String descrizioneGateway) throws DaoException {
		rowSets(descrizioneSocieta, descrizioneUtente, descrizioneGateway, 0, 0);
	}

	public void doRowSets(String descrizioneSocieta, String descrizioneUtente, String descrizioneGateway, int rowsPerPage, int pageNumber) throws DaoException {
		if (rowsPerPage <= 0)
			throw new IllegalArgumentException(Messages.INVALID_PARAMETER.format("rowsPerPage"));
		if (pageNumber <= 0)
			throw new IllegalArgumentException(Messages.INVALID_PARAMETER.format("pageNumber"));
		rowSets(descrizioneSocieta, descrizioneUtente, descrizioneGateway, rowsPerPage, pageNumber);
	}

	public void rowSets(String descrizioneSocieta, String descrizioneUtente, String descrizioneGateway, int rowsPerPage, int pageNumber) throws DaoException {
		//inizio LP PG21XX04 Leak
		CallableStatement callableStatement = null;
		//fine LP PG21XX04 Leak
		try	{
			//inizio LP PG21XX04 Leak
			//CallableStatement callableStatement = prepareCall(Routines.CST_DOLISTWEB.routine());
			callableStatement = prepareCall(Routines.CST_DOLISTWEB.routine());
			//fine LP PG21XX04 Leak
			callableStatement.setString(1,descrizioneSocieta);
			callableStatement.setString(2,descrizioneUtente);
			callableStatement.setString(3,descrizioneGateway);
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

	public void doSave(CostiTransazioneBanca costi, String codOp) throws DaoException {
		CallableStatement callableStatement = null;
		try	{
			if (costi.getChiaveFasciaCosto() == null || costi.getChiaveFasciaCosto().length()==0)
				throw new IllegalArgumentException(Messages.INVALID_PARAMETER.format("costiTransazioneBanca.chiaveFasciaCosto"));
			if (costi.getChiaveGateway() == null ||costi.getChiaveGateway().length() ==0)
				throw new IllegalArgumentException(Messages.INVALID_PARAMETER.format("costiTransazioneBanca.chiaveGateway"));						
			CostiTransazioneBanca data = doDetail(costi.getChiaveFasciaCosto());
			if (data != null && codOp !=null && codOp.compareTo(TypeRequest.ADD_SCOPE.scope())==0)  throw new IllegalArgumentException(Messages.INVALID_PARAMETER.format("costiTransazioneBanca.saveadd.error"));
			if (data != null) {
				callableStatement = prepareCall(Routines.CST_DOUPDATE.routine());
				costi.update(callableStatement);
			}
			else {
				callableStatement = prepareCall(Routines.CST_DOINSERT.routine());
				costi.save(callableStatement);
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

	public void doDelete(CostiTransazioneBanca costi) throws DaoException {
		//inizio LP PG21XX04 Leak
		CallableStatement callableStatement = null;
		//fine LP PG21XX04 Leak
		try	{
			//inizio LP PG21XX04 Leak
			//CallableStatement callableStatement = prepareCall(Routines.CST_DODELETE.routine());
			callableStatement = prepareCall(Routines.CST_DODELETE.routine());
			//fine LP PG21XX04 Leak
			if (costi.getChiaveFasciaCosto() == null || costi.getChiaveFasciaCosto().length()==0)
				throw new IllegalArgumentException(Messages.INVALID_PARAMETER.format("costiTransazioneBanca.chiaveFasciaCosto"));
			

			callableStatement.setString(1, costi.getChiaveFasciaCosto());
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