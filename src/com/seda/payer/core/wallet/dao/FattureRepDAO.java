package com.seda.payer.core.wallet.dao;

import java.util.Calendar;

import com.seda.payer.core.exception.DaoException;
import com.seda.payer.core.wallet.bean.FattureRep;
import com.seda.payer.core.wallet.bean.AnagraficaFiglioMense;

public interface FattureRepDAO {
	//public void commitInsertBatch() throws DaoException;

	public void openInsertBatch()	throws DaoException ;
	
	public void closeInsertBatch() throws DaoException;

	public void executeInsertBatch() throws DaoException;

	public void insertBatch(FattureRep fattureRep) throws DaoException ;
	
	public AnagraficaFiglioMense getDatiAnagraficaServizio( String idWallet,String codiceAnagraficaFiglio, String codiceFiscaleFiglio, Calendar periodoCompetenza) throws DaoException, Exception;
}
