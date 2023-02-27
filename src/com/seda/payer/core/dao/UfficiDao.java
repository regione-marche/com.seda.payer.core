package com.seda.payer.core.dao;

import java.io.Serializable;

import com.seda.payer.core.bean.UfficiPageList;
import com.seda.payer.core.bean.Ufficio;
import com.seda.payer.core.exception.DaoException;

public interface UfficiDao extends Serializable {
	public Ufficio select(Ufficio ufficio) throws  DaoException;
	public Integer update(Ufficio ufficio) throws  DaoException;
	public UfficiPageList ufficiList(Ufficio ufficio, int rowsPerPage, int pageNumber, String OrderBy) throws  DaoException;
	public Integer insert(Ufficio ufficio) throws  DaoException;
	public Integer delete(Ufficio ufficio) throws  DaoException;
}
