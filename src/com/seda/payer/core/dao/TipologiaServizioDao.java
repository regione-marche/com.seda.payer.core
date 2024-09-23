package com.seda.payer.core.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

import com.seda.payer.commons.bean.TypeRequest;
import com.seda.payer.core.bean.TipologiaServizio;
import com.seda.payer.core.exception.DaoException;
import com.seda.payer.core.handler.BaseDaoHandler;
import com.seda.payer.core.messages.Messages;
import com.seda.data.helper.HelperException;
/**
 * 
 * @author mmontisano
 *
 */
public class TipologiaServizioDao extends BaseDaoHandler {
	
	public TipologiaServizioDao(Connection connection, String schema) {
		super(connection, schema);
	}

	//inizio LP 20240905 - PGNTCORE-24/PGNTPROR-5/PGNTBIMAIO-1
	public TipologiaServizio doDetail(String companyCode, String codiceTipologiaServizio) throws DaoException {
		//inizio LP 20240921 - PGNTCORE-24/PGNTPROR-5/PGNTBIMAIO-1/PGNTECCSV-10
		//return doDetailTail(true, companyCode, codiceTipologiaServizio);
		return doDetailBatch(true, true, companyCode, codiceTipologiaServizio);
		//fine LP 20240921 - PGNTCORE-24/PGNTPROR-5/PGNTBIMAIO-1/PGNTECCSV-10
	}
		
