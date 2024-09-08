package com.seda.payer.core.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import com.seda.payer.commons.bean.TypeRequest;
import com.seda.payer.core.bean.AnagEnte;
import com.seda.payer.core.exception.DaoException;
import com.seda.payer.core.handler.BaseDaoHandler;
import com.seda.payer.core.messages.Messages;
import com.seda.data.helper.HelperException;
/**
 * 
 * @author mmontisano
 *
 */
public class AnagEnteDao extends BaseDaoHandler {
	
	public AnagEnteDao(Connection connection, String schema) {
		super(connection, schema);
	}

	//inizio LP 20240905 - PGNTCORE-24/PGNTPROR-5/PGNTPROR-5/PGNTBIMAIO-1
	public AnagEnte doDetail(String chiaveEnte) throws DaoException {
		return doDetailTail(true, chiaveEnte);
	}

	public AnagEnte doDetailTail(boolean bFlagUpdateAutocommit, String chiaveEnte) throws DaoException {
	//fine LP 20240905 - PGNTCORE-24/PGNTPROR-5/PGNTPROR-5/PGNTBIMAIO-1
		//inizio LP PG21XX04 Leak
		CallableStatement callableStatement = null;
		ResultSet data = null;
		//fine LP PG21XX04 Leak
		try	{
			//inizio LP PG21XX04 Leak
			//CallableStatement callableStatement = prepareCall(Routines.ANE_DODETAIL.routine());
			//inizio LP 20240905 - PGNTCORE-24/PGNTPROR-5/PGNTPROR-5/PGNTBIMAIO-1
			//callableStatement = prepareCall(Routines.ANE_DODETAIL.routine());
			callableStatement = prepareCall(bFlagUpdateAutocommit, Routines.ANE_DODETAIL.routine());
			//fine LP 20240905 - PGNTCORE-24/PGNTPROR-5/PGNTPROR-5/PGNTBIMAIO-1
			//fine LP PG21XX04 Leak
			callableStatement.setString(1, chiaveEnte);
			if (callableStatement.execute()) {
				//inizio LP PG21XX04 Leak
				//ResultSet data = callableStatement.getResultSet();
				data = callableStatement.getResultSet();
				//fine LP PG21XX04 Leak
				if (data.next())
					return new AnagEnte(data);
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

	public void doRowSets(AnagEnte anagEnte) throws DaoException {
		rowSets(anagEnte, 0, 0);
	}

	public void doRowSets(AnagEnte anagEnte, int rowsPerPage, int pageNumber) throws DaoException {

			if (rowsPerPage <= 0)
				throw new IllegalArgumentException(Messages.INVALID_PARAMETER.format("rowsPerPage"));

			if (pageNumber <= 0)
				throw new IllegalArgumentException(Messages.INVALID_PARAMETER.format("pageNumber"));
 
			rowSets(anagEnte, rowsPerPage, pageNumber);

	}

	public void rowSets(AnagEnte anagEnte, int rowsPerPage, int pageNumber) throws DaoException {
		//inizio LP PG21XX04 Leak
		CallableStatement callableStatement = null;
		//fine LP PG21XX04 Leak
		try	{
			//inizio LP PG21XX04 Leak
			//CallableStatement callableStatement = prepareCall(Routines.ANE_DOLIST.routine());
			callableStatement = prepareCall(Routines.ANE_DOLIST.routine());
			//fine LP PG21XX04 Leak
			callableStatement.setString(1, anagEnte.getChiaveEnte());
			//callableStatement.setString(2, anagEnte.getAnagProvCom().getCompany().getCompanyCode());
			callableStatement.setString(2, anagEnte.getAnagProvCom().getCodiceBelfiore());
			callableStatement.setString(3, anagEnte.getCodiceEnte());
			callableStatement.setString(4, anagEnte.getDescrizioneEnte());
			callableStatement.setString(5, (anagEnte.getAnagProvCom() != null && anagEnte.getAnagProvCom().getSiglaProvincia() != null) ? anagEnte.getAnagProvCom().getSiglaProvincia() : "");
			callableStatement.setString(6, "");
			callableStatement.setInt(7, rowsPerPage);
			callableStatement.setInt(8, pageNumber);
			/* we register row start */
			callableStatement.registerOutParameter(9, Types.INTEGER);
			/* we register row end */
			callableStatement.registerOutParameter(10, Types.INTEGER);
			/* we register total rows */
			callableStatement.registerOutParameter(11, Types.INTEGER);
			/* we register total pages */
			callableStatement.registerOutParameter(12, Types.SMALLINT);
			/* we execute procedure */
			if (callableStatement.execute()) {
				this.loadWebRowSets(callableStatement);
				/* we register page info */
				registerPageInfo(rowsPerPage, pageNumber, callableStatement.getInt(9), callableStatement.getInt(10), 
								 callableStatement.getInt(11), callableStatement.getInt(12));
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

	public void doSave(AnagEnte anagEnte,String codOp) throws DaoException {
		CallableStatement callableStatement = null;
		try	{
			if ((anagEnte.getChiaveEnte() == null || anagEnte.getChiaveEnte().length() == 0) && codOp.compareTo(TypeRequest.EDIT_SCOPE.scope())==0)
				throw new IllegalArgumentException(Messages.INVALID_PARAMETER.format("anagEnte.chiaveRange"));
			if (anagEnte.getAnagProvCom() == null || anagEnte.getAnagProvCom().getCodiceBelfiore() == null || anagEnte.getAnagProvCom().getCodiceBelfiore().length() == 0)
				throw new IllegalArgumentException(Messages.INVALID_PARAMETER.format("anagEnte.codiceBelfiore"));			
	/*		if (                            anagEnte.getAnagProvCom().getCompany() == null || anagEnte.getAnagProvCom().getCompany().getCompanyCode()== null ||  anagEnte.getAnagProvCom().getCompany().getCompanyCode().length()==0)
				throw new IllegalArgumentException(Messages.INVALID_PARAMETER.format("anagEnte.companyCode")); */			
			AnagEnte data = doDetail(anagEnte.getChiaveEnte());
			if ((data != null) && codOp!=null && codOp.compareTo(TypeRequest.ADD_SCOPE.scope())==0) throw new IllegalArgumentException(Messages.INVALID_PARAMETER.format("anagEnte.saveadd.error"));
			if (data != null) {
				callableStatement = prepareCall(Routines.ANE_DOUPDATE.routine());
				anagEnte.update(callableStatement);
			}
			else {
				callableStatement = prepareCall(Routines.ANE_DOINSERT.routine());
				anagEnte.save(callableStatement);
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

	public void doDelete(AnagEnte anagEnte) throws DaoException {
		//inizio LP PG21XX04 Leak
		CallableStatement callableStatement = null;
		//fine LP PG21XX04 Leak
		try	{
			//inizio LP PG21XX04 Leak
			//CallableStatement callableStatement = prepareCall(Routines.ANE_DODELETE.routine());
			callableStatement = prepareCall(Routines.ANE_DODELETE.routine());
			//fine LP PG21XX04 Leak
			if (anagEnte.getChiaveEnte() == null || anagEnte.getChiaveEnte().length() == 0)
				throw new IllegalArgumentException(Messages.INVALID_PARAMETER.format("anagEnte.chiaveEnte"));

			callableStatement.setString(1, anagEnte.getChiaveEnte());
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
}