package com.seda.payer.core.dao;

import java.io.Serializable;
import com.seda.payer.core.bean.LogSepa;
import com.seda.payer.core.exception.DaoException;

public interface LogSepaDao extends Serializable{
	public LogSepa doDetail(String codiceFiscale, String iban) throws DaoException;
	public Integer doInsert(LogSepa bean) throws DaoException ;
	public Integer doUpdate(LogSepa bean) throws DaoException;
}
