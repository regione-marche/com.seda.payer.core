package com.seda.payer.core.wallet.dao;

import java.math.BigDecimal;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import javax.sql.DataSource;
import com.seda.data.helper.Helper;
import com.seda.data.procedure.reflection.MetaProcedure;
import com.seda.payer.core.dao.Routines;
import com.seda.payer.core.exception.DaoException;
import com.seda.payer.core.handler.BaseDaoHandler; 
import com.seda.payer.core.wallet.bean.Rep; 

public class RepDAOImpl extends BaseDaoHandler  implements RepDAO { 
	CallableStatement insertBatchCs=null;
	Connection connection = null;
	
	public RepDAOImpl(DataSource ds, String schema) throws SQLException {
		super(ds.getConnection(), schema);
	}

	public RepDAOImpl(Connection connection, String schema) throws SQLException { //MODIFICA
		super(connection, schema);
	}

	public void openInsertBatch( )	throws DaoException { 
		try {
			connection = getConnection();
			//inizio LP PGNTCORE-24
			//insertBatchCs = Helper.prepareCall(connection, getSchema(), Routines.PYREPSP_INS.routine());
			insertBatchCs =  MetaProcedure.prepareCall(connection, getSchema(), Routines.PYREPSP_INS.routine());
			//fine LP PGNTCORE-24
		} catch (Exception e) {
			throw new DaoException(e);
		}
	} 
	
	// public void commitInsertBatch() throws DaoException{}

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
	
	public void insertBatch(Rep rep)	throws DaoException {
		  
		try { 
			insertBatchCs.setString(1, rep.getIdWallet());
			insertBatchCs.setString(2, rep.getNumeroProgressivoDisposizione() ); 
			insertBatchCs.setString(3, rep.getTipoDisposizione() ); 
			insertBatchCs.setString(4, rep.getCodiceEnteCreditore() );  
			insertBatchCs.setBigDecimal(5, rep.getImportoCarico() ); 
			insertBatchCs.setBigDecimal(6, new BigDecimal(0) ); 
			insertBatchCs.setBigDecimal(7, new BigDecimal(0) ); 
			insertBatchCs.setString(8, rep.getCodiceAnagraficaFiglio() );  
			insertBatchCs.setString(9, rep.getNote() );  
			insertBatchCs.setString(10, rep.getCodiceServizio() );  
			insertBatchCs.setBigDecimal(11, rep.getImportoTariffa().multiply(new BigDecimal(100)) ); 
			//modificato come da email di P. il 19/08/2013
			//insertBatchCs.setString(12, rep.getTipoTariffa());
			insertBatchCs.setString(12, "M");
			insertBatchCs.setString(13, "N");
			insertBatchCs.setString(14, rep.getCodiceAnagraficaGenitore());
			insertBatchCs.setString(15, rep.getKrepkrep());
			//insertBatchCs.executeBatch();
			insertBatchCs.execute();
		} catch (SQLException e) {
			if (e.getErrorCode() == -803) {
				String msg = "Disposizione già presente "+Routines.PYREPSP_INS.routine();
				throw new DaoException(803,msg,e);
			} else {
				String msg = "Errore nell'esecuzione della stored "+Routines.PYREPSP_INS.routine();
				throw new DaoException(1,msg,e);
			}
		} 
	}
	
}
