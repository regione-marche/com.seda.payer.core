package com.seda.payer.core.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;


import com.seda.data.helper.HelperException;

import com.seda.payer.core.bean.LogSepa;
import com.seda.payer.core.exception.DaoException;
import com.seda.payer.core.handler.BaseDaoHandler;

public class LogSepaDAOImpl extends BaseDaoHandler implements LogSepaDao  { 
	private static final long serialVersionUID = 1L;
	protected CallableStatement callableStatementServAnno = null;
	
	/**
	 * Default constructor
	 * @param connection
	 * @param schema
	 */
	public LogSepaDAOImpl(Connection connection, String schema) {
		super(connection, schema);
	}
	
	/**
	 * @param idRequest
	 * @param tagSuffissoTabella
	 * @return
	 * @throws DaoException
	 */
	public LogSepa doDetail(String codiceFiscale, String iban) throws DaoException {
		CallableStatement callableStatement = null;
		ResultSet data = null;
		
		try {
			callableStatement = prepareCall(Routines.PYSELSP_SEL.routine());
			LogSepa bean = new LogSepa();
			bean.setCodiceFiscale(codiceFiscale);
			bean.setIban(iban);
			bean.load(callableStatement);
			if (callableStatement.execute()) {
				data = callableStatement.getResultSet();
				if (data.next()){
					return new LogSepa(data);
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
				} catch (SQLException e) {
					e.printStackTrace();
					System.out.println("callableStatement.close errore su finally ");
				}
			}
		}
	}
	
	/**
	 * @param bean
	 * @return
	 */
	public Integer doInsert(LogSepa bean) throws DaoException {
		CallableStatement callableStatement = null;
		Integer ret = 0;
		try {
			callableStatement = prepareCall(Routines.PYSELSP_INS.routine());
			bean.save(callableStatement);
			callableStatement.executeUpdate();
			
			ret = callableStatement.getInt(5);
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
		
		return ret;
	}
	/**
	 * @param bean
	 * @return
	 * @throws DaoException
	 */
	public Integer doUpdate(LogSepa bean) throws DaoException {
		
		CallableStatement callableStatement = null;
		Integer ret = 0;
		try {
			callableStatement = prepareCall(Routines.PYSELSP_UPD.routine());
			bean.update(callableStatement);
			callableStatement.execute();
			
			ret = callableStatement.getInt(5);
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
		return ret;
		
	}
	

}
