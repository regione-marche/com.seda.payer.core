package com.seda.payer.core.dao;

import java.io.Serializable;

import com.seda.payer.core.bean.ConfigurazioneEnteISNotifica;
import com.seda.payer.core.bean.ConfigurazioneEnteISNotificaPagelist;
import com.seda.payer.core.exception.DaoException;
import com.seda.payer.core.wallet.bean.EsitoRisposte;

public interface ConfigurazioneEnteISNotificaDao extends Serializable{
	public ConfigurazioneEnteISNotifica select(ConfigurazioneEnteISNotifica configurazioneEnteISNotifica) throws  DaoException;
	public ConfigurazioneEnteISNotifica select(String cutecute, String ente, String codiceIS) throws  DaoException;
	public Integer update(ConfigurazioneEnteISNotifica configurazioneEnteISNotifica) throws  DaoException;
	public ConfigurazioneEnteISNotificaPagelist configurazioneEnteISNotificaList(ConfigurazioneEnteISNotifica configurazioneEnteISNotifica, int rowsPerPage, int pageNumber, String OrderBy) throws  DaoException;
	public EsitoRisposte insert(ConfigurazioneEnteISNotifica configurazioneEnteISNotifica) throws  DaoException;
	public EsitoRisposte delete(ConfigurazioneEnteISNotifica configurazioneEnteISNotifica) throws  DaoException;
}
