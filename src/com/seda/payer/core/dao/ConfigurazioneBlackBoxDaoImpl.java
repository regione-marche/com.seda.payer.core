package com.seda.payer.core.dao;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Properties;

import javax.sql.DataSource;
import javax.sql.rowset.CachedRowSet;

import com.seda.commons.string.Convert;
import com.seda.data.helper.HelperException;
import com.seda.data.spi.PageInfo;
import com.seda.payer.core.bean.BlackBoxPagelist;
import com.seda.payer.core.bean.BlackBoxPosLog;
import com.seda.payer.core.bean.BlackBoxPosLogPagelist;
import com.seda.payer.core.bean.BlackBoxPosPagelist;
import com.seda.payer.core.bean.ConfigurazioneBlackBox;
import com.seda.payer.core.bean.ConfigurazioneBlackBoxPos;
import com.seda.payer.core.exception.DaoException;
import com.seda.payer.core.handler.BaseDaoHandler;
import com.seda.payer.core.wallet.bean.EsitoRisposte;

public class ConfigurazioneBlackBoxDaoImpl extends BaseDaoHandler implements ConfigurazioneBlackBoxDao {
	private static final long serialVersionUID = 1L;

	Properties attributes = new Properties();

	//inizio LP PG21XX04 Leak
	@Deprecated
	//fine LP PG21XX04 Leak
	public ConfigurazioneBlackBoxDaoImpl(DataSource dataSource, String schema) throws SQLException {
		super(dataSource.getConnection(), schema);
	}

	public ConfigurazioneBlackBoxDaoImpl(Connection connection, String schema) throws SQLException {
		super(connection, schema);
	}

