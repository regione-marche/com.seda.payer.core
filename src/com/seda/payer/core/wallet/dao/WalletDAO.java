package com.seda.payer.core.wallet.dao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.seda.data.page.Page;
import com.seda.payer.core.exception.DaoException;
import com.seda.payer.core.wallet.bean.CertificazioneBonusNido;
import com.seda.payer.core.wallet.bean.FattureRep;
import com.seda.payer.core.wallet.bean.Wallet;
import com.seda.payer.core.wallet.bean.WalletHomePageList;
import com.seda.payer.core.wallet.bean.WalletPageList;
	   
public interface WalletDAO extends Serializable { 
	public final static String BORSELLINO_NUMERO_SMS_CORTESIA= "NUMSMSCORTESIA";
	public final static String BORSELLINO_NUMERO_SMS_SOLLECITO= "NUMSMSSOLLECITO";
	public final static String BORSELLINO_NUMERO_EMAIL_SOLLECITO= "NUMEMAILSOLLECITO";
	public final static String BORSELLINO_NUMERO_INVIO_CARTACEO= "NUMINVIOCARTACEO";
	public final static String BORSELLINO_TOTALE_IMPORTO_ONERI= "TOTALEIMPORTOONERI";
	public final static String BORSELLINO_TOTALE_IMPORTO_ONERI_PAGATI= "TOTALEIMPORTOONERIPAGATI";
	public final static String SELECT_XML= "SELECT_XML";
	//attributi inseriti per campi in più presenti nelle ricerche
	public final static String PERIDOCARICO_DA = "PERIDOCARICO_DA";
	public final static String PERIDOCARICO_A = "PERIDOCARICO_A";
	 
	public final static String SMSCORTESIAINVIATI = "SMSCORTESIAINVIATI";
	public final static String SMSSOLLECITOINVIATI = "SMSSOLLECITOINVIATI";
	public final static String EMAILSOLLECITOINVIATE = "EMAILSOLLECITOINVIATE";
	public final static String SOLLECATICARTACEI = "SOLLECITICARTACEI";
	public final static String DATAULTIMOAGGIORNAMENTO = "DATAULTIMOAGGIORNAMENTO";
	public final static String DATAULTIMOSOLLECITO = "DATAULTIMOSOLLECITO";
	public final static String IMPORTOONERISTAMPACARICATI = "IMPORTOONERISTAMPACARICATI";
	public final static String IMPORTOONERISTAMPAPAGATI = "IMPORTOONERISTAMPAPAGATI";
	public final static String IMPORTOONERIRENDICONTATI = "IMPORTOONERIRENDICONTATI";
	public final static String IMPORTO_CARRELLO= "IMPORTOCARRELLO";
	public final static String IMPORTO_CARRELLO_DECIMALE= "IMPORTOCARRELLODECIMALE";
	public final static String FLAG_WELCOMEKIT= "FLAGWELCOMEKIT";
	public final static String FLAG_ESTRATTOCONTO= "ESTRATTOCONTO";
	public final static String FLAG_PRIMOACCESSO_MERCATI= "PRIMOACCESSOMERCATI";
	
	
	
	public final static String DENOMINAZIONECONTRIBUENTE = "DENOMINAZIONECONTRIBUENTE";
	
	
	public Wallet insertBatch(Wallet wallet) throws  DaoException;
	public Wallet select(Wallet wallet) throws  DaoException;
	public Wallet select(Wallet wallet,boolean closeConnection) throws  DaoException;
	public Wallet selectTail(boolean bFlagUpdateAutocommit, Wallet wallet,boolean closeConnection) throws  DaoException;
	public Wallet selectBatch(Wallet wallet) throws  DaoException;
	public Wallet selectBatchTail(boolean bFlagUpdateAutocommit, Wallet wallet) throws  DaoException;
	public Wallet select_anag(Wallet wallet)throws DaoException;
	public String select_id( Wallet wallet)throws DaoException;
	public void update(Wallet wallet) throws  DaoException;
	public void update(Wallet wallet,boolean closeConnection) throws  DaoException;
	public void updateTail(boolean bFlagUpdateAutocommit,Wallet wallet,boolean closeConnection) throws  DaoException;
	public Integer updateAnagrafica(Wallet wallet) throws  DaoException;	
	public WalletPageList walletList(Wallet wallet, String tipoServizio, String tipoSollecito,String flagrendicontato, String presenzaOneri,int rowsPerPage, int pageNumber,String OrderBy) throws  DaoException;
	public boolean verRepCaricato(FattureRep fattureRep)  throws  DaoException;  
	public StringBuffer walletListCsv(Wallet wallet, String tipoServizio, String tipoSollecito,String flagrendicontato, String presenzaOneri,int rowsPerPage, int pageNumber,String OrderBy) throws  DaoException;
	public StringBuffer walletListCsv2(Wallet wallet, String tipoServizio, String tipoSollecito,String flagrendicontato, String presenzaOneri,int rowsPerPage, int pageNumber,String OrderBy) throws  DaoException;
	public String[] walletListCsvBatchDownload(Wallet wallet, String tipoServizio, String tipoSollecito,String flagrendicontato, String presenzaOneri,String OrderBy) throws  DaoException;
	
