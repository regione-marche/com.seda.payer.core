package com.seda.payer.core.mercato.dao;

import java.util.ArrayList;

import com.seda.payer.core.exception.DaoException;
import com.seda.payer.core.mercato.bean.ConfigurazioneAreaMercantile;
import com.seda.payer.core.mercato.bean.EsitoRisposte;
import com.seda.payer.core.mercato.bean.MercatoPageList;

public interface ConfigurazioneAreaMercantileDAO {
	public ConfigurazioneAreaMercantile select(ConfigurazioneAreaMercantile configurazioneAreaMercantile) throws  DaoException;
	public EsitoRisposte insert(ConfigurazioneAreaMercantile configurazioneAreaMercantile) throws  DaoException;
	public EsitoRisposte delete(ConfigurazioneAreaMercantile configurazioneAreaMercantile) throws  DaoException;
	public Integer update(ConfigurazioneAreaMercantile configurazioneAreaMercantile) throws  DaoException;
	public MercatoPageList ListConfigurazioneAreaMercantile(ConfigurazioneAreaMercantile configurazioneAreaMercantile, int rowsPerPage, int pageNumber,String OrderBy) throws  DaoException;
	public ArrayList<ConfigurazioneAreaMercantile> listAreaMercantile(ConfigurazioneAreaMercantile configurazioneAreaMercantile) throws DaoException;
	public ArrayList<ConfigurazioneAreaMercantile> getAllPerDescr(ConfigurazioneAreaMercantile configurazioneAreaMercantile) throws DaoException;
}
