package com.esed.log.req;

import java.sql.Connection;
import java.sql.SQLException;

import com.esed.log.req.config.ConfigLogRequest;
import com.esed.log.req.dati.CollectionDto;
import com.esed.log.req.dati.LogWin;
import com.seda.commons.logger.CustomLoggerManager;
import com.seda.commons.logger.LoggerWrapper;
import com.seda.payer.core.dao.LogWinDao;
import com.seda.payer.core.exception.DaoException;

public class ExecLogWin {
	
	private static final LoggerWrapper logger =  CustomLoggerManager.get(ExecLogWin.class);	
	public static Integer saveRequest(LogWin in) throws LogRequestException {

		if(in == null) {
			logger.debug("logWin == null");
			return new Integer(-1);
		}
		logger.debug("logWin: " + in.toString());
		Connection conn = null;
		try {
			String dbSchemaCodSocieta = in.getDbSchemaCodSocieta();
			
//			String dataSourceName = ConfigLogRequest.getDataSourceName(dbSchemaCodSocieta);
			String schemaName = ConfigLogRequest.getSchemaName(dbSchemaCodSocieta);
			conn = ConfigLogRequest.getConnection(dbSchemaCodSocieta, true);
			
			if(conn != null) {
//				logger.info("dopo di connection OK");
				LogWinDao logWinDao = new LogWinDao(conn, schemaName);
				com.seda.payer.core.bean.LogWin bean = new com.seda.payer.core.bean.LogWin();
//				bean.setIdLog(in.getIdLog());
				bean.setTipoChiamata(in.getTipoChiamata());
				bean.setCodiceUtente(in.getCodiceUtente());
				bean.setCodiceSocieta(in.getCodiceSocieta());
				bean.setCodiceEnte(in.getCodiceEnte());
				bean.setBollettino(in.getBollettino());
				bean.setCodiceFiscale(in.getCodiceFiscale());
				bean.setIdDominio(in.getIdDominio());
				bean.setDataInizioChiamata(in.getDataInizioChiamata());
				bean.setDataFineChiamata(in.getDataFineChiamata());
				bean.setXmlRequest(in.getXmlRequest());
				bean.setXmlResponse(in.getXmlResponse());
				bean.setEsito(in.getEsito());
				bean.setMessaggioErrore(in.getMessaggioErrore());
//				bean.setDataInserimento(in.getDataInserimento());
				bean.setOperatoreInserimento(in.getOperatoreInserimento());
				
				logWinDao.doInsert(bean);
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

	public static CollectionDto searchRequest(LogWin logWin) throws LogRequestException
	{
		if(logWin == null) {
			logger.debug("logWin == null");
			throw new LogRequestException("Errore in search request logWin == null");
		}
		logger.debug("logWin: " + logWin.toString());
		Connection conn = null;
		CollectionDto collectionDto = null;
		try {
			String dbSchemaCodSocieta = logWin.getDbSchemaCodSocieta();
//			String dataSourceName = ConfigLogRequest.getDataSourceName(dbSchemaCodSocieta);
			String schemaName = ConfigLogRequest.getSchemaName(dbSchemaCodSocieta);
			conn = ConfigLogRequest.getConnection(dbSchemaCodSocieta, true);
			if(conn != null) {
//				logger.info("dopo di connection OK");
				int rowsPerPage = logWin.getRowsPerPage();
				int pageNumber = logWin.getPageNumber();
				String order = logWin.getOrder();
				LogWinDao logWinDao = new LogWinDao(conn, schemaName);
				com.seda.payer.core.bean.LogWin bean = new com.seda.payer.core.bean.LogWin();

				bean.setTipoChiamata(logWin.getTipoChiamata());
				bean.setCodiceUtente(logWin.getCodiceUtente());
				bean.setCodiceSocieta(logWin.getCodiceSocieta());
				bean.setCodiceEnte(logWin.getCodiceEnte());
				bean.setBollettino(logWin.getBollettino());
				bean.setCodiceFiscale(logWin.getCodiceFiscale());
				bean.setIdDominio(logWin.getIdDominio());
				bean.setDataInizioChiamata(logWin.getDataInizioChiamata());
				bean.setDataFineChiamata(logWin.getDataFineChiamata());
				bean.setXmlRequest(logWin.getXmlRequest());
				bean.setXmlResponse(logWin.getXmlResponse());
				bean.setEsito(logWin.getEsito());
				bean.setMessaggioErrore(logWin.getMessaggioErrore());
				bean.setDataInserimento(logWin.getDataInserimento());
				bean.setOperatoreInserimento(logWin.getOperatoreInserimento());

				if(!ConfigLogRequest.getAmbienteSviluppo()) {
					bean.setDbSchemaCodSocieta(dbSchemaCodSocieta);
				}

				
//				logger.info("prima di doWebRowSets");
	    		if(rowsPerPage == 0 && pageNumber == 0) {
	    			logWinDao.doWebRowSets(bean, 0, 0, order);
	    		} else {
	    			logWinDao.doWebRowSets(bean, rowsPerPage <= 0 ? ConfigLogRequest.getDefaultListRows(dbSchemaCodSocieta) : rowsPerPage, pageNumber, order);
	    		}
//				logger.info("dopo di doWebRowSets");
	    		collectionDto = new CollectionDto(logWinDao.getWebRowSetXml(LogWinDao.IDX_DOLIST_LISTA), logWinDao.getPageInfo());
//				logger.info("dopo di new CollectionDto");
			} else {
				logger.error("dopo di connection KO");
			}
//			logger.debug("Fine search");
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

	public static LogWin getRequest(LogWin logWin) throws LogRequestException
	{
		logger.debug("--------------- INIZIO GET REQUEST -----------------");
		if(logWin == null) {
			logger.debug("logWin == null");
			throw new LogRequestException("Errore in get request logWin == null");
		}
		logger.debug("LogWin: " + logWin.toString());
		Connection conn = null;
		try {
			String dbSchemaCodSocieta = logWin.getDbSchemaCodSocieta();
			String schemaName = ConfigLogRequest.getSchemaName(dbSchemaCodSocieta);
			conn = ConfigLogRequest.getConnection(dbSchemaCodSocieta, true);
			if(conn != null) {
				LogWinDao logWinDao = new LogWinDao(conn, schemaName);
				String tagSuffissoTabella = logWin.getTagSuffissoTabella();
				//chiamata ritorna una logWin
				com.seda.payer.core.bean.LogWin bean = logWinDao.doDetail(logWin.getIdLog(), tagSuffissoTabella);
	    		logWin = new LogWin();

	    		logWin.setIdLog(bean.getIdLog());
	    		logWin.setTipoChiamata(bean.getTipoChiamata());
	    		logWin.setCodiceUtente(bean.getCodiceUtente());
	    		logWin.setCodiceSocieta(bean.getCodiceSocieta());
	    		logWin.setCodiceEnte(bean.getCodiceEnte());
				logWin.setBollettino(bean.getBollettino());
				logWin.setCodiceFiscale(bean.getCodiceFiscale());
				logWin.setIdDominio(bean.getIdDominio());
				logWin.setDataInizioChiamata(bean.getDataInizioChiamata());
				logWin.setDataFineChiamata(bean.getDataFineChiamata());
				logWin.setXmlRequest(bean.getXmlRequest());
				logWin.setXmlResponse(bean.getXmlResponse());
				logWin.setEsito(bean.getEsito());
				logWin.setMessaggioErrore(bean.getMessaggioErrore());
				logWin.setDataInserimento(bean.getDataInserimento());
				logWin.setOperatoreInserimento(bean.getOperatoreInserimento());
				logWin.setTagSuffissoTabella(tagSuffissoTabella == null ? "" : tagSuffissoTabella);
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
		return logWin;
	}

}
