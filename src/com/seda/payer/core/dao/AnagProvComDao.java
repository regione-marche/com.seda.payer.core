package com.seda.payer.core.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

import com.seda.payer.commons.bean.TypeRequest;
import com.seda.payer.core.bean.AnagProvCom;
import com.seda.payer.core.exception.DaoException;
import com.seda.payer.core.handler.BaseDaoHandler;
import com.seda.payer.core.messages.Messages;
import com.seda.data.helper.HelperException;
/**
 * 
 * @author mmontisano
 *
 */
public class AnagProvComDao extends BaseDaoHandler {

	//inizio LP 20240909 - PGNTBOLDER-1
	private CallableStatement callableStatementDetail = null;  
	//fine LP 20240909 - PGNTBOLDER-1

	public AnagProvComDao(Connection connection, String schema) {
		super(connection, schema);
	}

	public AnagProvCom doDetail(String codiceBelfiore) throws DaoException {
	//inizio LP 20240909 - PGNTBOLDER-1
		return doDetailTail2(true, codiceBelfiore, true);
	}

	public AnagProvCom doDetailBatch(boolean bFlagUpdateAutocomit, String codiceBelfiore) throws DaoException {
		return doDetailTail2(bFlagUpdateAutocomit, codiceBelfiore, false);
	}	

	public AnagProvCom doDetailTail(boolean bFlagUpdateAutocomit, String codiceBelfiore) throws DaoException {
		return doDetailTail2(bFlagUpdateAutocomit, codiceBelfiore, true);
	}

