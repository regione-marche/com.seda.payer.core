package com.seda.payer.core.wallet.dao;

import com.seda.payer.core.exception.DaoException;
import com.seda.payer.core.wallet.bean.AnagraficaSpedizioneREP;


/**
 * PG130100
 *
 */

public interface AnagraficaSpedizioneREPDAO {

	//public void commitInsertBatch() throws DaoException;

	public void openInsertBatch()	throws DaoException ;
	
	public void closeInsertBatch() throws DaoException;

	public void executeInsertBatch() throws DaoException;

	public void insertBatch(AnagraficaSpedizioneREP anagraficaSpedizioneREP) throws DaoException ;
	
}
