package com.seda.payer.core.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import com.seda.data.helper.HelperException;
import com.seda.payer.commons.bean.TypeRequest;
import com.seda.payer.core.bean.Company;
import com.seda.payer.core.exception.DaoException;
import com.seda.payer.core.handler.BaseDaoHandler;
import com.seda.payer.core.messages.Messages;
/**
 * 
 * @author mmontisano
 *
 */
public class CompanyDao extends BaseDaoHandler {
	
	public CompanyDao(Connection connection, String schema) {
		super(connection, schema);
	}

	public Company doDetail(String companyCode) throws DaoException {
		//inizio LP PG21XX04 Leak
		CallableStatement callableStatement = null;
		ResultSet data = null;;
		//fine LP PG21XX04 Leak
		try	{
			//inizio LP PG21XX04 Leak
			//CallableStatement callableStatement = prepareCall(Routines.COMPANY_DODETAIL.routine());
			callableStatement = prepareCall(Routines.COMPANY_DODETAIL.routine());
			//fine LP PG21XX04 Leak
			callableStatement.setString(1, companyCode);
			if (callableStatement.execute()) {
				//inizio LP PG21XX04 Leak
				//ResultSet data = callableStatement.getResultSet();
				data = callableStatement.getResultSet();
				//fine LP PG21XX04 Leak
				if (data.next())
					return new Company(data);
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

	public void doRowSets(String companyCode, String companyDescription) throws DaoException {
		rowSets(companyCode, companyDescription, 0, 0);
	}

	public void doRowSets(String companyCode, String companyDescription, int rowsPerPage, int pageNumber) throws DaoException {

			if (rowsPerPage <= 0)
				throw new IllegalArgumentException(Messages.INVALID_PARAMETER.format("rowsPerPage"));

			if (pageNumber <= 0)
				throw new IllegalArgumentException(Messages.INVALID_PARAMETER.format("pageNumber"));
 
			rowSets(companyCode, companyDescription, rowsPerPage, pageNumber);

	}

	private void rowSets(String companyCode, String companyDescription, int rowsPerPage, int pageNumber) throws DaoException {
		//inizio LP PG21XX04 Leak
		CallableStatement callableStatement = null;
		//fine LP PG21XX04 Leak
		try	{
			//inizio LP PG21XX04 Leak
			//CallableStatement callableStatement = prepareCall(Routines.COMPANY_DOLIST.routine());
			callableStatement = prepareCall(Routines.COMPANY_DOLIST.routine());
			//fine LP PG21XX04 Leak
			callableStatement.setString(1, companyCode);
			callableStatement.setString(2, companyDescription);
			callableStatement.setString(3, "");
			callableStatement.setInt(4, rowsPerPage);
			callableStatement.setInt(5, pageNumber);
			/* we register row start */
			callableStatement.registerOutParameter(6, Types.INTEGER);
			/* we register row end */
			callableStatement.registerOutParameter(7, Types.INTEGER);
			/* we register total rows */
			callableStatement.registerOutParameter(8, Types.INTEGER);
			/* we register total pages */
			callableStatement.registerOutParameter(9, Types.SMALLINT);
			/* we execute procedure */
			if (callableStatement.execute()) {
				this.loadWebRowSets(callableStatement);
				/* we register page info */
				registerPageInfo(rowsPerPage, pageNumber, callableStatement.getInt(6), callableStatement.getInt(7), 
								 callableStatement.getInt(8), callableStatement.getInt(9));
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

	//inizio LP 20240821 - Versione dei metodi per essere usati in autocommit == true o false 
	public void doSave(Company company, String codOp) throws DaoException {
		doSaveTail(company,  codOp, true);
	}

	public void doSaveAutocommitFalse(Company company, String codOp) throws DaoException {
		doSaveTail(company,  codOp, false);
	}
		
	private void doSaveTail(Company company, String codOp, boolean bFlagUpdateAutocommit) throws DaoException {
	//fine LP 20240821 
		CallableStatement callableStatement=null;
		try	{
			if (company.getCompanyCode() == null || company.getCompanyCode().length() == 0)
				throw new IllegalArgumentException(Messages.INVALID_PARAMETER.format("company.companyCode"));

			Company data = doDetail(company.getCompanyCode());
			if ((data != null) && codOp!=null && codOp.compareTo(TypeRequest.ADD_SCOPE.scope())==0) throw new IllegalArgumentException(Messages.INVALID_PARAMETER.format("company.saveadd.error"));
			 
			if (data != null)	
			    callableStatement = prepareCall(Routines.COMPANY_DOUPDATE.routine(), bFlagUpdateAutocommit); //LP 20240821
			else callableStatement = prepareCall(Routines.COMPANY_DOINSERT.routine(), bFlagUpdateAutocommit); //LP 20240821
			
			company.save(callableStatement);
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

	public void doDelete(Company company) throws DaoException {
		//inizio LP PG21XX04 Leak
		CallableStatement callableStatement = null;
		//fine LP PG21XX04 Leak
		try	{
			//inizio LP PG21XX04 Leak
			//CallableStatement callableStatement = prepareCall(Routines.COMPANY_DODELETE.routine());
			callableStatement = prepareCall(Routines.COMPANY_DODELETE.routine());
			//fine LP PG21XX04 Leak
			if (company.getCompanyCode() == null || company.getCompanyCode().length() == 0)
				throw new IllegalArgumentException(Messages.INVALID_PARAMETER.format("company.companyCode"));
	
			callableStatement.setString(1, company.getCompanyCode());
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
}