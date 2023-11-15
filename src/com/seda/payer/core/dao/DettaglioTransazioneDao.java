package com.seda.payer.core.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import com.seda.commons.logger.CustomLoggerManager;
import com.seda.commons.logger.LoggerWrapper;
//inizio LP PG21XX04 Leak
//import com.seda.data.dao.DAOHelper;
//fine LP PG21XX04 Leak
import com.seda.data.helper.HelperException;
import com.seda.payer.core.bean.DettaglioMonTransazione;
import com.seda.payer.core.bean.DettaglioTransazione;
import com.seda.payer.core.exception.DaoException;
import com.seda.payer.core.handler.BaseDaoHandler;

public class DettaglioTransazioneDao extends BaseDaoHandler {

	private static final LoggerWrapper log =  CustomLoggerManager.get(DettaglioFlussoOtticoDao.class);

	public DettaglioTransazioneDao(Connection connection, String schema) {
		super(connection, schema);
	}

	public List<DettaglioTransazione> listMonTransazioni(String codiceSocieta, String codiceUtente, String codiceEnte,
			String tipoUfficio, String codiceUfficio, String numeroDocumento, String numeroBollettinoPagoPA)
			throws DaoException {
		CallableStatement callableStatement = null;
		// inizio LP PG21XX04 Leak
		ResultSet data = null;
		// fine LP PG21XX04 Leak
		List<DettaglioTransazione> list = null;
		try {
			// we prepare callableStatement
			callableStatement = prepareCall(Routines.PYTRASP_MONITORAGGIO_TRANSAZIONI.routine());
			callableStatement.setString(1, codiceUtente.trim());
			callableStatement.setString(2, codiceSocieta.trim());
			callableStatement.setString(3, codiceEnte.trim());
			callableStatement.setString(4, tipoUfficio == null ? "" : tipoUfficio.trim());
			callableStatement.setString(5, codiceUfficio == null ? "" : codiceUfficio.trim());
			callableStatement.setString(6, numeroDocumento.trim());
			callableStatement.setString(7, numeroBollettinoPagoPA.trim());
			list = new ArrayList<DettaglioTransazione>();
			DettaglioTransazione dt = null;
			// we execute callableStatement
			if (callableStatement.execute()) {
				log.debug("DettaglioTransazioneDao: esecuzione chiamata db");
				// inizio LP PG21XX04 Leak
				// ResultSet data = callableStatement.getResultSet();
				data = callableStatement.getResultSet();
				// fine LP PG21XX04 Leak
				if (data.next()) {
					do {
//						list.add(new DettaglioTransazione(data));
						log.debug("La lista ha: " + list.size() + " elementi");
						dt = new DettaglioTransazione();
						dt.setCodiceTransazione(data.getString("TRA_KTRAKTRA"));
						dt.setDataTransazione(getCalendarFromDate(data.getDate("TRA_GTRADTRA")));
						dt.setDataPagamento(getCalendarFromDate(data.getDate("TRA_GTRADPAG")));
						dt.setCodiceAutorizzazioneBanca(data.getString("TRA_CTRAAUBA"));
						dt.setCodiceIdentificativoBancaIUR(data.getString("TRA_CTRAIDBA"));
						dt.setCodiceIUV(data.getString("CODICEIUV"));
						dt.setImportoTotaleTransazione(data.getBigDecimal("TRA_ITRAITOT"));
						dt.setTipologiaBollettino(data.getString("TDT_TBOLTBOL"));
						dt.setTipologiaServizio(data.getString("TDT_CTSECTSE"));
						dt.setNumeroBollettinoPagoPA(data.getString("TDT_CTDTCBOL"));
						dt.setImportoBollettino(data.getBigDecimal("TDT_ITDTTOTA"));
						dt.setNumeroContoCorrente(data.getString("TDT_NTDTNCCP"));
						dt.setIntestatarioContoCorrente(data.getString("TDT_DTDTDINT"));
						dt.setNumeroDocumento(data.getString("TDT_CTDTNDOC"));
						dt.setCodiceFiscale(data.getString("TDT_CTDTCFIS"));
						dt.setDenominazione(data.getString("DENOM"));
						dt.setFlagRendicontazione(data.getString("TRA_CTRAREND"));
						dt.setFlagRiconciliazione(data.getString("TRA_CTRAQUAD"));
						list.add(dt);
					} while (data.next());
				}
				log.debug("La lista ha: " + list.size() + " elementi");
				return list;
			}
		} catch (SQLException x) {
			throw new DaoException(x);
		} catch (IllegalArgumentException x) {
			throw new DaoException(x);
		} catch (HelperException x) {
			throw new DaoException(x);
		} finally {
			// inizio LP PG21XX04 Leak
			// closeCallableStatement(callableStatement);
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
			// fine LP PG21XX04 Leak
		}
		return list;
	}

