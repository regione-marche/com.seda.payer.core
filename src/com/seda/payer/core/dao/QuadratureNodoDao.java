package com.seda.payer.core.dao;

import java.math.BigDecimal;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import java.lang.Long;

import com.seda.data.helper.HelperException;
import com.seda.data.spi.PageInfo;
import com.seda.payer.core.bean.IUVQuadraturaNodo;
import com.seda.payer.core.bean.QuadraturaNodo;
import com.seda.payer.core.bean.RiepilogoMovimentiCBI;
import com.seda.payer.core.exception.DaoException;
import com.seda.payer.core.handler.BaseDaoHandler;


public class QuadratureNodoDao  extends BaseDaoHandler{

	private PageInfo pageInfo = null;

	public QuadratureNodoDao(Connection connection, String schema) {
		super(connection, schema);
	}
	
	public PageInfo getPageInfo() {
		return pageInfo;
	}
	
	public int insertQuadraturaNodo(long keyQuadratura, String codSocieta,
			String codUtente, String keyGateway, String nomeFileTxt,
			Timestamp dtInizioRend, Timestamp dtFineRend, long numTransazioni,
			BigDecimal impSquadratura, String statoSquadratura,
			String movimentoElab, Timestamp dtChiusuraFlusso,
			Timestamp dtUltAggior, String codOperatore, String versOggetto,
			String idFlusso, Timestamp dtflusso, String idUnivocoRegol,
			Date dtregol, String tipoIdUnivocoMitt, String codIdUnivocoMitt,
			String denomMitt, String tipoIdUnivocoRice,
			String codIdUnivocoRice, String denomRice, long numTotPagamenti,
			BigDecimal impTotPagamenti, String codiceIUV/*, String codiceRIS,
			BigDecimal impPagamento, String esitoPagam, Date dtEsitoPagam*/)throws DaoException
	{
		int chiaveQuadratura=0;
		int numrighe=0;
		String messaggio="";
		CallableStatement callableStatement = null;
		try{
			callableStatement = prepareCall(Routines.QUN_NODO_QUNINS.routine());
			callableStatement.setLong(1, keyQuadratura);
			callableStatement.setString(2,codSocieta);
			callableStatement.setString(3,codUtente);
			callableStatement.setString(4,keyGateway);
			callableStatement.setString(5,nomeFileTxt);
			callableStatement.setTimestamp(6,dtInizioRend);
			callableStatement.setTimestamp(7,dtFineRend);
			callableStatement.setLong(8,numTransazioni);
			callableStatement.setBigDecimal(9,impSquadratura);	
			callableStatement.setString(10,statoSquadratura);
			callableStatement.setString(11,movimentoElab);
			callableStatement.setTimestamp(12,dtChiusuraFlusso);
			callableStatement.setTimestamp(13,dtUltAggior);
			callableStatement.setString(14,codOperatore);
		    
			callableStatement.setString(15,versOggetto);
			callableStatement.setString(16,idFlusso);
			callableStatement.setTimestamp(17,dtflusso);
			callableStatement.setString(18,idUnivocoRegol);
			callableStatement.setDate(19,dtregol);
			callableStatement.setString(20,tipoIdUnivocoMitt);
			callableStatement.setString(21,codIdUnivocoMitt);
			callableStatement.setString(22,denomMitt);
			callableStatement.setString(23,tipoIdUnivocoRice);
			callableStatement.setString(24,codIdUnivocoRice);
			callableStatement.setString(25,denomRice);
			callableStatement.setLong(26,numTotPagamenti);
			callableStatement.setBigDecimal(27,impTotPagamenti);
			callableStatement.setString(28,codiceIUV);
			/*callableStatement.setString(29,codiceRIS);
			callableStatement.setBigDecimal(30,impPagamento);
			callableStatement.setString(31,esitoPagam);
			callableStatement.setDate(32,dtEsitoPagam);
			callableStatement.registerOutParameter(33, Types.BIGINT);
			callableStatement.registerOutParameter(34, Types.INTEGER);
			callableStatement.registerOutParameter(35, Types.VARCHAR);*/
			
			callableStatement.registerOutParameter(29, Types.BIGINT);
			callableStatement.registerOutParameter(30, Types.INTEGER);
			callableStatement.registerOutParameter(31, Types.VARCHAR);
			callableStatement.executeUpdate();
			chiaveQuadratura =  callableStatement.getInt(29);
			numrighe =  callableStatement.getInt(30);
			messaggio =  callableStatement.getString(31);
			
		} 
		catch (Exception x) 
		{
			throw new DaoException(x);
		}finally {
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
		if (numrighe == 1) 
			return chiaveQuadratura;
		else 
			throw new DaoException(1, messaggio);
	}

	public Object[] riconciliaTransazioneNodo(int chiaveQuadratura, 
											String chiaveTransazione,
											BigDecimal importo,
											BigDecimal spese,
											String operatore,
											String idUnivocoRiscossione,
											String idDominio) throws DaoException
	{
		CallableStatement callableStatement = null;
		
		try{
			callableStatement = prepareCall(Routines.QUN_NODO_TRARIC.routine());
			callableStatement.setLong(1, chiaveQuadratura);
			callableStatement.setString(2, chiaveTransazione);
			callableStatement.setBigDecimal(3, importo);
			callableStatement.setBigDecimal(4, spese);
			callableStatement.setString(5, operatore);
			if (idUnivocoRiscossione != null)
				callableStatement.setString(6, idUnivocoRiscossione);
			else 
				callableStatement.setNull(6, Types.VARCHAR);
			callableStatement.setString(7, idDominio);
			callableStatement.registerOutParameter(8, Types.DECIMAL);
			callableStatement.registerOutParameter(9, Types.DECIMAL);
			callableStatement.registerOutParameter(10, Types.INTEGER);
			callableStatement.registerOutParameter(11, Types.INTEGER);
			callableStatement.registerOutParameter(12, Types.VARCHAR);
			callableStatement.registerOutParameter(13, Types.VARCHAR);
			callableStatement.executeUpdate();
			Object result[] = new Object[6];
			for(int i=0; i<result.length; i++){
				result[i]=callableStatement.getObject(8+i);
			}
			return result;
		} 
		catch (Exception x) 
		{
			throw new DaoException(x);
		}finally {
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

	//inizio LP PG22XX04
	public Object[] riconciliaTransazioneNodoIUVIURMulti
	(
			int chiaveQuadratura, 
			String chiaveTransazione,
			BigDecimal importo,
			BigDecimal spese,
			String operatore,
			String iurMulti,
			String idDominio
	) throws DaoException {
		CallableStatement callableStatement = null;
		
		try{
			callableStatement = prepareCall("PYQUNSP_NODO_SEARCHM");
			callableStatement.setLong(1, chiaveQuadratura);
			callableStatement.setString(2, chiaveTransazione);
			callableStatement.setBigDecimal(3, importo);
			callableStatement.setBigDecimal(4, spese);
			callableStatement.setString(5, operatore);
			callableStatement.setString(6, iurMulti);
			callableStatement.setString(7, idDominio);
			callableStatement.registerOutParameter(8, Types.DECIMAL);
			callableStatement.registerOutParameter(9, Types.DECIMAL);
			callableStatement.registerOutParameter(10, Types.INTEGER);
			callableStatement.registerOutParameter(11, Types.INTEGER);
			callableStatement.registerOutParameter(12, Types.VARCHAR);
			callableStatement.registerOutParameter(13, Types.VARCHAR);
			callableStatement.executeUpdate();
			Object result[] = new Object[6];
			for(int i=0; i<result.length; i++)
				result[i]=callableStatement.getObject(8+i);
			return result;
		} catch (Exception x) {
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
	//fine LP PG22XX04

	public int chiudiQuadraturaNodo(int chiaveQuadratura, String quadrareMovimentiSquadrati,String chiaveTransazione, int txRecuperate) throws DaoException
	{
		int scritture = 0;
		CallableStatement callableStatement = null;
		try
		{
			callableStatement = prepareCall(Routines.QUN_NODO_QUNEND.routine());
			callableStatement.setLong(1, chiaveQuadratura);
			callableStatement.setString(2, quadrareMovimentiSquadrati);
			callableStatement.setString(3, chiaveTransazione); //PG170300 - 30/1/2018 - INIZIO - gestione multibollettino 
			callableStatement.setLong(4, txRecuperate);  //PG190240_001 SB
			callableStatement.registerOutParameter(5, Types.INTEGER);
			callableStatement.executeUpdate();
			scritture = callableStatement.getInt(5);
		} 
		catch (Exception x) 
		{
			throw new DaoException(x);
		}finally {
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
		return scritture;
	}
	
	public String getListaMovimentiXmlNodo(long keyQuadratura, String codSocieta,
			String codUtente, String keyGateway, String nomeFileTxt,
			String dtInizioRend, String dtFineRend, long numTransazioni,
			String impSquadratura, String statoSquadratura,
			String movimentoElab, String dtChiusuraFlusso,
			String dtUltAggior, String codOperatore, String versOggetto,
			String idFlusso, String dtflusso, String idUnivocoRegol,
			String dtregol, String tipoIdUnivocoMitt, String codIdUnivocoMitt,
			String denomMitt, String tipoIdUnivocoRice,
			String codIdUnivocoRice, String denomRice, long numTotPagamenti,
			String impTotPagamenti, String codiceIUV, String codiceRIS,
			String impPagamento, String esitoPagam, String dtEsitoPagam,
			int rowsPerPage, int pageNumber, String order,
			String siglaProvincia, String impPagamentoDa,
			//inizio LP PG200200
			//String impPagamentoA, String dtflussoDa, String dtflussoA) throws DaoException {
			String impPagamentoA, String dtflussoDa, String dtflussoA
			, String dtMakeFlussoDa, String dtMakeFlussoA
			, String tipologiaFlusso, String scartateFlusso, String recuperateFlusso, String esitoInvioWs, String esitoInvioConservazione
			, String chiaveTransazione	//PGNTCORE-3

			) throws DaoException {
		    //fine LP PG200200
		CallableStatement callableStatement = null;
		ResultSet data = null;
		try{
			// modifica per passaggio codice Ente
			String codEnte=null;
			if(codUtente.length()>5){
				codEnte =codUtente; 
				codUtente =null;
			}
				
			
		    BigDecimal importo_da_bd = new BigDecimal("0");
		    BigDecimal importo_a_bd = new BigDecimal("0");
			callableStatement = prepareCall(Routines.QUN_RECUPERA_MOVIMENTI_NODO.routine());
			callableStatement.setInt(1, pageNumber);
			callableStatement.setInt(2, rowsPerPage);
			callableStatement.setString(3, order == null ? "" : order);
			callableStatement.setLong(4, keyQuadratura);
			callableStatement.setString(5, (codSocieta == null) || (codSocieta.equals(StoredProcConf.SOCIETA_ALL_VALUE.getParam())) ? "" :  codSocieta);
			callableStatement.setString(6, (siglaProvincia== null) || (siglaProvincia.equals(StoredProcConf.PROVINCIA_ALL_VALUE.getParam())) ? "" :  siglaProvincia);
			callableStatement.setString(7, (codUtente == null) || (codUtente.equals(StoredProcConf.UTENTE_ALL_VALUE.getParam())) ? "" : codUtente);
			callableStatement.setString(8, statoSquadratura == null ? "" :statoSquadratura);
			callableStatement.setString(9, (codiceIUV == null ? "" :  codiceIUV));
			callableStatement.setString(10, nomeFileTxt == null ? "" :  nomeFileTxt);
		    if((impPagamentoDa.toString() != null)&&(!impPagamentoDa.toString().equals(""))) importo_da_bd = new BigDecimal(impPagamentoDa.toString().replace(",", "."));
		    if((impPagamentoA.toString() != null)&&(!impPagamentoA.toString().equals("")))importo_a_bd = new BigDecimal(impPagamentoA.toString().replace(",", "."));
			callableStatement.setBigDecimal(11, importo_da_bd);
		    callableStatement.setBigDecimal(12, importo_a_bd);
			callableStatement.setString(13, esitoPagam == null ? "" : esitoPagam);
			callableStatement.setString(14, idFlusso == null ? "" : idFlusso);
		    callableStatement.setString(15, dtflussoDa == null ? "" : dtflussoDa);
			callableStatement.setString(16, dtflussoA == null ? "" : dtflussoA);
			callableStatement.setString(17, codEnte == null ? "" : codEnte);
			
		    
			//inizio LP PG200200
			//callableStatement.registerOutParameter(18, Types.VARCHAR);
			//callableStatement.registerOutParameter(19, Types.INTEGER);
			//callableStatement.registerOutParameter(20, Types.INTEGER);
			//callableStatement.registerOutParameter(21, Types.INTEGER);
			//callableStatement.registerOutParameter(22, Types.SMALLINT);
			//if(callableStatement.execute())	{
			//	pageInfo = new PageInfo();
			//	pageInfo.setPageNumber(pageNumber);
			//	pageInfo.setRowsPerPage(rowsPerPage);
			//	pageInfo.setFirstRow(callableStatement.getInt(19));
			//	pageInfo.setLastRow(callableStatement.getInt(20));
			//	pageInfo.setNumRows(callableStatement.getInt(21));
			//	pageInfo.setNumPages(callableStatement.getInt(22));
			//
			//	data = callableStatement.getResultSet();
			//	loadWebRowSet(data);
			//	return getWebRowSetXml();
			//}

		    callableStatement.setString(18, dtMakeFlussoDa == null ? "" : dtMakeFlussoDa);
		    callableStatement.setString(19, dtMakeFlussoA == null ? "" : dtMakeFlussoA);
		    callableStatement.setString(20, tipologiaFlusso == null ? "" : tipologiaFlusso);
			callableStatement.setString(21, scartateFlusso == null ? "" : scartateFlusso);
			callableStatement.setString(22, recuperateFlusso == null ? "" : recuperateFlusso);
			callableStatement.setString(23, esitoInvioWs == null ? "" : esitoInvioWs);
			callableStatement.setString(24, esitoInvioConservazione == null ? "" : esitoInvioConservazione);
			callableStatement.setString(25, chiaveTransazione == null ? "" : chiaveTransazione);	//PGNTCORE-3

			
			callableStatement.registerOutParameter(26, Types.VARCHAR);
			callableStatement.registerOutParameter(27, Types.INTEGER);
			callableStatement.registerOutParameter(28, Types.INTEGER);
			callableStatement.registerOutParameter(29, Types.INTEGER);
			callableStatement.registerOutParameter(30, Types.SMALLINT);
			if(callableStatement.execute())	{
				pageInfo = new PageInfo();
				pageInfo.setPageNumber(pageNumber);
				pageInfo.setRowsPerPage(rowsPerPage);
				pageInfo.setFirstRow(callableStatement.getInt(27));
				pageInfo.setLastRow(callableStatement.getInt(28));
				pageInfo.setNumRows(callableStatement.getInt(29));
				pageInfo.setNumPages(callableStatement.getInt(30));

				data = callableStatement.getResultSet();
				loadWebRowSet(data);
				return getWebRowSetXml();
			}
			//fine LP PG200200
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

	public List<RiepilogoMovimentiCBI> getListaMovimentiGroupedXmlNodo(long keyQuadratura, String codSocieta,
			String codUtente, String keyGateway, String nomeFileTxt,
			String dtInizioRend, String dtFineRend, long numTransazioni,
			String impSquadratura, String statoSquadratura,
			String movimentoElab, String dtChiusuraFlusso,
			String dtUltAggior, String codOperatore, String versOggetto,
			String idFlusso, String dtflusso, String idUnivocoRegol,
			String dtregol, String tipoIdUnivocoMitt, String codIdUnivocoMitt,
			String denomMitt, String tipoIdUnivocoRice,
			String codIdUnivocoRice, String denomRice, long numTotPagamenti,
			String impTotPagamenti, String codiceIUV, String codiceRIS,
			String impPagamento, String esitoPagam, String dtEsitoPagam,
			String siglaProvincia, String impPagamentoDa,
			//inizio LP PG200200
			//String impPagamentoA, String dtflussoDa, String dtflussoA) throws DaoException {
			String impPagamentoA, String dtflussoDa, String dtflussoA
			, String dtMakeFlussoDa, String dtMakeFlussoA
			, String tipologiaFlusso, String scartateFlusso, String recuperateFlusso, String esitoInvioWs, String esitoInvioConservazione
			, String chiaveTransazione	//PGNTCORE-3

			) throws DaoException {
		    //fine LP PG200200
		CallableStatement callableStatement = null;
		ResultSet data = null;
		try{
			// modifica per passaggio codice Ente
			String codEnte=null;
			if(codUtente.length()>5){
				codEnte =codUtente; 
				codUtente ="";
			}
		    BigDecimal importo_da_bd = new BigDecimal("0");
		    BigDecimal importo_a_bd = new BigDecimal("0");
			callableStatement = prepareCall(Routines.QUN_RECUPERA_MOVIMENTI_GROUPED_NODO.routine());
			callableStatement.setLong(1, keyQuadratura);
			callableStatement.setString(2, (codSocieta == null) || (codSocieta.equals(StoredProcConf.SOCIETA_ALL_VALUE.getParam())) ? "" :  codSocieta);
			callableStatement.setString(3, (siglaProvincia== null) || (siglaProvincia.equals(StoredProcConf.PROVINCIA_ALL_VALUE.getParam())) ? "" :  siglaProvincia);
			callableStatement.setString(4, (codUtente == null) || (codUtente.equals(StoredProcConf.UTENTE_ALL_VALUE.getParam())) ? "" :  codUtente);
			callableStatement.setString(5, statoSquadratura == null ? "" :statoSquadratura);
			callableStatement.setString(6, (codiceIUV == null ? "" :  codiceIUV));
			callableStatement.setString(7, nomeFileTxt == null ? "" :  nomeFileTxt);
		    if((impPagamentoDa.toString() != null)&&(!impPagamentoDa.toString().equals(""))) importo_da_bd = new BigDecimal(impPagamentoDa.toString().replace(",", "."));
		    if((impPagamentoA.toString() != null)&&(!impPagamentoA.toString().equals("")))importo_a_bd = new BigDecimal(impPagamentoA.toString().replace(",", "."));
			callableStatement.setBigDecimal(8, importo_da_bd);
		    callableStatement.setBigDecimal(9, importo_a_bd);
			callableStatement.setString(10, esitoPagam == null ? "" : esitoPagam);
			callableStatement.setString(11, idFlusso == null ? "" : idFlusso);
		    callableStatement.setString(12, dtflussoDa == null ? "" : dtflussoDa);
			callableStatement.setString(13, dtflussoA == null ? "" : dtflussoA);
			callableStatement.setString(14, codEnte == null ? "" : codEnte);
			//inizio LP PG200200
		    callableStatement.setString(15, dtMakeFlussoDa == null ? "" : dtMakeFlussoDa);
		    callableStatement.setString(16, dtMakeFlussoA == null ? "" : dtMakeFlussoA);
		    callableStatement.setString(17, tipologiaFlusso == null ? "" : tipologiaFlusso);
			callableStatement.setString(18, scartateFlusso == null ? "" : scartateFlusso);
			callableStatement.setString(19, recuperateFlusso == null ? "" : recuperateFlusso);
		    //fine LP PG200200
			callableStatement.setString(20, esitoInvioWs == null ? "" : esitoInvioWs);
			callableStatement.setString(21, esitoInvioConservazione == null ? "" : esitoInvioConservazione);
			callableStatement.setString(22, chiaveTransazione == null ? "" : chiaveTransazione);	//PGNTCORE-3

			List<RiepilogoMovimentiCBI> list = new ArrayList<RiepilogoMovimentiCBI>();
			if(callableStatement.execute()) {
				data = callableStatement.getResultSet();
				while(data.next()) {
					RiepilogoMovimentiCBI bean = new RiepilogoMovimentiCBI(data);
					list.add(bean);
				}
			}
			return list;

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

	public boolean elaboraMovimentiQuadraturaNodo(String quadrareMovimentiSquadrati) throws DaoException {
		CallableStatement callableStatement = null;
		try{
			callableStatement = prepareCall(Routines.QUN_ELABORA_MOVIMENTO_NODO.routine());
//			callableStatement.setString(1, quadrareMovimentiSquadrati);			
			if (callableStatement.execute()) {
				return true;
			}
			return false;
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
	
	public boolean forzaChiusuraQuadraturaNodo(int chiaveQuadratura, String user) throws DaoException {
		CallableStatement callableStatement = null;
		boolean risultato = false;
		try{
			callableStatement = prepareCall(Routines.QUN_CHIUDI_MOVIMENTO_NODO.routine());
			callableStatement.setLong(1, chiaveQuadratura);
			callableStatement.setString(2,user);
			callableStatement.execute();
			risultato = true;
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
		
		return risultato;
	}
	
	public String recuperaMovimentiApertiNodo() throws DaoException {
		CallableStatement callableStatement = null;
		ResultSet data = null;
		try{
			callableStatement = prepareCall(Routines.QUN_RECUPERA_MOVIMENTI_APERTI_NODO.routine());
			if(callableStatement.execute())	{
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

	//flusso fittizio
	public int assegnaTranAFittizioNodo(String chiaveQuadratura, String multiGTW, String user) throws DaoException {
		CallableStatement callableStatement = null;
		int risultato;
		try{
			if (multiGTW.equals("0"))
				callableStatement = prepareCall(Routines.TRA_ASSOCIA_FITTIZIO.routine());
			else
				callableStatement = prepareCall(Routines.TRA_ASSOCIA_FITTIZIO_MULTIGTW.routine());
				
			callableStatement.setString(1, chiaveQuadratura);
			callableStatement.setString(2,user);
			callableStatement.registerOutParameter(3, Types.INTEGER);		
			callableStatement.execute();
			risultato = callableStatement.getInt(3);
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
		
		return risultato;
	}
	
	//PG190190_001 SB - inizio
	public String updateQuadraturaNodo(QuadraturaNodo quadratura) throws DaoException, SQLException{
		int numrighe=0;
		String messaggio="";
		CallableStatement callableStatement = null;
		try{
			callableStatement = prepareCall("PYQUNSP_UPD2");
			//callableStatement = prepareCall(Routines.QUN_NODO_UPD.routine());
			callableStatement.setString(1, quadratura.getKeyQuadratura().toString());
			callableStatement.setString(2, quadratura.getEsitoInvioQuadratura());
			callableStatement.setString(3, quadratura.getErroreInvioQuadratura());
			
			callableStatement.registerOutParameter(4, Types.VARCHAR);
			callableStatement.registerOutParameter(5, Types.INTEGER);
			
			callableStatement.executeUpdate();
			messaggio =  callableStatement.getString(4);
			numrighe =  callableStatement.getInt(5);
			
			
		} catch (SQLException x) {
			throw new DaoException(x);
		} catch (IllegalArgumentException x) {
			throw new DaoException(x);
		} catch (HelperException x) {
			throw new DaoException(x);
		}finally {
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
		if (numrighe == 1) 
			return messaggio;
		else 
			throw new DaoException(1, "L'aggiornamento del record con chiave quadratura "+ quadratura.getKeyQuadratura() +" non è andato a buon fine");
	}
	//PG190190_001 SB - fine

	//inizio LP PG200200
	public String getListaIUVFlussoNodoXml(
			long keyQuadratura, String codiceIUV,
			String codiceEsito, String codiceQuadratura, String statoQuadratura,
			String impPagamentoDa, String impPagamentoA,
			int rowsPerPage, int pageNumber, String order
			) throws DaoException {
		CallableStatement callableStatement = null;
		ResultSet data = null;
		try{
		    BigDecimal importo_da_bd = new BigDecimal("0");
		    BigDecimal importo_a_bd = new BigDecimal("0");
			callableStatement = prepareCall(Routines.QUI_RECUPERA_IUV_MOVIMENTI_NODO.routine());
			callableStatement.setInt(1, pageNumber);
			callableStatement.setInt(2, rowsPerPage);
			callableStatement.setString(3, (order == null ? "" : order));
			callableStatement.setLong(4, keyQuadratura);
			callableStatement.setString(5, (codiceIUV == null ? "" :  codiceIUV));
			callableStatement.setString(6, (codiceEsito == null ? "" :  codiceEsito));
			callableStatement.setString(7, (codiceQuadratura == null ? "" : codiceQuadratura));
			callableStatement.setString(8, (statoQuadratura == null ? "" :  statoQuadratura));
		    if((impPagamentoDa != null) && (impPagamentoDa.toString() != null) && (!impPagamentoDa.toString().equals(""))) importo_da_bd = new BigDecimal(impPagamentoDa.toString());
		    if((impPagamentoA != null) && (impPagamentoA.toString() != null) && (!impPagamentoA.toString().equals(""))) importo_a_bd = new BigDecimal(impPagamentoA.toString());
			callableStatement.setBigDecimal(9, importo_da_bd);
		    callableStatement.setBigDecimal(10, importo_a_bd);
			callableStatement.registerOutParameter(11, Types.VARCHAR);
			callableStatement.registerOutParameter(12, Types.INTEGER);
			callableStatement.registerOutParameter(13, Types.INTEGER);
			callableStatement.registerOutParameter(14, Types.INTEGER);
			callableStatement.registerOutParameter(15, Types.SMALLINT);
			if(callableStatement.execute())	{
				pageInfo = new PageInfo();
				pageInfo.setPageNumber(pageNumber);
				pageInfo.setRowsPerPage(rowsPerPage);
				pageInfo.setFirstRow(callableStatement.getInt(12));
				pageInfo.setLastRow(callableStatement.getInt(13));
				pageInfo.setNumRows(callableStatement.getInt(14));
				pageInfo.setNumPages(callableStatement.getInt(15));

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
	
	public QuadraturaNodo getFlussoNodo(long keyQuadratura) throws DaoException {
		CallableStatement callableStatement = null;
		ResultSet data = null;
		try{
			callableStatement = prepareCall(Routines.QUN_DODETAIL.routine());
			callableStatement.setLong(1, keyQuadratura);
			if(callableStatement.execute())	{
				data = callableStatement.getResultSet();
				if(data.next()) {
					QuadraturaNodo quadraturaNodo = new QuadraturaNodo(data);
					return quadraturaNodo;
				}
			}
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
		return null;
	}

	public List<IUVQuadraturaNodo> getIUVFlussoNodo(long keyQuadratura) throws DaoException {
		CallableStatement callableStatement = null;
		ResultSet data = null;
		try{
			callableStatement = prepareCall(Routines.QUI_LISTIUV.routine());
			callableStatement.setLong(1, keyQuadratura);
			if(callableStatement.execute())	{
				List<IUVQuadraturaNodo> list = new ArrayList<IUVQuadraturaNodo>();
				if(callableStatement.execute()) {
					data = callableStatement.getResultSet();
					while(data.next()) {
						IUVQuadraturaNodo bean = IUVQuadraturaNodo.getBean(data);
						list.add(bean);
					}
				}
				return list;
			}
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
		return null;
	}

	public boolean insertIUVFlussoNodo(
			int posizione,
			Long keyQuadratura,
			String iuv,
			String iur,
			BigDecimal importo,
			BigDecimal spese,
			String esito,
			String dataesito,
			String operatore
			) throws DaoException {
		CallableStatement callableStatement = null;
		try {
			callableStatement = prepareCall(Routines.QUI_INSERT.routine());
			callableStatement.setInt(1, posizione);
			callableStatement.setLong(2, keyQuadratura);
			callableStatement.setString(3, iuv);
			callableStatement.setString(4, iur);
			callableStatement.setBigDecimal(5, importo);
			callableStatement.setBigDecimal(6, spese);
			callableStatement.setString(7, esito);
			callableStatement.setString(8, dataesito);
			callableStatement.setString(9, operatore);
			callableStatement.registerOutParameter(10, Types.INTEGER);
			callableStatement.executeUpdate();
			int numrighe =  callableStatement.getInt(10);
			if (numrighe == 1) {
				return true; 
			}
		} catch (Exception e) {
			throw new DaoException(e);
		}
		finally {
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
		return false;
	}
	
	public boolean updateStatoQuadraturaIUVFlussoNodo(
			int posizione,
			Long keyQuadratura,
			String codiceQuadratura,
			BigDecimal importoRpt,
			String operatore
			) throws DaoException {
		CallableStatement callableStatement = null;
		try{
			callableStatement = prepareCall(Routines.QUI_UPDATE.routine());
			callableStatement.setInt(1, posizione);
			callableStatement.setLong(2, keyQuadratura);
			callableStatement.setString(3, codiceQuadratura);
			callableStatement.setBigDecimal(4, importoRpt);
			if(operatore == null || operatore.trim().length() == 0) {
				callableStatement.setNull(5, Types.VARCHAR);
			} else {
				callableStatement.setString(5, operatore);
			}
			callableStatement.registerOutParameter(6, Types.INTEGER);
			callableStatement.executeUpdate();
			int numrighe = callableStatement.getInt(6);
			if (numrighe == 1) {
				return true; 
			}
		} catch (Exception e) {
			throw new DaoException(e);
		} finally {
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
		return false;
	}
	//fine LP PG200200
	
	//inizio LP 20210315
	//Per correzione Bug codiceSocieta, chiaveEnte, codiceUtente e chiaveGateway non valorizzati
	public String updateDatiQuadraturaNodo(QuadraturaNodo quadratura) throws DaoException, SQLException
	{
		int numrighe = 0;
		String messaggio = "";
		CallableStatement callableStatement = null;
		try{
			callableStatement = prepareCall("PYQUNSP_UPD3");
			callableStatement.setString(1, quadratura.getKeyQuadratura().toString());
			callableStatement.setString(2, quadratura.getCodSocieta());
			callableStatement.setString(3, quadratura.getCodUtente());
			callableStatement.setString(4, quadratura.getKeyEnte());
			if(quadratura.getKeyGateway() != null)
				callableStatement.setString(5, quadratura.getKeyGateway());
			else
				callableStatement.setNull(5, Types.VARCHAR);
			callableStatement.registerOutParameter(6, Types.VARCHAR);
			callableStatement.registerOutParameter(7, Types.INTEGER);
			
			callableStatement.executeUpdate();
			messaggio =  callableStatement.getString(6);
			numrighe =  callableStatement.getInt(7);
			
		} catch (SQLException x) {
			throw new DaoException(x);
		} catch (IllegalArgumentException x) {
			throw new DaoException(x);
		} catch (HelperException x) {
			throw new DaoException(x);
		}finally {
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
		if (numrighe == 1) 
			return messaggio;
		else 
			throw new DaoException(1, "L'aggiornamento tramite PYQUNSP_UPD3 del record con chiave quadratura '"+ quadratura.getKeyQuadratura() +"' non è andato a buon fine.");
	}
	//fine LP 20210315

	//inizio LP PG22XX04
	public int addTransAQuadraturaNodo(int chiaveQuadratura) throws DaoException
	{
		int scritture = 0;
		CallableStatement callableStatement = null;
		try
		{
			callableStatement = prepareCall("PYQUNSP_UPN");
			callableStatement.setLong(1, chiaveQuadratura);
			callableStatement.registerOutParameter(2, Types.INTEGER);
			callableStatement.executeUpdate();
			scritture = callableStatement.getInt(2);
		} 
		catch (Exception x) 
		{
			throw new DaoException(x);
		}finally {
			if (callableStatement != null) {
				try {
					callableStatement.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return scritture;
	}
	//fine LP PG22XX04

	public int getNumeroTrasazioniPerEnte(String dataInizio, String dataFine, String chiaveEnte) throws DaoException {
		int numeroTransazioniPerEnte = 0;
		CallableStatement callableStatement = null;
		ResultSet data = null; //inizio LP 20240912 - PGNTFATT-5
		try {
			callableStatement = prepareCall(Routines.PYQUNSP_SEL_NTOT.routine());
			callableStatement.setString(1, dataInizio);
			callableStatement.setString(2, dataFine);
			callableStatement.setString(3, chiaveEnte);
			callableStatement.execute();
			//inizio LP 20240912 - PGNTFATT-5			
			//if(callableStatement.getResultSet().next()) {
			//	numeroTransazioniPerEnte = callableStatement.getResultSet().getInt(2);
			data = callableStatement.getResultSet();
			if(data.next()) {
				numeroTransazioniPerEnte = data.getInt(2);
			}
			//fine LP 20240912 - PGNTFATT-5			
		}
		catch (Exception x) {
			throw new DaoException(x);
		} finally {
			//inizio LP 20240912 - PGNTFATT-5			
			if (data != null) {
				try {
					data.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			//fine LP 20240912 - PGNTFATT-5			
			if (callableStatement != null) {
				try {
					callableStatement.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return numeroTransazioniPerEnte;
	}

	public BigDecimal getImportoTrasazioniPerEnte(String dataInizio, String dataFine, String chiaveEnte) throws DaoException {
		BigDecimal importoTransazioniPerEnte = BigDecimal.ZERO;
		CallableStatement callableStatement = null;
		try
		{
			callableStatement = prepareCall(Routines.PYQUNSP_SEL_NTOT.routine());
			callableStatement.setString(1, dataInizio);
			callableStatement.setString(2, dataFine);
			callableStatement.setString(3, chiaveEnte);
			callableStatement.execute();
			if(callableStatement.getResultSet().next()){
				importoTransazioniPerEnte = callableStatement.getResultSet().getBigDecimal(3);
			}
		}
		catch (Exception x) {
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
		return importoTransazioniPerEnte;
	}

	public List<QuadraturaNodo> getEntiQUN(Boolean hasFilters, String dataDa, String dataA) throws DaoException {
	//inizio LP 20240912 - PGNTFATT-5
		return getEntiQUNTail(true, true, hasFilters, dataDa, dataA);
	}
	
	public List<QuadraturaNodo> getEntiQUNBatch(boolean bFlagUpdateAutocomit, boolean bCloseStat, Boolean hasFilters, String dataDa, String dataA) throws DaoException {
		return getEntiQUNTail(bFlagUpdateAutocomit, bCloseStat, hasFilters, dataDa, dataA);
		
	}
	
	private List<QuadraturaNodo> getEntiQUNTail(boolean bFlagUpdateAutocomit, boolean bCloseStat, Boolean hasFilters, String dataDa, String dataA) throws DaoException {
		ResultSet data = null;
	//fine LP 20240912 - PGNTFATT-5
		List<QuadraturaNodo> list = new ArrayList<>();
		CallableStatement callableStatement = null;
		try
		{
			//inizio LP 20240912 - PGNTFATT-5
			//callableStatement = prepareCall(Routines.PYQUNSP_SEL_ENT.routine());
			callableStatement = prepareCall(bFlagUpdateAutocomit, Routines.PYQUNSP_SEL_ENT.routine());
			//fine LP 20240912 - PGNTFATT-5
			callableStatement.setString(1, hasFilters ? dataDa : "");
			callableStatement.setString(2, hasFilters ? dataA : "");
			callableStatement.execute();
			//inizio LP 20240912 - PGNTFATT-5
			//while(callableStatement.getResultSet().next()){
			//	QuadraturaNodo quadraturaNodo = new QuadraturaNodo();
			//	quadraturaNodo.setCodSocieta(callableStatement.getResultSet().getString(1));
			//	quadraturaNodo.setCodUtente(callableStatement.getResultSet().getString(2));
			//	quadraturaNodo.setKeyEnte(callableStatement.getResultSet().getString(3));
			data = callableStatement.getResultSet();
			while(data.next()){
				QuadraturaNodo quadraturaNodo = new QuadraturaNodo();
				quadraturaNodo.setCodSocieta(data.getString(1));
				quadraturaNodo.setCodUtente(data.getString(2));
				quadraturaNodo.setKeyEnte(data.getString(3));
			//fine LP 20240912 - PGNTFATT-5
				list.add(quadraturaNodo);
			}
		}
		catch (Exception x) {
			throw new DaoException(x);
		} finally {
			//inizio LP 20240912 - PGNTFATT-5			
			if (data != null) {
				try {
					data.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(bCloseStat) {
			//fine LP 20240912 - PGNTFATT-5			
				if (callableStatement != null) {
					try {
						callableStatement.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
			//inizio LP 20240912 - PGNTFATT-5			
			}
			//fine LP 20240912 - PGNTFATT-5			
		}
		return list;
	}
}
