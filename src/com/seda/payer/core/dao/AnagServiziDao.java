package com.seda.payer.core.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import com.seda.data.helper.HelperException;
import com.seda.payer.commons.bean.TypeRequest;
import com.seda.payer.core.bean.AnagServizi;
import com.seda.payer.core.exception.DaoException;
import com.seda.payer.core.handler.BaseDaoHandler;
import com.seda.payer.core.messages.Messages;
/**
 * 
 * @author adamodio
 *
 */
public class AnagServiziDao extends BaseDaoHandler {
	
	public AnagServiziDao(Connection connection, String schema) {
		super(connection, schema);
	}

	public AnagServizi doDetail(String codiceAnagServizi) throws DaoException {
		//inizio LP PG21XX04 Leak
		CallableStatement callableStatement = null;
		ResultSet data = null;
		//fine LP PG21XX04 Leak
		try	{
			//inizio LP PG21XX04 Leak
			//CallableStatement callableStatement = prepareCall(Routines.SER_DODETAIL.routine());
			callableStatement = prepareCall(Routines.SER_DODETAIL.routine());
			//fine LP PG21XX04 Leak
			callableStatement.setString(1, codiceAnagServizi);
			if (callableStatement.execute()) {
				//inizio LP PG21XX04 Leak
				//ResultSet data = callableStatement.getResultSet();
				data = callableStatement.getResultSet();
				//fine LP PG21XX04 Leak
				if (data.next())
					return new AnagServizi(data);
			}
			return null;
		} catch (SQLException x) {
			throw new DaoException(x);
		} catch (IllegalArgumentException x) {
			throw new DaoException(x);
		} catch (HelperException x) {
			throw new DaoException(x);
		//inizio LP PG21XX04 Leak
		//}
		} finally {
			if (data != null) {
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

	public void doRowSets(String codiceAnagServizi, String descrizioneAnagServizi) throws DaoException {
		rowSets(codiceAnagServizi, descrizioneAnagServizi, 0, 0);
	}

	public void doRowSets(String codiceAnagServizi, String descrizioneAnagServizi, int rowsPerPage, int pageNumber) throws DaoException {

			if (rowsPerPage <= 0)
				throw new IllegalArgumentException(Messages.INVALID_PARAMETER.format("rowsPerPage"));

			if (pageNumber <= 0)
				throw new IllegalArgumentException(Messages.INVALID_PARAMETER.format("pageNumber"));
 
			rowSets(codiceAnagServizi, descrizioneAnagServizi, rowsPerPage, pageNumber);

	}

	private void rowSets(String codiceAnagServizi, String descrizioneAnagServizi, int rowsPerPage, int pageNumber) throws DaoException {
		//inizio LP PG21XX04 Leak
		CallableStatement callableStatement = null;
		//fine LP PG21XX04 Leak
		try	{
			//inizio LP PG21XX04 Leak
			//CallableStatement callableStatement = prepareCall(Routines.SER_DOLIST.routine());
			callableStatement = prepareCall(Routines.SER_DOLIST.routine());
			//fine LP PG21XX04 Leak
			callableStatement.setString(1, codiceAnagServizi);
			callableStatement.setString(2, descrizioneAnagServizi);
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
		//inizio LP PG21XX04 Leak
		//}
		} finally {
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

	public void doSave(AnagServizi anagservizi,String codOp) throws DaoException {
		CallableStatement callableStatement=null;
		try	{
			if (anagservizi.getCodiceAnagServizi() == null || anagservizi.getCodiceAnagServizi().length() == 0)
				throw new IllegalArgumentException(Messages.INVALID_PARAMETER.format("anagServizi.codiceAnagServizi"));

			AnagServizi data = doDetail(anagservizi.getCodiceAnagServizi());
			if ((data != null) && codOp!=null && codOp.compareTo(TypeRequest.ADD_SCOPE.scope())==0) throw new IllegalArgumentException(Messages.INVALID_PARAMETER.format("anagServizi.saveadd.error"));
			if (data != null) 
				callableStatement = prepareCall(Routines.SER_DOUPDATE.routine());
			else callableStatement = prepareCall(Routines.SER_DOINSERT.routine());
			
			anagservizi.save(callableStatement);
			callableStatement.execute();
			//commit();
		} catch (SQLException x) {
			throw new DaoException(x);
		} catch (IllegalArgumentException x) {
			throw new DaoException(x);
		} catch (HelperException x) {
			throw new DaoException(x);
		//inizio LP PG21XX04 Leak
		//}
		} finally {
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

	public void doDelete(AnagServizi anagservizi) throws DaoException {
		//inizio LP PG21XX04 Leak
		CallableStatement callableStatement = null;
		//fine LP PG21XX04 Leak
		try	{
			//inizio LP PG21XX04 Leak
			//CallableStatement callableStatement = prepareCall(Routines.SER_DODELETE.routine());
			callableStatement = prepareCall(Routines.SER_DODELETE.routine());
			//fine LP PG21XX04 Leak
			if (anagservizi.getCodiceAnagServizi() == null || anagservizi.getCodiceAnagServizi().length() == 0)
				throw new IllegalArgumentException(Messages.INVALID_PARAMETER.format("anagservizi.codiceAnagServizi"));
	
			callableStatement.setString(1, anagservizi.getCodiceAnagServizi());
			callableStatement.execute();
			//commit();
		} catch (SQLException x) {
			throw new DaoException(x.getErrorCode(),x.getMessage(),x);
		} catch (IllegalArgumentException x) {
			throw new DaoException(Integer.parseInt(Messages.ILL_ARG_ERR_CODE.format()),x.getMessage(),x);
		} catch (HelperException x) {
			throw new DaoException(Integer.parseInt(Messages.HELPER_ERR_CODE.format()),x.getMessage(),x);
		//inizio LP PG21XX04 Leak
		//}
		} finally {
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