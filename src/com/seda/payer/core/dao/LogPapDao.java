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

import com.seda.data.helper.HelperException;
import com.seda.payer.core.bean.LogPap;
import com.seda.payer.core.exception.DaoException;
import com.seda.payer.core.handler.BaseDaoHandler;

public class LogPapDao extends BaseDaoHandler {

	//LP PG22XX06 - 20220607 private Logger log = Logger.getLogger(LogPapDao.class);

	public LogPapDao(Connection connection, String schema) {
		super(connection, schema);
	}
	

	public LogPap doDetail(BigInteger idLog, String tagSuffissoTabella) throws DaoException {
		CallableStatement callableStatement = null;
		ResultSet data = null;
		
		//LP PG22XX06 - 20220607 log.debug("LogPapDao: \n\tOP: DETAIL \n\t ROUTINE: " + 
		//LP PG22XX06 - 20220607 		Routines.PGPAPSP_SEL + "\n\tPARAM: idLog:" + idLog);
		try {
			callableStatement = prepareCall(Routines.PGPAPSP_SEL.routine());
			LogPap bean = new LogPap();
			bean.setIdLog(idLog);
			bean.setTagSuffissoTabella(tagSuffissoTabella);
			bean.load(callableStatement);
			if (callableStatement.execute()) {
				//LP PG22XX06 - 20220607 System.out.println("LogPapDao: esecuzione chiamata db");
				data = callableStatement.getResultSet();
				if (data.next()){
					//LP PG22XX06 - 20220607 System.out.println("LogPapDao: Stai ritornando un valore");
					return new LogPap(data);
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
					//LP PG22XX06 - 20220607 System.out.println("callableStatement.close eseguito su finally ");
				} catch (SQLException e) {
					e.printStackTrace();
					System.out.println("callableStatement.close errore su finally ");
				}
			}
		}
	}
	

