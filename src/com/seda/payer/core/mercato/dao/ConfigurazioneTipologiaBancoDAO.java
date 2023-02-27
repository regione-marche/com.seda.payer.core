package com.seda.payer.core.mercato.dao;

import java.util.ArrayList;

import com.seda.payer.core.exception.DaoException;
import com.seda.payer.core.mercato.bean.ConfigurazioneTipologiaBanco;
import com.seda.payer.core.mercato.bean.EsitoRisposte;
import com.seda.payer.core.mercato.bean.MercatoPageList;

public interface ConfigurazioneTipologiaBancoDAO {
	public ConfigurazioneTipologiaBanco select(ConfigurazioneTipologiaBanco configurazioneTipologiaBanco) throws  DaoException;
	public EsitoRisposte insert(ConfigurazioneTipologiaBanco configurazioneTipologiaBanco) throws  DaoException;
	public EsitoRisposte delete(ConfigurazioneTipologiaBanco configurazioneTipologiaBanco) throws  DaoException;
	public Integer update(ConfigurazioneTipologiaBanco configurazioneTipologiaBanco) throws  DaoException;
	public MercatoPageList ListConfigurazioneTipologiaBanco(ConfigurazioneTipologiaBanco configurazioneTipologiaBanco, int rowsPerPage, int pageNumber,String OrderBy) throws  DaoException;
	public ArrayList<ConfigurazioneTipologiaBanco> listTipologiaBanco(ConfigurazioneTipologiaBanco configurazioneTipologiaBanco) throws DaoException;
}