	private AnagProvCom doDetailTail2(boolean bFlagUpdateAutocomit, String codiceBelfiore, boolean bCloseStat) throws DaoException {
	//fine LP 20240909 - PGNTBOLDER-1
		//inizio LP PG21XX04 Leak
		CallableStatement callableStatement = null;
		ResultSet data = null;
		//fine LP PG21XX04 Leak
		try	{
			//inizio LP PG21XX04 Leak
			//CallableStatement callableStatement = prepareCall(Routines.APC_DODETAIL.routine());
			//inizio LP 20240909 - PGNTBOLDER-1
			//callableStatement = prepareCall(Routines.APC_DODETAIL.routine());
			if(callableStatementDetail == null) {
				callableStatementDetail = prepareCall(bFlagUpdateAutocomit, Routines.APC_DODETAIL.routine());
				callableStatement = callableStatementDetail; 
			}
			//fine LP 20240909 - PGNTBOLDER-1
			//fine LP PG21XX04 Leak
			callableStatement.setString(1,codiceBelfiore);
			if (callableStatement.execute()) {
				//inizio LP PG21XX04 Leak
				//ResultSet data = callableStatement.getResultSet();
				data = callableStatement.getResultSet();
				//fine LP PG21XX04 Leak
				if (data.next())
					return new AnagProvCom(data);
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
			if(data != null) {
				try {
					data.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			//inizio LP 20240912 - PGNTBOLDER-1
			if(bCloseStat) {
			//fine LP 20240909 - PGNTBOLDER-1
				if(callableStatement != null) {
					try {
						callableStatement.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
				//inizio LP 20240912 - PGNTBOLDER-1
				callableStatement = null;
				callableStatementDetail = null;
				//fine LP 20240909 - PGNTBOLDER-1
			}
		}
		//fine LP PG21XX04 Leak
	}
	
	public void doRowSets(AnagProvCom anag) throws DaoException {
		rowSets(anag, 0, 0);
	}

	public void doRowSets(AnagProvCom anag, int rowsPerPage, int pageNumber) throws DaoException {

			if (rowsPerPage <= 0)
				throw new IllegalArgumentException(Messages.INVALID_PARAMETER.format("rowsPerPage"));

			if (pageNumber <= 0)
				throw new IllegalArgumentException(Messages.INVALID_PARAMETER.format("pageNumber"));
 
			rowSets(anag, rowsPerPage, pageNumber);

	}
	
	public void rowSets(AnagProvCom anag, int rowsPerPage, int pageNumber) throws DaoException {
		//inizio LP PG21XX04 Leak
		CallableStatement callableStatement = null;
		//fine LP PG21XX04 Leak
		try	{
			//inizio LP PG21XX04 Leak
			//CallableStatement callableStatement = prepareCall(Routines.APC_DOLIST.routine());
			callableStatement = prepareCall(Routines.APC_DOLIST.routine());
			//fine LP PG21XX04 Leak
			//callableStatement.setString(1, anag.getCompany().getCompanyCode());
			callableStatement.setString(1, anag.getCodiceBelfiore());
			callableStatement.setString(2, anag.getCodiceProvincia());
			callableStatement.setString(3, anag.getCodiceComune());
			callableStatement.setString(4, "");
			callableStatement.setInt(5, rowsPerPage);
			callableStatement.setInt(6, pageNumber);
			/* we register row start */
			callableStatement.registerOutParameter(7, Types.INTEGER);
			/* we register row end */
			callableStatement.registerOutParameter(8, Types.INTEGER);
			/* we register total rows */
			callableStatement.registerOutParameter(9, Types.INTEGER);
			/* we register total pages */
			callableStatement.registerOutParameter(10, Types.SMALLINT);
			/* we execute procedure */
			if (callableStatement.execute()) {
				this.loadWebRowSets(callableStatement);
				/* we register page info */
				registerPageInfo(rowsPerPage, pageNumber, callableStatement.getInt(7), callableStatement.getInt(8), 
								 callableStatement.getInt(9), callableStatement.getInt(10));
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
			if(callableStatement != null) {
				try {
					callableStatement.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		//fine LP PG21XX04 Leak
	}

	//inizio LP 20240909 - PGNTBOLDER-1
	public void doSave(AnagProvCom anag, String codOp) throws DaoException {
		doSaveTail(true, anag, codOp);
	}

	public void doSaveTail(boolean bFlagUpdateAutocomit, AnagProvCom anag,String codOp) throws DaoException {
	//fine LP 20240909 - PGNTBOLDER-1
		CallableStatement callableStatement=null;
		try	{
			if (anag.getCodiceBelfiore() == null || anag.getCodiceBelfiore().length() == 0)
				throw new IllegalArgumentException(Messages.INVALID_PARAMETER.format("anagProvCom.codiceBelfiore"));

			/*if (anag.getCompany() == null || anag.getCompany().getCompanyCode() == null || anag.getCompany().getCompanyCode().length() == 0)
				throw new IllegalArgumentException(Messages.INVALID_PARAMETER.format("anagProvCom.company.companyCode"));*/
			//inizio LP 20240909 - PGNTBOLDER-1
			//AnagProvCom data = doDetail(anag.getCodiceBelfiore());
			AnagProvCom data = doDetailTail(bFlagUpdateAutocomit, anag.getCodiceBelfiore());
			//fine LP 20240909 - PGNTBOLDER-1
			if ((data != null) && codOp!=null && codOp.compareTo(TypeRequest.ADD_SCOPE.scope())==0) throw new IllegalArgumentException(Messages.INVALID_PARAMETER.format("anagProvCom.saveadd.error"));
			if (data != null) {
				//inizio LP 20240909 - PGNTBOLDER-1
				//callableStatement = prepareCall(Routines.APC_DOUPDATE.routine());
				callableStatement = prepareCall(bFlagUpdateAutocomit, Routines.APC_DOUPDATE.routine());
				//fine LP 20240909 - PGNTBOLDER-1
			} else {
				//inizio LP 20240909 - PGNTBOLDER-1
				//callableStatement = prepareCall(Routines.APC_DOINSERT.routine());
				callableStatement = prepareCall(bFlagUpdateAutocomit, Routines.APC_DOINSERT.routine());
				//fine LP 20240909 - PGNTBOLDER-1
			}
			anag.save(callableStatement);
			callableStatement.execute();
			//commit();
		} catch (SQLException x) {
			throw new DaoException(x);
		} catch (IllegalArgumentException x) {
			throw new DaoException(x);
		} catch (HelperException x) {
			throw new DaoException(x);
		}
		//inizio LP PG21XX04 Leak
		finally {
			if(callableStatement != null) {
				try {
					callableStatement.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		//fine LP PG21XX04 Leak
	}

	//inizio LP 20240909 - PGNTBOLDER-1
	public void doDelete(AnagProvCom anag) throws DaoException {
		doDeleteTail(true, anag);
	}

	public void doDeleteTail(boolean bFlagUpdateAutocomit, AnagProvCom anag) throws DaoException {
	//fine LP 20240909 - PGNTBOLDER-1
		//inizio LP PG21XX04 Leak
		CallableStatement callableStatement = null;
		//fine LP PG21XX04 Leak
		try	{
			//inizio LP PG21XX04 Leak
			//CallableStatement callableStatement = prepareCall(Routines.APC_DODELETE.routine());
			//inizio LP 20240909 - PGNTBOLDER-1
			//callableStatement = prepareCall(Routines.APC_DODELETE.routine());
			callableStatement = prepareCall(bFlagUpdateAutocomit, Routines.APC_DODELETE.routine());
			//fine LP 20240909 - PGNTBOLDER-1
			//fine LP PG21XX04 Leak
			if (anag.getCodiceBelfiore() == null || anag.getCodiceBelfiore().length() == 0)
				throw new IllegalArgumentException(Messages.INVALID_PARAMETER.format("anagProvCom.codiceBelfiore"));

			/*if (anag.getCompany() == null || anag.getCompany().getCompanyCode() == null || anag.getCompany().getCompanyCode().length() == 0)
				throw new IllegalArgumentException(Messages.INVALID_PARAMETER.format("anagProvCom.company.companyCode"));
			*/
			callableStatement.setString(1, anag.getCodiceBelfiore());
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
			if(callableStatement != null) {
				try {
					callableStatement.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		//fine LP PG21XX04 Leak
	}

    //inizio LP 20240912 - PAGONET-604
    public void closeCallableStatementS()  {
	    if(callableStatementDetail != null) {
			try {
				callableStatementDetail.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			callableStatementDetail = null;
	    	
	    }
    }
    //fine LP 20240912 - PAGONET-604

}