	public void doWebRowSets(LogPap bean, int rowsPerPage, int pageNumber, String order) throws DaoException {
		CallableStatement callableStatement = null;
		try	{
			//LP PG22XX06 - 20220607 log.debug("LogPapDao: OP: doWebRowSets\n\tOBJ: " + bean.toString());
			callableStatement = prepareCall(Routines.PGPAPSP_LST.routine());
			bean.load(callableStatement, rowsPerPage, pageNumber, order);
			
			if (callableStatement.execute()) {
				this.loadWebRowSets(callableStatement);
				
				registerPageInfo(rowsPerPage, pageNumber, callableStatement.getInt(16), callableStatement.getInt(17), 
								 callableStatement.getInt(18), callableStatement.getInt(19));
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
	
	
	public void doInsert(LogPap bean) throws DaoException {
		CallableStatement callableStatement = null;
		try {
			makeTabBackup(bean.getIdDominio());
			callableStatement = prepareCall(Routines.PGPAPSP_INS.routine());
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
	
	private void makeTabBackup(
			String dataInizioBackup, String dataFineBackup,
			String dataInizio, String dataFine,
			String dataInizioCorrente, String dataFineCorrente,
			String idDominio) throws DaoException {
		
		String tagBackup = dataInizioBackup + "_" + dataFineBackup;
		CallableStatement callableStatement = null;
		
		String nameSchema = getSchema();
		try {
			String tabBackup = "PGPAPTB_" +  tagBackup;
			callableStatement = prepareCall(Routines.PGSP_EXIST_TABLE.routine());
			//LP PG22XX06 - 20220607 log.debug("\nLogPapDao: \n\tOP: Esiste tab \n\t ROUTINE: " + Routines.PGSP_EXIST_TABLE.routine());
			callableStatement.setString(1, tabBackup);
			callableStatement.setString(2, nameSchema);
			callableStatement.registerOutParameter(3, Types.INTEGER);
			callableStatement.executeUpdate();
			//Se non esiste il backup lo creo, come copia della tab "_PREC"
			//la tab log viene spostata su "_PREC" e la tab log viene "svuotata"
			//viene inoltre aggiornata la tab dizionario
			if(callableStatement.getInt(3) == 0) {
				callableStatement.close();
				callableStatement = prepareCall(Routines.PGSP_BACKUP_TABLE.routine());
				//LP PG22XX06 - 20220607 log.debug("\nLogPapDao: \n\tOP: Make tab backup\n\t ROUTINE: " + Routines.PGSP_BACKUP_TABLE.routine());
				callableStatement.setString(1, "PGPAPTB");
				callableStatement.setString(2, tagBackup);
				callableStatement.setString(3, dataInizioBackup);
				callableStatement.setString(4, dataFineBackup);
				callableStatement.setString(5, dataInizio);
				callableStatement.setString(6, dataFine);
				callableStatement.setString(7, dataInizioCorrente);
				callableStatement.setString(8, dataFineCorrente);
				callableStatement.setString(9, idDominio);
				callableStatement.setString(10, nameSchema);
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
	
	public void makeTabBackup(String idDominio) throws DaoException {
//		log.debug("LogPapDao: \n\tOP: makeTabBackup");
		Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("Europe/Rome"));
		Calendar cal0 = Calendar.getInstance(TimeZone.getTimeZone("Europe/Rome"));
		int cheGiornoE = cal.get(Calendar.DAY_OF_WEEK);
		int fine = -1;
		int inizio = -6;
		if(cheGiornoE != 2) {
			//se non è lunedì
			if(cheGiornoE > 2)
				fine = 1 - cheGiornoE;
			else
				fine = -7;
		}
		try {
			cal.add(Calendar.DAY_OF_YEAR, fine); //imposto alla domenica di una settimana prima
			String dataFine = formatDate(cal, "yyyyMMdd"); //tag della data finale
			cal.add(Calendar.DAY_OF_YEAR, inizio); //imposto al lunedì di una settimana prima
			String dataInizio = formatDate(cal, "yyyyMMdd"); //tag della data iniziale
			cal.add(Calendar.DAY_OF_YEAR, -1); //imposto alla domenica di due settimane prima
			String dataFineBackup = formatDate(cal, "yyyyMMdd"); //tag della data finale
			cal.add(Calendar.DAY_OF_YEAR, -6); //imposto al lunedì di due settimane prima
			String dataInizioBackup = formatDate(cal, "yyyyMMdd"); //tag della data finale
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
				cal0.add(Calendar.DAY_OF_YEAR, inizio); //imposto al lunedì della settimana corrente
				dataInizioCorrente = formatDate(cal0, "yyyyMMdd"); //tag della data di inizio della settimana corrente
			}
			cal0.add(Calendar.DAY_OF_YEAR, 6); //imposto alla domenica della settimana corrente
			dataFineCorrente = formatDate(cal0, "yyyyMMdd"); //tag della data finale della settimana corrente
			makeTabBackup(dataInizioBackup, dataFineBackup, dataInizio, dataFine, dataInizioCorrente, dataFineCorrente, idDominio);
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
	

	// YLM PG22XX08 INI
	public void getTipoOperazioni() throws DaoException {
		CallableStatement callableStatement = null;
//		ResultSet data = null;
//		ArrayList<String> list = new ArrayList<String>();
		try {
			callableStatement = prepareCall(Routines.PGPAPSP_OPE_DDL.routine());
			if (callableStatement.execute()) {
				this.loadWebRowSets(callableStatement);
				
//				data = callableStatement.getResultSet();
//				
//				while (data.next()){
//					list.add(data.getString(1));
//				}
//				return list;
			}
//			return null;
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
//			if (data != null) {
//				try {
//					data.close();
//				} catch (SQLException e) {
//					e.printStackTrace();
//				}
//			}
			if (callableStatement != null) {
				try {
					callableStatement.close();
				} catch (SQLException e) {
					e.printStackTrace();
					System.out.println("callableStatement.close errore su finally ");
				}
			}
		}
	}
	// YLM PG22XX08 FINE
	
}