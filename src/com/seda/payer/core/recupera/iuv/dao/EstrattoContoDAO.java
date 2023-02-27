package com.seda.payer.core.recupera.iuv.dao;

import java.io.Serializable;
import com.seda.payer.core.exception.DaoException;


public interface EstrattoContoDAO extends Serializable {
	
	//public PosizioneIuv select(String idDominio, String codEnte, String codIuv, String codIuv_Iuv) throws DaoException;
	
	 public String[] getListEnti(String codiceFiscale, String tipoRichiesta) throws DaoException;
	 
	 public String[] getListDoc(String codiceEnte, String codiceFiscale, String tipoRichiesta) throws DaoException;
}
