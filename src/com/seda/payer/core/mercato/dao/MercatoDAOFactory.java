package com.seda.payer.core.mercato.dao;

import java.lang.reflect.Constructor;
//import java.sql.Connection;
import java.sql.Connection;

import javax.sql.DataSource;
import com.seda.payer.core.exception.DaoException;
import com.seda.payer.core.mercato.dao.ConfigurazioneTipologiaBancoDAO;
import com.seda.payer.core.mercato.dao.ConfigurazioneTipologiaBancoDAOImpl;
import com.seda.payer.core.mercato.dao.ConfigurazioneAreaMercantileDAO;
import com.seda.payer.core.mercato.dao.ConfigurazioneAreaMercantileDAOImpl;
import com.seda.payer.core.mercato.dao.ConfigurazionePrenotazioniDAO;
import com.seda.payer.core.mercato.dao.ConfigurazionePrenotazioniDAOImpl;

public class MercatoDAOFactory {

	private static String MERCATO_CLASS;

	static {
		MERCATO_CLASS=MercatoDAOImpl.class.getName();
	}
	//inizio LP PG21XX04 Leak
	@Deprecated
	//fine LP PG21XX04 Leak
	@SuppressWarnings("unchecked")
	public static MercatoDAO getMercatoDAO(DataSource datasource, String schema) throws DaoException {
		MercatoDAO mercatoDAO = null;
		try {
			Class<MercatoDAO> clazz = (Class<MercatoDAO>)Class.forName(MERCATO_CLASS);
			Constructor<MercatoDAO> constructor = clazz.getConstructor(new Class[] {javax.sql.DataSource.class, java.lang.String.class});
			mercatoDAO = constructor.newInstance(datasource,schema);
		} catch (Exception x) {
			throw new DaoException(x);
		} 
		return mercatoDAO;
	}
	
	//inizio LP PG21XX04 Leak
	@SuppressWarnings("unchecked")
	public static MercatoDAO getMercatoDAO(Connection connection, String schema) throws DaoException {
		MercatoDAO mercatoDAO = null;
		try {
			Class<MercatoDAO> clazz = (Class<MercatoDAO>)Class.forName(MERCATO_CLASS);
			Constructor<MercatoDAO> constructor = clazz.getConstructor(new Class[] {java.sql.Connection.class, java.lang.String.class});
			mercatoDAO = constructor.newInstance(connection, schema);
		} catch (Exception x) {
			throw new DaoException(x);
		} 
		return mercatoDAO;
	}
	//fine LP PG21XX04 Leak

	private static String CONFIGURAZIONE_TIPOLOGIABANCO_CLASS;

	static {
		CONFIGURAZIONE_TIPOLOGIABANCO_CLASS = ConfigurazioneTipologiaBancoDAOImpl.class.getName();
	}    
	//inizio LP PG21XX04 Leak
	@Deprecated
	//fine LP PG21XX04 Leak
	@SuppressWarnings("unchecked")
	public static ConfigurazioneTipologiaBancoDAO getConfigurazioneTipologiaBanco(DataSource datasource, String schema) throws DaoException {
		ConfigurazioneTipologiaBancoDAO configurazioneTipologiaBancoDAO = null;
		try {
			Class<ConfigurazioneTipologiaBancoDAO> clazz = (Class<ConfigurazioneTipologiaBancoDAO>)Class.forName(CONFIGURAZIONE_TIPOLOGIABANCO_CLASS);
			Constructor<ConfigurazioneTipologiaBancoDAO> constructor = clazz.getConstructor(new Class[] {javax.sql.DataSource.class, java.lang.String.class});
			configurazioneTipologiaBancoDAO = constructor.newInstance(datasource,schema);
		} catch (Exception x) {
			throw new DaoException(x);
		}
		return configurazioneTipologiaBancoDAO;
	}

	//inizio LP PG21XX04 Leak
	public static ConfigurazioneTipologiaBancoDAO getConfigurazioneTipologiaBanco(Connection connection, String schema) throws DaoException {
		ConfigurazioneTipologiaBancoDAO configurazioneTipologiaBancoDAO = null;
		try {
			Class<ConfigurazioneTipologiaBancoDAO> clazz = (Class<ConfigurazioneTipologiaBancoDAO>)Class.forName(CONFIGURAZIONE_TIPOLOGIABANCO_CLASS);
			Constructor<ConfigurazioneTipologiaBancoDAO> constructor = clazz.getConstructor(new Class[] {java.sql.Connection.class, java.lang.String.class});
			configurazioneTipologiaBancoDAO = constructor.newInstance(connection, schema);
		} catch (Exception x) {
			throw new DaoException(x);
		}
		return configurazioneTipologiaBancoDAO;
	}
	//fine LP PG21XX04 Leak

