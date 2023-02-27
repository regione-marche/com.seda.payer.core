package com.seda.payer.core.wallet.dao;

import java.io.Serializable;

import com.seda.data.page.Page;
import com.seda.payer.core.exception.DaoException;
import com.seda.payer.core.wallet.bean.Scuola;
	   
public interface ScuolaDAO extends Serializable {
							
	public String insertBatch(Scuola scuola) throws  DaoException;
//	public Wallet select(Scuola scuola) throws  DaoException;
//	public Page scuolaList(Scuola scuola,int rowsPerPage, int pageNumber,String OrderBy) throws  DaoException;
}