package com.seda.payer.core.dao;

import java.math.BigDecimal;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Vector;


import com.seda.data.helper.HelperException;
import com.seda.data.spi.PageInfo;
import com.seda.payer.core.bean.CollectionTransazione;
import com.seda.payer.core.bean.ConfiguraPerUtenteEnteServizio;
import com.seda.payer.core.bean.EsistenzaPagamento;
import com.seda.payer.core.bean.ObjectKeyKey;
import com.seda.payer.core.bean.Transazione;
import com.seda.payer.core.bean.TransazioneAtm;
import com.seda.payer.core.bean.TransazioneDocumentoEC;
import com.seda.payer.core.bean.TransazioneFreccia;
import com.seda.payer.core.bean.TransazioneIV;
import com.seda.payer.core.bean.TransazioneIV_Dett;
import com.seda.payer.core.bean.TransazioneIci;
import com.seda.payer.core.exception.DaoException;
import com.seda.payer.core.handler.BaseDaoHandler;


public class TxTransazioniDao extends BaseDaoHandler{
	private static final String ESITO_SMS = "S";
	private static final String ESITO_EMAIL_BANCA_AMMINISTRATORE = "A";
	private static final String ESITO_EMAIL_BANCA_CONTRIBUENTE = "CA";
	private static final String ESITO_EMAIL_BOLLETTINI_CONTRIBUENTE = "CB";
	private static final String ESITO_EMAIL_SCARTI_CONTRIBUENTE = "SC";
	private static final String ESITO_EMAIL_SCARTI_AMMINISTRATORE = "SA";
	public static final String TRANSACTION_COMPLETED_STATE = "1";
	private static final String TRANSACTION_PENDING_STATE = "0";
	public static final String TRANSACTION_FAILED_STATE = "2";
	private PageInfo pageInfo = null;

	public TxTransazioniDao(Connection connection, String schema) {
		super(connection, schema);
	}
	
	//inizio LP PG21XX04 Leak
	//private void closeCallableStatement(CallableStatement callableStatement)
	//{
	//	if (callableStatement != null)
	//		DAOHelper.closeIgnoringException(callableStatement);
	//}
	//fine LP PG21XX04 Leak
	
	public PageInfo getPageInfo() {
		return pageInfo;
	}
	
	
	public CollectionTransazione recuperaTransazione(String codice_transazione) throws DaoException
	{
		CollectionTransazione retBean = new CollectionTransazione();
		
		retBean.setTransazione(getTransazione(codice_transazione));
		retBean.setListIci(getTransazioneIci(codice_transazione));
		retBean.setListIV(getTransazioneIV(codice_transazione));
		//inizio LP PG200060
		//NOTA: non viene usato il recuperaTransazione del DAO ma quello del facade.
		//      lascio il try/catch come promemoria.
		try {
		//fine LP PG200060
		//MGB-PR 16072013
		retBean.setListIV_Dett(getTransazioneIV_Dett(codice_transazione));
		//inizio LP PG200060
		} catch (Exception e) {
			//previene errore
			retBean.setListIV_Dett(null);
		}
		//fine LP PG200060
		retBean.setListFreccia(getTransazioneFreccia(codice_transazione));
		retBean.setListTransazioni(getTransazioniPending(0));	//PG170040 GG 27022017 - Introdotto parametro timeElapseMM
		
		return retBean;
	}

