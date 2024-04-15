package com.seda.payer.core.wallet.dao;

import java.lang.reflect.Constructor;
import java.sql.Connection;

import javax.sql.DataSource;
import com.seda.payer.core.exception.DaoException;

public class WalletDAOFactory {

	private static String WALLET_CLASS;

	static {
		WALLET_CLASS=WalletDAOImpl.class.getName();
	}
	//inizio LP PG21XX04 Leak
	@Deprecated
	//fine LP PG21XX04 Leak
	@SuppressWarnings("unchecked")
	public static WalletDAO getWalletDAO(DataSource datasource, String schema) throws DaoException {
		WalletDAO walletDAO = null;
		try {
			Class<WalletDAO> clazz = (Class<WalletDAO>)Class.forName(WALLET_CLASS);
			Constructor<WalletDAO> constructor = clazz.getConstructor(new Class[] {javax.sql.DataSource.class, java.lang.String.class});
			walletDAO = constructor.newInstance(datasource,schema);
		} catch (Exception x) {
			throw new DaoException(x);
		}
		return walletDAO;
	}

	//inizio LP PG21XX04 Leak
	@SuppressWarnings("unchecked")
	public static WalletDAO getWalletDAO(Connection connection, String schema) throws DaoException {
		WalletDAO walletDAO = null;
		try {
			Class<WalletDAO> clazz = (Class<WalletDAO>)Class.forName(WALLET_CLASS);
			Constructor<WalletDAO> constructor = clazz.getConstructor(new Class[] {java.sql.Connection.class, java.lang.String.class});
			walletDAO = constructor.newInstance(connection, schema);
		} catch (Exception x) {
			throw new DaoException(x);
		}
		return walletDAO;
	}
	//fine LP PG21XX04 Leak

	private static String ANAGRAFICA_GENITORE_MENSE_CLASS;


	private static String ANAGRAFICA_FIGLIO_MENSE_CLASS;

	static {
		ANAGRAFICA_FIGLIO_MENSE_CLASS = AnagraficaFiglioMenseDAOImpl.class.getName();
	}
	//inizio LP PG21XX04 Leak
	@Deprecated
	//fine LP PG21XX04 Leak
	@SuppressWarnings("unchecked")
	public static AnagraficaFiglioMenseDAO getAnagraficaFiglioMenseDAO(DataSource datasource, String schema) throws DaoException {
		AnagraficaFiglioMenseDAO anagraficaFiglioMenseDAO = null;
		try {
			Class<AnagraficaFiglioMenseDAO> clazz = (Class<AnagraficaFiglioMenseDAO>)Class.forName(ANAGRAFICA_FIGLIO_MENSE_CLASS);
			Constructor<AnagraficaFiglioMenseDAO> constructor = clazz.getConstructor(new Class[] {javax.sql.DataSource.class, java.lang.String.class});
			anagraficaFiglioMenseDAO = constructor.newInstance(datasource,schema);
		} catch (Exception x) {
			throw new DaoException(x);
		}
		return anagraficaFiglioMenseDAO;
	}

	//inizio LP PG21XX04 Leak
	@SuppressWarnings("unchecked")
	public static AnagraficaFiglioMenseDAO getAnagraficaFiglioMenseDAO(Connection connection, String schema) throws DaoException {
		AnagraficaFiglioMenseDAO anagraficaFiglioMenseDAO = null;
		try {
			Class<AnagraficaFiglioMenseDAO> clazz = (Class<AnagraficaFiglioMenseDAO>)Class.forName(ANAGRAFICA_FIGLIO_MENSE_CLASS);
			Constructor<AnagraficaFiglioMenseDAO> constructor = clazz.getConstructor(new Class[] {java.sql.Connection.class, java.lang.String.class});
			anagraficaFiglioMenseDAO = constructor.newInstance(connection, schema);
		} catch (Exception x) {
			throw new DaoException(x);
		}
		return anagraficaFiglioMenseDAO;
	}
	//fine LP PG21XX04 Leak

	private static String SCUOLA_CLASS;

	static {
		SCUOLA_CLASS = ScuolaDAOImpl.class.getName();
	}
	//inizio LP PG21XX04 Leak
	@Deprecated
	//fine LP PG21XX04 Leak
	@SuppressWarnings("unchecked")
	public static ScuolaDAO getScuolaDAO(DataSource datasource, String schema) throws DaoException {
		ScuolaDAO scuolaDAO = null;
		try {
			Class<ScuolaDAO> clazz = (Class<ScuolaDAO>)Class.forName(SCUOLA_CLASS);
			Constructor<ScuolaDAO> constructor = clazz.getConstructor(new Class[] {javax.sql.DataSource.class, java.lang.String.class});
			scuolaDAO = constructor.newInstance(datasource,schema);
		} catch (Exception x) {
			throw new DaoException(x);
		}
		return scuolaDAO;
	}

	//inizio LP PG21XX04 Leak
	@SuppressWarnings("unchecked")
	public static ScuolaDAO getScuolaDAO(Connection connection, String schema) throws DaoException {
		ScuolaDAO scuolaDAO = null;
		try {
			Class<ScuolaDAO> clazz = (Class<ScuolaDAO>)Class.forName(SCUOLA_CLASS);
			Constructor<ScuolaDAO> constructor = clazz.getConstructor(new Class[] {java.sql.Connection.class, java.lang.String.class});
			scuolaDAO = constructor.newInstance(connection, schema);
		} catch (Exception x) {
			throw new DaoException(x);
		}
		return scuolaDAO;
	}
	//fine LP PG21XX04 Leak

