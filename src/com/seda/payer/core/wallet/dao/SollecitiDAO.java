package com.seda.payer.core.wallet.dao;

import java.io.Serializable;

import com.seda.payer.core.exception.DaoException;
import com.seda.payer.core.wallet.bean.Solleciti;

public interface SollecitiDAO extends Serializable  {
	
	public String sollecitoStoricoList(Solleciti solleciti) throws  DaoException;
	public String[] elencoOneriList(Solleciti solleciti) throws  DaoException;

}
