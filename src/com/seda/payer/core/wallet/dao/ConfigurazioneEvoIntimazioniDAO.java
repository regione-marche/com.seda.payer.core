package com.seda.payer.core.wallet.dao;

import com.seda.payer.core.exception.DaoException;
import com.seda.payer.core.wallet.bean.ConfigurazioneEvoIntimazioni;
import com.seda.payer.core.wallet.bean.EsitoRisposte;
import com.seda.payer.core.wallet.bean.WalletPageList;

public interface ConfigurazioneEvoIntimazioniDAO {
	public final static String VALIDITA_DA = "VALIDITA_DA";
	public final static String VALIDITA_A = "VALIDITA_A";

	public ConfigurazioneEvoIntimazioni select(ConfigurazioneEvoIntimazioni configurazioneEvoIntimazioni) throws  DaoException;
	public EsitoRisposte insert(ConfigurazioneEvoIntimazioni configurazioneEvoIntimazioni) throws  DaoException;
	public EsitoRisposte delete(ConfigurazioneEvoIntimazioni configurazioneEvoIntimazioni) throws  DaoException;
	public Integer update(ConfigurazioneEvoIntimazioni configurazioneEvoIntimazioni) throws  DaoException;
	public WalletPageList ListConfigurazioneEvoIntimazioni (ConfigurazioneEvoIntimazioni configurazioneEvoIntimazioni , int rowsPerPage, int pageNumber,String OrderBy) throws  DaoException;
}
