package com.seda.payer.core.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;

import com.seda.data.helper.HelperException;
import com.seda.payer.core.exception.DaoException;
import com.seda.payer.core.handler.rest.RestBaseDaoHandler;

public class ImuTasiDao extends RestBaseDaoHandler {
	
	public ImuTasiDao(Connection connection, String schema) {
		super(connection, schema);
	}
	
	public ImuTasiDao(Connection connection, String schema, boolean isRest, String baseUrl) {
		super(connection, schema, isRest, baseUrl);
	}
	
	//PG160180_001 GG - Introdotto dataPagamentoDa e dataPagamentoA
	public void recuperaPagamentiImuTasi(String codiceUtente, String codiceProvinciaComune, String codiceFiscaleContribuente, String dataPagamentoDa, String dataPagamentoA) throws DaoException
	{
		CallableStatement callableStatement = null;
		try {
			callableStatement = prepareCall(Routines.SPEPIT01.routine());
			callableStatement.setString(1, codiceUtente);
			callableStatement.setString(2, codiceProvinciaComune);
			callableStatement.setString(3, codiceFiscaleContribuente);
			//PG160180_001 GG - inizio
			callableStatement.setString(4, dataPagamentoDa);
			callableStatement.setString(5, dataPagamentoA);
			//PG160180_001 GG - fine
		    callableStatement.registerOutParameter(6, Types.CHAR);
		    callableStatement.registerOutParameter(7, Types.CHAR);
			if (callableStatement.execute())
				this.loadWebRowSets(callableStatement);
		} catch (HelperException x) {
			throw new DaoException(x);
		} catch (SQLException x) {
			throw new DaoException(x);
		} catch (IllegalArgumentException x) {
			throw new DaoException(x);
		} finally {
			//inizio LP PG21XX04 Leak
			//if (callableStatement != null)
			//	DAOHelper.closeIgnoringException(callableStatement);
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
	
	public void recuperaDettaglioPagamentoImuTasi(String codiceUtente, String codiceProvinciaComune, String codiceFiscaleContribuente, String flagPagamento, String keyPagamento) throws DaoException
	{
		CallableStatement callableStatement = null;
		try {
			callableStatement = prepareCall(Routines.SPEPIT02.routine());
			callableStatement.setString(1, codiceUtente);
			callableStatement.setString(2, codiceProvinciaComune);
			callableStatement.setString(3, codiceFiscaleContribuente);
			callableStatement.setString(4, flagPagamento);
			callableStatement.setString(5, keyPagamento);
		    callableStatement.registerOutParameter(6, Types.CHAR);
		    callableStatement.registerOutParameter(7, Types.CHAR);
			if (callableStatement.execute())
				this.loadWebRowSets(callableStatement);		//TODO da verificare
		} catch (HelperException x) {
			throw new DaoException(x);
		} catch (SQLException x) {
			throw new DaoException(x);
		} catch (IllegalArgumentException x) {
			throw new DaoException(x);
		} finally {
			//inizio LP PG21XX04 Leak
			//if (callableStatement != null)
			//	DAOHelper.closeIgnoringException(callableStatement);
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
