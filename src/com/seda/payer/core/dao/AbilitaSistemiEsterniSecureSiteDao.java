package com.seda.payer.core.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

import com.seda.data.helper.HelperException;
import com.seda.payer.commons.bean.TypeRequest;
import com.seda.payer.core.bean.AbilitaSistemiEsterniSecureSite;
import com.seda.payer.core.exception.DaoException;
import com.seda.payer.core.handler.BaseDaoHandler;
import com.seda.payer.core.messages.Messages;

public class AbilitaSistemiEsterniSecureSiteDao extends BaseDaoHandler {
	
	public AbilitaSistemiEsterniSecureSiteDao(Connection connection, String schema) {
		super(connection, schema);
	}
 
	public AbilitaSistemiEsterniSecureSite doDetail(String urlAccesso, String idServizio) throws DaoException {
		//inizio LP PG21XX04 Leak
		CallableStatement callableStatement = null;
		ResultSet data = null;
		//fine LP PG21XX04 Leak
		try	{
			//inizio LP PG21XX04 Leak
			//CallableStatement callableStatement = prepareCall(Routines.SEC_DODETAIL.routine());
			callableStatement = prepareCall(Routines.SEC_DODETAIL.routine());
			//fine LP PG21XX04 Leak
			callableStatement.setString(1, urlAccesso);
			callableStatement.setString(2, idServizio);
			
			if (callableStatement.execute()) {
				//inizio LP PG21XX04 Leak
				//ResultSet data = callableStatement.getResultSet();
				data = callableStatement.getResultSet();
				//fine LP PG21XX04 Leak
				if (data.next())
					return new AbilitaSistemiEsterniSecureSite(data);
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
	
	public void doRowSets(String urlAccesso, String descrizione, String idServizio) throws DaoException {
		rowSets(urlAccesso, descrizione, idServizio, 0, 0);
	}

	public void doRowSets(String urlAccesso, String descrizione, String idServizio, int rowsPerPage, int pageNumber) throws DaoException {

			if (rowsPerPage <= 0)
				throw new IllegalArgumentException(Messages.INVALID_PARAMETER.format("rowsPerPage"));

			if (pageNumber <= 0)
				throw new IllegalArgumentException(Messages.INVALID_PARAMETER.format("pageNumber"));
 
			rowSets(urlAccesso, descrizione, idServizio, rowsPerPage, pageNumber);

	}

	private void rowSets(String urlAccesso, String descrizione, String idServizio, int rowsPerPage, int pageNumber) throws DaoException {
		//inizio LP PG21XX04 Leak
		CallableStatement callableStatement = null;
		//fine LP PG21XX04 Leak
		try	{
			//inizio LP PG21XX04 Leak
			//CallableStatement callableStatement = prepareCall(Routines.SEC_DOLIST.routine());
			callableStatement = prepareCall(Routines.SEC_DOLIST.routine());
			//fine LP PG21XX04 Leak
			callableStatement.setString(1, urlAccesso);
			callableStatement.setString(2, descrizione);
			callableStatement.setString(3, idServizio);
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

	public void doSave(AbilitaSistemiEsterniSecureSite abilita,String codOp) throws DaoException {
		CallableStatement callableStatement = null;
		try	{
			if (abilita.getUrlAccesso() == null || abilita.getUrlAccesso().length() == 0) {
				throw new IllegalArgumentException(Messages.INVALID_PARAMETER.format("abilitaSistemiEsterniSecureSite.urlAccesso"));
				
			}
			//19022011 GG inizio
			if (abilita.getIdServizio() == null) {
				throw new IllegalArgumentException(Messages.INVALID_PARAMETER.format("abilitaSistemiEsterniSecureSite.idServizio"));
			}
			//19022011 GG fine
			
			//21022011 GG inizio
			/*
			AbilitaSistemiEsterniSecureSite data = doDetail(abilita.getUrlAccesso(), abilita.getIdServizio());
			if ((data != null) && codOp!=null && codOp.compareTo(TypeRequest.ADD_SCOPE.scope())==0) {
				throw new IllegalArgumentException(Messages.INVALID_PARAMETER.format("abilitaSistemiEsterniSecureSite.save.error"));
			}
			if (data != null) { 
				callableStatement = prepareCall(Routines.SEC_DOUPDATE.routine());
			}
			else {
				callableStatement = prepareCall(Routines.SEC_DOINSERT.routine());
			}
			*/
						
			if (codOp.equals(TypeRequest.ADD_SCOPE.scope()))
			{
				callableStatement = prepareCall(Routines.SEC_DOINSERT.routine());
			}
			else if (codOp.equals(TypeRequest.EDIT_SCOPE.scope()))
			{
				callableStatement = prepareCall(Routines.SEC_DOUPDATE.routine());
			}
			else
			{
				throw new IllegalArgumentException(Messages.INVALID_PARAMETER.format("abilitaSistemiEsterniSecureSite.save.error"));
			}
			//21022011 GG fine
			abilita.save(callableStatement);
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

	public void doDelete(AbilitaSistemiEsterniSecureSite abilita) throws DaoException {
		//inizio LP PG21XX04 Leak
		CallableStatement callableStatement = null;
		//fine LP PG21XX04 Leak
		try	{
			//inizio LP PG21XX04 Leak
			//CallableStatement callableStatement = prepareCall(Routines.SEC_DODELETE.routine());
			callableStatement = prepareCall(Routines.SEC_DODELETE.routine());
			//fine LP PG21XX04 Leak
			if (abilita.getUrlAccesso() == null || abilita.getUrlAccesso().length() == 0) {
				throw new IllegalArgumentException(Messages.INVALID_PARAMETER.format("abilitaSistemiEsterniSecureSite.urlAccesso"));
			}
			//19022011 GG inizio
			if (abilita.getIdServizio() == null) {
				throw new IllegalArgumentException(Messages.INVALID_PARAMETER.format("abilitaSistemiEsterniSecureSite.idServizio"));
			}
			//19022011 GG fine
			callableStatement.setString(1, abilita.getUrlAccesso());
			callableStatement.setString(2, abilita.getIdServizio());
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
