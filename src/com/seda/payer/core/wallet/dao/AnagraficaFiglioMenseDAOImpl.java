package com.seda.payer.core.wallet.dao;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.sql.Types;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import javax.sql.DataSource;
import javax.sql.rowset.CachedRowSet;
import com.seda.commons.string.Convert;
import com.seda.data.helper.HelperException;
import com.seda.data.spi.PageInfo;
import com.seda.payer.core.dao.Routines;
import com.seda.payer.core.exception.DaoException;
import com.seda.payer.core.handler.BaseDaoHandler;
import com.seda.payer.core.wallet.bean.AnagraficaFiglioMense;
import com.seda.payer.core.wallet.bean.WalletPageList;

public class AnagraficaFiglioMenseDAOImpl extends BaseDaoHandler  implements AnagraficaFiglioMenseDAO  {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//inizio LP 20240918 - PGNTWPB-3
	//protected CallableStatement callableStatementAFM_INS = null;
	//fine LP 20240918 - PGNTWPB-3
	//inizio LP PG21XX04 Leak
	@Deprecated
	//fine LP PG21XX04 Leak
	public AnagraficaFiglioMenseDAOImpl(DataSource dataSource, String schema) throws SQLException {
		super(dataSource.getConnection(), schema);
	}

	public AnagraficaFiglioMenseDAOImpl(Connection connection, String schema) throws SQLException {
		super(connection, schema);
	}

