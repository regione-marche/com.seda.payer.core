package com.seda.payer.core.wallet.dao;

import java.util.ArrayList;

import com.seda.payer.core.exception.DaoException;
import com.seda.payer.core.wallet.bean.Tributo;

public interface TributoDAO {
	public ArrayList<Tributo> listTributo() throws  DaoException;
	public ArrayList<Tributo> listTributoServizio(String societa,String utente,String ente) throws  DaoException;

}
