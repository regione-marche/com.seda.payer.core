package com.seda.payer.core.dao;

import java.math.BigDecimal;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;

//inizio LP PG21XX04 Leak
//import com.seda.data.dao.DAOHelper;
//fine LP PG21XX04 Leak
import com.seda.data.helper.HelperException;
import com.seda.payer.core.bean.CodFiscHost;
import com.seda.payer.core.exception.DaoException;
import com.seda.payer.core.handler.BaseDaoHandler;
import com.seda.payer.core.messages.Messages;

public class EstrattoContoDao extends BaseDaoHandler {

	public EstrattoContoDao(Connection connection, String schema) {
		super(connection, schema);
		
	}
	//inizio LP PG21XX04 Leak
	//private void closeConnection(CallableStatement callableStatement)
	//{
	//	if (callableStatement != null)
	//		DAOHelper.closeIgnoringException(callableStatement);
	//}
	//fine LP PG21XX04 Leak
	//PG160170_001 GG 02022017 - Introdotto progCoobbligato
	/**
	 * codiceUtente= 000TO  , codiceEnte=06954, codiceFiscale=BAUFBA74T23L219Z, tipoServizio=EP, tipoRichiesta=I o S, 
	 * @param codiceUtente 
	 * @param codiceEnte
	 * @param tipoUfficio
	 * @param codiceUfficio
	 * @param codFiscale
	 * @param tipoServizio
	 * @param tipoRichiesta
	 * @param numeroDoc
	 * @param tipoIntegrazione
	 * @param idBollettino
	 * @param progCoobbligato
	 * @param codiecIuv
	 * @throws DaoException
	 */
	public String[] doCachedRowSetScadenze(String codiceUtente, String codiceEnte, 
			String tipoUfficio, String codiceUfficio, String codiceFiscale,
			String tipoServizio, String tipoRichiesta, 
			String numeroDoc, String tipoIntegrazione, String idBollettino, BigDecimal progCoobbligato, String codiceIuv
			, String tipologiaServizio, String impostaServizio) throws DaoException 
	{
		
		CallableStatement callableStatement = null;	
		try	
		{
			String[] outRes = new String[4];
			outRes[0] = "";
			outRes[1] = "KO";
			outRes[2] = "Error";
			outRes[3] = "";
			
			if (tipoIntegrazione.equals("I") || tipoIntegrazione.equals("S"))
			{
				// IMMEDIATA o IMMEDIATA SEDA  --> SPEPSTC2
				callableStatement = prepareCall(Routines.ECS_DOLIST.routine());	
				
				callableStatement.setString(1, codiceUtente);
				callableStatement.setString(2, codiceEnte);
				callableStatement.setString(3, codiceFiscale);
				callableStatement.setString(4, tipoServizio);
				callableStatement.setString(5, tipoRichiesta);
				callableStatement.setString(6, numeroDoc);
				callableStatement.setBigDecimal(7, progCoobbligato);	//PG160170_001 GG 02022017
			  
				/* we register row start */
				callableStatement.registerOutParameter(8, Types.CHAR);
				//KCS-Di Biagio PG140405
				callableStatement.registerOutParameter(9, Types.CHAR);
				
				callableStatement.registerOutParameter(10, Types.VARCHAR);
				/* we register row end */
				callableStatement.registerOutParameter(11, Types.VARCHAR);
				
				if (callableStatement.execute()) 
				{
					this.loadWebRowSets(callableStatement);
				}			
	
				outRes[0] = callableStatement.getString(8).trim();
				outRes[3] = callableStatement.getString(9).trim();
				outRes[1] = callableStatement.getString(10).trim();
				outRes[2] = callableStatement.getString(11).trim();
			}
			else if (tipoIntegrazione.equals("D"))
			{
				// IMMEDIATA  --> PYEH1SP
				callableStatement = prepareCall(Routines.ECS_DIFF_DOLIST.routine());
				
				System.out.println("Parametri PYEH2SP");
				System.out.println("codiceUtente: " + codiceUtente);
				System.out.println("codiceEnte: " + codiceEnte);
				System.out.println("tipoUfficio: " + tipoUfficio);
				System.out.println("codiceUfficio: " + codiceUfficio);
				System.out.println("codiceFiscale: " + codiceFiscale);
				System.out.println("tipoServizio: " + tipoServizio);
				System.out.println("tipoRichiesta: " + tipoRichiesta);
				System.out.println("numeroDoc: " + numeroDoc);
				System.out.println("idBollettino: " + idBollettino);
				System.out.println("codiceIuv: " + codiceIuv);
				System.out.println("tipologiaServizio: " + tipologiaServizio);
				System.out.println("impostaServizio: " + impostaServizio);
				
				callableStatement.setString(1, codiceUtente);
				callableStatement.setString(2, codiceEnte);
				callableStatement.setString(3, tipoUfficio);
				callableStatement.setString(4, codiceUfficio);
				callableStatement.setString(5, codiceFiscale);
				callableStatement.setString(6, tipoServizio);
				callableStatement.setString(7, tipoRichiesta);
				callableStatement.setString(8, numeroDoc);
				callableStatement.setString(9, idBollettino==null?"":idBollettino);
				callableStatement.setString(10, codiceIuv==null?"":codiceIuv);
				callableStatement.setString(11, tipologiaServizio==null?"":tipologiaServizio);
				callableStatement.setString(12, impostaServizio==null?"":impostaServizio);
				/* we register row start */
				callableStatement.registerOutParameter(13, Types.VARCHAR);
				/* we register row end */
				callableStatement.registerOutParameter(14, Types.VARCHAR);
				
				if (callableStatement.execute()) 
				{
					this.loadWebRowSets(callableStatement);				
				}			
	
				outRes[0] = callableStatement.getString(13).trim();
				outRes[1] = callableStatement.getString(14).trim();
			}
			
			return outRes;
			
		} catch (SQLException x) {
			x.printStackTrace();
			throw new DaoException(x);
		} catch (IllegalArgumentException x) {
			x.printStackTrace();
			throw new DaoException(x);
		} catch (HelperException x) {
			x.printStackTrace();
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
	
	/**
	 * codiceUtente= 000TO  , codiceEnte=06954, codiceFiscale=BAUFBA74T23L219Z, tipoServizio=EP, tipoRichiesta=I o S, 
	 * @param codiceUtente 
	 * @param codiceEnte
	 * @param tipoUfficio
	 * @param codiceUfficio
	 * @param codFiscale
	 * @param tipoServizio
	 * @param tipoRichiesta
	 * @param numeroDoc
	 * @param progCoobbligato
	 * @param TipoIntegrazione
	 * @throws DaoException
	 */
	//PG160170_001 GG 21022017 - Introdotto progCoobbligato
	public String[] doCachedRowSetMovimenti(String codiceUtente, String codiceEnte, 
			String tipoUfficio, String codiceUfficio, String codiceFiscale,
			String tipoServizio, String tipoRichiesta,
			String numeroDoc, BigDecimal progCoobbligato, String tipoIntegrazione, String impostaServizio) throws DaoException 
	{	
		CallableStatement callableStatement = null;	
		try	
		{
			String[] outRes = new String[2];
			outRes[0] = "KO";
			outRes[1] = "Error";
			
			
			if (tipoIntegrazione.equals("I") || tipoIntegrazione.equals("S"))
			{
				// IMMEDIATA o IMMEDIATA SEDA
				callableStatement = prepareCall(Routines.ECM_DOLIST.routine());	
				callableStatement.setString(1, codiceUtente);
				callableStatement.setString(2, codiceEnte);
				callableStatement.setString(3, codiceFiscale);
				callableStatement.setString(4, tipoServizio);
				callableStatement.setString(5, tipoRichiesta);
				callableStatement.setString(6, numeroDoc);
				callableStatement.setString(7, "N");					//flag uso progCoobbligato
				callableStatement.setBigDecimal(8, BigDecimal.ZERO);	//progCoobbligato
			  
				/* we register row start */
				callableStatement.registerOutParameter(9, Types.VARCHAR);
				/* we register row end */
				callableStatement.registerOutParameter(10, Types.VARCHAR);
				if (callableStatement.execute()) 
				{
					this.loadWebRowSets(callableStatement);
				}			

				outRes[0] = callableStatement.getString(9).trim();
				outRes[1] = callableStatement.getString(10).trim();
			}
			else if (tipoIntegrazione.equals("D"))
			{
				 // DIFFERITA
				callableStatement = prepareCall(Routines.ECM_DIFF_DOLIST.routine());
				callableStatement.setString(1, codiceUtente);
				callableStatement.setString(2, codiceEnte);
				callableStatement.setString(3, tipoUfficio);
				callableStatement.setString(4, codiceUfficio);
				callableStatement.setString(5, codiceFiscale);
				callableStatement.setString(6, tipoServizio);
				callableStatement.setString(7, tipoRichiesta);
				callableStatement.setString(8, numeroDoc);
				callableStatement.setString(9, impostaServizio);
				
				/* we register row start */
				callableStatement.registerOutParameter(10, Types.VARCHAR);
				/* we register row end */
				callableStatement.registerOutParameter(11, Types.VARCHAR);
				System.out.println("esecuzione = " + Routines.ECM_DIFF_DOLIST.routine());
				System.out.println("codiceUtente = " + codiceUtente);
				System.out.println("codiceEnte = " + codiceEnte);
				System.out.println("tipoUfficio = " + tipoUfficio);
				System.out.println("codiceUfficio = " + codiceUfficio);
				System.out.println("codiceFiscale = " + codiceFiscale);
				System.out.println("tipoServizio = " + tipoServizio);
				System.out.println("tipoRichiesta = " + tipoRichiesta);
				System.out.println("numeroDoc = " + numeroDoc);
				
				
				if (callableStatement.execute()) 
				{
					this.loadWebRowSets(callableStatement);
				}			

				outRes[0] = callableStatement.getString(10).trim();
				outRes[1] = callableStatement.getString(11).trim();
			}
			return outRes;
			
		} catch (SQLException x) {
			x.printStackTrace();
			throw new DaoException(x);
		} catch (IllegalArgumentException x) {
			x.printStackTrace();
			throw new DaoException(x);
		} catch (HelperException x) {
			x.printStackTrace();
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
	
	/**
	 * codiceUtente= 000TO  , codiceEnte=06954, codiceFiscale=BAUFBA74T23L219Z, tipoServizio=EP, tipoRichiesta=I o S, 
	 * @param codiceUtente 
	 * @param codiceEnte
	 * @param tipoUfficio
	 * @param codiceUfficio
	 * @param codFiscale
	 * @param tipoServizio
	 * @param tipoRichiesta
	 * @param numeroDoc
	 * @param TipoIntegrazione
	 * @throws DaoException
	 */
	public String[] doCachedRowSetTributi(String codiceUtente, String codiceEnte, 
			String tipoUfficio, String codiceUfficio, String codiceFiscale,
			String tipoServizio, String tipoRichiesta,
			String numeroDoc, String tipoIntegrazione, String impostaServizio) throws DaoException 
	{	
		CallableStatement callableStatement = null;	
		try	
		{
			String[] outRes = new String[2];
			outRes[0] = "KO";
			outRes[1] = "Error";
			
			
			if (tipoIntegrazione.equals("I") || tipoIntegrazione.equals("S"))
			{
				// IMMEDIATA o IMMEDIATA SEDA
				// non esiste solo differita
		
			}
			else if (tipoIntegrazione.equals("D"))
			{
				 // DIFFERITA
				callableStatement = prepareCall(Routines.ECT_DIFF_DOLIST.routine());
				callableStatement.setString(1, codiceUtente);
				callableStatement.setString(2, codiceEnte);
				callableStatement.setString(3, tipoUfficio);
				callableStatement.setString(4, codiceUfficio);
				callableStatement.setString(5, codiceFiscale);
				callableStatement.setString(6, tipoServizio);
				callableStatement.setString(7, tipoRichiesta);
				callableStatement.setString(8, numeroDoc);
				callableStatement.setString(9, impostaServizio == null ? "" : impostaServizio);
				/* we register row start */
				callableStatement.registerOutParameter(10, Types.VARCHAR);
				/* we register row end */
				callableStatement.registerOutParameter(11, Types.VARCHAR);
				
				if (callableStatement.execute()) 
				{
					this.loadWebRowSets(callableStatement);
				}			

				outRes[0] = callableStatement.getString(10).trim();
				outRes[1] = callableStatement.getString(11).trim();
			}
			
			return outRes;
			
		} catch (IllegalArgumentException x) {
			throw new DaoException(x);
		} catch (SQLException e) {
			throw new DaoException(e);
		} catch (HelperException e) {
			throw new DaoException(e);
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

	/**
	 * codiceUtente= 000TO  , codiceEnte=06954, codiceFiscale=BAUFBA74T23L219Z, tipoServizio=EP, tipoRichiesta=I o S, 
	 * Il ritorno è 0 Esito e 1 MessaggioErrore
	 * tipoServizio=EP, tipoRichiesta=I o S, 
	 * @param codiceUtente 
	 * @param codiceEnte
	 * @param tipoUfficio
	 * @param codiceUfficio
	 * @param codFiscale
	 * @param tipoServizio
	 * @param tipoRichiesta
	 * @param tipologiaServizio
	 * @param numeroDoc
	 * @param TipoIntegrazione
	 * @throws DaoException
	 */
	public void doCachedRowSetPagSpontanei(String codiceUtente, String codiceEnte, 
			String tipoUfficio, String codiceUfficio, String codiceFiscale) throws DaoException {
		
		CallableStatement callableStatement = null;	
		
		try	
		{
			callableStatement = prepareCall(Routines.EC_PAGSPON_DOLIST.routine());	
			if (callableStatement != null)
			{
				callableStatement.setString(1, codiceUtente);
				callableStatement.setString(2, codiceEnte);
				callableStatement.setString(3, tipoUfficio);
				callableStatement.setString(4, codiceUfficio);
				callableStatement.setString(5, codiceFiscale);
				
				if (callableStatement.execute()) 
					this.loadWebRowSets(callableStatement);					
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
	
	//PG160180_001 GG - Introdotto dataPagamentoDa e dataPagamentoA
	public void doCachedRowSetPagSpontaneiIciIscop(String codiceUtente, String codiceEnte, 
			String tipoUfficio, String codiceUfficio, String codiceFiscale,
			String dataPagamentoDa, String dataPagamentoA) throws DaoException 
	{
		CallableStatement callableStatement = null;	
		
		try	
		{
			callableStatement = prepareCall(Routines.EC_PAGSPON_TIC_DOLIST.routine());	
			if (callableStatement != null)
			{
				/*System.out.println("codiceUtente: " + codiceUtente);
				System.out.println("codiceEnte: " + codiceEnte);
				System.out.println("tipoUfficio: " + tipoUfficio);
				System.out.println("codiceUfficio: " + codiceUfficio);
				System.out.println("codiceFiscale: " + codiceFiscale);
				System.out.println("codiceFiscale: " + dataPagamentoDa);
				System.out.println("codiceFiscale: " + dataPagamentoA);
				*/
				callableStatement.setString(1, codiceUtente);
				callableStatement.setString(2, codiceEnte);
				callableStatement.setString(3, tipoUfficio);
				callableStatement.setString(4, codiceUfficio);
				callableStatement.setString(5, codiceFiscale);
				callableStatement.setString(6, dataPagamentoDa);
				callableStatement.setString(7, dataPagamentoA);
				//PG160180_001 GG - fine
				
				if (callableStatement.execute()) 
					this.loadWebRowSets(callableStatement);					
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
	
	
	/**
	 * codiceUtente= 000TO  , codiceEnte=06954, codiceFiscale=BAUFBA74T23L219Z, tipoServizio=EP, tipoRichiesta=I o S, 
	 * Il ritorno è 0 Esito e 1 MessaggioErrore
	 * tipoServizio=EP, tipoRichiesta=I o S, 
	 * @param codiceUtente 
	 * @param codiceEnte
	 * @param tipoUfficio
	 * @param codiceUfficio
	 * @param codFiscale
	 * @param tipoServizio
	 * @param tipoRichiesta
	 * @param tipologiaServizio
	 * @param numeroDoc
	 * @param TipoIntegrazione
	 * @throws DaoException
	 */
	public String[] doCachedRowAcquisizioneTransazioni(String codiceUtente, String codiceEnte, 
			String tipoUfficio, String codiceUfficio, String codiceFiscale,
			String tipoServizio, String tipoRichiesta, 
			String numeroDoc, String tipoIntegrazione) throws DaoException {
		
		CallableStatement callableStatement = null;	
		
		try	
		{
			String[] outRes = new String[2];
			outRes[0] = "KO";
			outRes[1] = "No Data";
			
			
			// IMMEDIATA
			callableStatement = prepareCall(Routines.EC_ACQUTRANS_DOLIST.routine());	
			/*System.out.println("codiceUtente: " + codiceUtente);
			System.out.println("codiceEnte: " + codiceEnte);
			System.out.println("tipoUfficio: " + tipoUfficio);
			System.out.println("codiceUfficio: " + codiceUfficio);
			System.out.println("numeroDoc: " + numeroDoc);
			*/
			
			if (callableStatement != null)
			{
				callableStatement.setString(1, codiceUtente);
				callableStatement.setString(2, codiceEnte);
				callableStatement.setString(3, tipoUfficio);
				callableStatement.setString(4, codiceUfficio);
				callableStatement.setString(5, numeroDoc);
				
				if (callableStatement.execute()) 
				{
					outRes[0] = "00";
					outRes[1] = "OPERAZIONE EFFETTUATA";
					this.loadWebRowSets(callableStatement);					
				}
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
	
	/**
	 * codiceUtente= 000TO  , codiceEnte=06954, codiceFiscale=BAUFBA74T23L219Z, tipoServizio=EP, tipoRichiesta=I o S, 
	 * Il ritorno è 0 Esito e 1 MessaggioErrore
	 * tipoServizio=EP, tipoRichiesta=I o S, 
	 * @param codiceUtente 
	 * @param codiceEnte
	 * @param tipoUfficio
	 * @param codiceUfficio
	 */
	public String[] doCachedRowConfig(String codiceUtente, String codiceEnte, String tipoUfficio, String codiceUfficio) throws DaoException {
		
		CallableStatement callableStatement = null;	
		try	
		{
			String[] outRes = new String[2];
			outRes[0] = "KO";
			outRes[1] = "Error "+codiceEnte+" "+tipoUfficio+" "+codiceUfficio;
			
			
			// IMMEDIATA
			callableStatement = prepareCall(Routines.EC_CONFIG_DOLIST.routine());	
			if (callableStatement != null)
			{
				callableStatement.setString(1, codiceEnte);
				callableStatement.setString(2, tipoUfficio);
				callableStatement.setString(3, codiceUfficio);
				callableStatement.setString(4, codiceUtente);
				
				if (callableStatement.execute()) 
				{
					outRes[0] = "00";
					outRes[1] = "OPERAZIONE EFFETTUATA";
					this.loadWebRowSets(callableStatement);					
				}
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
	
	
	/**
	 * tipoServizio=EP, tipoRichiesta=I o S, 
	 * @param codiceUtente 
	 * @param codiceEnte
	 * @param tipoUfficio
	 * @param codiceUfficio
	 * @param codFiscale
	 * @param tipoServizio
	 * @param tipoRichiesta
	 * @param tipologiaServizio
	 * @param numeroDoc
	 * @param TipoIntegrazione
	 * @throws DaoException
	 */
	//PG10070 GG 14062017 - Introdotto codiceIuv
	//PG160170_001 GG 21022017 - Introdotto progCoobbligato
	//PG160220 GG 28112016 Inserito tipologiaUtenza
	//PG180330 CT 20190418 Inserito tipologiaUfficiale
	public String[] doCachedRowSetList(String codiceUtente, String codiceEnte, 
			String tipoUfficio, String codiceUfficio, String codiceFiscale,
			String tipoServizio, String tipoRichiesta, String tipologiaServizio, String impostaServizio,
			String numeroDoc, String annoEmissione, String tipoIntegrazione, String filtriEstrattoConto,
			String idDocumento, BigDecimal idBollettino, String raccomandata, String cronologico, String barCode, String idProcedura, BigDecimal ImportoMinimoEccedenza, String tipologiaUtenza
			, BigDecimal progCoobbligato, String codiceIuv, String tipologiaUfficiale
			) throws DaoException {
		
		return doCachedRowSetListMultiPage(codiceUtente, codiceEnte, tipoUfficio, codiceUfficio, codiceFiscale,
				tipoServizio, tipoRichiesta, tipologiaServizio, impostaServizio, numeroDoc, annoEmissione, tipoIntegrazione, filtriEstrattoConto,
				idDocumento, idBollettino, raccomandata, cronologico, barCode, idProcedura,ImportoMinimoEccedenza, tipologiaUtenza, progCoobbligato, codiceIuv,tipologiaUfficiale,
				1, 1);
	}
	
	/**
	 *  tipoServizio=EP, tipoRichiesta=I o S, 
	 * @param codiceUtente 
	 * @param codiceEnte
	 * @param tipoUfficio
	 * @param codiceUfficio
	 * @param codFiscale
	 * @param tipoServizio
	 * @param tipoRichiesta
	 * @param tipologiaServizio
	 * @param numeroDoc
	 * @param TipoIntegrazione
	 * @throws DaoException
	 */
	//PG170070 GG 14062017 - Introdotto codiceIuv
	//PG160170_001 GG 21022017 - Introdotto progCoobbligato
	//PG160220 GG 28112016 Inserito tipologiaUtenza
	//PG180330 CT 20190418 Inserito tipologiaUfficiale
	public String[] doCachedRowSetList(String codiceUtente, String codiceEnte, 
			String tipoUfficio, String codiceUfficio, String codiceFiscale,
			String tipoServizio, String tipoRichiesta, String tipologiaServizio, String impostaServizio,
			String numeroDoc, String tipoIntegrazione, String filtriEstrattoConto, 
			String idDocumento, BigDecimal idBollettino, String raccomandata, String cronologico, String barCode, String idProcedura, BigDecimal ImportoMinimoEccedenza, String tipologiaUtenza,
			BigDecimal progCoobligato, String codiceIuv, String tipologiaUfficiale, int rowsPerPage, int pageNumber) throws DaoException 
	{
		
			if (rowsPerPage <= 0)
				throw new IllegalArgumentException(Messages.INVALID_PARAMETER.format("rowsPerPage"));

			if (pageNumber <= 0)
				throw new IllegalArgumentException(Messages.INVALID_PARAMETER.format("pageNumber"));
 
			return doCachedRowSetListMultiPage(codiceUtente, codiceEnte, tipoUfficio, codiceUfficio, codiceFiscale,
					tipoServizio, tipoRichiesta, tipologiaServizio, impostaServizio, numeroDoc, "", tipoIntegrazione, filtriEstrattoConto,
					idDocumento, idBollettino, raccomandata, cronologico, barCode, idProcedura, ImportoMinimoEccedenza, tipologiaUtenza, progCoobligato, codiceIuv, tipologiaUfficiale,
					rowsPerPage, pageNumber );
		
	}
	
	 /**
	 * tipoServizio=EP, tipoRichiesta=I o S, 
	 * @param codiceUtente 
	 * @param codiceEnte
	 * @param tipoUfficio
	 * @param codiceUfficio
	 * @param codiceFiscale
	 * @param tipoServizio
	 * @param tipoRichiesta
	 * @param tipologiaServizio
	 * @param impostaServizio
	 * @param numeroDoc
	 * @param annoEmissione
	 * @param tipoIntegrazione
	 * @param filtriEstrattoConto
	 * @param idDocumento
	 * @param idBollettino
	 * @param raccomandata
	 * @param cronologico
	 * @param barCode
	 * @param idProcedura
	 * @param ImportoMinimoEccedenza
	 * @param tipologiaUtenza
	 * @param progCoobbligato
	 * @param tipologiaUfficiale
	 * @param codiceIuv
	 * @throws DaoException
	 */
	//PG170070 GG 14062017 - Introdotto codiceIuv
	//PG160170_001 GG 21022017 - Introdotto progCoobbligato
	//PG160220 GG 28112016 - Inserito tipologiaUtenza
	 private String[] doCachedRowSetListMultiPage(String codiceUtente, String codiceEnte, 
			String tipoUfficio, String codiceUfficio, String codiceFiscale,
			String tipoServizio, String tipoRichiesta, String tipologiaServizio, String impostaServizio,
			String numeroDoc, String annoEmissione, String tipoIntegrazione, String filtriEstrattoConto, 
			String idDocumento, BigDecimal idBollettino, String raccomandata, String cronologico, String barCode, String idProcedura, BigDecimal ImportoMinimoEccedenza, String tipologiaUtenza,
			BigDecimal progCoobbligato, String codiceIuv, String tipologiaUfficiale, int rowsPerPage, int pageNumber) throws DaoException {
		 
		CallableStatement callableStatement = null;
		try	
		{ 
			String[] outRes = new String[2];
			outRes[0] = "KO";
			outRes[1] = "Error";
			
				
			if (tipoIntegrazione.equals("I") || tipoIntegrazione.equals("S"))
			{
				
				callableStatement = prepareCall(Routines.EC_DOLIST.routine());				
				callableStatement.setString(1, codiceUtente);
				callableStatement.setString(2, codiceEnte);
				callableStatement.setString(3, codiceFiscale);
				callableStatement.setString(4, tipoServizio);
				callableStatement.setString(5, tipoRichiesta);
				callableStatement.setString(6, filtriEstrattoConto);
				callableStatement.setString(7, idDocumento);
				callableStatement.setBigDecimal(8, idBollettino);
				callableStatement.setString(9, raccomandata);
				callableStatement.setString(10, cronologico);
				callableStatement.setString(11, barCode);
				callableStatement.setString(12, idProcedura);
				
				//decommentata la riga seguente e incrementati i parametri di output
				//nei 2 punti successivi
          		//callableStatement.setString(6, filtriEstrattoConto);
				
				/* messaggio in uscita OK not OK */
				
				callableStatement.setBigDecimal(13, ImportoMinimoEccedenza);
				callableStatement.setString(14, tipologiaUtenza==null?"":tipologiaUtenza);					//PG160220 GG 28112016
				callableStatement.setBigDecimal(15, progCoobbligato==null?BigDecimal.ZERO:progCoobbligato);	//PG160170_001 GG 21022017
				
				callableStatement.setString(16, tipologiaUfficiale==null?"":tipologiaUfficiale);					//PG180330 CT 20190418
				callableStatement.registerOutParameter(17, Types.VARCHAR);
				/* indicazione errore */
				callableStatement.registerOutParameter(18, Types.VARCHAR);

		        //inizio LP PG180250		
				int ik = 0;
				System.out.println("Parametri in input per SP SPEPSTC1: ");
				System.out.println(++ik + " - codiceUtente           <" + codiceUtente + ">");
				System.out.println(++ik + " - codiceEnte             <" + codiceEnte + ">");
				System.out.println(++ik + " - codiceFiscale          <" + codiceFiscale + ">");
				System.out.println(++ik + " - tipoServizio           <" + tipoServizio + ">");
				System.out.println(++ik + " - tipoRichiesta          <" + tipoRichiesta + ">");
				System.out.println(++ik + " - filtriEstrattoConto    <" + filtriEstrattoConto + ">");
				System.out.println(++ik + " - idDocumento            <" + idDocumento + ">");
				System.out.println(++ik + " - idBollettino           <" + idBollettino + ">");
				System.out.println(++ik + " - raccomandata           <" + raccomandata + ">");
				System.out.println(++ik + " - cronologico            <" + cronologico + ">");
				System.out.println(++ik + " - barCode                <" + barCode + ">");
				System.out.println(++ik + " - idProcedura            <" + idProcedura + ">");
				System.out.println(++ik + " - ImportoMinimoEccedenza <" + ImportoMinimoEccedenza + ">");
				System.out.println(++ik + " - tipologiaUtenza        <" + (tipologiaUtenza==null?"":tipologiaUtenza) + ">");
				System.out.println(++ik + " - progCoobbligato        <" + (progCoobbligato==null?BigDecimal.ZERO:progCoobbligato) + ">");
				System.out.println(++ik + " - tipologiaUfficiale     <" + (tipologiaUfficiale==null?"":tipologiaUfficiale) + ">");
				
		        //fine LP PG180250		
				
				if (callableStatement.execute()) 
				{
					this.loadWebRowSets(callableStatement);
				}			

				outRes[0] = callableStatement.getString(17).trim();
				outRes[1] = callableStatement.getString(18).trim();
			}
			else if (tipoIntegrazione.equals("D"))
			{
				// DIFFERITA
				callableStatement = prepareCall(Routines.EC_DIFF_DOLIST.routine());
				callableStatement.setString(1, codiceUtente);
				callableStatement.setString(2, codiceEnte);
				callableStatement.setString(3, tipoUfficio);
				callableStatement.setString(4, codiceUfficio);				
				callableStatement.setString(5, codiceFiscale);
				callableStatement.setString(6, tipoServizio);
				callableStatement.setString(7, tipoRichiesta);
				callableStatement.setString(8, tipologiaServizio);
				callableStatement.setString(9, impostaServizio);
				callableStatement.setString(10, numeroDoc);
				callableStatement.setString(11, annoEmissione);
				//22072016 GG PG160130 - inizio
				String sIdBollettino = idBollettino.compareTo(BigDecimal.ZERO)<1?"":idBollettino.toString();
				if (sIdBollettino.length()>0 && sIdBollettino.length()<18) {
					sIdBollettino = leftPad(sIdBollettino, '0', 18);
				}
				callableStatement.setString(12, sIdBollettino);
				//22072016 GG PG160130 - fine
				callableStatement.setString(13, codiceIuv==null?"":codiceIuv);	//PG170070 GG 14062017
				/* messaggio in uscita OK not OK */
				callableStatement.registerOutParameter(14, Types.VARCHAR);
				/* indicazione errore */
				callableStatement.registerOutParameter(15, Types.VARCHAR);
				
				System.out.println("Parametri SP PYEH1SP: ");
				System.out.println("codiceUtente: <" + codiceUtente +">");
				System.out.println("codiceEnte: <" + codiceEnte +">");
				System.out.println("tipoUfficio: <" + tipoUfficio +">");
				System.out.println("codiceUfficio: <" + codiceUfficio +">");
				System.out.println("codiceFiscale: <" + codiceFiscale +">");
				System.out.println("tipoServizio: <" + tipoServizio +">");
				System.out.println("tipoRichiesta: <" + tipoRichiesta +">");
				System.out.println("tipologiaServizio: <" + tipologiaServizio +">");
				System.out.println("impostaServizio: <" + impostaServizio +">");
				System.out.println("numeroDoc: <" + numeroDoc +">");
				System.out.println("annoEmissione: <" + annoEmissione +">");
				System.out.println("idBollettino: <" + sIdBollettino +">");
				System.out.println("codiceIuv: <" + codiceIuv +">");
								
				if (callableStatement.execute()) 
				{
					System.out.println("post execute");
					this.loadWebRowSets(callableStatement);
				}			

				outRes[0] = callableStatement.getString(14).trim();
				outRes[1] = callableStatement.getString(15).trim();
			}
			
			return outRes;
			
		} catch (SQLException x) {
			x.printStackTrace();
			throw new DaoException(x);
		} catch (IllegalArgumentException x) {
			x.printStackTrace();
			throw new DaoException(x);
		} catch (HelperException x) {
			x.printStackTrace();
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
	 
	 //PG160170_001 GG 03022017 - Introdotto progCoobbligato
	 //KCS-Di Biagio PG140450
	 public String[] doCahcedRowSetDettDocBollettino(String codiceUtente, String codiceEnte, String codiceFiscale,
				String tipoServizio, String documento, String tipoDocumento, String numRata, String flgLimit, 
				BigDecimal importo, BigDecimal impOneri, String tipoIntegrazione, BigDecimal progCoobbligato) throws DaoException 
		{
			
			CallableStatement callableStatement = null;	
			try	
			{
				//inizio LP PG180250
				//String[] outRes = new String[12];
				String[] outRes = new String[24];
				//fine LP PG180250
				outRes[0] = "KO";
				outRes[1] = "Error";
				//inizio LP PG180250
				/*
				outRes[2] = "";
				outRes[3] = "";
				outRes[4] = "";
				outRes[5] = "";
				outRes[6] = "";
				outRes[7] = "";
				outRes[8] = "";
				outRes[9] = "";
				outRes[10] = "";
				outRes[11] = "";
				*/
				int ii;
				for(ii = 2;  ii < 24; ii++) {
					outRes[ii] = "";
				}
				//fine LP PG180250
				
				//Il tipo integrazione non è gestito ma comunque faccio arrivare il FLAG per una futura gestione
				//inizio LP PG180250
				//tipo integrazione ==> I='solo informativa, non verrà inserito nessun RAV', S='per effettuare la stampa e l’inserimento del bollettino'
				//Con  il PG180250 il tipo integrazione è ora gestito da Host si è quindi abilitata la riga con la condizione "if" sotto......
				if (tipoIntegrazione.equals("I") || tipoIntegrazione.equals("S")) // PG180250
				//fine LP PG180250
				{
					// IMMEDIATA o IMMEDIATA SEDA  --> SPEPSTC4
					callableStatement = prepareCall(Routines.EC_DETBOL.routine());	
					
					callableStatement.setString(1, codiceUtente);
					callableStatement.setString(2, codiceEnte);
					callableStatement.setString(3, codiceFiscale);
					callableStatement.setString(4, tipoServizio);
					callableStatement.setString(5, documento);
					callableStatement.setString(6, tipoDocumento);
					callableStatement.setString(7, leftPad(numRata,'0',3));		//RE170170 GG 23102017 formattato numero rata a 3, es. 000
					callableStatement.setString(8, flgLimit);
					callableStatement.setBigDecimal(9, importo);
					callableStatement.setBigDecimal(10, impOneri);
					callableStatement.setBigDecimal(11, progCoobbligato);
					//inizio LP PG180250
					callableStatement.setString(12, tipoIntegrazione);
					
					int ij = 0;
					System.out.println("Parametri in input per SP SPEPSTC4:");
					System.out.println(++ij + " - codiceUtente     <" + codiceUtente + ">");
					System.out.println(++ij + " - codiceEnte       <" + codiceEnte + ">");
					System.out.println(++ij + " - codiceFiscale    <" + codiceFiscale + ">");
					System.out.println(++ij + " - tipoServizio     <" + tipoServizio + ">");
					System.out.println(++ij + " - documento        <" + documento + ">");
					System.out.println(++ij + " - tipoDocumento    <" + tipoDocumento + ">");
					System.out.println(++ij + " - numRata          <" + leftPad(numRata,'0',3) + ">");
					System.out.println(++ij + " - flgLimit         <" + flgLimit + ">");
					System.out.println(++ij + " - importo          <" + importo + ">");
					System.out.println(++ij + " - impOneri         <" + impOneri + ">");
					System.out.println(++ij + " - progCoobbligato  <" + progCoobbligato + ">");
					System.out.println(++ij + " - tipoIntegrazione <" + tipoIntegrazione + ">");
					//fine LP PG180250
				  
					/* we register row start */
					//inizio LP PG180250
					/*
					callableStatement.registerOutParameter(12, Types.VARCHAR);
					callableStatement.registerOutParameter(13, Types.VARCHAR);
					callableStatement.registerOutParameter(14, Types.VARCHAR);
					callableStatement.registerOutParameter(15, Types.VARCHAR);
					callableStatement.registerOutParameter(16, Types.VARCHAR);
					callableStatement.registerOutParameter(17, Types.VARCHAR);
					callableStatement.registerOutParameter(18, Types.CHAR);
					callableStatement.registerOutParameter(19, Types.VARCHAR);
					callableStatement.registerOutParameter(20, Types.VARCHAR);
					callableStatement.registerOutParameter(21, Types.VARCHAR);
					callableStatement.registerOutParameter(22, Types.VARCHAR);
					callableStatement.registerOutParameter(23, Types.VARCHAR);
					*/
					int ik = 12;
					for(ii = 0;  ii < 24; ii++) {
						callableStatement.registerOutParameter(++ik, Types.VARCHAR);
					}
					int indice_retmess = ik;     //ultimo parametro
					int indice_retcode = ik - 1; //penultimo parametro
					//fine LP PG180250
					
					if (callableStatement.execute()) 
					{
						this.loadWebRowSets(callableStatement);
					}			
		
					//Imposto sui primi due items la gestione errore in modo che nel futuro non ci siano problemi se si aggiunge un parametro
					//inizio LP PG180250
					/*
					outRes[0] = callableStatement.getString(22).trim();
					outRes[1] = callableStatement.getString(23).trim();
					*/
					outRes[0] = callableStatement.getString(indice_retcode).trim();
					outRes[1] = callableStatement.getString(indice_retmess).trim();
					//fine LP PG180250
					
					//inizio LP PG180250
					/*
					outRes[2] = callableStatement.getString(12).trim();
					outRes[3] = callableStatement.getString(13).trim();
					outRes[4] = callableStatement.getString(14).trim();
					outRes[5] = callableStatement.getString(15).trim();
					outRes[6] = callableStatement.getString(16).trim();
					outRes[7] = callableStatement.getString(17).trim();
					outRes[8] = callableStatement.getString(18).trim();
					outRes[9] = callableStatement.getString(19).trim();
					outRes[10] = callableStatement.getString(20).trim();
					outRes[11] = callableStatement.getString(21).trim();
					*/
					ik = 12;
					for(ii = 2;  ii < 24; ii++) {
						outRes[ii] = callableStatement.getString(++ik).trim();
					}
					//fine LP PG180250
				}
				//inizio LP PG180250
				else {
					outRes[1] = "Errore nel valore del parametro 'tipoIntegrazione': " + tipoIntegrazione;
				}
				//fine LP PG180250
				
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
	 
	 //PG160170_001 GG 02022017 - Introdotto progCoobbligato
	 public CodFiscHost codFiscHost(String cuteCute, String idDocumento,BigDecimal numBollettino, String numRaccomandata,	String numCoronologico, String idBarcode, String idProcedura, BigDecimal progCoobbligato) throws DaoException {
			CallableStatement callableStatement = null;
			try	{ 
				callableStatement = prepareCall(Routines.EC_CODFISC.routine());

				callableStatement.setString(1, cuteCute);
				if(idDocumento== null){
				idDocumento="";
				}
				callableStatement.setString(2, idDocumento); 
				callableStatement.setBigDecimal(3, numBollettino);
				callableStatement.setString(4, numRaccomandata);
				callableStatement.setString(5, numCoronologico); 
				callableStatement.setString(6, idBarcode);
				callableStatement.setString(7, idProcedura);
				callableStatement.setBigDecimal(8, progCoobbligato);	//PG160170_001 GG 02022017
				callableStatement.registerOutParameter(9, Types.CHAR);
				callableStatement.registerOutParameter(10, Types.CHAR);
				callableStatement.registerOutParameter(11, Types.CHAR);

				callableStatement.execute();
				String retCode = callableStatement.getString(10);
				String message = callableStatement.getString(11);

				//  Legenda ReturnCode:
				//- OK: presenti pagamenti in acquisizione
				//- KL: nessun pagamento in acquisizione
				//- KO: errore in esecuzione

//				if (!retCode.equalsIgnoreCase("OK")) {
//					return null; 
//				} 


				return new CodFiscHost(callableStatement);

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
	 
	 //PG150290_001 GG - inizio
	 //PG160170_001 GG 02022017 - Introdotto progCoobbligato
	 /**
		 * codiceUtente= 000TO  , codiceEnte=06954, codiceFiscale=BAUFBA74T23L219Z, tipoServizio=EP, tipoRichiesta=I o S, 
		 * @param codiceUtente 
		 * @param codiceEnte
		 * @param tipoUfficio
		 * @param codiceUfficio
		 * @param codFiscale
		 * @param tipoServizio
		 * @param tipoRichiesta
		 * @param numeroDoc
		 * @param flagProvenienza
		 * @param tipoIntegrazione
		 * @param progCoobbligato
		 * @throws DaoException
		 */
		public String[] doCachedRowSetAttiContenzioso(String codiceUtente, String codiceEnte, 
				String codiceFiscale, String tipoServizio, String tipoRichiesta, 
				String numeroDoc, String flagAccesso, String tipoIntegrazione, BigDecimal progCoobbligato) throws DaoException 
		{
			
			CallableStatement callableStatement = null;	
			try	
			{
				String[] outRes = new String[2];
				outRes[0] = "KO";
				outRes[1] = "Error";
				
				if (tipoIntegrazione.equals("I") || tipoIntegrazione.equals("S"))
				{
					// IMMEDIATA o IMMEDIATA SEDA  --> nuova sp host
					callableStatement = prepareCall(Routines.EC_ATTICONT_DOLIST.routine());	
					
					callableStatement.setString(1, codiceUtente);
					callableStatement.setString(2, codiceEnte);
					callableStatement.setString(3, codiceFiscale);
					callableStatement.setString(4, tipoServizio);
					callableStatement.setString(5, tipoRichiesta);
					callableStatement.setString(6, numeroDoc);
					callableStatement.setString(7, flagAccesso);
					callableStatement.setBigDecimal(8, progCoobbligato);
				  
					callableStatement.registerOutParameter(9, Types.VARCHAR);
					callableStatement.registerOutParameter(10, Types.VARCHAR);
										
					if (callableStatement.execute()) 
					{
						this.loadWebRowSets(callableStatement);
					}			

					outRes[0] = callableStatement.getString(9).trim();
					outRes[1] = callableStatement.getString(10).trim();
				}
				else if (tipoIntegrazione.equals("D"))
				{
					// DIFFERITA
					// non esiste solo IMMEDIATA o IMMEDIATA SEDA
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
		//PG150290_001 GG - fine
		
		//PG160180_001 GG - inizio
		/**
		 * tipoServizio=EP, tipoRichiesta=I o S, 
		 * @param codiceUtente 
		 * @param codiceEnte
		 * @param codFiscale
		 * @param tipoServizio
		 * @param dataPagamentoDa
		 * @param dataPagamentoA
		 * @param tipoIntegrazione
		 * @param filtriEstrattoConto
		 * @throws DaoException
		 */
		public String[] doCachedRowSetList(String codiceUtente, String codiceEnte, String codiceFiscale,
				String tipoServizio, String dataPagamentoDa, String dataPagamentoA, String tipoIntegrazione, String filtriEstrattoConto) throws DaoException {
			
			return doCachedRowSetListMultiPage(codiceUtente, codiceEnte, codiceFiscale,
					tipoServizio, dataPagamentoDa, dataPagamentoA, tipoIntegrazione, filtriEstrattoConto,
					1, 1);
		}
		
		/**
		 * tipoServizio=EP, tipoRichiesta=I o S, 
		 * @param codiceUtente 
		 * @param codiceEnte
		 * @param codFiscale
		 * @param tipoServizio
		 * @param dataPagamentoDa
		 * @param dataPagamentoA
		 * @param tipoIntegrazione
		 * @param filtriEstrattoConto
		 * @param rowsPerPage
		 * @param pageNumber
		 * @throws DaoException
		 */
		 private String[] doCachedRowSetListMultiPage(String codiceUtente, String codiceEnte, String codiceFiscale,
				String tipoServizio, String dataPagamentoDa, String dataPagamentoA, String tipoIntegrazione, String filtriEstrattoConto,
				int rowsPerPage, int pageNumber) throws DaoException {
			 
			CallableStatement callableStatement = null;
			try	
			{ 
				String[] outRes = new String[2];
				outRes[0] = "KO";
				outRes[1] = "Error";
					
//				if (tipoIntegrazione.equals("I") || tipoIntegrazione.equals("S"))
//				{
				callableStatement = prepareCall(Routines.EC_PAG_DOLIST.routine());				
				callableStatement.setString(1, codiceUtente);
				callableStatement.setString(2, codiceEnte);
				callableStatement.setString(3, codiceFiscale);
				callableStatement.setString(4, tipoServizio);
				callableStatement.setString(5, dataPagamentoDa);
				callableStatement.setString(6, dataPagamentoA);
				callableStatement.setString(7, filtriEstrattoConto);
								
				/* messaggio in uscita OK not OK */
				
				callableStatement.registerOutParameter(8, Types.VARCHAR);
				/* indicazione errore */
				callableStatement.registerOutParameter(9, Types.VARCHAR);
				if (callableStatement.execute()) 
				{
					this.loadWebRowSets(callableStatement);
				}			

				outRes[0] = callableStatement.getString(8).trim();
				outRes[1] = callableStatement.getString(9).trim();
//				}
//				else if (tipoIntegrazione.equals("D"))
//				{
//					// DIFFERITA
//					// non esiste solo IMMEDIATA o IMMEDIATA SEDA
//				}
				
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
		//PG160180_001 GG - fine
		 
		//10012017  GG - recupero lista enti estratto conto - inizio
		/**
		 * @param codiceUtente 
		 * @param codFiscale
		 * @param tipoServizio
		 * @param tipoIntegrazione
		 * @throws DaoException
		 */
		 public String[] getListEnti(String codiceUtente, String codiceFiscale, String tipoServizio,String tipoIntegrazione, String tipoRichiesta) throws DaoException {
			 
			CallableStatement callableStatement = null;
			try	
			{ 	
				String[] outRes = new String[2];
				outRes[0] = "KO";
				outRes[1] = "Error";
				
				if (tipoIntegrazione.equals("D"))
				{
					callableStatement = prepareCall(Routines.EC_DIFF_DOLIST_ENT.routine());
					callableStatement.setString(1, codiceUtente);
					callableStatement.setString(2, codiceFiscale);
					callableStatement.setString(3, tipoServizio);
					
					if (callableStatement.execute()) 
					{
						this.loadWebRowSets(callableStatement);
					}
				}else if(tipoIntegrazione.equals("I") || tipoIntegrazione.equals("S")){
					
					callableStatement = prepareCall(Routines.SPEPSTE1.routine());
					callableStatement.setString(1, codiceUtente);
					callableStatement.setString(2, codiceFiscale);
					callableStatement.setString(3, tipoServizio);
					callableStatement.setString(4, tipoRichiesta);
					
					callableStatement.registerOutParameter(5, Types.VARCHAR);
					callableStatement.registerOutParameter(6, Types.VARCHAR);
					
					
					if (callableStatement.execute()) 
					{
						this.loadWebRowSets(callableStatement);
					}
					outRes[0] = callableStatement.getString(5).trim();
					outRes[1] = callableStatement.getString(6).trim();
				}else if(tipoIntegrazione.equals("P")){
					
					callableStatement = prepareCall(Routines.PYTDTSP_LST_ENT.routine());
					callableStatement.setString(1, codiceUtente);
					callableStatement.setString(2, codiceFiscale);
					
//					callableStatement.registerOutParameter(5, Types.VARCHAR);
//					callableStatement.registerOutParameter(6, Types.VARCHAR);
					
					
					if (callableStatement.execute()) 
					{
						this.loadWebRowSets(callableStatement);
					}
//					outRes[0] = callableStatement.getString(5).trim();
//					outRes[1] = callableStatement.getString(6).trim();
					outRes[0] = "";
					outRes[1] = "";

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
		//10012017  GG - recupero lista enti estratto conto - fine 
		 
		private String leftPad(String in, char pad, int len) {
	    	StringBuffer buffer = new StringBuffer();
	    	while (buffer.length() < len-in.length()) {
	    		buffer.append(pad);
	    	}
	    	buffer.append(in);
	    	return buffer.toString();
	    }
		
		//PG170200 GG 31082017 - inizio
		/** 
		 * @param codiceUtente 
		 * @param codFiscale
		 * @param flagSms
		 * @param cellulare
		 * @param flagMail
		 * @param mail
		 * @param flagMailPec
		 * @param mailPec
		 * @throws DaoException
		 */
		public String[] allineaAnagraficaContatto(String codUtente, String tipoRichiesta, String codiceEnte, String codiceSettore, String codiceDocumento, String progCoobbligato, String codFiscale, String flagSms, String cellulare, String flagMail, String mail, String flagMailPec, String mailPec, String codiceSDI) throws DaoException 
		{
			CallableStatement callableStatement = null;
			String[] outRes = new String[2];
			outRes[0] = "KO";
			outRes[1] = "Error";
			
			try	
			{
				callableStatement = prepareCall(Routines.ALL_ANA_CONTATTO.routine());
				callableStatement.setString(1, codUtente);
				callableStatement.setString(2, "IN");
				callableStatement.setString(3, "");
				callableStatement.setString(4, "");
				callableStatement.setString(5, "");
				callableStatement.setString(6, codFiscale);
				callableStatement.setString(7, "");
				callableStatement.setString(8, flagSms);
				callableStatement.setString(9, cellulare);
				callableStatement.setString(10, flagMail);
				callableStatement.setString(11, mail);
				callableStatement.setString(12, flagMailPec);
				callableStatement.setString(13, mailPec);
				callableStatement.setString(14, codiceSDI);
				callableStatement.registerOutParameter(15, Types.CHAR);
				//callableStatement.registerOutParameter(15, Types.CHAR);
				callableStatement.setString(16, "");
				callableStatement.registerOutParameter(17, Types.CHAR);
				callableStatement.registerOutParameter(18, Types.CHAR);
				callableStatement.registerOutParameter(19, Types.CHAR);
				callableStatement.registerOutParameter(20, Types.CHAR);
				callableStatement.registerOutParameter(21, Types.CHAR);
				
				callableStatement.execute();
				
				outRes[0] = callableStatement.getString(20).trim();
				outRes[1] = callableStatement.getString(21).trim();
				
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
		//PG170200 GG 31082017 - fine
		
		//PG190120 GG - inizio
		public String[] getDescEnteTipologiaServizio(String codUtente, String codiceEnte, String tipoUfficio, String codiceUfficio, String codiceTipologiaServizio) throws DaoException 
		{
			CallableStatement callableStatement = null;
			String[] outRes = new String[2];
			outRes[0] = "";
			outRes[1] = "";
			
			try
			{
				callableStatement = prepareCall(Routines.DESCRSERVDESCRENTE.routine());
				callableStatement.setString(1, codiceEnte);
				callableStatement.setString(2, tipoUfficio);
				callableStatement.setString(3, codiceUfficio);
				callableStatement.setString(4, codiceTipologiaServizio);
				callableStatement.setString(5, codUtente);
				callableStatement.registerOutParameter(6, Types.VARCHAR);
				callableStatement.registerOutParameter(7, Types.VARCHAR);
				
				callableStatement.execute();
				
				outRes[0] = callableStatement.getString(7).trim();	//descrizione ente
				outRes[1] = callableStatement.getString(6).trim();	//descrizione tipologiaservizio
				
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
		//PG190120 GG - fine
		
		//20220808 SB - inizio
		/**
		 * codiceUtente= 000TO  , codiceEnte=06954, codiceFiscale=BAUFBA74T23L219Z, tipoServizio=EP, tipoRichiesta=I o S, 
		 * Il ritorno è 0 Esito e 1 MessaggioErrore
		 * tipoServizio=EP, tipoRichiesta=I o S, 
		 * @param codiceUtente 
		 * @param codiceEnte
		 * @param tipoUfficio
		 * @param codiceUfficio
		 * @param codFiscale
		 * @param tipoServizio
		 * @param tipoRichiesta
		 * @param tipologiaServizio
		 * @param numeroDoc
		 * @param TipoIntegrazione
		 * @param impostaServizio
		 * @throws DaoException
		 */
		public String[] doCachedRowAcquisizioneTransazioniIS(String codiceUtente, String codiceEnte, 
				String tipoUfficio, String codiceUfficio, String codiceFiscale,
				String tipoServizio, String tipoRichiesta, 
				String numeroDoc, String tipoIntegrazione, String impostaServizio) throws DaoException {
			
			CallableStatement callableStatement = null;	
			
			try	
			{
				String[] outRes = new String[2];
				outRes[0] = "KO";
				outRes[1] = "No Data";
				
				
				// IMMEDIATA
				callableStatement = prepareCall("PYTRASP_LSTNUMDOC_IS");	
				/*System.out.println("codiceUtente: " + codiceUtente);
				System.out.println("codiceEnte: " + codiceEnte);
				System.out.println("tipoUfficio: " + tipoUfficio);
				System.out.println("codiceUfficio: " + codiceUfficio);
				System.out.println("numeroDoc: " + numeroDoc);
				*/
				
				if (callableStatement != null)
				{
					callableStatement.setString(1, codiceUtente);
					callableStatement.setString(2, codiceEnte);
					callableStatement.setString(3, tipoUfficio);
					callableStatement.setString(4, codiceUfficio);
					callableStatement.setString(5, numeroDoc);
					callableStatement.setString(6, impostaServizio);
					
					if (callableStatement.execute()) 
					{
						outRes[0] = "00";
						outRes[1] = "OPERAZIONE EFFETTUATA";
						this.loadWebRowSets(callableStatement);					
					}
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
		//20220808 SB - fine

}
