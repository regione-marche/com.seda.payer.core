package com.seda.payer.core.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;


import com.seda.payer.commons.bean.TypeRequest;
import com.seda.payer.core.bean.CartaPagamento;
import com.seda.payer.core.exception.DaoException;
import com.seda.payer.core.handler.BaseDaoHandler;
import com.seda.payer.core.messages.Messages;
import com.seda.data.helper.HelperException;
/**
 * 
 * @author mmontisano
 *
 */
public class CartaPagamentoDao extends BaseDaoHandler {
	
	public CartaPagamentoDao(Connection connection, String schema) {
		super(connection, schema);
	}

	//inizio LP 20240909 - PGNTBOLDER-1
	public CartaPagamento doDetail(String codiceCarta) throws DaoException {
		return doDetailTail(true, codiceCarta);
	}

	public CartaPagamento doDetailTail(boolean bFlagUpdateAutocomit, String codiceCarta) throws DaoException {
	//fine LP 20240909 - PGNTBOLDER-1
		//inizio LP PG21XX04 Leak
		CallableStatement callableStatement = null;;
		ResultSet data = null;
		//fine LP PG21XX04 Leak
		try	{
			//inizio LP PG21XX04 Leak
			//CallableStatement callableStatement = prepareCall(Routines.CAR_DODETAIL.routine());
			//callableStatement = prepareCall(Routines.CAR_DODETAIL.routine());
			callableStatement = prepareCall(bFlagUpdateAutocomit, Routines.CAR_DODETAIL.routine());
			//fine LP PG21XX04 Leak
			callableStatement.setString(1, codiceCarta);
			if (callableStatement.execute()) {
				//inizio LP PG21XX04 Leak
				//ResultSet data = callableStatement.getResultSet();
				data = callableStatement.getResultSet();
				//fine LP PG21XX04 Leak
				if (data.next())
					return new CartaPagamento(data);
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
	public void doRowSets(CartaPagamento carta) throws DaoException {
		rowSets(carta, 0, 0);
	}
	
	public void doRowSets(CartaPagamento carta,int rowsPerPage, int pageNumber) throws DaoException {
		
			if (rowsPerPage <= 0)
				throw new IllegalArgumentException(Messages.INVALID_PARAMETER.format("rowsPerPage"));

			if (pageNumber <= 0)
				throw new IllegalArgumentException(Messages.INVALID_PARAMETER.format("pageNumber"));
		    
			rowSets(carta, rowsPerPage, pageNumber);
	}

	public void rowSets(CartaPagamento carta, int rowsPerPage, int pageNumber) throws DaoException {
		//inizio LP PG21XX04 Leak
		CallableStatement callableStatement = null;;
		//fine LP PG21XX04 Leak
		try	{
			//inizio LP PG21XX04 Leak
			//CallableStatement callableStatement = prepareCall(Routines.CAR_DOLIST.routine());
			callableStatement = prepareCall(Routines.CAR_DOLIST.routine());
			//fine LP PG21XX04 Leak
			callableStatement.setString(1, carta.getCodiceCartaPagamento());
			callableStatement.setString(2, carta.getDescrizioneCartaPagamento());
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
	public void doSave(CartaPagamento carta, String codOp) throws DaoException {
		doSaveTail(true, carta, codOp);
	}

	public void doSaveTail(boolean bFlagUpdateAutocomit, CartaPagamento carta, String codOp) throws DaoException {
	//fine LP 20240909 - PGNTBOLDER-1
		CallableStatement callableStatement = null;
		try	{
			if (carta.getCodiceCartaPagamento() == null || carta.getCodiceCartaPagamento().length() == 0)
				throw new IllegalArgumentException(Messages.INVALID_PARAMETER.format("carta.codiceCartaPagamento"));
			//inizio LP 20240909 - PGNTBOLDER-1
			//CartaPagamento data = doDetail(carta.getCodiceCartaPagamento());
			CartaPagamento data = doDetailTail(bFlagUpdateAutocomit, carta.getCodiceCartaPagamento());
			//fine LP 20240909 - PGNTBOLDER-1
			if ((data != null) && codOp!=null && codOp.compareTo(TypeRequest.ADD_SCOPE.scope())==0) throw new IllegalArgumentException(Messages.INVALID_PARAMETER.format("cartaPagamento.saveadd.error"));
			if (data != null) {
				//inizio LP 20240909 - PGNTBOLDER-1
				//callableStatement = prepareCall(Routines.CAR_DOUPDATE.routine());
				callableStatement = prepareCall(bFlagUpdateAutocomit, Routines.CAR_DOUPDATE.routine());
				//fine LP 20240909 - PGNTBOLDER-1
			} else {
				//inizio LP 20240909 - PGNTBOLDER-1
				//callableStatement = prepareCall(Routines.CAR_DOINSERT.routine());
				callableStatement = prepareCall(bFlagUpdateAutocomit, Routines.CAR_DOINSERT.routine());
				//fine LP 20240909 - PGNTBOLDER-1
			}
			carta.save(callableStatement);
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
	public void doDelete(CartaPagamento carta) throws DaoException {
		doDeleteTail(true, carta);
	}

	public void doDeleteTail(boolean bFlagUpdateAutocomit, CartaPagamento carta) throws DaoException {
	//fine LP 20240909 - PGNTBOLDER-1
		//inizio LP PG21XX04 Leak
		CallableStatement callableStatement = null;;
		//fine LP PG21XX04 Leak
		try	{
			//inizio LP PG21XX04 Leak
			//CallableStatement callableStatement = prepareCall(Routines.CAR_DODELETE.routine());
			//inizio LP 20240909 - PGNTBOLDER-1
			//callableStatement = prepareCall(Routines.CAR_DODELETE.routine());
			callableStatement = prepareCall(bFlagUpdateAutocomit, Routines.CAR_DODELETE.routine());
			//fine LP 20240909 - PGNTBOLDER-1
			//fine LP PG21XX04 Leak
			if (carta.getCodiceCartaPagamento() == null || carta.getCodiceCartaPagamento().length() == 0)
				throw new IllegalArgumentException(Messages.INVALID_PARAMETER.format("carta.codiceCartaPagamento"));

			callableStatement.setString(1, carta.getCodiceCartaPagamento());
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