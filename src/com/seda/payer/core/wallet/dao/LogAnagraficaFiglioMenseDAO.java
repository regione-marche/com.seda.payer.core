package com.seda.payer.core.wallet.dao;

import java.io.Serializable;
import java.util.ArrayList;

import com.seda.payer.core.exception.DaoException;
import com.seda.payer.core.wallet.bean.LogAnagraficaFiglioMense;

public interface LogAnagraficaFiglioMenseDAO extends Serializable {
	
	public final static String DESCRIZIONE_SCUOLA= "DESCRIZIONESCUOLA";
	public final static String DATA_VALIDITA_STRING="DATAVALIDITASTRING";
	public final static String IMPORTOTARIFFA= "IMPORTOTARIFFA";
	public final static String IMPORTOISEE= "IMPORTOISEE";
	
	public ArrayList<LogAnagraficaFiglioMense> list(LogAnagraficaFiglioMense logAnagraficaFiglioMense) throws  DaoException;

}
