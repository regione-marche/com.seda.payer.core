package com.seda.payer.core.wallet.dao;

import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import javax.sql.DataSource;
import javax.sql.rowset.CachedRowSet;
import com.seda.commons.string.Convert;
import com.seda.data.helper.HelperException;
import com.seda.data.spi.PageInfo;
import com.seda.payer.core.dao.Routines;
import com.seda.payer.core.exception.DaoException;
import com.seda.payer.core.handler.BaseDaoHandler;
import com.seda.payer.core.wallet.bean.ConfigurazioneRaccordoPagonet;
import com.seda.payer.core.wallet.bean.EsitoRisposte;
import com.seda.payer.core.wallet.bean.WalletPageList;

public class ConfigurazioneRaccordoPagonetDAOImpl  extends BaseDaoHandler  implements ConfigurazioneRaccordoPagonetDAO  {

		private static final long serialVersionUID = 1L;

		//inizio LP PG21XX04 Leak
		@Deprecated
		//fine LP PG21XX04 Leak
		public ConfigurazioneRaccordoPagonetDAOImpl(DataSource dataSource, String schema) throws SQLException {
			super(dataSource.getConnection(), schema);
		}

		//inizio LP PG21XX04 Leak
		public ConfigurazioneRaccordoPagonetDAOImpl(Connection connection, String schema) throws SQLException {
			super(connection, schema);
		}
		//fine LP PG21XX04 Leak

