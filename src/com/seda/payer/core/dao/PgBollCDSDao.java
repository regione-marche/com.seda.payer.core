package com.seda.payer.core.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;

import com.seda.data.dao.DAOHelper;
import com.seda.data.helper.HelperException;
import com.seda.payer.core.bean.PgBollCDS;
import com.seda.payer.core.exception.DaoException;
import com.seda.payer.core.handler.BaseDaoHandler;

public class PgBollCDSDao extends BaseDaoHandler
{
	
	public PgBollCDSDao(Connection connection, String schema) {
		super(connection, schema);	
	}

	
	public PgBollCDS doBollDetailIV(String cuteCute, String bollettino) throws DaoException {
		CallableStatement callableStatement = null;
		try	{ 
			callableStatement = prepareCall(Routines.CDS_DODETAIL.routine());
			
			callableStatement.setString(1, cuteCute);
			callableStatement.setString(2, bollettino);
		    callableStatement.registerOutParameter(3, Types.VARCHAR);
		    callableStatement.registerOutParameter(4, Types.VARCHAR);

		    callableStatement.execute();			
		    return new PgBollCDS(callableStatement);		    
		    
		} catch (SQLException x) {
			throw new DaoException(x);
		} catch (IllegalArgumentException x) {
			throw new DaoException(x);
		} catch (HelperException x) {
			throw new DaoException(x);
		} finally {
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

}
