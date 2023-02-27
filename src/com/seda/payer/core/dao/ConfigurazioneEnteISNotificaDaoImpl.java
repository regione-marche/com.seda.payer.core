package com.seda.payer.core.dao;

import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;
import javax.sql.rowset.WebRowSet;

import com.seda.commons.string.Convert;
//inizio LP PG21XX04 Leak
//import com.seda.data.dao.DAOHelper;
//fine LP PG21XX04 Leak
import com.seda.data.helper.Helper;
import com.seda.data.helper.HelperException;
import com.seda.data.spi.PageInfo;
import com.seda.payer.core.bean.ConfigurazioneEnteISNotifica;
import com.seda.payer.core.bean.ConfigurazioneEnteISNotificaPagelist;
import com.seda.payer.core.exception.DaoException;
import com.seda.payer.core.handler.BaseDaoHandler;
import com.seda.payer.core.wallet.bean.EsitoRisposte;

public class ConfigurazioneEnteISNotificaDaoImpl extends BaseDaoHandler implements ConfigurazioneEnteISNotificaDao   {
	private static final long serialVersionUID = 1L;

	//inizio LP PG21XX04 Leak
	@Deprecated
	//fine LP PG21XX04 Leak
	public ConfigurazioneEnteISNotificaDaoImpl(DataSource dataSource, String schema) throws SQLException {
		super(dataSource.getConnection(), schema);
	}

	public ConfigurazioneEnteISNotificaDaoImpl(Connection connection, String schema) throws SQLException {
		super(connection, schema);
	}

