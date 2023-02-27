package com.seda.payer.core.dao;

import java.io.Serializable;

import com.seda.payer.core.bean.BlackBoxPagelist;
import com.seda.payer.core.bean.ConfigurazioneBlackBox;
import com.seda.payer.core.bean.ConfigurazioneModello3;
import com.seda.payer.core.bean.ConfigurazioneModello3Pagelist;
import com.seda.payer.core.exception.DaoException;
import com.seda.payer.core.wallet.bean.ConfigurazioneSolleciti;
import com.seda.payer.core.wallet.bean.EsitoRisposte;
import com.seda.payer.core.wallet.bean.Wallet;

public interface ConfigurazioneModello3Dao extends Serializable{

	
	public ConfigurazioneModello3 select(ConfigurazioneModello3 configurazioneModello3) throws  DaoException;
	public Integer update(ConfigurazioneModello3 configurazioneModello3) throws  DaoException;
	public ConfigurazioneModello3Pagelist configurazioneModello3List(ConfigurazioneModello3 configurazioneModello3, int rowsPerPage, int pageNumber, String OrderBy) throws  DaoException;
	//inizio LP PG21XX09
	public ConfigurazioneModello3Pagelist configurazioneModello3ListNoCloseConnection(ConfigurazioneModello3 configurazioneModello3, int rowsPerPage, int pageNumber, String OrderBy) throws  DaoException;
	//fine LP PG21XX09
	public EsitoRisposte insert(ConfigurazioneModello3 configurazioneModello3) throws  DaoException;
	public EsitoRisposte delete(ConfigurazioneModello3 configurazioneModello3) throws  DaoException;
}
