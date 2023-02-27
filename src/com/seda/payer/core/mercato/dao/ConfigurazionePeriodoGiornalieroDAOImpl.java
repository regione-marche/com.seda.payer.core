package com.seda.payer.core.mercato.dao;

import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;

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
import com.seda.payer.core.mercato.bean.ConfigurazionePeriodoGiornaliero;
import com.seda.payer.core.mercato.bean.EsitoRisposte;
import com.seda.payer.core.mercato.bean.MercatoPageList;
import com.seda.payer.core.mercato.dao.MercatoDAO;

public class ConfigurazionePeriodoGiornalieroDAOImpl extends BaseDaoHandler  implements ConfigurazionePeriodoGiornalieroDAO  {
	private static final long serialVersionUID = 1L;
	//inizio LP PG21XX04 Leak
	@Deprecated
	//fine LP PG21XX04 Leak
	public ConfigurazionePeriodoGiornalieroDAOImpl(DataSource dataSource, String schema) throws SQLException {
		super(dataSource.getConnection(), schema);
	}
 
	//inizio LP PG21XX04 Leak
	public ConfigurazionePeriodoGiornalieroDAOImpl(Connection connection, String schema) throws SQLException {
		super(connection, schema);
	}
	//fine LP PG21XX04 Leak

	public MercatoPageList ListConfigurazionePeriodoGiornaliero(
			ConfigurazionePeriodoGiornaliero configurazionePeriodoGiornaliero,
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
//				IN I_PEG_CSOCCSOC VARCHAR(5),
//				IN I_PEG_CUTECUTE VARCHAR(5),
//				IN I_PEG_KANEKENT CHAR(10),
//				IN I_PEG_CPEGCTIP VARCHAR(10),
//				IN I_PEG_CPEGDSPE VARCHAR(70),
//				OUT O_ROWINI INTEGER,
//				OUT O_ROWEND INTEGER,
//				OUT O_TOTROWS INTEGER,
//				OUT O_TOTPAGES SMALLINT
				
			connection = getConnection();
			callableStatement = Helper.prepareCall(connection, getSchema(), Routines.PYPEGSP_LST.routine());
			callableStatement.setInt(1, pageNumber);                          /* rows per page */
			callableStatement.setInt(2, rowsPerPage);                        /* page number*/
			callableStatement.setString(3,configurazionePeriodoGiornaliero.getCodiceSocieta());
			callableStatement.setString(4,configurazionePeriodoGiornaliero.getCuteCute());
			callableStatement.setString(5,configurazionePeriodoGiornaliero.getChiaveEnte());
			callableStatement.setString(6,configurazionePeriodoGiornaliero.getCodicePeriodoGiornaliero());
			callableStatement.setString(7,configurazionePeriodoGiornaliero.getDescrizionePeriodoGiornaliero());
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

	public EsitoRisposte delete(ConfigurazionePeriodoGiornaliero configurazionePeriodoGiornaliero)
			throws DaoException 
	{
		CallableStatement callableStatement=null;
		Connection connection = null;
		EsitoRisposte  esitoRisposte = new EsitoRisposte();
		try {
			connection = getConnection();
			callableStatement = Helper.prepareCall(connection, getSchema(), Routines.PYPEGSP_DEL.routine());
//			IN I_PEG_CSOCCSOC VARCHAR(5), 
//			IN I_PEG_CUTECUTE VARCHAR(5),
//			IN I_PEG_KANEKENT CHAR(10),
//			IN I_PEG_CPEGCTIP VARCHAR(10),
//			OUT O_PEG_CODESITO VARCHAR(2),
//			OUT O_PEG_MSGESITO VARCHAR(100)

			callableStatement.setString(1, configurazionePeriodoGiornaliero.getCodiceSocieta());
			callableStatement.setString(2, configurazionePeriodoGiornaliero.getCuteCute());
			callableStatement.setString(3, configurazionePeriodoGiornaliero.getChiaveEnte());
			callableStatement.setString(4, configurazionePeriodoGiornaliero.getCodicePeriodoGiornaliero());			
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

	public EsitoRisposte insert(ConfigurazionePeriodoGiornaliero configurazionePeriodoGiornaliero)
		throws DaoException 
	{
		CallableStatement callableStatement=null;
		Connection connection = null;
		EsitoRisposte esitoRisposte = new EsitoRisposte();
		try {
			connection = getConnection();
			callableStatement = Helper.prepareCall(connection, getSchema(), Routines.PYPEGSP_INS.routine());

//					IN I_PEG_CSOCCSOC VARCHAR(5), 
//					IN I_PEG_CUTECUTE VARCHAR(5),
//					IN I_PEG_KANEKENT CHAR(10),
//					IN I_PEG_CPEGCTIP VARCHAR(10),
//					IN I_PEG_CPEGDSPE VARCHAR(70),			
//					OUT O_PEG_CODESITO VARCHAR(2),
//					OUT O_PEG_MSGESITO VARCHAR(100)			
//			private String codiceSocieta;        			//	"PEG_CSOCCSOC" VARCHAR(5)
//			private String cuteCute;						//	"PEG_CUTECUTE" VARCHAR(5)
//			private String chiaveEnte;						//	"PEG_KANEKENT" CHAR(10)
//			private String codicePeriodoGiornaliero;  		//	"PEG_CPEGCTIP" VARCHAR(10)
// 			private String descrizionePeriodoGiornaliero;	//	"PEG_CPEGDSPE" VARCHAR(70)			
			
			callableStatement.setString(1, configurazionePeriodoGiornaliero.getCodiceSocieta());
			callableStatement.setString(2, configurazionePeriodoGiornaliero.getCuteCute());
			callableStatement.setString(3, configurazionePeriodoGiornaliero.getChiaveEnte());
			callableStatement.setString(4, configurazionePeriodoGiornaliero.getCodicePeriodoGiornaliero());
			callableStatement.setString(5, configurazionePeriodoGiornaliero.getDescrizionePeriodoGiornaliero());

			callableStatement.registerOutParameter(6, Types.VARCHAR);
			callableStatement.registerOutParameter(7, Types.VARCHAR);			
			callableStatement.execute();
			esitoRisposte.setCodiceMessaggio(callableStatement.getString(6));
			esitoRisposte.setDescrizioneMessaggio(callableStatement.getString(7));
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

	public ConfigurazionePeriodoGiornaliero select(ConfigurazionePeriodoGiornaliero configurazionePeriodoGiornaliero)
			throws DaoException 
	{
		CallableStatement callableStatement=null;
		ResultSet resultSet=null;
		Connection connection = null;
		CachedRowSet rowSet = null;
		try {
			connection = getConnection();
//			IN I_PEG_CSOCCSOC VARCHAR(5), 
//			IN I_PEG_CUTECUTE VARCHAR(5),
//			IN I_PEG_KANEKENT CHAR(10),
//			IN I_PEG_CPEGCTIP VARCHAR(10)			
			callableStatement = Helper.prepareCall(connection, getSchema(), Routines.PYPEGSP_SEL.routine());
			callableStatement.setString(1, configurazionePeriodoGiornaliero.getCodiceSocieta());
			callableStatement.setString(2, configurazionePeriodoGiornaliero.getCuteCute());
			callableStatement.setString(3, configurazionePeriodoGiornaliero.getChiaveEnte());
			callableStatement.setString(4, configurazionePeriodoGiornaliero.getCodicePeriodoGiornaliero());
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
				 String codiceKeyPeriodoGiornaliero = rowSet.getString(1);
				 String codiceSocieta = rowSet.getString(2);       		
				 String cuteCute = rowSet.getString(3);					
				 String chiaveEnte = rowSet.getString(4);
				 String codicePeriodoGiornaliero = rowSet.getString(5);
				 String descrizionePeriodoGiornaliero = rowSet.getString(6);

				 configurazionePeriodoGiornaliero = new ConfigurazionePeriodoGiornaliero(
						 codiceKeyPeriodoGiornaliero, codiceSocieta, cuteCute, 
						 chiaveEnte, codicePeriodoGiornaliero, descrizionePeriodoGiornaliero);
			
				 configurazionePeriodoGiornaliero.setAttribute(MercatoDAO.SELECT_XML, selectXml);
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
		return configurazionePeriodoGiornaliero;
	}

	public ArrayList<ConfigurazionePeriodoGiornaliero> listPeriodoGiornaliero(ConfigurazionePeriodoGiornaliero configurazionePeriodoGiornaliero) throws DaoException {
//		IN I_PEG_CSOCCSOC VARCHAR(5), 
//		IN I_PEG_CUTECUTE VARCHAR(5),
//		IN I_PEG_KANEKENT CHAR(10),
//		IN I_PEG_CPEGCTIP VARCHAR(10),
//		IN I_PEG_CPEGDSPE VARCHAR(70)
		CallableStatement callableStatement=null;
		ArrayList<ConfigurazionePeriodoGiornaliero> configurazionePeriodoGiornalieroList = null;
		Connection connection = null;
		ResultSet resultSet=null;
		try {
			connection = getConnection();
			callableStatement = Helper.prepareCall(connection, getSchema(), Routines.PYPEGSP_TOT.routine());
			callableStatement.setString(1, configurazionePeriodoGiornaliero.getCodiceSocieta());
			callableStatement.setString(2, configurazionePeriodoGiornaliero.getCuteCute());
			callableStatement.setString(3, configurazionePeriodoGiornaliero.getChiaveEnte());
			callableStatement.setString(4, configurazionePeriodoGiornaliero.getCodicePeriodoGiornaliero());
			callableStatement.setString(5, configurazionePeriodoGiornaliero.getDescrizionePeriodoGiornaliero());
			callableStatement.execute();
			resultSet=callableStatement.getResultSet();
			
			if ( !resultSet.next() ) {
				configurazionePeriodoGiornalieroList = new ArrayList<ConfigurazionePeriodoGiornaliero>();
			} else {
				configurazionePeriodoGiornalieroList = new ArrayList<ConfigurazionePeriodoGiornaliero>();
				do {
					ConfigurazionePeriodoGiornaliero item = new ConfigurazionePeriodoGiornaliero();
					item.setCodiceKeyPeriodoGiornaliero(resultSet.getString("PEG_KPEGKPEG"));
					item.setCodicePeriodoGiornaliero(resultSet.getString("PEG_CPEGCTIP"));
					item.setDescrizionePeriodoGiornaliero(resultSet.getString("PEG_CPEGDSPE"));
					configurazionePeriodoGiornalieroList.add(item);
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
		
		return configurazionePeriodoGiornalieroList;

	}	

	
	public Integer update(ConfigurazionePeriodoGiornaliero configurazionePeriodoGiornaliero)
		throws DaoException 
	{
		CallableStatement callableStatement=null;
		Connection connection = null;
		int ret=0;
		try {
			connection = getConnection();
			callableStatement = Helper.prepareCall(connection, getSchema(), Routines.PYPEGSP_UPD.routine());

//			IN I_PEG_CSOCCSOC VARCHAR(5), 
//			IN I_PEG_CUTECUTE VARCHAR(5),
//			IN I_PEG_KANEKENT CHAR(10),
//			IN I_PEG_CTPBCTIP VARCHAR(10),
//			IN I_PEG_CPEGDSPE VARCHAR(70),
//			OUT O_TOTROWS INTEGER )

//			private String codiceSocieta;        			//	"PEG_CSOCCSOC" VARCHAR(5)
//			private String cuteCute;						//	"PEG_CUTECUTE" VARCHAR(5)
//			private String chiaveEnte;						//	"PEG_KANEKENT" CHAR(10)
//			private Integer codicePeriodoGiornaliero;		//	"PEG_CPEGCTIP" VARCHAR(10)
//			private String descrizionePeriodoGiornaliero;	//	"PEG_CPEGDSPE" VARCHAR(70)
			
			callableStatement.setString(1, configurazionePeriodoGiornaliero.getCodiceSocieta());
			callableStatement.setString(2, configurazionePeriodoGiornaliero.getCuteCute());
			callableStatement.setString(3, configurazionePeriodoGiornaliero.getChiaveEnte());
			callableStatement.setString(4, configurazionePeriodoGiornaliero.getCodicePeriodoGiornaliero());
			callableStatement.setString(5, configurazionePeriodoGiornaliero.getDescrizionePeriodoGiornaliero());
			callableStatement.registerOutParameter(6, Types.INTEGER);						
			callableStatement.execute();
			ret = callableStatement.getInt(6);
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
