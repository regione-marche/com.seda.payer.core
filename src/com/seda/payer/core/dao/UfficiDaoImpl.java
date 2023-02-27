package com.seda.payer.core.dao;

import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import javax.sql.DataSource;
import javax.sql.rowset.CachedRowSet;

import com.seda.commons.string.Convert;
import com.seda.data.dao.DAOHelper;
import com.seda.data.helper.Helper;
import com.seda.data.helper.HelperException;
import com.seda.data.spi.PageInfo;
import com.seda.payer.core.bean.UfficiPageList;
import com.seda.payer.core.bean.Ufficio;
import com.seda.payer.core.exception.DaoException;
import com.seda.payer.core.handler.BaseDaoHandler;

public class UfficiDaoImpl extends BaseDaoHandler implements UfficiDao {
	
	private static final long serialVersionUID = 1L;
	
	//inizio LP PG21XX04 Leak
	@Deprecated
	//fine LP PG21XX04 Leak
	public UfficiDaoImpl(DataSource dataSource, String schema) throws SQLException {
		super(dataSource.getConnection(), schema);
	}
	
	public UfficiDaoImpl(Connection connection, String schema) throws SQLException {
		super(connection, schema);
	}
	
	public Ufficio select(Ufficio ufficio) throws DaoException {
		CallableStatement callableStatement = null;
		ResultSet resultSet = null;
		Connection connection = null;
		try {
			connection = getConnection();
			callableStatement = Helper.prepareCall(connection, getSchema(), Routines.PYPABSP_SEL.routine());
			callableStatement.setString(1, ufficio.getIdUfficio());
			callableStatement.execute();
			resultSet=callableStatement.getResultSet();
			if(resultSet.next()) {
				ufficio = new Ufficio(resultSet);
			}
		} catch (SQLException e) {
			throw new DaoException(e);
		} catch (IllegalArgumentException e) {
			throw new DaoException(e);
		} catch (HelperException e) {
			throw new DaoException(e);
		} finally {
			//inizio LP PG21XX04 Leak
			//DAOHelper.closeIgnoringException(callableStatement);
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
		return ufficio;
	}
	
	public Integer update(Ufficio ufficio) throws DaoException {
		CallableStatement callableStatement=null;
		Connection connection = null;
		int ret=0;
		try {
			connection = getConnection();
			callableStatement = Helper.prepareCall(connection, getSchema(), Routines.PYPABSP_UPD.routine());
			callableStatement.setString(1, ufficio.getIdUfficio());
			callableStatement.setString(2, ufficio.getCodiceUfficio());
			callableStatement.setString(3, ufficio.getDescrizioneIT());
			callableStatement.setString(4, ufficio.getDescrizioneDE());					
			callableStatement.execute();
			ret=1;
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
	
	public Integer delete(Ufficio ufficio) throws DaoException {
		CallableStatement callableStatement=null;
		Connection connection = null;
		int ret=0;
		try {
			connection = getConnection();
			callableStatement = Helper.prepareCall(connection, getSchema(), Routines.PYPABSP_DEL.routine());
			callableStatement.setString(1, ufficio.getIdUfficio());			
			callableStatement.execute();
			ret=1;
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
	
	public Integer insert(Ufficio ufficio)	throws DaoException {
		CallableStatement callableStatement=null;
		Connection connection = null;
		int ret=0;
		try {
			connection = getConnection();
			callableStatement = Helper.prepareCall(connection, getSchema(), Routines.PYPABSP_INS.routine());
			callableStatement.setString(1, ufficio.getIdUfficio());
			callableStatement.setString(2, ufficio.getCodiceUfficio());
			callableStatement.setString(3, ufficio.getDescrizioneIT());
			callableStatement.setString(4, ufficio.getDescrizioneDE());
			callableStatement.execute();
			ret=1;
		} catch (SQLException e) {
			ret = e.getErrorCode();
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
			//
			//return ret;
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
		//inizio LP PG21XX04 Leak
		return ret;
		//fine LP PG21XX04 Leak
	}

	public UfficiPageList ufficiList(Ufficio ufficio, int rowsPerPage, int pageNumber, String OrderBy) throws DaoException {
		CallableStatement callableStatement = null;
		Connection connection = null;
		ResultSet data = null;
		PageInfo pageInfo = null;
		UfficiPageList ufficiPagelist = null;
		String selectXml = "";
		try {
			connection = getConnection();
			callableStatement = Helper.prepareCall(connection, getSchema(), Routines.PYPABSP_LST.routine());
			callableStatement.setString(1, (ufficio.getCodiceUfficio()==null || ufficio.getCodiceUfficio().equals("")) ? "" : ufficio.getCodiceUfficio());
			callableStatement.setString(2, ufficio.getDescrizioneIT());
			callableStatement.setString(3, ufficio.getDescrizioneDE());
			callableStatement.setString(4, OrderBy);
			callableStatement.setInt(5, rowsPerPage);
			callableStatement.setInt(6, pageNumber);
			callableStatement.registerOutParameter(7, Types.INTEGER);
			callableStatement.registerOutParameter(8, Types.INTEGER);
			callableStatement.registerOutParameter(9, Types.INTEGER);
			callableStatement.registerOutParameter(10, Types.SMALLINT);
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
				selectXml = getWebRowSetXml();
			}
			ufficiPagelist = new UfficiPageList(pageInfo, "00", "", selectXml);
		} catch (SQLException e) {
			throw new DaoException(e);
		} catch (IllegalArgumentException e) {
			throw new DaoException(e);
		} catch (HelperException e) {
			throw new DaoException(e);
		} finally {
			//inizio LP PG21XX04 Leak
			//	DAOHelper.closeIgnoringException(callableStatement);
			//	DAOHelper.closeIgnoringException(connection);
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
		return ufficiPagelist;
	}

}
