package com.seda.payer.core.mercato.dao;

import java.util.ArrayList;

import com.seda.payer.core.exception.DaoException;
import com.seda.payer.core.mercato.bean.ConfigurazioneCompenso;
import com.seda.payer.core.mercato.bean.EsitoRisposte;
import com.seda.payer.core.mercato.bean.MercatoPageList;

public interface ConfigurazioneCompensoDAO {
	public ConfigurazioneCompenso select(ConfigurazioneCompenso configurazioneCompenso) throws  DaoException;
	public EsitoRisposte insert(ConfigurazioneCompenso configurazioneCompenso) throws  DaoException;
	public EsitoRisposte delete(ConfigurazioneCompenso configurazioneCompenso) throws  DaoException;
	public Integer update(ConfigurazioneCompenso configurazioneCompenso) throws  DaoException;
	public MercatoPageList ListConfigurazioneCompenso(ConfigurazioneCompenso configurazioneCompenso, int rowsPerPage, int pageNumber,String OrderBy) throws  DaoException;
	public ArrayList<ConfigurazioneCompenso> listCompenso(ConfigurazioneCompenso configurazioneCompenso) throws DaoException;
}
