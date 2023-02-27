package com.seda.payer.core.sosta.dao;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLData;
import java.sql.SQLException;
import java.sql.Types;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.sql.DataSource;
import javax.sql.rowset.CachedRowSet;



import com.seda.commons.string.Convert;
import com.seda.data.dao.DAOHelper;
import com.seda.data.helper.Helper;
import com.seda.data.helper.HelperException;
import com.seda.data.spi.PageInfo;

import com.seda.payer.core.dao.Routines;
import com.seda.payer.core.exception.DaoException;
import com.seda.payer.core.handler.BaseDaoHandler;
import com.seda.payer.core.wallet.bean.FattureRep;
import com.seda.payer.core.wallet.bean.Wallet;
import com.seda.payer.core.wallet.bean.WalletPageList;


public class SostaDAOImpl extends BaseDaoHandler implements SostaDAO  { 
	private static final long serialVersionUID = 1L;
	
	//inizio LP PG21XX04 Leak
	@Deprecated
	//fine LP PG21XX04 Leak
	public SostaDAOImpl(DataSource dataSource, String schema) throws SQLException {
		super(dataSource.getConnection(), schema);
	}
	public SostaDAOImpl(Connection connection, String schema) throws SQLException {
		super(connection, schema);
	}	
	
	
	public long calcolaSosta(String codiceUtente, String ente, String targa,
			String matricola, String nomevia, String numerocivico,
			Calendar dataorainiziososta, Calendar dataorafinesosta,
			long identificativoagevolazione) throws DaoException {


		CallableStatement callableStatement=null;
		ResultSet resultSet=null;
		Connection connection = null;
		CachedRowSet rowSet = null;
		
		String orario = String.valueOf(dataorainiziososta.get(Calendar.HOUR_OF_DAY));
		long tariffaOraria = 0;
		long importoSosta = 0;
		long milliSosta = (dataorafinesosta.getTimeInMillis() - dataorainiziososta.getTimeInMillis());
		long minutiSosta = milliSosta / (1000 * 60);
		//TODO: gestire eventuale arrotondamento per eccesso dei minuti
		
		try {
			connection = getConnection();
			callableStatement = Helper.prepareCall(connection, getSchema(), Routines.PYSATSP_SEL.routine());
			callableStatement.setString(1, codiceUtente);
			callableStatement.setString(2, ente);
			callableStatement.setString(3, matricola);
			callableStatement.setString(4, nomevia);
			callableStatement.setString(5, numerocivico);
			callableStatement.setString(6, orario);
			callableStatement.setString(7, String.valueOf(identificativoagevolazione));

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
				String zonaTariffaria = rowSet.getString(1);
				String matricolaTar = rowSet.getString(2);
				String nomeviaTar= rowSet.getString(3);
				String orarioTar= rowSet.getString(4);
				tariffaOraria = rowSet.getLong(5);
				String municipio = rowSet.getString(6);
				String ambito = rowSet.getString(7);
				String tratto = rowSet.getString(8);
				String agevolazioniTariffarie = rowSet.getString(9);
				
			}
		} catch (SQLException e) {
			throw new DaoException(e);
		} catch (IllegalArgumentException e) {
			throw new DaoException(e);
		} catch (HelperException e) {
			throw new DaoException(e);
		}finally {
			//if (closeConnection) {
				//inizio LP PG21XX04 Leak
				//DAOHelper.closeIgnoringException(callableStatement);
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
			//}
		}
		
		importoSosta =  (long)(tariffaOraria / 60f * minutiSosta); 
		//TODO: gestire eventuale arrotondamento
		
		return importoSosta;
	}
