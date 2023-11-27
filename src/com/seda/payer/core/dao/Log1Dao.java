package com.seda.payer.core.dao;

import java.math.BigInteger;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.seda.data.helper.HelperException;
import com.seda.payer.core.bean.Log1;
import com.seda.payer.core.exception.DaoException;
import com.seda.payer.core.handler.BaseDaoHandler;

public class Log1Dao extends BaseDaoHandler {

	/**
	 * Default constructor
	 * @param connection
	 * @param schema
	 */
	public Log1Dao(Connection connection, String schema) {
		super(connection, schema);
//		log.debug("Log1Dao: Dao avviato");
	}
	
	/**
	 * @param key
	 * @return
	 * @throws DaoException
	 */
	public Log1 doDetail(BigInteger key) throws DaoException {
		// we define callableStatement
		CallableStatement callableStatement = null;
		ResultSet data = null;
		
//		log.debug("Log1Dao: \n\tOP: DETAIL \n\t ROUTINE: PYLG1SP_SEL"
//                  + "\n\tPARAM: idRequest:" + key);
		try {
			// we prepare callableStatement
			//TODO: non � stata implementata la SP PGLG1SP_SEL
			callableStatement = prepareCall("PYLG1SP_SEL");
			Log1 bean = new Log1();
			bean.setChiaveLog(key);
			// we invoke method load
			bean.load(callableStatement);
			//we execute callableStatement
			if (callableStatement.execute()) {
//				System.out.println("Log1Dao: esecuzione chiamata db");
				data = callableStatement.getResultSet();
				if (data.next()){
					System.out.println("Log1Dao: Stai ritornando un valore");
					return new Log1(data);
				}
			}
			return null;
		} catch (SQLException x) {
			System.out.println("errore1: " + x.getMessage());
			throw new DaoException(x);
		} catch (IllegalArgumentException x) {
			System.out.println("errore2: " + x.getMessage());
			throw new DaoException(x);
		} catch (HelperException x) {
			System.out.println("errore3: " + x.getMessage());
			throw new DaoException(x);
		} finally {
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
//					System.out.println("callableStatement.close eseguito su finally ");
				} catch (SQLException e) {
					e.printStackTrace();
//					System.out.println("callableStatement.close errore su finally ");
				}
			}
		}
	}
	
	/**
	 * @param bean
	 * @param rowsPerPage
	 * @param pageNumber
	 * @param order
	 * @throws DaoException
	 */
	public void doWebRowSets(Log1 bean, int rowsPerPage, int pageNumber, String order) throws DaoException {
		CallableStatement callableStatement = null;
		try	{
//			log.debug("Log1Dao: OP: doWebRowSets\n\tOBJ: " + bean.toString());
			//TODO: non � stata implementata la SP PYLG1SP_LST
			callableStatement = prepareCall("PYLG1SP_LST");
			bean.load(callableStatement, rowsPerPage, pageNumber, order);
			/* we execute procedure */
			if (callableStatement.execute()) {
				this.loadWebRowSets(callableStatement);
				/* we register page info */
				registerPageInfo(rowsPerPage, pageNumber, callableStatement.getInt(35), callableStatement.getInt(36), 
								 callableStatement.getInt(37), callableStatement.getInt(38));
			}
		} catch (SQLException x) {
			throw new DaoException(x);
		} catch (IllegalArgumentException x) {
			throw new DaoException(x);
		} catch (HelperException x) {
			throw new DaoException(x);
		} finally {
			if (callableStatement != null) {
				try {
					callableStatement.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	/**
	 * @param bean
	 * @return
	 */
	public void doInsert(Log1 bean) throws DaoException {
		CallableStatement callableStatement = null;
		try {
//			log.debug("Log1Dao: \n\tOP: INSERT \n\t ROUTINE: PYLG1SP_INS"
//						+ "\n\tOBJ: " + bean.toString());
			callableStatement = prepareCall("PYLG1SP_INS");
			bean.save(callableStatement);
			callableStatement.executeUpdate();
		} catch (SQLException x) {
			x.printStackTrace();
			throw new DaoException(x);
		} catch (IllegalArgumentException x) {
			x.printStackTrace();
			throw new DaoException(x);
		} catch (HelperException x) {
			x.printStackTrace();
			throw new DaoException(x);
		} finally {
			if (callableStatement != null) {
				try {
					callableStatement.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}
	/**
	 * @param bean
	 * @return
	 * @throws DaoException
	 */
	public void doUpdate(Log1 bean) throws DaoException {
		//Nota: non implementata non dovrebbe servire
	}
	/**
	 * @param key
	 * @return
	 * @throws DaoException
	 */
	public void doDelete(BigInteger key) throws DaoException {
		//Nota: non implementata non dovrebbe servire
	}
	
}