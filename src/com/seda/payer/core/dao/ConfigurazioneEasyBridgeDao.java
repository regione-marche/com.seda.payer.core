package com.seda.payer.core.dao;

import java.io.Serializable;



import com.seda.payer.core.bean.ConfigurazioneEasyBridge;
import com.seda.payer.core.exception.DaoException;

import com.seda.payer.core.wallet.bean.EsitoRisposte;


public interface ConfigurazioneEasyBridgeDao extends Serializable{

	public EsitoRisposte insert(ConfigurazioneEasyBridge configurazioneEasyBridge) throws  DaoException;
	public ConfigurazioneEasyBridge select(ConfigurazioneEasyBridge configurazioneEasyBridge) throws  DaoException;
}
