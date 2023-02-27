package com.seda.payer.core.wallet.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;

import javax.sql.DataSource;

import com.seda.data.dao.DAOHelper;
import com.seda.data.helper.Helper;
import com.seda.data.helper.HelperException;
import com.seda.data.spi.PageInfo;
import com.seda.payer.core.dao.Routines;
import com.seda.payer.core.exception.DaoException;
import com.seda.payer.core.handler.BaseDaoHandler;
import com.seda.payer.core.wallet.bean.AbilitazioneDiscarico;
import com.seda.payer.core.wallet.bean.EsitoRisposte;
import com.seda.payer.core.wallet.bean.WalletPageList;

public class AbilitazioneDiscaricoDAOImpl extends BaseDaoHandler implements
		AbilitazioneDiscaricoDAO {

	public AbilitazioneDiscaricoDAOImpl(Connection connection, String schema) {
		super(connection, schema);
		// TODO Auto-generated constructor stub
	}
	//inizio LP PG21XX04 Leak
	@Deprecated
	//fine LP PG21XX04 Leak
	public AbilitazioneDiscaricoDAOImpl(DataSource dataSource, String schema) throws SQLException  {
		super(dataSource.getConnection(), schema);
	}

	public WalletPageList abilitazioneList(
			AbilitazioneDiscarico abilitazioneDiscarico, int rowsPerPage,
			int pageNumber, String OrderBy) throws DaoException {
		CallableStatement callableStatement=null;
		Connection connection = null;
		ResultSet data = null;
		PageInfo pageInfo = null;
		WalletPageList walletPageList = null;
		try {

//			IN I_PAGENO SMALLINT,
//			IN I_ROWSPERPAGE SMALLINT,
//			IN I_ABD_CSOCCSOC VARCHAR(5),
//		    IN I_ABD_CUTECUTE VARCHAR(5),
//		    IN I_ABD_KANEKENT CHAR(10),
//		    IN I_ABD_CTRBCODI VARCHAR(5),
//		    IN I_ABD_DTRBDESC VARCHAR(200),  
//		    IN I_ABD_FABDDISC CHAR(1),
//			OUT O_ROWINI INTEGER,
//			OUT O_ROWEND INTEGER,
//			OUT O_TOTROWS INTEGER,
//			OUT O_TOTPAGES SMALLINT
			
			connection = getConnection();
			callableStatement = Helper.prepareCall(connection, getSchema(), Routines.PYABDSP_LST.routine());
			callableStatement.setInt(1, pageNumber);                          /* rows per page */
			callableStatement.setInt(2, rowsPerPage);                        /* page number*/
			callableStatement.setString(3,abilitazioneDiscarico.getCodSoc());
			callableStatement.setString(4,abilitazioneDiscarico.getCuteCute());
			callableStatement.setString(5,abilitazioneDiscarico.getChiaveEnte());
			callableStatement.setString(6,abilitazioneDiscarico.getCodiceTributo());
			callableStatement.setString(7,abilitazioneDiscarico.getDescrizioneTributo().replace("'", "''"));
			callableStatement.setString(8 ,abilitazioneDiscarico.getFlagAbilitazioneDiscarico() );
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

	public EsitoRisposte deleteAbilitazione(AbilitazioneDiscarico abilitazioneDiscarico)
			throws DaoException {
		CallableStatement callableStatement=null;
		Connection connection = null;
		EsitoRisposte  esitoRisposte = new EsitoRisposte();
		try {
			connection = getConnection();
			callableStatement = Helper.prepareCall(connection, getSchema(), Routines.PYABDSP_DEL.routine());
//			IN I_ABD_CSOCCSOC VARCHAR(5),
//			IN I_ABD_CUTECUTE VARCHAR(5),
//			IN I_ABD_KANEKENT CHAR(10),  
//			IN I_ABD_CTRBCODI VARCHAR(5),
//			OUT O_SLI_CODESITO VARCHAR(2),
//			OUT O_SLI_MSGESITO VARCHAR(100)		

			callableStatement.setString(1, abilitazioneDiscarico.getCodSoc());
			callableStatement.setString(2, abilitazioneDiscarico.getCuteCute());
			callableStatement.setString(3, abilitazioneDiscarico.getChiaveEnte());
			callableStatement.setString(4, abilitazioneDiscarico.getCodiceTributo());			
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

	public  String insertAbilitazione(AbilitazioneDiscarico abilitazioneDiscarico)
			throws DaoException {
		CallableStatement callableStatement=null;
		Connection connection = null;	
		connection = getConnection();
		String esito="";
		try {
			callableStatement = Helper.prepareCall(connection, getSchema(), Routines.PYABDSP_INS.routine());
			callableStatement.setString(1, abilitazioneDiscarico.getCodSoc());
			callableStatement.setString(2, abilitazioneDiscarico.getCuteCute());
			callableStatement.setString(3, abilitazioneDiscarico.getChiaveEnte());
			callableStatement.setString(4, abilitazioneDiscarico.getCodiceTributo());
			callableStatement.setString(5, abilitazioneDiscarico.getDescrizioneTributo());
			callableStatement.setString(6, abilitazioneDiscarico.getFlagAbilitazioneDiscarico());
			callableStatement.setString(7, abilitazioneDiscarico.getOperatoreInserimento());
			callableStatement.registerOutParameter(8, Types.VARCHAR);
			callableStatement.registerOutParameter(9, Types.VARCHAR);
			
			callableStatement.execute();
			esito=callableStatement.getString(8);
			
			
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DaoException(01,"Problemi generici nell'inserimento dei dati",e);
		} catch (HelperException e) {
			e.printStackTrace();
			throw new DaoException(01,"Problemi generici nell'inserimento dei dati",e);
		} finally {
			//inizio LP PG21XX04 Leak
			//DAOHelper.closeIgnoringException(callableStatement);
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

	

	public int  updateAbilitazione(AbilitazioneDiscarico abilitazioneDiscarico)
			throws DaoException {
		// TODO Auto-generated method stub
		CallableStatement callableStatement=null;
		Connection connection = null;	
		connection = getConnection();
		int recordInseriti=0;
		try {
			callableStatement = Helper.prepareCall(connection, getSchema(), Routines.PYABDSP_UPD.routine());
			callableStatement.setString(1, abilitazioneDiscarico.getCodSoc());
			callableStatement.setString(2, abilitazioneDiscarico.getCuteCute());
			callableStatement.setString(3, abilitazioneDiscarico.getChiaveEnte());
			callableStatement.setString(4, abilitazioneDiscarico.getCodiceTributo());
			callableStatement.setString(5, abilitazioneDiscarico.getFlagAbilitazioneDiscarico());
			callableStatement.setString(6, abilitazioneDiscarico.getOperatoreInserimento());
			callableStatement.registerOutParameter(7, Types.INTEGER);
			callableStatement.execute();
			recordInseriti=callableStatement.getInt(7);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DaoException(01,"Problemi generici nell'aggiornamneto dei dati",e);
		} catch (HelperException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new DaoException(01,"Problemi generici nell'aggiornamneto dei dati",e);
		}finally {
			//inizio LP PG21XX04 Leak
			//DAOHelper.closeIgnoringException(callableStatement);
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
		return recordInseriti;
	}

}
