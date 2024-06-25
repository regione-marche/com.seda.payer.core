package com.seda.payer.core.dao;

import java.math.BigDecimal;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

//inizio LP PG21XX04 Leak
//import com.seda.data.dao.DAOHelper;
//fine LP PG21XX04 Leak
import com.seda.commons.logger.CustomLoggerManager;
import com.seda.commons.logger.LoggerWrapper;
import com.seda.data.helper.HelperException;
import com.seda.payer.core.bean.AnagraficaStrutturaRicettiva;
import com.seda.payer.core.bean.DatiBollettino;
import com.seda.payer.core.bean.DettaglioComunicazioneImpostaSoggiorno;
import com.seda.payer.core.bean.ImpostaSoggiornoDatiAggregati;
import com.seda.payer.core.bean.ResponseData;
import com.seda.payer.core.bean.RiepilogoImpostaSoggiorno;
import com.seda.payer.core.bean.TariffaImpostaSoggiorno;
import com.seda.payer.core.bean.TestataComunicazioneImpostaSoggiorno;
import com.seda.payer.core.bean.TipologiaStrutturaRicettiva;
import com.seda.payer.core.exception.DaoException;
import com.seda.payer.core.handler.rest.RestBaseDaoHandler;
import com.sun.rowset.WebRowSetImpl;

@SuppressWarnings("restriction")
public class ComunicazioneImpostaSoggiornoDao extends RestBaseDaoHandler {

	protected LoggerWrapper logger = CustomLoggerManager.get(ComunicazioneImpostaSoggiornoDao.class);

	public ComunicazioneImpostaSoggiornoDao(Connection connection, String schema) {
		super(connection, schema);
	}

	//inizio LP PG21XX04 Leak
	//private void closeConnection(CallableStatement callableStatement)
	//{
	//	if (callableStatement != null)
	//		DAOHelper.closeIgnoringException(callableStatement);
	//}
	//fine LP PG21XX04 Leak
	
	public ComunicazioneImpostaSoggiornoDao(Connection connection, String schema, boolean isRest, String baseUrl) {
		super(connection, schema, isRest, baseUrl);
	}

