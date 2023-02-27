package com.seda.payer.core.mercato.dao;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.sql.CallableStatement;
import java.sql.Connection;
//import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.TimeZone;

import javax.sql.DataSource;
import javax.sql.rowset.CachedRowSet;

import com.seda.commons.security.TokenGenerator;
import com.seda.commons.string.Convert;
import com.seda.data.dao.DAOHelper;
import com.seda.data.helper.Helper;
import com.seda.data.helper.HelperException;
//import com.seda.data.spi.PageInfo;
import com.seda.payer.core.dao.Routines;
import com.seda.payer.core.exception.DaoException;
import com.seda.payer.core.handler.BaseDaoHandler;
import com.seda.payer.core.mercato.bean.ConfigurazioneTariffe;
import com.seda.payer.core.mercato.bean.EsitoRisposte;
//import com.seda.payer.core.mercato.bean.MercatoPageList;
import com.seda.payer.core.mercato.dao.MercatoDAO;
import com.seda.payer.core.mercato.bean.ConfigurazionePrenotazioni;

public class ConfigurazionePrenotazioniDAOImpl extends BaseDaoHandler  implements ConfigurazionePrenotazioniDAO  {
	private static final long serialVersionUID = 1L;
	//inizio LP PG21XX04 Leak
	@Deprecated
	//fine LP PG21XX04 Leak
	public ConfigurazionePrenotazioniDAOImpl(DataSource dataSource, String schema) throws SQLException {
		super(dataSource.getConnection(), schema);
	}
	
	//inizio LP PG21XX04 Leak
	public ConfigurazionePrenotazioniDAOImpl(Connection connection, String schema) throws SQLException {
		super(connection, schema);
	}
	//fine LP PG21XX04 Leak

