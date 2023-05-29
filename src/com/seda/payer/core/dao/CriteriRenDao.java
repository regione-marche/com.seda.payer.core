package com.seda.payer.core.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

//inizio LP PG21XX04 Leak
//import com.seda.data.dao.DAOHelper;
//fine LP PG21XX04 Leak
import com.seda.data.helper.HelperException;
import com.seda.payer.core.bean.CriteriRen;
import com.seda.payer.core.exception.DaoException;
import com.seda.payer.core.handler.BaseDaoHandler;


/**
 * @author s.parisi
 *
 */

public class CriteriRenDao extends BaseDaoHandler{
	

	/**
	 * Costruttore
	 * @param connection
	 * @param schema
	 */
	public CriteriRenDao(Connection connection, String schema) {
		super(connection, schema);
	}
	
	/**
	 * Metodo che ritorna i criteri di rendicontazione analizzando le due tabelle PYREETB e PYRESTB
	 * @param codiceSocieta
	 * @param codiceUtente
	 * @param chiaveEnte
	 * @param tipologiaServizio
	 * @return CriteriRen
	 * @throws DaoException
	 */
	public CriteriRen returnCriteriRend (String codiceSocieta, String codiceUtente, String chiaveEnte, String tipologiaServizio)throws DaoException
	{
		CallableStatement callableStatement = null;
		//inizio LP PG21XX04 Leak
		ResultSet data = null;
		ResultSet data2 = null;
		//fine LP PG21XX04 Leak
		try{
			callableStatement = prepareCall(Routines.CREN_SELBYCSCUCETS.routine());
			callableStatement.setString(1, codiceSocieta);
			callableStatement.setString(2, codiceUtente);
			callableStatement.setString(3, chiaveEnte);
			callableStatement.setString(4, tipologiaServizio);
			boolean returnfirstrs = false;
			if (callableStatement.execute()) {
				//inizio LP PG21XX04 Leak
				//ResultSet data = callableStatement.getResultSet();
				data = callableStatement.getResultSet();
				//fine LP PG21XX04 Leak
				if (data.next()){
				            returnfirstrs = true;
				            if (data.getString("REE_CSOCCSOC") != null && data.getString("REE_CUTECUTE") != null && data.getString("REE_KANEKENT") != null && data.getString("REE_CTSECTSE")!=null)
					             return new CriteriRen(codiceSocieta, codiceUtente, chiaveEnte, tipologiaServizio,
						                               data.getString("REE_EREEEDES"), data.getString("REE_EREEECCN"),
						                               data.getString("REE_EREEEMIT"), data.getString("REE_DREEDMIT"), data.getString("REE_FREEMAIL"),
						                               data.getString("REE_DREESFTP"), data.getString("REE_DREEUFTP"), data.getString("REE_DREEPFTP"),
						                               data.getString("REE_DREERDIR"), data.getString("REE_FREEAFTP"), data.getInt("REE_NREEMAXB"),
						                               data.getDate("REE_GREEGAGG"), data.getString("REE_CREECOPE"),data.getString("REE_FREETREN"),
						                               data.getString("REE_FREECARI"),data.getString("REE_CREEENTE"),data.getString("REE_CREEIMPS"),
						                               data.getString("REE_FREECONT"),data.getString("REE_CREECTRB"), data.getString("REE_CREEAWBS"),
						                               data.getString("REE_CREEUWBS"), data.getString("REE_CREEUTWS"), data.getString("REE_CREEPWWS"),
						                               "");
				            return null;
			 
				}
			}
			if (!returnfirstrs){
				if(callableStatement.getMoreResults()){
				//inizio LP PG21XX04 Leak
				//ResultSet data2 = callableStatement.getResultSet();
				data2 = callableStatement.getResultSet();
				//fine LP PG21XX04 Leak
				if (data2.next()) 
					if (data2.getString("RES_CSOCCSOC") != null && data2.getString("RES_CUTECUTE") != null && data2.getString("RES_CTSECTSE")!=null)
						return new CriteriRen(codiceSocieta, codiceUtente, chiaveEnte, tipologiaServizio,
                                              data2.getString("RES_ERESEDES"), data2.getString("RES_ERESECCN"),
                                              data2.getString("RES_ERESEMIT"), data2.getString("RES_DRESDMIT"), data2.getString("RES_FRESMAIL"),
                                              data2.getString("RES_DRESSFTP"), data2.getString("RES_DRESUFTP"), data2.getString("RES_DRESPFTP"),
                                              data2.getString("RES_DRESRDIR"), data2.getString("RES_FRESAFTP"), data2.getInt("RES_NRESMAXB"),
                                              data2.getDate("RES_GRESGAGG"), data2.getString("RES_CRESCOPE"), data2.getString("RES_FRESTREN"),
                                              data2.getString("RES_FRESCARI"),data2.getString("RES_CRESENTE"),data2.getString("RES_CRESIMPS"),
				                              data2.getString("RES_FRESCONT"),data2.getString("RES_CRESCTRB"), data2.getString("RES_CRESAWBS"),
				                              data2.getString("RES_CRESUWBS"), data2.getString("RES_CRESUTWS"), data2.getString("RES_CRESPWWS"),
				                              "");
					return null;
			}
		   }
			return null;
		} catch (SQLException x) {
			throw new DaoException(x);
		} catch (IllegalArgumentException x) {
			throw new DaoException(x);
		} catch (HelperException x) {
			throw new DaoException(x);
		}
		finally
		{
			//inizio LP PG21XX04 Leak
			//DAOHelper.closeIgnoringException(callableStatement);
			if (data != null) {
				try {
					data.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (data2 != null) {
				try {
					data2.close();
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
