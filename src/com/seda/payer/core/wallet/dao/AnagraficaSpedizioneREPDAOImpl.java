package com.seda.payer.core.wallet.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Types;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import javax.sql.DataSource;

import com.seda.data.dao.DAOHelper;
import com.seda.data.helper.Helper;
import com.seda.data.helper.HelperException;
import com.seda.payer.core.dao.Routines;
import com.seda.payer.core.exception.DaoException;
import com.seda.payer.core.handler.BaseDaoHandler; 
import com.seda.payer.core.wallet.bean.AnagraficaSpedizioneREP;
import com.seda.payer.core.wallet.bean.Rep;

/**
 * PG130100
 *
 */
public class AnagraficaSpedizioneREPDAOImpl  extends BaseDaoHandler  implements AnagraficaSpedizioneREPDAO  {  
	CallableStatement insertBatchCs=null;
	Connection connection = null;
	 
	
	public AnagraficaSpedizioneREPDAOImpl(Connection connection, String schema) {
		super(connection, schema); 
	}

	

	public void openInsertBatch( )	throws DaoException { 
		try {
			connection = getConnection();
			insertBatchCs = Helper.prepareCall(connection, getSchema(), Routines.PYASPSP_INS.routine());
		}catch (Exception e) {
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
	
	public void insertBatch(AnagraficaSpedizioneREP anagraficaSpedizioneREP) throws DaoException {
		try { 
			insertBatchCs.setString(1, anagraficaSpedizioneREP.getIdWallet());
			insertBatchCs.setString(2, anagraficaSpedizioneREP.getNumeroProgressivoDisposizione() ); 
			insertBatchCs.setString(3, anagraficaSpedizioneREP.getDenominazioneDichiarante() ); 
			insertBatchCs.setString(4, anagraficaSpedizioneREP.getIndirizzoDichiarante()); 
			insertBatchCs.setString(5, anagraficaSpedizioneREP.getCapDichiarante()); 
			insertBatchCs.setString(6, anagraficaSpedizioneREP.getLocalitaDichiarante() ); 
			insertBatchCs.setString(7, anagraficaSpedizioneREP.getProvinciaDichiarante()); 
			insertBatchCs.setString(8, "N"); 
			insertBatchCs.setString(9, anagraficaSpedizioneREP.getKrepkrep()); 
			insertBatchCs.registerOutParameter(10, Types.INTEGER);
			//insertBatchCs.addBatch();
			insertBatchCs.execute();
			
		} catch (Exception e) {
			String msg = "Errore nell'esecuzione della stored "+Routines.PYASPSP_INS.routine();
			//System.out.println(msg);
			throw new DaoException(-1,msg,e);
		} 
		 
	}

	

	private static final long serialVersionUID = -8095357451919803282L;

 

 
}