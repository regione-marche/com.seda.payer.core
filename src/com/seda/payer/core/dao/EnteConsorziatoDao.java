package com.seda.payer.core.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

import com.seda.payer.commons.bean.TypeRequest;
import com.seda.payer.core.bean.EnteConsorziato;
import com.seda.payer.core.exception.DaoException;
import com.seda.payer.core.handler.BaseDaoHandler;
import com.seda.payer.core.messages.Messages;
import com.seda.data.helper.HelperException;
/**
 * 
 * @author mmontisano
 *
 */
public class EnteConsorziatoDao extends BaseDaoHandler {
	
	public EnteConsorziatoDao(Connection connection, String schema) {
		super(connection, schema);
	}

	public EnteConsorziato doDetail(String companyCode, String userCode, String chiaveEnteCon, String chiaveEnteEnt) throws DaoException {
		//inizio LP PG21XX04 Leak
		CallableStatement callableStatement = null;
		ResultSet data = null;
		//fine LP PG21XX04 Leak
		try	{
			//inizio LP PG21XX04 Leak
			//CallableStatement callableStatement = prepareCall(Routines.ENC_DODETAIL.routine());
			callableStatement = prepareCall(Routines.ENC_DODETAIL.routine());
			//fine LP PG21XX04 Leak
			callableStatement.setString(1, companyCode);
			callableStatement.setString(2, userCode);
			callableStatement.setString(3, chiaveEnteCon);
			callableStatement.setString(4, chiaveEnteEnt);

			if (callableStatement.execute()) {
				//inizio LP PG21XX04 Leak
				//ResultSet data = callableStatement.getResultSet();
				data = callableStatement.getResultSet();
				//fine LP PG21XX04 Leak
				if (data.next())
					return new EnteConsorziato(data);
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

	public void doRowSets(EnteConsorziato entec, String strDescrEnte,String strDescrSocieta,String strDescrUtente) throws DaoException {
		rowSets(entec, 0, 0,strDescrEnte,strDescrSocieta,strDescrUtente);
	}

	public void doRowSets(EnteConsorziato entec, int rowsPerPage, int pageNumber, String strDescrEnte,String strDescrSocieta,String strDescrUtente) throws DaoException {

			if (rowsPerPage <= 0)
				throw new IllegalArgumentException(Messages.INVALID_PARAMETER.format("rowsPerPage"));

			if (pageNumber <= 0)
				throw new IllegalArgumentException(Messages.INVALID_PARAMETER.format("pageNumber"));
 
			rowSets(entec, rowsPerPage, pageNumber,strDescrEnte,strDescrSocieta,strDescrUtente);

	}
	
	public void rowSets(EnteConsorziato entec, int rowsPerPage, int pageNumber, String strDescrEnte,String strDescrSocieta,String strDescrUtente) throws DaoException {
		//inizio LP PG21XX04 Leak
		CallableStatement callableStatement = null;
		//fine LP PG21XX04 Leak
		try	{
			//inizio LP PG21XX04 Leak
			//CallableStatement callableStatement = prepareCall(Routines.ENC_DOLIST.routine());
			callableStatement = prepareCall(Routines.ENC_DOLIST.routine());
			//fine LP PG21XX04 Leak
			callableStatement.setString(1, entec.getEnte().getUser().getCompany().getCompanyCode());
			callableStatement.setString(2, entec.getEnte().getUser().getUserCode());
			callableStatement.setString(3, entec.getEnte().getAnagEnte().getChiaveEnte());
			callableStatement.setString(4, entec.getAnagEnte().getChiaveEnte());
			callableStatement.setString(5, strDescrEnte);
			callableStatement.setString(6, strDescrSocieta);
			callableStatement.setString(7, strDescrUtente);
			callableStatement.setString(8, "");
			callableStatement.setInt(9, rowsPerPage);
			callableStatement.setInt(10, pageNumber);
			/* we register row start */
			callableStatement.registerOutParameter(11, Types.INTEGER);
			/* we register row end */
			callableStatement.registerOutParameter(12, Types.INTEGER);
			/* we register total rows */
			callableStatement.registerOutParameter(13, Types.INTEGER);
			/* we register total pages */
			callableStatement.registerOutParameter(14, Types.SMALLINT);
			/* we execute procedure */
			if (callableStatement.execute()) {
				this.loadWebRowSets(callableStatement);
				/* we register page info */
				registerPageInfo(rowsPerPage, pageNumber, callableStatement.getInt(11), callableStatement.getInt(12), 
								 callableStatement.getInt(13), callableStatement.getInt(14));
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

	public void doSave(EnteConsorziato entec,String codOp) throws DaoException {
		CallableStatement callableStatement = null;
		try	{
			
			if (entec.getEnte().getUser() == null || entec.getEnte().getUser().getUserCode() == null || entec.getEnte().getUser().getUserCode().length() == 0)
				throw new IllegalArgumentException(Messages.INVALID_PARAMETER.format("enteconsorziato.ente.userCode"));

			if (                          entec.getEnte().getUser().getCompany() == null || entec.getEnte().getUser().getCompany().getCompanyCode() == null || entec.getEnte().getUser().getCompany().getCompanyCode().length() == 0)
				throw new IllegalArgumentException(Messages.INVALID_PARAMETER.format("enteconsorziato.ente.userCode.company.companyCode"));
			
			if (entec.getEnte().getAnagEnte() == null || entec.getEnte().getAnagEnte().getChiaveEnte() == null || entec.getEnte().getAnagEnte().getChiaveEnte().length() == 0)
				throw new IllegalArgumentException(Messages.INVALID_PARAMETER.format("enteconsorziato.ente.anagEnte.chiaveEnte"));
			
			if (entec.getAnagEnte() == null || entec.getAnagEnte().getChiaveEnte() == null || entec.getAnagEnte().getChiaveEnte().length() == 0)
				throw new IllegalArgumentException(Messages.INVALID_PARAMETER.format("enteconsorziato.anagEnte.chiaveEnte"));
			
			EnteConsorziato data = doDetail(entec.getEnte().getUser().getCompany().getCompanyCode(),entec.getEnte().getUser().getUserCode(),entec.getEnte().getAnagEnte().getChiaveEnte(),entec.getAnagEnte().getChiaveEnte());
			if ((data != null) && codOp!=null && codOp.compareTo(TypeRequest.ADD_SCOPE.scope())==0) throw new IllegalArgumentException(Messages.INVALID_PARAMETER.format("ente.saveadd.error"));
			if (data != null) 
				callableStatement = prepareCall(Routines.ENC_DOUPDATE.routine());
			else callableStatement = prepareCall(Routines.ENC_DOINSERT.routine());
			
			entec.save(callableStatement);
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

	public void doDelete(EnteConsorziato entec) throws DaoException {
		//inizio LP PG21XX04 Leak
		CallableStatement callableStatement = null;
		//fine LP PG21XX04 Leak
		try	{
			//inizio LP PG21XX04 Leak
			//CallableStatement callableStatement = prepareCall(Routines.ENC_DODELETE.routine());
			callableStatement = prepareCall(Routines.ENC_DODELETE.routine());
			//fine LP PG21XX04 Leak
			if (entec.getEnte().getUser().getUserCode() == null || entec.getEnte().getUser().getUserCode().length() == 0)
				throw new IllegalArgumentException(Messages.INVALID_PARAMETER.format("enteconsorziato.ente.userCode"));

			if (entec.getEnte().getUser().getCompany() == null || entec.getEnte().getUser().getCompany().getCompanyCode() == null || entec.getEnte().getUser().getCompany().getCompanyCode().length() == 0)
				throw new IllegalArgumentException(Messages.INVALID_PARAMETER.format("enteconsorziato.ente.user.company.companyCode"));

			if (entec.getEnte().getAnagEnte() == null || entec.getEnte().getAnagEnte().getChiaveEnte() == null || entec.getEnte().getAnagEnte().getChiaveEnte().length() == 0)
				throw new IllegalArgumentException(Messages.INVALID_PARAMETER.format("enteconsorziato.ente.anagEnte.chiaveEnte"));
			
			if (entec.getAnagEnte() == null || entec.getAnagEnte().getChiaveEnte() == null || entec.getAnagEnte().getChiaveEnte().length() == 0)
				throw new IllegalArgumentException(Messages.INVALID_PARAMETER.format("enteconsorziato.anagEnte.chiaveEnte"));
			
			
			callableStatement.setString(1, entec.getEnte().getUser().getCompany().getCompanyCode());
			callableStatement.setString(2, entec.getEnte().getUser().getUserCode());
			callableStatement.setString(3, entec.getEnte().getAnagEnte().getChiaveEnte());
			callableStatement.setString(4, entec.getAnagEnte().getChiaveEnte());
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