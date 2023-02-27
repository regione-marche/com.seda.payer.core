package com.seda.payer.core.monitoraggiocruss.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import com.seda.data.dao.DAOHelper;
import com.seda.data.helper.Helper;
import com.seda.data.helper.HelperException;
import com.seda.payer.core.exception.DaoException;
import com.seda.payer.core.handler.BaseDaoHandler;
import com.seda.payer.core.monitoraggiocruss.bean.MonitoraggioCruscotto;
import com.seda.payer.core.monitoraggiocruss.bean.NotificaList;
import com.seda.payer.core.monitoraggiocruss.bean.PagamentoList;
import com.seda.payer.core.monitoraggiocruss.bean.RendicontazioneList;
import com.seda.payer.core.monitoraggiocruss.bean.TransazioneList;

public class MonitoraggioCruscottoDAOImpl extends BaseDaoHandler implements MonitoraggioCruscottoDAO   {
	private static final long serialVersionUID = 1L;
	
	//inizio LP PG21XX04 Leak
	@Deprecated
	//fine LP PG21XX04 Leak
	public MonitoraggioCruscottoDAOImpl(DataSource dataSource, String schema) throws SQLException {
		super(dataSource.getConnection(), schema);
	}
	public MonitoraggioCruscottoDAOImpl(Connection connection, String schema) throws SQLException {
		super(connection, schema);
	}

	public MonitoraggioCruscotto listMonitoraggioCruscotto(String dataDa, String dataAl) throws  DaoException{
		CallableStatement callableStatement=null;
		Connection connection = null;
		ResultSet data = null;
		MonitoraggioCruscotto monitoraggioCruscotto = null;
		PagamentoList pagamentoList = null;
		TransazioneList transazioneList = null;
		NotificaList notificaList = null;
		RendicontazioneList rendicontazioneList = null;
		try {			
			connection = getConnection();
			callableStatement = Helper.prepareCall(connection, getSchema(), "PYTRASP_SEL_CRUSS_MG");
			callableStatement.setString(1, dataDa);
			callableStatement.setString(2, dataAl);
			callableStatement.setString(3, "1");	
			if(callableStatement.execute())	{			
				data = callableStatement.getResultSet();
				loadWebRowSet(data);
				pagamentoList = new PagamentoList(getWebRowSetXml());
				System.out.println(" get lista pagamenti");
			}
			//inizio LP PG21XX04 Leak
			if (data != null) {
				try {
					data.close();
					data = null;
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			//fine LP PG21XX04 Leak
			callableStatement.setString(3, "2");
			if(callableStatement.execute())	{			
				data = callableStatement.getResultSet();
				loadWebRowSet(data);
				transazioneList = new TransazioneList(getWebRowSetXml());
				System.out.println("get lista transazioni sospese");
			}
			//inizio LP PG21XX04 Leak
			if (data != null) {
				try {
					data.close();
					data = null;
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			//fine LP PG21XX04 Leak
			callableStatement.setString(3, "3");
			if(callableStatement.execute())	{			
				data = callableStatement.getResultSet();
				loadWebRowSet(data);
				rendicontazioneList = new RendicontazioneList(getWebRowSetXml());
				System.out.println("get lista rendicontazioni");
			}
			//inizio LP PG21XX04 Leak
			if (data != null) {
				try {
					data.close();
					data = null;
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			//fine LP PG21XX04 Leak
			callableStatement.setString(3, "4");
			if(callableStatement.execute())	{			
				data = callableStatement.getResultSet();
				loadWebRowSet(data);
				notificaList = new NotificaList(getWebRowSetXml());
				System.out.println("get lista notifiche fallite");
			}
			monitoraggioCruscotto = new MonitoraggioCruscotto(pagamentoList, transazioneList, notificaList, rendicontazioneList);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (HelperException e) {
			e.printStackTrace();
		} finally {
			//inizio LP PG21XX04 Leak
			//DAOHelper.closeIgnoringException(connection);
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
			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			//fine LP PG21XX04 Leak
			
		}
		return monitoraggioCruscotto;
	}

}
