package com.seda.payer.core.wallet.dao;

import java.io.Serializable;

import com.seda.payer.core.exception.DaoException;
import com.seda.payer.core.wallet.bean.Avviso;
import com.seda.payer.core.wallet.bean.WalletPageList;

public interface GestioneAvvisiDAO extends Serializable {
	
	
	WalletPageList avvisiList(Avviso avviso,int rowsPerPage, int pageNumber,String OrderBy) throws  DaoException;
	int updateAvviso(Avviso avviso) throws  DaoException;
	Avviso selectAvviso(Avviso avviso) throws  DaoException;
    
}
