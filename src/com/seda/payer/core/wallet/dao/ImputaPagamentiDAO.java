package com.seda.payer.core.wallet.dao;
 
import java.util.ArrayList;
import java.util.Calendar;

import com.seda.data.helper.HelperException;
import com.seda.payer.core.exception.DaoException;
import com.seda.payer.core.wallet.bean.FlussoSISE;
import com.seda.payer.core.wallet.bean.ImpPagamenti;
import com.seda.payer.core.wallet.bean.TributiForSORINET;

public interface ImputaPagamentiDAO {

	public String inserisciPagamentoWeb(String codTransazione, Calendar dataPagamento, String flagGestioneMercati)throws DaoException, HelperException ;
	public ArrayList<FlussoSISE> assegnaPagamenti(ImpPagamenti impPagamenti)throws DaoException, HelperException ;
	public ArrayList<FlussoSISE> produciFlussoSISE(String cutecute)throws DaoException, HelperException ;
	public void cancellaTabellaSISE(String cutecute)throws DaoException, HelperException ;
	public String generaSISEglobale(String cutecute)throws DaoException, HelperException ;
	public ArrayList<TributiForSORINET> listForSORINET(String cutecute)throws DaoException, HelperException ;
	public String aggTributiSORINET(String funzione, TributiForSORINET tributiForSORINET)throws DaoException, HelperException ;
	
}
