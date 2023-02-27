package com.seda.payer.core.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import com.seda.data.helper.HelperException;
import com.seda.payer.core.bean.ModuloIntegrazionePagamentiNodo;
import com.seda.payer.core.bean.ModuloIntegrazionePagamentiNodoContainer;
import com.seda.payer.core.bean.ModuloIntegrazionePagamentiNodoPaymentStatus;
import com.seda.payer.core.exception.DaoException;
import com.seda.payer.core.handler.BaseDaoHandler;
import com.seda.payer.core.messages.Messages;

public class ModuloIntegrazionePagamentiNodoDao extends BaseDaoHandler {
	
	public ModuloIntegrazionePagamentiNodoDao(Connection connection, String schema) {
		super(connection, schema);
	}
 
	public ModuloIntegrazionePagamentiNodo doDetail(String chiaveTransazione) throws DaoException {
		CallableStatement callableStatement = null;
		ResultSet data = null;
		try	{
			callableStatement = prepareCall(Routines.MIN_DODETAIL.routine());
			callableStatement.setString(1, chiaveTransazione);
			
			if (callableStatement.execute()) {
				data = callableStatement.getResultSet();
				if (data.next())
					return new ModuloIntegrazionePagamentiNodo(data);
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
	
	public ModuloIntegrazionePagamentiNodo doDetail_Operazione(String numeroOperazione, String idPortale) throws DaoException {
		CallableStatement callableStatement = null;
		ResultSet data = null;
		try	{
			callableStatement = prepareCall(Routines.MIN_DODETAIL2.routine());
			callableStatement.setString(1, numeroOperazione);
			callableStatement.setString(2, idPortale);
			
			if (callableStatement.execute()) {
				data = callableStatement.getResultSet();
				if (data.next())
					return new ModuloIntegrazionePagamentiNodo(data);
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
	

	public ModuloIntegrazionePagamentiNodoPaymentStatus doDetailPaymentStatus(String chiaveTransazione, int gruppoTentativi, int progressivoTentativo) throws DaoException {
		CallableStatement callableStatement = null;
		ResultSet data = null;
		try	{
			callableStatement = prepareCall(Routines.MPN_DODETAIL.routine());
			callableStatement.setString(1, chiaveTransazione);
			callableStatement.setInt(2, gruppoTentativi);
			callableStatement.setInt(3, progressivoTentativo);
			
			if (callableStatement.execute()) {
				data = callableStatement.getResultSet();
				if (data.next())
					return new ModuloIntegrazionePagamentiNodoPaymentStatus(data);
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
	
	public void doSave(ModuloIntegrazionePagamentiNodo mip) throws DaoException {
		CallableStatement callableStatement = null;
		try	{
			if (mip.getChiaveTransazione() == null || mip.getChiaveTransazione().length() == 0) 
				throw new IllegalArgumentException(Messages.INVALID_PARAMETER.format("ModuloIntegrazionePagamenti.chiaveTransazione"));
			
			ModuloIntegrazionePagamentiNodo data = doDetail(mip.getChiaveTransazione());
			
			if (data != null)  
			{
				callableStatement = prepareCall(Routines.MIN_DOUPDATE.routine());
				mip.update(callableStatement);
			}
			else
			{
				callableStatement = prepareCall(Routines.MIN_DOINSERT.routine());
				mip.save(callableStatement);
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
	
	public int[] doSavePaymentStatus(ModuloIntegrazionePagamentiNodoPaymentStatus mipStatus) throws DaoException {
		int[] res = new int[2];
		CallableStatement callableStatement = null;
		try	{
			
			callableStatement = prepareCall(Routines.MPN_DOINSERT.routine());
			mipStatus.save(callableStatement);

			callableStatement.execute();
			
//			YLM PG22XX07 INI
//			res[0] = callableStatement.getInt(17);
//			res[1] = callableStatement.getInt(18);
			res[0] = callableStatement.getInt(18);
			res[1] = callableStatement.getInt(19);
//			YLM PG22XX07 FINE
			
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
	
	public ModuloIntegrazionePagamentiNodoPaymentStatus doUpdatePaymentStatus(ModuloIntegrazionePagamentiNodoPaymentStatus mipStatus) throws DaoException {
		CallableStatement callableStatement = null;
		ResultSet data = null;
		try	{
			
			callableStatement = prepareCall(Routines.MPN_DOUPDATE.routine());
			mipStatus.update(callableStatement);

			//callableStatement.execute();
			if (callableStatement.execute()) {
				data = callableStatement.getResultSet();
				if (data.next())
					return new ModuloIntegrazionePagamentiNodoPaymentStatus(data, false, true);
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
	
	public ModuloIntegrazionePagamentiNodoContainer doCheckPaymentStatus(String chiaveTransazione, int intervalloCheck) throws DaoException
	{
		CallableStatement callableStatement = null;
		ResultSet data = null;
		try	{
			callableStatement = prepareCall(Routines.MPN_DOCHECK.routine());
			callableStatement.setString(1, chiaveTransazione);
			callableStatement.setInt(2, intervalloCheck);
			
			callableStatement.registerOutParameter(3, Types.VARCHAR);
			callableStatement.registerOutParameter(4, Types.VARCHAR);
			
			if (callableStatement.execute()) {
				
				String pidCheck = callableStatement.getString(3);
				String faseRedirect = callableStatement.getString(4);
				
				data = callableStatement.getResultSet();
				if (data.next())
					return new ModuloIntegrazionePagamentiNodoContainer(
							new ModuloIntegrazionePagamentiNodoPaymentStatus(data),
							pidCheck.equals("OK"),
							faseRedirect.equals("OK"));
				else
					return new ModuloIntegrazionePagamentiNodoContainer(null,
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
	
	public List<ModuloIntegrazionePagamentiNodoPaymentStatus> doListPaymentStatus(int numeroMaxTentativi) throws DaoException
	{
		CallableStatement callableStatement = null;
		ResultSet data = null;
		List<ModuloIntegrazionePagamentiNodoPaymentStatus> listPaymentStatus = null;
		try	{
			callableStatement = prepareCall(Routines.MPN_DOBATCH.routine());
			callableStatement.setInt(1, numeroMaxTentativi);

			if (callableStatement.execute()) {
				data = callableStatement.getResultSet();
				listPaymentStatus = new ArrayList<ModuloIntegrazionePagamentiNodoPaymentStatus>();
				
				while (data.next())
					listPaymentStatus.add(new ModuloIntegrazionePagamentiNodoPaymentStatus(data, true, false));
			
				return listPaymentStatus;
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

	//inizio LP PG200060
	/*
	public void doListMIP(String codiceSocieta, String provincia, String codiceUtente, String chiaveEnte,
			String chiaveTransazione, String numeroOperazione, String numeroDocumento, 
			String esitoNotifica, String dataDa, String dataA,
			int pageNumber, int rowsPerPage, String order) throws DaoException {
	*/
	//inizio LP PG200060
	/*
	public void doListMIP(String codiceSocieta, String provincia, String codiceUtente, String chiaveEnte,
			String chiaveTransazione, String numeroOperazione, String numeroDocumento, 
			String esitoNotifica, String esitoInvioRt, String statoInvioRT, String numProtocolloRt, String dataDa, String dataA,
			int pageNumber, int rowsPerPage, String order) throws DaoException {
	*/
	//inizio LP PG180290
	public void doListMIP(String codiceSocieta, String provincia, String codiceUtente, String chiaveEnte,
			String chiaveTransazione, String numeroOperazione, String numeroDocumento, 
			String esitoNotifica, String esitoInvioRt, String statoInvioRT, String numProtocolloRt, String tipoGateways, String dataDa, String dataA, String esitoInvioConservazione,
			int pageNumber, int rowsPerPage, String order) throws DaoException {
	//fine LP PG180290
	//fine LP PG200060
		CallableStatement callableStatement = null;
		try	{ 
			callableStatement = prepareCall(Routines.MIN_DOLIST.routine());
			callableStatement.setString(1, codiceSocieta);
			callableStatement.setString(2, provincia);
			callableStatement.setString(3, codiceUtente);
			callableStatement.setString(4, chiaveEnte);
			callableStatement.setString(5, chiaveTransazione);
			callableStatement.setString(6, numeroOperazione);
			callableStatement.setString(7, numeroDocumento);
			callableStatement.setString(8, esitoNotifica);
			callableStatement.setString(9, dataDa);
			callableStatement.setString(10, dataA);
			//inizio LP PG200060
			//Eseguito allineamento SP da MySql vs DB2
			/*
			callableStatement.setString(11, order);
			callableStatement.setInt(12, rowsPerPage);
			callableStatement.setInt(13, pageNumber);
			 //OUT O_ROWINI INT
			callableStatement.registerOutParameter(14, Types.INTEGER);
			//OUT O_ROWEND INT
			callableStatement.registerOutParameter(15, Types.INTEGER);
			//OUT O_TOTROWS INT
			callableStatement.registerOutParameter(16, Types.INTEGER);
			//OUT O_TOTPAGES INT
			callableStatement.registerOutParameter(17, Types.SMALLINT);
			
			if (callableStatement.execute()) {
				this.loadWebRowSets(callableStatement);
				registerPageInfo(rowsPerPage, pageNumber, callableStatement.getInt(14), 
						callableStatement.getInt(15), callableStatement.getInt(16), callableStatement.getInt(17));
			}
			*/
			callableStatement.setString(11, esitoInvioRt);
			callableStatement.setString(12, statoInvioRT);
			callableStatement.setString(13, numProtocolloRt);
			//inizio LP PG180290
			/*
			callableStatement.setString(14, order);
			callableStatement.setInt(15, rowsPerPage);
			callableStatement.setInt(16, pageNumber);
			
			callableStatement.registerOutParameter(17, Types.INTEGER);
			callableStatement.registerOutParameter(18, Types.INTEGER);
			callableStatement.registerOutParameter(19, Types.INTEGER);
			callableStatement.registerOutParameter(20, Types.SMALLINT);
			
			if (callableStatement.execute()) {
				this.loadWebRowSets(callableStatement);
				registerPageInfo(rowsPerPage, pageNumber, callableStatement.getInt(17), 
						callableStatement.getInt(18), callableStatement.getInt(19), callableStatement.getInt(20));
			}
			*/
			callableStatement.setString(14, tipoGateways);
			callableStatement.setString(15, esitoInvioConservazione);
			callableStatement.setString(16, order);
			callableStatement.setInt(17, rowsPerPage);
			callableStatement.setInt(18, pageNumber);
			
			callableStatement.registerOutParameter(19, Types.INTEGER);
			callableStatement.registerOutParameter(20, Types.INTEGER);
			callableStatement.registerOutParameter(21, Types.INTEGER);
			callableStatement.registerOutParameter(22, Types.SMALLINT);
			
			if (callableStatement.execute()) {
				this.loadWebRowSets(callableStatement);
				registerPageInfo(rowsPerPage, pageNumber, callableStatement.getInt(19), 
						callableStatement.getInt(20), callableStatement.getInt(21), callableStatement.getInt(22));
			}
			//fine LP PG180290
			//fine LP PG200060
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
		
	public void doListMPS(String chiaveTransazione, String dataDa, String dataA, int pageNumber, int rowsPerPage, String order) throws DaoException {
		CallableStatement callableStatement = null;
		try	{ 
			callableStatement = prepareCall(Routines.MPN_DOLIST.routine());
			callableStatement.setString(1, chiaveTransazione);
			callableStatement.setString(2, order);
			callableStatement.setString(3, dataDa);
			callableStatement.setString(4, dataA);
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
	
	
	public int countMPS_transazione(String chiaveTransazione) throws DaoException {
		CallableStatement callableStatement = null;
		try	{ 
			callableStatement = prepareCall(Routines.MPN_DOCOUNT.routine());
			callableStatement.setString(1, chiaveTransazione);
			
			callableStatement.registerOutParameter(2, Types.INTEGER);
			
			callableStatement.execute();
			return callableStatement.getInt(2); 
			
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
