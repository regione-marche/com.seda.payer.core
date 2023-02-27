package com.seda.payer.core.mercato.dao;

import java.util.ArrayList;

import com.seda.payer.core.exception.DaoException;
import com.seda.payer.core.mercato.bean.ConfigurazionePiazzola;
import com.seda.payer.core.mercato.bean.EsitoRisposte;
import com.seda.payer.core.mercato.bean.MercatoPageList;

public interface ConfigurazionePiazzolaDAO {
	public ConfigurazionePiazzola select(ConfigurazionePiazzola configurazionePiazzola) throws  DaoException;
	public ConfigurazionePiazzola getPerKey(ConfigurazionePiazzola configurazionePiazzola) throws  DaoException;
	public EsitoRisposte insert(ConfigurazionePiazzola configurazionePiazzola) throws  DaoException;
	public EsitoRisposte delete(ConfigurazionePiazzola configurazionePiazzola) throws  DaoException;
	public Integer update(ConfigurazionePiazzola configurazionePiazzola) throws  DaoException;
	public Integer updatePerKey(ConfigurazionePiazzola configurazionePiazzola) throws  DaoException;
	public MercatoPageList ListConfigurazionePiazzola(ConfigurazionePiazzola configurazionePiazzola, int rowsPerPage, int pageNumber,String OrderBy) throws  DaoException;
	public ArrayList <ConfigurazionePiazzola> listPiazzola(ConfigurazionePiazzola configurazionePiazzola) throws DaoException;
	public ArrayList <ConfigurazionePiazzola> getAllPerMercato(ConfigurazionePiazzola configurazionePiazzola) throws DaoException;
}
