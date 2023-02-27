package com.seda.payer.core.wallet.dao;

import com.seda.payer.core.exception.DaoException;
import com.seda.payer.core.wallet.bean.ConfigurazioneAttribuzionePagamentoServizio;
import com.seda.payer.core.wallet.bean.EsitoRisposte;
import com.seda.payer.core.wallet.bean.WalletPageList;

public interface ConfigurazioneAttribuzionePagamentoServizioDAO {
	public final static String DATA_INSERGENZA_CREDITO_DA = "DATA_INSERGENZA_CREDITO_DA";
	public final static String DATA_INSERGENZA_CREDITO_A = "DATA_INSERGENZA_CREDITO_A";
	public final static String DATA_NASCITA_FIGLIO_DA = "DATA_NASCITA_FIGLIO_DA";
	public final static String DATA_NASCITA_FIGLIO_A = "DATA_NASCITA_FIGLIO_A";

	public ConfigurazioneAttribuzionePagamentoServizio select(ConfigurazioneAttribuzionePagamentoServizio ConfigurazioneAttribuzionePagamentoServizio) throws  DaoException;
	public EsitoRisposte insert(ConfigurazioneAttribuzionePagamentoServizio ConfigurazioneAttribuzionePagamentoServizio) throws  DaoException;
	public EsitoRisposte delete(ConfigurazioneAttribuzionePagamentoServizio ConfigurazioneAttribuzionePagamentoServizio) throws  DaoException;
	public EsitoRisposte update(ConfigurazioneAttribuzionePagamentoServizio ConfigurazioneAttribuzionePagamentoServizio) throws  DaoException;
	public WalletPageList ListaConfigurazioneAttribuzionePagamentoServizio (ConfigurazioneAttribuzionePagamentoServizio configurazioneAttribuzionePagamentoServizio , int rowsPerPage, int pageNumber,String OrderBy) throws  DaoException;
}