		public WalletPageList ListConfigurazioneRaccordoPagonet(
				ConfigurazioneRaccordoPagonet configurazioneRaccordoPagonet,
				int rowsPerPage, int pageNumber, String OrderBy)
				throws DaoException {
			CallableStatement callableStatement=null;
			Connection connection = null;
			ResultSet data = null;
			PageInfo pageInfo = null;
			WalletPageList walletPageList = null;
			try {

//				IN I_PAGENO SMALLINT,
//				IN I_ROWSPERPAGE SMALLINT,
//				IN I_RCP_CSOCCSOC VARCHAR(5),
//				IN I_RCP_CUTECUTE VARCHAR(5),
//				IN I_RCP_KANEKENT CHAR(10),
//				OUT O_ROWINI INTEGER,
//				OUT O_ROWEND INTEGER,
//				OUT O_TOTROWS INTEGER,
//				OUT O_TOTPAGES SMALLINT
					
				connection = getConnection();
				//inizio LP PGNTCORE-24
				//callableStatement = Helper.prepareCall(connection, getSchema(), Routines.PYRCPSP_LST.routine());
				callableStatement = prepareCall(Routines.PYRCPSP_LST.routine());
				//fine LP PGNTCORE-24
				callableStatement.setInt(1, pageNumber);                          /* rows per page */
				callableStatement.setInt(2, rowsPerPage);                        /* page number*/
				callableStatement.setString(3,configurazioneRaccordoPagonet.getCodiceSocieta());
				callableStatement.setString(4,configurazioneRaccordoPagonet.getCuteCute());
				callableStatement.setString(5,configurazioneRaccordoPagonet.getChiaveEnte());
				//callableStatement.setString(6,configurazioneRaccordoPagonet.getTipologiaServizio());
				/* we register row start */
				callableStatement.registerOutParameter(6, Types.INTEGER);
				/* we register row end */
				callableStatement.registerOutParameter(7, Types.INTEGER);
				/* we register total rows */
				callableStatement.registerOutParameter(8, Types.INTEGER);
				/* we register total pages */
				callableStatement.registerOutParameter(9, Types.SMALLINT);
				 
				/* we execute procedure */
				if(callableStatement.execute())	{
					pageInfo = new PageInfo();
					pageInfo.setPageNumber(pageNumber);
					pageInfo.setRowsPerPage(rowsPerPage);
					pageInfo.setFirstRow(callableStatement.getInt(6));
					pageInfo.setLastRow(callableStatement.getInt(7));
					pageInfo.setNumRows(callableStatement.getInt(8));
					pageInfo.setNumPages(callableStatement.getInt(9));
					
					data = callableStatement.getResultSet();
					loadWebRowSet(data);
					walletPageList = new WalletPageList(pageInfo, "00","",getWebRowSetXml());
					return walletPageList;
				}
				
				
				
			} catch (SQLException e) {
				e.printStackTrace();
				walletPageList = new WalletPageList(pageInfo, "01","Sql-Exception","");
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
				walletPageList = new WalletPageList(pageInfo, "01","Sql-Exception","");
			} catch (HelperException e) {
				e.printStackTrace();
				walletPageList = new WalletPageList(pageInfo, "01","Sql-Exception","");
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
			return walletPageList;			
		}
		
		public EsitoRisposte delete(ConfigurazioneRaccordoPagonet configurazioneRaccordoPagonet)
				throws DaoException {
			CallableStatement callableStatement=null;
			Connection connection = null;
			EsitoRisposte  esitoRisposte = new EsitoRisposte();
			try {
				connection = getConnection();
				//inizio LP PGNTCORE-24
				//callableStatement = Helper.prepareCall(connection, getSchema(), Routines.PYRCPSP_DEL.routine());
				callableStatement = prepareCall(Routines.PYRCPSP_DEL.routine());
				//fine LP PGNTCORE-24
//				IN I_RCP_CSOCCSOC VARCHAR(5), 
//				IN I_RCP_CUTECUTE VARCHAR(5),
//				IN I_RCP_KANEKENT CHAR(10),
//				IN I_RCP_CRCPTSER CHAR(3),
//				OUT O_RCP_CODESITO VARCHAR(2),
//				OUT O_RCP_MSGESITO VARCHAR(100)

				callableStatement.setString(1, configurazioneRaccordoPagonet.getCodiceSocieta());
				callableStatement.setString(2, configurazioneRaccordoPagonet.getCuteCute());
				callableStatement.setString(3, configurazioneRaccordoPagonet.getChiaveEnte());
				callableStatement.setString(4, configurazioneRaccordoPagonet.getTipologiaServizio());			
				callableStatement.registerOutParameter(5, Types.VARCHAR);
				callableStatement.registerOutParameter(6, Types.VARCHAR);			
				callableStatement.execute();
				esitoRisposte.setCodiceMessaggio(callableStatement.getString(5));
				esitoRisposte.setDescrizioneMessaggio(callableStatement.getString(6));
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
		
		public EsitoRisposte insert(ConfigurazioneRaccordoPagonet configurazioneRaccordoPagonet)
				throws DaoException {

			CallableStatement callableStatement=null;
			Connection connection = null;
			EsitoRisposte esitoRisposte = new EsitoRisposte();
			try {
				connection = getConnection();
				//inizio LP PGNTCORE-24
				//callableStatement = Helper.prepareCall(connection, getSchema(), Routines.PYRCPSP_INS.routine());
				callableStatement = prepareCall(Routines.PYRCPSP_INS.routine());
				//fine LP PGNTCORE-24
//				IN I_RCP_CSOCCSOC VARCHAR(5), 
//				IN I_RCP_CUTECUTE VARCHAR(5),
//				IN I_RCP_KANEKENT CHAR(10),
//				IN I_RCP_CRCPTSER CHAR(3),
//				IN I_RCP_CRCPCOPE  VARCHAR(50),
//				OUT O_RCP_CODESITO VARCHAR(2),
//				OUT O_RCP_MSGESITO VARCHAR(100)
				
				
				callableStatement.setString(1, configurazioneRaccordoPagonet.getCodiceSocieta());
				callableStatement.setString(2, configurazioneRaccordoPagonet.getCuteCute());
				callableStatement.setString(3, configurazioneRaccordoPagonet.getChiaveEnte());
				callableStatement.setString(4, configurazioneRaccordoPagonet.getTipologiaServizio());
				callableStatement.setString(5, configurazioneRaccordoPagonet.getOperatore());
				callableStatement.setString(6, configurazioneRaccordoPagonet.getDescrAutBollettini());
				callableStatement.setString(7, configurazioneRaccordoPagonet.getDescrAutBollettini_2());

				callableStatement.registerOutParameter(8, Types.VARCHAR);
				callableStatement.registerOutParameter(9, Types.VARCHAR);			
				callableStatement.execute();
				esitoRisposte.setCodiceMessaggio(callableStatement.getString(8));
				esitoRisposte.setDescrizioneMessaggio(callableStatement.getString(9));
				callableStatement.execute();
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
		public ConfigurazioneRaccordoPagonet select(ConfigurazioneRaccordoPagonet configurazioneRaccordoPagonet)
				throws DaoException {

			CallableStatement callableStatement=null;
			ResultSet resultSet=null;
			Connection connection = null;
			CachedRowSet rowSet = null;
			try {
				connection = getConnection();

//				IN I_RCP_CSOCCSOC VARCHAR(5), 
//				IN I_RCP_CUTECUTE VARCHAR(5),
//				IN I_RCP_KANEKENT CHAR(10),
//				IN I_RCP_CRCPTSER CHAR(3)
				//inizio LP PGNTCORE-24
				//callableStatement = Helper.prepareCall(connection, getSchema(), Routines.PYRCPSP_SEL.routine());
				callableStatement = prepareCall(Routines.PYRCPSP_SEL.routine());
				//fine LP PGNTCORE-24
				callableStatement.setString(1, configurazioneRaccordoPagonet.getCodiceSocieta());
				callableStatement.setString(2, configurazioneRaccordoPagonet.getCuteCute());
				callableStatement.setString(3, configurazioneRaccordoPagonet.getChiaveEnte());
				callableStatement.setString(4, configurazioneRaccordoPagonet.getTipologiaServizio());
				callableStatement.execute();
				
				resultSet=callableStatement.getResultSet();
				loadWebRowSet(resultSet);
				String selectXml = getWebRowSetXml();
				try {
					rowSet = Convert.stringToWebRowSet(selectXml);
				} catch (IOException e) {
					e.printStackTrace();
				}
				
				if (rowSet.next() ) {
					 String codiceSocieta = rowSet.getString(1);       		
					 String cuteCute = rowSet.getString(2);					
					 String chiaveEnte = rowSet.getString(3);
					 String tipologiaServizio = rowSet.getString(4);
					 String operatore = rowSet.getString(6);
					 String descrizioneAutBollettini = rowSet.getString(7);
					 String descrizioneAutBollettini_2 = rowSet.getString(8);

					 configurazioneRaccordoPagonet = new ConfigurazioneRaccordoPagonet(
							 codiceSocieta, cuteCute, chiaveEnte, tipologiaServizio, operatore,descrizioneAutBollettini,descrizioneAutBollettini_2);
				
					 configurazioneRaccordoPagonet.setAttribute(WalletDAO.SELECT_XML, selectXml);
				}
				
			} catch (SQLException e) {
				throw new DaoException(e);
			} catch (IllegalArgumentException e) {
				throw new DaoException(e);
			} catch (HelperException e) {
				throw new DaoException(e);
			}
			//inizio LP PG21XX04 Leak
			finally {
				if (rowSet != null) {
					try {
						rowSet.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
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
			}
			//fine LP PG21XX04 Leak
			return configurazioneRaccordoPagonet;
		}
		
		public Integer update(ConfigurazioneRaccordoPagonet configurazioneRaccordoPagonet)
				throws DaoException {
			CallableStatement callableStatement=null;
			Connection connection = null;
			int ret=0;
			try {
				connection = getConnection();
				//inizio LP PGNTCORE-24
				//callableStatement = Helper.prepareCall(connection, getSchema(), Routines.PYRCPSP_UPD.routine());
				callableStatement = prepareCall(Routines.PYRCPSP_UPD.routine());
				//fine LP PGNTCORE-24
//				IN I_RCP_CSOCCSOC VARCHAR(5), 
//				IN I_RCP_CUTECUTE VARCHAR(5),
//				IN I_RCP_KANEKENT CHAR(10),
//				IN I_RCP_CRCPTSER CHAR(3),
//				IN I_RCP_CRCPCOPE  VARCHAR(50),
				
				callableStatement.setString(1, configurazioneRaccordoPagonet.getCodiceSocieta());
				callableStatement.setString(2, configurazioneRaccordoPagonet.getCuteCute());
				callableStatement.setString(3, configurazioneRaccordoPagonet.getChiaveEnte());				
				callableStatement.setString(4, configurazioneRaccordoPagonet.getTipologiaServizio());
				callableStatement.setString(5, configurazioneRaccordoPagonet.getOperatore());
				callableStatement.setString(6, configurazioneRaccordoPagonet.getDescrAutBollettini());
				callableStatement.setString(7, configurazioneRaccordoPagonet.getDescrAutBollettini_2());
				callableStatement.registerOutParameter(8, Types.INTEGER);						
				callableStatement.execute();
				ret = callableStatement.getInt(8);
				callableStatement.execute();
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
		
}