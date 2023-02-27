package com.seda.payer.core.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;

import com.seda.payer.commons.bean.TypeRequest;
import com.seda.payer.core.bean.ContoGestione;
import com.seda.payer.core.bean.ConvenzioneImp;
import com.seda.payer.core.bean.EccedenzaTestaBean;
import com.seda.payer.core.bean.Ente;
import com.seda.payer.core.bean.ContoGestioneBean;
import com.seda.payer.core.bean.TransazioneMod21Err;
import com.seda.payer.core.exception.DaoException;
import com.seda.payer.core.handler.BaseDaoHandler;
import com.seda.payer.core.messages.Messages;
//inizio LP PG21XX04 Leak
//import com.seda.data.dao.DAOHelper;
//fine LP PG21XX04 Leak
import com.seda.data.helper.HelperException;

import java.math.BigDecimal;

public class ContoGestioneDao extends BaseDaoHandler {

	public ContoGestioneDao(Connection connection, String schema) {
		super(connection, schema);
	}
	

	public void doRowSets(ContoGestione contoGestione, String ordine) throws DaoException {
		doRowSets(contoGestione, ordine, 0, 0);
	}

	public void doRowSets(ContoGestione contoGestione, String ordine, int rowsPerPage, int pageNumber) throws DaoException {
		if (rowsPerPage <= 0)
			throw new IllegalArgumentException(Messages.INVALID_PARAMETER.format("rowsPerPage"));

		if (pageNumber <= 0)
			throw new IllegalArgumentException(Messages.INVALID_PARAMETER.format("pageNumber"));

		CallableStatement callableStatement = null;

		try	{
			
			/*
	        IN I_MCG_CSOCCSOC CHAR(5),
	    	IN I_APC_CAPCSIGL CHAR(2),
  	    	IN I_MCG_CUTECUTE CHAR(5),
  	    	IN I_MCG_KANEKENT CHAR(10),
  	    	IN I_MCG_FMCGFTIP CHAR(1),
  	    	IN I_MCG_CMCGCPER CHAR(6),
  	    	IN I_MCG_IMCGITOT_DA DECIMAL(15 , 2),
			IN I_MCG_IMCGITOT_A DECIMAL(15 , 2),
			*/

			callableStatement = prepareCall(Routines.MCG_DOLIST.routine());
			callableStatement.setString(1, contoGestione.getUser().getCompany().getCompanyCode());
			callableStatement.setString(2, contoGestione.getAnagEnte().getAnagProvCom().getCodiceProvincia());
			callableStatement.setString(3, contoGestione.getUser().getUserCode());
			callableStatement.setString(4, contoGestione.getAnagEnte().getChiaveEnte());		

			callableStatement.setString(5, contoGestione.getTipoModello());		
			callableStatement.setString(6, contoGestione.getPeriodo());		
			callableStatement.setBigDecimal(7, contoGestione.getImportoDa());		
			callableStatement.setBigDecimal(8, contoGestione.getImportoA());
			
			callableStatement.setString(9, ordine);
			callableStatement.setInt(10, rowsPerPage);
			callableStatement.setInt(11, pageNumber);
			/* we register row start */
			callableStatement.registerOutParameter(12, Types.INTEGER);
			/* we register row end */
			callableStatement.registerOutParameter(13, Types.INTEGER);
			/* we register total rows */
			callableStatement.registerOutParameter(14, Types.INTEGER);
			/* we register total pages */
			callableStatement.registerOutParameter(15, Types.SMALLINT);
			/* we execute procedure */
			if (callableStatement.execute()) {
				this.loadWebRowSets(callableStatement);
				/* we register page info */
				registerPageInfo(rowsPerPage, pageNumber, callableStatement.getInt(12), callableStatement.getInt(13), 
								 callableStatement.getInt(14), callableStatement.getInt(15));
			}
		} catch (SQLException x) {
			throw new DaoException(x);
		} catch (IllegalArgumentException x) {
			throw new DaoException(x);
		} catch (HelperException x) {
			throw new DaoException(x);
		} finally {
			//inizio LP PG21XX04 Leak
			//closeConnection(callableStatement);
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

	
	public ArrayList<Ente> getListaEntiMod21() throws DaoException 
	{
		//inizio LP PG21XX04 Leak
		CallableStatement callableStatement = null;
		ResultSet data = null;
		//fine LP PG21XX04 Leak
		ArrayList<Ente> result = new ArrayList<Ente>();
		try	{
			//inizio LP PG21XX04 Leak
			//CallableStatement callableStatement = prepareCall(Routines.ENT_DOLIST_MOD21.routine());
			callableStatement = prepareCall(Routines.ENT_DOLIST_MOD21.routine());
			//fine LP PG21XX04 Leak
			if (callableStatement.execute()) {
				//inizio LP PG21XX04 Leak
				//ResultSet data = callableStatement.getResultSet();
				data = callableStatement.getResultSet();
				//fine LP PG21XX04 Leak
				while (data.next())
					result.add(new Ente(data));
			}
			return result;
		} catch (SQLException x) {
			throw new DaoException(x);
		} catch (IllegalArgumentException x) {
			throw new DaoException(x);
		} catch (HelperException x) {
			throw new DaoException(x);
		}
		//inizio LP PG21XX04 Leak
		finally {
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
		}
		//fine LP PG21XX04 Leak
	}
	
	
	//ritorna tutti i periodi creati
//	public ArrayList<String> getPeriodi(String societa, String utente, String codAna, String tipo )  throws DaoException 
	public ArrayList<String> getPeriodi(ContoGestioneBean cgb )  throws DaoException 
	{
//		PYMCGSP_LST_PERIODI
		//inizio LP PG21XX04 Leak
		CallableStatement callableStatement = null;
		ResultSet data = null;
		//fine LP PG21XX04 Leak
		ArrayList<String> result = new ArrayList<String>();
		try	{
//			CallableStatement callableStatement = prepareCall("PYMCGSP_LST_PERIODI");
			//inizio LP PG21XX04 Leak
			//CallableStatement callableStatement = prepareCall(Routines.MCG_LST_PERIODI.routine());
			callableStatement = prepareCall(Routines.MCG_LST_PERIODI.routine());
			//fine LP PG21XX04 Leak
			callableStatement.setString(1, cgb.getCodiceSocieta());
			callableStatement.setString(2, cgb.getCodiceUtente());
			callableStatement.setString(3, cgb.getChiaveAnagrafica());
			callableStatement.setString(4, cgb.getTipo());

			if (callableStatement.execute()) {
				//inizio LP PG21XX04 Leak
				//ResultSet data = callableStatement.getResultSet();
				data = callableStatement.getResultSet();
				//fine LP PG21XX04 Leak
				while (data.next())
					result.add(data.getString(1));
			}
			return result;
		} catch (SQLException x) {
			throw new DaoException(x);
		} catch (IllegalArgumentException x) {
			throw new DaoException(x);
		} catch (HelperException x) {
			throw new DaoException(x);
		}
		//inizio LP PG21XX04 Leak
		finally {
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
		}
		//fine LP PG21XX04 Leak
	}

	// PYTRASP_LST_PRIMO_PER
//	public String getPrimoPeriodo(String societa, String utente, String codAna, String tipo )  throws DaoException 
	public String getPrimoPeriodo(ContoGestioneBean cgb )  throws DaoException 
	{
		//inizio LP PG21XX04 Leak
		CallableStatement callableStatement = null;
		//fine LP PG21XX04 Leak
		String risultato = null;
		try	{
//			CallableStatement callableStatement = prepareCall("PYTRASP_LST_PRIMO_PER");
			//inizio LP PG21XX04 Leak
			//CallableStatement callableStatement = prepareCall(Routines.TRA_LST_PRIMO_PER.routine());
			callableStatement = prepareCall(Routines.TRA_LST_PRIMO_PER.routine());
			//fine LP PG21XX04 Leak

			callableStatement.setString(1, cgb.getCodiceSocieta());
			callableStatement.setString(2, cgb.getCodiceUtente());
			callableStatement.setString(3, cgb.getChiaveAnagrafica());
			callableStatement.setString(4, cgb.getTipo());
			callableStatement.registerOutParameter(5, Types.VARCHAR);
			
			callableStatement.execute();
			risultato = callableStatement.getString(5);
			}
	    catch (SQLException x) {
			throw new DaoException(x);
		} catch (IllegalArgumentException x) {
			throw new DaoException(x);
		} catch (HelperException x) {
			throw new DaoException(x);
		}
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
		return risultato;

	}

	//ritorna le transazioni che non consentono la generazione del report
//	public ArrayList<TransazioneMod21Err> getTranErrate(String societa, String utente, String codAna, String anno, String mese )  throws DaoException 
	public ArrayList<TransazioneMod21Err> getTranErrate(ContoGestioneBean cgb )  throws DaoException 
	{
//		PYTRASP_LST_ERR_BONIFICO
		//inizio LP PG21XX04 Leak
		CallableStatement callableStatement = null;
		ResultSet data = null;
		//fine LP PG21XX04 Leak
		ArrayList<TransazioneMod21Err> result = new ArrayList<TransazioneMod21Err>();
		try	{
//			CallableStatement callableStatement = prepareCall("PYTRASP_LST_ERR_BONIFICO");
			//inizio LP PG21XX04 Leak
			//CallableStatement callableStatement = prepareCall(Routines.TRA_LST_ERR_BONIFICO.routine());
			callableStatement = prepareCall(Routines.TRA_LST_ERR_BONIFICO.routine());
			//fine LP PG21XX04 Leak
			callableStatement.setString(1, cgb.getCodiceSocieta());
			callableStatement.setString(2, cgb.getCodiceUtente());
			callableStatement.setString(3, cgb.getChiaveAnagrafica());
			callableStatement.setString(4, cgb.getPeriodo().substring(0,4));
			callableStatement.setString(5, cgb.getPeriodo().substring(4));

			if (callableStatement.execute()) {
				//inizio LP PG21XX04 Leak
				//ResultSet data = callableStatement.getResultSet();
				data = callableStatement.getResultSet();
				//fine LP PG21XX04 Leak
				while (data.next())
					result.add(new TransazioneMod21Err(data));
			}
			return result;
		} catch (SQLException x) {
			throw new DaoException(x);
		} catch (IllegalArgumentException x) {
			throw new DaoException(x);
		} catch (HelperException x) {
			throw new DaoException(x);
		}
		//inizio LP PG21XX04 Leak
		finally {
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
		}
		//fine LP PG21XX04 Leak
	}


	public BigDecimal getTotMod21(ContoGestioneBean cgb)  throws DaoException 
	{
		//inizio LP PG21XX04 Leak
		CallableStatement callableStatement = null;
		//fine LP PG21XX04 Leak
		BigDecimal risultato = new BigDecimal(0);
		
		try	{
//			CallableStatement callableStatement = prepareCall("PYMCGSP_TOT_MOD21");
			//inizio LP PG21XX04 Leak
			//CallableStatement callableStatement = prepareCall(Routines.MCG_TOT_MOD21.routine());
			callableStatement = prepareCall(Routines.MCG_TOT_MOD21.routine());
			//fine LP PG21XX04 Leak
			callableStatement.setString(1, cgb.getCodiceSocieta());
			callableStatement.setString(2, cgb.getCodiceUtente());
			callableStatement.setString(3, cgb.getChiaveAnagrafica());
			callableStatement.setString(4, cgb.getPeriodo().substring(0,4));
			callableStatement.setString(5, cgb.getPeriodo().substring(4));
			callableStatement.registerOutParameter(6, Types.DECIMAL);	
			
			callableStatement.execute();
			
			risultato = callableStatement.getBigDecimal(6);
			
		} catch (SQLException x) {
			throw new DaoException(x);
		} catch (IllegalArgumentException x) {
			throw new DaoException(x);
		} catch (HelperException x) {
			throw new DaoException(x);
		}
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
		
		return risultato;

}
	
// PYMCGSP_SEL
	public ContoGestioneBean doDetail(String chiaveCG) throws DaoException {
		CallableStatement callableStatement = null;
		//inizio LP PG21XX04 Leak
		ResultSet data = null;
		//fine LP PG21XX04 Leak
		try	{
			
//			callableStatement = prepareCall("PYMCGSP_SEL");	
			callableStatement = prepareCall(Routines.MCG_DODETAIL.routine());	
			callableStatement.setString(1, chiaveCG);

			if (callableStatement.execute()) {
				//inizio LP PG21XX04 Leak
				//ResultSet data = callableStatement.getResultSet();
				data = callableStatement.getResultSet();
				//fine LP PG21XX04 Leak
				if (data.next())
					return new ContoGestioneBean(data);
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
			//closeConnection(callableStatement);
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
	
	
	public void insert(ContoGestioneBean cgb) throws DaoException {
		//CallableStatement callableStatement = null;
		//inizio LP PG21XX04 Leak
		CallableStatement callableStatement = null;
		//fine LP PG21XX04 Leak
		try	{
//			CallableStatement callableStatement = prepareCall("PYMCGSP_INS");
			//inizio LP PG21XX04 Leak
			//CallableStatement callableStatement = prepareCall(Routines.MCG_DOINSERT.routine());
			callableStatement = prepareCall(Routines.MCG_DOINSERT.routine());
			//fine LP PG21XX04 Leak
			
			cgb.save(callableStatement);
			
			callableStatement.execute();

		} catch (SQLException x) {
			throw new DaoException(x);
		} catch (IllegalArgumentException x) {
			System.out.println("insert ContoGestione failed generic error due to: " + x.getMessage());
			throw new DaoException(101, x.getMessage());
		} catch (HelperException x) {
			throw new DaoException(x);
		}
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
	}

	//inizio LP PG21XX04 Leak
	//private void closeConnection(CallableStatement callableStatement)
	//{
	//	if (callableStatement != null)
	//		DAOHelper.closeIgnoringException(callableStatement);
	//}
	//fine LP PG21XX04 Leak
}