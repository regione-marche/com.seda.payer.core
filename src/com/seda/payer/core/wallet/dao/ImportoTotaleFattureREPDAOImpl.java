package com.seda.payer.core.wallet.dao;

import java.math.BigDecimal;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;
import com.seda.data.helper.Helper;
import com.seda.payer.core.dao.Routines;
import com.seda.payer.core.exception.DaoException;
import com.seda.payer.core.handler.BaseDaoHandler; 
import com.seda.payer.core.wallet.bean.ImportoTotaleFattureREP;

/**
 * PG130100
 *
 */

public class ImportoTotaleFattureREPDAOImpl  extends BaseDaoHandler  implements ImportoTotaleFattureREPDAO {  
	CallableStatement insertBatchCs = null;
	//Connection connection = null; //LP PGNTCORE-24
	 
	
	public ImportoTotaleFattureREPDAOImpl(Connection connection, String schema) {
		super(connection, schema); 
	}

	
	public void openInsertBatch( )	throws DaoException { 
		try {
			//inizio LP PGNTCORE-24
			//connection = getConnection();
			//insertBatchCs = Helper.prepareCall(connection, getSchema(), Routines.PYIFTSP_INS.routine());
			insertBatchCs = prepareCall(Routines.PYIFTSP_INS.routine());
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
		insertBatchCs = null; //LP PGNTCORE-24
		//Helper.close(connection); 
	}
 
	public void executeInsertBatch( ) throws DaoException {
		try {
			insertBatchCs.executeBatch();
		} catch (SQLException e) {
			throw new DaoException(e);
		}
	}
	
	public void insertBatch(ImportoTotaleFattureREP importoTotaleFattureREP) throws DaoException {
		try { 
			insertBatchCs.setString(1, importoTotaleFattureREP.getIdWallet());
			insertBatchCs.setString(2, importoTotaleFattureREP.getNumeroProgressivoDisposizione() ); 
			insertBatchCs.setString(3, importoTotaleFattureREP.getCodiceServizio());
			insertBatchCs.setInt(4, Integer.parseInt(importoTotaleFattureREP.getTipoServizioSpecifico())); 
			insertBatchCs.setBigDecimal(5, importoTotaleFattureREP.getImportoTotaleFattura()); 
			insertBatchCs.setString(6, importoTotaleFattureREP.getAnnoTributo()); 
			insertBatchCs.setString(7, importoTotaleFattureREP.getCodiceTributo()); 
			insertBatchCs.setString(8, importoTotaleFattureREP.getDescrizioneVoceOperazione()); 
			//insertBatchCs.setString(9, importoTotaleFattureREP.getSegno()); 
			//insertBatchCs.setBigDecimal(10, new BigDecimal(importoTotaleFattureREP.getImportoVoce())); 
			insertBatchCs.setString(9, "N"); 
			insertBatchCs.setString(10, importoTotaleFattureREP.getKiftkift()); 
			insertBatchCs.setInt(11, Integer.parseInt(importoTotaleFattureREP.getNumeroProgressivoRicorrenza())); 
			insertBatchCs.setString(12, importoTotaleFattureREP.getKrepkrep()); 
			insertBatchCs.setBigDecimal(13, new BigDecimal(0)); 
			insertBatchCs.setBigDecimal(14, new BigDecimal(0)); 
			insertBatchCs.registerOutParameter(15, Types.INTEGER);
			//insertBatchCs.addBatch();
			insertBatchCs.execute();
			
		} catch (Exception e) {   
			throw new DaoException(e);
		} 
		 
	}

	/*
   IN I_IFT_KITFKITF VARCHAR (64)   -- Chiave 
 , IN I_IFT_KBRSKBRS VARCHAR (18)   -- id wallet
 , IN I_IFT_CREPNPRD VARCHAR (7)    -- numero progressivo
 , IN I_IFT_CSRVCODI CHAR    (2)    -- codice servizio
 , IN I_IFT_TIFTSERV INTEGER        -- 
 , IN I_IFT_CIFTNPRG CHAR    (5)
 , IN I_IFT_IIFTIMPO DECIMAL (10)
 , IN I_IFT_CIFTANTR VARCHAR (4)
 , IN I_IFT_CIFTCODT VARCHAR (5)
 , IN I_IFT_DIFTDESC VARCHAR (48)
 , IN I_IFT_CIFTSEGN VARCHAR (1)
 , IN I_IFT_IIFTIBOL DECIMAL (10)
 , OUT O_TOTROWS INTEGER )
	*/
	   	
	private static final long serialVersionUID = -8095357451919803282L;
 
}

