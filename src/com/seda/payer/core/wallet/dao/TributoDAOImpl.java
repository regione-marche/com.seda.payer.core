package com.seda.payer.core.wallet.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.sql.DataSource;

import com.seda.data.dao.DAOHelper;
import com.seda.data.helper.Helper;
import com.seda.data.helper.HelperException;
import com.seda.data.procedure.reflection.MetaProcedure;
import com.seda.data.procedure.reflection.ProcedureReflectorException;
import com.seda.payer.core.dao.Routines;
import com.seda.payer.core.exception.DaoException;
import com.seda.payer.core.handler.BaseDaoHandler;
import com.seda.payer.core.wallet.bean.Tributo;


public class TributoDAOImpl   extends BaseDaoHandler  implements TributoDAO  {
		private static final long serialVersionUID = 1L;
		//inizio LP PG21XX04 Leak
		@Deprecated
		//fine LP PG21XX04 Leak
		public TributoDAOImpl(DataSource dataSource, String schema) throws SQLException {
			super(dataSource.getConnection(), schema);
		}
		//inizio LP PG21XX04 Leak
		public TributoDAOImpl(Connection connection, String schema) throws SQLException {
			super(connection, schema);
		}
		//fine LP PG21XX04 Leak

	public ArrayList<Tributo> listTributo() throws DaoException {
		CallableStatement callableStatement=null;
		ArrayList<Tributo> listTributo = null;
		Connection connection = null;
		ResultSet resultSet=null;
		try {
			connection = getConnection();
			//inizio LP PGNTCORE-24
			//callableStatement = Helper.prepareCall(connection, getSchema(), Routines.PYTRBSP_LST.routine());
			callableStatement =  MetaProcedure.prepareCall(connection, getSchema(), Routines.PYTRBSP_LST.routine());
			//fine LP PGNTCORE-24
			callableStatement.execute();
			resultSet=callableStatement.getResultSet();
			
			if ( !resultSet.next() ) {
				listTributo = new ArrayList<Tributo>();
			} else {
				listTributo = new ArrayList<Tributo>();
				do {
					Tributo item = new Tributo();               
					item.setCodiceTributo(resultSet.getString("TRB_CTRBCODI"));
					item.setDescrizioneTributo(resultSet.getString("TRB_DTRBDESC"));
					listTributo.add(item);
				} while(resultSet.next());
			}
		} catch (SQLException e) {
			throw new DaoException(e);
		} catch (IllegalArgumentException e) {
			throw new DaoException(e);
		//inizio LP PGNTCORE-24
		//} catch (HelperException e) {
		//	throw new DaoException(e);
		} catch (ProcedureReflectorException e) {
			throw new DaoException(e);
		//fine LP PGNTCORE-24
		} finally {
			//inizio LP PG21XX04 Leak
			//DAOHelper.closeIgnoringException(connection);
			if (resultSet != null) {
				try {
					resultSet.close();
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
		
		return listTributo;

	}

	public ArrayList<Tributo> listTributoServizio(String societa,String utente,String ente) throws DaoException {
		CallableStatement callableStatement=null;
		ArrayList<Tributo> listTributo = null;
		Connection connection = null;
		ResultSet resultSet=null;
		try {
			connection = getConnection();
			//inizio LP PGNTCORE-24
			//callableStatement = Helper.prepareCall(connection, getSchema(), Routines.PYTRBSP_LST_SRV.routine());
			callableStatement =  MetaProcedure.prepareCall(connection, getSchema(), Routines.PYTRBSP_LST_SRV.routine());
			//fine LP PGNTCORE-24
			callableStatement.setString(1, societa);
			callableStatement.setString(2, utente);
			callableStatement.setString(3,ente);
			callableStatement.execute();
			resultSet=callableStatement.getResultSet();
			
			if ( !resultSet.next() ) {
				listTributo = new ArrayList<Tributo>();
			} else {
				listTributo = new ArrayList<Tributo>();
				do {
					Tributo item = new Tributo();               
					item.setCodiceTributo(resultSet.getString("TRB_CTRBCODI"));
					item.setDescrizioneTributo(resultSet.getString("TRB_DTRBDESC"));
					listTributo.add(item);
				} while(resultSet.next());
			}
		} catch (SQLException e) {
			throw new DaoException(e);
		} catch (IllegalArgumentException e) {
			throw new DaoException(e);
		//inizio LP PGNTCORE-24
		//} catch (HelperException e) {
		//	throw new DaoException(e);
		} catch (ProcedureReflectorException e) {
			throw new DaoException(e);
		//fine LP PGNTCORE-24
		} finally {
			//inizio LP PG21XX04 Leak
			//DAOHelper.closeIgnoringException(connection);
			if (resultSet != null) {
				try {
					resultSet.close();
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
		
		return listTributo;
	}
	
}
