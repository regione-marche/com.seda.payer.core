package com.seda.payer.core.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Types;
import java.text.SimpleDateFormat;

import com.seda.data.dao.DAOHelper;
import com.seda.data.helper.Helper;
import com.seda.data.helper.HelperException;
import com.seda.payer.commons.utility.LogUtility;
import com.seda.payer.core.bean.FlussiRen;
import com.seda.payer.core.exception.DaoException;
import com.seda.payer.core.handler.BaseDaoHandler;
import com.sun.rowset.WebRowSetImpl;

/**
 * @author s.parisi
 *
 */
public class FlussiRenDao extends BaseDaoHandler{
/**	
 * Il carattere usato per separare i campi nei file CSV
 */
	private final String csvSep = ";";
	protected CallableStatement callableStatementSelByKeyBatch = null;
	
	public FlussiRenDao(Connection connection, String schema) {
		super(connection, schema);
	}
    public static SimpleDateFormat ddMMyyyy = new SimpleDateFormat("dd/MM/yyyy");
	public static enum TipoStringa {XML,CSV };
	private static String listaCampiWeb = "\"TIPO_FLUSSO\";\"SOCIETA\";\"UTENTE\";\"ENTE\";\"UFFICIO\";\"DATA_CREAZIONE_FLUSSO\";\"GATEWAY_PAGAMENTO\";\"CHIAVE_FLUSSO\";\"NOME_FILE\";\"INVIO_FTP\";\"INVIO_EMAIL\";\"NUMERO_PAGAMENTI\";\"TOTALE_IMPORTI\"\n";
	