	private static String CONFIGURAZIONE_AREAMERCANTILE_CLASS;
	
	static {
		CONFIGURAZIONE_AREAMERCANTILE_CLASS = ConfigurazioneAreaMercantileDAOImpl.class.getName();
	}
	//inizio LP PG21XX04 Leak
	@Deprecated
	//fine LP PG21XX04 Leak
	@SuppressWarnings("unchecked")
	public static ConfigurazioneAreaMercantileDAO getConfigurazioneAreaMercantile(DataSource datasource, String schema) throws DaoException {
		ConfigurazioneAreaMercantileDAO configurazioneAreaMercantileDAO = null;
		try {
			Class<ConfigurazioneAreaMercantileDAO> clazz = (Class<ConfigurazioneAreaMercantileDAO>)Class.forName(CONFIGURAZIONE_AREAMERCANTILE_CLASS);
			Constructor<ConfigurazioneAreaMercantileDAO> constructor = clazz.getConstructor(new Class[] {javax.sql.DataSource.class, java.lang.String.class});
			configurazioneAreaMercantileDAO = constructor.newInstance(datasource,schema);
		} catch (Exception x) {
			throw new DaoException(x);
		}
		return configurazioneAreaMercantileDAO;
	}

	//inizio LP PG21XX04 Leak
	@SuppressWarnings("unchecked")
	public static ConfigurazioneAreaMercantileDAO getConfigurazioneAreaMercantile(Connection connection, String schema) throws DaoException {
		ConfigurazioneAreaMercantileDAO configurazioneAreaMercantileDAO = null;
		try {
			Class<ConfigurazioneAreaMercantileDAO> clazz = (Class<ConfigurazioneAreaMercantileDAO>)Class.forName(CONFIGURAZIONE_AREAMERCANTILE_CLASS);
			Constructor<ConfigurazioneAreaMercantileDAO> constructor = clazz.getConstructor(new Class[] {java.sql.Connection.class, java.lang.String.class});
			configurazioneAreaMercantileDAO = constructor.newInstance(connection, schema);
		} catch (Exception x) {
			throw new DaoException(x);
		}
		return configurazioneAreaMercantileDAO;
	}
	//fine LP PG21XX04 Leak

	private static String CONFIGURAZIONE_PERIODOGIORNALIERO_CLASS;

	static {
		CONFIGURAZIONE_PERIODOGIORNALIERO_CLASS = ConfigurazionePeriodoGiornalieroDAOImpl.class.getName();
	}    
	//inizio LP PG21XX04 Leak
	@Deprecated
	//fine LP PG21XX04 Leak
	@SuppressWarnings("unchecked")
	public static ConfigurazionePeriodoGiornalieroDAO getConfigurazionePeriodoGiornaliero(DataSource datasource, String schema) throws DaoException {
		ConfigurazionePeriodoGiornalieroDAO configurazionePeriodoGiornalieroDAO = null;
		try {
			Class<ConfigurazionePeriodoGiornalieroDAO> clazz = (Class<ConfigurazionePeriodoGiornalieroDAO>)Class.forName(CONFIGURAZIONE_PERIODOGIORNALIERO_CLASS);
			Constructor<ConfigurazionePeriodoGiornalieroDAO> constructor = clazz.getConstructor(new Class[] {javax.sql.DataSource.class, java.lang.String.class});
			configurazionePeriodoGiornalieroDAO = constructor.newInstance(datasource,schema);
		} catch (Exception x) {
			throw new DaoException(x);
		}
		return configurazionePeriodoGiornalieroDAO;
	}

