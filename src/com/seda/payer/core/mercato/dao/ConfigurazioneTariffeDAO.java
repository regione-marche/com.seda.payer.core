package com.seda.payer.core.mercato.dao;

import java.util.ArrayList;

import com.seda.payer.core.exception.DaoException;
import com.seda.payer.core.mercato.bean.ConfigurazioneTariffe;
import com.seda.payer.core.mercato.bean.EsitoRisposte;
import com.seda.payer.core.mercato.bean.MercatoPageList;

public interface ConfigurazioneTariffeDAO {
	public ConfigurazioneTariffe select(ConfigurazioneTariffe configurazioneTariffe) throws  DaoException;
	public ConfigurazioneTariffe getPerKey(ConfigurazioneTariffe configurazioneTariffe) throws DaoException;
	public EsitoRisposte insert(ConfigurazioneTariffe configurazioneTariffe) throws  DaoException;
	public EsitoRisposte delete(ConfigurazioneTariffe configurazioneTariffe) throws  DaoException;
	public Integer update(ConfigurazioneTariffe configurazioneTariffe) throws  DaoException;
	public Integer updatePerKey(ConfigurazioneTariffe configurazioneTariffe) throws DaoException;
	public MercatoPageList ListConfigurazioneTariffe(ConfigurazioneTariffe configurazionePiazzola, int rowsPerPage, int pageNumber,String OrderBy) throws  DaoException;
	public ArrayList<ConfigurazioneTariffe> listTariffe(ConfigurazioneTariffe configurazioneTariffe) throws DaoException;
	public ArrayList<ConfigurazioneTariffe> getAllPerAutorizzazione(ConfigurazioneTariffe configurazioneTariffe) throws DaoException;
	public ArrayList<ConfigurazioneTariffe> getAllPerPiazzola(ConfigurazioneTariffe configurazioneTariffe) throws DaoException;
}