	private static String SERVIZIO_CLASS;

	static {
		SERVIZIO_CLASS = ServizioDAOImpl.class.getName();
	}
	//inizio LP PG21XX04 Leak
	@Deprecated
	//fine LP PG21XX04 Leak
	@SuppressWarnings("unchecked")
	public static ServizioDAO getServizioDAO(DataSource datasource, String schema) throws DaoException {
		ServizioDAO servizioDAO = null;
		try {
			Class<ServizioDAO> clazz = (Class<ServizioDAO>)Class.forName(SERVIZIO_CLASS);
			Constructor<ServizioDAO> constructor = clazz.getConstructor(new Class[] {javax.sql.DataSource.class, java.lang.String.class});
			servizioDAO = constructor.newInstance(datasource,schema);
		} catch (Exception x) {
			throw new DaoException(x);
		}
		return servizioDAO;
	}

	//inizio LP PG21XX04 Leak
	@SuppressWarnings("unchecked")
	public static ServizioDAO getServizioDAO(Connection connection, String schema) throws DaoException {
		ServizioDAO servizioDAO = null;
		try {
			Class<ServizioDAO> clazz = (Class<ServizioDAO>)Class.forName(SERVIZIO_CLASS);
			Constructor<ServizioDAO> constructor = clazz.getConstructor(new Class[] {java.sql.Connection.class, java.lang.String.class});
			servizioDAO = constructor.newInstance(connection, schema);
		} catch (Exception x) {
			throw new DaoException(x);
		}
		return servizioDAO;
	}
	//fine LP PG21XX04 Leak

	private static String CONFIGURAZIONE_SOLLECITI_CLASS;

	static {
		CONFIGURAZIONE_SOLLECITI_CLASS =  ConfigurazioneSollecitiDAOImpl.class.getName();
	}
	//inizio LP PG21XX04 Leak
	@Deprecated
	//fine LP PG21XX04 Leak
	@SuppressWarnings("unchecked")
	public static ConfigurazioneSollecitiDAO getConfigurazioneSollecitiDAO(DataSource datasource, String schema) throws DaoException {
		ConfigurazioneSollecitiDAO configurazioneSollecitiDAO= null;
		try {
			Class<ConfigurazioneSollecitiDAO> clazz = (Class<ConfigurazioneSollecitiDAO>)Class.forName(CONFIGURAZIONE_SOLLECITI_CLASS);
			Constructor<ConfigurazioneSollecitiDAO> constructor = clazz.getConstructor(new Class[] {javax.sql.DataSource.class, java.lang.String.class});
			configurazioneSollecitiDAO = constructor.newInstance(datasource,schema);
		} catch (Exception x) {
			throw new DaoException(x);
		}
		return configurazioneSollecitiDAO;
	}

	//inizio LP PG21XX04 Leak
	@SuppressWarnings("unchecked")
	public static ConfigurazioneSollecitiDAO getConfigurazioneSollecitiDAO(Connection connection, String schema) throws DaoException {
		ConfigurazioneSollecitiDAO configurazioneSollecitiDAO= null;
		try {
			Class<ConfigurazioneSollecitiDAO> clazz = (Class<ConfigurazioneSollecitiDAO>)Class.forName(CONFIGURAZIONE_SOLLECITI_CLASS);
			Constructor<ConfigurazioneSollecitiDAO> constructor = clazz.getConstructor(new Class[] {java.sql.Connection.class, java.lang.String.class});
			configurazioneSollecitiDAO = constructor.newInstance(connection ,schema);
		} catch (Exception x) {
			throw new DaoException(x);
		}
		return configurazioneSollecitiDAO;
	}
	//fine LP PG21XX04 Leak

	private static String CONFIGURAZIONE_EVOINTIMAZIONI_CLASS;

	static {
		CONFIGURAZIONE_EVOINTIMAZIONI_CLASS =  ConfigurazioneEvoIntimazioniDAOImpl.class.getName();
	}
	//inizio LP PG21XX04 Leak
	@Deprecated
	//fine LP PG21XX04 Leak
	@SuppressWarnings("unchecked")
	//public static ConfigurazioneSollecitiDAO getConfigurazioneSollecitiDAO(DataSource datasource, String schema) throws DaoException {
	public static ConfigurazioneEvoIntimazioniDAO getConfigurazioneEvoIntimazione(DataSource datasource, String schema) throws DaoException {
		ConfigurazioneEvoIntimazioniDAO configurazioneEvoIntimazioniDAO= null;
		try {
			Class<ConfigurazioneEvoIntimazioniDAO> clazz = (Class<ConfigurazioneEvoIntimazioniDAO>)Class.forName(CONFIGURAZIONE_EVOINTIMAZIONI_CLASS);
			Constructor<ConfigurazioneEvoIntimazioniDAO> constructor = clazz.getConstructor(new Class[] {javax.sql.DataSource.class, java.lang.String.class});
			configurazioneEvoIntimazioniDAO = constructor.newInstance(datasource,schema);
		} catch (Exception x) {
			throw new DaoException(x);
		}
		return configurazioneEvoIntimazioniDAO;
	}