	//inizio LP PG21XX04 Leak
	@SuppressWarnings("unchecked")
	public static ConfigurazionePeriodoGiornalieroDAO getConfigurazionePeriodoGiornaliero(Connection connection, String schema) throws DaoException {
		ConfigurazionePeriodoGiornalieroDAO configurazionePeriodoGiornalieroDAO = null;
		try {
			Class<ConfigurazionePeriodoGiornalieroDAO> clazz = (Class<ConfigurazionePeriodoGiornalieroDAO>)Class.forName(CONFIGURAZIONE_PERIODOGIORNALIERO_CLASS);
			Constructor<ConfigurazionePeriodoGiornalieroDAO> constructor = clazz.getConstructor(new Class[] {java.sql.Connection.class, java.lang.String.class});
			configurazionePeriodoGiornalieroDAO = constructor.newInstance(connection, schema);
		} catch (Exception x) {
			throw new DaoException(x);
		}
		return configurazionePeriodoGiornalieroDAO;
	}
	//fine LP PG21XX04 Leak

	private static String CONFIGURAZIONE_ANAGAUTORIZZAZIONE_CLASS;

	static {
		CONFIGURAZIONE_ANAGAUTORIZZAZIONE_CLASS = ConfigurazioneAnagAutorizzazioneDAOImpl.class.getName();
	}    
	//inizio LP PG21XX04 Leak
	@Deprecated
	//fine LP PG21XX04 Leak
	@SuppressWarnings("unchecked")
	public static ConfigurazioneAnagAutorizzazioneDAO getConfigurazioneAnagAutorizzazione(DataSource datasource, String schema) throws DaoException {
		ConfigurazioneAnagAutorizzazioneDAO configurazioneAnagAutorizzazioneDAO = null;
		try {
			Class<ConfigurazioneAnagAutorizzazioneDAO> clazz = (Class<ConfigurazioneAnagAutorizzazioneDAO>)Class.forName(CONFIGURAZIONE_ANAGAUTORIZZAZIONE_CLASS);
			Constructor<ConfigurazioneAnagAutorizzazioneDAO> constructor = clazz.getConstructor(new Class[] {javax.sql.DataSource.class, java.lang.String.class});
			configurazioneAnagAutorizzazioneDAO = constructor.newInstance(datasource,schema);
		} catch (Exception x) {
			throw new DaoException(x);
		}
		return configurazioneAnagAutorizzazioneDAO;
	}

	//inizio LP PG21XX04 Leak
	@SuppressWarnings("unchecked")
	public static ConfigurazioneAnagAutorizzazioneDAO getConfigurazioneAnagAutorizzazione(Connection connection, String schema) throws DaoException {
		ConfigurazioneAnagAutorizzazioneDAO configurazioneAnagAutorizzazioneDAO = null;
		try {
			Class<ConfigurazioneAnagAutorizzazioneDAO> clazz = (Class<ConfigurazioneAnagAutorizzazioneDAO>)Class.forName(CONFIGURAZIONE_ANAGAUTORIZZAZIONE_CLASS);
			Constructor<ConfigurazioneAnagAutorizzazioneDAO> constructor = clazz.getConstructor(new Class[] {java.sql.Connection.class, java.lang.String.class});
			configurazioneAnagAutorizzazioneDAO = constructor.newInstance(connection, schema);
		} catch (Exception x) {
			throw new DaoException(x);
		}
		return configurazioneAnagAutorizzazioneDAO;
	}
	//fine LP PG21XX04 Leak

	private static String CONFIGURAZIONE_PIAZZOLA_CLASS;
	static {
		CONFIGURAZIONE_PIAZZOLA_CLASS = ConfigurazionePiazzolaDAOImpl.class.getName();
	}    
	//inizio LP PG21XX04 Leak
	@Deprecated
	//fine LP PG21XX04 Leak
	@SuppressWarnings("unchecked")
	public static ConfigurazionePiazzolaDAO getConfigurazionePiazzola(DataSource datasource, String schema) throws DaoException {
		ConfigurazionePiazzolaDAO configurazionePiazzolaDAO = null;
		try {
			Class<ConfigurazionePiazzolaDAO> clazz = (Class<ConfigurazionePiazzolaDAO>)Class.forName(CONFIGURAZIONE_PIAZZOLA_CLASS);
			Constructor<ConfigurazionePiazzolaDAO> constructor = clazz.getConstructor(new Class[] {javax.sql.DataSource.class, java.lang.String.class});
			configurazionePiazzolaDAO = constructor.newInstance(datasource,schema);
		} catch (Exception x) {
			throw new DaoException(x);
		}
		return configurazionePiazzolaDAO;
	}

