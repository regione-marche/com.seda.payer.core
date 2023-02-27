package com.seda.payer.core.wallet.dao;

import com.seda.payer.core.exception.DaoException;
import com.seda.payer.core.wallet.bean.ConfigurazionePagamentoServizio;
import com.seda.payer.core.wallet.bean.EsitoRisposte;
import com.seda.payer.core.wallet.bean.WalletPageList;

public interface ConfigurazionePagamentoServizioDAO {
	public ConfigurazionePagamentoServizio select(ConfigurazionePagamentoServizio configurazionePagamentoServizio) throws  DaoException;
	public EsitoRisposte insert(ConfigurazionePagamentoServizio configurazionePagamentoServizio) throws  DaoException;
	public EsitoRisposte delete(ConfigurazionePagamentoServizio configurazionePagamentoServizio) throws  DaoException;
	public Integer update(ConfigurazionePagamentoServizio configurazionePagamentoServizio) throws  DaoException;
	public WalletPageList ListConfigurazionePagamentoServizio(ConfigurazionePagamentoServizio configurazionePagamentoServizio, int rowsPerPage, int pageNumber,String OrderBy) throws  DaoException;
}