	//inizio LP PG21XX04 Leak
	@SuppressWarnings("unchecked")
	public static ConfigurazioneEvoIntimazioniDAO getConfigurazioneEvoIntimazione(Connection connection, String schema) throws DaoException {
		ConfigurazioneEvoIntimazioniDAO configurazioneEvoIntimazioniDAO= null;
		try {
			Class<ConfigurazioneEvoIntimazioniDAO> clazz = (Class<ConfigurazioneEvoIntimazioniDAO>)Class.forName(CONFIGURAZIONE_EVOINTIMAZIONI_CLASS);
			Constructor<ConfigurazioneEvoIntimazioniDAO> constructor = clazz.getConstructor(new Class[] {java.sql.Connection.class, java.lang.String.class});
			configurazioneEvoIntimazioniDAO = constructor.newInstance(connection, schema);
		} catch (Exception x) {
			throw new DaoException(x);
		}
		return configurazioneEvoIntimazioniDAO;
	}
	//fine LP PG21XX04 Leak

	private static String CONFIGURAZIONE_PAGAMENTOPERSERVIZIO_CLASS;
	static {
		CONFIGURAZIONE_PAGAMENTOPERSERVIZIO_CLASS = ConfigurazionePagamentoServizioDAOImpl.class.getName();
	}
	//inizio LP PG21XX04 Leak
	@Deprecated
	//fine LP PG21XX04 Leak
	@SuppressWarnings("unchecked")
	public static ConfigurazionePagamentoServizioDAO getPagamentoPerservizio(DataSource datasource, String schema) throws DaoException {
		ConfigurazionePagamentoServizioDAO configurazionePagamentoServizioDAO= null;
		try {
			Class<ConfigurazionePagamentoServizioDAO> clazz = (Class<ConfigurazionePagamentoServizioDAO>)Class.forName(CONFIGURAZIONE_PAGAMENTOPERSERVIZIO_CLASS);
			Constructor<ConfigurazionePagamentoServizioDAO> constructor = clazz.getConstructor(new Class[] {javax.sql.DataSource.class, java.lang.String.class});
			configurazionePagamentoServizioDAO = constructor.newInstance(datasource,schema);
		} catch (Exception x) {
			throw new DaoException(x);
		}
		return configurazionePagamentoServizioDAO;
	}

	//inizio LP PG21XX04 Leak
	@SuppressWarnings("unchecked")
	public static ConfigurazionePagamentoServizioDAO getPagamentoPerservizio(Connection connection, String schema) throws DaoException {
		ConfigurazionePagamentoServizioDAO configurazionePagamentoServizioDAO= null;
		try {
			Class<ConfigurazionePagamentoServizioDAO> clazz = (Class<ConfigurazionePagamentoServizioDAO>)Class.forName(CONFIGURAZIONE_PAGAMENTOPERSERVIZIO_CLASS);
			Constructor<ConfigurazionePagamentoServizioDAO> constructor = clazz.getConstructor(new Class[] {java.sql.Connection.class, java.lang.String.class});
			configurazionePagamentoServizioDAO = constructor.newInstance(connection, schema);
		} catch (Exception x) {
			throw new DaoException(x);
		}
		return configurazionePagamentoServizioDAO;
	}
	//fine LP PG21XX04 Leak

	private static String TRIBUTO_CLASS;

	static {
		TRIBUTO_CLASS = TributoDAOImpl.class.getName();
	}
	//inizio LP PG21XX04 Leak
	@Deprecated
	//fine LP PG21XX04 Leak
	@SuppressWarnings("unchecked")
	public static TributoDAO getTributoDAO(DataSource datasource, String schema) throws DaoException {
		TributoDAO tributoDAO = null;
		try {
			Class<TributoDAO> clazz = (Class<TributoDAO>)Class.forName(TRIBUTO_CLASS);
			Constructor<TributoDAO> constructor = clazz.getConstructor(new Class[] {javax.sql.DataSource.class, java.lang.String.class});
			tributoDAO = constructor.newInstance(datasource,schema);
		} catch (Exception x) {
			throw new DaoException(x);
		}
		return tributoDAO;
	}

	//inizio LP PG21XX04 Leak
	@SuppressWarnings("unchecked")
	public static TributoDAO getTributoDAO(Connection connection, String schema) throws DaoException {
		TributoDAO tributoDAO = null;
		try {
			Class<TributoDAO> clazz = (Class<TributoDAO>)Class.forName(TRIBUTO_CLASS);
			Constructor<TributoDAO> constructor = clazz.getConstructor(new Class[] {java.sql.Connection.class, java.lang.String.class});
			tributoDAO = constructor.newInstance(connection, schema);
		} catch (Exception x) {
			throw new DaoException(x);
		}
		return tributoDAO;
	}
	//fine LP PG21XX04 Leak

	private static String SOLLECITI_CLASS;

	static {
		SOLLECITI_CLASS=SollecitiDAOImpl.class.getName();
	}
	//inizio LP PG21XX04 Leak
	@Deprecated
	//fine LP PG21XX04 Leak
	@SuppressWarnings("unchecked")
	public static SollecitiDAO getSollecitiDAO(DataSource datasource, String schema) throws DaoException {
		SollecitiDAO sollecitiDAO = null;
		try {
			Class<SollecitiDAO> clazz = (Class<SollecitiDAO>)Class.forName(SOLLECITI_CLASS);
			Constructor<SollecitiDAO> constructor = clazz.getConstructor(new Class[] {javax.sql.DataSource.class, java.lang.String.class});
			sollecitiDAO = constructor.newInstance(datasource,schema);
		} catch (Exception x) {
			throw new DaoException(x);
		}
		return sollecitiDAO;
	}

