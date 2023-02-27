package com.seda.payer.core.mercato.dao;

import java.util.ArrayList;

import com.seda.payer.core.exception.DaoException;
import com.seda.payer.core.mercato.bean.ConfigurazionePeriodoGiornaliero;
import com.seda.payer.core.mercato.bean.EsitoRisposte;
import com.seda.payer.core.mercato.bean.MercatoPageList;

public interface ConfigurazionePeriodoGiornalieroDAO {
	public ConfigurazionePeriodoGiornaliero select(ConfigurazionePeriodoGiornaliero configurazionePeriodoGiornaliero) throws  DaoException;
	public EsitoRisposte insert(ConfigurazionePeriodoGiornaliero configurazionePeriodoGiornaliero) throws  DaoException;
	public EsitoRisposte delete(ConfigurazionePeriodoGiornaliero configurazionePeriodoGiornaliero) throws  DaoException;
	public Integer update(ConfigurazionePeriodoGiornaliero configurazionePeriodoGiornaliero) throws  DaoException;
	public MercatoPageList ListConfigurazionePeriodoGiornaliero(ConfigurazionePeriodoGiornaliero configurazionePeriodoGiornaliero, int rowsPerPage, int pageNumber,String OrderBy) throws  DaoException;
	public ArrayList<ConfigurazionePeriodoGiornaliero> listPeriodoGiornaliero(ConfigurazionePeriodoGiornaliero configurazionePeriodoGiornaliero) throws DaoException;
}