	public AnagraficaFiglioMense select(AnagraficaFiglioMense figlio) throws DaoException {
		CallableStatement callableStatement = null;
		ResultSet resultSet = null;
		Connection connection = null;
		CachedRowSet rowSet = null;
		try {
			connection = getConnection();
			//inizio LP 20240921 - PGNTCORE-24
			//callableStatement = Helper.prepareCall(connection, getSchema(), Routines.PYAFMSP_SEL.routine());
            callableStatement = prepareCall(Routines.PYAFMSP_SEL.routine());
			//fine LP 20240921 - PGNTCORE-24
			callableStatement.setString(1, figlio.getIdWallet());
			callableStatement.setString(2, figlio.getCodiceFiscaleFiglio());
			callableStatement.setString(3, figlio.getCodiceServizio());
			callableStatement.execute();
			resultSet=callableStatement.getResultSet();
			loadWebRowSet(resultSet);
			String selectXml = getWebRowSetXml();
			try {
				rowSet = Convert.stringToWebRowSet(selectXml);
			} catch (IOException e) {
				e.printStackTrace();
			}
			if (rowSet.next() ) {
				String idWallet = rowSet.getString(1);
				String codiceServizio = rowSet.getString(2);
				String codiceScuola= rowSet.getString(3);
				String codiceAnagraficaFiglio = rowSet.getString(4);
				String codiceFiscaleFiglio= rowSet.getString(5);
				String codiceAnagraficaGenitore = rowSet.getString(6);
				String codiceFiscaleGenitore= rowSet.getString(7);
				String denominazioneFiglio = rowSet.getString(8);
				Timestamp dataVal= rowSet.getTimestamp(9);
				String strDataVal = (new SimpleDateFormat("dd/MM/yyyy")).format(dataVal);
				Date dataValid = new Date(dataVal.getTime());
				Calendar dataValidita = Calendar.getInstance();
				dataValidita.setTime(dataValid);
				BigDecimal importoTariffa = rowSet.getBigDecimal(10);
				String importoTariffaStr = importoTariffa.toString();
				importoTariffaStr= importoTariffaStr.replace(",", "");
				importoTariffaStr= importoTariffaStr.replace(".", ",");
				BigDecimal importoIsee = rowSet.getBigDecimal(11);
				String importoIseeStr;
				if (importoIsee != null ) {
					importoIseeStr = importoIsee.toString();
					importoIseeStr= importoIseeStr.replace(",", "");
					importoIseeStr= importoIseeStr.replace(".", ",");
				} else {
					importoIseeStr = null;
				}
				String tipologiaTariffa = rowSet.getString(12);
				String classeSezione = rowSet.getString(13);
				String flagAttivazione = rowSet.getString(14);
				Timestamp dataCar= rowSet.getTimestamp(15);
				String strDataCar = (new SimpleDateFormat("dd/MM/yyyy")).format(dataCar);
				Date dataCaric = new Date(dataCar.getTime());
				Calendar dataCaricamento = Calendar.getInstance();
				dataCaricamento.setTime(dataCaric);
				String denominazioneScuola = rowSet.getString(16);
				String flagSospensione = rowSet.getString(17);		//PG150310_001 GG
				//PG150310_001 GG - inizio
				//figlio = new AnagraficaFiglioMense(idWallet, codiceServizio, codiceAnagraficaFiglio, codiceFiscaleFiglio, codiceAnagraficaGenitore, codiceFiscaleGenitore, denominazioneFiglio, codiceScuola, dataValidita, tipologiaTariffa, classeSezione, importoTariffa, importoIsee, flagAttivazione, dataCaricamento, null, null, null,"");
				figlio = new AnagraficaFiglioMense(idWallet, codiceServizio, codiceAnagraficaFiglio, codiceFiscaleFiglio, codiceAnagraficaGenitore, codiceFiscaleGenitore, denominazioneFiglio, codiceScuola, dataValidita, tipologiaTariffa, classeSezione, importoTariffa, importoIsee, flagAttivazione, dataCaricamento, null, null, null,"",flagSospensione);
				//PG150310_001 GG - fine
				figlio.setAttribute(AnagraficaFiglioMenseDAO.ANAGRAFICA_DENOMINAZIONE_FIGLIO, denominazioneFiglio);
				figlio.setAttribute(AnagraficaFiglioMenseDAO.ANAGRAFICA_DENOMINAZIONE_SCUOLA, denominazioneScuola);
				figlio.setAttribute(AnagraficaFiglioMenseDAO.ANAGRAFICA_DATA_VALIDITA, strDataVal);
				figlio.setAttribute(AnagraficaFiglioMenseDAO.ANAGRAFICA_DATA_CARICAMENTO, strDataCar);
				figlio.setAttribute(AnagraficaFiglioMenseDAO.IMPORTOTARIFFA, importoTariffaStr);
				figlio.setAttribute(AnagraficaFiglioMenseDAO.IMPORTOISEE, importoIseeStr);
			}
		} catch (SQLException e) {
			throw new DaoException(e);
		} catch (IllegalArgumentException e) {
			throw new DaoException(e);
		} catch (HelperException e) {
			throw new DaoException(e);
		} finally {
			//inizio LP PG21XX04 Leak
			//DAOHelper.closeIgnoringException(callableStatement);
			//if (connection!=null) DAOHelper.closeIgnoringException(connection);
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
		return figlio;
	}

	//inizio LP 20240916 - PGNTCORE-24/PGNTWPB-3
	public AnagraficaFiglioMense insertBatch(AnagraficaFiglioMense anagraficaFiglioMense) throws DaoException {
		return insertBatchTail(true, anagraficaFiglioMense);
	}

	public AnagraficaFiglioMense insertBatchTail(boolean bflagUpdateAutocommit, AnagraficaFiglioMense anagraficaFiglioMense) throws DaoException {
	//fine LP 20240916 - PGNTCORE-24/PGNTWPB-3
		String errori;
		CallableStatement callableStatementAFM_INS = null; //LP 20240918 - PGNTWPB-3
		try {
			//inizio LP 20240916 - PGNTCORE-24/PGNTWPB-3
			//if (callableStatementAFM_INS == null) {
			//	callableStatementAFM_INS = Helper.prepareCall(getConnection(), getSchema(), Routines.PYAFMSP_INS.routine());
			//}
			callableStatementAFM_INS = prepareCall(bflagUpdateAutocommit, Routines.PYAFMSP_INS.routine());
        	//fine LP 20240916 - PGNTCORE-24/PGNTWPB-3
			callableStatementAFM_INS.setString(1, (String)anagraficaFiglioMense.getAttribute("cutecute"));
			callableStatementAFM_INS.setString(2, (String)anagraficaFiglioMense.getAttribute("codente"));
			callableStatementAFM_INS.setString(3, anagraficaFiglioMense.getCodiceAnagraficaFiglio());
			callableStatementAFM_INS.setString(4, anagraficaFiglioMense.getCodiceFiscaleFiglio());
			callableStatementAFM_INS.setString(5, anagraficaFiglioMense.getCodiceAnagraficaGenitore());
			callableStatementAFM_INS.setString(6, anagraficaFiglioMense.getCodiceFiscaleGenitore());
			callableStatementAFM_INS.setString(7, anagraficaFiglioMense.getDenominazioneFiglio());
			callableStatementAFM_INS.setString(8, anagraficaFiglioMense.getCodiceScuola());
			callableStatementAFM_INS.setDate(9 , new Date(anagraficaFiglioMense.getDataValidita().getTimeInMillis()));
			callableStatementAFM_INS.setBigDecimal(10, anagraficaFiglioMense.getImportoTariffa());
			callableStatementAFM_INS.setBigDecimal(11, anagraficaFiglioMense.getImportoIsee());			
			callableStatementAFM_INS.setString(12, anagraficaFiglioMense.getTipologiaTariffa());
			callableStatementAFM_INS.setString(13, anagraficaFiglioMense.getClasseSezione());
			callableStatementAFM_INS.setString(14, anagraficaFiglioMense.getFlagAttivazione()); 
			callableStatementAFM_INS.setDate(15, new Date(anagraficaFiglioMense.getDataNascitaFiglio().getTimeInMillis()));
			callableStatementAFM_INS.setString(16, anagraficaFiglioMense.getFlagSospensione());	//PG150310_001 GG
			callableStatementAFM_INS.registerOutParameter(17, Types.VARCHAR);
			callableStatementAFM_INS.execute();
			errori = callableStatementAFM_INS.getString(17);
			anagraficaFiglioMense.setAttribute("errori", errori);
		} catch (SQLException e) {
			System.out.println(e);
			throw new DaoException(e);
		} catch (IllegalArgumentException e) {
			System.out.println(e);
			throw new DaoException(e);
		} catch (HelperException e) {
			System.out.println(e);
			throw new DaoException(e);
		} finally {
//			DAOHelper.closeIgnoringException(callableStatement);
			//inizio LP 20240916 - PGNTCORE-24/PGNTWPB-3
			if (callableStatementAFM_INS != null) {
				try {
					callableStatementAFM_INS.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
        	//fine LP 20240916 - PGNTCORE-24/PGNTWPB-3
		}
		return anagraficaFiglioMense;
	}


	public WalletPageList figliList(AnagraficaFiglioMense figlio)	throws DaoException {
		CallableStatement callableStatement=null;
		Connection connection = null;
		ResultSet data = null;
		PageInfo pageInfo = null;
		WalletPageList walletPageList = null;
		try {
			connection = getConnection();
			//inizio LP 20240921 - PGNTCORE-24
			//callableStatement = Helper.prepareCall(connection, getSchema(), Routines.PYAFMSP_LST.routine());
            callableStatement = prepareCall(Routines.PYAFMSP_LST.routine());
			//fine LP 20240921 - PGNTCORE-24
			callableStatement.setString(1,figlio.getIdWallet());
			callableStatement.setString(2,figlio.getCodiceAnagraficaGenitore());
			callableStatement.setString(3,figlio.getCodiceServizio());
			/* we execute procedure */
			if(callableStatement.execute())	{
				// Non essendo prevista la paginazione imposto tutto a default
//				pageInfo = new PageInfo();
//				pageInfo.setPageNumber(1);
//				pageInfo.setRowsPerPage(100);
//				pageInfo.setFirstRow(1);
				data = callableStatement.getResultSet();
				loadWebRowSet(data);
				walletPageList = new WalletPageList(null, "00","",getWebRowSetXml());
				return walletPageList;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			walletPageList = new WalletPageList(pageInfo, "01","Sql-Exception","");
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
			walletPageList = new WalletPageList(pageInfo, "01","Sql-Exception","");
		} catch (HelperException e) {
			e.printStackTrace();
			walletPageList = new WalletPageList(pageInfo, "01","Sql-Exception","");
		} finally {
			//inizio LP PG21XX04 Leak
			//DAOHelper.closeIgnoringException(connection);
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
		return walletPageList;
	}
	
//	public WalletPageList presenzeListTot(AnagraficaFiglioMense figlio)	throws DaoException {
//		CallableStatement callableStatement=null;
//		Connection connection = null;
//		ResultSet data = null;
//		PageInfo pageInfo = null;
//		WalletPageList walletPageList = null;
//		try {
//			connection = getConnection();
//			callableStatement = Helper.prepareCall(connection, getSchema(), Routines.PYAFMSP_LST_PRE_TOT.routine());
//			callableStatement.setString(1,figlio.getIdWallet());
//			callableStatement.setString(2,figlio.getCodiceScuola());
//			callableStatement.setString(3,figlio.getCodiceAnagraficaFiglio());
//			callableStatement.setString(4,figlio.getCodiceFiscaleFiglio());
//			callableStatement.setString(5,figlio.getCodiceServizio());
//			callableStatement.setString(6,(String)figlio.getAttribute(AnagraficaFiglioMenseDAO.ANAGRAFICA_ANNO_SCOLASTICO));
//			
//			/* we execute procedure */
//			if(callableStatement.execute())	{
//				// Non essendo prevista la paginazione imposto tutto a default
////				pageInfo = new PageInfo();
////				pageInfo.setPageNumber(1);
////				pageInfo.setRowsPerPage(100);
////				pageInfo.setFirstRow(1);
//
//
//				data = callableStatement.getResultSet();
//				loadWebRowSet(data);
//				walletPageList = new WalletPageList(null, "00","",getWebRowSetXml());
//				
//				return walletPageList;
//			}	
//		} catch (SQLException e) {
//			e.printStackTrace();
//			walletPageList = new WalletPageList(pageInfo, "01","Sql-Exception","");
//		} catch (IllegalArgumentException e) {
//			e.printStackTrace();
//			walletPageList = new WalletPageList(pageInfo, "01","Sql-Exception","");
//		} catch (HelperException e) {
//			e.printStackTrace();
//			walletPageList = new WalletPageList(pageInfo, "01","Sql-Exception","");
//		} finally {
//			DAOHelper.closeIgnoringException(connection);
//		}
//		return walletPageList;
//	}
	 
	public WalletPageList presenzeList(AnagraficaFiglioMense figlio) throws DaoException {
		CallableStatement callableStatement=null;
		Connection connection = null;
		ResultSet data = null;
		PageInfo pageInfo = null;
		WalletPageList walletPageList = null;
		try {
			connection = getConnection();
			//inizio LP 20240921 - PGNTCORE-24
			//callableStatement = Helper.prepareCall(connection, getSchema(), Routines.PYAFMSP_LST_PRE.routine());
            callableStatement = prepareCall(Routines.PYAFMSP_LST_PRE.routine());
			//fine LP 20240921 - PGNTCORE-24
			callableStatement.setString(1,figlio.getIdWallet());
			callableStatement.setString(2,figlio.getCodiceScuola());
			callableStatement.setString(3,figlio.getCodiceAnagraficaFiglio());
			callableStatement.setString(4,figlio.getCodiceFiscaleFiglio());
			callableStatement.setString(5,figlio.getCodiceServizio());
			callableStatement.setString(6,(String)figlio.getAttribute(AnagraficaFiglioMenseDAO.ANAGRAFICA_ANNO_SCOLASTICO));
			callableStatement.setString(7,(String)figlio.getAttribute(AnagraficaFiglioMenseDAO.ANAGRAFICA_CODICE_UTENTE));
			callableStatement.setString(8,(String)figlio.getAttribute(AnagraficaFiglioMenseDAO.ANAGRAFICA_CHIAVE_ENTE));
			callableStatement.setString(9,(String)figlio.getAttribute(AnagraficaFiglioMenseDAO.ANAGRAFICA_CODICE_SOCIETA));
			callableStatement.setString(10,figlio.getCodiceAnagraficaGenitore());
			callableStatement.registerOutParameter(11, Types.VARCHAR);
			/* we execute procedure */
			if(callableStatement.execute())	{
				String flagMsg = "";
				flagMsg = callableStatement.getString(11);
				data = callableStatement.getResultSet();
				loadWebRowSet(data);
				// ciclo per vedere se c'è almeno un record con le intimazioni
				String msg = "";
				if (flagMsg.equals("Y")) {
					System.out.println("intimazioni presenti");
					msg = "Attenzione: i consumi evoluti in intimazione non sono pagabili sul borsellino elettronico.</br>I documenti di intimazione possono essere consultati e pagati collegandosi all'Estratto Conto.";
				}
				String selectXml = getWebRowSetXml();
				walletPageList = new WalletPageList(pageInfo, "00",msg,selectXml);
				return walletPageList;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			walletPageList = new WalletPageList(pageInfo, "01","Sql-Exception","");
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
			walletPageList = new WalletPageList(pageInfo, "01","Sql-Exception","");
		} catch (HelperException e) {
			e.printStackTrace();
			walletPageList = new WalletPageList(pageInfo, "01","Sql-Exception","");
		} finally {
			//inizio LP PG21XX04 Leak
			//DAOHelper.closeIgnoringException(callableStatement);
			//if (connection!=null) DAOHelper.closeIgnoringException(connection);
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
		return walletPageList;
	}
	
	public WalletPageList listAnnoScolastico(AnagraficaFiglioMense figlio) throws DaoException {
		CallableStatement callableStatement=null;
		Connection connection = null;
		ResultSet resultSet=null;
		WalletPageList walletPageList = null;
		try {
			connection = getConnection();
			//inizio LP 20240921 - PGNTCORE-24
 			//callableStatement = Helper.prepareCall(connection, getSchema(), Routines.PYAFMSP_LST_ANNO_SCOL.routine());
            callableStatement = prepareCall(Routines.PYAFMSP_LST_ANNO_SCOL.routine());
			//fine LP 20240921 - PGNTCORE-24
			callableStatement.setString(1,figlio.getIdWallet());
			callableStatement.setString(2,figlio.getCodiceServizio());
			callableStatement.setString(3,figlio.getCodiceScuola());
			callableStatement.setString(4,figlio.getCodiceAnagraficaFiglio());
			callableStatement.execute();
			resultSet=callableStatement.getResultSet();
			loadWebRowSet(resultSet);
			walletPageList = new WalletPageList(null, "00","",getWebRowSetXml());
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
		return walletPageList;
	}

	public WalletPageList presenzeListPerBorsellino(AnagraficaFiglioMense figlio) throws DaoException {
		CallableStatement callableStatement=null;
		Connection connection = null;
		ResultSet data = null;
		PageInfo pageInfo = null;
		WalletPageList walletPageList = null;
		Integer rowsPerPage=(Integer)figlio.getAttribute("rowsPerPage");
		Integer pageNumber=(Integer) figlio.getAttribute("pageNumber");
		try {
			connection = getConnection();
			//inizio LP 20240921 - PGNTCORE-24
			//callableStatement = Helper.prepareCall(connection, getSchema(), Routines.PYAFMSP_LST_TOT.routine());
            callableStatement = prepareCall(Routines.PYAFMSP_LST_TOT.routine());
			//fine LP 20240921 - PGNTCORE-24
			callableStatement.setInt(1,(int) pageNumber);
			callableStatement.setInt(2,(int) rowsPerPage);
			callableStatement.setString(3,(String) figlio.getAttribute("order"));
			callableStatement.setString(4,figlio.getIdWallet());
			callableStatement.setString(5,(String)figlio.getAttribute(AnagraficaFiglioMenseDAO.ANAGRAFICA_CODICE_UTENTE));
			callableStatement.setString(6,(String)figlio.getAttribute(AnagraficaFiglioMenseDAO.ANAGRAFICA_CHIAVE_ENTE));
			callableStatement.setString(7,(String)figlio.getAttribute(AnagraficaFiglioMenseDAO.ANAGRAFICA_CODICE_SOCIETA));
			/* we register row start */
			callableStatement.registerOutParameter(8, Types.INTEGER);
			/* we register row end */
			callableStatement.registerOutParameter(9, Types.INTEGER);
			/* we register total rows */
			callableStatement.registerOutParameter(10, Types.INTEGER);
			/* we register total pages */
			callableStatement.registerOutParameter(11, Types.SMALLINT);
			/* we execute procedure */
			if(callableStatement.execute())	{
				pageInfo = new PageInfo();
				pageInfo.setPageNumber(pageNumber);
				pageInfo.setRowsPerPage(rowsPerPage);
				pageInfo.setFirstRow(callableStatement.getInt(8));
				pageInfo.setLastRow(callableStatement.getInt(9));
				pageInfo.setNumRows(callableStatement.getInt(10));
				pageInfo.setNumPages(callableStatement.getInt(11));
				data = callableStatement.getResultSet();
				loadWebRowSet(data);
				//walletPageList = new WalletPageList(null, "00","",getWebRowSetXml());
				String selectXml = getWebRowSetXml();
				walletPageList = new WalletPageList(pageInfo, "00","",selectXml);
				return walletPageList;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			walletPageList = new WalletPageList(pageInfo, "01","Sql-Exception","");
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
			walletPageList = new WalletPageList(pageInfo, "01","Sql-Exception","");
		} catch (HelperException e) {
			e.printStackTrace();
			walletPageList = new WalletPageList(pageInfo, "01","Sql-Exception","");
		} finally {
			//inizio LP PG21XX04 Leak
			//DAOHelper.closeIgnoringException(callableStatement);
			//if (connection!=null) DAOHelper.closeIgnoringException(connection);
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
		return walletPageList;
	}
	
	//inizio LP PG21XX04 Leak
	//Nota. Metodo usato solo da procedure batch
	//fine LP PG21XX04 Leak
	//inizio LP 20240918 - PGNTWPB-3
	public ArrayList<AnagraficaFiglioMense> updateFlagSospensione(AnagraficaFiglioMense figlio) throws  DaoException {
		return updateFlagSospensioneBatch(true, true, figlio);
	}

	public ArrayList<AnagraficaFiglioMense> updateFlagSospensioneBatch(boolean bFlagUpdateAutocommit, boolean bCloseStat, AnagraficaFiglioMense figlio) throws  DaoException {
	//fine LP 20240918 - PGNTWPB-3
		CallableStatement callableStatement = null;
		ResultSet data = null;
		ArrayList<AnagraficaFiglioMense> anagraficaFiglioMenseList = null;
		int recordsAggiornati=0;
		try {
			//inizio LP 20240918 - PGNTWPB-3
			//callableStatement = Helper.prepareCall(getConnection(), getSchema(), Routines.PYAFMSP_UPD_FSOS.routine());
			callableStatement = prepareCall(bFlagUpdateAutocommit, Routines.PYAFMSP_UPD_FSOS.routine());
        	//fine LP 20240918 - PGNTWPB-3
			callableStatement.setString(1, figlio.getCodiceFiscaleGenitore());
			callableStatement.setString(2, figlio.getCodiceFiscaleFiglio());
			callableStatement.setString(3, figlio.getFlagSospensione());
			callableStatement.registerOutParameter(4, Types.INTEGER);
			if (callableStatement.execute()) {
				recordsAggiornati=callableStatement.getInt(4);
				data = callableStatement.getResultSet();
				anagraficaFiglioMenseList = new ArrayList<AnagraficaFiglioMense>();
				while (data.next()) {
					AnagraficaFiglioMense anagraficaFiglioMense = new AnagraficaFiglioMense();
					anagraficaFiglioMense.setIdWallet(data.getString(1));
					anagraficaFiglioMense.setCodiceFiscaleGenitore(data.getString(2));
					anagraficaFiglioMense.setCodiceFiscaleFiglio(data.getString(3));
					anagraficaFiglioMense.setAttribute("recordsAggiornati", recordsAggiornati);
					anagraficaFiglioMenseList.add(anagraficaFiglioMense);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DaoException(01,"Problemi generici nell'aggiornamento del flag sospensione: " + e.getMessage() + " ErrorCode: " + e.getErrorCode() + "SQLState: " + e.getSQLState(),e);
		} catch (HelperException e) {
			e.printStackTrace();
			throw new DaoException(01,"Problemi generici nell'aggiornamento del flag sospensione: "+ e.getMessage(),e);
		} finally {
			//inizio LP PG21XX04 Leak
			//DAOHelper.closeIgnoringException(callableStatement);
			if (data != null) {
				try {
					data.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(bCloseStat) {
				if (callableStatement != null) {
					try {
						callableStatement.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
			}
			//fine LP PG21XX04 Leak
		}
		return anagraficaFiglioMenseList;
	}
}