	/**
	 * Metodo che effettua la ricerca per chiaveRendicontazione e restituisce l'oggetto FlussiRen
	 * @param chiaveRendicontazione
	 * @return
	 * @throws DaoException
	 */
	public FlussiRen selectByKey (String chiaveRendicontazione)throws DaoException
	{
		CallableStatement callableStatement = null;
		ResultSet data = null;
			
		try{
			callableStatement = prepareCall(Routines.FLREN_SELBYKEY.routine());
			callableStatement.setString(1, chiaveRendicontazione);
			if (callableStatement.execute()) {
				data = callableStatement.getResultSet();
				if (data.next() && data.getString("REN_KRENKREN") != null) return FlussiRen.getBean(data);
			}
			return null;
		} catch (SQLException x) {
			throw new DaoException(x);
		} catch (IllegalArgumentException x) {
			throw new DaoException(x);
		} catch (HelperException x) {
			throw new DaoException(x);
		}
		finally
		{
			//inizio LP PG21XX04 Leak
			//DAOHelper.closeIgnoringException(callableStatement);
            //DAOHelper.closeIgnoringException(data);
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
	
	/**
	 * Metodo che inserisce un record (oggetti FlussiRen) nella tabella PYRENTB 
	 * @param fr
	 * @return
	 * @throws DaoException
	 */
	public boolean insRecord (FlussiRen fr)throws DaoException
	{
		CallableStatement callableStatement = null;
		try{
			if(selectByKey(fr.getChiaveRendicontazione()) == null){
				callableStatement = prepareCall(Routines.FLREN_INSRECORD.routine());
				callableStatement.setString(1,fr.getChiaveRendicontazione());
				callableStatement.setString(2, fr.getTipologiaFlusso());
				callableStatement.setString(3, fr.getCodiceSocieta());
				callableStatement.setString(4, fr.getCodiceUtente());
				callableStatement.setString(5, fr.getChiaveEnte());
				callableStatement.setInt(6, fr.getProgressivoFlusso());
				callableStatement.setString(7, fr.getCodiceTiplogiaServizio());
				callableStatement.setString(8, fr.getCodiceImpostaServizio());
				callableStatement.setString(9, fr.getChiaveGateway());
				callableStatement.setString(10, fr.getNumeroContoCorrentePostale());
				callableStatement.setString(11, fr.getNomeFile());
				callableStatement.setString(12, "N");
				callableStatement.setString(13, "N");
				callableStatement.setString(14, fr.getOperatoreUltimoAggiornamento());
				callableStatement.registerOutParameter(15, Types.INTEGER);
				callableStatement.executeUpdate();
				int numrighe =  callableStatement.getInt(15);
				if (numrighe == 1) return true;
				else return false;
			}
			return false;
		} catch (SQLException x) {
			throw new DaoException(x);
		} catch (IllegalArgumentException x) {
			throw new DaoException(x);
		} catch (HelperException x) {
			throw new DaoException(x);
		}
		finally
		{
			//inizio LP PG21XX04 Leak
			//DAOHelper.closeIgnoringException(callableStatement);
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
 * Questo metodo restituisce un webrowsetXML della lista di flussi 
 * di rendicontazione corrispondenti ai parametri di input.
 * Se è stata attivata la paginazione (paginazione='Y') 
 * viene valorizzato "pageInfo", in caso contrario vengono restituiti
 * tutti i record e "pageInfo" vale "null".
 * 
 * @param paginazione
 * @param rowsPerPage
 * @param pageNumber
 * @param order
 * @param codiceSocieta
 * @param codiceUtente
 * @param codiceEnte
 * @param codiceTransazione
 * @param codiceCanale
 * @param chiaveRendicontazione
 * @param codiceGateway
 * @param dataPagamentoDa
 * @param dataPagamentoA
 * @param codiceTipologiaServizio
 * @param codiceStrumento
 * @param tipoFlusso
 * @param dataCreazioneDa
 * @param dataCreazioneA
 * @return String
 * @throws DaoException
 */
	//LP PG200060 questo medoto non è più utilizzato dovrebbe essere commentato e poi cancellato
	//            ho allineato la lista dei parametri in ingresso come il metodo getListaFlussiWebRowSetImpl
	public String getListaFlussiRenXML(
			String paginazione,
			int rowsPerPage, 
			int pageNumber,
			String order,
			String codiceSocieta,
			String codiceUtente,
			String codiceEnte,
			String codiceTransazione,
			String codiceCanale,
			String chiaveRendicontazione,
			String codiceGateway,
			String dataPagamentoDa,
			String dataPagamentoA,
			String codiceTipologiaServizio,
			String codiceStrumento,
			String tipoFlusso,
			String dataCreazioneDa,
			String dataCreazioneA,
			String codiceProvincia,
			String idMovimentoCassa,
			String codicePSP //LP PG200060
) throws DaoException
	{
		CallableStatement callableStatement = null;
		ResultSet data = null;
		try
		{
			callableStatement = prepareCall(Routines.FLREN_SEL_FOR_GRID.routine());
			callableStatement.setString(1, paginazione.equals("Y") ? "Y" : "N");
			callableStatement.setInt(2, pageNumber);
			callableStatement.setInt(3, rowsPerPage);
			callableStatement.setString(4, order == null ? "" : order);
			callableStatement.setString(5, codiceSocieta == null ? "" :  codiceSocieta);
			callableStatement.setString(6, codiceUtente == null ? "" :  codiceUtente);
			callableStatement.setString(7, codiceEnte == null ? "" :  codiceEnte);
			callableStatement.setString(8, codiceTransazione == null ? "" :  codiceTransazione);
			callableStatement.setString(9, codiceCanale == null ? "" :  codiceCanale);
			callableStatement.setString(10, chiaveRendicontazione == null ? "" :  chiaveRendicontazione);
			callableStatement.setString(11, codiceGateway == null ? "" :  codiceGateway);
			callableStatement.setString(12, dataPagamentoDa == null ? "" :  dataPagamentoDa);
			callableStatement.setString(13, dataPagamentoA == null ? "" :  dataPagamentoA);
			callableStatement.setString(14, codiceTipologiaServizio == null ? "" :  codiceTipologiaServizio);
			callableStatement.setString(15, codiceStrumento == null ? "" :  codiceStrumento);
			callableStatement.setString(16, tipoFlusso == null ? "" :  tipoFlusso);
			callableStatement.setString(17, dataCreazioneDa == null ? "" :  dataCreazioneDa);
			callableStatement.setString(18, dataCreazioneA == null ? "" :  dataCreazioneA);
			callableStatement.setString(19,codiceProvincia);
			callableStatement.registerOutParameter(20, Types.VARCHAR);
			callableStatement.registerOutParameter(21, Types.INTEGER);
			callableStatement.registerOutParameter(22, Types.INTEGER);
			callableStatement.registerOutParameter(23, Types.INTEGER);
			callableStatement.registerOutParameter(24, Types.SMALLINT);
			callableStatement.setLong(25, idMovimentoCassa == null ? 0 : idMovimentoCassa.equals("") ? 0 : Long.parseLong(idMovimentoCassa));
			//inizio LP PG200060
			callableStatement.setString(26, codicePSP == null ? "" : codicePSP);
			//fine LP PG200060

			if(callableStatement.execute())
			{
				data = callableStatement.getResultSet();
				loadWebRowSet(data);
				if(paginazione.equals("Y")) 
					registerPageInfo(rowsPerPage, pageNumber, callableStatement.getInt(21), callableStatement.getInt(22), callableStatement.getInt(23), callableStatement.getInt(24));
				return getWebRowSetXml();
			}
			return null;
		}
		catch (IllegalArgumentException e) {
			throw new DaoException(e);
		} catch (SQLException e) {
			throw new DaoException(e);
		} catch (HelperException e) {
			throw new DaoException(e);
		}
		finally
		{
			//inizio LP PG21XX04 Leak
			//DAOHelper.closeIgnoringException(callableStatement);
            //DAOHelper.closeIgnoringException(data);
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
	
	public String convertWebRowSetImplToCsv(WebRowSetImpl data, String listaCampi) throws DaoException
	{
		String dati_csv = null;
		String file_csv = null;
		ResultSetMetaData rsmd = null;
		StringBuffer sb_dati = new StringBuffer();
		StringBuffer sb_intestazione = new StringBuffer();
		if(data == null) return null;
		try {
			rsmd = data.getMetaData();
			int numcol = rsmd.getColumnCount();
			//
			// Inserisco i dati
			//
			while(data.next())
			{
				/*
				 * Scrivo i primi n-1 campi di una riga
				 */
				for(int i=1;i<numcol;i++)
				{
					sb_dati.append("\"");
					sb_dati.append(getColumnData(data, rsmd, i));
					sb_dati.append("\"");
					sb_dati.append(csvSep);
				}
				/*
				 * Scrivo l'ultimo campo della riga
				 * più il line feed
				 */
				sb_dati.append("\"");
				sb_dati.append(getColumnData(data, rsmd, numcol));
				sb_dati.append("\"\n");
			}
			dati_csv = sb_dati.toString();
			if ((dati_csv != null) && (!dati_csv.equals("")))
			{
				//
				// inserisco la prima riga con i nomi dei campi
				// li prendo dai metadati oppure da "listaCampi"
				//
				if(listaCampi == null)
				{
					for(int i=1;i<numcol;i++)
					{
						sb_intestazione.append("\"");
						sb_intestazione.append(rsmd.getColumnName(i));
						sb_intestazione.append("\",");
					}
					sb_intestazione.append("\"");
					sb_intestazione.append(rsmd.getColumnName(numcol));
					sb_intestazione.append("\"\n");
					
				}
				else
				{
					sb_intestazione.append(listaCampi);
				}
				file_csv = sb_intestazione.toString() + dati_csv;
			}
			return file_csv;
		} catch (SQLException e) {
			throw new DaoException(e);
		}
	}

	public WebRowSetImpl getListaFlussiWebRowSetImpl(
			String paginazione,
			int rowsPerPage, 
			int pageNumber,
			String order,
			String codiceSocieta,
			String codiceUtente,
			String codiceEnte,
			String codiceTransazione,
			String codiceCanale,
			String chiaveRendicontazione,
			String codiceGateway,
			String dataPagamentoDa,
			String dataPagamentoA,
			String codiceTipologiaServizio,
			String codiceStrumento,
			String tipoFlusso,
			String dataCreazioneDa,
			String dataCreazioneA,
			String codiceProvincia,
			String idMovimentoCassa,
			String codicePSP,
			String movimentoCassa,
			String giornaleCassa
) throws DaoException
	{
		CallableStatement callableStatement = null;
		ResultSet data = null;
		WebRowSetImpl out = null;
		try
		{
			callableStatement = prepareCall(Routines.FLREN_SEL_FOR_GRID.routine());
			callableStatement.setString(1, paginazione.equals("Y") ? "Y" : "N");
			callableStatement.setInt(2, pageNumber);
			callableStatement.setInt(3, rowsPerPage);
			callableStatement.setString(4, order == null ? "" : order);
			callableStatement.setString(5, codiceSocieta == null ? "" :  codiceSocieta);
			callableStatement.setString(6, codiceUtente == null ? "" :  codiceUtente);
			callableStatement.setString(7, codiceEnte == null ? "" :  codiceEnte);
			callableStatement.setString(8, codiceTransazione == null ? "" :  codiceTransazione);
			callableStatement.setString(9, codiceCanale == null ? "" :  codiceCanale);
			callableStatement.setString(10, chiaveRendicontazione == null ? "" :  chiaveRendicontazione);
			callableStatement.setString(11, codiceGateway == null ? "" :  codiceGateway);
			callableStatement.setString(12, dataPagamentoDa == null ? "" :  dataPagamentoDa);
			callableStatement.setString(13, dataPagamentoA == null ? "" :  dataPagamentoA);
			callableStatement.setString(14, codiceTipologiaServizio == null ? "" :  codiceTipologiaServizio);
			callableStatement.setString(15, codiceStrumento == null ? "" :  codiceStrumento);
			callableStatement.setString(16, tipoFlusso == null ? "" :  tipoFlusso);
			callableStatement.setString(17, dataCreazioneDa == null ? "" :  dataCreazioneDa);
			callableStatement.setString(18, dataCreazioneA == null ? "" :  dataCreazioneA);
			callableStatement.setString(19, codiceProvincia);
			callableStatement.registerOutParameter(20, Types.VARCHAR);
			callableStatement.registerOutParameter(21, Types.INTEGER);
			callableStatement.registerOutParameter(22, Types.INTEGER);
			callableStatement.registerOutParameter(23, Types.INTEGER);
			callableStatement.registerOutParameter(24, Types.SMALLINT);
			callableStatement.setLong(25, idMovimentoCassa == null ? 0 : idMovimentoCassa.equals("") ? 0 : Long.parseLong(idMovimentoCassa));
			callableStatement.setString(26, codicePSP == null ? "" : codicePSP);
			callableStatement.setLong(27, movimentoCassa == null ? 0 : movimentoCassa.equals("") ? 0 : Long.parseLong(movimentoCassa));
			callableStatement.setString(28, giornaleCassa == null ? "" : giornaleCassa.trim());

			if(callableStatement.execute())
			{
				data = callableStatement.getResultSet();
				loadWebRowSet(data);
				if(paginazione.equals("Y")) 
					registerPageInfo(rowsPerPage, pageNumber, callableStatement.getInt(21), callableStatement.getInt(22), callableStatement.getInt(23), callableStatement.getInt(24));
				out = getWebRowSetImpl();
			}
			return out;
		}
		catch (IllegalArgumentException e) {
			throw new DaoException(e);
		} catch (SQLException e) {
			throw new DaoException(e);
		} catch (HelperException e) {
			throw new DaoException(e);
		}
		finally
		{
			//inizio LP PG21XX04 Leak
			//DAOHelper.closeIgnoringException(callableStatement);
            //DAOHelper.closeIgnoringException(data);
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

	public WebRowSetImpl getListaFlussiGroupedWebRowSetImpl(
			String codiceSocieta,
			String codiceUtente,
			String codiceEnte,
			String codiceTransazione,
			String codiceCanale,
			String chiaveRendicontazione,
			String codiceGateway,
			String dataPagamentoDa,
			String dataPagamentoA,
			String codiceTipologiaServizio,
			String codiceStrumento,
			String tipoFlusso,
			String dataCreazioneDa,
			String dataCreazioneA,
			String codiceProvincia,
			String idMovimentoCassa,
			String codicePSP,
			String movimentoCassa,
			String giornaleCassa
) throws DaoException
	{
		CallableStatement callableStatement = null;
		ResultSet data = null;
		WebRowSetImpl out = null;
		try
		{
			callableStatement = prepareCall(Routines.FLREN_SEL_FOR_GROUP.routine());
			callableStatement.setString(1, codiceSocieta == null ? "" :  codiceSocieta);
			callableStatement.setString(2, codiceUtente == null ? "" :  codiceUtente);
			callableStatement.setString(3, codiceEnte == null ? "" :  codiceEnte);
			callableStatement.setString(4, codiceTransazione == null ? "" :  codiceTransazione);
			callableStatement.setString(5, codiceCanale == null ? "" :  codiceCanale);
			callableStatement.setString(6, chiaveRendicontazione == null ? "" :  chiaveRendicontazione);
			callableStatement.setString(7, codiceGateway == null ? "" :  codiceGateway);
			callableStatement.setString(8, dataPagamentoDa == null ? "" :  dataPagamentoDa);
			callableStatement.setString(9, dataPagamentoA == null ? "" :  dataPagamentoA);
			callableStatement.setString(10, codiceTipologiaServizio == null ? "" :  codiceTipologiaServizio);
			callableStatement.setString(11, codiceStrumento == null ? "" :  codiceStrumento);
			callableStatement.setString(12, tipoFlusso == null ? "" :  tipoFlusso);
			callableStatement.setString(13, dataCreazioneDa == null ? "" :  dataCreazioneDa);
			callableStatement.setString(14, dataCreazioneA == null ? "" :  dataCreazioneA);
			callableStatement.setString(15,codiceProvincia);
			callableStatement.registerOutParameter(16, Types.VARCHAR);
			callableStatement.setLong(17, idMovimentoCassa == null ? 0 : idMovimentoCassa.equals("") ? 0 : Long.parseLong(idMovimentoCassa));
			callableStatement.setString(18, codicePSP == null ? "" : codicePSP);
			callableStatement.setLong(19, movimentoCassa == null ? 0 : movimentoCassa.equals("") ? 0 : Long.parseLong(movimentoCassa));
			callableStatement.setString(20, giornaleCassa == null ? "" : giornaleCassa.trim());
			if(callableStatement.execute())
			{
				data = callableStatement.getResultSet();
				loadWebRowSet(data);
				out = getWebRowSetImpl();
			}
			return out;
		}
		catch (IllegalArgumentException e) {
			throw new DaoException(e);
		} catch (SQLException e) {
			throw new DaoException(e);
		} catch (HelperException e) {
			throw new DaoException(e);
		}
		finally
		{
			//inizio LP PG21XX04 Leak
			//DAOHelper.closeIgnoringException(callableStatement);
            //DAOHelper.closeIgnoringException(data);
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
	
	public String getStringListaFlussi(
			TipoStringa formato,
			String paginazione,
			int rowsPerPage, 
			int pageNumber,
			String order,
			String codiceSocieta,
			String codiceUtente,
			String codiceEnte,
			String codiceTransazione,
			String codiceCanale,
			String chiaveRendicontazione,
			String codiceGateway,
			String dataPagamentoDa,
			String dataPagamentoA,
			String codiceTipologiaServizio,
			String codiceStrumento,
			String tipoFlusso,
			String dataCreazioneDa,
			String dataCreazioneA,
			String codiceProvincia,
			String idMovimentoCassa,
			String codicePSP,
			String movimentoCassa,
			String giornaleCassa
			) throws DaoException
	{
		String s_return = null;
		WebRowSetImpl wrsi = getListaFlussiWebRowSetImpl(paginazione, rowsPerPage, pageNumber, order, codiceSocieta, codiceUtente, codiceEnte, codiceTransazione, codiceCanale, chiaveRendicontazione, codiceGateway, dataPagamentoDa, dataPagamentoA, codiceTipologiaServizio, codiceStrumento, tipoFlusso, dataCreazioneDa, dataCreazioneA,codiceProvincia,idMovimentoCassa, codicePSP, movimentoCassa, giornaleCassa);
		try 
		{
			if(wrsi != null)
			{
				switch(formato)
				{
					case CSV:
						s_return = convertWebRowSetImplToCsv(wrsi, listaCampiWeb);
						break;
					case XML:
					default:
						s_return = getWebRowSetXml();
						break;
				}
				return s_return;
			}
			return null;
		} 
		catch (SQLException e) {
			throw new DaoException(e);
		}
		//inizio LP PG21XX04 Leak
		finally {
			if (wrsi != null) {
				try {
					wrsi.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		//fine LP PG21XX04 Leak
	}

	public String getStringListaFlussiGrouped(
			TipoStringa formato,
			String codiceSocieta,
			String codiceUtente,
			String codiceEnte,
			String codiceTransazione,
			String codiceCanale,
			String chiaveRendicontazione,
			String codiceGateway,
			String dataPagamentoDa,
			String dataPagamentoA,
			String codiceTipologiaServizio,
			String codiceStrumento,
			String tipoFlusso,
			String dataCreazioneDa,
			String dataCreazioneA,
			String codiceProvincia,
			String idMovimentoCassa,
			String codicePSP,
			String movimentoCassa,
			String giornaleCassa
			) throws DaoException
	{
		String s_return = null;
		WebRowSetImpl wrsi = getListaFlussiGroupedWebRowSetImpl( codiceSocieta, codiceUtente, codiceEnte, codiceTransazione, codiceCanale, chiaveRendicontazione, codiceGateway, dataPagamentoDa, dataPagamentoA, codiceTipologiaServizio, codiceStrumento, tipoFlusso, dataCreazioneDa, dataCreazioneA,codiceProvincia,idMovimentoCassa, codicePSP, movimentoCassa, giornaleCassa);
		try 
		{
			if(wrsi != null)
			{
				switch(formato)
				{
					case CSV:
						s_return = convertWebRowSetImplToCsv(wrsi, null);
						break;
					case XML:
					default:
						s_return = getWebRowSetXml();
						break;
				}
				return s_return;
			}
			return null;
		} 
		catch (SQLException e) {
			throw new DaoException(e);
		}
		//inizio LP PG21XX04 Leak
		finally {
			if (wrsi != null) {
				try {
					wrsi.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		//fine LP PG21XX04 Leak
	}
	
	/**
	 * Metodo che ritorna true se il flusso di rendicontazione è stato inviato
	 * false altrimenti
	 * 
	 * @param chiaveRendicontazione
	 * @return
	 * @throws DaoException
	 */
	public boolean getStatoInvio (String chiaveRendicontazione)throws DaoException{
		try 
		{
			FlussiRen fr = selectByKey(chiaveRendicontazione);
			//if(fr==null){
				//System.out.println(chiaveRendicontazione);
			//}
			boolean ok=(fr.getFlagInvioFtp().equalsIgnoreCase("Y") || fr.getFlagInvioMail().equalsIgnoreCase("Y"));
			//inizio LP PG21XX04
			ok = (ok || fr.getFlagInvioWS().equalsIgnoreCase("Y"));
			//fine LP PG21XX04 
			return ok;
		} catch (IllegalArgumentException x) {
			throw new DaoException(x);
		}
	}
	
	/**
	 * Metodo che ritorna true se il flusso di rendicontazione è stato inviato
	 * false altrimenti
	 * 
	 * @param chiaveRendicontazione
	 * @return
	 * @throws DaoException
	 */
	public boolean getStatoInvioBatch (String chiaveRendicontazione)throws DaoException{
		try 
		{
			FlussiRen fr = selectByKeyBatch(chiaveRendicontazione);
			//if(fr==null){
				//System.out.println(chiaveRendicontazione);
			//}
			boolean ok=(fr.getFlagInvioFtp().equalsIgnoreCase("Y") || fr.getFlagInvioMail().equalsIgnoreCase("Y"));
			//inizio LP PG21XX04
			ok = (ok || fr.getFlagInvioWS().equalsIgnoreCase("Y"));
			//fine LP PG21XX04 
			return ok;
		} catch (IllegalArgumentException x) {
			throw new DaoException(x);
		}
	}
	
	/**
	 * Metodo che effettua la ricerca per chiaveRendicontazione e restituisce l'oggetto FlussiRen
	 * @param chiaveRendicontazione
	 * @return
	 * @throws DaoException
	 */
	public FlussiRen selectByKeyBatch (String chiaveRendicontazione)throws DaoException
	{
		ResultSet data = null;
	
		try{
			if (callableStatementSelByKeyBatch == null) {
				callableStatementSelByKeyBatch = prepareCall(Routines.FLREN_SELBYKEY.routine());
			} 
			callableStatementSelByKeyBatch.setString(1, chiaveRendicontazione);
			if (callableStatementSelByKeyBatch.execute()) {
				data = callableStatementSelByKeyBatch.getResultSet();
				if (data.next() && data.getString("REN_KRENKREN") != null) return FlussiRen.getBean(data);
			}
			return null;
		} catch (SQLException x) {
			throw new DaoException(x);
		} catch (IllegalArgumentException x) {
			throw new DaoException(x);
		} catch (HelperException x) {
			throw new DaoException(x);
		}
		finally
		{
			if (data != null) {
				try {
					data.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	/**
	 * Metodo che inserisce un record (oggetti FlussiRen) nella tabella PYRENTB 
	 * @param fr
	 * @return
	 * @throws DaoException
	 */
	//inizio LP PG21XX04
	//public boolean updRecord (FlussiRen fr,boolean bFtp,boolean bEmail)throws DaoException
	public boolean updRecord (FlussiRen fr, boolean bFtp, boolean bEmail, boolean bWS) throws DaoException
	//fine LP PG21XX04
	{
		CallableStatement callableStatement = null;
		try{
			callableStatement = prepareCall(Routines.FLREN_UPD.routine());
			callableStatement.setString(1,fr.getChiaveRendicontazione());
			callableStatement.setString(2, fr.getTipologiaFlusso());
			callableStatement.setString(3, fr.getCodiceSocieta());
			callableStatement.setString(4, fr.getCodiceUtente());
			callableStatement.setString(5, fr.getChiaveEnte());
			callableStatement.setTimestamp(6, new java.sql.Timestamp(System.currentTimeMillis()));
			callableStatement.setNull(7, Types.INTEGER);
			callableStatement.setNull(8, Types.VARCHAR);
			callableStatement.setNull(9, Types.VARCHAR);
			callableStatement.setNull(10, Types.VARCHAR);
			callableStatement.setNull(11, Types.VARCHAR);
			callableStatement.setNull(12, Types.VARCHAR);
			
			if (bEmail)
				callableStatement.setString(13, "Y");
			else
				callableStatement.setNull(13, Types.VARCHAR);
			if(bFtp)
				callableStatement.setString(14, "Y");
			else
				callableStatement.setNull(14, Types.VARCHAR);
			//inizio LP PG21XX04
			//callableStatement.setTimestamp(15, new java.sql.Timestamp(System.currentTimeMillis()));
			//callableStatement.setString(16, fr.getOperatoreUltimoAggiornamento());
			if(bWS)
				callableStatement.setString(15, "Y");
			else
				callableStatement.setNull(15, Types.VARCHAR);

			callableStatement.setTimestamp(16, new java.sql.Timestamp(System.currentTimeMillis()));
			callableStatement.setString(17, fr.getOperatoreUltimoAggiornamento());
			//fine LP PG21XX04

			callableStatement.executeUpdate();

			return true;
			
		} catch (SQLException x) {
			throw new DaoException(x);
		} catch (IllegalArgumentException x) {
			throw new DaoException(x);
		} catch (HelperException x) {
			throw new DaoException(x);
		}
		finally
		{
			//inizio LP PG21XX04 Leak
			//DAOHelper.closeIgnoringException(callableStatement);
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

	private String getColumnData(WebRowSetImpl data,ResultSetMetaData rsmd, int i)
	{
		String columnData = null;
		try {
			String nomeTipo = rsmd.getColumnTypeName(i);
			if(nomeTipo.equals("TIMESTAMP"))
			{
				columnData = ddMMyyyy.format(data.getObject(i));
			}
			else
			{
				if(nomeTipo.equals("DECIMAL"))
				{
					columnData = data.getObject(i).toString().replace('.', ',');
				}
				else
				{
					columnData = data.getObject(i).toString();
					columnData = (columnData.equalsIgnoreCase("Y") ? "SI" : (columnData.equalsIgnoreCase("N") ? "NO" : columnData) );
				}
			}
		} catch (SQLException e) {
			columnData= null;
		}
		return columnData;
	}

	//Inserito il metodo per poter ottenere il codiceUtenteSeda
	//relativo alla PYRENSP_LST_CONFIG
	public String codiceSeda (String codiceSocieta,String codiceUtente,String chiaveEnte,
			String codiceTipologiaServizio) throws DaoException
	{
		//inizio LP PG21XX04 Leak
		//CallableStatement callableStatement;
		CallableStatement callableStatement = null;
		ResultSet data = null;
		//fine LP PG21XX04 Leak
		String codSeda = null;
		try
		{
			
			{
				callableStatement = prepareCall(Routines.FLREN_COD_UTE.routine());
				callableStatement.setString(1, codiceSocieta == null ? "" :  codiceSocieta);
				callableStatement.setString(2, codiceUtente == null ? "" :  codiceUtente);
				callableStatement.setString(3, chiaveEnte == null ? "" : chiaveEnte);
				callableStatement.setString(4, codiceTipologiaServizio == null ? "" : codiceTipologiaServizio);
				if(callableStatement.execute())
				{
					//inizio LP PG21XX04 Leak
					//ResultSet data = callableStatement.getResultSet();
					data = callableStatement.getResultSet();
					//fine LP PG21XX04 Leak
					if (data.next())
					{
						codSeda = data.getString(1);
					
						
					}
				}
			}
		}
		catch (IllegalArgumentException e) {
			throw new DaoException(e);
		} catch (SQLException e) {
			throw new DaoException(e);
		} catch (HelperException e) {
			throw new DaoException(e);
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
		return codSeda;
	}

	public Integer updateEsitoRendicontazioneComplex ()throws DaoException
	{
		CallableStatement callableStatement = null;
		int numrighe= -1;

		try{
			callableStatement = prepareCall(Routines.PYRENSP_UPD_ST_REN_ALL.routine());
			callableStatement.registerOutParameter(1, Types.INTEGER);
			callableStatement.executeUpdate();
			numrighe =  callableStatement.getInt(1);

			return numrighe;
		} catch (SQLException x) {
			throw new DaoException(x);
		} catch (IllegalArgumentException x) {
			throw new DaoException(x);
		} catch (HelperException x) {
			throw new DaoException(x);
		}
		finally
		{
			if (callableStatement != null) {
				try {
					callableStatement.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	
}
