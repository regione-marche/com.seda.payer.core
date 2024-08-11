package com.seda.payer.core.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

import com.seda.data.helper.HelperException;
import com.seda.payer.core.bean.TipologiaServizioAPPIO;
import com.seda.payer.core.bean.TipologiaServizioAPPIOList;
import com.seda.payer.core.exception.DaoException;
import com.seda.payer.core.handler.BaseDaoHandler;

public class TipologiaServizioAPPIODao extends BaseDaoHandler {

	public TipologiaServizioAPPIODao(Connection connection, String schema) {
		super(connection, schema);
	}

	public String insertTipologiaServizio(TipologiaServizioAPPIO tipologiaServizioAPPIO) throws DaoException {
		String codiceTipologia = "";
		CallableStatement cs = null;
		try {
			cs = prepareCall(Routines.TIO_DOINS.routine());
			int p = 1;
			cs.setString(p++, tipologiaServizioAPPIO.getCodiceTipologiaServizio());
			cs.setString(p++, tipologiaServizioAPPIO.getDescrizioneTipologiaServizio());
			cs.setString(p++, tipologiaServizioAPPIO.getCodiceOperatore());

			cs.registerOutParameter(p++, Types.INTEGER);
			cs.registerOutParameter(p++, Types.CHAR);
			cs.execute();
			codiceTipologia = cs.getString(5);
		} catch (SQLException e) {
			throw new DaoException(e);
		} catch (HelperException e) {
			throw new DaoException(e);
		} finally {
			if (cs != null) {
				try { cs.close(); } catch (SQLException e) { }
			}
		}
		return codiceTipologia;
	}

	public TipologiaServizioAPPIO detailTipologiaAPPIO(String codiceTipologiaServizioAPPIO) throws DaoException {
		TipologiaServizioAPPIO tipologiaServizioAPPIO = null;
		CallableStatement cs = null;
		try {
			cs = prepareCall(Routines.TIO_DODETAIL.routine());
			int p = 1;
			cs.setString(p++, codiceTipologiaServizioAPPIO);
//			cs.setString(p++, codiceUtente);
//			cs.setString(p++, codiceEnte);
//			cs.setString(p++, tipologiaServizio);
//			cs.setString(p++, impostaServizio);
			//inizio LP 20240811 - PGNTCORE-24
			//ResultSet rs = cs.executeQuery();
			if(cs.execute()) {
				ResultSet rs = cs.getResultSet();
				if(rs != null) {
			//fine LP 20240811 - PGNTCORE-24
					try {
						if (rs.next())
							tipologiaServizioAPPIO = new TipologiaServizioAPPIO(rs);
					} finally {
						if (rs!=null)
							try { rs.close(); } catch (SQLException e) { }
					}
			//inizio LP 20240811 - PGNTCORE-24
				}
			}
			//fine LP 20240811 - PGNTCORE-24
		} catch (SQLException e) {
			throw new DaoException(e);
		} catch (HelperException e) {
			throw new DaoException(e);
		} finally {
			if (cs != null) {
				try { cs.close(); } catch (SQLException e) { }
			}
		}
		return tipologiaServizioAPPIO;
	}

	public int updateTipologiaServizio(TipologiaServizioAPPIO tipologiaServizioAPPIO) throws DaoException {
		int numRighe = 0;
		CallableStatement cs = null;
		try {
			cs = prepareCall(Routines.TIO_DOUPD.routine());
			cs.setString(1, tipologiaServizioAPPIO.getCodiceTipologiaServizio());
			cs.setString(2, tipologiaServizioAPPIO.getDescrizioneTipologiaServizio());
			cs.setString(3, tipologiaServizioAPPIO.getCodiceOperatore());
			cs.registerOutParameter(4, Types.INTEGER);
			cs.execute();
			numRighe = cs.getInt(4);
		} catch (SQLException e) {
			throw new DaoException(e);
		} catch (HelperException e) {
			throw new DaoException(e);
		} finally {
			if (cs != null) {
				try { cs.close(); } catch (SQLException e) { }
			}
		}
		return numRighe;
	}

