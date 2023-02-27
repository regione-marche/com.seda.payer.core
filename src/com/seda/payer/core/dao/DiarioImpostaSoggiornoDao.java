package com.seda.payer.core.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

//inizio LP PG21XX04 Leak
//import com.seda.data.dao.DAOHelper;
//fine LP PG21XX04 Leak
import com.seda.data.helper.HelperException;
import com.seda.payer.core.bean.AnagraficaStrutturaRicettiva;
import com.seda.payer.core.bean.DettaglioDiarioImpostaSoggiorno;
import com.seda.payer.core.bean.ImpostaSoggiornoDiarioDatiAggregati;
import com.seda.payer.core.bean.TariffaImpostaSoggiorno;
import com.seda.payer.core.bean.TestataDiarioImpostaSoggiorno;
import com.seda.payer.core.bean.TipologiaStrutturaRicettiva;
import com.seda.payer.core.exception.DaoException;
import com.seda.payer.core.handler.BaseDaoHandler;

public class DiarioImpostaSoggiornoDao extends BaseDaoHandler {

	public DiarioImpostaSoggiornoDao(Connection connection, String schema) {
		super(connection, schema);
	}

	//inizio LP PG21XX04 Leak
	//private void closeConnection(CallableStatement callableStatement)
	//{
	//	if (callableStatement != null)
	//		DAOHelper.closeIgnoringException(callableStatement);
	//}
	//fine LP PG21XX04 Leak

