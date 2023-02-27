package com.seda.payer.core.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

import com.seda.payer.commons.bean.TypeRequest;
import com.seda.payer.core.bean.RangeAbiUtente;
import com.seda.payer.core.exception.DaoException;
import com.seda.payer.core.handler.BaseDaoHandler;
import com.seda.payer.core.messages.Messages;
import com.seda.data.helper.HelperException;
/**
 * 
 * @author mmontisano
 *
 */
public class RangeAbiUtenteDao extends BaseDaoHandler {
	
	public RangeAbiUtenteDao(Connection connection, String schema) {
		super(connection, schema);
	}

	public RangeAbiUtente doDetail(String chiaveRange) throws DaoException {
		//inizio LP PG21XX04 Leak
		CallableStatement callableStatement = null;
		ResultSet data = null;
		//fine LP PG21XX04 Leak
		try	{
			//inizio LP PG21XX04 Leak
			//CallableStatement callableStatement = prepareCall(Routines.RNU_DODETAIL.routine());
			callableStatement = prepareCall(Routines.RNU_DODETAIL.routine());
			//fine LP PG21XX04 Leak
			callableStatement.setString(1, chiaveRange);
			if (callableStatement.execute()) {
				//inizio LP PG21XX04 Leak
				//ResultSet data = callableStatement.getResultSet();
				data = callableStatement.getResultSet();
				//fine LP PG21XX04 Leak
				if (data.next())
					return new RangeAbiUtente(data);
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
		finally
		{
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

	public void doRowSets(RangeAbiUtente range,String strDescrSocieta,String strDescrUtente) throws DaoException {
		rowSets(range, 0, 0,strDescrSocieta,strDescrUtente);
	}

	public void doRowSets(RangeAbiUtente range, int rowsPerPage, int pageNumber,String strDescrSocieta,String strDescrUtente) throws DaoException {
		
			if (rowsPerPage <= 0)
				throw new IllegalArgumentException(Messages.INVALID_PARAMETER.format("rowsPerPage"));

			if (pageNumber <= 0)
				throw new IllegalArgumentException(Messages.INVALID_PARAMETER.format("pageNumber"));
 
			rowSets(range, rowsPerPage, pageNumber,strDescrSocieta,strDescrUtente);

	}
	public void rowSets(RangeAbiUtente range, int rowsPerPage, int pageNumber,String strDescrSocieta,String strDescrUtente) throws DaoException {
		//inizio LP PG21XX04 Leak
		CallableStatement callableStatement = null;
		//fine LP PG21XX04 Leak
		try	{
			//inizio LP PG21XX04 Leak
			//CallableStatement callableStatement = prepareCall(Routines.RNU_DOLIST.routine());
			callableStatement = prepareCall(Routines.RNU_DOLIST.routine());
			//fine LP PG21XX04 Leak
			callableStatement.setString(1, range.getChiaveRange());
			callableStatement.setString(2, range.getUser().getCompany().getCompanyCode());
			callableStatement.setString(3, range.getUser().getUserCode());
			callableStatement.setString(4, range.getInizioRangeDa());
			callableStatement.setString(5, range.getFineRangeA());
			callableStatement.setString(6, range.getInizioRangePer());
			callableStatement.setString(7, strDescrSocieta);
			callableStatement.setString(8, strDescrUtente);

			callableStatement.setString(9, "");
			callableStatement.setInt(10, rowsPerPage);
			callableStatement.setInt(11, pageNumber);
			/* we register row start */
			callableStatement.registerOutParameter(12, Types.INTEGER);
			/* we register row end */
			callableStatement.registerOutParameter(13, Types.INTEGER);
			/* we register total rows */
			callableStatement.registerOutParameter(14, Types.INTEGER);
			/* we register total pages */
			callableStatement.registerOutParameter(15, Types.SMALLINT);
			/* we execute procedure */
			if (callableStatement.execute()) {
				this.loadWebRowSets(callableStatement);
				/* we register page info */
				registerPageInfo(rowsPerPage, pageNumber, callableStatement.getInt(12), callableStatement.getInt(13), 
								 callableStatement.getInt(14), callableStatement.getInt(15));
			}
		} catch (SQLException x) {
			throw new DaoException(x);
		} catch (IllegalArgumentException x) {
			throw new DaoException(x);
		} catch (HelperException x) {
			throw new DaoException(x);
		}
		//inizio LP PG21XX04 Leak
		finally
		{
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

	public void doSave(RangeAbiUtente range,String codOp) throws DaoException {
	    CallableStatement callableStatement=null;
		try	{
			if ((range.getChiaveRange() == null || range.getChiaveRange().length() == 0) && codOp.compareTo(TypeRequest.EDIT_SCOPE.scope())==0)
				throw new IllegalArgumentException(Messages.INVALID_PARAMETER.format("rangeAbiUtente.chiaveRange"));
/*			if (range.getUser() == null || range.getUser().getUserCode() == null || range.getUser().getUserCode().length() == 0)
				throw new IllegalArgumentException(Messages.INVALID_PARAMETER.format("rangeAbiUtente.userCode"));			
			if (                            range.getUser().getCompany() == null || range.getUser().getCompany().getCompanyCode()== null ||  range.getUser().getCompany().getCompanyCode().length()==0)
				throw new IllegalArgumentException(Messages.INVALID_PARAMETER.format("rangeAbiUtente.companyCode"));  */			
			RangeAbiUtente data = doDetail(range.getChiaveRange());
			if ((data != null) && codOp!=null && codOp.compareTo(TypeRequest.ADD_SCOPE.scope())==0) throw new IllegalArgumentException(Messages.INVALID_PARAMETER.format("rangeAbiUtente.saveadd.error"));
			if (data != null) {
				callableStatement = prepareCall(Routines.RNU_DOUPDATE.routine());
				range.update(callableStatement);
			}
			else {
				callableStatement = prepareCall(Routines.RNU_DOINSERT.routine());
				range.save(callableStatement);
			}
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
		finally
		{
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

	public void doDelete(RangeAbiUtente range) throws DaoException {
		//inizio LP PG21XX04 Leak
		CallableStatement callableStatement = null;
		//fine LP PG21XX04 Leak
		try	{
			//inizio LP PG21XX04 Leak
			//CallableStatement callableStatement = prepareCall(Routines.RNU_DODELETE.routine());
			callableStatement = prepareCall(Routines.RNU_DODELETE.routine());
			//fine LP PG21XX04 Leak

			if (range.getChiaveRange() == null || range.getChiaveRange().length() == 0)
			throw new IllegalArgumentException(Messages.INVALID_PARAMETER.format("rangeAbiUtente.chiaveRange"));
			callableStatement.setString(1, range.getChiaveRange());
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
		finally
		{
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