	public Transazione getTransazione(String codice_transazione) throws DaoException
	{
		Transazione transazioneBean = null;
		//inizio LP PG21XX04 Leak
		//CallableStatement callableStatement;
		CallableStatement callableStatement = null;
		ResultSet data = null;
		//fine LP PG21XX04 Leak
		try {
		    callableStatement = prepareCall(Routines.TX_LISTA.routine());
			callableStatement.setString(1, codice_transazione);
			
			if(callableStatement.execute())
			{
				//inizio LP PG21XX04 Leak
				//ResultSet data = callableStatement.getResultSet();
				data = callableStatement.getResultSet();
				//fine LP PG21XX04 Leak
				while (data.next())
					transazioneBean = Transazione.getBean_Extended(data);
			}

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
		return transazioneBean;
	}
	
	//inizio LP PG22XX10_LP2 - Bug Result Set close - 20221102
	private CallableStatement callableStatementGetTransazione = null;
	
	public Transazione getTransazioneBatch(String codice_transazione) throws DaoException
	{
		Transazione transazioneBean = null;
		ResultSet data = null;
		try {
			if(callableStatementGetTransazione == null)
				callableStatementGetTransazione = prepareCall(Routines.TX_LISTA.routine());
			CallableStatement callableStatement = callableStatementGetTransazione;	
			callableStatement.setString(1, codice_transazione);
			
			if(callableStatement.execute())
			{
				//inizio LP PG21XX04 Leak
				//ResultSet data = callableStatement.getResultSet();
				data = callableStatement.getResultSet();
				//fine LP PG21XX04 Leak
				while (data.next())
					transazioneBean = Transazione.getBean_Extended(data);
			}

		} catch (IllegalArgumentException e) {
			throw new DaoException(e);
		} catch (SQLException e) {
			throw new DaoException(e);
		} catch (HelperException e) {
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
		}
		return transazioneBean;
	}
	//fine LP PG22XX10_LP2 - Bug Result Set close - 20221102
	
	public Transazione getTransazione_Banca(String codiceIdBanca, String codiceAutBanca, String tipoGateway) throws DaoException
	{
		Transazione transazioneBean = null;
		//inizio LP PG21XX04 Leak
		//CallableStatement callableStatement;
		CallableStatement callableStatement = null;
		ResultSet data = null;
		//fine LP PG21XX04 Leak
		try {
		    callableStatement = prepareCall(Routines.TX_PYTRASP_SEL_BANCA.routine());
			callableStatement.setString(1, codiceIdBanca != null ? codiceIdBanca : "");
			callableStatement.setString(2, codiceAutBanca != null ? codiceAutBanca : "");
			callableStatement.setString(3, tipoGateway != null ? tipoGateway : "");
			
			if(callableStatement.execute())
			{
				//inizio LP PG21XX04 Leak
				//ResultSet data = callableStatement.getResultSet();
				data = callableStatement.getResultSet();
				//fine LP PG21XX04 Leak
				
				if (data.next())
					transazioneBean = Transazione.getBean(data);
			}

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
		return transazioneBean;
	}
	
	public List<TransazioneFreccia> getTransazioneFreccia(String codice_transazione) throws DaoException
	{
		List<TransazioneFreccia> transazioneFrecciaBean = null;
		//inizio LP PG21XX04 Leak
		//CallableStatement callableStatement;
		CallableStatement callableStatement = null;
		ResultSet data = null;
		//fine LP PG21XX04 Leak
		try {
		    callableStatement = prepareCall(Routines.TX_LISTA_FRECCIA.routine());
			callableStatement.setString(1, codice_transazione);
			
			if(callableStatement.execute())
			{
				//inizio LP PG21XX04 Leak
				//ResultSet data = callableStatement.getResultSet();
				data = callableStatement.getResultSet();
				//fine LP PG21XX04 Leak
				transazioneFrecciaBean = new Vector<TransazioneFreccia>();
				while (data.next())
					//PG150180_001 GG - inizio
					//transazioneFrecciaBean.add(TransazioneFreccia.getBean(data));
					transazioneFrecciaBean.add(TransazioneFreccia.getBean_ExtendedNodoSpc(data));
					//PG150180_001 GG - fine
				
			}

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
		return transazioneFrecciaBean;
	}
	
	public List<TransazioneIci> getTransazioneIci(String codice_transazione) throws DaoException
	{
		List<TransazioneIci> transazioneIciBean = null;
		//inizio LP PG21XX04 Leak
		//CallableStatement callableStatement;
		CallableStatement callableStatement = null;
		ResultSet data = null;
		//fine LP PG21XX04 Leak
		try {
		    callableStatement = prepareCall(Routines.TX_LISTA_ICI.routine());
			callableStatement.setString(1, codice_transazione);
			
			if(callableStatement.execute())
			{
				//inizio LP PG21XX04 Leak
				//ResultSet data = callableStatement.getResultSet();
				data = callableStatement.getResultSet();
				//fine LP PG21XX04 Leak
				transazioneIciBean = new Vector<TransazioneIci>();
				while (data.next())
					//PG150180_001 GG - inizio
					//transazioneIciBean.add(TransazioneIci.getBean(data));
					transazioneIciBean.add(TransazioneIci.getBean_ExtendedNodoSpc(data));
					//PG150180_001 GG - fine
			}

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
		return transazioneIciBean;
	}
	
	public List<TransazioneIV> getTransazioneIV(String codice_transazione) throws DaoException
	{
		List<TransazioneIV> transazioneIVBean= null;
		//inizio LP PG21XX04 Leak
		//CallableStatement callableStatement;
		CallableStatement callableStatement = null;
		ResultSet data = null;
		//fine LP PG21XX04 Leak
		try {
		    callableStatement = prepareCall(Routines.TX_LISTA_IV.routine());
			callableStatement.setString(1, codice_transazione);
			
			if(callableStatement.execute())
			{
				//inizio LP PG21XX04 Leak
				//ResultSet data = callableStatement.getResultSet();
				data = callableStatement.getResultSet();
				//fine LP PG21XX04 Leak
				transazioneIVBean = new Vector<TransazioneIV>();
				while (data.next())
					//PG150180_001 GG - inizio
					transazioneIVBean.add(TransazioneIV.getBean_ExtendedNodoSpc(data));
					//transazioneIVBean.add(TransazioneIV.getBean(data));
					//PG150180_001 GG - fine
			}
			

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
		return transazioneIVBean;
	}
	//MGB-PR 16072013
	public List<TransazioneIV_Dett> getTransazioneIV_Dett(String codice_transazione) throws DaoException
	{
		List<TransazioneIV_Dett> transazioneIV_DettBean= null;
		//inizio LP PG21XX04 Leak
		//CallableStatement callableStatement;
		CallableStatement callableStatement = null;
		ResultSet data = null;
		//fine LP PG21XX04 Leak
		try {
		    callableStatement = prepareCall(Routines.TX_LISTA_IV.routine());
			callableStatement.setString(1, codice_transazione);
			
			if(callableStatement.execute())
			{
				//inizio LP PG21XX04 Leak
				//ResultSet data = callableStatement.getResultSet();
				data = callableStatement.getResultSet();
				//fine LP PG21XX04 Leak
				transazioneIV_DettBean = new Vector<TransazioneIV_Dett>();
				while (data.next())
					transazioneIV_DettBean.add(TransazioneIV_Dett.getBean(data));
			}
			

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
		return transazioneIV_DettBean;
	}
	
	public List<TransazioneAtm> getTransazioneAtm(String codice_transazione) throws DaoException {
		List<TransazioneAtm> transazioneAtmBean = null;
		//inizio LP PG21XX04 Leak
		//CallableStatement callableStatement;
		CallableStatement callableStatement = null;
		ResultSet data = null;
		//fine LP PG21XX04 Leak
		try {
		    callableStatement = prepareCall(Routines.ATM_TX_DOLIST.routine());
			callableStatement.setString(1, codice_transazione);
			if (callableStatement.execute()) {
				//inizio LP PG21XX04 Leak
				//ResultSet data = callableStatement.getResultSet();
				data = callableStatement.getResultSet();
				//fine LP PG21XX04 Leak
				transazioneAtmBean = new ArrayList<TransazioneAtm>();
				while (data.next()) {
					transazioneAtmBean.add(TransazioneAtm.getBean(data));
				}
			}
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
		return transazioneAtmBean;
	}
	//20022013--Giulia--
	public EsistenzaPagamento verificaEsistenzaPagamento(String flgTemplate, String codiceSocieta, String codiceUtente, String chiaveEnte,
			String tipologiaServizio, String tipoBollettino, String numBollettino, String tipoControllo) throws DaoException
	{
		CallableStatement callableStatement = null;
		EsistenzaPagamento res = new EsistenzaPagamento();
		
		try {
			if (tipoControllo != null && tipoControllo.equals("PEOPLE"))
				callableStatement = prepareCall(Routines.TX_PYTRASP_SEL_ESITO_PEOPLE.routine());
			else
				callableStatement = prepareCall(Routines.TX_PYTRASP_SEL_ESITO.routine());
			callableStatement.setString(1, codiceSocieta);
			callableStatement.setString(2, codiceUtente);
			callableStatement.setString(3, chiaveEnte);
			callableStatement.setString(4, tipologiaServizio);
			callableStatement.setString(5, tipoBollettino);
			callableStatement.setString(6, numBollettino);
			//Aggiunto campo input 
			callableStatement.setString(7, flgTemplate);
			/* we register row start */
			callableStatement.registerOutParameter(8, Types.INTEGER);
			callableStatement.registerOutParameter(9, Types.VARCHAR);
			callableStatement.registerOutParameter(10, Types.TIMESTAMP);
			callableStatement.registerOutParameter(11, Types.DECIMAL);
			
			callableStatement.execute();
			res.setStatoTransazione(String.valueOf(callableStatement.getInt(8)));			
			res.setIdTransazione(callableStatement.getString(9));			
			
			// calendario
			Calendar cal = Calendar.getInstance(Locale.ITALIAN);
			cal.setTime(callableStatement.getTimestamp(10));
			res.setDataPagamento(cal);
			res.setImportoBollettino(callableStatement.getBigDecimal(11));			
			
		} catch (Exception e) {
			throw new DaoException(e);
		} finally {
			//inizio LP PG21XX04 Leak
			//closeCallableStatement(callableStatement);
			if (callableStatement != null) {
				try {
					callableStatement.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			//fine LP PG21XX04 Leak
		}
		
		return res;
	}
	
	
	//100613013--Giulia--
	public EsistenzaPagamento verificaEsistenzaPagamentoIci(String codiceSocieta, String cutecute, String chiaveEnte, String codTipoServ, String tipoBoll, String flagRata,String codFisc,String comuneUbic, String annoImposta) throws DaoException
		 
	{
		CallableStatement callableStatement = null;
		EsistenzaPagamento res = new EsistenzaPagamento();
		
		try {
			
			callableStatement = prepareCall(Routines.TX_PYTRASP_SEL_ESITO_ICI.routine());
			callableStatement.setString(1, codiceSocieta);
			callableStatement.setString(2, cutecute);
			callableStatement.setString(3, chiaveEnte);
			callableStatement.setString(4, codTipoServ);
			callableStatement.setString(5, tipoBoll);
			callableStatement.setString(6, flagRata);
			//callableStatement.setBigDecimal(7, transazioneIci.getImportoMovimento());
			callableStatement.setString(7, codFisc);
			callableStatement.setString(8, comuneUbic);
			callableStatement.setString(9, annoImposta);
			
			
		
			
			/* we register row start */
			callableStatement.registerOutParameter(10, Types.INTEGER);
			callableStatement.registerOutParameter(11, Types.VARCHAR);
			callableStatement.registerOutParameter(12, Types.TIMESTAMP);
			callableStatement.registerOutParameter(13, Types.DECIMAL);
			
			callableStatement.execute();
			res.setStatoTransazione(String.valueOf(callableStatement.getInt(10)));			
			res.setIdTransazione(callableStatement.getString(11));			
			
			// calendario
			Calendar cal = Calendar.getInstance(Locale.ITALIAN);
			cal.setTime(callableStatement.getTimestamp(12));
			res.setDataPagamento(cal);
			res.setImportoBollettino(callableStatement.getBigDecimal(13));			
			
		} catch (Exception e) {
			throw new DaoException(e);
		} finally {
			//inizio LP PG21XX04 Leak
			//closeCallableStatement(callableStatement);
			if (callableStatement != null) {
				try {
					callableStatement.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			//fine LP PG21XX04 Leak
		}
		
		return res;
	}
	
	//090813--Giulia--Aggiunto Controllo per il Doppio Numero Documento non ancora rendicontato 
	public EsistenzaPagamento verificaEsistenzaDocumento(String codiceSocieta, String cutecute, String chiaveEnte, String codTipoServ, String tipoBoll, String numDocumento) throws DaoException
		 
	{
		CallableStatement callableStatement = null;
		EsistenzaPagamento res = new EsistenzaPagamento();
		
		try {
			
			callableStatement = prepareCall(Routines.TX_PYTRASP_SEL_ESITO_REND.routine());
			callableStatement.setString(1, codiceSocieta);
			callableStatement.setString(2, cutecute);
			callableStatement.setString(3, chiaveEnte);
			callableStatement.setString(4, codTipoServ);
			callableStatement.setString(5, tipoBoll);
			callableStatement.setString(6, numDocumento);
			/* we register row start */
			callableStatement.registerOutParameter(7, Types.INTEGER);
			callableStatement.registerOutParameter(8, Types.VARCHAR);
			callableStatement.registerOutParameter(9, Types.TIMESTAMP);
	
			
			callableStatement.execute();
			res.setStatoTransazione(String.valueOf(callableStatement.getInt(7)));			
			res.setIdTransazione(callableStatement.getString(8));			
			
			// calendario
			Calendar cal = Calendar.getInstance(Locale.ITALIAN);
			cal.setTime(callableStatement.getTimestamp(9));
			res.setDataPagamento(cal);
				
			
		} catch (Exception e) {
			throw new DaoException(e);
		} finally {
			//inizio LP PG21XX04 Leak
			//closeCallableStatement(callableStatement);
			if (callableStatement != null) {
				try {
					callableStatement.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			//fine LP PG21XX04 Leak
		}
		
		return res;
	}
	
	
	//PG130130 GG 20130620 - inizio
	public EsistenzaPagamento verificaEsistenzaTransazioneATM(String codiceSocieta, String codiceUtente, String chiaveEnte,
			String idTerminaleATM, String idTransazioneATM, String canalePagamento) throws DaoException
	{
		CallableStatement callableStatement = null;
		EsistenzaPagamento res = new EsistenzaPagamento();
		
		try {
			callableStatement = prepareCall(Routines.TX_PYTRASP_SEL_BY_CAN_TRAATM.routine());
			callableStatement.setString(1, codiceSocieta);
			callableStatement.setString(2, codiceUtente);
			callableStatement.setString(3, chiaveEnte);
			callableStatement.setString(4, idTerminaleATM);
			callableStatement.setString(5, idTransazioneATM);
			callableStatement.setString(6, canalePagamento);
			callableStatement.registerOutParameter(7, Types.INTEGER);
			callableStatement.registerOutParameter(8, Types.VARCHAR);
						
			callableStatement.execute();
			res.setStatoTransazione(String.valueOf(callableStatement.getInt(7)));			
			res.setIdTransazione(callableStatement.getString(8));						
		} catch (Exception e) {
			throw new DaoException(e);
		} finally {
			//inizio LP PG21XX04 Leak
			//closeCallableStatement(callableStatement);
			if (callableStatement != null) {
				try {
					callableStatement.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			//fine LP PG21XX04 Leak
		}
		return res;
	}
	
	public EsistenzaPagamento verificaEsistenzaPagamentoATM(String codiceSocieta, String codiceUtente, String chiaveEnte,
			String idTerminaleATM, String idTransazioneATM, BigDecimal importoTotale, String numBollettino, String canalePagamento) throws DaoException
	{
		CallableStatement callableStatement = null;
		EsistenzaPagamento res = new EsistenzaPagamento();
		
		try {
			callableStatement = prepareCall(Routines.TX_PYTRASP_SEL_CHECK_TRAATM.routine());
			callableStatement.setString(1, codiceSocieta);
			callableStatement.setString(2, codiceUtente);
			callableStatement.setString(3, chiaveEnte);
			callableStatement.setString(4, idTerminaleATM);
			callableStatement.setString(5, idTransazioneATM);
			callableStatement.setBigDecimal(6, importoTotale);
			callableStatement.setString(7, numBollettino);
			callableStatement.setString(8, canalePagamento);
			callableStatement.registerOutParameter(9, Types.INTEGER);
			callableStatement.registerOutParameter(10, Types.VARCHAR);
						
			callableStatement.execute();
			res.setStatoTransazione(String.valueOf(callableStatement.getInt(9)));			
			res.setIdTransazione(callableStatement.getString(10));						
		} catch (Exception e) {
			throw new DaoException(e);
		} finally {
			//inizio LP PG21XX04 Leak
			//closeCallableStatement(callableStatement);
			if (callableStatement != null) {
				try {
					callableStatement.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			//fine LP PG21XX04 Leak
		}
		return res;
	}
	//PG130130 GG 20130620 - fine
	
	/**
	 * @param codice_transazione
	 * @param tipo_notifica
	 * @return
	 * @throws DaoException
	 */
	public boolean updateEsitoNotifica(String codice_transazione, String tipo_notifica) throws DaoException {
		boolean bResultVal = false;
		CallableStatement callableStatement = null;
		try {
			callableStatement = prepareCall(Routines.TX_UPDATE_TRA.routine());
			callableStatement.setString(1, codice_transazione);
			callableStatement.setNull(2, Types.CHAR);
			callableStatement.setNull(3, Types.CHAR);
			callableStatement.setNull(4, Types.TIMESTAMP);
			callableStatement.setNull(5, Types.TIMESTAMP);
			callableStatement.setNull(6, Types.CHAR);
			callableStatement.setNull(7, Types.VARCHAR);
			callableStatement.setNull(8, Types.VARCHAR);
			callableStatement.setNull(9, Types.VARCHAR);
			callableStatement.setNull(10, Types.VARCHAR);
			callableStatement.setNull(11, Types.VARCHAR);
			callableStatement.setNull(12, Types.VARCHAR);
			callableStatement.setNull(13, Types.VARCHAR);
			callableStatement.setNull(14, Types.CHAR);
			callableStatement.setNull(15, Types.VARCHAR);
			callableStatement.setNull(16, Types.CHAR);
			if (tipo_notifica.equals(ESITO_SMS))
			{
				callableStatement.setNull(17, Types.CHAR);
				callableStatement.setNull(18, Types.CHAR);
				callableStatement.setNull(19, Types.CHAR);
				callableStatement.setString(20, "Y");
			}
			else if(tipo_notifica.equals(ESITO_EMAIL_BANCA_CONTRIBUENTE))
			{
				callableStatement.setNull(17, Types.CHAR);
				callableStatement.setString(18, "Y");
				callableStatement.setNull(19, Types.CHAR);
				callableStatement.setNull(20, Types.CHAR);
			}
			else if(tipo_notifica.equals(ESITO_EMAIL_BANCA_AMMINISTRATORE))
			{
				callableStatement.setNull(17, Types.CHAR);
				callableStatement.setNull(18, Types.CHAR);
				callableStatement.setString(19, "Y");
				callableStatement.setNull(20, Types.CHAR);				
			}
			else if(tipo_notifica.equals(ESITO_EMAIL_BOLLETTINI_CONTRIBUENTE))
			{
				callableStatement.setString(17, "Y");
				callableStatement.setNull(18, Types.CHAR);
				callableStatement.setNull(19, Types.CHAR);
				callableStatement.setNull(20, Types.CHAR);				
			}
			else 
			{
				callableStatement.setNull(17, Types.CHAR);
				callableStatement.setNull(18, Types.CHAR);
				callableStatement.setNull(19, Types.CHAR);
				callableStatement.setNull(20, Types.CHAR);
			}
			callableStatement.setNull(21, Types.CHAR);
			callableStatement.setNull(22, Types.TIMESTAMP);
			callableStatement.setNull(23, Types.VARCHAR);
			callableStatement.setNull(24, Types.DECIMAL);		
			callableStatement.setNull(25, Types.DECIMAL);		
			callableStatement.setNull(26, Types.DECIMAL);		
			callableStatement.setNull(27, Types.DECIMAL);		
			callableStatement.setNull(28, Types.VARCHAR);	
			callableStatement.setNull(29, Types.CHAR);
			callableStatement.setNull(30, Types.VARCHAR);
			callableStatement.setNull(31, Types.BIGINT);
			callableStatement.setNull(32, Types.CHAR);
			if (tipo_notifica.equals(ESITO_EMAIL_SCARTI_CONTRIBUENTE)) {
				callableStatement.setString(33, "Y");
				callableStatement.setNull(34, Types.CHAR);
			} else if (tipo_notifica.equals(ESITO_EMAIL_SCARTI_AMMINISTRATORE)) {
				callableStatement.setNull(33, Types.CHAR);
				callableStatement.setString(34, "Y");
			} else {
				callableStatement.setNull(33, Types.CHAR);
				callableStatement.setNull(34, Types.CHAR);
			}
			callableStatement.setNull(35, Types.VARCHAR);
            
			callableStatement.setNull(36, Types.CHAR);
			callableStatement.setNull(37, Types.SMALLINT);
			callableStatement.setNull(38, Types.VARCHAR);
			callableStatement.setNull(39, Types.VARCHAR);
			callableStatement.setNull(40, Types.VARCHAR);
			callableStatement.setNull(41, Types.CHAR);
			
			callableStatement.setNull(42, Types.TIMESTAMP);
			callableStatement.registerOutParameter(43, Types.INTEGER);
			
			callableStatement.executeUpdate();
			int numrighe =  callableStatement.getInt(43);
			if (numrighe == 1)
				bResultVal = true;
			else bResultVal = false;

		} catch (IllegalArgumentException e) {
			throw new DaoException(e);
		} catch (SQLException e) {
			throw new DaoException(e);
		} catch (HelperException e) {
			throw new DaoException(e);
		} finally {
			//inizio LP PG21XX04 Leak
			//try { DAOHelper.closeIgnoringException(callableStatement);
			//} catch (Exception ignore) { }
			if (callableStatement != null) {
				try {
					callableStatement.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			//fine LP PG21XX04 Leak
		}
		return bResultVal;
	}

	public boolean aggiornaTransazioniIG(Timestamp dataCreazioneFlusso, String canalePagamento) throws DaoException {
		boolean bResultVal = false;
		CallableStatement callableStatement = null;
		try {
			callableStatement = prepareCall(Routines.IG_AGGIORNA_TRANSAZIONI_PENDING.routine()); {
				callableStatement.setTimestamp(1, dataCreazioneFlusso);
				callableStatement.setString(2, canalePagamento.toUpperCase());
				callableStatement.registerOutParameter(3, Types.INTEGER);
				callableStatement.executeUpdate();
			}
			int numrighe = callableStatement.getInt(3);
			if (numrighe > 0) bResultVal = true; 
			else bResultVal = false;

		} catch (IllegalArgumentException e) {
			throw new DaoException(e);
		} catch (SQLException e) {
			throw new DaoException(e);
		} catch (HelperException e) {
			throw new DaoException(e);
		} finally {
			//inizio LP PG21XX04 Leak
			//try { DAOHelper.closeIgnoringException(callableStatement);
			//} catch (Exception ignore) { }
			if (callableStatement != null) {
				try {
					callableStatement.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			//fine LP PG21XX04 Leak
		}
		return bResultVal;
	}
	
	public Collection<ConfiguraPerUtenteEnteServizio> getInfoNotifica(String codice_transazione) throws DaoException
	{
		Collection<ConfiguraPerUtenteEnteServizio> retBean = new Vector<ConfiguraPerUtenteEnteServizio>();
		int count = 0;
		//inizio LP PG21XX04 Leak
		//CallableStatement callableStatement;
		CallableStatement callableStatement = null;
		ResultSet data = null;
		//fine LP PG21XX04 Leak
		try 
		{
			List<TransazioneFreccia> listFreccia = getTransazioneFreccia(codice_transazione);
			List<TransazioneIci> listIci = getTransazioneIci(codice_transazione);
			List<TransazioneIV> listIV = getTransazioneIV(codice_transazione);
			
			if (listIV.size() >0)
			{
				for(TransazioneIV transazioneIV: listIV)
				{
					count = 0;
					callableStatement = prepareCall(Routines.TX_PYCFESP_SEL.routine());
					callableStatement.setString(1, transazioneIV.getCodiceSocieta());
					callableStatement.setString(2, transazioneIV.getCodiceUtente());
					callableStatement.setString(3, transazioneIV.getChiaveEnteEnt());
					callableStatement.setString(4, transazioneIV.getCodiceTipologiaServizio());
					if(callableStatement.execute())
					{
						//inizio LP PG21XX04 Leak
						//ResultSet data = callableStatement.getResultSet();
						data = callableStatement.getResultSet();
						//fine LP PG21XX04 Leak
						while (data.next())
						{
							retBean.add(ConfiguraPerUtenteEnteServizio.getBean(data,0));
							count++;
						}
					}
					//inizio LP PG21XX04 Leak
					if (data != null) {
						try {
							data.close();
							data = null;
						} catch (SQLException e) {
							e.printStackTrace();
						}
					}
					if (callableStatement != null) {
						try {
							callableStatement.close();
							callableStatement = null;
						} catch (SQLException e) {
							e.printStackTrace();
						}
					}
					//fine LP PG21XX04 Leak
					/*
					 * Se (count==0) non ci sono dati di configurazione nella tabella PYCFETB
					 * e allora li cerco in PYCFSTB
					 */
					if (count == 0)
					{
						callableStatement = prepareCall(Routines.TX_PYCFSSP_SEL.routine());
						callableStatement.setString(1, transazioneIV.getCodiceSocieta());
						callableStatement.setString(2, transazioneIV.getCodiceUtente());
						callableStatement.setString(3, transazioneIV.getCodiceTipologiaServizio());
						if(callableStatement.execute())
						{
							//inizio LP PG21XX04 Leak
							//ResultSet data = callableStatement.getResultSet();
							data = callableStatement.getResultSet();
							//fine LP PG21XX04 Leak
							while (data.next())
								retBean.add(ConfiguraPerUtenteEnteServizio.getBean(data,1));
						}
						//inizio LP PG21XX04 Leak
						if (data != null) {
							try {
								data.close();
								data = null;
							} catch (SQLException e) {
								e.printStackTrace();
							}
						}
						if (callableStatement != null) {
							try {
								callableStatement.close();
								callableStatement = null;
							} catch (SQLException e) {
								e.printStackTrace();
							}
						}
						//fine LP PG21XX04 Leak
					}
				}
			}
			if (listIci.size() >0)
			{
				for(TransazioneIci transazioneIci: listIci)
				{
					count = 0;
					callableStatement = prepareCall(Routines.TX_PYCFESP_SEL.routine());
					callableStatement.setString(1, transazioneIci.getCodiceSocieta());
					callableStatement.setString(2, transazioneIci.getCodiceUtente());
					callableStatement.setString(3, transazioneIci.getChiaveEnte());
					callableStatement.setString(4, transazioneIci.getCodiceTipologiaServizio());
					if(callableStatement.execute())
					{
						//inizio LP PG21XX04 Leak
						//ResultSet data = callableStatement.getResultSet();
						data = callableStatement.getResultSet();
						//fine LP PG21XX04 Leak
						while (data.next())
						{
							retBean.add(ConfiguraPerUtenteEnteServizio.getBean(data,0));
							count++;
						}
					}
					//inizio LP PG21XX04 Leak
					if (data != null) {
						try {
							data.close();
							data = null;
						} catch (SQLException e) {
							e.printStackTrace();
						}
					}
					if (callableStatement != null) {
						try {
							callableStatement.close();
							callableStatement = null;
						} catch (SQLException e) {
							e.printStackTrace();
						}
					}
					//fine LP PG21XX04 Leak
					/*
					 * Se (count==0) non ci sono dati di configurazione nella tabella PYCFETB
					 * e allora li cerco in PYCFSTB
					 */
					if (count == 0)
					{
						callableStatement = prepareCall(Routines.TX_PYCFSSP_SEL.routine());
						callableStatement.setString(1, transazioneIci.getCodiceSocieta());
						callableStatement.setString(2, transazioneIci.getCodiceUtente());
						callableStatement.setString(3, transazioneIci.getCodiceTipologiaServizio());
						if(callableStatement.execute())
						{
							//inizio LP PG21XX04 Leak
							//ResultSet data = callableStatement.getResultSet();
							data = callableStatement.getResultSet();
							//fine LP PG21XX04 Leak
							while (data.next())
								retBean.add(ConfiguraPerUtenteEnteServizio.getBean(data,1));
						}
						//inizio LP PG21XX04 Leak
						if (data != null) {
							try {
								data.close();
								data = null;
							} catch (SQLException e) {
								e.printStackTrace();
							}
						}
						if (callableStatement != null) {
							try {
								callableStatement.close();
								callableStatement = null;
							} catch (SQLException e) {
								e.printStackTrace();
							}
						}
						//fine LP PG21XX04 Leak
					}
				}
			}
			
			if (listFreccia.size() >0)
			{
				for(TransazioneFreccia transazioneFreccia: listFreccia)
				{
					count = 0;
					callableStatement = prepareCall(Routines.TX_PYCFESP_SEL.routine());
					callableStatement.setString(1, transazioneFreccia.getCodiceSocieta());
					callableStatement.setString(2, transazioneFreccia.getCodiceUtente());
					callableStatement.setString(3, transazioneFreccia.getChiaveEnteEnt());
					callableStatement.setString(4, transazioneFreccia.getCodiceTipologiaServizio());
					if(callableStatement.execute())
					{
						//inizio LP PG21XX04 Leak
						//ResultSet data = callableStatement.getResultSet();
						data = callableStatement.getResultSet();
						//fine LP PG21XX04 Leak
						while (data.next())
						{
							retBean.add(ConfiguraPerUtenteEnteServizio.getBean(data,0));
							count++;
						}
					}
					//inizio LP PG21XX04 Leak
					if (data != null) {
						try {
							data.close();
							data = null;
						} catch (SQLException e) {
							e.printStackTrace();
						}
					}
					if (callableStatement != null) {
						try {
							callableStatement.close();
							callableStatement = null;
						} catch (SQLException e) {
							e.printStackTrace();
						}
					}
					//fine LP PG21XX04 Leak
					/*
					 * Se (count==0) non ci sono dati di configurazione nella tabella PYCFETB
					 * e allora li cerco in PYCFSTB
					 */
					if (count == 0)
					{
						callableStatement = prepareCall(Routines.TX_PYCFSSP_SEL.routine());
						callableStatement.setString(1, transazioneFreccia.getCodiceSocieta());
						callableStatement.setString(2, transazioneFreccia.getCodiceUtente());
						callableStatement.setString(3, transazioneFreccia.getCodiceTipologiaServizio());
						if(callableStatement.execute())
						{
							//inizio LP PG21XX04 Leak
							//ResultSet data = callableStatement.getResultSet();
							data = callableStatement.getResultSet();
							//fine LP PG21XX04 Leak
							while (data.next())
								retBean.add(ConfiguraPerUtenteEnteServizio.getBean(data,1));
						}
						//inizio LP PG21XX04 Leak
						if (data != null) {
							try {
								data.close();
								data = null;
							} catch (SQLException e) {
								e.printStackTrace();
							}
						}
						if (callableStatement != null) {
							try {
								callableStatement.close();
								callableStatement = null;
							} catch (SQLException e) {
								e.printStackTrace();
							}
						}
						//fine LP PG21XX04 Leak
					}
				}
			}
				
			// Normalize list aorund email address
			
			if (retBean.size() > 0)
			{
				retBean = NormalizeEmail(retBean);
			}
			else
			{
				retBean = null;
			}
			

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
		return retBean;
	}
	
	private Collection<ConfiguraPerUtenteEnteServizio> NormalizeEmail(Collection<ConfiguraPerUtenteEnteServizio> inputList) throws DaoException
	{
		HashMap <String,ConfiguraPerUtenteEnteServizio > hashList = new HashMap <String,ConfiguraPerUtenteEnteServizio >(1000) ;
		
		for(ConfiguraPerUtenteEnteServizio elem: inputList)
		{
			String [] strList = elem.emailAmministratori.split(";");
			
			for(String str : strList)
			{
				if (!hashList.containsKey(str))
					hashList.put(str, elem);
			}
		}
		
		if(hashList.size() > 0)
			return hashList.values();
		
		return null;
	}
	/**
	 * Se note null non viene salvato
	 * @param chiaveTransazione
	 * @param esitoTransazione
	 * @param codAutBanca
	 * @param codIdBanca
	 * @param dataPagamento
	 * @param note
	 * @return
	 * @throws DaoException
	 */
	public boolean updateEsitoTX(String chiaveTransazione, String esitoTransazione, String codAutBanca, String codIdBanca, 
			Date dataPagamento, Date dataAccredito, String note) 
						  throws DaoException {
		//inizio LP PG21XX04 Leak
		//CallableStatement callableStatement;
		CallableStatement callableStatement = null;
		//fine LP PG21XX04 Leak
		try {
			callableStatement = prepareCall(Routines.TX_UPDATE_TRA.routine());
			callableStatement.setString(1, chiaveTransazione);
			callableStatement.setNull(2, Types.CHAR);
			callableStatement.setNull(3, Types.CHAR);
			callableStatement.setNull(4, Types.TIMESTAMP);
			callableStatement.setString(6, esitoTransazione);
			if (esitoTransazione.equals(TRANSACTION_COMPLETED_STATE))
			{
				//se mi è stata passata una data la inserisco, altrimenti uso la data attuale
				if (dataPagamento != null)
					callableStatement.setTimestamp(5, new Timestamp(dataPagamento.getTime()));
				else
					callableStatement.setTimestamp(5, new Timestamp(new Date().getTime()));
				
				if (dataAccredito != null)
					callableStatement.setTimestamp(42, new Timestamp(dataAccredito.getTime()));
				else 
					callableStatement.setTimestamp(42, new Timestamp(new Date().getTime()));
								
				callableStatement.setString(7, codIdBanca);
				if(codAutBanca.length()>20)
					callableStatement.setString(8, codAutBanca.substring(0,20));
				else
					callableStatement.setString(8, codAutBanca);
			
				callableStatement.setString(30, note);
			} 
			else 
			{
				callableStatement.setNull(5, Types.TIMESTAMP);
				callableStatement.setNull(7, Types.VARCHAR);
				callableStatement.setNull(8, Types.VARCHAR);				
				callableStatement.setNull(42, Types.TIMESTAMP);
				callableStatement.setNull(30, Types.VARCHAR);
			}
			
			callableStatement.setNull(9, Types.VARCHAR);
			callableStatement.setNull(10, Types.VARCHAR);
			callableStatement.setNull(11, Types.VARCHAR);
			callableStatement.setNull(12, Types.VARCHAR);
			callableStatement.setNull(13, Types.VARCHAR);
			callableStatement.setNull(14, Types.CHAR);
			callableStatement.setNull(15, Types.VARCHAR);
			callableStatement.setNull(16, Types.CHAR);
			callableStatement.setNull(17, Types.CHAR);
			callableStatement.setNull(18, Types.CHAR);
			callableStatement.setNull(19, Types.CHAR);
			callableStatement.setNull(20, Types.CHAR);
			callableStatement.setNull(21, Types.CHAR);
			callableStatement.setNull(22, Types.TIMESTAMP);
			callableStatement.setNull(23, Types.VARCHAR);
			callableStatement.setNull(24, Types.DECIMAL);
			callableStatement.setNull(25, Types.DECIMAL);
			callableStatement.setNull(26, Types.DECIMAL);
			callableStatement.setNull(27, Types.DECIMAL);
			callableStatement.setNull(28, Types.VARCHAR);
			callableStatement.setNull(29, Types.CHAR);
			// note
			callableStatement.setNull(31, Types.BIGINT);
			callableStatement.setNull(32, Types.CHAR);
			callableStatement.setNull(33, Types.CHAR);
			callableStatement.setNull(34, Types.CHAR);
			callableStatement.setNull(35, Types.VARCHAR);
			callableStatement.setNull(36, Types.CHAR);
			callableStatement.setNull(37, Types.SMALLINT);
			callableStatement.setNull(38, Types.VARCHAR);
			callableStatement.setNull(39, Types.VARCHAR);
			callableStatement.setNull(40, Types.VARCHAR);
			callableStatement.setNull(41, Types.CHAR);
			
			
			callableStatement.registerOutParameter(43, Types.INTEGER);
			callableStatement.executeUpdate();
			int numrighe =  callableStatement.getInt(43);
			if (numrighe == 1) 
				return true;
			else 
				return false;

		} catch (IllegalArgumentException e) {
			throw new DaoException(e);
		} catch (SQLException e) {
			throw new DaoException(e);
		} catch (HelperException e) {
			throw new DaoException(e);
		}
		//inizio LP PG21XX04 Leak
		finally {
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
	
	
	/**
	 * 
	 * @param idAttestante, descAttestante
	 * @return boolean
	 * @throws DaoException
	 */
	public boolean insertPSN(String idAttestante, String descAttestante) throws DaoException{
		CallableStatement callableStatement = null;
		ResultSet data = null;
		
		try{
			callableStatement = prepareCall("PYPSNSP_INS"); //cambiare
			callableStatement.setString(1, idAttestante);
			callableStatement.setString(2, descAttestante);
			
			callableStatement.registerOutParameter(3, Types.INTEGER);
			callableStatement.executeUpdate();
			int numrighe =  callableStatement.getInt(3);
			
			if(numrighe == 1)
				return true;
			else
				return false;
		}catch(Exception e){
			throw new DaoException(e);
		}
		finally{
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
	 * 
	 * @param idAttestante
	 * @return 
	 * @throws DaoException
	 */
	public boolean updatePSN(String idAttestante, String descAttestante) throws DaoException{
		CallableStatement callableStatement = null;
		ResultSet data = null;
		
		try{
			callableStatement = prepareCall("PYPSNSP_UPD"); //cambiare
			callableStatement.setString(1, idAttestante);
			callableStatement.setString(2, descAttestante);
			
			callableStatement.registerOutParameter(3, Types.INTEGER);
			callableStatement.executeUpdate();
			int numrighe =  callableStatement.getInt(3);
			
			if(numrighe == 1)
				return true;
			else
				return false;
		}catch(Exception e){
			throw new DaoException(e);
		}
		finally{
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
	 * 
	 * @param idAttestante, descAttestante
	 * @return boolean
	 * @throws DaoException
	 */
	public String selectPSN(String idAttestante) throws DaoException{
		CallableStatement callableStatement = null;
		ResultSet data = null;
		
		try{
			callableStatement = prepareCall("PYPSNSP_SEL"); //cambiare
			callableStatement.setString(1, idAttestante);
			if(callableStatement.execute()) {
				data = callableStatement.getResultSet();
				if(data.next()){
					return data.getString("PSN_CPSNIDPS");
				}
				return null;
			}
			return null;
		}catch(Exception e){
			throw new DaoException(e);
		}
		finally{
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
	 * 
	 * @param trans
	 * @return
	 * @throws DaoException
	 */
	public boolean aggiornaTransazione(Transazione trans)  throws DaoException {
		//inizio LP PG21XX04 Leak
		//CallableStatement callableStatement;
		CallableStatement callableStatement = null;
		//fine LP PG21XX04 Leak
		try {
			callableStatement = prepareCall(Routines.TX_UPDATE_TRA.routine());
			callableStatement.setString(1, trans.getChiaveTransazione());
			callableStatement.setString(2, trans.getCodiceSocieta());
			callableStatement.setString(3, trans.getChiaveGatewayDiPagamento());
			callableStatement.setTimestamp(4, trans.getDataInizioTransazione()==null ? null : new Timestamp(trans.getDataInizioTransazione().getTime()));
			callableStatement.setTimestamp(5, trans.getDataEffettivoPagamentoSuGateway()==null ? null : new Timestamp(trans.getDataEffettivoPagamentoSuGateway().getTime()));
			//Giulia 100713---Controllo Aggiorna transazione Esito Per Lot/Sis/Tot--Controllare
//			if (trans.getFlagEsitoTransazione().equals(TRANSACTION_PENDING_STATE)){
//				if (trans.getCanalePagamento().equalsIgnoreCase("TOT")||trans.getCanalePagamento().equalsIgnoreCase("LOT")||trans.getCanalePagamento().equalsIgnoreCase("SIS")){
//					if(trans.getDataEffettivoPagamentoSuGateway().before(trans.getDataAllineamentoBatchTransazione()) ){
//						callableStatement.setString(6, "2");	
//					}
//				}
//			}
//					else{
			callableStatement.setString(6, trans.getFlagEsitoTransazione());
//					}
			callableStatement.setString(7, trans.getCodiceIdentificativoBanca());
			callableStatement.setString(8, trans.getCodiceAutorizzazioneBanca());
			callableStatement.setString(9, trans.getIndirizzoIpTerminalePagamento());
			callableStatement.setString(10, trans.getEmailContribuente());
			callableStatement.setString(11, trans.getNumeroSmsContribuente());
			callableStatement.setString(12, trans.getDenominazioneContribuentePerInvioPostaOrdinaria());
			callableStatement.setString(13, trans.getIndirizzoContribuentePerInvioPostaOrdinaria());
			callableStatement.setString(14, trans.getCapContribuentePerInvioPostaOrdinaria());
			callableStatement.setString(15, trans.getCittaContribuentePerInvioPostaOrdinaria());
			callableStatement.setString(16, trans.getProvinciaContribuentePerInvioPostaOrdinaria());
			callableStatement.setString(17, trans.getInvioNotificaBollettiniPerEmail());
			callableStatement.setString(18, trans.getInvioAutorizzazioneBancaPerEmailContribuente());
			callableStatement.setString(19, trans.getInvioAutorizzazioneBancaPerEmailAmministratore());
			callableStatement.setString(20, trans.getInvioNotificaAutorizzazioneBancaPerSms());
			callableStatement.setString(21, trans.getInvioNotificaPerPostaOrdinaria());
			callableStatement.setTimestamp(22, trans.getDataAllineamentoBatchTransazione()==null ? null : new Timestamp(trans.getDataAllineamentoBatchTransazione().getTime()));
			callableStatement.setString(23, trans.getCodiceOrdineGateway());
			callableStatement.setBigDecimal(24, trans.getImportoTotaleTransazione());
			callableStatement.setBigDecimal(25, trans.getImportoCostoTransazione());
			callableStatement.setBigDecimal(26, trans.getImportoCostoGateway());
			callableStatement.setBigDecimal(27, trans.getImportoCostoSpeseDiNotifica());
			callableStatement.setString(28, trans.getChiaveTransazioneSistemaEsterno());
			callableStatement.setString(29, trans.getStatoRendicontazione());
			callableStatement.setString(30, trans.getNoteGeneriche());
			if (trans.getChiaveQuadratura() == null)
				callableStatement.setNull(31, Types.BIGINT);
			else 
				callableStatement.setLong(31, trans.getChiaveQuadratura());
			callableStatement.setString(32, trans.getStatoQuadratura());
			callableStatement.setString(33, trans.getInvioNotificaStatoPagamentoEmailContribuente());
			callableStatement.setString(34, trans.getInvioNotificaStatoPagamentoEmailAmministratore());
			callableStatement.setString(35, trans.getOpertoreUltimoAggiornamento());
			callableStatement.setString(36, trans.getTipoStorno());
			if (trans.getNumeroTentativiPagamento() == null)
				callableStatement.setNull(37, Types.INTEGER);
			else
				callableStatement.setInt(37, trans.getNumeroTentativiPagamento());
			callableStatement.setString(38, trans.getOperatoreInserimento());
			callableStatement.setString(39, trans.getCampoOpzionale1());
			callableStatement.setString(40, trans.getCampoOpzionale2());
			callableStatement.setString(41, trans.getChiaveFlussoRendicontazioneRnincaext());
			
			callableStatement.setTimestamp(42, trans.getDataAccredito()==null ? null : new Timestamp(trans.getDataAccredito().getTime()));
			
			callableStatement.registerOutParameter(43, Types.INTEGER);

			callableStatement.executeUpdate();

			int numrighe = callableStatement.getInt(43);
			if (numrighe == 1)
				return true;
			else
				return false;

		} catch (IllegalArgumentException e) {
			throw new DaoException(e);
		} catch (SQLException e) {
			throw new DaoException(e);
		} catch (HelperException e) {
			throw new DaoException(e);
		}
		//inizio LP PG21XX04 Leak
		finally {
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
	
	//PG170040 GG 27022017 - Introdotto parametro timeElapseMM (lasso di tempo espresso in minuti)
	public List<Transazione> getTransazioniPending(int timeElapseMM) throws DaoException {
		List<Transazione> transazioneBean = null;
		//inizio LP PG21XX04 Leak
		//CallableStatement callableStatement;
		CallableStatement callableStatement = null;
		ResultSet data = null;
		//fine LP PG21XX04 Leak
		try {
		    callableStatement = prepareCall(Routines.TX_LISTA_BY_FESI.routine());
			callableStatement.setString(1, TRANSACTION_PENDING_STATE);
			callableStatement.setInt(2, timeElapseMM);
			if(callableStatement.execute()) {
				//inizio LP PG21XX04 Leak
				//ResultSet data = callableStatement.getResultSet();
				data = callableStatement.getResultSet();
				//fine LP PG21XX04 Leak
				transazioneBean = new Vector<Transazione>();
				while (data.next())
					transazioneBean.add(Transazione.getBean(data));
			}
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
		return transazioneBean;
	}	
	
	/**
	 * Costruisce un vector di oggetti <TRA_KTRAKTRA, REN_KRENKREN> dall'array di chiavi di rendicontazione 
	 * per aggiornarne lo stato
	 * @param arrayChiavi
	 * @return
	 * @throws DaoException
	 */
	public Vector<ObjectKeyKey> getVectorOfKeys_OLD(String []arrayChiavi) throws DaoException{
		Vector <ObjectKeyKey> v = new Vector<ObjectKeyKey>();
		//inizio LP PG21XX04 Leak
		//CallableStatement callableStatement;
		CallableStatement callableStatement = null;
		ResultSet data = null;
		//fine LP PG21XX04 Leak
		try 
		{
			for (int i = 0; i < arrayChiavi.length; i++)
			{
				callableStatement = prepareCall(Routines.TX_JOINTRATFR.routine());
				callableStatement.setString(1, arrayChiavi[i]);
				
				if(callableStatement.execute())
				{
					//inizio LP PG21XX04 Leak
					//ResultSet data = callableStatement.getResultSet();
					data = callableStatement.getResultSet();
					//fine LP PG21XX04 Leak
					
					while (data.next())
						if(containKey(arrayChiavi,data.getString("TFR_KRENKREN")))
							v.add(new ObjectKeyKey(data.getString("TRA_KTRAKTRA"),data.getString("TFR_KRENKREN")));
				}
				//inizio LP PG21XX04 Leak
				if (data != null) {
					try {
						data.close();
						data = null;
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
				if (callableStatement != null) {
					try {
						callableStatement.close();
						callableStatement = null;
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
				//fine LP PG21XX04 Leak
				callableStatement = prepareCall(Routines.TX_JOINTRATIC.routine());
				callableStatement.setString(1, arrayChiavi[i]);
				if(callableStatement.execute())
				{
					//inizio LP PG21XX04 Leak
					//ResultSet data = callableStatement.getResultSet();
					data = callableStatement.getResultSet();
					//fine LP PG21XX04 Leak
					
					while (data.next())
						if(containKey(arrayChiavi,data.getString("TIC_KRENKREN")))
							v.add(new ObjectKeyKey(data.getString("TRA_KTRAKTRA"),data.getString("TIC_KRENKREN")));
				}
				//inizio LP PG21XX04 Leak
				if (data != null) {
					try {
						data.close();
						data = null;
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
				if (callableStatement != null) {
					try {
						callableStatement.close();
						callableStatement = null;
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
				//fine LP PG21XX04 Leak
				callableStatement = prepareCall(Routines.TX_JOINTRATDT.routine());
				callableStatement.setString(1, arrayChiavi[i]);
				if(callableStatement.execute())
				{
					//inizio LP PG21XX04 Leak
					//ResultSet data = callableStatement.getResultSet();
					data = callableStatement.getResultSet();
					//fine LP PG21XX04 Leak
					
					while (data.next())
						if(containKey(arrayChiavi,data.getString("TDT_KRENKREN")))
							v.add(new ObjectKeyKey(data.getString("TRA_KTRAKTRA"),data.getString("TDT_KRENKREN")));
				}
				//inizio LP PG21XX04 Leak
				if (data != null) {
					try {
						data.close();
						data = null;
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
				if (callableStatement != null) {
					try {
						callableStatement.close();
						callableStatement = null;
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
				//fine LP PG21XX04 Leak
			}

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
		return v;
	}
	/**
	 * Costruisce un vector di oggetti <TRA_KTRAKTRA, REN_KRENKREN> dall'array di chiavi di rendicontazione 
	 * per aggiornarne lo stato
	 * @param arrayChiavi
	 * @return
	 * @throws DaoException
	 */
	public Vector<ObjectKeyKey> getVectorOfKeys(String []arrayChiavi) throws DaoException{
		Vector <ObjectKeyKey> v = new Vector<ObjectKeyKey>();
		//inizio LP PG21XX04 Leak
		//CallableStatement callableStatement;
		CallableStatement callableStatement = null;
		ResultSet data = null;
		//fine LP PG21XX04 Leak
		try {
		    
			for (int i = 0; i < arrayChiavi.length; i++)
			{
				callableStatement = prepareCall(Routines.TX_PYRENJOIN_ALL.routine());
				callableStatement.setString(1, arrayChiavi[i]);
				
				if(callableStatement.execute())
				{
					//inizio LP PG21XX04 Leak
					//ResultSet data = callableStatement.getResultSet();
					data = callableStatement.getResultSet();
					//fine LP PG21XX04 Leak
					
					while (data.next())
					//	if(containKey(arrayChiavi,data.getString("KRENKREN")))
							v.add(new ObjectKeyKey(data.getString("TRA_KTRAKTRA"),data.getString("KRENKREN")));
				}
				
			
			}

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
		return v;
	}	
	
	//inizio LP PG22XX10_LP2 - Ottimizzazione Rendicontazione - 20221101
	/**
	 * Costruisce un vector di oggetti <TRA_KTRAKTRA, REN_KRENKREN> dall'array di chiavi di rendicontazione 
	 * per aggiornarne lo stato versione per uso da Batch
	 * @param arrayChiavi
	 * @return
	 * @throws DaoException
	 */
	
	public CallableStatement callableStatementtVectorOfKeys = null;
	
	public Vector<ObjectKeyKey> getVectorOfKeysBatch(String [] arrayChiavi) throws DaoException{
		Vector <ObjectKeyKey> v = new Vector<ObjectKeyKey>();
		CallableStatement callableStatement = null;
		ResultSet data = null;
		try {
			
			if(callableStatementtVectorOfKeys == null)
				callableStatementtVectorOfKeys = prepareCall(Routines.TX_PYRENJOIN_ALL.routine());
		    
			for (int i = 0; i < arrayChiavi.length; i++) {
				callableStatement = callableStatementtVectorOfKeys;
				callableStatement.setString(1, arrayChiavi[i]);
				
				if(callableStatement.execute()) {
					data = callableStatement.getResultSet();
					while (data.next())
						v.add(new ObjectKeyKey(data.getString("TRA_KTRAKTRA"),data.getString("KRENKREN")));
				}
			}
		} catch (IllegalArgumentException e) {
			throw new DaoException(e);
		} catch (SQLException e) {
			throw new DaoException(e);
		} catch (HelperException e) {
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
		}
		return v;
	}	
	//fine LP PG22XX10_LP2 - Ottimizzazione Rendicontazione - 20221101

	/**
	 * Metodo che richiama la stored procedure di UPDATE per aggiornare lo stato di rendicontazione sulla tabella
	 * PYTRATB
	 * 
	 * @param chiaveTransazione
	 * @param flagStato
	 * @return
	 * @throws DaoException
	 */
	public boolean aggiornaStatoRen (String chiaveTransazione, String flagStato) throws DaoException{
		//inizio LP PG21XX04 Leak
		CallableStatement callableStatement = null;
		//fine LP PG21XX04 Leak
		try{
			//inizio LP PG21XX04 Leak
			//CallableStatement callableStatement = prepareCall(Routines.TX_UPDATE_TRA.routine());
			callableStatement = prepareCall(Routines.TX_UPDATE_TRA.routine());
			//fine LP PG21XX04 Leak
			callableStatement.setString(1, chiaveTransazione);
			callableStatement.setString(2, null);
			callableStatement.setString(3, null);
			callableStatement.setString(4, null);
			callableStatement.setString(5, null);
			callableStatement.setString(6, null);
			callableStatement.setString(7, null);
			callableStatement.setString(8, null);
			callableStatement.setString(9, null);
			callableStatement.setString(10, null);
			callableStatement.setString(11, null);
			callableStatement.setString(12, null);
			callableStatement.setString(13, null);
			callableStatement.setString(14, null);
			callableStatement.setString(15, null);
			callableStatement.setString(16, null);
			callableStatement.setString(17, null);
			callableStatement.setString(18, null);
			callableStatement.setString(19, null);
			callableStatement.setString(20, null);
			callableStatement.setString(21, null);
			callableStatement.setString(22, null);
			callableStatement.setString(23, null);
			callableStatement.setString(24, null);
			callableStatement.setString(25, null);
			callableStatement.setString(26, null);
			callableStatement.setString(27, null);
			callableStatement.setString(28, null);
			callableStatement.setString(29, flagStato);
			callableStatement.setString(30, null);
			callableStatement.setString(31, null);
			callableStatement.setString(32, null);
			callableStatement.setString(33, null);
			callableStatement.setString(34, null);
			callableStatement.setString(35, "SedaBatch");
			callableStatement.setString(36, null);
			callableStatement.setString(37, null);
			callableStatement.setString(38, null);
			callableStatement.setString(39, null);
			callableStatement.setString(40, null);
			callableStatement.setString(41, null);
			callableStatement.setString(42, null);
			
			callableStatement.registerOutParameter(43, Types.INTEGER);
			callableStatement.executeUpdate();
			int numrighe =  callableStatement.getInt(43);
			if (numrighe == 1) 
				return true;
			else 
				return false;
		} catch (SQLException x) {
			throw new DaoException(x);
		} catch (IllegalArgumentException x) {
			throw new DaoException(x);
		} catch (HelperException x) {
		throw new DaoException(x);
		}
		//inizio LP PG21XX04 Leak
		finally {
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

	/**
	 * Metodo che richiama la stored procedure di UPDATE per aggiornare lo stato del numero MAV sulla tabella
	 * PYTRATB
	 * 
	 * @param chiaveTransazione
	 * @param flagStato
	 * @return
	 * @throws DaoException
	 */
	public boolean aggiornaMAV (String chiaveTransazione, String numeroMAV) throws DaoException{
		//inizio LP PG21XX04 Leak
		CallableStatement callableStatement = null;
		//fine LP PG21XX04 Leak
		try{
			//inizio LP PG21XX04 Leak
			//CallableStatement callableStatement = prepareCall(Routines.TX_UPDATE_TRA.routine());
			callableStatement = prepareCall(Routines.TX_UPDATE_TRA.routine());
			//fine LP PG21XX04 Leak
			callableStatement.setString(1, chiaveTransazione);
			callableStatement.setString(2, null);
			callableStatement.setString(3, null);
			callableStatement.setString(4, null);
			callableStatement.setString(5, null);
			callableStatement.setString(6, null);
			callableStatement.setString(7, null);
			callableStatement.setString(8, null);
			callableStatement.setString(9, null);
			callableStatement.setString(10, null);
			callableStatement.setString(11, null);
			callableStatement.setString(12, null);
			callableStatement.setString(13, null);
			callableStatement.setString(14, null);
			callableStatement.setString(15, null);
			callableStatement.setString(16, null);
			callableStatement.setString(17, null);
			callableStatement.setString(18, null);
			callableStatement.setString(19, null);
			callableStatement.setString(20, null);
			callableStatement.setString(21, null);
			callableStatement.setString(22, null);
			callableStatement.setString(23, numeroMAV); // codice ordine gateway
			callableStatement.setString(24, null);
			callableStatement.setString(25, null);
			callableStatement.setString(26, null);
			callableStatement.setString(27, null);
			callableStatement.setString(28, null);
			callableStatement.setString(29, null);
			callableStatement.setString(30, null);
			callableStatement.setString(31, null);
			callableStatement.setString(32, null);
			callableStatement.setString(33, null);
			callableStatement.setString(34, null);
			callableStatement.setString(35, "SedaGateways");
			callableStatement.setString(36, null);
			callableStatement.setString(37, null);
			callableStatement.setString(38, null);
			callableStatement.setString(39, null);
			callableStatement.setString(40, null);
			callableStatement.setString(41, null);
			callableStatement.setString(42, null);
			
			callableStatement.registerOutParameter(43, Types.INTEGER);
			callableStatement.executeUpdate();
			int numrighe =  callableStatement.getInt(43);
			if (numrighe == 1) 
				return true;
			else 
				return false;
		} catch (SQLException x) {
			throw new DaoException(x);
		} catch (IllegalArgumentException x) {
			throw new DaoException(x);
		} catch (HelperException x) {
		throw new DaoException(x);
		}
		//inizio LP PG21XX04 Leak
		finally {
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
	
	public boolean aggiornaTransazioneCBI_InsolutiRID(String chiaveTransazione, Calendar dataflusso, String noteCBI, String esito) throws DaoException {
		CallableStatement callableStatement = null;
		try {
			callableStatement = prepareCall(Routines.TX_UPDATE_TRA.routine());
			callableStatement.setString(1, chiaveTransazione);
			
			callableStatement.setNull(2, Types.CHAR);
			callableStatement.setNull(3, Types.CHAR);
			callableStatement.setNull(4, Types.TIMESTAMP);
			callableStatement.setNull(5, Types.TIMESTAMP);
			callableStatement.setString(6, esito);
			callableStatement.setNull(7, Types.VARCHAR);
			callableStatement.setNull(8, Types.VARCHAR);
			callableStatement.setNull(9, Types.VARCHAR);
			callableStatement.setNull(10, Types.VARCHAR);
			callableStatement.setNull(11, Types.VARCHAR);
			callableStatement.setNull(12, Types.VARCHAR);
			callableStatement.setNull(13, Types.VARCHAR);
			callableStatement.setNull(14, Types.CHAR);
			callableStatement.setNull(15, Types.VARCHAR);
			callableStatement.setNull(16, Types.CHAR);
			callableStatement.setNull(17, Types.CHAR);
			callableStatement.setNull(18, Types.CHAR);
			callableStatement.setNull(19, Types.CHAR);
			callableStatement.setNull(20, Types.CHAR);
			callableStatement.setNull(21, Types.CHAR);
			callableStatement.setTimestamp(22, new Timestamp(dataflusso.getTimeInMillis()));
			callableStatement.setNull(23, Types.VARCHAR);
			callableStatement.setNull(24, Types.DECIMAL);		
			callableStatement.setNull(25, Types.DECIMAL);		
			callableStatement.setNull(26, Types.DECIMAL);		
			callableStatement.setNull(27, Types.DECIMAL);		
			callableStatement.setNull(28, Types.VARCHAR);	
			callableStatement.setNull(29, Types.CHAR);
			callableStatement.setString(30, noteCBI);
			callableStatement.setNull(31, Types.BIGINT);
			callableStatement.setNull(32, Types.CHAR);
			callableStatement.setNull(33, Types.CHAR);
			callableStatement.setNull(34, Types.CHAR);
			callableStatement.setNull(35, Types.VARCHAR);
			callableStatement.setNull(36, Types.CHAR);
			callableStatement.setNull(37, Types.SMALLINT);
			callableStatement.setNull(38, Types.VARCHAR);
			callableStatement.setNull(39, Types.VARCHAR);
			callableStatement.setNull(40, Types.VARCHAR);
			callableStatement.setNull(41, Types.CHAR);
			callableStatement.setNull(42, Types.TIMESTAMP);
			
			callableStatement.registerOutParameter(43, Types.INTEGER);
			callableStatement.executeUpdate();
			int numrighe =  callableStatement.getInt(43);
			if (numrighe == 1)
				return true; 
			else 
				return false;

		} catch (IllegalArgumentException e) {
			throw new DaoException(e);
		} catch (SQLException e) {
			throw new DaoException(e);
		} catch (HelperException e) {
			throw new DaoException(e);
		} finally {
			//inizio LP PG21XX04 Leak
			//try { DAOHelper.closeIgnoringException(callableStatement);
			//} catch (Exception ignore) { }
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
	
	public void insertTransazione(Transazione transazione) throws DaoException, SQLException {
		CallableStatement callableStatement = null;
		try
		{
			callableStatement = prepareCall(Routines.TRA_DOINSERT.routine(),false);
			transazione.save(callableStatement);
			callableStatement.execute();
			String esito=callableStatement.getString(49);
			if(esito.trim().equalsIgnoreCase("KO")){
				throw new DaoException(100,"Il numero di riferimento ordine associato alla transazione corrente: "+transazione.getChiaveTransazione()+" è già presente.");
			}
		} catch (IllegalArgumentException x) {
			throw new DaoException(x);
		} catch (HelperException x) {
			throw new DaoException(x);
		} finally {
			//inizio LP PG21XX04 Leak
			//closeCallableStatement(callableStatement);
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
	 * Metodo che verifica se la chiave di rendicontazione è contenuta nell'array di chiavi
	 * di rendicontazione sulle quali verranno effettuate le operazioni di aggiornamento
	 * 
	 * @param arrayChiavi
	 * @param key
	 * @return
	 * @throws DaoException
	 */
	public boolean containKey(String []arrayChiavi, String key) throws DaoException{
		for(int i=0; i<arrayChiavi.length; i++)
			if(key.equals(arrayChiavi[i]))
				return true;
		return false;
	}
	
	public List<TransazioneDocumentoEC> getTransazioniDocumentoEC(String codiceSocieta, String codiceUtente, String chiaveEnte, String numeroDocumento) throws DaoException {
		List<TransazioneDocumentoEC> listTransazioniEC = null;
		
		CallableStatement callableStatement = null;
		ResultSet dataIV = null;
		ResultSet dataFreccia = null;
		try {
		    callableStatement = prepareCall(Routines.TDT_LSTNUMDOC.routine());
			callableStatement.setString(1, codiceSocieta);
			callableStatement.setString(2, codiceUtente);
			callableStatement.setString(3, chiaveEnte);
			callableStatement.setString(4, numeroDocumento);
			if(callableStatement.execute()) {

				//resulset 1: dati della TDT - Bean IV
				dataIV = callableStatement.getResultSet();

				while (dataIV.next())
				{
					Transazione trans = Transazione.getBean(dataIV);
					TransazioneIV transIV = TransazioneIV.getBean(dataIV);
					if (trans != null && transIV != null)
					{
						if (listTransazioniEC == null)
							listTransazioniEC = new ArrayList<TransazioneDocumentoEC>();
						listTransazioniEC.add(new TransazioneDocumentoEC(trans, transIV));
					}
				}
				
				//resulset 1: dati della TFR - Bean Freccia
				if (callableStatement.getMoreResults())
				{
					dataFreccia = callableStatement.getResultSet();
					
					while (dataFreccia.next())
					{
						Transazione trans = Transazione.getBean(dataFreccia);
						TransazioneFreccia transFreccia = TransazioneFreccia.getBean(dataFreccia);
						if (trans != null && transFreccia != null)
						{
							if (listTransazioniEC == null)
								listTransazioniEC = new ArrayList<TransazioneDocumentoEC>();
							listTransazioniEC.add(new TransazioneDocumentoEC(trans, transFreccia));
						}
					}
				}
			}
		} catch (IllegalArgumentException e) {
			throw new DaoException(e);
		} catch (SQLException e) {
			throw new DaoException(e);
		} catch (HelperException e) {
			throw new DaoException(e);
		} finally {
			//inizio LP PG21XX04 Leak
			//if (callableStatement != null)
			//	DAOHelper.closeIgnoringException(callableStatement);
			//if (dataIV != null)
			//	DAOHelper.closeIgnoringException(dataIV);
			//if (dataFreccia != null)
			//	DAOHelper.closeIgnoringException(dataFreccia);
			if (dataFreccia != null) {
				try {
					dataFreccia.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (dataIV != null) {
				try {
					dataIV.close();
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
		return listTransazioniEC;
	}	
	
	//PG130130 GG - inizio
	public boolean updateTransazione(Transazione trans) throws DaoException, SQLException {
	//inizio LP PG22XX10_LP2 - Ottimizzazione Quadratura - 20221102
		CallableStatement callableStatement = null;
		try {
			callableStatement = prepareCall(Routines.TX_UPDATE_TRA.routine());
			return updateTransazioneTail(trans, callableStatement); 
		} catch (DaoException e) {
			throw new DaoException(e);
		} catch (SQLException e) {
			throw new SQLException(e);
		} catch (IllegalArgumentException e) {
			throw new DaoException(e);
		} catch (HelperException e) {
			throw new DaoException(e);
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

	private CallableStatement callableStatementUpdateTransazione = null;

	public boolean updateTransazioneBatch(Transazione trans) throws DaoException, SQLException {
		boolean bOk = false;
		try {
			if(callableStatementUpdateTransazione == null)
				callableStatementUpdateTransazione = prepareCall(Routines.TX_UPDATE_TRA.routine());
			bOk =  updateTransazioneTail(trans, callableStatementUpdateTransazione); 
		} catch (DaoException e) {
			throw new DaoException(e);
		} catch (SQLException e) {
			throw new SQLException(e);
		} catch (IllegalArgumentException e) {
			throw new DaoException(e);
		} catch (HelperException e) {
			throw new DaoException(e);
		}
		return bOk;
	}
	
	public boolean updateTransazioneTail(Transazione trans, CallableStatement callableStatement) throws DaoException, SQLException {
	//fine LP PG22XX10_LP2 - Ottimizzazione Quadratura - 20221102
		boolean bResultVal = false;
		//CallableStatement callableStatement = null; //LP PG22XX10_LP2 - Ottimizzazione Quadratura - 20221102
		try {
			//callableStatement = prepareCall(Routines.TX_UPDATE_TRA.routine()); //LP PG22XX10_LP2 - Ottimizzazione Quadratura - 20221102
			callableStatement.setString(1, trans.getChiaveTransazione());
			callableStatement.setNull(2, Types.CHAR);
			callableStatement.setString(3, trans.getChiaveGatewayDiPagamento());
			callableStatement.setNull(4, Types.TIMESTAMP);
			callableStatement.setTimestamp(5, new Timestamp(trans.getDataEffettivoPagamentoSuGateway().getTime()));
			callableStatement.setString(6, trans.getFlagEsitoTransazione());
			callableStatement.setNull(7, Types.VARCHAR);
			callableStatement.setNull(8, Types.VARCHAR);
			callableStatement.setNull(9, Types.VARCHAR);
			callableStatement.setNull(10, Types.VARCHAR);
			callableStatement.setNull(11, Types.VARCHAR);
			callableStatement.setNull(12, Types.VARCHAR);
			callableStatement.setNull(13, Types.VARCHAR);
			callableStatement.setNull(14, Types.CHAR);
			callableStatement.setNull(15, Types.VARCHAR);
			callableStatement.setNull(16, Types.CHAR);
			callableStatement.setNull(17, Types.CHAR);
			callableStatement.setNull(18, Types.CHAR);
			callableStatement.setNull(19, Types.CHAR);
			callableStatement.setNull(20, Types.CHAR);
			callableStatement.setNull(21, Types.CHAR);
			callableStatement.setNull(22, Types.TIMESTAMP);
			callableStatement.setNull(23, Types.VARCHAR);
			callableStatement.setBigDecimal(24, trans.getImportoTotaleTransazione());
			callableStatement.setBigDecimal(25, trans.getImportoCostoTransazione());
			if (trans.getImportoCostoGateway() != null) 
				callableStatement.setBigDecimal(26, trans.getImportoCostoGateway());
			else 
				callableStatement.setNull(26, Types.DECIMAL);
			callableStatement.setNull(27, Types.DECIMAL);
			callableStatement.setNull(28, Types.VARCHAR);
			callableStatement.setNull(29, Types.CHAR);
			callableStatement.setNull(30, Types.VARCHAR);
			callableStatement.setNull(31, Types.BIGINT);
			callableStatement.setNull(32, Types.CHAR);
			callableStatement.setNull(33, Types.CHAR);
			callableStatement.setNull(34, Types.CHAR);
			callableStatement.setString(35, trans.getOpertoreUltimoAggiornamento());
			callableStatement.setNull(36, Types.CHAR);
			callableStatement.setNull(37, Types.SMALLINT);
			callableStatement.setNull(38, Types.VARCHAR);
			callableStatement.setNull(39, Types.VARCHAR);
			if(trans.getCampoOpzionale2() != null)
				callableStatement.setString(40, trans.getCampoOpzionale2());
			else
				callableStatement.setNull(40, Types.VARCHAR);
			callableStatement.setNull(41, Types.CHAR);
			callableStatement.setNull(42, Types.TIMESTAMP);
			callableStatement.registerOutParameter(43, Types.INTEGER);
			
			callableStatement.execute();
			int numrighe =  callableStatement.getInt(43);
			if (numrighe == 1) 
				bResultVal = true;
			else bResultVal = false;
		} catch (IllegalArgumentException e) {
			throw new DaoException(e);
		//inizio LP PG22XX10_LP2 - Ottimizzazione Quadratura - 20221102
		//} catch (HelperException e) {
		//    throw new DaoException(e);
		//} finally {
		//	//inizio LP PG21XX04 Leak
		//	//closeCallableStatement(callableStatement);
		//	if (callableStatement != null) {
		//		try {
		//					callableStatement.close();
		//		} catch (SQLException e) {
		//			e.printStackTrace();
		//		}
		//	}
		//	//fine LP PG21XX04 Leak
		//fine LP PG22XX10_LP2 - Ottimizzazione Quadratura - 20221102
		}
		return bResultVal;
	}
	//PG130130 GG - fine
	
	public String getChiaveEnteByIUV(String iuv, String idDominio, String iur) throws DaoException
	{
		CallableStatement callableStatement = null;
		//inizio LP PG21XX04 Leak
		ResultSet data = null;
		//fine LP PG21XX04 Leak
		try {
		    callableStatement = prepareCall("PYRPTSP_SEL_KANE_BY_IUV");
			callableStatement.setString(1, idDominio);
			callableStatement.setString(2, iuv);
			callableStatement.setString(3, iur);
			
			if(callableStatement.execute())
			{
				//inizio LP PG21XX04 Leak
				//ResultSet data = callableStatement.getResultSet();
				data = callableStatement.getResultSet();
				//fine LP PG21XX04 Leak
				if (data.next())
				{
					return data.getString("CSOCCSOC")+"|"+data.getString("CUTECUTE")+"|"+data.getString("KANEKENT");
				}else{
					System.out.println("Non ci sono dati per la coppia iuv "+iuv+" iddominio "+idDominio);
					Exception e = new Exception("Non ci sono dati per la coppia iuv "+iuv+" iddominio "+idDominio);
					throw new DaoException(e);
				}
			}else{
				System.out.println("Non è stato possibile recuperare la chiave ente per la coppia iuv "+iuv+" iddominio "+idDominio);
				Exception e = new Exception("Non è stato possibile recuperare la chiave ente per la coppia iuv "+iuv+" iddominio "+idDominio);
				throw new DaoException(e);
			}
			
		} catch (IllegalArgumentException e) {
			throw new DaoException(e);
		} catch (SQLException e) {
			throw new DaoException(e);
		} catch (HelperException e) {
			throw new DaoException(e);
		} finally {
			//inizio LP PG21XX04 Leak
			//closeCallableStatement(callableStatement);
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
	
	//inizio LP PG22XX10_LP2
	public void close() {
		if(callableStatementGetTransazione != null) {
			try {
				callableStatementGetTransazione.close();
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				callableStatementGetTransazione = null;
			}
		}
		if(callableStatementtVectorOfKeys != null) {
			try {
				callableStatementtVectorOfKeys.close();
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				callableStatementtVectorOfKeys = null;				
			}
		}
		if(callableStatementUpdateTransazione != null) {
			try {
				callableStatementUpdateTransazione.close();
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				callableStatementUpdateTransazione = null;				
			}
		}
		return;
	}
	//fine LP PG22XX10_LP2

}