	//inizio LP PG21XX04 Leak
	@SuppressWarnings("unchecked")
	public static SollecitiDAO getSollecitiDAO(Connection connection, String schema) throws DaoException {
		SollecitiDAO sollecitiDAO = null;
		try {
			Class<SollecitiDAO> clazz = (Class<SollecitiDAO>)Class.forName(SOLLECITI_CLASS);
			Constructor<SollecitiDAO> constructor = clazz.getConstructor(new Class[] {java.sql.Connection.class, java.lang.String.class});
			sollecitiDAO = constructor.newInstance(connection, schema);
		} catch (Exception x) {
			throw new DaoException(x);
		}
		return sollecitiDAO;
	}
	//fine LP PG21XX04 Leak

	private static String CONFIGURAZIONE_ATTRIBUZIONEPAGSERV_CLASS;
	static {
		CONFIGURAZIONE_ATTRIBUZIONEPAGSERV_CLASS = ConfigurazioneAttribuzionePagamentoServizioDAOImpl.class.getName();
	}
	//inizio LP PG21XX04 Leak
	@Deprecated
	//fine LP PG21XX04 Leak
	@SuppressWarnings("unchecked")
	public static ConfigurazioneAttribuzionePagamentoServizioDAO getAttribuzionePagamentoServizio(DataSource datasource, String schema) throws DaoException {
		ConfigurazioneAttribuzionePagamentoServizioDAO configurazioneAttribuzionePagamentoServizioDAO= null;
		try {
			Class<ConfigurazioneAttribuzionePagamentoServizioDAO> clazz = (Class<ConfigurazioneAttribuzionePagamentoServizioDAO>)Class.forName(CONFIGURAZIONE_ATTRIBUZIONEPAGSERV_CLASS);
			Constructor<ConfigurazioneAttribuzionePagamentoServizioDAO> constructor = clazz.getConstructor(new Class[] {javax.sql.DataSource.class, java.lang.String.class});
			configurazioneAttribuzionePagamentoServizioDAO = constructor.newInstance(datasource,schema);
		} catch (Exception x) {
			throw new DaoException(x);
		}
		return configurazioneAttribuzionePagamentoServizioDAO;
	}

	//inizio LP PG21XX04 Leak
	@SuppressWarnings("unchecked")
	public static ConfigurazioneAttribuzionePagamentoServizioDAO getAttribuzionePagamentoServizio(Connection connection, String schema) throws DaoException {
		ConfigurazioneAttribuzionePagamentoServizioDAO configurazioneAttribuzionePagamentoServizioDAO= null;
		try {
			Class<ConfigurazioneAttribuzionePagamentoServizioDAO> clazz = (Class<ConfigurazioneAttribuzionePagamentoServizioDAO>)Class.forName(CONFIGURAZIONE_ATTRIBUZIONEPAGSERV_CLASS);
			Constructor<ConfigurazioneAttribuzionePagamentoServizioDAO> constructor = clazz.getConstructor(new Class[] {java.sql.Connection.class, java.lang.String.class});
			configurazioneAttribuzionePagamentoServizioDAO = constructor.newInstance(connection, schema);
		} catch (Exception x) {
			throw new DaoException(x);
		}
		return configurazioneAttribuzionePagamentoServizioDAO;
	}
	//fine LP PG21XX04 Leak

	private static String CONFIGURAZIONE_RACCORDOPAGONET_CLASS;
	static {
		CONFIGURAZIONE_RACCORDOPAGONET_CLASS = ConfigurazioneRaccordoPagonetDAOImpl.class.getName();
	}
	//inizio LP PG21XX04 Leak
	@Deprecated
	//fine LP PG21XX04 Leak
	@SuppressWarnings("unchecked")
	public static ConfigurazioneRaccordoPagonetDAO getRaccordoPagonet(DataSource datasource, String schema) throws DaoException {
		ConfigurazioneRaccordoPagonetDAO configurazioneRaccordoPagonetDAO= null;
		try {
			Class<ConfigurazioneRaccordoPagonetDAO> clazz = (Class<ConfigurazioneRaccordoPagonetDAO>)Class.forName(CONFIGURAZIONE_RACCORDOPAGONET_CLASS);
			Constructor<ConfigurazioneRaccordoPagonetDAO> constructor = clazz.getConstructor(new Class[] {javax.sql.DataSource.class, java.lang.String.class});
			configurazioneRaccordoPagonetDAO = constructor.newInstance(datasource,schema);
		} catch (Exception x) {
			throw new DaoException(x);
		}
		return configurazioneRaccordoPagonetDAO;
	}

