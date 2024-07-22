package com.seda.payer.core.wallet.dao;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Calendar;
import javax.sql.DataSource;
import javax.sql.rowset.CachedRowSet;
import com.seda.commons.string.Convert;
import com.seda.data.procedure.reflection.MetaProcedure;
import com.seda.data.procedure.reflection.ProcedureReflectorException;
import com.seda.data.spi.PageInfo;
import com.seda.payer.core.dao.Routines;
import com.seda.payer.core.exception.DaoException;
import com.seda.payer.core.handler.BaseDaoHandler;
import com.seda.payer.core.wallet.bean.ConfigurazioneEvoIntimazioni;
import com.seda.payer.core.wallet.bean.EsitoRisposte;
import com.seda.payer.core.wallet.bean.WalletPageList;

public class ConfigurazioneEvoIntimazioniDAOImpl extends BaseDaoHandler  implements ConfigurazioneEvoIntimazioniDAO  {

	private static final long serialVersionUID = 1L;
	//inizio LP PG21XX04 Leak
	@Deprecated
	//fine LP PG21XX04 Leak
	public ConfigurazioneEvoIntimazioniDAOImpl(DataSource dataSource, String schema) throws SQLException {
		super(dataSource.getConnection(), schema);
	}
	//inizio LP PG21XX04 Leak
	public ConfigurazioneEvoIntimazioniDAOImpl(Connection connection, String schema) throws SQLException {
		super(connection, schema);
	}
	//fine LP PG21XX04 Leak
	public EsitoRisposte insert(ConfigurazioneEvoIntimazioni configurazioneEvoIntimazioni )	throws DaoException {
		CallableStatement callableStatement=null;
		Connection connection = null;
		EsitoRisposte esitoRisposte = new EsitoRisposte();
		try {
			connection = getConnection();
			//inizio LP PGNTCORE-24
			//callableStatement = Helper.prepareCall(connection, getSchema(), Routines.PYSLISP_INS.routine());
			callableStatement = MetaProcedure.prepareCall(connection, getSchema(), Routines.PYSLISP_INS.routine());
			//fine LP PGNTCORE-24
//			IN I_SLI_CSOCCSOC VARCHAR(5), 
//			IN I_SLI_CUTECUTE VARCHAR(5),
//			IN I_SLI_KANEKENT CHAR(10),
//			IN I_SLI_GSLIGVAL DATE,
//			IN I_SLI_ISLIIRES DECIMAL(15,2),
//			IN I_SLI_FSLIFSMS VARCHAR(1),
//			OUT O_SLI_CODESITO VARCHAR(2),
//			OUT O_SLI_MSGESITO VARCHAR(100)
			
//			private String codiceSocieta;        		//	"SLI_CSOCCSOC" VARCHAR(5)
//			private String cuteCute;					//	"SLI_CUTECUTE" VARCHAR(5)
//			private String chiaveEnte;					//	"SLI_KANEKENT" CHAR(10)
//			private Date dataValidita;  				//	"SLI_GSLIGVAL" DATE
//			private BigDecimal importoResiduo;			//	"SLI_ISLIIRES" DECIMAL(15 , 2)
//			private String smsSollecito;				//	"SLI_FSLIFSMS" CHAR(1)
			
			
			callableStatement.setString(1, configurazioneEvoIntimazioni.getCodiceSocieta());
			callableStatement.setString(2, configurazioneEvoIntimazioni.getCuteCute());
			callableStatement.setString(3, configurazioneEvoIntimazioni.getChiaveEnte());
			callableStatement.setDate(4, new Date(configurazioneEvoIntimazioni.getDataValidita().getTimeInMillis()));
			callableStatement.setBigDecimal(5, configurazioneEvoIntimazioni.getImportoResiduo());
			callableStatement.setString(6, configurazioneEvoIntimazioni.getSmsSollecito());			
			callableStatement.setString(7, configurazioneEvoIntimazioni.getOperatore());
			callableStatement.setString(8, configurazioneEvoIntimazioni.getFlagSollecitoCartaceo());
			callableStatement.setInt(9, configurazioneEvoIntimazioni.getIntervalloGiorniEvoluzione());
			
			callableStatement.setString(10, configurazioneEvoIntimazioni.getFlagAttivazione());
			callableStatement.setString(11, configurazioneEvoIntimazioni.getCodiceEnteEvoluzione());
			callableStatement.setString(12, configurazioneEvoIntimazioni.getImpostaServizioEvoluzione());
			callableStatement.setString(13, configurazioneEvoIntimazioni.getTipoServizio());
			 
			callableStatement.registerOutParameter(14, Types.VARCHAR);
			callableStatement.registerOutParameter(15, Types.VARCHAR);			
			callableStatement.execute();
			esitoRisposte.setCodiceMessaggio(callableStatement.getString(14));
			esitoRisposte.setDescrizioneMessaggio(callableStatement.getString(15));
			callableStatement.execute();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DaoException(e);
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
			throw new DaoException(e);
		//inizio LP PGNTCORE-24
		//} catch (HelperException e) {
		//	e.printStackTrace();
		//	throw new DaoException(e);
		} catch (ProcedureReflectorException e) {
			e.printStackTrace();
			throw new DaoException(e);
		//fine LP PGNTCORE-24
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


	public EsitoRisposte delete(ConfigurazioneEvoIntimazioni configurazioneEvoIntimazioni)
			throws DaoException {

		CallableStatement callableStatement=null;
		Connection connection = null;
		EsitoRisposte  esitoRisposte = new EsitoRisposte();
		try {
			connection = getConnection();
			//inizio LP PGNTCORE-24
			//callableStatement = Helper.prepareCall(connection, getSchema(), Routines.PYSLISP_DEL.routine());
			callableStatement = MetaProcedure.prepareCall(connection, getSchema(), Routines.PYSLISP_DEL.routine());
			//fine LP PGNTCORE-24
//			IN I_SLI_CSOCCSOC VARCHAR(5), 
//			IN I_SLI_CUTECUTE VARCHAR(5),
//			IN I_SLI_KANEKENT CHAR(10),
//			IN I_SLI_GSLIGVAL DATE,
//			OUT O_SLI_CODESITO VARCHAR(2),
//			OUT O_SLI_MSGESITO VARCHAR(100)			

			callableStatement.setString(1, configurazioneEvoIntimazioni.getCodiceSocieta());
			callableStatement.setString(2, configurazioneEvoIntimazioni.getCuteCute());
			callableStatement.setString(3, configurazioneEvoIntimazioni.getChiaveEnte());
			callableStatement.setDate(4, new Date(configurazioneEvoIntimazioni.getDataValidita().getTimeInMillis()));			
			callableStatement.setString(5, configurazioneEvoIntimazioni.getTipoServizio());
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
		//inizio LP PGNTCORE-24
		//} catch (HelperException e) {
		//	e.printStackTrace();
		//	throw new DaoException(e);
		} catch (ProcedureReflectorException e) {
			e.printStackTrace();
			throw new DaoException(e);
		//fine LP PGNTCORE-24
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


	public ConfigurazioneEvoIntimazioni select(ConfigurazioneEvoIntimazioni configurazioneEvoIntimazioni)throws DaoException {
		CallableStatement callableStatement=null;
		ResultSet resultSet=null;
		Connection connection = null;
		CachedRowSet rowSet = null;
		try {
			connection = getConnection();
			//inizio LP PGNTCORE-24
			//callableStatement = Helper.prepareCall(connection, getSchema(), Routines.PYSLISP_SEL.routine());
			callableStatement = MetaProcedure.prepareCall(connection, getSchema(), Routines.PYSLISP_SEL.routine());
			//fine LP PGNTCORE-24
			callableStatement.setString(1, configurazioneEvoIntimazioni.getCodiceSocieta());
			callableStatement.setString(2, configurazioneEvoIntimazioni.getCuteCute());
			callableStatement.setString(3, configurazioneEvoIntimazioni.getChiaveEnte());
			callableStatement.setDate(4, new Date(configurazioneEvoIntimazioni.getDataValidita().getTimeInMillis()));
			callableStatement.setString(5, configurazioneEvoIntimazioni.getTipoServizio());
			//callableStatement.setString(5, configurazioneEvoIntimazioni.getSmsSollecito());
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
				 Date dataValidita = rowSet.getDate(4);				 
				 BigDecimal importoResiduo = rowSet.getBigDecimal(5);
				 String SmsSollecito = rowSet.getString(6);
				 Calendar dataValiditaCal=Calendar.getInstance();
				 dataValiditaCal.setTime(dataValidita);
				 String operatore = rowSet.getString(7);
				 String flagSollecitoCartaceo = rowSet.getString(8);
				 Integer intervalloGiorniEvoluzione = rowSet.getInt(9);
				 
				 String flagAttivazione = rowSet.getString(10);
				 String codiceEnteEvoluzione = rowSet.getString(11);
				 String impostaServizioEvoluzione = rowSet.getString(12);
				 String tipoServizio = rowSet.getString(13);
				 
				 
				 configurazioneEvoIntimazioni = new ConfigurazioneEvoIntimazioni(
						 codiceSocieta, cuteCute, chiaveEnte, dataValiditaCal, SmsSollecito, importoResiduo,operatore,flagSollecitoCartaceo,intervalloGiorniEvoluzione,flagAttivazione,codiceEnteEvoluzione,impostaServizioEvoluzione,
						 tipoServizio);
			
				//configurazioneSolleciti.setAttribute("denom", denominazione);
				 configurazioneEvoIntimazioni.setAttribute(WalletDAO.SELECT_XML, selectXml);
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
		return configurazioneEvoIntimazioni;
	}


	public Integer update(ConfigurazioneEvoIntimazioni configurazioneEvoIntimazioni)	throws DaoException {
		CallableStatement callableStatement=null;
		Connection connection = null;
		int ret=0;
		try {
			connection = getConnection();
			//inizio LP PGNTCORE-24
			//callableStatement = Helper.prepareCall(connection, getSchema(), Routines.PYSLISP_UPD.routine());
			callableStatement = MetaProcedure.prepareCall(connection, getSchema(), Routines.PYSLISP_UPD.routine());
			//fine LP PGNTCORE-24
//			IN I_SLI_CSOCCSOC VARCHAR(5), 
//			IN I_SLI_CUTECUTE VARCHAR(5),
//			IN I_SLI_KANEKENT CHAR(10),
//			IN I_SLI_GSLIGVAL DATE,
//			IN I_SLI_ISLIIRES DECIMAL(15,2),
//			IN I_SLI_FSLIFSMS VARCHAR(1),
//			OUT O_TOTROWS INTEGER )

//			private String codiceSocieta;        		//	"SLI_CSOCCSOC" VARCHAR(5)
//			private String cuteCute;					//	"SLI_CUTECUTE" VARCHAR(5)
//			private String chiaveEnte;					//	"SLI_KANEKENT" CHAR(10)
//			private Date dataValidita;  				//	"SLI_GSLIGVAL" DATE
//			private BigDecimal importoResiduo;			//	"SLI_ISLIIRES" DECIMAL(15 , 2)
//			private String smsSollecito;				//	"SLI_FSLIFSMS" CHAR(1)
			
			callableStatement.setString(1, configurazioneEvoIntimazioni.getCodiceSocieta());
			callableStatement.setString(2, configurazioneEvoIntimazioni.getCuteCute());
			callableStatement.setString(3, configurazioneEvoIntimazioni.getChiaveEnte());
			callableStatement.setDate(4, new Date(configurazioneEvoIntimazioni.getDataValidita().getTimeInMillis()));
			callableStatement.setBigDecimal(5, configurazioneEvoIntimazioni.getImportoResiduo());
			callableStatement.setString(6, configurazioneEvoIntimazioni.getSmsSollecito());
			callableStatement.setString(7,configurazioneEvoIntimazioni.getOperatore());
			callableStatement.setString(8,configurazioneEvoIntimazioni.getFlagSollecitoCartaceo());
			callableStatement.setInt(9,configurazioneEvoIntimazioni.getIntervalloGiorniEvoluzione());
			
			callableStatement.setString(10, configurazioneEvoIntimazioni.getFlagAttivazione());
			callableStatement.setString(11, configurazioneEvoIntimazioni.getCodiceEnteEvoluzione());
			callableStatement.setString(12, configurazioneEvoIntimazioni.getImpostaServizioEvoluzione());
			callableStatement.setString(13, configurazioneEvoIntimazioni.getTipoServizio());
			
			callableStatement.registerOutParameter(14, Types.INTEGER);						
			callableStatement.execute();
			ret = callableStatement.getInt(14);
			callableStatement.execute();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DaoException(e);
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
			throw new DaoException(e);
		//inizio LP PGNTCORE-24
		//} catch (HelperException e) {
		//	e.printStackTrace();
		//	throw new DaoException(e);
		} catch (ProcedureReflectorException e) {
			e.printStackTrace();
			throw new DaoException(e);
		//fine LP PGNTCORE-24
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

		
	public WalletPageList ListConfigurazioneEvoIntimazioni(
			ConfigurazioneEvoIntimazioni configurazioneEvoIntimazioni, int rowsPerPage,
			int pageNumber, String OrderBy) throws DaoException {
			
		CallableStatement callableStatement=null;
		Connection connection = null;
		ResultSet data = null;
		PageInfo pageInfo = null;
		WalletPageList walletPageList = null;
		try {

//			IN I_PAGENO SMALLINT,
//			IN I_ROWSPERPAGE SMALLINT,
//			IN I_SLI_CSOCCSOC VARCHAR(5),
//			IN I_SLI_CUTECUTE VARCHAR(5),
//			IN I_SLI_KANEKENT CHAR(10),
//			IN I_SLI_FSLIFSMS CHAR(1),
//			IN I_SLI_GCSLGVAL_DA VARCHAR(10),
//			IN I_SLI_GCSLGVAL_A  VARCHAR(10),
//			OUT O_ROWINI INTEGER,
//			OUT O_ROWEND INTEGER,
//			OUT O_TOTROWS INTEGER,
//			OUT O_TOTPAGES SMALLINT
				
			connection = getConnection();
			//inizio LP PGNTCORE-24
			//callableStatement = Helper.prepareCall(connection, getSchema(), Routines.PYSLISP_LST.routine());
			callableStatement = MetaProcedure.prepareCall(connection, getSchema(), Routines.PYSLISP_LST.routine());
			//fine LP PGNTCORE-24
			callableStatement.setInt(1, pageNumber);                          /* rows per page */
			callableStatement.setInt(2, rowsPerPage);                        /* page number*/
			callableStatement.setString(3,configurazioneEvoIntimazioni.getCodiceSocieta());
			callableStatement.setString(4,configurazioneEvoIntimazioni.getCuteCute());
			callableStatement.setString(5,configurazioneEvoIntimazioni.getChiaveEnte());
			callableStatement.setString(6,configurazioneEvoIntimazioni.getSmsSollecito());
			callableStatement.setString(7 , (String)configurazioneEvoIntimazioni.getAttribute(ConfigurazioneEvoIntimazioniDAO.VALIDITA_DA));
			callableStatement.setString(8 , (String)configurazioneEvoIntimazioni.getAttribute(ConfigurazioneEvoIntimazioniDAO.VALIDITA_A));
			callableStatement.setString(9 , (String)configurazioneEvoIntimazioni.getTipoServizio());
			callableStatement.setString(10,configurazioneEvoIntimazioni.getFlagSollecitoCartaceo());
			/* we register row start */
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
				walletPageList = new WalletPageList(pageInfo, "00","",getWebRowSetXml());
				return walletPageList;
			}
			
			
			
		} catch (SQLException e) {
			e.printStackTrace();
			walletPageList = new WalletPageList(pageInfo, "01","Sql-Exception","");
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
			walletPageList = new WalletPageList(pageInfo, "01","Sql-Exception","");
		//inizio LP PGNTCORE-24
		//} catch (HelperException e) {
		//	e.printStackTrace();
		//	walletPageList = new WalletPageList(pageInfo, "01","Sql-Exception","");
		} catch (ProcedureReflectorException e) {
			e.printStackTrace();
			walletPageList = new WalletPageList(pageInfo, "01","Sql-Exception","");
		//fine LP PGNTCORE-24
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


}