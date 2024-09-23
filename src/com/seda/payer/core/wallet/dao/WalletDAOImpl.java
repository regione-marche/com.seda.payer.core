package com.seda.payer.core.wallet.dao;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
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
import com.seda.data.helper.HelperException;
import com.seda.data.spi.PageInfo;
import com.seda.payer.core.dao.Routines;
import com.seda.payer.core.exception.DaoException;
import com.seda.payer.core.handler.BaseDaoHandler;
import com.seda.payer.core.wallet.bean.CertificazioneBonusNido;
import com.seda.payer.core.wallet.bean.FattureRep;
import com.seda.payer.core.wallet.bean.Wallet;
import com.seda.payer.core.wallet.bean.WalletPageList;

@SuppressWarnings("unused")
public class WalletDAOImpl extends BaseDaoHandler implements WalletDAO  { 
	private static final long serialVersionUID = 1L;
	protected CallableStatement callableStatementServAnno = null;
	protected CallableStatement callableStatementOnere = null;
	protected CallableStatement callableStatementBollettino = null;
	protected CallableStatement callableStatementBRSINS = null;
	protected CallableStatement callableStatementBRSUP = null;
	protected CallableStatement callableStatementBRSBATCH = null;
	//inizio LP 20240913 - PAGONET-604
	protected CallableStatement callableStatementBRSLSTSMSEMAIL = null;
	protected CallableStatement callableStatementSOLDELSOL = null;
	protected CallableStatement callableStatementSOLUPDLSTW = null;
	protected CallableStatement callableStatementBRLSPLST = null;
	protected CallableStatement callableStatementRCPSEL = null;
	protected CallableStatement callableStatementBRSSRVLST = null;
	//fine LP 20240913 - PAGONET-604
	//inizio LP 20240913 - PAGONET-24/PGNTWPB-3
	protected CallableStatement callableStatementBRSSEL = null;
	//fine LP 20240913 - PAGONET-24/PGNTWPB-3
	//inizio LP PG21XX04 Leak
	@Deprecated
	//fine LP PG21XX04 Leak
	public WalletDAOImpl(DataSource dataSource, String schema) throws SQLException {
		super(dataSource.getConnection(), schema);
	}
	public WalletDAOImpl(Connection connection, String schema) throws SQLException {
		super(connection, schema);
	}	

	//inizio LP PG21XX04 Leak
	//Nota: nei metodi usati dalle procedude batch deve essere
	//      il chiamante a chiudere la connection e per pulizia
	//      forse bisognerebbe introdurre un metodo che chiude 
	//      se aperti i vari callableStatement dichiarati come
	//      attributi della classe.
	//      Questa nota va estesa a tutte le altri classi java 
	//      utilizzate per la gestione del borsellino.
	//fine LP PG21XX04 Leak
	
	public Wallet insertBatch(Wallet wallet) throws DaoException {
//		CallableStatement callableStatement=null;
//		Connection connection = null; 
		String idWallet="";
		String errori = "";
		try { 
//			connection = getConnection();
			if (callableStatementBRSINS == null) {
				//inizio LP 20240828 - PGNTCORE-24/PGNTWPB-3
				//callableStatementBRSINS = Helper.prepareCall(getConnection(), getSchema(), Routines.PYBRSSP_INS.routine());
				callableStatementBRSINS =  prepareCall(Routines.PYBRSSP_INS.routine());
				//fine LP 20240828 - PGNTCORE-24/PGNTWPB-3
			}
			callableStatementBRSINS.setString(1, wallet.getCuteCute().trim());
			callableStatementBRSINS.setString(2, wallet.getChiaveEnte().trim());
			callableStatementBRSINS.setString(3, wallet.getCodiceAnagraficaGenitore().trim());
			callableStatementBRSINS.setString(4, wallet.getCodiceFiscaleGenitore().trim());
			callableStatementBRSINS.setString(5, wallet.getTipoAnagrafica().trim());
			callableStatementBRSINS.setString(6, wallet.getDenominazioneGenitore().trim());
			callableStatementBRSINS.setString(7, wallet.getInidirizzoGenitore().trim());
			callableStatementBRSINS.setString(8, wallet.getDenominazioneComuneGenitore().trim());
			callableStatementBRSINS.setString(9, wallet.getCapComuneGenitore().trim());
			callableStatementBRSINS.setString(10, wallet.getProvinciaGenitore().trim());
			callableStatementBRSINS.setString(11, wallet.getOperatore());
			callableStatementBRSINS.setString(12, wallet.getAnagraficaDaBonificare()); //PG140300
			callableStatementBRSINS.registerOutParameter(13, Types.VARCHAR);
			callableStatementBRSINS.registerOutParameter(14, Types.VARCHAR);
			callableStatementBRSINS.execute();
			//			System.out.println("ins brs");
			//			System.out.println(wallet.getCuteCute().trim());
			//			System.out.println(wallet.getChiaveEnte().trim());
			//			System.out.println(wallet.getCodiceAnagraficaGenitore().trim());
			//			System.out.println(wallet.getCodiceFiscaleGenitore().trim());
			//			System.out.println(wallet.getTipoAnagrafica().trim());
			//			System.out.println(wallet.getDenominazioneGenitore().trim());
			//			System.out.println(wallet.getInidirizzoGenitore().trim());
			//			System.out.println(wallet.getDenominazioneComuneGenitore().trim());
			//			System.out.println(wallet.getCapComuneGenitore().trim());
			//			System.out.println(wallet.getProvinciaGenitore().trim());
			//			System.out.println(wallet.getOperatore());
			idWallet = callableStatementBRSINS.getString(13);
			errori = callableStatementBRSINS.getString(14);
			wallet.setAttribute("errori", errori);
			wallet.setIdWallet(idWallet);
		} catch (SQLException e) {
			throw new DaoException(e);
		} catch (IllegalArgumentException e) {
			throw new DaoException(e);
		} catch (HelperException e) {
			throw new DaoException(e);
		} finally {
			//DAOHelper.closeIgnoringException(callableStatementBRSINS);
			//DAOHelper.closeIgnoringException(connection);
		}
		return wallet;
	}

	//inizio LP 20240916 - PGNTCORE-24/PGNTWPB-3
	public Wallet select(Wallet wallet) throws DaoException {
		return selectTail(true, true, true, wallet);
	}

	public Wallet selectBatch(boolean bFlagUpdateAutocommit, boolean bCloseStat, boolean bCloseConn, Wallet wallet) throws DaoException {
		return selectTail(bFlagUpdateAutocommit, bCloseStat, bCloseConn, wallet);
	}
	
