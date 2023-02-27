package com.seda.payer.core.dao;

import java.io.Serializable;

import com.seda.payer.core.bean.ScadenzaUrlNotifica;
import com.seda.payer.core.exception.DaoException;
import com.seda.payer.core.wallet.bean.EsitoRisposte;

public interface ScadenzaUrlNotificaDao extends Serializable{
	
	public EsitoRisposte insert(ScadenzaUrlNotifica configurazioneEnteISNotifica) throws  DaoException;
	public String getCodiceSocietaByEnteUtente(String cutecute, String codEnte) throws DaoException;
	public Integer update(ScadenzaUrlNotifica scadenzaUrl) throws DaoException;
	public ScadenzaUrlNotifica select(String codEnte, String codiceFiscale, String numeroDocumento) throws DaoException;
}
