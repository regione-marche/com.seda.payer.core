package com.seda.payer.core.wallet.dao;

import java.io.Serializable;

import com.seda.payer.core.exception.DaoException;
import com.seda.payer.core.wallet.bean.StatisticheForCruscotto;

public interface CruscottoDAO extends Serializable {
	
	public final static String STATISTICHE_CRUSCOTTO = "PYBRSSP_CRUSCOTTO";
	
	public StatisticheForCruscotto generaFlussoStatistiche(String cutecute,String localFilePathScuole) throws  DaoException;
}