	public void doInsertTestataComunicazione(TestataComunicazioneImpostaSoggiorno testataComunicazione) throws DaoException, SQLException {
		CallableStatement callableStatement = null;
		try	{
			callableStatement = prepareCall(Routines.SCT_DOINSERT.routine());
			callableStatement.setString(1, testataComunicazione.getChiaveTestataComunicazione());
			callableStatement.setString(2, testataComunicazione.getChiaveAnagraficaStrutturaRicettiva());
			callableStatement.setString(3, testataComunicazione.getCodiceSocieta());
			callableStatement.setString(4, testataComunicazione.getCodiceUtente());
			callableStatement.setString(5, testataComunicazione.getChiaveEnte());
			callableStatement.setDate(6, new java.sql.Date(testataComunicazione.getDataInizioComunicazione().getTime()));
			callableStatement.setDate(7, new java.sql.Date(testataComunicazione.getDataFineComunicazione().getTime()));
			callableStatement.setInt(8, testataComunicazione.getNumeroGiorniPeriodoPermanenzaTotale());
			callableStatement.setString(9, testataComunicazione.getTipoComunicazione());
			callableStatement.setString(10, testataComunicazione.getChiaveTariffaImpostaSoggiorno());
			callableStatement.setString(11, testataComunicazione.getNoteAggiuntive());
			callableStatement.setDate(12, new java.sql.Date(testataComunicazione.getDataScadenzaComunicazione().getTime()));
			callableStatement.setString(13, testataComunicazione.getStatoComunicazione());
			callableStatement.setString(14, testataComunicazione.getModalitaPagamento());
			callableStatement.setString(15, testataComunicazione.getCodiceRID());
			callableStatement.setString(16, testataComunicazione.getNumeroDocumentoGestionaleEntrate());
			callableStatement.setString(17, testataComunicazione.getCodiceBollettino());
			callableStatement.setString(18, testataComunicazione.getStatoDocumento());
			callableStatement.setDate(19, new java.sql.Date(testataComunicazione.getDataPagamento().getTime()));
			callableStatement.setString(20, testataComunicazione.getUsernameUtenteUltimoAggiornamento());
			callableStatement.setString(21, testataComunicazione.getOperatoreUltimoAggiornamento());
			//inizio LP PG190010_002_LP
			int ik = 21;
			callableStatement.setString(++ik, testataComunicazione.getDescrizioneEnte());
			callableStatement.setString(++ik, testataComunicazione.getTipoCC());
			callableStatement.setString(++ik, testataComunicazione.getNumeroCC());
			callableStatement.setString(++ik, testataComunicazione.getIntestazioneCC());
			callableStatement.setString(++ik, testataComunicazione.getAutorizzazioneCC());
			callableStatement.setString(++ik, testataComunicazione.getCodiceFiscaleEnte());
			callableStatement.setString(++ik, testataComunicazione.getCodiceCBill());
			callableStatement.setString(++ik, testataComunicazione.getBarcodePagoPA());
			callableStatement.setString(++ik, testataComunicazione.getQrCodePagoPA());
			callableStatement.setString(++ik, testataComunicazione.getCausaleDocumento());
			callableStatement.setString(++ik, testataComunicazione.getDescrizioneUfficio());
			callableStatement.setString(++ik, testataComunicazione.getDescrizioneTipoServizio());
			callableStatement.setString(++ik, testataComunicazione.getDescrizioneImpostaServizio());
			callableStatement.setString(++ik, testataComunicazione.getNumeroAvvisoPagoPA());
			callableStatement.setString(++ik, testataComunicazione.getCodiceIUV());
			//fine LP PG190010_002_LP
			//PG190300 - inizio
			callableStatement.setDate(++ik,  new java.sql.Date(testataComunicazione.getDataLimiteComunicazione().getTime()));
			callableStatement.setString(++ik, testataComunicazione.getChiaveAnagraficaStrutturaRicettivaPrincipale());
			callableStatement.setString(++ik, testataComunicazione.getNoteOperatore()==null?"":testataComunicazione.getNoteOperatore());
			callableStatement.setString(++ik, testataComunicazione.getOperatoreInserimentoComunicazione()==null?"":testataComunicazione.getOperatoreInserimentoComunicazione());
			//PG190300 - fine
		
			callableStatement.execute();
			
		} catch (IllegalArgumentException x) {
			throw new DaoException(x);
		} catch (HelperException x) {
			throw new DaoException(x);
		}
		finally {
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
	
	public void doUpdateTestataComunicazione(TestataComunicazioneImpostaSoggiorno testataComunicazione) throws DaoException, SQLException {
		CallableStatement callableStatement = null;
		System.out.println(testataComunicazione.toString());
		try	{
			callableStatement = prepareCall("PYSCTSP_UPD");
			callableStatement.setString(1, testataComunicazione.getChiaveTestataComunicazione());
			callableStatement.setString(2, testataComunicazione.getChiaveAnagraficaStrutturaRicettiva());
			callableStatement.setString(3, testataComunicazione.getCodiceSocieta());
			callableStatement.setString(4, testataComunicazione.getCodiceUtente());
			callableStatement.setString(5, testataComunicazione.getChiaveEnte());
			if (testataComunicazione.getDataInizioComunicazione() != null)
				callableStatement.setDate(6, new java.sql.Date(testataComunicazione.getDataInizioComunicazione().getTime()));
			else
				callableStatement.setNull(6, Types.DATE);
			if (testataComunicazione.getDataFineComunicazione() != null)
				callableStatement.setDate(7, new java.sql.Date(testataComunicazione.getDataFineComunicazione().getTime()));
			else
				callableStatement.setNull(7, Types.DATE);
			if (testataComunicazione.getNumeroGiorniPeriodoPermanenzaTotale() != null)
				callableStatement.setInt(8, testataComunicazione.getNumeroGiorniPeriodoPermanenzaTotale());
			else
				callableStatement.setNull(8, Types.INTEGER);
			callableStatement.setString(9, testataComunicazione.getTipoComunicazione());
			callableStatement.setString(10, testataComunicazione.getChiaveTariffaImpostaSoggiorno());
			callableStatement.setString(11, testataComunicazione.getNoteAggiuntive());
			if (testataComunicazione.getDataScadenzaComunicazione() != null)
				callableStatement.setDate(12, new java.sql.Date(testataComunicazione.getDataScadenzaComunicazione().getTime()));
			else
				callableStatement.setNull(12, Types.DATE);
			callableStatement.setString(13, testataComunicazione.getStatoComunicazione());
			callableStatement.setString(14, testataComunicazione.getModalitaPagamento());
			callableStatement.setString(15, testataComunicazione.getCodiceRID());
			callableStatement.setString(16, testataComunicazione.getNumeroDocumentoGestionaleEntrate());
			callableStatement.setString(17, testataComunicazione.getCodiceBollettino());
			callableStatement.setString(18, testataComunicazione.getStatoDocumento());
			if (testataComunicazione.getDataPagamento() != null)
				callableStatement.setDate(19, new java.sql.Date(testataComunicazione.getDataPagamento().getTime()));
			else
				callableStatement.setNull(19, Types.DATE);
			callableStatement.setString(20, testataComunicazione.getUsernameUtenteUltimoAggiornamento());
			callableStatement.setString(21, testataComunicazione.getOperatoreUltimoAggiornamento());
			//PG190300 - inizio
			//callableStatement.setString(22, testataComunicazione.getDataConfermaComunicazione()==null?null:"Y");//nella SP nn passo la data ma solo un flag se � valorizzata perch� provengo dall'invio della comunicazione
			if (testataComunicazione.getDataConfermaComunicazione() != null)
				callableStatement.setTimestamp(22, new Timestamp(testataComunicazione.getDataConfermaComunicazione().getTimeInMillis()));
			else
				callableStatement.setNull(22, Types.TIMESTAMP);
			//PG190300 - fine
			callableStatement.setString(23, testataComunicazione.getCodiceFreccia());
			//inizio LP PG190010_002_LP
			int ik = 23;
			//inizio LP PG200230
			//callableStatement.setString(++ik, testataComunicazione.getDescrizioneEnte());
			//callableStatement.setString(++ik, testataComunicazione.getTipoCC());
			//callableStatement.setString(++ik, testataComunicazione.getNumeroCC());
			//callableStatement.setString(++ik, testataComunicazione.getIntestazioneCC());
			//callableStatement.setString(++ik, testataComunicazione.getAutorizzazioneCC());
			//callableStatement.setString(++ik, testataComunicazione.getCodiceFiscaleEnte());
			//callableStatement.setString(++ik, testataComunicazione.getCodiceCBill());
			//callableStatement.setString(++ik, testataComunicazione.getBarcodePagoPA());
			//callableStatement.setString(++ik, testataComunicazione.getQrCodePagoPA());
			//callableStatement.setString(++ik, testataComunicazione.getCausaleDocumento());
			//callableStatement.setString(++ik, testataComunicazione.getDescrizioneUfficio());
			//callableStatement.setString(++ik, testataComunicazione.getDescrizioneTipoServizio());
			//callableStatement.setString(++ik, testataComunicazione.getDescrizioneImpostaServizio());
			//callableStatement.setString(++ik, testataComunicazione.getNumeroAvvisoPagoPA());
			//callableStatement.setString(++ik, testataComunicazione.getCodiceIUV());
			if (testataComunicazione.getDescrizioneEnte() != null)
				callableStatement.setString(++ik, testataComunicazione.getDescrizioneEnte());
			else
				callableStatement.setNull(++ik, Types.VARCHAR);
			if (testataComunicazione.getTipoCC() != null)
				callableStatement.setString(++ik, testataComunicazione.getTipoCC());
			else
				callableStatement.setNull(++ik, Types.VARCHAR);
			if (testataComunicazione.getNumeroCC() != null)
				callableStatement.setString(++ik, testataComunicazione.getNumeroCC());
			else
				callableStatement.setNull(++ik, Types.VARCHAR);
			if (testataComunicazione.getIntestazioneCC() != null)
				callableStatement.setString(++ik, testataComunicazione.getIntestazioneCC());
			else
				callableStatement.setNull(++ik, Types.VARCHAR);
			if (testataComunicazione.getAutorizzazioneCC() != null)
				callableStatement.setString(++ik, testataComunicazione.getAutorizzazioneCC());
			else
				callableStatement.setNull(++ik, Types.VARCHAR);
			if (testataComunicazione.getCodiceFiscaleEnte() != null)
				callableStatement.setString(++ik, testataComunicazione.getCodiceFiscaleEnte());
			else
				callableStatement.setNull(++ik, Types.VARCHAR);
			if (testataComunicazione.getCodiceCBill() != null)
				callableStatement.setString(++ik, testataComunicazione.getCodiceCBill());
			else
				callableStatement.setNull(++ik, Types.VARCHAR);
			if (testataComunicazione.getBarcodePagoPA() != null)
				callableStatement.setString(++ik, testataComunicazione.getBarcodePagoPA());
			else
				callableStatement.setNull(++ik, Types.VARCHAR);
			if (testataComunicazione.getQrCodePagoPA() != null)
				callableStatement.setString(++ik, testataComunicazione.getQrCodePagoPA());
			else
				callableStatement.setNull(++ik, Types.VARCHAR);
			if (testataComunicazione.getCausaleDocumento() != null)
				callableStatement.setString(++ik, testataComunicazione.getCausaleDocumento());
			else
				callableStatement.setNull(++ik, Types.VARCHAR);
			if (testataComunicazione.getDescrizioneUfficio() != null)
				callableStatement.setString(++ik, testataComunicazione.getDescrizioneUfficio());
			else
				callableStatement.setNull(++ik, Types.VARCHAR);
			if (testataComunicazione.getDescrizioneTipoServizio() != null)
				callableStatement.setString(++ik, testataComunicazione.getDescrizioneTipoServizio());
			else
				callableStatement.setNull(++ik, Types.VARCHAR);
			if (testataComunicazione.getDescrizioneImpostaServizio() != null)
				callableStatement.setString(++ik, testataComunicazione.getDescrizioneImpostaServizio());
			else
				callableStatement.setNull(++ik, Types.VARCHAR);
			if (testataComunicazione.getNumeroAvvisoPagoPA() != null)
				callableStatement.setString(++ik, testataComunicazione.getNumeroAvvisoPagoPA());
			else
				callableStatement.setNull(++ik, Types.VARCHAR);
			if (testataComunicazione.getCodiceIUV() != null)
				callableStatement.setString(++ik, testataComunicazione.getCodiceIUV());
			else
				callableStatement.setNull(++ik, Types.VARCHAR);
			//fine LP PG200230
			//fine LP PG190010_002_LP
			if (testataComunicazione.getDataLimiteComunicazione() != null)
				callableStatement.setDate(++ik, new java.sql.Date(testataComunicazione.getDataLimiteComunicazione().getTime()));
			else
				callableStatement.setNull(++ik, Types.DATE);
			callableStatement.setString(++ik, testataComunicazione.getChiaveAnagraficaStrutturaRicettivaPrincipale());
			if (testataComunicazione.getCodiceBollettinoCumulativo() != null)
				callableStatement.setString(++ik, testataComunicazione.getCodiceBollettinoCumulativo());
			else
				callableStatement.setNull(++ik, Types.VARCHAR);
			callableStatement.setString(++ik, testataComunicazione.getNoteOperatore());
			//inizio LP PG200230
			if (testataComunicazione.getBarcodePagoPACumulativo() != null)
				callableStatement.setString(++ik, testataComunicazione.getBarcodePagoPACumulativo());
			else
				callableStatement.setNull(++ik, Types.VARCHAR);
			if (testataComunicazione.getCodiceIUVCumulativo() != null)
				callableStatement.setString(++ik, testataComunicazione.getCodiceIUVCumulativo());
			else
				callableStatement.setNull(++ik, Types.VARCHAR);
			if (testataComunicazione.getNumeroAvvisoPagoPACumulativo() != null)
				callableStatement.setString(++ik, testataComunicazione.getNumeroAvvisoPagoPACumulativo());
			else
				callableStatement.setNull(++ik, Types.VARCHAR);
			if (testataComunicazione.getQrCodePagoPACumulativo() != null)
				callableStatement.setString(++ik, testataComunicazione.getQrCodePagoPACumulativo());
			else
				callableStatement.setNull(++ik, Types.VARCHAR);

			if(testataComunicazione.getOperatoreInserimentoComunicazione()!=null){
				callableStatement.setString(++ik, testataComunicazione.getOperatoreInserimentoComunicazione());
			}else{
				callableStatement.setNull(++ik, Types.VARCHAR);
			}
			//fine LP PG200230
			callableStatement.execute();
			
		} catch (IllegalArgumentException x) {
			throw new DaoException(x);
		} catch (HelperException x) {
			throw new DaoException(x);
		}
		finally {
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
	
	
	public void doInsertDettaglioComunicazione(DettaglioComunicazioneImpostaSoggiorno dettaglioComunicazione) throws DaoException, SQLException {
		CallableStatement callableStatement = null;
		try	{
			callableStatement = prepareCall(Routines.SCD_DOINSERT.routine());
			callableStatement.setString(1, dettaglioComunicazione.getChiaveDettaglioComunicazione());
			callableStatement.setString(2, dettaglioComunicazione.getChiaveTestataComunicazione());
			callableStatement.setString(3, dettaglioComunicazione.getChiaveTipologiaSoggetto());
			callableStatement.setInt(4, dettaglioComunicazione.getNumeroGiorniPermanenzaTotale());
			callableStatement.setInt(5, dettaglioComunicazione.getNumeroGiorniPermanenzaPagamento());
			callableStatement.setInt(6, dettaglioComunicazione.getNumeroPersone());
			callableStatement.setBigDecimal(7, dettaglioComunicazione.getImportoDaPagare());
			callableStatement.setString(8, dettaglioComunicazione.getOperatoreUltimoAggiornamento());
	    	//inizio LP PG1800XX_016
			callableStatement.setInt(9, dettaglioComunicazione.getChiaveFasciaTariffa());
	    	//fine LP PG1800XX_016
			callableStatement.setString(10, dettaglioComunicazione.getChiaveTariffa());

			callableStatement.execute();
			
		} catch (IllegalArgumentException x) {
			throw new DaoException(x);
		} catch (HelperException x) {
			throw new DaoException(x);
		}
		finally {
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
	
	public void doDeleteDettaglioComunicazione(String chiaveDettaglioComunicazione) throws DaoException, SQLException {
		CallableStatement callableStatement = null;
		try	{
			callableStatement = prepareCall(Routines.SCD_DODELETE_SCT.routine());
			callableStatement.setString(1, chiaveDettaglioComunicazione);
			
			callableStatement.execute();
			
		} catch (IllegalArgumentException x) {
			throw new DaoException(x);
		} catch (HelperException x) {
			throw new DaoException(x);
		}
		finally {
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
	
	//PG160210_001 GG 09122016 - introdotti dataConfermaDa e dataConfermaA
	public void doListComunicazioni(String chiaveAnagraficaStrutture, String dataInserimentoDa, String dataInserimentoA,
			String dataComunicazioneDa, String dataComunicazioneA, String tipoComunicazione,
			String statoComunicazione, String numeroDocumento, String statoDocumento, String dataConfermaDa, String dataConfermaA) throws DaoException {
		
		CallableStatement callableStatement = null;
		try	
		{
			callableStatement = prepareCall(Routines.SCT_DOLIST.routine());	
			callableStatement.setString(1, chiaveAnagraficaStrutture);
			callableStatement.setString(2, dataInserimentoDa);
			callableStatement.setString(3, dataInserimentoA);
			callableStatement.setString(4, dataComunicazioneDa);
			callableStatement.setString(5, dataComunicazioneA);
			callableStatement.setString(6, tipoComunicazione);
			callableStatement.setString(7, statoComunicazione);
			callableStatement.setString(8, numeroDocumento);
			callableStatement.setString(9, statoDocumento);
			//PG160210_001 GG 09122016 - inizio
			callableStatement.setString(10, dataConfermaDa);
			callableStatement.setString(11, dataConfermaA);
			//PG160210_001 GG 09122016 - fine
			
			//numero righe
			callableStatement.registerOutParameter(12, Types.INTEGER);
			
			if (callableStatement.execute()) 
			{
				this.loadWebRowSets(callableStatement);
				int numRows = callableStatement.getInt(12);
				registerPageInfo(numRows,1,1,numRows,numRows,1);
			}
			
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
	
	//PG160210_001 GG 07122016 - introdotti dataConfermaDa e dataConfermaA
	public ResponseData doListComunicazioniManager(String siglaProvincia, String codiceBelfiore, String numeroAutorizzazione, 
			String dataInserimentoDa, String dataInserimentoA, String dataComunicazioneDa, String dataComunicazioneA, 
			String tipoComunicazione, String statoComunicazione, String numeroDocumento, String statoPagamento,
			String usernameOperatore, String codiceTipologiaStruttura, String insegna, String dataConfermaDa, String dataConfermaA, int rowsPerPage, int pageNumber, String order) throws DaoException {
		
		ResponseData res = new ResponseData();
		CallableStatement callableStatement = null;
		try	
		{
			callableStatement = prepareCall(Routines.SCT_DOLIST_MGR.routine());	
			callableStatement.setString(1, siglaProvincia);
			callableStatement.setString(2, codiceBelfiore);
			callableStatement.setString(3, numeroAutorizzazione);
			callableStatement.setString(4, dataInserimentoDa);
			callableStatement.setString(5, dataInserimentoA);
			callableStatement.setString(6, dataComunicazioneDa);
			callableStatement.setString(7, dataComunicazioneA);
			callableStatement.setString(8, tipoComunicazione);
			callableStatement.setString(9, statoComunicazione);
			callableStatement.setString(10, numeroDocumento);
			callableStatement.setString(11, statoPagamento);
			callableStatement.setString(12, usernameOperatore);
			callableStatement.setString(13, codiceTipologiaStruttura);
			callableStatement.setString(14, insegna);
			//PG160210_001 GG 07122016 - inizio
			callableStatement.setString(15, dataConfermaDa);
			callableStatement.setString(16, dataConfermaA);
			//PG160210_001 GG 07122016 - fine
			callableStatement.setInt(17, rowsPerPage);
			callableStatement.setInt(18, pageNumber);
			callableStatement.setString(19, order);
			
			callableStatement.registerOutParameter(20, Types.INTEGER);
			callableStatement.registerOutParameter(21, Types.INTEGER);
			callableStatement.registerOutParameter(22, Types.INTEGER);
			callableStatement.registerOutParameter(23, Types.SMALLINT);
			
			callableStatement.registerOutParameter(24, Types.INTEGER); //tot_soggetti
			callableStatement.registerOutParameter(25, Types.INTEGER); //tot_pernottamenti_soggetti_ad_imposta
			callableStatement.registerOutParameter(26, Types.DECIMAL); //importo_totale
			
			if (callableStatement.execute()) {
				this.loadWebRowSets(callableStatement);
				registerPageInfo(rowsPerPage, pageNumber, 
								callableStatement.getInt(20), 
								callableStatement.getInt(21), 
								callableStatement.getInt(22), 
								callableStatement.getInt(23));
				
				
				res.setInfo1int(callableStatement.getInt(24)); //tot_soggetti
				res.setInfo2int(callableStatement.getInt(25)); //tot_pernottamenti_soggetti_ad_imposta
				res.setInfo1bd(callableStatement.getBigDecimal(26)); //importo_totale
				return res;
			}
			
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
		return null;
	}
	
	public void doListComunicazioniManagerCsv(String siglaProvincia, String codiceBelfiore, String numeroAutorizzazione, 
			String dataInserimentoDa, String dataInserimentoA, String dataComunicazioneDa, String dataComunicazioneA, 
			String tipoComunicazione, String statoComunicazione, String numeroDocumento, String statoPagamento,
			String usernameOperatore, String codiceTipologiaStruttura, String insegna) throws DaoException {
		
		CallableStatement callableStatement = null;
		try	
		{
			callableStatement = prepareCall(Routines.SCT_DOLIST_CSV.routine());	
			callableStatement.setString(1, siglaProvincia);
			callableStatement.setString(2, codiceBelfiore);
			callableStatement.setString(3, numeroAutorizzazione);
			callableStatement.setString(4, dataInserimentoDa);
			callableStatement.setString(5, dataInserimentoA);
			callableStatement.setString(6, dataComunicazioneDa);
			callableStatement.setString(7, dataComunicazioneA);
			callableStatement.setString(8, tipoComunicazione);
			callableStatement.setString(9, statoComunicazione);
			callableStatement.setString(10, numeroDocumento);
			callableStatement.setString(11, statoPagamento);
			callableStatement.setString(12, usernameOperatore);
			callableStatement.setString(13, codiceTipologiaStruttura);
			callableStatement.setString(14, insegna);
			
			if (callableStatement.execute()) {
				this.loadWebRowSets(callableStatement);
			}
			
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
	
	public void doListComunicazioniManagerCsvTrentino(String siglaProvincia, String codiceBelfiore, String numeroAutorizzazione, 
			String dataInserimentoDa, String dataInserimentoA, String dataComunicazioneDa, String dataComunicazioneA, 
			String tipoComunicazione, String statoComunicazione, String numeroDocumento, String statoPagamento,
			String usernameOperatore, String codiceTipologiaStruttura, String insegna, String dataConfermaDa, String dataConfermaA) throws DaoException {
		
		CallableStatement callableStatement = null;
		try	
		{
			callableStatement = prepareCall(Routines.SCT_DOLIST_CSV_TR.routine());	
			callableStatement.setString(1, siglaProvincia);
			callableStatement.setString(2, codiceBelfiore);
			callableStatement.setString(3, numeroAutorizzazione);
			callableStatement.setString(4, dataInserimentoDa);
			callableStatement.setString(5, dataInserimentoA);
			callableStatement.setString(6, dataComunicazioneDa);
			callableStatement.setString(7, dataComunicazioneA);
			callableStatement.setString(8, tipoComunicazione);
			callableStatement.setString(9, statoComunicazione);
			callableStatement.setString(10, numeroDocumento);
			callableStatement.setString(11, statoPagamento);
			callableStatement.setString(12, usernameOperatore);
			callableStatement.setString(13, codiceTipologiaStruttura);
			callableStatement.setString(14, insegna);
			callableStatement.setString(15, dataConfermaDa);
			callableStatement.setString(16, dataConfermaA);
			
			if (callableStatement.execute()) {
				this.loadWebRowSets(callableStatement);
			}
			
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
	
//inizio LP PG1800XX_016
	public void doListComunicazioniManagerCsvSassari(String siglaProvincia, String codiceBelfiore, String numeroAutorizzazione, 
			String dataInserimentoDa, String dataInserimentoA, String dataComunicazioneDa, String dataComunicazioneA, 
			String tipoComunicazione, String statoComunicazione, String numeroDocumento, String statoPagamento,
			String usernameOperatore, String codiceTipologiaStruttura, String insegna) throws DaoException {
		
		CallableStatement callableStatement = null;
		try	
		{
			callableStatement = prepareCall(Routines.SCT_DOLIST_CSV_SS.routine());
			callableStatement.setString(1, siglaProvincia);
			callableStatement.setString(2, codiceBelfiore);
			callableStatement.setString(3, numeroAutorizzazione);
			callableStatement.setString(4, dataInserimentoDa);
			callableStatement.setString(5, dataInserimentoA);
			callableStatement.setString(6, dataComunicazioneDa);
			callableStatement.setString(7, dataComunicazioneA);
			callableStatement.setString(8, tipoComunicazione);
			callableStatement.setString(9, statoComunicazione);
			callableStatement.setString(10, numeroDocumento);
			callableStatement.setString(11, statoPagamento);
			callableStatement.setString(12, usernameOperatore);
			callableStatement.setString(13, codiceTipologiaStruttura);
			callableStatement.setString(14, insegna);

			if (callableStatement.execute()) {
				this.loadWebRowSets(callableStatement);
			}
			
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
//fine LP PG1800XX_016
	
public ResponseData verificaAbilitazioneRIDHost(String codiceUtente, String codiceEnteGestionaleEntrate, 
			String impostaServizioGestionaleEntrate, String codiceContribuente) throws DaoException
	{
		CallableStatement callableStatement = null;
		try	{
			
    		System.out.println("DATA-IN-CODUTEN: " + codiceUtente);
    		System.out.println("DATA-IN-CODENTE: " + codiceEnteGestionaleEntrate);
    		System.out.println("DATA-IN-CODIMSE: " + impostaServizioGestionaleEntrate);
    		System.out.println("DATA-IN-CONTRIB: " + codiceContribuente);
			
			callableStatement = prepareCall(Routines.IS_RID_DODETAIL.routine());
			callableStatement.setString(1, codiceUtente);
			callableStatement.setString(2, codiceEnteGestionaleEntrate);
			callableStatement.setString(3, impostaServizioGestionaleEntrate);
			callableStatement.setString(4, codiceContribuente);
			
			//parametri di output
			callableStatement.registerOutParameter(5, Types.VARCHAR);
			callableStatement.registerOutParameter(6, Types.VARCHAR);
			callableStatement.registerOutParameter(7, Types.VARCHAR);
			callableStatement.registerOutParameter(8, Types.VARCHAR);
			callableStatement.registerOutParameter(9, Types.VARCHAR);
			
			callableStatement.execute();
			
			ResponseData res = new ResponseData();
			res.setRetCode(callableStatement.getString(8));
			res.setRetMessage(callableStatement.getString(9));
			res.setInfo1(callableStatement.getString(5)); //SI/NO: indica se per l'ente � attiva la procedura RID
			res.setInfo2(callableStatement.getString(6)); //SI/NO: indica se il codice RID contribuente � autorizzato
			res.setInfo3(callableStatement.getString(7).trim()); //codice RID contribuente
			
			return res;
			
		} catch (SQLException x) {
			throw new DaoException(x);
		} catch (IllegalArgumentException x) {
			throw new DaoException(x);
		} catch (HelperException x) {
			throw new DaoException(x);
		}
		finally {
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
	
	public ImpostaSoggiornoDatiAggregati doDetailComunicazione_Web(String chiaveTestataComunicazione) throws DaoException
	{
		ImpostaSoggiornoDatiAggregati datiRes = null;
		CallableStatement callableStatement = null;
		ResultSet testata = null;
		WebRowSetImpl dettaglio = null;
		//inizio LP PG21XX04 Leak
		ResultSet dettRs = null;
		//fine LP PG21XX04 Leak
		try	{
			callableStatement = prepareCall(Routines.SCT_DODETAIL_WEB.routine());
			callableStatement.setString(1, chiaveTestataComunicazione);
			
			if (callableStatement.execute()) 
			{
				datiRes = new ImpostaSoggiornoDatiAggregati();
				
				//resultset 1: testata + anagrafica struttura + tariffa + tipologia struttura
				testata = callableStatement.getResultSet();
				
				if (testata.next())
				{
					datiRes.setTestataComunicazione(new TestataComunicazioneImpostaSoggiorno(testata));
					datiRes.setAnagraficaStrutturaRicettiva(new AnagraficaStrutturaRicettiva(testata));
					datiRes.setTariffaImpostaSoggiorno(new TariffaImpostaSoggiorno(testata));
					datiRes.setTipologiaStrutturaRicettiva(new TipologiaStrutturaRicettiva(testata));
				}
				
				//resultset 2: dettaglio
				if (callableStatement.getMoreResults())
				{
					//lo appoggio in un webrowset per poterlo scorrere 2 volte
					dettaglio = new WebRowSetImpl();	
					//inizio LP PG21XX04 Leak
					//dettaglio.populate(callableStatement.getResultSet());
					dettRs = callableStatement.getResultSet();
					dettaglio.populate(dettRs); 
					//fine LP PG21XX04 Leak
					
					//carico il dettaglio sottoforma di struttura
					while (dettaglio.next())
						datiRes.addDettaglioComunicazione(new DettaglioComunicazioneImpostaSoggiorno(dettaglio));
					
					dettaglio.beforeFirst();
					//carico il dettaglio sottoforma di xml
					this.loadWebRowSet(dettaglio); 
					
				}
			}
			
			return datiRes;
			
		} catch (SQLException x) {
			throw new DaoException(x);
		} catch (IllegalArgumentException x) {
			throw new DaoException(x);
		} catch (HelperException x) {
			throw new DaoException(x);
		}
		finally {
			//inizio LP PG21XX04 Leak
			//closeConnection(callableStatement);
			//if (testata != null)
			//	DAOHelper.closeIgnoringException(testata);
			//if (dettaglio != null)
			//	DAOHelper.closeIgnoringException(dettaglio);
			if (testata != null) {
				try {
					testata.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (dettaglio != null) {
				try {
					dettaglio.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (dettRs != null) {
				try {
					dettRs.close();
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
	
	public ResponseData doSaveComunicazioneHost(AnagraficaStrutturaRicettiva anagStruttura, 
			String annoTributo, String codiceTributoGestionaleEntrate, String descrizioneComune, String siglaProvincia,
			BigDecimal importoTotaleComunicazione, String dataScadenza, String chiaveTestataComunicazione,
			String progressivoPeriodo, String tipologiaComunicazione,
			String notaTributo, String notaDocumento, String annoDocumentoGestionaleEntrate
			, String dataConferma, String dataLimite	//PG190300
			) throws DaoException
	{
		CallableStatement callableStatement = null;
		try	{			
			if(anagStruttura != null) {
				System.out.println("anagStruttura.getCodiceUtente(): <"+anagStruttura.getCodiceUtente()+">");
				System.out.println("anagStruttura.getCodiceEnteGestionaleEntrate(): <"+anagStruttura.getCodiceEnteGestionaleEntrate()+">");
				System.out.println("anagStruttura.getImpostaServizioGestionaleEntrate(): <"+anagStruttura.getImpostaServizioGestionaleEntrate()+">");
				System.out.println("anagStruttura.getCodiceContribuenteGestionaleEntrate(): <"+anagStruttura.getCodiceContribuenteGestionaleEntrate()+">");
				System.out.println("annoTributo: <"+annoTributo+">");
				System.out.println("codiceTributoGestionaleEntrate: <"+codiceTributoGestionaleEntrate+">");
				System.out.println("anagStruttura.getInsegnaStruttura(): <"+anagStruttura.getInsegnaStruttura().toUpperCase()+">");
				System.out.println("anagStruttura.getRagioneSocialeStruttura(): <"+anagStruttura.getRagioneSocialeStruttura().toUpperCase()+">");
				System.out.println("anagStruttura.getIndirizzoStruttura(): <"+anagStruttura.getIndirizzoStruttura().toUpperCase()+">");
				System.out.println("descrizioneComune: <"+descrizioneComune.toUpperCase()+">");
				System.out.println("siglaProvincia: <"+siglaProvincia.toUpperCase()+">");
				System.out.println("anagStruttura.getCapStruttura(): <"+anagStruttura.getCapStruttura()+">");
				System.out.println("importoTotaleComunicazione: <"+importoTotaleComunicazione+">");
				System.out.println("dataScadenza: <"+dataScadenza+">");
				System.out.println("operatore: <WISMANAGER>");
				System.out.println("anagStruttura.getCodiceBelfiore(): <"+anagStruttura.getCodiceBelfiore()+">");
				System.out.println("anagStruttura.getCodiceAutorizzazione(): <"+anagStruttura.getCodiceAutorizzazione()+">");
				System.out.println("anagStruttura.getPartitaIVAStruttura(): <"+anagStruttura.getPartitaIVAStruttura()+">");
				System.out.println("anagStruttura.getChiaveStrutturaRicettiva(): <"+anagStruttura.getChiaveStrutturaRicettiva()+">");
				System.out.println("chiaveTestataComunicazione: <"+chiaveTestataComunicazione+">");
				System.out.println("progressivoPeriodo: <"+progressivoPeriodo+">");
				System.out.println("tipologiaComunicazione: <"+tipologiaComunicazione+">");
				System.out.println("notaTributo: <"+notaTributo+">");
				System.out.println("notaDocumento: <"+notaDocumento+">");
				System.out.println("annoDocumentoGestionaleEntrate: <"+annoDocumentoGestionaleEntrate+">");
				System.out.println("dataConferma: <"+dataConferma+">");
				System.out.println("dataLimite: <"+dataLimite+">");
			}
			
			callableStatement = prepareCall(Routines.IS_COMUNICAZIONE_DOSAVE.routine());
			callableStatement.setString(1, anagStruttura.getCodiceUtente());
			callableStatement.setString(2, anagStruttura.getCodiceEnteGestionaleEntrate());
			callableStatement.setString(3, anagStruttura.getImpostaServizioGestionaleEntrate());
			callableStatement.setString(4, anagStruttura.getCodiceContribuenteGestionaleEntrate());
			callableStatement.setString(5, annoTributo);
			callableStatement.setString(6, codiceTributoGestionaleEntrate);
			String insegnaStruttura = (anagStruttura.getInsegnaStruttura()!=null && anagStruttura.getInsegnaStruttura().trim().length()>0)?anagStruttura.getInsegnaStruttura():anagStruttura.getRagioneSocialeStruttura();
			if (insegnaStruttura.trim().length()>100) {
				callableStatement.setString(7, insegnaStruttura.substring(0,100).toUpperCase());
			}
			else {
				callableStatement.setString(7, insegnaStruttura.toUpperCase());
			}
			if (anagStruttura.getIndirizzoStruttura().trim().length()>60) {
				callableStatement.setString(8, anagStruttura.getIndirizzoStruttura().substring(0,60).toUpperCase());
			}
			else {
				callableStatement.setString(8, anagStruttura.getIndirizzoStruttura().toUpperCase());
			}
			callableStatement.setString(9, descrizioneComune.toUpperCase());
			callableStatement.setString(10, siglaProvincia.toUpperCase());
			callableStatement.setString(11, anagStruttura.getCapStruttura());
			callableStatement.setBigDecimal(12, importoTotaleComunicazione);
			callableStatement.setString(13, dataScadenza);
			callableStatement.setString(14, "WISMANAGER");
			callableStatement.setString(15, anagStruttura.getCodiceBelfiore());
			callableStatement.setString(16, anagStruttura.getCodiceAutorizzazione());
			callableStatement.setString(17, anagStruttura.getPartitaIVAStruttura().toUpperCase());
			callableStatement.setString(18, anagStruttura.getChiaveStrutturaRicettiva());
			callableStatement.setString(19, chiaveTestataComunicazione);
			callableStatement.setString(20, progressivoPeriodo);
			callableStatement.setString(21, tipologiaComunicazione);
			callableStatement.setString(22, notaTributo);
			callableStatement.setString(23, notaDocumento);
			callableStatement.setString(24, annoDocumentoGestionaleEntrate);
			//PG190300 - inizio
			callableStatement.setString(25, dataConferma);
			callableStatement.setString(26, dataLimite);
			//PG190300 - fine
			
			//parametri di output
			callableStatement.registerOutParameter(27, Types.VARCHAR);
			callableStatement.registerOutParameter(28, Types.VARCHAR);
			callableStatement.registerOutParameter(29, Types.VARCHAR);
			callableStatement.registerOutParameter(30, Types.VARCHAR);
			callableStatement.registerOutParameter(31, Types.VARCHAR);
			//inizio LP PG190010_002_LP
			callableStatement.registerOutParameter(32, Types.VARCHAR);
			callableStatement.registerOutParameter(33, Types.VARCHAR);
			callableStatement.registerOutParameter(34, Types.VARCHAR);
			callableStatement.registerOutParameter(35, Types.VARCHAR);
			callableStatement.registerOutParameter(36, Types.VARCHAR);
			callableStatement.registerOutParameter(37, Types.VARCHAR);
			callableStatement.registerOutParameter(38, Types.VARCHAR);
			callableStatement.registerOutParameter(39, Types.VARCHAR);
			callableStatement.registerOutParameter(40, Types.VARCHAR);
			callableStatement.registerOutParameter(41, Types.VARCHAR);
			callableStatement.registerOutParameter(42, Types.VARCHAR);
			callableStatement.registerOutParameter(43, Types.VARCHAR);
			callableStatement.registerOutParameter(44, Types.VARCHAR);
			callableStatement.registerOutParameter(45, Types.VARCHAR);
			callableStatement.registerOutParameter(46, Types.VARCHAR);
			//fine LP PG190010_002_LP
			callableStatement.registerOutParameter(47, Types.VARCHAR);
			callableStatement.registerOutParameter(48, Types.VARCHAR);
			
			callableStatement.execute();
			
			ResponseData res = new ResponseData();
			res.setRetCode(callableStatement.getString(47));
			res.setRetMessage(callableStatement.getString(48));

			res.setInfo1(callableStatement.getString(27)); //numero documento
			res.setInfo2(callableStatement.getString(29)); //codice RID
			String numeroBollettino = callableStatement.getString(30);
			if (numeroBollettino.length() == 17) //aggiungo uno 0 in testa
				numeroBollettino = "0" + numeroBollettino;
			res.setInfo3(numeroBollettino); //numero bollettino
			res.setInfo4(callableStatement.getString(28)); //chiave main frame
			res.setInfo5(callableStatement.getString(31)); //codice Freccia esteso
	    	//inizio LP PG190010_002_LP
			res.setDescrizioneEnte(callableStatement.getString(32));
			res.setTipoCC(callableStatement.getString(33));
			res.setNumeroCC(callableStatement.getString(34));
			res.setIntestazioneCC(callableStatement.getString(35));
			res.setAutorizzazioneCC(callableStatement.getString(36));
			res.setCodiceFiscaleEnte(callableStatement.getString(37));
			res.setCodiceCBill(callableStatement.getString(38));
			res.setBarcodePagoPA(callableStatement.getString(39));
			res.setQrCodePagoPA(callableStatement.getString(40));
			res.setCausaleDocumento(callableStatement.getString(41));
			res.setDescrizioneUfficio(callableStatement.getString(42));
			res.setDescrizioneTipoServizio(callableStatement.getString(43));
			res.setDescrizioneImpostaServizio(callableStatement.getString(44));
			res.setNumeroAvvisoPagoPA(callableStatement.getString(45));
			res.setCodiceIUV(callableStatement.getString(46));
			//fine LP PG190010_002_LP
			
//			System.out.println("retCode: <"+callableStatement.getString(47)+">");
//			System.out.println("retMessage: <"+callableStatement.getString(48)+">");
//			System.out.println("info1: <"+callableStatement.getString(27)+">");
//			System.out.println("info2: <"+callableStatement.getString(29)+">");
//			System.out.println("info3: <"+numeroBollettino+">");
//			System.out.println("info4: <"+callableStatement.getString(30)+">");
//			System.out.println("info5: <"+callableStatement.getString(31)+">");
//			System.out.println("DescrizioneEnte: <"+callableStatement.getString(32)+">");
//			System.out.println("TipoCC: <"+callableStatement.getString(33)+">");
//			System.out.println("NumeroCC: <"+callableStatement.getString(34)+">");
//			System.out.println("IntestazioneCC: <"+callableStatement.getString(35)+">");
//			System.out.println("AutorizzazioneCC: <"+callableStatement.getString(36)+">");
//			System.out.println("CodiceFiscaleEnte: <"+callableStatement.getString(37)+">");
//			System.out.println("CodiceCBill: <"+callableStatement.getString(38)+">");
//			System.out.println("BarcodePagoPA: <"+callableStatement.getString(39)+">");
//			System.out.println("QrCodePagoPA: <"+callableStatement.getString(40)+">");
//			System.out.println("CausaleDocumento: <"+callableStatement.getString(41)+">");
//			System.out.println("DescrizioneUfficio: <"+callableStatement.getString(42)+">");
//			System.out.println("DescrizioneTipoServizio: <"+callableStatement.getString(43)+">");
//			System.out.println("DescrizioneImpostaServizio: <"+callableStatement.getString(44)+">");
//			System.out.println("NumeroAvvisoPagoPA: <"+callableStatement.getString(45)+">");
//			System.out.println("CodiceIUV: <"+callableStatement.getString(46)+">");
		
			return res;
			
		} catch (SQLException x) {
			x.printStackTrace();
			throw new DaoException(x);
		} catch (IllegalArgumentException x) {
			x.printStackTrace();
			throw new DaoException(x);
		} catch (HelperException x) {
			x.printStackTrace();
			throw new DaoException(x);
		}
		finally {
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
	
	//PG190300 - inizio
	public void doListComunicazioniSecondarie(String chiaveTestataComunicazione) throws DaoException {
		
		CallableStatement callableStatement = null;
		try	
		{
			callableStatement = prepareCall(Routines.SCT_DOLIST_SEC.routine());	
			callableStatement.setString(1, chiaveTestataComunicazione);
						
			if (callableStatement.execute()) {
				this.loadWebRowSets(callableStatement);
			}
			
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
	
	public boolean doCheckComunicazioniSecondarie(String chiaveTestataComunicazione) throws DaoException, SQLException {
		CallableStatement callableStatement = null;
		try	{
			callableStatement = prepareCall(Routines.SCT_DOCHECK_SEC.routine());
			callableStatement.setString(1, chiaveTestataComunicazione);
			callableStatement.registerOutParameter(2, Types.VARCHAR);
			callableStatement.execute();
			return callableStatement.getString(2).equalsIgnoreCase("OK");
		} catch (IllegalArgumentException x) {
			throw new DaoException(x);
		} catch (HelperException x) {
			throw new DaoException(x);
		}
		finally {
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
	//Recupera codice freccia esteso e importo bollettino
	public DatiBollettino doGetDatiBollettino(String cutecute, String nBollettino, String flagZero) throws DaoException {
		DatiBollettino res = new DatiBollettino();
		CallableStatement callableStatement = null;
		
		try	{
			System.out.println("DATA-IN-CODUTEN: " + cutecute);
			System.out.println("DATA-IN-NUMERAV: " + nBollettino);	
			System.out.println("DATA-IN-FLAG-ZERO: " + flagZero);
			
			callableStatement = prepareCall(Routines.DO_DATI_BOLLETTINO.routine());	
			callableStatement.setString(1, cutecute);
			callableStatement.setBigDecimal(2, new BigDecimal(nBollettino));
			callableStatement.setString(3, flagZero);
			callableStatement.registerOutParameter(4, Types.DECIMAL);
			callableStatement.registerOutParameter(5, Types.VARCHAR);
			//inizio LP PG200230
			//callableStatement.registerOutParameter(6, Types.VARCHAR);
			//callableStatement.registerOutParameter(7, Types.VARCHAR);
			callableStatement.registerOutParameter(6, Types.VARCHAR); //DescrizioneEnte
			callableStatement.registerOutParameter(7, Types.VARCHAR); //TipoCC
			callableStatement.registerOutParameter(8, Types.VARCHAR); //NumeroCC
			callableStatement.registerOutParameter(9, Types.VARCHAR); //IntestazioneCC / DescrizioneCC
			callableStatement.registerOutParameter(10, Types.VARCHAR); //AutorizzazioneCC
			callableStatement.registerOutParameter(11, Types.VARCHAR); //CodiceFiscaleEnte
			callableStatement.registerOutParameter(12, Types.VARCHAR); //CodiceCBill
			callableStatement.registerOutParameter(13, Types.VARCHAR); //BarcodePagoPA
			callableStatement.registerOutParameter(14, Types.VARCHAR); //QrCodePagoPA
			callableStatement.registerOutParameter(15, Types.VARCHAR); //CausaleDocumento
			callableStatement.registerOutParameter(16, Types.VARCHAR); //DescrizioneUfficio
			callableStatement.registerOutParameter(17, Types.VARCHAR); //DescrizioneTipoServizio
			callableStatement.registerOutParameter(18, Types.VARCHAR); //DescrizioneImpostaServizio
			callableStatement.registerOutParameter(19, Types.VARCHAR); //NumeroAvvisoPagoPA
			callableStatement.registerOutParameter(20, Types.VARCHAR); //CodiceIUV
			callableStatement.registerOutParameter(21, Types.VARCHAR); //RetCode
			callableStatement.registerOutParameter(22, Types.VARCHAR); //Message
			//fine LP PG200230
						
			callableStatement.execute();

			System.out.println("Importo bollettino: " + callableStatement.getBigDecimal(4));
			res.setImportoBollettino(callableStatement.getBigDecimal(4));
			res.setCodiceFreccia(callableStatement.getString(5));
			//inizio LP PG200230
			//res.setRetCode(callableStatement.getString(6));
			//res.setRetMessage(callableStatement.getString(7));
			res.setDescrizioneEnte(callableStatement.getString(6));
			res.setTipoCC(callableStatement.getString(7));
			res.setNumeroCC(callableStatement.getString(8));
			res.setIntestazioneCC(callableStatement.getString(9));
			res.setAutorizzazioneCC(callableStatement.getString(10));
			res.setCodiceFiscaleEnte(callableStatement.getString(11));
			res.setCodiceCBill(callableStatement.getString(12));
			res.setBarcodePagoPA(callableStatement.getString(13));
			res.setQrCodePagoPA(callableStatement.getString(14));
			res.setCausaleDocumento(callableStatement.getString(15));
			res.setDescrizioneUfficio(callableStatement.getString(16));
			res.setDescrizioneTipoServizio(callableStatement.getString(17));
			res.setDescrizioneImpostaServizio(callableStatement.getString(18));
			res.setNumeroAvvisoPagoPA(callableStatement.getString(19));
			res.setCodiceIUV(callableStatement.getString(20));
			res.setRetCode(callableStatement.getString(21));
			res.setRetMessage(callableStatement.getString(22));
			//fine LP PG200230
			
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
		return res;
	}
	
	public DatiBollettino doGetBollettinoCumulativo(String cutecute, String listaDocumentiStruttureCorrelate, String idBollettinoCumulativoDaAggiornare) throws DaoException {
		DatiBollettino res = new DatiBollettino();
		CallableStatement callableStatement = null;
		System.out.println("cutecute: " + cutecute);
		System.out.println("idBollettinoCumulativoDaAggiornare: " + (idBollettinoCumulativoDaAggiornare == null || idBollettinoCumulativoDaAggiornare.equals("") ? new BigDecimal(0) : new BigDecimal(idBollettinoCumulativoDaAggiornare)));	
		System.out.println("listaDocumentiStruttureCorrelate: " + listaDocumentiStruttureCorrelate);
		try	
		{
    		System.out.println("DATA-IN-CODUTEN: " + cutecute);
    		System.out.println("DATA-IN-NUMERAV: " + (idBollettinoCumulativoDaAggiornare == null || idBollettinoCumulativoDaAggiornare.equals("") ? new BigDecimal(0) : new BigDecimal(idBollettinoCumulativoDaAggiornare)));
    		System.out.println("DATA-IN-LISDOCU: " + listaDocumentiStruttureCorrelate);

			callableStatement = prepareCall(Routines.DO_BOLLETTINO_CUMULATIVO.routine());	
			callableStatement.setString(1, cutecute);
			callableStatement.setBigDecimal(2, (idBollettinoCumulativoDaAggiornare == null || idBollettinoCumulativoDaAggiornare.equals("") ? new BigDecimal(0) : new BigDecimal(idBollettinoCumulativoDaAggiornare)) );
			callableStatement.setString(3, listaDocumentiStruttureCorrelate);

			callableStatement.registerOutParameter(4, Types.DECIMAL);
			callableStatement.registerOutParameter(5, Types.VARCHAR);
			callableStatement.registerOutParameter(6, Types.VARCHAR);
						
			callableStatement.execute();
			
			res.setNumeroBollettinoCumulativo(callableStatement.getString(4));
			res.setRetCode(callableStatement.getString(5));
			res.setRetMessage(callableStatement.getString(6));
		
			
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
		return res;
		
	}
	
	//PG190300 - fine
	public void getDdlAlloggi(String partitaIVA, String anno) throws DaoException {
		
		CallableStatement callableStatement = null;
		try	{ 
			callableStatement = prepareCall(Routines.SAN_DDL_ALLOGGI.routine());
			callableStatement.setString(1, partitaIVA);
			callableStatement.setString(2, anno);
			if (callableStatement.execute()) {
				this.loadWebRowSet(callableStatement);
			}
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
	
	public RiepilogoImpostaSoggiorno getRiepilogoIS(String partitaIva, String anno) throws DaoException{
		RiepilogoImpostaSoggiorno res = new RiepilogoImpostaSoggiorno();
		CallableStatement callableStatement = null;
		try	{ 
			callableStatement = prepareCall(Routines.DO_RIEPILOGO.routine());
			callableStatement.setString(1, partitaIva);
			callableStatement.setString(2, anno);
			callableStatement.registerOutParameter(3, Types.INTEGER);
			callableStatement.registerOutParameter(4, Types.INTEGER);
			callableStatement.registerOutParameter(5, Types.VARCHAR);
			
			callableStatement.execute();
			
			res.setComunicazioniInviate(callableStatement.getInt(3));
			res.setComunicazioniDaInviare(callableStatement.getInt(4));
			res.setStato(callableStatement.getString(5));
			
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
		return res;
	}
	public void doList_tariffeIS(String codiceBelfiore,String tipologiaStruttura,String ricercaDataDa, String ricercaDataA)throws DaoException{
		CallableStatement callableStatement = null;
		try	{ 
			callableStatement = prepareCall("PYSTFSP_LST_ALL");
			callableStatement.setString(1, codiceBelfiore);
			callableStatement.setString(2, tipologiaStruttura);
			callableStatement.setString(3, ricercaDataDa);
			callableStatement.setString(4, ricercaDataA);
			
			if (callableStatement.execute()) {
				this.loadWebRowSet(callableStatement);
			}
			
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
	//PG22XX04_SB1 - inizio
	public void checkComunicazioniBollettinoCumulativo(String partitaIva, String anno)throws DaoException{
		CallableStatement callableStatement = null;
		try	{ 
			callableStatement = prepareCall("PYSCTSP_CHK_CUM");
			callableStatement.setString(1, partitaIva);
			callableStatement.setString(2, anno);
			
			if (callableStatement.execute()) {
				this.loadWebRowSet(callableStatement);
			}
			
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
	}
   //PG22XX04_SB1 - fine

	public List<TestataComunicazioneImpostaSoggiorno> listaComunicazioni(String data, String flag)throws DaoException{
		CallableStatement callableStatement = null;
		ResultSet resultSet = null;
		LocalDate date=null;
		try {
			logger.info("data core " + data);
			logger.info("data java sql " + java.sql.Date.valueOf(data));
			DateTimeFormatter dtf = new DateTimeFormatterBuilder()
					.parseCaseInsensitive() //For case-insensitive parsing
					.appendPattern("yyyy-MM-dd")
					.toFormatter(Locale.ITALIAN);

			date = LocalDate.parse(data, dtf);
		}catch(Throwable e){
			logger.info(e.getMessage());
			date= LocalDate.parse("1000-01-01");
		}
		List<TestataComunicazioneImpostaSoggiorno> testate = new ArrayList<>();
		TestataComunicazioneImpostaSoggiorno testata = new TestataComunicazioneImpostaSoggiorno();
		try	{
			callableStatement = prepareCall("PYSCTSP_LST_SEND_UFFICIO");
			callableStatement.setString(1, Date.valueOf(date).toString()); //Stringa
			callableStatement.setString(2, flag);

			if (callableStatement.execute()) {
				this.loadWebRowSet(callableStatement);
			}

			while (this.getWebRowSet().next()) {

                testata.setChiaveTestataComunicazione(this.getWebRowSet().getString(1));
				testata.setChiaveAnagraficaStrutturaRicettiva(this.getWebRowSet().getString(2));
				testata.setCodiceSocieta(this.getWebRowSet().getString(3));
				testata.setCodiceUtente(this.getWebRowSet().getString(4));
				testata.setChiaveEnte(this.getWebRowSet().getString(5));
				Calendar calInse = Calendar.getInstance();
				calInse.setTimeInMillis(this.getWebRowSet().getDate(6).getTime());
				testata.setDataInserimentoComunicazione(calInse);
				testata.setDataInizioComunicazione(this.getWebRowSet().getDate(7));
				testata.setDataFineComunicazione(this.getWebRowSet().getDate(8));
				testata.setNumeroGiorniPeriodoPermanenzaTotale(this.getWebRowSet().getInt(9));
				testata.setTipoComunicazione(this.getWebRowSet().getString(10));
				testata.setChiaveTariffaImpostaSoggiorno(this.getWebRowSet().getString(11));
				testata.setNoteAggiuntive(this.getWebRowSet().getString(12));
				testata.setDataScadenzaComunicazione(this.getWebRowSet().getDate(13));
				testata.setStatoComunicazione(this.getWebRowSet().getString(14));
				testata.setModalitaPagamento(this.getWebRowSet().getString(15));
				testata.setCodiceRID(this.getWebRowSet().getString(16));
				testata.setNumeroDocumentoGestionaleEntrate(this.getWebRowSet().getString(17));
				testata.setCodiceBollettino(this.getWebRowSet().getString(18));
				testata.setStatoDocumento(this.getWebRowSet().getString(19));
				testata.setDataPagamento(this.getWebRowSet().getDate(20));
				testata.setUsernameUtenteUltimoAggiornamento(this.getWebRowSet().getString(21));
				Calendar cal = Calendar.getInstance();
				cal.setTimeInMillis(this.getWebRowSet().getDate(22).getTime());
				testata.setDataUltimoAggiornamento(cal);
				testata.setOperatoreUltimoAggiornamento(this.getWebRowSet().getString(23));
				Calendar calConf = Calendar.getInstance();
				calConf.setTimeInMillis(this.getWebRowSet().getDate(24).getTime());
				testata.setDataConfermaComunicazione(calConf);
				//LP PG200230 Ho inserito la trim in tutti le data.getString da qui in avanti
				testata.setCodiceFreccia(this.getWebRowSet().getString(25));
				//inizio LP PG190010_002_LP
				testata.setDescrizioneEnte(this.getWebRowSet().getString(26));
				testata.setTipoCC(this.getWebRowSet().getString(27));
				testata.setNumeroCC(this.getWebRowSet().getString(28));
				testata.setIntestazioneCC(this.getWebRowSet().getString(29));
				testata.setAutorizzazioneCC(this.getWebRowSet().getString(30));
				testata.setCodiceFiscaleEnte(this.getWebRowSet().getString(31));
				testata.setCodiceCBill(this.getWebRowSet().getString(32));
				testata.setBarcodePagoPA(this.getWebRowSet().getString(33));
				testata.setQrCodePagoPA(this.getWebRowSet().getString(34));
				testata.setCausaleDocumento(this.getWebRowSet().getString(35));
				testata.setDescrizioneUfficio(this.getWebRowSet().getString(36));
				testata.setDescrizioneTipoServizio(this.getWebRowSet().getString(37));
				testata.setDescrizioneImpostaServizio(this.getWebRowSet().getString(38));
				testata.setNumeroAvvisoPagoPA(this.getWebRowSet().getString(39));
				testata.setCodiceIUV(this.getWebRowSet().getString(40));
				//fine LP PG190010_002_LP
				//PG190300 - inizio
				testata.setDataLimiteComunicazione(this.getWebRowSet().getDate(41));
				testata.setChiaveAnagraficaStrutturaRicettivaPrincipale(this.getWebRowSet().getString(42));
				testata.setCodiceBollettinoCumulativo(this.getWebRowSet().getString(43));
				testata.setNoteOperatore(this.getWebRowSet().getString(44));
				//PG190300 - fine
				//PG190330 GG - inizio
				try {
					testata.setFlagAlloggio(this.getWebRowSet().getString(45));
				} catch (Exception ex) {
				}
				//PG190330 GG - fine
				//inizio LP PG200230
				testata.setBarcodePagoPACumulativo(this.getWebRowSet().getString(46));
				testata.setQrCodePagoPACumulativo(this.getWebRowSet().getString(47));
				testata.setNumeroAvvisoPagoPACumulativo(this.getWebRowSet().getString(48));
				testata.setOperatoreInserimentoComunicazione(this.getWebRowSet().getString(49));

				testate.add(testata);
			}

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
		return testate;
	}

}
