package com.seda.payer.core.wallet.dao;

import com.seda.payer.core.exception.DaoException;
import com.seda.payer.core.wallet.bean.ConfigurazioneSolleciti;
import com.seda.payer.core.wallet.bean.EsitoRisposte;
import com.seda.payer.core.wallet.bean.WalletPageList;

public interface ConfigurazioneSollecitiDAO {
	public final static String VALIDITA_DA = "VALIDITA_DA";
	public final static String VALIDITA_A = "VALIDITA_A";

	public ConfigurazioneSolleciti select(ConfigurazioneSolleciti configurazioneSolleciti) throws  DaoException;
	public EsitoRisposte insert(ConfigurazioneSolleciti configurazioneSolleciti) throws  DaoException;
	public EsitoRisposte delete(ConfigurazioneSolleciti configurazioneSolleciti) throws  DaoException;
	public Integer update(ConfigurazioneSolleciti configurazioneSolleciti) throws  DaoException;
	public WalletPageList sollecitiListConfigurazioneSolleciti (ConfigurazioneSolleciti configurazioneSolleciti , int rowsPerPage, int pageNumber,String OrderBy) throws  DaoException;

}