	public ConfigurazioneEnteISNotifica select(ConfigurazioneEnteISNotifica configurazioneEnteISNotifica) throws DaoException {
		CallableStatement callableStatement = null;
		ResultSet resultSet = null;
		Connection connection = null;
		try {
			connection = getConnection();
			//callableStatement = Helper.prepareCall(connection, getSchema(), Routines.PYMDTSP_SEL.routine()); //TODO
			callableStatement = Helper.prepareCall(connection, getSchema(), "PYENOSP_SEL");
			callableStatement.setString(1, configurazioneEnteISNotifica.getCodiceUtente());
			callableStatement.setString(2, configurazioneEnteISNotifica.getChiaveEnte());
			callableStatement.setString(3, configurazioneEnteISNotifica.getCodiceImpostaServizio());
			callableStatement.execute();

			resultSet=callableStatement.getResultSet();
			if(resultSet.next()) {
				configurazioneEnteISNotifica = new ConfigurazioneEnteISNotifica(resultSet);
			}
			
		} catch (SQLException e) {
			throw new DaoException(e);
		} catch (IllegalArgumentException e) {
			throw new DaoException(e);
		} catch (HelperException e) {
			throw new DaoException(e);
		} finally {
			//inizio LP PG21XX04 Leak
			//	DAOHelper.closeIgnoringException(callableStatement);
			//	DAOHelper.closeIgnoringException(connection);
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
		return configurazioneEnteISNotifica;
	}

	public ConfigurazioneEnteISNotifica select(String cutecute, String ente, String codiceIS ) throws DaoException {
		CallableStatement callableStatement = null;
		ResultSet resultSet = null;
		Connection connection = null;
		ConfigurazioneEnteISNotifica configurazioneEnteISNotifica = null;
		try {
			connection = getConnection();
			//callableStatement = Helper.prepareCall(connection, getSchema(), Routines.PYMDTSP_SEL.routine()); //TODO
			callableStatement = Helper.prepareCall(connection, getSchema(), "PYENOSP_SEL2");
			System.out.println("PYENOSP_SEL2 - cutecute: " + cutecute);
			System.out.println("PYENOSP_SEL2 - ente: " + ente);
			System.out.println("PYENOSP_SEL2 - codiceIS: " + codiceIS);
			callableStatement.setString(1, cutecute);
			callableStatement.setString(2, ente);
			callableStatement.setString(3, codiceIS);

			callableStatement.execute();

			resultSet=callableStatement.getResultSet();
			if(resultSet.next()) {
				configurazioneEnteISNotifica = new ConfigurazioneEnteISNotifica(resultSet);
			}
			
		} catch (SQLException e) {
			throw new DaoException(e);
		} catch (IllegalArgumentException e) {
			throw new DaoException(e);
		} catch (HelperException e) {
			throw new DaoException(e);
		} finally {
			//inizio LP PG21XX04 Leak
			//		DAOHelper.closeIgnoringException(callableStatement);
			//		DAOHelper.closeIgnoringException(connection);
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
		return configurazioneEnteISNotifica;
	}
	
	public Integer update(ConfigurazioneEnteISNotifica configurazioneEnteISNotifica )	throws DaoException {
		CallableStatement callableStatement=null;
		Connection connection = null;
		int ret=0;
		try {
			connection = getConnection();
			//callableStatement = Helper.prepareCall(connection, getSchema(), Routines.PYMDTSP_UPD.routine()); //TODO
			callableStatement = Helper.prepareCall(connection, getSchema(), "PYENOSP_UPD");
			callableStatement.setString(1, configurazioneEnteISNotifica.getCodiceUtente());
			callableStatement.setString(2, configurazioneEnteISNotifica.getChiaveEnte());
			callableStatement.setString(3, configurazioneEnteISNotifica.getCodiceImpostaServizio());
			callableStatement.setString(4, configurazioneEnteISNotifica.getFlagNotificaAllegato());
			callableStatement.setString(5, configurazioneEnteISNotifica.getCodiceOperatore());
			callableStatement.registerOutParameter(6, Types.INTEGER);						
			callableStatement.execute();
			ret = callableStatement.getInt(6);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DaoException(e);
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
			throw new DaoException(e);
		} catch (HelperException e) {
			e.printStackTrace();
			throw new DaoException(e);
		} finally {
			//inizio LP PG21XX04 Leak
			//DAOHelper.closeIgnoringException(connection);
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
		return ret;
	}
	
	public EsitoRisposte delete(ConfigurazioneEnteISNotifica configurazioneEnteISNotifica)throws DaoException {
		CallableStatement callableStatement=null;
		Connection connection = null;
		EsitoRisposte  esitoRisposte = new EsitoRisposte();
		try {
			connection = getConnection();
			//callableStatement = Helper.prepareCall(connection, getSchema(), Routines.PYMDTSP_DEL.routine());
			callableStatement = Helper.prepareCall(connection, getSchema(), "PYENOSP_DEL");
			callableStatement.setString(1, configurazioneEnteISNotifica.getCodiceUtente());
			callableStatement.setString(2, configurazioneEnteISNotifica.getChiaveEnte());
			callableStatement.setString(3, configurazioneEnteISNotifica.getCodiceImpostaServizio());
			callableStatement.registerOutParameter(4, Types.VARCHAR);
			callableStatement.registerOutParameter(5, Types.VARCHAR);			
			callableStatement.execute();
			esitoRisposte.setCodiceMessaggio(callableStatement.getString(4));
			esitoRisposte.setDescrizioneMessaggio(callableStatement.getString(5));
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DaoException(e);
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
			throw new DaoException(e);
		} catch (HelperException e) {
			e.printStackTrace();
			throw new DaoException(e);
		} finally {
			//inizio LP PG21XX04 Leak
			//DAOHelper.closeIgnoringException(connection);
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
		return esitoRisposte;
	}
	public EsitoRisposte insert(ConfigurazioneEnteISNotifica configurazioneEnteISNotifica )	throws DaoException {
		CallableStatement callableStatement=null;
		Connection connection = null;
		EsitoRisposte  esitoRisposte = new EsitoRisposte();
		try {
			connection = getConnection();
			//callableStatement = Helper.prepareCall(connection, getSchema(), Routines.PYMDTSP_INS.routine()); //TODO
			callableStatement = Helper.prepareCall(connection, getSchema(), "PYENOSP_INS");
			callableStatement.setString(1, configurazioneEnteISNotifica.getCodiceUtente());
			callableStatement.setString(2, configurazioneEnteISNotifica.getChiaveEnte());
			callableStatement.setString(3, configurazioneEnteISNotifica.getCodiceImpostaServizio());
			callableStatement.setString(4, configurazioneEnteISNotifica.getFlagNotificaAllegato());
			callableStatement.setString(5, configurazioneEnteISNotifica.getCodiceOperatore());
			callableStatement.registerOutParameter(6, Types.VARCHAR);		
			callableStatement.registerOutParameter(7, Types.VARCHAR);
			callableStatement.execute();
			esitoRisposte.setCodiceMessaggio(callableStatement.getString(6));
			esitoRisposte.setDescrizioneMessaggio(callableStatement.getString(7));
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DaoException(e);
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
			throw new DaoException(e);
		} catch (HelperException e) {
			e.printStackTrace();
			throw new DaoException(e);
		} finally {
			//inizio LP PG21XX04 Leak
			//DAOHelper.closeIgnoringException(connection);
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
		return esitoRisposte;
	}
	
	public ConfigurazioneEnteISNotificaPagelist configurazioneEnteISNotificaList(ConfigurazioneEnteISNotifica configurazioneEnteISNotifica,int rowsPerPage,
			int pageNumber, String OrderBy) throws DaoException {
		CallableStatement callableStatement=null;

		Connection connection = null;
		ResultSet data = null;
		//inizio LP PG21XX04 Leak
		WebRowSet tmpRowSet = null; 
		//fine LP PG21XX04 Leak
		PageInfo pageInfo = null; 
		ConfigurazioneEnteISNotificaPagelist configurazioneEnteISNotificaPagelist = null;
		String[] configurazioneEnteISNotificaLst  = new String[2];
		List<ConfigurazioneEnteISNotifica> listConfigurazioneEnteISNotifica = null;
		try {
			connection = getConnection();
			//callableStatement = Helper.prepareCall(connection, getSchema(),Routines.PYMDTSP_LST.routine());  //TODO PYENOSP_LST2
			callableStatement = Helper.prepareCall(connection, getSchema(),"PYENOSP_LST2");  //TODO 
			callableStatement.setString(1,configurazioneEnteISNotifica.getCodiceUtente());
			callableStatement.setString(2,configurazioneEnteISNotifica.getChiaveEnte());
			callableStatement.setString(3,configurazioneEnteISNotifica.getCodiceImpostaServizio());
			callableStatement.setString(4,configurazioneEnteISNotifica.getFlagNotificaAllegato());
			callableStatement.setString(5,OrderBy);
			callableStatement.setInt(6, rowsPerPage);                         /* page number*/
			callableStatement.setInt(7, pageNumber);                          /* rows per page */
			callableStatement.registerOutParameter(8, Types.INTEGER);
			/* we register row end */
			callableStatement.registerOutParameter(9, Types.INTEGER);
			/* we register total rows */
			callableStatement.registerOutParameter(10, Types.INTEGER);
			/* we register total pages */
			callableStatement.registerOutParameter(11, Types.SMALLINT);

			/* we execute procedure */

			if(callableStatement.execute())	{
				pageInfo = new PageInfo();
				pageInfo.setPageNumber(pageNumber);
				pageInfo.setRowsPerPage(rowsPerPage);
				pageInfo.setFirstRow(callableStatement.getInt(8));
				pageInfo.setLastRow(callableStatement.getInt(9));
				pageInfo.setNumRows(callableStatement.getInt(10));
				pageInfo.setNumPages(callableStatement.getInt(11));

				data = callableStatement.getResultSet();
				loadWebRowSet(data);
				configurazioneEnteISNotificaLst[0] = getWebRowSetXml();

				//inizio LP PG21XX04 Leak
				//WebRowSet tmpRowSet = Convert.stringToWebRowSet(configurazioneEnteISNotificaLst[0]);
				tmpRowSet = Convert.stringToWebRowSet(configurazioneEnteISNotificaLst[0]);
				//fine LP PG21XX04 Leak
				listConfigurazioneEnteISNotifica = new ArrayList<ConfigurazioneEnteISNotifica>();
				while(tmpRowSet.next()) {
					ConfigurazioneEnteISNotifica item = new ConfigurazioneEnteISNotifica(tmpRowSet);
					listConfigurazioneEnteISNotifica.add(item);
				}
				
				if(callableStatement.getMoreResults()){
					//inizio LP PG21XX04 Leak
					if (data != null) {
						try {
							data.close();
						} catch (SQLException e) {
							e.printStackTrace();
						}
					}
					//fine LP PG21XX04 Leak
					data = callableStatement.getResultSet();
					loadWebRowSet(data);
					configurazioneEnteISNotificaLst[1] = getWebRowSetXml();
				}

				
			}
			configurazioneEnteISNotificaPagelist = new ConfigurazioneEnteISNotificaPagelist(pageInfo, "00","",configurazioneEnteISNotificaLst, listConfigurazioneEnteISNotifica);
			return configurazioneEnteISNotificaPagelist;

		} catch (SQLException e) {
			e.printStackTrace();
			configurazioneEnteISNotificaPagelist = new ConfigurazioneEnteISNotificaPagelist(pageInfo, "01","Sql-Exception","", null);
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
			configurazioneEnteISNotificaPagelist = new ConfigurazioneEnteISNotificaPagelist(pageInfo, "01","Sql-Exception","", null);
		} catch (HelperException e) {
			e.printStackTrace();
			configurazioneEnteISNotificaPagelist = new ConfigurazioneEnteISNotificaPagelist(pageInfo, "01","Sql-Exception","", null);
		} catch (IOException e) {
			e.printStackTrace();
			configurazioneEnteISNotificaPagelist = new ConfigurazioneEnteISNotificaPagelist(pageInfo, "01","Sql-Exception","", null);
		} finally {
			//inizio LP PG21XX04 Leak
			//DAOHelper.closeIgnoringException(callableStatement);
			//DAOHelper.closeIgnoringException(connection);
			if (tmpRowSet != null) {
				try {
					tmpRowSet.close();
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
		return configurazioneEnteISNotificaPagelist;
	}

}