	//inizio LP PG21XX04 Leak
	@SuppressWarnings("unchecked")
	public static ConfigurazioneRaccordoPagonetDAO getRaccordoPagonet(Connection connection, String schema) throws DaoException {
		ConfigurazioneRaccordoPagonetDAO configurazioneRaccordoPagonetDAO= null;
		try {
			Class<ConfigurazioneRaccordoPagonetDAO> clazz = (Class<ConfigurazioneRaccordoPagonetDAO>)Class.forName(CONFIGURAZIONE_RACCORDOPAGONET_CLASS);
			Constructor<ConfigurazioneRaccordoPagonetDAO> constructor = clazz.getConstructor(new Class[] {java.sql.Connection.class, java.lang.String.class});
			configurazioneRaccordoPagonetDAO = constructor.newInstance(connection, schema);
		} catch (Exception x) {
			throw new DaoException(x);
		}
		return configurazioneRaccordoPagonetDAO;
	}
	//fine LP PG21XX04 Leak

	private static String REP_CLASS;
	static {
		REP_CLASS = RepDAOImpl.class.getName();
	}
	@SuppressWarnings("unchecked")
	public static RepDAO getRepDao(Connection connection, String schema) throws DaoException {
		RepDAO dao= null;
		try {
			Class<RepDAO> clazz = (Class<RepDAO>)Class.forName(REP_CLASS);
			Constructor<RepDAO> constructor = clazz.getConstructor(new Class[] {java.sql.Connection.class, java.lang.String.class});
			dao = constructor.newInstance(connection,schema);
		} catch (Exception x) {
			throw new DaoException(x);
		}
		return dao;
	}

	private static String FATTURE_REP_CLASS;
	static {
		FATTURE_REP_CLASS = FattureRepDAOImpl.class.getName();
	}
	@SuppressWarnings("unchecked")
	public static FattureRepDAO getFattureRepDAO(Connection connection, String schema) throws DaoException {
		FattureRepDAO dao= null;
		try {
			Class<FattureRepDAO> clazz = (Class<FattureRepDAO>)Class.forName(FATTURE_REP_CLASS);
			Constructor<FattureRepDAO> constructor = clazz.getConstructor(new Class[] {java.sql.Connection.class, java.lang.String.class});
			dao = constructor.newInstance(connection,schema);
		} catch (Exception x) {
			throw new DaoException(x);
		}
		return dao;
	}


	private static String IMPORTO_TOTALE_FATTURE_REP_CLASS;
	static {
		IMPORTO_TOTALE_FATTURE_REP_CLASS = ImportoTotaleFattureREPDAOImpl.class.getName();
	}
	@SuppressWarnings("unchecked")
	public static ImportoTotaleFattureREPDAO getImportoTotaleFattureREPDao(Connection connection, String schema) throws DaoException {
		ImportoTotaleFattureREPDAO dao= null;
		try {
			Class<ImportoTotaleFattureREPDAO> clazz = (Class<ImportoTotaleFattureREPDAO>)Class.forName(IMPORTO_TOTALE_FATTURE_REP_CLASS);
			Constructor<ImportoTotaleFattureREPDAO> constructor = clazz.getConstructor(new Class[] {java.sql.Connection.class, java.lang.String.class});
			dao = constructor.newInstance(connection,schema);
		} catch (Exception x) {
			throw new DaoException(x);
		}
		return dao;
	}


	private static String FATTURE_COMUNICAZIONI_REP_CLASS;
	static {
		FATTURE_COMUNICAZIONI_REP_CLASS = FattureComunicazioniREPDAOImpl.class.getName();
	}
	@SuppressWarnings("unchecked")
	public static FattureComunicazioniREPDAO getFattureComunicazioniREPDAO(Connection connection, String schema) throws DaoException {
		FattureComunicazioniREPDAO dao= null;
		try {
			Class<FattureComunicazioniREPDAO> clazz = (Class<FattureComunicazioniREPDAO>)Class.forName(FATTURE_COMUNICAZIONI_REP_CLASS);
			Constructor<FattureComunicazioniREPDAO> constructor = clazz.getConstructor(new Class[] {java.sql.Connection.class, java.lang.String.class});
			dao = constructor.newInstance(connection,schema);
		} catch (Exception x) {
			throw new DaoException(x);
		}
		return dao;
	}


	private static String PRESENZEGIORNALIERE_CLASS;

	static {
		PRESENZEGIORNALIERE_CLASS =  PresenzeGiornaliereDAOImpl.class.getName();
	}
	//inizio LP PG21XX04 Leak
	@Deprecated
	//fine LP PG21XX04 Leak
	@SuppressWarnings("unchecked")
	public static PresenzeGiornaliereDAO getPresenzeGiornaliereDAO(DataSource datasource, String schema) throws DaoException {
		PresenzeGiornaliereDAO presenzeGiornaliereDAO= null;
		try {
			Class<PresenzeGiornaliereDAO> clazz = (Class<PresenzeGiornaliereDAO>)Class.forName(PRESENZEGIORNALIERE_CLASS);
			Constructor<PresenzeGiornaliereDAO> constructor = clazz.getConstructor(new Class[] {javax.sql.DataSource.class, java.lang.String.class});
			presenzeGiornaliereDAO = constructor.newInstance(datasource,schema);
		} catch (Exception x) {
			throw new DaoException(x);
		}
		return presenzeGiornaliereDAO;
	}