//
//	public Wallet select(Wallet wallet) throws DaoException {
//		return select(wallet,true);
//	}
//
//	public Wallet select(Wallet wallet,boolean closeConnection) throws DaoException {
//		CallableStatement callableStatement=null;
//		ResultSet resultSet=null;
//		Connection connection = null;
//		CachedRowSet rowSet = null;
//		try {
//			connection = getConnection();
//			callableStatement = Helper.prepareCall(connection, getSchema(), Routines.PYBRSSP_SEL.routine());
//			callableStatement.setString(1, wallet.getIdWallet());
//			callableStatement.setString(2, wallet.getCodiceFiscaleGenitore());
//			callableStatement.setString(3, wallet.getCodiceSocieta());
//			callableStatement.setString(4, wallet.getCuteCute());
//			callableStatement.setString(5, wallet.getChiaveEnte());
//			callableStatement.execute();
//
//			resultSet=callableStatement.getResultSet();
//			loadWebRowSet(resultSet);
//			String selectXml = getWebRowSetXml();
//			try {
//				rowSet = Convert.stringToWebRowSet(selectXml);
//			} catch (IOException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//
//			if (rowSet.next() ) {
//				String IdWallet = rowSet.getString(1);
//				String codiceSocieta = rowSet.getString(2);
//				String cuteCute= rowSet.getString(3);
//				String chiaveEnte= rowSet.getString(4);
//				String codiceAnagraficaGenitore = rowSet.getString(5);
//				String codiceFiscaleGenitore= rowSet.getString(6);
//				String tipoAnagrafica = rowSet.getString(7);
//				String numeroCell = rowSet.getString(8);
//				String indirizzoEmail = rowSet.getString(9);
//				Boolean flagAttivazione = rowSet.getString(10).equals("Y")?true:false;
//				Date dataCaricamento= rowSet.getDate(11);
//				Boolean flagPrimoAccesso = rowSet.getString(12).equals("N")?true:false;
//				BigDecimal importo = rowSet.getBigDecimal(13);;
//				String strImpo = importo.toString();
//				String strImpoInt = "";
//				String strImpoDec = "";
//				if (strImpo.startsWith("-")){
//					strImpo = strImpo.replace("-", "");
//					strImpoInt = strImpo.substring(0, strImpo.indexOf("."));
//					strImpoDec = strImpo.substring(strImpo.indexOf(".")+1);
//				} else {
//					strImpoInt = "0";
//					strImpoDec = "00";
//				}
//				String denominazione = rowSet.getString(14);
//				Calendar dataCaricamentoCal=Calendar.getInstance();
//				dataCaricamentoCal.setTime(dataCaricamento);
//
//				String denominazioneGenitore = rowSet.getString(15);
//				String indirizzoGenitore = rowSet.getString(16);
//				String denominazioneComuneGenitore = rowSet.getString(17);
//				String capComuneGenitore = rowSet.getString(18);
//				String provinciaGenitore = rowSet.getString(19);
//				Boolean  FlagEsclusioneSMSCortesia = rowSet.getString(20).equals("Y")?true:false;
//				Boolean  FlagEsclusioneSMSSollecito = rowSet.getString(21).equals("Y")?true:false;
//				Boolean  FlagEsclusioneSollecitoCartaceo = rowSet.getString(22).equals("Y")?true:false;
//				Boolean  FlagEsclusioneEvoluzioneIntimazione = rowSet.getString(23).equals("Y")?true:false;
//
//				String sollecitiSmsCortesiaInviati = rowSet.getString(24);
//				String sollecitiSmsSollecitiInviati = rowSet.getString(25);
//				String emailSollecitoInviate = rowSet.getString(26);
//				String sollecitiCartaceiInviati = rowSet.getString(27);
//				String datasollecitoinviato = rowSet.getString(28);
//				String ImportoOneriStampatiCaricati = rowSet.getString(29);
//				String ImportoOneriStampatiPagati = rowSet.getString(30);
//				String anagraficaBonificare = rowSet.getString(31);
//				String flagwelcomekit = rowSet.getString(32);
//				Boolean flagNoRivestizione = rowSet.getString(33).equals("Y")?true:false;
//				String codiceRid = rowSet.getString(34);
//				String ImportoOneriRendicontati = rowSet.getString(35);
//				wallet = new Wallet(IdWallet,codiceSocieta,cuteCute,chiaveEnte,codiceAnagraficaGenitore, 
//						codiceFiscaleGenitore,tipoAnagrafica,numeroCell,indirizzoEmail,flagAttivazione, 
//						dataCaricamentoCal,importo,flagPrimoAccesso, denominazioneGenitore, indirizzoGenitore,
//						denominazioneComuneGenitore, capComuneGenitore, provinciaGenitore,FlagEsclusioneSMSCortesia,FlagEsclusioneSMSSollecito,FlagEsclusioneSollecitoCartaceo,FlagEsclusioneEvoluzioneIntimazione,"",anagraficaBonificare,flagNoRivestizione,codiceRid );
//
//				wallet.setAttribute(WalletDAO.FLAG_WELCOMEKIT, flagwelcomekit);
//				wallet.setAttribute("denom", denominazione); 
//				wallet.setAttribute(WalletDAO.DENOMINAZIONECONTRIBUENTE, denominazione);
//
//				wallet.setAttribute(WalletDAO.SMSCORTESIAINVIATI, sollecitiSmsCortesiaInviati);
//				wallet.setAttribute(WalletDAO.SMSSOLLECITOINVIATI, sollecitiSmsSollecitiInviati);
//				wallet.setAttribute(WalletDAO.EMAILSOLLECITOINVIATE, emailSollecitoInviate);
//				wallet.setAttribute(WalletDAO.SOLLECATICARTACEI, sollecitiCartaceiInviati);
//
//				wallet.setAttribute(WalletDAO.DATAULTIMOSOLLECITO, datasollecitoinviato);
//				wallet.setAttribute(WalletDAO.IMPORTOONERISTAMPACARICATI, ImportoOneriStampatiCaricati);
//				wallet.setAttribute(WalletDAO.IMPORTOONERISTAMPAPAGATI, ImportoOneriStampatiPagati);
//				wallet.setAttribute(WalletDAO.IMPORTOONERIRENDICONTATI, ImportoOneriRendicontati);
//
//				wallet.setAttribute(WalletDAO.IMPORTO_CARRELLO, strImpoInt);
//				wallet.setAttribute(WalletDAO.IMPORTO_CARRELLO_DECIMALE, strImpoDec);
//
//				wallet.setAttribute(WalletDAO.SELECT_XML, selectXml);
//			}
//		} catch (SQLException e) {
//			throw new DaoException(e);
//		} catch (IllegalArgumentException e) {
//			throw new DaoException(e);
//		} catch (HelperException e) {
//			throw new DaoException(e);
//		}finally {
//			if (closeConnection) {
//				DAOHelper.closeIgnoringException(callableStatement);
//				DAOHelper.closeIgnoringException(connection);
//			}
//		}
//		return wallet;
//	}


