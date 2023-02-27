package com.seda.payer.core.wallet.dao;

import java.io.Serializable;
import java.util.ArrayList;

import com.seda.payer.core.exception.DaoException;
import com.seda.payer.core.wallet.bean.AnagraficaFiglioMense;
import com.seda.payer.core.wallet.bean.WalletPageList;

public interface AnagraficaFiglioMenseDAO  extends Serializable {
	
	public final static String ANAGRAFICA_DENOMINAZIONE_FIGLIO= "DENOMFIGLIO";
	public final static String ANAGRAFICA_DENOMINAZIONE_SCUOLA= "DENOMSCUOLA";
	public final static String ANAGRAFICA_DATA_VALIDITA= "DATAVALIDITA";
	public final static String ANAGRAFICA_DATA_CARICAMENTO= "DATACARICAMENTO";
	public final static String ANAGRAFICA_ANNO_SCOLASTICO= "ANNOSCOLASTICO";
	public final static String ANAGRAFICA_CODICE_UTENTE= "CODICEUTENTE";
	public final static String ANAGRAFICA_CODICE_SOCIETA= "CODICESOCIETA";
	public final static String ANAGRAFICA_CHIAVE_ENTE= "CHIAVEENTE";
	public final static String IMPORTOTARIFFA= "IMPORTOTARIFFA";
	public final static String IMPORTOISEE= "IMPORTOISEE";
	
	
	
	
	public AnagraficaFiglioMense insertBatch(AnagraficaFiglioMense anagraficaFiglioMense) throws  DaoException;

	public WalletPageList figliList(AnagraficaFiglioMense figlio) throws  DaoException;
	
	public WalletPageList presenzeList(AnagraficaFiglioMense figlio) throws  DaoException;
	
	public AnagraficaFiglioMense select (AnagraficaFiglioMense figlio)throws  DaoException;
	
	public  WalletPageList listAnnoScolastico(AnagraficaFiglioMense figlio) throws  DaoException;
	
	public WalletPageList presenzeListPerBorsellino(AnagraficaFiglioMense figlio) throws  DaoException;
	
	public ArrayList<AnagraficaFiglioMense> updateFlagSospensione(AnagraficaFiglioMense figlio) throws  DaoException;

}