	public int deleteTipologiaServizioAPPIO(String codiceTipologiaServizioAPPIO) throws DaoException {
		int numRighe = 0;
		CallableStatement cs = null;
		try {
			cs = prepareCall(Routines.TIO_DODELETE.routine());
			cs.setString(1, codiceTipologiaServizioAPPIO);
			cs.registerOutParameter(2, Types.INTEGER);
			cs.execute();
			numRighe = cs.getInt(2);
		} catch (SQLException e) {
			throw new DaoException(e);
		} catch (HelperException e) {
			throw new DaoException(e);
		} finally {
			if (cs != null) {
				try { cs.close(); } catch (SQLException e) { }
			}
		}
		return numRighe;
	}

	public TipologiaServizioAPPIOList tipologiaServizioAPPIOList(TipologiaServizioAPPIO tipologiaServizioAPPIO, int pageNumber, int rowsPerPage, String orderBy) throws DaoException {
		TipologiaServizioAPPIOList tipologiaServizioAPPIOList = null;
		CallableStatement cs = null;
		ResultSet data = null;
		try {
			int p = 1;
			cs = prepareCall(Routines.TIO_DOLIST.routine());
			cs.setString(p++, tipologiaServizioAPPIO.getCodiceTipologiaServizio());
			cs.setString(p++, tipologiaServizioAPPIO.getDescrizioneTipologiaServizio());
			cs.setString(p++, orderBy);
			cs.setInt(p++, rowsPerPage);
			cs.setInt(p++, pageNumber);

			cs.registerOutParameter(p++, Types.INTEGER);
			cs.registerOutParameter(p++, Types.INTEGER);
			cs.registerOutParameter(p++, Types.INTEGER);
			cs.registerOutParameter(p++, Types.SMALLINT);

			if (cs.execute()) {
				tipologiaServizioAPPIOList = new TipologiaServizioAPPIOList();
				tipologiaServizioAPPIOList.setPageNumber(pageNumber);
				tipologiaServizioAPPIOList.setRowsPerPage(rowsPerPage);
				tipologiaServizioAPPIOList.setFirstRow(cs.getInt(6));
				tipologiaServizioAPPIOList.setLastRow(cs.getInt(7));
				tipologiaServizioAPPIOList.setNumRows(cs.getInt(8));
				tipologiaServizioAPPIOList.setNumPages(cs.getInt(9));
				data = cs.getResultSet();
				loadWebRowSet(data);
				tipologiaServizioAPPIOList.setTipologiaServizioAPPIOListXml(getWebRowSetXml());
			}
		} catch (SQLException e) {
			throw new DaoException(e);
		} catch (HelperException e) {
			throw new DaoException(e);
		} finally {
			if (data != null) {
				try { data.close(); } catch (SQLException e) { }
			}
			if (cs != null) {
				try { cs.close(); } catch (SQLException e) { }
			}
		}
		return tipologiaServizioAPPIOList;
	}
	
//	public void rowSets(TipologiaServizioAPPIO tipologiaServizioAPPIO, int rowsPerPage, int pageNumber) throws DaoException {
//		CallableStatement callableStatement = null;
//		try	{
//		
//			callableStatement = prepareCall(Routines.TIO_DOLIST.routine());
//			callableStatement.setString(1, tipologiaServizioAPPIO.getCodiceTipologiaServizio());
//			callableStatement.setString(2, tipologiaServizioAPPIO.getDescrizioneTipologiaServizio());
//			callableStatement.setInt(3, rowsPerPage);
//			callableStatement.setInt(4, pageNumber);
//			/* we register row start */
//			callableStatement.registerOutParameter(5, Types.INTEGER);
//			/* we register row end */
//			callableStatement.registerOutParameter(6, Types.INTEGER);
//			/* we register total rows */
//			callableStatement.registerOutParameter(7, Types.INTEGER);
//			/* we register total pages */
//			callableStatement.registerOutParameter(8, Types.SMALLINT);
//			/* we execute procedure */
//			if (callableStatement.execute()) {
//				this.loadWebRowSets(callableStatement);
//				/* we register page info */
//				registerPageInfo(rowsPerPage, pageNumber, callableStatement.getInt(5), callableStatement.getInt(6), 
//								 callableStatement.getInt(7), callableStatement.getInt(8));
//			}
//		} catch (SQLException x) {
//			throw new DaoException(x);
//		} catch (IllegalArgumentException x) {
//			throw new DaoException(x);
//		} catch (HelperException x) {
//			throw new DaoException(x);
//		}
//		finally {
//			if (callableStatement != null) {
//				try {
//					callableStatement.close();
//				} catch (SQLException e) {
//					e.printStackTrace();
//				}
//			}
//		}
//	}
	
}