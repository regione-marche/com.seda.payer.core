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
import com.seda.data.helper.HelperException;
import com.seda.data.spi.PageInfo;
import com.seda.payer.core.dao.Routines;
import com.seda.payer.core.exception.DaoException;
import com.seda.payer.core.handler.BaseDaoHandler;
import com.seda.payer.core.mercato.bean.ConfigurazionePiazzola;
import com.seda.payer.core.mercato.bean.EsitoRisposte;
import com.seda.payer.core.mercato.bean.MercatoPageList;

public class ConfigurazionePiazzolaDAOImpl extends BaseDaoHandler  implements ConfigurazionePiazzolaDAO  {
	//private static final long serialVersionUID = 1L;
	//inizio LP PG21XX04 Leak
	@Deprecated
	//fine LP PG21XX04 Leak
	public ConfigurazionePiazzolaDAOImpl(DataSource dataSource, String schema) throws SQLException {
		super(dataSource.getConnection(), schema);
	}

	//inizio LP PG21XX04 Leak
	public ConfigurazionePiazzolaDAOImpl(Connection connection, String schema) throws SQLException {
		super(connection, schema);
	}
	//fine LP PG21XX04 Leak

	public MercatoPageList ListConfigurazionePiazzola(
			ConfigurazionePiazzola configurazionePiazzola,
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
//				IN I_PZL_CSOCCSOC VARCHAR(5),
//				IN I_PZL_CUTECUTE VARCHAR(5),
//				IN I_PZL_KANEKENT CHAR(10),
//				IN I_PZL_CPZLKMRC VARCHAR(10),
//				IN I_PZL_CPZLCTIP VARCHAR(10),
//				IN I_PZL_CPZLDSPZ VARCHAR(70),
//				IN I_PZL_GPZLDTIN_DA TIMESTAMP,
//				IN I_PZL_GPZLDTIN_A TIMESTAMP,
//				IN I_PZL_GPZLDTFN_DA TIMESTAMP,
//				IN I_PZL_GPZLDTFN_A TIMESTAMP,
//				OUT O_ROWINI INTEGER,
//				OUT O_ROWEND INTEGER,
//				OUT O_TOTROWS INTEGER,
//				OUT O_TOTPAGES SMALLINT			
			connection = getConnection();
			//inizio LP PGNTCORE-24
			//callableStatement = Helper.prepareCall(connection, getSchema(), Routines.PYPZLSP_LST.routine());
            callableStatement = prepareCall(Routines.PYPZLSP_LST.routine());
			//fine LP PGNTCORE-24
			callableStatement.setInt(1, pageNumber);                          /* rows per page */
			callableStatement.setInt(2, rowsPerPage);                        /* page number*/
			callableStatement.setString(3,configurazionePiazzola.getCodiceSocieta());
			callableStatement.setString(4,configurazionePiazzola.getCuteCute());
			callableStatement.setString(5,configurazionePiazzola.getChiaveEnte());
			callableStatement.setString(6,configurazionePiazzola.getCodiceKeyAreaMercantile());
			callableStatement.setString(7,configurazionePiazzola.getCodicePiazzola());
			callableStatement.setString(8,configurazionePiazzola.getDescrizionePiazzola());
			if (configurazionePiazzola.getDataInizioValidita()==null) {
				callableStatement.setTimestamp(9, null);
			} else {
			    callableStatement.setTimestamp(9, new java.sql.Timestamp(configurazionePiazzola.getDataInizioValidita().getTimeInMillis()));
			}			
			if (configurazionePiazzola.getDataAInizioValidita()==null) {
				callableStatement.setTimestamp(10, null);
			} else {
			    callableStatement.setTimestamp(10, new java.sql.Timestamp(configurazionePiazzola.getDataAInizioValidita().getTimeInMillis()));
			}			

			if (configurazionePiazzola.getDataFineValidita()==null) {
				callableStatement.setTimestamp(11, null);
			} else {
			    callableStatement.setTimestamp(11, new java.sql.Timestamp(configurazionePiazzola.getDataFineValidita().getTimeInMillis()));			
			}
			if (configurazionePiazzola.getDataAFineValidita()==null) {
				callableStatement.setTimestamp(12, null);
			} else {
			    callableStatement.setTimestamp(12, new java.sql.Timestamp(configurazionePiazzola.getDataAFineValidita().getTimeInMillis()));		
			}			
			/* we register row start */
			callableStatement.registerOutParameter(13, Types.INTEGER);
			/* we register row end */
			callableStatement.registerOutParameter(14, Types.INTEGER);
			/* we register total rows */
			callableStatement.registerOutParameter(15, Types.INTEGER);
			/* we register total pages */
			callableStatement.registerOutParameter(16, Types.SMALLINT);
			 
			/* we execute procedure */
			if(callableStatement.execute())	{
				pageInfo = new PageInfo();
				pageInfo.setPageNumber(pageNumber);
				pageInfo.setRowsPerPage(rowsPerPage);
				pageInfo.setFirstRow(callableStatement.getInt(13));
				pageInfo.setLastRow(callableStatement.getInt(14));
				pageInfo.setNumRows(callableStatement.getInt(15));
				pageInfo.setNumPages(callableStatement.getInt(16));
				
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

	public EsitoRisposte delete(ConfigurazionePiazzola configurazionePiazzola)
			throws DaoException 
	{
		CallableStatement callableStatement=null;
		Connection connection = null;
		EsitoRisposte  esitoRisposte = new EsitoRisposte();
		try {
			connection = getConnection();
			//inizio LP PGNTCORE-24
			//callableStatement = Helper.prepareCall(connection, getSchema(), Routines.PYPZLSP_DEL.routine());
            callableStatement = prepareCall(Routines.PYPZLSP_DEL.routine());
			//fine LP PGNTCORE-24
//			IN I_PZL_CSOCCSOC VARCHAR(5), 
//			IN I_PZL_CUTECUTE VARCHAR(5),
//			IN I_PZL_KANEKENT CHAR(10),
//			IN I_PZL_CPZLKMRC VARCHAR(10),
//			IN I_PZL_KPZLKPZL VARCHAR(64),			
//			OUT O_PPS_CODESITO VARCHAR(2),
//			OUT O_PPS_MSGESITO VARCHAR(100)

			callableStatement.setString(1, configurazionePiazzola.getCodiceSocieta());
			callableStatement.setString(2, configurazionePiazzola.getCuteCute());
			callableStatement.setString(3, configurazionePiazzola.getChiaveEnte());
			callableStatement.setString(4, configurazionePiazzola.getCodiceKeyAreaMercantile());
			callableStatement.setString(5, configurazionePiazzola.getCodiceKeyPiazzola());
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

	public EsitoRisposte insert(ConfigurazionePiazzola configurazionePiazzola)
		throws DaoException 
	{
		CallableStatement callableStatement=null;
		Connection connection = null;
		EsitoRisposte esitoRisposte = new EsitoRisposte();
		try {
			connection = getConnection();
			//inizio LP PGNTCORE-24
			//callableStatement = Helper.prepareCall(connection, getSchema(), Routines.PYPZLSP_INS.routine());
            callableStatement = prepareCall(Routines.PYPZLSP_INS.routine());
			//fine LP PGNTCORE-24
//					IN I_PZL_KPZLKPZL VARCHAR(64),
//					IN I_PZL_CSOCCSOC VARCHAR(5), 
//					IN I_PZL_CUTECUTE VARCHAR(5),
//					IN I_PZL_KANEKENT CHAR(10),
//					IN I_PZL_CPZLKMRC VARCHAR(10),
//					IN I_PZL_CPZLCTIP VARCHAR(10),
//					IN I_PZL_CPZLDSPZ VARCHAR(70),
//					IN I_PZL_DPZLA1LT DECIMAL(10,7),
//					IN I_PZL_DPZLA1LG DECIMAL(10,7),
//					IN I_PZL_DPZLA2LT DECIMAL(10,7),
//					IN I_PZL_DPZLA2LG DECIMAL(10,7),
//					IN I_PZL_DPZLA3LT DECIMAL(10,7),
//					IN I_PZL_DPZLA3LG DECIMAL(10,7),
//					IN I_PZL_DPZLA4LT DECIMAL(10,7),
//					IN I_PZL_DPZLA4LG DECIMAL(10,7),
//					IN I_PZL_GPZLDTIN TIMESTAMP,
//					IN I_PZL_GPZLDTFN TIMESTAMP,
//					OUT O_PPS_CODESITO VARCHAR(2),
//					OUT O_PPS_MSGESITO VARCHAR(100)	
//			private String codiceKeyPiazzola			//  "PZL_KPZLKPZL" VARCHAR(64)			
//			private String codiceSocieta;        		//	"PZL_CSOCCSOC" VARCHAR(5)
//			private String cuteCute;					//	"PZL_CUTECUTE" VARCHAR(5)
//			private String chiaveEnte;					//	"PZL_KANEKENT" CHAR(10)
//			private String codiceKeyAreaMercantile		//  "PZL_CPZLKMRC" VARCHAR(10)
//			private String codicePiazzola;  			//	"PZL_CPZLCTIP" VARCHAR(10)
// 			private String descrizionePiazzola;			//	"PZL_CMRCDSPZ" VARCHAR(70)
//			private Double coordLatAng1					//  "PZL_DPZLA1LT" DECIMAL(10,7)
//			private Double coordLonAng1					//  "PZL_DPZLA1LG" DECIMAL(10,7)
//			private Double coordLatAng2					//  "PZL_DPZLA2LT" DECIMAL(10,7)
//			private Double coordLonAng2					//  "PZL_DPZLA2LG" DECIMAL(10,7)
//			private Double coordLatAng3					//  "PZL_DPZLA3LT" DECIMAL(10,7)
//			private Double coordLonAng3					//  "PZL_DPZLA3LG" DECIMAL(10,7)
//			private Double coordLatAng4					//  "PZL_DPZLA4LT" DECIMAL(10,7)
//			private Double coordLonAng4					//  "PZL_DPZLA4LG" DECIMAL(10,7)
//			private Calendar dataInizioValidita;		//  "MRC_GMRCDTIN" TIMESTAMP
//			private Calendar dataFineValidita;			//  "MRC_GMRCDTFN" TIMESTAMP
			
			callableStatement.setString(1, configurazionePiazzola.getCodiceKeyPiazzola());
			callableStatement.setString(2, configurazionePiazzola.getCodiceSocieta());
			callableStatement.setString(3, configurazionePiazzola.getCuteCute());
			callableStatement.setString(4, configurazionePiazzola.getChiaveEnte());
			callableStatement.setString(5, configurazionePiazzola.getCodiceKeyAreaMercantile());
			callableStatement.setString(6, configurazionePiazzola.getCodicePiazzola());
			callableStatement.setString(7, configurazionePiazzola.getDescrizionePiazzola());
			if (configurazionePiazzola.getCoordLatAng1()==null) {
				callableStatement.setDouble(8, 0);
			} else {
				callableStatement.setDouble(8, configurazionePiazzola.getCoordLatAng1());
			}
			if (configurazionePiazzola.getCoordLonAng1()==null) {
				callableStatement.setDouble(9, 0);
			} else {
				callableStatement.setDouble(9, configurazionePiazzola.getCoordLonAng1());
			}
			if (configurazionePiazzola.getCoordLatAng2()==null) {
				callableStatement.setDouble(10, 0);
			} else {
				callableStatement.setDouble(10, configurazionePiazzola.getCoordLatAng2());
			}
			if (configurazionePiazzola.getCoordLonAng2()==null) {
				callableStatement.setDouble(11, 0);
			} else {
				callableStatement.setDouble(11, configurazionePiazzola.getCoordLonAng2());
			}
			if (configurazionePiazzola.getCoordLatAng3()==null) {
				callableStatement.setDouble(12, 0);
			} else {
				callableStatement.setDouble(12, configurazionePiazzola.getCoordLatAng3());
			}
			if (configurazionePiazzola.getCoordLonAng3()==null) {
				callableStatement.setDouble(13, 0);
			} else {
				callableStatement.setDouble(13, configurazionePiazzola.getCoordLonAng3());
			}
			if (configurazionePiazzola.getCoordLatAng4()==null) {
				callableStatement.setDouble(14, 0);
			} else {
				callableStatement.setDouble(14, configurazionePiazzola.getCoordLatAng4());
			}
			if (configurazionePiazzola.getCoordLonAng4()==null) {
				callableStatement.setDouble(15, 0);
			} else {
				callableStatement.setDouble(15, configurazionePiazzola.getCoordLonAng4());
			}
			if (configurazionePiazzola.getDataInizioValidita()==null) {
				callableStatement.setTimestamp(16, null);
			} else {
				callableStatement.setTimestamp(16, new java.sql.Timestamp(configurazionePiazzola.getDataInizioValidita().getTimeInMillis()));
			}
			if (configurazionePiazzola.getDataFineValidita()==null) {
				callableStatement.setTimestamp(17, null);
			} else {
			    callableStatement.setTimestamp(17, new java.sql.Timestamp(configurazionePiazzola.getDataFineValidita().getTimeInMillis()));
			}

			callableStatement.registerOutParameter(18, Types.VARCHAR);
			callableStatement.registerOutParameter(19, Types.VARCHAR);			
			callableStatement.execute();
			esitoRisposte.setCodiceMessaggio(callableStatement.getString(18));
			esitoRisposte.setDescrizioneMessaggio(callableStatement.getString(19));
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

	public ConfigurazionePiazzola select(ConfigurazionePiazzola configurazionePiazzola)
			throws DaoException 
	{
		CallableStatement callableStatement=null;
		ResultSet resultSet=null;
		Connection connection = null;
		CachedRowSet rowSet = null;
		try {
			connection = getConnection();
			//inizio LP PGNTCORE-24
			//callableStatement = Helper.prepareCall(connection, getSchema(), Routines.PYPZLSP_SEL.routine());
            callableStatement = prepareCall(Routines.PYPZLSP_SEL.routine());
			//fine LP PGNTCORE-24
			callableStatement.setString(1, configurazionePiazzola.getCodiceSocieta());
			callableStatement.setString(2, configurazionePiazzola.getCuteCute());
			callableStatement.setString(3, configurazionePiazzola.getChiaveEnte());
			callableStatement.setString(4, configurazionePiazzola.getCodiceKeyAreaMercantile());
			callableStatement.setString(5, configurazionePiazzola.getCodicePiazzola());
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
				 String codiceKeyPiazzola = rowSet.getString(1);
				 String codiceSocieta = rowSet.getString(2);       		
				 String cuteCute = rowSet.getString(3);					
				 String chiaveEnte = rowSet.getString(4);
				 String codiceKeyAreaMercantile = rowSet.getString(5);
				 String codicePiazzola = rowSet.getString(6);
				 String descrizionePiazzola = rowSet.getString(7);
				 Double coordLatAng1 = rowSet.getDouble(8);
				 Double coordLonAng1 = rowSet.getDouble(9);
				 Double coordLatAng2 = rowSet.getDouble(10);
				 Double coordLonAng2 = rowSet.getDouble(11);
				 Double coordLatAng3 = rowSet.getDouble(12);
				 Double coordLonAng3 = rowSet.getDouble(13);
				 Double coordLatAng4 = rowSet.getDouble(14);
				 Double coordLonAng4 = rowSet.getDouble(15);
				 GregorianCalendar dataInizioValidita = new GregorianCalendar();
				 dataInizioValidita.setTimeInMillis(rowSet.getTimestamp(16).getTime());
				 GregorianCalendar dataFineValidita = new GregorianCalendar();
				 dataFineValidita.setTimeInMillis(rowSet.getTimestamp(17).getTime());
				 
				 configurazionePiazzola = new ConfigurazionePiazzola(
						 codiceKeyPiazzola, codiceSocieta, cuteCute, chiaveEnte, codiceKeyAreaMercantile,
						 codicePiazzola, descrizionePiazzola, 
						 coordLatAng1, coordLonAng1, coordLatAng2, coordLonAng2, coordLatAng3, coordLonAng3,
						 coordLatAng4, coordLonAng4, dataInizioValidita, dataFineValidita, null, null, null);
			
				 configurazionePiazzola.setAttribute(MercatoDAO.SELECT_XML, selectXml);
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
		return configurazionePiazzola;
	}


	public ConfigurazionePiazzola getPerKey(ConfigurazionePiazzola configurazionePiazzola)
			throws DaoException 
	{
		CallableStatement callableStatement=null;
		ResultSet resultSet=null;
		Connection connection = null;
		CachedRowSet rowSet = null;
		try {
			connection = getConnection();
			//inizio LP PGNTCORE-24
			//callableStatement = Helper.prepareCall(connection, getSchema(), Routines.PYPZLSP_SEK.routine());
            callableStatement = prepareCall(Routines.PYPZLSP_SEK.routine());
			//fine LP PGNTCORE-24
			callableStatement.setString(1, configurazionePiazzola.getCodiceKeyPiazzola());
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
				 String codiceKeyPiazzola = rowSet.getString(1);
				 String codiceSocieta = rowSet.getString(2);       		
				 String cuteCute = rowSet.getString(3);					
				 String chiaveEnte = rowSet.getString(4);
				 String codiceKeyAreaMercantile = rowSet.getString(5);
				 String codicePiazzola = rowSet.getString(6);
				 String descrizionePiazzola = rowSet.getString(7);
				 Double coordLatAng1 = rowSet.getDouble(8);
				 Double coordLonAng1 = rowSet.getDouble(9);
				 Double coordLatAng2 = rowSet.getDouble(10);
				 Double coordLonAng2 = rowSet.getDouble(11);
				 Double coordLatAng3 = rowSet.getDouble(12);
				 Double coordLonAng3 = rowSet.getDouble(13);
				 Double coordLatAng4 = rowSet.getDouble(14);
				 Double coordLonAng4 = rowSet.getDouble(15);
				 GregorianCalendar dataInizioValidita = new GregorianCalendar();
				 dataInizioValidita.setTimeInMillis(rowSet.getTimestamp(16).getTime());
				 GregorianCalendar dataFineValidita = new GregorianCalendar();
				 dataFineValidita.setTimeInMillis(rowSet.getTimestamp(17).getTime());
				 
				 configurazionePiazzola = new ConfigurazionePiazzola(
						 codiceKeyPiazzola, codiceSocieta, cuteCute, chiaveEnte, codiceKeyAreaMercantile,
						 codicePiazzola, descrizionePiazzola, 
						 coordLatAng1, coordLonAng1, coordLatAng2, coordLonAng2, coordLatAng3, coordLonAng3,
						 coordLatAng4, coordLonAng4, dataInizioValidita, dataFineValidita, null, null, null);
			
				 configurazionePiazzola.setAttribute(MercatoDAO.SELECT_XML, selectXml);
			} 
			
		} catch (SQLException e) {
			throw new DaoException(e);
		} catch (IllegalArgumentException e) {
			throw new DaoException(e);
		} catch (HelperException e) {
			e.printStackTrace();
			throw new DaoException(e);
		} finally {
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
		return configurazionePiazzola;
	}
	
	
	public ArrayList <ConfigurazionePiazzola>listPiazzola(ConfigurazionePiazzola configurazionePiazzola) throws DaoException {
//		IN I_PZL_CSOCCSOC VARCHAR(5),
//		IN I_PZL_CUTECUTE VARCHAR(5),
//		IN I_PZL_KANEKENT CHAR(10),
//		IN I_PZL_CPZLKMRC VARCHAR(10),
//		IN I_PZL_CPZLCTIP VARCHAR(10),
//		IN I_PZL_CPZLDSPZ VARCHAR(70),
//		IN I_PZL_GPZLDTIN_DA TIMESTAMP,
//		IN I_PZL_GPZLDTIN_A TIMESTAMP,
//		IN I_PZL_GPZLDTFN_DA TIMESTAMP,
//		IN I_PZL_GPZLDTFN_A TIMESTAMP
		CallableStatement callableStatement=null;
		ArrayList<ConfigurazionePiazzola> configurazionePiazzolaList = null;
		Connection connection = null;
		ResultSet resultSet = null;
		try {
			connection = getConnection();
			//inizio LP PGNTCORE-24
			//callableStatement = Helper.prepareCall(connection, getSchema(), Routines.PYPZLSP_TOT.routine());
            callableStatement = prepareCall(Routines.PYPZLSP_TOT.routine());
			//fine LP PGNTCORE-24
			callableStatement.setString(1, configurazionePiazzola.getCodiceSocieta());
			callableStatement.setString(2, configurazionePiazzola.getCuteCute());
			callableStatement.setString(3, configurazionePiazzola.getChiaveEnte());
			callableStatement.setString(4,configurazionePiazzola.getCodiceKeyAreaMercantile());
			callableStatement.setString(5,configurazionePiazzola.getCodicePiazzola());
			callableStatement.setString(6,configurazionePiazzola.getDescrizionePiazzola());
			if (configurazionePiazzola.getDataInizioValidita()==null) {
				callableStatement.setTimestamp(7, null);
			} else {
			    callableStatement.setTimestamp(7, new java.sql.Timestamp(configurazionePiazzola.getDataInizioValidita().getTimeInMillis()));
			}			
			if (configurazionePiazzola.getDataAInizioValidita()==null) {
				callableStatement.setTimestamp(8, null);
			} else {
			    callableStatement.setTimestamp(8, new java.sql.Timestamp(configurazionePiazzola.getDataAInizioValidita().getTimeInMillis()));
			}			

			if (configurazionePiazzola.getDataFineValidita()==null) {
				callableStatement.setTimestamp(9, null);
			} else {
			    callableStatement.setTimestamp(9, new java.sql.Timestamp(configurazionePiazzola.getDataFineValidita().getTimeInMillis()));			
			}
			if (configurazionePiazzola.getDataAFineValidita()==null) {
				callableStatement.setTimestamp(10, null);
			} else {
			    callableStatement.setTimestamp(10, new java.sql.Timestamp(configurazionePiazzola.getDataAFineValidita().getTimeInMillis()));		
			}			
			
			callableStatement.execute();
			resultSet=callableStatement.getResultSet();
			
			if ( !resultSet.next() ) {
				configurazionePiazzolaList = new ArrayList<ConfigurazionePiazzola>();
			} else {
				configurazionePiazzolaList = new ArrayList<ConfigurazionePiazzola>();
				do {
					ConfigurazionePiazzola item = new ConfigurazionePiazzola();
					item.setCodiceKeyPiazzola(resultSet.getString("PZL_KPZLKPZL"));
					item.setCodicePiazzola(resultSet.getString("PZL_CPZLCTIP"));
					item.setDescrizionePiazzola(resultSet.getString("PZL_CPZLDSPZ"));
					item.setDescrizioneAreaMerc(resultSet.getString("MRC_CMRCDSAM"));
					item.setCoordLatAng1(resultSet.getDouble("PZL_DPZLA1LT"));
					item.setCoordLonAng1(resultSet.getDouble("PZL_DPZLA1LG"));
					item.setCoordLatAng2(resultSet.getDouble("PZL_DPZLA2LT"));
					item.setCoordLonAng2(resultSet.getDouble("PZL_DPZLA2LG"));
					item.setCoordLatAng3(resultSet.getDouble("PZL_DPZLA3LT"));
					item.setCoordLonAng3(resultSet.getDouble("PZL_DPZLA3LG"));
					item.setCoordLatAng4(resultSet.getDouble("PZL_DPZLA4LT"));
					item.setCoordLonAng4(resultSet.getDouble("PZL_DPZLA4LG"));
					GregorianCalendar DataIniVal = new GregorianCalendar();
					DataIniVal.setTimeInMillis(resultSet.getTimestamp("PZL_GPZLDTIN").getTime());
					item.setDataInizioValidita(DataIniVal);
					GregorianCalendar DataFinVal = new GregorianCalendar();
					DataFinVal.setTimeInMillis(resultSet.getTimestamp("PZL_GPZLDTFN").getTime());
					item.setDataFineValidita(DataFinVal);
					configurazionePiazzolaList.add(item);
				} while(resultSet.next());
			}
		} catch (SQLException e) {
			throw new DaoException(e);
		} catch (IllegalArgumentException e) {
			throw new DaoException(e);
		} catch (HelperException e) {
			e.printStackTrace();
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
		
		return configurazionePiazzolaList;
	
	}

	public ArrayList <ConfigurazionePiazzola>getAllPerMercato(ConfigurazionePiazzola configurazionePiazzola) throws DaoException {
//		IN I_PZL_CSOCCSOC VARCHAR(5),
//		IN I_PZL_CUTECUTE VARCHAR(5),
//		IN I_PZL_KANEKENT CHAR(10),
//		IN I_MRC_CMRCDSAM VARCHAR(70)
		CallableStatement callableStatement=null;
		ArrayList<ConfigurazionePiazzola> configurazionePiazzolaList = null;
		Connection connection = null;
		ResultSet resultSet = null;
		try {
			connection = getConnection();
			//inizio LP PGNTCORE-24
			//callableStatement = Helper.prepareCall(connection, getSchema(), Routines.PYPZLSP_APM.routine());
            callableStatement = prepareCall(Routines.PYPZLSP_APM.routine());
			//fine LP PGNTCORE-24
			callableStatement.setString(1, configurazionePiazzola.getCodiceSocieta());
			callableStatement.setString(2, configurazionePiazzola.getCuteCute());
			callableStatement.setString(3, configurazionePiazzola.getChiaveEnte());
			callableStatement.setString(4,configurazionePiazzola.getDescrizioneAreaMerc());
			
			callableStatement.execute();
			resultSet=callableStatement.getResultSet();
			
			if ( !resultSet.next() ) {
				configurazionePiazzolaList = new ArrayList<ConfigurazionePiazzola>();
			} else {
				configurazionePiazzolaList = new ArrayList<ConfigurazionePiazzola>();
				do {
					ConfigurazionePiazzola item = new ConfigurazionePiazzola();
					item.setCodiceKeyPiazzola(resultSet.getString("PZL_KPZLKPZL"));
					item.setCodicePiazzola(resultSet.getString("PZL_CPZLCTIP"));
					item.setDescrizionePiazzola(resultSet.getString("PZL_CPZLDSPZ"));
					item.setDescrizioneAreaMerc(resultSet.getString("MRC_CMRCDSAM"));
					item.setCoordLatAng1(resultSet.getDouble("PZL_DPZLA1LT"));
					item.setCoordLonAng1(resultSet.getDouble("PZL_DPZLA1LG"));
					item.setCoordLatAng2(resultSet.getDouble("PZL_DPZLA2LT"));
					item.setCoordLonAng2(resultSet.getDouble("PZL_DPZLA2LG"));
					item.setCoordLatAng3(resultSet.getDouble("PZL_DPZLA3LT"));
					item.setCoordLonAng3(resultSet.getDouble("PZL_DPZLA3LG"));
					item.setCoordLatAng4(resultSet.getDouble("PZL_DPZLA4LT"));
					item.setCoordLonAng4(resultSet.getDouble("PZL_DPZLA4LG"));
					GregorianCalendar DataIniVal = new GregorianCalendar();
					DataIniVal.setTimeInMillis(resultSet.getTimestamp("PZL_GPZLDTIN").getTime());
					item.setDataInizioValidita(DataIniVal);
					GregorianCalendar DataFinVal = new GregorianCalendar();
					DataFinVal.setTimeInMillis(resultSet.getTimestamp("PZL_GPZLDTFN").getTime());
					item.setDataFineValidita(DataFinVal);
					configurazionePiazzolaList.add(item);
				} while(resultSet.next());
			}
		} catch (SQLException e) {
			throw new DaoException(e);
		} catch (IllegalArgumentException e) {
			throw new DaoException(e);
		} catch (HelperException e) {
			e.printStackTrace();
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
		
		return configurazionePiazzolaList;
		
	}

	
	public Integer update(ConfigurazionePiazzola configurazionePiazzola)
		throws DaoException 
	{
		CallableStatement callableStatement=null;
		Connection connection = null;
		int ret=0;
		try {
			connection = getConnection();
			//inizio LP PGNTCORE-24
			//callableStatement = Helper.prepareCall(connection, getSchema(), Routines.PYPZLSP_UPD.routine());
            callableStatement = prepareCall(Routines.PYPZLSP_UPD.routine());
			//fine LP PGNTCORE-24
//			IN I_PZL_CSOCCSOC VARCHAR(5), 
//			IN I_PZL_CUTECUTE VARCHAR(5),
//			IN I_PZL_KANEKENT CHAR(10),
//			IN I_PZL_CPZLKMRC VARCHAR(10),	
//			IN I_PZL_CPZLCTIP VARCHAR(10),
//			IN I_PZL_DPZLA1LT DECIMAL(10,7),
//			IN I_PZL_DPZLA1LG DECIMAL(10,7),
//			IN I_PZL_DPZLA2LT DECIMAL(10,7),
//			IN I_PZL_DPZLA2LG DECIMAL(10,7),
//			IN I_PZL_DPZLA3LT DECIMAL(10,7),
//			IN I_PZL_DPZLA3LG DECIMAL(10,7),
//			IN I_PZL_DPZLA4LT DECIMAL(10,7),
//			IN I_PZL_DPZLA4LG DECIMAL(10,7),
//			IN I_PZL_GPZLDTIN TIMESTAMP,
//			IN I_PZL_GPZLDTFN TIMESTAMP,
//			OUT O_TOTROWS INTEGER 

//			private String codiceSocieta;        		//	"PZL_CSOCCSOC" VARCHAR(5)
//			private String cuteCute;					//	"PZL_CUTECUTE" VARCHAR(5)
//			private String chiaveEnte;					//	"PZL_KANEKENT" CHAR(10)
//			private String codiceKeyAreaMercantile		//  "PZL_CPZLKMRC" VARCHAR(10)
//			private String codicePiazzola;				//	"PZL_CPZLCTIP" VARCHAR(10)
//			private Double coordLatAng1					//  "PZL_DPZLA1LT" DECIMAL(10,7)
//			private Double coordLonAng1					//  "PZL_DPZLA1LG" DECIMAL(10,7)
//			private Double coordLatAng2					//  "PZL_DPZLA2LT" DECIMAL(10,7)
//			private Double coordLonAng2					//  "PZL_DPZLA2LG" DECIMAL(10,7)
//			private Double coordLatAng3					//  "PZL_DPZLA3LT" DECIMAL(10,7)
//			private Double coordLonAng3					//  "PZL_DPZLA3LG" DECIMAL(10,7)
//			private Double coordLatAng4					//  "PZL_DPZLA4LT" DECIMAL(10,7)
//			private Double coordLonAng4					//  "PZL_DPZLA4LG" DECIMAL(10,7)
//			private Calendar dataInizioValidita;		//  "PZL_GPZLDTIN" TIMESTAMP
//			private Calendar dataFineValdita;			//  "PZL_GPZLDTFN" TIMESTAMP
			
			callableStatement.setString(1, configurazionePiazzola.getCodiceSocieta());
			callableStatement.setString(2, configurazionePiazzola.getCuteCute());
			callableStatement.setString(3, configurazionePiazzola.getChiaveEnte());
			callableStatement.setString(4, configurazionePiazzola.getCodiceKeyAreaMercantile());
			callableStatement.setString(5, configurazionePiazzola.getCodicePiazzola());
			if (configurazionePiazzola.getCoordLatAng1()==null) {
				callableStatement.setDouble(6, 0);
			} else {
				callableStatement.setDouble(6, configurazionePiazzola.getCoordLatAng1());
			}
			if (configurazionePiazzola.getCoordLonAng1()==null) {
				callableStatement.setDouble(7, 0);
			} else {
				callableStatement.setDouble(7, configurazionePiazzola.getCoordLonAng1());
			}
			if (configurazionePiazzola.getCoordLatAng2()==null) {
				callableStatement.setDouble(8, 0);
			} else {
				callableStatement.setDouble(8, configurazionePiazzola.getCoordLatAng2());
			}
			if (configurazionePiazzola.getCoordLonAng2()==null) {
				callableStatement.setDouble(9, 0);
			} else {
				callableStatement.setDouble(9, configurazionePiazzola.getCoordLonAng2());
			}
			if (configurazionePiazzola.getCoordLatAng3()==null) {
				callableStatement.setDouble(10, 0);
			} else {
				callableStatement.setDouble(10, configurazionePiazzola.getCoordLatAng3());
			}
			if (configurazionePiazzola.getCoordLonAng3()==null) {
				callableStatement.setDouble(11, 0);
			} else {
				callableStatement.setDouble(11, configurazionePiazzola.getCoordLonAng3());
			}
			if (configurazionePiazzola.getCoordLatAng4()==null) {
				callableStatement.setDouble(12, 0);
			} else {
				callableStatement.setDouble(12, configurazionePiazzola.getCoordLatAng4());
			}
			if (configurazionePiazzola.getCoordLonAng4()==null) {
				callableStatement.setDouble(13, 0);
			} else {
				callableStatement.setDouble(13, configurazionePiazzola.getCoordLonAng4());
			}
			callableStatement.setTimestamp(14, new java.sql.Timestamp(configurazionePiazzola.getDataInizioValidita().getTimeInMillis()));
			if (configurazionePiazzola.getDataFineValidita()==null) {
				callableStatement.setTimestamp(15, null);
			} else {
				callableStatement.setTimestamp(15, new java.sql.Timestamp(configurazionePiazzola.getDataFineValidita().getTimeInMillis()));
			}
			callableStatement.registerOutParameter(16, Types.INTEGER);						
			callableStatement.execute();
			ret = callableStatement.getInt(16);
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

	public Integer updatePerKey(ConfigurazionePiazzola configurazionePiazzola)
		throws DaoException 
	{
		CallableStatement callableStatement=null;
		Connection connection = null;
		int ret=0;
		try {
			connection = getConnection();
			//inizio LP PGNTCORE-24
			//callableStatement = Helper.prepareCall(connection, getSchema(), Routines.PYPZLSP_UPK.routine());
            callableStatement = prepareCall(Routines.PYPZLSP_UPK.routine());
			//fine LP PGNTCORE-24
//		IN I_PZL_KPZLKPZL VARCHAR(64), 
//		IN I_PZL_GPZLDTIN TIMESTAMP,
//		IN I_PZL_GPZLDTFN TIMESTAMP,
//		OUT O_TOTROWS INTEGER 
//		private String codiceKeyPiazzola;        	//	"PZL_KPZLKPZL" VARCHAR(64)
//		private Calendar dataInizioValidita;		//  "PZL_GPZLDTIN" TIMESTAMP
//		private Calendar dataFineValdita;			//  "PZL_GPZLDTFN" TIMESTAMP
			callableStatement.setString(1, configurazionePiazzola.getCodiceKeyPiazzola());
			if (configurazionePiazzola.getDataInizioValidita()==null) {
				callableStatement.setTimestamp(2, null);
			} else {
				callableStatement.setTimestamp(2, new java.sql.Timestamp(configurazionePiazzola.getDataInizioValidita().getTimeInMillis()));
			}
			if (configurazionePiazzola.getDataFineValidita()==null) {
				callableStatement.setTimestamp(3, null);
			} else {
				callableStatement.setTimestamp(3, new java.sql.Timestamp(configurazionePiazzola.getDataFineValidita().getTimeInMillis()));
			}
			callableStatement.registerOutParameter(4, Types.INTEGER);						
			callableStatement.execute();
			ret = callableStatement.getInt(4);
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
