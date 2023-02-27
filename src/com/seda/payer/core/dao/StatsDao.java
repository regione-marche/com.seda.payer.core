package com.seda.payer.core.dao;

import java.io.Serializable;

import com.seda.payer.core.bean.Stats;
import com.seda.payer.core.exception.DaoException;

public interface StatsDao extends Serializable {

	public Integer update(Stats stats) throws  DaoException;
	
}