//	//----------------------------------------------------------------------------------
//	public WalletPageList  walletList(Wallet wallet,String tipoServizio,String tipoSollecito,String flagrendicontato,String presenzaOneri, int rowsPerPage, int pageNumber, String OrderBy)	throws DaoException {
//		CallableStatement callableStatement=null;
//
//		Connection connection = null;
//		ResultSet data = null;
//		ResultSet dataRiep = null;
//		PageInfo pageInfo = null; 
//		WalletPageList walletPageList = null;
//		String[] brsLst  = new String[2];
//		String[] brsLst2  = new String[2];
//		try {
//			connection = getConnection();
//			callableStatement = Helper.prepareCall(connection, getSchema(), Routines.PYBRSSP_LST.routine());
//			callableStatement.setInt(1, pageNumber);                          /* rows per page */
//			callableStatement.setInt(2, rowsPerPage);                        /* page number*/
//			callableStatement.setString(3,OrderBy);
//			callableStatement.setString(4,wallet.getCodiceSocieta());
//			callableStatement.setString(5,wallet.getCuteCute());
//			callableStatement.setString(6,wallet.getChiaveEnte());
//			callableStatement.setString(7,wallet.getIdWallet());
//			callableStatement.setString(8,wallet.getCodiceAnagraficaGenitore());
//			callableStatement.setString(9,wallet.getCodiceFiscaleGenitore());
//			callableStatement.setString(10, (String)wallet.getAttribute(WalletDAO.PERIDOCARICO_DA));
//			callableStatement.setString(11, (String)wallet.getAttribute(WalletDAO.PERIDOCARICO_A));
//			callableStatement.setString(12,tipoSollecito);
//			callableStatement.setString(13,presenzaOneri);
//			callableStatement.setString(14,tipoServizio);
//			callableStatement.setString(15,flagrendicontato);
//
//			if ( wallet.isFlagEsclusioneSMSCortesia() ==null ) {
//				callableStatement.setString(16, "");
//			} else {
//				callableStatement.setString(16, wallet.isFlagEsclusioneSMSCortesia()?"Y":"N");
//			}
//
//			if ( wallet.isFlagEsclusioneSMSSollecito() ==null ) {
//				callableStatement.setString(17, "");
//			} else {
//				callableStatement.setString(17, wallet.isFlagEsclusioneSMSSollecito()?"Y":"N");
//			}
//
//			if ( wallet.isFlagEsclusioneSollecitoCartaceo() ==null ) {
//				callableStatement.setString(18, "");
//			} else {
//				callableStatement.setString(18, wallet.isFlagEsclusioneSollecitoCartaceo()?"Y":"N");
//			}
//			if ( wallet.isFlagEsclusioneEvoluzioneIntimazione() ==null ) {
//				callableStatement.setString(19, "");
//			} else {
//				callableStatement.setString(19, wallet.isFlagEsclusioneEvoluzioneIntimazione()?"Y":"N");
//			}
//
//			/* we select */
//			callableStatement.registerOutParameter(20, Types.VARCHAR);
//			/* we register row start */
//			callableStatement.registerOutParameter(21, Types.INTEGER);
//			/* we register row end */
//			callableStatement.registerOutParameter(22, Types.INTEGER);
//			/* we register total rows */
//			callableStatement.registerOutParameter(23, Types.INTEGER);
//			/* we register total pages */
//			callableStatement.registerOutParameter(24, Types.SMALLINT);
//
//			/* we execute procedure */
//
//			if(callableStatement.execute())	{
//				pageInfo = new PageInfo();
//				pageInfo.setPageNumber(pageNumber);
//				pageInfo.setRowsPerPage(rowsPerPage);
//				pageInfo.setFirstRow(callableStatement.getInt(21));
//				pageInfo.setLastRow(callableStatement.getInt(22));
//				pageInfo.setNumRows(callableStatement.getInt(23));
//				pageInfo.setNumPages(callableStatement.getInt(24));
//
//				data = callableStatement.getResultSet();
//				loadWebRowSet(data);
//
//				brsLst[0] = getWebRowSetXml();
//				//				walletPageList = new WalletPageList(pageInfo, "00","",brsLst[0]);
//				//				return walletPageList;
//			}
//
//			if(callableStatement.getMoreResults()) {
//				dataRiep = callableStatement.getResultSet();
//
//				loadWebRowSet(dataRiep);
//				brsLst[1] = getWebRowSetXml();
//
//			}
//			walletPageList = new WalletPageList(pageInfo, "00","",brsLst);
//			return walletPageList;
//
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


