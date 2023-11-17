package com.seda.payer.core.dao;

import java.math.BigInteger;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.TimeZone;

import com.seda.commons.logger.CustomLoggerManager;
import com.seda.commons.logger.LoggerWrapper;
import com.seda.data.helper.HelperException;
import com.seda.payer.core.bean.LogRequest;
import com.seda.payer.core.exception.DaoException;
import com.seda.payer.core.handler.BaseDaoHandler;

public class LogRequestDao extends BaseDaoHandler {

	private static final LoggerWrapper log =  CustomLoggerManager.get(LogRequestDao.class);

	/**
	 * Default constructor
	 * @param connection
	 * @param schema
	 */
	public LogRequestDao(Connection connection, String schema) {
		super(connection, schema);
		//LP PG22XX02 - 20220223 log.debug("LogRequestDao: Dao avviato");
	}
	
	/**
	 * @param idRequest
	 * @param tagSuffissoTabella
	 * @return
	 * @throws DaoException
	 */
	public LogRequest doDetail(BigInteger idRequest, String tagSuffissoTabella) throws DaoException {
		CallableStatement callableStatement = null;
		ResultSet data = null;
		
		//LP PG22XX02 - 20220223 log.debug("LogRequestDao: \n\tOP: DETAIL \n\t ROUTINE: " + 
		//LP PG22XX02 - 20220223 		Routines.PGREQSP_SEL + "\n\tPARAM: idRequest:" + idRequest);
		try {
			callableStatement = prepareCall(Routines.PGREQSP_SEL.routine());
			LogRequest bean = new LogRequest();
			bean.setIdRequest(idRequest);
			bean.setTagSuffissoTabella(tagSuffissoTabella);
			bean.load(callableStatement);
			if (callableStatement.execute()) {
				//LP PG22XX02 - 20220223 System.out.println("LogRequestDao: esecuzione chiamata db");
				data = callableStatement.getResultSet();
				if (data.next()){
					//LP PG22XX02 - 20220223 System.out.println("LogRequestDao: Stai ritornando un valore");
					return new LogRequest(data);
				}
			}
			return null;
		} catch (SQLException x) {
			System.out.println("errore1: " + x.getMessage());
			throw new DaoException(x);
		} catch (IllegalArgumentException x) {
			System.out.println("errore2: " + x.getMessage());
			throw new DaoException(x);
		} catch (HelperException x) {
			System.out.println("errore3: " + x.getMessage());
			throw new DaoException(x);
		} finally {
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
					//LP PG22XX02 - 20220223 System.out.println("callableStatement.close eseguito su finally ");
				} catch (SQLException e) {
					e.printStackTrace();
					System.out.println("callableStatement.close errore su finally ");
				}
			}
		}
	}
	
	/**
	 * @param bean
	 * @param rowsPerPage
	 * @param pageNumber
	 * @param order
	 * @throws DaoException
	 */
	public void doWebRowSets(LogRequest bean, int rowsPerPage, int pageNumber, String order) throws DaoException {
		CallableStatement callableStatement = null;
		try	{
			//LP PG22XX02 - 20220223 log.debug("LogRequestDao: OP: doWebRowSets\n\tOBJ: " + bean.toString());
			callableStatement = prepareCall(Routines.PGREQSP_LST.routine());
			bean.load(callableStatement, rowsPerPage, pageNumber, order);
			/* we execute procedure */
			if (callableStatement.execute()) {
				this.loadWebRowSets(callableStatement);
				/* we register page info */
				registerPageInfo(rowsPerPage, pageNumber, callableStatement.getInt(36), callableStatement.getInt(37), 
								 callableStatement.getInt(38), callableStatement.getInt(39));
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
	
	/**
	 * @param bean
	 * @return
	 */
	public void doInsert(LogRequest bean) throws DaoException {
		CallableStatement callableStatement = null;
		try {
			makeTabBackup(bean.getOperatoreRequest());
			//LP PG22XX02 - 20220223 log.debug("LogRequestDao: \n\tOP: INSERT \n\t ROUTINE: " + 
			//LP PG22XX02 - 20220223 		Routines.PGREQSP_INS + "\n\tOBJ: " + bean.toString());
			callableStatement = prepareCall(Routines.PGREQSP_INS.routine());
			bean.save(callableStatement);
			callableStatement.executeUpdate();
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
	/**
	 * @param bean
	 * @return
	 * @throws DaoException
	 */
	public void doUpdateExt(LogRequest bean) throws DaoException {
		
		CallableStatement callableStatement = null;
		try {
			makeTabBackup(bean.getOperatoreRequest());

			callableStatement = prepareCall(Routines.PGREQSP_UPD_EXT.routine());
			callableStatement.setString(1, bean.getSessionID());
			callableStatement.setString(2, bean.getError());
			callableStatement.setString(3, bean.getAction());
			callableStatement.setString(4, bean.getIndirizzoIP());
			callableStatement.setString(5, bean.getApplicativo());
			callableStatement.setString(6, bean.getChiaveEnte());
			callableStatement.setString(7, bean.getDescrizioneEnte());
			callableStatement.setString(8, bean.getSiglaProvinciaEnte());
			callableStatement.setString(9, bean.getCodiceFiscale());
			callableStatement.setString(10, bean.getCodiceSocieta());
			callableStatement.setString(11, bean.getCodiceUtente());
			callableStatement.setString(12, bean.getDbSchemaCodSocieta());
			callableStatement.setString(13, bean.getOperatoreRequest());
			callableStatement.setString(14, bean.getQueryString());
			callableStatement.setString(15, bean.getRequest());
			callableStatement.setString(16, bean.getSezioneApplicativa());
			callableStatement.setString(17, bean.getTemplate());
			callableStatement.setString(18, bean.getTipoRequest());
			callableStatement.setString(19, bean.getUserName());
			callableStatement.setString(20, bean.getUserProfile());
			callableStatement.setString(21, bean.getBelfioreRequest());
			callableStatement.setString(22, bean.getComuneRequest());
			callableStatement.setString(23, bean.getSiglaProvinciaRequest());
			callableStatement.setString(24, bean.getChiaveTransazione());
			callableStatement.setString(25, bean.getNumeroIUV());
			callableStatement.setString(26, bean.getParam());
			
			callableStatement.execute();
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
	/**
	 * @param idRequest
	 * @return
	 * @throws DaoException
	 */
	public void doDelete(BigInteger idRequest) throws DaoException {
		//Nota: non implementata non dovrebbe servire
		/*
		CallableStatement callableStatement = null;
		log.debug("LogRequestDao: \n\tOP: DELETE \n\t ROUTINE: " + "PGREQSP_DEL" + "\n\Id: " + idRequest);
		try {
			callableStatement = prepareCall("PGREQSP_DEL");
			callableStatement.setInt(1, idRequest.intValue());
			callableStatement.executeUpdate();
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
		*/
	}
	
	/*
	//1� version crea solo se lunedi
	public void makeTabBackup() throws DaoException {
		CallbleStatement callableStatement = null;
		log.debug("LogRequestDao: \n\tOP: makeTabBackup");
		Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("Europe/Rome"));
		if(cal.get(Calendar.DAY_OF_WEEK) == 2) { //se � luned�
			try {
				cal.add(Calendar.DAY_OF_YEAR, -1); //imposto al giorno precedente ==> domenica
				String datafineBackup = formatDate(cal, "yyyyMMdd"); //tag della data finale
				cal.add(Calendar.DAY_OF_YEAR, -6); //imposto al luned� precedente
				String datainizioBackup = formatDate(cal, "yyyyMMdd"); //tag della data iniziale
				String tagBackup = datainizioBackup + "_" + datafineBackup;
				String tabBackup = "PGREQTB_" +  tagBackup;
				callableStatement = prepareCall(Routines.PGSP_EXIST_TABLE.routine());
				log.debug("LogRequestDao: \n\tOP: Esiste tab \n\t ROUTINE: " + 
						Routines.PGSP_EXIST_TABLE.routine());
				callableStatement.setString(1, tabBackup);
				callableStatement.registerOutParameter(2, Types.INTEGER);
				callableStatement.executeUpdate();
				if(callableStatement.getInt(2) == 0) { //se non esiste il backup lo creo e svuoto la tab del log
					callableStatement.close();
					callableStatement = prepareCall(Routines.PGSP_BACKUP_TABLE.routine());
					log.debug("LogRequestDao: \n\tOP: Make tab backup\n\t ROUTINE: " + 
							Routines.PGSP_BACKUP_TABLE.routine());
					callableStatement.setString(1, "PGREQTB");
					callableStatement.setString(2, tagBackup);
					callableStatement.executeUpdate();
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
	}  
	*/
	/*
	//2� versione verifica ed eventualmente crea backup se non gia' presente
	private void makeTabBackup(String tagBackup) throws DaoException {
		CallableStatement callableStatement = null;
		log.debug("LogRequestDao: \n\tOP: makeTabBackup(" + tagBackup + ")");
		try {
			String tabBackup = "PGREQTB_" +  tagBackup;
			callableStatement = prepareCall(Routines.PGSP_EXIST_TABLE.routine());
			log.debug("LogRequestDao: \n\tOP: Esiste tab \n\t ROUTINE: " + 
					Routines.PGSP_EXIST_TABLE.routine());
			callableStatement.setString(1, tabBackup);
			callableStatement.registerOutParameter(2, Types.INTEGER);
			callableStatement.executeUpdate();
			//Se non esiste il backup lo creo e svuoto la tab del log
			if(callableStatement.getInt(2) == 0) {
				callableStatement.close();
				callableStatement = prepareCall(Routines.PGSP_BACKUP_TABLE.routine());
				log.debug("LogRequestDao: \n\tOP: Make tab backup\n\t ROUTINE: " + 
						Routines.PGSP_BACKUP_TABLE.routine());
				callableStatement.setString(1, "PGREQTB");
				callableStatement.setString(2, tagBackup);
				callableStatement.executeUpdate();
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
	
	public void makeTabBackup() throws DaoException {
		log.debug("LogRequestDao: \n\tOP: makeTabBackup");
		Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("Europe/Rome"));
		int cheGiornoE = cal.get(Calendar.DAY_OF_WEEK);
		int fine = -1;
		int inizio = -6;
		if(cheGiornoE != 2) {
			//se non � luned�
			if(cheGiornoE > 2)
				fine = 1 - cheGiornoE;
			else
				fine = -7;
		}
		try {
			cal.add(Calendar.DAY_OF_YEAR, fine); //imposto alla domenica della settimana precedente
			String datafineBackup = formatDate(cal, "yyyyMMdd"); //tag della data finale
			cal.add(Calendar.DAY_OF_YEAR, inizio); //imposto al luned� della settimana precedente
			String datainizioBackup = formatDate(cal, "yyyyMMdd"); //tag della data iniziale
			String tagBackup = datainizioBackup + "_" + datafineBackup;
			log.debug("LogRequestDao tagBackup: " + tagBackup);
			makeTabBackup(tagBackup);
		} catch (Exception x) {
			throw new DaoException(x);
		}
	}  
	*/
	//3� versione verifica ed eventualmente crea backup se non � gia' presente
	//aggiorna il dizionario per consentire query su storico (per adesso non gestite)
	private void makeTabBackup(
			String dataInizioBackup, String dataFineBackup,
			String dataInizio, String dataFine,
			String dataInizioCorrente, String dataFineCorrente,
			String operatore) throws DaoException {
		String tagBackup = dataInizioBackup + "_" + dataFineBackup;
		CallableStatement callableStatement = null;
		//LP PG22XX02 - 20220223 log.debug("LogRequestDao: \n\tOP: makeTabBackup(" + dataInizioBackup + ", " + dataFineBackup + ")");
		//inizio LP PG21X007 - 202112
		String nameSchema = getSchema();
		//LP PG22XX02 - 20220223 log.debug("LogRequestDao: \n\tSchema: " + nameSchema);
		//fine LP PG21X007 - 202112
		try {
			String tabBackup = "PGREQTB_" +  tagBackup;
			callableStatement = prepareCall(Routines.PGSP_EXIST_TABLE.routine());
			//LP PG22XX02 - 20220223 log.debug("LogRequestDao: \n\tOP: Esiste tab \n\t ROUTINE: " + 
			//LP PG22XX02 - 20220223 		Routines.PGSP_EXIST_TABLE.routine());

			//LP PG22XX02 - 20220223 log.debug("LogRequestDao: \n\tOP: Esiste tab \n\t ROUTINE: " + 
			//LP PG22XX02 - 20220223 		Routines.PGREQSP_EXIST.routine());
			callableStatement.setString(1, tabBackup);
			//inizio LP PG21X007 - 202112
			//callableStatement.registerOutParameter(2, Types.INTEGER);
			callableStatement.setString(2, nameSchema);
			callableStatement.registerOutParameter(3, Types.INTEGER);
			//fine LP PG21X007 - 202112
			callableStatement.executeUpdate();
			//Se non esiste il backup lo creo, come copia della tab "_PREC"
			//la tab log viene spostata su "_PREC" e la tab log viene "svuotata"
			//viene inoltre aggiornata la tab dizionario
			//inizio LP PG21X007 - 202112
			//if(callableStatement.getInt(2) == 0) {
			if(callableStatement.getInt(3) == 0) {
			//fine LP PG21X007 - 202112
				callableStatement = prepareCall(Routines.PGSP_BACKUP_TABLE.routine());
				//LP PG22XX02 - 20220223 log.debug("LogRequestDao: \n\tOP: Make tab backup\n\t ROUTINE: " + 
				//LP PG22XX02 - 20220223 		Routines.PGSP_BACKUP_TABLE.routine());
				callableStatement.setString(1, "PGREQTB");
				callableStatement.setString(2, tagBackup);
				callableStatement.setString(3, dataInizioBackup);
				callableStatement.setString(4, dataFineBackup);
				callableStatement.setString(5, dataInizio);
				callableStatement.setString(6, dataFine);
				callableStatement.setString(7, dataInizioCorrente);
				callableStatement.setString(8, dataFineCorrente);
				callableStatement.setString(9, operatore);
				//inizio LP PG21X007 - 202112
				callableStatement.setString(10, nameSchema);
				//fine LP PG21X007 - 202112
				callableStatement.executeUpdate();
				callableStatement.close();
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
	//	IN CASO DI RECUPERO DEL CODICE CORRENTE RICORDARSI DI INSERIRE LA TABELLA DI BACKUP NEI PARAMETRI DI INPUT
	//3� versione con le operazioni sul dizionario nel codice java 
	/*
	private void makeTabBackup(
			String dataInizioBackup, String dataFineBackup,
			String dataInizio, String dataFine,
			String dataInizioCorrente, String dataFineCorrente,
			String operatore) throws DaoException {
		String tagBackup = dataInizioBackup + "_" + dataFineBackup;
		CallableStatement callableStatement = null;
		log.debug("LogRequestDao: \n\tOP: makeTabBackup(" + dataInizioBackup + ", " + dataFineBackup + ")");
		//inizio LP PG21X007 - 202112
		String nameSchema = getSchema();
		log.debug("LogRequestDao: \n\tSchema: " + nameSchema);
		//fine LP PG21X007 - 202112		
		try {
			String tabBackup = "PGREQTB_" +  tagBackup;
			callableStatement = prepareCall(Routines.PGSP_EXIST_TABLE.routine());
			log.debug("LogRequestDao: \n\tOP: Esiste tab \n\t ROUTINE: " + 
					Routines.PGSP_EXIST_TABLE.routine());
			callableStatement.setString(1, tabBackup);
			callableStatement.setString(2, nameSchema);
			callableStatement.registerOutParameter(3, Types.INTEGER);
			callableStatement.executeUpdate();
			//Se non esiste il backup lo creo, come copia della tab "_PREC"
			//la tab log viene spostata su "_PREC" e la tab log viene "svuotata"
			if(callableStatement.getInt(3) == 0) {
				callableStatement.close();
				callableStatement = prepareCall(Routines.PGSP_BACKUP_TABLE.routine());
				log.debug("LogRequestDao: \n\tOP: Make tab backup\n\t ROUTINE: " + 
						Routines.PGSP_BACKUP_TABLE.routine());
				callableStatement.setString(1, "PGREQTB");
				callableStatement.setString(2, tagBackup);
				callableStatement.setString(3, nameSchema);				
				callableStatement.executeUpdate();
				callableStatement.close();
				log.debug("LogRequestDao: \n\tOP: Make tab backup\n\t ROUTINE: PGBKDSP_CHK");
				callableStatement = prepareCall("PGBKDSP_CHK");
				callableStatement.setString(1, dataInizioBackup);
				callableStatement.registerOutParameter(2, Types.INTEGER);
				callableStatement.executeUpdate();
				if(callableStatement.getInt(2) == 0) {
					callableStatement.close();
					//Nessun rec sul dizionario
					log.debug("LogRequestDao: \n\tOP: Make tab backup\n\t ROUTINE: PGBKDSP_INS #1");
					callableStatement = prepareCall("PGBKDSP_INS");
					callableStatement.setString(1, dataInizioBackup);
					callableStatement.setString(2, dataFineBackup);
					callableStatement.setString(3, tagBackup);
					callableStatement.setString(4, operatore);
					callableStatement.registerOutParameter(5, Types.INTEGER);
					callableStatement.registerOutParameter(6, Types.BIGINT);
					callableStatement.executeUpdate();
					//callableStatement.close();
					log.debug("LogRequestDao: \n\tOP: Make tab backup\n\t ROUTINE: PGBKDSP_INS #2");
					//callableStatement = prepareCall("PGBKDSP_INS");
					callableStatement.setString(1, dataInizio);
					callableStatement.setString(2, dataFine);
					callableStatement.setString(3, "PREC");
					callableStatement.setString(4, operatore);
					callableStatement.registerOutParameter(5, Types.INTEGER);
					callableStatement.registerOutParameter(6, Types.BIGINT);
					callableStatement.executeUpdate();
					//callableStatement.close();
					log.debug("LogRequestDao: \n\tOP: Make tab backup\n\t ROUTINE: PGBKDSP_INS  #3");
					//callableStatement = prepareCall("PGBKDSP_INS");
					callableStatement.setString(1, dataInizioCorrente);
					callableStatement.setString(2, dataFineCorrente);
					callableStatement.setString(3, "");
					callableStatement.setString(4, operatore);
					callableStatement.registerOutParameter(5, Types.INTEGER);
					callableStatement.registerOutParameter(6, Types.BIGINT);
					callableStatement.executeUpdate();
				} else {
					callableStatement.close();
					//Due rec su dizionario "PREC" e ""
					log.debug("LogRequestDao: \n\tOP: Make tab backup\n\t ROUTINE: PGBKDSP_UPD #1");
					callableStatement = prepareCall("PGBKDSP_UPD");
					callableStatement.setString(1, dataInizioBackup);
					callableStatement.setString(2, tagBackup);
					callableStatement.setString(3, operatore);
					callableStatement.registerOutParameter(4, Types.INTEGER);
					callableStatement.executeUpdate();
					//callableStatement.close();
					log.debug("LogRequestDao: \n\tOP: Make tab backup\n\t ROUTINE: PGBKDSP_UPD #2");
					//callableStatement = prepareCall("PGBKDSP_UPD");
					callableStatement.setString(1, dataInizio);
					callableStatement.setString(2, "PREC");
					callableStatement.setString(3, operatore);
					callableStatement.registerOutParameter(4, Types.INTEGER);
					callableStatement.executeUpdate();
					callableStatement.close();
					log.debug("LogRequestDao: \n\tOP: Make tab backup\n\t ROUTINE: PGBKDSP_INS  #3");
					callableStatement = prepareCall("PGBKDSP_INS");
					callableStatement.setString(1, dataInizioCorrente);
					callableStatement.setString(2, dataFineCorrente);
					callableStatement.setString(3, "");
					callableStatement.setString(4, operatore);
					callableStatement.registerOutParameter(5, Types.INTEGER);
					callableStatement.registerOutParameter(6, Types.BIGINT);
					callableStatement.executeUpdate();
				}
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
	*/
	public void makeTabBackup(String operatore) throws DaoException {
		//LP PG22XX02 - 20220223 log.debug("LogRequestDao: \n\tOP: makeTabBackup");
		Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("Europe/Rome"));
		Calendar cal0 = Calendar.getInstance(TimeZone.getTimeZone("Europe/Rome"));
		int cheGiornoE = cal.get(Calendar.DAY_OF_WEEK);
		int fine = -1;
		int inizio = -6;
		if(cheGiornoE != 2) {
			//se non � luned�
			if(cheGiornoE > 2)
				fine = 1 - cheGiornoE;
			else
				fine = -7;
		}
		try {
			//LP PG22XX02 - 20220223 log.debug("LogRequestDao oggi data: " + formatDate(cal, "yyyyMMdd"));
			cal.add(Calendar.DAY_OF_YEAR, fine); //imposto alla domenica di una settimana prima
			String dataFine = formatDate(cal, "yyyyMMdd"); //tag della data finale
			cal.add(Calendar.DAY_OF_YEAR, inizio); //imposto al luned� di una settimana prima
			String dataInizio = formatDate(cal, "yyyyMMdd"); //tag della data iniziale
			cal.add(Calendar.DAY_OF_YEAR, -1); //imposto alla domenica di due settimane prima
			String dataFineBackup = formatDate(cal, "yyyyMMdd"); //tag della data finale
			cal.add(Calendar.DAY_OF_YEAR, -6); //imposto al luned� di due settimane prima
			String dataInizioBackup = formatDate(cal, "yyyyMMdd"); //tag della data finale
			//LP PG22XX02 - 20220223 log.debug("LogRequestDao settimana precedente dataInizio: " + dataInizio + " dataFine: " + dataFine);
			//LP PG22XX02 - 20220223 log.debug("LogRequestDao settimana in storico dataInizio: " + dataInizioBackup + " dataFine: " + dataFineBackup);
			cheGiornoE = cal0.get(Calendar.DAY_OF_WEEK);
			String dataInizioCorrente = ""; //tag della data di inizio della settimana corrente
			String dataFineCorrente = ""; //tag della data di fine della settimana corrente
			if(cheGiornoE == 2) {
				dataInizioCorrente = formatDate(cal0, "yyyyMMdd"); //tag della data di inizio della settimana corrente
			} else {
				if(cheGiornoE > 2)
					inizio = 2 - cheGiornoE;
				else
					inizio = -6;
				cal0.add(Calendar.DAY_OF_YEAR, inizio); //imposto al luned� della settimana corrente
				dataInizioCorrente = formatDate(cal0, "yyyyMMdd"); //tag della data di inizio della settimana corrente
			}
			cal0.add(Calendar.DAY_OF_YEAR, 6); //imposto alla domenica della settimana corrente
			dataFineCorrente = formatDate(cal0, "yyyyMMdd"); //tag della data finale della settimana corrente
			//LP PG22XX02 - 20220223 log.debug("LogRequestDao settimana corrente dataInizio: " + dataInizioCorrente + " dataFine: " + dataFineCorrente);
			makeTabBackup(dataInizioBackup, dataFineBackup, dataInizio, dataFine, dataInizioCorrente, dataFineCorrente, operatore);
		} catch (Exception x) {
			throw new DaoException(x);
		}
	}  

	private String formatDate(Calendar data, String formato)
	{
		String sdata = "";
		if (data != null) 
		{
			SimpleDateFormat sdf = new SimpleDateFormat(formato);
			sdata = sdf.format(data.getTime());
		}
		return sdata;
	}
}