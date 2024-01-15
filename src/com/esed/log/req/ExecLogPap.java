package com.esed.log.req;

import java.sql.Connection;
import java.sql.SQLException;

import com.esed.log.req.config.ConfigLogRequest;
import com.esed.log.req.dati.CollectionDto;
import com.esed.log.req.dati.LogPap;
import com.seda.commons.logger.CustomLoggerManager;
import com.seda.commons.logger.LoggerWrapper;
import com.seda.data.spi.PageInfo;
import com.seda.payer.core.dao.LogPapDao;
import com.seda.payer.core.exception.DaoException;

public class ExecLogPap {
	
	private static final LoggerWrapper logger =  CustomLoggerManager.get(ExecLogPap.class);	
	public static Integer saveRequest(LogPap in) throws LogRequestException {

		if(in == null) {
			logger.debug("logPap == null");
			return new Integer(-1);
		}
		logger.debug("logPap: " + in.toString());
		Connection conn = null;
		try {
			String dbSchemaCodSocieta = in.getDbSchemaCodSocieta();
			String schemaName = ConfigLogRequest.getSchemaName(dbSchemaCodSocieta);
			conn = ConfigLogRequest.getConnection(dbSchemaCodSocieta, true);
			
			if(conn != null) {
				LogPapDao logPapDao = new LogPapDao(conn, schemaName);
				com.seda.payer.core.bean.LogPap bean = new com.seda.payer.core.bean.LogPap();
				bean.setCodiceUtente(in.getCodiceUtente());
				bean.setIdDominio(in.getIdDominio());
				bean.setIuv(in.getIuv());
				bean.setDataInizioChiamata(in.getDataInizioChiamata());
				bean.setDataFineChiamata(in.getDataFineChiamata());
				bean.setXmlRequest(in.getXmlRequest());
				bean.setXmlResponse(in.getXmlResponse());
				bean.setEsito(in.getEsito());
				bean.setErrore(in.getErrore());
				bean.setOperazione(in.getOperazione());
				
				logPapDao.doInsert(bean);
			} else {
				logger.error("dopo di connection KO");
			}
			logger.info(in.toString());
		} catch (DaoException e) {
			e.printStackTrace();
			logger.error("--- ERRORE SAVE REQUEST DaoExecption ---");
			logger.error(e.getMessage());
			throw new LogRequestException("Errore in save request DaoExecption: " + e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("--- ERRORE SAVE REQUEST ---");
			logger.error(e.getMessage());
			throw new LogRequestException("Errore in save request: " + e.getMessage());
		} finally {
			if(conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					logger.info("--- ERRORE SAVE REQUEST SQLExecption ---");
					logger.info(e.getMessage());
				}
			}
		}
		return new Integer(1);
	}

