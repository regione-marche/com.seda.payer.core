package com.seda.payer.core.mercato.dao;

import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.GregorianCalendar;

import javax.sql.DataSource;
import javax.sql.rowset.CachedRowSet;

import com.seda.commons.string.Convert;
import com.seda.data.dao.DAOHelper;
import com.seda.data.helper.Helper;
import com.seda.data.helper.HelperException;
import com.seda.data.spi.PageInfo;
import com.seda.payer.core.dao.Routines;
import com.seda.payer.core.exception.DaoException;
import com.seda.payer.core.handler.BaseDaoHandler;
import com.seda.payer.core.mercato.bean.ConfigurazioneAreaMercantile;
import com.seda.payer.core.mercato.bean.EsitoRisposte;
import com.seda.payer.core.mercato.bean.MercatoPageList;
import com.seda.payer.core.mercato.dao.MercatoDAO;

public class ConfigurazioneAreaMercantileDAOImpl extends BaseDaoHandler  implements ConfigurazioneAreaMercantileDAO  {
	//private static final long serialVersionUID = 1L;
	//inizio LP PG21XX04 Leak
	@Deprecated
	//fine LP PG21XX04 Leak
	public ConfigurazioneAreaMercantileDAOImpl(DataSource dataSource, String schema) throws SQLException {
		super(dataSource.getConnection(), schema);
	}

	//inizio LP PG21XX04 Leak
	public ConfigurazioneAreaMercantileDAOImpl(Connection connection, String schema) throws SQLException {
		super(connection, schema);
	}
	//fine LP PG21XX04 Leak

	public MercatoPageList ListConfigurazioneAreaMercantile(
			ConfigurazioneAreaMercantile configurazioneAreaMercantile,
			int rowsPerPage, int pageNumber, String OrderBy)
			throws DaoException {
		CallableStatement callableStatement=null;
		Connection connection = null;
		ResultSet data = null;
		PageInfo pageInfo = null;
		MercatoPageList mercatoPageList = null;
		try {

//			    IN I_PAGENO SMALLINT,
//				IN I_ROWSPERPAGE SMALLINT,
//				IN I_MRC_CSOCCSOC VARCHAR(5),
//				IN I_MRC_CUTECUTE VARCHAR(5),
//				IN I_MRC_KANEKENT CHAR(10),
//				IN I_MRC_CMRCCTIP VARCHAR(10),
//				OUT O_ROWINI INTEGER,
//				OUT O_ROWEND INTEGER,
//				OUT O_TOTROWS INTEGER,
//				OUT O_TOTPAGES SMALLINT
				
			connection = getConnection();
			callableStatement = Helper.prepareCall(connection, getSchema(), Routines.PYMRCSP_LST.routine());
			callableStatement.setInt(1, pageNumber);                          /* rows per page */
			callableStatement.setInt(2, rowsPerPage);                        /* page number*/
			callableStatement.setString(3,configurazioneAreaMercantile.getCodiceSocieta());
			callableStatement.setString(4,configurazioneAreaMercantile.getCuteCute());
			callableStatement.setString(5,configurazioneAreaMercantile.getChiaveEnte());
			callableStatement.setString(6,configurazioneAreaMercantile.getCodiceAreaMercantile());
			callableStatement.setString(7,configurazioneAreaMercantile.getDescrizioneAreaMercantile());
			/* we register row start */
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
				mercatoPageList = new MercatoPageList(pageInfo, "00","",getWebRowSetXml());
				return mercatoPageList;
			}
			
			
			
		} catch (SQLException e) {
			e.printStackTrace();
			mercatoPageList = new MercatoPageList(pageInfo, "01","Sql-Exception","");
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
			mercatoPageList = new MercatoPageList(pageInfo, "01","Sql-Exception","");
		} catch (HelperException e) {
			e.printStackTrace();
			mercatoPageList = new MercatoPageList(pageInfo, "01","Sql-Exception","");
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
		return mercatoPageList;	
	}

