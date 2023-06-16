package com.seda.payer.core.dao;

import java.math.BigDecimal;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import com.seda.data.helper.HelperException;
import com.seda.data.spi.PageInfo;
import com.seda.payer.core.bean.ModuloIntegrazionePagamentiOneri;
import com.seda.payer.core.bean.RiepilogoTransazioniCompletate;
import com.seda.payer.core.bean.RiepilogoTransazioniGateway;
import com.seda.payer.core.bean.RiepilogoTransazioniNonQuadrate;
import com.seda.payer.core.bean.TransazioniGrouped;
import com.seda.payer.core.bean.TransazioniGroupedSuccess;
import com.seda.payer.core.exception.DaoException;
import com.seda.payer.core.handler.BaseDaoHandler;

public class ManagerTransazioniDao  extends BaseDaoHandler{

	private PageInfo pageInfo = null;
	/*
	private static String listaCampi = "\"ID TRANSAZIONE\";\"SOCIETA\";\"UTENTE\";\"DATA TRANSAZIONE\";\"DATA_PAGAMENTO\";\"INDIRIZZO IP\";\"EMAIL UTENTE\";\"NUMERO SMS UTENTE\";" +
			"\"CANALE\";\"TIPO CARTA\";\"STATO RENDICONTAZIONE\";\"STATO_RICONCILIAZIONE\";\"IMPORTO TOTALE\";\"ESITO\"\r\n";
	*/
/*
	private static String[] listaCampiCSV = {"ID TRANSAZIONE", "SOCIETA", "ENTE", "DATA TRANSAZIONE","DATA_PAGAMENTO","INDIRIZZO IP","EMAIL UTENTE","NUMERO SMS UTENTE",
		"CANALE","STRUMENTO","TIPO CARTA","STATO RENDICONTAZIONE","STATO_RICONCILIAZIONE","IMPORTO TOTALE","ESITO"};

	private static String[] listaValoreCampiCSV = {"TRA_KTRAKTRA","SOC_DSOCDSOC","ANE_DANEDENT,ANE_DANEDUFF","TRA_GTRADTRA","TRA_GTRADPAG","TRA_DTRAINIP",
		"TRA_ETRAECON","TRA_CTRANSMS",
		"CAN_DCANDCAN", "SER_DSERDSER", "GTW_DGTWDGTW","TRA_CTRAREND","TRA_CTRAQUAD","TRA_ITRAITOT","TRA_FTRAFESI"};
*/
	public ManagerTransazioniDao(Connection connection, String schema) {
		super(connection, schema);
	}
	
	public PageInfo getPageInfo() {
		return pageInfo;
	}

	//PG170010_001 GG - Introdotto idFlussoQuadratura
	//PG150180_001 GG - Introdotto codice iuv
	public String getListaTransazioniXml(int rowsPerPage, int pageNumber, String order, 
			String tx_societa, String tx_provincia, String tx_utente, String tx_ente, String tx_mostra, String tx_codice_transazione,
			String tx_indirizzo_ip, String tx_user_email, String tx_user_sms, String tx_canale_pagamento,
			String tx_strumento, String tx_tipo_carta, String tx_id_bollettino, String tx_servizio,
			String tx_stato_rendicontazione, String tx_stato_riconciliazione, String tx_importo_da, 
			String tx_importo_a, String tx_data_da,String tx_data_a, int chiaveQuadratura, String tx_id_Terminale_Atm, String tx_id_Transazione_Atm,
			String tx_chiave_rendicontazione, int tx_gg_storno, String tx_id_terminale_pos_fisico, String tx_data_accr_da, String tx_data_accr_a,String tx_codice_IUV, String idFlussoQuadratura, String tx_recuperate, String tx_codice_fiscale ) throws DaoException {
		CallableStatement callableStatement = null;
		ResultSet data = null;
		try {
			System.out.println("[CORE - ManagerTransazioniDao - getListaTransazioni()] INIZIO CHIAMATA A SP PYTRASP_LST_MG");
			data = getListaTransazioni(callableStatement, rowsPerPage, pageNumber, order, 
					tx_societa, tx_provincia, tx_utente, tx_ente, tx_mostra, tx_codice_transazione, 
					tx_indirizzo_ip, tx_user_email, tx_user_sms, tx_canale_pagamento, 
					tx_strumento, tx_tipo_carta, tx_id_bollettino, tx_servizio, tx_stato_rendicontazione, 
					tx_stato_riconciliazione, tx_importo_da, tx_importo_a, tx_data_da, tx_data_a, 
					chiaveQuadratura, tx_id_Terminale_Atm, tx_id_Transazione_Atm, 
					tx_chiave_rendicontazione, tx_gg_storno, tx_id_terminale_pos_fisico, tx_data_accr_da, 
					tx_data_accr_a, tx_codice_IUV, idFlussoQuadratura, tx_recuperate,tx_codice_fiscale);
			if (data != null) {
				loadWebRowSet(data);
				
				System.out.println("PARAMETRI PER CHIAMARE LA SP: " + 
				 "CALL `PAY00DB0_SE000SV`.`PYTRASP_LST_MG_CSV`( " + 
				  "'"+tx_societa+"' ,"+
				  "'"+tx_provincia+"' ,"+
				  "'"+tx_provincia+"' ,"+
				  "'"+tx_utente+"' ,"+
				  "'"+tx_ente+"' ,"+ // 5
				  "'"+tx_mostra+"' ,"+
				  "'"+tx_codice_transazione+"' ,"+
				  "'"+tx_indirizzo_ip+"' ,"+
				  "'"+tx_user_email+"' ,"+
				  "'"+tx_user_sms+"' ,"+ // 10
				  "'"+tx_canale_pagamento+"' ,"+
				  "'"+tx_strumento+"' ,"+ 
				  "'"+tx_tipo_carta+"' ,"+
				  "'"+tx_id_bollettino+"' ,"+ 
				  "'"+tx_servizio+"' ,"+ // 15
				  "'"+tx_stato_rendicontazione+"' ,"+
				  "'"+tx_importo_da+"' ,"+
				  "'"+tx_importo_a+"' ,"+
				  "'"+tx_data_da+"' ,"+ // 20
				  "'"+tx_data_a+"' ,"+
				  "'"+chiaveQuadratura+"' ,"+
				  "'"+tx_id_Terminale_Atm+"' ,"+
				  "'"+tx_id_Transazione_Atm+"' ,"+
				  "'"+tx_chiave_rendicontazione+"' ,"+ // 25
				  "'"+tx_gg_storno+"' ,"+
				  "'"+tx_id_terminale_pos_fisico+"' ,"+
				  "'"+tx_data_accr_da+"' ,"+
				  "'"+tx_codice_IUV+"' ,"+
				  "'"+idFlussoQuadratura+"' ,"+ // 30
				  "'"+tx_recuperate+"' ,"+
				  "'"+tx_codice_fiscale+"'"+");" );
				
				System.out.println("[CORE - ManagerTransazioniDao - getListaTransazioni()] FINE CHIAMATA e FINE LOADWEBROWSET");
				return getWebRowSetXml();
			}
			return null;

		} catch (Exception e) {
			throw new DaoException(e);
		}
		finally {
			//inizio LP PG21XX04 Leak
			//DaoUtil.closeResultSet(data);
			//DaoUtil.closeStatement(callableStatement);
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
			//fine LP PG21XX04 Leak
		}
	}

	/*public WebRowSet getListaTransazioniRowSet(String tx_societa, String tx_provincia, String tx_utente, String tx_ente, String tx_mostra, String tx_codice_transazione,
			String tx_indirizzo_ip, String tx_user_email, String tx_user_sms, String tx_canale_pagamento,
			String tx_strumento, String tx_tipo_carta, String tx_id_bollettino, String tx_servizio,
			String tx_stato_rendicontazione, String tx_stato_riconciliazione, String tx_importo_da, 
			String tx_importo_a, String tx_data_da,String tx_data_a, int chiaveQuadratura) throws DaoException {
		CallableStatement callableStatement = null;
		ResultSet data = null;
		try {
			data = getListaTransazioni(callableStatement, 0, 0, "", tx_societa, tx_provincia, tx_utente, tx_ente, tx_mostra, tx_codice_transazione, tx_indirizzo_ip, tx_user_email, tx_user_sms, tx_canale_pagamento, tx_strumento, tx_tipo_carta, tx_id_bollettino, tx_servizio, tx_stato_rendicontazione, tx_stato_riconciliazione, tx_importo_da, tx_importo_a, tx_data_da, tx_data_a, chiaveQuadratura,null, null,null,0, "");
			if (data != null) {
				loadWebRowSet(data);
				return getWebRowSet();
			}
			return null;

		} 
		catch (Exception e) {
			throw new DaoException(e);
		}
		finally {
			DaoUtil.closeResultSet(data);
			DaoUtil.closeStatement(callableStatement);
		}
	}*/

