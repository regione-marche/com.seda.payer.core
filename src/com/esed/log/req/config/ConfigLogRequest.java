package com.esed.log.req.config;

import java.sql.Connection;

import com.seda.commons.logger.CustomLoggerManager;
import com.seda.commons.logger.LoggerWrapper;
import com.seda.commons.properties.tree.PropertiesNodeException;
import com.seda.commons.properties.tree.PropertiesTree;
import com.seda.compatibility.SystemVariable;
import com.seda.data.dao.DAOHelper;
import com.seda.j2ee5.jndi.JndiProxy;
import com.seda.j2ee5.jndi.JndiProxyException;

public class ConfigLogRequest {
	
	private static final LoggerWrapper logger =  CustomLoggerManager.get(ConfigLogRequest.class);

	public static PropertiesTree configuration = null;
	public static String sRootPath = "LOGREQUEST_WSROOT";
	public static Boolean bInSviluppo = null;

	public static void initialized() {
		
		if (configuration == null) {
			//LP PG22XX02 - 20220223 logger.info("================================== inizio initialized======================================");
			SystemVariable sv = new SystemVariable();
			String rootPath = sv.getSystemVariableValue(sRootPath);
			sv = null;
			//LP PG22XX02 - 20220223 System.out.println("ConfigLogRequest - Path file di configurazione letto da variabile: " + rootPath);
			if (rootPath != null) {
				try {
					configuration = new PropertiesTree(rootPath);
				} catch (PropertiesNodeException e) {
					logger.error("ConfigLogRequest - Errore in Caricamento configurazioni");
					e.printStackTrace();
				}
				//LP PG22XX02 - 20220223 logger.info("ConfigLogRequest - Caricamento configurazioni");
			}
			//inizio LP PG22XX02
			else
				logger.error("ConfigLogRequest - Errore variabile di sistema '" + sRootPath + "' == null");
			//LP PG22XX02 - 20220223 logger.info("================================== fine   initialized======================================");
		}
	}

	public static String getDataSourceName(String dbSchemaCodSocieta) {
		initialized();
		if(configuration != null) {
			return configuration.getProperty("logRequestWs.datasource/" + dbSchemaCodSocieta +".logRequestWs.name");
		}
		else
			return null;
	}
	
	public static String getSchemaName(String dbSchemaCodSocieta) {
		initialized();
		if(configuration != null) {
			return configuration.getProperty("logRequestWs.datasource/" + dbSchemaCodSocieta +".logRequestWs.schema");
		}
		else
			return null;
	}

	public static int getDefaultListRows(String dbSchemaCodSocieta)
	{
		initialized();
		if(configuration != null) {
			String appo = configuration.getProperty("logRequestWs.datasource/" + dbSchemaCodSocieta +".logRequestWs.defaultListRows");
			if(appo != null)
				return Integer.parseInt(appo);
		}
		return 5;
	}
	
	public static boolean getAmbienteSviluppo()
	{
		if(bInSviluppo == null) {
			initialized();
			if(configuration != null) {
				String appo = configuration.getProperty("logRequestWs.AmbienteSviluppo");
				if(appo != null && appo.equals("S"))
					bInSviluppo = new Boolean(true);
					return true;
			}
			bInSviluppo = new Boolean(false);
		}
		return bInSviluppo.booleanValue();
	}
	/**
	 * @return the connection
	 * @param dbSchemaCodSocieta Codice societa per connessione dinamica al DB
	 * @param autoCommit Autocommit
	 */
	public static Connection getConnection(String dbSchemaCodSocieta, boolean autoCommit) throws JndiProxyException {
		return new JndiProxy().getSqlConnection(null, getDataSourceName(dbSchemaCodSocieta), autoCommit);
	}
	
	/**
	 * @param connection
	 */
	public static void closeConnection(Connection connection) {
		DAOHelper.closeIgnoringException(connection);
	}
}