	public static Calendar getCalendarFromDate(java.util.Date date) {
		if (date == null)
			return null;
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		return cal;
	}

	public List<DettaglioMonTransazione> listMonTransazioni(String codiceUtente, String codiceSocieta,
			String codiceEnte, String tipoUfficio, String codiceUfficio, LocalDate dataDA, LocalDate dataA,
			String numeroAvvisoPagoPA, String identificativoUnivocoVersamento, String identificativoUnivocoRiscossione,
			String identificativoFlussoRendicontazioneNodo) throws DaoException {

		List<DettaglioMonTransazione> list = new ArrayList<DettaglioMonTransazione>();
		//inizio LP PG21XX05 Adeguamento a 1.6 
		CallableStatement callableStatement = null;
		ResultSet data = null;
		//fine LP PG21XX04 Leak Adeguamento a 1.6
		try {
			//inizio LP PG21XX05 Adeguamento a 1.6 
			//try (CallableStatement callableStatement = prepareCall(Routines.PYTRASP_MONITORAGGIO_TRANSAZIONI_V2.routine())) {
			callableStatement = prepareCall(Routines.PYTRASP_MONITORAGGIO_TRANSAZIONI_V2.routine());
			//fine LP PG21XX05 Adeguamento a 1.6
				
				log.debug(String.format("codiceUtente: %s, codiceSocieta: %s, codiceEnte: %s, tipoUfficio: %s, "
						+ "codiceUfficio: %s, "
						+ "dataDA: %s, "
						+ "dataA: %s, "
						+ "numeroAvvisoPagoPA: %s, identificativoUnivocoVersamento: %s, "
						+ "identificativoUnivocoRiscossione: %s, identificativoFlussoRendicontazioneNodo: %s",
						codiceUtente, codiceSocieta, codiceEnte, tipoUfficio,
						codiceUfficio,
						dataDA == null ? "null" : dataDA.format(DateTimeFormatter.ofPattern("uuuu-MM-dd")),
						dataA == null ? "null" : dataA.format(DateTimeFormatter.ofPattern("uuuu-MM-dd")),
						numeroAvvisoPagoPA, identificativoUnivocoVersamento,
						identificativoUnivocoRiscossione, identificativoFlussoRendicontazioneNodo));
				
				callableStatement.setString(1, codiceUtente.trim());
				callableStatement.setString(2, codiceSocieta.trim());
				callableStatement.setString(3, codiceEnte.trim());
				callableStatement.setString(4, tipoUfficio == null ? "" : tipoUfficio.trim());
				callableStatement.setString(5, codiceUfficio == null ? "" : codiceUfficio.trim());
				callableStatement.setTimestamp(6, dataDA == null ? null : Timestamp.valueOf(dataDA.atStartOfDay()));
				callableStatement.setTimestamp(7, dataA == null ? null : Timestamp.valueOf(dataA.atStartOfDay()));
				callableStatement.setString(8, numeroAvvisoPagoPA == null ? "" : numeroAvvisoPagoPA.trim());
				callableStatement.setString(9, identificativoUnivocoVersamento == null ? "" : identificativoUnivocoVersamento.trim());
				callableStatement.setString(10, identificativoUnivocoRiscossione == null ? "" : identificativoUnivocoRiscossione.trim());
				callableStatement.setString(11, identificativoFlussoRendicontazioneNodo == null ? "" : identificativoFlussoRendicontazioneNodo.trim());
				
				if (callableStatement.execute()) {
					log.debug("DettaglioTransazioneDao: esecuzione chiamata db");
					
					//inizio LP PG21XX05 Adeguamento a 1.6 
					//try (ResultSet data = callableStatement.getResultSet()) {
						data = callableStatement.getResultSet();
					//fine LP PG21XX05 Adeguamento a 1.6
						
						while(data.next()) {
							list.add(DettaglioMonTransazione.getDettaglioMonTransazione(data));
						}
					//inizio LP PG21XX05 Adeguamento a 1.6 
					//}
					//fine LP PG21XX05 Adeguamento a 1.6
					log.debug("La lista ha: " + list.size() + " elementi");
				}
			//inizio LP PG21XX05 Adeguamento a 1.6 
			//}
			//fine LP PG21XX05 Adeguamento a 1.6
		} catch (SQLException x) {
			throw new DaoException(x);
		} catch (IllegalArgumentException x) {
			throw new DaoException(x);
		} catch (HelperException x) {
			throw new DaoException(x);
		}
		//inizio LP PG21XX05 Adeguamento a 1.6
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
		//fine LP PG21XX05 Adeguamento a 1.6
		return list;
	}

	// inizio LP PG21XX04 Leak
	// private void closeCallableStatement(CallableStatement callableStatement)
	// {
	// if (callableStatement != null)
	// DAOHelper.closeIgnoringException(callableStatement);
	// }
	// fine LP PG21XX04 Leak

}
