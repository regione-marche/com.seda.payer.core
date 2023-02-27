package com.seda.payer.core.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.seda.data.helper.HelperException;
import com.seda.payer.core.bean.IoItaliaMessaggio;
import com.seda.payer.core.exception.DaoException;
import com.seda.payer.core.handler.BaseDaoHandler;

public class MessaggioEnteDao extends BaseDaoHandler {
	
	public MessaggioEnteDao(Connection connection, String schema) {
		super(connection, schema);
	}
	
	public void inserisciMessaggioEnte(IoItaliaMessaggio messaggio) throws DaoException{
		CallableStatement callableStatement=null;
		//inizio LP PG21XX04 Leak
		//Connection connection = null;
		//fine LP PG21XX04 Leak

		try {			
			//inizio LP PG21XX04 Leak
			//connection = getConnection();
			//callableStatement = Helper.prepareCall(connection, getSchema(), "PYMESSP_INS");
			callableStatement = prepareCall("PYMESSP_INS");
			//fine LP PG21XX04 Leak
			callableStatement.setString(1, messaggio.getCutecute());
			callableStatement.setString(2, messaggio.getIdDominio());
			callableStatement.setString(3, messaggio.getTipologiaServizio());
			callableStatement.setString(4, messaggio.getTimestampParsingFile());
			callableStatement.setInt(5, messaggio.getPosizione());
			callableStatement.setString(6, messaggio.getCodiceFiscale());
			callableStatement.setString(7, messaggio.getOggettoMessaggio());
			callableStatement.setString(8, messaggio.getCorpoMessaggio());
			callableStatement.setDate(9, new java.sql.Date(messaggio.getDataScadenzaMessaggio().getTime()));
			callableStatement.setBigDecimal(10, messaggio.getImporto());
			callableStatement.setString(11, messaggio.getAvvisoPagoPa());
			callableStatement.setString(12, messaggio.getScadenzaPagamento());
			callableStatement.setString(13, messaggio.getEmail());
			callableStatement.setString(14, "0");
			callableStatement.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (HelperException e) {
			e.printStackTrace();
		} finally {
			//DAOHelper.closeIgnoringException(connection);
			//inizio LP PG21XX04 Leak
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
	
	public void updateMessaggioEnte(IoItaliaMessaggio messaggio) throws DaoException{
		CallableStatement callableStatement=null;
		//inizio LP PG21XX04 Leak
		//Connection connection = null;
		//fine LP PG21XX04 Leak

		try {			
			//inizio LP PG21XX04 Leak
			//connection = getConnection();
			//callableStatement = Helper.prepareCall(connection, getSchema(), "PYMESSP_UPD");
			callableStatement = prepareCall("PYMESSP_UPD");
			//fine LP PG21XX04 Leak
			callableStatement.setString(1, messaggio.getCutecute());
			callableStatement.setString(2, messaggio.getIdDominio());
			callableStatement.setString(3, messaggio.getTipologiaServizio());
			callableStatement.setString(4, messaggio.getTimestampParsingFile());
			callableStatement.setInt(5, messaggio.getPosizione());
			callableStatement.setString(6, messaggio.getStato());
			callableStatement.setString(7, messaggio.getMessaggioErrore());
			callableStatement.setDate(8, new java.sql.Date(messaggio.getDataInvioMessaggio().getTime()));
			callableStatement.setString(9, messaggio.getIdInvioMessaggio());
			callableStatement.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (HelperException e) {
			e.printStackTrace();
		} finally {
			//DAOHelper.closeIgnoringException(connection);
			//inizio LP PG21XX04 Leak
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

	public List<IoItaliaMessaggio> getMessaggiEnte(String cutecute, String stato) throws DaoException {
		CallableStatement callableStatement = null;
		//inizio LP PG21XX04 Leak
		//Connection connection = null;
		//fine LP PG21XX04 Leak
		ResultSet resultSet = null;
		List<IoItaliaMessaggio> listMessaggioEnte = null;
		try {
			//inizio LP PG21XX04 Leak
			//connection = getConnection();
			//callableStatement = Helper.prepareCall(connection, getSchema(), "PYMESSP_LST");
			callableStatement = prepareCall("PYMESSP_LST");
			//fine LP PG21XX04 Leak
			callableStatement.setString(1, cutecute);
			callableStatement.setString(2, stato);
			callableStatement.execute();
			resultSet=callableStatement.getResultSet();
			
			listMessaggioEnte = new ArrayList<IoItaliaMessaggio>();
			while(resultSet.next()) {
				IoItaliaMessaggio messaggio = new IoItaliaMessaggio(resultSet);
				listMessaggioEnte.add(messaggio);
			}
		} catch (SQLException e) {
			throw new DaoException(e);
		} catch (IllegalArgumentException e) {
			throw new DaoException(e);
		} catch (HelperException e) {
			throw new DaoException(e);
		} finally {
			//DAOHelper.closeIgnoringException(connection);
			//inizio LP PG21XX04 Leak
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
		
		return listMessaggioEnte;
	}

}
