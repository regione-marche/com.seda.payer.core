package com.seda.payer.core.wallet.dao;

import java.math.BigDecimal;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.sql.Types;
import java.util.Calendar;
import com.seda.data.helper.Helper;
import com.seda.data.procedure.reflection.MetaProcedure;
import com.seda.payer.core.dao.Routines;
import com.seda.payer.core.exception.DaoException;
import com.seda.payer.core.handler.BaseDaoHandler; 
import com.seda.payer.core.wallet.bean.AnagraficaFiglioMense;
import com.seda.payer.core.wallet.bean.FattureRep;

/**
 * PG130100
 *
 */
public class FattureRepDAOImpl  extends BaseDaoHandler  implements FattureRepDAO  {  
	CallableStatement insertBatchCs=null;
	Connection connection = null;
	 
	
	public FattureRepDAOImpl(Connection connection, String schema) {
		super(connection, schema); 
	}

	

	public void openInsertBatch( )	throws DaoException { 
		try {
			connection = getConnection();
			//inizio LP PGNTCORE-24
			//insertBatchCs = Helper.prepareCall(connection, getSchema(), Routines.PYFTRSP_INS.routine());
			insertBatchCs = MetaProcedure.prepareCall(connection, getSchema(), Routines.PYFTRSP_INS.routine());
			//fine LP PGNTCORE-24
		} catch (Exception e) {
			throw new DaoException(e);
		}
	}
/*
	public void commitInsertBatch() throws DaoException {
		try { 
			connection.commit();
		} catch (Exception e) {
			throw new DaoException(e);
		}
	}
*/
	public void closeInsertBatch( ) {
		Helper.close(insertBatchCs);
		//Helper.close(connection); 
	}
 

	public void executeInsertBatch( ) throws DaoException {
		try {
			insertBatchCs.executeBatch();
		} catch (SQLException e) {
			throw new DaoException(e);
		}
	}
	
	public void insertBatch(FattureRep fattureRep) throws DaoException {
		try { 
			insertBatchCs.setString(1, fattureRep.getIdWallet());
			insertBatchCs.setString(2,fattureRep.getCodiceServizio());
			insertBatchCs.setString(3, fattureRep.getNumeroProgressivoDisposizione() ); 
			insertBatchCs.setString(4, fattureRep.getCodiceAnagraficaFiglio()); 
			insertBatchCs.setString(5, fattureRep.getNumeroFattura()); 
			insertBatchCs.setTimestamp(6, new Timestamp(fattureRep.getPeriodoCompetenza().getTimeInMillis()) );
			insertBatchCs.setString(7,"N"); 
			insertBatchCs.setString(8,fattureRep.getKrepkrep()); 
			insertBatchCs.registerOutParameter(9, Types.INTEGER);
			//insertBatchCs.addBatch();
			insertBatchCs.execute();
			
		} catch (Exception e) {   
			String msg = "Errore nell'esecuzione della stored "+Routines.PYFTRSP_INS.routine();
			//System.out.println(msg);
			throw new DaoException(-1,msg,e);
		} 
		 
	}

	private static final long serialVersionUID = -8095357451919803282L;


	public AnagraficaFiglioMense getDatiAnagraficaServizio(Connection conn, String schema, String idWallet,String codiceAnagraficaFiglio, String codiceFiscaleFiglio, Calendar periodoCompetenza) throws DaoException ,Exception
	{
		AnagraficaFiglioMense ret = new AnagraficaFiglioMense();
		CallableStatement callableStatement=null;
//		Connection connection2 = getConnection();
		//inizio LP PG21XX04 Leak
		ResultSet data = null;
		//fine LP PG21XX04 Leak
		try	
		{
			//inizio LP PGNTCORE-24
			callableStatement = Helper.prepareCall(conn, getSchema(), Routines.PYAFMSP_SEL_SRV.routine());
			callableStatement = MetaProcedure.prepareCall(connection, getSchema(), Routines.PYAFMSP_SEL_SRV.routine());
			//fine LP PGNTCORE-24
			callableStatement.setString(1, idWallet);
			callableStatement.setString(2, codiceAnagraficaFiglio);
			callableStatement.setString(3, codiceFiscaleFiglio);
			callableStatement.setDate(4, new java.sql.Date( periodoCompetenza.getTimeInMillis() ) );
			
			
			if (callableStatement.execute()) {
				//inizio LP PG21XX04 Leak
				//ResultSet data = callableStatement.getResultSet();
				data = callableStatement.getResultSet();
				//fine LP PG21XX04 Leak
				if (data.next()){
					ret.setCodiceServizio(data.getString(1));
					ret.setImportoTariffa(data.getBigDecimal(2));
					ret.setTipologiaTariffa(data.getString(3));
				//tipoTariffa importoTariffa codiceServizio
				}
				else
				{
					ret.setCodiceServizio("");
					ret.setImportoTariffa(new BigDecimal(0));
					ret.setTipologiaTariffa("");
				}
			}			
		} catch (Exception e){
			e.printStackTrace();
			//inizio LP PG21XX04 Leak
			//throw e;
			throw new Exception(e);
			//fine LP PG21XX04 Leak
		} finally {
			//inizio LP PG21XX04 Leak
			//DAOHelper.closeIgnoringException(callableStatement);
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
//			DAOHelper.closeIgnoringException(connection2);
		}
		return ret;
	}

	public AnagraficaFiglioMense getDatiAnagraficaServizio(String idWallet,
			String codiceAnagraficaFiglio, String codiceFiscaleFiglio,
			Calendar periodoCompetenza) throws DaoException, Exception {
		return null;
	}

}
