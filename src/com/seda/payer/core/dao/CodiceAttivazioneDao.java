package com.seda.payer.core.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

//inizio LP PG21XX04 Leak
//import com.seda.data.dao.DAOHelper;
//fine LP PG21XX04 Leak
import com.seda.data.helper.HelperException;
import com.seda.payer.core.bean.CodiceAttivazione;
import com.seda.payer.core.exception.DaoException;
import com.seda.payer.core.handler.BaseDaoHandler;
import com.seda.payer.core.messages.Messages;

public class CodiceAttivazioneDao extends BaseDaoHandler {
	
	public CodiceAttivazioneDao(Connection connection, String schema) {
		super(connection, schema);
	}

	public Object[] doList(String companyCode, String userCode, String chiaveEnte, String codiceFiscale) throws DaoException {
		CallableStatement callableStatement = null;
		//inizio LP PG21XX04 Leak
		ResultSet data = null;
		//fine LP PG21XX04 Leak
		try	{
			callableStatement = prepareCall("PYECRSP_LST");
			callableStatement.setString(1, companyCode);
			callableStatement.setString(2, userCode);
			callableStatement.setString(3, chiaveEnte);			
			callableStatement.setString(4, codiceFiscale);
			callableStatement.registerOutParameter(5, Types.INTEGER);
			if (callableStatement.execute()) {
				//inizio LP PG21XX04 Leak
				//ResultSet data = callableStatement.getResultSet();
				data = callableStatement.getResultSet();
				//fine LP PG21XX04 Leak
				int nrow = callableStatement.getInt(5);
				if(nrow > 0) {
					int k = 0;
					Object[] ret = new Object[nrow]; 
					while(data.next()) {
						ret[k++] = (Object) new CodiceAttivazione(data);
					}
					return ret;
				} else {
					return null;
				}
			}
			return null;
		} catch (SQLException x) {
			throw new DaoException(x);
		} catch (IllegalArgumentException x) {
			throw new DaoException(x);
		} catch (HelperException x) {
			throw new DaoException(x);
		} finally {
			//inizio LP PG21XX04 Leak
			//closeConnection(callableStatement);
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
			//fine LP PG21XX04 Leak
		}
	}

	public int doSave(CodiceAttivazione codiceAttivazione) throws DaoException {
		int code = 0;
		CallableStatement callableStatement = null;
		//inizio LP PG21XX04 Leak
		Connection conn = null;
		//fine LP PG21XX04 Leak
		try	{
			if (codiceAttivazione.getCodiceSocieta() == null || codiceAttivazione.getCodiceSocieta().length() == 0)
				throw new IllegalArgumentException(Messages.INVALID_PARAMETER.format("codiceAttivazione.codiceSocieta"));

			if (codiceAttivazione.getCodiceUtente() == null || codiceAttivazione.getCodiceUtente().length() == 0)
				throw new IllegalArgumentException(Messages.INVALID_PARAMETER.format("codiceAttivazione.codiceUtente"));

			if (codiceAttivazione.getChiaveEnte() == null || codiceAttivazione.getChiaveEnte().length() == 0)
				throw new IllegalArgumentException(Messages.INVALID_PARAMETER.format("codiceAttivazione.chiavaEnte"));

			if (codiceAttivazione.getCodiceFiscale() == null || codiceAttivazione.getCodiceFiscale().length() == 0)
				throw new IllegalArgumentException(Messages.INVALID_PARAMETER.format("codiceAttivazione.codiceFiscale"));
			
			callableStatement = prepareCall("PYECRSP_INS");
			codiceAttivazione.save(callableStatement);
			//inizio LP PG21XX04 Leak
			//Connection conn = getConnection();
			conn = getConnection();
			//fine LP PG21XX04 Leak
			conn.setAutoCommit(false);
			callableStatement.execute();
			String sCode = callableStatement.getString(6);
			String sMess = callableStatement.getString(7);
			if(sCode.equals("00")) {
				conn.commit();
			} else {
				code = Integer.parseInt(sCode);
				conn.rollback();
				throw new SQLException(sMess); 
			}
		} catch (SQLException x) {
			System.out.println("doSave failed generic error due to: " + x.getMessage());
			throw new DaoException(x);
		} catch (IllegalArgumentException x) {
			System.out.println("doSave failed generic error due to: " + x.getMessage());
			throw new DaoException(101, x.getMessage());
		} catch (HelperException x) {
			throw new DaoException(x);
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
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			//fine LP PG21XX04 Leak
		}
		return code;
	}

	//inizio LP PG21XX04 Leak
	//private void closeConnection(CallableStatement callableStatement)
	//{
	//	if (callableStatement != null)
	//		DAOHelper.closeIgnoringException(callableStatement);
	//}
	//fine LP PG21XX04 Leak
}