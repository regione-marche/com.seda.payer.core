package com.seda.payer.core.wallet.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

import javax.sql.DataSource;

import com.seda.data.dao.DAOHelper;
import com.seda.data.helper.Helper;
import com.seda.data.helper.HelperException;
import com.seda.data.spi.PageInfo;
import com.seda.payer.core.dao.Routines;
import com.seda.payer.core.exception.DaoException;
import com.seda.payer.core.handler.BaseDaoHandler;
import com.seda.payer.core.wallet.bean.Avviso;
import com.seda.payer.core.wallet.bean.WalletPageList;

public class GestioneAvvisiDAOImpl extends BaseDaoHandler implements
GestioneAvvisiDAO {

	private static final long serialVersionUID = 1L;

	//inizio LP PG21XX04 Leak
	@Deprecated
	//fine LP PG21XX04 Leak
	public GestioneAvvisiDAOImpl(DataSource dataSource, String schema) throws SQLException  {
		super(dataSource.getConnection(), schema);
	}
	//inizio LP PG21XX04 Leak
	public GestioneAvvisiDAOImpl(Connection connection, String schema) throws SQLException  {
		super(connection, schema);
	}
	//fine LP PG21XX04 Leak

	public WalletPageList avvisiList(Avviso avviso, int rowsPerPage, int pageNumber, String OrderBy) throws DaoException {
		CallableStatement callableStatement=null;
		Connection connection = null;
		ResultSet data = null;
		PageInfo pageInfo = null;
		WalletPageList walletPageList = null;
		try {

			//		    IN I_PAGENO SMALLINT,
			//			IN I_ROWSPERPAGE SMALLINT,
			//		    IN I_MSG_CUTECUTE VARCHAR(5),
			//		    IN I_MSG_CSOCCSOC VARCHAR(5),
			//		    IN I_MSG_KANEKENT CHAR(10),
			//			OUT O_ROWINI INTEGER,
			//			OUT O_ROWEND INTEGER,
			//			OUT O_TOTROWS INTEGER,
			//			OUT O_TOTPAGES SMALLINT

			connection = getConnection();
			callableStatement = Helper.prepareCall(connection, getSchema(), Routines.PYMSGSP_LST.routine());
			callableStatement.setInt(1, pageNumber);                          /* rows per page */
			callableStatement.setInt(2, rowsPerPage);                        /* page number*/
			callableStatement.setString(3,avviso.getCuteCute());
			callableStatement.setString(4,avviso.getCodSoc());
			callableStatement.setString(5,avviso.getChiaveEnte());
			/* we register row start */
			callableStatement.registerOutParameter(6, Types.INTEGER);
			/* we register row end */
			callableStatement.registerOutParameter(7, Types.INTEGER);
			/* we register total rows */
			callableStatement.registerOutParameter(8, Types.INTEGER);
			/* we register total pages */
			callableStatement.registerOutParameter(9, Types.SMALLINT);
			/* we execute procedure */
			if(callableStatement.execute())	{
				pageInfo = new PageInfo();
				pageInfo.setPageNumber(pageNumber);
				pageInfo.setRowsPerPage(rowsPerPage);
				pageInfo.setFirstRow(callableStatement.getInt(6));
				pageInfo.setLastRow(callableStatement.getInt(7));
				pageInfo.setNumRows(callableStatement.getInt(8));
				pageInfo.setNumPages(callableStatement.getInt(9));

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
	
	
	public Avviso selectAvviso(Avviso avviso) throws DaoException {
		CallableStatement callableStatement=null;
		Connection connection = null;
		ResultSet data = null;
		try {
			connection = getConnection();
			callableStatement = Helper.prepareCall(connection, getSchema(), Routines.PYMSGSP_SEL.routine());
			callableStatement.setString(1,avviso.getCuteCute());
			callableStatement.setString(2,avviso.getCodSoc());
			callableStatement.setString(3,avviso.getChiaveEnte());
			callableStatement.setString(4,avviso.getNomeCampo());
			callableStatement.execute();
			data=callableStatement.getResultSet();
			if(data.next())	{
				avviso.setCuteCute(data.getString(1));
				avviso.setCodSoc(data.getString(2));
				avviso.setChiaveEnte(data.getString(3));
				avviso.setNomeCampo(data.getString(4));
				avviso.setDescrizioneCampo(data.getString(5));
				avviso.setTesto(data.getString(6));
				return avviso;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (HelperException e) {
			e.printStackTrace();
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
		return avviso;

	}


	public int  updateAvviso(Avviso avviso)	throws DaoException {
		// TODO Auto-generated method stub
		CallableStatement callableStatement=null;
		Connection connection = null;	
		connection = getConnection();
		int recordAggio=0;
		try {
			callableStatement = Helper.prepareCall(connection, getSchema(), Routines.PYMSGSP_UPD.routine());
			callableStatement.setString(1, avviso.getCodSoc());
			callableStatement.setString(2, avviso.getCuteCute());
			callableStatement.setString(3, avviso.getChiaveEnte());
			callableStatement.setString(4, avviso.getNomeCampo());
			callableStatement.setString(5, avviso.getTesto());
			callableStatement.registerOutParameter(6, Types.INTEGER);
			callableStatement.execute();
			recordAggio=callableStatement.getInt(6);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DaoException(01,"Problemi generici nell'aggiornamento dei dati",e);
		} catch (HelperException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new DaoException(01,"Problemi generici nell'aggiornamento dei dati",e);
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
		return recordAggio;
	}


}
