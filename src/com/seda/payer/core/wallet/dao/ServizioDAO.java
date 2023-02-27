package com.seda.payer.core.wallet.dao;

import java.io.Serializable;
import java.util.ArrayList;

import com.seda.payer.core.exception.DaoException;
import com.seda.payer.core.wallet.bean.Servizio;

public interface ServizioDAO extends Serializable {
	public ArrayList<Servizio> listServizi() throws  DaoException;
	public Servizio selectServizio(String societa, String cutecute, String ente, String codiceServizio) throws  DaoException;
	public ArrayList<String> listServiziFiglio(String wallet, String anagGen, String codFiscGen, String anno) throws  DaoException;
}
