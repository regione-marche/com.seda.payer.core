package com.seda.payer.core.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;

import com.seda.data.helper.HelperException;
import com.seda.data.spi.PageInfo;
import com.seda.payer.core.bean.TransazioneAtm;
import com.seda.payer.core.exception.DaoException;
import com.seda.payer.core.handler.BaseDaoHandler;

public class TxTransazioniAtmDao extends BaseDaoHandler {

	private PageInfo pageInfo = null;

	public TxTransazioniAtmDao(Connection connection, String schema) {
		super(connection, schema);
	}

	public PageInfo getPageInfo() {
		return pageInfo;
	}
	//inizio LP PG21XX04 Leak
	//private void closeCallableStatement(CallableStatement callableStatement) {
	//	if (callableStatement != null)
	//		DAOHelper.closeIgnoringException(callableStatement);
	//}
	//fine LP PG21XX04 Leak
	//inizio LP 20240909 - PGNTBOLDER-1
	public void insertTransazione(TransazioneAtm transazione) throws DaoException {
		//
		// Nota:
		//		Qui rispetto allo "standard" il metodo con firma 
		//		"pre postgres" viene usato con bFlagUpdateAutocomit == false
		//
		insertTransazioneTail(false, transazione);
	}

	public void insertTransazioneTail(boolean bFlagUpdateAutocomit, TransazioneAtm transazione) throws DaoException {
	//fine LP 20240909 - PGNTBOLDER-1
		CallableStatement callableStatement = null;
		try {
			//inizio LP 20240909 - PGNTBOLDER-1
			callableStatement = prepareCall(bFlagUpdateAutocomit, Routines.ATM_DOINSERT.routine());
			//fine LP 20240909 - PGNTBOLDER-1
			transazione.save(callableStatement);
			callableStatement.execute();
			//inizio LP 20240909 - PGNTBOLDER-1
			// A T T E N Z I O N E  A T T E N Z I O N E  A T T E N Z I O N E  A T T E N Z I O N E
			//
			// Nota.
			// 		Qui era presente esplicitamente il commit
			//		in contrasto a quando dovrebbe essere con
			//		la gestione del commit\rollback all'esterno
			//		Lo commento 20240909
			//
			//commit();
			// A T T E N Z I O N E  A T T E N Z I O N E  A T T E N Z I O N E  A T T E N Z I O N E
		} catch (SQLException x) {
			// A T T E N Z I O N E  A T T E N Z I O N E  A T T E N Z I O N E  A T T E N Z I O N E
			//
			// Nota.
			// 		Qui era presente esplicitamente il commit
			//		in contrasto a quando dovrebbe essere con
			//		la gestione del commit\rollback all'esterno
			//		Lo commento 20240909
			//
			//rollback();
			// A T T E N Z I O N E  A T T E N Z I O N E  A T T E N Z I O N E  A T T E N Z I O N E
			throw new DaoException(x);
		} catch (IllegalArgumentException x) {
			throw new DaoException(x);
		} catch (HelperException x) {
			throw new DaoException(x);
		} finally {
			//inizio LP PG21XX04 Leak
			//closeCallableStatement(callableStatement);
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
	
	//PG130130 GG - inizio
	public void updateTransazione(TransazioneAtm transazione) throws DaoException {
		//boolean bResultVal = false;
		CallableStatement callableStatement = null;
		try {
			callableStatement = prepareCall(Routines.PYATMSP_UPD.routine());
			callableStatement.setNull(1, Types.VARCHAR);
			callableStatement.setString(2, transazione.getChiaveTransazione());
			callableStatement.setString(3, transazione.getFlagOffline());
			callableStatement.setNull(4, Types.CHAR);
			callableStatement.setString(5, transazione.getIdRicevuta());
			callableStatement.setTimestamp(6, new java.sql.Timestamp(transazione.getDataRicevuta().getTime()));
			callableStatement.setNull(7, Types.VARCHAR);				//sistema
			//callableStatement.setString(8, transazione.getMetodo());	//TODO da verificare
			callableStatement.setNull(8, Types.CHAR);				//sistema
			callableStatement.setTimestamp(9, new java.sql.Timestamp(transazione.getDataOrdine().getTime()));
			callableStatement.setString(10, transazione.getIdTransazione());
			callableStatement.setTimestamp(11, new java.sql.Timestamp(transazione.getDataTransazione().getTime()));
			callableStatement.setString(12, transazione.getOperatoreUltimoAggiornamento());
			callableStatement.setString(13, transazione.getIdOrdine());
			callableStatement.setString(14, transazione.getIdAutBanca());
			callableStatement.registerOutParameter(15, Types.INTEGER);
			
			callableStatement.executeUpdate();
			/*
			int numrighe =  callableStatement.getInt(15);
			if (numrighe == 1)
				bResultVal = true;
			else bResultVal = false;
			*/
			commit();
		} catch (SQLException e) {
			rollback();
			throw new DaoException(e);
		} catch (IllegalArgumentException e) {
			throw new DaoException(e);
		} catch (HelperException e) {
			throw new DaoException(e);
		} finally {
			//inizio LP PG21XX04 Leak
			//closeCallableStatement(callableStatement);
			if (callableStatement != null) {
				try {
					callableStatement.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			//fine LP PG21XX04 Leak
		}
		//return bResultVal;
	}
	//PG130130 GG - fine
}