	public String walletRiepilogo(Wallet wallet, String tipoServizio, String tipoSollecito,String flagrendicontato, String presenzaOneri) throws  DaoException;
	public WalletPageList walletServList(Wallet wallet,String tipoServizio,String tributo,String flagrendicontato, int rowsPerPage, int pageNumber, String OrderBy) throws  DaoException;
	
	public WalletPageList walletRicaricheList(Wallet wallet, int rowsPerPage, int pageNumber, String OrderBy) throws  DaoException;
	
	public WalletPageList walletSollecitoList(Wallet wallet, int rowsPerPage, int pageNumber, String OrderBy) throws  DaoException;
	
	 
	public ArrayList<String> arrayRicaricheList(Wallet wallet, int rowsPerPage, int pageNumber, String OrderBy) throws  DaoException;
	public ArrayList<String> arraySollecitiList(Wallet wallet, int rowsPerPage, int pageNumber, String OrderBy) throws  DaoException;
	  
	
	public StringBuffer walletServListCsv(Wallet wallet,String tipoServizio,String tributo,String flagrendicontato, int rowsPerPage, int pageNumber, String OrderBy) throws  DaoException;
	
	public StringBuffer walletRicaricheListCsv(Wallet wallet, int rowsPerPage, int pageNumber, String OrderBy) throws  DaoException;

	public WalletPageList walletListServ(Wallet wallet) throws  DaoException;
	public String walletServizioRiepilogo (Wallet wallet,String tipoServizio,String tributo,String flagrendicontato, int rowsPerPage, int pageNumber, String OrderBy) throws  DaoException;
	public WalletPageList ListaServiziWalletManager(Wallet wallet, String tipoServizio) throws  DaoException;
	public WalletPageList walletListAnagraficaContribuenti(Wallet walletint, int rowsPerPage, int pageNumber,String OrderBy) throws  DaoException;
	public Integer updateFlagEsclusione (Wallet wallet) throws DaoException;
	public String [] tipologiaServizioSel(String codSoc, String codUte, String chiaveEnt) throws DaoException;
	public String selectDetSrv(String idWallet,String codServ, String tributo, String amcar)  throws DaoException;
	
	public String listBollettini(String idWallet)  throws DaoException;
	public String listBollettiniForQuaryHost(String idWallet)  throws DaoException;
	public String listBollettiniForQuaryHostBatch(String idWallet)  throws DaoException;
	
	public String listPagamenti(Wallet wallet)  throws DaoException;
	public String listSolleciti(Wallet wallet,int prog)  throws DaoException;
	public List<String> getSollecitoAsList(Wallet wallet)throws DaoException;
	public String insertRowDiscarico(List<String> sollecitoAsList,int prog) throws DaoException;
	public String insertRowAnnullamento(List<String> sollecitoAsList,int prog) throws DaoException;
	
	public ArrayList<String> getAutorizzazione(String societa, String cutecute, String ente) throws DaoException;									
	public ArrayList<String> getAutorizzazioneBatch(String societa, String cutecute, String ente) throws DaoException;
	public StringBuffer walletListAnagraficaContribuentiCsv(Wallet walletint, int rowsPerPage, int pageNumber,String OrderBy) throws  DaoException;
	public String getBollettino(String societa, String cutecute,String ente,String idWallet, Double importod, String tipoBoll , String tipoGener,String utente);
	
	public ArrayList<Wallet> listNoRid(String cuteCute) throws  DaoException;
	
	//PG180180_001 GG 22052019 - inizio
	public CertificazioneBonusNido getCertificazioneBonusNido(String idWallet, String anno, String mese, String chiavePresenza) throws  DaoException;
	public void insertCertificazioneBonusNido(CertificazioneBonusNido certBonusNido) throws  DaoException;
	//PG180180_001 GG 22052019 - fine
	
}
