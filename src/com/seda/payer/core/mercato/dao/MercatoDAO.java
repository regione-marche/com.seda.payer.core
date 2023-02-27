package com.seda.payer.core.mercato.dao;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.seda.data.page.Page;
import com.seda.payer.core.exception.DaoException;
import com.seda.payer.core.mercato.bean.MercatoPageList;
	   
public interface MercatoDAO extends Serializable { 
	public final static String SELECT_XML= "SELECT_XML";
		
}
