package com.seda.payer.core.dao;

import java.math.BigInteger;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.seda.data.dao.DAOHelper;
import com.seda.data.helper.HelperException;
import com.seda.payer.core.bean.Menu;
import com.seda.payer.core.exception.DaoException;
import com.seda.payer.core.handler.BaseDaoHandler;

public class MenuDao extends BaseDaoHandler{

	public MenuDao(Connection connection, String schema) {
		super(connection, schema);
	}

	public Menu getMenu(Long chiaveUtente) throws DaoException
	{
		CallableStatement callableStatement = null;
		ResultSet current_menu = null;
		String menu1_xml = null;
		String menu2_xml = null;
		String menu3_xml = null;
		Menu menu = null;
		try{
			callableStatement = prepareCall("PYMNUSP_SEL_MENU");
			callableStatement.setLong(1, chiaveUtente);
			if(callableStatement.execute())
			{
				current_menu = callableStatement.getResultSet();
				loadWebRowSet(current_menu);
				menu1_xml = getWebRowSetXml();
			}
			//inizio LP PG21XX04 Leak
			if (current_menu != null) {
				try {
					current_menu.close();
					current_menu = null;
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			//fine LP PG21XX04 Leak
			if(callableStatement.getMoreResults())
			{
				current_menu = callableStatement.getResultSet();
				loadWebRowSet(current_menu);
				menu2_xml = getWebRowSetXml();
			}
			//inizio LP PG21XX04 Leak
			if (current_menu != null) {
				try {
					current_menu.close();
					current_menu = null;
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			//fine LP PG21XX04 Leak
			if(callableStatement.getMoreResults())
			{
				current_menu = callableStatement.getResultSet();
				loadWebRowSet(current_menu);
				menu3_xml = getWebRowSetXml();
			}
			if(menu1_xml != null && menu2_xml != null && menu3_xml != null)
			{
				menu = new Menu(menu1_xml,menu2_xml,menu3_xml);
			}
		return menu;
	} catch (SQLException x) {
		throw new DaoException(x);
	} catch (IllegalArgumentException x) {
		throw new DaoException(x);
	} catch (HelperException x) {
		throw new DaoException(x);
	}
	finally
	{
		//inizio LP PG21XX04 Leak
        //DAOHelper.closeIgnoringException(current_menu);
		//DAOHelper.closeIgnoringException(callableStatement);
		if (current_menu != null) {
			try {
				current_menu.close();
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
		//fine LP PG21XX04 Leak
	}
	}
}
