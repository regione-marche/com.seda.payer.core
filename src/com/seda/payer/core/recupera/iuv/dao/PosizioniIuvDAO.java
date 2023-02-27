package com.seda.payer.core.recupera.iuv.dao;

import java.io.Serializable;

import com.seda.payer.core.exception.DaoException;
import com.seda.payer.core.recupera.iuv.bean.PosizioneIuv;


public interface PosizioniIuvDAO extends Serializable {
	
	public PosizioneIuv select(String idDominio, String codEnte, String codIuv, String codIuv_Iuv) throws DaoException;
}
