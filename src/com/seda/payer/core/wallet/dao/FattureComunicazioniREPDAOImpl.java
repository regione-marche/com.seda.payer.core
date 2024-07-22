package com.seda.payer.core.wallet.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;
import com.seda.data.helper.Helper;
import com.seda.data.procedure.reflection.MetaProcedure;
import com.seda.payer.core.dao.Routines;
import com.seda.payer.core.exception.DaoException;
import com.seda.payer.core.handler.BaseDaoHandler; 
import com.seda.payer.core.wallet.bean.FattureComunicazioniREP;

/**
 * PG130100
 *
 */

public class FattureComunicazioniREPDAOImpl  extends BaseDaoHandler  implements FattureComunicazioniREPDAO  {  
	CallableStatement insertBatchCs=null;
	Connection connection = null;
	 
	
	public FattureComunicazioniREPDAOImpl(Connection connection, String schema) {
		super(connection, schema); 
	}

	

	public void openInsertBatch( )	throws DaoException { 
		try {
			connection = getConnection();
			//inizio LP PGNTCORE-24
			//insertBatchCs = Helper.prepareCall(connection, getSchema(), Routines.PYICRSP_INS.routine());
			insertBatchCs = MetaProcedure.prepareCall(connection, getSchema(), Routines.PYICRSP_INS.routine());
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
	
	public void insertBatch(FattureComunicazioniREP fattureComunicazioniREP) throws DaoException {
		try { 
			insertBatchCs.setString(1, fattureComunicazioniREP.getKicrkicr());
			insertBatchCs.setString(2, fattureComunicazioniREP.getIdWallet());
			insertBatchCs.setBigDecimal(3, fattureComunicazioniREP.getImportoFatturaTotale()); 
			insertBatchCs.setString(4, fattureComunicazioniREP.getDescrizioneVoceOperazione()); 
			insertBatchCs.setString(5, fattureComunicazioniREP.getSegnoImportoVoce()); 
			insertBatchCs.setString(6, fattureComunicazioniREP.getNota()); 	
			insertBatchCs.setString(7, fattureComunicazioniREP.getNumeroProgressivoDisposizione() ); 
			insertBatchCs.setString(8, fattureComunicazioniREP.getTipoServizioSpecifico());
			insertBatchCs.setString(9, "N");
			insertBatchCs.setString(10, fattureComunicazioniREP.getNumeroProgressivoRicorrenza());
			insertBatchCs.setString(11, fattureComunicazioniREP.getKrepkrep()); 			
			insertBatchCs.registerOutParameter(12, Types.INTEGER);
			//insertBatchCs.addBatch();
			insertBatchCs.execute();
			
			
		} catch (Exception e) {   
			String msg = "Errore nell'esecuzione della stored "+Routines.PYICRSP_INS.routine();
			//System.out.println(msg);
			throw new DaoException(-1,msg,e);
		} 
		 
	}
	   

	private static final long serialVersionUID = -8095357451919803282L;


 
}
