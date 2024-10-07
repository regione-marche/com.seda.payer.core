package com.seda.payer.core.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import com.seda.payer.core.exception.DaoException;
import com.seda.payer.core.handler.BaseDaoHandler;
import com.seda.data.helper.HelperException;

//codice da spostare nella classe originale

public class GatewayPagamentoDao2 extends BaseDaoHandler {
	
	public GatewayPagamentoDao2(Connection connection, String schema) {
		super(connection, schema);
	}

//	public static final String GTWIMP_DODDLIST = "PYGTWCANSP_LST_DDL";

	public String getListaGatewayXml(String codSocieta, String codBelfiore, String codUtente, 
			                        String codImpositore, String codCanale)
	throws DaoException
	{
		CallableStatement callableStatement = null;
		try
		{

//			callableStatement = prepareCall(GTWIMP_DODDLIST);
			callableStatement = prepareCall(Routines.GTWIMP_DODDLIST.routine());
			callableStatement.setString(1, codSocieta == null ? "" : codSocieta);
			callableStatement.setString(2, codBelfiore == null ? "" : codBelfiore);
			callableStatement.setString(3, codUtente == null ? "" : codUtente);
			callableStatement.setString(4, codImpositore == null ? "" : codImpositore);
			callableStatement.setString(5, codCanale == null ? "" : codCanale);
			if(callableStatement.execute())
			{
				loadWebRowSet(callableStatement);
			}
			String appoggio = this.getWebRowSetXml();
			return appoggio;
		}
		catch (IllegalArgumentException e) {
			throw new DaoException(e);
		} catch (SQLException e) {
			throw new DaoException(e);
		} catch (HelperException e) {
			throw new DaoException(e);
		} finally {
			//inizio LP PG21XX04 Leak
			//closeConnection(callableStatement);
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
	//inizio LP PG21XX04 Leak
	//private void closeConnection(CallableStatement callableStatement)
	//{
	//	if (callableStatement != null)
	//		DAOHelper.closeIgnoringException(callableStatement);
	//}
	//fine LP PG21XX04 Leak
	
}