	//inizio LP PG21XX04 Leak
	@SuppressWarnings("unchecked")
	public static PresenzeGiornaliereDAO getPresenzeGiornaliereDAO(Connection connection, String schema) throws DaoException {
		PresenzeGiornaliereDAO presenzeGiornaliereDAO= null;
		try {
			Class<PresenzeGiornaliereDAO> clazz = (Class<PresenzeGiornaliereDAO>)Class.forName(PRESENZEGIORNALIERE_CLASS);
			Constructor<PresenzeGiornaliereDAO> constructor = clazz.getConstructor(new Class[] {java.sql.Connection.class, java.lang.String.class});
			presenzeGiornaliereDAO = constructor.newInstance(connection, schema);
		} catch (Exception x) {
			throw new DaoException(x);
		}
		return presenzeGiornaliereDAO;
	}
	//fine LP PG21XX04 Leak

	private static String ANAGRAFICA_SPEDIZIONI_REP_CLASS;
	static {
		ANAGRAFICA_SPEDIZIONI_REP_CLASS = AnagraficaSpedizioneREPDAOImpl.class.getName();
	}
	@SuppressWarnings("unchecked")
	public static AnagraficaSpedizioneREPDAO getAnagraficaSpedizioneREPDAO(Connection connection, String schema) throws DaoException {
		AnagraficaSpedizioneREPDAO dao= null;
		try {
			Class<AnagraficaSpedizioneREPDAO> clazz = (Class<AnagraficaSpedizioneREPDAO>)Class.forName(ANAGRAFICA_SPEDIZIONI_REP_CLASS);
			Constructor<AnagraficaSpedizioneREPDAO> constructor = clazz.getConstructor(new Class[] {java.sql.Connection.class, java.lang.String.class});
			dao = constructor.newInstance(connection,schema);
		} catch (Exception x) {
			throw new DaoException(x);
		}
		return dao;
	}

	private static String LOG_ANAGRAFICA_FIGLIO_MENSE_CLASS;

	static {
		LOG_ANAGRAFICA_FIGLIO_MENSE_CLASS = LogAnagraficaFiglioMenseDAOImpl.class.getName();
	}
	//inizio LP PG21XX04 Leak
	@Deprecated
	//fine LP PG21XX04 Leak
	@SuppressWarnings("unchecked")
	public static LogAnagraficaFiglioMenseDAO getLogAnagraficaFiglioMenseDAO(DataSource datasource, String schema) throws DaoException {
		LogAnagraficaFiglioMenseDAO logAnagraficaFiglioMenseDAO = null;
		try {
			Class<LogAnagraficaFiglioMenseDAO> clazz = (Class<LogAnagraficaFiglioMenseDAO>)Class.forName(LOG_ANAGRAFICA_FIGLIO_MENSE_CLASS);
			Constructor<LogAnagraficaFiglioMenseDAO> constructor = clazz.getConstructor(new Class[] {javax.sql.DataSource.class, java.lang.String.class});
			logAnagraficaFiglioMenseDAO = constructor.newInstance(datasource,schema);
		} catch (Exception x) {
			throw new DaoException(x);
		}
		return logAnagraficaFiglioMenseDAO;
	}

	//inizio LP PG21XX04 Leak
	@SuppressWarnings("unchecked")
	public static LogAnagraficaFiglioMenseDAO getLogAnagraficaFiglioMenseDAO(Connection connection, String schema) throws DaoException {
		LogAnagraficaFiglioMenseDAO logAnagraficaFiglioMenseDAO = null;
		try {
			Class<LogAnagraficaFiglioMenseDAO> clazz = (Class<LogAnagraficaFiglioMenseDAO>)Class.forName(LOG_ANAGRAFICA_FIGLIO_MENSE_CLASS);
			Constructor<LogAnagraficaFiglioMenseDAO> constructor = clazz.getConstructor(new Class[] {java.sql.Connection.class, java.lang.String.class});
			logAnagraficaFiglioMenseDAO = constructor.newInstance(connection,schema);
		} catch (Exception x) {
			throw new DaoException(x);
		}
		return logAnagraficaFiglioMenseDAO;
	}
	//fine LP PG21XX04 Leak

	private static String PAGAMENTO_BORSELLINO_CLASS;

	static {
		PAGAMENTO_BORSELLINO_CLASS = PagamentoBorsellinoDAOImpl.class.getName();
	}
	//inizio LP PG21XX04 Leak
	@Deprecated
	//fine LP PG21XX04 Leak
	@SuppressWarnings("unchecked")
	public static PagamentoBorsellinoDAO getPagamentoBorsellinoDAO(DataSource datasource, String schema) throws DaoException {
		PagamentoBorsellinoDAO pagamentoBorsellinoDAO = null;
		try {
			Class<PagamentoBorsellinoDAO> clazz = (Class<PagamentoBorsellinoDAO>)Class.forName(PAGAMENTO_BORSELLINO_CLASS);
			Constructor<PagamentoBorsellinoDAO> constructor = clazz.getConstructor(new Class[] {javax.sql.DataSource.class, java.lang.String.class});
			pagamentoBorsellinoDAO = constructor.newInstance(datasource,schema);
		} catch (Exception x) {
			throw new DaoException(x);
		}
		return pagamentoBorsellinoDAO;
	}

