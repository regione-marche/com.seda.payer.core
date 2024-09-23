package com.seda.payer.core.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.seda.data.helper.HelperException;
import com.seda.payer.core.bean.ModuloIntegrazionePagamenti;
import com.seda.payer.core.bean.ModuloIntegrazionePagamentiContainer;
import com.seda.payer.core.bean.ModuloIntegrazionePagamentiOneri;
import com.seda.payer.core.bean.ModuloIntegrazionePagamentiPaymentStatus;
import com.seda.payer.core.exception.DaoException;
import com.seda.payer.core.handler.BaseDaoHandler;
import com.seda.payer.core.messages.Messages;

public class ModuloIntegrazionePagamentiDao extends BaseDaoHandler {
	
	public ModuloIntegrazionePagamentiDao(Connection connection, String schema) {
		super(connection, schema);
	}
 
	//inizio LP 20240909 - PGNTBOLDER-1
	public ModuloIntegrazionePagamenti doDetail(String chiaveTransazione) throws DaoException {
		return doDetailTail(true, chiaveTransazione);
	}

	public ModuloIntegrazionePagamenti doDetailTail(boolean bFlagUpdateAutocomit, String chiaveTransazione) throws DaoException {
	//fine LP 20240909 - PGNTBOLDER-1
		CallableStatement callableStatement = null;
		ResultSet data = null;
		try	{
			//inizio LP 20240909 - PGNTBOLDER-1
			//callableStatement = prepareCall(Routines.MIP_DODETAIL.routine());
			callableStatement = prepareCall(bFlagUpdateAutocomit, Routines.MIP_DODETAIL.routine());
			//fine LP 20240909 - PGNTBOLDER-1
			callableStatement.setString(1, chiaveTransazione);
			if (callableStatement.execute()) {
				data = callableStatement.getResultSet();
				if (data.next())
					return new ModuloIntegrazionePagamenti(data);
			}
			return null;
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
	
	public ModuloIntegrazionePagamenti doDetail_Operazione(String numeroOperazione, String idPortale) throws DaoException {
		CallableStatement callableStatement = null;
		ResultSet data = null;
		try	{
			callableStatement = prepareCall(Routines.MIP_DODETAIL2.routine());
			callableStatement.setString(1, numeroOperazione);
			callableStatement.setString(2, idPortale);
			
			if (callableStatement.execute()) {
				data = callableStatement.getResultSet();
				if (data.next())
					return new ModuloIntegrazionePagamenti(data);
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
	

	public ModuloIntegrazionePagamentiPaymentStatus doDetailPaymentStatus(String chiaveTransazione, int gruppoTentativi, int progressivoTentativo) throws DaoException {
		CallableStatement callableStatement = null;
		ResultSet data = null;
		try	{
			callableStatement = prepareCall(Routines.MPS_DODETAIL.routine());
			callableStatement.setString(1, chiaveTransazione);
			callableStatement.setInt(2, gruppoTentativi);
			callableStatement.setInt(3, progressivoTentativo);
			
			if (callableStatement.execute()) {
				data = callableStatement.getResultSet();
				if (data.next())
					return new ModuloIntegrazionePagamentiPaymentStatus(data);
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

	//inizio LP 20240909 - PGNTBOLDER-1
	public void doSave(ModuloIntegrazionePagamenti mip) throws DaoException {
		doSaveTail(true, mip);
	}

	public void doSaveTail(boolean bFlagUpdateAutocomit, ModuloIntegrazionePagamenti mip) throws DaoException {
	//fine LP 20240909 - PGNTBOLDER-1
		CallableStatement callableStatement = null;
		try	{
			if (mip.getChiaveTransazione() == null || mip.getChiaveTransazione().length() == 0) 
				throw new IllegalArgumentException(Messages.INVALID_PARAMETER.format("ModuloIntegrazionePagamenti.chiaveTransazione"));
			//inizio LP 20240909 - PGNTBOLDER-1
			//ModuloIntegrazionePagamenti data = doDetail(mip.getChiaveTransazione());
			ModuloIntegrazionePagamenti data = doDetailTail(bFlagUpdateAutocomit, mip.getChiaveTransazione());
			//fine LP 20240909 - PGNTBOLDER-1
			if (data != null) {
				//inizio LP 20240909 - PGNTBOLDER-1
				//callableStatement = prepareCall(Routines.MIP_DOUPDATE.routine());
				callableStatement = prepareCall(bFlagUpdateAutocomit, Routines.MIP_DOUPDATE.routine());
				//fine LP 20240909 - PGNTBOLDER-1
				mip.update(callableStatement);
			} else {
				//inizio LP 20240909 - PGNTBOLDER-1
				//callableStatement = prepareCall(Routines.MIP_DOINSERT.routine());
				callableStatement = prepareCall(bFlagUpdateAutocomit, Routines.MIP_DOINSERT.routine());
				//fine LP 20240909 - PGNTBOLDER-1
				mip.save(callableStatement);
			}
			callableStatement.execute();
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
	
	public int[] doSavePaymentStatus(ModuloIntegrazionePagamentiPaymentStatus mipStatus) throws DaoException {
		int[] res = new int[2];
		CallableStatement callableStatement = null;
		try	{
			
			callableStatement = prepareCall(Routines.MPS_DOINSERT.routine());
			mipStatus.save(callableStatement);

			callableStatement.execute();
			
			res[0] = callableStatement.getInt(17);
			res[1] = callableStatement.getInt(18);
			
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
	
	public ModuloIntegrazionePagamentiPaymentStatus doUpdatePaymentStatus(ModuloIntegrazionePagamentiPaymentStatus mipStatus) throws DaoException {
		CallableStatement callableStatement = null;
		ResultSet data = null;
		try	{
			
			callableStatement = prepareCall(Routines.MPS_DOUPDATE.routine());
			mipStatus.update(callableStatement);

			//callableStatement.execute();
			if (callableStatement.execute()) {
				data = callableStatement.getResultSet();
				if (data.next())
					return new ModuloIntegrazionePagamentiPaymentStatus(data, false, true);
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
	
	public ModuloIntegrazionePagamentiContainer doCheckPaymentStatus(String chiaveTransazione, int intervalloCheck) throws DaoException
	{
		CallableStatement callableStatement = null;
		ResultSet data = null;
		try	{
			//Giulia 6022014 INIZIO 
			System.out.println("chiaveTransazione_PYMPSSPCHECKIN: " + chiaveTransazione);
			System.out.println("intervalloCheck_PYMPSSPCHECKIN: " + intervalloCheck);
			//Giulia 6022014 FINE  
			callableStatement = prepareCall(Routines.MPS_DOCHECK.routine());
			callableStatement.setString(1, chiaveTransazione);
			callableStatement.setInt(2, intervalloCheck);
			
			callableStatement.registerOutParameter(3, Types.VARCHAR);
			callableStatement.registerOutParameter(4, Types.VARCHAR);
			
			if (callableStatement.execute()) {
				
			
				String pidCheck = callableStatement.getString(3);
				String faseRedirect = callableStatement.getString(4);
				//Giulia 6022014 INIZIO 
				System.out.println("pidCheck_PYMPSSPCHECKOUT: " + pidCheck);
				System.out.println("faseRedirect__PYMPSSPCHECKOUT: " + faseRedirect);
				//Giulia 6022014 FINE
				data = callableStatement.getResultSet();
				if (data.next())
					return new ModuloIntegrazionePagamentiContainer(
							new ModuloIntegrazionePagamentiPaymentStatus(data),
							pidCheck.equals("OK"),
							faseRedirect.equals("OK"));
				else
					return new ModuloIntegrazionePagamentiContainer(null,
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
	
	public List<ModuloIntegrazionePagamentiPaymentStatus> doListPaymentStatus(int numeroMaxTentativi) throws DaoException
	{
		CallableStatement callableStatement = null;
		ResultSet data = null;
		List<ModuloIntegrazionePagamentiPaymentStatus> listPaymentStatus = null;
		try	{
			callableStatement = prepareCall(Routines.MPS_DOBATCH.routine());
			callableStatement.setInt(1, numeroMaxTentativi);

			if (callableStatement.execute()) {
				data = callableStatement.getResultSet();
				listPaymentStatus = new ArrayList<ModuloIntegrazionePagamentiPaymentStatus>();
				
				while (data.next())
					listPaymentStatus.add(new ModuloIntegrazionePagamentiPaymentStatus(data, true, false));
			
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

	
	public void doListMIP(String codiceSocieta, String provincia, String codiceUtente, String chiaveEnte,
			String chiaveTransazione, String numeroOperazione, String numeroDocumento, 
			String esitoNotifica, String dataDa, String dataA,
			int pageNumber, int rowsPerPage, String order) throws DaoException {
		CallableStatement callableStatement = null;
		try	{ 
			callableStatement = prepareCall(Routines.MIP_DOLIST.routine());
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
		
	public void doListMPS(String chiaveTransazione, int pageNumber, int rowsPerPage, String order) throws DaoException {
		CallableStatement callableStatement = null;
		try	{ 
			callableStatement = prepareCall(Routines.MPS_DOLIST.routine());
			callableStatement.setString(1, chiaveTransazione);
			callableStatement.setString(2, order);
			callableStatement.setInt(3, rowsPerPage);
			callableStatement.setInt(4, pageNumber);
			
			 //OUT O_ROWINI INT
			callableStatement.registerOutParameter(5, Types.INTEGER);
			//OUT O_ROWEND INT
			callableStatement.registerOutParameter(6, Types.INTEGER);
			//OUT O_TOTROWS INT
			callableStatement.registerOutParameter(7, Types.INTEGER);
			//OUT O_TOTPAGES INT
			callableStatement.registerOutParameter(8, Types.SMALLINT);
			
			if (callableStatement.execute()) {
				this.loadWebRowSets(callableStatement);	
				registerPageInfo(rowsPerPage, pageNumber, callableStatement.getInt(5), 
						callableStatement.getInt(6), callableStatement.getInt(7), callableStatement.getInt(8));
			
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
	
	public void doSaveOneri(ModuloIntegrazionePagamentiOneri mipOneri) throws DaoException {
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
	
	public List<ModuloIntegrazionePagamentiOneri> doListOneriByTransazioneObj(String chiaveTransazione) throws DaoException {
		
		CallableStatement callableStatement = null;
		ResultSet data = null;
		List<ModuloIntegrazionePagamentiOneri> listOneri = null;
		try	{
			callableStatement = prepareCall(Routines.ONE_DODETAILTRA.routine());
			callableStatement.setString(1, chiaveTransazione);

			if (callableStatement.execute()) {
				data = callableStatement.getResultSet();
				listOneri = new ArrayList<ModuloIntegrazionePagamentiOneri>();
				
				while (data.next())
					listOneri.add(new ModuloIntegrazionePagamentiOneri(data));
			
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
	
	public int countMPS_transazione(String chiaveTransazione) throws DaoException {
		CallableStatement callableStatement = null;
		try	{ 
			callableStatement = prepareCall(Routines.MPS_DOCOUNT.routine());
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
	
	public List<String> doBackup(Date dataMipDate,String flagEsitoNegativo,String flagNoEsito) throws DaoException {
		CallableStatement callableStatement = null;
		List<String> output=new ArrayList<String>();
		try	{
			callableStatement = prepareCall(Routines.MIP_DOMIPBK.routine());
			callableStatement.setTimestamp(1, new java.sql.Timestamp(dataMipDate.getTime()));
			callableStatement.setString(2,flagEsitoNegativo);
			callableStatement.setString(3,flagNoEsito);
			callableStatement.registerOutParameter(4,Types.INTEGER);
			callableStatement.registerOutParameter(5,Types.INTEGER);
			callableStatement.registerOutParameter(6,Types.VARCHAR);
			
			callableStatement.execute();
			//COUNT MPS ROW 
			output.add(String.valueOf(callableStatement.getInt(4)));
			//COUNT MIP ROW
			output.add(String.valueOf(callableStatement.getInt(5)));
			//ESITO
			output.add(callableStatement.getString(6));
				
			
		} catch (SQLException x) {
			throw new DaoException(x);
		} catch (IllegalArgumentException x) {
			throw new DaoException(x);
		} catch (HelperException x) {
			throw new DaoException(x);
		}
//		finally {
//			if (callableStatement != null)
//				DAOHelper.closeIgnoringException(callableStatement);
//		}
		//inizio LP PG21XX04 Leak
		finally {
			if (callableStatement != null) {
				try {
					callableStatement.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		//fine LP PG21XX04 Leak
		return output;
	}
	
}
