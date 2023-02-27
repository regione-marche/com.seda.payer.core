package com.seda.payer.core.wallet.dao;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

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
import com.seda.payer.core.wallet.bean.ConfigurazionePagamentoServizio;
import com.seda.payer.core.wallet.bean.EsitoRisposte;
import com.seda.payer.core.wallet.bean.WalletPageList;

public class ConfigurazionePagamentoServizioDAOImpl extends BaseDaoHandler  implements ConfigurazionePagamentoServizioDAO  {
	private static final long serialVersionUID = 1L;
	//inizio LP PG21XX04 Leak
	@Deprecated
	//fine LP PG21XX04 Leak
	public ConfigurazionePagamentoServizioDAOImpl(DataSource dataSource, String schema) throws SQLException {
		super(dataSource.getConnection(), schema);
	}
	//inizio LP PG21XX04 Leak
	public ConfigurazionePagamentoServizioDAOImpl(Connection connection, String schema) throws SQLException {
		super(connection, schema);
	}
	//fine LP PG21XX04 Leak

	public WalletPageList ListConfigurazionePagamentoServizio(
			ConfigurazionePagamentoServizio configurazionePagamentoServizio,
			int rowsPerPage, int pageNumber, String OrderBy)
			throws DaoException {
		CallableStatement callableStatement=null;
		Connection connection = null;
		ResultSet data = null;
		PageInfo pageInfo = null;
		WalletPageList walletPageList = null;
		try {

//			    IN I_PAGENO SMALLINT,
//				IN I_ROWSPERPAGE SMALLINT,
//				IN I_CSL_CSOCCSOC VARCHAR(5),
//				IN I_CSL_CUTECUTE VARCHAR(5),
//				IN I_CSL_KANEKENT CHAR(10),
//				IN I_CSL_CCSLCONE CHAR(3),
//				IN I_CSL_DCSLDESC VARCHAR(100),
//				IN I_CSL_GCSLGVAL_DA VARCHAR(10),
//				IN I_CSL_GCSLGVAL_A  VARCHAR(10),
//				OUT O_ROWINI INTEGER,
//				OUT O_ROWEND INTEGER,
//				OUT O_TOTROWS INTEGER,
//				OUT O_TOTPAGES SMALLINT
				
			connection = getConnection();
			callableStatement = Helper.prepareCall(connection, getSchema(), Routines.PYPPSSP_LST.routine());
			callableStatement.setInt(1, pageNumber);                          /* rows per page */
			callableStatement.setInt(2, rowsPerPage);                        /* page number*/
			callableStatement.setString(3,configurazionePagamentoServizio.getCodiceSocieta());
			callableStatement.setString(4,configurazionePagamentoServizio.getCuteCute());
			callableStatement.setString(5,configurazionePagamentoServizio.getChiaveEnte());
			callableStatement.setString(6,configurazionePagamentoServizio.getCodiceServizio());
			/* we register row start */
			callableStatement.registerOutParameter(7, Types.INTEGER);
			/* we register row end */
			callableStatement.registerOutParameter(8, Types.INTEGER);
			/* we register total rows */
			callableStatement.registerOutParameter(9, Types.INTEGER);
			/* we register total pages */
			callableStatement.registerOutParameter(10, Types.SMALLINT);
			 
			/* we execute procedure */
			if(callableStatement.execute())	{
				pageInfo = new PageInfo();
				pageInfo.setPageNumber(pageNumber);
				pageInfo.setRowsPerPage(rowsPerPage);
				pageInfo.setFirstRow(callableStatement.getInt(7));
				pageInfo.setLastRow(callableStatement.getInt(8));
				pageInfo.setNumRows(callableStatement.getInt(9));
				pageInfo.setNumPages(callableStatement.getInt(10));
				
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

	public EsitoRisposte delete(ConfigurazionePagamentoServizio configurazionePagamentoServizio)
			throws DaoException 
	{
		CallableStatement callableStatement=null;
		Connection connection = null;
		EsitoRisposte  esitoRisposte = new EsitoRisposte();
		try {
			connection = getConnection();
			callableStatement = Helper.prepareCall(connection, getSchema(), Routines.PYPPSSP_DEL.routine());
//			IN I_PPS_CSOCCSOC VARCHAR(5), 
//			IN I_PPS_CUTECUTE VARCHAR(5),
//			IN I_PPS_KANEKENT CHAR(10),
//			IN I_PPS_CPPSCODI CHAR(1),
//			OUT O_PPS_CODESITO VARCHAR(2),
//			OUT O_PPS_MSGESITO VARCHAR(100)

			callableStatement.setString(1, configurazionePagamentoServizio.getCodiceSocieta());
			callableStatement.setString(2, configurazionePagamentoServizio.getCuteCute());
			callableStatement.setString(3, configurazionePagamentoServizio.getChiaveEnte());
			callableStatement.setString(4, configurazionePagamentoServizio.getCodiceServizio());			
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

	public EsitoRisposte insert(ConfigurazionePagamentoServizio configurazionePagamentoServizio)
		throws DaoException 
	{
		CallableStatement callableStatement=null;
		Connection connection = null;
		EsitoRisposte esitoRisposte = new EsitoRisposte();
		try {
			connection = getConnection();
			callableStatement = Helper.prepareCall(connection, getSchema(), Routines.PYPPSSP_INS.routine());

//					IN I_PPS_CSOCCSOC VARCHAR(5), 
//					IN I_PPS_CUTECUTE VARCHAR(5),
//					IN I_PPS_KANEKENT CHAR(10),
//					IN I_PPS_CPPSCODI CHAR(1),
//					IN I_PPS_CPPSCPRI INTEGER,
//					OUT O_PPS_CODESITO VARCHAR(2),
//					OUT O_PPS_MSGESITO VARCHAR(100)			
//			private String codiceSocieta;        		//	"PPS_CSOCCSOC" VARCHAR(5)
//			private String cuteCute;					//	"PPS_CUTECUTE" VARCHAR(5)
//			private String chiaveEnte;					//	"PPS_KANEKENT" CHAR(10)
//			private String codiceServizio;  			//	"PPS_CSRVCODI" CHAR(2)
//			private Integer priorita;					//	"PPS_CPPSCPRI" INTEGER
			
			
			callableStatement.setString(1, configurazionePagamentoServizio.getCodiceSocieta());
			callableStatement.setString(2, configurazionePagamentoServizio.getCuteCute());
			callableStatement.setString(3, configurazionePagamentoServizio.getChiaveEnte());
			callableStatement.setString(4, configurazionePagamentoServizio.getCodiceServizio());
			callableStatement.setInt(5, configurazionePagamentoServizio.getPriorita());
			callableStatement.setString(6, configurazionePagamentoServizio.getOperatore());
			callableStatement.setString(7, configurazionePagamentoServizio.getEnteServizio());
			callableStatement.setString(8, configurazionePagamentoServizio.getImpostaServizio());

			callableStatement.registerOutParameter(9, Types.VARCHAR);
			callableStatement.registerOutParameter(10, Types.VARCHAR);			
			callableStatement.execute();
			esitoRisposte.setCodiceMessaggio(callableStatement.getString(9));
			esitoRisposte.setDescrizioneMessaggio(callableStatement.getString(10));
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

	public ConfigurazionePagamentoServizio select(ConfigurazionePagamentoServizio configurazionePagamentoServizio)
			throws DaoException 
	{
		CallableStatement callableStatement=null;
		ResultSet resultSet=null;
		Connection connection = null;
		CachedRowSet rowSet = null;
		try {
			connection = getConnection();
//			IN I_PPS_CSOCCSOC VARCHAR(5), 
//			IN I_PPS_CUTECUTE VARCHAR(5),
//			IN I_PPS_KANEKENT CHAR(10),
//			IN I_PPS_CPPSCODI CHAR(1)			
			callableStatement = Helper.prepareCall(connection, getSchema(), Routines.PYPPSSP_SEL.routine());
			callableStatement.setString(1, configurazionePagamentoServizio.getCodiceSocieta());
			callableStatement.setString(2, configurazionePagamentoServizio.getCuteCute());
			callableStatement.setString(3, configurazionePagamentoServizio.getChiaveEnte());
			callableStatement.setString(4, configurazionePagamentoServizio.getCodiceServizio());
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
				 String codiceSocieta = rowSet.getString(1);       		
				 String cuteCute = rowSet.getString(2);					
				 String chiaveEnte = rowSet.getString(3);
				 String codiceServizio = rowSet.getString(4);
				 Integer priorita = rowSet.getInt(5);
				 String operatore = rowSet.getString(6);
				 String enteSrv = rowSet.getString(7);
				 String impostaSrv = rowSet.getString(8);

				 configurazionePagamentoServizio = new ConfigurazionePagamentoServizio(
						 codiceSocieta, cuteCute, chiaveEnte, codiceServizio, priorita, operatore ,enteSrv,impostaSrv);
			
				 configurazionePagamentoServizio.setAttribute(WalletDAO.SELECT_XML, selectXml);
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
		return configurazionePagamentoServizio;
	}

	public Integer update(ConfigurazionePagamentoServizio configurazionePagamentoServizio)
		throws DaoException 
	{
		CallableStatement callableStatement=null;
		Connection connection = null;
		int ret=0;
		try {
			connection = getConnection();
			callableStatement = Helper.prepareCall(connection, getSchema(), Routines.PYPPSSP_UPD.routine());

//			IN I_PPS_CSOCCSOC VARCHAR(5), 
//			IN I_PPS_CUTECUTE VARCHAR(5),
//			IN I_PPS_KANEKENT CHAR(10),
//			IN I_PPS_CPPSCODI CHAR(1),
//			IN I_PPS_CPPSCPRI INTEGER,
//			OUT O_TOTROWS INTEGER )

//			private String codiceSocieta;        		//	"SLI_CSOCCSOC" VARCHAR(5)
//			private String cuteCute;					//	"SLI_CUTECUTE" VARCHAR(5)
//			private String chiaveEnte;					//	"SLI_KANEKENT" CHAR(10)
//			private Date dataValidita;  				//	"SLI_GSLIGVAL" DATE
//			private BigDecimal importoResiduo;			//	"SLI_ISLIIRES" DECIMAL(15 , 2)
//			private String smsSollecito;				//	"SLI_FSLIFSMS" CHAR(1)
			
			callableStatement.setString(1, configurazionePagamentoServizio.getCodiceSocieta());
			callableStatement.setString(2, configurazionePagamentoServizio.getCuteCute());
			callableStatement.setString(3, configurazionePagamentoServizio.getChiaveEnte());
			callableStatement.setString(4, configurazionePagamentoServizio.getCodiceServizio());
			callableStatement.setInt(5, configurazionePagamentoServizio.getPriorita());
			callableStatement.setString(6, configurazionePagamentoServizio.getOperatore());
			callableStatement.setString(7, configurazionePagamentoServizio.getEnteServizio());
			callableStatement.setString(8, configurazionePagamentoServizio.getImpostaServizio());
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
