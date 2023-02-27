package com.seda.payer.core.dao;

import java.io.Serializable;


import com.seda.payer.core.bean.GruppoAgenzia;
import com.seda.payer.core.bean.GruppoAgenziaPageList;
import com.seda.payer.core.exception.DaoException;
import com.seda.payer.core.wallet.bean.EsitoRisposte;
public interface GruppoAgenziaDao extends Serializable{

	public EsitoRisposte delete(String codiceGruppoAgenzia) throws  DaoException;
	public EsitoRisposte insert(GruppoAgenzia gruppoAgenzia) throws  DaoException;
	public Integer update(GruppoAgenzia gruppoAgenzia) throws  DaoException;
	public GruppoAgenzia select(String codiceGruppoAgenzia) throws  DaoException;
	public GruppoAgenziaPageList gruppoAgenziaList(GruppoAgenzia gruppoAgenzia, int rowsPerPage, int pageNumber, String OrderBy) throws  DaoException;
	public GruppoAgenziaPageList gruppoAgenziaListDDL() throws  DaoException;
	
}
