package com.seda.payer.core.wallet.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import javax.sql.DataSource;
import javax.sql.rowset.WebRowSet;

import com.seda.data.helper.HelperException;
import com.seda.payer.core.dao.Routines;
import com.seda.payer.core.exception.DaoException;
import com.seda.payer.core.handler.BaseDaoHandler;
import com.seda.payer.core.wallet.bean.Solleciti;

public class SollecitiDAOImpl  extends BaseDaoHandler implements SollecitiDAO {
	private static final long serialVersionUID = 1L;

	//inizio LP PG21XX04 Leak
	@Deprecated
	//fine LP PG21XX04 Leak
	public SollecitiDAOImpl(DataSource dataSource, String schema) throws SQLException {
		super(dataSource.getConnection(), schema);
	}

	public SollecitiDAOImpl(Connection connection, String schema) throws SQLException {
		super(connection, schema);
	}
	
	public String  sollecitoStoricoList(Solleciti solleciti)	throws DaoException {
		CallableStatement callableStatement=null;
		Connection connection = null;
		ResultSet data = null; 
		WebRowSet storico= null;
		String storicoXML= "";
		try {
			connection = getConnection();
			//inizio LP 20240921 - PGNTCORE-24 
			//callableStatement = Helper.prepareCall(connection, getSchema(), Routines.PYSOLSP_LST.routine());
			callableStatement = prepareCall(Routines.PYSOLSP_LST.routine());
			//fine LP 20240921 - PGNTCORE-24
			/* page number*/
			callableStatement.setString(1,solleciti.getIdWallet());			 
			/* we execute procedure */
			if(callableStatement.execute())	{
				data = callableStatement.getResultSet();
				loadWebRowSet(data);
				storico=getWebRowSet();
				while(storico.next()) {
					String dataStr= storico.getString(3);
					dataStr=dataStr.replace(".", "/");
					storico.setString(3, dataStr);
				}
				storicoXML = getWebRowSetXml();
			}	
		} catch (SQLException e) {
			throw new DaoException(e);
		} catch (IllegalArgumentException e) {
			throw new DaoException(e);
		} catch (HelperException e) {
			throw new DaoException(e);
		} finally {
			//inizio LP PG21XX04 Leak
			//DAOHelper.closeIgnoringException(connection);
			if (storico != null) {
				try {
					storico.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
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
		return storicoXML;
	}
	
	public String[] elencoOneriList(Solleciti solleciti)	throws DaoException {
		CallableStatement callableStatement=null;
		Connection connection = null;
		ResultSet data = null; 
		String storicoXML= "";
		String[] result = new String[2];
		try {
			connection = getConnection();
			//inizio LP 20240921 - PGNTCORE-24
			//callableStatement = Helper.prepareCall(connection, getSchema(), Routines.PYSOLSP_LST_ONE.routine());
			callableStatement = prepareCall(Routines.PYSOLSP_LST_ONE.routine());
			//fine LP 20240921 - PGNTCORE-24
            /* page number*/
			callableStatement.setString(1,solleciti.getIdWallet());			 
			callableStatement.registerOutParameter(2, Types.VARCHAR);
			/* we execute procedure */
			if(callableStatement.execute())	{
				String flagMsg = "";
				flagMsg = callableStatement.getString(2);
				
				data = callableStatement.getResultSet();
				loadWebRowSet(data);
				
				if (flagMsg.equals("Y")) {
					System.out.println("intimazioni presenti");
					String msg = "Attenzione: i consumi evoluti in intimazione non sono pagabili sul borsellino elettronico.</br>I documenti di intimazione possono essere consultati e pagati collegandosi all'Estratto Conto.";
					result[1] = msg;	
				}
				storicoXML = getWebRowSetXml();
				
				result[0] = storicoXML;
				
				
//				if (flagMsg.equals("Y")) {
//					String msg = "Attenzione: i consumi evoluti in intimazione non sono pagabili sul borsellino elettronico.</br>I documenti di intimazione possono essere consultati e pagati collegandosi all'Estratto Conto.";
//					result[1] = msg;	
//				}
				
			}	
		} catch (SQLException e) {
			throw new DaoException(e);
		} catch (IllegalArgumentException e) {
			throw new DaoException(e);
		} catch (HelperException e) {
			throw new DaoException(e);
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
		return result;
	}

}
