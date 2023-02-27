package com.seda.payer.core.mercato.dao;

import java.io.IOException;
import java.math.BigDecimal;
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
import com.seda.payer.core.mercato.bean.ConfigurazioneCompenso;
import com.seda.payer.core.mercato.bean.EsitoRisposte;
import com.seda.payer.core.mercato.bean.MercatoPageList;
import com.seda.payer.core.mercato.dao.MercatoDAO;

public class ConfigurazioneCompensoDAOImpl extends BaseDaoHandler  implements ConfigurazioneCompensoDAO  {
	//private static final long serialVersionUID = 1L;
	//inizio LP PG21XX04 Leak
	@Deprecated
	//fine LP PG21XX04 Leak
	public ConfigurazioneCompensoDAOImpl(DataSource dataSource, String schema) throws SQLException {
		super(dataSource.getConnection(), schema);
	}

	//inizio LP PG21XX04 Leak
	public ConfigurazioneCompensoDAOImpl(Connection connection, String schema) throws SQLException {
		super(connection, schema);
	}
	//fine LP PG21XX04 Leak

	public MercatoPageList ListConfigurazioneCompenso(
			ConfigurazioneCompenso configurazioneCompenso,
			int rowsPerPage, int pageNumber, String OrderBy)
			throws DaoException {
		CallableStatement callableStatement=null;
		Connection connection = null;
		ResultSet data = null;
		PageInfo pageInfo = null;
		MercatoPageList mercatoPageList = null;
		try {
				
			connection = getConnection();
			callableStatement = Helper.prepareCall(connection, getSchema(), Routines.PYCOMSP_LST.routine());
			callableStatement.setInt(1, pageNumber);                          /* rows per page */
			callableStatement.setInt(2, rowsPerPage);                        /* page number*/
			callableStatement.setString(3,configurazioneCompenso.getCodiceSocieta());
			callableStatement.setString(4,configurazioneCompenso.getCuteCute());
			callableStatement.setString(5,configurazioneCompenso.getChiaveEnte());
			callableStatement.setString(6,(String)configurazioneCompenso.getAttribute("tx_dval_ini"));
			callableStatement.setString(7,(String)configurazioneCompenso.getAttribute("tx_dval_a_ini"));
			callableStatement.setString(8,(String)configurazioneCompenso.getAttribute("tx_dval_fin"));
			callableStatement.setString(9,(String)configurazioneCompenso.getAttribute("tx_dval_a_fin"));
			
			
//			if (configurazioneCompenso.getDataInizioValidita()==null) {
//				callableStatement.setTimestamp(6, null);
//			} else {
//			    callableStatement.setTimestamp(6, new java.sql.Timestamp(configurazioneCompenso.getDataInizioValidita().getTimeInMillis()));
//			}			
//			if (configurazioneCompenso.getDataAInizioValidita()==null) {
//				callableStatement.setTimestamp(7, null);
//			} else {
//			    callableStatement.setTimestamp(7, new java.sql.Timestamp(configurazioneCompenso.getDataInizioValidita().getTimeInMillis()));
//			}			
//
//			if (configurazioneCompenso.getDataFineValidita()==null) {
//				callableStatement.setTimestamp(8, null);
//			} else {
//			    callableStatement.setTimestamp(8, new java.sql.Timestamp(configurazioneCompenso.getDataFineValidita().getTimeInMillis()));			
//			}
//			if (configurazioneCompenso.getDataAFineValidita()==null) {
//				callableStatement.setTimestamp(9, null);
//			} else {
//			    callableStatement.setTimestamp(9, new java.sql.Timestamp(configurazioneCompenso.getDataAFineValidita().getTimeInMillis()));		
//			}	

			/* we register row start */
			callableStatement.registerOutParameter(10, Types.INTEGER);
			/* we register row end */
			callableStatement.registerOutParameter(11, Types.INTEGER);
			/* we register total rows */
			callableStatement.registerOutParameter(12, Types.INTEGER);
			/* we register total pages */
			callableStatement.registerOutParameter(13, Types.SMALLINT);
			 
			/* we execute procedure */
			if(callableStatement.execute())	{
				pageInfo = new PageInfo();
				pageInfo.setPageNumber(pageNumber);
				pageInfo.setRowsPerPage(rowsPerPage);
				pageInfo.setFirstRow(callableStatement.getInt(10));
				pageInfo.setLastRow(callableStatement.getInt(11));
				pageInfo.setNumRows(callableStatement.getInt(12));
				pageInfo.setNumPages(callableStatement.getInt(13));
				
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

	public EsitoRisposte delete(ConfigurazioneCompenso configurazioneCompenso)
			throws DaoException 
	{
		CallableStatement callableStatement=null;
		Connection connection = null;
		EsitoRisposte  esitoRisposte = new EsitoRisposte();
		try {
			connection = getConnection();
			callableStatement = Helper.prepareCall(connection, getSchema(), Routines.PYCOMSP_DEL.routine());

			callableStatement.setString(1, configurazioneCompenso.getCodiceKeyCompenso());
			callableStatement.registerOutParameter(2, Types.VARCHAR);
			callableStatement.registerOutParameter(3, Types.VARCHAR);			
			callableStatement.execute();
			esitoRisposte.setCodiceMessaggio(callableStatement.getString(2));
			esitoRisposte.setDescrizioneMessaggio(callableStatement.getString(3));
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

	public EsitoRisposte insert(ConfigurazioneCompenso configurazioneCompenso)
		throws DaoException 
	{
		CallableStatement callableStatement=null;
		Connection connection = null;
		EsitoRisposte esitoRisposte = new EsitoRisposte();
		try {
			connection = getConnection();
			callableStatement = Helper.prepareCall(connection, getSchema(), Routines.PYCOMSP_INS.routine());
			
			callableStatement.setString(1, configurazioneCompenso.getCodiceSocieta());
			callableStatement.setString(2, configurazioneCompenso.getCuteCute());
			callableStatement.setString(3, configurazioneCompenso.getChiaveEnte());
			callableStatement.setBigDecimal(4, configurazioneCompenso.getImportoFisso());
			callableStatement.setDouble(5, configurazioneCompenso.getPercentualeCompenso());
			if (configurazioneCompenso.getPercentualeIva()==null){
				callableStatement.setDouble(6, 0);
			} else {
				callableStatement.setDouble(6, configurazioneCompenso.getPercentualeIva());
			}
			callableStatement.setTimestamp(7, new java.sql.Timestamp(configurazioneCompenso.getDataInizioValidita().getTimeInMillis()));
			if (configurazioneCompenso.getDataFineValidita()==null) {
				callableStatement.setTimestamp(8, null);
			} else {
			    callableStatement.setTimestamp(8, new java.sql.Timestamp(configurazioneCompenso.getDataFineValidita().getTimeInMillis()));
			}

			callableStatement.registerOutParameter(9, Types.VARCHAR);
			callableStatement.registerOutParameter(10, Types.VARCHAR);			
			callableStatement.execute();
			esitoRisposte.setCodiceMessaggio(callableStatement.getString(9));
			esitoRisposte.setDescrizioneMessaggio(callableStatement.getString(10));
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

	public ConfigurazioneCompenso select(ConfigurazioneCompenso configurazioneCompenso)
			throws DaoException 
	{
		CallableStatement callableStatement=null;
		ResultSet resultSet=null;
		Connection connection = null;
		CachedRowSet rowSet = null;
		try {
			connection = getConnection();
		
			callableStatement = Helper.prepareCall(connection, getSchema(), Routines.PYCOMSP_SEL.routine());
			callableStatement.setString(1, configurazioneCompenso.getCodiceKeyCompenso());
			
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
				 String codiceKeyAnagAutor = rowSet.getString(1);
				 String codiceSocieta = rowSet.getString(2);       		
				 String cuteCute = rowSet.getString(3);					
				 String chiaveEnte = rowSet.getString(4);
				 BigDecimal importoFisso = rowSet.getBigDecimal(5);
				 Double percentualeCompenso = rowSet.getDouble(6);
				 Double percentualeIva = rowSet.getDouble(7);
				 GregorianCalendar dataInizioValidita = new GregorianCalendar();
				 dataInizioValidita.setTimeInMillis(rowSet.getTimestamp(8).getTime());
				 GregorianCalendar dataFineValidita = new GregorianCalendar();
				 dataFineValidita.setTimeInMillis(rowSet.getTimestamp(9).getTime());
				 
				 configurazioneCompenso = new ConfigurazioneCompenso(
						 codiceKeyAnagAutor, codiceSocieta, cuteCute, chiaveEnte, 
						 importoFisso, percentualeCompenso, percentualeIva, 
						 dataInizioValidita, dataFineValidita, null, null);
			
				 configurazioneCompenso.setAttribute(MercatoDAO.SELECT_XML, selectXml);
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
		return configurazioneCompenso;
	}

	public ArrayList<ConfigurazioneCompenso> listCompenso(ConfigurazioneCompenso configurazioneCompenso) throws DaoException {
		CallableStatement callableStatement=null;
		ArrayList<ConfigurazioneCompenso> configurazioneCompensoList = null;
		Connection connection = null;
		ResultSet resultSet=null;
		try {
			connection = getConnection();
			callableStatement = Helper.prepareCall(connection, getSchema(), Routines.PYCOMSP_TOT.routine());
			callableStatement.setString(1, configurazioneCompenso.getCodiceSocieta());
			callableStatement.setString(2, configurazioneCompenso.getCuteCute());
			callableStatement.setString(3, configurazioneCompenso.getChiaveEnte());
			callableStatement.setString(4, (String)configurazioneCompenso.getAttribute("tx_dval_ini"));
			callableStatement.setString(5, (String)configurazioneCompenso.getAttribute("tx_dval_a_ini"));
			callableStatement.setString(6, (String)configurazioneCompenso.getAttribute("tx_dval_fin"));
			callableStatement.setString(7, (String)configurazioneCompenso.getAttribute("tx_dval_a_fin"));
//			if (configurazioneCompenso.getDataInizioValidita()==null) {
//				callableStatement.setTimestamp(4, null);
//			} else {
//			    callableStatement.setTimestamp(4, new java.sql.Timestamp(configurazioneCompenso.getDataInizioValidita().getTimeInMillis()));
//			}			
//			if (configurazioneCompenso.getDataAInizioValidita()==null) {
//				callableStatement.setTimestamp(5, null);
//			} else {
//			    callableStatement.setTimestamp(5, new java.sql.Timestamp(configurazioneCompenso.getDataAInizioValidita().getTimeInMillis()));
//			}			
//
//			if (configurazioneCompenso.getDataFineValidita()==null) {
//				callableStatement.setTimestamp(6, null);
//			} else {
//			    callableStatement.setTimestamp(6, new java.sql.Timestamp(configurazioneCompenso.getDataFineValidita().getTimeInMillis()));			
//			}
//			if (configurazioneCompenso.getDataAFineValidita()==null) {
//				callableStatement.setTimestamp(7, null);
//			} else {
//			    callableStatement.setTimestamp(7, new java.sql.Timestamp(configurazioneCompenso.getDataAFineValidita().getTimeInMillis()));		
//			}			

			callableStatement.execute();
			resultSet=callableStatement.getResultSet();
			
			if ( !resultSet.next() ) {
				configurazioneCompensoList = new ArrayList<ConfigurazioneCompenso>();
			} else {
				configurazioneCompensoList = new ArrayList<ConfigurazioneCompenso>();
				do {
					ConfigurazioneCompenso item = new ConfigurazioneCompenso();
					item.setCodiceKeyCompenso(resultSet.getString("COM_KCOMKCOM"));
					item.setImportoFisso(resultSet.getBigDecimal("COM_ICOMFISS"));
					item.setPercentualeCompenso(resultSet.getDouble("COM_DCOMPRCO"));
					item.setPercentualeIva(resultSet.getDouble("COM_DCOMPRCI"));
					GregorianCalendar DataIniVal = new GregorianCalendar();
					DataIniVal.setTimeInMillis(resultSet.getTimestamp("COM_GCOMDTIN").getTime());
					item.setDataInizioValidita(DataIniVal);
					GregorianCalendar DataFinVal = new GregorianCalendar();
					DataFinVal.setTimeInMillis(resultSet.getTimestamp("COM_GCOMDTFN").getTime());
					item.setDataFineValidita(DataFinVal);
					configurazioneCompensoList.add(item);
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
		
		return configurazioneCompensoList;

	}	
	
	public Integer update(ConfigurazioneCompenso configurazioneCompenso)
		throws DaoException 
	{
		CallableStatement callableStatement=null;
		Connection connection = null;
		int ret=0;
		try {
			connection = getConnection();
			callableStatement = Helper.prepareCall(connection, getSchema(), Routines.PYCOMSP_UPD.routine());
			
			callableStatement.setString(1, configurazioneCompenso.getCodiceKeyCompenso());
			callableStatement.setString(2, configurazioneCompenso.getCodiceSocieta());
			callableStatement.setString(3, configurazioneCompenso.getCuteCute());
			callableStatement.setString(4, configurazioneCompenso.getChiaveEnte());
			callableStatement.setBigDecimal(5, configurazioneCompenso.getImportoFisso());
			callableStatement.setDouble(6, configurazioneCompenso.getPercentualeCompenso());
			if (configurazioneCompenso.getPercentualeIva()==null) {
				callableStatement.setDouble(7, 0);
			} else {
				callableStatement.setDouble(7, configurazioneCompenso.getPercentualeIva());
			}
			callableStatement.setTimestamp(8, new java.sql.Timestamp(configurazioneCompenso.getDataInizioValidita().getTimeInMillis()));
			if (configurazioneCompenso.getDataFineValidita()==null) {
				callableStatement.setTimestamp(9, null);
			} else {
				callableStatement.setTimestamp(9, new java.sql.Timestamp(configurazioneCompenso.getDataFineValidita().getTimeInMillis()));
			}
			callableStatement.registerOutParameter(10, Types.INTEGER);						
			callableStatement.execute();
			ret = callableStatement.getInt(10);
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
// Termine della Classe
}
