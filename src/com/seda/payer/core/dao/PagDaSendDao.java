package com.seda.payer.core.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import com.seda.data.dao.DAOHelper;
import com.seda.data.helper.HelperException;
import com.seda.payer.core.bean.FlussiRen;
import com.seda.payer.core.bean.PagDaRend_Freccia;
import com.seda.payer.core.bean.PagDaRend_IV;
import com.seda.payer.core.exception.DaoException;
import com.seda.payer.core.handler.BaseDaoHandler;

public class PagDaSendDao extends BaseDaoHandler{
	
	public PagDaSendDao(Connection connection, String schema) {
		super(connection, schema);
	}
	
	public List<PagDaRend_IV> listaPagDaSend_IV(String chiaveRendicontazione) throws DaoException{
		//inizio LP PG21XX04 Leak
		//CallableStatement callableStatement;
		CallableStatement callableStatement = null;
		ResultSet data = null;
		//fine LP PG21XX04 Leak
		List<PagDaRend_IV> lista = null;
		try{
			if((chiaveRendicontazione != null) && (!chiaveRendicontazione.equals(""))){
				callableStatement = prepareCall("PYTDTSP_SEL_SEND");
				callableStatement.setString(1, chiaveRendicontazione);
				
				if(callableStatement.execute()){
					lista = new Vector<PagDaRend_IV>();
					//inizio LP PG21XX04 Leak
					//ResultSet data = callableStatement.getResultSet();
					data = callableStatement.getResultSet();
					//fine LP PG21XX04 Leak
					while (data.next()){
						lista.add(PagDaRend_IV.getBean(data));
					}
				}
			}
		}catch (IllegalArgumentException e) {
			throw new DaoException(e);
		} catch (SQLException e) {
			throw new DaoException(e);
		} catch (HelperException e) {
			throw new DaoException(e);
		}
		//inizio LP PG21XX04 Leak
		finally {
			if (data != null) {
				try {
					data.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (callableStatement != null) {
				try {
					callableStatement.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		//fine LP PG21XX04 Leak
		return lista;
	}
	
	public List<PagDaRend_Freccia> listaPagDaSend_Freccia(String chiaveRendicontazione) throws DaoException{
		//inizio LP PG21XX04 Leak
		//CallableStatement callableStatement;
		CallableStatement callableStatement = null;
		ResultSet data = null;
		//fine LP PG21XX04 Leak
		List<PagDaRend_Freccia> lista = null;
		try{
			if((chiaveRendicontazione != null) && (!chiaveRendicontazione.equals(""))){
				callableStatement = prepareCall("PYTFRSP_SEL_SEND");
				callableStatement.setString(1, chiaveRendicontazione);
				
				if(callableStatement.execute()){
					lista = new Vector<PagDaRend_Freccia>();
					//inizio LP PG21XX04 Leak
					//ResultSet data = callableStatement.getResultSet();
					data = callableStatement.getResultSet();
					//fine LP PG21XX04 Leak
					while (data.next()){
						lista.add(PagDaRend_Freccia.getBean(data));
					}
				}
			}
		}catch (IllegalArgumentException e) {
			throw new DaoException(e);
		} catch (SQLException e) {
			throw new DaoException(e);
		} catch (HelperException e) {
			throw new DaoException(e);
		}
		//inizio LP PG21XX04 Leak
		finally {
			if (data != null) {
				try {
					data.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (callableStatement != null) {
				try {
					callableStatement.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		//fine LP PG21XX04 Leak
		return lista;
	}
}
