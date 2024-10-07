package com.seda.payer.core.wallet.dao;

import java.util.ArrayList;

import com.seda.payer.core.exception.DaoException;
import com.seda.payer.core.wallet.bean.PagamentoBorsellino;
import com.seda.payer.core.wallet.bean.Servizio;
import com.seda.payer.core.wallet.bean.Wallet;


public interface PagamentoBorsellinoDAO  {
	
	public final static String DATA_PAGAMENTO= "DATAPAG";
	public final static String DESCRIZIONE= "DESCRIZIONE";
	public final static String FLAG_COMPRESSIONE= "FLAGCOMP";
	public final static String MESE= "MESE";
	public final static String ANNO= "ANNO";
	public final static String IMPORTOSTR= "IMPORTOSTR";
	public final static String SELECT_XML= "SELECT_XML";
	public final static String FLAG_SERVIZI_A_CONSUMO= "FLAG_SERVIZI_A_CONSUMO";
	public final static String FLAG_APPLICATO= "FLAG_APPLICATO";
	
	
	public ArrayList<PagamentoBorsellino> listPagamenti(PagamentoBorsellino pagamento) throws  DaoException;
	public ArrayList<PagamentoBorsellino> listPagamentiTot(PagamentoBorsellino pagamento) throws  DaoException;
	public ArrayList<PagamentoBorsellino> pagamentoListDett(PagamentoBorsellino pagamento,String anno,String mese)throws  DaoException;
	public ArrayList<PagamentoBorsellino> pagamentoListDettTot(PagamentoBorsellino pagamento,String anno,String mese)throws  DaoException;
//	public ArrayList<PresenzeGiornaliere> listAnnoScolastico(PresenzeGiornaliere presenzeGiornaliere) throws  DaoException;
	public String listDdlAnno(Wallet wallet) throws  DaoException; 
	public ArrayList<String> listDdlAnnoObject(Wallet wallet) throws  DaoException;
	public boolean listPagamentiPdf(String wallet, String ente, String cutecute, String societa, String annoString , String codAnagGen, String  codiceFiscaleGenitore, Servizio servizio) throws  DaoException; 
	public PagamentoBorsellino select_acq (PagamentoBorsellino pagamento, String flagGestioneMercati)throws  DaoException; //Giulia 5102013
	public PagamentoBorsellino select_appl (PagamentoBorsellino pagamento)throws  DaoException; //PG180040 GG 18062018
	public void insertPgHost(PagamentoBorsellino pagamento, ArrayList<PagamentoBorsellino> pagaHost)throws  DaoException; 
	public void deletePgHost(PagamentoBorsellino pagamento)throws  DaoException;
}
