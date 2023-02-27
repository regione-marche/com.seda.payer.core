package com.seda.payer.core.dao;

import java.io.IOException;
//inizio LP PG21XX09
//import java.math.BigDecimal;
//fine LP PG21XX09
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;
//inizio LP PG21XX09
//import javax.sql.rowset.CachedRowSet;
//fine LP PG21XX09
import javax.sql.rowset.WebRowSet;

import com.seda.commons.string.Convert;
//inizio LP PG21XX04 Leak
//import com.seda.data.dao.DAOHelper;
//fine LP PG21XX04 Leak
import com.seda.data.helper.Helper;
import com.seda.data.helper.HelperException;
import com.seda.data.spi.PageInfo;
import com.seda.payer.core.bean.ConfigurazioneModello3;
import com.seda.payer.core.bean.ConfigurazioneModello3Pagelist;
import com.seda.payer.core.exception.DaoException;
import com.seda.payer.core.handler.BaseDaoHandler;
import com.seda.payer.core.wallet.bean.EsitoRisposte;

public class ConfigurazioneModello3DaoImpl extends BaseDaoHandler implements ConfigurazioneModello3Dao   {
	private static final long serialVersionUID = 1L;
	
	//inizio LP PG21XX04 Leak
	@Deprecated
	//fine LP PG21XX04 Leak
	public ConfigurazioneModello3DaoImpl(DataSource dataSource, String schema) throws SQLException {
		super(dataSource.getConnection(), schema);
	}
	public ConfigurazioneModello3DaoImpl(Connection connection, String schema) throws SQLException {
		super(connection, schema);
	}
	
	
	public ConfigurazioneModello3 select(ConfigurazioneModello3 configurazioneModello3) throws DaoException {
		CallableStatement callableStatement=null;
		ResultSet resultSet=null;
		Connection connection = null;
		//inizio LP PG21XX04 Leak
		//CachedRowSet rowSet = null;
		//fine LP PG21XX04 Leak
		try {
			connection = getConnection();
			//PG180080 modificare SP e parametri da passare
			callableStatement = Helper.prepareCall(connection, getSchema(), Routines.PYMDTSP_SEL.routine());
			callableStatement.setString(1, configurazioneModello3.getCodiceSocieta());
			callableStatement.setString(2, configurazioneModello3.getCodiceUtente());
			callableStatement.setString(3, configurazioneModello3.getChiaveEnte());
			callableStatement.setString(4, configurazioneModello3.getCodiceIdentificativoDominio());
			callableStatement.setString(5, configurazioneModello3.getAuxDigit());
			callableStatement.setString(6, configurazioneModello3.getCodiceSegregazione());
			callableStatement.setString(7, configurazioneModello3.getCarattereDiServizio()); //SVILUPPO_002_SB
			callableStatement.setString(8, configurazioneModello3.getTipologiaServizio()==null ? "" :  configurazioneModello3.getTipologiaServizio()); //SB PG210140

			callableStatement.execute();

			resultSet=callableStatement.getResultSet();
			if(resultSet.next()) {
				configurazioneModello3 = new ConfigurazioneModello3(resultSet);
			}
			
//			loadWebRowSet(resultSet);
//			String selectXml = getWebRowSetXml();
//			try {
//				rowSet = Convert.stringToWebRowSet(selectXml);
//			} catch (IOException e) {
//				// Auto-generated catch block
//				e.printStackTrace();
//			}
//			
//			
//
//			if (rowSet.next() ) {
//
//				MDT_CSOCCSOC,
//				MDT_CUTECUTE,
//				MDT_KANEKENT,
//				MDT_CMDTIDDO,
//				MDT_CMDTAUXD,
//				MDT_CMDTCDSE,
//				MDT_GMDTTPSE,
//				MDT_GMDTUINT,
//				MDT_CMDTCOPE,
//				MDT_GMDTDAGG
//				String codiceSocieta = rowSet.getString(1);
//				String codiceUtente = rowSet.getString(2);
//				String chiaveEnte = rowSet.getString(3);
//				
//				String codicEnte = rowSet.getString(1);
//				String codiceIdentificativoDominio = rowSet.getString(2);
//				String codiceApplicationCode= rowSet.getString(3);
//				String codiceSegregazione= rowSet.getString(4);
//				String auxDigit = rowSet.getString(5);
//				String flagIuv= rowSet.getString(6);
//				String urlFtp = rowSet.getString(7);
//				String usrFtp = rowSet.getString(8);
//				String passwordFtp = rowSet.getString(9);
//				String directoryFtpDownload = rowSet.getString(10);
//				String directoryFtpUpload= rowSet.getString(11);
//				String pathLocaleInput = rowSet.getString(12);
//				String pathLocaleScarti = rowSet.getString(13);
//				String pathLocaleOutput = rowSet.getString(14);
//				configurazioneModello3 = new ConfigurazioneModello3(codicEnte,codiceIdentificativoDominio,codiceApplicationCode,codiceSegregazione,auxDigit, 
//						flagIuv,urlFtp,usrFtp,passwordFtp,directoryFtpDownload, 
//						directoryFtpUpload,pathLocaleInput,pathLocaleScarti,pathLocaleOutput);
//			}
		} catch (SQLException e) {
			throw new DaoException(e);
		} catch (IllegalArgumentException e) {
			throw new DaoException(e);
		} catch (HelperException e) {
			throw new DaoException(e);
		}finally {
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
		return configurazioneModello3;
	}

	public Integer update(ConfigurazioneModello3 configurazioneModello3 )	throws DaoException {
		CallableStatement callableStatement=null;
		Connection connection = null;
		int ret=0;
		try {
			connection = getConnection();
			callableStatement = Helper.prepareCall(connection, getSchema(), Routines.PYMDTSP_UPD.routine());

//			 IN I_MDT_CSOCCSOC CHAR(5), 	
//			 IN I_MDT_CUTECUTE CHAR(5),		
//			 IN I_MDT_KANEKENT CHAR(10),
//			 IN I_MDT_CMDTIDDO CHAR(16),			
//			 IN I_MDT_CMDTAUXD CHAR(1),			
//			 IN I_MDT_CMDTCDSE VARCHAR(2),		
//			 IN I_MDT_GMDTTPSE CHAR(3),	
//			 IN I_MDT_GMDTUINT VARCHAR(256),
//			 IN I_MDT_CMDTCOPE VARCHAR(50),
//			 OUT O_CODE INTEGER
			
			callableStatement.setString(1, configurazioneModello3.getCodiceSocieta());
			callableStatement.setString(2, configurazioneModello3.getCodiceUtente());
			callableStatement.setString(3, configurazioneModello3.getChiaveEnte());
			callableStatement.setString(4, configurazioneModello3.getCodiceIdentificativoDominio());
			callableStatement.setString(5, configurazioneModello3.getAuxDigit());
			callableStatement.setString(6, configurazioneModello3.getCodiceSegregazione());
			callableStatement.setString(7, configurazioneModello3.getTipologiaServizio());
			callableStatement.setString(8, configurazioneModello3.getUrlIntegrazione());
			callableStatement.setString(9, configurazioneModello3.getCodiceOperatore());
			callableStatement.setString(10, configurazioneModello3.getCarattereDiServizio());  //SVILUPPO_002_SB 22032019
			
			callableStatement.registerOutParameter(11, Types.INTEGER);						
			callableStatement.execute();
			ret = callableStatement.getInt(11);
			//callableStatement.execute();
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
	
	public EsitoRisposte delete(ConfigurazioneModello3 configurazioneModello3)throws DaoException {
		CallableStatement callableStatement=null;
		Connection connection = null;
		EsitoRisposte  esitoRisposte = new EsitoRisposte();
		try {
			connection = getConnection();
			callableStatement = Helper.prepareCall(connection, getSchema(), Routines.PYMDTSP_DEL.routine());
//			IN I_MDT_CSOCCSOC
//			IN I_MDT_CUTECUTE
//			IN I_MDT_KANEKENT
//			IN I_MDT_CMDTIDDO
//			IN I_MDT_CMDTAUXD
//			IN I_MDT_CMDTCDSE
//			OUT O_MDT_CODESITO
//			OUT O_MDT_MSGESITO
			
			callableStatement.setString(1, configurazioneModello3.getCodiceSocieta());
			callableStatement.setString(2, configurazioneModello3.getCodiceUtente());
			callableStatement.setString(3, configurazioneModello3.getChiaveEnte());
			callableStatement.setString(4, configurazioneModello3.getCodiceIdentificativoDominio());
			callableStatement.setString(5, configurazioneModello3.getAuxDigit());
			callableStatement.setString(6, configurazioneModello3.getCodiceSegregazione());
			callableStatement.setString(7, configurazioneModello3.getCarattereDiServizio()); //SVILUPPO_002_SB
			callableStatement.registerOutParameter(8, Types.VARCHAR);
			callableStatement.registerOutParameter(9, Types.VARCHAR);			
			callableStatement.execute();
			esitoRisposte.setCodiceMessaggio(callableStatement.getString(8));
			esitoRisposte.setDescrizioneMessaggio(callableStatement.getString(9));
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
	public EsitoRisposte insert(ConfigurazioneModello3 configurazioneModello3 )	throws DaoException {
		CallableStatement callableStatement=null;
		Connection connection = null;
		EsitoRisposte  esitoRisposte = new EsitoRisposte();
		try {
			connection = getConnection();
			callableStatement = Helper.prepareCall(connection, getSchema(), Routines.PYMDTSP_INS.routine());
			
//			 IN I_MDT_CSOCCSOC
//			 IN I_MDT_CUTECUTE
//			 IN I_MDT_KANEKENT
//			 IN I_MDT_CMDTIDDO			
//			 IN I_MDT_CMDTAUXD
//			 IN I_MDT_CMDTCDSE
//			 IN I_MDT_GMDTTPSE
//			 IN I_MDT_GMDTUINT
//			 IN I_MDT_CMDTCOPE
//			 OUT O_MDT_CODESITO
//			 OUT O_MDT_MSGESITO

			callableStatement.setString(1, configurazioneModello3.getCodiceSocieta());
			callableStatement.setString(2, configurazioneModello3.getCodiceUtente());
			callableStatement.setString(3, configurazioneModello3.getChiaveEnte());
			callableStatement.setString(4, configurazioneModello3.getCodiceIdentificativoDominio());
			callableStatement.setString(5, configurazioneModello3.getAuxDigit());
			callableStatement.setString(6, configurazioneModello3.getCodiceSegregazione());
			callableStatement.setString(7, configurazioneModello3.getTipologiaServizio());
			callableStatement.setString(8, configurazioneModello3.getUrlIntegrazione());
			callableStatement.setString(9, configurazioneModello3.getCodiceOperatore());
			callableStatement.setString(10, configurazioneModello3.getCarattereDiServizio()); //SVILUPPO_002_SB
			callableStatement.registerOutParameter(11, Types.VARCHAR);		
			callableStatement.registerOutParameter(12, Types.VARCHAR);
			callableStatement.execute();
			esitoRisposte.setCodiceMessaggio(callableStatement.getString(11));
			esitoRisposte.setDescrizioneMessaggio(callableStatement.getString(12));
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
	
	//inizio LP PG21XX09
	//public ConfigurazioneModello3Pagelist configurazioneModello3List(ConfigurazioneModello3 configurazioneModello3,int rowsPerPage,
	//		int pageNumber, String OrderBy) throws DaoException {
	public ConfigurazioneModello3Pagelist configurazioneModello3ListTail(
			ConfigurazioneModello3 configurazioneModello3,
			int rowsPerPage,
			int pageNumber,
			String OrderBy,
			boolean bCloseConnection
	) throws DaoException {
	//fine LP PG21XX09
		CallableStatement callableStatement=null;

		Connection connection = null;
		ResultSet data = null;
		//inizio LP PG21XX04 Leak
		WebRowSet tmpRowSet = null;
		//fine LP PG21XX04 Leak
		PageInfo pageInfo = null; 
		ConfigurazioneModello3Pagelist configurazioneModello3Pagelist = null;
		String[] configurazioneModello3Lst  = new String[2];
		List<ConfigurazioneModello3> listConfigurazioneModello3 = null;
		try {
			connection = getConnection();
			callableStatement = Helper.prepareCall(connection, getSchema(),Routines.PYMDTSP_LST.routine());
//			   IN I_MDT_CSOCCSOC
//             IN I_MDT_CUTECUTE
//             IN I_MDT_KANEKENT
//             IN I_MDT_CMDTIDDO
//             IN I_MDT_CMDTAUXD
//             IN I_MDT_CMDTCDSE
//             IN I_ORDER
//             IN I_ROWSXPAGE
//             IN I_PAGENO 
//             OUT O_ROWINI 
//             OUT O_ROWEND
//             OUT O_TOTROWS 
//             OUT O_TOTPAGES
			
			callableStatement.setString(1,configurazioneModello3.getCodiceSocieta());
			callableStatement.setString(2,configurazioneModello3.getCodiceUtente());
			callableStatement.setString(3,configurazioneModello3.getChiaveEnte());
			callableStatement.setString(4,configurazioneModello3.getCodiceIdentificativoDominio());
			callableStatement.setString(5,configurazioneModello3.getAuxDigit());
			callableStatement.setString(6,configurazioneModello3.getCodiceSegregazione());
			callableStatement.setString(7,configurazioneModello3.getCarattereDiServizio());
			callableStatement.setString(8,OrderBy);
			callableStatement.setInt(9, rowsPerPage);                        /* page number*/
			callableStatement.setInt(10, pageNumber);                          /* rows per page */
			callableStatement.registerOutParameter(11, Types.INTEGER);
			/* we register row end */
			callableStatement.registerOutParameter(12, Types.INTEGER);
			/* we register total rows */
			callableStatement.registerOutParameter(13, Types.INTEGER);
			/* we register total pages */
			callableStatement.registerOutParameter(14, Types.SMALLINT);

			/* we execute procedure */

			if(callableStatement.execute())	{
				pageInfo = new PageInfo();
				pageInfo.setPageNumber(pageNumber);
				pageInfo.setRowsPerPage(rowsPerPage);
				pageInfo.setFirstRow(callableStatement.getInt(11));
				pageInfo.setLastRow(callableStatement.getInt(12));
				pageInfo.setNumRows(callableStatement.getInt(13));
				pageInfo.setNumPages(callableStatement.getInt(14));

				data = callableStatement.getResultSet();
				loadWebRowSet(data);
				configurazioneModello3Lst[0] = getWebRowSetXml();
			

				//inizio LP PG21XX04 Leak
				//WebRowSet tmpRowSet = Convert.stringToWebRowSet(configurazioneModello3Lst[0]);
				tmpRowSet = Convert.stringToWebRowSet(configurazioneModello3Lst[0]);
				//fine LP PG21XX04 Leak
				listConfigurazioneModello3 = new ArrayList<ConfigurazioneModello3>();
				while(tmpRowSet.next()) {
					ConfigurazioneModello3 item = new ConfigurazioneModello3(tmpRowSet);
					listConfigurazioneModello3.add(item);
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
					data=callableStatement.getResultSet();
					loadWebRowSet(data);
					configurazioneModello3Lst[1] = getWebRowSetXml();
				}

				
			}
			configurazioneModello3Pagelist = new ConfigurazioneModello3Pagelist(pageInfo, "00","",configurazioneModello3Lst, listConfigurazioneModello3);
			return configurazioneModello3Pagelist;

		} catch (SQLException e) {
			e.printStackTrace();
			configurazioneModello3Pagelist = new ConfigurazioneModello3Pagelist(pageInfo, "01","Sql-Exception","", null);
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
			configurazioneModello3Pagelist = new ConfigurazioneModello3Pagelist(pageInfo, "01","Sql-Exception","", null);
		} catch (HelperException e) {
			e.printStackTrace();
			configurazioneModello3Pagelist = new ConfigurazioneModello3Pagelist(pageInfo, "01","Sql-Exception","", null);
		} catch (IOException e) {
			e.printStackTrace();
			configurazioneModello3Pagelist = new ConfigurazioneModello3Pagelist(pageInfo, "01","Sql-Exception","", null);
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
			//inizio LP PG21XX09
			//if (connection != null) {
			if (bCloseConnection && connection != null) {
			//fine LP PG21XX09
				try {
					connection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			//fine LP PG21XX04 Leak
		}
		return configurazioneModello3Pagelist;
	}

	//inizio LP PG21XX09
	public ConfigurazioneModello3Pagelist configurazioneModello3ListNoCloseConnection(ConfigurazioneModello3 configurazioneModello3,int rowsPerPage,
			int pageNumber, String OrderBy) throws DaoException {
		return configurazioneModello3ListTail(configurazioneModello3, rowsPerPage, pageNumber, OrderBy, false);
	}

	public ConfigurazioneModello3Pagelist configurazioneModello3List(ConfigurazioneModello3 configurazioneModello3,int rowsPerPage,
			int pageNumber, String OrderBy) throws DaoException {
		return configurazioneModello3ListTail(configurazioneModello3, rowsPerPage, pageNumber, OrderBy, true);
	}
	//fine LP PG21XX09
}
