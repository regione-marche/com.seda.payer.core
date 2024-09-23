package com.seda.payer.core.dao;

import java.math.BigDecimal;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.seda.data.helper.HelperException;
import com.seda.payer.core.bean.Mercato;
import com.seda.payer.core.bean.PrenotazionePiazzolaMercato;
import com.seda.payer.core.exception.DaoException;
import com.seda.payer.core.handler.BaseDaoHandler;

public class PrenotazionePiazzolaMercatiDAO extends BaseDaoHandler {

	public PrenotazionePiazzolaMercatiDAO(Connection connection, String schema) {
		super(connection, schema);
	}
	
	public PrenotazionePiazzolaMercato doGet(String chiavePrenotazione) throws DaoException{
		PrenotazionePiazzolaMercato piazzola = null;
		//inizio LP PG21XX04 Leak
		CallableStatement getPRNStatement = null;
		ResultSet data = null;
		//fine LP PG21XX04 Leak
		try	{
			//inizio LP PG21XX04 Leak
			//CallableStatement getPRNStatement = null;
			//fine LP PG21XX04 Leak
			getPRNStatement = prepareCall(Routines.PYPRNSP_SLK.routine());
			getPRNStatement.setString(1, chiavePrenotazione);
			if (getPRNStatement.execute()) {
				//inizio LP PG21XX04 Leak
				//ResultSet data = getPRNStatement.getResultSet();
				data = getPRNStatement.getResultSet();
				//fine LP PG21XX04 Leak
				if (data.next()){
					piazzola = new PrenotazionePiazzolaMercato(
							data.getString("PRN_KPRNKPRN"),//chiavePrenotazione
							data.getString("AUT_CAUTNMAU"),//codiceAutorizzazione
							data.getString("AUT_CAUTNOMN"),//nominativoAutorizzazione
							data.getString("ACM_CACMCFPI"),//codiceFiscale
							data.getString("ACM_KBRSKBRS"),//codiceBorsellino
							data.getString("MRC_KMRCKMRC"),//codiceMercato
							data.getString("MRC_CMRCDSAM"),//descrizioneMercato
							data.getString("PZL_CPZLCTIP"),//codicePiazzola
							data.getString("PZL_CPZLDSPZ"),//descrizionePiazzola
							data.getString("TPB_CTPBDSTB"),//tipologiaBanco
							data.getString("PEG_CPEGDSPE"),//periodoGiornaliero
							data.getInt("TAM_ITAMGSEM"),//giornoSettimana, 
							new Date(data.getDate("PRN_GPRNDATA").getTime()),//data
							new Date(data.getDate("PRN_GPRNDTPG").getTime()), //dataPagamento
							data.getFloat("PRN_DPRNIMPO"), //importoPagato
							data.getFloat("PRN_DPRNIMPO_D"),//importoDovuto
							null,null,null,null);
					piazzola.setPuntoN(data.getFloat("PZL_DPZLA1LT")+","+data.getFloat("PZL_DPZLA1LG"));
					piazzola.setPuntoS(data.getFloat("PZL_DPZLA2LT")+","+data.getFloat("PZL_DPZLA2LG"));
					piazzola.setPuntoE(data.getFloat("PZL_DPZLA3LT")+","+data.getFloat("PZL_DPZLA3LG"));
					piazzola.setPuntoO(data.getFloat("PZL_DPZLA4LT")+","+data.getFloat("PZL_DPZLA4LG"));
				}
			}
		}catch (Exception e) {
			e.printStackTrace();
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
			if (getPRNStatement != null) {
				try {
					getPRNStatement.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		//fine LP PG21XX04 Leak
		return piazzola;
	}
	
	public List<PrenotazionePiazzolaMercato> doList(String codSocieta, String codUt, String codEnte, String codiceFiscale, String codMercato, String codPiazzola, Calendar dataInizio, Calendar dataFine, Boolean pagate) throws DaoException{
		
		if( codUt == null || codUt.equals("") || codSocieta == null || codSocieta.equals("") || codEnte == null || codEnte.equals("")){
			throw new IllegalArgumentException(" codUt, codSocieta, codEnte sono richiesti");
		}
		CallableStatement callableStatement = null;
		//inizio LP PG21XX04 Leak
		ResultSet data = null;
		//fine LP PG21XX04 Leak
		List<PrenotazionePiazzolaMercato> prenotazioniPiazzola = new ArrayList<PrenotazionePiazzolaMercato>();
		try	{
			callableStatement = prepareCall(Routines.PYPZLSP_LST_PRN.routine());
			callableStatement.setObject(1, codSocieta, java.sql.Types.CHAR);
			callableStatement.setObject(2, codUt, java.sql.Types.CHAR);
			callableStatement.setObject(3, codEnte, java.sql.Types.CHAR);
			callableStatement.setString(4, codiceFiscale==null?"":codiceFiscale);
			callableStatement.setString(5, codMercato==null?"":codMercato);
			callableStatement.setString(6, codPiazzola==null?"":codPiazzola);
			if(pagate == null){
				callableStatement.setString(7, "");
			}else if(pagate){
				callableStatement.setString(7, "Y");
			}else if(!pagate){
				callableStatement.setString(7, "N");
			}
			
			callableStatement.setTimestamp(8, new java.sql.Timestamp(dataInizio.getTimeInMillis()));
			callableStatement.setTimestamp(9, new java.sql.Timestamp(dataFine.getTimeInMillis()));
			callableStatement.registerOutParameter(10, java.sql.Types.VARCHAR);
			if (callableStatement.execute()) {
				//inizio LP PG21XX04 Leak
				//ResultSet data = callableStatement.getResultSet();
				data = callableStatement.getResultSet();
				//fine LP PG21XX04 Leak
				while (data.next()){
					//18072018 - inizio
					System.out.println("chiavePrenotazione " + data.getString("PRN_KPRNKPRN"));
					System.out.println("importoPagato " + data.getFloat("PRN_DPRNIMPO"));
					System.out.println("importoDovuto " + data.getFloat("PRN_DPRNIMPO_D"));
					//18072018 - fine
					PrenotazionePiazzolaMercato prn = new PrenotazionePiazzolaMercato(
							data.getString("PRN_KPRNKPRN"),//chiavePrenotazione
							data.getString("AUT_CAUTNMAU"),//codiceAutorizzazione
							data.getString("AUT_CAUTNOMN"),//nominativoAutorizzazione
							null,//codiceFiscale
							null,//codiceBorsellino
							data.getString("MRC_KMRCKMRC"),//codiceMercato
							data.getString("MRC_CMRCDSAM"),//descrizioneMercato
							data.getString("PZL_CPZLCTIP"),//codicePiazzola
							data.getString("PZL_CPZLDSPZ"),//descrizionePiazzola
							data.getString("TPB_CTPBDSTB"),//tipologiaBanco
							data.getString("PEG_CPEGDSPE"),//periodoGiornaliero
							data.getInt("TAM_ITAMGSEM"),//giornoSettimana, 
							new Date(data.getDate("PRN_GPRNDATA").getTime()),//data
							null, //dataPagamento
							data.getFloat("PRN_DPRNIMPO"), //importoPagato
							data.getFloat("PRN_DPRNIMPO_D"),//importoDovuto
							null,null,null,null);
					prn.setPuntoN(data.getFloat("PZL_DPZLA1LT")+","+data.getFloat("PZL_DPZLA1LG"));
					prn.setPuntoS(data.getFloat("PZL_DPZLA2LT")+","+data.getFloat("PZL_DPZLA2LG"));
					prn.setPuntoE(data.getFloat("PZL_DPZLA3LT")+","+data.getFloat("PZL_DPZLA3LG"));
					prn.setPuntoO(data.getFloat("PZL_DPZLA4LT")+","+data.getFloat("PZL_DPZLA4LG"));
					prenotazioniPiazzola.add(prn);
				}
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
		return prenotazioniPiazzola;
		
	}
	
	public List<PrenotazionePiazzolaMercato> doListPiazzole(String codSocieta, String codUt, String codEnte, String codiceFiscale) throws DaoException{
		
		if( codUt == null || codUt.equals("") || codSocieta == null || codSocieta.equals("") || codEnte == null || codEnte.equals("")){
			throw new IllegalArgumentException(" codUt, codSocieta, codEnte sono richiesti");
		}
		CallableStatement callableStatement = null;
		//inizio LP PG21XX04 Leak
		ResultSet data = null;
		//fine LP PG21XX04 Leak
		List<PrenotazionePiazzolaMercato> piazzole = new ArrayList<PrenotazionePiazzolaMercato>();
		try	{
			callableStatement = prepareCall("PYPZLSP_LST_AUT");
			callableStatement.setObject(1, codSocieta, java.sql.Types.CHAR);
			callableStatement.setObject(2, codUt, java.sql.Types.CHAR);
			callableStatement.setObject(3, codEnte, java.sql.Types.CHAR);
			callableStatement.setString(4, codiceFiscale);
			
			if (callableStatement.execute()) {
				//inizio LP PG21XX04 Leak
				//ResultSet data = callableStatement.getResultSet();
				data = callableStatement.getResultSet();
				//fine LP PG21XX04 Leak
				while (data.next()){
					PrenotazionePiazzolaMercato prn = new PrenotazionePiazzolaMercato(
							null,//chiavePrenotazione
							data.getString("AUT_CAUTNMAU"),//codiceAutorizzazione
							data.getString("AUT_CAUTNOMN"),//nominativoAutorizzazione
							null,//codiceFiscale
							null,//codiceBorsellino
							data.getString("MRC_KMRCKMRC"),//codiceMercato
							data.getString("MRC_CMRCDSAM"),//descrizioneMercato
							data.getString("PZL_CPZLCTIP"),//codicePiazzola
							data.getString("PZL_CPZLDSPZ"),//descrizionePiazzola
							data.getString("TPB_CTPBDSTB"),//tipologiaBanco
							data.getString("PEG_CPEGDSPE"),//periodoGiornaliero
							data.getInt("TAM_ITAMGSEM"),//giornoSettimana, 
							null,//data
							null, //dataPagamento
							null, //importoPagato
							0,//importoDovuto
							null,null,null,null);
					piazzole.add(prn);
				}
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
		return piazzole;
		
	}
	
	/**	
	 * @param chiaveBorsellino
	 * @param importoPagamento
	 * @param chiavePrenotazione
	 * @return "true" in caso di successo o solleva eccezione!
	 * @throws DaoException, Exception
	 */
	public Boolean doPagamento(String chiaveBorsellino, float importoPagamento, String chiavePrenotazione) throws DaoException, Exception{
		//inizio LP PG21XX04 Leak
		CallableStatement getPRNStatement = null;
		CallableStatement callableStatement = null;
		CallableStatement updatePRNStatement = null;
		ResultSet prnData = null;
		//fine LP PG21XX04 Leak
		try	{
			//inizio LP PG21XX04 Leak
			//CallableStatement getPRNStatement = null;
			//fine LP PG21XX04 Leak
			getPRNStatement = prepareCall(Routines.PYPRNSP_SLK.routine());
			getPRNStatement.setString(1, chiavePrenotazione);
			if (getPRNStatement.execute()) {
				//inizio LP PG21XX04 Leak
				//ResultSet prnData = getPRNStatement.getResultSet();
				prnData = getPRNStatement.getResultSet();
				//fine LP PG21XX04 Leak
				
				if (prnData.next()){
					float prnImportoPagato = prnData.getFloat("PRN_DPRNIMPO");
					float prnImportoDovuto = prnData.getFloat("PRN_DPRNIMPO_D");
					float prnImportoCompenso = prnData.getFloat("IMP_COMPENSO");
					float prnIvaCompenso = prnData.getFloat("IMP_IVA_COMPENSO");
					float prnCosap = prnData.getFloat("TAM_DTAMTRCO");
					float prnTari = prnData.getFloat("TAM_DTAMTRTA");
					
					if(prnImportoPagato > 0){
						throw new Exception("La piazzola risulta già pagata");
					}
					if(importoPagamento != prnImportoDovuto){
						throw new Exception("L'importo che si intende pagare non corrisponde alle tariffe in vigore");
					}
					if(prnData.getString("FLAG_CONFIG").equals("N")){
						throw new Exception("La piazzola non può essere pagata per mancanza di configurazione");
					}
					
					//inizio LP PG21XX04 Leak
					//CallableStatement callableStatement = null;
					//fine LP PG21XX04 Leak
					callableStatement = prepareCall(Routines.PYADBSP_INS_OND.routine());
					//18072018 GG - inizio
					System.out.println("chiaveBorsellino = " + chiaveBorsellino);
					System.out.println("importoPagamento = " + new BigDecimal(importoPagamento).toString());
					System.out.println("importoPagamentoFloat = " + importoPagamento);
					System.out.println("chiavePrenotazione = " + chiavePrenotazione);
					//18072018 GG - fine
					callableStatement.setString(1, chiaveBorsellino);
					callableStatement.setFloat(2,importoPagamento);
					callableStatement.setString(3, chiavePrenotazione);
					callableStatement.registerOutParameter(4, java.sql.Types.VARCHAR);
					callableStatement.registerOutParameter(5, java.sql.Types.CHAR);
					callableStatement.registerOutParameter(6, java.sql.Types.VARCHAR);
					callableStatement.execute();
					if(callableStatement.getString(5).equals("00")){ // 00 SIGNIFICA PAGAMENTO RIUSCITO
						
						//inizio LP PG21XX04 Leak
						//CallableStatement updatePRNStatement = null;
						//fine LP PG21XX04 Leak
						updatePRNStatement = prepareCall("PYPRNSP_PAY");
						updatePRNStatement.setString(1, chiavePrenotazione);
						updatePRNStatement.setFloat(2, importoPagamento);
						updatePRNStatement.setFloat(3, prnTari);
						updatePRNStatement.setFloat(4, prnCosap);
						updatePRNStatement.setFloat(5, prnImportoCompenso);
						updatePRNStatement.setFloat(6, prnIvaCompenso);
						updatePRNStatement.setTimestamp(7, new java.sql.Timestamp(System.currentTimeMillis()));
						updatePRNStatement.registerOutParameter(8, java.sql.Types.INTEGER);
						updatePRNStatement.execute();
						if(updatePRNStatement.getInt(8)!=1){
							throw new Exception("Si è verificato un problema nel salvare il pagamento della piazzola");
						}
					}else{
						throw new Exception(callableStatement.getString(6));
					}
				}else{
					throw new Exception("Non è stato possibile recuperare la prenotazione");
				}
			}else{
				throw new Exception("Si è verificato un problema nel recupero della prenotazione");
			}
		}catch (SQLException x) {
			throw new DaoException(x);
		} catch (IllegalArgumentException x) {
			throw new DaoException(x);
		} catch (HelperException x) {
			throw new DaoException(x);
		}
		//inizio LP PG21XX04 Leak
		finally {
			if (prnData != null) {
				try {
					prnData.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (updatePRNStatement != null) {
				try {
					updatePRNStatement.close();
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
			if (getPRNStatement != null) {
				try {
					getPRNStatement.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		//fine LP PG21XX04 Leak
		return Boolean.TRUE;
	}
	
	public List<Mercato> listMercati(String codSocieta, String codUt, String codEnte,  String codiceFiscale, Date from, Date to) throws DaoException{
		if( codUt == null || codUt.equals("") || codSocieta == null || codSocieta.equals("") || codEnte == null || codEnte.equals("")){
			throw new IllegalArgumentException(" codUt, codSocieta, codEnte sono richiesti");
		}
		CallableStatement callableStatement = null;
		List<Mercato> mercati = new ArrayList<Mercato>();
		//inizio LP PG21XX04 Leak
		ResultSet data = null;
		//fine LP PG21XX04 Leak
		try	{
			callableStatement = prepareCall(Routines.PYMRCSP_LST_DATE.routine());
			callableStatement.setString(1, codSocieta);
			callableStatement.setString(2, codUt);
			callableStatement.setString(3, codEnte);
			callableStatement.setString(4, codiceFiscale==null?"":codiceFiscale);
			callableStatement.setTimestamp(5, new java.sql.Timestamp(from.getTime()));
			callableStatement.setTimestamp(6, new java.sql.Timestamp(to.getTime()));
			if (callableStatement.execute()) {
				//inizio LP PG21XX04 Leak
				//ResultSet data = callableStatement.getResultSet();
				data = callableStatement.getResultSet();
				//fine LP PG21XX04 Leak
				while (data.next()){
					mercati.add(new Mercato(data.getString("MRC_KMRCKMRC"), data.getString("MRC_CMRCDSAM")));
				}
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
		return mercati;
	}
}