	//inizio LP PG21XX04 Leak
	@SuppressWarnings("unchecked")
	public static PagamentoBorsellinoDAO getPagamentoBorsellinoDAO(Connection connection, String schema) throws DaoException {
		PagamentoBorsellinoDAO pagamentoBorsellinoDAO = null;
		try {
			Class<PagamentoBorsellinoDAO> clazz = (Class<PagamentoBorsellinoDAO>)Class.forName(PAGAMENTO_BORSELLINO_CLASS);
			Constructor<PagamentoBorsellinoDAO> constructor = clazz.getConstructor(new Class[] {java.sql.Connection.class, java.lang.String.class});
			pagamentoBorsellinoDAO = constructor.newInstance(connection,schema);
		} catch (Exception x) {
			throw new DaoException(x);
		}
		return pagamentoBorsellinoDAO;
	}
	//fine LP PG21XX04 Leak

	private static String ADDEBITI_BORSELLINO_CLASS;

	static {
		ADDEBITI_BORSELLINO_CLASS = AddebitiBorsellinoDAOImpl.class.getName();
	}
	//inizio LP PG21XX04 Leak
	@Deprecated
	//fine LP PG21XX04 Leak
	@SuppressWarnings("unchecked")
	public static AddebitiBorsellinoDAO getAddebitiBorsellinoDAO(DataSource datasource, String schema) throws DaoException {
		AddebitiBorsellinoDAO addebitiBorsellinoDAO = null;
		try {
			Class<AddebitiBorsellinoDAO> clazz = (Class<AddebitiBorsellinoDAO>)Class.forName(ADDEBITI_BORSELLINO_CLASS);
			Constructor<AddebitiBorsellinoDAO> constructor = clazz.getConstructor(new Class[] {javax.sql.DataSource.class, java.lang.String.class});
			addebitiBorsellinoDAO = constructor.newInstance(datasource,schema);
		} catch (Exception x) {
			throw new DaoException(x);
		}
		return addebitiBorsellinoDAO;
	}

	//inizio LP PG21XX04 Leak
	@SuppressWarnings("unchecked")
	public static AddebitiBorsellinoDAO getAddebitiBorsellinoDAO(Connection connection, String schema) throws DaoException {
		AddebitiBorsellinoDAO addebitiBorsellinoDAO = null;
		try {
			Class<AddebitiBorsellinoDAO> clazz = (Class<AddebitiBorsellinoDAO>)Class.forName(ADDEBITI_BORSELLINO_CLASS);
			Constructor<AddebitiBorsellinoDAO> constructor = clazz.getConstructor(new Class[] {java.sql.Connection.class, java.lang.String.class});
			addebitiBorsellinoDAO = constructor.newInstance(connection,schema);
		} catch (Exception x) {
			throw new DaoException(x);
		}
		return addebitiBorsellinoDAO;
	}
	//fine LP PG21XX04 Leak

	private static String IMPUTA_PAGAMENTI_CLASS;

	static {
		IMPUTA_PAGAMENTI_CLASS = ImputaPagamentiDAOImpl.class.getName();
	}
	//inizio LP PG21XX04 Leak
	@Deprecated
	//fine LP PG21XX04 Leak
	@SuppressWarnings("unchecked")
	public static ImputaPagamentiDAO getImputaPagamentiDAO(DataSource datasource, String schema) throws DaoException {
		ImputaPagamentiDAO imputaPagamentiDAO = null;
		try {
			Class<ImputaPagamentiDAO> clazz = (Class<ImputaPagamentiDAO>)Class.forName(IMPUTA_PAGAMENTI_CLASS);
			Constructor<ImputaPagamentiDAO> constructor = clazz.getConstructor(new Class[] {javax.sql.DataSource.class, java.lang.String.class});
			imputaPagamentiDAO = constructor.newInstance(datasource,schema);
		} catch (Exception x) {
			throw new DaoException(x);
		}
		return imputaPagamentiDAO;
	}

	//inizio LP PG21XX04 Leak
	@SuppressWarnings("unchecked")
	public static ImputaPagamentiDAO getImputaPagamentiDAO(Connection connection, String schema) throws DaoException {
		ImputaPagamentiDAO imputaPagamentiDAO = null;
		try {
			Class<ImputaPagamentiDAO> clazz = (Class<ImputaPagamentiDAO>)Class.forName(IMPUTA_PAGAMENTI_CLASS);
			Constructor<ImputaPagamentiDAO> constructor = clazz.getConstructor(new Class[] {java.sql.Connection.class, java.lang.String.class});
			imputaPagamentiDAO = constructor.newInstance(connection,schema);
		} catch (Exception x) {
			throw new DaoException(x);
		}
		return imputaPagamentiDAO;
	}
	//fine LP PG21XX04 Leak

	private static String ABILITAZIONE_DISCARICO_CLASS;
	static {
		ABILITAZIONE_DISCARICO_CLASS = AbilitazioneDiscaricoDAOImpl.class.getName();
	}
	//inizio LP PG21XX04 Leak
	@Deprecated
	//fine LP PG21XX04 Leak
	@SuppressWarnings("unchecked")
	public static AbilitazioneDiscaricoDAO getAbilitazioneDiscarico(DataSource datasource, String schema) throws DaoException {
		AbilitazioneDiscaricoDAO abilitazioneDiscaricoDAO=null;
		try{
			Class<AbilitazioneDiscaricoDAO> clazz = (Class<AbilitazioneDiscaricoDAO>)Class.forName(ABILITAZIONE_DISCARICO_CLASS);
			Constructor<AbilitazioneDiscaricoDAO> constructor = clazz.getConstructor(new Class[] {javax.sql.DataSource.class, java.lang.String.class});
			abilitazioneDiscaricoDAO = constructor.newInstance(datasource,schema);
		}catch (Exception x) {
			throw new DaoException(x);
		}
		return abilitazioneDiscaricoDAO;
	}