	//inizio LP PG21XX04 Leak
	@SuppressWarnings("unchecked")
	public static ConfigurazionePiazzolaDAO getConfigurazionePiazzola(Connection connection, String schema) throws DaoException {
		ConfigurazionePiazzolaDAO configurazionePiazzolaDAO = null;
		try {
			Class<ConfigurazionePiazzolaDAO> clazz = (Class<ConfigurazionePiazzolaDAO>)Class.forName(CONFIGURAZIONE_PIAZZOLA_CLASS);
			Constructor<ConfigurazionePiazzolaDAO> constructor = clazz.getConstructor(new Class[] {java.sql.Connection.class, java.lang.String.class});
			configurazionePiazzolaDAO = constructor.newInstance(connection, schema);
		} catch (Exception x) {
			throw new DaoException(x);
		}
		return configurazionePiazzolaDAO;
	}
	//fine LP PG21XX04 Leak

	private static String CONFIGURAZIONE_TARIFFE_CLASS;
	static {
		CONFIGURAZIONE_TARIFFE_CLASS = ConfigurazioneTariffeDAOImpl.class.getName();
	}    
	//inizio LP PG21XX04 Leak
	@Deprecated
	//fine LP PG21XX04 Leak
	@SuppressWarnings("unchecked")
	public static ConfigurazioneTariffeDAO getConfigurazioneTariffe(DataSource datasource, String schema) throws DaoException {
		ConfigurazioneTariffeDAO configurazioneTariffeDAO = null;
		try {
			Class<ConfigurazioneTariffeDAO> clazz = (Class<ConfigurazioneTariffeDAO>)Class.forName(CONFIGURAZIONE_TARIFFE_CLASS);
			Constructor<ConfigurazioneTariffeDAO> constructor = clazz.getConstructor(new Class[] {javax.sql.DataSource.class, java.lang.String.class});
			configurazioneTariffeDAO = constructor.newInstance(datasource,schema);
		} catch (Exception x) {
			throw new DaoException(x);
		}
		return configurazioneTariffeDAO;
	}

	//inizio LP PG21XX04 Leak
	@SuppressWarnings("unchecked")
	public static ConfigurazioneTariffeDAO getConfigurazioneTariffe(Connection connection, String schema) throws DaoException {
		ConfigurazioneTariffeDAO configurazioneTariffeDAO = null;
		try {
			Class<ConfigurazioneTariffeDAO> clazz = (Class<ConfigurazioneTariffeDAO>)Class.forName(CONFIGURAZIONE_TARIFFE_CLASS);
			Constructor<ConfigurazioneTariffeDAO> constructor = clazz.getConstructor(new Class[] {java.sql.Connection.class, java.lang.String.class});
			configurazioneTariffeDAO = constructor.newInstance(connection, schema);
		} catch (Exception x) {
			throw new DaoException(x);
		}
		return configurazioneTariffeDAO;
	}
	//fine LP PG21XX04 Leak

	private static String CONFIGURAZIONE_COMPENSO_CLASS;
	static {
		CONFIGURAZIONE_COMPENSO_CLASS = ConfigurazioneCompensoDAOImpl.class.getName();
	}    
	//inizio LP PG21XX04 Leak
	@Deprecated
	//fine LP PG21XX04 Leak
	@SuppressWarnings("unchecked")
	public static ConfigurazioneCompensoDAO getConfigurazioneCompenso(DataSource datasource, String schema) throws DaoException {
		ConfigurazioneCompensoDAO configurazioneCompensoDAO = null;
		try {
			Class<ConfigurazioneCompensoDAO> clazz = (Class<ConfigurazioneCompensoDAO>)Class.forName(CONFIGURAZIONE_COMPENSO_CLASS);
			Constructor<ConfigurazioneCompensoDAO> constructor = clazz.getConstructor(new Class[] {javax.sql.DataSource.class, java.lang.String.class});
			configurazioneCompensoDAO = constructor.newInstance(datasource,schema);
		} catch (Exception x) {
			throw new DaoException(x);
		}
		return configurazioneCompensoDAO;
	}

	//inizio LP PG21XX04 Leak
	@SuppressWarnings("unchecked")
	public static ConfigurazioneCompensoDAO getConfigurazioneCompenso(Connection connection, String schema) throws DaoException {
		ConfigurazioneCompensoDAO configurazioneCompensoDAO = null;
		try {
			Class<ConfigurazioneCompensoDAO> clazz = (Class<ConfigurazioneCompensoDAO>)Class.forName(CONFIGURAZIONE_COMPENSO_CLASS);
			Constructor<ConfigurazioneCompensoDAO> constructor = clazz.getConstructor(new Class[] {java.sql.Connection.class, java.lang.String.class});
			configurazioneCompensoDAO = constructor.newInstance(connection, schema);
		} catch (Exception x) {
			throw new DaoException(x);
		}
		return configurazioneCompensoDAO;
	}
	//fine LP PG21XX04 Leak

