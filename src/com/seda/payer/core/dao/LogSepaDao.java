package com.seda.payer.core.dao;

import java.io.Serializable;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.seda.data.helper.HelperException;
import com.seda.payer.core.bean.LogSepa;
import com.seda.payer.core.exception.DaoException;
import com.seda.payer.core.handler.BaseDaoHandler;

public interface LogSepaDao extends Serializable{
	
	public LogSepa doDetail(String codiceFiscale, String iban) throws DaoException;
	
	public Integer doInsert(LogSepa bean) throws DaoException ;
	
	public Integer doUpdate(LogSepa bean) throws DaoException;
	
	
}
