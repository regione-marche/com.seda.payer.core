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
import com.seda.payer.core.wallet.bean.ConfigurazioneAttribuzionePagamentoServizio;
import com.seda.payer.core.wallet.bean.EsitoRisposte;
import com.seda.payer.core.wallet.bean.WalletPageList;

public class ConfigurazioneAttribuzionePagamentoServizioDAOImpl  extends BaseDaoHandler  implements ConfigurazioneAttribuzionePagamentoServizioDAO  {
	private static final long serialVersionUID = 1L;
	// CERIONI
	//public ConfigurazioneAttribuzionePagamentoServizioDAOImpl(Connection connection, String schema) {
//		super(connection, schema);
//	
//	}
	//inizio LP PG21XX04 Leak
	@Deprecated
	//fine LP PG21XX04 Leak
	public ConfigurazioneAttribuzionePagamentoServizioDAOImpl(DataSource dataSource, String schema) throws SQLException {
		super(dataSource.getConnection(), schema);
	}
	//inizio LP PG21XX04 Leak
	public ConfigurazioneAttribuzionePagamentoServizioDAOImpl(Connection connection, String schema) {
		super(connection, schema);
	}
	//fine LP PG21XX04 Leak

	public WalletPageList ListaConfigurazioneAttribuzionePagamentoServizio( ConfigurazioneAttribuzionePagamentoServizio configurazioneAttribuzionePagamentoServizio,
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
			callableStatement = Helper.prepareCall(connection, getSchema(), Routines.PYPASSP_LST.routine());
			callableStatement.setInt(1, pageNumber);                          /* rows per page */
			callableStatement.setInt(2, rowsPerPage);                        /* page number*/
			callableStatement.setString(3,configurazioneAttribuzionePagamentoServizio.getCodiceSocieta());
			callableStatement.setString(4,configurazioneAttribuzionePagamentoServizio.getCuteCute());
			callableStatement.setString(5,configurazioneAttribuzionePagamentoServizio.getChiaveEnte());
			callableStatement.setString(6,configurazioneAttribuzionePagamentoServizio.getCodiceServizio());
			
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

	public EsitoRisposte delete(ConfigurazioneAttribuzionePagamentoServizio configurazioneAttribuzionePagamentoServizio)
			throws DaoException {
		CallableStatement callableStatement=null;
		Connection connection = null;
		EsitoRisposte  esitoRisposte = new EsitoRisposte();
		try {
			connection = getConnection();
			callableStatement = Helper.prepareCall(connection, getSchema(), Routines.PYPASSP_DEL.routine());
			
//			CREATE PROCEDURE PYPASSP_DEL ( 
//					IN I_PAS_CSOCCSOC VARCHAR(5), 
//					IN I_PAS_CUTECUTE VARCHAR(5),
//					IN I_PAS_KANEKENT CHAR(10),
//					IN I_PAS_CSRVCODI CHAR(2),
//					IN I_PAS_CTRBCODI CHAR(3),
//					OUT O_PAS_CODESITO VARCHAR(2),
//					OUT O_PAS_MSGESITO VARCHAR(100)
//			private String codiceSocieta;        		//	"PAS_CSOCCSOC" VARCHAR(5)
//			private String cuteCute;					//	"PAS_CUTECUTE" VARCHAR(5)
//			private String chiaveEnte;					//	"PAS_KANEKENT" CHAR(10)
//			private String codiceServizio;  			//	"PAS_CSRVCODI" CHAR(2)
//			private String codiceTributo;				//	"PAS_CTRBCODI" CHAR(3)


			callableStatement.setString(1, configurazioneAttribuzionePagamentoServizio.getCodiceSocieta());
			callableStatement.setString(2, configurazioneAttribuzionePagamentoServizio.getCuteCute());
			callableStatement.setString(3, configurazioneAttribuzionePagamentoServizio.getChiaveEnte());
			callableStatement.setString(4, configurazioneAttribuzionePagamentoServizio.getCodiceServizio());			
			callableStatement.setInt(5, configurazioneAttribuzionePagamentoServizio.getPriorita());
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

	public EsitoRisposte insert(ConfigurazioneAttribuzionePagamentoServizio configurazioneAttribuzionePagamentoServizio)
			throws DaoException {

		CallableStatement callableStatement=null;
		Connection connection = null;
		EsitoRisposte esitoRisposte = new EsitoRisposte();
		try {
			connection = getConnection();
			callableStatement = Helper.prepareCall(connection, getSchema(), Routines.PYPASSP_INS.routine());

//			CREATE PROCEDURE PYPASSP_INS ( 
//				1	IN I_PAS_CSOCCSOC VARCHAR(5), 
//				2	IN I_PAS_CUTECUTE VARCHAR(5),
//				3	IN I_PAS_KANEKENT CHAR(10),
//				4	IN I_PAS_CSRVCODI CHAR(2),
//				5	IN I_PAS_CTRBCODI VARCHAR(3),
//				6	IN I_PAS_CPASCPRI INTEGER,
//				7	IN I_PAS_CPASCOPE VARCHAR(50),
//					OUT O_PAS_CODESITO VARCHAR(2),
//					OUT O_PAS_MSGESITO VARCHAR(100)
			
//			private String codiceSocieta;        		//	"PAS_CSOCCSOC" VARCHAR(5)
//			private String cuteCute;					//	"PAS_CUTECUTE" VARCHAR(5)
//			private String chiaveEnte;					//	"PAS_KANEKENT" CHAR(10)
//			private String codiceServizio;  			//	"PAS_CSRVCODI" CHAR(2)
//			private String ordineScuola;				//	"PAS_TSCUTIPO" CHAR(1)
//			private Integer priorita;					//	"PAS_CPASCPRI" INTEGER
			
			
			callableStatement.setString(1, configurazioneAttribuzionePagamentoServizio.getCodiceSocieta());
			callableStatement.setString(2, configurazioneAttribuzionePagamentoServizio.getCuteCute());
			callableStatement.setString(3, configurazioneAttribuzionePagamentoServizio.getChiaveEnte());
			callableStatement.setString(4, configurazioneAttribuzionePagamentoServizio.getCodiceServizio());
			callableStatement.setString(5, configurazioneAttribuzionePagamentoServizio.getCodiceTributo());
			callableStatement.setInt(6, configurazioneAttribuzionePagamentoServizio.getPriorita());
			callableStatement.setString(7, configurazioneAttribuzionePagamentoServizio.getOperatore());
			callableStatement.setString(8, configurazioneAttribuzionePagamentoServizio.getCodiceTributo2());
			callableStatement.setString(9, configurazioneAttribuzionePagamentoServizio.getCodiceTributo3());
			
			
			callableStatement.registerOutParameter(10, Types.VARCHAR);
			callableStatement.registerOutParameter(11, Types.VARCHAR);			
			callableStatement.execute();
			esitoRisposte.setCodiceMessaggio(callableStatement.getString(10));
			esitoRisposte.setDescrizioneMessaggio(callableStatement.getString(11));
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
		return esitoRisposte;	}

	
	public ConfigurazioneAttribuzionePagamentoServizio select(ConfigurazioneAttribuzionePagamentoServizio configurazioneAttribuzionePagamentoServizio)
			throws DaoException {
		CallableStatement callableStatement=null;
		ResultSet resultSet=null;
		Connection connection = null;
		CachedRowSet rowSet = null;
		try {
			connection = getConnection();
			callableStatement = Helper.prepareCall(connection, getSchema(), Routines.PYPASSP_SEL.routine());
			callableStatement.setString(1, configurazioneAttribuzionePagamentoServizio.getCodiceSocieta());
			callableStatement.setString(2, configurazioneAttribuzionePagamentoServizio.getCuteCute());
			callableStatement.setString(3, configurazioneAttribuzionePagamentoServizio.getChiaveEnte());
			callableStatement.setString(4, configurazioneAttribuzionePagamentoServizio.getCodiceServizio());			
			callableStatement.setInt(5, configurazioneAttribuzionePagamentoServizio.getPriorita());
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
				 String codiceTributo = rowSet.getString(5);
				 Integer priorita = rowSet.getInt(6);
				 String descrizioneTributo = rowSet.getString(7);
				 String operatore = rowSet.getString(8);
				 String codiceTributo2 = rowSet.getString(9);
				 String codiceTributo3 = rowSet.getString(10);
				 

				 
				 configurazioneAttribuzionePagamentoServizio = new ConfigurazioneAttribuzionePagamentoServizio(
						 codiceSocieta, cuteCute, chiaveEnte, codiceServizio, codiceTributo, descrizioneTributo, priorita, operatore,codiceTributo2,codiceTributo3 );
			
				 configurazioneAttribuzionePagamentoServizio.setAttribute(WalletDAO.SELECT_XML, selectXml);
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
		return configurazioneAttribuzionePagamentoServizio;
	}

	public EsitoRisposte update(ConfigurazioneAttribuzionePagamentoServizio configurazioneAttribuzionePagamentoServizio)
			throws DaoException {
		CallableStatement callableStatement=null;
		Connection connection = null;
		EsitoRisposte esito = new EsitoRisposte();
		String retCode="00";
		String retMes = "";
		try {
			connection = getConnection();
			callableStatement = Helper.prepareCall(connection, getSchema(), Routines.PYPASSP_UPD.routine());

//			CREATE PROCEDURE PYPASSP_UPD (
//			1		IN I_PAS_CSOCCSOC VARCHAR(5), 
//			2		IN I_PAS_CUTECUTE VARCHAR(5),
//			3		IN I_PAS_KANEKENT CHAR(10),
//			4		IN I_PAS_CSRVCODI CHAR(2),
//			5		IN I_PAS_CTRBCODI CHAR(3),
//			6		IN I_PAS_CPASCOPE VARCHAR(50),
//			7		OUT O_TOTROWS INTEGER )			
//			private String codiceSocieta;        		//	"PAS_CSOCCSOC" VARCHAR(5)
//			private String cuteCute;					//	"PAS_CUTECUTE" VARCHAR(5)
//			private String chiaveEnte;					//	"PAS_KANEKENT" CHAR(10)
//			private String codiceServizio;  			//	"PAS_CSRVCODI" CHAR(2)
//			private String ordineScuola;				//	"PAS_TSCUTIPO" CHAR(1)
			
			callableStatement.setString(1, configurazioneAttribuzionePagamentoServizio.getCodiceSocieta());
			callableStatement.setString(2, configurazioneAttribuzionePagamentoServizio.getCuteCute());
			callableStatement.setString(3, configurazioneAttribuzionePagamentoServizio.getChiaveEnte());
			callableStatement.setString(4, configurazioneAttribuzionePagamentoServizio.getCodiceServizio());
			callableStatement.setString(5, configurazioneAttribuzionePagamentoServizio.getCodiceTributo());
			callableStatement.setInt(6, configurazioneAttribuzionePagamentoServizio.getPriorita());
			callableStatement.setString(7, configurazioneAttribuzionePagamentoServizio.getOperatore());
			callableStatement.setString(8, configurazioneAttribuzionePagamentoServizio.getCodiceTributo2());
			callableStatement.setString(9, configurazioneAttribuzionePagamentoServizio.getCodiceTributo3());
			callableStatement.registerOutParameter(10, Types.CHAR);						
			callableStatement.registerOutParameter(11, Types.CHAR);
			callableStatement.execute();
			retCode = callableStatement.getString(10);
			retMes = callableStatement.getString(11);
			callableStatement.execute();
			esito.setCodiceMessaggio(retCode);
			esito.setDescrizioneMessaggio(retMes);
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
		return esito;		
	}

}
