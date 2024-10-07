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
import com.seda.payer.core.mercato.bean.ConfigurazioneTariffe;
import com.seda.payer.core.mercato.bean.EsitoRisposte;
import com.seda.payer.core.mercato.bean.MercatoPageList;

public class ConfigurazioneTariffeDAOImpl extends BaseDaoHandler  implements ConfigurazioneTariffeDAO  {
	//private static final long serialVersionUID = 1L;
	//inizio LP PG21XX04 Leak
	@Deprecated
	//fine LP PG21XX04 Leak
	public ConfigurazioneTariffeDAOImpl(DataSource dataSource, String schema) throws SQLException {
		super(dataSource.getConnection(), schema);
	}

	//inizio LP PG21XX04 Leak
	public ConfigurazioneTariffeDAOImpl(Connection connection, String schema) throws SQLException {
		super(connection, schema);
	}
	//fine LP PG21XX04 Leak

	public MercatoPageList ListConfigurazioneTariffe(
			ConfigurazioneTariffe configurazioneTariffe,
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
//				IN I_TAM_CSOCCSOC VARCHAR(5),
//				IN I_TAM_CUTECUTE VARCHAR(5),
//				IN I_TAM_KANEKENT CHAR(10),
//				IN I_TAM_CTAMKMRC VARCHAR(10),
//				IN I_TAM_CTAMKPZL VARCHAR(64),
//				IN I_TAM_ITAMGSET INTEGER,
//				IN I_TAM_CAUTNMAU VARCHAR(30),
//				IN I_TAM_CTAMKTPB VARCHAR(10),
//				IN I_TAM_CTAMKPEG VARCHAR(10),
//				IN I_TAM_GTAMDTIN_DA TIMESTAMP,
//				IN I_TAM_GTAMDTIN_A TIMESTAMP,
//				IN I_TAM_GTAMDTFN_DA TIMESTAMP,
//				IN I_TAM_GTAMDTFN_A TIMESTAMP,			
//				OUT O_ROWINI INTEGER,
//				OUT O_ROWEND INTEGER,
//				OUT O_TOTROWS INTEGER,
//				OUT O_TOTPAGES SMALLINT			
			connection = getConnection();
			//inizio LP PGNTCORE-24
			//callableStatement = Helper.prepareCall(connection, getSchema(), Routines.PYTAMSP_LST.routine());
            callableStatement = prepareCall(Routines.PYTAMSP_LST.routine());
			//fine LP PGNTCORE-24
			callableStatement.setInt(1, pageNumber);                          /* rows per page */
			callableStatement.setInt(2, rowsPerPage);                        /* page number*/
			callableStatement.setString(3,configurazioneTariffe.getCodiceSocieta());
			callableStatement.setString(4,configurazioneTariffe.getCuteCute());
			callableStatement.setString(5,configurazioneTariffe.getChiaveEnte());
			callableStatement.setString(6,configurazioneTariffe.getCodiceKeyAreaMercantile());
			callableStatement.setString(7,configurazioneTariffe.getCodiceKeyPiazzola());
			callableStatement.setInt(8,configurazioneTariffe.getCodiceGiornoSettimana());
			callableStatement.setString(9,configurazioneTariffe.getCodiceKeyAutorizzazione());  //Per la lista corrisponde al numero autorizzazione
			callableStatement.setString(10,configurazioneTariffe.getCodiceKeyTipologiaBanco());
			callableStatement.setString(11,configurazioneTariffe.getCodiceKeyPeriodoGiornal());
			if (configurazioneTariffe.getDataInizioValidita()==null) {
				callableStatement.setTimestamp(12, null);
			} else {
			    callableStatement.setTimestamp(12, new java.sql.Timestamp(configurazioneTariffe.getDataInizioValidita().getTimeInMillis()));
			}			
			if (configurazioneTariffe.getDataAInizioValidita()==null) {
				callableStatement.setTimestamp(13, null);
			} else {
			    callableStatement.setTimestamp(13, new java.sql.Timestamp(configurazioneTariffe.getDataAInizioValidita().getTimeInMillis()));
			}			

			if (configurazioneTariffe.getDataFineValidita()==null) {
				callableStatement.setTimestamp(14, null);
			} else {
			    callableStatement.setTimestamp(14, new java.sql.Timestamp(configurazioneTariffe.getDataFineValidita().getTimeInMillis()));			
			}
			if (configurazioneTariffe.getDataAFineValidita()==null) {
				callableStatement.setTimestamp(15, null);
			} else {
			    callableStatement.setTimestamp(15, new java.sql.Timestamp(configurazioneTariffe.getDataAFineValidita().getTimeInMillis()));		
			}			
			
			/* we register row start */
			callableStatement.registerOutParameter(16, Types.INTEGER);
			/* we register row end */
			callableStatement.registerOutParameter(17, Types.INTEGER);
			/* we register total rows */
			callableStatement.registerOutParameter(18, Types.INTEGER);
			/* we register total pages */
			callableStatement.registerOutParameter(19, Types.SMALLINT);
			 
			/* we execute procedure */
			if(callableStatement.execute())	{
				pageInfo = new PageInfo();
				pageInfo.setPageNumber(pageNumber);
				pageInfo.setRowsPerPage(rowsPerPage);
				pageInfo.setFirstRow(callableStatement.getInt(16));
				pageInfo.setLastRow(callableStatement.getInt(17));
				pageInfo.setNumRows(callableStatement.getInt(18));
				pageInfo.setNumPages(callableStatement.getInt(19));
				
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

	public EsitoRisposte delete(ConfigurazioneTariffe configurazioneTariffe)
			throws DaoException 
	{
		CallableStatement callableStatement=null;
		Connection connection = null;
		EsitoRisposte  esitoRisposte = new EsitoRisposte();
		try {
			connection = getConnection();
			//inizio LP PGNTCORE-24
			//callableStatement = Helper.prepareCall(connection, getSchema(), Routines.PYTAMSP_DEL.routine());
            callableStatement = prepareCall(Routines.PYTAMSP_DEL.routine());
			//fine LP PGNTCORE-24
//			IN I_TAM_CSOCCSOC VARCHAR(5), 
//			IN I_TAM_CUTECUTE VARCHAR(5),
//			IN I_TAM_KANEKENT CHAR(10),
//			IN I_TAM_KTAMKTAM VARCHAR(64),
//			OUT O_TAM_CODESITO VARCHAR(2),
//			OUT O_TAM_MSGESITO VARCHAR(100)

			callableStatement.setString(1, configurazioneTariffe.getCodiceSocieta());
			callableStatement.setString(2, configurazioneTariffe.getCuteCute());
			callableStatement.setString(3, configurazioneTariffe.getChiaveEnte());
			callableStatement.setString(4, configurazioneTariffe.getCodiceKeyTariffa());
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

	public EsitoRisposte insert(ConfigurazioneTariffe configurazioneTariffe)
		throws DaoException 
	{
		CallableStatement callableStatement=null;
		Connection connection = null;
		EsitoRisposte esitoRisposte = new EsitoRisposte();
		try {
			connection = getConnection();
			//inizio LP PGNTCORE-24
			//callableStatement = Helper.prepareCall(connection, getSchema(), Routines.PYTAMSP_INS.routine());
            callableStatement = prepareCall(Routines.PYTAMSP_INS.routine());
			//fine LP PGNTCORE-24
//					IN I_TAM_KTAMKTAM" VARCHAR(64),
//					IN I_TAM_CSOCCSOC" CHAR(5),
//					IN I_TAM_CUTECUTE" CHAR(5),
//					IN I_TAM_KANEKENT" CHAR(10),
//					IN I_TAM_CTAMKMRC" VARCHAR(10),
//					IN I_TAM_CTAMKPZL" VARCHAR(64),
//					IN I_TAM_ITAMGSEM" INTEGER,
//					IN I_TAM_CTAMKAUT" VARCHAR(64),
//					IN I_TAM_CTAMKTPB" VARCHAR(10),
//					IN I_TAM_CTAMKPEG" VARCHAR(10),
//					IN I_TAM_CTAMKCOM" VARCHAR(10),
//					IN I_TAM_DTAMTRTA" DECIMAL(9,5),
//					IN I_TAM_DTAMTRCO" DECIMAL(9,5),
//					IN I_TAM_GTAMDTIN" TIMESTAMP,
//					IN I_TAM_GTAMDTFN" TIMESTAMP
//					OUT O_TAM_CODESITO VARCHAR(2),
//					OUT O_TAM_MSGESITO VARCHAR(100)	
//			private String codiceKeyTariffa				//  "TAM_KTAMKTAM" VARCHAR(64)			
//			private String codiceSocieta;        		//	"TAM_CSOCCSOC" VARCHAR(5)
//			private String cuteCute;					//	"TAM_CUTECUTE" VARCHAR(5)
//			private String chiaveEnte;					//	"TAM_KANEKENT" CHAR(10)
//			private String codiceKeyAreaMercantile		//  "TAM_CTAMKMRC" VARCHAR(10)
//			private String codiceKeyPiazzola;  			//	"TAM_CTAMKPZL" VARCHAR(64)
//			private Integer codiceGiornoSettimana;		//  "TAM_CTAMGSET" INTEGER
// 			private String codiceKeyTipologiaBanco;		//	"TAM_CTAMKTPB" VARCHAR(10)
//			private String codiceKeyPeriodoGiornal;		//  "TAM_CTAMKPEG" VARCHAR(10)
//			private String codiceKeyCompenso;			//  "TAM_CTAMKCOM" VARCHAR(10)
//			private Double tariffaTari					//  "TAM_DTAMTRTA" DECIMAL(9,5)
//			private Double tariffaCosap					//  "TAM_DTAMTRCO" DECIMAL(9,5)
//			private Calendar dataInizioValidita;		//  "MRC_GMRCDTIN" TIMESTAMP
//			private Calendar dataFineValidita;			//  "MRC_GMRCDTFN" TIMESTAMP
			
			callableStatement.setString(1, configurazioneTariffe.getCodiceKeyTariffa());
			callableStatement.setString(2, configurazioneTariffe.getCodiceSocieta());
			callableStatement.setString(3, configurazioneTariffe.getCuteCute());
			callableStatement.setString(4, configurazioneTariffe.getChiaveEnte());
			callableStatement.setString(5, configurazioneTariffe.getCodiceKeyAreaMercantile());
			callableStatement.setString(6, configurazioneTariffe.getCodiceKeyPiazzola());
			callableStatement.setInt(7, configurazioneTariffe.getCodiceGiornoSettimana());
			callableStatement.setString(8, configurazioneTariffe.getCodiceKeyAutorizzazione());
			callableStatement.setString(9, configurazioneTariffe.getCodiceKeyTipologiaBanco());
			callableStatement.setString(10, configurazioneTariffe.getCodiceKeyPeriodoGiornal());
			callableStatement.setString(11, configurazioneTariffe.getCodiceKeyCompenso());
			if (configurazioneTariffe.getTariffaTari() == null) {
				callableStatement.setDouble(12, 0.0);
			} else {
				callableStatement.setDouble(12, configurazioneTariffe.getTariffaTari());
			}
			if (configurazioneTariffe.getTariffaCosap() == null) {
				callableStatement.setDouble(13, 0.0);
			} else {
				callableStatement.setDouble(13, configurazioneTariffe.getTariffaCosap());
			}
			callableStatement.setTimestamp(14, new java.sql.Timestamp(configurazioneTariffe.getDataInizioValidita().getTimeInMillis()));
			if (configurazioneTariffe.getDataFineValidita()==null) {
				callableStatement.setTimestamp(15, null);
			} else {
			    callableStatement.setTimestamp(15, new java.sql.Timestamp(configurazioneTariffe.getDataFineValidita().getTimeInMillis()));
			}
			callableStatement.registerOutParameter(16, Types.VARCHAR);
			callableStatement.registerOutParameter(17, Types.VARCHAR);			
			callableStatement.execute();
			esitoRisposte.setCodiceMessaggio(callableStatement.getString(16));
			esitoRisposte.setDescrizioneMessaggio(callableStatement.getString(17));
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

	public ConfigurazioneTariffe select(ConfigurazioneTariffe configurazioneTariffe)
			throws DaoException 
	{
		CallableStatement callableStatement=null;
		ResultSet resultSet=null;
		Connection connection = null;
		CachedRowSet rowSet = null;
		try {
			connection = getConnection();
			//inizio LP PGNTCORE-24
			//callableStatement = Helper.prepareCall(connection, getSchema(), Routines.PYTAMSP_SEL.routine());
            callableStatement = prepareCall(Routines.PYTAMSP_SEL.routine());
			//fine LP PGNTCORE-24
//			IN I_TAM_CSOCCSOC VARCHAR(5), 
//			IN I_TAM_CUTECUTE VARCHAR(5),
//			IN I_TAM_KANEKENT CHAR(10),
//			IN I_TAM_CTAMKMRC VARCHAR(10),
//			IN I_TAM_CTAMKPZL VARCHAR(64),
//			IN I_TAM_ITAMGSET INTEGER,			
//			IN I_TAM_CTAMKAUT VARCHAR(64),
//			IN I_TAM_CTAMKTPB VARCHAR(10),
//			IN I_TAM_CTAMKPEG VARCHAR(10),					
			callableStatement.setString(1, configurazioneTariffe.getCodiceSocieta());
			callableStatement.setString(2, configurazioneTariffe.getCuteCute());
			callableStatement.setString(3, configurazioneTariffe.getChiaveEnte());
			callableStatement.setString(4, configurazioneTariffe.getCodiceKeyAreaMercantile());
			callableStatement.setString(5, configurazioneTariffe.getCodiceKeyPiazzola());
			if (configurazioneTariffe.getCodiceGiornoSettimana() == null) {
				callableStatement.setInt(6, 0);
			} else {
				callableStatement.setInt(6, configurazioneTariffe.getCodiceGiornoSettimana());
			}
			callableStatement.setString(7, configurazioneTariffe.getCodiceKeyAutorizzazione());
			callableStatement.setString(8, configurazioneTariffe.getCodiceKeyTipologiaBanco());
			callableStatement.setString(9, configurazioneTariffe.getCodiceKeyPeriodoGiornal());
			//callableStatement.setTimestamp(9, new java.sql.Timestamp(configurazioneTariffe.getDataInizioValidita().getTimeInMillis()));			
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
				 String codiceKeyTariffa = rowSet.getString(1);
				 String codiceSocieta = rowSet.getString(2);       		
				 String cuteCute = rowSet.getString(3);					
				 String chiaveEnte = rowSet.getString(4);
				 String codiceKeyAreaMercantile = rowSet.getString(5);
				 String codiceKeyPiazzola = rowSet.getString(6);
				 Integer codiceGiornoSettimana = rowSet.getInt(7);
				 String codiceKeyAutorizzazione = rowSet.getString(8);
				 String codiceKeyTipologiaBanco = rowSet.getString(9);
				 String codiceKeyPeriodoGiornal = rowSet.getString(10);
				 String codiceKeyCompenso = rowSet.getString(11);
				 Double tariffaTari = rowSet.getDouble(12);
				 Double tariffaCosap = rowSet.getDouble(13);
				 GregorianCalendar dataInizioValidita = new GregorianCalendar();
				 dataInizioValidita.setTimeInMillis(rowSet.getTimestamp(14).getTime());
				 GregorianCalendar dataFineValidita = new GregorianCalendar();
				 dataFineValidita.setTimeInMillis(rowSet.getTimestamp(15).getTime());

				 configurazioneTariffe = new ConfigurazioneTariffe(
						 codiceKeyTariffa, codiceSocieta, cuteCute, chiaveEnte, codiceKeyAreaMercantile,
						 codiceKeyPiazzola, codiceGiornoSettimana, codiceKeyAutorizzazione, codiceKeyTipologiaBanco,  
						 codiceKeyPeriodoGiornal, codiceKeyCompenso, tariffaTari, tariffaCosap, 
						 dataInizioValidita, dataFineValidita, null, null, null, null, null, null);
			
				 configurazioneTariffe.setAttribute(MercatoDAO.SELECT_XML, selectXml);
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
		return configurazioneTariffe;
	}

	public ConfigurazioneTariffe getPerKey(ConfigurazioneTariffe configurazioneTariffe)
	throws DaoException {
		CallableStatement callableStatement=null;
		ResultSet resultSet=null;
		Connection connection = null;
		CachedRowSet rowSet = null;
		try {
			connection = getConnection();
			//inizio LP PGNTCORE-24
			//callableStatement = Helper.prepareCall(connection, getSchema(), Routines.PYTAMSP_SLK.routine());
            callableStatement = prepareCall(Routines.PYTAMSP_SLK.routine());
			//fine LP PGNTCORE-24
//	IN I_TAM_KTAMKTAM VARCHAR(64) 				
			callableStatement.setString(1, configurazioneTariffe.getCodiceKeyTariffa());
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
				String codiceKeyTariffa = rowSet.getString(1);
				String codiceSocieta = rowSet.getString(2);       		
				String cuteCute = rowSet.getString(3);					
				String chiaveEnte = rowSet.getString(4);
				String codiceKeyAreaMercantile = rowSet.getString(5);
				String codiceKeyPiazzola = rowSet.getString(6);
				Integer codiceGiornoSettimana = rowSet.getInt(7);
				String codiceKeyAutorizzazione = rowSet.getString(8);
				String codiceKeyTipologiaBanco = rowSet.getString(9);
				String codiceKeyPeriodoGiornal = rowSet.getString(10);
				String codiceKeyCompenso = rowSet.getString(11);
				Double tariffaTari = rowSet.getDouble(12);
				Double tariffaCosap = rowSet.getDouble(13);
				GregorianCalendar dataInizioValidita = new GregorianCalendar();
				dataInizioValidita.setTimeInMillis(rowSet.getTimestamp(14).getTime());
				GregorianCalendar dataFineValidita = new GregorianCalendar();
				dataFineValidita.setTimeInMillis(rowSet.getTimestamp(15).getTime());

				configurazioneTariffe = new ConfigurazioneTariffe(
				  codiceKeyTariffa, codiceSocieta, cuteCute, chiaveEnte, codiceKeyAreaMercantile,
				  codiceKeyPiazzola, codiceGiornoSettimana, codiceKeyAutorizzazione, codiceKeyTipologiaBanco,  
				  codiceKeyPeriodoGiornal, codiceKeyCompenso, tariffaTari, tariffaCosap, 
				  dataInizioValidita, dataFineValidita, null, null, null, null, null, null);
	
				configurazioneTariffe.setAttribute(MercatoDAO.SELECT_XML, selectXml);
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
		return configurazioneTariffe;
}

	public ArrayList<ConfigurazioneTariffe> listTariffe(ConfigurazioneTariffe configurazioneTariffe) throws DaoException {
//		IN I_TAM_CSOCCSOC VARCHAR(5), 
//		IN I_TAM_CUTECUTE VARCHAR(5),
//		IN I_TAM_KANEKENT CHAR(10),
//		IN I_TAM_CTAMKMRC VARCHAR(10),
//		IN I_TAM_CTAMKPZL VARCHAR(64),
//		IN I_TAM_ITAMGSEM INTEGER,
//		IN I_TAM_CAUTNMAU VARCHAR(10),
//      IN I_TAM_CTAMKTPB VARCHAR(10),		
//		IN I_TAM_CTAMKPEG VARCHAR(10)
		CallableStatement callableStatement=null;
		ArrayList<ConfigurazioneTariffe> configurazioneTariffeList = null;
		Connection connection = null;
		ResultSet resultSet=null;
		try {
			connection = getConnection();
			//inizio LP PGNTCORE-24
			//callableStatement = Helper.prepareCall(connection, getSchema(), Routines.PYTAMSP_TOT.routine());
            callableStatement = prepareCall(Routines.PYTAMSP_TOT.routine());
			//fine LP PGNTCORE-24
			callableStatement.setString(1, configurazioneTariffe.getCodiceSocieta());
			callableStatement.setString(2, configurazioneTariffe.getCuteCute());
			callableStatement.setString(3, configurazioneTariffe.getChiaveEnte());
			callableStatement.setString(4, configurazioneTariffe.getCodiceKeyAreaMercantile());
			callableStatement.setString(5, configurazioneTariffe.getCodiceKeyPiazzola());
			callableStatement.setInt(6, configurazioneTariffe.getCodiceGiornoSettimana());
			callableStatement.setString(7, configurazioneTariffe.getCodiceKeyAutorizzazione());
			callableStatement.setString(8, configurazioneTariffe.getCodiceKeyTipologiaBanco());
			callableStatement.setString(9, configurazioneTariffe.getCodiceKeyPeriodoGiornal());
			//callableStatement.setTimestamp(10, new java.sql.Timestamp(configurazioneTariffe.getDataInizioValidita().getTimeInMillis()));
			callableStatement.execute();
			resultSet=callableStatement.getResultSet();
			
			if ( !resultSet.next() ) {
				configurazioneTariffeList = new ArrayList<ConfigurazioneTariffe>();
			} else {
				configurazioneTariffeList = new ArrayList<ConfigurazioneTariffe>();
				do {
					ConfigurazioneTariffe item = new ConfigurazioneTariffe();
					item.setCodiceKeyTariffa(resultSet.getString("TAM_KTAMKTAM"));
					item.setCodiceKeyAutorizzazione(resultSet.getString("AUT_CAUTNMAU"));
					item.setCodiceGiornoSettimana(Integer.parseInt(resultSet.getString("TAM_ITAMGSEM")));
					item.setDescrAreaMercato(resultSet.getString("MRC_CMRCDSAM"));
					item.setDescrPiazzola(resultSet.getString("PZL_CPZLDSPZ"));
					item.setDescrTipologiaBanco(resultSet.getString("TPB_CTPBDSTB"));
					item.setDescrPeriodoGiorn(resultSet.getString("PEG_CPEGDSPE"));
					item.setTariffaTari(resultSet.getDouble("TAM_DTAMTRTA"));
					item.setTariffaCosap(resultSet.getDouble("TAM_DTAMTRCO"));
					item.setCodiceKeyCompenso("");
					GregorianCalendar DataIniVal = new GregorianCalendar();
					DataIniVal.setTimeInMillis(resultSet.getTimestamp("TAM_GTAMDTIN").getTime());
					item.setDataInizioValidita(DataIniVal);
					GregorianCalendar DataFinVal = new GregorianCalendar();
					DataFinVal.setTimeInMillis(resultSet.getTimestamp("TAM_GTAMDTFN").getTime());
					item.setDataFineValidita(DataFinVal);
					configurazioneTariffeList.add(item);
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
		
		return configurazioneTariffeList;
		
	}			

	public ArrayList<ConfigurazioneTariffe> getAllPerAutorizzazione(ConfigurazioneTariffe configurazioneTariffe) throws DaoException {
//		IN I_TAM_CSOCCSOC VARCHAR(5), 
//		IN I_TAM_CUTECUTE VARCHAR(5),
//		IN I_TAM_KANEKENT CHAR(10),
//		IN I_TAM_CAUTNMAU VARCHAR(10)

		CallableStatement callableStatement=null;
		ArrayList<ConfigurazioneTariffe> configurazioneTariffeList = null;
		Connection connection = null;
		ResultSet resultSet=null;
		try {
			connection = getConnection();
			//inizio LP PGNTCORE-24
			//callableStatement = Helper.prepareCall(connection, getSchema(), Routines.PYTAMSP_APA.routine());
            callableStatement = prepareCall(Routines.PYTAMSP_APA.routine());
			//fine LP PGNTCORE-24
			callableStatement.setString(1, configurazioneTariffe.getCodiceSocieta());
			callableStatement.setString(2, configurazioneTariffe.getCuteCute());
			callableStatement.setString(3, configurazioneTariffe.getChiaveEnte());
			callableStatement.setString(4, configurazioneTariffe.getCodiceKeyAutorizzazione());
			callableStatement.execute();
			resultSet=callableStatement.getResultSet();
			
			if ( !resultSet.next() ) {
				configurazioneTariffeList = new ArrayList<ConfigurazioneTariffe>();
			} else {
				configurazioneTariffeList = new ArrayList<ConfigurazioneTariffe>();
				do {
					ConfigurazioneTariffe item = new ConfigurazioneTariffe();
					item.setCodiceKeyAutorizzazione(resultSet.getString("AUT_CAUTNMAU"));
					item.setCodiceGiornoSettimana(Integer.parseInt(resultSet.getString("TAM_ITAMGSEM")));
					item.setDescrAreaMercato(resultSet.getString("MRC_CMRCDSAM"));
					item.setDescrPiazzola(resultSet.getString("PZL_CPZLDSPZ"));
					item.setDescrTipologiaBanco(resultSet.getString("TPB_CTPBDSTB"));
					item.setDescrPeriodoGiorn(resultSet.getString("PEG_CPEGDSPE"));
					item.setTariffaTari(resultSet.getDouble("TAM_DTAMTRTA"));
					item.setTariffaCosap(resultSet.getDouble("TAM_DTAMTRCO"));
					item.setCodiceKeyCompenso("");
					GregorianCalendar DataIniVal = new GregorianCalendar();
					DataIniVal.setTimeInMillis(resultSet.getTimestamp("TAM_GTAMDTIN").getTime());
					item.setDataInizioValidita(DataIniVal);
					GregorianCalendar DataFinVal = new GregorianCalendar();
					DataFinVal.setTimeInMillis(resultSet.getTimestamp("TAM_GTAMDTFN").getTime());
					item.setDataFineValidita(DataFinVal);
					configurazioneTariffeList.add(item);
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
		
		return configurazioneTariffeList;
		
	}		

	public ArrayList<ConfigurazioneTariffe> getAllPerPiazzola(ConfigurazioneTariffe configurazioneTariffe) throws DaoException {
//		IN I_TAM_CTAMKPZL VARCHAR(64) 

		CallableStatement callableStatement=null;
		ArrayList<ConfigurazioneTariffe> configurazioneTariffeList = null;
		Connection connection = null;
		ResultSet resultSet=null;
		try {
			connection = getConnection();
			//inizio LP PGNTCORE-24
			//callableStatement = Helper.prepareCall(connection, getSchema(), Routines.PYTAMSP_APL.routine());
            callableStatement = prepareCall(Routines.PYTAMSP_APL.routine());
			//fine LP PGNTCORE-24
			callableStatement.setString(1, configurazioneTariffe.getCodiceKeyPiazzola());
			callableStatement.execute();
			resultSet=callableStatement.getResultSet();
			
			if ( !resultSet.next() ) {
				configurazioneTariffeList = new ArrayList<ConfigurazioneTariffe>();
			} else {
				configurazioneTariffeList = new ArrayList<ConfigurazioneTariffe>();
				do {
					ConfigurazioneTariffe item = new ConfigurazioneTariffe();
					item.setCodiceKeyTariffa(resultSet.getString("TAM_KTAMKTAM"));
					item.setCodiceKeyAutorizzazione(resultSet.getString("AUT_CAUTNMAU"));
					item.setCodiceGiornoSettimana(Integer.parseInt(resultSet.getString("TAM_ITAMGSEM")));
					item.setDescrAreaMercato(resultSet.getString("MRC_CMRCDSAM"));
					item.setDescrPiazzola(resultSet.getString("PZL_CPZLDSPZ"));
					item.setDescrTipologiaBanco(resultSet.getString("TPB_CTPBDSTB"));
					item.setDescrPeriodoGiorn(resultSet.getString("PEG_CPEGDSPE"));
					item.setTariffaTari(resultSet.getDouble("TAM_DTAMTRTA"));
					item.setTariffaCosap(resultSet.getDouble("TAM_DTAMTRCO"));
					item.setCodiceKeyCompenso("");
					GregorianCalendar DataIniVal = new GregorianCalendar();
					DataIniVal.setTimeInMillis(resultSet.getTimestamp("TAM_GTAMDTIN").getTime());
					item.setDataInizioValidita(DataIniVal);
					GregorianCalendar DataFinVal = new GregorianCalendar();
					DataFinVal.setTimeInMillis(resultSet.getTimestamp("TAM_GTAMDTFN").getTime());
					item.setDataFineValidita(DataFinVal);
					configurazioneTariffeList.add(item);
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
		
		return configurazioneTariffeList;
		
	}		
	
	
	public Integer update(ConfigurazioneTariffe configurazioneTariffe)
		throws DaoException 
	{
		CallableStatement callableStatement=null;
		Connection connection = null;
		int ret=0;
		try {
			connection = getConnection();
			//inizio LP PGNTCORE-24
			//callableStatement = Helper.prepareCall(connection, getSchema(), Routines.PYTAMSP_UPD.routine());
            callableStatement = prepareCall(Routines.PYTAMSP_UPD.routine());
			//fine LP PGNTCORE-24
//			IN I_TAM_CSOCCSOC VARCHAR(5), 
//			IN I_TAM_CUTECUTE VARCHAR(5),
//			IN I_TAM_KANEKENT CHAR(10),
//			IN I_TAM_CTAMKMRC VARCHAR(10),	
//			IN I_TAM_CTAMKPZL VARCHAR(64),
//			IN I_TAM_ITAMGSEM INTEGER,			
//			IN I_TAM_CTAMKAUT VARCHAR(64),
//			IN I_TAM_CTAMKTPB VARCHAR(10),
//			IN I_TAM_CTAMKPEG VARCHAR(10),
//			IN I_TAM_DTAMTRTA DECIMAL(9,5),
//			IN I_TAM_DTAMTRCO DECIMAL(9,5),
//			IN I_TAM_GPZLDTIN TIMESTAMP,
//			IN I_TAM_GPZLDTFN TIMESTAMP,
//			OUT O_TOTROWS INTEGER 

//			private String codiceSocieta;        		//	"TAM_CSOCCSOC" VARCHAR(5)
//			private String cuteCute;					//	"TAM_CUTECUTE" VARCHAR(5)
//			private String chiaveEnte;					//	"TAM_KANEKENT" CHAR(10)
//			private String codiceKeyAreaMercantile		//  "TAM_CTAMKMRC" VARCHAR(10)
//			private String codiceKeyPiazzola			//	"TAM_CTAMKPZL VARCHAR(10)
//			private Integer codiceGiornoSettimana		//  "TAM_ITAMGSEM" INTEGER
//			private String codiceKeyAutorizzazione		//  "TAM_CTAMKAUT" VARCHAR(64)
//			private String codiceKeyTipologiaBanco		//  "TAM_CTAMKTPB" VARCHAR(10)
//			private String codiceKeyPeriodoGiornal		//  "TAM_CTAMKPEG" VARCHAR(10)			
//			private Double tariffaTari					//  "TAM_DTAMTRTA" DECIMAL(9,5)
//			private Double tariffaCosap					//  "TAM_DTAMTRCO" DECIMAL(9,5)			
//			private Calendar dataInizioValidita;		//  "PZL_GPZLDTIN" TIMESTAMP
//			private Calendar dataFineValdita;			//  "PZL_GPZLDTFN" TIMESTAMP
			
			callableStatement.setString(1, configurazioneTariffe.getCodiceSocieta());
			callableStatement.setString(2, configurazioneTariffe.getCuteCute());
			callableStatement.setString(3, configurazioneTariffe.getChiaveEnte());
			callableStatement.setString(4, configurazioneTariffe.getCodiceKeyAreaMercantile());
			callableStatement.setString(5, configurazioneTariffe.getCodiceKeyPiazzola());
			callableStatement.setInt(6, configurazioneTariffe.getCodiceGiornoSettimana());
			callableStatement.setString(7, configurazioneTariffe.getCodiceKeyAutorizzazione());
			callableStatement.setString(8, configurazioneTariffe.getCodiceKeyTipologiaBanco());
			callableStatement.setString(9, configurazioneTariffe.getCodiceKeyPeriodoGiornal());
			callableStatement.setDouble(10, configurazioneTariffe.getTariffaTari());
			callableStatement.setDouble(11, configurazioneTariffe.getTariffaCosap());
			callableStatement.setTimestamp(12, new java.sql.Timestamp(configurazioneTariffe.getDataInizioValidita().getTimeInMillis()));
			callableStatement.setTimestamp(13, new java.sql.Timestamp(configurazioneTariffe.getDataFineValidita().getTimeInMillis()));
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

	public Integer updatePerKey(ConfigurazioneTariffe configurazioneTariffe)
	throws DaoException 
{
	CallableStatement callableStatement=null;
	Connection connection = null;
	int ret=0;
	try {
		connection = getConnection();
		//inizio LP PGNTCORE-24
		//callableStatement = Helper.prepareCall(connection, getSchema(), Routines.PYTAMSP_UPK.routine());
        callableStatement = prepareCall(Routines.PYTAMSP_UPK.routine());
		//fine LP PGNTCORE-24
//		IN I_TAM_KTAMKTAM VARCHAR(64), 
//		IN I_TAM_DTAMTRTA DECIMAL(9,5),
//		IN I_TAM_DTAMTRCO DECIMAL(9,5),
//		IN I_TAM_GPZLDTIN TIMESTAMP,
//		IN I_TAM_GPZLDTFN TIMESTAMP,
//		OUT O_TOTROWS INTEGER 

//		private String codiceKeyTariffa;        	//	"TAM_KTAMKTAM" VARCHAR(5)
//		private Double tariffaTari					//  "TAM_DTAMTRTA" DECIMAL(9,5)
//		private Double tariffaCosap					//  "TAM_DTAMTRCO" DECIMAL(9,5)			
//		private Calendar dataInizioValidita;		//  "PZL_GPZLDTIN" TIMESTAMP
//		private Calendar dataFineValdita;			//  "PZL_GPZLDTFN" TIMESTAMP
		
		callableStatement.setString(1, configurazioneTariffe.getCodiceKeyTariffa());
		if (configurazioneTariffe.getTariffaTari()!=null) {
			callableStatement.setDouble(2, configurazioneTariffe.getTariffaTari());
		} else {
			callableStatement.setDouble(2, 0.0);			
		}
		if (configurazioneTariffe.getTariffaCosap()!=null) { 
			callableStatement.setDouble(3, configurazioneTariffe.getTariffaCosap());
		} else {
			callableStatement.setDouble(3, 0.0);
		}
		if (configurazioneTariffe.getDataInizioValidita()!=null) {
			callableStatement.setTimestamp(4, new java.sql.Timestamp(configurazioneTariffe.getDataInizioValidita().getTimeInMillis()));
		} else {
			callableStatement.setTimestamp(4, null);
		}
		if (configurazioneTariffe.getDataFineValidita()!=null) {
			callableStatement.setTimestamp(5, new java.sql.Timestamp(configurazioneTariffe.getDataFineValidita().getTimeInMillis()));
		} else {
			callableStatement.setTimestamp(5, null);
		}
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
