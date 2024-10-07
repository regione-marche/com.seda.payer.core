package com.seda.payer.core.wallet.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.sql.DataSource;

import com.seda.data.helper.HelperException;
import com.seda.payer.core.dao.Routines;
import com.seda.payer.core.exception.DaoException;
import com.seda.payer.core.handler.BaseDaoHandler;
import com.seda.payer.core.wallet.bean.Servizio;

public class ServizioDAOImpl   extends BaseDaoHandler  implements ServizioDAO  {
	private static final long serialVersionUID = 1L;

	//inizio LP PG21XX04 Leak
	@Deprecated
	//fine LP PG21XX04 Leak
	public ServizioDAOImpl(DataSource dataSource, String schema) throws SQLException {
		super(dataSource.getConnection(), schema);
	}

	//inizio LP PG21XX04 Leak
	public ServizioDAOImpl(Connection connection, String schema) throws SQLException {
		super(connection, schema);
	}
	//fine LP PG21XX04 Leak

	public ArrayList<Servizio> listServizi() throws DaoException {
		CallableStatement callableStatement = null;
		ArrayList<Servizio> servizioList = null;
		Connection connection = null;
		ResultSet resultSet=null;
		try {
			connection = getConnection();
			//inizio LP PGNTCORE-24
			//callableStatement = Helper.prepareCall(connection, getSchema(), Routines.PYSRVSP_LST.routine());
			callableStatement = prepareCall(Routines.PYSRVSP_LST.routine());
			//fine LP PGNTCORE-24
			callableStatement.execute();
			resultSet=callableStatement.getResultSet();
			
			if ( !resultSet.next() ) {
				servizioList = new ArrayList<Servizio>();
			} else {
				servizioList = new ArrayList<Servizio>();
				do {
					Servizio item = new Servizio();
					item.setCodiceServizio(resultSet.getString("SRV_CSRVCODI"));
					item.setDescrizioneServizio(resultSet.getString("SRV_DSRVDESC"));
					servizioList.add(item);
				} while(resultSet.next());
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
		
		return servizioList;

	}

	//inizio LP PG21XX04
	//Nota. La chiusura della connection è affidata al chiamante.
	//fine LP PG21XX04
	public Servizio selectServizio(String societa, String cutecute, String ente, String codiceServizio) throws DaoException {
		CallableStatement callableStatement=null;
		//Connection connection = null; //LP PGNTCORE-24
		ResultSet resultSet=null;
		Servizio servizio=null;
		try {
			//inizio LP PGNTCORE-24
			//connection = getConnection();
			//callableStatement = Helper.prepareCall(connection, getSchema(), Routines.PYSRVSP_SEL.routine());
			callableStatement = prepareCall(Routines.PYSRVSP_SEL.routine());
			//fine LP PGNTCORE-24
			callableStatement.setString(1, societa);
			callableStatement.setString(2, cutecute);
			callableStatement.setString(3, ente);
			callableStatement.setString(4, codiceServizio);
			callableStatement.execute();
			resultSet=callableStatement.getResultSet();
			
			if ( !resultSet.next() ) {
				servizio = new Servizio();
			} else {
				servizio = new Servizio();				
				servizio.setCodiceServizio(resultSet.getString("SRV_CSRVCODI"));
				servizio.setDescrizioneServizio(resultSet.getString("SRV_DSRVDESC"));
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
			//fine LP PG21XX04 Leak
		} 
		return servizio;
	}
	
	//inizio LP PG21XX04
	//Nota. La chiusura della connection è affidata al chiamante.
	//fine LP PG21XX04
	public ArrayList<String> listServiziFiglio(String idWwallet, String anagGen, String codFiscGen, String anno) throws DaoException {
		CallableStatement callableStatement=null;
		//Connection connection = null; //LP PGNTCORE-24
		ResultSet resultSet=null;
		ArrayList<String> listServiziFiglio;
		try {
			//inizio LP PGNTCORE-24
			//connection = getConnection();
			//callableStatement = Helper.prepareCall(connection, getSchema(), Routines.PYAFMSP_LST_SRV.routine());
			callableStatement = prepareCall(Routines.PYAFMSP_LST_SRV.routine());
			//fine LP PGNTCORE-24
			callableStatement.setString(1, idWwallet);
			callableStatement.setString(2, anagGen);
			callableStatement.setString(3, codFiscGen);
			callableStatement.setString(4, anno);
			callableStatement.execute();
			resultSet=callableStatement.getResultSet();
			
			if ( !resultSet.next() ) {
				listServiziFiglio = new ArrayList<String>();
			} else {
				listServiziFiglio = new ArrayList<String>();
				do {
					String item = "";
					item=resultSet.getString("AFM_CSRVCODI");
					listServiziFiglio.add(item);
				} while(resultSet.next());			
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
			//fine LP PG21XX04 Leak
		} 
		return listServiziFiglio;
	}
}
