package com.seda.payer.core.dao;

import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.Types;
import java.util.ArrayList;

import javax.sql.DataSource;
import javax.sql.rowset.CachedRowSet;

import com.seda.commons.string.Convert;
import com.seda.data.helper.HelperException;
import com.seda.data.procedure.reflection.MetaProcedure;
import com.seda.data.procedure.reflection.ProcedureReflectorException;
import com.seda.data.spi.PageInfo;
import com.seda.payer.core.bean.AnagraficaBollettino;
import com.seda.payer.core.bean.AnagraficaBollettinoECReports;
import com.seda.payer.core.bean.AnagraficaBollettinoPageList;
import com.seda.payer.core.bean.AnaBollLogReportsPageList;
import com.seda.payer.core.bean.AnagraficaStrutturaRicettiva;
import com.seda.payer.core.exception.DaoException;
import com.seda.payer.core.handler.BaseDaoHandler;

public class AnagraficaBollettinoDAOImpl extends BaseDaoHandler implements AnagraficaBollettinoDAO{
	
	private static final long serialVersionUID = 1L;
	
	/**
	 * Costruttore
	 * @param connection
	 * @param schema
	 */
	public AnagraficaBollettinoDAOImpl(Connection connection, String schema) {
		super(connection, schema);
	}
	//inizio LP PG21XX04 Leak
	@Deprecated
	//fine LP PG21XX04 Leak
	public AnagraficaBollettinoDAOImpl(DataSource dataSource, String schema) throws SQLException {
		super(dataSource.getConnection(), schema);
	}
	
	
	public AnagraficaBollettino doDetail(
			String codiceSocieta,
			String codiceUtente,
			String chiaveEnte,
			String codiceFiscale
	) throws DaoException {
		Connection connection = null;
		CallableStatement callableStatement = null;
		ResultSet data = null;
		//inizio LP PG21XX04 Leak
		CachedRowSet rowSet = null;
		//fine LP PG21XX04 Leak
		try	{
			connection = getConnection();
			AnagraficaBollettino res=null;
			//PGNTCORE-24 - inizio
			//callableStatement = Helper.prepareCall(connection, getSchema(), Routines.PYECASP_SEL.routine());
			callableStatement = MetaProcedure.prepareCall(connection, getSchema(), Routines.PYECASP_SEL.routine());
			//PGNTCORE-24 - fine
			callableStatement.setString(1, codiceSocieta);
			callableStatement.setString(2, codiceUtente);
			callableStatement.setString(3, chiaveEnte);
			callableStatement.setString(4, codiceFiscale);
			
			if (callableStatement.execute()) {
				data = callableStatement.getResultSet();
				loadWebRowSet(data);
				String selectXml = getWebRowSetXml();
				//inizio LP PG21XX04 Leak
				//CachedRowSet rowSet = null;
				//inizio LP PG21XX04 Leak
				try {
					rowSet = Convert.stringToWebRowSet(selectXml);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				if (rowSet.next() ) 
				{
					res = new AnagraficaBollettino(rowSet);
					res.setAttribute(AnagraficaBollettinoDAO.SELECT_XML, selectXml);
				}
			}
			return res;
		} catch (SQLException x) {
			throw new DaoException(x);
		} catch (IllegalArgumentException x) {
			throw new DaoException(x);
		} catch (ProcedureReflectorException x) {
			throw new DaoException(x);
		} finally {
			//inizio LP PG21XX04 Leak
			//if (data!=null) DAOHelper.closeIgnoringException(data);
			//if (callableStatement!=null) DAOHelper.closeIgnoringException(callableStatement);
			//if (connection!=null) DAOHelper.closeIgnoringException(connection);
			if(rowSet != null) {
				try {
					rowSet.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(data != null) {
				try {
					data.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(callableStatement != null) {
				try {
					callableStatement.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(connection != null) {
				try {
					connection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			//fine LP PG21XX04 Leak
		}
	}

	//inizio LP PG21XX05 Bug loadPdfFromGeos
	public AnagraficaBollettino doDetailBatch(
		String codiceSocieta,
		String codiceUtente,
		String chiaveEnte,
		String codiceFiscale
	) throws DaoException {
		CallableStatement callableStatement = null;
		ResultSet data = null;
		CachedRowSet rowSet = null;
		try	{
			AnagraficaBollettino res = null;
			callableStatement = prepareCall(Routines.PYECASP_SEL.routine());
			callableStatement.setString(1, codiceSocieta);
			callableStatement.setString(2, codiceUtente);
			callableStatement.setString(3, chiaveEnte);
			callableStatement.setString(4, codiceFiscale);
			
			if (callableStatement.execute()) {
				data = callableStatement.getResultSet();
				loadWebRowSet(data);
				String selectXml = getWebRowSetXml();
				try {
					rowSet = Convert.stringToWebRowSet(selectXml);
				} catch (IOException e) {
					e.printStackTrace();
				}
		
				if (rowSet.next() ) 
				{
					res = new AnagraficaBollettino(rowSet);
					res.setAttribute(AnagraficaBollettinoDAO.SELECT_XML, selectXml);
				}
			}
			return res;
		} catch (SQLException x) {
			throw new DaoException(x);
		} catch (IllegalArgumentException x) {
			throw new DaoException(x);
		} catch (HelperException x) {
			throw new DaoException(x);
		} finally {
			if(rowSet != null) {
				try {
					rowSet.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(data != null) {
				try {
					data.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(callableStatement != null) {
				try {
					callableStatement.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}
	//fine LP PG21XX05 Bug loadPdfFromGeos

	/*PG190020_000_LP*/
	//public AnagraficaBollettinoPageList  doList(String codiceSocieta,String codiceUtente,String chiaveEnte,String codiceFiscale, String Denominazione, String anagraficaAttiva, int rowsPerPage,int pageNumber, String order) 	throws DaoException {
	public AnagraficaBollettinoPageList  doList(String codiceSocieta,String codiceUtente,String chiaveEnte,String codiceFiscale, String Denominazione, String anagraficaAttiva, int rowsPerPage,int pageNumber, String order, String flagEmail, String flagSms) 	throws DaoException {
	/*FINEPG190020_000_LP*/	
		
		CallableStatement callableStatement=null;
		Connection connection = null;
		ResultSet data = null;
		PageInfo pageInfo = null; 
		AnagraficaBollettinoPageList anaBollettinoPageList = null;
		String xmlLst  ="";
		
		try {
			connection = getConnection();
			//PGNTCORE-24 - inizio
			//callableStatement = Helper.prepareCall(connection, getSchema(), Routines.PYECASP_LST_ANA.routine());
			callableStatement = MetaProcedure.prepareCall(connection, getSchema(), Routines.PYECASP_LST_ANA.routine());
			//PGNTCORE-24 - fine
			callableStatement.setInt(1, pageNumber);                          /* rows per page */
			callableStatement.setInt(2, rowsPerPage);                        /* page number*/
			callableStatement.setString(3,order);
			callableStatement.setString(4,codiceSocieta);
			callableStatement.setString(5,codiceUtente);
			callableStatement.setString(6,chiaveEnte);
			callableStatement.setString(7,codiceFiscale);
			callableStatement.setString(8,Denominazione);
			callableStatement.setString(9,anagraficaAttiva);
			
			/*PG190020_000_LP*/
			callableStatement.setString(10,flagEmail);
			callableStatement.setString(11,flagSms);
			/*FINE PG190020_000_LP*/

			/* we select */
			//PG190020_000_LP callableStatement.registerOutParameter(10, Types.VARCHAR);
			callableStatement.registerOutParameter(12, Types.VARCHAR);
			/* we register row start */
			//PG190020_000_LP callableStatement.registerOutParameter(11, Types.INTEGER);
			callableStatement.registerOutParameter(13, Types.INTEGER);
			/* we register row end */
			//PG190020_000_LP callableStatement.registerOutParameter(12, Types.INTEGER);
			callableStatement.registerOutParameter(14, Types.INTEGER);
			/* we register total rows */
			//PG190020_000_LP callableStatement.registerOutParameter(13, Types.INTEGER)
			callableStatement.registerOutParameter(15, Types.INTEGER);
			/* we register total pages */
			//PG190020_000_LP callableStatement.registerOutParameter(14, Types.SMALLINT);
			callableStatement.registerOutParameter(16, Types.SMALLINT);

			/* we execute procedure */

			if(callableStatement.execute())	{
				pageInfo = new PageInfo();
				pageInfo.setPageNumber(pageNumber);
				pageInfo.setRowsPerPage(rowsPerPage);
				/*PG190020_000_LP*/
				//pageInfo.setFirstRow(callableStatement.getInt(11));
				//pageInfo.setLastRow(callableStatement.getInt(12));
				//pageInfo.setNumRows(callableStatement.getInt(13));
				//pageInfo.setNumPages(callableStatement.getInt(14));
				pageInfo.setFirstRow(callableStatement.getInt(13));
				pageInfo.setLastRow(callableStatement.getInt(14));
				pageInfo.setNumRows(callableStatement.getInt(15));
				pageInfo.setNumPages(callableStatement.getInt(16));
				/*FINE PG190020_000_LP*/
				data = callableStatement.getResultSet();
				loadWebRowSet(data);

				xmlLst = getWebRowSetXml();
			}

			
			anaBollettinoPageList = new AnagraficaBollettinoPageList(pageInfo, "00","",xmlLst);
			return anaBollettinoPageList;

		} catch (SQLException e) {
			e.printStackTrace();
			anaBollettinoPageList = new AnagraficaBollettinoPageList(pageInfo, "01","Sql-Exception","");
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
			anaBollettinoPageList = new AnagraficaBollettinoPageList(pageInfo, "01","Sql-Exception","");
		} catch (ProcedureReflectorException e) {
			e.printStackTrace();
			anaBollettinoPageList = new AnagraficaBollettinoPageList(pageInfo, "01","Sql-Exception","");
		} finally {
			//inizio LP PG21XX04 Leak
			//DAOHelper.closeIgnoringException(connection);
			if(data != null) {
				try {
					data.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(callableStatement != null) {
				try {
					callableStatement.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(connection != null) {
				try {
					connection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			//fine LP PG21XX04 Leak
		}
		return anaBollettinoPageList;
	}
	
	/*PG190020_000_LP*/
	//public StringBuffer  doListCsv(String codiceSocieta,String codiceUtente,String chiaveEnte,String codiceFiscale, String Denominazione, String anagraficaAttiva, int rowsPerPage,int pageNumber, String order) 	throws DaoException {
	public StringBuffer  doListCsv(String codiceSocieta,String codiceUtente,String chiaveEnte,String codiceFiscale, String Denominazione, String anagraficaAttiva, int rowsPerPage,int pageNumber, String order, String flagMail, String flagSms) 	throws DaoException {
	/*FINE PG190020_000_LP*/	
		CallableStatement callableStatement=null;

		Connection connection = null;
		ResultSet data = null;
		StringBuffer file = new StringBuffer();
		
		try {
			connection = getConnection();
			//PGNTCORE-24 - inzio
			//callableStatement = Helper.prepareCall(connection, getSchema(), Routines.PYECASP_LST_ANA.routine());
			callableStatement = MetaProcedure.prepareCall(connection, getSchema(), Routines.PYECASP_LST_ANA.routine());
			//PGNTCORE-24 - fine
			callableStatement.setInt(1, pageNumber);                          /* rows per page */
			callableStatement.setInt(2, rowsPerPage);                        /* page number*/
			callableStatement.setString(3,order);
			callableStatement.setString(4,codiceSocieta);
			callableStatement.setString(5,codiceUtente);
			callableStatement.setString(6,chiaveEnte);
			callableStatement.setString(7,codiceFiscale);
			callableStatement.setString(8,Denominazione);
			callableStatement.setString(9,anagraficaAttiva);
			
			/*PG190020_000_LP*/
			callableStatement.setString(10,flagMail);
			callableStatement.setString(11,flagSms);
			/*FINE PG190020_000_LP*/
			

			/* we select */
			//PG190020_000_LP callableStatement.registerOutParameter(10, Types.VARCHAR);
			callableStatement.registerOutParameter(12, Types.VARCHAR);
			/* we register row start */
			//PG190020_000_LP callableStatement.registerOutParameter(11, Types.INTEGER);
			callableStatement.registerOutParameter(13, Types.INTEGER);
			/* we register row end */
			//PG190020_000_LP callableStatement.registerOutParameter(12, Types.INTEGER);
			callableStatement.registerOutParameter(14, Types.INTEGER);
			/* we register total rows */
			//PG190020_000_LP callableStatement.registerOutParameter(13, Types.INTEGER)
			callableStatement.registerOutParameter(15, Types.INTEGER);
			/* we register total pages */
			//PG190020_000_LP callableStatement.registerOutParameter(14, Types.SMALLINT);
			callableStatement.registerOutParameter(16, Types.SMALLINT);


			/* we execute procedure */
			String row = "";
			if(callableStatement.execute())	{
				data = callableStatement.getResultSet();
				if ( data.next() ) {
					row += "Codice Fiscale;";
					row += "Cognome;";
					row += "Nome;";
					row += "Numero Cellulare;";
					row += "Email;";
					row += "EmailPec;";
					row += "Attivo;";
					row += "Welcome kit prodotto;";
					row += "Presenza Borsellino;";
					//row += "Ragione sociale;"; //PG170280 CT //Commentata perchè in fase di importazione le ragioni sociali vengono salvate nel campo cognome
					/*PG190020_000_LP*/
					row += "Notifica via Mail;";
					row += "Notifica via Sms;";
					row += "Autenticazione Presente;";
					/*FINE PG190020_000_LP*/
					row += "\r";
					file.append(row);
					row = "";
					do {

						String cfis = data.getString(4);
						String cognome = data.getString(5);
						String nome = data.getString(6);
						String cellulare = data.getString(7);
						String email = data.getString(8);
						String mailpec = data.getString(9);
						String attivo="NO";
						if (data.getString(11).toString().equals("N"))
						{
							attivo="SI";
						}
						String welcome="NO";
						if (data.getString(12).toString().equals("Y"))
						{
							welcome="SI";
						}
						String presenzaBorsellino="NO";
						if (data.getString(15).toString().equals("Y"))
						{
							presenzaBorsellino="SI";
						}
						String ragionesociale = data.getString(16); //PG170280 CT
						
						/*PG190020_000_LP*/
						String flgNotificaMail = data.getString(17).toString().equals("Y") ? "Attiva" : "Non Attiva";
						String flgNotificaSms = data.getString(18).toString().equals("Y") ? "Attiva" : "Non Attiva";
						
						String autPresente = data.getString(19).toString().equals("Y") ? "SI" : "NO";
						/*FINE PG190020_000_LP*/

						row += cfis + ";";
						row += cognome + ";";
						row += nome + ";";
						row += cellulare + ";";
						row += email + ";";
						row += mailpec  + ";";
						row += attivo  + ";";
						row += welcome  + ";";
						row += presenzaBorsellino  + ";";
						//row += ragionesociale  + ";"; //PG170280 CT //Commentata perchè in fase di importazione le ragioni sociali vengono salvate nel campo cognome
						
						/*PG190020_000_LP*/
						row += flgNotificaMail + ";";
						row += flgNotificaSms + ";";
						row += autPresente + ";";
						/*FINE PG190020_000_LP*/
						
						row += "\r";
						file.append(row);
						row = "";
					} while(data.next());
					data.close();
					callableStatement.close();
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (ProcedureReflectorException e) {
			e.printStackTrace();
		} finally {
			//inizio LP PG21XX04 Leak
			//DAOHelper.closeIgnoringException(connection);
			if(data != null) {
				try {
					data.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(callableStatement != null) {
				try {
					callableStatement.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(connection != null) {
				try {
					connection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			//fine LP PG21XX04 Leak
		}
		return file;
	}
	public Integer SetFirstAccessAnaBollettino(AnagraficaBollettino anagraficabollettino, String operatore) throws DaoException{
		CallableStatement callableStatement=null;
		Connection connection = null;
		int ret=0;
		try { 
			connection = getConnection();
			//PGNTCORE-24 - inzio
			//callableStatement = Helper.prepareCall(connection, getSchema(), Routines.PYECASP_UPD_PACC.routine());
			callableStatement = MetaProcedure.prepareCall(connection, getSchema(), Routines.PYECASP_UPD_PACC.routine());
			//PGNTCORE-24 - fine
			callableStatement.setString(1, anagraficabollettino.getCodiceSocieta());
			callableStatement.setString(2, anagraficabollettino.getCodiceUtente());
			callableStatement.setString(3, anagraficabollettino.getChiaveEnte());
			callableStatement.setString(4, anagraficabollettino.getCodiceFiscale());
			callableStatement.setString(5, anagraficabollettino.getCellulare());
			callableStatement.setString(6, anagraficabollettino.getMail());
			callableStatement.setString(7, anagraficabollettino.getMailPec());
			//PG170200 GG 25082017 - inizio
			callableStatement.setString(8, anagraficabollettino.getFlgNotificaMail());
			callableStatement.setString(9, anagraficabollettino.getFlgNotificaSms());
			callableStatement.setString(10, anagraficabollettino.getFlgFirstAccess()?"Y":"N");
			//PG170200 GG 25082017 - fine
			callableStatement.setString(11, anagraficabollettino.getCodiceSDI());
			callableStatement.setString(12, anagraficabollettino.getFlgInvioAllegato());
			callableStatement.setString(13, operatore);
			callableStatement.execute();

		} catch (SQLException e) {
			throw new DaoException(e);
		} catch (IllegalArgumentException e) {
			throw new DaoException(e);
		} catch (ProcedureReflectorException e) {
			throw new DaoException(e);
		} finally {
			//inizio LP PG21XX04 Leak
			//DAOHelper.closeIgnoringException(connection);
			if(callableStatement != null) {
				try {
					callableStatement.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(connection != null) {
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
	
	public AnagraficaBollettinoECReports  doReportECABRS(String codiceSocieta,String codiceUtente,String chiaveEnte,String codiceFiscale, String Denominazione,
			String servizio,String DataLogDa, String DataLogA) 	throws DaoException, IOException {
		CallableStatement callableStatement=null;
		//inizio LP PG21XX04 Leak
		CachedRowSet rowSet = null;
		//fine LP PG21XX04 Leak
		Connection connection = null;
		ResultSet data = null;
		AnagraficaBollettinoECReports res = null;
		try {
			connection = getConnection();
			//PGNTCORE-24 - inizio
			//callableStatement = Helper.prepareCall(connection, getSchema(), Routines.PYECABRS_RPT.routine());
			callableStatement = MetaProcedure.prepareCall(connection, getSchema(), Routines.PYECABRS_RPT.routine());
			//PGNTCORE-24 - fine
			callableStatement.setString(1,codiceSocieta);
			callableStatement.setString(2,codiceUtente);
			callableStatement.setString(3,chiaveEnte);
			callableStatement.setString(4,codiceFiscale);
			callableStatement.setString(5,Denominazione);
			callableStatement.setString(6,servizio);
			callableStatement.setString(7,DataLogDa);
			callableStatement.setString(8,DataLogA);


			if (callableStatement.execute()) {
				data = callableStatement.getResultSet();
				loadWebRowSet(data);
				String selectXml = getWebRowSetXml();
				//inizio LP PG21XX04 Leak
				//CachedRowSet rowSet = null;
				//fine LP PG21XX04 Leak
				try {
					rowSet = Convert.stringToWebRowSet(selectXml);
				} catch (IOException e) {
					e.printStackTrace();
					throw e;
				}

				if (rowSet.next() ) 
				{
					res = new AnagraficaBollettinoECReports(rowSet);
					res.setAnaBollLogListXml(selectXml);
					//res.setAttribute(AnagraficaBollettinoDAO.SELECT_XML, selectXml);
				}
			}
			return res;
		} catch (SQLException x) {
			throw new DaoException(x);
		} catch (IllegalArgumentException x) {
			throw new DaoException(x);
		} catch (ProcedureReflectorException x) {
			throw new DaoException(x);
		} finally {
			//inizio LP PG21XX04 Leak
			//DAOHelper.closeIgnoringException(connection);
			if(rowSet != null) {
				try {
					rowSet.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(data != null) {
				try {
					data.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(callableStatement != null) {
				try {
					callableStatement.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(connection != null) {
				try {
					connection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			//fine LP PG21XX04 Leak
		}
	}
	
	public AnaBollLogReportsPageList  doLogReportsList(String codiceSocieta,String codiceUtente,String chiaveEnte,String codiceFiscale, String Denominazione,
			String servizio,String DataLogDa, String DataLogA,int rowsPerPage,int pageNumber, String order) 	throws DaoException {
		CallableStatement callableStatement=null;

		Connection connection = null;
		ResultSet data = null;
		PageInfo pageInfo = null; 
		AnaBollLogReportsPageList anaBollLogReportsList = null;
		String xmlLst  ="";
		try {
			connection = getConnection();
			//PGNTCORE-24 - inizio
			//callableStatement = Helper.prepareCall(connection, getSchema(), Routines.PYECBRLOG_RPT.routine());
			callableStatement = MetaProcedure.prepareCall(connection, getSchema(), Routines.PYECBRLOG_RPT.routine());
			//PGNTCORE-24 - fine
			callableStatement.setInt(1, pageNumber);                          /* rows per page */
			callableStatement.setInt(2, rowsPerPage);                        /* page number*/
			callableStatement.setString(3,order);
			callableStatement.setString(4,codiceSocieta);
			callableStatement.setString(5,codiceUtente);
			callableStatement.setString(6,chiaveEnte);
			callableStatement.setString(7,codiceFiscale);
			callableStatement.setString(8,Denominazione);
			callableStatement.setString(9,servizio);
			callableStatement.setString(10,DataLogDa);
			callableStatement.setString(11,DataLogA);
			
			/* we select */
			callableStatement.registerOutParameter(12, Types.INTEGER);
			/* we register row start */
			callableStatement.registerOutParameter(13, Types.INTEGER);
			/* we register row end */
			callableStatement.registerOutParameter(14, Types.INTEGER);
			/* we register total rows */
			callableStatement.registerOutParameter(15, Types.INTEGER);

			/* we execute procedure */

			if(callableStatement.execute())	{
				pageInfo = new PageInfo();
				pageInfo.setPageNumber(pageNumber);
				pageInfo.setRowsPerPage(rowsPerPage);
				pageInfo.setFirstRow(callableStatement.getInt(12));
				pageInfo.setLastRow(callableStatement.getInt(13));
				pageInfo.setNumRows(callableStatement.getInt(14));
				pageInfo.setNumPages(callableStatement.getInt(15));

				data = callableStatement.getResultSet();
				loadWebRowSet(data);

				xmlLst = getWebRowSetXml();
			}

			
			anaBollLogReportsList = new AnaBollLogReportsPageList(pageInfo, "00","",xmlLst);
			return anaBollLogReportsList;

		} catch (SQLException e) {
			e.printStackTrace();
			anaBollLogReportsList = new AnaBollLogReportsPageList(pageInfo, "01","Sql-Exception","");
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
			anaBollLogReportsList = new AnaBollLogReportsPageList(pageInfo, "01","Sql-Exception","");
		} catch (ProcedureReflectorException e) {
			e.printStackTrace();
			anaBollLogReportsList = new AnaBollLogReportsPageList(pageInfo, "01","Sql-Exception","");
		} finally {
			//inizio LP PG21XX04 Leak
			//DAOHelper.closeIgnoringException(connection);
			if(data != null) {
				try {
					data.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(callableStatement != null) {
				try {
					callableStatement.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(connection != null) {
				try {
					connection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			//fine LP PG21XX04 Leak
		}
		return anaBollLogReportsList;
	}
	
	public void updateAnaBollettino(AnagraficaBollettino anagraficabollettino, String operatore) throws DaoException{
		CallableStatement callableStatement=null;
		Connection connection = null;
		try { 
			connection = getConnection();
			//PGNTCORE-24 - inizio
			//callableStatement = Helper.prepareCall(connection, getSchema(), Routines.PYECASP_UPD.routine());
			callableStatement = MetaProcedure.prepareCall(connection, getSchema(), Routines.PYECASP_UPD.routine());
			//PGNTCORE-24 - fine
			callableStatement.setString(1, anagraficabollettino.getCodiceSocieta());				
			callableStatement.setString(2, anagraficabollettino.getCodiceUtente());
			callableStatement.setString(3, anagraficabollettino.getChiaveEnte());
			callableStatement.setString(4, anagraficabollettino.getCodiceFiscale());
			callableStatement.setString(5, anagraficabollettino.getCognome());
			callableStatement.setString(6, anagraficabollettino.getNome());
			callableStatement.setString(7, anagraficabollettino.getCellulare());
			callableStatement.setString(8, anagraficabollettino.getMail());
			callableStatement.setString(9, anagraficabollettino.getMailPec());
			callableStatement.setString(10, anagraficabollettino.getIndirizzo());
			callableStatement.setString(11, anagraficabollettino.getCap());
			callableStatement.setString(12, anagraficabollettino.getComune());
			callableStatement.setString(13, anagraficabollettino.getProvincia());
			callableStatement.setString(14, anagraficabollettino.getCodiceSDI());
			callableStatement.setString(15, operatore);
			
			callableStatement.execute();

		} catch (SQLException e) {
			throw new DaoException(e);
		} catch (IllegalArgumentException e) {
			throw new DaoException(e);
		} catch (ProcedureReflectorException e) {
			throw new DaoException(e);
		} finally {
			//inizio LP PG21XX04 Leak
			//DAOHelper.closeIgnoringException(connection);
			if(callableStatement != null) {
				try {
					callableStatement.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(connection != null) {
				try {
					connection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			//fine LP PG21XX04 Leak
		}
	}
		
	public AnagraficaStrutturaRicettiva getAnagraficaStrutturaRicettiva(String codiceFiscale) throws DaoException {
		Connection connection = null;
		CallableStatement callableStatement = null;
		ResultSet data = null;
		//inizio LP PG21XX04 Leak
		CachedRowSet rowSet = null;
		//fine LP PG21XX04 Leak
		try	{
			connection = getConnection();
			AnagraficaStrutturaRicettiva res = null;
			//PGNTCORE-24 - inizio
			//callableStatement = Helper.prepareCall(connection, getSchema(), Routines.PYSANSP_SEL_CFIS.routine());
			callableStatement = MetaProcedure.prepareCall(connection, getSchema(), Routines.PYSANSP_SEL_CFIS.routine());
			//PGNTCORE-24 - fine
			callableStatement.setString(1, codiceFiscale);
			callableStatement.registerOutParameter(2, Types.INTEGER);

			if (callableStatement.execute()) {
				data = callableStatement.getResultSet();
				loadWebRowSet(data);
				String selectXml = getWebRowSetXml();
				//inizio LP PG21XX04 Leak
				//CachedRowSet rowSet = null;
				//fine LP PG21XX04 Leak
				try {
					rowSet = Convert.stringToWebRowSet(selectXml);
				} catch (IOException e) {
					e.printStackTrace();
				}
				if (rowSet.next() ) 
				{
					//Come da accordi con Andrea Polenta, nel caso di più records per lo stesso codice fiscale non sarà abilitato il link Imposta Soggiorno
					if (!(callableStatement.getInt(2) > 1)) {	//TODO da verificare
						res = new AnagraficaStrutturaRicettiva(rowSet);
						res.setAttribute(AnagraficaBollettinoDAO.SELECT_XML, selectXml);
					}
				}
			}
			return res;
		} catch (SQLException x) {
			throw new DaoException(x);
		} catch (IllegalArgumentException x) {
			throw new DaoException(x);
		} catch (ProcedureReflectorException x) {
			throw new DaoException(x);
		} finally {
			//inizio LP PG21XX04 Leak
			//if (data!=null) DAOHelper.closeIgnoringException(data);
			//if (callableStatement!=null) DAOHelper.closeIgnoringException(callableStatement);
			//if (connection!=null) DAOHelper.closeIgnoringException(connection);
			if(rowSet != null) {
				try {
					rowSet.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(data != null) {
				try {
					data.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(callableStatement != null) {
				try {
					callableStatement.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(connection != null) {
				try {
					connection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			//fine LP PG21XX04 Leak
		}
	}
	
	public ArrayList<AnagraficaStrutturaRicettiva> getAnagraficaStrutturaRicettivaList(String codiceFiscale) throws DaoException {
		Connection connection = null;
		CallableStatement callableStatement = null;
		ResultSet data = null;
		ArrayList<AnagraficaStrutturaRicettiva> anaStrutturaRicettivaList = null;
		try	{	
			connection = getConnection();
			//PGNTCORE-24 - inizio
			callableStatement = MetaProcedure.prepareCall(connection, getSchema(), Routines.PYSANSP_SEL_CFIS.routine());
			//PGNTCORE-24 - fine
			callableStatement.setString(1, codiceFiscale);
			callableStatement.registerOutParameter(2, Types.INTEGER);

			if (callableStatement.execute()) {
				data = callableStatement.getResultSet();
				anaStrutturaRicettivaList = new ArrayList<AnagraficaStrutturaRicettiva>();
				while (data.next()) {
					AnagraficaStrutturaRicettiva anaStrutturaRicettiva = new AnagraficaStrutturaRicettiva();
					anaStrutturaRicettiva.setCodiceBelfiore(data.getString(2));
					anaStrutturaRicettiva.setCodiceAutorizzazione(data.getString(3));
					anaStrutturaRicettivaList.add(anaStrutturaRicettiva);
				}
				return anaStrutturaRicettivaList;
			}
		} catch (SQLException x) {
			throw new DaoException(x);
		} catch (IllegalArgumentException x) {
			throw new DaoException(x);
		} catch (ProcedureReflectorException x) {
			throw new DaoException(x);
		} finally {
			//inizio LP PG21XX04 Leak
			//if (data!=null) DAOHelper.closeIgnoringException(data);
			//if (callableStatement!=null) DAOHelper.closeIgnoringException(callableStatement);
			//if (connection!=null) DAOHelper.closeIgnoringException(connection);
			if(data != null) {
				try {
					data.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(callableStatement != null) {
				try {
					callableStatement.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(connection != null) {
				try {
					connection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			//fine LP PG21XX04 Leak
		}
		return anaStrutturaRicettivaList;
	}
	//PG150140 GG 29042015 - fine
	
	//PG170200 GG 22082017 - inizio
	public String insertAnaBollettino(AnagraficaBollettino anagraficabollettino, String operatore) throws DaoException{
		String res = "";
		CallableStatement callableStatement=null;
		Connection connection = null;
		String retCode = "";
		String retMessage = "";
		try { 
			connection = getConnection();
			//PGNTCORE-24 - inizio
			//callableStatement = Helper.prepareCall(connection, getSchema(), Routines.PYECASP_INS.routine());
			callableStatement = MetaProcedure.prepareCall(connection, getSchema(), Routines.PYECASP_INS.routine());
			//PGNTCORE-24 - fine
			callableStatement.setString(1, anagraficabollettino.getCodiceSocieta());				
			callableStatement.setString(2, anagraficabollettino.getCodiceUtente());
			callableStatement.setString(3, anagraficabollettino.getChiaveEnte());
			callableStatement.setString(4, anagraficabollettino.getCodiceFiscale());
			callableStatement.setString(5, anagraficabollettino.getCognome());
			callableStatement.setString(6, anagraficabollettino.getNome());
			callableStatement.setString(7, anagraficabollettino.getCellulare());
			callableStatement.setString(8, anagraficabollettino.getMail());
			callableStatement.setString(9, anagraficabollettino.getMailPec());
			callableStatement.setString(10, anagraficabollettino.getFlgActivate()?"Y":"N");
			callableStatement.setInt(11, 0);		//codiceAttivazione
			callableStatement.setString(12, anagraficabollettino.getFlgFirstAccess()?"Y":"N");
			callableStatement.setString(13, anagraficabollettino.getFlgFwlk()?"Y":"N");
			callableStatement.setString(14, "N");	//flagProduzioneFileCsi
			callableStatement.setString(15, anagraficabollettino.getFlgPresent()?"Y":"N");
			callableStatement.setString(16, anagraficabollettino.getIndirizzo());
			callableStatement.setString(17, anagraficabollettino.getCap());
			callableStatement.setString(18, anagraficabollettino.getComune());
			callableStatement.setString(19, anagraficabollettino.getProvincia());
			callableStatement.setNull(20, Types.TIMESTAMP);
			callableStatement.setNull(21, Types.TIMESTAMP);
			callableStatement.setString(22, operatore);
			callableStatement.setString(23, anagraficabollettino.getFlgNotificaMail());
			callableStatement.setString(24, anagraficabollettino.getFlgNotificaSms());
			callableStatement.setString(25, anagraficabollettino.getPinCodeMail());
			callableStatement.setString(26, anagraficabollettino.getPinCodeSms());
			callableStatement.setString(27, anagraficabollettino.getFlgValidazionePinCodeMail());
			callableStatement.setString(28, anagraficabollettino.getFlgValidazionePinCodeSms());
			callableStatement.setString(29, anagraficabollettino.getFlgInvioAllegato()==null ? "N" : anagraficabollettino.getFlgInvioAllegato());
			callableStatement.registerOutParameter(30, Types.VARCHAR);
			callableStatement.registerOutParameter(31, Types.VARCHAR);
			
			
			callableStatement.execute();
			
			retCode = callableStatement.getString(30);
			retMessage = callableStatement.getString(31);
			if (!retCode.equals("00")) res = retMessage;

		} catch (SQLException e) {
			throw new DaoException(e);
		} catch (IllegalArgumentException e) {
			throw new DaoException(e);
		} catch (ProcedureReflectorException e) {
			throw new DaoException(e);
		} finally {
			//inizio LP PG21XX04 Leak
			//DAOHelper.closeIgnoringException(connection);
			if(callableStatement != null) {
				try {
					callableStatement.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(connection != null) {
				try {
					connection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			//fine LP PG21XX04 Leak
		}
		return res;
	}
	
	//inizio LP 20240827 - PGNTCONS-3
	public String selCodiceSocieta(String codiceUtente) throws DaoException {
		return selCodiceSocietaTail(true, codiceUtente);
	}

	public String selCodiceSocietaTail(boolean bFlagUpdateAutocommit, String codiceUtente) throws DaoException {
	//fine LP 20240827 - PGNTCONS-3
		String codiceSocieta = "";
		ResultSet listCodSoc = null;
		CallableStatement callableStatement=null;
		Connection connection = null;
		try {
			//inizio LP 20240827 - PGNTCONS-3
			if(bFlagUpdateAutocommit) {
			//fine LP 20240827 - PGNTCONS-3
				connection = getConnection();
			//inizio LP 20240827 - PGNTCONS-3
			}
			//fine LP 20240827 - PGNTCONS-3
			//PGNTCORE-24 - inizio
			//callableStatement = Helper.prepareCall(connection, getSchema(), Routines.PYUTESP_SEL_SOC.routine());
			//inizio LP 20240827 - PGNTCONS-3
			//callableStatement = MetaProcedure.prepareCall(connection, getSchema(), Routines.PYUTESP_SEL_SOC.routine());
			callableStatement = prepareCall(bFlagUpdateAutocommit, Routines.PYUTESP_SEL_SOC.routine());
			//fine LP 20240827 - PGNTCONS-3
			//PGNTCORE-24 - fine
			callableStatement.setString(1, codiceUtente);
			callableStatement.execute();
			
			listCodSoc = callableStatement.getResultSet();
			if (listCodSoc.next()){
				do{
					codiceSocieta = listCodSoc.getString(1);
				} while(listCodSoc.next());
			}
		}  catch (Exception e){
			throw new DaoException(e);
		} finally {
			//inizio LP PG21XX04 Leak
			//DAOHelper.closeIgnoringException(connection);
			if(listCodSoc != null) {
				try {
					listCodSoc.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(callableStatement != null) {
				try {
					callableStatement.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(connection != null) {
				try {
					connection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			//fine LP PG21XX04 Leak
		}
		return codiceSocieta;
	}
	
	public String selFlagStampa(String codUtente, String codEnte,String tipoUfficio, String codUfficio, String impostaServizio,String numeroDocumento) throws DaoException {
		String res = "";
		CallableStatement callableStatement=null;
		Connection connection = null;
		String flagStampa = "";
		ResultSet out = null;
		try { 
			connection = getConnection();
			//PGNTCORE-24 - inizio
			//callableStatement = Helper.prepareCall(connection, getSchema(), Routines.EH1_FLAGSTAMPA.routine());
			callableStatement = MetaProcedure.prepareCall(connection, getSchema(), Routines.EH1_FLAGSTAMPA.routine());
			//PGNTCORE-24 - fine
			callableStatement.setString(1,codUtente);
			callableStatement.setString(2,"");
			callableStatement.setString(3,codEnte);
			callableStatement.setString(4,tipoUfficio);
			callableStatement.setString(5,codUfficio);
			callableStatement.setString(6,impostaServizio);
			callableStatement.setString(7,numeroDocumento);
			callableStatement.setString(8,"");
			
			callableStatement.execute();
			out = callableStatement.getResultSet();
			if (out.next()){
				flagStampa = out.getString(1);
			}
			
		} catch (SQLException e) {
			throw new DaoException(e);
		} catch (IllegalArgumentException e) {
			throw new DaoException(e);
		} catch (ProcedureReflectorException e) {
			throw new DaoException(e);
		} finally {
			//inizio LP PG21XX04 Leak
			//DAOHelper.closeIgnoringException(connection);
			if(out != null) {
				try {
					out.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(callableStatement != null) {
				try {
					callableStatement.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(connection != null) {
				try {
					connection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			//fine LP PG21XX04 Leak
		}
		return flagStampa;
		
	}
	public void UpdatePincodeAndFlag(String codiceSocieta, String codiceUtente,
			String chiaveEnte, String codiceFiscale, String pincodeEmail,
			String pincodeEmailPEC, String pincodeSms,
			String flgValidazionePinCodeMail, String flgValidazionePinCodeMailPEC,
			String flgValidazionePinCodeSms)  throws DaoException {

		CallableStatement callableStatement=null;
		Connection connection = null;
		try { 
			connection = getConnection();
			//PGNTCORE-24 - inizio
			callableStatement = MetaProcedure.prepareCall(connection, getSchema(), Routines.PYECASP_UPD_PIN.routine());
			//PGNTCORE-24 - fine
			callableStatement.setString(1, codiceSocieta);				
			callableStatement.setString(2, codiceUtente);
			callableStatement.setString(3, chiaveEnte);
			callableStatement.setString(4, codiceFiscale);
			callableStatement.setString(5, pincodeEmail);
			callableStatement.setString(6, pincodeEmailPEC);
			callableStatement.setString(7, pincodeSms);
			callableStatement.setString(8, flgValidazionePinCodeMail);
			callableStatement.setString(9, flgValidazionePinCodeMailPEC);
			callableStatement.setString(10, flgValidazionePinCodeSms);
			
			callableStatement.execute();

		} catch (SQLException e) {
			throw new DaoException(e);
		} catch (IllegalArgumentException e) {
			throw new DaoException(e);
		} catch (ProcedureReflectorException e) {
			throw new DaoException(e);
		} finally {
			//inizio LP PG21XX04 Leak
			//DAOHelper.closeIgnoringException(connection);
			if(callableStatement != null) {
				try {
					callableStatement.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(connection != null) {
				try {
					connection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			//fine LP PG21XX04 Leak
		}
	
	}
	
	//PG190480_001 SB - inizio
	public AnagraficaBollettino selByCF(
			String codiceSocieta,
			String codiceUtente,
			String codiceFiscale
	) throws DaoException {
		//inizio LP PG21XX04 Leak
		CallableStatement callableStatement = null;
		ResultSet data = null;
		CachedRowSet rowSet = null;
		//fine LP PG21XX04 Leak
		try	{
			AnagraficaBollettino res=null;
			//inizio LP PG21XX04 Leak
			//CallableStatement callableStatement = prepareCall("PYECASP_SEL_CFIS");
			callableStatement = prepareCall("PYECASP_SEL_CFIS");
			//fine LP PG21XX04 Leak
			callableStatement.setString(1, codiceSocieta);
			callableStatement.setString(2, codiceUtente);
			callableStatement.setString(3, codiceFiscale);
			
			if (callableStatement.execute()) {
				//inizio LP PG21XX04 Leak
				//ResultSet data = callableStatement.getResultSet();
				data = callableStatement.getResultSet();
				//fine LP PG21XX04 Leak
				loadWebRowSet(data);
				String selectXml = getWebRowSetXml();
				//inizio LP PG21XX04 Leak
				//CachedRowSet rowSet = null;
				//fine LP PG21XX04 Leak
				try {
					rowSet = Convert.stringToWebRowSet(selectXml);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				if (rowSet.next() ) 
				{
					res = new AnagraficaBollettino(rowSet);
					res.setAttribute(AnagraficaBollettinoDAO.SELECT_XML, selectXml);
				}
			}
			return res;
		} catch (SQLException x) {
			throw new DaoException(x);
		} catch (IllegalArgumentException x) {
			throw new DaoException(x);
		} catch (HelperException x) {
			throw new DaoException(x);
		}
		//inizio LP PG21XX04 Leak
		finally {
			if(rowSet != null) {
				try {
					rowSet.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(data != null) {
				try {
					data.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(callableStatement != null) {
				try {
					callableStatement.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		//fine LP PG21XX04 Leak
	}
	//PG190480_001 SB - fine
	
}

