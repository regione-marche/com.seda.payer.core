package com.seda.payer.core.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

import com.seda.payer.commons.bean.TypeRequest;
import com.seda.payer.core.bean.CanalePagamento;
import com.seda.payer.core.exception.DaoException;
import com.seda.payer.core.handler.BaseDaoHandler;
import com.seda.payer.core.messages.Messages;
import com.seda.data.helper.HelperException;
/**
 * 
 * @author mmontisano
 *
 */
public class CanalePagamentoDao extends BaseDaoHandler {
	
	public CanalePagamentoDao(Connection connection, String schema) {
		super(connection, schema);
	}
 
	//inizio LP 20240909 - PGNTBOLDER-1
	public CanalePagamento doDetail(String code) throws DaoException {
		return doDetailTail(true, code);
	}

	public CanalePagamento doDetailTail(boolean bFlagUpdateAutocomit, String code) throws DaoException {
	//fine LP 20240909 - PGNTBOLDER-1
		//inizio LP PG21XX04 Leak
		CallableStatement callableStatement = null;
		ResultSet data = null;
		//fine LP PG21XX04 Leak
		try	{
			//inizio LP PG21XX04 Leak
			//CallableStatement callableStatement = prepareCall(Routines.CAN_DODETAIL.routine());
			//inizio LP 20240909 - PGNTBOLDER-1
			//callableStatement = prepareCall(Routines.CAN_DODETAIL.routine());
			callableStatement = prepareCall(bFlagUpdateAutocomit, Routines.CAN_DODETAIL.routine());
			//fine LP 20240909 - PGNTBOLDER-1
			//fine LP PG21XX04 Leak
			callableStatement.setString(1, code);
			if (callableStatement.execute()) {
				//inizio LP PG21XX04 Leak
				//ResultSet data = callableStatement.getResultSet();
				data = callableStatement.getResultSet();
				//fine LP PG21XX04 Leak
				if (data.next())
					return new CanalePagamento(data);
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
	
	public void doRowSets(String code, String desc) throws DaoException {
		rowSets(code, desc, 0, 0);
	}
	
	public void doRowSets(String code, String desc,int rowsPerPage, int pageNumber) throws DaoException {
		if (rowsPerPage <= 0)
			throw new IllegalArgumentException(Messages.INVALID_PARAMETER.format("rowsPerPage"));
		if (pageNumber <= 0)
			throw new IllegalArgumentException(Messages.INVALID_PARAMETER.format("pageNumber"));
		rowSets(code, desc, rowsPerPage, pageNumber);
	}

	public void rowSets(String code, String description,int rowsPerPage, int pageNumber) throws DaoException {
		//inizio LP PG21XX04 Leak
		CallableStatement callableStatement = null;
		//fine LP PG21XX04 Leak
		try	{
			//inizio LP PG21XX04 Leak
			//CallableStatement callableStatement = prepareCall(Routines.CAN_DOLIST.routine());
			callableStatement = prepareCall(Routines.CAN_DOLIST.routine());
			//fine LP PG21XX04 Leak
			callableStatement.setString(1, code);
			callableStatement.setString(2, description);
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

	//inizio LP 20240909 - PGNTBOLDER-1
	public void doSave(CanalePagamento can, String codOp) throws DaoException {
		doSaveTail(true, can, codOp);
	}

	public void doSaveTail(boolean bFlagUpdateAutocomit, CanalePagamento can, String codOp) throws DaoException {
	//fine LP 20240909 - PGNTBOLDER-1
		CallableStatement callableStatement = null;
		try	{
			if (can.getChiaveCanalePagamento() == null || can.getChiaveCanalePagamento().length() == 0)
				throw new IllegalArgumentException(Messages.INVALID_PARAMETER.format("canalepagamento.chiaveCanalePagamento"));
			//inizio LP 20240909 - PGNTBOLDER-1
			//CanalePagamento data = doDetail(can.getChiaveCanalePagamento());
			CanalePagamento data = doDetailTail(bFlagUpdateAutocomit, can.getChiaveCanalePagamento());
			//fine LP 20240909 - PGNTBOLDER-1
			if ((data != null) && codOp != null && codOp.compareTo(TypeRequest.ADD_SCOPE.scope()) == 0)
				throw new IllegalArgumentException(Messages.INVALID_PARAMETER.format("canalePagamento.saveadd.error"));
			if (data != null)  {
				//inizio LP 20240909 - PGNTBOLDER-1
				//callableStatement = prepareCall(Routines.CAN_DOUPDATE.routine());
				callableStatement = prepareCall(bFlagUpdateAutocomit, Routines.CAN_DOUPDATE.routine());
				//fine LP 20240909 - PGNTBOLDER-1
			} else {
				//inizio LP 20240909 - PGNTBOLDER-1
				//callableStatement = prepareCall(Routines.CAN_DOINSERT.routine());
				callableStatement = prepareCall(bFlagUpdateAutocomit, Routines.CAN_DOINSERT.routine());
				//fine LP 20240909 - PGNTBOLDER-1
			}
			can.save(callableStatement);
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

	//inizio LP 20240909 - PGNTBOLDER-1
	public void doDelete(CanalePagamento can) throws DaoException {
		doDeleteTail(true, can);
	}

	public void doDeleteTail(boolean bFlagUpdateAutocomit, CanalePagamento can) throws DaoException {
	//fine LP 20240909 - PGNTBOLDER-1
		//inizio LP PG21XX04 Leak
		CallableStatement callableStatement = null;
		//fine LP PG21XX04 Leak
		try	{
			//inizio LP PG21XX04 Leak
			//CallableStatement callableStatement = prepareCall(Routines.CAN_DODELETE.routine());
			//inizio LP 20240909 - PGNTBOLDER-1
			//callableStatement = prepareCall(Routines.CAN_DODELETE.routine());
			callableStatement = prepareCall(bFlagUpdateAutocomit, Routines.CAN_DODELETE.routine());
			//fine LP 20240909 - PGNTBOLDER-1
			//fine LP PG21XX04 Leak
			if (can.getChiaveCanalePagamento() == null || can.getChiaveCanalePagamento().length() == 0)
				throw new IllegalArgumentException(Messages.INVALID_PARAMETER.format("canalepagamento.chiaveCanalePagamento"));
			callableStatement.setString(1, can.getChiaveCanalePagamento());
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