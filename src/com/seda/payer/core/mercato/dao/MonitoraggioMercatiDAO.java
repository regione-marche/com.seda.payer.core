package com.seda.payer.core.mercato.dao;

import java.util.Calendar;
import java.util.List;

import com.seda.payer.core.mercato.bean.MercatoPageList;
import com.seda.payer.core.mercato.bean.MonitoraggioMercati;
import com.seda.payer.core.exception.DaoException;
import com.seda.payer.core.handler.BaseDaoHandler;

public interface MonitoraggioMercatiDAO {
	public List<MonitoraggioMercati> doList(MonitoraggioMercati monitor) throws DaoException;
	public MercatoPageList ListMonitoraggioMercati(MonitoraggioMercati monitoraggioMercati, int rowsPerPage, int pageNumber,String OrderBy) throws  DaoException;
	public MonitoraggioMercati getPerKey(MonitoraggioMercati monitor) throws DaoException;
}