//	public String listPagamenti(Wallet wallet)  throws DaoException {
//		String walletlistBollettiniXml="";
//		CallableStatement callableStatement=null;
//		Connection connection = null;
//		ResultSet data = null;
//		try {
//			connection = getConnection();
//
//			callableStatement = Helper.prepareCall(connection, getSchema(), Routines.PYPGBTB_LST_MG.routine());
//			callableStatement.setString(1,wallet.getIdWallet());
//			callableStatement.setString(2, (String)wallet.getAttribute("PGBDATA_PAGAMENTO_DA"));
//			callableStatement.setString(3, (String)wallet.getAttribute("PGBDATA_PAGAMENTO_A"));
//
//			if(callableStatement.execute())	{
//				data = callableStatement.getResultSet();
//				loadWebRowSet(data);
//				walletlistBollettiniXml = getWebRowSetXml();
//			}
//
//
//
//		} catch (SQLException e) {
//			e.printStackTrace();
//		} catch (IllegalArgumentException e) {
//			e.printStackTrace();
//		} catch (HelperException e) {
//			e.printStackTrace();
//		} finally {
//			DAOHelper.closeIgnoringException(connection);
//		}
//
//		return walletlistBollettiniXml;
//	}

//
//
//	public List<String> getSollecitoAsList(Wallet wallet)  throws DaoException {
//		List<String> sollecitoAsString= new ArrayList<String>();
//		String walletlistSollecitiXml="";
//		CallableStatement callableStatement=null;
//		Connection connection = null;
//		ResultSet data = null;
//		try {
//			connection = getConnection();
//
//			callableStatement = Helper.prepareCall(connection, getSchema(), Routines.PYSOLSP_LST_ANNO.routine());
//			callableStatement.setString(1,wallet.getIdWallet());
//			callableStatement.setString(2, (String)wallet.getAttribute("PGBDATA_PAGAMENTO_DA"));
//			callableStatement.setString(3, (String)wallet.getAttribute("PGBDATA_PAGAMENTO_A"));
//			callableStatement.setString(4, "");
//			callableStatement.setString(5, "");
//			callableStatement.setInt(6, 0);
//
//			if(callableStatement.execute())	{
//				data = callableStatement.getResultSet();
//				if(data.next()){
//					sollecitoAsString.add(data.getString(1)); //SOL_KBRSKBRS
//					sollecitoAsString.add(data.getString(2)); //SOL_CSOLTIPO
//					sollecitoAsString.add(data.getString(3)); //SOL_GSOLDTA
//					sollecitoAsString.add(data.getString(4)); //SOL_DSOLFILE
//					sollecitoAsString.add(data.getString(5)); //SOL_CSOLCONE
//					sollecitoAsString.add(data.getString(6)); //SOL_DSOLDESC
//					sollecitoAsString.add(data.getString(7)); //SOL_ISOLIMPO
//					sollecitoAsString.add(data.getString(8)); //SOL_ISOLIPAG
//					sollecitoAsString.add(data.getString(9)); //SOL_ISOLIREN
//					sollecitoAsString.add(data.getString(10)); //SOL_CSRVCODI
//					sollecitoAsString.add(data.getString(11)); //SOL_DSOLDDES
//					sollecitoAsString.add(data.getString(12)); //SOL_ISOLISOL
//					sollecitoAsString.add(data.getString(13)); //SOL_CSOLNTEL
//					sollecitoAsString.add(data.getString(14)); //SOL_DSOLMAIL
//					sollecitoAsString.add(data.getString(15)); //SOL_CSOLIDFL
//					sollecitoAsString.add(data.getString(16)); //SOL_CSOLCESI
//					sollecitoAsString.add(data.getString(17)); //SOL_DSOLDESI
//					sollecitoAsString.add(data.getString(18)); //SOL_FSOLFTMB
//					sollecitoAsString.add(data.getString(19)); //SOL_NSOLPROG
//					sollecitoAsString.add(data.getString(20)); //SOL_FSOLENTE
//					sollecitoAsString.add(data.getString(21)); //SOL_FSOLVALI
//					sollecitoAsString.add(data.getString(22)); //SOL_FSOLDISC
//					sollecitoAsString.add(data.getString(23)); //SOL_GSOLINSE
//					sollecitoAsString.add(data.getString(24)); //SOL_CSOLOPER
//				}
//			}
//
//
//
//		} catch (SQLException e) {
//			e.printStackTrace();
//		} catch (IllegalArgumentException e) {
//			e.printStackTrace();
//		} catch (HelperException e) {
//			e.printStackTrace();
//		} finally {
//			//DAOHelper.closeIgnoringException(connection);
//		}
//
//		return sollecitoAsString;
//	}

}
