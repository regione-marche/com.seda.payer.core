package com.seda.payer.core.mercato.dao;
import java.io.IOException;
//import java.math.BigDecimal;
//import java.sql.CallableStatement;
import java.sql.Connection;
//import java.sql.Date;
//import java.sql.ResultSet;
//import java.sql.SQLData;
import java.sql.SQLException;
//import java.sql.Types;
//import java.text.ParseException;
//import java.text.SimpleDateFormat;
//import java.util.ArrayList;
//import java.util.Calendar;
//import java.util.List;

import javax.sql.DataSource;
//import javax.sql.rowset.CachedRowSet;



//import com.seda.commons.string.Convert;
//import com.seda.data.dao.DAOHelper;
//import com.seda.data.helper.Helper;
//import com.seda.data.helper.HelperException;
//import com.seda.data.spi.PageInfo;

//import com.seda.payer.core.dao.Routines;
//import com.seda.payer.core.exception.DaoException;
import com.seda.payer.core.handler.BaseDaoHandler;
//import com.seda.payer.core.wallet.bean.Wallet;
import com.seda.payer.core.mercato.bean.MercatoPageList;

public class MercatoDAOImpl extends BaseDaoHandler implements MercatoDAO  { 
	private static final long serialVersionUID = 1L;
	//protected CallableStatement callableStatementServAnno = null;
	//protected CallableStatement callableStatementOnere = null;
	//protected CallableStatement callableStatementBollettino = null;
	//protected CallableStatement callableStatementBRS = null;
	//protected CallableStatement callableStatementBRSUP = null;
	//protected CallableStatement callableStatementBRSBATCH = null;
	
	
	public MercatoDAOImpl(DataSource dataSource, String schema) throws SQLException {
		super(dataSource.getConnection(), schema);
	}
	public MercatoDAOImpl(Connection connection, String schema) throws SQLException {
		super(connection, schema);
	}	

}
