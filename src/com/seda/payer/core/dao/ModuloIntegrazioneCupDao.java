package com.seda.payer.core.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import com.seda.data.dao.DAOHelper;
import com.seda.data.helper.HelperException;
import com.seda.payer.core.bean.ModuloIntegrazioneCup;
import com.seda.payer.core.bean.ModuloIntegrazioneCupContainer;
import com.seda.payer.core.bean.ModuloIntegrazioneCupStatus;
import com.seda.payer.core.bean.ModuloIntegrazioneCup;
import com.seda.payer.core.bean.ModuloIntegrazioneCupContainer;
//import com.seda.payer.core.bean.ModuloIntegrazioneCupOneri;
import com.seda.payer.core.exception.DaoException;
import com.seda.payer.core.handler.BaseDaoHandler;
import com.seda.payer.core.messages.Messages;

public class ModuloIntegrazioneCupDao extends BaseDaoHandler {
	
	public ModuloIntegrazioneCupDao(Connection connection, String schema) {
		super(connection, schema);
	}
 
	public ModuloIntegrazioneCup doDetail(String chiaveTransazione, String codiceFiscale, String codicePagamento) throws DaoException {
		CallableStatement callableStatement = null;
		ResultSet data = null;
		try	{
			callableStatement = prepareCall(Routines.MIC_DODETAIL.routine());
			callableStatement.setString(1, chiaveTransazione);
			callableStatement.setString(2, codiceFiscale);
			callableStatement.setString(3, codicePagamento);
			
			if (callableStatement.execute()) {
				data = callableStatement.getResultSet();
				if (data.next())
					return new ModuloIntegrazioneCup(data);
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
	
	
	public ModuloIntegrazioneCup doDetail_Operazione(String numeroOperazione, String idPortale) throws DaoException {
		CallableStatement callableStatement = null;
		ResultSet data = null;
		try	{
			callableStatement = prepareCall(Routines.MCP_DODETAIL2.routine());
			
			callableStatement.setString(1, numeroOperazione);
			callableStatement.setString(2, idPortale);
			
			if (callableStatement.execute()) {
				data = callableStatement.getResultSet();
				if (data.next())
					return new ModuloIntegrazioneCup(data);
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
	

	public ModuloIntegrazioneCupStatus doDetailCupStatus(String chiaveTransazione, String codiceFiscale, String codicePagamento, int gruppoTentativi, int progressivoTentativo) throws DaoException {
		CallableStatement callableStatement = null;
		ResultSet data = null;
		try	{
			callableStatement = prepareCall(Routines.MCS_DODETAIL.routine());
			
			callableStatement.setString(1, chiaveTransazione);
			callableStatement.setString(2, codiceFiscale);
			callableStatement.setString(3, codicePagamento);
			callableStatement.setInt(4, gruppoTentativi);
			callableStatement.setInt(5, progressivoTentativo);
			
			if (callableStatement.execute()) {
				data = callableStatement.getResultSet();
				if (data.next())
					return new ModuloIntegrazioneCupStatus(data);
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
	
	public void doSave(ModuloIntegrazioneCup mic) throws DaoException {
		CallableStatement callableStatement = null;
		try	{
			if (mic.getChiaveTransazione() == null || mic.getChiaveTransazione().length() == 0) 
				throw new IllegalArgumentException(Messages.INVALID_PARAMETER.format("ModuloIntegrazioneCup.chiaveTransazione"));
			if (mic.getCodiceFiscale() == null || mic.getCodiceFiscale().length() == 0) 
				throw new IllegalArgumentException(Messages.INVALID_PARAMETER.format("ModuloIntegrazioneCup.codiceFiscale"));
			if (mic.getCodicePagamento() == null || mic.getCodicePagamento().length() == 0) 
				throw new IllegalArgumentException(Messages.INVALID_PARAMETER.format("ModuloIntegrazioneCup.codicePagamento"));
			
			ModuloIntegrazioneCup data = doDetail(mic.getChiaveTransazione(), mic.getCodiceFiscale(), mic.getCodicePagamento());
			
			if (data != null)  
			{
				callableStatement = prepareCall(Routines.MIC_DOUPDATE.routine());
				mic.update(callableStatement);
			}
			else
			{
				callableStatement = prepareCall(Routines.MIC_DOINSERT.routine());
				
				mic.save(callableStatement);
			}

			callableStatement.execute();
			
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
	
	public int[] doSaveCupStatus(ModuloIntegrazioneCupStatus micStatus) throws DaoException {
		int[] res = new int[2];
		CallableStatement callableStatement = null;
		try	{
			
			callableStatement = prepareCall(Routines.MCS_DOINSERT.routine());
			micStatus.save(callableStatement);

			callableStatement.execute();
			
			res[0] = callableStatement.getInt(19);
			res[1] = callableStatement.getInt(20);
			
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
			if (callableStatement != null) {
				try {
					callableStatement.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			//fine LP PG21XX04 Leak
		}
		
		return res;
	}
	
	public ModuloIntegrazioneCupStatus doUpdateCupStatus(ModuloIntegrazioneCupStatus micStatus) throws DaoException {
		CallableStatement callableStatement = null;
		ResultSet data = null;
		try	{
			
			callableStatement = prepareCall(Routines.MCS_DOUPDATE.routine());
			
			micStatus.update(callableStatement);

			//callableStatement.execute();
			if (callableStatement.execute()) {
				data = callableStatement.getResultSet();
				if (data.next())
					return new ModuloIntegrazioneCupStatus(data, false, true);
			}
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
		return null;
	}
	
	public ModuloIntegrazioneCupContainer doCheckCupStatus(String chiaveTransazione, String codiceFiscale, String codicePagamento, int intervalloCheck) throws DaoException
	{
		CallableStatement callableStatement = null;
		ResultSet data = null;
		try	{
			//Giulia 6022014 INIZIO 
			System.out.println("chiaveTransazione_PYMCSSPCHECKIN: " + chiaveTransazione);
			System.out.println("intervalloCheck_PYMCSSPCHECKIN: " + intervalloCheck);
			//Giulia 6022014 FINE 
			callableStatement = prepareCall(Routines.MCS_DOCHECK.routine());
			
			callableStatement.setString(1, chiaveTransazione);
			callableStatement.setString(2, codiceFiscale);
			callableStatement.setString(3, codicePagamento);
			callableStatement.setInt(4, intervalloCheck);
			
			callableStatement.registerOutParameter(5, Types.VARCHAR);
			callableStatement.registerOutParameter(6, Types.VARCHAR);
			
			if (callableStatement.execute()) {
				
				String pidCheck = callableStatement.getString(5);
				String faseRedirect = callableStatement.getString(6);
				//Giulia 6022014 INIZIO 
				System.out.println("pidCheck_PYMPSSPCHECKOUT: " + pidCheck);
				System.out.println("faseRedirect__PYMPSSPCHECKOUT: " + faseRedirect);
				//Giulia 6022014 FINE
				
				data = callableStatement.getResultSet();
				if (data.next())
					return new ModuloIntegrazioneCupContainer(
							new ModuloIntegrazioneCupStatus(data),
							pidCheck.equals("OK"),
							faseRedirect.equals("OK"));
				else
					return new ModuloIntegrazioneCupContainer(null,
							pidCheck.equals("OK"),
							faseRedirect.equals("OK"));
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
	
	public List<ModuloIntegrazioneCupStatus> doListCupStatus(int numeroMaxTentativi) throws DaoException
	{
		CallableStatement callableStatement = null;
		ResultSet data = null;
		List<ModuloIntegrazioneCupStatus> listCupStatus = null;
		try	{
			callableStatement = prepareCall(Routines.MCS_DOBATCH.routine());
			callableStatement.setInt(1, numeroMaxTentativi);

			if (callableStatement.execute()) {
				data = callableStatement.getResultSet();
				listCupStatus = new ArrayList<ModuloIntegrazioneCupStatus>();
				
				while (data.next())
					listCupStatus.add(new ModuloIntegrazioneCupStatus(data, true, false));
			
				return listCupStatus;
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

	
	public void doListMIC(String codiceSocieta, String provincia, String codiceUtente, String chiaveEnte,
			String chiaveTransazione, String codiceFiscale, String codicePagamento, String numeroOperazione, String numeroDocumento, 
			String esitoNotifica, String dataDa, String dataA,
			int pageNumber, int rowsPerPage, String order) throws DaoException {
		CallableStatement callableStatement = null;
		try	{ 
			callableStatement = prepareCall(Routines.MIC_DOLIST.routine());
			int ix=1;
			callableStatement.setString(ix++, codiceSocieta);
			callableStatement.setString(ix++, provincia);
			callableStatement.setString(ix++, codiceUtente);
			callableStatement.setString(ix++, chiaveEnte);
			callableStatement.setString(ix++, chiaveTransazione);
			callableStatement.setString(ix++, codiceFiscale);
			callableStatement.setString(ix++, codicePagamento);
			
			callableStatement.setString(ix++, numeroOperazione);
			callableStatement.setString(ix++, numeroDocumento);
			callableStatement.setString(ix++, esitoNotifica);
			callableStatement.setString(ix++, dataDa);
			callableStatement.setString(ix++, dataA);
			callableStatement.setString(ix++, order);
			callableStatement.setInt(ix++, rowsPerPage);
			callableStatement.setInt(ix++, pageNumber);
			
			 //OUT O_ROWINI INT
			callableStatement.registerOutParameter(16, Types.INTEGER);
			//OUT O_ROWEND INT
			callableStatement.registerOutParameter(17, Types.INTEGER);
			//OUT O_TOTROWS INT
			callableStatement.registerOutParameter(18, Types.INTEGER);
			//OUT O_TOTPAGES INT
			callableStatement.registerOutParameter(19, Types.SMALLINT);
			
			if (callableStatement.execute()) {
				this.loadWebRowSets(callableStatement);
				registerPageInfo(rowsPerPage, pageNumber, callableStatement.getInt(16), 
						callableStatement.getInt(17), callableStatement.getInt(18), callableStatement.getInt(19));
			}
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
		
	public void doListMCS(String chiaveTransazione, String codiceFiscale, String codicePagamento, int pageNumber, int rowsPerPage, String order) throws DaoException {
		CallableStatement callableStatement = null;
		try	{ 
			callableStatement = prepareCall(Routines.MCS_DOLIST.routine());
			callableStatement.setString(1, chiaveTransazione);
			callableStatement.setString(2, codiceFiscale);
			callableStatement.setString(3, codicePagamento);
			callableStatement.setString(4, order);
			callableStatement.setInt(5, rowsPerPage);
			callableStatement.setInt(6, pageNumber);
			
			 //OUT O_ROWINI INT
			callableStatement.registerOutParameter(7, Types.INTEGER);
			//OUT O_ROWEND INT
			callableStatement.registerOutParameter(8, Types.INTEGER);
			//OUT O_TOTROWS INT
			callableStatement.registerOutParameter(9, Types.INTEGER);
			//OUT O_TOTPAGES INT
			callableStatement.registerOutParameter(10, Types.SMALLINT);
			
			if (callableStatement.execute()) {
				this.loadWebRowSets(callableStatement);	
				registerPageInfo(rowsPerPage, pageNumber, callableStatement.getInt(7), 
						callableStatement.getInt(8), callableStatement.getInt(9), callableStatement.getInt(10));
			
			}
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
	
	/*
	public void doSaveOneri(ModuloIntegrazioneCupOneri mipOneri) throws DaoException {
		CallableStatement callableStatement = null;
		try	{
			
			callableStatement = prepareCall(Routines.ONE_DOINSERT.routine());
			mipOneri.save(callableStatement);

			callableStatement.execute();
			
			
		} catch (SQLException x) {
			throw new DaoException(x);
		} catch (IllegalArgumentException x) {
			throw new DaoException(x);
		} catch (HelperException x) {
			throw new DaoException(x);
		}
		finally {
			if (callableStatement != null)
				DAOHelper.closeIgnoringException(callableStatement);
		}
		
	}
	
	public void doListOneriByTransazione(String chiaveTransazione) throws DaoException {
		CallableStatement callableStatement = null;
		try	{ 
			callableStatement = prepareCall(Routines.ONE_DODETAILTRA.routine());
			callableStatement.setString(1, chiaveTransazione);
		
			if (callableStatement.execute()) 
				this.loadWebRowSets(callableStatement);

		} catch (SQLException x) {
			throw new DaoException(x);
		} catch (IllegalArgumentException x) {
			throw new DaoException(x);
		} catch (HelperException x) {
			throw new DaoException(x);
		} finally {
			if (callableStatement != null)
				DAOHelper.closeIgnoringException(callableStatement);
		}
		
	}
	
	public List<ModuloIntegrazioneCupOneri> doListOneriByTransazioneObj(String chiaveTransazione) throws DaoException {
		
		CallableStatement callableStatement = null;
		ResultSet data = null;
		List<ModuloIntegrazioneCupOneri> listOneri = null;
		try	{
			callableStatement = prepareCall(Routines.ONE_DODETAILTRA.routine());
			callableStatement.setString(1, chiaveTransazione);

			if (callableStatement.execute()) {
				data = callableStatement.getResultSet();
				listOneri = new ArrayList<ModuloIntegrazioneCupOneri>();
				
				while (data.next())
					listOneri.add(new ModuloIntegrazioneCupOneri(data));
			
				return listOneri;
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
			if (callableStatement != null)
				DAOHelper.closeIgnoringException(callableStatement);
			if (data != null)
				DAOHelper.closeIgnoringException(data);
		}
	}
	*/
	
	public int countMCS_transazione(String chiaveTransazione, String codiceFiscale, String codicePagamento) throws DaoException {
		CallableStatement callableStatement = null;
		try	{ 
			callableStatement = prepareCall(Routines.MCS_DOCOUNT.routine());
			callableStatement.setString(1, chiaveTransazione);
			callableStatement.setString(2, codiceFiscale);
			callableStatement.setString(3, codicePagamento);
			
			callableStatement.registerOutParameter(4, Types.INTEGER);
			
			callableStatement.execute();
			return callableStatement.getInt(4); 
			
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