	public void doInsertTestataDiario(TestataDiarioImpostaSoggiorno testataDiario) throws DaoException, SQLException {
		CallableStatement callableStatement = null;
		try	{
			callableStatement = prepareCall(Routines.SDT_DOINSERT.routine());
			callableStatement.setString(1, testataDiario.getChiaveTestataCompilazione());
			callableStatement.setString(2, testataDiario.getChiaveAnagraficaStrutturaRicettiva());
			callableStatement.setString(3, testataDiario.getCodiceSocieta());
			callableStatement.setString(4, testataDiario.getCodiceUtente());
			callableStatement.setString(5, testataDiario.getChiaveEnte());
			callableStatement.setDate(6, new java.sql.Date(testataDiario.getDataInizioCompilazione().getTime()));
			callableStatement.setDate(7, new java.sql.Date(testataDiario.getDataFineCompilazione().getTime()));
			callableStatement.setString(8, testataDiario.getChiaveTariffaImpostaSoggiorno());
			callableStatement.setString(9, testataDiario.getFlagChiusura());
			callableStatement.setString(10, testataDiario.getNoteAggiuntive());
			callableStatement.setString(11, testataDiario.getOperatoreUltimoAggiornamento());
			
			callableStatement.execute();
			
		} catch (IllegalArgumentException x) {
			throw new DaoException(x);
		} catch (HelperException x) {
			throw new DaoException(x);
		}
		finally {
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
	
	public void doUpdateTestataDiario(TestataDiarioImpostaSoggiorno testataDiario) throws DaoException, SQLException {
		CallableStatement callableStatement = null;
		try	{
			callableStatement = prepareCall(Routines.SDT_DOUPDATE.routine());
			callableStatement.setString(1, testataDiario.getChiaveTestataCompilazione());
			callableStatement.setString(2, testataDiario.getChiaveAnagraficaStrutturaRicettiva());
			callableStatement.setString(3, testataDiario.getCodiceSocieta());
			callableStatement.setString(4, testataDiario.getCodiceUtente());
			callableStatement.setString(5, testataDiario.getChiaveEnte());
			if (testataDiario.getDataInizioCompilazione() != null)
				callableStatement.setDate(6, new java.sql.Date(testataDiario.getDataInizioCompilazione().getTime()));
			else
				callableStatement.setNull(6, Types.DATE);
			if (testataDiario.getDataFineCompilazione() != null)
				callableStatement.setDate(7, new java.sql.Date(testataDiario.getDataFineCompilazione().getTime()));
			else
				callableStatement.setNull(7, Types.DATE);
			callableStatement.setString(8, testataDiario.getChiaveTariffaImpostaSoggiorno());
			callableStatement.setString(9, testataDiario.getFlagChiusura());
			callableStatement.setString(10, testataDiario.getNoteAggiuntive());
			callableStatement.setString(11, testataDiario.getOperatoreUltimoAggiornamento());
			
			callableStatement.execute();
			
		} catch (IllegalArgumentException x) {
			throw new DaoException(x);
		} catch (HelperException x) {
			throw new DaoException(x);
		}
		finally {
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
	
	public boolean doCheckOverlay(String chiaveTestataCompilazione, String chiaveStrutturaRicettiva, String dataInizio, String dataFine) throws DaoException, SQLException {
		CallableStatement callableStatement = null;
		try	{
			callableStatement = prepareCall(Routines.SDT_OVERLAY.routine());
			callableStatement.setString(1, chiaveTestataCompilazione);
			callableStatement.setString(2, chiaveStrutturaRicettiva);
			callableStatement.setString(3, dataInizio);
			callableStatement.setString(4, dataFine);
			
			callableStatement.registerOutParameter(5, Types.CHAR);
			
			callableStatement.execute();
			
			return callableStatement.getString(5).equals("N");
			
		} catch (IllegalArgumentException x) {
			throw new DaoException(x);
		} catch (HelperException x) {
			throw new DaoException(x);
		}
		finally {
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
	
	public void doDeleteTestataDiario(String chiaveTestataCompilazione) throws DaoException, SQLException {
		CallableStatement callableStatement = null;
		try	{
			callableStatement = prepareCall(Routines.SDT_DODELETE.routine());
			callableStatement.setString(1, chiaveTestataCompilazione);
			
			callableStatement.execute();
			
		} catch (IllegalArgumentException x) {
			throw new DaoException(x);
		} catch (HelperException x) {
			throw new DaoException(x);
		}
		finally {
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
	
	public List<TestataDiarioImpostaSoggiorno> doListCompilazioni(String chiaveStrutturaRicettiva, String dataInizio, String dataFine) throws DaoException
	{
		List<TestataDiarioImpostaSoggiorno> listRes = null;
		CallableStatement callableStatement = null;
		ResultSet data = null;
		try	{
			callableStatement = prepareCall(Routines.SDT_DOLIST.routine());
			callableStatement.setString(1, chiaveStrutturaRicettiva);
			callableStatement.setString(2, dataInizio);
			callableStatement.setString(3, dataFine);
			
			if (callableStatement.execute()) 
			{
				listRes = new ArrayList<TestataDiarioImpostaSoggiorno>();
				data = callableStatement.getResultSet();
				
				while (data.next())
					listRes.add(new TestataDiarioImpostaSoggiorno(data));
			}
			
			return listRes;
			
		} catch (SQLException x) {
			throw new DaoException(x);
		} catch (IllegalArgumentException x) {
			throw new DaoException(x);
		} catch (HelperException x) {
			throw new DaoException(x);
		}
		finally {
			//inizio LP PG21XX04 Leak
			//closeCallableStatement(callableStatement);
			//if (data != null)
			//	DAOHelper.closeIgnoringException(data);
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
		}
	}
	
	public void doInsertDettaglioDiario(DettaglioDiarioImpostaSoggiorno dettaglioDiario) throws DaoException, SQLException {
		CallableStatement callableStatement = null;
		try	{
			callableStatement = prepareCall(Routines.SDD_DOINSERT.routine());
			callableStatement.setString(1, dettaglioDiario.getChiaveDettaglioCompilazione());
			callableStatement.setString(2, dettaglioDiario.getChiaveTestataCompilazione());
			callableStatement.setString(3, dettaglioDiario.getChiaveTipologiaSoggetto());
			callableStatement.setInt(4, dettaglioDiario.getNumeroGiorniPermanenzaTotale());
			callableStatement.setInt(5, dettaglioDiario.getNumeroGiorniPermanenzaPagamento());
			callableStatement.setInt(6, dettaglioDiario.getNumeroPersone());
			callableStatement.setBigDecimal(7, dettaglioDiario.getImportoDaPagare());
			callableStatement.setString(8, dettaglioDiario.getOperatoreUltimoAggiornamento());
	    	//inizio LP PG1800XX_016
			callableStatement.setInt(9, dettaglioDiario.getChiaveFasciaTariffa());
	    	//fine LP PG1800XX_016
			callableStatement.setString(10, dettaglioDiario.getChiaveTariffa()==null ? "":dettaglioDiario.getChiaveTariffa());
			
			callableStatement.execute();
			
		} catch (IllegalArgumentException x) {
			throw new DaoException(x);
		} catch (HelperException x) {
			throw new DaoException(x);
		}
		finally {
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
	
	public void doDeleteDettaglioDiario(String chiaveTestataCompilazione) throws DaoException, SQLException {
		CallableStatement callableStatement = null;
		try	{
			callableStatement = prepareCall(Routines.SDD_DODELETE_SDT.routine());
			callableStatement.setString(1, chiaveTestataCompilazione);
			
			callableStatement.execute();
			
		} catch (IllegalArgumentException x) {
			throw new DaoException(x);
		} catch (HelperException x) {
			throw new DaoException(x);
		}
		finally {
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

	public List<DettaglioDiarioImpostaSoggiorno> doSumDettagliDiario(String chiaveStrutturaRicettiva, String dataInizio, String dataFine) throws DaoException
	{
		List<DettaglioDiarioImpostaSoggiorno> listRes = null;
		CallableStatement callableStatement = null;
		ResultSet data = null;
		try	{
			callableStatement = prepareCall(Routines.SDD_DOSUM.routine());
			callableStatement.setString(1, chiaveStrutturaRicettiva);
			callableStatement.setString(2, dataInizio);
			callableStatement.setString(3, dataFine);
			
			if (callableStatement.execute()) 
			{
				listRes = new ArrayList<DettaglioDiarioImpostaSoggiorno>();
				data = callableStatement.getResultSet();
				
				while (data.next())
					listRes.add(new DettaglioDiarioImpostaSoggiorno(data, true));
			}
			
			return listRes;
			
		} catch (SQLException x) {
			throw new DaoException(x);
		} catch (IllegalArgumentException x) {
			throw new DaoException(x);
		} catch (HelperException x) {
			throw new DaoException(x);
		}
		finally {
			//inizio LP PG21XX04 Leak
			//closeConnection(callableStatement);
			//if (data != null)
			//	DAOHelper.closeIgnoringException(data);
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
		}
	}
	
	public ImpostaSoggiornoDiarioDatiAggregati doDetailCompilazione_Web(String chiaveTestataCompilazione) throws DaoException
	{
		ImpostaSoggiornoDiarioDatiAggregati datiRes = null;
		CallableStatement callableStatement = null;
		ResultSet testata = null;
		ResultSet dettaglio = null;
		try	{
			callableStatement = prepareCall(Routines.SDT_DODETAIL_WEB.routine());
			callableStatement.setString(1, chiaveTestataCompilazione);
			
			if (callableStatement.execute()) 
			{
				datiRes = new ImpostaSoggiornoDiarioDatiAggregati();
				
				//resultset 1: testata + anagrafica struttura + tariffa + tipologia struttura
				testata = callableStatement.getResultSet();
				
				if (testata.next())
				{
					datiRes.setTestataDiario(new TestataDiarioImpostaSoggiorno(testata));
					datiRes.setAnagraficaStrutturaRicettiva(new AnagraficaStrutturaRicettiva(testata));
					datiRes.setTariffaImpostaSoggiorno(new TariffaImpostaSoggiorno(testata));
					datiRes.setTipologiaStrutturaRicettiva(new TipologiaStrutturaRicettiva(testata));
				}
				
				//resultset 2: dettaglio
				if (callableStatement.getMoreResults())
				{
					dettaglio = callableStatement.getResultSet(); 
					
					//carico il dettaglio sottoforma di struttura
					while (dettaglio.next())
						datiRes.addDettaglioDiario(new DettaglioDiarioImpostaSoggiorno(dettaglio));
				}
			}
			
			return datiRes;
			
		} catch (SQLException x) {
			throw new DaoException(x);
		} catch (IllegalArgumentException x) {
			throw new DaoException(x);
		} catch (HelperException x) {
			throw new DaoException(x);
		}
		finally {
			//inizio LP PG21XX04 Leak
			//closeConnection(callableStatement);
			//if (testata != null)
			//	DAOHelper.closeIgnoringException(testata);
			//if (dettaglio != null)
			//	DAOHelper.closeIgnoringException(dettaglio);
			if (testata != null) {
				try {
					testata.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (dettaglio != null) {
				try {
					dettaglio.close();
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
