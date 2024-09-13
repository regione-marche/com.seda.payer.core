package com.seda.payer.core.dao;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.UndeclaredThrowableException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import com.seda.data.helper.HelperException;
import com.seda.payer.core.bean.NodoSpcRpt;
import com.seda.payer.core.bean.NodoSpcPsc;
import com.seda.payer.core.bean.NodoSpcPsp;
import com.seda.payer.core.exception.DaoException;
import com.seda.payer.core.handler.BaseDaoHandler;

public class NodoSpcDao extends BaseDaoHandler {
	
	public NodoSpcDao(Connection connection, String schema) {
		super(connection, schema);
	}
	
	/***
	 * Restituisce l'elenco dei PSP aderenti al Nodo SPC
	 * @return
	 * @throws DaoException
	 */
	public List<NodoSpcPsp> doListPsp() throws DaoException {
		CallableStatement callableStatement = null;
		ResultSet data = null;
		ResultSet dataCanali = null;
		List<NodoSpcPsp> lista = new ArrayList<NodoSpcPsp>();
		
		try	{
			callableStatement = prepareCall(Routines.PYPSPSP_LST_DDL.routine());
			
			if (callableStatement.execute()) 
			{
				NodoSpcPsp psp = new NodoSpcPsp();
				NodoSpcPsc psc = new NodoSpcPsc();
				
				//resultset 1
				data = callableStatement.getResultSet();
				
				while (data.next())
				{
					psp = new NodoSpcPsp(data);
					//Estraggo canali di pagamento del psp
					List<NodoSpcPsc> listaCanali = new ArrayList<NodoSpcPsc>();
					callableStatement = prepareCall(Routines.PYPSCSP_LST.routine());
					callableStatement.setString(1, psp.getChiave());
					if (callableStatement.execute()) {
						dataCanali = callableStatement.getResultSet();
						while (dataCanali.next())
						{
							psc = new NodoSpcPsc(dataCanali);
							listaCanali.add(psc);
						}
					}
					psp.setListaCanali(listaCanali);
					lista.add(psp);
				}
			}	
		} catch (SQLException x) {
			throw new DaoException(x);
		} catch (IllegalArgumentException x) {
			throw new DaoException(x);
		} catch (HelperException x) {
			throw new DaoException(x);
		} finally {
			//inizio LP PG21XX04 Leak
			//if (callableStatement != null)
			//	DAOHelper.closeIgnoringException(callableStatement);
			//if (data != null)
			//	DAOHelper.closeIgnoringException(data);
			//if (dataCanali != null)
			//	DAOHelper.closeIgnoringException(dataCanali);
			if (dataCanali != null) {
				try {
					dataCanali.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
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
		
		return lista;
		
	}

	//inizio LP 20240811  - PGNTCORE-24
	//private BufferedWriter getFilePath(String nomeFilePath) throws FileNotFoundException  {
	//	return  new BufferedWriter( new OutputStreamWriter( new FileOutputStream( new File(nomeFilePath) , false) )  );   // il true finale indica che siamo in append
	//}
	//fine LP 20240811  - PGNTCORE-24

	/***
	 * Svuota e ricarica la tabella dei PSP ottenuti dal Nodo Nazionale SPC
	 * @param nodoSpcPsp
	 * @return
	 * @throws DaoException
	 */
	public int popolaListaPspNodoSpc(List<NodoSpcPsp> listaPspNodoSpc) throws DaoException 
	{
		int code = 0;
		CallableStatement callableStatement = null;
//		BufferedWriter filelog = null;
		try	{
			//chiamata a sp di azzeramenteo tabella psp
			
			callableStatement = prepareCall(Routines.PYPSPSP_DEL.routine());
			callableStatement.execute();
			//inizio LP PG21XX04 Leak
			if (callableStatement != null) {
				try {
					callableStatement.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			//fine LP PG21XX04 Leak
			//chiamata a sp di azzeramenteo tabella psc
			callableStatement = prepareCall(Routines.PYPSCSP_DEL.routine());
			callableStatement.execute();
			
		
			for (NodoSpcPsp psp : listaPspNodoSpc) {
				callableStatement = prepareCall(Routines.PYPSPSP_INS.routine());
				callableStatement.setString(1, psp.getChiave());
				callableStatement.setString(2, "");
				callableStatement.setString(3, psp.getNome());				
				//callableStatement.registerOutParameter(7, Types.INTEGER);				
				
//				try {
//					filelog = getFilePath("D:\\Applications\\Pagonet2\\Log\\logDAONodoBatch.log");
//				
//				} catch (FileNotFoundException e) {
//					e.printStackTrace();
//				}
//				try {
//					filelog.write("psp.getChiave() = " + psp.getChiave());
//					filelog.write("psp.getChiave() = " + psp.getNome());
//					
//				} catch (IOException e) {
//					e.printStackTrace();
//				}
				
				callableStatement.execute();
				
				//Inserimeno canali di pagamento in tabella PYPSCTB
				for (NodoSpcPsc psc : psp.getListaCanali()) {
					
					
//					try {
//						filelog.write("getIdCanale = " + psc.getIdCanale());
//						filelog.write("getIdPsp = " + psc.getIdPsp());
//						filelog.write("getIdIntermediario = " + psc.getIdIntermediario());
//						filelog.write("getTipoVersamento = " + psc.getTipoVersamento());
//						filelog.write("getPriorita = " + psc.getPriorita());
//						filelog.write("getDisponibilitaServizio = " + psc.getDisponibilitaServizio());
//						filelog.write("getDescrizioneServizio = " + psc.getDescrizioneServizio());
//						filelog.write("getCondizioniEconomiche = " + psc.getCondizioniEconomiche());
//						filelog.write("getUrl = " + psc.getUrl());
//					} catch (IOException e) {
//					}
					
					callableStatement = prepareCall(Routines.PYPSCSP_INS.routine());
					callableStatement.setString(1, psc.getIdCanale());
					callableStatement.setString(2, psc.getIdPsp());
					callableStatement.setString(3, psc.getIdIntermediario());
					callableStatement.setString(4, psc.getTipoVersamento());
					callableStatement.setInt(5, psc.getPriorita());
					callableStatement.setString(6, psc.getDisponibilitaServizio());
					callableStatement.setString(7, psc.getDescrizioneServizio());
					callableStatement.setString(8, psc.getCondizioniEconomiche());
					callableStatement.setString(9, psc.getUrl());
					
					callableStatement.execute();
				}
			}

				//code = callableStatement.getInt(7);
			
		} catch (SQLException x) {
			if(x.getErrorCode()== -803){
				throw new DaoException(55,"esiste già un psp con la chiave selezionata");
			}
			throw new DaoException(x);
		//inizio LP 20240811  - PGNTCORE-24
		} catch (UndeclaredThrowableException x) {
			DaoException.makeIfDuplicateKeyError(x, 55, "Esiste già un psp con la chiave selezionata");
		//fine LP 20240811  - PGNTCORE-24
		} catch (IllegalArgumentException x) {
			System.out.println("doSave failed generic error due to: " + x.getMessage());
//			try {
//				filelog.write("errore  = " + x.getMessage());
//				filelog.close();
//				
//			} catch (IOException e) {
//				e.printStackTrace();
//			}
			throw new DaoException(101, x.getMessage());
		} catch (HelperException x) {
//			try {
//				filelog.write("errore  = " + x.getMessage());
//				filelog.close();
//			} catch (IOException e) {
//				e.printStackTrace();
//			}
			throw new DaoException(x);
		} finally {
//			try {
//				filelog.close();
//			} catch (IOException e) {
//				e.printStackTrace();
//			}
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
		return code;
	}
	
	/***
	 * Restituisce il codice IUV della RPT appena inserita
	 * @param nodoSpcRpt
	 * @return
	 * @throws DaoException
	 */
	public String[] inserisciRptNodoSpc(NodoSpcRpt nodoSpcRpt) throws DaoException 
	{
		String[] outCode = new String[2];
		outCode[0] = "";
		outCode[1] = "";
		CallableStatement callableStatement = null;
		String Iuv = "";
		//29072016 PG160130 GG - inizio
		if (nodoSpcRpt.getCodiceIuv()!=null && !nodoSpcRpt.getCodiceIuv().trim().equals("")) {
			//Modello 3
			Iuv = nodoSpcRpt.getCodiceIuv();
		}
		//29072016 PG160130 GG - fine
		try	{
			//Insert del record
			//inizio LP PG21XX08_1
			//callableStatement = prepareCall(Routines.PYRPTSP_INS.routine());
			//Nota. Per il cutecute 000R0 o 000R3 (in entrambi i casi Creset) si esegue il calcolo dello IUV
			//      prelevando il codice segregazione dalla tab MDT
			//      deve essere presente un record con:
			//      cutecute == nodoSpcRpt.getCodUtente(),
			//      codsocieta == nodoSpcRpt.getCodSocieta()
			//      iddominio == nodoSpcRpt.getIdDominio()
			//      auxdigit == "3"
			//      (viene selezionato il primo della query)
			//      Se non e' presente almeno un record si produce una eccezione!
			boolean bCuteCute = (nodoSpcRpt.getCodUtente().equals("000R0") || nodoSpcRpt.getCodUtente().equals("000R3"));
			if(Iuv.equals("") && bCuteCute)
				callableStatement = prepareCall("PYRPTSP_INS_CDSE");
			else
				callableStatement = prepareCall(Routines.PYRPTSP_INS.routine());
			//fine LP PG21XX08_1
			callableStatement.setString(1, nodoSpcRpt.getChiaveTra());
			callableStatement.setString(2, nodoSpcRpt.getCodSocieta());
			callableStatement.setString(3, nodoSpcRpt.getCodUtente());
			//29072016 GG PG160130 - inizio
			//callableStatement.setString(4, "");
			callableStatement.setString(4, Iuv);
			//29072016 GG PG160130 - fine
			callableStatement.setString(5, nodoSpcRpt.getCodiceTabella());
			callableStatement.setString(6, nodoSpcRpt.getChiaveTabella());
			callableStatement.setBigDecimal(7, nodoSpcRpt.getImporto());
			callableStatement.setString(8, nodoSpcRpt.getRpt());
			callableStatement.setString(9, nodoSpcRpt.getRptEsito());
			callableStatement.setString(10, nodoSpcRpt.getRptFirma());
			callableStatement.setString(11, nodoSpcRpt.getRt());
			callableStatement.setString(12, nodoSpcRpt.getRtEsito());
			callableStatement.setString(13, nodoSpcRpt.getRtFirma());
			callableStatement.setString(14, nodoSpcRpt.getCodContestoPagamento());	//29072016 GG PG160130
			callableStatement.setString(15, nodoSpcRpt.getIdDominio());				//29072016 GG PG160130
			callableStatement.setString(16, nodoSpcRpt.getIdPSP());					//31012017 GG PG160130_02X
			callableStatement.setString(17, nodoSpcRpt.getIdIntermediarioPSP());	//31012017 GG PG160130_02X	
			callableStatement.setString(18, nodoSpcRpt.getIdCanalePSP());			//31012017 GG PG160130_02X
			callableStatement.registerOutParameter(19, Types.BIGINT);
			callableStatement.registerOutParameter(20, Types.BIGINT);				//29072016 GG PG160130
			//inizio LP PG21XX08_1
			if(Iuv.equals("") && bCuteCute)
				callableStatement.registerOutParameter(21, Types.VARCHAR);
			//fine LP PG21XX08_1

			callableStatement.execute();
			//recupero id
			int id = (int) callableStatement.getLong(19);
			System.out.println("ID dopo INS = " + id);
			
			int progIuv = (int) callableStatement.getLong(20);
			System.out.println("PROG IUV dopo INS = " + progIuv);
			
			//inizio LP PG21XX08_1
			String alfaIuv = "KO";
			
			if(Iuv.equals("") && bCuteCute) {
				alfaIuv = callableStatement.getString(21);
				if(alfaIuv.equals("KO")) {
					String mErr = "manca configurazione del codice segregazione per cutecute = '" + nodoSpcRpt.getCodUtente() + "', societa' = '" + nodoSpcRpt.getCodSocieta() + "', iddominio = '" + nodoSpcRpt.getIdDominio() + "' e auxidigit = '3'.";
					System.out.println("inserisciRptNodoSpc errore: " + mErr);
					throw new DaoException(56, mErr);
				}
			}
			//fine LP PG21XX08_1
			//29072016 GG PG160130 - In caso di iuv valorizzato non occorre ricalcolarlo ed aggiornare la PYRPTTB
			if (Iuv.equals("")) {	//Non Modello 3
				//Calcolo IUV
				//Iuv = CalculateIUV(id);		//10082016 GG PG160130
				//inizio LP PG21XX08_1
				if(bCuteCute) {
					System.out.println("inserisciRptNodoSpc da DB alfaIuv = '" + alfaIuv + "'");
					Iuv = CalculateIUV(alfaIuv);
				} else
				{
					//fine LP PG21XX08_1
					Iuv = CalculateIUV(progIuv);	//10082016 GG PG160130
						
				}
				System.out.println("Iuv = " + Iuv);
				System.out.println("ID prima UPD = " + id);
				//Update iuv
				//inizio LP PG21XX04 Leak
				if (callableStatement != null) {
					try {
						callableStatement.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
				//fine LP PG21XX04 Leak
				callableStatement = prepareCall(Routines.PYRPTSP_UPD.routine());
				callableStatement.setLong(1, id);
				callableStatement.setNull(2, Types.VARCHAR);
				callableStatement.setNull(3, Types.CHAR);
				callableStatement.setNull(4, Types.CHAR);
				callableStatement.setString(5, Iuv);
				callableStatement.setNull(6, Types.CHAR);
				callableStatement.setNull(7, Types.VARCHAR);
				callableStatement.setNull(8, Types.DECIMAL);
				callableStatement.setNull(9, Types.BINARY);
				callableStatement.setNull(10, Types.VARCHAR);
				callableStatement.setNull(11, Types.VARCHAR);
				callableStatement.setNull(12, Types.BINARY);
				callableStatement.setNull(13, Types.VARCHAR);
				callableStatement.setNull(14, Types.VARCHAR);
				callableStatement.setNull(15, Types.VARCHAR);
				callableStatement.setNull(16, Types.VARCHAR);
				callableStatement.setNull(17, Types.VARCHAR);	//29072016 GG PG160130 introdotto codContestoPagamento
				callableStatement.setNull(18, Types.VARCHAR);	//29072016 GG PG160130 introdotto idDominio
				callableStatement.setNull(19, Types.VARCHAR);
				callableStatement.setNull(20, Types.VARCHAR);
				callableStatement.setNull(21, Types.VARCHAR);
				//inizio LP PG180290
				/*
				callableStatement.registerOutParameter(22, Types.INTEGER);
				
				callableStatement.execute();
				
				int recCount = callableStatement.getInt(22);
				*/
				callableStatement.setNull(22, Types.VARCHAR);
				callableStatement.setNull(23, Types.VARCHAR);
				callableStatement.setNull(24, Types.VARCHAR);
				
				//inizio LP PG190220
				//callableStatement.registerOutParameter(25, Types.INTEGER);
				callableStatement.setNull(25, Types.VARCHAR);//RT Annullata
				callableStatement.setNull(26, Types.VARCHAR);//RT Annullata Esito
				callableStatement.setNull(27, Types.VARCHAR);//ER Esito
				callableStatement.setNull(28, Types.VARCHAR);//Esito Inivio Email Revoca Notifica Admin
				callableStatement.setNull(29, Types.TIMESTAMP);//Data Esito Inivio Email Revoca Notifica Admin
				callableStatement.setNull(30, Types.VARCHAR);//Esito Inivio Email Revoca Notifica Contribuente
				callableStatement.setNull(31, Types.TIMESTAMP);//Data Esito Inivio Email Revoca Notifica Contribuente
//				YLM PG22XX07 INI
				callableStatement.setNull(32, Types.TIMESTAMP);//XML SEND RT
//				YLM PG22XX07 FINE
				callableStatement.registerOutParameter(33, Types.INTEGER);
				//fine LP PG190220

				callableStatement.execute();
				
				//inizio LP PG190220
				//int recCount = callableStatement.getInt(25);
				//inizio LP 20240811  - PGNTCORE-24
				//int recCount = callableStatement.getInt(33);
				//fine LP 20240811  - PGNTCORE-24
				//fine LP PG190220
				//fine LP PG180290
				
			}
			outCode[0] = String.valueOf(id);
			outCode[1] = Iuv;
		} catch (SQLException x) {
			if(x.getErrorCode()== -803){
				throw new DaoException(55,"esiste già una rpt con la chiave selezionata");
			}
			throw new DaoException(x);
		//inizio LP 20240811  - PGNTCORE-24
		} catch (UndeclaredThrowableException x) {
			DaoException.makeIfDuplicateKeyError(x, 55, "Esiste già un rpt con la chiave selezionata");
		//fine LP 20240811  - PGNTCORE-24
		} catch (IllegalArgumentException x) {
			x.printStackTrace();
			System.out.println("doSave failed generic error due to: " + x.getMessage());
			throw new DaoException(101, x.getMessage());
		} catch (HelperException x) {
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
		return outCode;
	}
	

	/***
	 * Aggiorna la tabella RPT con i valori passati diversi da null
	 * @param nodoSpcRpt
	 * @return numero di righe modificate
	 * @throws DaoException
	 */
	//inizio LP 20240826 - PGNTBNPE-1
	public int updateRptNodoSpc(NodoSpcRpt nodoSpcRpt) throws DaoException 
	{
		return updateRptNodoSpcTail(true, nodoSpcRpt);
	}
		
	public int updateRptNodoSpcTail(boolean bFlagAutocommitUpdate, NodoSpcRpt nodoSpcRpt) throws DaoException 
	//fine LP 20240826 - PGNTBNPE-1
	{
		int recCount = 0;
		CallableStatement callableStatement = null;
		try	{
			//recupero id
			int id = nodoSpcRpt.getId().intValue();
			System.out.println("recupero id = " + id);
			//inizio LP 20240826 - PGNTBNPE-1
			//callableStatement = prepareCall(Routines.PYRPTSP_UPD.routine());
			callableStatement = prepareCall(bFlagAutocommitUpdate, Routines.PYRPTSP_UPD.routine());
			//fine LP 20240826 - PGNTBNPE-1
			callableStatement.setInt(1, id);
			if(nodoSpcRpt.getChiaveTra() != null &&  !nodoSpcRpt.getChiaveTra().equalsIgnoreCase(""))
				callableStatement.setString(2, nodoSpcRpt.getChiaveTra());
			else
				callableStatement.setNull(2, Types.VARCHAR);

			if(nodoSpcRpt.getCodSocieta() != null &&  !nodoSpcRpt.getCodSocieta().equalsIgnoreCase(""))
				callableStatement.setString(3, nodoSpcRpt.getCodSocieta());
			else
				callableStatement.setNull(3, Types.CHAR);

			if(nodoSpcRpt.getCodUtente() != null &&  !nodoSpcRpt.getCodUtente().equalsIgnoreCase(""))
				callableStatement.setString(4, nodoSpcRpt.getCodUtente());
			else
				callableStatement.setNull(4, Types.CHAR);
			
			if(nodoSpcRpt.getCodiceIuv() != null &&  !nodoSpcRpt.getCodiceIuv().equalsIgnoreCase(""))
				callableStatement.setString(5, nodoSpcRpt.getCodiceIuv());
			else
				callableStatement.setNull(5, Types.VARCHAR);
			
			if(nodoSpcRpt.getCodiceTabella() != null &&  !nodoSpcRpt.getCodiceTabella().equalsIgnoreCase(""))
				callableStatement.setString(6, nodoSpcRpt.getCodiceTabella());
			else
				callableStatement.setNull(6, Types.CHAR);
			
			if(nodoSpcRpt.getChiaveTabella() != null &&  !nodoSpcRpt.getChiaveTabella().equalsIgnoreCase(""))
				callableStatement.setString(7, nodoSpcRpt.getChiaveTabella());
			else
				callableStatement.setNull(7, Types.VARCHAR);
			
			if(nodoSpcRpt.getImporto() != null &&  nodoSpcRpt.getImporto().compareTo(new BigDecimal(0)) > 0)
				callableStatement.setBigDecimal(8, nodoSpcRpt.getImporto());
			else
				callableStatement.setNull(8, Types.DECIMAL);
			
			if(nodoSpcRpt.getRpt() != null &&  !nodoSpcRpt.getRpt().equalsIgnoreCase(""))
				callableStatement.setString(9, nodoSpcRpt.getRpt());
			else
				callableStatement.setNull(9, Types.BINARY);
			
			if(nodoSpcRpt.getRptEsito() != null &&  !nodoSpcRpt.getRptEsito().equalsIgnoreCase(""))
				callableStatement.setString(10, nodoSpcRpt.getRptEsito());
			else
				callableStatement.setNull(10, Types.VARCHAR);
			
			if(nodoSpcRpt.getRptFirma() != null &&  !nodoSpcRpt.getRptFirma().equalsIgnoreCase(""))
				callableStatement.setString(11, nodoSpcRpt.getRptFirma());
			else
				callableStatement.setNull(11, Types.VARCHAR);
			
			if(nodoSpcRpt.getRt() != null && !nodoSpcRpt.getRt().equalsIgnoreCase(""))
			//inizio LP PG190220
			{	
				if(nodoSpcRpt.getRt().equalsIgnoreCase("<svuota>")) {
					nodoSpcRpt.setRt(""); 
				}
			//fine LP PG190220
				callableStatement.setString(12, nodoSpcRpt.getRt());
			//inizio LP PG190220
			}
			//fine LP PG190220
			else
				callableStatement.setNull(12, Types.BINARY);
			
			if(nodoSpcRpt.getRtEsito() != null && !nodoSpcRpt.getRtEsito().equalsIgnoreCase(""))
			//inizio LP PG190220
			{
				if(nodoSpcRpt.getRtEsito().equalsIgnoreCase("<svuota>")) {
					nodoSpcRpt.setRtEsito(""); 
				}
			//fine LP PG190220
				callableStatement.setString(13, nodoSpcRpt.getRtEsito());
				//inizio LP PG190220
			}
			//fine LP PG190220
			else
				callableStatement.setNull(13, Types.VARCHAR);
			
			if(nodoSpcRpt.getRtFirma() != null && !nodoSpcRpt.getRtFirma().equalsIgnoreCase(""))
				callableStatement.setString(14, nodoSpcRpt.getRtFirma());
			else
				callableStatement.setNull(14, Types.VARCHAR);
			
			//inizio LP PG190220
			if(nodoSpcRpt.getRr() != null && nodoSpcRpt.getRr().length() > 0) {
				if(nodoSpcRpt.getRr().equalsIgnoreCase("<svuota>")) {
					nodoSpcRpt.setRr(""); 
				}
				callableStatement.setString(15, nodoSpcRpt.getRr());
			} else
			//fine LP PG190220
				callableStatement.setNull(15, Types.VARCHAR);//RR
			
			//inizio LP PG190220
			if(nodoSpcRpt.getEr() != null && nodoSpcRpt.getEr().length() > 0) {
				if(nodoSpcRpt.getEr().equalsIgnoreCase("<svuota>")) {
					nodoSpcRpt.setEr(""); 
				}
				callableStatement.setString(16, nodoSpcRpt.getEr());
			} else
			//fine LP PG190220
				callableStatement.setNull(16, Types.VARCHAR);//ER
			
			//29072016 GG PG1601340 - inizio
			if(nodoSpcRpt.getCodContestoPagamento() != null &&  !nodoSpcRpt.getCodContestoPagamento().equalsIgnoreCase(""))
				callableStatement.setString(17, nodoSpcRpt.getCodContestoPagamento());
			else
				callableStatement.setNull(17, Types.VARCHAR);
			
			if(nodoSpcRpt.getIdDominio() != null &&  !nodoSpcRpt.getIdDominio().equalsIgnoreCase(""))
				callableStatement.setString(18, nodoSpcRpt.getIdDominio());
			else
				callableStatement.setNull(18, Types.VARCHAR);
			//29072016 GG PG1601340 - fine
			
			//310120107 GG - inizio
			if(nodoSpcRpt.getIdPSP() != null &&  !nodoSpcRpt.getIdPSP().equalsIgnoreCase(""))
				callableStatement.setString(19, nodoSpcRpt.getIdPSP());
			else
				callableStatement.setNull(19, Types.VARCHAR);
			
			if(nodoSpcRpt.getIdIntermediarioPSP() != null &&  !nodoSpcRpt.getIdIntermediarioPSP().equalsIgnoreCase(""))
				callableStatement.setString(20, nodoSpcRpt.getIdIntermediarioPSP());
			else
				callableStatement.setNull(20, Types.VARCHAR);
			
			if(nodoSpcRpt.getIdCanalePSP() != null &&  !nodoSpcRpt.getIdCanalePSP().equalsIgnoreCase(""))
				callableStatement.setString(21, nodoSpcRpt.getIdCanalePSP());
			else
				callableStatement.setNull(21, Types.VARCHAR);
			//310120107 GG - fine
			//inizio LP PG180290
			/*
			callableStatement.registerOutParameter(22, Types.INTEGER);
			
			callableStatement.execute();
			
			recCount = callableStatement.getInt(22);
			*/
			if(nodoSpcRpt.getIdSessioneCarrello() != null && nodoSpcRpt.getIdSessioneCarrello().length() > 0)
				callableStatement.setString(22, nodoSpcRpt.getIdSessioneCarrello());
			else
				callableStatement.setNull(22, Types.VARCHAR);
			if(nodoSpcRpt.getIdentificativoTipoDovuto() != null &&  nodoSpcRpt.getIdentificativoTipoDovuto().length() > 0)
				callableStatement.setString(23, nodoSpcRpt.getIdentificativoTipoDovuto());
			else
				callableStatement.setNull(23, Types.VARCHAR);
			if(nodoSpcRpt.getIdentificativoUnivocoDovuto() != null &&  nodoSpcRpt.getIdentificativoUnivocoDovuto().length() > 0)
				callableStatement.setString(24, nodoSpcRpt.getIdentificativoUnivocoDovuto());
			else
				callableStatement.setNull(24, Types.VARCHAR);
			
			//inizio LP PG190220
			//callableStatement.registerOutParameter(25, Types.INTEGER);
			if(nodoSpcRpt.getRtAnnullata() != null && !nodoSpcRpt.getRtAnnullata().equalsIgnoreCase("")) {
				if(nodoSpcRpt.getRtAnnullata().equalsIgnoreCase("<svuota>")) {
					nodoSpcRpt.setRtAnnullata(""); 
				}
				callableStatement.setString(25, nodoSpcRpt.getRtAnnullata());
			} else
				callableStatement.setNull(25, Types.VARCHAR);//RT Annullata

			if(nodoSpcRpt.getRtAnnullataEsito() != null && !nodoSpcRpt.getRtAnnullataEsito().equalsIgnoreCase("")) {
				if(nodoSpcRpt.getRtAnnullataEsito().equalsIgnoreCase("<svuota>")) {
					nodoSpcRpt.setRtAnnullataEsito(""); 
				}
				callableStatement.setString(26, nodoSpcRpt.getRtAnnullataEsito());
			} else
				callableStatement.setNull(26, Types.VARCHAR);//RT Annullata Esito

			if(nodoSpcRpt.getErEsito() != null && nodoSpcRpt.getErEsito().length() > 0) {
				if(nodoSpcRpt.getErEsito().equalsIgnoreCase("<svuota>")) {
					nodoSpcRpt.setErEsito(""); 
				}
				callableStatement.setString(27, nodoSpcRpt.getErEsito());
			} else
				callableStatement.setNull(27, Types.VARCHAR);//ER Esito

			if(nodoSpcRpt.getEsitoInvioRevocaEmailAdmin() != null && nodoSpcRpt.getEsitoInvioRevocaEmailAdmin().length() > 0) {
				if(nodoSpcRpt.getEsitoInvioRevocaEmailAdmin().equalsIgnoreCase("<svuota>")) {
					nodoSpcRpt.setEsitoInvioRevocaEmailAdmin(""); 
				}
				callableStatement.setString(28, nodoSpcRpt.getEsitoInvioRevocaEmailAdmin());
			} else
				callableStatement.setNull(28, Types.VARCHAR);//Esito Inivio Email Revoca Notifica Admin

			if(nodoSpcRpt.getDataInvioRevocaEmailAdmin() != null) {
				callableStatement.setTimestamp(29, new java.sql.Timestamp(nodoSpcRpt.getDataInvioRevocaEmailAdmin().getTime()));
			} else
				callableStatement.setNull(29, Types.TIMESTAMP);//Data Esito Inivio Email Revoca Notifica Admin
			
			if(nodoSpcRpt.getEsitoInvioRevocaEmailContribuente() != null && nodoSpcRpt.getEsitoInvioRevocaEmailContribuente().length() > 0) {
				if(nodoSpcRpt.getEsitoInvioRevocaEmailContribuente().equalsIgnoreCase("<svuota>")) {
					nodoSpcRpt.setEsitoInvioRevocaEmailContribuente(""); 
				}
				callableStatement.setString(30, nodoSpcRpt.getEsitoInvioRevocaEmailContribuente());
			} else
				callableStatement.setNull(30, Types.VARCHAR);//Esito Inivio Email Revoca Notifica Contribuente
			
			if(nodoSpcRpt.getDataInvioRevocaEmailContribuente() != null) {
				callableStatement.setTimestamp(31, new java.sql.Timestamp(nodoSpcRpt.getDataInvioRevocaEmailContribuente().getTime()));
			} else
				callableStatement.setNull(31, Types.TIMESTAMP);//Data Esito Inivio Email Revoca Notifica Contribuente
//			YLM PG22XX07 INI
			if(nodoSpcRpt.getXmlSendRT() != null) {
				callableStatement.setString(32, nodoSpcRpt.getXmlSendRT());
			} else {
				callableStatement.setNull(32, Types.VARCHAR);
			}
//			YLM PG22XX07 FINE
			callableStatement.registerOutParameter(33, Types.INTEGER);
			//fine LP PG190220
			

			
			callableStatement.execute();
			
			//inizio LP PG190220
			//recCount = callableStatement.getInt(25);
			recCount = callableStatement.getInt(33);
			//fine LP PG190220
			//fine LP PG180290
			
			
		} catch (SQLException x) {
			if(x.getErrorCode()== -803){
				throw new DaoException(55,"esiste già una rpt con la chiave selezionata");
			}
			throw new DaoException(x);
		//inizio LP 20240811 - PGNTCORE-24
		} catch (UndeclaredThrowableException x) {
			DaoException.makeIfDuplicateKeyError(x, 55, "Esiste già un rpt con la chiave selezionata");
		//fine LP 20240811 - PGNTCORE-24
		} catch (IllegalArgumentException x) {
			System.out.println("doSave failed generic error due to: " + x.getMessage());
			throw new DaoException(101, x.getMessage());
		} catch (HelperException x) {
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
		return recCount;
	}

	private String CalculateIUV(int paymentId){
		String res = "";
		
		String id = String.valueOf(paymentId);
		String checkString = id + "2715" + "00"; //2715 fisso, corrisponde a 'RF' numerico
		long check = Long.valueOf(checkString) % 97;
		check = 98-check;
		//res = "RF" + ((check<10)?"0":"") + check + ("0000000000000000000000000000000" + id).substring(id.length());
		res = "RF" + ((check<10)?"0":"") + check + id;
//		String[] sGroup = res.split("(?<=\\G.{4})");
//		String gRes = "";
//		for (String gr : sGroup) {
//			gRes += gr + " ";
//		}
//		gRes = gRes.trim();
//		res = gRes;
		return res;
	}

	//inizio LP PG21XX08_1
	private String CalculateIUV(String alfaIuv){
		String res = "";
		
		BigInteger b1, b2;
		String checkString = alfaIuv + "2715" + "00"; //2715 fisso, corrisponde a 'RF' numerico
        b1 = new BigInteger(checkString);
        b2 = new BigInteger("97");
        BigInteger resultMod = b1.mod(b2);
		long check = resultMod.longValue();
		check = 98-check;
		res = "RF" + ((check<10)?"0":"") + check + alfaIuv;
		
		return res;
	}
	//fine LP PG21XX08_1

	//29072016 PG160130 GG introdotto codContestoPagamento
	//inizio LP 20240826 - PGNTBNPE-1
	public List<NodoSpcRpt> recuperaRPT(BigInteger id, String chiaveTra, String codiceIuv, String codContestoPagamento, String idDominio, String identificativoPSP) throws DaoException 
	{
		return recuperaRPTTail(true, id, chiaveTra, codiceIuv, codContestoPagamento, idDominio, identificativoPSP);
	}
		
	public List<NodoSpcRpt> recuperaRPTTail(boolean bFlagAutocommitUpdate, BigInteger id, String chiaveTra, String codiceIuv, String codContestoPagamento, String idDominio, String identificativoPSP) throws DaoException 
	//fine LP 20240826 - PGNTBNPE-1
	{
		List<NodoSpcRpt> listRpt = new ArrayList<NodoSpcRpt>(); 
		CallableStatement callableStatement = null;
		ResultSet data = null;
		try	{
			//inizio LP 20240826 - PGNTBNPE-1
			callableStatement = prepareCall(bFlagAutocommitUpdate,  Routines.PYRPTSP_SEL.routine());
			//fine LP 20240826 - PGNTBNPE-1
			if (id != null && id.longValue()>0)
				callableStatement.setInt(1, id.intValue());
			else
				callableStatement.setNull(1, Types.BIGINT);
			if (chiaveTra != null && !chiaveTra.equals(""))
				callableStatement.setString(2, chiaveTra);
			else
				callableStatement.setNull(2, Types.VARCHAR);
			if (codiceIuv != null && !codiceIuv.equals(""))
				callableStatement.setString(3, codiceIuv);
			else
				callableStatement.setNull(3, Types.VARCHAR);
			//29072016 PG160130 GG introdotto codContestoPagamento
			if (codContestoPagamento != null && !codContestoPagamento.equals(""))
				callableStatement.setString(4, codContestoPagamento);
			else
				callableStatement.setNull(4, Types.VARCHAR);
			//29072016 PG160130 GG introdotto idDominio
			if (idDominio != null && !idDominio.equals(""))
				callableStatement.setString(5, idDominio);
			else
				callableStatement.setNull(5, Types.VARCHAR);
			if (identificativoPSP != null && !identificativoPSP.equals(""))
				callableStatement.setString(6, identificativoPSP);
			else
				callableStatement.setNull(6, Types.VARCHAR);
			
			if(callableStatement.execute()) {
				data = callableStatement.getResultSet();
				while(data.next()) {
					NodoSpcRpt bean = new NodoSpcRpt(data);
					listRpt.add(bean);
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
		return listRpt;
	}
	
	//inizio LP PG22XX05 - Bug ricerca rpt per paSendRT
	public List<NodoSpcRpt> recuperaRPT(String codiceIuv, String idDominio) throws DaoException 
	{
		List<NodoSpcRpt> listRpt = new ArrayList<NodoSpcRpt>(); 
		CallableStatement callableStatement = null;
		ResultSet data = null;
		try	{
			callableStatement = prepareCall("PYRPTSP_SEL_SENDRT");
			callableStatement.setString(1, idDominio);
			callableStatement.setString(2, codiceIuv);
			if(callableStatement.execute()) {
				data = callableStatement.getResultSet();
				while(data.next()) {
					NodoSpcRpt bean = new NodoSpcRpt(data);
					listRpt.add(bean);
				}
			}
		} catch (Exception e) {
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
		return listRpt;
	}

	public List<NodoSpcRpt> recuperaRPT0(String codiceIuv, String idDominio) throws DaoException 
	{
		List<NodoSpcRpt> listRpt = new ArrayList<NodoSpcRpt>(); 
		CallableStatement callableStatement = null;
		ResultSet data = null;
		try	{
			callableStatement = prepareCall("PYRPTSP_SEL_SENDRT0");
			callableStatement.setString(1, idDominio);
			callableStatement.setString(2, codiceIuv);
			if(callableStatement.execute()) {
				data = callableStatement.getResultSet();
				while(data.next()) {
					NodoSpcRpt bean = new NodoSpcRpt(data);
					listRpt.add(bean);
				}
			}
		} catch (Exception e) {
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
		return listRpt;
	}
	//fine LP PG22XX05 - Bug ricerca rpt per paSendRT
	//Salvataggio RT
	/***
	 * Salva l'xml RT nella riga della tbrpttb già inserita
	 */
	public int inserisciRT(int paymentId, byte[] RT) throws DaoException 
	{
		int code = 0;
		CallableStatement callableStatement = null;
		try	{
			//Update RT
			callableStatement = prepareCall(Routines.PYRPTSP_UPD.routine());
			callableStatement.setInt(1, paymentId);
			callableStatement.setNull(2, Types.VARCHAR);
			callableStatement.setNull(3, Types.CHAR);
			callableStatement.setNull(4, Types.CHAR);
			callableStatement.setNull(5, Types.VARCHAR);
			callableStatement.setNull(6, Types.CHAR);
			callableStatement.setNull(7, Types.VARCHAR);
			callableStatement.setNull(8, Types.DECIMAL);
			callableStatement.setNull(9, Types.BINARY);
			callableStatement.setNull(10, Types.VARCHAR);
			callableStatement.setNull(11, Types.VARCHAR);
			
			//byte[] RTDecoded = org.apache.commons.codec.binary.Base64.decodeBase64(RT);
			//inizio LP 20210325 - Per Bug caratteri 'sfuri' su RPT\RT
			//callableStatement.setString(12, new String(RT));
			callableStatement.setString(12, new String(RT, "UTF-8"));
			//fine LP 20210325
			callableStatement.setNull(13, Types.VARCHAR);
			callableStatement.setNull(14, Types.VARCHAR);
			callableStatement.setNull(15, Types.VARCHAR);
			callableStatement.setNull(16, Types.VARCHAR);
			callableStatement.setNull(17, Types.VARCHAR);	//29072016 GG PG160130 introdotto codContestoPagamento
			callableStatement.setNull(18, Types.VARCHAR);	//29072016 GG PG160130 introdotto idDominio
			callableStatement.setNull(19, Types.VARCHAR);
			callableStatement.setNull(20, Types.VARCHAR);
			callableStatement.setNull(21, Types.VARCHAR);
			//inizio LP PG180290
			/*
			callableStatement.registerOutParameter(22, Types.INTEGER);
			
			callableStatement.execute();
			
			int recCount = callableStatement.getInt(22);
			*/
			callableStatement.setNull(22, Types.VARCHAR);
			callableStatement.setNull(23, Types.VARCHAR);
			callableStatement.setNull(24, Types.VARCHAR);
			
			//inizio LP PG190220
			//callableStatement.registerOutParameter(25, Types.INTEGER);
			callableStatement.setNull(25, Types.VARCHAR);//RT Annullata
			callableStatement.setNull(26, Types.VARCHAR);//RT Annullata Esito
			callableStatement.setNull(27, Types.VARCHAR);//ER Esito
			callableStatement.setNull(28, Types.VARCHAR);//Esito Inivio Email Revoca Notifica Admin
			callableStatement.setNull(29, Types.TIMESTAMP);//Data Esito Inivio Email Revoca Notifica Admin
			callableStatement.setNull(30, Types.VARCHAR);//Esito Inivio Email Revoca Notifica Contribuente
			callableStatement.setNull(31, Types.TIMESTAMP);//Data Esito Inivio Email Revoca Notifica Contribuente
//			YLM PG22XX07 INI
			callableStatement.setNull(32, Types.TIMESTAMP);//XML SEND RT
//			YLM PG22XX07 FINE
			callableStatement.registerOutParameter(33, Types.INTEGER);
			//fine LP PG190220

			callableStatement.execute();
			
			//inizio LP PG190220
			//int recCount = callableStatement.getInt(25);
			int recCount = callableStatement.getInt(33);
			//fine LP PG190220
			
			//fine LP PG180290
			
		} catch (SQLException x) {
			if(x.getErrorCode()== -803){
				throw new DaoException(55,"esiste già un psp con la chiave selezionata");
			}
			throw new DaoException(x);
		//inizio LP 20240811 - PGNTCORE-24
		} catch (UndeclaredThrowableException x) {
			DaoException.makeIfDuplicateKeyError(x, 55, "Esiste già un psp con la chiave selezionata");
		//fine LP 20240811 - PGNTCORE-24
		} catch (IllegalArgumentException x) {
			System.out.println("doSave failed generic error due to: " + x.getMessage());
			throw new DaoException(101, x.getMessage());
		} catch (HelperException x) {
			throw new DaoException(x);
		//inizio LP 20210325 - Per Bug caratteri 'sfuri' su RPT\RT
		} catch (UnsupportedEncodingException x) {
			throw new DaoException(x);
		//fine LP 20210325
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
		return code;
	}
	
	//Salvataggio RPT
	/***
	 * Salva l'xml RPT nella riga della tbrpttb già inserita
	 */
	public int inserisciRPT(int paymentId, byte[] RPT) throws DaoException 
	{
		int code = 0;
		CallableStatement callableStatement = null;
		try	{
			//Update RT
			System.out.println("paymentId id = " + paymentId);
			callableStatement = prepareCall(Routines.PYRPTSP_UPD.routine());
			callableStatement.setInt(1, paymentId);
			callableStatement.setNull(2, Types.VARCHAR);
			callableStatement.setNull(3, Types.CHAR);
			callableStatement.setNull(4, Types.CHAR);
			callableStatement.setNull(5, Types.VARCHAR);
			callableStatement.setNull(6, Types.CHAR);
			callableStatement.setNull(7, Types.VARCHAR);
			callableStatement.setNull(8, Types.DECIMAL);
			//inizio LP 20210325 - Per Bug caratteri 'sfuri' su RPT\RT
			//callableStatement.setString(9, new String(RPT));
			callableStatement.setString(9, new String(RPT, "UTF-8"));
			//fine LP 20210325
			callableStatement.setNull(10, Types.VARCHAR);
			callableStatement.setNull(11, Types.VARCHAR);
			callableStatement.setNull(12, Types.BINARY);
			callableStatement.setNull(13, Types.VARCHAR);
			callableStatement.setNull(14, Types.VARCHAR);
			callableStatement.setNull(15, Types.VARCHAR);
			callableStatement.setNull(16, Types.VARCHAR);
			callableStatement.setNull(17, Types.VARCHAR);	//29072016 GG PG160130 introdotto codContestoPagamento
			callableStatement.setNull(18, Types.VARCHAR);	//29072016 GG PG160130 introdotto idDominio
			callableStatement.setNull(19, Types.VARCHAR);
			callableStatement.setNull(20, Types.VARCHAR);
			callableStatement.setNull(21, Types.VARCHAR);
			//inizio LP PG180290
			/*
			callableStatement.registerOutParameter(22, Types.INTEGER);
			
			callableStatement.execute();
			
			int recCount = callableStatement.getInt(22);
			*/
			callableStatement.setNull(22, Types.VARCHAR);
			callableStatement.setNull(23, Types.VARCHAR);
			callableStatement.setNull(24, Types.VARCHAR);
			
			//inizio LP PG190220
			//callableStatement.registerOutParameter(25, Types.INTEGER);
			callableStatement.setNull(25, Types.VARCHAR);//RT Annullata
			callableStatement.setNull(26, Types.VARCHAR);//RT Annullata Esito
			callableStatement.setNull(27, Types.VARCHAR);//ER Esito
			callableStatement.setNull(28, Types.VARCHAR);//Esito Inivio Email Revoca Notifica Admin
			callableStatement.setNull(29, Types.TIMESTAMP);//Data Esito Inivio Email Revoca Notifica Admin
			callableStatement.setNull(30, Types.VARCHAR);//Esito Inivio Email Revoca Notifica Contribuente
			callableStatement.setNull(31, Types.TIMESTAMP);//Data Esito Inivio Email Revoca Notifica Contribuente
//			YLM PG22XX07 INI
			callableStatement.setNull(32, Types.TIMESTAMP);//XML SEND RT
//			YLM PG22XX07 FINE
			callableStatement.registerOutParameter(33, Types.INTEGER);
			//fine LP PG190220

			callableStatement.execute();
			
			//inizio LP PG190220
			//int recCount = callableStatement.getInt(25);
			int recCount = callableStatement.getInt(33);
			//fine LP PG190220
			
			//fine LP PG180290
			
		} catch (SQLException x) {
			if(x.getErrorCode()== -803){
				throw new DaoException(55,"esiste già un psp con la chiave selezionata");
			}
			throw new DaoException(x);
		//inizio LP 20240811 - PGNTCORE-24
		} catch (UndeclaredThrowableException x) {
			DaoException.makeIfDuplicateKeyError(x, 55, "Esiste già un psp con la chiave selezionata");
		//fine LP 20240811 - PGNTCORE-24
		} catch (IllegalArgumentException x) {
			System.out.println("doSave failed generic error due to: " + x.getMessage());
			throw new DaoException(101, x.getMessage());
		} catch (HelperException x) {
			throw new DaoException(x);
		//inizio LP 20210325 - Per Bug caratteri 'sfuri' su RPT\RT
		} catch (UnsupportedEncodingException x) {
			throw new DaoException(x);
		//fine LP 20210325
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
		return code;
	}
	
	//Salvataggio RR/ER (Richiesta Revoca/Esito Revoca)
	public int inserisciRR(int paymentId, byte[] RR, byte[] ER) throws DaoException 
	{
		int code = 0;
		CallableStatement callableStatement = null;
		try	{
			//Update RT
			callableStatement = prepareCall(Routines.PYRPTSP_UPD.routine());
			callableStatement.setInt(1, paymentId);
			callableStatement.setNull(2, Types.VARCHAR);
			callableStatement.setNull(3, Types.CHAR);
			callableStatement.setNull(4, Types.CHAR);
			callableStatement.setNull(5, Types.VARCHAR);
			callableStatement.setNull(6, Types.CHAR);
			callableStatement.setNull(7, Types.VARCHAR);
			callableStatement.setNull(8, Types.DECIMAL);
			callableStatement.setNull(9, Types.BINARY);
			callableStatement.setNull(10, Types.VARCHAR);
			callableStatement.setNull(11, Types.VARCHAR);
			callableStatement.setNull(12, Types.BINARY);
			callableStatement.setNull(13, Types.VARCHAR);
			callableStatement.setNull(14, Types.VARCHAR);
			
			if (RR != null){
				///byte[] RRDecoded = org.apache.commons.codec.binary.Base64.decodeBase64(RR);
				//inizio LP 20210325 - Per Bug caratteri 'sfuri' su RPT\RT
				//callableStatement.setString(15, new String(RR));
				callableStatement.setString(15, new String(RR, "UTF-8"));
				//fine LP 20210325
			}
			else{
				callableStatement.setNull(15, Types.VARCHAR);
			}
			if (ER != null){
				//byte[] ERDecoded = org.apache.commons.codec.binary.Base64.decodeBase64(ER);
				//inizio LP 20210325 - Per Bug caratteri 'sfuri' su RPT\RT
				//callableStatement.setString(16, new String(ER));
				callableStatement.setString(16, new String(ER, "UTF-8"));
				//fine LP 20210325
			}
			else{
				callableStatement.setNull(16, Types.VARCHAR);
			}
			callableStatement.setNull(17, Types.VARCHAR);	//29072016 GG PG160130 introdotto codContestoPagamento
			callableStatement.setNull(18, Types.VARCHAR);	//29072016 GG PG160130 introdotto idDominio
			callableStatement.setNull(19, Types.VARCHAR);
			callableStatement.setNull(20, Types.VARCHAR);
			callableStatement.setNull(21, Types.VARCHAR);
			//inizio LP PG180290
			/*
			callableStatement.registerOutParameter(22, Types.INTEGER);
			
			callableStatement.execute();
			
			int recCount = callableStatement.getInt(22);
			*/
			callableStatement.setNull(22, Types.VARCHAR);
			callableStatement.setNull(23, Types.VARCHAR);
			callableStatement.setNull(24, Types.VARCHAR);
			
			//inizio LP PG190220
			//callableStatement.registerOutParameter(25, Types.INTEGER);
			callableStatement.setNull(25, Types.VARCHAR);//RT Annullata
			callableStatement.setNull(26, Types.VARCHAR);//RT Annullata Esito
			callableStatement.setNull(27, Types.VARCHAR);//ER Esito
			callableStatement.setNull(28, Types.VARCHAR);//Esito Inivio Email Revoca Notifica Admin
			callableStatement.setNull(29, Types.TIMESTAMP);//Data Esito Inivio Email Revoca Notifica Admin
			callableStatement.setNull(30, Types.VARCHAR);//Esito Inivio Email Revoca Notifica Contribuente
			callableStatement.setNull(31, Types.TIMESTAMP);//Data Esito Inivio Email Revoca Notifica Contribuente
//			YLM PG22XX07 INI
			callableStatement.setNull(32, Types.TIMESTAMP);//XML SEND RT
//			YLM PG22XX07 FINE
			callableStatement.registerOutParameter(33, Types.INTEGER);
			//fine LP PG190220

			callableStatement.execute();
			
			//inizio LP PG190220
			//int recCount = callableStatement.getInt(25);
			int recCount = callableStatement.getInt(33);
			//fine LP PG190220
			
			//fine LP PG180290
			
		} catch (SQLException x) {
			if(x.getErrorCode()== -803){
				throw new DaoException(55,"esiste già un psp con la chiave selezionata");
			}
			throw new DaoException(x);
		//inizio LP 20240811 - PGNTCORE-24
		} catch (UndeclaredThrowableException x) {
			DaoException.makeIfDuplicateKeyError(x, 55, "Esiste già un psp con la chiave selezionata");
		//fine LP 20240811 - PGNTCORE-24
		} catch (IllegalArgumentException x) {
			System.out.println("doSave failed generic error due to: " + x.getMessage());
			throw new DaoException(101, x.getMessage());
		} catch (HelperException x) {
			throw new DaoException(x);
		//inizio LP 20210325 - Per Bug caratteri 'sfuri' su RPT\RT
		} catch (UnsupportedEncodingException x) {
			throw new DaoException(x);
		//fine LP 20210325
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
		return code;
	}
	
}
