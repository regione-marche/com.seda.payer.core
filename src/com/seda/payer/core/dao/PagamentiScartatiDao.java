package com.seda.payer.core.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import com.seda.data.dao.DAOHelper;
import com.seda.data.helper.HelperException;
import com.seda.payer.core.exception.DaoException;
import com.seda.payer.core.handler.BaseDaoHandler;

public class PagamentiScartatiDao extends BaseDaoHandler {
	
	public PagamentiScartatiDao(Connection connection, String schema) {
		super(connection, schema);
	}
	
	private void closeConnection(CallableStatement callableStatement) {
		if (callableStatement != null)
			DAOHelper.closeIgnoringException(callableStatement);
	}

	public int doInsertPagamentiScartati(String tipoFlusso, String nomeFile, String recordScartato, String tipologiaRecord, char stato) 
								  throws DaoException {
		CallableStatement callableStatement = null;	
		try	{
			callableStatement = prepareCall(Routines.SCA_DOINSERT.routine());	
			callableStatement.setString(1, tipoFlusso);
			callableStatement.setString(2, nomeFile);
			callableStatement.setString(3, recordScartato);
			callableStatement.setString(4, tipologiaRecord);
			callableStatement.setString(5, String.valueOf(stato));
			callableStatement.registerOutParameter(6, Types.INTEGER);
			callableStatement.execute();
			int i = callableStatement.getInt(6);
			return i;
		} catch (SQLException x) {
			throw new DaoException(x);
		} catch (IllegalArgumentException x) {
			throw new DaoException(x);
		} catch (HelperException x) {
			throw new DaoException(x);
		} finally {
			//inizio LP PG21XX04 Leak
			//closeConnection(callableStatement);
			if (callableStatement != null) {
				try {
					callableStatement.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			//fine LP PG21XX04 Leak
		}
	}
	
	public int doUpdatePagamentiScartati(String tipoFlusso, String nomeFile, String recordScartato, String tipologiaRecord, char stato, int pKey) 
								  throws DaoException {
		CallableStatement callableStatement = null;	
		try	{
			callableStatement = prepareCall(Routines.SCA_DOUPDATE.routine());	
			callableStatement.setString(1, tipoFlusso);
			callableStatement.setString(2, nomeFile);
			callableStatement.setString(3, recordScartato);
			callableStatement.setString(4, tipologiaRecord);
			callableStatement.setString(5, String.valueOf(stato));
			callableStatement.setInt(6, pKey);
			callableStatement.registerOutParameter(7, Types.INTEGER);
			callableStatement.execute();
			int i = callableStatement.getInt(7);
			return i;
		} catch (SQLException x) {
			throw new DaoException(x);
		} catch (IllegalArgumentException x) {
			throw new DaoException(x);
		} catch (HelperException x) {
			throw new DaoException(x);
		} finally {
			//inizio LP PG21XX04 Leak
			//closeConnection(callableStatement);
			if (callableStatement != null) {
				try {
					callableStatement.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			//fine LP PG21XX04 Leak
		}
	}

	public List<String[]> doAssignPagamentiScartati() throws DaoException {
		CallableStatement callableStatement = null;	
		//inizio LP PG21XX04 Leak
		ResultSet resultSet = null;
		//fine LP PG21XX04 Leak
		try	{
			callableStatement = prepareCall(Routines.SCA_DOASSIGN.routine());	
			callableStatement.setString(1, " ");
			callableStatement.setInt(2, 0);
			//inizio LP PG21XX04 Leak
			//ResultSet resultSet = callableStatement.executeQuery();
			resultSet = callableStatement.executeQuery();
			//fine LP PG21XX04 Leak
			if (resultSet.next()) {
				List<String[]> output = new ArrayList<String[]>();
				do { output.add(new String[] { 
							resultSet.getString("chiaveTransazione"), 
							resultSet.getString("chiaveScarto"),
							resultSet.getString("descrizioneTipologiaServizio"),
							resultSet.getString("descrizioneEnte"),
							resultSet.getString("descrizioneUfficio") });
				} while (resultSet.next());
				return output;
			}
			return null;
		} catch (SQLException x) { throw new DaoException(x);
		} catch (IllegalArgumentException x) { throw new DaoException(x);
		} catch (HelperException x) { throw new DaoException(x);
		} finally {
			//inizio LP PG21XX04 Leak
			//closeConnection(callableStatement);
			if (resultSet != null) {
				try {
					resultSet.close();
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
			//fine LP PG21XX04 Leak
		}
	}

	public int doDeletePagamentiScartati(String nomeFile, char stato) throws DaoException {
		CallableStatement callableStatement = null;	
		try	{
			callableStatement = prepareCall(Routines.SCA_DODELETE_BY_FSCAELAB.routine()); {	
				callableStatement.setString(1, nomeFile);
				callableStatement.setString(2, String.valueOf(stato));
				callableStatement.registerOutParameter(3, Types.INTEGER);
				callableStatement.execute();
			}
			int i = callableStatement.getInt(7);
			return i;
		} catch (SQLException x) {
			throw new DaoException(x);
		} catch (IllegalArgumentException x) {
			throw new DaoException(x);
		} catch (HelperException x) {
			throw new DaoException(x);
		} finally {
			//inizio LP PG21XX04 Leak
			//closeConnection(callableStatement);
			if (callableStatement != null) {
				try {
					callableStatement.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			//fine LP PG21XX04 Leak
		}
	}	
}