package com.seda.payer.core.dao;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

import com.seda.data.helper.HelperException;
import com.seda.payer.core.exception.DaoException;
import com.seda.payer.core.handler.BaseDaoHandler;

import com.seda.data.spi.PageInfo;


public class TxSpedizioneFlussiDao   extends BaseDaoHandler {

	private PageInfo pageInfo = null;

	public TxSpedizioneFlussiDao(Connection connection, String schema) {
		super(connection, schema);
	}
	
	public PageInfo getPageInfo() {
		return pageInfo;
	}

	 
	public String getSpedizioneFlussiXml(int rowsPerPage, int pageNumber,String order,
			String tx_chiave_spedizione,	String tx_tipo_carta, String tx_tipo_flusso,  String tx_inviato_host,
			String tx_societa,String tx_utente,String tx_ente_consorzio,String tx_ente_consorziato,
			String tx_data_da,String tx_data_a ) throws DaoException
	{
		//inizio LP PG21XX04 Leak
		//CallableStatement callableStatement;
		CallableStatement callableStatement = null;
		ResultSet data = null;
		//fine LP PG21XX04 Leak
		try {
		    //callableStatement = prepareCall(Routines.TX_SPEDIZIONE_FLUSSI_TX.routine());
			callableStatement = prepareCall("SP_NON_UTILIZZATA");
			callableStatement.setInt(1, pageNumber);
			callableStatement.setInt(2, rowsPerPage);
			callableStatement.setString(3, order == null ? "" : order);
			callableStatement.setString(4, tx_chiave_spedizione == null ? "" :  tx_chiave_spedizione);
			callableStatement.setString(5, (tx_tipo_carta == null) ||(tx_tipo_carta.equals(StoredProcConf.CARTA_ALL_VALUE.getParam())) ? "" :  tx_tipo_carta);
			callableStatement.setString(6, tx_tipo_flusso == null ? "" :  tx_tipo_flusso);
			callableStatement.setString(7, tx_inviato_host == null ? "" :  tx_inviato_host);
			callableStatement.setString(8, (tx_societa == null) || (tx_societa.equals(StoredProcConf.SOCIETA_ALL_VALUE.getParam())) ? "" :  tx_societa);
			callableStatement.setString(9, (tx_utente == null) || (tx_utente.equals(StoredProcConf.AMBITOUTENTE_ALL_VALUE.getParam())) ? "" :  tx_utente);
			callableStatement.setString(10, (tx_ente_consorzio == null) || (tx_ente_consorzio.equals(StoredProcConf.ENTECONSORZIO_ALL_VALUE.getParam())) ? "" :  tx_ente_consorzio);
		    callableStatement.setString(11, (tx_ente_consorziato == null) || (tx_societa.equals(StoredProcConf.ENTECONSORZIATO_ALL_VALUE.getParam())) ? "" :  tx_ente_consorziato);
			callableStatement.setString(12,tx_data_da == null ? "" : tx_data_da);
			callableStatement.setString(13,tx_data_a == null ? "" : tx_data_a);
			
			callableStatement.registerOutParameter(14, Types.INTEGER);
			callableStatement.registerOutParameter(15, Types.INTEGER);
			callableStatement.registerOutParameter(16, Types.INTEGER);
			callableStatement.registerOutParameter(17, Types.SMALLINT);
			if(callableStatement.execute())
			{
				//System.err.println("Q = " + callableStatement.getInt(16));
				pageInfo = new PageInfo();
				pageInfo.setPageNumber(pageNumber);
				pageInfo.setRowsPerPage(rowsPerPage);
				pageInfo.setFirstRow(callableStatement.getInt(14));
				pageInfo.setLastRow(callableStatement.getInt(15));
				pageInfo.setNumRows(callableStatement.getInt(16));
				pageInfo.setNumPages(callableStatement.getInt(17));

				//inizio LP PG21XX04 Leak
				//ResultSet data = callableStatement.getResultSet();
				data = callableStatement.getResultSet();
				//fine LP PG21XX04 Leak
				loadWebRowSet(data);
				return getWebRowSetXml();
			}
			return null;

		} catch (IllegalArgumentException e) {
			throw new DaoException(e);
		} catch (SQLException e) {
			throw new DaoException(e);
		} catch (HelperException e) {
			throw new DaoException(e);
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
	
	public String getSpedizioneFlussiGroupedXml(String tx_chiave_spedizione,	String tx_tipo_carta, String tx_tipo_flusso,  String tx_inviato_host,
			String tx_societa,String tx_utente,String tx_ente_consorzio,String tx_ente_consorziato,
			String tx_data_da,String tx_data_a ) throws DaoException
	{
		//inizio LP PG21XX04 Leak
		//CallableStatement callableStatement;
		CallableStatement callableStatement = null;
		ResultSet data = null;
		//fine LP PG21XX04 Leak
		try {
			callableStatement = prepareCall("TX_SPEDIZIONE_FLUSSI_GROUPED_TX");
			callableStatement.setString(1, tx_chiave_spedizione == null ? "" :  tx_chiave_spedizione);
			callableStatement.setString(2, (tx_tipo_carta == null) ||(tx_tipo_carta.equals(StoredProcConf.CARTA_ALL_VALUE.getParam())) ? "" :  tx_tipo_carta);
			callableStatement.setString(3, tx_tipo_flusso == null ? "" :  tx_tipo_flusso);
			callableStatement.setString(4, tx_inviato_host == null ? "" :  tx_inviato_host);
			callableStatement.setString(5, (tx_societa == null) || (tx_societa.equals(StoredProcConf.SOCIETA_ALL_VALUE.getParam())) ? "" :  tx_societa);
			callableStatement.setString(6, (tx_utente == null) || (tx_utente.equals(StoredProcConf.AMBITOUTENTE_ALL_VALUE.getParam())) ? "" :  tx_utente);
			callableStatement.setString(7, (tx_ente_consorzio == null) || (tx_ente_consorzio.equals(StoredProcConf.ENTECONSORZIO_ALL_VALUE.getParam())) ? "" :  tx_ente_consorzio);
		    callableStatement.setString(8, (tx_ente_consorziato == null) || (tx_societa.equals(StoredProcConf.ENTECONSORZIATO_ALL_VALUE.getParam())) ? "" :  tx_ente_consorziato);
			callableStatement.setString(9,tx_data_da == null ? "" : tx_data_da);
			callableStatement.setString(10,tx_data_a == null ? "" : tx_data_a);
			if(callableStatement.execute())
			{
				//inizio LP PG21XX04 Leak
				//ResultSet data = callableStatement.getResultSet();
				data = callableStatement.getResultSet();
				//fine LP PG21XX04 Leak
				loadWebRowSet(data);
				return getWebRowSetXml();
			}
			return null;

		} catch (IllegalArgumentException e) {
			throw new DaoException(e);
		} catch (SQLException e) {
			throw new DaoException(e);
		} catch (HelperException e) {
			throw new DaoException(e);
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
	
	public String  getSpedizioneFlussiGroupedTotalXml(String tx_chiave_spedizione,	String tx_tipo_carta, String tx_tipo_flusso,  String tx_inviato_host,
			String tx_societa,String tx_utente,String tx_ente_consorzio,String tx_ente_consorziato,
			String tx_data_da,String tx_data_a ) throws DaoException
	{
		//inizio LP PG21XX04 Leak
		//CallableStatement callableStatement;
		CallableStatement callableStatement = null;
		ResultSet data = null;
		//fine LP PG21XX04 Leak
		try {
			callableStatement = prepareCall("TX_SPEDIZIONE_FLUSSI_GROUPED_TOTAL_TX");
			callableStatement.setString(1, tx_chiave_spedizione == null ? "" :  tx_chiave_spedizione);
			callableStatement.setString(2, (tx_tipo_carta == null) ||(tx_tipo_carta.equals(StoredProcConf.CARTA_ALL_VALUE.getParam())) ? "" :  tx_tipo_carta);
			callableStatement.setString(3, tx_tipo_flusso == null ? "" :  tx_tipo_flusso);
			callableStatement.setString(4, tx_inviato_host == null ? "" :  tx_inviato_host);
			callableStatement.setString(5, (tx_societa == null) || (tx_societa.equals(StoredProcConf.SOCIETA_ALL_VALUE.getParam())) ? "" :  tx_societa);
			callableStatement.setString(6, (tx_utente == null) || (tx_utente.equals(StoredProcConf.AMBITOUTENTE_ALL_VALUE.getParam())) ? "" :  tx_utente);
			callableStatement.setString(7, (tx_ente_consorzio == null) || (tx_ente_consorzio.equals(StoredProcConf.ENTECONSORZIO_ALL_VALUE.getParam())) ? "" :  tx_ente_consorzio);
		    callableStatement.setString(8, (tx_ente_consorziato == null) || (tx_societa.equals(StoredProcConf.ENTECONSORZIATO_ALL_VALUE.getParam())) ? "" :  tx_ente_consorziato);
			callableStatement.setString(9,tx_data_da == null ? "" : tx_data_da);
			callableStatement.setString(10,tx_data_a == null ? "" : tx_data_a);
			if(callableStatement.execute())
			{
				//inizio LP PG21XX04 Leak
				//ResultSet data = callableStatement.getResultSet();
				data = callableStatement.getResultSet();
				//fine LP PG21XX04 Leak
				loadWebRowSet(data);
				return getWebRowSetXml();
			}
			return null;

		} catch (IllegalArgumentException e) {
			throw new DaoException(e);
		} catch (SQLException e) {
			throw new DaoException(e);
		} catch (HelperException e) {
			throw new DaoException(e);
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
}
