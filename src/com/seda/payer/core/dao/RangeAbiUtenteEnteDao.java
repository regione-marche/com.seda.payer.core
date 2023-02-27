package com.seda.payer.core.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

import com.seda.payer.commons.bean.TypeRequest;
import com.seda.payer.core.bean.RangeAbiUtenteEnte;
import com.seda.payer.core.exception.DaoException;
import com.seda.payer.core.handler.BaseDaoHandler;
import com.seda.payer.core.messages.Messages;
import com.seda.data.helper.HelperException;
/**
 * 
 * @author mmontisano
 *
 */
public class RangeAbiUtenteEnteDao extends BaseDaoHandler {
	
	public RangeAbiUtenteEnteDao(Connection connection, String schema) {
		super(connection, schema);
	}

	public RangeAbiUtenteEnte doDetail(String chiaveRange) throws DaoException {
		//inizio LP PG21XX04 Leak
		CallableStatement callableStatement = null;
		ResultSet data = null;
		//fine LP PG21XX04 Leak
		try	{
			//inizio LP PG21XX04 Leak
			//CallableStatement callableStatement = prepareCall(Routines.RNC_DODETAIL.routine());
			callableStatement = prepareCall(Routines.RNC_DODETAIL.routine());
			//fine LP PG21XX04 Leak
			callableStatement.setString(1, chiaveRange);	
			if (callableStatement.execute()) {
				//inizio LP PG21XX04 Leak
				//ResultSet data = callableStatement.getResultSet();
				data = callableStatement.getResultSet();
				//fine LP PG21XX04 Leak
				if (data.next())
					return new RangeAbiUtenteEnte(data);
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
	public void doRowSets(RangeAbiUtenteEnte range,String strDescrEnte,String strDescrSocieta,String strDescrUtente) throws DaoException {
		rowSets(range,0,0,strDescrEnte,strDescrSocieta,strDescrUtente);
	}

	public void doRowSets(RangeAbiUtenteEnte range, int rowsPerPage, int pageNumber,String strDescrEnte,String strDescrSocieta,String strDescrUtente) throws DaoException {
		
			if (rowsPerPage <= 0)
				throw new IllegalArgumentException(Messages.INVALID_PARAMETER.format("rowsPerPage"));

			if (pageNumber <= 0)
				throw new IllegalArgumentException(Messages.INVALID_PARAMETER.format("pageNumber"));
 
			rowSets(range, rowsPerPage, pageNumber,strDescrEnte,strDescrSocieta,strDescrUtente);

	}
	
	public void rowSets(RangeAbiUtenteEnte range, int rowsPerPage, int pageNumber,String strDescrEnte,String strDescrSocieta,String strDescrUtente) throws DaoException {
		//inizio LP PG21XX04 Leak
		CallableStatement callableStatement = null;
		//fine LP PG21XX04 Leak
		try	{
			//inizio LP PG21XX04 Leak
			//CallableStatement callableStatement = prepareCall(Routines.RNC_DOLIST.routine());
			callableStatement = prepareCall(Routines.RNC_DOLIST.routine());
			//fine LP PG21XX04 Leak
			callableStatement.setString(1, range.getRange().getChiaveRange());
			callableStatement.setString(2, range.getRange().getUser().getCompany().getCompanyCode());
			callableStatement.setString(3, range.getRange().getUser().getUserCode());
			callableStatement.setString(4, range.getEnte().getAnagEnte().getChiaveEnte());
			callableStatement.setString(5, range.getRange().getInizioRangeDa());
			callableStatement.setString(6, range.getRange().getFineRangeA());
			callableStatement.setString(7, range.getRange().getInizioRangePer());
			callableStatement.setString(8, strDescrEnte);
			callableStatement.setString(9, strDescrSocieta);
			callableStatement.setString(10, strDescrUtente);
			callableStatement.setString(11, "");
			callableStatement.setInt(12, rowsPerPage);
			callableStatement.setInt(13, pageNumber);
			/* we register row start */
			callableStatement.registerOutParameter(14, Types.INTEGER);
			/* we register row end */
			callableStatement.registerOutParameter(15, Types.INTEGER);
			/* we register total rows */
			callableStatement.registerOutParameter(16, Types.INTEGER);
			/* we register total pages */
			callableStatement.registerOutParameter(17, Types.SMALLINT);
			/* we execute procedure */
			if (callableStatement.execute()) {
				this.loadWebRowSets(callableStatement);
				/* we register page info */
				registerPageInfo(rowsPerPage, pageNumber, callableStatement.getInt(14), callableStatement.getInt(15), 
								 callableStatement.getInt(16), callableStatement.getInt(17));
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

	public void doSave(RangeAbiUtenteEnte range,String codOp) throws DaoException {
	    CallableStatement callableStatement=null;
		try	{
			
			if ((range.getRange() == null || range.getRange().getChiaveRange() == null || range.getRange().getChiaveRange().length() == 0) && codOp.compareTo(TypeRequest.EDIT_SCOPE.scope())==0)
				throw new IllegalArgumentException(Messages.INVALID_PARAMETER.format("rangeAbiUtenteEnte.range.chiaveRange"));	
			
			RangeAbiUtenteEnte data = doDetail(range.getRange().getChiaveRange());
			if ((data != null) && codOp!=null && codOp.compareTo(TypeRequest.ADD_SCOPE.scope())==0) throw new IllegalArgumentException(Messages.INVALID_PARAMETER.format("rangeAbiUtenteEnte.saveadd.error"));
			if (data != null) {
				callableStatement = prepareCall(Routines.RNC_DOUPDATE.routine());
				range.update(callableStatement);
			}
			else {
				callableStatement = prepareCall(Routines.RNC_DOINSERT.routine());
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

	public void doDelete(RangeAbiUtenteEnte range) throws DaoException {
		//inizio LP PG21XX04 Leak
		CallableStatement callableStatement = null;
		//fine LP PG21XX04 Leak
		try	{
			//inizio LP PG21XX04 Leak
			//CallableStatement callableStatement = prepareCall(Routines.RNC_DODELETE.routine());
			callableStatement = prepareCall(Routines.RNC_DODELETE.routine());
			//fine LP PG21XX04 Leak
			if (range.getRange() == null || range.getRange().getChiaveRange() == null || range.getRange().getChiaveRange().length() == 0)
				throw new IllegalArgumentException(Messages.INVALID_PARAMETER.format("rangeAbiUtenteEnte.range.chiaveRange"));
		
			callableStatement.setString(1, range.getRange().getChiaveRange());
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