package com.seda.payer.core.monitoraggiocruss.dao;

import java.io.Serializable;

import com.seda.payer.core.exception.DaoException;
import com.seda.payer.core.monitoraggiocruss.bean.MonitoraggioCruscotto;

public interface MonitoraggioCruscottoDAO extends Serializable{
	public MonitoraggioCruscotto listMonitoraggioCruscotto(String dataDa, String dataAl) throws  DaoException;
}