	//inizio LP PG21XX04 Leak
	@SuppressWarnings("unchecked")
	public static AbilitazioneDiscaricoDAO getAbilitazioneDiscarico(Connection connection, String schema) throws DaoException {
		AbilitazioneDiscaricoDAO abilitazioneDiscaricoDAO=null;
		try{
			Class<AbilitazioneDiscaricoDAO> clazz = (Class<AbilitazioneDiscaricoDAO>)Class.forName(ABILITAZIONE_DISCARICO_CLASS);
			Constructor<AbilitazioneDiscaricoDAO> constructor = clazz.getConstructor(new Class[] {java.sql.Connection.class, java.lang.String.class});
			abilitazioneDiscaricoDAO = constructor.newInstance(connection,schema);
		}catch (Exception x) {
			throw new DaoException(x);
		}
		return abilitazioneDiscaricoDAO;
	}
	//fine LP PG21XX04 Leak

	private static String SEPA_CLASS;

	static {
		SEPA_CLASS = SepaDAOImpl.class.getName();
	}
	//inizio LP PG21XX04 Leak
	@Deprecated
	//fine LP PG21XX04 Leak
	@SuppressWarnings("unchecked")
	public static SepaDAO getSepaDAO(DataSource datasource, String schema) throws DaoException {
		SepaDAO sepaDAO = null;
		try {
			Class<SepaDAO> clazz = (Class<SepaDAO>)Class.forName(SEPA_CLASS);
			Constructor<SepaDAO> constructor = clazz.getConstructor(new Class[] {javax.sql.DataSource.class, java.lang.String.class});
			sepaDAO = constructor.newInstance(datasource,schema);
		} catch (Exception x) {
			throw new DaoException(x);
		}
		return sepaDAO;
	}

	// Connection connection, String schema, boolean isRest, String baseUrl
	public static SepaDAO getSepaDAO(Connection connection, String schema, boolean isRest, String baseUrl) throws DaoException {
		SepaDAO sepaDAO = null;
		try {
			Class<SepaDAO> clazz = (Class<SepaDAO>)Class.forName(SEPA_CLASS);
			Constructor<SepaDAO> constructor = clazz.getConstructor(new Class[] {java.sql.Connection.class, java.lang.String.class, boolean.class, java.lang.String.class});
			sepaDAO = constructor.newInstance(connection,schema,isRest,baseUrl);
		} catch (Exception x) {
			throw new DaoException(x);
		}
		return sepaDAO;
	}

	//inizio LP PG21XX04 Leak
	@SuppressWarnings("unchecked")
	public static SepaDAO getSepaDAO(Connection connection, String schema) throws DaoException {
		SepaDAO sepaDAO = null;
		try {
			Class<SepaDAO> clazz = (Class<SepaDAO>)Class.forName(SEPA_CLASS);
			Constructor<SepaDAO> constructor = clazz.getConstructor(new Class[] {java.sql.Connection.class, java.lang.String.class});
			sepaDAO = constructor.newInstance(connection, schema);
		} catch (Exception x) {
			throw new DaoException(x);
		}
		return sepaDAO;
	}
	//fine LP PG21XX04 Leak

	private static String GESTIONE_AVVISI_CLASS;
	static {
		GESTIONE_AVVISI_CLASS = GestioneAvvisiDAOImpl.class.getName();
	}
	//inizio LP PG21XX04 Leak
	@Deprecated
	//fine LP PG21XX04 Leak
	@SuppressWarnings("unchecked")
	public static GestioneAvvisiDAO getGestioneAvvisi(DataSource datasource, String schema) throws DaoException {
		GestioneAvvisiDAO gestioneAvvisiDAO=null;
		try{
			Class<GestioneAvvisiDAO> clazz = (Class<GestioneAvvisiDAO>)Class.forName(GESTIONE_AVVISI_CLASS);
			Constructor<GestioneAvvisiDAO> constructor = clazz.getConstructor(new Class[] {javax.sql.DataSource.class, java.lang.String.class});
			gestioneAvvisiDAO = constructor.newInstance(datasource,schema);
		}catch (Exception x) {
			throw new DaoException(x);
		}
		return gestioneAvvisiDAO;
	}

	//inizio LP PG21XX04 Leak
	@SuppressWarnings("unchecked")
	public static GestioneAvvisiDAO getGestioneAvvisi(Connection connection, String schema) throws DaoException {
		GestioneAvvisiDAO gestioneAvvisiDAO=null;
		try{
			Class<GestioneAvvisiDAO> clazz = (Class<GestioneAvvisiDAO>)Class.forName(GESTIONE_AVVISI_CLASS);
			Constructor<GestioneAvvisiDAO> constructor = clazz.getConstructor(new Class[] {java.sql.Connection.class, java.lang.String.class});
			gestioneAvvisiDAO = constructor.newInstance(connection, schema);
		}catch (Exception x) {
			throw new DaoException(x);
		}
		return gestioneAvvisiDAO;
	}
	//fine LP PG21XX04 Leak

}
