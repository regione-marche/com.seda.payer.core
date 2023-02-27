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
import com.seda.data.dao.DAOHelper;
import com.seda.data.helper.Helper;
import com.seda.data.helper.HelperException;
import com.seda.data.spi.PageInfo;
import com.seda.payer.core.dao.Routines;
import com.seda.payer.core.exception.DaoException;
import com.seda.payer.core.handler.BaseDaoHandler;
import com.seda.payer.core.wallet.bean.ConfigurazioneSolleciti;
import com.seda.payer.core.wallet.bean.EsitoRisposte;
import com.seda.payer.core.wallet.bean.WalletPageList;

public class ConfigurazioneSollecitiDAOImpl extends BaseDaoHandler  implements ConfigurazioneSollecitiDAO  {

	private static final long serialVersionUID = 1L;
	//inizio LP PG21XX04 Leak
	@Deprecated
	//fine LP PG21XX04 Leak
	public ConfigurazioneSollecitiDAOImpl(DataSource dataSource, String schema) throws SQLException {
		super(dataSource.getConnection(), schema);
	}
	//inizio LP PG21XX04 Leak
	public ConfigurazioneSollecitiDAOImpl(Connection connection, String schema) throws SQLException {
		super(connection, schema);
	}
	//fine LP PG21XX04 Leak
	public EsitoRisposte insert(ConfigurazioneSolleciti configurazioneSolleciti )	throws DaoException {
		CallableStatement callableStatement=null;
		Connection connection = null;
		EsitoRisposte  esitoRisposte = new EsitoRisposte();
		try {
			connection = getConnection();
			callableStatement = Helper.prepareCall(connection, getSchema(), Routines.PYCSLSP_INS.routine());

//			IN I_CSL_CSOCCSOC VARCHAR(5), 
//			IN I_CSL_CUTECUTE VARCHAR(5),
//			IN I_CSL_KANEKENT CHAR(10),
//			IN I_CSL_GCSLGVAL DATE,
//			IN I_CSL_TCSLTIPO CHAR(1),
//			IN I_CSL_ICSLIRES DECIMAL(15,2),
//			IN I_CSL_NCSLNGIO INTEGER,
//			IN I_CSL_TCSLTSCH CHAR(1),
//			IN I_CSL_NCSLMPER_DA INTEGER,
//			IN I_CSL_NCSLMPER_A INTEGER,
//			IN I_CSL_FCSLMAIL CHAR(1),
//			IN I_CSL_CCSLCONE CHAR(3),
//			IN I_CSL_DCSLDESC VARCHAR(100),
//			IN I_CSL_ICSLIONE DECIMAL(15,2),
//			IN I_CSL_NCSLNING INTEGER,
//			IN I_CSL_CCSLCCCN char(12),
//			IN I_CSL_DCSLDCCN VARCHAR(100),
			
//			private String codiceSocieta;        		//	"CSL_CSOCCSOC" VARCHAR(5)
//			private String cuteCute;					//	"CSL_CUTECUTE" VARCHAR(5)
//			private String chiaveEnte;					//	"CSL_KANEKENT" CHAR(10)
//			private Date dataValidita;  				//	"CSL_GCSLGVAL" DATE
//			private String smsTipoCortesiaSollecito;	//	"CSL_TCSLTIPO" CHAR(1)
//			private BigDecimal importoResiduo;			//	"CSL_ICSLIRES" DECIMAL(10 , 2)
//			private Integer intervalloGiorniTraInviiSuccessivi;	//	"CSL_NCSLNGIO" INTEGER
//			private String tipoSchedulazione;			//	"CSL_TCSLTSCH" CHAR(1)
//			private Date inizioPeriodoInvioSMS;			//	"CSL_GCSLGPER_DA" DATE
//			private Date finePeriodoInvioSMS;			//	"CSL_GCSLGPER_A" DATE
//			private String invioEmail;					//	"CSL_FCSLMAIL" CHAR(1)
//			private String codiceOnereSollecitoCartaceo;//	"CSL_CCSLCONE" CHAR(3)
//			private String descrOnereSollecitoCartaceo;	//	"CSL_DCSLDESC" VARCHAR(100)
//			private BigDecimal importoOnere;			//	"CSL_ICSLIONE" DECIMAL(15,2)
//			private Integer giorniRivestizioneAnagrafica;//	"CSL_NCSLNING" INTEGER
//			private String contoCorrentePostale;		//	"CSL_CCSLCCCN" CHAR(12)
//			private String descrContoCorrentePostale;	//	"CSL_DCSLDCCN" VARCHAR(100)	
//			OUT O_CSL_CODESITO VARCHAR(2),
//			OUT O_CSL_MSGESITO VARCHAR(100)
			
			
			callableStatement.setString(1, configurazioneSolleciti.getCodiceSocieta());
			callableStatement.setString(2, configurazioneSolleciti.getCuteCute());
			callableStatement.setString(3, configurazioneSolleciti.getChiaveEnte());
			callableStatement.setDate(4, new Date(configurazioneSolleciti.getDataValidita().getTimeInMillis()));
			callableStatement.setString(5, configurazioneSolleciti.getSmsTipoCortesiaSollecito());
			callableStatement.setBigDecimal(6, configurazioneSolleciti.getImportoResiduo());
			callableStatement.setInt(7, configurazioneSolleciti.getIntervalloGiorniTraInviiSuccessivi());
			callableStatement.setString(8, configurazioneSolleciti.getTipoSchedulazione());
			callableStatement.setInt(9, configurazioneSolleciti.getMeseInizioInvioSMS());
			callableStatement.setInt(10,configurazioneSolleciti.getMeseFineInvioSMS());			
			callableStatement.setString(11, configurazioneSolleciti.getInvioEmail());
			callableStatement.setString(12, configurazioneSolleciti.getCodiceOnereSollecitoCartaceo());
			callableStatement.setString(13, configurazioneSolleciti.getDescrOnereSollecitoCartaceo());
			callableStatement.setBigDecimal(14, configurazioneSolleciti.getImportoOnere());
			callableStatement.setInt(15, configurazioneSolleciti.getGiorniRivestizioneAnagrafica());
			callableStatement.setString(16, configurazioneSolleciti.getContoCorrentePostale());
			callableStatement.setString(17, configurazioneSolleciti.getDescrContoCorrentePostale());
			callableStatement.setString(18, configurazioneSolleciti.getOperatore());
			callableStatement.setInt(19, configurazioneSolleciti.getIntervalloGiorniInvioSollecito());
			callableStatement.setString(20, configurazioneSolleciti.getTribXEvoluzione());
			callableStatement.registerOutParameter(21, Types.VARCHAR);
			callableStatement.registerOutParameter(22, Types.VARCHAR);			
			callableStatement.execute();
			esitoRisposte.setCodiceMessaggio(callableStatement.getString(21));
			esitoRisposte.setDescrizioneMessaggio(callableStatement.getString(22));
//			callableStatement.execute();
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


	public EsitoRisposte delete(ConfigurazioneSolleciti configurazioneSolleciti)
			throws DaoException {

		CallableStatement callableStatement=null;
		Connection connection = null;
		EsitoRisposte  esitoRisposte = new EsitoRisposte();
		try {
			connection = getConnection();
			callableStatement = Helper.prepareCall(connection, getSchema(), Routines.PYCSLSP_DEL.routine());
//				private String codiceSocieta;        		//	"I_CSL_CSOCCSOC" VARCHAR(5)
//				private String cuteCute;					//	"I_CSL_CUTECUTE" VARCHAR(5)
//				private String chiaveEnte;					//	"I_CSL_KANEKENT" CHAR(10)
//				private Date dataValidita;  				//	"CSL_GCSLGVAL" DATE
//				private String smsTipoCortesiaSollecito;	//	"CSL_TCSLTIPO" CHAR(1)
			callableStatement.setString(1, configurazioneSolleciti.getCodiceSocieta());
			callableStatement.setString(2, configurazioneSolleciti.getCuteCute());
			callableStatement.setString(3, configurazioneSolleciti.getChiaveEnte());
			callableStatement.setDate(4, new Date(configurazioneSolleciti.getDataValidita().getTimeInMillis()));
			callableStatement.setString(5, configurazioneSolleciti.getSmsTipoCortesiaSollecito());
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


	public ConfigurazioneSolleciti select(ConfigurazioneSolleciti configurazioneSolleciti)throws DaoException {
		CallableStatement callableStatement=null;
		ResultSet resultSet=null;
		Connection connection = null;
		CachedRowSet rowSet = null;
		try {
			connection = getConnection();
			callableStatement = Helper.prepareCall(connection, getSchema(), Routines.PYCSLSP_SEL.routine());
			callableStatement.setString(1, configurazioneSolleciti.getCodiceSocieta());
			callableStatement.setString(2, configurazioneSolleciti.getCuteCute());
			callableStatement.setString(3, configurazioneSolleciti.getChiaveEnte());
			callableStatement.setDate(4, new Date(configurazioneSolleciti.getDataValidita().getTimeInMillis()));
			callableStatement.setString(5, configurazioneSolleciti.getSmsTipoCortesiaSollecito());
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
				 Date dataValidita = rowSet.getDate(4);
				 String smsTipoCortesiaSollecito = rowSet.getString(5);
				 BigDecimal importoResiduo = rowSet.getBigDecimal(6);
				 Integer intervalloGiorniTraInviiSuccessivi = rowSet.getInt(7);
				 String tipoSchedulazione = rowSet.getString(8);
				 Integer meseInizioInvioSMS = rowSet.getInt(9);
				 Integer meseFineInvioSMS = rowSet.getInt(10);
				 String invioEmail = rowSet.getString(11);
				 String codiceOnereSollecitoCartaceo = rowSet.getString(12);
				 String descrOnereSollecitoCartaceo = rowSet.getString(13);
				 BigDecimal importoOnere = rowSet.getBigDecimal(14);
				 Integer giorniRivestizioneAnagrafica = rowSet.getInt(15);
				 String contoCorrentePostale = rowSet.getString(16);
				 String descrContoCorrentePostale = rowSet.getString(17);
				 String operatore = rowSet.getString(18);
				 Integer intervallogioniinviosollecito = rowSet.getInt(19);
				 String flagAttivazione = rowSet.getString(20).trim();
				 String tribXEvoluzione = rowSet.getString(21).trim();

				 Calendar dataValiditaCal=Calendar.getInstance();
				 dataValiditaCal.setTime(dataValidita);
				 
 				 configurazioneSolleciti = new ConfigurazioneSolleciti(codiceSocieta, cuteCute, chiaveEnte, dataValiditaCal, 
						smsTipoCortesiaSollecito, importoResiduo, intervalloGiorniTraInviiSuccessivi, tipoSchedulazione, 
						meseInizioInvioSMS, meseFineInvioSMS, invioEmail, codiceOnereSollecitoCartaceo, descrOnereSollecitoCartaceo, 
						importoOnere, giorniRivestizioneAnagrafica, contoCorrentePostale, descrContoCorrentePostale,operatore,intervallogioniinviosollecito,
						flagAttivazione,tribXEvoluzione);
			
				//configurazioneSolleciti.setAttribute("denom", denominazione);
				//configurazioneSolleciti.setAttribute(WalletDAO.SELECT_XML, selectXml);
			}
			
		} catch (SQLException e) {
			throw new DaoException(e);
		} catch (IllegalArgumentException e) {
			throw new DaoException(e);
		} catch (HelperException e) {
			throw new DaoException(e);
		}
		finally {
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
		return configurazioneSolleciti;
	}


	public Integer update(ConfigurazioneSolleciti configurazioneSolleciti )	throws DaoException {
		CallableStatement callableStatement=null;
		Connection connection = null;
		int ret=0;
		try {
			connection = getConnection();
			callableStatement = Helper.prepareCall(connection, getSchema(), Routines.PYCSLSP_UPD.routine());

//			IN I_CSL_CSOCCSOC VARCHAR(5), 
//			IN I_CSL_CUTECUTE VARCHAR(5),
//			IN I_CSL_KANEKENT CHAR(10),
//			IN I_CSL_GCSLGVAL DATE,
//			IN I_CSL_TCSLTIPO CHAR(1),
//			IN I_CSL_ICSLIRES DECIMAL(15,2),
//			IN I_CSL_NCSLNGIO INTEGER,
//			IN I_CSL_TCSLTSCH CHAR(1),
//			IN I_CSL_NCSLMPER_DA INTEGER,
//			IN I_CSL_NCSLMPER_A INTEGER,
//			IN I_CSL_FCSLMAIL CHAR(1),
//			IN I_CSL_CCSLCONE CHAR(3),
//			IN I_CSL_DCSLDESC VARCHAR(100),
//			IN I_CSL_ICSLIONE DECIMAL(15,2),
//			IN I_CSL_NCSLNING INTEGER,
//			IN I_CSL_CCSLCCCN char(12),
//			IN I_CSL_DCSLDCCN VARCHAR(100),
//			OUT O_TOTROWS INTEGER )
			
//			private String codiceSocieta;        		//	"CSL_CSOCCSOC" VARCHAR(5)
//			private String cuteCute;					//	"CSL_CUTECUTE" VARCHAR(5)
//			private String chiaveEnte;					//	"CSL_KANEKENT" CHAR(10)
//			private Date dataValidita;  				//	"CSL_GCSLGVAL" DATE
//			private String smsTipoCortesiaSollecito;	//	"CSL_TCSLTIPO" CHAR(1)
//			private BigDecimal importoResiduo;			//	"CSL_ICSLIRES" DECIMAL(10 , 2)
//			private Integer intervalloGiorniTraInviiSuccessivi;	//	"CSL_NCSLNGIO" INTEGER
//			private String tipoSchedulazione;			//	"CSL_TCSLTSCH" CHAR(1)
//			private Date inizioPeriodoInvioSMS;			//	"CSL_GCSLGPER_DA" DATE
//			private Date finePeriodoInvioSMS;			//	"CSL_GCSLGPER_A" DATE
//			private String invioEmail;					//	"CSL_FCSLMAIL" CHAR(1)
//			private String codiceOnereSollecitoCartaceo;//	"CSL_CCSLCONE" CHAR(3)
//			private String descrOnereSollecitoCartaceo;	//	"CSL_DCSLDESC" VARCHAR(100)
//			private BigDecimal importoOnere;			//	"CSL_ICSLIONE" DECIMAL(15,2)
//			private Integer giorniRivestizioneAnagrafica;//	"CSL_NCSLNING" INTEGER
//			private String contoCorrentePostale;		//	"CSL_CCSLCCCN" CHAR(12)
//			private String descrContoCorrentePostale;	//	"CSL_DCSLDCCN" VARCHAR(100)	
//			OUT O_CSL_CODESITO VARCHAR(2),
//			OUT O_CSL_MSGESITO VARCHAR(100)
			
			
			callableStatement.setString(1, configurazioneSolleciti.getCodiceSocieta());
			callableStatement.setString(2, configurazioneSolleciti.getCuteCute());
			callableStatement.setString(3, configurazioneSolleciti.getChiaveEnte());
			callableStatement.setDate(4, new Date(configurazioneSolleciti.getDataValidita().getTimeInMillis()));
			callableStatement.setString(5, configurazioneSolleciti.getSmsTipoCortesiaSollecito());
			callableStatement.setBigDecimal(6, configurazioneSolleciti.getImportoResiduo());
			callableStatement.setInt(7, configurazioneSolleciti.getIntervalloGiorniTraInviiSuccessivi());
			callableStatement.setString(8, configurazioneSolleciti.getTipoSchedulazione());
			callableStatement.setInt(9, configurazioneSolleciti.getMeseInizioInvioSMS());
			callableStatement.setInt(10,configurazioneSolleciti.getMeseFineInvioSMS());			
			callableStatement.setString(11, configurazioneSolleciti.getInvioEmail());
			callableStatement.setString(12, configurazioneSolleciti.getCodiceOnereSollecitoCartaceo());
			callableStatement.setString(13, configurazioneSolleciti.getDescrOnereSollecitoCartaceo());
			callableStatement.setBigDecimal(14, configurazioneSolleciti.getImportoOnere());
			callableStatement.setInt(15, configurazioneSolleciti.getGiorniRivestizioneAnagrafica());
			callableStatement.setString(16, configurazioneSolleciti.getContoCorrentePostale());
			callableStatement.setString(17, configurazioneSolleciti.getDescrContoCorrentePostale());
			callableStatement.setString(18, configurazioneSolleciti.getOperatore());
			callableStatement.setInt(19, configurazioneSolleciti.getIntervalloGiorniInvioSollecito());
			callableStatement.setString(20 , configurazioneSolleciti.getFlagAttivazione());
			callableStatement.setString(21 , configurazioneSolleciti.getTribXEvoluzione());

//			callableStatement.setString(5, "1");
//			callableStatement.setBigDecimal(6, configurazioneSolleciti.getImportoResiduo());
//			callableStatement.setInt(7, 1);
//			callableStatement.setString(8, "a");
//			callableStatement.setInt(9, 1);
//			callableStatement.setInt(10,1);			
//			callableStatement.setString(11, "a");
//			callableStatement.setString(12, "a");
//			callableStatement.setString(13, "a");
//			callableStatement.setBigDecimal(14, configurazioneSolleciti.getImportoOnere());
//			callableStatement.setInt(15, 1);
//			callableStatement.setString(16, "a");
//			callableStatement.setString(17, "a");
//			callableStatement.setString(18, "a");
			
			callableStatement.registerOutParameter(22, Types.INTEGER);						
			callableStatement.execute();
			ret = callableStatement.getInt(22);
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

		
	public WalletPageList sollecitiListConfigurazioneSolleciti(
				ConfigurazioneSolleciti configurazioneSolleciti, int rowsPerPage,
				int pageNumber, String OrderBy) throws DaoException {
			
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
			callableStatement = Helper.prepareCall(connection, getSchema(), Routines.PYCSLSP_LST.routine());
			callableStatement.setInt(1, pageNumber);                          /* rows per page */
			callableStatement.setInt(2, rowsPerPage);                        /* page number*/
			callableStatement.setString(3,configurazioneSolleciti.getCodiceSocieta());
			callableStatement.setString(4,configurazioneSolleciti.getCuteCute());
			callableStatement.setString(5,configurazioneSolleciti.getChiaveEnte());
			callableStatement.setString(6,configurazioneSolleciti.getCodiceOnereSollecitoCartaceo());
			callableStatement.setString(7,configurazioneSolleciti.getDescrOnereSollecitoCartaceo());
			callableStatement.setString(8 , (String)configurazioneSolleciti.getAttribute(ConfigurazioneSollecitiDAO.VALIDITA_DA));
			callableStatement.setString(9 , (String)configurazioneSolleciti.getAttribute(ConfigurazioneSollecitiDAO.VALIDITA_A));
			callableStatement.setString(10 , (String)configurazioneSolleciti.getSmsTipoCortesiaSollecito());
			callableStatement.setString(10 , (String)configurazioneSolleciti.getSmsTipoCortesiaSollecito());
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



}