	private Wallet selectTail(boolean bFlagUpdateAutocommit, boolean bCloseStat, boolean bCloseConn, Wallet wallet) throws DaoException {
	//fine LP 20240916 - PGNTCORE-24/PGNTWPB-3
		CallableStatement callableStatement=null;
		ResultSet resultSet=null;
		Connection connection = null;
		CachedRowSet rowSet = null;
		try {
			//inizio LP 2024916 - PGNTCORE-24/PGNTWPB-3
			//connection = getConnection();
			//callableStatement = Helper.prepareCall(connection, getSchema(), Routines.PYBRSSP_SEL.routine());
			if(callableStatementBRSSEL == null) {
				callableStatementBRSSEL = prepareCall(bFlagUpdateAutocommit, Routines.PYBRSSP_SEL.routine());
				callableStatement = callableStatementBRSSEL;
			}
			//fine LP 20240916 - PGNTCORE-24/PGNTWPB-3
			callableStatement.setString(1, wallet.getIdWallet());
			callableStatement.setString(2, wallet.getCodiceFiscaleGenitore());
			callableStatement.setString(3, wallet.getCodiceSocieta());
			callableStatement.setString(4, wallet.getCuteCute());
			callableStatement.setString(5, wallet.getChiaveEnte());
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
				String IdWallet = rowSet.getString(1);
				String codiceSocieta = rowSet.getString(2);
				String cuteCute= rowSet.getString(3);
				String chiaveEnte= rowSet.getString(4);
				String codiceAnagraficaGenitore = rowSet.getString(5);
				String codiceFiscaleGenitore= rowSet.getString(6);
				String tipoAnagrafica = rowSet.getString(7);
				String numeroCell = rowSet.getString(8);
				String indirizzoEmail = rowSet.getString(9);
				Boolean flagAttivazione = rowSet.getString(10).equals("Y")?true:false;
				Date dataCaricamento= rowSet.getDate(11);
				Boolean flagPrimoAccesso = rowSet.getString(12).equals("N")?true:false;
				BigDecimal importo = rowSet.getBigDecimal(13);;
				String strImpo = importo.toString();
				String strImpoInt = "";
				String strImpoDec = "";
				if (strImpo.startsWith("-")){
					strImpo = strImpo.replace("-", "");
					strImpoInt = strImpo.substring(0, strImpo.indexOf("."));
					strImpoDec = strImpo.substring(strImpo.indexOf(".")+1);
				} else {
					strImpoInt = "0";
					strImpoDec = "00";
				}
				String denominazione = rowSet.getString(14);
				Calendar dataCaricamentoCal=Calendar.getInstance();
				dataCaricamentoCal.setTime(dataCaricamento);
				String denominazioneGenitore = rowSet.getString(15);
				String indirizzoGenitore = rowSet.getString(16);
				String denominazioneComuneGenitore = rowSet.getString(17);
				String capComuneGenitore = rowSet.getString(18);
				String provinciaGenitore = rowSet.getString(19);
				Boolean  FlagEsclusioneSMSCortesia = rowSet.getString(20).equals("Y")?true:false;
				Boolean  FlagEsclusioneSMSSollecito = rowSet.getString(21).equals("Y")?true:false;
				Boolean  FlagEsclusioneSollecitoCartaceo = rowSet.getString(22).equals("Y")?true:false;
				Boolean  FlagEsclusioneEvoluzioneIntimazione = rowSet.getString(23).equals("Y")?true:false;
				String sollecitiSmsCortesiaInviati = rowSet.getString(24);
				String sollecitiSmsSollecitiInviati = rowSet.getString(25);
				String emailSollecitoInviate = rowSet.getString(26);
				String sollecitiCartaceiInviati = rowSet.getString(27);
				String datasollecitoinviato = rowSet.getString(28);
				String ImportoOneriStampatiCaricati = rowSet.getString(29);
				String ImportoOneriStampatiPagati = rowSet.getString(30);
				String anagraficaBonificare = rowSet.getString(31);
				String flagwelcomekit = rowSet.getString(32);
				Boolean flagNoRivestizione = rowSet.getString(33).equals("Y")?true:false;
				String codiceRid = rowSet.getString(34);
				String ImportoOneriRendicontati = rowSet.getString(35);
				// DA FNIRE PG150270
				String flagestrattoconto = rowSet.getString(36);
				//PG180040 - inzio
				String flagPrimoAccessoMercati = rowSet.getString(38);	//Valori possibili N o Y
				BigDecimal importoOnDemand = rowSet.getBigDecimal(39);
				//PG180040 - fine
				wallet = new Wallet(IdWallet,codiceSocieta,cuteCute,chiaveEnte,codiceAnagraficaGenitore, 
						codiceFiscaleGenitore,tipoAnagrafica,numeroCell,indirizzoEmail,flagAttivazione, 
						dataCaricamentoCal,importo,flagPrimoAccesso, denominazioneGenitore, indirizzoGenitore,
						denominazioneComuneGenitore, capComuneGenitore, provinciaGenitore,FlagEsclusioneSMSCortesia,FlagEsclusioneSMSSollecito,FlagEsclusioneSollecitoCartaceo,FlagEsclusioneEvoluzioneIntimazione,"",anagraficaBonificare,flagNoRivestizione,codiceRid,importoOnDemand);

				wallet.setAttribute(WalletDAO.FLAG_PRIMOACCESSO_MERCATI, flagPrimoAccessoMercati);	//PG180040
				wallet.setAttribute(WalletDAO.FLAG_WELCOMEKIT, flagwelcomekit);
				wallet.setAttribute(WalletDAO.FLAG_ESTRATTOCONTO, flagestrattoconto);
				wallet.setAttribute("denom", denominazione); 
				wallet.setAttribute(WalletDAO.DENOMINAZIONECONTRIBUENTE, denominazione);

				wallet.setAttribute(WalletDAO.SMSCORTESIAINVIATI, sollecitiSmsCortesiaInviati);
				wallet.setAttribute(WalletDAO.SMSSOLLECITOINVIATI, sollecitiSmsSollecitiInviati);
				wallet.setAttribute(WalletDAO.EMAILSOLLECITOINVIATE, emailSollecitoInviate);
				wallet.setAttribute(WalletDAO.SOLLECATICARTACEI, sollecitiCartaceiInviati);

				wallet.setAttribute(WalletDAO.DATAULTIMOSOLLECITO, datasollecitoinviato);
				wallet.setAttribute(WalletDAO.IMPORTOONERISTAMPACARICATI, ImportoOneriStampatiCaricati);
				wallet.setAttribute(WalletDAO.IMPORTOONERISTAMPAPAGATI, ImportoOneriStampatiPagati);
				wallet.setAttribute(WalletDAO.IMPORTOONERIRENDICONTATI, ImportoOneriRendicontati);

				wallet.setAttribute(WalletDAO.IMPORTO_CARRELLO, strImpoInt);
				wallet.setAttribute(WalletDAO.IMPORTO_CARRELLO_DECIMALE, strImpoDec);

				wallet.setAttribute(WalletDAO.SELECT_XML, selectXml);
			}
		} catch (SQLException e) {
			throw new DaoException(e);
		} catch (IllegalArgumentException e) {
			throw new DaoException(e);
		} catch (HelperException e) {
			throw new DaoException(e);
		} finally {
			//inizio LP PG21XX04 Leak
			//if (closeConnection) {
				//DAOHelper.closeIgnoringException(callableStatement);
				//DAOHelper.closeIgnoringException(connection);
			if(rowSet != null) {
				try {
					rowSet.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(resultSet != null) {
				try {
					resultSet.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			//inizio LP 20240916 - PGNTCORE-24/PGNTWPB-3
			if(bCloseStat) {
			//fine LP 20240916 - PGNTCORE-24/PGNTWPB-3
				if(callableStatement != null) {
					try {
						callableStatement.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
				//inizio LP 20240916 - PGNTCORE-24/PGNTWPB-3
				callableStatement = null;
				callableStatementBRSSEL = null;
			}
			if(bCloseConn) {
				connection = getConnection();
			//fine LP 20240916 - PGNTCORE-24/PGNTWPB-3
				if(connection != null) {
					try {
						connection.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
				//fine LP PG21XX04 Leak
			//inizio LP 20240916 - PGNTCORE-24/PGNTWPB-3
				connection = null;
			}
			//fine LP 20240916 - PGNTCORE-24/PGNTWPB-3
		}
		return wallet;
	}

	//inizio LP 20240828 - PGNTCORE-24/PGNTWPB-3
	public Wallet selectBatch(Wallet wallet) throws DaoException {
		return selectBatchTail(true, wallet);
	}

	public Wallet selectBatchTail(boolean bFlagUpdateAutocommit, Wallet wallet) throws DaoException {
	//fine LP 20240828 - PGNTCORE-24/PGNTWPB-3
		ResultSet resultSet = null;
		try {
			if (callableStatementBRSBATCH == null) {
				//inizio LP 20240828 - PGNTCORE-24/PGNTWPB-3
				//callableStatementBRSBATCH  = Helper.prepareCall(getConnection(), getSchema(), Routines.PYBRSSP_SEL.routine());
				callableStatementBRSBATCH =  prepareCall(bFlagUpdateAutocommit, Routines.PYBRSSP_SEL.routine());
				//fine LP 20240828 - PGNTCORE-24/PGNTWPB-3
			}
			callableStatementBRSBATCH.setString(1, wallet.getIdWallet());
			callableStatementBRSBATCH.setString(2, wallet.getCodiceFiscaleGenitore());
			callableStatementBRSBATCH.setString(3, wallet.getCodiceSocieta());
			callableStatementBRSBATCH.setString(4, wallet.getCuteCute());
			callableStatementBRSBATCH.setString(5, wallet.getChiaveEnte());
			callableStatementBRSBATCH.execute();
			resultSet = callableStatementBRSBATCH.getResultSet();
			if (resultSet.next() ) {
				String IdWallet = resultSet.getString(1);
				String codiceSocieta = resultSet.getString(2);
				String cuteCute= resultSet.getString(3);
				String chiaveEnte= resultSet.getString(4);
				String codiceAnagraficaGenitore = resultSet.getString(5);
				String codiceFiscaleGenitore= resultSet.getString(6);
				String tipoAnagrafica = resultSet.getString(7);
				String numeroCell = resultSet.getString(8);
				String indirizzoEmail = resultSet.getString(9);
				Boolean flagAttivazione = resultSet.getString(10).equals("Y")?true:false;
				Date dataCaricamento= resultSet.getDate(11);
				Boolean flagPrimoAccesso = resultSet.getString(12).equals("N")?true:false;
				BigDecimal importo = resultSet.getBigDecimal(13);;
				String strImpo = importo.toString();
				String strImpoInt = "";
				String strImpoDec = "";
				if (strImpo.startsWith("-")){
					strImpo = strImpo.replace("-", "");
					strImpoInt = strImpo.substring(0, strImpo.indexOf("."));
					strImpoDec = strImpo.substring(strImpo.indexOf(".")+1);
				} else {
					strImpoInt = "0";
					strImpoDec = "00";
				}
				String denominazione = resultSet.getString(14);
				Calendar dataCaricamentoCal=Calendar.getInstance();
				dataCaricamentoCal.setTime(dataCaricamento);

				String denominazioneGenitore = resultSet.getString(15);
				String indirizzoGenitore = resultSet.getString(16);
				String denominazioneComuneGenitore = resultSet.getString(17);
				String capComuneGenitore = resultSet.getString(18);
				String provinciaGenitore = resultSet.getString(19);
				Boolean  FlagEsclusioneSMSCortesia = resultSet.getString(20).equals("Y")?true:false;
				Boolean  FlagEsclusioneSMSSollecito = resultSet.getString(21).equals("Y")?true:false;
				Boolean  FlagEsclusioneSollecitoCartaceo = resultSet.getString(22).equals("Y")?true:false;
				Boolean  FlagEsclusioneEvoluzioneIntimazione = resultSet.getString(23).equals("Y")?true:false;

				String sollecitiSmsCortesiaInviati = resultSet.getString(24);
				String sollecitiSmsSollecitiInviati = resultSet.getString(25);
				String emailSollecitoInviate = resultSet.getString(26);
				String sollecitiCartaceiInviati = resultSet.getString(27);
				String datasollecitoinviato = resultSet.getString(28);
				String ImportoOneriStampatiCaricati = resultSet.getString(29);
				String ImportoOneriStampatiPagati = resultSet.getString(30);
				String anagraficaBonificare = resultSet.getString(31);
				String flagwelcomekit = resultSet.getString(32);
				Boolean flagNoRivestizione = resultSet.getString(33).equals("Y")?true:false;
				String codiceRid = resultSet.getString(34);
				BigDecimal importoOnDemand = resultSet.getBigDecimal("BRS_IBRSIMPD");	//PG180040	//posizione 38 o 39
				wallet = new Wallet(IdWallet,codiceSocieta,cuteCute,chiaveEnte,codiceAnagraficaGenitore, 
						codiceFiscaleGenitore,tipoAnagrafica,numeroCell,indirizzoEmail,flagAttivazione, 
						dataCaricamentoCal,importo,flagPrimoAccesso, denominazioneGenitore, indirizzoGenitore,
						denominazioneComuneGenitore, capComuneGenitore, provinciaGenitore,FlagEsclusioneSMSCortesia,FlagEsclusioneSMSSollecito,FlagEsclusioneSollecitoCartaceo,FlagEsclusioneEvoluzioneIntimazione,"",anagraficaBonificare,flagNoRivestizione,codiceRid,importoOnDemand);
			}
		} catch (SQLException e) {
			throw new DaoException(e);
		} catch (IllegalArgumentException e) {
			throw new DaoException(e);
		} catch (HelperException e) {
			throw new DaoException(e);
		} finally {
			//inizio LP PG21XX04 Leak
			if(resultSet != null) {
				try {
					resultSet.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			//fine LP PG21XX04 Leak
		}
		return wallet;
	}

	//----------------------------------------------------------------------------------

	public Wallet select_anag(Wallet wallet) throws DaoException {
		CallableStatement callableStatement=null;
		ResultSet resultSet=null;
		Connection connection = null;
		CachedRowSet rowSet = null;
		try {
			connection = getConnection();
			//inizio LP 20240913 - PGNTCORE-24
			//callableStatement = Helper.prepareCall(connection, getSchema(), Routines.PYBRSSP_SEL_ANA.routine());
			callableStatement =  prepareCall(Routines.PYBRSSP_SEL_ANA.routine());
			//fine LP 20240913 - PGNTCORE-24
			callableStatement.setString(1, ""); // non passiamo il borsellino
			callableStatement.setString(2, wallet.getCodiceAnagraficaGenitore());
			callableStatement.setString(3, wallet.getCodiceSocieta());
			callableStatement.setString(4, wallet.getCuteCute());
			callableStatement.setString(5, wallet.getChiaveEnte()); //  "ANE0000009"
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
				String IdWallet = rowSet.getString(1);
				String codiceSocieta = rowSet.getString(2);
				String cuteCute= rowSet.getString(3);
				String chiaveEnte= rowSet.getString(4);
				String codiceAnagraficaGenitore = rowSet.getString(5);
				String codiceFiscaleGenitore= rowSet.getString(6);
				String tipoAnagrafica = rowSet.getString(7);
				String numeroCell = rowSet.getString(8);
				String indirizzoEmail = rowSet.getString(9);
				Boolean flagAttivazione = rowSet.getString(10).equals("Y")?true:false;
				Date dataCaricamento= rowSet.getDate(11);
				Boolean flagPrimoAccesso = rowSet.getString(12).equals("N")?true:false;
				BigDecimal importo = rowSet.getBigDecimal(13);;
				String denominazione = rowSet.getString(14);
				Calendar dataCaricamentoCal=Calendar.getInstance();
				dataCaricamentoCal.setTime(dataCaricamento);

				String denominazioneGenitore = rowSet.getString(15);
				String indirizzoGenitore = rowSet.getString(16);
				String denominazioneComuneGenitore = rowSet.getString(17);
				String capComuneGenitore = rowSet.getString(18);
				String provinciaGenitore = rowSet.getString(19);
				Boolean  FlagEsclusioneSMSCortesia = rowSet.getString(20).equals("Y")?true:false;
				Boolean  FlagEsclusioneSMSSollecito = rowSet.getString(21).equals("Y")?true:false;
				Boolean  FlagEsclusioneSollecitoCartaceo = rowSet.getString(22).equals("Y")?true:false;
				Boolean  FlagEsclusioneEvoluzioneIntimazione = rowSet.getString(23).equals("Y")?true:false;

				String sollecitiSmsCortesiaInviati = rowSet.getString(24);
				String sollecitiSmsSollecitiInviati = rowSet.getString(25);
				String emailSollecitoInviate = rowSet.getString(26);
				String sollecitiCartaceiInviati = rowSet.getString(27);
				String datasollecitoinviato = rowSet.getString(28);
				String ImportoOneriStampatiCaricati = rowSet.getString(29);
				String ImportoOneriStampatiPagati = rowSet.getString(30);

				wallet = new Wallet(IdWallet,codiceSocieta,cuteCute,chiaveEnte,codiceAnagraficaGenitore, 
						codiceFiscaleGenitore,tipoAnagrafica,numeroCell,indirizzoEmail,flagAttivazione, 
						dataCaricamentoCal,importo,flagPrimoAccesso, denominazioneGenitore, indirizzoGenitore,
						denominazioneComuneGenitore, capComuneGenitore, provinciaGenitore,FlagEsclusioneSMSCortesia,FlagEsclusioneSMSSollecito,FlagEsclusioneSollecitoCartaceo,FlagEsclusioneEvoluzioneIntimazione,"","",false,"", BigDecimal.ZERO);	//PG180040

				wallet.setAttribute("denom", denominazione); 
				wallet.setAttribute(WalletDAO.DENOMINAZIONECONTRIBUENTE, denominazione);

				wallet.setAttribute(WalletDAO.SMSCORTESIAINVIATI, sollecitiSmsCortesiaInviati);
				wallet.setAttribute(WalletDAO.SMSSOLLECITOINVIATI, sollecitiSmsSollecitiInviati);
				wallet.setAttribute(WalletDAO.EMAILSOLLECITOINVIATE, emailSollecitoInviate);
				wallet.setAttribute(WalletDAO.SOLLECATICARTACEI, sollecitiCartaceiInviati);

				wallet.setAttribute(WalletDAO.DATAULTIMOSOLLECITO, datasollecitoinviato);
				wallet.setAttribute(WalletDAO.IMPORTOONERISTAMPACARICATI, ImportoOneriStampatiCaricati);
				wallet.setAttribute(WalletDAO.IMPORTOONERISTAMPAPAGATI, ImportoOneriStampatiPagati);

				wallet.setAttribute(WalletDAO.SELECT_XML, selectXml);
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
			//DAOHelper.closeIgnoringException(connection);
			if(rowSet != null) {
				try {
					rowSet.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(resultSet != null) {
				try {
					resultSet.close();
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
		return wallet;
	}

	public String select_id(Wallet wallet) throws DaoException {
		CallableStatement callableStatement=null;
		Connection connection = null;
		String idWallet = "";
		try {
			connection = getConnection();
			//inizio LP PG21XX04 Leak
			//callableStatement = Helper.prepareCall(getConnection(), getSchema(), Routines.PYBRSSP_SEL_ID.routine());
			//inizio LP 20240913 - PGNTCORE-24
			//callableStatement = Helper.prepareCall(connection, getSchema(), Routines.PYBRSSP_SEL_ID.routine());
			callableStatement =  prepareCall(Routines.PYBRSSP_SEL_ID.routine());
			//fine LP 20240913 - PGNTCORE-24
			//inizio LP PG21XX04 Leak
			callableStatement.setString(1, wallet.getCodiceAnagraficaGenitore());
			callableStatement.setString(2, wallet.getCodiceSocieta());
			callableStatement.setString(3, wallet.getCuteCute());
			callableStatement.setString(4, wallet.getChiaveEnte()); //  "ANE0000009"
			callableStatement.registerOutParameter(5,Types.VARCHAR);
			callableStatement.execute();
			idWallet = callableStatement.getString(5);
		} catch (SQLException e) {
			throw new DaoException(e);
		} catch (IllegalArgumentException e) {
			throw new DaoException(e);
		} catch (HelperException e) {
			throw new DaoException(e);
		} finally {
			//inizio LP PG21XX04 Leak
			//DAOHelper.closeIgnoringException(callableStatement);
			////DAOHelper.closeIgnoringException(connection);
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
		return idWallet;
	}		

	//----------------------------------------------------------------------------------

	public void update(Wallet wallet) throws DaoException {
		update(wallet,true);
	}

	//inizio LP PG21XX04 Leak
	//Nota. il metodo è usato sia dai batch che dal web
	//      per il batch vale quanto scritto in testa a
	//      questo file, per il web essendo chiamato con
	//      closeConnection == true si esegue la chiusura
	//      sia della connection che del callableStatement
	//fine LP PG21XX04 Leak
	//inizio LP 20240828 - PGNTCORE-24/PAGONET-604/PGNTWPB-3
	public void update(Wallet wallet, boolean closeConnection) throws DaoException {
		updateTail(true, wallet, closeConnection);
	}

	public void updateTail(boolean bFlagUpdateAutocommit, Wallet wallet, boolean closeConnection) throws DaoException {
	//fine LP 20240828 - PGNTCORE-24/PAGONET-604/PGNTWPB-3
		//CallableStatement callableStatement=null; //LP 20240828 - PGNTCORE-24/PAGONET-604/PGNTWPB-3
		Connection connection = null;
		try { 
			//connection = getConnection();
			if (callableStatementBRSUP == null) {
				//inizio LP PGNTCORE-24
				//callableStatementBRSUP = Helper.prepareCall(connection, getSchema(), Routines.PYBRSSP_UPD.routine());
				//inizio LP 20240828 - PGNTCORE-24/PAGONET-604/PGNTWPB-3
				connection = getConnection();
				callableStatementBRSUP = prepareCall(bFlagUpdateAutocommit, Routines.PYBRSSP_UPD.routine());
				//fine LP 20240828 - PGNTCORE-24/PAGONET-604/PGNTWPB-3
				//fine LP PGNTCORE-24
			}
			callableStatementBRSUP.setString(1, wallet.getIdWallet());
			callableStatementBRSUP.setString(2, wallet.getNumeroCell());
			callableStatementBRSUP.setString(3, wallet.getIndirizzoEmail());
			callableStatementBRSUP.setString(4, wallet.getCodiceFiscaleGenitore());
			callableStatementBRSUP.setString(5, wallet.getCodiceRid()==null?"":wallet.getCodiceRid());
			//PG180040 - inizio
			if (wallet.getImportoOnDemand() == null) 
				callableStatementBRSUP.setNull(6, Types.DECIMAL);
			else 
				callableStatementBRSUP.setBigDecimal(6, wallet.getImportoOnDemand());
			//PG180040 - fine
			callableStatementBRSUP.execute();

		} catch (SQLException e) {
			throw new DaoException(e);
		} catch (IllegalArgumentException e) {
			throw new DaoException(e);
		} catch (HelperException e) {
			throw new DaoException(e);
		} finally {
			if (closeConnection)
			//inizio LP PG21XX04 Leak
				//DAOHelper.closeIgnoringException(connection);
			{
				if(callableStatementBRSUP != null) {
					try {
						callableStatementBRSUP.close();
						callableStatementBRSUP = null;
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
				//fine LP PG21XX04 Leak
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
	}

	public Integer updateAnagrafica(Wallet wallet) throws DaoException {
		CallableStatement callableStatement=null;
		Connection connection = null;
		int ret=0;
		try { 
			connection = getConnection();
			//			1	IN I_BRS_CSOCCSOC VARCHAR(5),
			//			2	IN I_BRS_CUTECUTE VARCHAR(5),
			//			3	IN I_BRS_KANEKENT CHAR(10),
			//			4	IN I_BRS_KBRSKBRS VARCHAR(18),
			//			5	IN I_BRS_DBRSINDI VARCHAR(50),
			//			6	IN I_BRS_DBRSCOMU VARCHAR(50),
			//			7	IN I_BRS_CBRSCCAP VARCHAR(5),
			//			8	IN I_BRS_CBRSCPRV VARCHAR(2),
			//			9	IN I_BRS_CBRSCOPE VARCHAR(50),
			//inizio LP 20240913 - PGNTCORE-24
			//callableStatement = Helper.prepareCall(connection, getSchema(), Routines.PYBRSSP_UPD_ANA.routine());
			callableStatement =  prepareCall(Routines.PYBRSSP_UPD_ANA.routine());
			//fine LP 20240913 - PGNTCORE-24
			callableStatement.setString(1, wallet.getCodiceSocieta());
			callableStatement.setString(2, wallet.getCuteCute());
			callableStatement.setString(3, wallet.getChiaveEnte());
			callableStatement.setString(4, wallet.getIdWallet());
			callableStatement.setString(5, wallet.getInidirizzoGenitore());
			callableStatement.setString(6, wallet.getDenominazioneComuneGenitore());
			callableStatement.setString(7, wallet.getCapComuneGenitore());
			callableStatement.setString(8, wallet.getProvinciaGenitore());
			callableStatement.setString(9, wallet.getOperatore());
			callableStatement.setString(10, wallet.getDenominazioneGenitore());
			callableStatement.setString(11, wallet.getIndirizzoEmail());
			callableStatement.setString(12, wallet.getNumeroCell());
			callableStatement.setString(13, wallet.isFlagNoRivestizione()?"Y":"N");

			callableStatement.registerOutParameter(14, Types.INTEGER);						
			callableStatement.execute();
			ret = callableStatement.getInt(14);
			callableStatement.execute();


		} catch (SQLException e) {
			throw new DaoException(e);
		} catch (IllegalArgumentException e) {
			throw new DaoException(e);
		} catch (HelperException e) {
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

	public StringBuffer walletListCsv(Wallet wallet,String tipoServizio,String tipoSollecito,String flagrendicontato,String presenzaOneri, int rowsPerPage, int pageNumber, String OrderBy)	throws DaoException {
		CallableStatement callableStatement=null;
		CallableStatement callableStatementsrv = null;
		Connection connection = null;
		ResultSet data = null;
		//inizio LP PG21XX04 Leak
		ResultSet dataDett = null;
		//fine LP PG21XX04 Leak
		PageInfo pageInfo = null;
		StringBuffer file = new StringBuffer();
		try {
			connection = getConnection();
			//inizio LP 20240913 - PGNTCORE-24
			//callableStatement = Helper.prepareCall(connection, getSchema(), Routines.PYBRSSP_LST.routine());
			callableStatement =  prepareCall(Routines.PYBRSSP_LST.routine());
			//fine LP 20240913 - PGNTCORE-24
			callableStatement.setInt(1, pageNumber);                          /* rows per page */
			callableStatement.setInt(2, rowsPerPage);                        /* page number*/
			callableStatement.setString(3,OrderBy);
			callableStatement.setString(4,wallet.getCodiceSocieta());
			callableStatement.setString(5,wallet.getCuteCute());
			callableStatement.setString(6,wallet.getChiaveEnte());
			callableStatement.setString(7,wallet.getIdWallet());
			callableStatement.setString(8,wallet.getCodiceAnagraficaGenitore());
			callableStatement.setString(9,wallet.getCodiceFiscaleGenitore());
			callableStatement.setString(10, (String)wallet.getAttribute(WalletDAO.PERIDOCARICO_DA));
			callableStatement.setString(11, (String)wallet.getAttribute(WalletDAO.PERIDOCARICO_A));
			callableStatement.setString(12,tipoSollecito);
			callableStatement.setString(13,presenzaOneri);
			callableStatement.setString(14,tipoServizio);
			callableStatement.setString(15,flagrendicontato);

			if ( wallet.isFlagEsclusioneSMSCortesia() ==null ) {
				callableStatement.setString(16, "");
			} else {
				callableStatement.setString(16, wallet.isFlagEsclusioneSMSCortesia()?"Y":"N");
			}

			if ( wallet.isFlagEsclusioneSMSSollecito() ==null ) {
				callableStatement.setString(17, "");
			} else {
				callableStatement.setString(17, wallet.isFlagEsclusioneSMSSollecito()?"Y":"N");
			}

			if ( wallet.isFlagEsclusioneSollecitoCartaceo() ==null ) {
				callableStatement.setString(18, "");
			} else {
				callableStatement.setString(18, wallet.isFlagEsclusioneSollecitoCartaceo()?"Y":"N");
			}
			if ( wallet.isFlagEsclusioneEvoluzioneIntimazione() ==null ) {
				callableStatement.setString(19, "");
			} else {
				callableStatement.setString(19, wallet.isFlagEsclusioneEvoluzioneIntimazione()?"Y":"N");
			}

			/* we select */
			callableStatement.registerOutParameter(20, Types.VARCHAR);
			/* we register row start */
			callableStatement.registerOutParameter(21, Types.INTEGER);
			/* we register row end */
			callableStatement.registerOutParameter(22, Types.INTEGER);
			/* we register total rows */
			callableStatement.registerOutParameter(23, Types.INTEGER);
			/* we register total pages */
			callableStatement.registerOutParameter(24, Types.SMALLINT);
			String walletCsv = ""; 

			/* we execute procedure */
			String row = "";  
			if(callableStatement.execute())	{

				data = callableStatement.getResultSet();
				if ( data.next() ) { 
					row += "Tipo;"; 
					row += "Identificativo Borsellino;";
					row += "Codice Fiscale;";
					row += "SmS Cortesia;";
					row += "SmS Sollecito;";
					row += "Email Sollecito;";
					row += "Sollecito Cartaceo;";
					row += "Importo Oneri;";
					row += "Importo Oneri Pagati;";
					row += "Importo Oneri Rendicontati;";
					row += "Carico;";
					row += "Pagato;";
					row += "Residuo;";
					row += "Rendicontato;";	//Tk 2017033088000055
					row += "Flag Esclusione SMS Cortesia;";
					row += "Flag Esclusione SMS Sollecito;";
					row += "Flag Esclusione Sollecito Cartaceo;";
					row += "Flag Esclusione Evoluzione Intimazione;";
					row += "Servizio;";
					row += "Carico per Servizio;";
					row += "Pagato per Servizio;";
					row += "Residuo per Servizio;";
					row += "Rendicontato per Servizio;";

					row += "\r";
					file.append(row);
					row = "";
					int i=0;
					do {
						i++;

						String soc = data.getString(1);
						String ute = data.getString(2);
						String ente = data.getString(3);
						String idWallet = data.getString(4);
						String cfis = data.getString(5);
						String smsCortesia = String.valueOf(data.getString(6));
						String smsSollecito = String.valueOf(data.getString(7));
						String emailSollecito = String.valueOf(data.getString(8));
						String sollecitoCartaceo = String.valueOf(data.getString(9));
						String impoOneri = String.valueOf(data.getBigDecimal(10)).replace(".", ",");
						String impoOneriPagati = String.valueOf(data.getBigDecimal(11)).replace(".", ",");
						String carico = String.valueOf(data.getBigDecimal(12)).replace(".", ",");
						String pagato = String.valueOf(data.getBigDecimal(13)).replace(".", ",");
						String residuo = String.valueOf(data.getBigDecimal(14)).replace(".", ",");
						String rendicontato = String.valueOf(data.getBigDecimal(15)).replace(".", ",");	//Tk 2017033088000055
						String impoOneriRendicontati = String.valueOf(data.getBigDecimal(20)).replace(".", ",");
						row = "TRA;";
						row += idWallet + ";";
						row += cfis + ";";
						row += smsCortesia + ";";
						row += smsSollecito + ";";
						row += emailSollecito + ";";
						row += sollecitoCartaceo + ";";
						row += impoOneri + ";";
						row += impoOneriPagati  + ";";
						row += impoOneriRendicontati  + ";";
						row += carico  + ";";
						row += pagato  + ";";
						row += residuo + ";";
						row += rendicontato + ";";	//Tk 2017033088000055

						row += data.getString(16)  + ";";
						row += data.getString(17)  + ";";
						row += data.getString(18)  + ";";
						row += data.getString(19)  + ";";

						row += "\r";
						file.append(row);
						row = "";

						// inizio produzione dettaglio
						//inizio LP PG21XX04 Leak
						//ResultSet dataDett = null;
						dataDett = null;
						//fine LP PG21XX04 Leak
						if (callableStatementsrv == null) {
							//inizio LP 20240913 - PGNTCORE-24
							//callableStatementsrv = Helper.prepareCall(connection, getSchema(), Routines.PYBRSSP_LST_SRV_MG.routine());
							callableStatement =  prepareCall(Routines.PYBRSSP_LST_SRV_MG.routine());
							//fine LP 20240913 - PGNTCORE-24
						}
						callableStatementsrv.setString(1,soc);
						callableStatementsrv.setString(2,ute);
						callableStatementsrv.setString(3,ente);
						callableStatementsrv.setString(4,idWallet);
						//Tk 2017033088000055 20170403 - inizio
						callableStatementsrv.setString(5,tipoServizio);
						callableStatementsrv.setString(6,(String)wallet.getAttribute(WalletDAO.PERIDOCARICO_DA));
						callableStatementsrv.setString(7,(String)wallet.getAttribute(WalletDAO.PERIDOCARICO_A));
						//Tk 2017033088000055 20170403 - fine
						if(callableStatementsrv.execute())	{
							dataDett = callableStatementsrv.getResultSet();
							if ( dataDett.next() ) {
								do {
									String servizioDet = dataDett.getString(8);
									String caricoDet = String.valueOf(dataDett.getBigDecimal(3)).replace(".", ",");
									String pagatoDet = String.valueOf(dataDett.getBigDecimal(4)).replace(".", ",");
									String residuoDet = String.valueOf(dataDett.getBigDecimal(5)).replace(".", ",");
									String rendicontatoDet = String.valueOf(dataDett.getBigDecimal(6)).replace(".", ",");
									row = "DETT;";

									row += idWallet + ";";
									row += cfis + ";";
									row +=  ";";
									row += ";";
									row += ";";
									row += ";";
									row += ";";
									row += ";";
									row += ";";
									row += ";";
									row += ";";
									row += ";";
									row += ";";		//Tk 2017033088000055

									row += ";";
									row += ";";
									row += ";";
									row += ";";


									row += servizioDet + ";";
									row += caricoDet + ";";
									row += pagatoDet + ";";
									row += residuoDet + ";";
									row += rendicontatoDet + ";";
									row += "\r";
									file.append(row);
									row = "";
								} while(dataDett.next());
								dataDett.close();
							}
						}
						callableStatementsrv.close();
						// fine produzione dettaglio
					} while(data.next());
					data.close();
					callableStatement.close();
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (HelperException e) {
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
			if(dataDett != null) {
				try {
					dataDett.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(callableStatementsrv != null) {
				try {
					callableStatementsrv.close();
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
	
	//Tk 2017033088000055 20170406 - inizio
	public StringBuffer  walletListCsv2(Wallet wallet,String tipoServizio,String tipoSollecito,String flagrendicontato,String presenzaOneri, int rowsPerPage, int pageNumber, String OrderBy)	throws DaoException {
		CallableStatement callableStatement=null;
		Connection connection = null;
		ResultSet data = null;
		StringBuffer file = new StringBuffer();
		try {
			connection = getConnection();
			//inizio LP 20240913 - PGNTCORE-24
			//callableStatement = Helper.prepareCall(connection, getSchema(), Routines.PYBRSSP_LST_DWN_MG.routine());
			callableStatement = prepareCall(Routines.PYBRSSP_LST_DWN_MG.routine());
			//fine LP 20240913 - PGNTCORE-24
			callableStatement.setString(1,OrderBy);
			callableStatement.setString(2,wallet.getCodiceSocieta());
			callableStatement.setString(3,wallet.getCuteCute());
			callableStatement.setString(4,wallet.getChiaveEnte());
			callableStatement.setString(5,wallet.getIdWallet());
			callableStatement.setString(6,wallet.getCodiceAnagraficaGenitore());
			callableStatement.setString(7,wallet.getCodiceFiscaleGenitore());
			callableStatement.setString(8, (String)wallet.getAttribute(WalletDAO.PERIDOCARICO_DA));
			callableStatement.setString(9, (String)wallet.getAttribute(WalletDAO.PERIDOCARICO_A));
			callableStatement.setString(10,tipoSollecito);
			callableStatement.setString(11,presenzaOneri);
			callableStatement.setString(12,tipoServizio);
			callableStatement.setString(13,flagrendicontato);

			if ( wallet.isFlagEsclusioneSMSCortesia() ==null ) {
				callableStatement.setString(14, "");
			} else {
				callableStatement.setString(14, wallet.isFlagEsclusioneSMSCortesia()?"Y":"N");
			}

			if ( wallet.isFlagEsclusioneSMSSollecito() ==null ) {
				callableStatement.setString(15, "");
			} else {
				callableStatement.setString(15, wallet.isFlagEsclusioneSMSSollecito()?"Y":"N");
			}

			if ( wallet.isFlagEsclusioneSollecitoCartaceo() ==null ) {
				callableStatement.setString(16, "");
			} else {
				callableStatement.setString(16, wallet.isFlagEsclusioneSollecitoCartaceo()?"Y":"N");
			}
			if ( wallet.isFlagEsclusioneEvoluzioneIntimazione() ==null ) {
				callableStatement.setString(17, "");
			} else {
				callableStatement.setString(17, wallet.isFlagEsclusioneEvoluzioneIntimazione()?"Y":"N");
			}

			String walletCsv = ""; 

			/* we execute procedure */
			String row = "";  
			String idWalletOld = "";
			
			if(callableStatement.execute())	{

				data = callableStatement.getResultSet();
				if ( data.next() ) { 
					row += "Tipo;"; 
					row += "Identificativo Borsellino;";
					row += "Codice Fiscale;";
					row += "SmS Cortesia;";
					row += "SmS Sollecito;";
					row += "Email Sollecito;";
					row += "Sollecito Cartaceo;";
					row += "Importo Oneri;";
					row += "Importo Oneri Pagati;";
					row += "Importo Oneri Rendicontati;";
					row += "Carico;";
					row += "Pagato;";
					row += "Residuo;";
					row += "Rendicontato;";	//Tk 2017033088000055
					row += "Flag Esclusione SMS Cortesia;";
					row += "Flag Esclusione SMS Sollecito;";
					row += "Flag Esclusione Sollecito Cartaceo;";
					row += "Flag Esclusione Evoluzione Intimazione;";
					row += "Servizio;";
					row += "Carico per Servizio;";
					row += "Pagato per Servizio;";
					row += "Residuo per Servizio;";
					row += "Rendicontato per Servizio;";

					row += "\r";
					file.append(row);
					row = "";
					int i=0;
					do {
						i++;
						
						String soc = data.getString(1);
						String ute = data.getString(2);
						String ente = data.getString(3);
						String idWallet = data.getString(4);
						String cfis = data.getString(5);
						String smsCortesia = String.valueOf(data.getString(6));
						String smsSollecito = String.valueOf(data.getString(7));
						String emailSollecito = String.valueOf(data.getString(8));
						String sollecitoCartaceo = String.valueOf(data.getString(9));
						String impoOneri = String.valueOf(data.getBigDecimal(10)).replace(".", ",");
						String impoOneriPagati = String.valueOf(data.getBigDecimal(11)).replace(".", ",");
						String carico = String.valueOf(data.getBigDecimal(12)).replace(".", ",");
						String pagato = String.valueOf(data.getBigDecimal(13)).replace(".", ",");
						String residuo = String.valueOf(data.getBigDecimal(14)).replace(".", ",");
						String rendicontato = String.valueOf(data.getBigDecimal(15)).replace(".", ",");	//Tk 2017033088000055
						String impoOneriRendicontati = String.valueOf(data.getBigDecimal(20)).replace(".", ",");
						if (!idWallet.equals(idWalletOld)) {	//Trattasi di prima riga di dettaglio del borsellino
							row = "TRA;";
							row += idWallet + ";";
							row += cfis + ";";
							row += smsCortesia + ";";
							row += smsSollecito + ";";
							row += emailSollecito + ";";
							row += sollecitoCartaceo + ";";
							row += impoOneri + ";";
							row += impoOneriPagati  + ";";
							row += impoOneriRendicontati  + ";";
							row += carico  + ";";
							row += pagato  + ";";
							row += residuo + ";";
							row += rendicontato + ";";	//Tk 2017033088000055
	
							row += data.getString(16)  + ";";
							row += data.getString(17)  + ";";
							row += data.getString(18)  + ";";
							row += data.getString(19)  + ";";
	
							row += "\r";
							file.append(row);
							row = "";
							idWalletOld = idWallet;
						}
						
						// inizio produzione dettaglio
						String servizioDet = data.getString(21);
						String caricoDet = String.valueOf(data.getBigDecimal(22)).replace(".", ",");
						String pagatoDet = String.valueOf(data.getBigDecimal(23)).replace(".", ",");
						String residuoDet = String.valueOf(data.getBigDecimal(24)).replace(".", ",");
						String rendicontatoDet = String.valueOf(data.getBigDecimal(25)).replace(".", ",");
						
						row = "DETT;";

						row += idWallet + ";";
						row += cfis + ";";
						row +=  ";";
						row += ";";
						row += ";";
						row += ";";
						row += ";";
						row += ";";
						row += ";";
						row += ";";
						row += ";";
						row += ";";
						row += ";";	//Tk 2017033088000055

						row += ";";
						row += ";";
						row += ";";
						row += ";";


						row += servizioDet + ";";
						row += caricoDet + ";";
						row += pagatoDet + ";";
						row += residuoDet + ";";
						row += rendicontatoDet + ";";
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
		} catch (HelperException e) {
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
	//Tk 2017033088000055 20170406 - fine
	
	public WalletPageList  walletList(Wallet wallet,String tipoServizio,String tipoSollecito,String flagrendicontato,String presenzaOneri, int rowsPerPage, int pageNumber, String OrderBy)	throws DaoException {
		CallableStatement callableStatement=null;

		Connection connection = null;
		ResultSet data = null;
		ResultSet dataRiep = null;
		PageInfo pageInfo = null; 
		WalletPageList walletPageList = null;
		String[] brsLst  = new String[2];
		try {
			connection = getConnection();
			//inizio LP 20240913 - PGNTCORE-24
			//callableStatement = Helper.prepareCall(connection, getSchema(), Routines.PYBRSSP_LST.routine());
			callableStatement = prepareCall(Routines.PYBRSSP_LST.routine());
			//fine LP 20240913 - PGNTCORE-24
			callableStatement.setInt(1, pageNumber);                          /* rows per page */
			callableStatement.setInt(2, rowsPerPage);                        /* page number*/
			callableStatement.setString(3,OrderBy);
			callableStatement.setString(4,wallet.getCodiceSocieta());
			callableStatement.setString(5,wallet.getCuteCute());
			callableStatement.setString(6,wallet.getChiaveEnte());
			callableStatement.setString(7,wallet.getIdWallet());
			callableStatement.setString(8,wallet.getCodiceAnagraficaGenitore());
			callableStatement.setString(9,wallet.getCodiceFiscaleGenitore());
			callableStatement.setString(10, (String)wallet.getAttribute(WalletDAO.PERIDOCARICO_DA));
			callableStatement.setString(11, (String)wallet.getAttribute(WalletDAO.PERIDOCARICO_A));
			callableStatement.setString(12,tipoSollecito);
			callableStatement.setString(13,presenzaOneri);
			callableStatement.setString(14,tipoServizio);
			callableStatement.setString(15,flagrendicontato);

			if ( wallet.isFlagEsclusioneSMSCortesia() ==null ) {
				callableStatement.setString(16, "");
			} else {
				callableStatement.setString(16, wallet.isFlagEsclusioneSMSCortesia()?"Y":"N");
			}

			if ( wallet.isFlagEsclusioneSMSSollecito() ==null ) {
				callableStatement.setString(17, "");
			} else {
				callableStatement.setString(17, wallet.isFlagEsclusioneSMSSollecito()?"Y":"N");
			}

			if ( wallet.isFlagEsclusioneSollecitoCartaceo() ==null ) {
				callableStatement.setString(18, "");
			} else {
				callableStatement.setString(18, wallet.isFlagEsclusioneSollecitoCartaceo()?"Y":"N");
			}
			if ( wallet.isFlagEsclusioneEvoluzioneIntimazione() ==null ) {
				callableStatement.setString(19, "");
			} else {
				callableStatement.setString(19, wallet.isFlagEsclusioneEvoluzioneIntimazione()?"Y":"N");
			}

			/* we select */
			callableStatement.registerOutParameter(20, Types.VARCHAR);
			/* we register row start */
			callableStatement.registerOutParameter(21, Types.INTEGER);
			/* we register row end */
			callableStatement.registerOutParameter(22, Types.INTEGER);
			/* we register total rows */
			callableStatement.registerOutParameter(23, Types.INTEGER);
			/* we register total pages */
			callableStatement.registerOutParameter(24, Types.SMALLINT);

			/* we execute procedure */

			if(callableStatement.execute())	{
				pageInfo = new PageInfo();
				pageInfo.setPageNumber(pageNumber);
				pageInfo.setRowsPerPage(rowsPerPage);
				pageInfo.setFirstRow(callableStatement.getInt(21));
				pageInfo.setLastRow(callableStatement.getInt(22));
				pageInfo.setNumRows(callableStatement.getInt(23));
				pageInfo.setNumPages(callableStatement.getInt(24));

				data = callableStatement.getResultSet();
				loadWebRowSet(data);

				brsLst[0] = getWebRowSetXml();
				//				walletPageList = new WalletPageList(pageInfo, "00","",brsLst[0]);
				//				return walletPageList;
			}

			if(callableStatement.getMoreResults()) {
				dataRiep = callableStatement.getResultSet();

				loadWebRowSet(dataRiep);
				brsLst[1] = getWebRowSetXml();

			}
			walletPageList = new WalletPageList(pageInfo, "00","",brsLst);
			return walletPageList;

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
			if(dataRiep != null) {
				try {
					dataRiep.close();
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
		return walletPageList;
	}

	public String walletRiepilogo(Wallet wallet, String tipoServizio,
			String tipoSollecito, String flagrendicontato, String presenzaOneri)
	throws DaoException {

		CallableStatement callableStatement=null;
		ResultSet resultSet=null;
		Connection connection = null;
		String selectXml = "";
		try {
			connection = getConnection();
			//inizio LP 20240913 - PGNTCORE-24
			//callableStatement = Helper.prepareCall(connection, getSchema(), Routines.PYBRSSP_LST_RIEP.routine());
			callableStatement =  prepareCall(Routines.PYBRSSP_LST_RIEP.routine());
			//fine LP 20240913 - PGNTCORE-24
			callableStatement.setString(1,wallet.getCodiceSocieta());
			callableStatement.setString(2,wallet.getCuteCute());
			callableStatement.setString(3,wallet.getChiaveEnte());
			callableStatement.setString(4,wallet.getIdWallet());
			callableStatement.setString(5,wallet.getCodiceAnagraficaGenitore());
			callableStatement.setString(6,wallet.getCodiceFiscaleGenitore());
			callableStatement.setString(7, (String)wallet.getAttribute(WalletDAO.PERIDOCARICO_DA));
			callableStatement.setString(8, (String)wallet.getAttribute(WalletDAO.PERIDOCARICO_A));
			callableStatement.setString(9,tipoSollecito);
			callableStatement.setString(10,presenzaOneri);
			callableStatement.setString(11,tipoServizio);
			callableStatement.setString(12,flagrendicontato);
			if ( wallet.isFlagEsclusioneSMSCortesia() ==null ) {
				callableStatement.setString(13, "");
			} else {
				callableStatement.setString(13, wallet.isFlagEsclusioneSMSCortesia()?"Y":"N");
			}

			if ( wallet.isFlagEsclusioneSMSSollecito() ==null ) {
				callableStatement.setString(14, "");
			} else {
				callableStatement.setString(14, wallet.isFlagEsclusioneSMSSollecito()?"Y":"N");
			}

			if ( wallet.isFlagEsclusioneSollecitoCartaceo() ==null ) {
				callableStatement.setString(15, "");
			} else {
				callableStatement.setString(15, wallet.isFlagEsclusioneSollecitoCartaceo()?"Y":"N");
			}
			if ( wallet.isFlagEsclusioneEvoluzioneIntimazione() ==null ) {
				callableStatement.setString(16, "");
			} else {
				callableStatement.setString(16, wallet.isFlagEsclusioneEvoluzioneIntimazione()?"Y":"N");
			}
			callableStatement.execute();

			resultSet=callableStatement.getResultSet();
			loadWebRowSet(resultSet);
			selectXml = getWebRowSetXml();

		} catch (SQLException e) {
			throw new DaoException(e);
		} catch (IllegalArgumentException e) {
			throw new DaoException(e);
		} catch (HelperException e) {
			throw new DaoException(e);
		} finally {
			//inizio LP PG21XX04 Leak
			//DAOHelper.closeIgnoringException(connection);
			if(resultSet != null) {
				try {
					resultSet.close();
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
		return selectXml;
	}

	public StringBuffer walletServListCsv(Wallet wallet,String tipoServizio,String tributo,String flagrendicontato, int rowsPerPage, int pageNumber, String OrderBy)	throws DaoException {
		CallableStatement callableStatement=null;
		Connection connection = null;
		ResultSet data = null;
		PageInfo pageInfo = null;
		StringBuffer file = new StringBuffer();
		try {
			connection = getConnection();
			// IMPLEMENTARE la SP !!!!!!!!!!!!!!!!!!!
			// GC_2013_06_26
			//inizio LP 20240913 - PGNTCORE-24
			//callableStatement = Helper.prepareCall(connection, getSchema(), Routines.PYBRSSP_SRV_LST.routine());
			callableStatement =  prepareCall(Routines.PYBRSSP_SRV_LST.routine());
			//fine LP 20240913 - PGNTCORE-24
			callableStatement.setInt(1, pageNumber);                          /* rows per page */
			callableStatement.setInt(2, rowsPerPage);                        /* page number*/
			callableStatement.setString(3,OrderBy);
			callableStatement.setString(4,wallet.getCodiceSocieta());
			callableStatement.setString(5,wallet.getCuteCute());
			callableStatement.setString(6,wallet.getChiaveEnte());
			callableStatement.setString(7,wallet.getCodiceFiscaleGenitore());
			callableStatement.setString(8,wallet.getIdWallet());

			callableStatement.setString(9,tipoServizio);
			callableStatement.setString(10,tributo);
			callableStatement.setString(11, (String)wallet.getAttribute(WalletDAO.PERIDOCARICO_DA));
			callableStatement.setString(12, (String)wallet.getAttribute(WalletDAO.PERIDOCARICO_A));
			callableStatement.setString(13, flagrendicontato);
			/* we register row start */
			callableStatement.registerOutParameter(14, Types.INTEGER);
			/* we register row end */
			callableStatement.registerOutParameter(15, Types.INTEGER);
			/* we register total rows */
			callableStatement.registerOutParameter(16, Types.INTEGER);
			/* we register total pages */
			callableStatement.registerOutParameter(17, Types.SMALLINT);
			String walletCsv = ""; 

			/* we execute procedure */
			String row = "";
			if(callableStatement.execute())	{

				data = callableStatement.getResultSet();
				if ( data.next() ) {
					row += "Servizio;";
					row += "Codice Borsellino;";
					row += "Codice Fiscale;";
					row += "Anno/Mese Carico;";
					row += "Codice Tributo;";
					row += "Carico;";
					row += "Pagato;";
					row += "Residuo;";
					row += "Rendicontato;";
					row += "\r";
					file.append(row);
					row = "";
					do {

						String servizio = data.getString(1);
						String ifWallet = data.getString(2);
						String cfis = data.getString(3);
						String annoMeseCar = String.valueOf(data.getString(4));
						String trib = String.valueOf(data.getString(5));

						String carico = String.valueOf(data.getBigDecimal(6)).replace(".", ",");
						String pagato = String.valueOf(data.getBigDecimal(7)).replace(".", ",");
						String residuo = String.valueOf(data.getBigDecimal(8)).replace(".", ",");
						String rend = String.valueOf(data.getBigDecimal(9)).replace(".", ",");
						row += servizio + ";";
						row += ifWallet + ";";
						row += cfis + ";";
						row += annoMeseCar + ";";
						row += trib + ";";

						row += carico  + ";";
						row += pagato  + ";";
						row += residuo + ";";
						row += rend + ";";
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
		} catch (HelperException e) {
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



	public StringBuffer walletRicaricheListCsv(Wallet wallet, int rowsPerPage, int pageNumber, String OrderBy)	throws DaoException {
		CallableStatement callableStatement=null;
		Connection connection = null;
		ResultSet data = null;
		PageInfo pageInfo = null;
		StringBuffer file = new StringBuffer();
		try {
			connection = getConnection();
			// IMPLEMENTARE la SP !!!!!!!!!!!!!!!!!!!
			// GC_2013_06_26
			//inizio LP 20240913 - PGNTCORE-24
			//callableStatement = Helper.prepareCall(connection, getSchema(), Routines.PYBRSSP_PGB_ADB_LST.routine());
			callableStatement =  prepareCall(Routines.PYBRSSP_PGB_ADB_LST.routine());
			//fine LP 20240913 - PGNTCORE-24
			callableStatement.setInt(1, pageNumber);                          /* rows per page */
			callableStatement.setInt(2, rowsPerPage);                        /* page number*/
			callableStatement.setString(3,OrderBy);
			callableStatement.setString(4,wallet.getCodiceSocieta());
			callableStatement.setString(5,wallet.getCuteCute());
			callableStatement.setString(6,wallet.getChiaveEnte());
			callableStatement.setString(7,wallet.getCodiceFiscaleGenitore());
			callableStatement.setString(8,wallet.getIdWallet());
			callableStatement.setString(9,wallet.getDenominazioneGenitore());
			callableStatement.setString(10, (String)wallet.getAttribute("PGBDATA_PAGAMENTO_DA"));
			callableStatement.setString(11, (String)wallet.getAttribute("PGBDATA_PAGAMENTO_A"));

			/* we register row start */
			callableStatement.registerOutParameter(12, Types.INTEGER);
			/* we register row end */
			callableStatement.registerOutParameter(13, Types.INTEGER);
			/* we register total rows */
			callableStatement.registerOutParameter(14, Types.INTEGER);
			/* we register total pages */
			callableStatement.registerOutParameter(15, Types.SMALLINT);
			String walletCsv = ""; 

			/* we execute procedure */
			String row = "";
			if(callableStatement.execute())	{

				data = callableStatement.getResultSet();
				if ( data.next() ) {
					row += "Codice Borsellino;";
					row += "Codice Fiscale;";
					row += "Denominazione;";
					row += "Importo ricariche eseguite;";
					row += "Importo ricariche acquisite;";
					row += "Importo ricariche non acquisite;";
					row += "Importo consumi pagati;";
					row += "Importo consumi inviati a gestionale entrate;";
					row += "Importo consumi non inviati a gestionale entrate;";
					row += "Residuo;";
					row += "\r";
					file.append(row);
					row = "";
					do {

						String ifWallet = data.getString(1);
						String cfis = data.getString(2);
						String denom = String.valueOf(data.getString(3));
						String cariche = String.valueOf(data.getString(4)).replace(".", ",");
						String caricheacq = String.valueOf(data.getString(5)).replace(".", ",");
						String caricheNOacq = String.valueOf(data.getString(6)).replace(".", ",");
						String consumi = String.valueOf(data.getString(7)).replace(".", ",");
						String consumiinviati = String.valueOf(data.getString(8)).replace(".", ",");
						String consumiNONinviati = String.valueOf(data.getString(9)).replace(".", ",");
						String residuo = String.valueOf(data.getString(10)).replace(".", ",");


						row += ifWallet + ";";
						row += cfis + ";";
						row += denom + ";";
						row += cariche + ";";
						row += caricheacq + ";";
						row += caricheNOacq  + ";";
						row += consumi  + ";";
						row += consumiinviati + ";";
						row += consumiNONinviati + ";";
						row += residuo + ";";
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
		} catch (HelperException e) {
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

	public StringBuffer walletListAnagraficaContribuentiCsv(Wallet wallet, int rowsPerPage, int pageNumber, String OrderBy)	throws DaoException {
		CallableStatement callableStatement=null;
		Connection connection = null;
		ResultSet data = null;
		PageInfo pageInfo = null;
		StringBuffer file = new StringBuffer();
		try {
			connection = getConnection();
			// IMPLEMENTARE la SP !!!!!!!!!!!!!!!!!!!
			// GC_2013_06_26
			//inizio LP 20240913 - PGNTCORE-24
			//callableStatement = Helper.prepareCall(connection, getSchema(), Routines.PYBRSSP_LST_ANA.routine());
			callableStatement =  prepareCall(Routines.PYBRSSP_LST_ANA.routine());
			//fine LP 20240913 - PGNTCORE-24
			callableStatement.setString(1,wallet.getCodiceSocieta());
			callableStatement.setString(2,wallet.getCuteCute());
			callableStatement.setString(3,wallet.getChiaveEnte());
			callableStatement.setString(4,wallet.getIdWallet());
			callableStatement.setString(5,wallet.getCodiceFiscaleGenitore());
			callableStatement.setString(6,wallet.getDenominazioneGenitore());
			callableStatement.setString(7,wallet.getAnagraficaDaBonificare());
			// QF 2013-10-18 aggiunta flagAttivazione
			if(wallet.isFlagPrimoAccesso()!=null)
				callableStatement.setString(8, wallet.isFlagPrimoAccesso()?"Y":"N");
			else
				callableStatement.setString(8, "");
			callableStatement.setString(9,wallet.getAttribute("IDBOLLETTINO").toString());
			callableStatement.setString(10,wallet.getCodiceRid());
			callableStatement.setString(11,OrderBy);
			callableStatement.setInt(12, rowsPerPage);                        /* page number*/
			callableStatement.setInt(13, pageNumber);                          /* rows per page */
			/* we register row start */
			callableStatement.registerOutParameter(14, Types.INTEGER);
			/* we register row end */
			callableStatement.registerOutParameter(15, Types.INTEGER);
			/* we register total rows */
			callableStatement.registerOutParameter(16, Types.INTEGER);
			/* we register total pages */
			callableStatement.registerOutParameter(17, Types.SMALLINT);
			String walletCsv = ""; 

			/* we execute procedure */
			String row = "";
			if(callableStatement.execute())	{

				data = callableStatement.getResultSet();
				if ( data.next() ) {
					row += "Codice Borsellino;";
					row += "Codice Fiscale;";
					row += "Denominazione;";
					row += "Email;";
					row += "Numero Cellulare;";
					row += "Indirizzo;";
					row += "Comune;";
					row += "CAP;";
					row += "Provincia;";
					row += "Stato Bonifica;";
					row += "Stato Attivazione;";
					row += "Welcome Kit Prodotto;";
					row += "Presenza Estratto Conto;";
					row += "\r";
					file.append(row);
					row = "";
					do {

						String ifWallet = data.getString(4);
						String cfis = data.getString(5);
						String denominazione = data.getString(6);
						String indirizzo = data.getString(7);
						String email = data.getString(12);
						String nTel = data.getString(11);
						String comune = data.getString(13);
						String cap = data.getString(14);
						String provincia = data.getString(15);
						String primoaccesso = data.getString(10);
						String statobonifica = data.getString(9);
						String welcomekit = data.getString(16);
						if(!welcomekit.equals("Y"))
							welcomekit = "N";
						String estrattoConto  =data.getString(18);
						if(!estrattoConto.equals("Y"))
							estrattoConto = "N";

						row += ifWallet + ";";
						row += cfis + ";";
						row += denominazione + ";";
						row += email + ";";
						row += nTel + ";";
						row += indirizzo  + ";";
						row += comune + ";";
						row += cap + ";";
						row += provincia + ";";
						row += statobonifica + ";";
						row += primoaccesso  + ";";
						row += welcomekit  + ";";
						row += estrattoConto  + ";";

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
		} catch (HelperException e) {
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

	public WalletPageList  walletServList(Wallet wallet,String tipoServizio,String tributo,String flagrendicontato, int rowsPerPage, int pageNumber, String OrderBy)	throws DaoException {
		CallableStatement callableStatement=null;
		Connection connection = null;
		ResultSet data = null;
		PageInfo pageInfo = null;
		WalletPageList walletPageList = null;
		try {
			connection = getConnection();
			// IMPLEMENTARE la SP !!!!!!!!!!!!!!!!!!!
			// GC_2013_06_26
			//inizio LP 20240913 - PGNTCORE-24
			//callableStatement = Helper.prepareCall(connection, getSchema(), Routines.PYBRSSP_SRV_LST.routine());
			callableStatement =  prepareCall(Routines.PYBRSSP_SRV_LST.routine());
			//fine LP 20240913 - PGNTCORE-24
			callableStatement.setInt(1, pageNumber);                          /* rows per page */
			callableStatement.setInt(2, rowsPerPage);                        /* page number*/
			callableStatement.setString(3,OrderBy);
			callableStatement.setString(4,wallet.getCodiceSocieta());
			callableStatement.setString(5,wallet.getCuteCute());
			callableStatement.setString(6,wallet.getChiaveEnte());
			callableStatement.setString(7,wallet.getCodiceFiscaleGenitore());
			callableStatement.setString(8,wallet.getIdWallet());

			callableStatement.setString(9,tipoServizio);
			callableStatement.setString(10,tributo);
			callableStatement.setString(11, (String)wallet.getAttribute(WalletDAO.PERIDOCARICO_DA));
			callableStatement.setString(12, (String)wallet.getAttribute(WalletDAO.PERIDOCARICO_A));
			callableStatement.setString(13, flagrendicontato);
			/* we register row start */
			callableStatement.registerOutParameter(14, Types.INTEGER);
			/* we register row end */
			callableStatement.registerOutParameter(15, Types.INTEGER);
			/* we register total rows */
			callableStatement.registerOutParameter(16, Types.INTEGER);
			/* we register total pages */
			callableStatement.registerOutParameter(17, Types.SMALLINT);

			/* we execute procedure */
			if(callableStatement.execute())	{
				pageInfo = new PageInfo();
				pageInfo.setPageNumber(pageNumber);
				pageInfo.setRowsPerPage(rowsPerPage);
				pageInfo.setFirstRow(callableStatement.getInt(14));
				pageInfo.setLastRow(callableStatement.getInt(15));
				pageInfo.setNumRows(callableStatement.getInt(16));
				pageInfo.setNumPages(callableStatement.getInt(17));

				data = callableStatement.getResultSet();
				loadWebRowSet(data);
				walletPageList = new WalletPageList(pageInfo, "00","",getWebRowSetXml());
				return walletPageList;
			}



		} catch (SQLException e) {
			e.printStackTrace();
			walletPageList = new WalletPageList(pageInfo, "01", Routines.PYBRSSP_SRV_LST.routine() + "Sql-Exception","");
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
			walletPageList = new WalletPageList(pageInfo, "02", Routines.PYBRSSP_SRV_LST.routine() + "Sql-Exception","");
		} catch (HelperException e) {
			e.printStackTrace();
			walletPageList = new WalletPageList(pageInfo, "03",Routines.PYBRSSP_SRV_LST.routine() + "Sql-Exception","");
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
		return walletPageList;
	}


	//	public CallableStatement  walletServListEmailBatch(Wallet wallet,String tipoServizio,String tributo,String flagrendicontato, int rowsPerPage, int pageNumber, String OrderBy)	throws DaoException {
	//		CallableStatement callableStatement=null;
	//		try {
	//			callableStatement = Helper.prepareCall(getConnection(), getSchema(), Routines.PYBRSSP_LST_SMSEMAIL.routine());
	//			callableStatement.setString(1, cutecute);                          /* rows per page */
	//			callableStatement.execute();
	//		} catch (SQLException e) {
	//			e.printStackTrace();
	//		} catch (IllegalArgumentException e) {
	//			e.printStackTrace();
	//		} catch (HelperException e) {
	//			e.printStackTrace();
	//		} finally {
	//		}
	//		return callableStatement;
	//	}	



	public WalletPageList  walletListServ(Wallet wallet)	throws DaoException {
		CallableStatement callableStatement=null;
		Connection connection = null;
		ResultSet data = null;
		//inizio LP PG21XX04 Leak
		CachedRowSet rowSet = null;
		//fine LP PG21XX04 Leak
		PageInfo pageInfo = null;
		WalletPageList walletPageList = null;
		try {
			connection = getConnection();
			//inizio LP 20240913 - PGNTCORE-24
			//callableStatement = Helper.prepareCall(connection, getSchema(), Routines.PYBRSSP_LST_SRV.routine());
			callableStatement =  prepareCall(Routines.PYBRSSP_LST_SRV.routine());
			//fine LP 20240913 - PGNTCORE-24
			/* page number*/
			callableStatement.setString(1,wallet.getIdWallet());                 //I_BRS_KBRSKBRS
			callableStatement.setString(2,wallet.getCodiceFiscaleGenitore());    //I_BRS_CFISCGEN
			callableStatement.setString(3, wallet.getCuteCute());                //I_BRS_CUTECUTE 
			callableStatement.setString(4, wallet.getChiaveEnte());              //I_BRS_KANEKENT
			callableStatement.setString(5, wallet.getCodiceSocieta());           //I_BRS_CSOCCSOC
			/* we execute procedure */
			if(callableStatement.execute())	{
				data = callableStatement.getResultSet();
				loadWebRowSet(data);
				//walletHomePageList = new WalletHomePageList(pageInfo, "00","",getWebRowSetXml(),"","","");
				String selectXml = getWebRowSetXml();
				//inizio LP PG21XX04 Leak
				//CachedRowSet rowSet = Convert.stringToWebRowSet(selectXml);
				rowSet = Convert.stringToWebRowSet(selectXml);
				//fine LP PG21XX04 Leak
				if (rowSet.next()) {
					if (rowSet.getString(1)==null){
						return null;	
					}
				} 
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
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			//inizio LP PG21XX04 Leak
			//DAOHelper.closeIgnoringException(callableStatement);
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
		return walletPageList;
	}

	public WalletPageList  ListaServiziWalletManager(Wallet wallet, String tipoServizio)	throws DaoException {
		CallableStatement callableStatement=null;
		Connection connection = null;
		ResultSet data = null;
		PageInfo pageInfo = null;
		WalletPageList walletPageList = null;
		try {
			connection = getConnection();
			//inizio LP 20240913 - PGNTCORE-24
			//callableStatement = Helper.prepareCall(connection, getSchema(), Routines.PYBRSSP_LST_SRV_MG.routine());
			callableStatement =  prepareCall(Routines.PYBRSSP_LST_SRV_MG.routine());
			//fine LP 20240913 - PGNTCORE-24
			callableStatement.setString(1,wallet.getCodiceSocieta());
			callableStatement.setString(2,wallet.getCuteCute());
			callableStatement.setString(3,wallet.getChiaveEnte());
			callableStatement.setString(4,wallet.getIdWallet());
			//Tk 2017033088000055 20170403 - inizio
			callableStatement.setString(5,tipoServizio==null?"":tipoServizio);
			callableStatement.setString(6,(String)wallet.getAttribute("PERIDOCARICO_DA")==null?"":(String)wallet.getAttribute("PERIDOCARICO_DA"));
			callableStatement.setString(7,(String)wallet.getAttribute("PERIDOCARICO_A")==null?"":(String)wallet.getAttribute("PERIDOCARICO_A"));
			//Tk 2017033088000055 20170403 - fine
			/* we execute procedure */
			if(callableStatement.execute())	{
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
		return walletPageList;
	}

	public Integer updateFlagEsclusione(Wallet wallet) throws DaoException {
		CallableStatement callableStatement=null;
		Connection connection = null;
		int ret=0;
		try { 
			connection = getConnection();
			//inizio LP 20240913 - PGNTCORE-24
			//callableStatement = Helper.prepareCall(connection, getSchema(), Routines.PYBRSSP_UPD_FESC.routine());
			callableStatement =  prepareCall(Routines.PYBRSSP_UPD_FESC.routine());
			//fine LP 20240913 - PGNTCORE-24
			callableStatement.setString(1, wallet.getCodiceSocieta());
			callableStatement.setString(2, wallet.getCuteCute());
			callableStatement.setString(3, wallet.getChiaveEnte());
			callableStatement.setString(4, wallet.getIdWallet());
			callableStatement.setString(5, wallet.isFlagEsclusioneSMSCortesia()?"Y":"N");
			callableStatement.setString(6, wallet.isFlagEsclusioneSMSSollecito()?"Y":"N");
			callableStatement.setString(7, wallet.isFlagEsclusioneSollecitoCartaceo()?"Y":"N");
			callableStatement.setString(8, wallet.isFlagEsclusioneEvoluzioneIntimazione()?"Y":"N");
			callableStatement.setString(9, wallet.getOperatore());
			callableStatement.registerOutParameter(10, Types.INTEGER);
			callableStatement.execute();
			ret = callableStatement.getInt(10);
		} catch (SQLException e) {
			throw new DaoException(e);
		} catch (IllegalArgumentException e) {
			throw new DaoException(e);
		} catch (HelperException e) {
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

	public String [] tipologiaServizioSel(String codSoc, String codUte, String chiaveEnt)	throws DaoException {
		CallableStatement callableStatement=null;
		Connection connection = null;
		ResultSet data = null;
		String [] tipologiaServizio=new String[2];
		tipologiaServizio[0]="";
		tipologiaServizio[1]="";
		try {
			connection = getConnection();
			//inizio LP 20240913 - PGNTCORE-24
			//callableStatement = Helper.prepareCall(connection, getSchema(), Routines.PYCFESP_SEL_TBOLL.routine());
			callableStatement = prepareCall(Routines.PYCFESP_SEL_TBOLL.routine());
			//fine LP 20240913 - PGNTCORE-24
			callableStatement.setString(1,codUte);           //I_RCP_CUTECUTE
			callableStatement.setString(2,chiaveEnt);        //I_RCP_KANEKENT
			callableStatement.setString(3,codSoc);           //I_RCP_CSOCCSOC		 
			/* we execute procedure */
			if(callableStatement.execute())	{
				data = callableStatement.getResultSet();
				if(data.next()){
					tipologiaServizio[0]=data.getString(1);
					tipologiaServizio[1]=data.getString(2);
				}			
				return tipologiaServizio;
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
		return tipologiaServizio;
	}

	public WalletPageList walletListAnagraficaContribuenti(Wallet wallet, int rowsPerPage, int pageNumber, String OrderBy)	throws DaoException {
		CallableStatement callableStatement=null;
		Connection connection = null;
		ResultSet data = null;
		PageInfo pageInfo = null;
		WalletPageList walletPageList = null;
		try {
			connection = getConnection();
			//			CREATE PROCEDURE PYBRSSP_LST_ANA (	
			//				1	IN I_BRS_CSOCCSOC VARCHAR(5),
			//				2	IN I_BRS_CUTECUTE VARCHAR(5),
			//				3	IN I_BRS_KANEKENT CHAR(10),
			//				4	IN I_BRS_KBRSKBRS VARCHAR(18),
			//				5	IN I_BRS_CFISCGEN VARCHAR(16),
			//				6	IN I_BRS_DBRSGENI VARCHAR(61),	
			//				7	IN I_ORDER VARCHAR(64),
			//				8	IN I_ROWSXPAGE SMALLINT,
			//				9	IN I_PAGENO SMALLINT, 
			//				10	OUT O_ROWINI INTEGER, 
			//				11	OUT O_ROWEND INTEGER ,
			//				12	OUT O_TOTROWS INTEGER, 
			//				13	OUT O_TOTPAGES SMALLINT ) 
			//inizio LP 20240913 - PGNTCORE-24
			//callableStatement = Helper.prepareCall(connection, getSchema(), Routines.PYBRSSP_LST_ANA.routine());
			callableStatement = prepareCall(Routines.PYBRSSP_LST_ANA.routine());
			//fine LP 20240913 - PGNTCORE-24
			callableStatement.setString(1,wallet.getCodiceSocieta());
			callableStatement.setString(2,wallet.getCuteCute());
			callableStatement.setString(3,wallet.getChiaveEnte());
			callableStatement.setString(4,wallet.getIdWallet());
			callableStatement.setString(5,wallet.getCodiceFiscaleGenitore());
			callableStatement.setString(6,wallet.getDenominazioneGenitore());
			callableStatement.setString(7,wallet.getAnagraficaDaBonificare());
			// QF 2013-10-18 aggiunta flagAttivazione
			if(wallet.isFlagPrimoAccesso()!=null)
				callableStatement.setString(8, wallet.isFlagPrimoAccesso()?"Y":"N");
			else
				callableStatement.setString(8, "");
			callableStatement.setString(9,wallet.getAttribute("IDBOLLETTINO").toString());
			callableStatement.setString(10,wallet.getCodiceRid());
			callableStatement.setString(11,OrderBy);
			callableStatement.setInt(12, rowsPerPage);                        /* page number*/
			callableStatement.setInt(13, pageNumber);                          /* rows per page */
			/* we register row start */
			callableStatement.registerOutParameter(14, Types.INTEGER);
			/* we register row end */
			callableStatement.registerOutParameter(15, Types.INTEGER);
			/* we register total rows */
			callableStatement.registerOutParameter(16, Types.INTEGER);
			/* we register total pages */
			callableStatement.registerOutParameter(17, Types.SMALLINT);

			/* we execute procedure */
			if(callableStatement.execute())	{
				pageInfo = new PageInfo();
				pageInfo.setPageNumber(pageNumber);
				pageInfo.setRowsPerPage(rowsPerPage);
				pageInfo.setFirstRow(callableStatement.getInt(14));
				pageInfo.setLastRow(callableStatement.getInt(15));
				pageInfo.setNumRows(callableStatement.getInt(16));
				pageInfo.setNumPages(callableStatement.getInt(17));

				data = callableStatement.getResultSet();
				loadWebRowSet(data);
				walletPageList = new WalletPageList(pageInfo, "00","",getWebRowSetXml());
				return walletPageList;
			}

		} catch (SQLException e) {
			e.printStackTrace();
			walletPageList = new WalletPageList(pageInfo, "01", Routines.PYBRSSP_LST_ANA.routine() + "Sql-Exception","");
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
			walletPageList = new WalletPageList(pageInfo, "02", Routines.PYBRSSP_LST_ANA.routine() + "Sql-Exception","");
		} catch (HelperException e) {
			e.printStackTrace();
			walletPageList = new WalletPageList(pageInfo, "03",Routines.PYBRSSP_LST_ANA.routine() + "Sql-Exception","");
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
		return walletPageList;
	}

	public String walletServizioRiepilogo(Wallet wallet, String tipoServizio,
			String tributo, String flagrendicontato, int rowsPerPage,
			int pageNumber, String OrderBy) throws DaoException {
		CallableStatement callableStatement=null;
		String walletRiepilogoListXml = null;
		WalletPageList walletPageList = null;
		Connection connection = null;
		ResultSet data = null;
		PageInfo pageInfo = null;
		try {
			connection = getConnection();
			//inizio LP 20240913 - PGNTCORE-24
			//callableStatement = Helper.prepareCall(connection, getSchema(), Routines.PYBRSSP_SRV_RIEP.routine());
			callableStatement = prepareCall(Routines.PYBRSSP_SRV_RIEP.routine());
			//fine LP 20240913 - PGNTCORE-24
			callableStatement.setString(1,wallet.getCodiceSocieta());
			callableStatement.setString(2,wallet.getCuteCute());
			callableStatement.setString(3,wallet.getChiaveEnte());
			callableStatement.setString(4,wallet.getCodiceFiscaleGenitore());

			callableStatement.setString(5,wallet.getIdWallet());
			callableStatement.setString(6,tipoServizio);
			callableStatement.setString(7,tributo);
			callableStatement.setString(8 , (String)wallet.getAttribute(WalletDAO.PERIDOCARICO_DA));
			callableStatement.setString(9 , (String)wallet.getAttribute(WalletDAO.PERIDOCARICO_A));
			callableStatement.setString(10, flagrendicontato);
			/* we select */
			/* we execute procedure */
			if(callableStatement.execute())	{
				data = callableStatement.getResultSet();
				loadWebRowSet(data);
				walletRiepilogoListXml = getWebRowSetXml();
				//return walletRiepilogoListXml;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			walletPageList = new WalletPageList(pageInfo, "01", Routines.PYBRSSP_SRV_RIEP.routine() + "Sql-Exception","");
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
			walletPageList = new WalletPageList(pageInfo, "02", Routines.PYBRSSP_SRV_RIEP.routine() + "Sql-Exception","");
		} catch (HelperException e) {
			e.printStackTrace();
			walletPageList = new WalletPageList(pageInfo, "03",Routines.PYBRSSP_SRV_RIEP.routine() + "Sql-Exception","");
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
		return walletRiepilogoListXml;
	}

	public String selectDetSrv(String idWallet,String codServ,String tributo, String amcar)  throws DaoException {
		String walletDetSrvListXml="";
		CallableStatement callableStatement=null;
		Connection connection = null;
		ResultSet data = null;
		try {
			connection = getConnection();
			//inizio LP 20240913 - PGNTCORE-24
			//callableStatement = Helper.prepareCall(connection, getSchema(), Routines.PYBRSSP_SRV_DETT.routine());
			callableStatement = prepareCall(Routines.PYBRSSP_SRV_DETT.routine());
			//fine LP 20240913 - PGNTCORE-24
			callableStatement.setString(1,idWallet);
			callableStatement.setString(2,codServ);
			callableStatement.setString(3,tributo);
			callableStatement.setString(4,amcar);

			if(callableStatement.execute())	{
				data = callableStatement.getResultSet();
				loadWebRowSet(data);
				walletDetSrvListXml = getWebRowSetXml();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (HelperException e) {
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
		return walletDetSrvListXml;
	}

	public String listBollettini(String idWallet)  throws DaoException {
		String walletlistBollettiniXml="";
		CallableStatement callableStatement=null;
		Connection connection = null;
		ResultSet data = null;
		try {
			connection = getConnection();
			//inizio LP 20240913 - PGNTCORE-24
			//callableStatement = Helper.prepareCall(connection, getSchema(), Routines.PYBRLSP_LST.routine());
			callableStatement = prepareCall(Routines.PYBRLSP_LST.routine());
			//fine LP 20240913 - PGNTCORE-24
			callableStatement.setString(1,idWallet);
			if(callableStatement.execute())	{
				data = callableStatement.getResultSet();
				loadWebRowSet(data);
				walletlistBollettiniXml = getWebRowSetXml();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (HelperException e) {
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
		return walletlistBollettiniXml;
	}

	public String listBollettiniForQuaryHost(String idWallet)  throws DaoException {
		CallableStatement callableStatement=null;
		Connection connection = null;
		ResultSet data = null;
		String arrayListBRL ="'"+ idWallet+"'";
		try {
			connection = getConnection();
			//inizio LP 20240913 - PGNTCORE-24
			//callableStatement = Helper.prepareCall(connection, getSchema(), Routines.PYBRLSP_LST.routine());
			callableStatement = prepareCall(Routines.PYBRLSP_LST.routine());
			//fine LP 20240913 - PGNTCORE-24
			callableStatement.setString(1,idWallet);
			if(callableStatement.execute())	{
				data = callableStatement.getResultSet();
				if ( data.next() ) {
					do {
						String bollettino = data.getString(2);
						arrayListBRL = arrayListBRL + ",'" + bollettino + "'";
					} while(data.next());
					data.close();
					callableStatement.close();
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (HelperException e) {
			e.printStackTrace();
		} finally {
			//inizio LP PG21XX04 Leak
			//DAOHelper.closeIgnoringException(connection);
			//DAOHelper.closeIgnoringException(callableStatement);
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
		return arrayListBRL;
	}

	//inizio LP 20240913 - PGNTCORE-24
	//public String listBollettiniForQuaryHostBatch(String idWallet)  throws DaoException {
	public String listBollettiniForQuaryHostBatch(boolean bFlagUpdateAutocommit, boolean bCloseStat, String idWallet)  throws DaoException {
	//fine LP 20240913 - PGNTCORE-24
		CallableStatement callableStatement=null;
		Connection connection = null;
		ResultSet data = null;
		String arrayListBRL ="'"+ idWallet+"'";
		try {
			connection = getConnection();
			//inizio LP 20240913 - PGNTCORE-24
			//callableStatement = Helper.prepareCall(connection, getSchema(), Routines.PYBRLSP_LST.routine());
			if(callableStatementBRLSPLST == null) {
				callableStatementBRLSPLST = prepareCall(Routines.PYBRLSP_LST.routine());
				callableStatement = callableStatementBRLSPLST; 
			}
			//fine LP 20240913 - PGNTCORE-24
			callableStatement.setString(1,idWallet);
			if(callableStatement.execute())	{
				data = callableStatement.getResultSet();
				if ( data.next() ) {
					do {
						String bollettino = data.getString(2);
						arrayListBRL = arrayListBRL + ",'" + bollettino + "'";
					} while(data.next());
					data.close();
					callableStatement.close();
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (HelperException e) {
			e.printStackTrace();
		} finally {
			//inizio LP PG21XX04 Leak
			//DAOHelper.closeIgnoringException(callableStatement);
			if(data != null) {
				try {
					data.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			//inizio LP 20240913 - PAGONET-604
			if(bCloseStat) {
			//fine LP 20240913 - PAGONET-604
				if(callableStatement != null) {
					try {
						callableStatement.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
				//fine LP PG21XX04 Leak
			//inizio LP 20240913 - PAGONET-604
			}
			//fine LP 20240913 - PAGONET-604
		}
		return arrayListBRL;

	}

	public String listPagamenti(Wallet wallet)  throws DaoException {
		String walletlistBollettiniXml="";
		CallableStatement callableStatement=null;
		Connection connection = null;
		ResultSet data = null;
		try {
			connection = getConnection();
			//inizio LP 20240913 - PGNTCORE-24
			//callableStatement = Helper.prepareCall(connection, getSchema(), Routines.PYPGBTB_LST_MG.routine());
			callableStatement = prepareCall(Routines.PYPGBTB_LST_MG.routine());
			//fine LP 20240913 - PGNTCORE-24
			callableStatement.setString(1,wallet.getIdWallet());
			callableStatement.setString(2, (String)wallet.getAttribute("PGBDATA_PAGAMENTO_DA"));
			callableStatement.setString(3, (String)wallet.getAttribute("PGBDATA_PAGAMENTO_A"));
			if(callableStatement.execute())	{
				data = callableStatement.getResultSet();
				loadWebRowSet(data);
				walletlistBollettiniXml = getWebRowSetXml();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (HelperException e) {
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
		return walletlistBollettiniXml;
	}

	public String listSolleciti(Wallet wallet,int prog)  throws DaoException {
		String walletlistSollecitiXml="";
		CallableStatement callableStatement=null;
		Connection connection = null;
		ResultSet data = null;
		try {
			connection = getConnection();
			//inizio LP 20240913 - PGNTCORE-24
			//callableStatement = Helper.prepareCall(connection, getSchema(), Routines.PYSOLSP_LST_ANNO.routine());
			callableStatement = prepareCall(Routines.PYSOLSP_LST_ANNO.routine());
			//fine LP 20240913 - PGNTCORE-24
			callableStatement.setString(1,wallet.getIdWallet());
			callableStatement.setString(2, (String)wallet.getAttribute("PGBDATA_PAGAMENTO_DA"));
			callableStatement.setString(3, (String)wallet.getAttribute("PGBDATA_PAGAMENTO_A"));
			callableStatement.setString(4, (String)wallet.getAttribute("PGBDATADIS_PAGAMENTO_DA"));
			callableStatement.setString(5, (String)wallet.getAttribute("PGBDATADIS_PAGAMENTO_A"));
			callableStatement.setInt(6, prog);
			if(callableStatement.execute())	{
				data = callableStatement.getResultSet();
				loadWebRowSet(data);
				walletlistSollecitiXml = getWebRowSetXml();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (HelperException e) {
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
		return walletlistSollecitiXml;
	}

	public List<String> getSollecitoAsList(Wallet wallet)  throws DaoException {
		List<String> sollecitoAsString= new ArrayList<String>();
		String walletlistSollecitiXml="";
		CallableStatement callableStatement=null;
		Connection connection = null;
		ResultSet data = null;
		try {
			connection = getConnection();
			//inizio LP 20240913 - PGNTCORE-24
			//callableStatement = Helper.prepareCall(connection, getSchema(), Routines.PYSOLSP_LST_ANNO.routine());
			callableStatement = prepareCall(Routines.PYSOLSP_LST_ANNO.routine());
			//fine LP 20240913 - PGNTCORE-24
			callableStatement.setString(1,wallet.getIdWallet());
			callableStatement.setString(2, (String)wallet.getAttribute("PGBDATA_PAGAMENTO_DA"));
			callableStatement.setString(3, (String)wallet.getAttribute("PGBDATA_PAGAMENTO_A"));
			callableStatement.setString(4, "");
			callableStatement.setString(5, "");
			callableStatement.setInt(6, 0);

			if(callableStatement.execute())	{
				data = callableStatement.getResultSet();
				if(data.next()){
					sollecitoAsString.add(data.getString(1)); //SOL_KBRSKBRS
					sollecitoAsString.add(data.getString(2)); //SOL_CSOLTIPO
					sollecitoAsString.add(data.getString(3)); //SOL_GSOLDTA
					sollecitoAsString.add(data.getString(4)); //SOL_DSOLFILE
					sollecitoAsString.add(data.getString(5)); //SOL_CSOLCONE
					sollecitoAsString.add(data.getString(6)); //SOL_DSOLDESC
					sollecitoAsString.add(data.getString(7)); //SOL_ISOLIMPO
					sollecitoAsString.add(data.getString(8)); //SOL_ISOLIPAG
					sollecitoAsString.add(data.getString(9)); //SOL_ISOLIREN
					sollecitoAsString.add(data.getString(10)); //SOL_CSRVCODI
					sollecitoAsString.add(data.getString(11)); //SOL_DSOLDDES
					sollecitoAsString.add(data.getString(12)); //SOL_ISOLISOL
					sollecitoAsString.add(data.getString(13)); //SOL_CSOLNTEL
					sollecitoAsString.add(data.getString(14)); //SOL_DSOLMAIL
					sollecitoAsString.add(data.getString(15)); //SOL_CSOLIDFL
					sollecitoAsString.add(data.getString(16)); //SOL_CSOLCESI
					sollecitoAsString.add(data.getString(17)); //SOL_DSOLDESI
					sollecitoAsString.add(data.getString(18)); //SOL_FSOLFTMB
					sollecitoAsString.add(data.getString(19)); //SOL_NSOLPROG
					sollecitoAsString.add(data.getString(20)); //SOL_FSOLENTE
					sollecitoAsString.add(data.getString(21)); //SOL_FSOLVALI
					sollecitoAsString.add(data.getString(22)); //SOL_FSOLDISC
					sollecitoAsString.add(data.getString(23)); //SOL_GSOLINSE
					sollecitoAsString.add(data.getString(24)); //SOL_CSOLOPER
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (HelperException e) {
			e.printStackTrace();
		} finally {
			//DAOHelper.closeIgnoringException(connection);
			//inizio LP PG21XX04 Leak
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
		return sollecitoAsString;
	}

	public String insertRowDiscarico(List<String> sollecitoAsList,int prog) throws DaoException{
		CallableStatement callableStatement=null;
		Connection connection = null; 
		SimpleDateFormat df=new SimpleDateFormat("dd/MM/yyyy");
		String errori = "";
		try { 
			connection = getConnection();
			//inizio LP 20240913 - PGNTCORE-24
			//callableStatement = Helper.prepareCall(connection, getSchema(), Routines.PYSOLSP_INS.routine());
			callableStatement = prepareCall(Routines.PYSOLSP_INS.routine());
			//fine LP 20240913 - PGNTCORE-24
			callableStatement.setString(1, sollecitoAsList.get(0).trim());
			callableStatement.setString(2, sollecitoAsList.get(1).trim());
			java.util.Date dataSollecito=df.parse(sollecitoAsList.get(2).trim());
			callableStatement.setDate(3,new java.sql.Date(dataSollecito.getTime()));
			callableStatement.setString(4, sollecitoAsList.get(3).trim());
			callableStatement.setString(5, sollecitoAsList.get(4).trim());
			callableStatement.setString(6, sollecitoAsList.get(5).trim());
			///
			callableStatement.setBigDecimal(7, new BigDecimal(sollecitoAsList.get(6).trim()).multiply(BigDecimal.valueOf(-1.0)));
			callableStatement.setBigDecimal(8, new BigDecimal(sollecitoAsList.get(7).trim()));
			callableStatement.setBigDecimal(9, new BigDecimal(sollecitoAsList.get(8).trim()));
			//
			callableStatement.setString(10, sollecitoAsList.get(9).trim());
			callableStatement.setString(11, sollecitoAsList.get(10).trim());
			//
			callableStatement.setBigDecimal(12, new BigDecimal(sollecitoAsList.get(11).trim()));
			//
			callableStatement.setString(13, sollecitoAsList.get(12).trim());
			callableStatement.setString(14, sollecitoAsList.get(13).trim());
			callableStatement.setString(15, sollecitoAsList.get(14).trim());
			callableStatement.setString(16, sollecitoAsList.get(15).trim());
			callableStatement.setString(17, sollecitoAsList.get(16).trim());
			//TOMB ST.
			callableStatement.setString(18, "N");
			//
			prog++;
			callableStatement.setInt(19, prog);
			//
			callableStatement.setString(20, sollecitoAsList.get(19).trim());
			//Discarico
			callableStatement.setString(21, "D");
			//KEY: P_GSOLADATA_NSOLPROG
			SimpleDateFormat dfKey=new SimpleDateFormat("yyyy-MM-dd");
			String progFormat="%05d";
			prog--;
			String key="P".concat("_").concat(dfKey.format(dataSollecito)).concat("_").concat(String.format(progFormat,prog));
			callableStatement.setString(22, key);
			//Timestamp data di inserimento
			java.util.Date currentTime=new java.util.Date();
			callableStatement.setTimestamp(23, new java.sql.Timestamp(currentTime.getTime()));
			//User Id collegato
			callableStatement.setString(24, sollecitoAsList.get(23).trim());
			callableStatement.execute();
		} catch (SQLException e) {
			throw new DaoException(e);
		} catch (IllegalArgumentException e) {
			throw new DaoException(e);
		} catch (HelperException e) {
			throw new DaoException(e);
		} catch (ParseException e) {
			e.printStackTrace();
			throw new DaoException(e);
		} finally {
			//inizio LP PG21XX04 Leak
			//DAOHelper.closeIgnoringException(callableStatement);
			////			DAOHelper.closeIgnoringException(connection);
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
		return "";
	}

	public String insertRowAnnullamento(List<String> sollecitoAsList,int prog) throws DaoException{
		CallableStatement callableStatement=null;
		Connection connection = null; 
		SimpleDateFormat df=new SimpleDateFormat("dd/MM/yyyy");
		String errori = "";
		try { 
			connection = getConnection();
			//inizio LP 20240913 - PGNTCORE-24
			//callableStatement = Helper.prepareCall(connection, getSchema(), Routines.PYSOLSP_INS.routine());
			callableStatement = prepareCall(Routines.PYSOLSP_INS.routine());
			//fine LP 20240913 - PGNTCORE-24
			callableStatement.setString(1, sollecitoAsList.get(0).trim());
			callableStatement.setString(2, sollecitoAsList.get(1).trim());
			java.util.Date dataSollecito=df.parse(sollecitoAsList.get(2).trim());
			callableStatement.setDate(3,new java.sql.Date(dataSollecito.getTime()));
			callableStatement.setString(4, sollecitoAsList.get(3).trim());
			callableStatement.setString(5, sollecitoAsList.get(4).trim());
			callableStatement.setString(6, sollecitoAsList.get(5).trim());
			///
			callableStatement.setBigDecimal(7, new BigDecimal(sollecitoAsList.get(6).trim()));
			callableStatement.setBigDecimal(8, new BigDecimal(sollecitoAsList.get(7).trim()));
			callableStatement.setBigDecimal(9, new BigDecimal(sollecitoAsList.get(8).trim()));
			//
			callableStatement.setString(10, sollecitoAsList.get(9).trim());
			callableStatement.setString(11, sollecitoAsList.get(10).trim());
			//
			callableStatement.setBigDecimal(12, new BigDecimal(sollecitoAsList.get(11).trim()));
			//
			callableStatement.setString(13, sollecitoAsList.get(12).trim());
			callableStatement.setString(14, sollecitoAsList.get(13).trim());
			callableStatement.setString(15, sollecitoAsList.get(14).trim());
			callableStatement.setString(16, sollecitoAsList.get(15).trim());
			callableStatement.setString(17, sollecitoAsList.get(16).trim());
			//TOMB ST.
			callableStatement.setString(18, "N");
			//
			prog++;
			callableStatement.setInt(19, prog);
			//
			callableStatement.setString(20, sollecitoAsList.get(19).trim());
			//Discarico
			callableStatement.setString(21, "A");
			//KEY: P_GSOLADATA_NSOLPROG
			SimpleDateFormat dfKey=new SimpleDateFormat("yyyy-MM-dd");
			String progFormat="%05d";
			prog--;
			String key="P".concat("_").concat(dfKey.format(dataSollecito)).concat("_").concat(String.format(progFormat,prog));
			callableStatement.setString(22, key);
			//Timestamp data di inserimento
			java.util.Date currentTime=new java.util.Date();
			callableStatement.setTimestamp(23, new java.sql.Timestamp(currentTime.getTime()));
			//User Id collegato
			callableStatement.setString(24, sollecitoAsList.get(23).trim());
			callableStatement.execute();
		} catch (SQLException e) {
			throw new DaoException(e);
		} catch (IllegalArgumentException e) {
			throw new DaoException(e);
		} catch (HelperException e) {
			throw new DaoException(e);
		} catch (ParseException e) {
			e.printStackTrace();
			throw new DaoException(e);
		} finally {
			//inizio LP PG21XX04 Leak
			//DAOHelper.closeIgnoringException(callableStatement);
			////			DAOHelper.closeIgnoringException(connection);
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
		return "";
	}

	public ArrayList<String> getAutorizzazione(String societa,String cutecute, String ente)  throws DaoException {		
		CallableStatement callableStatement=null;
		Connection connection = null;
		ResultSet data = null;
		ArrayList<String> autorizzazioni = null;
		try {
			connection = getConnection();
			//inizio LP 20240913 - PGNTCORE-24
			//callableStatement = Helper.prepareCall(connection, getSchema(), Routines.PYRCPSP_SEL.routine());
			callableStatement = prepareCall(Routines.PYRCPSP_SEL.routine());
			//fine LP 20240913 - PGNTCORE-24
			callableStatement.setString(1,societa);
			callableStatement.setString(2,cutecute);
			callableStatement.setString(3,ente);
			callableStatement.setString(4,"BOR");

			if(callableStatement.execute())	{
				data = callableStatement.getResultSet();
				if(data.next()){
					autorizzazioni = new ArrayList<String>();
					autorizzazioni.add(data.getString(7));
					autorizzazioni.add(data.getString(8));
				}			
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (HelperException e) {
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

		return autorizzazioni;
	}

	public ArrayList<String> getAutorizzazioneBatch(String societa,String cutecute, String ente)  throws DaoException {		
		CallableStatement callableStatement=null;
		Connection connection = null;
		ResultSet data = null;
		ArrayList<String> autorizzazioni = null;
		try {
			//inizio LP 20240913 - PGNTCORE-24
			//connection = getConnection();
			//callableStatement = Helper.prepareCall(connection, getSchema(), Routines.PYRCPSP_SEL.routine());
			if(callableStatementRCPSEL == null) {
				callableStatementRCPSEL = prepareCall(false, Routines.PYRCPSP_SEL.routine());
				callableStatement = callableStatementRCPSEL;
			}
			//fine LP 20240913 - PGNTCORE-24
			callableStatement.setString(1,societa);
			callableStatement.setString(2,cutecute);
			callableStatement.setString(3,ente);
			callableStatement.setString(4,"BOR");

			if(callableStatement.execute())	{
				data = callableStatement.getResultSet();
				if(data.next()){
					autorizzazioni = new ArrayList<String>();
					autorizzazioni.add(data.getString(7));
					autorizzazioni.add(data.getString(8));
				}			
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (HelperException e) {
			e.printStackTrace();
		} finally {
			//inizio LP PG21XX04 Leak
			//DAOHelper.closeIgnoringException(callableStatement);
			if(data != null) {
				try {
					data.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			//inizio LP 20240913 - PGNTCORE-24
			//if(callableStatement != null) {
			//	try {
			//		callableStatement.close();
			//	} catch (SQLException e) {
			//		e.printStackTrace();
			//	}
			//}
			//fine LP PG21XX04 Leak
			//fine LP 20240913 - PGNTCORE-24
		}
		return autorizzazioni;
	}

//PG150310_001 GG - introdotto parametro borselliniAttivi
//	PG140300
	public CallableStatement  listWalletforSmsEmailBatch(String cutecute, String tipoInvio,String idWallet,String IdFlusso,String flagRivestizione,String borselliniAttivi)	throws DaoException {
//  public CallableStatement  listWalletforSmsEmailBatch(String cutecute, String tipoInvio,String idWallet,String IdFlusso,String flagRivestizione)	throws DaoException {
//	public CallableStatement  listWalletforSmsEmailBatch(String cutecute, String tipoInvio,String idWallet,String IdFlusso)	throws DaoException {
		CallableStatement callableStatement=null;
		try {
			//inizio LP 20240913 - PGNTCORE-24
			//callableStatement = Helper.prepareCall(getConnection(), getSchema(), Routines.PYBRSSP_LST_SMSEMAIL.routine());
			if(callableStatementBRSLSTSMSEMAIL == null) {
				callableStatementBRSLSTSMSEMAIL = prepareCall(false, Routines.PYBRSSP_LST_SMSEMAIL.routine());
				callableStatement = callableStatementBRSLSTSMSEMAIL;
			}
			//fine LP 20240913 - PGNTCORE-24
			callableStatement.setString(1, cutecute);                          
			callableStatement.setString(2, tipoInvio);                         
			callableStatement.setString(3, idWallet);                          
			callableStatement.setString(4, IdFlusso);                          
			callableStatement.setString(5, flagRivestizione);
			callableStatement.setString(6, borselliniAttivi);	//PG150310_001 GG
			callableStatement.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (HelperException e) {
			e.printStackTrace();
		}
		return callableStatement;
	}	

	//inizio LP PG21XX04 Leak
	//Nota. Metodo usato solo da procedure batch
	//fine LP PG21XX04 Leak
	//inizio LP 20240913 - PAGONET-604
	public void  updateSollecitiConIdflusso(String idWallet, String tipoOperazione,String dataoperazione,String idFlusso)	throws DaoException {
		updateSollecitiConIdflussoTail(true, true, idWallet, tipoOperazione, dataoperazione, idFlusso);
	}

	public void  updateSollecitiConIdflussoBatch(boolean bFlagUpdateAutocommit, boolean bCloseStat, String idWallet, String tipoOperazione,String dataoperazione,String idFlusso )	throws DaoException {
		updateSollecitiConIdflussoTail(bFlagUpdateAutocommit, bCloseStat, idWallet, tipoOperazione, dataoperazione, idFlusso);
	}

	private void  updateSollecitiConIdflussoTail(boolean bFlagUpdateAutocommit, boolean bCloseStat, String idWallet, String tipoOperazione,String dataoperazione,String idFlusso )	throws DaoException {
	//fine LP 20240913 - PAGONET-604
		CallableStatement callableStatement=null;
		try {
			//inizio LP 20240913 - PGNTCORE-24
			//callableStatement = Helper.prepareCall(getConnection(), getSchema(), Routines.PYSOLSP_UPD_LSTW.routine());
			if(callableStatementSOLUPDLSTW == null) {
				callableStatementSOLUPDLSTW = prepareCall(bFlagUpdateAutocommit, Routines.PYSOLSP_UPD_LSTW.routine());
				callableStatement = callableStatementSOLUPDLSTW;
			}
			//fine LP 20240913 - PGNTCORE-24
			callableStatement.setString(1, idWallet);                         
			callableStatement.setString(2, tipoOperazione);                   
			callableStatement.setString(3, dataoperazione);                     
			callableStatement.setString(4, idFlusso);

			callableStatement.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (HelperException e) {
			e.printStackTrace();
		} finally {
			//inizio LP 20240913 - PAGONET-604
			if(bCloseStat) {
			//fine LP 20240913 - PAGONET-604
				//inizio LP PG21XX04 Leak
				if(callableStatement != null) {
					try {
						callableStatement.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
				//fine LP PG21XX04 Leak
			//inizio LP 20240913 - PAGONET-604
				callableStatement = null;
				callableStatementSOLUPDLSTW = null;
			}
			//fine LP 20240913 - PAGONET-604
		}
	}

	//inizio LP PG21XX04 Leak
	//Nota. Metodo usato solo da procedure batch
	//fine LP PG21XX04 Leak
	//inizio LP 20240913 - PGNTCORE-24
	public void  cancellaSollecitopagAcq(String idWallet, String tipoOperazione,String dataoperazione,String NumeroTelefono)	throws DaoException {
		cancellaSollecitopagAcqTail(true, true, idWallet, tipoOperazione, dataoperazione, NumeroTelefono);
	}

	public void  cancellaSollecitopagAcqBatch(boolean bFlagUpdateAutocommit, boolean bCloseStat, String idWallet, String tipoOperazione,String dataoperazione,String NumeroTelefono )	throws DaoException {
		cancellaSollecitopagAcqTail(bFlagUpdateAutocommit, bCloseStat, idWallet, tipoOperazione, dataoperazione, NumeroTelefono);
	}

	private void cancellaSollecitopagAcqTail(boolean bFlagUpdateAutocommit, boolean bCloseStat, String idWallet, String tipoOperazione,String dataoperazione,String NumeroTelefono )	throws DaoException {
	//fine LP 20240913 - PGNTCORE-24
		CallableStatement callableStatement=null;
		try {
			//inizio LP 20240913 - PGNTCORE-24
			//callableStatement = Helper.prepareCall(getConnection(), getSchema(), Routines.PYSOLSP_DEL_SOL.routine());
			if(callableStatementSOLDELSOL == null) {
				callableStatementSOLDELSOL = prepareCall(Routines.PYSOLSP_DEL_SOL.routine());
				callableStatement = callableStatementSOLDELSOL;
			}
			//fine LP 20240913 - PGNTCORE-24
			callableStatement.setString(1, idWallet);                         
			callableStatement.setString(2, tipoOperazione);                   
			callableStatement.setString(3, dataoperazione);                     
			callableStatement.setString(4, NumeroTelefono);

			callableStatement.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (HelperException e) {
			e.printStackTrace();
		} finally {
			//inizio LP 20240913 - PGNTCORE-24
			if(bCloseStat) {
			//fine LP 20240913 - PGNTCORE-24
				//inizio LP PG21XX04 Leak
				if(callableStatement != null) {
				//fine LP PG21XX04 Leak
					try {
						callableStatement.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
				//inizio LP PG21XX04 Leak
				}
				//fine LP PG21XX04 Leak
			//inizio LP 20240913 - PGNTCORE-24
				callableStatement = null;
				callableStatementSOLDELSOL = null;
			}
			//fine LP 20240913 - PGNTCORE-24
		}
	}

	//inizio LP PG21XX04 Leak
	//Nota. Metodo usato solo da procedure batch
	//fine LP PG21XX04 Leak
	private CallableStatement callableStatementEsitiSMS = null;  

	public Integer updateSollecitiConEsitoSMS(String numerocellulare, String Esito,String DescrizioneEsito,String IdFlusso, java.util.Date dataFile) throws DaoException {
	//inizio LP 20240913 - PGNTCORE-24/PGNTWPB-3
		return updateSollecitiConEsitoSMSTail(true, numerocellulare, Esito, DescrizioneEsito, IdFlusso, dataFile);
	}

	public Integer updateSollecitiConEsitoSMSBatch(boolean bFlagUpdateAutocommit, String numerocellulare, String Esito,String DescrizioneEsito,String IdFlusso,java.util.Date dataFile) throws DaoException {
		return updateSollecitiConEsitoSMSTail(bFlagUpdateAutocommit, numerocellulare, Esito, DescrizioneEsito, IdFlusso, dataFile);
	}

	private Integer updateSollecitiConEsitoSMSTail(boolean bFlagUpdateAutocommit, String numerocellulare, String Esito,String DescrizioneEsito,String IdFlusso,java.util.Date dataFile) throws DaoException {
	//fine LP 20240913 - PGNTCORE-24/PGNTWPB-3
		//CallableStatement callableStatement=null;
		int ret=0;
		try {

			//callableStatement = Helper.prepareCall(getConnection(), getSchema(), Routines.PYSOLSP_UPD_ESITOSMS.routine());
			if(callableStatementEsitiSMS == null){
				//inizio LP 20240913 - PGNTCORE-24/PGNTWPB-3
				//callableStatementEsitiSMS = Helper.prepareCall(getConnection(), getSchema(), Routines.PYSOLSP_UPD_ESITOSMS.routine());
				callableStatementEsitiSMS = prepareCall(bFlagUpdateAutocommit, Routines.PYSOLSP_UPD_ESITOSMS.routine());
				//fine LP 20240913 - PGNTCORE-24/PGNTWPB-3
			}
			callableStatementEsitiSMS.setString(1, numerocellulare);                         
			callableStatementEsitiSMS.setString(2, IdFlusso);                   
			callableStatementEsitiSMS.setString(3, Esito);                     
			callableStatementEsitiSMS.setString(4, DescrizioneEsito);
			callableStatementEsitiSMS.setDate(5,new  java.sql.Date(dataFile.getTime()));
			callableStatementEsitiSMS.registerOutParameter(6, Types.INTEGER);

			callableStatementEsitiSMS.execute();
			ret = callableStatementEsitiSMS.getInt(6);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (HelperException e) {
			e.printStackTrace();
		}
		return ret;
	}

	public CallableStatement walletServListEmailBatch(Wallet wallet,int rowsPerPage, int pageNumber, String OrderBy)	throws DaoException {
		CallableStatement callableStatement = null;
		//Connection connection = null; //LP 20240913 - PGNTCORE-24
		try {
			//inizio LP 20240913 - PGNTCORE-24
			//connection = getConnection();
			//callableStatement = Helper.prepareCall(connection, getSchema(), Routines.PYBRSSP_SRV_LST.routine());
			callableStatement = prepareCall(false, Routines.PYBRSSP_SRV_LST.routine());
			//fine LP 20240913 - PGNTCORE-24
			callableStatement.setInt(1, pageNumber);                          /* rows per page */
			callableStatement.setInt(2, rowsPerPage);                        /* page number*/
			callableStatement.setString(3,OrderBy);
			callableStatement.setString(4,wallet.getCodiceSocieta());
			callableStatement.setString(5,wallet.getCuteCute());
			callableStatement.setString(6,wallet.getChiaveEnte());
			callableStatement.setString(7,wallet.getCodiceFiscaleGenitore());
			callableStatement.setString(8,wallet.getIdWallet());
			callableStatement.setString(9,"");
			callableStatement.setString(10,"");
			callableStatement.setString(11, "");
			callableStatement.setString(12, "");
			callableStatement.setString(13, "");
			/* we register row start */
			callableStatement.registerOutParameter(14, Types.INTEGER);
			/* we register row end */
			callableStatement.registerOutParameter(15, Types.INTEGER);
			/* we register total rows */
			callableStatement.registerOutParameter(16, Types.INTEGER);
			/* we register total pages */
			callableStatement.registerOutParameter(17, Types.SMALLINT);

			callableStatement.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (HelperException e) {
			e.printStackTrace();
		}
		return callableStatement;
	}

	//inizio LP 20240913 - PGNTCORE-24
	public ResultSet walletServListEmailBatchRes(Wallet wallet, int rowsPerPage, int pageNumber, String OrderBy) throws DaoException
	{
		ResultSet res = null;
		try {
			if(callableStatementBRSSRVLST == null) {
				callableStatementBRSSRVLST = prepareCall(false, Routines.PYBRSSP_SRV_LST.routine());
			}
			callableStatementBRSSRVLST.setInt(1, pageNumber); /* rows per page */
			callableStatementBRSSRVLST.setInt(2, rowsPerPage); /* page number*/
			callableStatementBRSSRVLST.setString(3,OrderBy);
			callableStatementBRSSRVLST.setString(4,wallet.getCodiceSocieta());
			callableStatementBRSSRVLST.setString(5,wallet.getCuteCute());
			callableStatementBRSSRVLST.setString(6,wallet.getChiaveEnte());
			callableStatementBRSSRVLST.setString(7,wallet.getCodiceFiscaleGenitore());
			callableStatementBRSSRVLST.setString(8,wallet.getIdWallet());
			callableStatementBRSSRVLST.setString(9,"");
			callableStatementBRSSRVLST.setString(10,"");
			callableStatementBRSSRVLST.setString(11, "");
			callableStatementBRSSRVLST.setString(12, "");
			callableStatementBRSSRVLST.setString(13, "");
			/* we register row start */
			callableStatementBRSSRVLST.registerOutParameter(14, Types.INTEGER);
			/* we register row end */
			callableStatementBRSSRVLST.registerOutParameter(15, Types.INTEGER);
			/* we register total rows */
			callableStatementBRSSRVLST.registerOutParameter(16, Types.INTEGER);
			/* we register total pages */
			callableStatementBRSSRVLST.registerOutParameter(17, Types.SMALLINT);
			callableStatementBRSSRVLST.execute();
			res = callableStatementBRSSRVLST.getResultSet();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (HelperException e) {
			e.printStackTrace();
		}
		return res;
	}

	public ResultSet walletServListEmailBatchAnno(Wallet wallet,int rowsPerPage, int pageNumber, String OrderBy)	throws DaoException {
		//CallableStatement callableStatement=null;
		//Connection connection = null;//LP 20240913 - PGNTCORE-24
		ResultSet res = null;
		try {
			//connection = getConnection(); //LP 20240913 - PGNTCORE-24
			if (callableStatementServAnno == null) {
				//inizio LP 20240913 - PGNTCORE-24
				//callableStatementServAnno = Helper.prepareCall(connection, getSchema(), Routines.PYBRSSP_SRV_LST_ANNO.routine());
				callableStatementServAnno = prepareCall(false, Routines.PYBRSSP_SRV_LST_ANNO.routine());
				//fine LP 20240913 - PGNTCORE-24
			}
			callableStatementServAnno.setInt(1, pageNumber);                          /* rows per page */
			callableStatementServAnno.setInt(2, rowsPerPage);                        /* page number*/
			callableStatementServAnno.setString(3,OrderBy);
			callableStatementServAnno.setString(4,wallet.getCodiceSocieta());
			callableStatementServAnno.setString(5,wallet.getCuteCute());
			callableStatementServAnno.setString(6,wallet.getChiaveEnte());
			callableStatementServAnno.setString(7,wallet.getCodiceFiscaleGenitore());
			callableStatementServAnno.setString(8,wallet.getIdWallet());
			callableStatementServAnno.setString(9,"");
			callableStatementServAnno.setString(10,"");
			callableStatementServAnno.setString(11, "");
			callableStatementServAnno.setString(12, "");
			callableStatementServAnno.setString(13, "");
			/* we register row start */
			callableStatementServAnno.registerOutParameter(14, Types.INTEGER);
			/* we register row end */
			callableStatementServAnno.registerOutParameter(15, Types.INTEGER);
			/* we register total rows */
			callableStatementServAnno.registerOutParameter(16, Types.INTEGER);
			/* we register total pages */
			callableStatementServAnno.registerOutParameter(17, Types.SMALLINT);

			callableStatementServAnno.execute();
			res = callableStatementServAnno.getResultSet();

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (HelperException e) {
			e.printStackTrace();
		}
		return res;
	}

	//inizio LP PG21XX04 Leak
	//Nota. Metodo usato solo da procedure batch
	//fine LP PG21XX04 Leak
	public ResultSet walletOneriNonPagati(Wallet wallet,int rowsPerPage, int pageNumber, String OrderBy)	throws DaoException {
		//CallableStatement callableStatement=null;
		Connection connection = null;
		ResultSet res = null;
		try {
			connection = getConnection();
			if (callableStatementOnere == null) {
				//inizio LP 20240913 - PGNTCORE-24
				//callableStatementOnere = Helper.prepareCall(connection, getSchema(), Routines.PYSOLSP_ONE_NOP.routine());
				callableStatementOnere = prepareCall(Routines.PYSOLSP_ONE_NOP.routine());
				//fine LP 20240913 - PGNTCORE-24
			}
			callableStatementOnere.setString(1,wallet.getIdWallet());
			callableStatementOnere.execute();
			res = callableStatementOnere.getResultSet();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (HelperException e) {
			e.printStackTrace();
		}
		return res;
	}

	//inizio LP PG21XX04 Leak
	//Nota. Il metodo era usato solo da procedure batch
	//fine LP PG21XX04 Leak
	public boolean verRepCaricato(FattureRep fattureRep) throws DaoException {
		CallableStatement callableStatement=null;
		Connection connection = null;
		Integer ret = 0;
		boolean presente = true; 
		try {
			connection = getConnection();
			//inizio LP 20240913 - PGNTCORE-24
			//callableStatement = Helper.prepareCall(connection, getSchema(), Routines.PYBRSSP_REP_VER_CAR.routine());
			callableStatement = prepareCall(Routines.PYBRSSP_REP_VER_CAR.routine());
			//fine LP 20240913 - PGNTCORE-24
			callableStatement.setString(1, fattureRep.getIdWallet());  
			callableStatement.setString(2, fattureRep.getNumeroFattura());

			callableStatement.setDate(3, new java.sql.Date( fattureRep.getPeriodoCompetenza().getTimeInMillis() ) );
			callableStatement.registerOutParameter(4, Types.SMALLINT);
			callableStatement.execute();
			ret = callableStatement.getInt(4);
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
			//DAOHelper.closeIgnoringException(callableStatement);
			if (callableStatement != null) {
				try {
					callableStatement.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			//fine LP PG21XX04 Leak
		}	
		if (ret == 1) {
			presente = true;
		} else {
			presente = false;
		}
		return presente;
	}

	public WalletPageList  walletRicaricheList(Wallet wallet,int rowsPerPage, int pageNumber, String OrderBy)	throws DaoException {
		CallableStatement callableStatement=null;

		Connection connection = null;
		ResultSet data = null;
		ResultSet dataRiep = null;
		PageInfo pageInfo = null; 
		WalletPageList walletPageList = null;
		String[] brsLst  = new String[2];
		try {
			connection = getConnection();
			//inizio LP 20240913 - PGNTCORE-24
			//callableStatement = Helper.prepareCall(connection, getSchema(), Routines.PYBRSSP_PGB_ADB_LST.routine());
			callableStatement = prepareCall(Routines.PYBRSSP_PGB_ADB_LST.routine());
			//fine LP 20240913 - PGNTCORE-24
			callableStatement.setInt(1, pageNumber);                          /* rows per page */
			callableStatement.setInt(2, rowsPerPage);                        /* page number*/
			callableStatement.setString(3,OrderBy);
			callableStatement.setString(4,wallet.getCodiceSocieta());
			callableStatement.setString(5,wallet.getCuteCute());
			callableStatement.setString(6,wallet.getChiaveEnte());
			callableStatement.setString(7,wallet.getCodiceFiscaleGenitore());
			callableStatement.setString(8,wallet.getIdWallet());
			callableStatement.setString(9,wallet.getDenominazioneGenitore());
			callableStatement.setString(10, (String)wallet.getAttribute("PGBDATA_PAGAMENTO_DA"));
			callableStatement.setString(11, (String)wallet.getAttribute("PGBDATA_PAGAMENTO_A"));

			/* we register row start */
			callableStatement.registerOutParameter(12, Types.INTEGER);
			/* we register row end */
			callableStatement.registerOutParameter(13, Types.INTEGER);
			/* we register total rows */
			callableStatement.registerOutParameter(14, Types.INTEGER);
			/* we register total pages */
			callableStatement.registerOutParameter(15, Types.SMALLINT);

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

				brsLst[0] = getWebRowSetXml();
				//				walletPageList = new WalletPageList(pageInfo, "00","",brsLst[0]);
				//				return walletPageList;
			}

			if(callableStatement.getMoreResults()) {
				dataRiep = callableStatement.getResultSet();

				loadWebRowSet(dataRiep);
				brsLst[1] = getWebRowSetXml();

			}
			walletPageList = new WalletPageList(pageInfo, "00","",brsLst);
			return walletPageList;

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
			if (dataRiep != null) {
				try {
					dataRiep.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
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

	public ArrayList<String>  arrayRicaricheList(Wallet wallet,int rowsPerPage, int pageNumber, String OrderBy)	throws DaoException {
		CallableStatement callableStatement=null;

		Connection connection = null;
		ResultSet data = null;
		ResultSet dataRiep = null;
		PageInfo pageInfo = null; 
		WalletPageList walletPageList = null;
		String[] brsLst  = new String[2];
		String[] brsLst2  = new String[2];
		ArrayList<String> arrayApp  = new ArrayList<String>();
		try {
			connection = getConnection();
			//inizio LP 20240913 - PGNTCORE-24
			//callableStatement = Helper.prepareCall(connection, getSchema(), Routines.PYBRSSP_PGB_ADB_LST.routine());
			callableStatement = prepareCall(Routines.PYBRSSP_PGB_ADB_LST.routine());
			//fine LP 20240913 - PGNTCORE-24
			callableStatement.setInt(1, pageNumber);                          /* rows per page */
			callableStatement.setInt(2, rowsPerPage);                        /* page number*/
			callableStatement.setString(3,OrderBy);
			callableStatement.setString(4,wallet.getCodiceSocieta());
			callableStatement.setString(5,wallet.getCuteCute());
			callableStatement.setString(6,wallet.getChiaveEnte());
			callableStatement.setString(7,wallet.getCodiceFiscaleGenitore());
			callableStatement.setString(8,wallet.getIdWallet());
			callableStatement.setString(9,wallet.getDenominazioneGenitore());
			callableStatement.setString(10, (String)wallet.getAttribute("PGBDATA_PAGAMENTO_DA"));
			callableStatement.setString(11, (String)wallet.getAttribute("PGBDATA_PAGAMENTO_A"));

			/* we register row start */
			callableStatement.registerOutParameter(12, Types.INTEGER);
			/* we register row end */
			callableStatement.registerOutParameter(13, Types.INTEGER);
			/* we register total rows */
			callableStatement.registerOutParameter(14, Types.INTEGER);
			/* we register total pages */
			callableStatement.registerOutParameter(15, Types.SMALLINT);

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

				while (data.next()){
					arrayApp.add(data.getString(1));
					arrayApp.add(data.getString(2));
					arrayApp.add(data.getString(3));
					arrayApp.add(data.getString(4));
					arrayApp.add(data.getString(5));
					arrayApp.add(data.getString(6));
					arrayApp.add(data.getString(7));
					arrayApp.add(data.getString(8));
					arrayApp.add(data.getString(9));
					arrayApp.add(data.getString(10));
				}

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
		return arrayApp;
	} 

	public ArrayList<String>  arraySollecitiList(Wallet wallet,int rowsPerPage, int pageNumber, String OrderBy)	throws DaoException {
		CallableStatement callableStatement=null;
		Connection connection = null;
		ResultSet data = null;
		WalletPageList walletPageList = null;
		PageInfo pageInfo = null; 
		String[] brsLst  = new String[2];
		String[] brsLst2  = new String[2];
		ArrayList<String> arrayApp  = new ArrayList<String>();

		try {
			connection = getConnection();
			//inizio LP 20240913 - PGNTCORE-24
			//callableStatement = Helper.prepareCall(connection, getSchema(),Routines.PYBRSSP_SOL_LSTSOL.routine());
			callableStatement = prepareCall(Routines.PYBRSSP_SOL_LSTSOL.routine());
			//fine LP 20240913 - PGNTCORE-24
			callableStatement.setInt(1, pageNumber);                          /* rows per page */
			callableStatement.setInt(2, rowsPerPage);                        /* page number*/
			callableStatement.setString(3,OrderBy);
			callableStatement.setString(4,wallet.getIdWallet());
			callableStatement.setString(5,wallet.getCodiceFiscaleGenitore());
			callableStatement.setString(6,wallet.getCuteCute());
			callableStatement.setString(7,wallet.getChiaveEnte());
			callableStatement.setString(8,wallet.getCodiceSocieta());
			callableStatement.setString(9, (String)wallet.getAttribute("PGBDATA_PAGAMENTO_DA"));
			callableStatement.setString(10, (String)wallet.getAttribute("PGBDATA_PAGAMENTO_A"));
			//
			callableStatement.setString(11, (String)wallet.getAttribute("PGBDATADIS_PAGAMENTO_DA"));
			callableStatement.setString(12, (String)wallet.getAttribute("PGBDATADIS_PAGAMENTO_A"));
			//
			callableStatement.setString(13,wallet.getDenominazioneGenitore());
			callableStatement.setString(14,wallet.getAttribute("caricoEnte")!=null?(String)wallet.getAttribute("caricoEnte"):"");
			callableStatement.setString(15,wallet.getAttribute("discaricoAttivo")!=null?(String)wallet.getAttribute("discaricoAttivo"):"");
			/* we register row start */
			callableStatement.registerOutParameter(16, Types.INTEGER);
			/* we register row end */
			callableStatement.registerOutParameter(17, Types.INTEGER);
			/* we register total rows */
			callableStatement.registerOutParameter(18, Types.INTEGER);
			/* we register total pages */
			callableStatement.registerOutParameter(19, Types.SMALLINT);

			/* we execute procedure */

			if(callableStatement.execute())	{
				pageInfo = new PageInfo();
				pageInfo.setPageNumber(pageNumber);
				pageInfo.setRowsPerPage(rowsPerPage);
				pageInfo.setFirstRow(callableStatement.getInt(16));
				pageInfo.setLastRow(callableStatement.getInt(17));
				pageInfo.setNumRows(callableStatement.getInt(18));
				pageInfo.setNumPages(callableStatement.getInt(19));
				data = callableStatement.getResultSet();
				if(data.next()){
					arrayApp.add(data.getString(1));
					arrayApp.add(data.getString(2));
					arrayApp.add(data.getString(3));
					arrayApp.add(data.getString(4));
				}
				data.close();
			}


			return arrayApp;

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
		return arrayApp;
	}


	public String  getBollettino(String societa, String cutecute,String ente,String idWallet, Double importod, String tipoBoll , String tipoGener,String utente) {
		String bollettino = "";
		String nuovo  ="";
		CallableStatement callableStatement=null;

		Connection connection = null;
		//inizio LP PG21XX04 Leak
		//connection = getConnection();
		//fine LP PG21XX04 Leak

		try {
			//inizio LP PG21XX04 Leak
			connection = getConnection();
			//fine LP PG21XX04 Leak
			//inizio LP 20240913 - PGNTCORE-24
			//callableStatement = Helper.prepareCall(connection, getSchema(), Routines.PYBRSSP_NEWIDBRL.routine());
			callableStatement = prepareCall(Routines.PYBRSSP_NEWIDBRL.routine());
			//fine LP 20240913 - PGNTCORE-24
			callableStatement.setString(1, societa);
			callableStatement.setString(2, cutecute);                        /* page number*/
			callableStatement.setString(3,ente);
			callableStatement.setString(4,idWallet);
			callableStatement.setBigDecimal(5,new BigDecimal(importod));
			callableStatement.setString(6,tipoBoll);
			callableStatement.setString(7,tipoGener);
			callableStatement.setString(8,utente);
			callableStatement.registerOutParameter(9, Types.CHAR);
			callableStatement.registerOutParameter(10, Types.VARCHAR);
			callableStatement.execute();
			nuovo = callableStatement.getString(9);
			bollettino = callableStatement.getString(10);

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (HelperException e) {
			e.printStackTrace();
		} catch (Throwable e) {
			System.out.println(e);
			e.printStackTrace();
		}
		//inizio LP PG21XX04 Leak
		finally {
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
		}
		//fine LP PG21XX04 Leak
		return bollettino;
	}

	public String  getBollettinoBatch(String societa, String cutecute,String ente,String idWallet, Double importod, String tipoBoll, String tipoGener,String utente) {
		String bollettino = "";
		String nuovo  ="";
		//CallableStatement callableStatement=null;
		try {
			if (callableStatementBollettino == null) {
				//inizio LP 20240913 - PGNTCORE-24
				//callableStatementBollettino = Helper.prepareCall(getConnection(), getSchema(), Routines.PYBRSSP_NEWIDBRL.routine());
				callableStatementBollettino = prepareCall(false, Routines.PYBRSSP_NEWIDBRL.routine());
				//fine LP 20240913 - PGNTCORE-24
			}
			callableStatementBollettino.setString(1, societa);
			callableStatementBollettino.setString(2, cutecute);  /* page number*/
			callableStatementBollettino.setString(3,ente);
			callableStatementBollettino.setString(4,idWallet);
			callableStatementBollettino.setBigDecimal(5,new BigDecimal(importod));
			callableStatementBollettino.setString(6,tipoBoll);
			callableStatementBollettino.setString(7,tipoGener);
			callableStatementBollettino.setString(8,utente);
			callableStatementBollettino.registerOutParameter(9, Types.CHAR);
			callableStatementBollettino.registerOutParameter(10, Types.VARCHAR);
			callableStatementBollettino.execute();
			nuovo = callableStatementBollettino.getString(9);
			bollettino = callableStatementBollettino.getString(10);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (HelperException e) {
			e.printStackTrace();
		} catch (Throwable e) {
			System.out.println(e);
			e.printStackTrace();
		}
		return bollettino;
	}

	public WalletPageList walletSollecitoList(Wallet wallet,int rowsPerPage,
			int pageNumber, String OrderBy) throws DaoException {
		CallableStatement callableStatement=null;

		Connection connection = null;
		ResultSet data = null;

		PageInfo pageInfo = null; 
		WalletPageList walletPageList = null;
		String[] brsLst  = new String[2];

		try {
			connection = getConnection();
			//inizio LP 20240913 - PGNTCORE-24
			//callableStatement = Helper.prepareCall(connection, getSchema(),Routines.PYBRSSP_SOL_LSTSOL.routine());
			callableStatement = prepareCall(Routines.PYBRSSP_SOL_LSTSOL.routine());
			//fine LP 20240913 - PGNTCORE-24
			callableStatement.setInt(1, pageNumber);                          /* rows per page */
			callableStatement.setInt(2, rowsPerPage);                        /* page number*/
			callableStatement.setString(3,OrderBy);
			callableStatement.setString(4,wallet.getIdWallet());
			callableStatement.setString(5,wallet.getCodiceFiscaleGenitore());
			callableStatement.setString(6,wallet.getCuteCute());
			callableStatement.setString(7,wallet.getChiaveEnte());
			callableStatement.setString(8,wallet.getCodiceSocieta());
			callableStatement.setString(9, (String)wallet.getAttribute("PGBDATA_PAGAMENTO_DA"));
			callableStatement.setString(10, (String)wallet.getAttribute("PGBDATA_PAGAMENTO_A"));
			//wallet.setAttribute("PGBDATADIS_PAGAMENTO_A" , dataDisA);
			callableStatement.setString(11, (String)wallet.getAttribute("PGBDATADIS_PAGAMENTO_DA"));
			callableStatement.setString(12, (String)wallet.getAttribute("PGBDATADIS_PAGAMENTO_A"));
			////
			callableStatement.setString(13,wallet.getDenominazioneGenitore());
			callableStatement.setString(14,(String)wallet.getAttribute("caricoEnte"));
			callableStatement.setString(15,(String)wallet.getAttribute("discaricoAttivo"));
			/* we register row start */
			callableStatement.registerOutParameter(16, Types.INTEGER);
			/* we register row end */
			callableStatement.registerOutParameter(17, Types.INTEGER);
			/* we register total rows */
			callableStatement.registerOutParameter(18, Types.INTEGER);
			/* we register total pages */
			callableStatement.registerOutParameter(19, Types.SMALLINT);

			/* we execute procedure */

			if(callableStatement.execute())	{
				pageInfo = new PageInfo();
				pageInfo.setPageNumber(pageNumber);
				pageInfo.setRowsPerPage(rowsPerPage);
				pageInfo.setFirstRow(callableStatement.getInt(16));
				pageInfo.setLastRow(callableStatement.getInt(17));
				pageInfo.setNumRows(callableStatement.getInt(18));
				pageInfo.setNumPages(callableStatement.getInt(19));

				data = callableStatement.getResultSet();
				loadWebRowSet(data);

				brsLst[0] = getWebRowSetXml();

				if(callableStatement.getMoreResults()){
					//inizio LP PG21XX04 Leak
					if (data != null) {
						try {
							data.close();
						} catch (SQLException e) {
							e.printStackTrace();
						}
					}
					//fine LP PG21XX04 Leak
					data=callableStatement.getResultSet();
					loadWebRowSet(data);
					brsLst[1] = getWebRowSetXml();
				}

				//				walletPageList = new WalletPageList(pageInfo, "00","",brsLst[0]);
				//				return walletPageList;
			}
			walletPageList = new WalletPageList(pageInfo, "00","",brsLst);
			return walletPageList;

		} catch (SQLException e) {
			e.printStackTrace();
			walletPageList = new WalletPageList(pageInfo, "01","Sql-Exception","");
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
			walletPageList = new WalletPageList(pageInfo, "01","IllegalArgument-Exception","");
		} catch (HelperException e) {
			e.printStackTrace();
			walletPageList = new WalletPageList(pageInfo, "01","Helper-Exception","");
		} finally {
			//inizio LP PG21XX04 Leak
			//DAOHelper.closeIgnoringException(callableStatement);
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

	//inizio LP PG21XX04 Leak
	//Nota. Metodo usato solo da procedure batch
	//fine LP PG21XX04 Leak
	public ArrayList<Wallet> listNoRid(String cuteCute) throws DaoException {
		CallableStatement callableStatement=null;
		ArrayList<Wallet> result = new ArrayList<Wallet>();
		//inizio LP PG21XX04 Leak
		ResultSet data = null;
		//fine LP PG21XX04 Leak
		Connection connection = null; 
		Wallet wallet = new Wallet();
		try {
			connection = getConnection();
			//inizio LP 20240828 - PGNTCORE-24/PAGONET-604
			//callableStatement = Helper.prepareCall(connection, getSchema(), Routines.PYBRSSP_SEL_NORID.routine());
			//Nota. Mi fido del vecchio me se la procedura e' usata solo da batch flagUpdateAutocommit ==> false
			callableStatement = prepareCall(false, Routines.PYBRSSP_SEL_NORID.routine());
			//inizio LP 20240828 - PGNTCORE-24/PAGONET-604
			callableStatement.setString(1, cuteCute);
			callableStatement.execute();
			if (callableStatement.execute()) {
				//inizio LP PG21XX04 Leak
				//ResultSet data = callableStatement.getResultSet();
				data = callableStatement.getResultSet();
				//fine LP PG21XX04 Leak
				while (data.next()) {
					wallet = new Wallet();
					wallet.setIdWallet(data.getString(1));
					wallet.setCodiceFiscaleGenitore(data.getString(2));
					result.add(wallet);
				}
			}
		} catch (SQLException e) {
			System.out.println(e);
		} catch (IllegalArgumentException e) {
			System.out.println(e);
		} catch (HelperException e) {
			System.out.println(e);
		} finally {
			//DAOHelper.closeIgnoringException(connection);
			//inizio LP PG21XX04 Leak
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
			//fine LP PG21XX04 Leak
		}
		return result;
	}
	
	//PG180180_001 GG 2052019 - inizio
	public CertificazioneBonusNido getCertificazioneBonusNido(String idWallet, String anno, String mese, String chiavePresenza) throws  DaoException {
		CertificazioneBonusNido certBonusNido = null;
		CallableStatement callableStatement=null;
		Connection connection = null;
		//inizio LP PG21XX04 Leak
		ResultSet data = null;
		//fine LP PG21XX04 Leak
		try {
			connection = getConnection();
			//inizio LP 20240913 - PGNTCORE-24
			//callableStatement = Helper.prepareCall(connection, getSchema(), Routines.PYCBNSP_SEL.routine());
			callableStatement = prepareCall(Routines.PYCBNSP_SEL.routine());
			//fine LP 20240913 - PGNTCORE-24
			callableStatement.setString(1, idWallet);
			callableStatement.setString(2, anno);
			callableStatement.setString(3, mese);
			callableStatement.setString(4, chiavePresenza);
			callableStatement.registerOutParameter(5, Types.INTEGER);
			if (callableStatement.execute()) {
				//inizio LP PG21XX04 Leak
				//ResultSet data = callableStatement.getResultSet();
				data = callableStatement.getResultSet();
				//fine LP PG21XX04 Leak
				if (data.next())
					certBonusNido = new CertificazioneBonusNido(data);
				else 
					certBonusNido = new CertificazioneBonusNido();
				certBonusNido.setAttribute("maxProgressivo", callableStatement.getInt(5));
			}
		} catch (Exception e) {
			throw new DaoException(e);
		} finally {
			//inizio LP PG21XX04 Leak
			//DAOHelper.closeIgnoringException(callableStatement);
			////DAOHelper.closeIgnoringException(connection);	//non devo chiudere la connessione perchè serve per la chiamata successiva
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
		return certBonusNido;
		
	}
	
	public void insertCertificazioneBonusNido(CertificazioneBonusNido certBonusNido) throws  DaoException {
		CallableStatement callableStatement=null;
		Connection connection = null; 
		try { 
			connection = getConnection();
			//inizio LP 20240913 - PGNTCORE-24
			//callableStatement = Helper.prepareCall(connection, getSchema(), Routines.PYCBNSP_INS.routine());
			callableStatement = prepareCall(Routines.PYCBNSP_INS.routine());
			//fine LP 20240913 - PGNTCORE-24
			callableStatement.setString(1, certBonusNido.getNumeroProtocollo());
			callableStatement.setString(2, certBonusNido.getIdWallet());
			callableStatement.setString(3, certBonusNido.getAnno());
			callableStatement.setString(4, certBonusNido.getMese());
			callableStatement.setInt(5, certBonusNido.getProgressivo());
			callableStatement.setString(6, certBonusNido.getChiavePresenza());
			callableStatement.setString(7, certBonusNido.getData());
			callableStatement.setString(8, certBonusNido.getDenomGenitore());
			callableStatement.setString(9, certBonusNido.getCodFiscGenitore());
			callableStatement.setString(10, certBonusNido.getAnnoScolastico());
			callableStatement.setString(11, certBonusNido.getDenomFiglio());
			callableStatement.setString(12, certBonusNido.getCodFiscFiglio());
			callableStatement.setString(13, certBonusNido.getCodiceScuola());
			callableStatement.setString(14, certBonusNido.getDenomScuola());
			callableStatement.setString(15, certBonusNido.getIndirizzoScuola());
			callableStatement.setBigDecimal(16, certBonusNido.getImporto());
			callableStatement.setString(17, certBonusNido.getDenomDirigente());
			callableStatement.setString(18, certBonusNido.getFlagAttivo()==true?"Y":"N");	//flag attivo
			callableStatement.setString(19, certBonusNido.getPathFileCertificazione());
			if(certBonusNido.getFileCertificazione()!= null) {
				ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(certBonusNido.getFileCertificazione());
				callableStatement.setBinaryStream(20, byteArrayInputStream, certBonusNido.getFileCertificazione().length);
			}
			else
				callableStatement.setBinaryStream(20, null, 0);
			
			callableStatement.execute();
		} catch (SQLException e) {
			throw new DaoException(e);
		} catch (IllegalArgumentException e) {
			throw new DaoException(e);
		} catch (HelperException e) {
			throw new DaoException(e);
		} finally {
			//inizio LP PG21XX04 Leak
			//DAOHelper.closeIgnoringException(connection);
			//DAOHelper.closeIgnoringException(callableStatement);
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
		
	}
	//PG180180_001 GG 2052019 - fine
	
	public String[] walletListCsvBatchDownload(Wallet wallet,String tipoServizio,String tipoSollecito,String flagrendicontato,String presenzaOneri, String OrderBy)	throws DaoException {
		CallableStatement callableStatement=null;
		Connection connection = null;
		String[] brsLst = new String[2];
		ResultSet data = null;
		try {
			connection = getConnection();
			//inizio LP 20240913 - PGNTCORE-24
			//callableStatement = Helper.prepareCall(connection, getSchema(), Routines.PYBRSSP_LST_CSV.routine());
			callableStatement = prepareCall(Routines.PYBRSSP_LST_CSV.routine());
			//fine LP 20240913 - PGNTCORE-24
			callableStatement.setString(1,OrderBy);
			callableStatement.setString(2,wallet.getCodiceSocieta());
			callableStatement.setString(3,wallet.getCuteCute());
			callableStatement.setString(4,wallet.getChiaveEnte());
			callableStatement.setString(5,wallet.getIdWallet());
			callableStatement.setString(6,wallet.getCodiceAnagraficaGenitore());
			callableStatement.setString(7,wallet.getCodiceFiscaleGenitore());
			callableStatement.setString(8, (String)wallet.getAttribute(WalletDAO.PERIDOCARICO_DA));
			callableStatement.setString(9, (String)wallet.getAttribute(WalletDAO.PERIDOCARICO_A));
			callableStatement.setString(10,tipoSollecito);
			callableStatement.setString(11,presenzaOneri);
			callableStatement.setString(12,tipoServizio);
			callableStatement.setString(13,flagrendicontato);

			if ( wallet.isFlagEsclusioneSMSCortesia() ==null ) {
				callableStatement.setString(14, "");
			} else {
				callableStatement.setString(14, wallet.isFlagEsclusioneSMSCortesia()?"Y":"N");
			}

			if ( wallet.isFlagEsclusioneSMSSollecito() ==null ) {
				callableStatement.setString(15, "");
			} else {
				callableStatement.setString(15, wallet.isFlagEsclusioneSMSSollecito()?"Y":"N");
			}

			if ( wallet.isFlagEsclusioneSollecitoCartaceo() ==null ) {
				callableStatement.setString(16, "");
			} else {
				callableStatement.setString(16, wallet.isFlagEsclusioneSollecitoCartaceo()?"Y":"N");
			}
			if ( wallet.isFlagEsclusioneEvoluzioneIntimazione() ==null ) {
				callableStatement.setString(17, "");
			} else {
				callableStatement.setString(17, wallet.isFlagEsclusioneEvoluzioneIntimazione()?"Y":"N");
			}

			/* we execute procedure */
			
			if(callableStatement.execute())	{
				data = callableStatement.getResultSet();
				loadWebRowSet(data);
				brsLst[0] = getWebRowSetXml();
				
				if(callableStatement.getMoreResults()) {
					//inizio LP PG21XX04 Leak
					if (data != null) {
						try {
							data.close();
						} catch (SQLException e) {
							e.printStackTrace();
						}
					}
					//fine LP PG21XX04 Leak
					data = callableStatement.getResultSet();
					loadWebRowSet(data);
					brsLst[1] = getWebRowSetXml();
				}
					
			}


		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (HelperException e) {
			e.printStackTrace();
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
			//fine LP PG21XX04 Leak
		}
		return brsLst;
	}

	//inizio LP 20240913 - PAGONET-604
    public void closeCallableStatementS()  {
	    if(callableStatementServAnno != null) {
			try {
				callableStatementServAnno.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			callableStatementServAnno = null;
	    }
	    if(callableStatementOnere != null) {
			try {
				callableStatementOnere.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			callableStatementOnere = null;
	    }
	    if(callableStatementBollettino != null) {
			try {
				callableStatementBollettino.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			callableStatementBollettino = null;
	    }
	    if(callableStatementBRSINS != null) {
			try {
				callableStatementBRSINS.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			callableStatementBRSINS = null;
	    }
	    if(callableStatementBRSUP != null) {
			try {
				callableStatementBRSUP.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			callableStatementBRSUP = null;
	    }
	    if(callableStatementBRSBATCH != null) {
			try {
				callableStatementBRSBATCH.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			callableStatementBRSBATCH = null;
	    }
	    if(callableStatementEsitiSMS != null) {
			try {
				callableStatementEsitiSMS.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			callableStatementEsitiSMS = null;
	    }
		if(callableStatementSOLDELSOL != null) {
			try {
				callableStatementSOLDELSOL.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			callableStatementSOLDELSOL = null;
		}
		if(callableStatementBRSLSTSMSEMAIL != null) {
			try {
				callableStatementBRSLSTSMSEMAIL.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			callableStatementBRSLSTSMSEMAIL = null;
		}
		if(callableStatementSOLUPDLSTW != null) {
			try {
				callableStatementSOLUPDLSTW.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			callableStatementSOLUPDLSTW = null;
		}
		if(callableStatementBRLSPLST != null) {
			try {
				callableStatementBRLSPLST.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			callableStatementBRLSPLST = null;
		}
		if(callableStatementRCPSEL == null) {
			try {
				callableStatementRCPSEL.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			callableStatementRCPSEL = null;
		}
		if(callableStatementBRSSRVLST == null) {
			try {
				callableStatementBRSSRVLST.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			callableStatementBRSSRVLST = null;
		}
		//inizio LP 20240913 - PAGONET-24/PGNTWPB-3
		if(callableStatementBRSSEL == null) {
			try {
				callableStatementBRSSEL.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			callableStatementBRSSEL = null;
		}
		//fine LP 20240913 - PAGONET-24/PGNTWPB-3
    }
    //fine LP 20240913 - PAGONET-604
}
