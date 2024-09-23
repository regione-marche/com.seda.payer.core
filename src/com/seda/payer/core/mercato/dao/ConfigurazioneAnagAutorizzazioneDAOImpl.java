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
import com.seda.payer.core.mercato.bean.ConfigurazioneAnagAutorizzazione;
import com.seda.payer.core.mercato.bean.EsitoRisposte;
import com.seda.payer.core.mercato.bean.MercatoPageList;

public class ConfigurazioneAnagAutorizzazioneDAOImpl extends BaseDaoHandler  implements ConfigurazioneAnagAutorizzazioneDAO  {
	//private static final long serialVersionUID = 1L;
	//inizio LP PG21XX04 Leak
	@Deprecated
	//fine LP PG21XX04 Leak
	public ConfigurazioneAnagAutorizzazioneDAOImpl(DataSource dataSource, String schema) throws SQLException {
		super(dataSource.getConnection(), schema);
	}

	//inizio LP PG21XX04 Leak
	public ConfigurazioneAnagAutorizzazioneDAOImpl(Connection connection, String schema) throws SQLException {
		super(connection, schema);
	}
	//fine LP PG21XX04 Leak


	public MercatoPageList ListConfigurazioneAnagAutorizzazione(
			ConfigurazioneAnagAutorizzazione configurazioneAnagAutorizzazione,
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
//				IN I_AUT_CSOCCSOC VARCHAR(5),
//				IN I_AUT_CUTECUTE VARCHAR(5),
//				IN I_AUT_KANEKENT CHAR(10),
//				IN I_AUT_CAUTNMAU VARCHAR(30),
//				IN I_AUT_CAUTCFPI VARCHAR(20),
//				IN I_AUT_CAUTNOMN VARCHAR(70),
//				OUT O_ROWINI INTEGER,
//				OUT O_ROWEND INTEGER,
//				OUT O_TOTROWS INTEGER,
//				OUT O_TOTPAGES SMALLINT
				
			connection = getConnection();
			//inizio LP PGNTCORE-24 
			//callableStatement = Helper.prepareCall(connection, getSchema(), Routines.PYAUTSP_LST.routine());
            callableStatement = prepareCall(Routines.PYAUTSP_LST.routine());
			//fine LP PGNTCORE-24
			callableStatement.setInt(1, pageNumber);                          /* rows per page */
			callableStatement.setInt(2, rowsPerPage);                        /* page number*/
			callableStatement.setString(3,configurazioneAnagAutorizzazione.getCodiceSocieta());
			callableStatement.setString(4,configurazioneAnagAutorizzazione.getCuteCute());
			callableStatement.setString(5,configurazioneAnagAutorizzazione.getChiaveEnte());
			callableStatement.setString(6,configurazioneAnagAutorizzazione.getCodiceAnagAutorizzazione());
			callableStatement.setString(7,configurazioneAnagAutorizzazione.getCodiceFiscAnagAutorizzazione());
			callableStatement.setString(8,configurazioneAnagAutorizzazione.getNominativoAnagAutorizzazione());
			/* we register row start */
			callableStatement.registerOutParameter(9, Types.INTEGER);
			/* we register row end */
			callableStatement.registerOutParameter(10, Types.INTEGER);
			/* we register total rows */
			callableStatement.registerOutParameter(11, Types.INTEGER);
			/* we register total pages */
			callableStatement.registerOutParameter(12, Types.SMALLINT);
			 
			/* we execute procedure */
			if(callableStatement.execute())	{
				pageInfo = new PageInfo();
				pageInfo.setPageNumber(pageNumber);
				pageInfo.setRowsPerPage(rowsPerPage);
				pageInfo.setFirstRow(callableStatement.getInt(9));
				pageInfo.setLastRow(callableStatement.getInt(10));
				pageInfo.setNumRows(callableStatement.getInt(11));
				pageInfo.setNumPages(callableStatement.getInt(12));
				
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

	public EsitoRisposte delete(ConfigurazioneAnagAutorizzazione configurazioneAnagAutorizzazione)
			throws DaoException 
	{
		CallableStatement callableStatement=null;
		Connection connection = null;
		EsitoRisposte  esitoRisposte = new EsitoRisposte();
		try {
			connection = getConnection();
			//inizio LP PGNTCORE-24 
			//callableStatement = Helper.prepareCall(connection, getSchema(), Routines.PYAUTSP_DEL.routine());
            callableStatement = prepareCall(Routines.PYAUTSP_DEL.routine());
			//fine LP PGNTCORE-24
//			IN I_AUT_CSOCCSOC VARCHAR(5), 
//			IN I_AUT_CUTECUTE VARCHAR(5),
//			IN I_AUT_KANEKENT CHAR(10),
//			IN I_AUT_CAUTNMAU VARCHAR(30),
//			OUT O_PPS_CODESITO VARCHAR(2),
//			OUT O_PPS_MSGESITO VARCHAR(100)

			callableStatement.setString(1, configurazioneAnagAutorizzazione.getCodiceSocieta());
			callableStatement.setString(2, configurazioneAnagAutorizzazione.getCuteCute());
			callableStatement.setString(3, configurazioneAnagAutorizzazione.getChiaveEnte());
			callableStatement.setString(4, configurazioneAnagAutorizzazione.getCodiceAnagAutorizzazione());			
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

	public EsitoRisposte insert(ConfigurazioneAnagAutorizzazione configurazioneAnagAutorizzazione)
		throws DaoException 
	{
		CallableStatement callableStatement=null;
		Connection connection = null;
		EsitoRisposte esitoRisposte = new EsitoRisposte();
		try {
			connection = getConnection();
			//inizio LP PGNTCORE-24 
			//callableStatement = Helper.prepareCall(connection, getSchema(), Routines.PYAUTSP_INS.routine());
            callableStatement = prepareCall(Routines.PYAUTSP_INS.routine());
			//fine LP PGNTCORE-24
//					IN I_AUT_KAUTKAUT VARCHAR(64),
//					IN I_AUT_CSOCCSOC VARCHAR(5), 
//					IN I_AUT_CUTECUTE VARCHAR(5),
//					IN I_AUT_KANEKENT CHAR(10),
//					IN I_AUT_CAUTNMAU VARCHAR(30),
//					IN I_AUT_CAUTCFPI VARCHAR(20),
//					IN I_AUT_CAUTNOMN VARCHAR(90)			
//					IN_I_AUT_GMRCDTIN TIMESTAMP,
//					IN_I_AUT_GMRCDTFN TIMESTAMP,
//					OUT O_AUT_CODESITO VARCHAR(2),
//					OUT O_AUT_MSGESITO VARCHAR(100)
//			private String codiceKeyAnagAutor				//  "AUT_KAUTKAUT" VARCHAR(64)
//			private String codiceSocieta;        			//	"AUT_CSOCCSOC" VARCHAR(5)
//			private String cuteCute;						//	"AUT_CUTECUTE" VARCHAR(5)
//			private String chiaveEnte;						//	"AUT_KANEKENT" CHAR(10)
//			private String codiceAnagAutorizzazione;  		//	"AUT_CAUTNMAU" VARCHAR(30)
// 			private String codiceFiscAnagAutorizzazione;	//	"AUT_CAUTCFPI" VARCHAR(20)
//			private String nominativoAnagAutorizzazione;	//  "AUT_CAUTNOMN" VARCHAR(90)
//			private Calendar dataInizioValidita;			//  "AUT_GAUTDTIN" TIMESTAMP
//			private Calendar dataFineValidita;				//  "AUT_GAUTDTFN" TIMESTAMP
			
			callableStatement.setString(1, configurazioneAnagAutorizzazione.getCodiceKeyAnagAutor());
			callableStatement.setString(2, configurazioneAnagAutorizzazione.getCodiceSocieta());
			callableStatement.setString(3, configurazioneAnagAutorizzazione.getCuteCute());
			callableStatement.setString(4, configurazioneAnagAutorizzazione.getChiaveEnte());
			callableStatement.setString(5, configurazioneAnagAutorizzazione.getCodiceAnagAutorizzazione());
			callableStatement.setString(6, configurazioneAnagAutorizzazione.getCodiceFiscAnagAutorizzazione());
			callableStatement.setString(7, configurazioneAnagAutorizzazione.getNominativoAnagAutorizzazione());
			callableStatement.setTimestamp(8, new java.sql.Timestamp(configurazioneAnagAutorizzazione.getDataInizioValidita().getTimeInMillis()));
			if (configurazioneAnagAutorizzazione.getDataFineValidita()==null) {
				callableStatement.setTimestamp(9, null);
			} else {
			    callableStatement.setTimestamp(9, new java.sql.Timestamp(configurazioneAnagAutorizzazione.getDataFineValidita().getTimeInMillis()));
			}

			callableStatement.registerOutParameter(10, Types.VARCHAR);
			callableStatement.registerOutParameter(11, Types.VARCHAR);			
			callableStatement.execute();
			esitoRisposte.setCodiceMessaggio(callableStatement.getString(10));
			esitoRisposte.setDescrizioneMessaggio(callableStatement.getString(11));
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

	@SuppressWarnings("deprecation")
	public ConfigurazioneAnagAutorizzazione select(ConfigurazioneAnagAutorizzazione configurazioneAnagAutorizzazione)
			throws DaoException 
	{
		CallableStatement callableStatement=null;
		ResultSet resultSet=null;
		Connection connection = null;
		CachedRowSet rowSet = null;
		try {
			connection = getConnection();
			//inizio LP PGNTCORE-24 
			//callableStatement = Helper.prepareCall(connection, getSchema(), Routines.PYAUTSP_SEL.routine());
            callableStatement = prepareCall(Routines.PYAUTSP_SEL.routine());
			//fine LP PGNTCORE-24
			callableStatement.setString(1, configurazioneAnagAutorizzazione.getCodiceSocieta());
			callableStatement.setString(2, configurazioneAnagAutorizzazione.getCuteCute());
			callableStatement.setString(3, configurazioneAnagAutorizzazione.getChiaveEnte());
			callableStatement.setString(4, configurazioneAnagAutorizzazione.getCodiceAnagAutorizzazione());
			
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
				 String codiceKeyAnagAutor = rowSet.getString(1);
				 String codiceSocieta = rowSet.getString(2);       		
				 String cuteCute = rowSet.getString(3);					
				 String chiaveEnte = rowSet.getString(4);
				 String codiceAnagAutorizzazione = rowSet.getString(5);
				 String codiceFiscAnagAutorizzazione = rowSet.getString(6);
				 String nominativoAnagAutorizzazione = rowSet.getString(7);
				 GregorianCalendar dataInizioValidita = new GregorianCalendar();
				 dataInizioValidita.setTimeInMillis(rowSet.getTimestamp(8).getTime());
				 GregorianCalendar dataFineValidita = new GregorianCalendar();
				 dataFineValidita.setTimeInMillis(rowSet.getTimestamp(9).getTime());

				 configurazioneAnagAutorizzazione = new ConfigurazioneAnagAutorizzazione(
						 codiceKeyAnagAutor, codiceSocieta, cuteCute, chiaveEnte, 
						 codiceAnagAutorizzazione, codiceFiscAnagAutorizzazione,
						 nominativoAnagAutorizzazione, 
						 dataInizioValidita, dataFineValidita);
			
				 configurazioneAnagAutorizzazione.setAttribute(MercatoDAO.SELECT_XML, selectXml);
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
		return configurazioneAnagAutorizzazione;
	}


	public ArrayList<ConfigurazioneAnagAutorizzazione> listAnagAutorizzazione(ConfigurazioneAnagAutorizzazione configurazioneAnagAutorizzazione) throws DaoException {
//		IN I_AUT_CSOCCSOC VARCHAR(5), 
//		IN I_AUT_CUTECUTE VARCHAR(5),
//		IN I_AUT_KANEKENT CHAR(10),
//		IN I_AUT_CAUTCFPI VARCHAR(20),
//		IN I_AUT_CAUTNOMN VARCHAR(70)
		CallableStatement callableStatement=null;
		ArrayList<ConfigurazioneAnagAutorizzazione> configurazioneAnagAutorizzazioneList = null;
		Connection connection = null;
		ResultSet resultSet=null;
		try {
			connection = getConnection();
			//inizio LP PGNTCORE-24 
			//callableStatement = Helper.prepareCall(connection, getSchema(), Routines.PYAUTSP_TOT.routine());
            callableStatement = prepareCall(Routines.PYAUTSP_TOT.routine());
			//fine LP PGNTCORE-24
			callableStatement.setString(1, configurazioneAnagAutorizzazione.getCodiceSocieta());
			callableStatement.setString(2, configurazioneAnagAutorizzazione.getCuteCute());
			callableStatement.setString(3, configurazioneAnagAutorizzazione.getChiaveEnte());
			callableStatement.setString(4, configurazioneAnagAutorizzazione.getCodiceFiscAnagAutorizzazione());
			callableStatement.setString(5, configurazioneAnagAutorizzazione.getCodiceAnagAutorizzazione());
			callableStatement.setString(6, configurazioneAnagAutorizzazione.getNominativoAnagAutorizzazione());			
			callableStatement.execute();
			resultSet=callableStatement.getResultSet();
			
			if ( !resultSet.next() ) {
				configurazioneAnagAutorizzazioneList = new ArrayList<ConfigurazioneAnagAutorizzazione>();
			} else {
				configurazioneAnagAutorizzazioneList = new ArrayList<ConfigurazioneAnagAutorizzazione>();
				do {
					ConfigurazioneAnagAutorizzazione item = new ConfigurazioneAnagAutorizzazione();
					item.setCodiceAnagAutorizzazione(resultSet.getString("AUT_CAUTNMAU"));
					item.setNominativoAnagAutorizzazione(resultSet.getString("AUT_CAUTNOMN"));
					item.setCodiceFiscAnagAutorizzazione(resultSet.getString("AUT_CAUTCFPI"));
					GregorianCalendar DataIniVal = new GregorianCalendar();
					DataIniVal.setTimeInMillis(resultSet.getTimestamp("AUT_GAUTDTIN").getTime());
					item.setDataInizioValidita(DataIniVal);
					GregorianCalendar DataFinVal = new GregorianCalendar();
					DataFinVal.setTimeInMillis(resultSet.getTimestamp("AUT_GAUTDTFN").getTime());
					item.setDataFineValidita(DataFinVal);
					configurazioneAnagAutorizzazioneList.add(item);
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
		
		return configurazioneAnagAutorizzazioneList;

	}	


	public ArrayList<ConfigurazioneAnagAutorizzazione> listPerCF(ConfigurazioneAnagAutorizzazione configurazioneAnagAutorizzazione) throws DaoException {
//		IN I_AUT_CSOCCSOC VARCHAR(5),
//		IN I_AUT_CUTECUTE VARCHAR(5),
//		IN I_AUT_KANEKENT CHAR(10),
//		IN I_AUT_CAUTCFPI VARCHAR(20),
//		IN I_AUT_CAUTNMAU VARCHAR(30),
//		IN I_AUT_CAUTNOMN VARCHAR(70)	
		CallableStatement callableStatement=null;
		ArrayList<ConfigurazioneAnagAutorizzazione> configurazioneAnagAutorizzazioneList = null;
		Connection connection = null;
		ResultSet resultSet=null;
		try {
			connection = getConnection();
			//inizio LP PGNTCORE-24 
			//callableStatement = Helper.prepareCall(connection, getSchema(), Routines.PYAUTSP_TOT.routine());
            callableStatement = prepareCall(Routines.PYAUTSP_TOT.routine());
			//fine LP PGNTCORE-24
			callableStatement.setString(1, configurazioneAnagAutorizzazione.getCodiceSocieta());
			callableStatement.setString(2, configurazioneAnagAutorizzazione.getCuteCute());
			callableStatement.setString(3, configurazioneAnagAutorizzazione.getChiaveEnte());
			callableStatement.setString(4, configurazioneAnagAutorizzazione.getCodiceFiscAnagAutorizzazione());
			callableStatement.setString(5, configurazioneAnagAutorizzazione.getCodiceAnagAutorizzazione());
			callableStatement.setString(6, configurazioneAnagAutorizzazione.getNominativoAnagAutorizzazione());
			callableStatement.execute();
			resultSet=callableStatement.getResultSet();
			
			if ( !resultSet.next() ) {
				configurazioneAnagAutorizzazioneList = new ArrayList<ConfigurazioneAnagAutorizzazione>();
			} else {
				configurazioneAnagAutorizzazioneList = new ArrayList<ConfigurazioneAnagAutorizzazione>();
				do {
					ConfigurazioneAnagAutorizzazione item = new ConfigurazioneAnagAutorizzazione();
					item.setCodiceAnagAutorizzazione(resultSet.getString("AUT_CAUTNMAU"));
					item.setNominativoAnagAutorizzazione(resultSet.getString("AUT_CAUTNOMN"));
					item.setCodiceFiscAnagAutorizzazione(resultSet.getString("AUT_CAUTCFPI"));
					GregorianCalendar DataIniVal = new GregorianCalendar();
					DataIniVal.setTimeInMillis(resultSet.getTimestamp("AUT_GAUTDTIN").getTime());
					item.setDataInizioValidita(DataIniVal);
					GregorianCalendar DataFinVal = new GregorianCalendar();
					DataFinVal.setTimeInMillis(resultSet.getTimestamp("AUT_GAUTDTFN").getTime());
					item.setDataFineValidita(DataFinVal);
					configurazioneAnagAutorizzazioneList.add(item);
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
		
		return configurazioneAnagAutorizzazioneList;

	}	


	public ArrayList<ConfigurazioneAnagAutorizzazione> listPerNome(ConfigurazioneAnagAutorizzazione configurazioneAnagAutorizzazione) throws DaoException {
//		IN I_AUT_CSOCCSOC VARCHAR(5), 
//		IN I_AUT_CUTECUTE VARCHAR(5),
//		IN I_AUT_KANEKENT CHAR(10),
//		IN I_AUT_CAUTNOM VARCHAR(70)		
		CallableStatement callableStatement=null;
		ArrayList<ConfigurazioneAnagAutorizzazione> configurazioneAnagAutorizzazioneList = null;
		Connection connection = null;
		ResultSet resultSet=null;
		try {
			connection = getConnection();
			//inizio LP PGNTCORE-24 
			//callableStatement = Helper.prepareCall(connection, getSchema(), Routines.PYAUTSP_NOM.routine());
            callableStatement = prepareCall(Routines.PYAUTSP_NOM.routine());
			//fine LP PGNTCORE-24
			callableStatement.setString(1, configurazioneAnagAutorizzazione.getCodiceSocieta());
			callableStatement.setString(2, configurazioneAnagAutorizzazione.getCuteCute());
			callableStatement.setString(3, configurazioneAnagAutorizzazione.getChiaveEnte());
			callableStatement.setString(4, configurazioneAnagAutorizzazione.getNominativoAnagAutorizzazione());
			callableStatement.execute();
			resultSet=callableStatement.getResultSet();
			
			if ( !resultSet.next() ) {
				configurazioneAnagAutorizzazioneList = new ArrayList<ConfigurazioneAnagAutorizzazione>();
			} else {
				configurazioneAnagAutorizzazioneList = new ArrayList<ConfigurazioneAnagAutorizzazione>();
				do {
					ConfigurazioneAnagAutorizzazione item = new ConfigurazioneAnagAutorizzazione();
					item.setCodiceAnagAutorizzazione(resultSet.getString("AUT_CAUTNMAU"));
					item.setNominativoAnagAutorizzazione(resultSet.getString("AUT_CAUTNOMN"));
					item.setCodiceFiscAnagAutorizzazione(resultSet.getString("AUT_CAUTCFPI"));
					GregorianCalendar DataIniVal = new GregorianCalendar();
					DataIniVal.setTimeInMillis(resultSet.getTimestamp("AUT_GAUTDTIN").getTime());
					item.setDataInizioValidita(DataIniVal);
					GregorianCalendar DataFinVal = new GregorianCalendar();
					DataFinVal.setTimeInMillis(resultSet.getTimestamp("AUT_GAUTDTFN").getTime());
					item.setDataFineValidita(DataFinVal);
					configurazioneAnagAutorizzazioneList.add(item);
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
		
		return configurazioneAnagAutorizzazioneList;

	}	
	

	
	public Integer update(ConfigurazioneAnagAutorizzazione configurazioneAnagAutorizzazione)
		throws DaoException 
	{
		CallableStatement callableStatement=null;
		Connection connection = null;
		int ret=0;
		try {
			connection = getConnection();
			//inizio LP PGNTCORE-24 
			//callableStatement = Helper.prepareCall(connection, getSchema(), Routines.PYAUTSP_UPD.routine());
            callableStatement = prepareCall(Routines.PYAUTSP_UPD.routine());
			//fine LP PGNTCORE-24

//			IN I_AUT_CSOCCSOC VARCHAR(5), 
//			IN I_AUT_CUTECUTE VARCHAR(5),
//			IN I_AUT_KANEKENT CHAR(10),
//			IN I_AUT_CAUTNMAU VARCHAR(30),
//			IN I_AUT_CAUTCFPI VARCHAR(20),
//			IN I_AUT_CAUTNOMN VARCHAR(90),
//			IN I_AUT_GAUTDTIN TIMESTAMP,
//			IN I_AUT_GAUTDTFN TIMESTAMP,
//			OUT O_TOTROWS INTEGER )

//			private String codiceSocieta;        			//	"AUT_CSOCCSOC" VARCHAR(5)
//			private String cuteCute;						//	"AUT_CUTECUTE" VARCHAR(5)
//			private String chiaveEnte;						//	"AUT_KANEKENT" CHAR(10)
//			private String codiceAnagAutorizzazione;		//	"AUT_CAUTNMAU" VARCHAR(30)
//			private String codiceFiscAnagAutorizzazione;	//	"AUT_CAUTCFPI" VARCHAR(20)
//			private String nominativoAnagAutorizzazione;	//  "AUT_CAUTNOMN" VARCHAR(90)			
//			private Calendar dataInizioValidita;			//  "AUT_GAUTDTIN" TIMESTAMP
//			private Calendar dataFineValdita;				//  "AUT_GAUTDTFN" TIMESTAMP
			
			callableStatement.setString(1, configurazioneAnagAutorizzazione.getCodiceSocieta());
			callableStatement.setString(2, configurazioneAnagAutorizzazione.getCuteCute());
			callableStatement.setString(3, configurazioneAnagAutorizzazione.getChiaveEnte());
			callableStatement.setString(4, configurazioneAnagAutorizzazione.getCodiceAnagAutorizzazione());
			callableStatement.setString(5, configurazioneAnagAutorizzazione.getCodiceFiscAnagAutorizzazione());
			callableStatement.setString(6, configurazioneAnagAutorizzazione.getNominativoAnagAutorizzazione());
			callableStatement.setTimestamp(7, new java.sql.Timestamp(configurazioneAnagAutorizzazione.getDataInizioValidita().getTimeInMillis()));
			if (configurazioneAnagAutorizzazione.getDataFineValidita()==null) {
				callableStatement.setTimestamp(8, null);
			} else {
				callableStatement.setTimestamp(8, new java.sql.Timestamp(configurazioneAnagAutorizzazione.getDataFineValidita().getTimeInMillis()));
			}
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
