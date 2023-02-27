package com.seda.payer.core.mercato.dao;

import java.util.ArrayList;
import java.util.Calendar;

import com.seda.payer.core.exception.DaoException;
import com.seda.payer.core.mercato.bean.EsitoRisposte;
import com.seda.payer.core.mercato.bean.ConfigurazionePrenotazioni;

public interface ConfigurazionePrenotazioniDAO {
	public EsitoRisposte insert(ConfigurazionePrenotazioni prenotazioni) throws  DaoException;
	public EsitoRisposte delete(ConfigurazionePrenotazioni prenotazioni) throws  DaoException;
	public EsitoRisposte deletePerRange(ConfigurazionePrenotazioni prenotazioni) throws DaoException;
	public Integer update(ConfigurazionePrenotazioni prenotazioni) throws  DaoException;
	public ArrayList<ConfigurazionePrenotazioni>getAllPerTariffa(ConfigurazionePrenotazioni configurazionePrenotazioni) throws DaoException;
	public EsitoRisposte schedulaPrenotazioni(String codiceKeyTariffa, String codSocieta, String codUte, String codEnte, Calendar dataInizioVal, Calendar dataFineVal, int giornoSett, String dateEscluse) throws DaoException;
	public EsitoRisposte schedulaPrenotazioniDaPiazzola(String codSocieta, String codUte, String codEnte, String codicePiazzole, String codiceMercato, Calendar dataInizioVal, Calendar dataFineVal, int giornoSett) throws DaoException;
	public EsitoRisposte schedulaPrenotazioniDaKeyPiazzola(String codiceKeyPiazzola, String codiceKeyAreaMerc, String codiceSocieta, String codiceUtente, String codiceEnte, Calendar dataInizioVal, Calendar dataFineVal, int giornoSett) throws DaoException;
	
}
