package com.esed.log.req;

import java.sql.Connection;
import java.sql.SQLException;

import com.esed.log.req.config.ConfigLogRequest;
import com.esed.log.req.dati.CollectionDto;
import com.esed.log.req.dati.LogRequest;
import com.seda.commons.logger.CustomLoggerManager;
import com.seda.commons.logger.LoggerWrapper;
import com.seda.payer.core.dao.LogRequestDao;
import com.seda.payer.core.exception.DaoException;

public class ExecRequest {
	
	private static final LoggerWrapper logger =  CustomLoggerManager.get(ExecRequest.class);
	
	public static Integer saveRequest(LogRequest logRequest) throws LogRequestException {

		//LP PG22XX02 - 20220223 logger.debug("--------------- INIZIO SAVE REQUEST -----------------");
		if(logRequest == null) {
			//LP PG22XX02 - 20220223 logger.debug("logRequest == null");
			return new Integer(-1);
		}
		//LP PG22XX02 - 20220223 logger.debug("LogRequest: " + logRequest.toString());
		Connection conn = null;
		try {
			String dbSchemaCodSocieta = logRequest.getDbSchemaCodSocieta();
			//LP PG22XX02 - 20220223 logger.info("prima di dataSourceName");
			String dataSourceName = ConfigLogRequest.getDataSourceName(dbSchemaCodSocieta);
			//LP PG22XX02 - 20220223 logger.info("dataSourceName: " + dataSourceName);
			//LP PG22XX02 - 20220223 logger.info("prima di schemaName");
			String schemaName = ConfigLogRequest.getSchemaName(dbSchemaCodSocieta);
			//LP PG22XX02 - 20220223 logger.info("schemaName: " + schemaName);
			//LP PG22XX02 - 20220223 logger.info("prima di connection");
			//inizio LP PG22XX02
			//Nota. verifica presenza definizione dataSourceName e schemaName
			if(dataSourceName == null
			   || dataSourceName.trim().length() == 0
			   || schemaName == null
			   || schemaName.trim().length() == 0			   
			) {
				return new Integer(-1);
			}
			//fine LP PG22XX02
			conn = ConfigLogRequest.getConnection(dbSchemaCodSocieta, true);
			if(conn != null) {
				//LP PG22XX02 - 20220223 logger.info("dopo di connection OK");
				LogRequestDao logRequestDao = new LogRequestDao(conn, schemaName);
				com.seda.payer.core.bean.LogRequest bean = new com.seda.payer.core.bean.LogRequest();
				bean.setAction(logRequest.getAction());
				bean.setApplicativo(logRequest.getApplicativo());
				bean.setCanalePagamento(logRequest.getCanalePagamento());
				bean.setChiaveEnte(logRequest.getChiaveEnte());
				bean.setDescrizioneEnte(logRequest.getDescrizioneEnte());
				bean.setSiglaProvinciaEnte(logRequest.getSiglaProvinciaEnte());
				bean.setCodiceFiscale(logRequest.getCodiceFiscale());
				bean.setCodiceSocieta(logRequest.getCodiceSocieta());
				bean.setCodiceUtente(logRequest.getCodiceUtente());
				bean.setDbSchemaCodSocieta(dbSchemaCodSocieta);
				bean.setIndirizzoIP(logRequest.getIndirizzoIP());
				bean.setNumeroBollettino(logRequest.getNumeroBollettino());
				bean.setNumeroDocumento(logRequest.getNumeroDocumento());
				bean.setOperatoreRequest(logRequest.getOperatoreRequest());
				bean.setParam(logRequest.getParam());
				bean.setQueryString(logRequest.getQueryString());
				bean.setRequest(logRequest.getRequest());
				bean.setSessionID(logRequest.getSessionID());
				bean.setSezioneApplicativa(logRequest.getSezioneApplicativa());
				bean.setTemplate(logRequest.getTemplate());
				bean.setTipoRequest(logRequest.getTipoRequest());
				bean.setUserName(logRequest.getUserName());
				bean.setUserProfile(logRequest.getUserProfile());
				bean.setBelfioreRequest(logRequest.getBelfioreRequest());
				bean.setComuneRequest(logRequest.getComuneRequest());
				bean.setSiglaProvinciaRequest(logRequest.getSiglaProvinciaRequest());
				bean.setChiaveTransazione(logRequest.getChiaveTransazione());
				bean.setNumeroIUV(logRequest.getNumeroIUV());
				//LP PG22XX02 - 20220223 logger.info("prima di doInsert");
				logRequestDao.doInsert(bean);
				//LP PG22XX02 - 20220223 logger.info("dopo di doInsert");
			} else {
				//inizio LP PG22XX02
				//logger.error("dopo di connection KO");
				return new Integer(-1);
				//fine LP PG22XX02
			}
			//LP PG22XX02 - 20220223 logger.info(logRequest.toString());
			//LP PG22XX02 - 20220223 logger.debug("Fine save");
		} catch (DaoException e) {
			e.printStackTrace();
			logger.error("--------------- ERRORE SAVE REQUEST DaoExecption -----------------");
			logger.error(e.getMessage());
			throw new LogRequestException("Errore in save request DaoExecption: " + e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("--------------- ERRORE SAVE REQUEST -----------------");
			logger.error(e.getMessage());
			throw new LogRequestException("Errore in save request: " + e.getMessage());
		} finally {
			if(conn != null) {
				try {
					//LP PG22XX02 - 20220223 logger.info("prima di connection close");
					conn.close();
					//LP PG22XX02 - 20220223 logger.info("dopo di connection close");
				} catch (SQLException e) {
					logger.info("--------------- ERRORE SAVE REQUEST SQLExecption -----------------");
					logger.info(e.getMessage());
				}
			}
		}
		//LP PG22XX02 - 20220223 logger.debug("--------------- FINE SAVE REQUEST -----------------");
		return new Integer(1);
	}

	public static CollectionDto searchRequest(LogRequest logRequest) throws LogRequestException
	{
		//LP PG22XX02 - 20220223 logger.debug("--------------- INIZIO SEARCH REQUEST -----------------");
		if(logRequest == null) {
			//LP PG22XX02 - 20220223 logger.debug("logRequest == null");
			throw new LogRequestException("Errore in search request logRequest == null");
		}
		//LP PG22XX02 - 20220223 logger.debug("inizio search");
		//LP PG22XX02 - 20220223 logger.debug("LogRequest: " + logRequest.toString());
		Connection conn = null;
		CollectionDto collectionDto = null;
		try {
			String dbSchemaCodSocieta = logRequest.getDbSchemaCodSocieta();
			//LP PG22XX02 - 20220223 logger.info("prima di dataSourceName");
			String dataSourceName = ConfigLogRequest.getDataSourceName(dbSchemaCodSocieta);
			//LP PG22XX02 - 20220223 logger.info("dataSourceName: " + dataSourceName);
			//LP PG22XX02 - 20220223 logger.info("prima di schemaName");
			String schemaName = ConfigLogRequest.getSchemaName(dbSchemaCodSocieta);
			//LP PG22XX02 - 20220223 logger.info("schemaName: " + schemaName);
			//LP PG22XX02 - 20220223 logger.info("prima di connection");
			//inizio LP PG22XX02
			//Nota. verifica presenza definizione dataSourceName e schemaName
			if(dataSourceName == null
			   || dataSourceName.trim().length() == 0
			   || schemaName == null
			   || schemaName.trim().length() == 0			   
			) {
				throw new LogRequestException("Configurazione dati non valorizzati");
			}
			//fine LP PG22XX02
			conn = ConfigLogRequest.getConnection(dbSchemaCodSocieta, true);
			if(conn != null) {
				//LP PG22XX02 - 20220223 logger.info("dopo di connection OK");
				int rowsPerPage = logRequest.getRowsPerPage();
				int pageNumber = logRequest.getPageNumber();
				String order = logRequest.getOrder();
				LogRequestDao logRequestDao = new LogRequestDao(conn, schemaName);
				com.seda.payer.core.bean.LogRequest bean = new com.seda.payer.core.bean.LogRequest();
				bean.setAction(logRequest.getAction());
				bean.setApplicativo(logRequest.getApplicativo());
				bean.setDataInizio_da(logRequest.getDataInizio_da());
				bean.setDataFine_a(logRequest.getDataFine_a());
				bean.setCanalePagamento(logRequest.getCanalePagamento());
				bean.setChiaveEnte(logRequest.getChiaveEnte());
				bean.setDescrizioneEnte(logRequest.getDescrizioneEnte());
				bean.setSiglaProvinciaEnte(logRequest.getSiglaProvinciaEnte());
				bean.setCodiceFiscale(logRequest.getCodiceFiscale());
				bean.setCodiceSocieta(logRequest.getCodiceSocieta());
				bean.setCodiceUtente(logRequest.getCodiceUtente());
				if(!ConfigLogRequest.getAmbienteSviluppo()) {
					bean.setDbSchemaCodSocieta(dbSchemaCodSocieta);
				}
				bean.setIdRequest(logRequest.getIdRequest());
				bean.setIndirizzoIP(logRequest.getIndirizzoIP());
				bean.setNumeroBollettino(logRequest.getNumeroBollettino());
				bean.setNumeroDocumento(logRequest.getNumeroDocumento());
				bean.setOperatoreRequest(logRequest.getOperatoreRequest());
				bean.setParam(logRequest.getParam());
				bean.setQueryString(logRequest.getQueryString());
				//bean.setRequest(logRequest.getRequest());
				bean.setSessionID(logRequest.getSessionID());
				bean.setSezioneApplicativa(logRequest.getSezioneApplicativa());
				bean.setTemplate(logRequest.getTemplate());
				bean.setTipoRequest(logRequest.getTipoRequest());
				bean.setUserName(logRequest.getUserName());
				bean.setUserProfile(logRequest.getUserProfile());
				bean.setBelfioreRequest(logRequest.getBelfioreRequest());
				bean.setComuneRequest(logRequest.getComuneRequest());
				bean.setSiglaProvinciaRequest(logRequest.getSiglaProvinciaRequest());
				bean.setChiaveTransazione(logRequest.getChiaveTransazione());
				bean.setNumeroIUV(logRequest.getNumeroIUV());

				bean.setError(logRequest.getError());
				
				//LP PG22XX02 - 20220223 logger.info("prima di doWebRowSets");
	    		if(rowsPerPage == 0 && pageNumber == 0) {
	    			logRequestDao.doWebRowSets(bean, 0, 0, order);
	    		} else {
	    			logRequestDao.doWebRowSets(bean, rowsPerPage <= 0 ? ConfigLogRequest.getDefaultListRows(dbSchemaCodSocieta) : rowsPerPage, pageNumber, order);
	    		}
	    		//LP PG22XX02 - 20220223 logger.info("dopo di doWebRowSets");
	    		collectionDto = new CollectionDto(logRequestDao.getWebRowSetXml(LogRequestDao.IDX_DOLIST_LISTA), logRequestDao.getPageInfo());
	    		//LP PG22XX02 - 20220223 logger.info("dopo di new CollectionDto");
			} else {
				//inizio LP PG22XX02
				//logger.error("dopo di connection KO");
				throw new LogRequestException("Connessione non riuscita");
				//fine LP PG22XX02
			}
			//LP PG22XX02 - 20220223 logger.debug("Fine search");
		} catch (DaoException e) {
			e.printStackTrace();
			logger.error("--------------- ERRORE SEARCH REQUEST DaoExecption -----------------");
			logger.error(e.getMessage());
			throw new LogRequestException("Errore in search request DaoExecption: " + e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("--------------- ERRORE SEARCH REQUEST -----------------");
			logger.error(e.getMessage());
			throw new LogRequestException("Errore in search request: " + e.getMessage());
		} finally {
			if(conn != null) {
				try {
					//LP PG22XX02 - 20220223 logger.info("prima di connection close");
					conn.close();
					//LP PG22XX02 - 20220223 logger.info("dopo di connection close");
				} catch (SQLException e) {
					logger.info("--------------- ERRORE SEARCH REQUEST SQLExecption -----------------");
					logger.info(e.getMessage());
				}
			}
		}
		//LP PG22XX02 - 20220223 logger.debug("--------------- FINE SEARCH REQUEST -----------------");
		return collectionDto;
	}

	public static LogRequest getRequest(LogRequest logRequest) throws LogRequestException
	{
		//LP PG22XX02 - 20220223 logger.debug("--------------- INIZIO GET REQUEST -----------------");
		if(logRequest == null) {
			//LP PG22XX02 - 20220223 logger.debug("logRequest == null");
			throw new LogRequestException("Errore in get request logRequest == null");
		}
		//LP PG22XX02 - 20220223 logger.debug("inizio get");
		//LP PG22XX02 - 20220223 logger.debug("LogRequest: " + logRequest.toString());
		Connection conn = null;
		try {
			String dbSchemaCodSocieta = logRequest.getDbSchemaCodSocieta();
			String schemaName = ConfigLogRequest.getSchemaName(dbSchemaCodSocieta);
			//inizio LP PG22XX02
			String dataSourceName = ConfigLogRequest.getDataSourceName(dbSchemaCodSocieta);
			//Nota. verifica presenza definizione dataSourceName e schemaName
			if(dataSourceName == null
			   || dataSourceName.trim().length() == 0
			   || schemaName == null
			   || schemaName.trim().length() == 0			   
			) {
				throw new LogRequestException("Configurazione dati non valorizzati");
			}
			//fine LP PG22XX02
			conn = ConfigLogRequest.getConnection(dbSchemaCodSocieta, true);
			if(conn != null) {
				LogRequestDao logRequestDao = new LogRequestDao(conn, schemaName);
				String tagSuffissoTabella = logRequest.getTagSuffissoTabella();
				//chiamata ritorna una logRequest
				com.seda.payer.core.bean.LogRequest bean = logRequestDao.doDetail(logRequest.getIdRequest(), tagSuffissoTabella);
	    		logRequest = new LogRequest();
				logRequest.setAction(bean.getAction());
				logRequest.setApplicativo(bean.getApplicativo());
				logRequest.setDataRequest(bean.getDataRequest());
				logRequest.setCanalePagamento(bean.getCanalePagamento());
				logRequest.setChiaveEnte(bean.getChiaveEnte());
				logRequest.setDescrizioneEnte(bean.getDescrizioneEnte());
				logRequest.setSiglaProvinciaEnte(bean.getSiglaProvinciaEnte());
				logRequest.setCodiceFiscale(bean.getCodiceFiscale());
				logRequest.setCodiceSocieta(bean.getCodiceSocieta());
				logRequest.setCodiceUtente(bean.getCodiceUtente());
				logRequest.setDbSchemaCodSocieta(dbSchemaCodSocieta);
				logRequest.setIdRequest(bean.getIdRequest());
				logRequest.setIndirizzoIP(bean.getIndirizzoIP());
				logRequest.setNumeroBollettino(bean.getNumeroBollettino());
				logRequest.setNumeroDocumento(bean.getNumeroDocumento());
				logRequest.setOperatoreRequest(bean.getOperatoreRequest());
				logRequest.setParam(bean.getParam());
				logRequest.setQueryString(bean.getQueryString());
				logRequest.setRequest(bean.getRequest());
				logRequest.setSessionID(bean.getSessionID());
				logRequest.setSezioneApplicativa(bean.getSezioneApplicativa());
				logRequest.setTemplate(bean.getTemplate());
				logRequest.setTipoRequest(bean.getTipoRequest());
				logRequest.setUserName(bean.getUserName());
				logRequest.setUserProfile(bean.getUserProfile());
				logRequest.setBelfioreRequest(bean.getBelfioreRequest());
				logRequest.setComuneRequest(bean.getComuneRequest());
				logRequest.setSiglaProvinciaRequest(bean.getSiglaProvinciaRequest());
				logRequest.setChiaveTransazione(bean.getChiaveTransazione());
				logRequest.setNumeroIUV(bean.getNumeroIUV());
				logRequest.setError(bean.getError());
				logRequest.setTagSuffissoTabella(tagSuffissoTabella == null ? "" : tagSuffissoTabella);
			} else {
				//inizio LP PG22XX02
				//logger.error("dopo di connection KO");
				throw new LogRequestException("Connessione non riuscita");
				//fine LP PG22XX02
			}
		} catch (DaoException e) {
			e.printStackTrace();
			logger.error("--- ERRORE GET REQUEST DaoExecption ---");
			logger.error(e.getMessage());
			throw new LogRequestException("Errore in get request DaoExecption: " + e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("--- ERRORE GET REQUEST ---");
			logger.error(e.getMessage());
			throw new LogRequestException("Errore in get request: " + e.getMessage());
		} finally {
			if(conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					logger.error("--- ERRORE GET REQUEST SQLExecption ---");
					logger.error(e.getMessage());
				}
			}
		}
		return logRequest;
	}

	public static Integer updateExtRequest(LogRequest logRequest) throws LogRequestException {
		if(logRequest == null) {
			return new Integer(-1);
		}
		Connection conn = null;
		try {
			String dbSchemaCodSocieta = logRequest.getDbSchemaCodSocieta();
//			String dataSourceName = ConfigLogRequest.getDataSourceName(dbSchemaCodSocieta);
			String schemaName = ConfigLogRequest.getSchemaName(dbSchemaCodSocieta);
			//inizio LP PG22XX02
			String dataSourceName = ConfigLogRequest.getDataSourceName(dbSchemaCodSocieta);
			//Nota. verifica presenza definizione dataSourceName e schemaName
			if(dataSourceName == null
			   || dataSourceName.trim().length() == 0
			   || schemaName == null
			   || schemaName.trim().length() == 0			   
			) {
				//Configurazione dati connessione non valorizzati
				return new Integer(-1);
			}
			//fine LP PG22XX02
			conn = ConfigLogRequest.getConnection(dbSchemaCodSocieta, true);
			if(conn != null) {
				LogRequestDao logRequestDao = new LogRequestDao(conn, schemaName);
				com.seda.payer.core.bean.LogRequest bean = new com.seda.payer.core.bean.LogRequest();
				bean.setAction(logRequest.getAction());
				bean.setApplicativo(logRequest.getApplicativo());
				bean.setCanalePagamento(logRequest.getCanalePagamento());
				bean.setChiaveEnte(logRequest.getChiaveEnte());
				bean.setDescrizioneEnte(logRequest.getDescrizioneEnte());
				bean.setSiglaProvinciaEnte(logRequest.getSiglaProvinciaEnte());
				bean.setCodiceFiscale(logRequest.getCodiceFiscale());
				bean.setCodiceSocieta(logRequest.getCodiceSocieta());
				bean.setCodiceUtente(logRequest.getCodiceUtente());
				bean.setDbSchemaCodSocieta(dbSchemaCodSocieta);
				bean.setIndirizzoIP(logRequest.getIndirizzoIP());
				bean.setNumeroBollettino(logRequest.getNumeroBollettino());
				bean.setNumeroDocumento(logRequest.getNumeroDocumento());
				bean.setOperatoreRequest(logRequest.getOperatoreRequest());
				bean.setParam(logRequest.getParam());
				bean.setError(logRequest.getError());
				bean.setQueryString(logRequest.getQueryString());
				bean.setRequest(logRequest.getRequest());
				bean.setSessionID(logRequest.getSessionID());
				bean.setSezioneApplicativa(logRequest.getSezioneApplicativa());
				bean.setTemplate(logRequest.getTemplate());
				bean.setTipoRequest(logRequest.getTipoRequest());
				bean.setUserName(logRequest.getUserName());
				bean.setUserProfile(logRequest.getUserProfile());
				bean.setBelfioreRequest(logRequest.getBelfioreRequest());
				bean.setComuneRequest(logRequest.getComuneRequest());
				bean.setSiglaProvinciaRequest(logRequest.getSiglaProvinciaRequest());
				bean.setChiaveTransazione(logRequest.getChiaveTransazione());
				bean.setNumeroIUV(logRequest.getNumeroIUV());
				logRequestDao.doUpdateExt(bean);
			} else {
				//inizio LP PG22XX02
				//logger.error("dopo di connection KO");
				return new Integer(-1);
				//fine LP PG22XX02
			}
		} catch (DaoException e) {
			e.printStackTrace();
			logger.error("--------------- ERRORE UPDATE ext REQUEST DaoExecption -----------------");
			logger.error(e.getMessage());
			throw new LogRequestException("Errore in update ext request DaoExecption: " + e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("--------------- ERRORE UPDATE ext REQUEST -----------------");
			logger.error(e.getMessage());
			throw new LogRequestException("Errore in update ext request: " + e.getMessage());
		} finally {
			if(conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					logger.error(e.getMessage());
				}
			}
		}
		return new Integer(1);
	}
}
