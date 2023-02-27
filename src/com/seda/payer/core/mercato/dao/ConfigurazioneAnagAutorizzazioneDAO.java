package com.seda.payer.core.mercato.dao;

import java.util.ArrayList;

import com.seda.payer.core.exception.DaoException;
import com.seda.payer.core.mercato.bean.ConfigurazioneAnagAutorizzazione;
import com.seda.payer.core.mercato.bean.EsitoRisposte;
import com.seda.payer.core.mercato.bean.MercatoPageList;

public interface ConfigurazioneAnagAutorizzazioneDAO {
	public ConfigurazioneAnagAutorizzazione select(ConfigurazioneAnagAutorizzazione configurazioneAnagAutorizzazione) throws  DaoException;
	public EsitoRisposte insert(ConfigurazioneAnagAutorizzazione configurazioneAnagAutorizzazione) throws  DaoException;
	public EsitoRisposte delete(ConfigurazioneAnagAutorizzazione configurazioneAnagAutorizzazione) throws  DaoException;
	public Integer update(ConfigurazioneAnagAutorizzazione configurazioneAnagAutorizzazione) throws  DaoException;
	public MercatoPageList ListConfigurazioneAnagAutorizzazione(ConfigurazioneAnagAutorizzazione configurazioneAnagAutorizzazione, int rowsPerPage, int pageNumber,String OrderBy) throws  DaoException;
	public ArrayList<ConfigurazioneAnagAutorizzazione> listAnagAutorizzazione(ConfigurazioneAnagAutorizzazione configurazioneAnagAutorizzazione) throws DaoException;
	public ArrayList<ConfigurazioneAnagAutorizzazione> listPerCF(ConfigurazioneAnagAutorizzazione configurazioneAnagAutorizzazione) throws DaoException;
	public ArrayList<ConfigurazioneAnagAutorizzazione> listPerNome(ConfigurazioneAnagAutorizzazione configurazioneAnagAutorizzazione) throws DaoException;
}
