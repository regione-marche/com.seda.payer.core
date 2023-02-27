package com.seda.payer.core.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;

import com.seda.data.helper.HelperException;
import com.seda.payer.core.bean.FlussiRendInca;
import com.seda.payer.core.exception.DaoException;
import com.seda.payer.core.handler.BaseDaoHandler;

public class FlussiRendIncaDao extends BaseDaoHandler {
	public FlussiRendIncaDao(Connection connection, String schema) {
		super(connection, schema);
	}

	public int doDetail(String codiceSocieta) throws DaoException {
		//inizio LP PG21XX04 Leak
		CallableStatement callableStatement = null;
		//fine LP PG21XX04 Leak
		try	{
			//inizio LP PG21XX04 Leak
			//CallableStatement callableStatement = prepareCall(Routines.POF_DODETAIL.routine());
			callableStatement = prepareCall(Routines.POF_DODETAIL.routine());
			//fine LP PG21XX04 Leak
			callableStatement.setString(1, codiceSocieta);
			callableStatement.registerOutParameter(2, Types.INTEGER);
			callableStatement.execute();
			return callableStatement.getInt(2);
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
	
	public String doSave(FlussiRendInca flu/*,String codOp*/) throws DaoException {
		CallableStatement callableStatement=null;
		try	{
			//int data = doDetail(flu.getCodiceSocietaMittente());
			//if ((data != 0) && codOp!=null && codOp.compareTo(TypeRequest.ADD_SCOPE.scope())==0) throw new IllegalArgumentException(Messages.INVALID_PARAMETER.format("FlussiRendInca.saveadd.error"));
			//if (data != 0) 
			//callableStatement = prepareCall(Routines.POF_DOUPDATE.routine());
			callableStatement = prepareCall(Routines.POF_DOINSERT.routine());

			flu.save(callableStatement);
			callableStatement.execute();
			return callableStatement.getString(6);
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
}
