package com.seda.payer.core.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.seda.data.helper.HelperException;
import com.seda.payer.core.bean.AgendaEvento;
import com.seda.payer.core.bean.AgendaScadenzePossibili;
import com.seda.payer.core.exception.DaoException;
import com.seda.payer.core.handler.BaseDaoHandler;

public class AgendaBatchDao extends BaseDaoHandler {

	public AgendaBatchDao(Connection connection, String schema) {
		super(connection, schema);
		
	}

	//inizio LP PG21XX04 Leak
	//private void closeConnection(CallableStatement callableStatement)
	//{
	//	if (callableStatement != null)
	//		DAOHelper.closeIgnoringException(callableStatement);
	//}
	//fine LP PG21XX04 Leak

	/**
	 * ritornano tutte gli eventi validi e di cui non è stato inviata email e/o SMS
	 * @return
	 * @throws DaoException
	 */
	//inizio LP PG190340
	//public List<AgendaEvento> prelevaEventoScadenza() throws DaoException {
	public List<AgendaEvento> prelevaEventoScadenza(Integer numHourPre, Integer numHourPost) throws DaoException {
	//fine LP PG190340
		CallableStatement callableStatement = null;
		ResultSet data = null;
		/* risultato
		outRes = new String[2];
		outRes[0] = "KO";
		outRes[1] = "Not valid List";
		*/
		List<AgendaEvento> lEvento = null;
		try	{
			callableStatement = prepareCall(Routines.AGC_DOLIST_SCADEVENTO.routine());
			//inizio LP PG190340
			callableStatement.setInt(1, numHourPre);
			callableStatement.setInt(2, numHourPost);
			//fine LP PG190340
			if (callableStatement.execute()) {
				lEvento = new ArrayList<AgendaEvento>();
				data = callableStatement.getResultSet();				
				// ciclo su tutti i risultati
				while (data.next())
					lEvento.add(new AgendaEvento(data));
				/* esito operazione;
				outRes[0] = "00";
				outRes[1] = "OPERAZIONE EFFETTUATA";
				*/				
			}
			return lEvento;
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
			if(data != null) {
				try {
					data.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(callableStatement != null) {
				try {
					callableStatement.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
				callableStatement = null; //LP 20240909 - PGNTCORE-24
			}
			//fine LP PG21XX04 Leak
		}
	}

	/**
	 * ritornano tutti gli eventi validi e di cui non è stato inviato ancora il preavviso (sola mail)
	 * @return
	 * @throws DaoException
	 */
	//inizio LP PG190340
	//public List<AgendaEvento> prelevaEventoPreavviso() throws DaoException {
	public List<AgendaEvento> prelevaEventoPreavviso(Integer numDayMax) throws DaoException {
	//inizio LP PG190340
		CallableStatement callableStatement = null;
		ResultSet data = null;
		/* risultato
		outRes = new String[2];
		outRes[0] = "KO";
		outRes[1] = "Not valid List";
		*/
		List<AgendaEvento> lEvento = null;
		try	{
			callableStatement = prepareCall(Routines.AGC_DOLIST_SCADPREAVVISO.routine());
			//inizio LP PG190340
			callableStatement.setInt(1, numDayMax);
			//fine LP PG190340
			if (callableStatement.execute()) {
				lEvento = new ArrayList<AgendaEvento>();
				data = callableStatement.getResultSet();				
				// ciclo su tutti i risultati
				while (data.next())
					lEvento.add(new AgendaEvento(data));
				/* esito operazione;
				outRes[0] = "00";
				outRes[1] = "OPERAZIONE EFFETTUATA";
				*/				
			}
			return lEvento;
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
			//if (data != null)
			//	DAOHelper.closeIgnoringException(data);
			if(data != null) {
				try {
					data.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(callableStatement != null) {
				try {
					callableStatement.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
				callableStatement = null; //LP 20240909 - PGNTCORE-24
			}
			//fine LP PG21XX04 Leak
		}		
	}

	/**
	 * ritornano tutti i possibili pagamenti da effetuare nel range indicato 
	 * (numDay - numero di giorni entro cui considerare le rate rispetto ad oggi)
	 * @param numDay
	 * @return
	 * @throws DaoException
	 */
	public List<AgendaScadenzePossibili> analizzaScadenze(String codiceFiscaleDecrypt, Integer numDay) throws DaoException {
		CallableStatement callableStatement = null;
		ResultSet data = null;
		/* risultato
		outRes = new String[2];
		outRes[0] = "KO";
		outRes[1] = "Not valid List";
		*/
		List<AgendaScadenzePossibili> lScadenzePossibiliRateDocumenti = null;
		try	{
			callableStatement = prepareCall(Routines.AGC_ANALISISCADENZE.routine());
			callableStatement.setString(1, codiceFiscaleDecrypt);
			callableStatement.setInt(2, numDay);
			if (callableStatement.execute()) {
				lScadenzePossibiliRateDocumenti = new ArrayList<AgendaScadenzePossibili>();
				data = callableStatement.getResultSet();				
				// cicolo su tutti i risultati
				while (data.next())
					lScadenzePossibiliRateDocumenti.add(new AgendaScadenzePossibili(data));
				/* esito operazione;
				outRes[0] = "00";
				outRes[1] = "OPERAZIONE EFFETTUATA";
				*/				
			}
			return lScadenzePossibiliRateDocumenti;
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
			if(data != null) {
				try {
					data.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(callableStatement != null) {
				try {
					callableStatement.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
				callableStatement = null; //LP 20240909 - PGNTCORE-24
			}
			//fine LP PG21XX04 Leak
		}		
	}
}