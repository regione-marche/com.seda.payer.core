package com.seda.payer.core.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

import com.seda.data.dao.DAOHelper;
import com.seda.data.helper.HelperException;

import com.seda.payer.core.messages.Messages;
import com.seda.payer.core.bean.AgendaEvento;
import com.seda.payer.core.bean.AgendaUtenteAG;
//import com.seda.payer.core.dao.Routines;
import com.seda.payer.core.exception.DaoException;
import  com.seda.payer.core.handler.BaseDaoHandler;

public class AgendaDao extends BaseDaoHandler {

	public AgendaDao(Connection connection, String schema) {
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
	 * Ritorna la lista tipologia evento
	 * @throws DaoException
	 */
	public String[] doCachedRowSetTipologiaEvento() throws DaoException 
	{
		
		CallableStatement callableStatement = null;	
		try	
		{
			String[] outRes = new String[2];
			outRes[0] = "KO";
			outRes[1] = "Error";
			
			// lista 
			callableStatement = prepareCall(Routines.AGE_DOLIST.routine());			
			if (callableStatement.execute()) 
			{
				this.loadWebRowSets(callableStatement);
				outRes[0] = "00";
				outRes[1] = "OPERAZIONE EFFETTUATA";
			}	
			
			return outRes;
			
		} catch (SQLException x) {
			throw new DaoException(x);
		} catch (IllegalArgumentException x) {
			throw new DaoException(x);
		} catch (HelperException x) {
			throw new DaoException(x);
		} finally {
			//inizio LP PG21XX04 Leak
			//closeConnection(callableStatement);
			try {
				callableStatement.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			//fine LP PG21XX04 Leak
		}
	}
		
	/**
	 * Elenco Intervalli
	 * @throws DaoException
	 */
	public String[] doCachedRowSetElencoIntervalli() throws DaoException 
	{
		
		CallableStatement callableStatement = null;	
		try	
		{
			String[] outRes = new String[2];
			outRes[0] = "KO";
			outRes[1] = "Error";
			
			// lista 
			callableStatement = prepareCall(Routines.AGI_DOLIST.routine());			
			if (callableStatement.execute()) 
			{
				this.loadWebRowSets(callableStatement);
				outRes[0] = "00";
				outRes[1] = "OPERAZIONE EFFETTUATA";
			}	
			
			return outRes;
			
		} catch (SQLException x) {
			throw new DaoException(x);
		} catch (IllegalArgumentException x) {
			throw new DaoException(x);
		} catch (HelperException x) {
			throw new DaoException(x);
		} finally {
			//inizio LP PG21XX04 Leak
			//closeConnection(callableStatement);
			try {
				callableStatement.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			//fine LP PG21XX04 Leak
		}
	}
	
	/**
	 * preleva l'utente con il codice fiscale
	 * @param codiceFiscaleCript
	 * @return
	 * @throws DaoException
	 */
	public AgendaUtenteAG doUtenteDetailFiscale(String codiceFiscaleCript) throws DaoException {
		CallableStatement callableStatement = null;
		ResultSet data = null;
		try	{
			callableStatement = prepareCall(Routines.AGU_DODETAILFISCALE.routine());
			callableStatement.setString(1, codiceFiscaleCript);
			
			if (callableStatement.execute()) {
				data = callableStatement.getResultSet();
				if (data.next())
					return new AgendaUtenteAG(data);
			}
			
			return null;
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
			}
			//fine LP PG21XX04 Leak
		}
		
	}
	/**
	 * preleva l'utente con chiave utente
	 * @param codiceFiscaleCript
	 * @return
	 * @throws DaoException
	 */
	public AgendaUtenteAG doUtenteDetail(String chiaveUtente) throws DaoException {
		CallableStatement callableStatement = null;
		ResultSet data = null;
		try	{
			callableStatement = prepareCall(Routines.AGU_DODETAIL.routine());
			callableStatement.setString(1, chiaveUtente);
			
			if (callableStatement.execute()) {
				data = callableStatement.getResultSet();
				if (data.next())
					return new AgendaUtenteAG(data);
			}
			
			return null;
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
			}
			//fine LP PG21XX04 Leak
		}
		
	}
	/**
	 * @param utente
	 * @throws DaoException
	 */
	public String[] doUtenteSave(AgendaUtenteAG utente) throws DaoException {
		CallableStatement callableStatement = null;
		
		String[] outRes = new String[2];
		outRes[0] = "KO";
		outRes[1] = "Not valid save";
		
		try	{
			if (utente.getChiaveUtente() == null || utente.getChiaveUtente().length() == 0) 
				throw new IllegalArgumentException(Messages.INVALID_PARAMETER.format("AgendaUtenteAG.getChiaveUtente"));
			
			AgendaUtenteAG data = doUtenteDetail(utente.getChiaveUtente());			
			//inizio LP PG190340
			int iLast = 6;
			//fine LP PG190340
			if (data == null)  
			{
				// creazione perchè NON esite elemento
				callableStatement = prepareCall(Routines.AGU_DOINSERT.routine());
				utente.save(callableStatement);
				//inizio LP PG190340
				callableStatement.registerOutParameter(6, Types.INTEGER);
				//fine LP PG190340
			}
			else
			{
				// aggiornamento perchè esite elemento
				callableStatement = prepareCall(Routines.AGU_DOUPDATE.routine());
				utente.update(callableStatement);
				//inizio LP PG190340
				callableStatement.registerOutParameter(7, Types.INTEGER);
				iLast = 7;
				//fine LP PG190340
			}

			// numero di righe salvate
			//inizio LP PG190340
			//callableStatement.registerOutParameter(6, Types.INTEGER);
			//fine LP PG190340
			callableStatement.execute();
				
			//inizio LP PG190340
			//if (callableStatement.getInt(6) == 1)
			if (callableStatement.getInt(iLast) == 1)
			//fine LP PG190340
			{				
				outRes[0] = "00";
				outRes[1] = "OPERAZIONE EFFETTUATA";
			}
			
			return outRes;
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
			if(callableStatement != null) {
				try {
					callableStatement.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			//fine LP PG21XX04 Leak
		}
	}
	/**
	 * preleva la lista totale degli utenti
	 * @param codiceFiscaleCript
	 * @return
	 * @throws DaoException
	 */
	public List<AgendaUtenteAG> doUtenteList() throws DaoException {
		CallableStatement callableStatement = null;
		ResultSet data = null;
		try	{
			callableStatement = prepareCall(Routines.AGU_DOLIST.routine());
			//callableStatement.setString(1, codiceFiscaleCript);
						
			if (callableStatement.execute()) 
			{
				data = callableStatement.getResultSet();
				
				//la chiave è il CTSE (codice tipologia servizio) e il valore è l'oggetto ConfigPagamento
				List<AgendaUtenteAG> lUtente = new ArrayList<AgendaUtenteAG>();
				AgendaUtenteAG utente;
				while (data.next())
				{
					utente = new AgendaUtenteAG(data);
					lUtente.add(utente);
				}
				
				return lUtente;
			}
			
			return null;
			
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
			}
			//fine LP PG21XX04 Leak
		}
		
	}
	
	/**
	 * ritorna un evento specifico
	 * @param chiaveEvento
	 * @param dataEvento
	 * @param progressivoEvento
	 * @return
	 * @throws DaoException
	 */
	public AgendaEvento doEventoDetail(String chiaveEvento) throws DaoException {
		CallableStatement callableStatement = null;
		ResultSet data = null;
		try	{
			callableStatement = prepareCall(Routines.AGC_DODETAIL.routine());
			callableStatement.setString(1, chiaveEvento);
			
			if (callableStatement.execute()) {
				data = callableStatement.getResultSet();
				if (data.next())
					return new AgendaEvento(data);
			}
			
			return null;
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
			}
			//fine LP PG21XX04 Leak
		}
		
	}
	/**
     * Salvataggio dell'evento
	 * @param evento
	 * @throws DaoException
	 */
	public String[] doEventoSave(AgendaEvento evento) throws DaoException {
		CallableStatement callableStatement = null;
		
		String[] outRes = new String[2];
		outRes[0] = "KO";
		outRes[1] = "Not valid evento";
		
		try	{
			if (evento.getChiaveUtente() == null || evento.getChiaveUtente().length() == 0) 
				throw new IllegalArgumentException(Messages.INVALID_PARAMETER.format("AgendaEvento.getChiaveUtente"));
			
			AgendaEvento data = doEventoDetail(evento.getChiaveEvento());			
			if (data == null)  
			{
				// creazione perchè NON esite elemento
				callableStatement = prepareCall(Routines.AGC_DOINSERT.routine());
				evento.save(callableStatement);
			}
			else
			{
				// aggiornamento perchè esite elemento
				callableStatement = prepareCall(Routines.AGC_DOUPDATE.routine());
				evento.update(callableStatement);
			}

			// numero di righe salvate
			callableStatement.registerOutParameter(19, Types.INTEGER);
			callableStatement.execute();

			if (callableStatement.getInt(19) == 1)
			{				
				outRes[0] = "00";
				outRes[1] = "OPERAZIONE EFFETTUATA";
			}

			return outRes;
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
			if(callableStatement != null) {
				try {
					callableStatement.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			//fine LP PG21XX04 Leak
		}
	}
	/**
	 * Cancellazione dell'evento
	 * @param evento
	 * @throws DaoException
	 */
	public String[] doEventoDelete(String chiaveEvento) throws DaoException {
		CallableStatement callableStatement = null;
		
		String[] outRes = new String[2];
		outRes[0] = "KO";
		outRes[1] = "Not valid evento";
		
		
		try	{
			if (chiaveEvento == null || chiaveEvento.length() == 0) 
				throw new IllegalArgumentException(Messages.INVALID_PARAMETER.format("doEventoDelete AgendaEvento.getChiaveUtente"));
			
			AgendaEvento eventoToDelete = doEventoDetail(chiaveEvento);			
			if (eventoToDelete != null)  
			{
				//cancellazione logica
				eventoToDelete.setChiaveEvento(chiaveEvento);
				eventoToDelete.setCancellazione("Y");
				callableStatement = prepareCall(Routines.AGC_DOUPDATE.routine());
				eventoToDelete.update(callableStatement);
			}

			// numero di righe modificate
			callableStatement.registerOutParameter(19, Types.INTEGER);
			callableStatement.execute();
			if (callableStatement.getInt(19) == 1) {
				outRes[0] = "00";
				outRes[1] = "OPERAZIONE EFFETTUATA";
			}
			return outRes;
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
			if(callableStatement != null) {
				try {
					callableStatement.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			//fine LP PG21XX04 Leak
		}
	} 
	/**
	 * preleva la lista degli eventi dove per chiave ho i giorni e in valore il numero di eventi
	 * @param codiceFiscaleCript
	 * @return
	 * @throws DaoException
	 */
	public HashMap<Integer, Integer> doEventoListCount(String chiaveUtente, Calendar dateInit, Calendar dateEnd) throws DaoException {
		CallableStatement callableStatement = null;
		ResultSet data = null;
		
		HashMap<Integer, Integer> hmNumEvento = new HashMap<Integer,Integer>();
		try	{
			callableStatement = prepareCall(Routines.AGC_DOLIST_USERDATE.routine());
			callableStatement.setString(1, chiaveUtente);
			callableStatement.setDate(2, new java.sql.Date(dateInit.getTime().getTime()));
			callableStatement.setDate(3, new java.sql.Date(dateEnd.getTime().getTime()));
						
			if (callableStatement.execute()) 
			{
				data = callableStatement.getResultSet();
				
				//la chiave è il CTSE (codice tipologia servizio) e il valore è l'oggetto ConfigPagamento
				while (data.next())
				{
					// numero del giorno e numero di eventi associati
					//Calendar cal = getCalendarFromDate(data.getDate("Data"));					
					//hmNumEvento.put(cal.get(Calendar.DATE), data.getInt("NumEventi"));
					hmNumEvento.put(data.getInt("Giorno"), data.getInt("NumEventi"));
				}
				
//				// aggiungo i giorni non trovati nel DB con eventi pari a 0
//				Integer iDayTemp = dateInit.get(Calendar.DATE);
//				while (iDayTemp <= dateEnd.get(Calendar.DATE))
//				{
//					if (!hmNumEvento.containsKey(iDayTemp))
//						hmNumEvento.put(iDayTemp, 0);
//					
//					iDayTemp++;
//				}
			}
			
			return hmNumEvento;
			
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
			}
			//fine LP PG21XX04 Leak
		}
		
	}
	/**
	 * ritorna lista di eventi
	 * @param chiaveUtente
	 * @param dateInit
	 * @param dateEnd
	 * @return
	 * @throws DaoException
	 */
	public String[] doEventoList(String chiaveUtente, Calendar dateInit, Calendar dateEnd, 
			String flagStatoEvento, String flagScadutoEvento, String chiaveTipologiaEvento) throws DaoException {
		CallableStatement callableStatement = null;
		
		String[] outRes = new String[2];
		outRes[0] = "KO";
		outRes[1] = "Not valid doEventoList";
		
		try	{
			callableStatement = prepareCall(Routines.AGC_DOLIST.routine());
			callableStatement.setString(1, chiaveUtente);
			callableStatement.setDate(2, new java.sql.Date(dateInit.getTime().getTime()));
			callableStatement.setDate(3, new java.sql.Date(dateEnd.getTime().getTime()));
			callableStatement.setString(4, flagStatoEvento);
			callableStatement.setString(5, flagScadutoEvento);
			callableStatement.setString(6, chiaveTipologiaEvento);
			
			// lista 
			if (callableStatement.execute()) 
			{
				this.loadWebRowSets(callableStatement);
				outRes[0] = "00";
				outRes[1] = "OPERAZIONE EFFETTUATA";
			}	
			
			return outRes;
			
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
			if(callableStatement != null) {
				try {
					callableStatement.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			//fine LP PG21XX04 Leak
		}
		
	}
	/**
	 * chiaveEventoAutomatico == NumDoc criptato
	 * @param utente
	 * @param chiaveEventoAutomatico
	 * @throws DaoException
	 */
	public boolean doEventoAutomatico(String chiaveUtente, Calendar dataEvento,String chiaveEventoAutomatico) throws DaoException {
		
		CallableStatement callableStatement = null;		
		/*
		outRes = new String[2];
		outRes[0] = "KO";
		outRes[1] = "Not valid List";
		*/
		
		try	{
			if (chiaveUtente == null || chiaveUtente.length() == 0) 
				throw new IllegalArgumentException(Messages.INVALID_PARAMETER.format("AgendaUtente.getChiaveUtente"));
			
			callableStatement = prepareCall(Routines.AGC_DOLIST_AUTOMATICO.routine());
			callableStatement.setString(1, chiaveUtente);
			callableStatement.setDate(2, new java.sql.Date(dataEvento.getTime().getTime()));
			callableStatement.setString(3, chiaveEventoAutomatico);		

			// numero di righe trovate
			callableStatement.registerOutParameter(4, Types.INTEGER);
			callableStatement.execute();
				
			if (callableStatement.getInt(4) >= 0)
			{				
				// numero della colonna callableStatement.getInt(4);
				/*
				outRes[0] = "00";
				outRes[1] = "OPERAZIONE EFFETTUATA";
				*/
				
				// è presente l'evento con la chiave indicata 
				return callableStatement.getInt(4) > 0;
				
			}
			
			return false;
			
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
			if(callableStatement != null) {
				try {
					callableStatement.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			//fine LP PG21XX04 Leak
		}
	}
	
	public static Calendar getCalendarFromDate(java.util.Date date)
    {
          if (date == null) return null;
          Calendar cal = Calendar.getInstance();
          cal.setTime(date);
          return cal;
    }
    
    public static java.util.Date getDateFromCalendar(Calendar cal)
    {
          if (cal == null) return null;
          return cal.getTime();
    }

}
