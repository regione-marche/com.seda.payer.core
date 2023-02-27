package com.seda.payer.core.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.seda.data.dao.DAOHelper;
import com.seda.data.helper.HelperException;
import com.seda.payer.core.bean.Aea;
import com.seda.payer.core.bean.Rid;
import com.seda.payer.core.exception.DaoException;
import com.seda.payer.core.handler.BaseDaoHandler;
import com.seda.payer.core.messages.Messages;

public class RidDao extends BaseDaoHandler {

	public RidDao(Connection connection, String schema) {
		super(connection, schema);
		
	}
	
	/**
	 * ritornano tutte gli eventi validi e di cui non è stato inviata email e/o SMS
	 * @return
	 * @throws DaoException
	 */
	public Rid doDetail(String sCodiceDebitore) throws DaoException {
		
		CallableStatement callableStatement = null;
		ResultSet data = null;
		try	{
			callableStatement = prepareCall(Routines.RID_DODETAIL.routine());
			callableStatement.setString(1, sCodiceDebitore);
			
			if (callableStatement.execute()) 
			{
				data = callableStatement.getResultSet();
				if (data.next())
					return new Rid(data);
			}
			
			return null;
		} catch (SQLException x) {
			throw new DaoException(x);
		} catch (IllegalArgumentException x) {
			throw new DaoException(x);
		} catch (HelperException x) {
			throw new DaoException(x);
		}
		finally {
			//inizio LP PG21XX04 Leak
			//if (callableStatement != null)
			//	DAOHelper.closeIgnoringException(callableStatement);
			//if (data != null)
			//	DAOHelper.closeIgnoringException(data);
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
			//fine LP PG21XX04 Leak
		}
		
	}
	
	
	public void doSave(Rid r) throws DaoException {
		CallableStatement callableStatement = null;
		try	{
			if (r.getCodiceDebitore() == null || r.getCodiceDebitore().length() == 0) 
				throw new IllegalArgumentException(Messages.INVALID_PARAMETER.format("ModuloIntegrazionePagamenti.chiaveTransazione"));
			
			Rid data = doDetail(r.getCodiceDebitore());
			
			if (data != null)  
			{
				callableStatement = prepareCall(Routines.RID_DOUPDATE.routine());
				r.update(callableStatement);
			}
			else
			{
				callableStatement = prepareCall(Routines.RID_DOINSERT.routine());
				r.save(callableStatement);
			}

			callableStatement.execute();
			
		} catch (SQLException x) {
			throw new DaoException(x);
		} catch (IllegalArgumentException x) {
			throw new DaoException(x);
		} catch (HelperException x) {
			throw new DaoException(x);
		}
		finally {
			//inizio LP PG21XX04 Leak
			//if (callableStatement != null)
			//	DAOHelper.closeIgnoringException(callableStatement);
			if (callableStatement != null) {
				try {
					callableStatement.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			//fine LP PG21XX04 Leak
		}
	}
	
	
	public void doDelete(String sCodiceDebitore) throws DaoException {
		
		CallableStatement callableStatement = null;
		try	{
			callableStatement = prepareCall(Routines.RID_DODEL.routine());
			callableStatement.setString(1, sCodiceDebitore);			
			callableStatement.execute();
			
		} catch (SQLException x) {
			throw new DaoException(x);
		} catch (IllegalArgumentException x) {
			throw new DaoException(x);
		} catch (HelperException x) {
			throw new DaoException(x);
		}
		finally {
			//inizio LP PG21XX04 Leak
			//if (callableStatement != null)
			//	DAOHelper.closeIgnoringException(callableStatement);
			if (callableStatement != null) {
				try {
					callableStatement.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			//fine LP PG21XX04 Leak
		}
		
	}
	
	
	public Aea doDetailAea(String codiceCausale) throws DaoException {
		
		CallableStatement callableStatement = null;
		ResultSet data = null;
		try	{
			callableStatement = prepareCall(Routines.AEA_DODETAIL.routine());
			callableStatement.setString(1, codiceCausale);
			
			if (callableStatement.execute()) 
			{
				data = callableStatement.getResultSet();
				if (data.next())
					return new Aea(data);
			}
			
			return null;
		} catch (SQLException x) {
			throw new DaoException(x);
		} catch (IllegalArgumentException x) {
			throw new DaoException(x);
		} catch (HelperException x) {
			throw new DaoException(x);
		}
		finally {
			//inizio LP PG21XX04 Leak
			//if (callableStatement != null)
			//	DAOHelper.closeIgnoringException(callableStatement);
			//if (data != null)
			//	DAOHelper.closeIgnoringException(data);
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
			//fine LP PG21XX04 Leak
		}
		
	}
	
	
	
	/**
	 * ritorna la lista di tutte le richieste di revoca in corso, precedenti alla data passata come
	 * parametro, da accettare in automatico
	 * @return
	 * @throws DaoException
	 */
	public List<Rid> doListAutoRevoca(String sData) throws DaoException 
	{
		List<Rid> listRid = new ArrayList<Rid>();
		CallableStatement callableStatement = null;
		ResultSet data = null;
		try	{
			callableStatement = prepareCall(Routines.RID_DOLIST_BATCH.routine());
			callableStatement.setString(1, sData);
			
			if (callableStatement.execute()) 
			{
				data = callableStatement.getResultSet();
				while (data.next())
					listRid.add(new Rid(data));
			}
			
			return listRid;
		} catch (SQLException x) {
			throw new DaoException(x);
		} catch (IllegalArgumentException x) {
			throw new DaoException(x);
		} catch (HelperException x) {
			throw new DaoException(x);
		}
		finally {
			//inizio LP PG21XX04 Leak
			//if (callableStatement != null)
			//	DAOHelper.closeIgnoringException(callableStatement);
			//if (data != null)
			//	DAOHelper.closeIgnoringException(data);
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
			//fine LP PG21XX04 Leak
		}
	}
}