	private static String PRENOTAZIONI_CLASS;
	static {
		PRENOTAZIONI_CLASS = ConfigurazionePrenotazioniDAOImpl.class.getName();
	}    
	//inizio LP PG21XX04 Leak
	@Deprecated
	//fine LP PG21XX04 Leak
	@SuppressWarnings("unchecked")
	public static ConfigurazionePrenotazioniDAO getPrenotazioni(DataSource datasource, String schema) throws DaoException {
		ConfigurazionePrenotazioniDAO ConfigurazionePrenotazioniDAO = null;
		try {
			Class<ConfigurazionePrenotazioniDAO> clazz = (Class<ConfigurazionePrenotazioniDAO>)Class.forName(PRENOTAZIONI_CLASS);
			Constructor<ConfigurazionePrenotazioniDAO> constructor = clazz.getConstructor(new Class[] {javax.sql.DataSource.class, java.lang.String.class});
			ConfigurazionePrenotazioniDAO = constructor.newInstance(datasource,schema);
		} catch (Exception x) {
			throw new DaoException(x);
		}
		return ConfigurazionePrenotazioniDAO;
	}

	//inizio LP PG21XX04 Leak
	@SuppressWarnings("unchecked")
	public static ConfigurazionePrenotazioniDAO getPrenotazioni(Connection connection, String schema) throws DaoException {
		ConfigurazionePrenotazioniDAO ConfigurazionePrenotazioniDAO = null;
		try {
			Class<ConfigurazionePrenotazioniDAO> clazz = (Class<ConfigurazionePrenotazioniDAO>)Class.forName(PRENOTAZIONI_CLASS);
			Constructor<ConfigurazionePrenotazioniDAO> constructor = clazz.getConstructor(new Class[] {java.sql.Connection.class, java.lang.String.class});
			ConfigurazionePrenotazioniDAO = constructor.newInstance(connection, schema);
		} catch (Exception x) {
			throw new DaoException(x);
		}
		return ConfigurazionePrenotazioniDAO;
	}
	//fine LP PG21XX04 Leak

	private static String MONITORAGGIOMERCATI_CLASS;
	static {
		MONITORAGGIOMERCATI_CLASS = MonitoraggioMercatiDAOImpl.class.getName();
	}    
	//inizio LP PG21XX04 Leak
	@Deprecated
	//fine LP PG21XX04 Leak
	@SuppressWarnings("unchecked")
	public static MonitoraggioMercatiDAO getMonitoraggioMercati(DataSource datasource, String schema) throws DaoException {
		MonitoraggioMercatiDAO monitoraggioMercatiDAO = null;
		try {
			Class<MonitoraggioMercatiDAO> clazz = (Class<MonitoraggioMercatiDAO>)Class.forName(MONITORAGGIOMERCATI_CLASS);
			Constructor<MonitoraggioMercatiDAO> constructor = clazz.getConstructor(new Class[] {javax.sql.DataSource.class, java.lang.String.class});
			monitoraggioMercatiDAO = constructor.newInstance(datasource,schema);
		} catch (Exception x) {
			throw new DaoException(x);
		}
		return monitoraggioMercatiDAO;
	}

	//inizio LP PG21XX04 Leak
	@SuppressWarnings("unchecked")
	public static MonitoraggioMercatiDAO getMonitoraggioMercati(Connection connection, String schema) throws DaoException {
		MonitoraggioMercatiDAO monitoraggioMercatiDAO = null;
		try {
			Class<MonitoraggioMercatiDAO> clazz = (Class<MonitoraggioMercatiDAO>)Class.forName(MONITORAGGIOMERCATI_CLASS);
			Constructor<MonitoraggioMercatiDAO> constructor = clazz.getConstructor(new Class[] {java.sql.Connection.class, java.lang.String.class});
			monitoraggioMercatiDAO = constructor.newInstance(connection, schema);
		} catch (Exception x) {
			throw new DaoException(x);
		}
		return monitoraggioMercatiDAO;
	}
	//fine LP PG21XX04 Leak
	
//Termine della Classe	
}