	@Override
	public ConfigurazioneBlackBox select(ConfigurazioneBlackBox blackbox) throws DaoException {
		CallableStatement callableStatement = null;
		ResultSet resultSet = null;
		Connection connection = null;
		CachedRowSet rowSet = null;
		try {
			connection = getConnection();
			//inizio LP 20240919 PGNTCORE-24
			callableStatement = prepareCall(Routines.CNCNFSP_SEL.routine());
			//fine LP 20240919 PGNTCORE-24
			callableStatement.setString(1, blackbox.getCodiceEnte());
			callableStatement.setString(2, blackbox.getCodiceIdentificativoDominio());
			callableStatement.setString(3, blackbox.getCodiceApplicationCode());
			callableStatement.setString(4, blackbox.getCodiceSegregazione());
			callableStatement.setString(5, blackbox.getAuxDigit());
			callableStatement.setString(6, blackbox.getCodiceServizio());
			callableStatement.execute();

			resultSet = callableStatement.getResultSet();
			loadWebRowSet(resultSet);
			String selectXml = getWebRowSetXml();
			try {
				rowSet = Convert.stringToWebRowSet(selectXml);
			} catch (IOException e) {
				e.printStackTrace();
			}
			if (rowSet.next()) {
				String codicEnte = rowSet.getString(1);
				String codiceIdentificativoDominio = rowSet.getString(2);
				String codiceApplicationCode = rowSet.getString(3);
				String codiceSegregazione = rowSet.getString(4);
				String auxDigit = rowSet.getString(5);
				String flagIuv = rowSet.getString(6);
				String urlFtp = rowSet.getString(7);
				String usrFtp = rowSet.getString(8);
				String passwordFtp = rowSet.getString(9);
				String directoryFtpDownload = rowSet.getString(10);
				String directoryFtpUpload = rowSet.getString(11);
				String pathLocaleInput = rowSet.getString(12);
				String pathLocaleScarti = rowSet.getString(13);
				String pathLocaleOutput = rowSet.getString(14);
				String codiceServizio = rowSet.getString(15);
				String flagPoste = rowSet.getString(16);
				String emailPoste = rowSet.getString(17);
				blackbox = new ConfigurazioneBlackBox(codicEnte, codiceIdentificativoDominio, codiceApplicationCode,
						codiceSegregazione, auxDigit, flagIuv, urlFtp, usrFtp, passwordFtp, directoryFtpDownload,
						directoryFtpUpload, pathLocaleInput, pathLocaleScarti, pathLocaleOutput, codiceServizio,
						flagPoste, emailPoste);
			}
		} catch (SQLException e) {
			throw new DaoException(e);
		} catch (IllegalArgumentException e) {
			throw new DaoException(e);
		} catch (HelperException e) {
			throw new DaoException(e);
		} finally {
			// inizio LP PG21XX04 Leak
			// DAOHelper.closeIgnoringException(callableStatement);
			// DAOHelper.closeIgnoringException(connection);
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
				connection = null; //LP 20240919 PGNTCORE-24
			}
			// fine LP PG21XX04 Leak
		}
		return blackbox;
	}

	@Override
	public Integer update(ConfigurazioneBlackBox configurazioneBlackBox) throws DaoException {
		CallableStatement callableStatement = null;
		Connection connection = null;
		int ret = 0;
		try {
			connection = getConnection();
			//inizio LP 20240919 PGNTCORE-24
			//callableStatement = Helper.prepareCall(connection, getSchema(), Routines.CNCNFSP_UPD.routine());
			callableStatement = prepareCall(Routines.CNCNFSP_UPD.routine());
			//fine LP 20240919 PGNTCORE-24
//			     IN I_CNF_CCNFCENT VARCHAR(5), 	
//				 IN I_CNF_CCNFCIDD VARCHAR(16),		
//				 IN I_CNF_CCNFAPPC VARCHAR(34),
//				 IN I_CNF_CCNFSEGC CHAR(2),			
//				 IN I_CNF_CCNFAXDC CHAR(1),			
//				 IN I_CNF_CCNFFIUV VARCHAR(1),	
//				 IN I_CNF_CCNFURLF VARCHAR(256),		
//				 IN I_CNF_CCNFUSRF VARCHAR(50),		
//				 IN I_CNF_CCNFPWDF VARCHAR(50),		
//				 IN I_CNF_CCNFDIRD VARCHAR(50),	
//				 IN I_CNF_CCNFDIRU VARCHAR(50),			
//				 IN I_CNF_CCNFINPT VARCHAR(255),			
//				 IN I_CNF_CCNFSCRT VARCHAR(255),			
//				 IN I_CNF_CCNFOUTP VARCHAR(255),
//			     IN I_CNF_CCNFFPST CHAR(1),
//		         IN I_CNF_CCNFEPST VARCHAR(256),
//               OUT O_CODE INTEGER

			callableStatement.setString(1, configurazioneBlackBox.getCodiceEnte());
			callableStatement.setString(2, configurazioneBlackBox.getCodiceIdentificativoDominio());
			callableStatement.setString(3, configurazioneBlackBox.getCodiceApplicationCode());
			callableStatement.setString(4, configurazioneBlackBox.getCodiceSegregazione());
			callableStatement.setString(5, configurazioneBlackBox.getAuxDigit());
			callableStatement.setString(6, configurazioneBlackBox.getFlagIuv());
			callableStatement.setString(7, configurazioneBlackBox.getUrlFtp());
			callableStatement.setString(8, configurazioneBlackBox.getUsrFtp());
			callableStatement.setString(9, configurazioneBlackBox.getPasswordFtp());
			callableStatement.setString(10, configurazioneBlackBox.getDirectoryFtpDownload());
			callableStatement.setString(11, configurazioneBlackBox.getDirectoryFtpUpload());
			callableStatement.setString(12, configurazioneBlackBox.getPathLocaleInput());
			callableStatement.setString(13, configurazioneBlackBox.getPathLocaleScarti());
			callableStatement.setString(14, configurazioneBlackBox.getPathLocaleOutput());
			callableStatement.setString(15, configurazioneBlackBox.getCodiceServizio());
			callableStatement.setString(16, configurazioneBlackBox.getFlagPoste());
			callableStatement.setString(17, configurazioneBlackBox.getEmailPoste());
			callableStatement.registerOutParameter(18, Types.INTEGER);
			callableStatement.execute();
			ret = callableStatement.getInt(18);
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
			// inizio LP PG21XX04 Leak
			// DAOHelper.closeIgnoringException(connection);
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
				connection = null; //LP 20240919 PGNTCORE-24
			}
			// fine LP PG21XX04 Leak
		}
		return ret;
	}

	@Override
	public EsitoRisposte delete(ConfigurazioneBlackBox configurazioneBlackBox) throws DaoException {
		CallableStatement callableStatement = null;
		Connection connection = null;
		EsitoRisposte esitoRisposte = new EsitoRisposte();
		try {
			connection = getConnection();
			//inizio LP 20240919 PGNTCORE-24
			//callableStatement = Helper.prepareCall(connection, getSchema(), Routines.CNCNFSP_DEL.routine());
			callableStatement = prepareCall(Routines.CNCNFSP_DEL.routine());
			//fine LP 20240919 PGNTCORE-24 
			callableStatement.setString(1, configurazioneBlackBox.getCodiceEnte());
			callableStatement.setString(2, configurazioneBlackBox.getCodiceApplicationCode());
			callableStatement.setString(3, configurazioneBlackBox.getCodiceSegregazione());
			callableStatement.setString(4, configurazioneBlackBox.getAuxDigit());
			callableStatement.setString(5, configurazioneBlackBox.getCodiceServizio());
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
			// inizio LP PG21XX04 Leak
			// DAOHelper.closeIgnoringException(connection);
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
			// fine LP PG21XX04 Leak
		}
		return esitoRisposte;
	}

	@Override
	public EsitoRisposte insert(ConfigurazioneBlackBox configurazioneBlackBox) throws DaoException {
		CallableStatement callableStatement = null;
		Connection connection = null;
		EsitoRisposte esitoRisposte = new EsitoRisposte();
		try {
			connection = getConnection();
			//inizio LP 20240919 PGNTCORE-24			
			//callableStatement = Helper.prepareCall(connection, getSchema(), Routines.CNCNFSP_INS.routine());
			callableStatement = prepareCall(Routines.CNCNFSP_INS.routine());
			//fine LP 20240919 PGNTCORE-24 
			callableStatement.setString(1, configurazioneBlackBox.getCodiceEnte());
			callableStatement.setString(2, configurazioneBlackBox.getCodiceIdentificativoDominio());
			callableStatement.setString(3, configurazioneBlackBox.getCodiceApplicationCode());
			callableStatement.setString(4, configurazioneBlackBox.getCodiceSegregazione());
			callableStatement.setString(5, configurazioneBlackBox.getAuxDigit());
			callableStatement.setString(6, configurazioneBlackBox.getFlagIuv());
			callableStatement.setString(7, configurazioneBlackBox.getUrlFtp());
			callableStatement.setString(8, configurazioneBlackBox.getUsrFtp());
			callableStatement.setString(9, configurazioneBlackBox.getPasswordFtp());
			callableStatement.setString(10, configurazioneBlackBox.getDirectoryFtpDownload());
			callableStatement.setString(11, configurazioneBlackBox.getDirectoryFtpUpload());
			callableStatement.setString(12, configurazioneBlackBox.getPathLocaleInput());
			callableStatement.setString(13, configurazioneBlackBox.getPathLocaleScarti());
			callableStatement.setString(14, configurazioneBlackBox.getPathLocaleOutput());
			callableStatement.setString(15, configurazioneBlackBox.getCodiceServizio());
			callableStatement.setString(16, configurazioneBlackBox.getFlagPoste());
			callableStatement.setString(17, configurazioneBlackBox.getEmailPoste());
			callableStatement.registerOutParameter(18, Types.VARCHAR);
			callableStatement.registerOutParameter(19, Types.VARCHAR);
			callableStatement.execute();
			esitoRisposte.setCodiceMessaggio(callableStatement.getString(18));
			esitoRisposte.setDescrizioneMessaggio(callableStatement.getString(19));
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
			// inizio LP PG21XX04 Leak
			// DAOHelper.closeIgnoringException(connection);
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
			// fine LP PG21XX04 Leak
		}
		return esitoRisposte;
	}

	@Override
	public BlackBoxPagelist blackboxList(ConfigurazioneBlackBox blackbox, int rowsPerPage, int pageNumber,
			String OrderBy) throws DaoException {
		CallableStatement callableStatement = null;
		Connection connection = null;
		ResultSet data = null;
		PageInfo pageInfo = null;
		BlackBoxPagelist blackboxPagelist = null;
		String[] blackboxLst = new String[2];
		try {
			connection = getConnection();
			//inizio LP 20240919 PGNTCORE-24 
			//callableStatement = Helper.prepareCall(connection, getSchema(), Routines.CNCNFSP_LST.routine());
			callableStatement = prepareCall(Routines.CNCNFSP_LST.routine());
			//fine LP 20240919 PGNTCORE-24
			callableStatement.setString(1, blackbox.getCodiceEnte());
			callableStatement.setString(2, blackbox.getCodiceIdentificativoDominio());
			callableStatement.setString(3, blackbox.getFlagIuv());
			callableStatement.setInt(4, pageNumber); /* rows per page */
			callableStatement.setInt(5, rowsPerPage); /* page number */
			callableStatement.setString(6, OrderBy);
			callableStatement.registerOutParameter(7, Types.INTEGER);
			/* we register row end */
			callableStatement.registerOutParameter(8, Types.INTEGER);
			/* we register total rows */
			callableStatement.registerOutParameter(9, Types.INTEGER);
			/* we register total pages */
			callableStatement.registerOutParameter(10, Types.SMALLINT);
			/* we execute procedure */
			if (callableStatement.execute()) {
				pageInfo = new PageInfo();
				pageInfo.setPageNumber(pageNumber);
				pageInfo.setRowsPerPage(rowsPerPage);
				pageInfo.setFirstRow(callableStatement.getInt(7));
				pageInfo.setLastRow(callableStatement.getInt(8));
				pageInfo.setNumRows(callableStatement.getInt(9));
				pageInfo.setNumPages(callableStatement.getInt(10));
				data = callableStatement.getResultSet();
				loadWebRowSet(data);
				blackboxLst[0] = getWebRowSetXml();
				if (callableStatement.getMoreResults()) {
					// inizio LP PG21XX04 Leak
					if (data != null) {
						try {
							data.close();
						} catch (SQLException e) {
							e.printStackTrace();
						}
					}
					// fine LP PG21XX04 Leak
					data = callableStatement.getResultSet();
					loadWebRowSet(data);
					blackboxLst[1] = getWebRowSetXml();
				}
			}
			blackboxPagelist = new BlackBoxPagelist(pageInfo, "00", "", blackboxLst);
			return blackboxPagelist;
		} catch (SQLException e) {
			e.printStackTrace();
			blackboxPagelist = new BlackBoxPagelist(pageInfo, "01", "Sql-Exception", "");
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
			blackboxPagelist = new BlackBoxPagelist(pageInfo, "01", "Sql-Exception", "");
		} catch (HelperException e) {
			e.printStackTrace();
			blackboxPagelist = new BlackBoxPagelist(pageInfo, "01", "Sql-Exception", "");
		} finally {
			// inizio LP PG21XX04 Leak
			// DAOHelper.closeIgnoringException(callableStatement);
			// DAOHelper.closeIgnoringException(connection);
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
			// fine LP PG21XX04 Leak
		}
		return blackboxPagelist;
	}

	// PG200120
	@Override
	public BlackBoxPosPagelist blackboxposList(ConfigurazioneBlackBoxPos blackboxpos, int rowsPerPage, int pageNumber,
			String OrderBy) throws DaoException {
		CallableStatement callableStatement = null;
		Connection connection = null;
		ResultSet data = null;
		PageInfo pageInfo = null;
		BlackBoxPosPagelist blackBoxPosPagelist = null;
		String[] blackboxLst = new String[2];
		try {
			connection = getConnection();
			//inizio LP 20240919 PGNTCORE-24 
			//callableStatement = Helper.prepareCall(connection, getSchema(), Routines.CNDOCSP_LST.routine());
			callableStatement = prepareCall(Routines.CNDOCSP_LST.routine());
			//fine LP 20240919 PGNTCORE-24 
			callableStatement.setString(1, blackboxpos.getCodiceIdentificativoDominio());
			callableStatement.setString(2, blackboxpos.getCodiceEnte());
			callableStatement.setString(3, blackboxpos.getNumeroAvviso());
			callableStatement.setString(4, blackboxpos.getCodiceFiscale());
			callableStatement.setString(5, blackboxpos.getCodiceIdentificativoDocumento());
			callableStatement.setString(6, blackboxpos.getFlagPagato());
			callableStatement.setDate(7, blackboxpos.getDataCreazione() != null ? new java.sql.Date(blackboxpos.getDataCreazione().getTimeInMillis()) : null);
			callableStatement.setInt(8, blackboxpos.getAnnoRiferimento());
			callableStatement.setString(9, OrderBy);
			callableStatement.setInt(10, rowsPerPage);
			callableStatement.setInt(11, pageNumber);
			callableStatement.registerOutParameter(12, Types.INTEGER);
			callableStatement.registerOutParameter(13, Types.INTEGER);
			callableStatement.registerOutParameter(14, Types.INTEGER);
			callableStatement.registerOutParameter(15, Types.SMALLINT);
			if (callableStatement.execute()) {
				pageInfo = new PageInfo();
				pageInfo.setPageNumber(pageNumber);
				pageInfo.setRowsPerPage(rowsPerPage);
				pageInfo.setFirstRow(callableStatement.getInt(12));
				pageInfo.setLastRow(callableStatement.getInt(13));
				pageInfo.setNumRows(callableStatement.getInt(14));
				pageInfo.setNumPages(callableStatement.getInt(15));
				data = callableStatement.getResultSet();
				loadWebRowSet(data);
				blackboxLst[0] = getWebRowSetXml();
				if (callableStatement.getMoreResults()) {
					// inizio LP PG21XX04 Leak
					if (data != null) {
						try {
							data.close();
						} catch (SQLException e) {
							e.printStackTrace();
						}
					}
					// fine LP PG21XX04 Leak
					data = callableStatement.getResultSet();
					loadWebRowSet(data);
					blackboxLst[1] = getWebRowSetXml();
				}
			}
			blackBoxPosPagelist = new BlackBoxPosPagelist(pageInfo, "00", "", blackboxLst);
			return blackBoxPosPagelist;

		} catch (SQLException e) {
			e.printStackTrace();
			blackBoxPosPagelist = new BlackBoxPosPagelist(pageInfo, "01", "Sql-Exception", "");
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
			blackBoxPosPagelist = new BlackBoxPosPagelist(pageInfo, "01", "Sql-Exception", "");
		} catch (HelperException e) {
			e.printStackTrace();
			blackBoxPosPagelist = new BlackBoxPosPagelist(pageInfo, "01", "Sql-Exception", "");
		} finally {
			// inizio LP PG21XX04 Leak
			// DAOHelper.closeIgnoringException(callableStatement);
			// DAOHelper.closeIgnoringException(connection);
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
			// fine LP PG21XX04 Leak
		}
		return blackBoxPosPagelist;
	}

	@Override
	public EsitoRisposte delete(ConfigurazioneBlackBoxPos configurazioneBlackBoxpos) throws DaoException {
		CallableStatement callableStatement = null;
		Connection connection = null;
		EsitoRisposte esitoRisposte = new EsitoRisposte();
		try {
			connection = getConnection();
			//inizio LP 20240919 PGNTCORE-24 
			//callableStatement = Helper.prepareCall(connection, getSchema(), Routines.CNDOCSP_DEL.routine());
			callableStatement = prepareCall(Routines.CNDOCSP_DEL.routine());
			//fine LP 20240919 PGNTCORE-24 
			callableStatement.setString(1, configurazioneBlackBoxpos.getCodiceIdentificativoDominio());
			callableStatement.setString(2, configurazioneBlackBoxpos.getCodiceEnte());
			callableStatement.setString(3, configurazioneBlackBoxpos.getNumeroAvviso());
			callableStatement.registerOutParameter(4, Types.VARCHAR);
			callableStatement.registerOutParameter(5, Types.VARCHAR);
			callableStatement.execute();
			esitoRisposte.setCodiceMessaggio(callableStatement.getString(4));
			esitoRisposte.setDescrizioneMessaggio(callableStatement.getString(5));
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
			// inizio LP PG21XX04 Leak
			// DAOHelper.closeIgnoringException(connection);
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
			// fine LP PG21XX04 Leak
		}
		return esitoRisposte;
		// return null;
	}

	@Override
	public ConfigurazioneBlackBoxPos select(ConfigurazioneBlackBoxPos blackboxpos) throws DaoException {
		CallableStatement callableStatement = null;
		ResultSet resultSet = null;
		Connection connection = null;
		try {
			connection = getConnection();
			//inizio LP 20240919 PGNTCORE-24 
			//callableStatement = Helper.prepareCall(connection, getSchema(), Routines.CNDOCSP_SEL.routine());
			callableStatement = prepareCall(Routines.CNDOCSP_SEL.routine());
			//fine LP 20240919 PGNTCORE-24 
			callableStatement.setString(1, blackboxpos.getCodiceIdentificativoDominio());
			callableStatement.setString(2, blackboxpos.getCodiceEnte());
			callableStatement.setString(3, blackboxpos.getNumeroAvviso());
//			callableStatement.setString(3, blackboxpos.getCodiceIuv());
			callableStatement.execute();
			resultSet = callableStatement.getResultSet();
			if (resultSet.next()) {
				blackboxpos = new ConfigurazioneBlackBoxPos(resultSet);
			}
		} catch (SQLException e) {
			throw new DaoException(e);
		} catch (IllegalArgumentException e) {
			throw new DaoException(e);
		} catch (HelperException e) {
			e.printStackTrace();
			throw new DaoException(e);
		} finally {
			// inizio LP PG21XX04 Leak
			// DAOHelper.closeIgnoringException(callableStatement);
			// DAOHelper.closeIgnoringException(connection);
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
			// fine LP PG21XX04 Leak
		}
		return blackboxpos;
	}

	@Override
	public Integer update(ConfigurazioneBlackBoxPos blackboxpos) throws DaoException {
		CallableStatement callableStatement = null;
		Connection connection = null;
		int ret = 0;
		try {

			connection = getConnection();
			//inizio LP 20240919 PGNTCORE-24 
			//callableStatement = Helper.prepareCall(connection, getSchema(), Routines.CNDOCSP_UPD.routine());
			callableStatement = prepareCall(Routines.CNDOCSP_UPD.routine());
			//fine LP 20240919 PGNTCORE-24
			callableStatement.setString(1, blackboxpos.getCodiceIdentificativoDominio());
			callableStatement.setString(2, blackboxpos.getCodiceEnte());
			callableStatement.setString(3, blackboxpos.getNumeroAvviso());
			callableStatement.setString(4, blackboxpos.getCodiceIdentificativoFlusso());
			callableStatement.setDate(5, blackboxpos.getDataCreazione()!=null ? new java.sql.Date(blackboxpos.getDataCreazione().getTimeInMillis()) : null);
			callableStatement.setString(6, blackboxpos.getTipoRecord());
			callableStatement.setString(7, blackboxpos.getCodiceIdentificativoDocumento());
			callableStatement.setString(8, blackboxpos.getNumeroRata());
			callableStatement.setDate(9, blackboxpos.getDataScadenza()!=null ? new java.sql.Date(blackboxpos.getDataScadenza().getTimeInMillis()) : null);
			callableStatement.setString(10, blackboxpos.getCodiceFiscale());
			//callableStatement.setDouble(11, blackboxpos.getImporto());
			callableStatement.setBigDecimal(11, new BigDecimal(blackboxpos.getImporto()));
			callableStatement.setString(12, blackboxpos.getDenominazioneDebitore());
			callableStatement.setString(13, blackboxpos.getIndirizzoContribuente());
			callableStatement.setString(14, blackboxpos.getLocalitaContribuente());
			callableStatement.setString(15, blackboxpos.getProvinciaContribuente());
			callableStatement.setString(16, blackboxpos.getFlagAnnullamento());
			callableStatement.setString(17, blackboxpos.getCodiceIbanAccredito());
			callableStatement.setString(18, blackboxpos.getCodiceIuv());
			callableStatement.setString(19, blackboxpos.getFlagPagato());
			// inizio LP PG200370
			callableStatement.setString(20, (blackboxpos.getTassonomia() != null ? blackboxpos.getTassonomia().trim() : ""));
			// fine LP PG200370
			// inizio LP PG200370
			// callableStatement.registerOutParameter(20, Types.INTEGER);
			callableStatement.registerOutParameter(21, Types.INTEGER);
			// fine LP PG200370
			callableStatement.execute();
			// inizio LP PG200370
			// ret = callableStatement.getInt(20);
			ret = callableStatement.getInt(21);
			// fine LP PG200370
		} catch (SQLException e) {
			e.printStackTrace();
			ret = e.getErrorCode();
			throw new DaoException(e);
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
			throw new DaoException(e);
		} catch (HelperException e) {
			e.printStackTrace();
			throw new DaoException(e);
		} finally {
			// inizio LP PG21XX04 Leak
			// DAOHelper.closeIgnoringException(connection);
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
			// fine LP PG21XX04 Leak
		}
		return ret;
	}

	@Override
	public void updatePagPos(ConfigurazioneBlackBoxPos blackboxpos) throws DaoException {
		CallableStatement callableStatement = null;
		Connection connection = null;
		try {
			connection = getConnection();
			// callableStatement = Helper.prepareCall(connection, getSchema(),
			// Routines.CNDOCSP_UPD_PAG.routine());
			// inizio LP PG200370
			// Nota. Proviamo ad ripristinare l'uso del SP CNDOCSP_UPD_PAG modificata e resa
			// uguale alla CNDOCSP_UPD_PAG3
			// cancellando dal DB e da GIT la SP CNDOCSP_UPD_PAG3
			// callableStatement = Helper.prepareCall(connection, getSchema(),
			// "CNDOCSP_UPD_PAG3");
			//inizio LP 20240919 PGNTCORE-24 
			//callableStatement = Helper.prepareCall(connection, getSchema(), Routines.CNDOCSP_UPD_PAG.routine());
			callableStatement = prepareCall(Routines.CNDOCSP_UPD_PAG.routine());
			//fine LP 20240919 PGNTCORE-24 
			// fine LP PG200370
			callableStatement.setString(1, blackboxpos.getCodiceIdentificativoDominio());
			callableStatement.setString(2, blackboxpos.getCodiceEnte());
			callableStatement.setString(3, blackboxpos.getNumeroAvviso());
			callableStatement.setString(4, blackboxpos.getFlagPagato());
			callableStatement.setBigDecimal(5, new BigDecimal(blackboxpos.getImportoPagato()));
			callableStatement.setDate(6, blackboxpos.getDataPagamento() != null ? new java.sql.Date(blackboxpos.getDataPagamento().getTimeInMillis()) : null);
			callableStatement.registerOutParameter(7, Types.INTEGER);
			callableStatement.execute();
			int nRows = callableStatement.getInt(7);
			System.out.println("updatePagPos - identificativoDominio: " + blackboxpos.getCodiceIdentificativoDominio());
			System.out.println("updatePagPos - codiceEnte: " + blackboxpos.getCodiceEnte());
			System.out.println("updatePagPos - numeroAvviso: " + blackboxpos.getNumeroAvviso());
			System.out.println("updatePagPos - flgPagato: " + blackboxpos.getFlagPagato());
			System.out.println("updatePagPos - importoPagato: " + new BigDecimal(blackboxpos.getImportoPagato()));
			System.out.println("updatePagPos - dataPagamento: "
					+ new java.sql.Date(blackboxpos.getDataPagamento().getTimeInMillis()));
			System.out.println("updatePagPos - nRows: " + nRows);
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
			// inizio LP PG21XX04 Leak
			// DAOHelper.closeIgnoringException(connection);
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
			// fine LP PG21XX04 Leak
		}
	}

	@Override
	public BlackBoxPosLogPagelist blackboxposlogList(BlackBoxPosLog blackboxpos, int rowsPerPage, int pageNumber,
			String OrderBy) throws DaoException {
		CallableStatement callableStatement = null;
		Connection connection = null;
		ResultSet data = null;
		PageInfo pageInfo = null;
		BlackBoxPosLogPagelist blackBoxPosLogPagelist = null;
		String[] blackboxLst = new String[2];
		try {
			connection = getConnection();
			//inizio LP 20240919 PGNTCORE-24 
			//callableStatement = Helper.prepareCall(connection, getSchema(), Routines.CNDOLSP_LST.routine());
			callableStatement = prepareCall(Routines.CNDOLSP_LST.routine());
			//fine LP 20240919 PGNTCORE-24 
			callableStatement.setString(1, blackboxpos.getCodiceIdentificativoDominio());
			callableStatement.setString(2, blackboxpos.getCodiceEnte());
			callableStatement.setString(3, blackboxpos.getNumeroAvviso());
			callableStatement.setString(4, OrderBy);
			callableStatement.setInt(5, rowsPerPage);
			callableStatement.setInt(6, pageNumber);
			callableStatement.registerOutParameter(7, Types.INTEGER);
			callableStatement.registerOutParameter(8, Types.INTEGER);
			callableStatement.registerOutParameter(9, Types.INTEGER);
			callableStatement.registerOutParameter(10, Types.SMALLINT);
			if (callableStatement.execute()) {
				pageInfo = new PageInfo();
				pageInfo.setPageNumber(pageNumber);
				pageInfo.setRowsPerPage(rowsPerPage);
				pageInfo.setFirstRow(callableStatement.getInt(7));
				pageInfo.setLastRow(callableStatement.getInt(8));
				pageInfo.setNumRows(callableStatement.getInt(9));
				pageInfo.setNumPages(callableStatement.getInt(10));
				data = callableStatement.getResultSet();
				loadWebRowSet(data);
				blackboxLst[0] = getWebRowSetXml();
				if (callableStatement.getMoreResults()) {
					// inizio LP PG21XX04 Leak
					if (data != null) {
						try {
							data.close();
						} catch (SQLException e) {
							e.printStackTrace();
						}
					}
					// fine LP PG21XX04 Leak
					data = callableStatement.getResultSet();
					loadWebRowSet(data);
					blackboxLst[1] = getWebRowSetXml();
				}
			}
			blackBoxPosLogPagelist = new BlackBoxPosLogPagelist(pageInfo, "00", "", blackboxLst);
			return blackBoxPosLogPagelist;
		} catch (SQLException e) {
			e.printStackTrace();
			blackBoxPosLogPagelist = new BlackBoxPosLogPagelist(pageInfo, "01", "Sql-Exception", "");
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
			blackBoxPosLogPagelist = new BlackBoxPosLogPagelist(pageInfo, "01", "Sql-Exception", "");
		} catch (HelperException e) {
			e.printStackTrace();
			blackBoxPosLogPagelist = new BlackBoxPosLogPagelist(pageInfo, "01", "Sql-Exception", "");
		} finally {
			// inizio LP PG21XX04 Leak
			// DAOHelper.closeIgnoringException(callableStatement);
			// DAOHelper.closeIgnoringException(connection);
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
			// fine LP PG21XX04 Leak
		}
		return blackBoxPosLogPagelist;
	}

	@Override
	public EsitoRisposte delete(BlackBoxPosLog configurazioneBlackBoxPosLog) throws DaoException {
		CallableStatement callableStatement = null;
		Connection connection = null;
		EsitoRisposte esitoRisposte = new EsitoRisposte();
		try {
			connection = getConnection();
			//inizio LP 20240919 PGNTCORE-24 
			//callableStatement = Helper.prepareCall(connection, getSchema(), Routines.CNDOLSP_DEL.routine());
			callableStatement = prepareCall(Routines.CNDOLSP_DEL.routine());
			//fine LP 20240919 PGNTCORE-24 
			callableStatement.setInt(1, configurazioneBlackBoxPosLog.getIdLog());
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
			// inizio LP PG21XX04 Leak
			// DAOHelper.closeIgnoringException(connection);
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
			// fine LP PG21XX04 Leak
		}
		return esitoRisposte;
	}

	@Override
	public Integer insert(BlackBoxPosLog configurazioneBlackBoxpos) throws DaoException {
		CallableStatement callableStatement = null;
		Connection connection = null;
		int esito = -1;
		try {
			connection = getConnection();
			//inizio LP 20240919 PGNTCORE-24 
			//callableStatement = Helper.prepareCall(connection, getSchema(), Routines.CNDOLSP_INS.routine());
			callableStatement = prepareCall(Routines.CNDOLSP_INS.routine());
			//fine LP 20240919 PGNTCORE-24 
			callableStatement.setString(1, configurazioneBlackBoxpos.getCodiceIdentificativoDominio());
			callableStatement.setString(2, configurazioneBlackBoxpos.getCodiceEnte());
			callableStatement.setString(3, configurazioneBlackBoxpos.getNumeroAvviso());
			callableStatement.setDate(4, configurazioneBlackBoxpos.getDataInserimento() != null ? new java.sql.Date(configurazioneBlackBoxpos.getDataInserimento().getTimeInMillis()) : null);
			callableStatement.setString(5, configurazioneBlackBoxpos.getOperazioneEseguita());
			callableStatement.registerOutParameter(6, Types.INTEGER);
			callableStatement.execute();
			esito = callableStatement.getInt(6);
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
			// inizio LP PG21XX04 Leak
			// DAOHelper.closeIgnoringException(connection);
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
			// fine LP PG21XX04 Leak
		}
		return esito;
	}

	// PG200180
	@Override
	public int getProgressivoIuv(String codiceEnte, String code) throws DaoException {
		CallableStatement callableStatement = null;
		Connection connection = null;
		int ret = 0;
		try {
			connection = getConnection();
			//inizio LP 20240919 PGNTCORE-24 
			//callableStatement = Helper.prepareCall(connection, getSchema(), Routines.CNKEYSP.routine());
			callableStatement = prepareCall(Routines.CNKEYSP.routine());
			//fine LP 20240919 PGNTCORE-24 
			callableStatement.setString(1, codiceEnte);
			callableStatement.setString(2, code);
			callableStatement.registerOutParameter(3, ret);
			callableStatement.execute();
			ret = callableStatement.getInt(3);
		} catch (SQLException e) {
			e.printStackTrace();
			ret = e.getErrorCode();
			throw new DaoException(e);
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
			throw new DaoException(e);
		} catch (HelperException e) {
			e.printStackTrace();
			throw new DaoException(e);
		} finally {
			// inizio LP PG21XX04 Leak
			// DAOHelper.closeIgnoringException(connection);
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
			// fine LP PG21XX04 Leak
		}
		return ret;
	}

	@Override
	public Integer insert(ConfigurazioneBlackBoxPos blackboxpos) throws DaoException {
		CallableStatement callableStatement = null;
		Connection connection = null;
		int ret = 0;
		try {
			connection = getConnection();
			//inizio LP 20240919 PGNTCORE-24 
			//callableStatement = Helper.prepareCall(connection, getSchema(), Routines.CNDOCSP_INS.routine());
			callableStatement = prepareCall(Routines.CNDOCSP_INS.routine());
			//fine LP 20240919 PGNTCORE-24
			callableStatement.setString(1, blackboxpos.getCodiceIdentificativoDominio());
			callableStatement.setString(2, blackboxpos.getCodiceEnte());
			callableStatement.setString(3, blackboxpos.getNumeroAvviso());
			callableStatement.setString(4, blackboxpos.getCodiceIdentificativoFlusso());
			callableStatement.setDate(5, blackboxpos.getDataCreazione() != null ? new java.sql.Date(blackboxpos.getDataCreazione().getTimeInMillis()) : null);
			callableStatement.setString(6, blackboxpos.getTipoRecord());
			callableStatement.setString(7, blackboxpos.getCodiceIdentificativoDocumento());
			callableStatement.setString(8, blackboxpos.getNumeroRata());
			callableStatement.setDate(9, blackboxpos.getDataScadenza() !=null ? new java.sql.Date(blackboxpos.getDataScadenza().getTimeInMillis()) : null);
			callableStatement.setString(10, blackboxpos.getCodiceFiscale());
			//callableStatement.setDouble(11, blackboxpos.getImporto());
			callableStatement.setBigDecimal(11, new BigDecimal(blackboxpos.getImporto()));
			callableStatement.setString(12, blackboxpos.getDenominazioneDebitore());
			callableStatement.setString(13, blackboxpos.getIndirizzoContribuente());
			callableStatement.setString(14, blackboxpos.getLocalitaContribuente());
			callableStatement.setString(15, blackboxpos.getProvinciaContribuente());
			callableStatement.setString(16, blackboxpos.getFlagAnnullamento());
			callableStatement.setDate(17, blackboxpos.getDataAggiornamentoRecord() != null ?
					new java.sql.Date(blackboxpos.getDataAggiornamentoRecord().getTimeInMillis()) : null);
			callableStatement.setString(18, blackboxpos.getCodiceIbanAccredito());
			callableStatement.setString(19, blackboxpos.getCodiceIuv());
			callableStatement.setString(20, blackboxpos.getFlagPagato());
			//callableStatement.setDouble(21, blackboxpos.getImportoPagato());
			callableStatement.setBigDecimal(21, new BigDecimal(blackboxpos.getImportoPagato()));
			callableStatement.setString(22, blackboxpos.getCodiceTipologiaServizio());
			callableStatement.setString(23, blackboxpos.getCodiceIBAN2() == null ? "" : blackboxpos.getCodiceIBAN2());
			callableStatement.setString(24, blackboxpos.getCausaleServizio() == null ? "" : blackboxpos.getCausaleServizio());
			callableStatement.setString(25, blackboxpos.getCespite() == null ? "" : blackboxpos.getCespite());
			callableStatement.setString(26, blackboxpos.getAnnoRif() == null ? "" : blackboxpos.getAnnoRif());
			callableStatement.setString(27, blackboxpos.getCittaCAP() == null ? "" : blackboxpos.getCittaCAP());
			callableStatement.setString(28, blackboxpos.getCodiceUtente() == null ? "" : blackboxpos.getCodiceUtente());
			callableStatement.setString(29, blackboxpos.getCodiceSocieta() == null ? "" : blackboxpos.getCodiceSocieta());
			callableStatement.setString(30, blackboxpos.getChiaveEnte() == null ? "" : blackboxpos.getChiaveEnte());
			// inizio LP PG200370
			// callableStatement.registerOutParameter(31, Types.INTEGER);
			callableStatement.setString(31, (blackboxpos.getTassonomia() != null ? blackboxpos.getTassonomia().trim() : ""));
			//inizio SB PG210170
			callableStatement.setString(32, (blackboxpos.getTarga() != null ? blackboxpos.getTarga().trim() : ""));
			System.out.println("Data Verbale: " + blackboxpos.getDataVerbale());
			if(blackboxpos.getDataVerbale()!=null)
				callableStatement.setString(33, blackboxpos.getDataVerbale());
			else
				callableStatement.setNull(33,Types.VARCHAR);
			callableStatement.setString(34, (blackboxpos.getNumeroVerbale() != null ? blackboxpos.getNumeroVerbale().trim() : ""));
			callableStatement.setString(35, (blackboxpos.getNumBollettinoCDS() != null ? blackboxpos.getNumBollettinoCDS().trim() : ""));
			//fine SB PG210170
			callableStatement.registerOutParameter(36, Types.INTEGER);
			// fine LP PG200370
			callableStatement.execute();
			// inizio LP PG200370
			// ret = callableStatement.getInt(31);
			ret = callableStatement.getInt(36);
			// fine LP PG200370
		} catch (SQLException e) {
			e.printStackTrace();
			ret = e.getErrorCode();
			throw new DaoException(e);
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
			throw new DaoException(e);
		} catch (HelperException e) {
			e.printStackTrace();
			throw new DaoException(e);
		} finally {
			// inizio LP PG21XX04 Leak
			// DAOHelper.closeIgnoringException(connection);
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
			// fine LP PG21XX04 Leak
		}

		return ret;
	}
	// FINE PG200180

	@Override
	public int getProgressivoIuvGiornaliero(String codiceEnte, String code) throws DaoException {
		CallableStatement callableStatement = null;
		Connection connection = null;
		int ret = 0;
		try {
			connection = getConnection();
			//inizio LP 20240919 PGNTCORE-24 
			//callableStatement = Helper.prepareCall(connection, getSchema(), Routines.CNKEYSP_GG.routine());
			callableStatement = prepareCall(Routines.CNKEYSP_GG.routine());
			//fine LP 20240919 PGNTCORE-24 
			callableStatement.setString(1, codiceEnte);
			callableStatement.setString(2, code);
			callableStatement.registerOutParameter(3, ret);
			callableStatement.execute();
			ret = callableStatement.getInt(3);
		} catch (SQLException e) {
			e.printStackTrace();
			ret = e.getErrorCode();
			throw new DaoException(e);
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
			throw new DaoException(e);
		} catch (HelperException e) {
			e.printStackTrace();
			throw new DaoException(e);
		} finally {
			// inizio LP PG21XX04 Leak
			// DAOHelper.closeIgnoringException(connection);
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
			// fine LP PG21XX04 Leak
		}
		return ret;
	}
	
	//inizio SR PAGONET-11
	public void aggiornaFlagInviaDovuto(String codiceIdentificativoDominio, String numeroAvviso, String flagInviaDovuto) throws Exception {
		CallableStatement callableStatement = null;
		Connection connection = null;
		try {
			connection = getConnection();
			//inizio LP 20240919 PGNTCORE-24 
			//callableStatement = Helper.prepareCall(connection, getSchema(),  Routines.CNDOCSP_UPD_INV.routine());		
			callableStatement = prepareCall(Routines.CNDOCSP_UPD_INV.routine());
			//fine LP 20240919 PGNTCORE-24 
			callableStatement.setString(1, codiceIdentificativoDominio.trim());
			callableStatement.setString(2, numeroAvviso.trim());
			callableStatement.setString(3, flagInviaDovuto);
			callableStatement.execute();
		} catch (SQLException x) {
			throw new Exception(x);
		} catch (IllegalArgumentException x) {
			throw new Exception(x);
		} catch (HelperException x) {
			x.printStackTrace();
			throw new DaoException(x);
		} finally {
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
		}
	}
	// fine SR PAGONET-11
	
}