package com.seda.payer.core.dao;
 
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import com.seda.payer.commons.bean.TypeRequest;
import com.seda.payer.core.bean.FasciaGatewayPagamento;
import com.seda.payer.core.exception.DaoException;
import com.seda.payer.core.handler.BaseDaoHandler;
import com.seda.payer.core.messages.Messages;
import com.seda.data.helper.HelperException;
/**
 * 
 * @author mmontisano
 *
 */
public class FasciaGatewayPagamentoDao extends BaseDaoHandler {
	
	public FasciaGatewayPagamentoDao(Connection connection, String schema) {
		super(connection, schema);
	}

	public FasciaGatewayPagamento doDetail(String chiaveFascia) throws DaoException {
		//inizio LP PG21XX04 Leak
		CallableStatement callableStatement = null;
		ResultSet data = null;
		//fine LP PG21XX04 Leak
		try	{
			//inizio LP PG21XX04 Leak
			//CallableStatement callableStatement = prepareCall(Routines.FSC_DODETAIL.routine());
			callableStatement = prepareCall(Routines.FSC_DODETAIL.routine());
			//fine LP PG21XX04 Leak
			callableStatement.setString(1, chiaveFascia);
			if (callableStatement.execute()) {
				//inizio LP PG21XX04 Leak
				//ResultSet data = callableStatement.getResultSet();
				data = callableStatement.getResultSet();
				//fine LP PG21XX04 Leak
				if (data.next())
					return new FasciaGatewayPagamento(data);
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

	public void doRowSets(FasciaGatewayPagamento fascia) throws DaoException {
		rowSets(fascia, 0, 0);
	}

	public void doRowSets(FasciaGatewayPagamento fascia, int rowsPerPage, int pageNumber) throws DaoException {
		try	{
			if (rowsPerPage <= 0)
				throw new IllegalArgumentException(Messages.INVALID_PARAMETER.format("rowsPerPage"));

			if (pageNumber <= 0)
				throw new IllegalArgumentException(Messages.INVALID_PARAMETER.format("pageNumber"));
 
			rowSets(fascia, rowsPerPage, pageNumber);
		} catch (IllegalArgumentException x) {
			throw new DaoException(x);
		}
	}
	
	public void rowSets(FasciaGatewayPagamento fascia, int rowsPerPage, int pageNumber) throws DaoException {
		//inizio LP PG21XX04 Leak
		CallableStatement callableStatement = null;
		//fine LP PG21XX04 Leak
		try	{
			//inizio LP PG21XX04 Leak
			//CallableStatement callableStatement = prepareCall(Routines.FSC_DOLIST.routine());
			callableStatement = prepareCall(Routines.FSC_DOLIST.routine());
			//fine LP PG21XX04 Leak
			callableStatement.setString(1, fascia.getChiaveFascia());
			callableStatement.setString(2, fascia.getGateway().getChiaveGateway());
			callableStatement.setString(3, fascia.getTipoFascia());
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

	public void doSave(FasciaGatewayPagamento fascia,String codOp) throws DaoException {
	    CallableStatement callableStatement=null;
		try	{
			if ((fascia.getChiaveFascia() == null || fascia.getChiaveFascia().length() == 0) && codOp.compareTo(TypeRequest.EDIT_SCOPE.scope())==0)
				throw new IllegalArgumentException(Messages.INVALID_PARAMETER.format("fasciaGatewayPagamento.chiaveFascia"));
			FasciaGatewayPagamento data = doDetail(fascia.getChiaveFascia());
			if ((data != null) && codOp!=null && codOp.compareTo(TypeRequest.ADD_SCOPE.scope())==0) throw new IllegalArgumentException(Messages.INVALID_PARAMETER.format("fasciaGatewayPagamento.saveadd.error"));
			if (data != null) {
				callableStatement = prepareCall(Routines.FSC_DOUPDATE.routine());
				fascia.update(callableStatement);
			}
			else {
				callableStatement = prepareCall(Routines.FSC_DOINSERT.routine());
				fascia.save(callableStatement);
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

	public void doDelete(FasciaGatewayPagamento fascia) throws DaoException {
		//inizio LP PG21XX04 Leak
		CallableStatement callableStatement = null;
		//fine LP PG21XX04 Leak
		try	{
			//inizio LP PG21XX04 Leak
			//CallableStatement callableStatement = prepareCall(Routines.FSC_DODELETE.routine());
			callableStatement = prepareCall(Routines.FSC_DODELETE.routine());
			//fine LP PG21XX04 Leak
			if (fascia.getChiaveFascia() == null || fascia.getChiaveFascia().length() == 0)
				throw new IllegalArgumentException(Messages.INVALID_PARAMETER.format("fasciaGatewayPagamento.chiaveFascia"));

			callableStatement.setString(1, fascia.getChiaveFascia());
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