	public EsitoRisposte delete(ConfigurazioneAreaMercantile configurazioneAreaMercantile)
			throws DaoException 
	{
		CallableStatement callableStatement=null;
		Connection connection = null;
		EsitoRisposte  esitoRisposte = new EsitoRisposte();
		try {
			connection = getConnection();
			callableStatement = Helper.prepareCall(connection, getSchema(), Routines.PYMRCSP_DEL.routine());
//			IN I_MRC_CSOCCSOC VARCHAR(5), 
//			IN I_MRC_CUTECUTE VARCHAR(5),
//			IN I_MRC_KANEKENT CHAR(10),
//			IN I_MRC_KMRCKMRC VARCHAR(10),
//			OUT O_PPS_CODESITO VARCHAR(2),
//			OUT O_PPS_MSGESITO VARCHAR(100)

			callableStatement.setString(1, configurazioneAreaMercantile.getCodiceSocieta());
			callableStatement.setString(2, configurazioneAreaMercantile.getCuteCute());
			callableStatement.setString(3, configurazioneAreaMercantile.getChiaveEnte());
			callableStatement.setString(4, configurazioneAreaMercantile.getCodiceKeyAreaMercantile());			
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

	public EsitoRisposte insert(ConfigurazioneAreaMercantile configurazioneAreaMercantile)
		throws DaoException 
	{
		CallableStatement callableStatement=null;
		Connection connection = null;
		EsitoRisposte esitoRisposte = new EsitoRisposte();
		try {
			connection = getConnection();
			callableStatement = Helper.prepareCall(connection, getSchema(), Routines.PYMRCSP_INS.routine());

//					IN I_MRC_CSOCCSOC VARCHAR(5), 
//					IN I_MRC_CUTECUTE VARCHAR(5),
//					IN I_MRC_KANEKENT CHAR(10),
//					IN I_MRC_CMRCCTIP VARCHAR(10),
//					IN I_MRC_CMRCDSAM VARCHAR(70),
//					IN_I_MRC_GMRCDTIN TIMESTAMP,
//					IN_I_MRC_GMRCDTFN TIMESTAMP,
//					OUT O_PPS_CODESITO VARCHAR(2),
//					OUT O_PPS_MSGESITO VARCHAR(100)			
//			private String codiceSocieta;        		//	"MRC_CSOCCSOC" VARCHAR(5)
//			private String cuteCute;					//	"MRC_CUTECUTE" VARCHAR(5)
//			private String chiaveEnte;					//	"MRC_KANEKENT" CHAR(10)
//			private String codiceAreaMercantile;  		//	"MRC_CMRCCTIP" VARCHAR(10)
// 			private String descrizioneAreaMercantile;	//	"MRC_CMRCDSAM" VARCHAR(70)
//			private Calendar dataInizioValidita;		//  "MRC_GMRCDTIN" TIMESTAMP
//			private Calendar dataFineValidita;			//  "MRC_GMRCDTFN" TIMESTAMP
			
			callableStatement.setString(1, configurazioneAreaMercantile.getCodiceSocieta());
			callableStatement.setString(2, configurazioneAreaMercantile.getCuteCute());
			callableStatement.setString(3, configurazioneAreaMercantile.getChiaveEnte());
			callableStatement.setString(4, configurazioneAreaMercantile.getCodiceAreaMercantile());
			callableStatement.setString(5, configurazioneAreaMercantile.getDescrizioneAreaMercantile());
			callableStatement.setTimestamp(6, new java.sql.Timestamp(configurazioneAreaMercantile.getDataInizioValidita().getTimeInMillis()));
			if (configurazioneAreaMercantile.getDataFineValidita()==null) {
				callableStatement.setTimestamp(7, null);
			} else {
			    callableStatement.setTimestamp(7, new java.sql.Timestamp(configurazioneAreaMercantile.getDataFineValidita().getTimeInMillis()));
			}

			callableStatement.registerOutParameter(8, Types.VARCHAR);
			callableStatement.registerOutParameter(9, Types.VARCHAR);			
			callableStatement.execute();
			esitoRisposte.setCodiceMessaggio(callableStatement.getString(8));
			esitoRisposte.setDescrizioneMessaggio(callableStatement.getString(9));
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
		return esitoRisposte;
	}

	public ConfigurazioneAreaMercantile select(ConfigurazioneAreaMercantile configurazioneAreaMercantile)
			throws DaoException 
	{
		CallableStatement callableStatement=null;
		ResultSet resultSet=null;
		Connection connection = null;
		CachedRowSet rowSet = null;
		try {
			connection = getConnection();
		
			callableStatement = Helper.prepareCall(connection, getSchema(), Routines.PYMRCSP_SEL.routine());
			callableStatement.setString(1, configurazioneAreaMercantile.getCodiceSocieta());
			callableStatement.setString(2, configurazioneAreaMercantile.getCuteCute());
			callableStatement.setString(3, configurazioneAreaMercantile.getChiaveEnte());
			callableStatement.setString(4, configurazioneAreaMercantile.getCodiceKeyAreaMercantile());
			callableStatement.execute();
			
			resultSet=callableStatement.getResultSet();
			loadWebRowSet(resultSet);
			String selectXml = getWebRowSetXml();
			try {
				rowSet = Convert.stringToWebRowSet(selectXml);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			if (rowSet.next() ) {
				 String codiceSocieta = rowSet.getString(2);       		
				 String cuteCute = rowSet.getString(3);					
				 String chiaveEnte = rowSet.getString(4);
				 String codiceKeyAreaMercantile=rowSet.getString(1);
				 String codiceAreaMercantile = rowSet.getString(5);
				 String descrizioneAreaMercantile = rowSet.getString(6);
				 GregorianCalendar dataInizioValidita = new GregorianCalendar();
				 dataInizioValidita.setTimeInMillis(rowSet.getTimestamp(7).getTime());
				 
				 GregorianCalendar dataFineValidita = new GregorianCalendar();
				 if (rowSet.getTimestamp(8) == null) {
				 	dataFineValidita.setTimeInMillis(0);
				 } else {
					 dataFineValidita.setTimeInMillis(rowSet.getTimestamp(8).getTime());
				 }

				 configurazioneAreaMercantile = new ConfigurazioneAreaMercantile(
						 codiceSocieta, cuteCute, chiaveEnte, codiceKeyAreaMercantile, 
						 codiceAreaMercantile, descrizioneAreaMercantile, dataInizioValidita, dataFineValidita);
			
				 configurazioneAreaMercantile.setAttribute(MercatoDAO.SELECT_XML, selectXml);
			} 
			
		} catch (SQLException e) {
			throw new DaoException(e);
		} catch (IllegalArgumentException e) {
			throw new DaoException(e);
		} catch (HelperException e) {
			throw new DaoException(e);
		}finally {
			//inizio LP PG21XX04 Leak
			//DAOHelper.closeIgnoringException(connection);
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
			//fine LP PG21XX04 Leak
		}
		return configurazioneAreaMercantile;
	}
	
	public ArrayList<ConfigurazioneAreaMercantile> getAllPerDescr(ConfigurazioneAreaMercantile configurazioneAreaMercantile) throws DaoException {
//		IN I_MRC_CSOCCSOC VARCHAR(5), 
//		IN I_MRC_CUTECUTE VARCHAR(5),
//		IN I_MRC_KANEKENT CHAR(10),
//      IN I_MRC_CMRCDSAM VARCHAR(70)
		CallableStatement callableStatement=null;
		ArrayList<ConfigurazioneAreaMercantile> configurazioneAreaMercantileList = null;
		Connection connection = null;
		ResultSet resultSet=null;
		try {
			connection = getConnection();
			callableStatement = Helper.prepareCall(connection, getSchema(), Routines.PYMRCSP_APD.routine());
			callableStatement.setString(1, configurazioneAreaMercantile.getCodiceSocieta());
			callableStatement.setString(2, configurazioneAreaMercantile.getCuteCute());
			callableStatement.setString(3, configurazioneAreaMercantile.getChiaveEnte());
			callableStatement.setString(4, configurazioneAreaMercantile.getDescrizioneAreaMercantile());
			callableStatement.execute();
			resultSet=callableStatement.getResultSet();
			
			if ( !resultSet.next() ) {
				configurazioneAreaMercantileList = new ArrayList<ConfigurazioneAreaMercantile>();
			} else {
				configurazioneAreaMercantileList = new ArrayList<ConfigurazioneAreaMercantile>();
				do {
					ConfigurazioneAreaMercantile item = new ConfigurazioneAreaMercantile();
					item.setCodiceKeyAreaMercantile(resultSet.getString("MRC_KMRCKMRC"));
					item.setCodiceAreaMercantile(resultSet.getString("MRC_CMRCCTIP"));
					item.setDescrizioneAreaMercantile(resultSet.getString("MRC_CMRCDSAM"));
					GregorianCalendar DataIniVal = new GregorianCalendar();
					DataIniVal.setTimeInMillis(resultSet.getTimestamp("MRC_GMRCDTIN").getTime());
					item.setDataInizioValidita(DataIniVal);
					GregorianCalendar DataFinVal = new GregorianCalendar();
					DataFinVal.setTimeInMillis(resultSet.getTimestamp("MRC_GMRCDTFN").getTime());
					item.setDataFineValidita(DataFinVal);
					configurazioneAreaMercantileList.add(item);
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
		
		return configurazioneAreaMercantileList;
	}		
				
	
	public ArrayList<ConfigurazioneAreaMercantile> listAreaMercantile(ConfigurazioneAreaMercantile configurazioneAreaMercantile) throws DaoException {
//		IN I_MRC_CSOCCSOC VARCHAR(5), 
//		IN I_MRC_CUTECUTE VARCHAR(5),
//		IN I_MRC_KANEKENT CHAR(10),
//		IN I_MRC_CMRCCTIP VARCHAR(10),
//		IN I_MRC_CMRCDSAM VARCHAR(10),
		CallableStatement callableStatement=null;
		ArrayList<ConfigurazioneAreaMercantile> configurazioneAreaMercantileList = null;
		Connection connection = null;
		ResultSet resultSet=null;
		try {
			connection = getConnection();
			callableStatement = Helper.prepareCall(connection, getSchema(), Routines.PYMRCSP_TOT.routine());
			callableStatement.setString(1, configurazioneAreaMercantile.getCodiceSocieta());
			callableStatement.setString(2, configurazioneAreaMercantile.getCuteCute());
			callableStatement.setString(3, configurazioneAreaMercantile.getChiaveEnte());
			callableStatement.setString(4, configurazioneAreaMercantile.getCodiceAreaMercantile());
			callableStatement.setString(5, configurazioneAreaMercantile.getDescrizioneAreaMercantile());
			callableStatement.execute();
			resultSet=callableStatement.getResultSet();
			
			if ( !resultSet.next() ) {
				configurazioneAreaMercantileList = new ArrayList<ConfigurazioneAreaMercantile>();
			} else {
				configurazioneAreaMercantileList = new ArrayList<ConfigurazioneAreaMercantile>();
				do {
					ConfigurazioneAreaMercantile item = new ConfigurazioneAreaMercantile();
					item.setCodiceKeyAreaMercantile(resultSet.getString("MRC_KMRCKMRC"));
					item.setCodiceAreaMercantile(resultSet.getString("MRC_CMRCCTIP"));
					item.setDescrizioneAreaMercantile(resultSet.getString("MRC_CMRCDSAM"));
					GregorianCalendar DataIniVal = new GregorianCalendar();
					DataIniVal.setTimeInMillis(resultSet.getTimestamp("MRC_GMRCDTIN").getTime());
					item.setDataInizioValidita(DataIniVal);
					GregorianCalendar DataFinVal = new GregorianCalendar();
					DataFinVal.setTimeInMillis(resultSet.getTimestamp("MRC_GMRCDTFN").getTime());
					item.setDataFineValidita(DataFinVal);
					configurazioneAreaMercantileList.add(item);
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
		
		return configurazioneAreaMercantileList;

	}		
	
	public Integer update(ConfigurazioneAreaMercantile configurazioneAreaMercantile)
		throws DaoException 
	{
		CallableStatement callableStatement=null;
		Connection connection = null;
		int ret=0;
		try {
			connection = getConnection();
			callableStatement = Helper.prepareCall(connection, getSchema(), Routines.PYMRCSP_UPD.routine());

//			IN I_MRC_CSOCCSOC VARCHAR(5), 
//			IN I_MRC_CUTECUTE VARCHAR(5),
//			IN I_MRC_KANEKENT CHAR(10),
//			IN I_MRC_KMRCKMRC VARCHAR(10),			
//			IN I_MRC_CMRCCTIP VARCHAR(10),
//			IN I_MRC_CMRCDSTB VARCHAR(70),
//			IN I_MRC_GMRCDTIN TIMESTAMP,
//			IN I_MRC_GMRCDTFN TIMESTAMP,
//			OUT O_TOTROWS INTEGER )

//			private String codiceSocieta;        		//	"MRC_CSOCCSOC" VARCHAR(5)
//			private String cuteCute;					//	"MRC_CUTECUTE" VARCHAR(5)
//			private String chiaveEnte;					//	"MRC_KANEKENT" CHAR(10)
//			private String codiceKeyAreaMercantile;		//	"MRC_KMRCKMRC" VARCHAR(10)			
//			private String codiceAreaMercantile;		//	"MRC_CMRCCTIP" VARCHAR(10)
//			private String descrizioneAreaMercantile;	//	"MRC_CMRCDSTB" VARCHAR(70)
//			private Calendar dataInizioValidita;		//  "MRC_GMRCDTIN" TIMESTAMP
//			private Calendar dataFineValdita;			//  "MRC_GMRCDTFN" TIMESTAMP
			
			callableStatement.setString(1, configurazioneAreaMercantile.getCodiceSocieta());
			callableStatement.setString(2, configurazioneAreaMercantile.getCuteCute());
			callableStatement.setString(3, configurazioneAreaMercantile.getChiaveEnte());
			callableStatement.setString(4, configurazioneAreaMercantile.getCodiceKeyAreaMercantile());
			callableStatement.setString(5, configurazioneAreaMercantile.getCodiceAreaMercantile());
			//L'unico campo modificabile in aggiornamento è la data fine validità
			callableStatement.setString(6, null);
			callableStatement.setTimestamp(7, null);
			callableStatement.setTimestamp(8, new java.sql.Timestamp(configurazioneAreaMercantile.getDataFineValidita().getTimeInMillis()));
			callableStatement.registerOutParameter(9, Types.INTEGER);						
			callableStatement.execute();
			ret = callableStatement.getInt(9);
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