	public ArrayList<ConfigurazionePrenotazioni> getAllPerTariffa(ConfigurazionePrenotazioni configurazionePrenotazioni) throws DaoException{
//		IN I_PRN_CPRNKTAM VARCHAR(64)
		CallableStatement callableStatement=null;
		ArrayList<ConfigurazionePrenotazioni> configurazionePrenotazioniList = null;
		Connection connection = null;
		ResultSet resultSet=null;
		try {
			connection = getConnection();
			callableStatement = Helper.prepareCall(connection, getSchema(), Routines.PYPRNSP_SLT.routine());
			callableStatement.setString(1, configurazionePrenotazioni.getCodiceKeyTariffa());
			callableStatement.execute();
			resultSet=callableStatement.getResultSet();
			
			if ( !resultSet.next() ) {
				configurazionePrenotazioniList = new ArrayList<ConfigurazionePrenotazioni>();
			} else {
				configurazionePrenotazioniList = new ArrayList<ConfigurazionePrenotazioni>();
				do {
					ConfigurazionePrenotazioni item = new ConfigurazionePrenotazioni();
					item.setCodiceKeyTariffa(resultSet.getString("PRN_CPRNKTAM"));
					item.setImportoPagato(resultSet.getDouble("PRN_DPRNIMPO"));
					GregorianCalendar DataPren = new GregorianCalendar();
					DataPren.setTimeInMillis(resultSet.getTimestamp("PRN_GPRNDATA").getTime());
					item.setDataPrenotazione(DataPren);
					configurazionePrenotazioniList.add(item);
				} while(resultSet.next());
			}
		} catch (SQLException e) {
			throw new DaoException(e);
		} catch (IllegalArgumentException e) {
			throw new DaoException(e);
		} catch (HelperException e) {
			throw new DaoException(e);
		} finally {
			//inizio LP PG21XX04 Leak
			//DAOHelper.closeIgnoringException(connection);
			if (resultSet != null) {
				try {
					resultSet.close();
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
			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			//fine LP PG21XX04 Leak
		} 
		
		return configurazionePrenotazioniList;
		
	}
	public EsitoRisposte schedulaPrenotazioni(String codiceKeyTariffa, String codSocieta, String codUte, String codEnte, Calendar dataInizioVal, Calendar dataFineVal, int giornoSett, String dateEscluse) 
	         throws DaoException {
		EsitoRisposte esitoRisposte = new EsitoRisposte();
		Calendar dataPren = Calendar.getInstance(TimeZone.getTimeZone("Europe/Rome"));
		dataPren = dataInizioVal;
		boolean ciclo = true;
		int gapIni=0;
		int day = dataInizioVal.get(dataInizioVal.DAY_OF_WEEK) - 1;
		if (day == 0) day = 7;
		if (day==giornoSett) {
			gapIni=0;
		} else if (day>giornoSett) {
			gapIni = (7 - day) + giornoSett;
		} else if (day<giornoSett) {
			gapIni = giornoSett - day;
		}
		dataPren.add(Calendar.DATE, gapIni);
		if (dataPren.compareTo(dataFineVal)>0) {
			ciclo=false;
			esitoRisposte.setCodiceMessaggio("KO");
			esitoRisposte.setDescrizioneMessaggio("Schedulazione non effettuata per date incongruenti (data fine inferiore a data inizio)");
		}
		Connection conn = null;
		conn = getConnection();
		//inizio LP PG21XX04 Leak
		//Statement stmt;
		Statement stmt = null;
		try {
		//fine LP PG21XX04 Leak
		try {
			stmt = conn.createStatement();
		} catch (SQLException e) {
			esitoRisposte.setCodiceMessaggio("KO");
			esitoRisposte.setDescrizioneMessaggio("Schedulazione non effettuata per problemi interni");
			e.printStackTrace();
			throw new DaoException(e);	
		}
		while (ciclo) {
			String codiceKeyPrenotazione = "";
			DateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
			try {
				codiceKeyPrenotazione = TokenGenerator.generateUUIDToken();
			} catch (NoSuchAlgorithmException e) {
				e.printStackTrace();
			}
				
			String Conf = df.format(dataPren.getTime());
			boolean Insert = true;
			if (dateEscluse!=null && !(dateEscluse.equals(""))) {
				String[] DateEscl = dateEscluse.split(";");
				for (String dataEsc : DateEscl) {
					if (dataEsc.equals(Conf)) {
						Insert = false;
					}
				}
			}
			if (Insert) {
				try {
					String SQL = "INSERT INTO PYPRNTB VALUES ('" + codiceKeyPrenotazione + "', '"
					+ codSocieta + "', '" + codUte + "', '" + codEnte + "', '"  + codiceKeyTariffa + "', '"
					+ df.format(dataPren.getTime()) + "', 0.0, 0.0, 0.0, 0.0, 0.0, null)";
					stmt.addBatch(SQL);
				} catch (SQLException e) {
					e.printStackTrace();
					esitoRisposte.setCodiceMessaggio("KO");
					esitoRisposte.setDescrizioneMessaggio("Errore in fase di inserimento schedulazione");
					throw new DaoException(e);			
				}
			}
			dataPren.add(Calendar.DATE, 7);
			if (dataPren.compareTo(dataFineVal)>0) {
				ciclo=false;
			}
		}
		try {
			int[] Count = stmt.executeBatch();
			int tot = 0;
			for (int i=0;i<Count.length;i++) {
				tot=tot+Count[i];
			}
			if (tot>0) {
				conn.commit();
				esitoRisposte.setCodiceMessaggio("OK");
				esitoRisposte.setDescrizioneMessaggio("Schedulazione Effettuata");
			} else {
				if (esitoRisposte.getCodiceMessaggio().equals("OK")) {
					esitoRisposte.setCodiceMessaggio("KO");
					esitoRisposte.setDescrizioneMessaggio("Schedulazione non inserita");
				}
			}
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			while (e != null) {
				e.printStackTrace();
				e = e.getNextException();
			}
			esitoRisposte.setCodiceMessaggio("KO");
			esitoRisposte.setDescrizioneMessaggio("Errore in fase di inserimento schedulazione");			
			throw new DaoException(e);
		} 

		return esitoRisposte;
		//inizio LP PG21XX04 Leak
		} catch (Exception e) {
			throw new DaoException(e);
		}
		finally {
			if (stmt != null) {
				try {
					stmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		//fine LP PG21XX04 Leak
	}
	
	public EsitoRisposte schedulaPrenotazioniDaPiazzola(String codiceSocieta, String codiceUtente, String codiceEnte, String codiceMercato, String codicePiazzola, Calendar dataInizioVal, Calendar dataFineVal, int giornoSett)
			throws DaoException {
		EsitoRisposte  esitoRisposte = new EsitoRisposte();
		//Recupero Tariffa da Cod Piazzola + Cod. Mercato
		Connection conn = null;
		conn = getConnection();
		//inizio LP PG21XX04 Leak
		//Statement stmt;
		Statement stmt = null;
		ResultSet rs = null;
		try {
		//fine LP PG21XX04 Leak
		boolean esito=true;
		String msg ="";
		ArrayList <String> CodKeyTar = new ArrayList<String>();
		String SQL = "SELECT TAM_KTAMKTAM FROM PYTAMTB WHERE TAM_CTAMKPZL IN"
		   + "(SELECT DISTINCT PZL_KPZLKPZL FROM PYPZLTB WHERE PZL_CSOCCSOC = '" + codiceSocieta + "' AND PZL_CUTECUTE = '"
		   + codiceUtente + "' AND PZL_KANEKENT='" + codiceEnte + "' AND PZL_CPZLKMRC = '" + codiceMercato 
		   + "' AND PZL_CPZLCTIP = '" + codicePiazzola + "' AND (PZL_GPZLDTFN IS NULL OR PZL_GPZLDTFN > CURRENT TIMESTAMP))"
		   + " AND TAM_ITAMGSEM = " + giornoSett;
		try {
			stmt = conn.createStatement();
			//inizio LP PG21XX04 Leak
			//ResultSet rs = stmt.executeQuery(SQL);
			rs = stmt.executeQuery(SQL);
			//fine LP PG21XX04 Leak
			while (rs.next()) {
				CodKeyTar.add(rs.getString("TAM_KTAMKTAM"));
			}
			stmt.close();
		} catch (SQLException e) {
			esitoRisposte.setCodiceMessaggio("KO");
			esitoRisposte.setDescrizioneMessaggio("Schedulazione non effettuata per problemi interni");
			e.printStackTrace();
			throw new DaoException(e);	
		}
		Calendar dataPren = dataInizioVal;
		Calendar dataIniValSave = Calendar.getInstance();
		dataIniValSave.setTimeInMillis(dataIniValSave.getTimeInMillis());
		boolean ciclo = true;
		int gapIni=0;
		int day = dataInizioVal.get(dataInizioVal.DAY_OF_WEEK) - 1;
		if (day == 0) day = 7;
		if (day==giornoSett) {
			gapIni=0;
		} else if (day>giornoSett) {
			gapIni = (7 - day) + giornoSett;
		} else if (day<giornoSett) {
			gapIni = giornoSett - day;
		}
	//Ciclo
		for (int i=0; i<CodKeyTar.size();i++) {
			ciclo=true;
			dataPren.setTimeInMillis(dataIniValSave.getTimeInMillis());
			dataPren.add(Calendar.DATE, gapIni);		
			if (dataPren.compareTo(dataFineVal)>0) {
				ciclo=false;
				esito=false;
				msg="Schedulazione non effettuata per date incongrunti (data fine inferiore a data inizio)";
			}			
			try {
				stmt = conn.createStatement();
			} catch(SQLException e) {
				esito=false;
				if (msg.equals("")) msg="Schedulazione non inserita per errore interno";
			}
			while (ciclo) {
				String codiceKeyPrenotazione = "";
				DateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
				try {
					codiceKeyPrenotazione = TokenGenerator.generateUUIDToken();
				} catch (NoSuchAlgorithmException e) {
					e.printStackTrace();
				}
				try {
					SQL = "INSERT INTO PYPRNTB VALUES ('" + codiceKeyPrenotazione + "', '"
					     + codiceSocieta + "', '" + codiceUtente + "', '" + codiceEnte + "', '"  + CodKeyTar.get(i) + "', '"
					     + df.format(dataPren.getTime()) + "', 0.0, 0.0, 0.0, 0.0, 0.0, NULL)";
					stmt.addBatch(SQL);
					dataPren.add(Calendar.DATE, 7);
					if (dataPren.compareTo(dataFineVal)>0) {
						ciclo=false;
					}
				} catch (SQLException e) {
					e.printStackTrace();
					esito=false;
					if (msg.equals("")) msg="Errore in fase di inserimento schedulazione";
					throw new DaoException(e);			
				}
			}
			try {
				int[] Count = stmt.executeBatch();
				int tot = 0;
				for (int a=0;a<Count.length;a++) {
					tot=tot+Count[a];
				}
				if (!(tot>0)) {
					esito=false;
					if (msg.equals("")) msg="Schedulazione non inserita";
				}
				stmt.close();
			} catch (SQLException e) {
				while (e != null) {
					e.printStackTrace();
					e = e.getNextException();
				}
				esito=false;
				msg="Errore in fase di inserimento schedulazione";			
				throw new DaoException(e);
			} 
//Fine ciclo 			
		}
		if (esito) {
			esitoRisposte.setCodiceMessaggio("OK");
			try {
				conn.commit();
			} catch(SQLException ex) {
				ex.printStackTrace();
			}
		} else {
			esitoRisposte.setCodiceMessaggio("KO");
			esitoRisposte.setDescrizioneMessaggio(msg);
		}

		return esitoRisposte;
		//inizio LP PG21XX04 Leak
		} catch (Exception e) {
			throw new DaoException(e);
		}
		finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (stmt != null) {
				try {
					stmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		//fine LP PG21XX04 Leak
	}

	public EsitoRisposte schedulaPrenotazioniDaKeyPiazzola(String codiceKeyPiazzola, String codiceKeyAreaMerc, String codiceSocieta, String codiceUtente, String codiceEnte, Calendar dataInizioVal, Calendar dataFineVal, int giornoSett)
		throws DaoException {
		EsitoRisposte  esitoRisposte = new EsitoRisposte();
		//Recupero Tariffa da Codice Key Piazzola
		Connection conn = null;
		conn = getConnection();
		//inizio LP PG21XX04 Leak
		//Statement stmt;
		Statement stmt = null;
		ResultSet rs = null;
		try {
		//fine LP PG21XX04 Leak
		boolean esito=true;
		String msg ="";
		ArrayList <String> CodKeyTar = new ArrayList<String>();
		String SQL = "SELECT TAM_KTAMKTAM FROM PYTAMTB WHERE TAM_CTAMKMRC = '" + codiceKeyAreaMerc
		   + "' AND TAM_CTAMKPZL = '" + codiceKeyPiazzola 
		   + "' AND TAM_ITAMGSEM = " + giornoSett;
		try {
			stmt = conn.createStatement();
			//inizio LP PG21XX04 Leak
			//ResultSet rs = stmt.executeQuery(SQL);
			rs = stmt.executeQuery(SQL);
			//fine LP PG21XX04 Leak
			while (rs.next()) {
				CodKeyTar.add(rs.getString("TAM_KTAMKTAM"));
			}
			stmt.close();
		} catch (SQLException e) {
			esitoRisposte.setCodiceMessaggio("KO");
			esitoRisposte.setDescrizioneMessaggio("Schedulazione non effettuata per problemi interni");
			e.printStackTrace();
			throw new DaoException(e);	
		}
		Calendar dataPren = dataInizioVal;
		Calendar dataIniValSave = Calendar.getInstance();
		dataIniValSave.setTimeInMillis(dataInizioVal.getTimeInMillis());
		boolean ciclo = true;
		int gapIni=0;
		int day = dataInizioVal.get(dataInizioVal.DAY_OF_WEEK) - 1;
		if (day == 0) day = 7;
		if (day==giornoSett) {
			gapIni=0;
		} else if (day>giornoSett) {
			gapIni = (7 - day) + giornoSett;
		} else if (day<giornoSett) {
			gapIni = giornoSett - day;
		}
	//Ciclo
		for (int i=0; i<CodKeyTar.size();i++) {
			ciclo=true;
			dataPren.setTimeInMillis(dataIniValSave.getTimeInMillis());
			dataPren.add(Calendar.DATE, gapIni);		
			if (dataPren.compareTo(dataFineVal)>0) {
				ciclo=false;
				esito=false;
				msg="Schedulazione non effettuata per date incongrunti (data fine inferiore a data inizio)";
			}			
			try {
				stmt = conn.createStatement();
			} catch(SQLException e) {
				esito=false;
				if (msg.equals("")) msg="Schedulazione non inserita per errore interno";
			}
			while (ciclo) {
				String codiceKeyPrenotazione = "";
				DateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
				try {
					codiceKeyPrenotazione = TokenGenerator.generateUUIDToken();
				} catch (NoSuchAlgorithmException e) {
					e.printStackTrace();
				}
				try {
					SQL = "INSERT INTO PYPRNTB VALUES ('" + codiceKeyPrenotazione + "', '"
					     + codiceSocieta + "', '" + codiceUtente + "', '" + codiceEnte + "', '"  + CodKeyTar.get(i) + "', '"
					     + df.format(dataPren.getTime()) + "', 0.0, 0.0, 0.0, 0.0, 0.0, NULL)";
					stmt.addBatch(SQL);
					dataPren.add(Calendar.DATE, 7);
					if (dataPren.compareTo(dataFineVal)>0) {
						ciclo=false;
					}
				} catch (SQLException e) {
					e.printStackTrace();
					esito=false;
					if (msg.equals("")) msg="Errore in fase di inserimento schedulazione";
					throw new DaoException(e);			
				}
			}
			try {
				int[] Count = stmt.executeBatch();
				int tot = 0;
				for (int a=0;a<Count.length;a++) {
					tot=tot+Count[a];
				}
				if (!(tot>0)) {
					esito=false;
					if (msg.equals("")) msg="Schedulazione non inserita";
				}
				stmt.close();
			} catch (SQLException e) {
				while (e != null) {
					e.printStackTrace();
					e = e.getNextException();
				}
				esito=false;
				msg="Errore in fase di inserimento schedulazione";			
				throw new DaoException(e);
			} 
	//Fine ciclo 			
		}
		if (esito) {
			esitoRisposte.setCodiceMessaggio("OK");
			try {
				conn.commit();
			} catch(SQLException ex) {
				ex.printStackTrace();
			}
		} else {
			esitoRisposte.setCodiceMessaggio("KO");
			esitoRisposte.setDescrizioneMessaggio(msg);
		}
		
		return esitoRisposte;
		//inizio LP PG21XX04 Leak
		} catch (Exception e) {
			throw new DaoException(e);
		}
		finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (stmt != null) {
				try {
					stmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		//fine LP PG21XX04 Leak
	}	
	
	public EsitoRisposte delete(ConfigurazionePrenotazioni prenotazioni)
			throws DaoException 
	{
		CallableStatement callableStatement=null;
		Connection connection = null;
		EsitoRisposte  esitoRisposte = new EsitoRisposte();
		try {
			connection = getConnection();
			callableStatement = Helper.prepareCall(connection, getSchema(), Routines.PYPRNSP_DEL.routine());
//			IN I_PRN_CSOCCSOC VARCHAR(5), 
//			IN I_PRN_CUTECUTE VARCHAR(5),
//			IN I_PRN_KANEKENT CHAR(10),
//			IN I_PRN_CPRNKTAM VARCHAR(64),
//			IN I_PRN_GPRNDATA TIMESTAMP,
//			OUT O_PRN_CODESITO VARCHAR(2),
//			OUT O_PRN_MSGESITO VARCHAR(100)

			callableStatement.setString(1, prenotazioni.getCodiceSocieta());
			callableStatement.setString(2, prenotazioni.getCuteCute());
			callableStatement.setString(3, prenotazioni.getChiaveEnte());
			callableStatement.setString(4, prenotazioni.getCodiceKeyTariffa());
			callableStatement.setTimestamp(5, new java.sql.Timestamp(prenotazioni.getDataPrenotazione().getTimeInMillis()));
			callableStatement.registerOutParameter(6, Types.VARCHAR);
			callableStatement.registerOutParameter(7, Types.VARCHAR);			
			callableStatement.execute();
			esitoRisposte.setCodiceMessaggio(callableStatement.getString(6));
			esitoRisposte.setDescrizioneMessaggio(callableStatement.getString(7));
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DaoException(e);
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
			throw new DaoException(e);
		} catch (HelperException e) {
			e.printStackTrace();
			throw new DaoException(e);
		} finally {
			//inizio LP PG21XX04 Leak
			//DAOHelper.closeIgnoringException(connection);
			if (callableStatement != null) {
				try {
					callableStatement.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			//fine LP PG21XX04 Leak
		}
		return esitoRisposte;	
	}

	public EsitoRisposte deletePerRange(ConfigurazionePrenotazioni prenotazioni)
			throws DaoException 
	{
		CallableStatement callableStatement=null;
		Connection connection = null;
		EsitoRisposte  esitoRisposte = new EsitoRisposte();
		try {
			connection = getConnection();
			callableStatement = Helper.prepareCall(connection, getSchema(), Routines.PYPRNSP_DLK.routine());
//	IN I_PRN_CPRNKTAM VARCHAR(64), 
//	IN I_PRN_GPRNDATA_INI TIMESTAMP,
//  IN I_PRN_GPRNDATA_FIN TIMESTAMP,
//	OUT O_PRN_CODESITO VARCHAR(2),
//	OUT O_PRN_MSGESITO VARCHAR(100)

			callableStatement.setString(1, prenotazioni.getCodiceKeyTariffa());
			callableStatement.setTimestamp(2, new java.sql.Timestamp(prenotazioni.getDataPrenotazione().getTimeInMillis()));
			callableStatement.setTimestamp(3, new java.sql.Timestamp(prenotazioni.getDataPrenotazioneAl().getTimeInMillis()));
			callableStatement.registerOutParameter(4, Types.VARCHAR);
			callableStatement.registerOutParameter(5, Types.VARCHAR);			
			callableStatement.execute();
			esitoRisposte.setCodiceMessaggio(callableStatement.getString(4));
			esitoRisposte.setDescrizioneMessaggio(callableStatement.getString(5));
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DaoException(e);
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
			throw new DaoException(e);
		} catch (HelperException e) {
			e.printStackTrace();
	throw new DaoException(e);
		} finally {
			//inizio LP PG21XX04 Leak
			//DAOHelper.closeIgnoringException(connection);
			if (callableStatement != null) {
				try {
					callableStatement.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			//fine LP PG21XX04 Leak
		}
		return esitoRisposte;	
	}

	
	public EsitoRisposte insert(ConfigurazionePrenotazioni prenotazioni)
		throws DaoException 
	{
		CallableStatement callableStatement=null;
		Connection connection = null;
		EsitoRisposte esitoRisposte = new EsitoRisposte();
		try {
			connection = getConnection();
			callableStatement = Helper.prepareCall(connection, getSchema(), Routines.PYPRNSP_INS.routine());
//					IN I_PRN_KPRNKPRN VARCHAR(64),
//					IN I_PRN_CSOCCSOC VARCHAR(5), 
//					IN I_PRN_CUTECUTE VARCHAR(5),
//					IN I_PRN_KANEKENT CHAR(10),
//					IN I_PRN_CPRNKTAM VARCHAR(64),
//					IN I_PRN_GPRNDATA TIMESTAMP,
//					IN I_PRN_DPRNIMPO DECIMAL(9,3),		
//					OUT O_PRN_CODESITO VARCHAR(2),
//					OUT O_PRN_MSGESITO VARCHAR(100)
//			private String codiceKeyPrenotazioni  		//  "PRN_KPRNKPRN" VARCHAR(64)
//			private String codiceSocieta;        		//	"PRN_CSOCCSOC" VARCHAR(5)
//			private String cuteCute;					//	"PRN_CUTECUTE" VARCHAR(5)
//			private String chiaveEnte;					//	"PRN_KANEKENT" CHAR(10)
//			private String codiceKeyTariffa;  			//	"PRN_CPRNKTAM" VARCHAR(64)
// 			private Calendar dataPrenotazione;			//	"PRN_GPRNDATA" TIMESTAMP
//			private Double importoPagato;				//  "PRN_DPRNIMPO" DECIMAL(9,3)
			
			//Chiave primaria autogenerata da classe
			String chiavePrenotazione = "";
			try {
				chiavePrenotazione = TokenGenerator.generateUUIDToken();
			} catch (NoSuchAlgorithmException e) {
				e.printStackTrace();
			}	
			callableStatement.setString(1, chiavePrenotazione);
			callableStatement.setString(2, prenotazioni.getCodiceSocieta());
			callableStatement.setString(3, prenotazioni.getCuteCute());
			callableStatement.setString(4, prenotazioni.getChiaveEnte());
			callableStatement.setString(5, prenotazioni.getCodiceKeyTariffa());
			callableStatement.setTimestamp(6, new java.sql.Timestamp(prenotazioni.getDataPrenotazione().getTimeInMillis()));
			if (prenotazioni.getImportoPagato() == null) {
				callableStatement.setDouble(7, 0.0);
			} else {
				callableStatement.setDouble(7, prenotazioni.getImportoPagato());
			}
			callableStatement.registerOutParameter(8, Types.VARCHAR);
			callableStatement.registerOutParameter(9, Types.VARCHAR);			
			callableStatement.execute();
			esitoRisposte.setCodiceMessaggio(callableStatement.getString(8));
			esitoRisposte.setDescrizioneMessaggio(callableStatement.getString(9));
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DaoException(e);
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
			throw new DaoException(e);
		} catch (HelperException e) {
			e.printStackTrace();
			throw new DaoException(e);
		} finally {
			//inizio LP PG21XX04 Leak
			//DAOHelper.closeIgnoringException(connection);
			if (callableStatement != null) {
				try {
					callableStatement.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			//fine LP PG21XX04 Leak
		}
		return esitoRisposte;
	}

	public ConfigurazionePrenotazioni select(ConfigurazionePrenotazioni prenotazioni)
			throws DaoException 
	{
		CallableStatement callableStatement=null;
		ResultSet resultSet=null;
		Connection connection = null;
		CachedRowSet rowSet = null;
		try {
			connection = getConnection();
//			IN I_PRN_CSOCCSOC VARCHAR(5), 
//			IN I_PRN_CUTECUTE VARCHAR(5),
//			IN I_PRN_KANEKENT CHAR(10),
//			IN I_PRN_CPRNKTAM VARCHAR(64),
//			IN I_PRN_GPRNDATA TIMESTAMP
			callableStatement = Helper.prepareCall(connection, getSchema(), Routines.PYPRNSP_SEL.routine());
			callableStatement.setString(1, prenotazioni.getCodiceSocieta());
			callableStatement.setString(2, prenotazioni.getCuteCute());
			callableStatement.setString(3, prenotazioni.getChiaveEnte());
			callableStatement.setString(4, prenotazioni.getCodiceKeyTariffa());
			callableStatement.setTimestamp(5,new java.sql.Timestamp(prenotazioni.getDataPrenotazione().getTimeInMillis()));
			callableStatement.execute();
			
			resultSet=callableStatement.getResultSet();
			loadWebRowSet(resultSet);
			String selectXml = getWebRowSetXml();
			try {
				rowSet = Convert.stringToWebRowSet(selectXml);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			if (rowSet.next() ) {
				 String codiceKeyPrenotazioni = rowSet.getString(1);
				 String codiceSocieta = rowSet.getString(2);       		
				 String cuteCute = rowSet.getString(3);					
				 String chiaveEnte = rowSet.getString(4);
				 String codiceKeyTariffa = rowSet.getString(5);
				 GregorianCalendar dataPrenotazione = new GregorianCalendar();
				 dataPrenotazione.setTimeInMillis(rowSet.getTimestamp(6).getTime());
				 Double importoPagato = rowSet.getDouble(7);
				 prenotazioni = new ConfigurazionePrenotazioni(
				         codiceKeyPrenotazioni, codiceSocieta, cuteCute, chiaveEnte, codiceKeyTariffa, dataPrenotazione, importoPagato, null);
				 prenotazioni.setAttribute(MercatoDAO.SELECT_XML, selectXml);
			} 
			
		} catch (SQLException e) {
			throw new DaoException(e);
		} catch (IllegalArgumentException e) {
			throw new DaoException(e);
		} catch (HelperException e) {
			throw new DaoException(e);
		}finally {
			//inizio LP PG21XX04 Leak
			//DAOHelper.closeIgnoringException(connection);
			if (rowSet != null) {
				try {
					rowSet.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (resultSet != null) {
				try {
					resultSet.close();
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
			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			//fine LP PG21XX04 Leak
		}
		return prenotazioni;
	}

	
	public Integer update(ConfigurazionePrenotazioni prenotazioni)
		throws DaoException 
	{
		CallableStatement callableStatement=null;
		Connection connection = null;
		int ret=0;
		try {
			connection = getConnection();
			callableStatement = Helper.prepareCall(connection, getSchema(), Routines.PYPRNSP_UPD.routine());

//			IN I_PRN_CSOCCSOC VARCHAR(5), 
//			IN I_PRN_CUTECUTE VARCHAR(5),
//			IN I_PRN_KANEKENT CHAR(10),
//			IN I_PRN_CPRNKTAM VARCHAR(64),
//			IN I_PRN_GPRNDATA TIMESTAMP,
//			IN I_PRN_DPRNIMPO DECIMAL(9,3),
//			OUT O_TOTROWS INTEGER
//			private String codiceSocieta;        		//	"PRN_CSOCCSOC" VARCHAR(5)
//			private String cuteCute;					//	"PRN_CUTECUTE" VARCHAR(5)
//			private String chiaveEnte;					//	"PRN_KANEKENT" CHAR(10)
//			private String codiceKeyTariffa;			//	"PRN_CPRNKTAM" VARCHAR(64)
//			private Calendar dataPrenotazione;			//	"PRN_GPRNDATA" TIMESTAMP
//			private Double importoPagato;				//  "PRN_DPRNIMPO" DECIMAL(9,3)			
			
			callableStatement.setString(1, prenotazioni.getCodiceSocieta());
			callableStatement.setString(2, prenotazioni.getCuteCute());
			callableStatement.setString(3, prenotazioni.getChiaveEnte());
			callableStatement.setString(4, prenotazioni.getCodiceKeyTariffa());
			callableStatement.setTimestamp(5, new java.sql.Timestamp(prenotazioni.getDataPrenotazione().getTimeInMillis()));
			callableStatement.setDouble(6, prenotazioni.getImportoPagato());
			callableStatement.registerOutParameter(7, Types.INTEGER);						
			callableStatement.execute();
			ret = callableStatement.getInt(7);
			//callableStatement.execute();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DaoException(e);
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
			throw new DaoException(e);
		} catch (HelperException e) {
			e.printStackTrace();
			throw new DaoException(e);
		} finally {
			//inizio LP PG21XX04 Leak
			//DAOHelper.closeIgnoringException(connection);
			if (callableStatement != null) {
				try {
					callableStatement.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			//fine LP PG21XX04 Leak
		}
		return ret;
	}	

}
