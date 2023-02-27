package com.seda.payer.core.wallet.dao;

import com.seda.payer.core.exception.DaoException;
import com.seda.payer.core.wallet.bean.ConfigurazioneRaccordoPagonet;
import com.seda.payer.core.wallet.bean.EsitoRisposte;
import com.seda.payer.core.wallet.bean.WalletPageList;

public interface ConfigurazioneRaccordoPagonetDAO {

	public ConfigurazioneRaccordoPagonet select(ConfigurazioneRaccordoPagonet configurazioneRaccordoPagonet) throws  DaoException;
	public EsitoRisposte insert(ConfigurazioneRaccordoPagonet configurazioneRaccordoPagonet) throws  DaoException;
	public EsitoRisposte delete(ConfigurazioneRaccordoPagonet configurazioneRaccordoPagonet) throws  DaoException;
	public Integer update(ConfigurazioneRaccordoPagonet configurazioneRaccordoPagonet) throws  DaoException;
	public WalletPageList ListConfigurazioneRaccordoPagonet(ConfigurazioneRaccordoPagonet configurazioneRaccordoPagonet , int rowsPerPage, int pageNumber,String OrderBy) throws  DaoException;
}
