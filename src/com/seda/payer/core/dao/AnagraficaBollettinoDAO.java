package com.seda.payer.core.dao;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;

import com.seda.payer.core.bean.AnaBollLogReportsPageList;
import com.seda.payer.core.bean.AnagraficaBollettino;
import com.seda.payer.core.bean.AnagraficaBollettinoECReports;
import com.seda.payer.core.bean.AnagraficaBollettinoPageList;
import com.seda.payer.core.bean.AnagraficaStrutturaRicettiva;
import com.seda.payer.core.exception.DaoException;
import com.seda.payer.core.wallet.bean.Wallet;


public interface AnagraficaBollettinoDAO  extends Serializable {
	public final static String SELECT_XML= "SELECT_XML";
	public AnagraficaBollettino doDetail(String codiceSocieta,String codiceUtente,String chiaveEnte,String codiceFiscale) throws DaoException;
	//inizio LP PG21XX05 Bug loadPdfFromGeos
	public AnagraficaBollettino doDetailBatch(String codiceSocieta,String codiceUtente,String chiaveEnte,String codiceFiscale) throws DaoException;
	//fine LP PG21XX05 Bug loadPdfFromGeos
	/*PG190020_000_LP*/
	//public AnagraficaBollettinoPageList doList(String codiceSocieta,String codiceUtente,String chiaveEnte,String codiceFiscale, String Denominazione, String anagraficaAttiva,int rowsPerPage,int pageNumber, String order) throws DaoException;
	public AnagraficaBollettinoPageList doList(String codiceSocieta,String codiceUtente,String chiaveEnte,String codiceFiscale, String Denominazione, String anagraficaAttiva,int rowsPerPage,int pageNumber, String order, String flagMail, String flagSms) throws DaoException;
	/*FINE PG190020_000_LP*/
	public Integer SetFirstAccessAnaBollettino(AnagraficaBollettino anagraficabollettino, String operatore) throws DaoException;
	/*PG190020_000_LP*/
	//public StringBuffer doListCsv(String codiceSocieta,String codiceUtente,String chiaveEnte,String codiceFiscale, String Denominazione, String anagraficaAttiva, int rowsPerPage,int pageNumber, String order) 	throws DaoException;
	public StringBuffer doListCsv(String codiceSocieta,String codiceUtente,String chiaveEnte,String codiceFiscale, String Denominazione, String anagraficaAttiva, int rowsPerPage,int pageNumber, String order, String flagMail, String flagSms) 	throws DaoException;
	/*FINE PG190020_000_LP*/
	public AnagraficaBollettinoECReports  doReportECABRS(String codiceSocieta,String codiceUtente,String chiaveEnte,String codiceFiscale, String Denominazione,String servizio,String DataLogDa, String DataLogA) 	throws DaoException, IOException;
	public AnaBollLogReportsPageList  doLogReportsList(String codiceSocieta,String codiceUtente,String chiaveEnte,String codiceFiscale, String Denominazione,String servizio,String DataLogDa, String DataLogA,int rowsPerPage,int pageNumber, String order) 	throws DaoException ;
	public void updateAnaBollettino(AnagraficaBollettino anagraficabollettino, String operatore) throws DaoException;
	public AnagraficaStrutturaRicettiva getAnagraficaStrutturaRicettiva(String codiceFiscale) throws DaoException;	//PG150140 GG 29042015	
	public ArrayList<AnagraficaStrutturaRicettiva> getAnagraficaStrutturaRicettivaList(String codiceFiscale) throws DaoException;	//PG150140 GG 29042015
	public String insertAnaBollettino(AnagraficaBollettino anagraficabollettino, String operatore) throws DaoException;	//PG170200 GG 22082017
	public String selCodiceSocieta(String codiceUtente) throws DaoException;	//PG170200 GG 04092017
	public String selFlagStampa(String codUtente,String codEnte,String tipoUfficio,String codUfficio,String tipologiaServizio,String numeroDocumento) throws DaoException;
	public void UpdatePincodeAndFlag(String codSoc, String codUte,
			String chiaveEnte, String codiceFiscale, String pincodeEmail,
			 String pincodeEmailPEC, String pincodeSms, String flgValidazionePinCodeMail,
			String flgValidazionePinCodeMailPEC,String flgValidazionePinCodeSms) throws DaoException;	//PG170180 CT 02/12/2017
	public AnagraficaBollettino selByCF(String codiceSocieta,String codiceUtente,String codiceFiscale) throws DaoException;  //PG190480_001
}