	//PG170010_001 GG - Introdotto idFlussoQuadratura
	//PG150180_001 GG - Introdotto codice iuv
//	public String getListaTransazioniCsv(String tx_societa, String tx_provincia, String tx_utente, String tx_ente, String tx_mostra, String tx_codice_transazione,
	public String getListaTransazioniCsv(String tx_societa, String tx_provincia, String tx_utente, String tx_ente, String tx_mostra, String tx_codice_transazione,
			String tx_indirizzo_ip, String tx_user_email, String tx_user_sms, String tx_canale_pagamento,
			String tx_strumento, String tx_tipo_carta, String tx_id_bollettino, String tx_servizio,
			String tx_stato_rendicontazione, String tx_stato_riconciliazione, String tx_importo_da, 
			String tx_importo_a, String tx_data_da,String tx_data_a, int chiaveQuadratura,
			String tx_id_Terminale_Atm, String tx_id_transazione_Atm, String chiaveRendicontazione, 
			String tx_id_terminale_pos_fisico, String tx_data_accr_da, String tx_data_accr_a, String tx_codice_IUV, String idFlussoQuadratura, String cuteCute,String codice_fiscale) throws DaoException	{	

		CallableStatement callableStatement = null;
		ResultSet rsDati = null;
				
		String result="";
		//WebRowSet rowSet = null;
		try {
		    BigDecimal tx_importo_da_bd = new BigDecimal("0");
		    BigDecimal tx_importo_a_bd = new BigDecimal("0");
		    if((tx_importo_da != null)&&(!tx_importo_da.equals("")))
		    		tx_importo_da_bd = new BigDecimal(tx_importo_da);
		    if((tx_importo_a != null)&&(!tx_importo_a.equals("")))
		    		tx_importo_a_bd = new BigDecimal(tx_importo_a);
			callableStatement = prepareCall(Routines.TMG_RECUPERA_TRANSAZIONI_CSV.routine());
			callableStatement.setString(1, (tx_societa == null) || (tx_societa.equals(StoredProcConf.SOCIETA_ALL_VALUE.getParam())) ? "" :  tx_societa);
			callableStatement.setString(2, (tx_provincia == null) || (tx_provincia.equals(StoredProcConf.PROVINCIA_ALL_VALUE.getParam())) ? "" :  tx_provincia);
			callableStatement.setString(3, (tx_utente == null) || (tx_utente.equals(StoredProcConf.UTENTE_ALL_VALUE.getParam())) ? "" :  tx_utente);
			callableStatement.setString(4, (tx_ente == null) || (tx_ente.equals(StoredProcConf.ENTECONSORZIATO_ALL_VALUE.getParam())) ? "" :  tx_ente);
			callableStatement.setString(5, tx_mostra == null ? "" : tx_mostra);
			callableStatement.setString(6, tx_codice_transazione == null ? "" :  tx_codice_transazione);
			callableStatement.setString(7, tx_indirizzo_ip == null ? "" :  tx_indirizzo_ip);
			callableStatement.setString(8, tx_user_email == null ? "" :  tx_user_email);
			callableStatement.setString(9, tx_user_sms == null ? "" :  tx_user_sms);
			callableStatement.setString(10, (tx_canale_pagamento == null) || (tx_canale_pagamento.equals(StoredProcConf.CANALE_ALL_VALUE.getParam())) ? "" :  tx_canale_pagamento);
			callableStatement.setString(11, (tx_strumento == null)|| (tx_strumento.equals(StoredProcConf.STRUMENTO_ALL_VALUE.getParam())) ? "" :  tx_strumento);
			callableStatement.setString(12, (tx_tipo_carta == null) ||(tx_tipo_carta.equals(StoredProcConf.CARTA_ALL_VALUE.getParam())) ? "" :  tx_tipo_carta);
			callableStatement.setString(13, tx_id_bollettino == null ? "" :  tx_id_bollettino);
			callableStatement.setString(14, (tx_servizio == null)|| (tx_servizio.equals(StoredProcConf.TIPOLOGIA_SERVIZIO_ALL_VALUE.getParam())) ? "" :  tx_servizio);
			callableStatement.setString(15, (tx_stato_rendicontazione == null) || (tx_stato_rendicontazione.equals(StoredProcConf.STATORENDICONTAZIONE_ALL_VALUE.getParam())) ? ""  : tx_stato_rendicontazione);
			callableStatement.setString(16, (tx_stato_riconciliazione == null)|| (tx_stato_riconciliazione.equals(StoredProcConf.STATORICONCILIAZIONE_ALL_VALUE.getParam())) ? "" :  tx_stato_riconciliazione);
			callableStatement.setBigDecimal(17, tx_importo_da_bd);
		    callableStatement.setBigDecimal(18, tx_importo_a_bd);
			callableStatement.setString(19,tx_data_da == null ? "" : tx_data_da);
			callableStatement.setString(20,tx_data_a == null ? "" : tx_data_a);
			callableStatement.setInt(21, chiaveQuadratura);
			callableStatement.setString(22,tx_id_Terminale_Atm == null ? "" : tx_id_Terminale_Atm);
			callableStatement.setString(23,tx_id_transazione_Atm == null ? "" : tx_id_transazione_Atm);
			callableStatement.setString(24,chiaveRendicontazione == null ? "" : chiaveRendicontazione);
			callableStatement.setString(25,tx_id_terminale_pos_fisico == null ? "" : tx_id_terminale_pos_fisico);
			callableStatement.setString(26,tx_data_accr_da == null ? "" : tx_data_accr_da);
			callableStatement.setString(27,tx_data_accr_a == null ? "" : tx_data_accr_a);
			callableStatement.setString(28,tx_codice_IUV == null ? "" :  tx_codice_IUV);			//27032015 GG
			callableStatement.setString(29,idFlussoQuadratura == null ? "" :  idFlussoQuadratura);	//15022017 GG
			callableStatement.setString(30, cuteCute); //PG190120_001
			callableStatement.setString(31, codice_fiscale);
			
			
			StringBuffer sb = new StringBuffer();
			boolean resultsAvailable = callableStatement.execute();
			
			
			while (resultsAvailable) {
			
				
					rsDati = callableStatement.getResultSet();
					
					while(rsDati.next()) {
						
						sb.append(rsDati.getString("RECORD"));
						sb.append("\r\n");
		
							
					}
					
					resultsAvailable = callableStatement.getMoreResults();
				
			}
			
			result = sb.toString();
			
		} 
		catch (Exception e) {
			throw new DaoException(e);
		}
		//inizio LP PG21XX04 Leak
		finally {
			if (rsDati != null) {
				try {
					rsDati.close();
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

		return result;
	}
		
	//PG170010_001 GG - Introdotto idFlussoQuadratura
	//PG150180_001 GG - Introdotto codice iuv
	//inizio LP PG21XX04 Leak
	//Nota. CallableStatement e ResultSet sono chiusi da parte del chiamante 
	//fine LP PG21XX04 Leak
	private ResultSet getListaTransazioni(CallableStatement callableStatement,int rowsPerPage, int pageNumber, String order, 
			String tx_societa, String tx_provincia, String tx_utente, String tx_ente, String tx_mostra, String tx_codice_transazione,
			String tx_indirizzo_ip, String tx_user_email, String tx_user_sms, String tx_canale_pagamento,
			String tx_strumento, String tx_tipo_carta, String tx_id_bollettino, String tx_servizio,
			String tx_stato_rendicontazione, String tx_stato_riconciliazione,String tx_importo_da, 
			String tx_importo_a, String tx_data_da,String tx_data_a, int chiaveQuadratura, String tx_id_Terminale_Atm,String tx_id_Transazione_Atm, 
			String tx_chiave_rendicontazione, int tx_gg_storno, String tx_id_terminale_pos_fisico, String tx_data_accr_da, String tx_data_accr_a, String tx_codice_IUV, String idFlussoQuadratura, String tx_recuperate, String tx_codice_fiscale) throws SQLException, IllegalArgumentException, HelperException {
	   
		BigDecimal tx_importo_da_bd = new BigDecimal("0");
	    BigDecimal tx_importo_a_bd = new BigDecimal("0");
	    if((tx_importo_da != null)&&(!tx_importo_da.equals("")))tx_importo_da_bd = new BigDecimal(tx_importo_da);
	    if((tx_importo_a != null)&&(!tx_importo_a.equals("")))tx_importo_a_bd = new BigDecimal(tx_importo_a);
		callableStatement = prepareCall(Routines.TMG_RECUPERA_TRANSAZIONI.routine());
		callableStatement.setInt(1, pageNumber);
		callableStatement.setInt(2, rowsPerPage);
		callableStatement.setString(3, order == null ? "" : order);
		callableStatement.setString(4, (tx_societa == null) || (tx_societa.equals(StoredProcConf.SOCIETA_ALL_VALUE.getParam())) ? "" :  tx_societa);
		callableStatement.setString(5, (tx_provincia == null) || (tx_provincia.equals(StoredProcConf.PROVINCIA_ALL_VALUE.getParam())) ? "" :  tx_provincia);
		callableStatement.setString(6, (tx_utente == null) || (tx_utente.equals(StoredProcConf.UTENTE_ALL_VALUE.getParam())) ? "" :  tx_utente);
		callableStatement.setString(7, (tx_ente == null) || (tx_ente.equals(StoredProcConf.ENTECONSORZIATO_ALL_VALUE.getParam())) ? "" :  tx_ente);
		callableStatement.setString(8, tx_mostra == null ? "" : tx_mostra);
		callableStatement.setString(9, tx_codice_transazione == null ? "" :  tx_codice_transazione);
		callableStatement.setString(10, tx_indirizzo_ip == null ? "" :  tx_indirizzo_ip);
		callableStatement.setString(11, tx_user_email == null ? "" :  tx_user_email);
		callableStatement.setString(12, tx_user_sms == null ? "" :  tx_user_sms);
		callableStatement.setString(13, (tx_canale_pagamento == null) || (tx_canale_pagamento.equals(StoredProcConf.CANALE_ALL_VALUE.getParam())) ? "" :  tx_canale_pagamento);
		callableStatement.setString(14, (tx_strumento == null)|| (tx_strumento.equals(StoredProcConf.STRUMENTO_ALL_VALUE.getParam())) ? "" :  tx_strumento);
		callableStatement.setString(15, (tx_tipo_carta == null) ||(tx_tipo_carta.equals(StoredProcConf.CARTA_ALL_VALUE.getParam())) ? "" :  tx_tipo_carta);
		callableStatement.setString(16, tx_id_bollettino == null ? "" :  tx_id_bollettino);
		callableStatement.setString(17, (tx_servizio == null)|| (tx_servizio.equals(StoredProcConf.TIPOLOGIA_SERVIZIO_ALL_VALUE.getParam())) ? "" :  tx_servizio);
		callableStatement.setString(18, (tx_stato_rendicontazione == null) || (tx_stato_rendicontazione.equals(StoredProcConf.STATORENDICONTAZIONE_ALL_VALUE.getParam())) ? ""  : tx_stato_rendicontazione);
		callableStatement.setString(19, (tx_stato_riconciliazione == null)|| (tx_stato_riconciliazione.equals(StoredProcConf.STATORICONCILIAZIONE_ALL_VALUE.getParam())) ? "" :  tx_stato_riconciliazione);
		callableStatement.setBigDecimal(20, tx_importo_da_bd);
	    callableStatement.setBigDecimal(21, tx_importo_a_bd);
		callableStatement.setString(22,tx_data_da == null ? "" : tx_data_da);
		callableStatement.setString(23,tx_data_a == null ? "" : tx_data_a);
		callableStatement.setInt(24, chiaveQuadratura);
		callableStatement.setString(25,tx_id_Terminale_Atm == null ? "" : tx_id_Terminale_Atm);
		callableStatement.setString(26,tx_id_Transazione_Atm == null ? "" : tx_id_Transazione_Atm);
		callableStatement.setString(27,tx_chiave_rendicontazione == null ? "" : tx_chiave_rendicontazione);
		callableStatement.setInt(28, tx_gg_storno);
		callableStatement.setString(29, tx_id_terminale_pos_fisico == null ? "" : tx_id_terminale_pos_fisico);
		callableStatement.setString(30,tx_data_accr_da == null ? "" : tx_data_accr_da);
		callableStatement.setString(31,tx_data_accr_a == null ? "" : tx_data_accr_a);
		callableStatement.setString(32,tx_codice_IUV == null ? "" : tx_codice_IUV);
		callableStatement.setString(33,idFlussoQuadratura == null ? "" : idFlussoQuadratura);
		callableStatement.setString(34, tx_recuperate == null ? "" : tx_recuperate);   //PG200050_001 SB
		callableStatement.setString(35, tx_codice_fiscale == null ? "" : tx_codice_fiscale); // PAGONET-437
		
		
		
		callableStatement.registerOutParameter(36, Types.VARCHAR);
		callableStatement.registerOutParameter(37, Types.INTEGER);
		callableStatement.registerOutParameter(38, Types.INTEGER);
		callableStatement.registerOutParameter(39, Types.INTEGER);
		callableStatement.registerOutParameter(40, Types.SMALLINT);
		
		callableStatement.toString();
		
		if(callableStatement.execute())	{
			pageInfo = new PageInfo();
			pageInfo.setPageNumber(pageNumber);
			pageInfo.setRowsPerPage(rowsPerPage);
			pageInfo.setFirstRow(callableStatement.getInt(37));
			pageInfo.setLastRow(callableStatement.getInt(38));
			pageInfo.setNumRows(callableStatement.getInt(39));
			pageInfo.setNumPages(callableStatement.getInt(40));

			ResultSet data = callableStatement.getResultSet();
			return data;
		}
		return null;
	}

	//PG160170_001 GG - Introdotto idFlussoQuadratura
	//PG150180_001 GG - Introdotto codice iuv
	public RiepilogoTransazioniGateway getRiepilogoTransazioniGateway(String tx_societa, String tx_provincia, String tx_utente, String tx_ente, String tx_codice_transazione,
			String tx_indirizzo_ip, String tx_user_email, String tx_user_sms, String tx_canale_pagamento,
			String tx_strumento, String tx_tipo_carta, String tx_id_bollettino, String tx_servizio,
			String tx_stato_rendicontazione, String tx_stato_riconciliazione,String tx_importo_da, 
			String tx_importo_a, String tx_data_da,String tx_data_a, int chiaveQuadratura, String tx_id_Terminale_Atm, String tx_id_Transazione_Atm,
			String chiaveRendicontazione, String tx_mostra, String tx_id_terminale_pos_fisico, String tx_data_accr_da, String tx_data_accr_a, String tx_codice_IUV, String idFlussoQuadratura, String tx_recuperate,String tx_codice_fiscale) throws DaoException {
		    BigDecimal tx_importo_da_bd = new BigDecimal("0");
		    BigDecimal tx_importo_a_bd = new BigDecimal("0");
			CallableStatement callableStatement = null;
			ResultSet data = null;
			
		try {
			if((tx_importo_da != null)&&(!tx_importo_da.equals("")))tx_importo_da_bd = new BigDecimal(tx_importo_da);
		    if((tx_importo_a != null)&&(!tx_importo_a.equals("")))tx_importo_a_bd = new BigDecimal(tx_importo_a);
			callableStatement = prepareCall(Routines.TMG_RECUPERA_TRANSAZIONI_GROUPED.routine());
			callableStatement.setString(1, (tx_societa == null) || (tx_societa.equals(StoredProcConf.SOCIETA_ALL_VALUE.getParam())) ? "" :  tx_societa);
			callableStatement.setString(2, (tx_provincia == null) || (tx_provincia.equals(StoredProcConf.PROVINCIA_ALL_VALUE.getParam())) ? "" :  tx_provincia);
			callableStatement.setString(3, (tx_ente == null) || (tx_ente.equals(StoredProcConf.ENTECONSORZIATO_ALL_VALUE.getParam())) ? "" :  tx_ente);
			callableStatement.setString(4, (tx_utente == null) || (tx_utente.equals(StoredProcConf.UTENTE_ALL_VALUE.getParam())) ? "" :  tx_utente);
			callableStatement.setString(5, tx_codice_transazione == null ? "" :  tx_codice_transazione);
			callableStatement.setString(6, tx_indirizzo_ip == null ? "" :  tx_indirizzo_ip);
			callableStatement.setString(7, tx_user_email == null ? "" :  tx_user_email);
			callableStatement.setString(8, tx_user_sms == null ? "" :  tx_user_sms);
			callableStatement.setString(9, (tx_canale_pagamento == null) || (tx_canale_pagamento.equals(StoredProcConf.CANALE_ALL_VALUE.getParam())) ? "" :  tx_canale_pagamento);
			callableStatement.setString(10, (tx_strumento == null)|| (tx_strumento.equals(StoredProcConf.STRUMENTO_ALL_VALUE.getParam())) ? "" :  tx_strumento);
			callableStatement.setString(11, (tx_tipo_carta == null) ||(tx_tipo_carta.equals(StoredProcConf.CARTA_ALL_VALUE.getParam())) ? "" :  tx_tipo_carta);
			callableStatement.setString(12, tx_id_bollettino == null ? "" :  tx_id_bollettino);
			callableStatement.setString(13, (tx_servizio == null)|| (tx_servizio.equals(StoredProcConf.TIPOLOGIA_SERVIZIO_ALL_VALUE.getParam())) ? "" :  tx_servizio);
			callableStatement.setString(14, (tx_stato_rendicontazione == null) || (tx_stato_rendicontazione.equals(StoredProcConf.STATORENDICONTAZIONE_ALL_VALUE.getParam())) ? ""  : tx_stato_rendicontazione);
			callableStatement.setString(15, (tx_stato_riconciliazione == null)|| (tx_stato_riconciliazione.equals(StoredProcConf.STATORICONCILIAZIONE_ALL_VALUE.getParam())) ? "" :  tx_stato_riconciliazione);
			callableStatement.setBigDecimal(16, tx_importo_da_bd);
		    callableStatement.setBigDecimal(17, tx_importo_a_bd);
			callableStatement.setString(18,tx_data_da == null ? "" : tx_data_da);
			callableStatement.setString(19,tx_data_a == null ? "" : tx_data_a);
			callableStatement.setInt(20, chiaveQuadratura);
			callableStatement.setString(21,tx_id_Terminale_Atm == null ? "" : tx_id_Terminale_Atm);
			callableStatement.setString(22,tx_id_Transazione_Atm == null ? "" : tx_id_Transazione_Atm);
			callableStatement.setString(23,chiaveRendicontazione == null ? "" : chiaveRendicontazione);
			callableStatement.setString(24,tx_id_terminale_pos_fisico == null ? "" : tx_id_terminale_pos_fisico);
			callableStatement.setString(25,tx_data_accr_da == null ? "" : tx_data_accr_da);
			callableStatement.setString(26,tx_data_accr_a == null ? "" : tx_data_accr_a);
			callableStatement.setString(27,tx_codice_IUV == null ? "" :  tx_codice_IUV);			//17032015 GG
			callableStatement.setString(28,idFlussoQuadratura == null ? "" :  idFlussoQuadratura);	//15022017 GG
			callableStatement.setString(29,tx_recuperate==null ? "" : tx_recuperate);
			callableStatement.setString(30,tx_mostra == null ? "" : tx_mostra);
			callableStatement.setString(31, tx_codice_fiscale == null ? "" : tx_codice_fiscale); // PAGONET-437
			
			callableStatement.registerOutParameter(32, Types.DECIMAL);
			callableStatement.registerOutParameter(33, Types.DECIMAL);
			callableStatement.registerOutParameter(34, Types.DECIMAL);
			callableStatement.registerOutParameter(35, Types.DECIMAL);
			callableStatement.registerOutParameter(36, Types.DECIMAL);
			callableStatement.registerOutParameter(37, Types.DECIMAL);

			if(callableStatement.execute()) {
				BigDecimal totale = callableStatement.getBigDecimal(36);
				List<TransazioniGrouped> list = new ArrayList<TransazioniGrouped>();
				data = callableStatement.getResultSet();
				while(data.next()) {
					TransazioniGrouped bean = new TransazioniGrouped(data);
					list.add(bean);
				}
				//data = callableStatement.getResultSet();
				//loadWebRowSet(data);
				String listaXml = null;//getWebRowSetXml();
				return new RiepilogoTransazioniGateway(list, listaXml, totale);
			}
			return null;

		} 
		catch (Exception e) {
			throw new DaoException(e);
		}
		finally {
			//inizio LP PG21XX04 Leak
			//DaoUtil.closeResultSet(data);
			//DaoUtil.closeStatement(callableStatement);
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
			//fine LP PG21XX04 Leak
		}
	}
	
	//PG160170_001 GG - Introdotto idFlussoQuadratura
	//PG150180_001 GG - Introdotto codice iuv
	public RiepilogoTransazioniCompletate getRiepilogoTransazioniCompletate(String tx_societa, String tx_provincia, String tx_utente, String tx_ente, String tx_codice_transazione,
			String tx_indirizzo_ip, String tx_user_email, String tx_user_sms, String tx_canale_pagamento,
			String tx_strumento, String tx_tipo_carta, String tx_id_bollettino, String tx_servizio,
			String tx_stato_rendicontazione, String tx_stato_riconciliazione,String tx_importo_da, 
			String tx_importo_a, String tx_data_da,String tx_data_a, int chiaveQuadratura,String tx_id_Terminale_Atm, String tx_id_Transazione_Atm,
			String chiaveRendicontazione,
			String tx_mostra, String tx_id_terminale_pos_fisico, String tx_data_accr_da, String tx_data_accr_a, String tx_codice_IUV, String idFlussoQuadratura, String tx_recuperate,String dbSchemaCodSocieta,String tx_codice_fiscale) throws DaoException
	{
	    BigDecimal tx_importo_da_bd = new BigDecimal("0");
	    BigDecimal tx_importo_a_bd = new BigDecimal("0");
		CallableStatement callableStatement = null;
		ResultSet data = null;
		try {
			if((tx_importo_da != null)&&(!tx_importo_da.equals("")))tx_importo_da_bd = new BigDecimal(tx_importo_da);
		    if((tx_importo_a != null)&&(!tx_importo_a.equals("")))tx_importo_a_bd = new BigDecimal(tx_importo_a);
			callableStatement = prepareCall(Routines.TMG_RECUPERA_TRANSAZIONI_GROUPED_SUCCESS.routine());
			callableStatement.setString(1, (tx_societa == null) || (tx_societa.equals(StoredProcConf.SOCIETA_ALL_VALUE.getParam())) ? "" :  tx_societa);
			callableStatement.setString(2, (tx_provincia == null) || (tx_provincia.equals(StoredProcConf.PROVINCIA_ALL_VALUE.getParam())) ? "" :  tx_provincia);
			callableStatement.setString(3, (tx_ente == null) || (tx_ente.equals(StoredProcConf.ENTECONSORZIATO_ALL_VALUE.getParam())) ? "" :  tx_ente);
			callableStatement.setString(4, (tx_utente == null) || (tx_utente.equals(StoredProcConf.UTENTE_ALL_VALUE.getParam())) ? "" :  tx_utente);
			callableStatement.setString(5, tx_codice_transazione == null ? "" :  tx_codice_transazione);
			callableStatement.setString(6, tx_indirizzo_ip == null ? "" :  tx_indirizzo_ip);
			callableStatement.setString(7, tx_user_email == null ? "" :  tx_user_email);
			callableStatement.setString(8, tx_user_sms == null ? "" :  tx_user_sms);
			callableStatement.setString(9, (tx_canale_pagamento == null) || (tx_canale_pagamento.equals(StoredProcConf.CANALE_ALL_VALUE.getParam())) ? "" :  tx_canale_pagamento);
			callableStatement.setString(10, (tx_strumento == null)|| (tx_strumento.equals(StoredProcConf.STRUMENTO_ALL_VALUE.getParam())) ? "" :  tx_strumento);
			callableStatement.setString(11, (tx_tipo_carta == null) ||(tx_tipo_carta.equals(StoredProcConf.CARTA_ALL_VALUE.getParam())) ? "" :  tx_tipo_carta);
			callableStatement.setString(12, tx_id_bollettino == null ? "" :  tx_id_bollettino);
			callableStatement.setString(13, (tx_servizio == null)|| (tx_servizio.equals(StoredProcConf.TIPOLOGIA_SERVIZIO_ALL_VALUE.getParam())) ? "" :  tx_servizio);
			callableStatement.setString(14, (tx_stato_rendicontazione == null) || (tx_stato_rendicontazione.equals(StoredProcConf.STATORENDICONTAZIONE_ALL_VALUE.getParam())) ? ""  : tx_stato_rendicontazione);
			callableStatement.setString(15, (tx_stato_riconciliazione == null)|| (tx_stato_riconciliazione.equals(StoredProcConf.STATORICONCILIAZIONE_ALL_VALUE.getParam())) ? "" :  tx_stato_riconciliazione);
			callableStatement.setBigDecimal(16, tx_importo_da_bd);
		    callableStatement.setBigDecimal(17, tx_importo_a_bd);
			callableStatement.setString(18,tx_data_da == null ? "" : tx_data_da);
			callableStatement.setString(19,tx_data_a == null ? "" : tx_data_a);
			callableStatement.setInt(20, chiaveQuadratura);
			callableStatement.setString(21,tx_id_Terminale_Atm == null ? "" : tx_id_Terminale_Atm);
			callableStatement.setString(22,tx_id_Transazione_Atm == null ? "" : tx_id_Transazione_Atm);
			callableStatement.setString(23,chiaveRendicontazione == null ? "" : chiaveRendicontazione);
			callableStatement.setString(24,tx_id_terminale_pos_fisico == null ? "" : tx_id_terminale_pos_fisico);
			callableStatement.setString(25,tx_data_accr_da == null ? "" : tx_data_accr_da);
			callableStatement.setString(26,tx_data_accr_a == null ? "" : tx_data_accr_a);
			callableStatement.setString(27,tx_codice_IUV == null ? "" :  tx_codice_IUV);			//17032015 GG
			callableStatement.setString(28,idFlussoQuadratura == null ? "" :  idFlussoQuadratura);	//15022017 GG
			callableStatement.setString(29,tx_recuperate == null ? "" :  tx_recuperate);	//PG200050_001
			callableStatement.setString(30, tx_codice_fiscale == null ? "" : tx_codice_fiscale); // PAGONET-437
			
			callableStatement.registerOutParameter(31, Types.DECIMAL);
			callableStatement.registerOutParameter(32, Types.DECIMAL);
			callableStatement.registerOutParameter(33, Types.DECIMAL);
			callableStatement.registerOutParameter(34, Types.DECIMAL);
			callableStatement.registerOutParameter(35, Types.DECIMAL);
 
			callableStatement.registerOutParameter(36, Types.DECIMAL);
			callableStatement.setString(37,tx_mostra == null ? "" : tx_mostra);

			if(callableStatement.execute()) {
				BigDecimal totale = callableStatement.getBigDecimal(36);
				List<TransazioniGroupedSuccess> list = new ArrayList<TransazioniGroupedSuccess>();
				data = callableStatement.getResultSet();
				while(data.next()) {
					TransazioniGroupedSuccess bean = new TransazioniGroupedSuccess(data);
					list.add(bean);
				}
				//data = callableStatement.getResultSet();
				//loadWebRowSet(data);
				String listaXml = null;//getWebRowSetXml();
				return new RiepilogoTransazioniCompletate(list, listaXml, totale);
			}
			return null;
		} 
		catch (Exception e) {
			throw new DaoException(e);
		}
		finally {
			//inizio LP PG21XX04 Leak
			//DaoUtil.closeResultSet(data);
			//DaoUtil.closeStatement(callableStatement);
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
			//fine LP PG21XX04 Leak
		}
	}

	public String getListaCanaleXml() throws DaoException {
		CallableStatement callableStatement = null;
		ResultSet data = null;
		try {
			callableStatement = prepareCall(Routines.TX_LISTA_CANALE.routine());
			callableStatement.setString(1, StoredProcConf.CANALE_ALL_VALUE.getParam());
			callableStatement.setString(2, StoredProcConf.CANALE_ALL_TEXT.getParam());
			callableStatement.setString(3, StoredProcConf.CANALE_PREFIX.getParam());
			if(callableStatement.execute()) {
				data = callableStatement.getResultSet();
				loadWebRowSet(data);
				return getWebRowSetXml();
			}
			return null;

		} 
		catch (Exception e) {
			throw new DaoException(e);
		}
		finally {
			//inizio LP PG21XX04 Leak
			//DaoUtil.closeResultSet(data);
			//DaoUtil.closeStatement(callableStatement);
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
			//fine LP PG21XX04 Leak
		}
	}

	
	public String getListaTipoCartaXml() throws DaoException {
		CallableStatement callableStatement = null;
		ResultSet data = null;
		try {
			callableStatement = prepareCall(Routines.TX_LISTA_TIPO_CARTA.routine());
			callableStatement.setString(1, StoredProcConf.CARTA_ALL_VALUE.getParam());
			callableStatement.setString(2, StoredProcConf.CARTA_ALL_TEXT.getParam());
			callableStatement.setString(3, StoredProcConf.CARTA_PREFIX.getParam());
			if(callableStatement.execute()) {
				data = callableStatement.getResultSet();
				loadWebRowSet(data);
				return getWebRowSetXml();
			}
			return null;
		}
		catch (Exception e) {
			throw new DaoException(e);
		}
		finally {
			//inizio LP PG21XX04 Leak
			//DaoUtil.closeResultSet(data);
			//DaoUtil.closeStatement(callableStatement);
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
			//fine LP PG21XX04 Leak
		}
	}
	
	/**
	 * Restituisce la lista dei gateway come WebRowSetXml
	 * tenendo conto della coppia Socieà/utente
	 * 
	 * @param codiceSocieta
	 * @param codiceUtente
	 * @return String
	 * @throws DaoException
	 */
	public String getListaTipoCartaXml_DDL
	(
		String codiceSocieta,
		String codiceUtente
	) throws DaoException {
		CallableStatement callableStatement = null;
		ResultSet data = null;
		try {
			callableStatement = prepareCall(Routines.TMG_GATEWAY_GET_DDL.routine());
			callableStatement.setString(1, codiceSocieta);
			callableStatement.setString(2, codiceUtente);
			callableStatement.registerOutParameter(3, Types.VARCHAR);
			if(callableStatement.execute()) {
				data = callableStatement.getResultSet();
				loadWebRowSet(data);
				return getWebRowSetXml();
			}
			return null;
		}
		catch (Exception e) {
			throw new DaoException(e);
		}
		finally {
			//inizio LP PG21XX04 Leak
			//DaoUtil.closeResultSet(data);
			//DaoUtil.closeStatement(callableStatement);
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
			//fine LP PG21XX04 Leak
		}
	}

	public String getListaStrumentiXml() throws DaoException {
		CallableStatement callableStatement = null;
		ResultSet data = null;
		try {
			callableStatement = prepareCall(Routines.TX_SERVIXIO.routine());
			callableStatement.setString(1, StoredProcConf.STRUMENTO_ALL_VALUE.getParam());
			callableStatement.setString(2, StoredProcConf.STRUMENTO_ALL_TEXT.getParam());
			callableStatement.setString(3, StoredProcConf.STRUMENTO_PREFIX.getParam());
			if(callableStatement.execute()) {
				data = callableStatement.getResultSet();
				loadWebRowSet(data);
				return getWebRowSetXml();
			}
			return null;
		}
		catch (Exception e) {
			throw new DaoException(e);
		}
		finally {
			//inizio LP PG21XX04 Leak
			//DaoUtil.closeResultSet(data);
			//DaoUtil.closeStatement(callableStatement);
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
			//fine LP PG21XX04 Leak
		}
	}

	public String getListaTipologiaServizioXml() throws DaoException {
		CallableStatement callableStatement = null;
		ResultSet data = null;
		try {
			callableStatement = prepareCall(Routines.TMG_TIPOLOGIA_SERVIZIO_DDL.routine());
			callableStatement.setString(1, StoredProcConf.TIPOLOGIA_SERVIZIO_ALL_VALUE.getParam());
			callableStatement.setString(2, StoredProcConf.TIPOLOGIA_SERVIZIO_ALL_TEXT.getParam());
			callableStatement.setString(3, StoredProcConf.TIPOLOGIA_SERVIZIO_PREFIX.getParam());
			if(callableStatement.execute()) {
				data = callableStatement.getResultSet();
				loadWebRowSet(data);
				return getWebRowSetXml();
			}
			return null;
		}
		catch (Exception e) {
			throw new DaoException(e);
		}
		finally {
			//inizio LP PG21XX04 Leak
			//DaoUtil.closeResultSet(data);
			//DaoUtil.closeStatement(callableStatement);
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
			//fine LP PG21XX04 Leak
		}
	}

	/**
	 * Restituisce, come WebRowSetXml, la lista delle tipologie
	 * di servizio in funzione della società.
	 * ===========================================================
	 * UTILIZZO: Alimenta la DDL "Tipologia Servizio" 
	 * ===========================================================
	 * NOTA:Sotituirà "getListaTipologiaServizioXml" che non 
	 * tiene conto della Società.
	 * ===========================================================
	 * @param codiceSocieta
	 * @return String
	 * @throws DaoException
	 */
	public String getListaTipologiaServizioXml_DDL(String codiceSocieta) throws DaoException {
		CallableStatement callableStatement = null;
		ResultSet data = null;
		try {
			callableStatement = prepareCall(Routines.TMG_TIPOLOGIA_SERVIZIO_GET_DDL.routine());
			callableStatement.setString(1, codiceSocieta == null ? "" : codiceSocieta);
			if(callableStatement.execute()) {
				data = callableStatement.getResultSet();
				loadWebRowSet(data);
				return getWebRowSetXml();
			}
			return null;
		}
		catch (Exception e) {
			throw new DaoException(e);
		}
		finally {
			//inizio LP PG21XX04 Leak
			//DaoUtil.closeResultSet(data);
			//DaoUtil.closeStatement(callableStatement);
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
			//fine LP PG21XX04 Leak
		}
	}
	
	
	/**
	 * Restituisce, come WebRowSetXml, la lista delle tipologie
	 * di servizio in funzione della società, codice utente, chiave ente e, nel caso di utenti AMEN,
	 * un elenco di tipologie servizio nel seguente formato: 'TSE','TAR','ICI',....
	 * Le tipologie servizio sono estratte solo se abilitate nella tabella PYCESTB indipendentemente dal flag di attivazione 
	 * ===========================================================
	 * UTILIZZO: Alimenta la DDL "Tipologia Servizio" 
	 * ===========================================================
	 * NOTA:Sotituirà "getListaTipologiaServizioXml_DDL" che non 
	 * tiene conto della Società.
	 * ===========================================================
	 * @param codiceSocieta
	 * @param codiceUtente
	 * @param chiaveEnte
	 * @param listaTipologiaServizio
	 * @return String
	 * @throws DaoException
	 */
	public String getListaTipologiaServizioXml_DDL_2(String codiceSocieta, String codiceUtente, String chiaveEnte, String listaTipologiaServizio) throws DaoException {
		CallableStatement callableStatement = null;
		ResultSet data = null;
		try {
			callableStatement = prepareCall(Routines.PYTSESP_DDL_2.routine());
			callableStatement.setString(1, codiceSocieta == null ? "" : codiceSocieta);
			callableStatement.setString(2, codiceUtente == null ? "" : codiceUtente);
			callableStatement.setString(3, chiaveEnte == null ? "" : chiaveEnte);
			callableStatement.setString(4, listaTipologiaServizio == null ? "" : listaTipologiaServizio);
			if(callableStatement.execute()) {
				data = callableStatement.getResultSet();
				loadWebRowSet(data);
				return getWebRowSetXml();
			}
			return null;
		}
		catch (Exception e) {
			throw new DaoException(e);
		}
		finally {
			//inizio LP PG21XX04 Leak
			//DaoUtil.closeResultSet(data);
			//DaoUtil.closeStatement(callableStatement);
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
			//fine LP PG21XX04 Leak
		}
	}
	
	//YLM PG22XX06 INIZIO DDL TIPOLOGIE SERVIZI APP IO 
	public String getListaTipologiaServizioAPPIOXml_DDL(String codiceSocieta, String codiceUtente , String chiaveEnte) throws DaoException {
		CallableStatement callableStatement = null;
		ResultSet data = null;
		try {
			callableStatement = prepareCall(Routines.TIO_DODDL.routine());
			callableStatement.setString(1, codiceSocieta == null ? "" : codiceSocieta);
			callableStatement.setString(2, codiceUtente == null ? "" : codiceUtente);
			callableStatement.setString(3, chiaveEnte == null ? "" : chiaveEnte);
			if(callableStatement.execute()) {
				data = callableStatement.getResultSet();
				loadWebRowSet(data);
				return getWebRowSetXml();
			}
			return null;
		}
		catch (Exception e) {
			throw new DaoException(e);
		}
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
	}
	//YLM PG22XX06 FINE DDL TIPOLOGIE SERVIZI APP IO 
	

	public String getListaGatewayXml() throws DaoException	{
		CallableStatement callableStatement = null;
		ResultSet data = null;
		try {
			callableStatement = prepareCall(Routines.TMG_GATEWAY_DDL.routine());
			if(callableStatement.execute()) {
				data = callableStatement.getResultSet();
				loadWebRowSet(data);
				return getWebRowSetXml();
			}
			return null;
		}
		catch (Exception e) {
			throw new DaoException(e);
		}
		finally {
			//inizio LP PG21XX04 Leak
			//DaoUtil.closeResultSet(data);
			//DaoUtil.closeStatement(callableStatement);
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
			//fine LP PG21XX04 Leak
		}
	}

	/**
	 * Restituisce, come WebRowSetXml, la lista dei Gateway/Carte 
	 * in funzione della coppia Società/Utente.
	 * ===========================================================
	 * UTILIZZO: Alimenta la DDL "Tipo Carta" 
	 * ===========================================================
	 * NOTA: Sotituirà "getListaGatewayXml" che non 
	 * tiene conto di Utente e Società.
	 * ===========================================================
	 * @param codiceSocieta
	 * @param codiceUtente
	 * @return String
	 * @throws DaoException
	 */
	public String getListaGatewayXml_DDL
	(
			String codiceSocieta,
			String codiceUtente
	) 
	throws DaoException
	{
		CallableStatement callableStatement = null;
		ResultSet data = null;
		try {
			callableStatement = prepareCall(Routines.TMG_GATEWAY_GET_DDL.routine());
			callableStatement.setString(1, codiceSocieta == null ? "" :codiceSocieta );
			callableStatement.setString(2, codiceUtente == null ? "" : codiceUtente);
			callableStatement.registerOutParameter(3, Types.VARCHAR);
			if(callableStatement.execute()) {
				data = callableStatement.getResultSet();
				loadWebRowSet(data);
				return getWebRowSetXml();
			}
			return null;
		}
		catch (Exception e) {
			throw new DaoException(e);
		}
		finally {
			//inizio LP PG21XX04 Leak
			//DaoUtil.closeResultSet(data);
			//DaoUtil.closeStatement(callableStatement);
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
			//fine LP PG21XX04 Leak
		}
	}

	public String getListaSocietaXml() throws DaoException	{
		CallableStatement callableStatement = null;
		ResultSet data = null;
		try {
			callableStatement = prepareCall(Routines.TMG_SOCIETA_DDL.routine());
			if(callableStatement.execute()) {
				data = callableStatement.getResultSet();
				loadWebRowSet(data);
				return getWebRowSetXml();
			}
			return null;
		}
		catch (Exception e) {
			throw new DaoException(e);
		}
		finally {
			//inizio LP PG21XX04 Leak
			//DaoUtil.closeResultSet(data);
			//DaoUtil.closeStatement(callableStatement);
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
			//fine LP PG21XX04 Leak
		}
	}

	/**
	 * Sostituirà getListaSocietaXml()
	 * 
	 * Restitiusce la lista delle Società
	 * 
	 * @return String
	 * @throws DaoException
	 */
	
	public String getListaSocietaXml_DDL() throws DaoException	{
		CallableStatement callableStatement = null;
		ResultSet data = null;
		try {
			callableStatement = prepareCall(Routines.TMG_SOCIETA_GET_DDL.routine());
			if(callableStatement.execute()) {
				data = callableStatement.getResultSet();
				loadWebRowSet(data);
				return getWebRowSetXml();
			}
			return null;
		}
		catch (Exception e) {
			throw new DaoException(e);
		}
		finally {
			//inizio LP PG21XX04 Leak
			//DaoUtil.closeResultSet(data);
			//DaoUtil.closeStatement(callableStatement);
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
			//fine LP PG21XX04 Leak
		}
	}
	
	
	public String getListaProvinceXml(String codiceSocieta) throws DaoException	{
		CallableStatement callableStatement = null;
		ResultSet data = null;
		try {
			callableStatement = prepareCall(Routines.TMG_PROVINCIA_DDL.routine());
			callableStatement.setString(1, codiceSocieta);
			if(callableStatement.execute()) {
				data = callableStatement.getResultSet();
				loadWebRowSet(data);
				return getWebRowSetXml();
			}
			return null;
		}
		catch (Exception e) {
			throw new DaoException(e);
		}
		finally {
			//inizio LP PG21XX04 Leak
			//DaoUtil.closeResultSet(data);
			//DaoUtil.closeStatement(callableStatement);
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
			//fine LP PG21XX04 Leak
		}
	}
	/**
	 * sostituirà getListaProvinceXml() 
	 * 
	 * restituisce la lista delle province
	 * 
	 * @param codiceSocieta
	 * @return String
	 * @throws DaoException
	 */
	public String getListaProvinceXml_DDL(String codiceSocieta) throws DaoException	{
		CallableStatement callableStatement = null;
		ResultSet data = null;
		try {
			callableStatement = prepareCall(Routines.TMG_PROVINCIA_GET_DDL.routine());
			callableStatement.setString(1, codiceSocieta == null ? "" : codiceSocieta);
			callableStatement.registerOutParameter(2, Types.VARCHAR);
			if(callableStatement.execute()) {
				data = callableStatement.getResultSet();
				loadWebRowSet(data);
				return getWebRowSetXml();
			}
			return null;
		}
		catch (Exception e) {
			throw new DaoException(e);
		}
		finally {
			//inizio LP PG21XX04 Leak
			//DaoUtil.closeResultSet(data);
			//DaoUtil.closeStatement(callableStatement);
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
			//fine LP PG21XX04 Leak
		}
	}

	/**
	 * DEVE ESSERE ELIMINATA PERCHE' NELLA NUOVA
	 * GESTIONE LA DDL RELATIVA AGLI UTENTI NON ESISTE PIU'
	 * @param codiceSocieta
	 * @param codiceProvincia
	 * @return
	 * @throws DaoException
	 */
	public String getListaUtentiXml(String codiceSocieta, String codiceProvincia) throws DaoException	{
		CallableStatement callableStatement = null;
		ResultSet data = null;
		try {
			callableStatement = prepareCall(Routines.TMG_UTENTE_DDL.routine());
			callableStatement.setString(1, codiceSocieta);
			callableStatement.setString(2, codiceProvincia);
			if(callableStatement.execute()) {
				data = callableStatement.getResultSet();
				loadWebRowSet(data);
				return getWebRowSetXml();
			}
			return null;
		}
		catch (Exception e) {
			throw new DaoException(e);
		}
		finally {
			//inizio LP PG21XX04 Leak
			//DaoUtil.closeResultSet(data);
			//DaoUtil.closeStatement(callableStatement);
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
			//fine LP PG21XX04 Leak
		}
	}

	/**
	 * sostituirà getListaUtentiXml() perchè questa utilizza per le province
	 * il codice Belfiore invece del codice delle province
	 * 
	 * restituisce la lista degli utenti 
	 * 
	 * @param codiceSocieta
	 * @param codiceProvincia
	 * @return String
	 * @throws DaoException
	 */
	public String getListaUtentiXml_DDL(String codiceSocieta, String codiceProvincia) throws DaoException	{
		CallableStatement callableStatement = null;
		ResultSet data = null;
		try {
			callableStatement = prepareCall(Routines.TMG_UTENTE_GET_DDL.routine());
			callableStatement.setString(1, codiceSocieta == null ? "" : codiceSocieta);
			callableStatement.setString(2, codiceProvincia == null ? "" : codiceProvincia );
			callableStatement.registerOutParameter(3, Types.VARCHAR);
			if(callableStatement.execute()) {
				data = callableStatement.getResultSet();
				loadWebRowSet(data);
				return getWebRowSetXml();
			}
			return null;
		}
		catch (Exception e) {
			throw new DaoException(e);
		}
		finally {
			//inizio LP PG21XX04 Leak
			//DaoUtil.closeResultSet(data);
			//DaoUtil.closeStatement(callableStatement);
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
			//fine LP PG21XX04 Leak
		}
	}

	public String getListaEntiXml(String codiceSocieta, String codiceProvincia, String codiceUtente) throws DaoException	{
		CallableStatement callableStatement = null;
		ResultSet data = null;
		try {
			callableStatement = prepareCall(Routines.TMG_ENTE_DDL.routine());
			callableStatement.setString(1, codiceSocieta);
			callableStatement.setString(2, codiceProvincia);
			callableStatement.setString(3, codiceUtente);
			if(callableStatement.execute()) {
				data = callableStatement.getResultSet();
				loadWebRowSet(data);
				return getWebRowSetXml();
			}
			return null;
		}
		catch (Exception e) {
			throw new DaoException(e);
		}
		finally {
			//inizio LP PG21XX04 Leak
			//DaoUtil.closeResultSet(data);
			//DaoUtil.closeStatement(callableStatement);
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
			//fine LP PG21XX04 Leak
		}
	}
	
	/**
	 * sostituirà getListaEntiXml() 
	 * 
	 * restituisce la lista degli utenti-enti
	 * 
	 * @param codiceSocieta
	 * @param siglaProvincia
	 * @param codiceEnte
	 * @param codiceUtente
	 * @return String
	 * @throws DaoException
	 */
	
	public String getListaUtentiEntiXml_DDL
	(
			String codiceSocieta, 
			String siglaProvincia, 
			String codiceEnte,
			String codiceUtente
	) throws DaoException	{
		CallableStatement callableStatement = null;
		ResultSet data = null;
		try {
			callableStatement = prepareCall(Routines.TMG_ENTE_GET_DDL.routine());
			callableStatement.setString(1, codiceSocieta == null ? "" :codiceSocieta);
			callableStatement.setString(2, siglaProvincia == null ? "" :siglaProvincia);
			callableStatement.setString(3, codiceEnte == null ? "" :codiceEnte);
			callableStatement.setString(4, codiceUtente == null ? "" :codiceUtente);
			callableStatement.registerOutParameter(5, Types.VARCHAR);
			if(callableStatement.execute()) {
				data = callableStatement.getResultSet();
				loadWebRowSet(data);
				return getWebRowSetXml();
			}
			return null;
		}
		catch (Exception e) {
			throw new DaoException(e);
		}
		finally {
			//inizio LP PG21XX04 Leak
			//DaoUtil.closeResultSet(data);
			//DaoUtil.closeStatement(callableStatement);
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
			//fine LP PG21XX04 Leak
		}
	}
	
	public String getListaUtentiEntiGenericiXml_DDL
	(
			String codiceSocieta, 
			String siglaProvincia, 
			String codiceUtente
	) throws DaoException	{
		CallableStatement callableStatement = null;
		ResultSet data = null;
		try {
			callableStatement = prepareCall(Routines.TMG_ENTE_GET_DDL_GENERICI.routine());
			callableStatement.setString(1, codiceSocieta == null ? "" : codiceSocieta);
			callableStatement.setString(2, siglaProvincia == null ? "" : siglaProvincia);
			callableStatement.setString(3, codiceUtente == null ? "" : codiceUtente);
			callableStatement.registerOutParameter(4, Types.VARCHAR);
			if(callableStatement.execute()) {
				data = callableStatement.getResultSet();
				loadWebRowSet(data);
				return getWebRowSetXml();
			}
			return null;
		}
		catch (Exception e) {
			throw new DaoException(e);
		}
		finally {
			//inizio LP PG21XX04 Leak
			//DaoUtil.closeResultSet(data);
			//DaoUtil.closeStatement(callableStatement);
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
			//fine LP PG21XX04 Leak
		}
	}
	
	public String getListaPSP() throws DaoException {
		CallableStatement callableStatement = null;
		ResultSet data = null;
		
		try	{
			callableStatement = prepareCall(Routines.PYPSNSP_LST_DDL.routine());
			
			if (callableStatement.execute()) 
			{
				data = callableStatement.getResultSet();
				loadWebRowSet(data);
				return getWebRowSetXml();
			}	
			return null;
		} catch (Exception e) {
			throw new DaoException(e);
		} 
		finally {
			//inizio LP PG21XX04 Leak
			//DaoUtil.closeResultSet(data);
			//DaoUtil.closeStatement(callableStatement);
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
			//fine LP PG21XX04 Leak
		}
	}

	public String getListaBollettiniXml() throws DaoException
	{
		CallableStatement callableStatement = null;
		ResultSet data = null;
		try
		{
			callableStatement = prepareCall(Routines.BOL_RECUPERA_LISTA.routine());
			if(callableStatement.execute())
			{
				data = callableStatement.getResultSet();
				loadWebRowSet(data);
				return getWebRowSetXml();
			}
			return null;
		}
		catch (Exception e) {
			throw new DaoException(e);
		} 
		finally {
			//inizio LP PG21XX04 Leak
			//DaoUtil.closeResultSet(data);
			//DaoUtil.closeStatement(callableStatement);
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
			//fine LP PG21XX04 Leak
		}
	}

	public boolean modificaEmailSms(String codiceTransazione, String emailUtente, String smsUtente, String user) throws DaoException	{
		CallableStatement callableStatement = null;
		try {
			callableStatement = prepareCall(Routines.TMG_UPDATE_EMAIL_SMS.routine());
			System.out.println("codiceTransazione =" + codiceTransazione);
			System.out.println("emailUtente =" + emailUtente);
			System.out.println("smsUtente =" + smsUtente);
			System.out.println("user =" + user);
			callableStatement.setString(1, codiceTransazione);
			callableStatement.setString(2, emailUtente);
			callableStatement.setString(3, smsUtente);
			callableStatement.setString(4, user);
			callableStatement.registerOutParameter(5, Types.INTEGER);
			callableStatement.executeUpdate();
			int numrighe =  callableStatement.getInt(5);
			if (numrighe == 1){
				return true;
			}
			else return false;
		} 
		catch (Exception e) {
			System.out.println(e.getMessage());
			throw new DaoException(e);
		}
		finally {
			//inizio LP PG21XX04 Leak
			//DaoUtil.closeStatement(callableStatement);
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


	public boolean eliminaTransazione(String codiceTransazione) throws DaoException	{
		CallableStatement callableStatement = null;
		try {
			callableStatement = prepareCall(Routines.TMG_ELIMINA_TRANSAZIONE.routine());
			callableStatement.setString(1, codiceTransazione);
			callableStatement.registerOutParameter(2, Types.INTEGER);
			callableStatement.executeUpdate();
			int numrighe =  callableStatement.getInt(2);
			if (numrighe == 1){
				return true;
			}
			else return false;
		} 
		catch (Exception e) {
			throw new DaoException(e);
		}
		finally {
			//inizio LP PG21XX04 Leak
			//DaoUtil.closeStatement(callableStatement);
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

	public boolean modificaRifMovimento(String codiceTransazione, Integer rifMovimento, String user) throws DaoException	{
		CallableStatement callableStatement = null;
		try {
			callableStatement = prepareCall(Routines.TMG_UPDATE_RIF_MOVIMENTO.routine());
			callableStatement.setString(1, codiceTransazione);
			callableStatement.setInt(2, rifMovimento);
			callableStatement.setString(3, user);
			callableStatement.registerOutParameter(4, Types.INTEGER);
			callableStatement.executeUpdate();
			int numrighe =  callableStatement.getInt(4);
			if (numrighe == 1){
				return true;
			}
			else return false;
		} 
		catch (Exception e) {
			throw new DaoException(e);
		}
		finally {
			//inizio LP PG21XX04 Leak
			//DaoUtil.closeStatement(callableStatement);
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

	public String getTransazioneXml(String codiceTransazione) throws DaoException {
		CallableStatement callableStatement = null;
		ResultSet data = null;
		try {
			callableStatement = prepareCall(Routines.TMG_RECUPERA_TRANSAZIONE.routine());
			callableStatement.setString(1, codiceTransazione);
			if(callableStatement.execute()) { 
				data = callableStatement.getResultSet();
				loadWebRowSet(data);
				return getWebRowSetXml();
			}
			return null;

		} 
		catch (Exception e) {
			throw new DaoException(e);
		}
		finally {
			//inizio LP PG21XX04 Leak
			//DaoUtil.closeResultSet(data);
			//DaoUtil.closeStatement(callableStatement);
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
			//fine LP PG21XX04 Leak
		}
	}

	public String getDettaglioTransazione(String codiceTransazione) throws DaoException {
		CallableStatement callableStatement = null;
		ResultSet data = null;
		try {
			callableStatement = prepareCall(Routines.TMG_DETTAGLIO.routine());
			callableStatement.setString(1, codiceTransazione);
			if(callableStatement.execute()) { 
				data = callableStatement.getResultSet();
				loadWebRowSet(data);
				return getWebRowSetXml();
			}
			return null;

		} 
		catch (Exception e) {
			throw new DaoException(e);
		}
		finally {
			//inizio LP PG21XX04 Leak
			//DaoUtil.closeResultSet(data);
			//DaoUtil.closeStatement(callableStatement);
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
			//fine LP PG21XX04 Leak
		}
	}

	public String getDettaglioTransazioneICI(String codiceTransazione) throws DaoException {
		CallableStatement callableStatement = null;
		ResultSet data = null;
		try {
			callableStatement = prepareCall(Routines.TMG_DETTAGLIO_ICI.routine());
			callableStatement.setString(1, codiceTransazione);
			if(callableStatement.execute()) { 
				data = callableStatement.getResultSet();
				loadWebRowSet(data);
				return getWebRowSetXml();
			}
			return null;

		} 
		catch (Exception e) {
			throw new DaoException(e);
		}
		finally {
			//inizio LP PG21XX04 Leak
			//DaoUtil.closeResultSet(data);
			//DaoUtil.closeStatement(callableStatement);
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
			//fine LP PG21XX04 Leak
		}
	}

	public String getDettaglioTransazioneFreccia(String codiceTransazione) throws DaoException {
		CallableStatement callableStatement = null;
		ResultSet data = null;
		try {
			callableStatement = prepareCall(Routines.TMG_DETTAGLIO_FRECCIA.routine());
			callableStatement.setString(1, codiceTransazione);
			if(callableStatement.execute()) { 
				data = callableStatement.getResultSet();
				loadWebRowSet(data);
				return getWebRowSetXml();
			}
			return null;

		} 
		catch (Exception e) {
			throw new DaoException(e);
		}
		finally {
			//inizio LP PG21XX04 Leak
			//DaoUtil.closeResultSet(data);
			//DaoUtil.closeStatement(callableStatement);
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
			//fine LP PG21XX04 Leak
		}
	}
	
	public boolean forzaAllineamentoTransazione(String codiceTransazione, String codiceIdentificativoBanca, String codiceAutorizzazioneBanca, String dataPagamento, String oraPagamento, String note, String user) throws DaoException {
		CallableStatement callableStatement = null;
		try {
			callableStatement = prepareCall(Routines.TMG_FORZA_ALLINEAMENTO.routine());
			callableStatement.setString(1, codiceTransazione);
			callableStatement.setString(2, StoredProcConf.TRA_SOSPESA.getParam());
			callableStatement.setString(3, codiceIdentificativoBanca);
			callableStatement.setString(4, codiceAutorizzazioneBanca);
			callableStatement.setString(5, dataPagamento);
			callableStatement.setString(6, oraPagamento);
			callableStatement.setString(7, note);
			callableStatement.setString(8, user);
			callableStatement.registerOutParameter(9, Types.INTEGER);
			callableStatement.executeUpdate();
			int numrighe =  callableStatement.getInt(9);
			if (numrighe == 1) return true;
			else return false;
		} 
		catch (Exception e) {
			throw new DaoException(e);
		}
		finally {
			//inizio LP PG21XX04 Leak
			//DaoUtil.closeStatement(callableStatement);
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
	
	public String getListaTransazioniNonQuadrate(int rowsPerPage, int pageNumber, String order, 
			String codiceSocieta, String codiceProvincia, String codiceUtente, String codiceTransazione,
			String chiaveGateway, String canalePagamento, String strumento, String codiceAbi, String codiceCab, 
			String codiceSia, String contoCorrente, String cro, String nomeSupporto,
			String dataPagamentoDa, String dataPagamentoA, String dataTransazioneDa, 
			String dataTransazioneA, int chiaveMovimento) throws DaoException {
		
		CallableStatement callableStatement = null;
		ResultSet data = null;
		try {
			callableStatement = prepareCall(Routines.TMG_RECUPERA_TRANSAZIONI_NON_QUADRATE.routine());
			callableStatement.setInt(1, pageNumber);
			callableStatement.setInt(2, rowsPerPage);
			callableStatement.setString(3, order == null ? "" : order);
			callableStatement.setString(4, codiceSocieta == null ? "" :  codiceSocieta);
			callableStatement.setString(5, codiceProvincia == null ? "" :  codiceProvincia);
			callableStatement.setString(6, codiceUtente == null ? "" :  codiceUtente);
			callableStatement.setString(7, codiceTransazione == null ? "" :  codiceTransazione);
			callableStatement.setString(8, chiaveGateway == null ? "" :  chiaveGateway);
			callableStatement.setString(9, canalePagamento == null ? "" :  canalePagamento);
			callableStatement.setString(10, strumento == null ? "" :  strumento);
			callableStatement.setString(11, codiceAbi ==  null ? "" : codiceAbi);
			callableStatement.setString(12, codiceSia == null ? "" :  codiceSia);
			callableStatement.setString(13, contoCorrente == null ? "" :  contoCorrente);
			callableStatement.setString(14, cro == null ? "" :  cro);
			callableStatement.setString(15, nomeSupporto == null ? "" :  nomeSupporto);
			callableStatement.setString(16, dataPagamentoDa ==  null ? "" : dataPagamentoDa);
			callableStatement.setString(17, dataPagamentoA == null ? "" : dataPagamentoA);
			callableStatement.setString(18, dataTransazioneDa ==  null ? "" : dataTransazioneDa);
			callableStatement.setString(19, dataTransazioneA == null ? "" : dataTransazioneA);
			callableStatement.setInt(20, chiaveMovimento);
			callableStatement.registerOutParameter(21, Types.VARCHAR);
			callableStatement.registerOutParameter(22, Types.INTEGER);
			callableStatement.registerOutParameter(23, Types.INTEGER);
			callableStatement.registerOutParameter(24, Types.INTEGER);
			callableStatement.registerOutParameter(25, Types.SMALLINT);
			callableStatement.setString(26, codiceCab ==  null ? "" : codiceCab);
			if(callableStatement.execute())	{
				pageInfo = new PageInfo();
				pageInfo.setPageNumber(pageNumber);
				pageInfo.setRowsPerPage(rowsPerPage);
				pageInfo.setFirstRow(callableStatement.getInt(22));
				pageInfo.setLastRow(callableStatement.getInt(23));
				pageInfo.setNumRows(callableStatement.getInt(24));
				pageInfo.setNumPages(callableStatement.getInt(25));
				data = callableStatement.getResultSet();
				loadWebRowSet(data);
				return getWebRowSetXml();
			}
			return null;
		} 
		catch (Exception e) {
			throw new DaoException(e);
		}
		finally {
			//inizio LP PG21XX04 Leak
			//DaoUtil.closeResultSet(data);
			//DaoUtil.closeStatement(callableStatement);
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
			//fine LP PG21XX04 Leak
		}
	}

	public RiepilogoTransazioniNonQuadrate getRipilogoTransazioniNonQuadrate(String codiceSocieta, String codiceProvincia, 
			String codiceUtente, String codiceTransazione, String chiaveGateway, String canalePagamento, 
			String strumento, String codiceAbi, String codiceCab, String codiceSia, String contoCorrente, String cro, 
			String nomeSupporto, String dataPagamentoDa, String dataPagamentoA, String dataTransazioneDa, 
			String dataTransazioneA, int chiaveMovimento) throws DaoException {
		
		CallableStatement callableStatement = null;
		ResultSet data = null;
		try {
//			callableStatement = prepareCall(Routines.TMG_RIEPILOGO_TRANSAZIONI_NON_QUADRATE.routine());
//			callableStatement.setString(1, codiceSocieta == null ? "" :  codiceSocieta);
//			callableStatement.setString(2, codiceProvincia == null ? "" :  codiceProvincia);
//			callableStatement.setString(3, codiceUtente == null ? "" :  codiceUtente);
//			callableStatement.setString(4, codiceTransazione == null ? "" :  codiceTransazione);
//			callableStatement.setString(5, chiaveGateway == null ? "" :  chiaveGateway);
//			callableStatement.setString(6, canalePagamento == null ? "" :  canalePagamento);
//			callableStatement.setString(7, strumento == null ? "" :  strumento);
//			callableStatement.setString(8, codiceAbi ==  null ? "" : codiceAbi);
//			callableStatement.setString(9, codiceSia == null ? "" :  codiceSia);
//			callableStatement.setString(10, contoCorrente == null ? "" :  contoCorrente);
//			callableStatement.setString(11, cro == null ? "" :  cro);
//			callableStatement.setString(12, nomeSupporto == null ? "" :  nomeSupporto);
//			callableStatement.setString(13, dataPagamentoDa ==  null ? "" : dataPagamentoDa);
//			callableStatement.setString(14, dataPagamentoA == null ? "" : dataPagamentoA);
//			callableStatement.setString(15, dataTransazioneDa ==  null ? "" : dataTransazioneDa);
//			callableStatement.setString(16, dataTransazioneA == null ? "" : dataTransazioneA);
//			callableStatement.setInt(17, chiaveMovimento);
			callableStatement = prepareCall(Routines.TMG_RIEPILOGO_QUADRATURA.routine());
			callableStatement.setInt(1, chiaveMovimento);
			callableStatement.registerOutParameter(2, Types.DECIMAL);
			callableStatement.registerOutParameter(3, Types.DECIMAL);
			callableStatement.registerOutParameter(4, Types.DECIMAL);
			callableStatement.execute();
			return new RiepilogoTransazioniNonQuadrate(callableStatement.getBigDecimal(2), callableStatement.getBigDecimal(3), callableStatement.getBigDecimal(4));
		} 
		catch (Exception e) {
			throw new DaoException(e);
		}
		finally {
			//inizio LP PG21XX04 Leak
			//DaoUtil.closeResultSet(data);
			//DaoUtil.closeStatement(callableStatement);
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
			//fine LP PG21XX04 Leak
		}
	}

	//PG160170_001 GG - Introdotto idFlussoQuadratura
	//PG150180_001 GG - Introdotto codice IUV	
	public List<ModuloIntegrazionePagamentiOneri> getRiepilogoTransazioniOneri(String tx_societa, String tx_provincia, String tx_utente, String tx_ente, String tx_codice_transazione,
			String tx_indirizzo_ip, String tx_user_email, String tx_user_sms, String tx_canale_pagamento,
			String tx_strumento, String tx_tipo_carta, String tx_id_bollettino, String tx_servizio,
			String tx_stato_rendicontazione, String tx_stato_riconciliazione,String tx_importo_da, 
			String tx_importo_a, String tx_data_da,String tx_data_a, int chiaveQuadratura, String tx_id_Terminale_Atm, String tx_id_Transazione_Atm,
			String chiaveRendicontazione, String tx_mostra, String tx_id_terminale_pos_fisico, String tx_data_accr_da, String tx_data_accr_a, String tx_codice_IUV, String idFlussoQuadratura, String tx_recuperate, String tx_codice_fiscale) throws DaoException {
	   
		BigDecimal tx_importo_da_bd = new BigDecimal("0");
	    BigDecimal tx_importo_a_bd = new BigDecimal("0");
		CallableStatement callableStatement = null;
		ResultSet data = null;
		try {
			if((tx_importo_da != null)&&(!tx_importo_da.equals("")))tx_importo_da_bd = new BigDecimal(tx_importo_da);
		    if((tx_importo_a != null)&&(!tx_importo_a.equals("")))tx_importo_a_bd = new BigDecimal(tx_importo_a);
			
		    callableStatement = prepareCall(Routines.TMG_RECUPERA_TRANSAZIONI_GROUPED_ONERI.routine());
			callableStatement.setString(1, (tx_societa == null) || (tx_societa.equals(StoredProcConf.SOCIETA_ALL_VALUE.getParam())) ? "" :  tx_societa);
			callableStatement.setString(2, (tx_provincia == null) || (tx_provincia.equals(StoredProcConf.PROVINCIA_ALL_VALUE.getParam())) ? "" :  tx_provincia);
			callableStatement.setString(3, (tx_ente == null) || (tx_ente.equals(StoredProcConf.ENTECONSORZIATO_ALL_VALUE.getParam())) ? "" :  tx_ente);
			callableStatement.setString(4, (tx_utente == null) || (tx_utente.equals(StoredProcConf.UTENTE_ALL_VALUE.getParam())) ? "" :  tx_utente);
			callableStatement.setString(5, tx_codice_transazione == null ? "" :  tx_codice_transazione);
			callableStatement.setString(6, tx_indirizzo_ip == null ? "" :  tx_indirizzo_ip);
			callableStatement.setString(7, tx_user_email == null ? "" :  tx_user_email);
			callableStatement.setString(8, tx_user_sms == null ? "" :  tx_user_sms);
			callableStatement.setString(9, (tx_canale_pagamento == null) || (tx_canale_pagamento.equals(StoredProcConf.CANALE_ALL_VALUE.getParam())) ? "" :  tx_canale_pagamento);
			callableStatement.setString(10, (tx_strumento == null)|| (tx_strumento.equals(StoredProcConf.STRUMENTO_ALL_VALUE.getParam())) ? "" :  tx_strumento);
			callableStatement.setString(11, (tx_tipo_carta == null) ||(tx_tipo_carta.equals(StoredProcConf.CARTA_ALL_VALUE.getParam())) ? "" :  tx_tipo_carta);
			callableStatement.setString(12, tx_id_bollettino == null ? "" :  tx_id_bollettino);
			callableStatement.setString(13, (tx_servizio == null)|| (tx_servizio.equals(StoredProcConf.TIPOLOGIA_SERVIZIO_ALL_VALUE.getParam())) ? "" :  tx_servizio);
			callableStatement.setString(14, (tx_stato_rendicontazione == null) || (tx_stato_rendicontazione.equals(StoredProcConf.STATORENDICONTAZIONE_ALL_VALUE.getParam())) ? ""  : tx_stato_rendicontazione);
			callableStatement.setString(15, (tx_stato_riconciliazione == null)|| (tx_stato_riconciliazione.equals(StoredProcConf.STATORICONCILIAZIONE_ALL_VALUE.getParam())) ? "" :  tx_stato_riconciliazione);
			callableStatement.setBigDecimal(16, tx_importo_da_bd);
		    callableStatement.setBigDecimal(17, tx_importo_a_bd);
			callableStatement.setString(18,tx_data_da == null ? "" : tx_data_da);
			callableStatement.setString(19,tx_data_a == null ? "" : tx_data_a);
			callableStatement.setInt(20, chiaveQuadratura);
			callableStatement.setString(21,tx_id_Terminale_Atm == null ? "" : tx_id_Terminale_Atm);
			callableStatement.setString(22,tx_id_Transazione_Atm == null ? "" : tx_id_Transazione_Atm);
			callableStatement.setString(23,chiaveRendicontazione == null ? "" : chiaveRendicontazione);
			callableStatement.setString(24,tx_mostra == null ? "" : tx_mostra);
			callableStatement.setString(25,tx_id_terminale_pos_fisico == null ? "" : tx_id_terminale_pos_fisico);
			callableStatement.setString(26,tx_data_accr_da == null ? "" : tx_data_accr_da);
			callableStatement.setString(27,tx_data_accr_a == null ? "" : tx_data_accr_a);
			callableStatement.setString(28,tx_codice_IUV == null ? "" :  tx_codice_IUV);			//17032015 GG
			callableStatement.setString(29,idFlussoQuadratura == null ? "" :  idFlussoQuadratura);	//15022017 GG
			callableStatement.setString(30, tx_recuperate==null ? "" : tx_recuperate);		//PG200050_001 SB
			callableStatement.setString(31, tx_codice_fiscale==null ? "" : tx_codice_fiscale);
			
			callableStatement.registerOutParameter(32, Types.DECIMAL);
			callableStatement.registerOutParameter(33, Types.DECIMAL);
			callableStatement.registerOutParameter(34, Types.DECIMAL);
			
			if(callableStatement.execute()) {
				List<ModuloIntegrazionePagamentiOneri> list = new ArrayList<ModuloIntegrazionePagamentiOneri>();
				data = callableStatement.getResultSet();
				while(data.next()) {
					ModuloIntegrazionePagamentiOneri bean = new ModuloIntegrazionePagamentiOneri(data, true);
					list.add(bean);
				}
				
				return list;
			}
			
			return null;

		} 
		catch (Exception e) {
			throw new DaoException(e);
		}
		finally {
			//inizio LP PG21XX04 Leak
			//DaoUtil.closeResultSet(data);
			//DaoUtil.closeStatement(callableStatement);
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
			//fine LP PG21XX04 Leak
		}
	}
	
}