	public static CollectionDto searchRequest(LogPap logPap) throws LogRequestException
	{
		if(logPap == null) {
			logger.debug("logPap == null");
			throw new LogRequestException("Errore in search request logPap == null");
		}
		logger.debug("logPap: " + logPap.toString());
		Connection conn = null;
		CollectionDto collectionDto = null;
		try {
			String dbSchemaCodSocieta = logPap.getDbSchemaCodSocieta();
			String schemaName = ConfigLogRequest.getSchemaName(dbSchemaCodSocieta);
			conn = ConfigLogRequest.getConnection(dbSchemaCodSocieta, true);
			if(conn != null) {
				int rowsPerPage = logPap.getRowsPerPage();
				int pageNumber = logPap.getPageNumber();
				String order = logPap.getOrder();
				LogPapDao logPapDao = new LogPapDao(conn, schemaName);
				com.seda.payer.core.bean.LogPap bean = new com.seda.payer.core.bean.LogPap();
				bean.setCodiceUtente(logPap.getCodiceUtente());
				bean.setIdDominio(logPap.getIdDominio());
				bean.setIuv(logPap.getIuv());
				bean.setDataInizioChiamata(logPap.getDataInizioChiamata());
				bean.setDataFineChiamata(logPap.getDataFineChiamata());
				bean.setXmlRequest(logPap.getXmlRequest());
				bean.setXmlResponse(logPap.getXmlResponse());
				bean.setEsito(logPap.getEsito());
				bean.setErrore(logPap.getErrore());
				bean.setDataInserimento(logPap.getDataInserimento());
				bean.setOperazione(logPap.getOperazione());


				if(!ConfigLogRequest.getAmbienteSviluppo()) {
					bean.setDbSchemaCodSocieta(dbSchemaCodSocieta);
				}

	    		if(rowsPerPage == 0 && pageNumber == 0) {
	    			logPapDao.doWebRowSets(bean, 0, 0, order);
	    		} else {
	    			logPapDao.doWebRowSets(bean, rowsPerPage <= 0 ? ConfigLogRequest.getDefaultListRows(dbSchemaCodSocieta) : rowsPerPage, pageNumber, order);
	    		}
	    		collectionDto = new CollectionDto(logPapDao.getWebRowSetXml(LogPapDao.IDX_DOLIST_LISTA), logPapDao.getPageInfo());
			} else {
				logger.error("dopo di connection KO");
			}
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
					conn.close();
				} catch (SQLException e) {
					logger.info("--------------- ERRORE SEARCH REQUEST SQLExecption -----------------");
					logger.info(e.getMessage());
				}
			}
		}
		logger.debug("--------------- FINE SEARCH REQUEST -----------------");
		return collectionDto;
	}

	public static LogPap getRequest(LogPap logPap) throws LogRequestException
	{
		logger.debug("--------------- INIZIO GET REQUEST -----------------");
		if(logPap == null) {
			logger.debug("LogPap == null");
			throw new LogRequestException("Errore in get request logPap == null");
		}
		logger.debug("LogPap: " + logPap.toString());
		Connection conn = null;
		try {
			String dbSchemaCodSocieta = logPap.getDbSchemaCodSocieta();
			String schemaName = ConfigLogRequest.getSchemaName(dbSchemaCodSocieta);
			conn = ConfigLogRequest.getConnection(dbSchemaCodSocieta, true);
			if(conn != null) {
				LogPapDao logPapDao = new LogPapDao(conn, schemaName);
				String tagSuffissoTabella = logPap.getTagSuffissoTabella();
				com.seda.payer.core.bean.LogPap bean = logPapDao.doDetail(logPap.getIdLog(), tagSuffissoTabella);
				logPap = new LogPap();

				logPap.setIdLog(bean.getIdLog());
				logPap.setCodiceUtente(bean.getCodiceUtente());
	    		logPap.setIdDominio(bean.getIdDominio());
	    		logPap.setIuv(bean.getIuv());
	    		logPap.setDataInizioChiamata(bean.getDataInizioChiamata());
	    		logPap.setDataFineChiamata(bean.getDataFineChiamata());
	    		logPap.setXmlRequest(bean.getXmlRequest());
	    		logPap.setXmlResponse(bean.getXmlResponse());
	    		logPap.setEsito(bean.getEsito());
	    		logPap.setErrore(bean.getErrore());
	    		logPap.setDataInserimento(bean.getDataInserimento());
	    		logPap.setOperazione(bean.getOperazione());
	    		logPap.setTagSuffissoTabella(tagSuffissoTabella == null ? "" : tagSuffissoTabella);
			} else {
				logger.error("dopo di connection KO");
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
		return logPap;
	}
	


	// YLM PG22XX08 INI
	public static CollectionDto getTipoOperazioni(String dbSchemaCodSocieta) throws LogRequestException
	{
		CollectionDto collectionDto = new CollectionDto();
		Connection conn = null;
		try {
			String schemaName = ConfigLogRequest.getSchemaName(dbSchemaCodSocieta);
			conn = ConfigLogRequest.getConnection(dbSchemaCodSocieta, true);
			if(conn != null) {
				LogPapDao logPapDao = new LogPapDao(conn, schemaName);
				
				logPapDao.getTipoOperazioni();
				
				collectionDto = new CollectionDto(logPapDao.getWebRowSetXml(LogPapDao.IDX_DOLIST_LISTA), new PageInfo());
			} else {
				logger.error("dopo di connection KO");
			}
		} catch (DaoException e) {
			e.printStackTrace();
			logger.error("--- ERRORE getTipoOperazioni PAP DaoExecption ---");
			logger.error(e.getMessage());
			throw new LogRequestException("Errore in getTipoOperazioni PAP DaoExecption: " + e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("--- ERRORE getTipoOperazioni PAP ---");
			logger.error(e.getMessage());
			throw new LogRequestException("Errore in getTipoOperazioni PAP: " + e.getMessage());
		} finally {
			if(conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					logger.error("--- ERRORE getTipoOperazioni PAP SQLExecption ---");
					logger.error(e.getMessage());
				}
			}
		}
		return collectionDto;
	}
	// YLM PG22XX08 FINE

}