	//inizio LP 20240921 - PGNTCORE-24/PGNTPROR-5/PGNTBIMAIO-1/PGNTECCSV-10
	//public TipologiaServizio doDetailTail(boolean bFlagUpdateAutocommit, String companyCode, String codiceTipologiaServizio) throws DaoException {
	public TipologiaServizio doDetailBatch(boolean bFlagUpdateAutocommit, boolean bCloseStat, String companyCode, String codiceTipologiaServizio) throws DaoException {
	//fine LP 20240921 - PGNTCORE-24/PGNTPROR-5/PGNTBIMAIO-1/PGNTECCSV-10
	//fine LP 20240905 - PGNTCORE-24/PGNTPROR-5/PGNTBIMAIO-1
		//inizio LP PG21XX04 Leak
		CallableStatement callableStatement = null;
		ResultSet data = null;
		//fine LP PG21XX04 Leak
		try	{
			//inizio LP PG21XX04 Leak
			//CallableStatement callableStatement = prepareCall(Routines.TSE_DODETAIL.routine());
			//inizio LP 20240905 - PGNTCORE-24/PGNTPROR-5/PGNTBIMAIO-1
			//callableStatement = prepareCall(Routines.TSE_DODETAIL.routine());
			//inizio LP 20240921 - PGNTCORE-24/PGNTPROR-5/PGNTBIMAIO-1
			callableStatement = prepareCall(bFlagUpdateAutocommit, Routines.TSE_DODETAIL.routine());
			//fine LP 20240921 - PGNTCORE-24/PGNTPROR-5/PGNTBIMAIO-1
			//fine LP 20240905 - PGNTCORE-24/PGNTPROR-5/PGNTBIMAIO-1
			//fine LP PG21XX04 Leak
			callableStatement.setString(1, companyCode);
			callableStatement.setString(2, codiceTipologiaServizio);
			if (callableStatement.execute()) {
				//inizio LP PG21XX04 Leak
				//ResultSet data = callableStatement.getResultSet();
				data = callableStatement.getResultSet();
				//fine LP PG21XX04 Leak
				if (data.next())
					return new TipologiaServizio(data);
			}
			return null;
		} catch (SQLException x) {
			throw new DaoException(x);
		} catch (IllegalArgumentException x) {
			throw new DaoException(x);
		} catch (HelperException x) {
			throw new DaoException(x);
		}
		//inizio LP PG21XX04 Leak
		finally {
			if (data != null) {
				try {
					data.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			//inizio LP 20240921 - PGNTCORE-24/PGNTPROR-5/PGNTBIMAIO-1/PGNTECCSV-10
			if(bCloseStat) {
			//fine LP 20240921 - PGNTCORE-24/PGNTPROR-5/PGNTBIMAIO-1/PGNTECCSV-10
				if (callableStatement != null) {
					try {
						callableStatement.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
			//inizio LP 20240921 - PGNTCORE-24/PGNTPROR-5/PGNTBIMAIO-1/PGNTECCSV-10
				callableStatement = null;
			}
			//fine LP 20240921 - PGNTCORE-24/PGNTPROR-5/PGNTBIMAIO-1/PGNTECCSV-10
		}
		//fine LP PG21XX04 Leak
	}

	public void doRowSets(TipologiaServizio tipologiaServizio,String strDescrSocieta) throws DaoException {
		rowSets(tipologiaServizio, 0, 0, strDescrSocieta);
	}

	public void doRowSets(TipologiaServizio tipologiaServizio, int rowsPerPage, int pageNumber,String strDescrSocieta) throws DaoException {
		if (rowsPerPage <= 0)
			throw new IllegalArgumentException(Messages.INVALID_PARAMETER.format("rowsPerPage"));
		if (pageNumber <= 0)
			throw new IllegalArgumentException(Messages.INVALID_PARAMETER.format("pageNumber"));
		rowSets(tipologiaServizio, rowsPerPage, pageNumber, strDescrSocieta);
	}

	public void rowSets(TipologiaServizio tipo, int rowsPerPage, int pageNumber,String strDescrSocieta) throws DaoException {
		//inizio LP PG21XX04 Leak
		CallableStatement callableStatement = null;;
		//fine LP PG21XX04 Leak
		try	{
			//inizio LP PG21XX04 Leak
			//CallableStatement callableStatement = prepareCall(Routines.TSE_DOLIST.routine());
			callableStatement = prepareCall(Routines.TSE_DOLIST.routine());
			//fine LP PG21XX04 Leak
			callableStatement.setString(1, tipo.getCompany().getCompanyCode());
			callableStatement.setString(2, tipo.getCodiceTipologiaServizio());
			callableStatement.setString(3, tipo.getDescrizioneTipologiaServizio());
			callableStatement.setString(4, strDescrSocieta);
			callableStatement.setString(5, "");
			callableStatement.setInt(6, rowsPerPage);
			callableStatement.setInt(7, pageNumber);
			/* we register row start */
			callableStatement.registerOutParameter(8, Types.INTEGER);
			/* we register row end */
			callableStatement.registerOutParameter(9, Types.INTEGER);
			/* we register total rows */
			callableStatement.registerOutParameter(10, Types.INTEGER);
			/* we register total pages */
			callableStatement.registerOutParameter(11, Types.SMALLINT);
			/* we execute procedure */
			if (callableStatement.execute()) {
				this.loadWebRowSets(callableStatement);
				/* we register page info */
				registerPageInfo(rowsPerPage, pageNumber, callableStatement.getInt(8), callableStatement.getInt(9), 
								 callableStatement.getInt(10), callableStatement.getInt(11));
			}
		} catch (SQLException x) {
			throw new DaoException(x);
		} catch (IllegalArgumentException x) {
			throw new DaoException(x);
		} catch (HelperException x) {
			throw new DaoException(x);
		}
		//inizio LP PG21XX04 Leak
		finally {
			if (callableStatement != null) {
				try {
					callableStatement.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		//fine LP PG21XX04 Leak
	}

	//inizio LP 20240909 - PGNTCORE-24/PGNTBOLDER-1
	public void doSave(TipologiaServizio tipo,String codOp) throws DaoException {
		//inizio LP 20240921 - PGNTCORE-24/PGNTBOLDER-1/PGNTECCSV-10
		//doSaveTail(true, tipo, codOp);
		doSaveBatch(true, true, tipo, codOp);
		//fine LP 20240921 - PGNTCORE-24/PGNTBOLDER-1/PGNTECCSV-10
	}

	//inizio LP 20240921 - PGNTCORE-24/PGNTBOLDER-1/PGNTECCSV-10
	//public void doSaveTail(boolean bFlagUpdateAutocommit, TipologiaServizio tipo,String codOp) throws DaoException {
	public void doSaveBatch(boolean bFlagUpdateAutocommit, boolean bCloseStat, TipologiaServizio tipo,String codOp) throws DaoException {
	//fine LP 20240921 - PGNTCORE-24/PGNTBOLDER-1/PGNTECCSV-10
	//fine LP 20240909 - PGNTCORE-24/PGNTBOLDER-1
		CallableStatement callableStatement = null;
		try	{
			if (tipo.getCodiceTipologiaServizio() == null || tipo.getCodiceTipologiaServizio().length() == 0)
				throw new IllegalArgumentException(Messages.INVALID_PARAMETER.format("tipologiaServizio.codiceTipologiaServizio"));
			if (tipo.getCompany() == null || tipo.getCompany().getCompanyCode() == null || tipo.getCompany().getCompanyCode().length() == 0)
				throw new IllegalArgumentException(Messages.INVALID_PARAMETER.format("tipologiaServizio.company.companyCode"));
			//inizio LP 20240921 - PGNTCORE-24/PGNTBOLDER-1/PGNTECCSV-10
			//TipologiaServizio data = doDetail(tipo.getCompany().getCompanyCode(), tipo.getCodiceTipologiaServizio());
			TipologiaServizio data = doDetailBatch(bFlagUpdateAutocommit, bCloseStat, tipo.getCompany().getCompanyCode(), tipo.getCodiceTipologiaServizio());
			//fine LP 20240921 - PGNTCORE-24/PGNTBOLDER-1/PGNTECCSV-10
			if ((data != null) && codOp != null && codOp.compareTo(TypeRequest.ADD_SCOPE.scope())==0) throw new IllegalArgumentException(Messages.INVALID_PARAMETER.format("tipologiaServizio.saveadd.error"));
			if (data != null) {
				//inizio LP 20240921 - PGNTCORE-24/PGNTBOLDER-1/PGNTECCSV-10
				//callableStatement = prepareCall(Routines.TSE_DOUPDATE.routine());
				callableStatement = prepareCall(bFlagUpdateAutocommit, Routines.TSE_DOUPDATE.routine()); 
				//fine LP 20240921 - PGNTCORE-24/PGNTBOLDER-1/PGNTECCSV-10
			} else {
				//inizio LP 20240921 - PGNTCORE-24/PGNTBOLDER-1/PGNTECCSV-10
				//callableStatement = prepareCall(Routines.TSE_DOINSERT.routine());
				callableStatement = prepareCall(bFlagUpdateAutocommit, Routines.TSE_DOINSERT.routine()); 
				//fine LP 20240921 - PGNTCORE-24/PGNTBOLDER-1/PGNTECCSV-10
			}
			tipo.save(callableStatement);
			callableStatement.execute();
			//commit();
		} catch (SQLException x) {
			throw new DaoException(x);
		} catch (IllegalArgumentException x) {
			throw new DaoException(x);
		} catch (HelperException x) {
			throw new DaoException(x);
		//inizio LP PG21XX04 Leak
		} finally {
			//inizio LP 20240921 - PGNTCORE-24/PGNTBOLDER-1/PGNTECCSV-10
			if(bCloseStat) {
			//fine LP 20240921 - PGNTCORE-24/PGNTBOLDER-1/PGNTECCSV-10
				if (callableStatement != null) {
					try {
						callableStatement.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
			//inizio LP 20240921 - PGNTCORE-24/PPGNTECCSV-10
				callableStatement = null;
			}
			//fine LP 20240921 - PGNTCORE-24/PGNTPROR-5/PGNTBIMAIO-1/PGNTECCSV-10
			//fine LP PG21XX04 Leak
		}
	}

	//inizio LP 20240909 - PGNTCORE-24/PGNTBOLDER-1
	public void doDelete(TipologiaServizio tipo) throws DaoException {
		//inizio LP 20240921 - PGNTCORE-24/PGNTBOLDER-1/PGNTECCSV-10
		//doDeleteTail(true, tipo);
		doDeleteBatch(true, true, tipo);
		//fine LP 20240921 - PGNTCORE-24/PGNTBOLDER-1/PGNTECCSV-10
	}

	//inizio LP 20240921 - PGNTCORE-24/PGNTBOLDER-1/PGNTECCSV-10
	//public void doDeleteTail(boolean bFlagUpdateAutocommit, TipologiaServizio tipo) throws DaoException {
	public void doDeleteBatch(boolean bFlagUpdateAutocommit, boolean bCloseStat, TipologiaServizio tipo) throws DaoException {
	//fine LP 20240921 - PGNTCORE-24/PGNTBOLDER-1/PGNTECCSV-10
	//fine LP 20240909 - PGNTCORE-24/PGNTBOLDER-1
		//inizio LP PG21XX04 Leak
		CallableStatement callableStatement = null;;
		//fine LP PG21XX04 Leak
		try	{
			//inizio LP PG21XX04 Leak
			//CallableStatement callableStatement = prepareCall(Routines.TSE_DODELETE.routine());
			//inizio LP 20240921 - PGNTCORE-24/PGNTBOLDER-1/PGNTECCSV-10
			callableStatement = prepareCall(bFlagUpdateAutocommit, Routines.TSE_DODELETE.routine());
			//fine LP 20240921 - PGNTCORE-24/PGNTBOLDER-1/PGNTECCSV-10
			//fine LP PG21XX04 Leak
			if (tipo.getCodiceTipologiaServizio() == null || tipo.getCodiceTipologiaServizio().length() == 0)
				throw new IllegalArgumentException(Messages.INVALID_PARAMETER.format("tipo.codiceTipologiaServizio"));
			if (tipo.getCompany() == null || tipo.getCompany().getCompanyCode() == null || tipo.getCompany().getCompanyCode().length() == 0)
				throw new IllegalArgumentException(Messages.INVALID_PARAMETER.format("tipo.company.companyCode"));
			callableStatement.setString(1, tipo.getCompany().getCompanyCode());
			callableStatement.setString(2, tipo.getCodiceTipologiaServizio());
			callableStatement.execute();
			//commit();
		} catch (SQLException x) {
			throw new DaoException(x.getErrorCode(),x.getMessage(),x);
		} catch (IllegalArgumentException x) {
			throw new DaoException(Integer.parseInt(Messages.ILL_ARG_ERR_CODE.format()),x.getMessage(),x);
		} catch (HelperException x) {
			throw new DaoException(Integer.parseInt(Messages.HELPER_ERR_CODE.format()),x.getMessage(),x);
		}
		//inizio LP PG21XX04 Leak
		finally {
			//inizio LP 20240921 - PGNTCORE-24/PGNTBOLDER-1/PGNTECCSV-10
			if(bCloseStat) {
			//fine LP 20240921 - PGNTCORE-24/PGNTBOLDER-1/PGNTECCSV-10
				if (callableStatement != null) {
					try {
						callableStatement.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
			//inizio LP 20240921 - PGNTCORE-24/PGNTBOLDER-1/PGNTECCSV-10
				callableStatement = null;
			}
			//fine LP 20240921 - PGNTCORE-24/PGNTBOLDER-1/PGNTECCSV-10
		}
		//fine LP PG21XX04 Leak
	}
}