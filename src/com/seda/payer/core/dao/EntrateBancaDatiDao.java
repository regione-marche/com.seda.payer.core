package com.seda.payer.core.dao;

import java.math.BigDecimal;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import javax.sql.rowset.WebRowSet;
import com.seda.data.helper.HelperException;
import com.seda.payer.core.bean.EntrateDocumentiPage;
import com.seda.payer.core.bean.Documento;
import com.seda.payer.core.bean.EntrateNotePage;
import com.seda.payer.core.bean.EntratePagamentiDocumentiPage;
import com.seda.payer.core.bean.EntratePagamentiPage;
import com.seda.payer.core.bean.EntratePagamentiCSV;
import com.seda.payer.core.bean.EntrateAnagrafichePage;
import com.seda.payer.core.bean.EntrateDettaglioAnagrafica;
import com.seda.payer.core.bean.EntrateDocumentiAnagrafica;
import com.seda.payer.core.bean.EntrateDettaglioEmissioneS;
import com.seda.payer.core.bean.EntrateDocumentiEmissione;
import com.seda.payer.core.bean.Paginazione;
import com.seda.payer.core.bean.EntrateDocumentoDettaglio;
import com.seda.payer.core.bean.EntrateTributoDettaglio;
import com.seda.payer.core.bean.Scadenza;
import com.seda.payer.core.bean.EntrateScadenzePage;
import com.seda.payer.core.bean.EntrateTributiPage;
import com.seda.payer.core.exception.DaoException;
import com.seda.payer.core.handler.BaseDaoHandler;
import com.seda.payer.core.messages.Messages;

//@SuppressWarnings("unchecked")
public class EntrateBancaDatiDao extends BaseDaoHandler {
	
	//Dom

	/*
	private final String PYEH3SP_LST = "PYEH3SP_LST";	//OK
	private final String PYEH3SP_LST_CSV = "PYEH3SP_LST_CSV";
	private final String PYEH8SP_LST = "PYEH8SP_LST";	
	private final String PYEH8SP_SEL = "PYEH8SP_SEL";
	private final String PYEH1SP_DOC_ANA_LST = "PYEH1SP_DOC_ANA_LST2";	

	private final String PYEH1SP_EMI_S_SEL = "PYEH1SP_EMI_S_SEL";
	private final String PYEH1SP_DOC_EMI_LST = "PYEH1SP_DOC_EMI_LST2";	
	
	//Graz
//	private final String PYEH1SP_LST = "PYEH1SP_LST";	//OK
	private final String PYELGSP_SEL_DATE = "PYELGSP_SEL_DATE";
	private final String PYEH1SP_STAT = "PYEH1SP_STAT";
	private final String PYANESP_LST_DDL_UFF = "PYANESP_LST_DDL_UFF";
	private final String PYISESP_LST_DDL = "PYISESP_LST_DDL";
	private final String PYEH1SP_SEL = "PYEH1SP_SEL";
	private final String PYEH7SP_LST = "PYEH7SP_LST";
	private final String PYEH2SP_LST = "PYEH2SP_LST";
	private final String PYEH3SP_LST_DOC = "PYEH3SP_LST_DOC";
	
	private final String PYEH7SP_LST_NOTE = "PYEH7SP_LST_NOTE";
	private final String PYEH7SP_SEL = "PYEH7SP_SEL";
	private final String PYEH1SP_LST_EMIS = "PYEH1SP_LST_EMIS";
	private final String PYEH1SP_STAT_EMIS = "PYEH1SP_STAT_EMIS";
	private final String PYEH1SP_SEL_EMIS = "PYEH1SP_SEL_EMIS";
	*/

	public EntrateBancaDatiDao(Connection connection, String schema) {
		super(connection, schema);
	}

// pagamenti	
	public EntratePagamentiPage getPagamenti(EntratePagamentiPage dto, String ordine) throws DaoException {
		return getPagamenti(dto, ordine, 0, 0);
	}

	public EntratePagamentiPage getPagamenti(EntratePagamentiPage dto, String ordine, int rowsPerPage, int pageNumber) throws DaoException {
		if (rowsPerPage <= 0)
			throw new IllegalArgumentException(Messages.INVALID_PARAMETER.format("rowsPerPage"));
		if (pageNumber <= 0)
			throw new IllegalArgumentException(Messages.INVALID_PARAMETER.format("pageNumber"));
		dto = getListaPagamenti(dto, ordine, rowsPerPage, pageNumber);
		return dto;
	}

	private EntratePagamentiPage getListaPagamenti(EntratePagamentiPage dto, String ordine, int rowsPerPage, int pageNumber) throws DaoException 
	{
		//CallableStatement callableStatement = null;
		//inizio LP PG21XX04 Leak
		CallableStatement callableStatement = null;
		//fine LP PG21XX04 Leak
		try	{
//			CallableStatement callableStatement = prepareCall(PYEH3SP_LST);		
			//inizio LP PG21XX04 Leak
			//CallableStatement callableStatement = prepareCall(Routines.EH3_DOLIST.routine());
			callableStatement = prepareCall(Routines.EH3_DOLIST.routine());
			//fine LP PG21XX04 Leak
			callableStatement.setString(1, dto.getCodiceSocieta());
			callableStatement.setString(2, dto.getCodiceUtente());
			callableStatement.setString(3, dto.getCodiceEnte());			
			callableStatement.setString(4, dto.getImpostaServizio());
			callableStatement.setString(5, dto.getCodiceFiscale());
			callableStatement.setString(6, dto.getModalita());
			callableStatement.setString(7, dto.getTipoMovimento());
			callableStatement.setString(8, dto.getTipoUfficio());
			callableStatement.setString(9, dto.getCodiceUfficio());
			callableStatement.setString(10, dto.getAnnoEmissione());
			callableStatement.setString(11, dto.getNumeroEmissione());
			callableStatement.setString(12, dto.getDataPagamentoDa());
			callableStatement.setString(13, dto.getDataPagamentoA());
			callableStatement.setString(14, dto.getTipologiaServizio());
			callableStatement.setString(15, dto.getNumeroDocumento());
			if (dto.getProgRiscossione()==null || dto.getProgRiscossione().equals(""))
				callableStatement.setInt(16, -1);
			else	
				callableStatement.setInt(16, Integer.parseInt(dto.getProgRiscossione()));
			callableStatement.setString(17, ordine);
			callableStatement.setInt(18, rowsPerPage);
			callableStatement.setInt(19, pageNumber);
			/* we register row start */
			callableStatement.registerOutParameter(20, Types.INTEGER);
			/* we register row end */
			callableStatement.registerOutParameter(21, Types.INTEGER);
			/* we register total rows */
			callableStatement.registerOutParameter(22, Types.INTEGER);
			/* we register total pages */
			callableStatement.registerOutParameter(23, Types.SMALLINT);
			/* we execute procedure */
			if (callableStatement.execute()) {
				this.loadWebRowSets(callableStatement);
				/* we register page info */
				registerPageInfo(rowsPerPage, pageNumber, callableStatement.getInt(20), callableStatement.getInt(21), 
								 callableStatement.getInt(22), callableStatement.getInt(23));
				dto.setPageInfo(getPageInfo());
				dto.setListXml(getWebRowSetXml(EccedenzeDao.IDX_DOLIST_LISTA));
			}
		} catch (SQLException x) {
			throw new DaoException(x);
		} catch (IllegalArgumentException x) {
			throw new DaoException(x);
		} catch (HelperException x) {
			throw new DaoException(x);
		} finally {
			//inizio LP PG21XX04 Leak
			if (callableStatement != null) {
				try {
					callableStatement.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			//fine LP PG21XX04 Leak
		}
		return dto;
	}

	public WebRowSet estraiPagamentiCsv(EntratePagamentiCSV dto) throws DaoException	
	{	
		ResultSet data = null;
		WebRowSet rowSet = null;
		//inizio LP PG21XX04 Leak
		CallableStatement callableStatement = null;
		//fine LP PG21XX04 Leak
		try {
			//CallableStatement callableStatement = prepareCall(PYEH3SP_LST_CSV);		
			//inizio LP PG21XX04 Leak
			//CallableStatement callableStatement = prepareCall(Routines.EH3_DOLIST_CSV.routine());
			callableStatement = prepareCall(Routines.EH3_DOLIST_CSV.routine());
			//fine LP PG21XX04 Leak
			callableStatement.setString(1, dto.getCodiceSocieta());
			callableStatement.setString(2, dto.getCodiceUtente());
			callableStatement.setString(3, dto.getCodiceEnte());			
			callableStatement.setString(4, dto.getImpostaServizio());
			callableStatement.setString(5, dto.getCodiceFiscale());
			callableStatement.setString(6, dto.getModalita());
			callableStatement.setString(7, dto.getTipoMovimento());
			callableStatement.setString(8, dto.getTipoUfficio());
			callableStatement.setString(9, dto.getCodiceUfficio());
			callableStatement.setString(10, dto.getAnnoEmissione());
			callableStatement.setString(11, dto.getNumeroEmissione());
			callableStatement.setString(12, dto.getDataPagamentoDa());
			callableStatement.setString(13, dto.getDataPagamentoA());
			callableStatement.setString(14, dto.getTipologiaServizio());
			callableStatement.setString(15, dto.getNumeroDocumento());
			if (dto.getProgRiscossione()==null || dto.getProgRiscossione().equals(""))
				callableStatement.setInt(16, -1);
			else	
				callableStatement.setInt(16, (Integer.parseInt(dto.getProgRiscossione())));
			//inizio LP 20240810 - PGNTCORE-24
				//data = callableStatement.executeQuery();
			if(callableStatement.execute()) {
				data = callableStatement.getResultSet();
			//fine LP 20240810 - PGNTCORE-24
				if (data != null) {
					loadWebRowSet(data);
					rowSet = getWebRowSet();
				}
			//inizio LP 20240810 - PGNTCORE-24
			}
			//fine LP 20240810 - PGNTCORE-24
		} catch (SQLException x) {
			throw new DaoException(x);
		} catch (IllegalArgumentException x) {
			throw new DaoException(x);
		} catch (HelperException x) {
			throw new DaoException(x);
		}
		//inizio LP PG21XX04 Leak
		//finally {}
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
		return rowSet;
	}

	//anagrafiche
	public EntrateAnagrafichePage getAnagrafiche(EntrateAnagrafichePage dto) throws DaoException {
		Paginazione paginazione = dto.getPaginazione();
		if (paginazione.getRowsPerPage() <= 0)
			throw new IllegalArgumentException(Messages.INVALID_PARAMETER.format("rowsPerPage"));

		if (paginazione.getPageNumber() <= 0)
			throw new IllegalArgumentException(Messages.INVALID_PARAMETER.format("pageNumber"));
		//CallableStatement callableStatement = null;
		//inizio LP PG21XX04 Leak
		CallableStatement callableStatement = null;
		//fine LP PG21XX04 Leak
		try	{
			//CallableStatement callableStatement = prepareCall(PYEH8SP_LST);		
			//inizio LP PG21XX04 Leak
			//CallableStatement callableStatement = prepareCall(Routines.EH8_DOLIST.routine());
			callableStatement = prepareCall(Routines.EH8_DOLIST.routine());
			//fine LP PG21XX04 Leak
			callableStatement.setString(1, dto.getCodiceSocieta());
			callableStatement.setString(2, dto.getCodiceUtente());
			callableStatement.setString(3, dto.getCodiceEnte());			
			callableStatement.setString(4, dto.getImpostaServizio());
			callableStatement.setString(5, dto.getCodiceFiscale());
			callableStatement.setString(6, dto.getDenominazione());
			callableStatement.setString(7, dto.getTipoRic());
			callableStatement.setString(8, paginazione.getOrder());
			callableStatement.setInt(9, paginazione.getRowsPerPage());
			callableStatement.setInt(10, paginazione.getPageNumber());
			/* we register row start */
			callableStatement.registerOutParameter(11, Types.INTEGER);
			/* we register row end */
			callableStatement.registerOutParameter(12, Types.INTEGER);
			/* we register total rows */
			callableStatement.registerOutParameter(13, Types.INTEGER);
			/* we register total pages */
			callableStatement.registerOutParameter(14, Types.SMALLINT);
			/* we execute procedure */
			if (callableStatement.execute()) {
				this.loadWebRowSets(callableStatement);
				/* we register page info */
				registerPageInfo(paginazione.getRowsPerPage(), paginazione.getPageNumber(), callableStatement.getInt(11), callableStatement.getInt(12), 
								 callableStatement.getInt(13), callableStatement.getInt(14));
				dto.setPageInfo(getPageInfo());
				dto.setListXml(getWebRowSetXml(EccedenzeDao.IDX_DOLIST_LISTA));
			}
		} catch (SQLException x) {
			throw new DaoException(x);
		} catch (IllegalArgumentException x) {
			throw new DaoException(x);
		} catch (HelperException x) {
			throw new DaoException(x);
		} finally {
			//inizio LP PG21XX04 Leak
			if (callableStatement != null) {
				try {
					callableStatement.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			//fine LP PG21XX04 Leak
		}
		return dto;
	}

	public EntrateDettaglioAnagrafica getDettaglioAnagrafica(EntrateDettaglioAnagrafica dto) throws DaoException 
	{
//		CallableStatement callableStatement = null;
		//inizio LP PG21XX04 Leak
		CallableStatement callableStatement = null;
		//fine LP PG21XX04 Leak
		try	{
			//CallableStatement callableStatement = prepareCall(PYEH8SP_SEL);		
			//inizio LP PG21XX04 Leak
			//CallableStatement callableStatement = prepareCall(Routines.EH8_DODETAIL.routine());
			callableStatement = prepareCall(Routines.EH8_DODETAIL.routine());
			//fine LP PG21XX04 Leak
            callableStatement.setLong(1, new Long(dto.getProgressivoFlusso()));
            callableStatement.setString(2, dto.getCodiceUtente()!=null?dto.getCodiceUtente():"");
            callableStatement.setString(3, dto.getDataCreazioneFlusso()!=null?dto.getDataCreazioneFlusso():"");
            callableStatement.setString(4, dto.getTipoServizio()!=null?dto.getTipoServizio():"");
            callableStatement.setString(5, dto.getCodiceEnte()!=null?dto.getCodiceEnte():"");
            callableStatement.setString(6, dto.getTipoUfficio()!=null?dto.getTipoUfficio():"");
            callableStatement.setString(7, dto.getCodiceUfficio()!=null?dto.getCodiceUfficio():"");
            callableStatement.setString(8, dto.getImpostaServizio()!=null?dto.getImpostaServizio():"");
            callableStatement.setString(9, dto.getCodiceFiscale()!=null?dto.getCodiceFiscale():"");
            callableStatement.setString(10, dto.getCodiceTomb()!=null?dto.getCodiceTomb():"");
			if (callableStatement.execute()) {
				this.loadWebRowSets(callableStatement);
				dto.setListXml(getWebRowSetXml(RiversamentoDao.IDX_DOLIST_LISTA));                
			}
			/*
			if (callableStatement.execute()) 
			{
				ResultSet ris = callableStatement.getResultSet();
				ris.next();
				dto.setDettaglio(new AnagraficaDettaglioBean(ris));				
			}
			 */
		} catch (SQLException x) {
			throw new DaoException(x);
		} catch (IllegalArgumentException x) {
			throw new DaoException(x);
		} catch (HelperException x) {
			throw new DaoException(x);
		} finally {
			//inizio LP PG21XX04 Leak
			if (callableStatement != null) {
				try {
					callableStatement.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			//fine LP PG21XX04 Leak
		}
		return dto;
	}

	public EntrateDocumentiAnagrafica getDocumentiAnagrafica(EntrateDocumentiAnagrafica dto) throws DaoException {
		Paginazione paginazione = dto.getPaginazione();
		if (paginazione.getRowsPerPage() <= 0)
			throw new IllegalArgumentException(Messages.INVALID_PARAMETER.format("rowsPerPage"));
		if (paginazione.getPageNumber() <= 0)
			throw new IllegalArgumentException(Messages.INVALID_PARAMETER.format("pageNumber"));
		//CallableStatement callableStatement = null;
		//inizio LP PG21XX04 Leak
		CallableStatement callableStatement = null;
		//fine LP PG21XX04 Leak
		try	{
//			CallableStatement callableStatement = prepareCall(PYEH1SP_DOC_ANA_LST);		
			//inizio LP PG21XX04 Leak
			//CallableStatement callableStatement = prepareCall(Routines.EH1_DOC_ANA_DOLIST.routine());
			callableStatement = prepareCall(Routines.EH1_DOC_ANA_DOLIST.routine());
			//fine LP PG21XX04 Leak
            callableStatement.setLong(1, new Long(dto.getProgressivoFlusso()));
            callableStatement.setString(2, dto.getCodiceUtente()!=null?dto.getCodiceUtente():"");
            callableStatement.setString(3, dto.getDataCreazioneFlusso()!=null?dto.getDataCreazioneFlusso():"");
            callableStatement.setString(4, dto.getTipoServizio()!=null?dto.getTipoServizio():"");
            callableStatement.setString(5, dto.getCodiceEnte()!=null?dto.getCodiceEnte():"");
            callableStatement.setString(6, dto.getTipoUfficio()!=null?dto.getTipoUfficio():"");
            callableStatement.setString(7, dto.getCodiceUfficio()!=null?dto.getCodiceUfficio():"");
            callableStatement.setString(8, dto.getImpostaServizio()!=null?dto.getImpostaServizio():"");
            callableStatement.setString(9, dto.getCodiceFiscale()!=null?dto.getCodiceFiscale():"");
            callableStatement.setString(10, dto.getCodiceTomb()!=null?dto.getCodiceTomb():"");
			callableStatement.setString(11, paginazione.getOrder());
			callableStatement.setInt(12, paginazione.getRowsPerPage());
			callableStatement.setInt(13, paginazione.getPageNumber());
			/* we register row start */
			callableStatement.registerOutParameter(14, Types.INTEGER);
			/* we register row end */
			callableStatement.registerOutParameter(15, Types.INTEGER);
			/* we register total rows */
			callableStatement.registerOutParameter(16, Types.INTEGER);
			/* we register total pages */
			callableStatement.registerOutParameter(17, Types.SMALLINT);
			/* we execute procedure */
			if (callableStatement.execute()) {
				this.loadWebRowSets(callableStatement);
				/* we register page info */
				registerPageInfo(paginazione.getRowsPerPage(), paginazione.getPageNumber(), callableStatement.getInt(14), callableStatement.getInt(15), 
								 callableStatement.getInt(16), callableStatement.getInt(17));
				dto.setPageInfo(getPageInfo());
				dto.setListXml(getWebRowSetXml(EccedenzeDao.IDX_DOLIST_LISTA));
			}
		} catch (SQLException x) {
			throw new DaoException(x);
		} catch (IllegalArgumentException x) {
			throw new DaoException(x);
		} catch (HelperException x) {
			throw new DaoException(x);
		} finally {
			//inizio LP PG21XX04 Leak
			if (callableStatement != null) {
				try {
					callableStatement.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			//fine LP PG21XX04 Leak
		}
		return dto;
	}

// fine anagrafiche	

// documenti emissione
	public EntrateDettaglioEmissioneS getDettaglioEmissioneS(EntrateDettaglioEmissioneS dto) throws DaoException 
	{
//		CallableStatement callableStatement = null;
		//inizio LP PG21XX04 Leak
		CallableStatement callableStatement = null;
		//fine LP PG21XX04 Leak
		try	{
//			CallableStatement callableStatement = prepareCall(PYEH1SP_EMI_S_SEL);		
			//inizio LP PG21XX04 Leak
			//CallableStatement callableStatement = prepareCall(Routines.EH1_EMI_S_DODETAIL.routine());
			callableStatement = prepareCall(Routines.EH1_EMI_S_DODETAIL.routine());
			//fine LP PG21XX04 Leak
            callableStatement.setString(1, dto.getCodiceUtente()!=null?dto.getCodiceUtente():"");
            callableStatement.setString(2, dto.getCodiceEnte()!=null?dto.getCodiceEnte():"");
            callableStatement.setString(3, dto.getTipoUfficio()!=null?dto.getTipoUfficio():"");
            callableStatement.setString(4, dto.getCodiceUfficio()!=null?dto.getCodiceUfficio():"");
            callableStatement.setString(5, dto.getImpostaServizio()!=null?dto.getImpostaServizio():"");
            callableStatement.setString(6, dto.getAnnoEmissione()!=null?dto.getAnnoEmissione():"");
            callableStatement.setString(7, dto.getNumeroEmissione()!=null?dto.getNumeroEmissione():"");
            callableStatement.setString(8, dto.getCodiceTomb()!=null?dto.getCodiceTomb():"");
			if (callableStatement.execute()) {
				this.loadWebRowSets(callableStatement);
				dto.setListXml(getWebRowSetXml(RiversamentoDao.IDX_DOLIST_LISTA));                
			}
			/*
			if (callableStatement.execute()) 
			{
				ResultSet ris = callableStatement.getResultSet();
				ris.next();
				dto.setDettaglio(new AnagraficaDettaglioBean(ris));				
			}
			 */
		} catch (SQLException x) {
			throw new DaoException(x);
		} catch (IllegalArgumentException x) {
			throw new DaoException(x);
		} catch (HelperException x) {
			throw new DaoException(x);
		} finally {
			//inizio LP PG21XX04 Leak
			if (callableStatement != null) {
				try {
					callableStatement.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			//fine LP PG21XX04 Leak
		}
		return dto;
	}

	public EntrateDocumentiEmissione getDocumentiEmissione(EntrateDocumentiEmissione dto) throws DaoException {
		Paginazione paginazione = dto.getPaginazione();
		if (paginazione.getRowsPerPage() <= 0)
			throw new IllegalArgumentException(Messages.INVALID_PARAMETER.format("rowsPerPage"));
		if (paginazione.getPageNumber() <= 0)
			throw new IllegalArgumentException(Messages.INVALID_PARAMETER.format("pageNumber"));
		//CallableStatement callableStatement = null;
		//inizio LP PG21XX04 Leak
		CallableStatement callableStatement = null;
		//fine LP PG21XX04 Leak
		try	{
//			CallableStatement callableStatement = prepareCall(PYEH1SP_DOC_EMI_LST);		
			//inizio LP PG21XX04 Leak
			//CallableStatement callableStatement = prepareCall(Routines.EH1_DOC_EMI_LST.routine());
			callableStatement = prepareCall(Routines.EH1_DOC_EMI_LST.routine());
			//fine LP PG21XX04 Leak
            callableStatement.setString(1, dto.getCodiceUtente()!=null?dto.getCodiceUtente():"");
            callableStatement.setString(2, dto.getCodiceEnte()!=null?dto.getCodiceEnte():"");
            callableStatement.setString(3, dto.getTipoUfficio()!=null?dto.getTipoUfficio():"");
            callableStatement.setString(4, dto.getCodiceUfficio()!=null?dto.getCodiceUfficio():"");
            callableStatement.setString(5, dto.getImpostaServizio()!=null?dto.getImpostaServizio():"");
            callableStatement.setString(6, dto.getAnnoEmissione()!=null?dto.getAnnoEmissione():"");
            callableStatement.setString(7, dto.getNumeroEmissione()!=null?dto.getNumeroEmissione():"");
            callableStatement.setString(8, dto.getCodiceTomb()!=null?dto.getCodiceTomb():"");
			callableStatement.setString(9, paginazione.getOrder());
			callableStatement.setInt(10, paginazione.getRowsPerPage());
			callableStatement.setInt(11, paginazione.getPageNumber());
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
				registerPageInfo(paginazione.getRowsPerPage(), paginazione.getPageNumber(), callableStatement.getInt(12), callableStatement.getInt(13), 
								 callableStatement.getInt(14), callableStatement.getInt(15));
				dto.setPageInfo(getPageInfo());
				dto.setListXml(getWebRowSetXml(EccedenzeDao.IDX_DOLIST_LISTA));
			}
		} catch (SQLException x) {
			throw new DaoException(x);
		} catch (IllegalArgumentException x) {
			throw new DaoException(x);
		} catch (HelperException x) {
			throw new DaoException(x);
		} finally {
			//inizio LP PG21XX04 Leak
			if (callableStatement != null) {
				try {
					callableStatement.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			//fine LP PG21XX04 Leak
		}
		return dto;
	}
	
// fine documenti emissione

	public EntrateTributiPage getListaTributi(EntrateTributiPage dto, String ordine, int rowsPerPage, int pageNumber) throws DaoException 
	{
		//CallableStatement callableStatement = null;
		//inizio LP PG21XX04 Leak
		CallableStatement callableStatement = null;
		//fine LP PG21XX04 Leak
		try	{
//			CallableStatement callableStatement = prepareCall(PYEH7SP_LST);
			//inizio LP PG21XX04 Leak
			//CallableStatement callableStatement = prepareCall(Routines.EH7_DOLIST.routine());
			callableStatement = prepareCall(Routines.EH7_DOLIST.routine());
			//fine LP PG21XX04 Leak
			callableStatement.setInt(1, pageNumber);
			callableStatement.setInt(2, rowsPerPage);
			callableStatement.setString(3, ordine);
			callableStatement.setString(4, dto.getCodiceSocieta());
			callableStatement.setString(5, dto.getCodiceUtente());
			callableStatement.setString(6, dto.getCodiceEnte());
			callableStatement.setString(7, dto.getImpostaServizio());
			callableStatement.setString(8, dto.getTipoUfficio());
			callableStatement.setString(9, dto.getCodiceUfficio());
			callableStatement.setString(10, dto.getNumeroDocumento());
			/* we register row start */
			callableStatement.registerOutParameter(11, Types.INTEGER);
			/* we register row end */
			callableStatement.registerOutParameter(12, Types.INTEGER);
			/* we register total rows */
			callableStatement.registerOutParameter(13, Types.INTEGER);
			/* we register total pages */
			callableStatement.registerOutParameter(14, Types.SMALLINT);
			/* we execute procedure */
			if (callableStatement.execute()) {
				this.loadWebRowSets(callableStatement);
				/* we register page info */
				registerPageInfo(rowsPerPage, pageNumber, callableStatement.getInt(11), callableStatement.getInt(12), 
								 callableStatement.getInt(13), callableStatement.getInt(14));
				dto.setPageInfo(getPageInfo());
				dto.setListXml(getWebRowSetXml(EntrateBancaDatiDao.IDX_DOLIST_LISTA));
			}
		} catch (SQLException x) {
			throw new DaoException(x);
		} catch (IllegalArgumentException x) {
			throw new DaoException(x);
		} catch (HelperException x) {
			throw new DaoException(x);
		} finally {
			//inizio LP PG21XX04 Leak
			if (callableStatement != null) {
				try {
					callableStatement.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			//fine LP PG21XX04 Leak
		}
		return dto;
	}

	public EntrateNotePage getListaNote(EntrateNotePage dto, String ordine, int rowsPerPage, int pageNumber) throws DaoException 
	{
		//CallableStatement callableStatement = null;

		/*IN I_EH7_CEH7NDOC VARCHAR(20),
		IN I_EH7_CUTECUTE CHAR(5),
		IN I_EH7_PEH7FLUS VARCHAR(20),
		IN I_EH7_TEH7SERV CHAR(2),
		IN I_EH7_CANECENT CHAR(5),
		IN I_EH7_TANETUFF CHAR(5),
		IN I_EH7_CANECUFF CHAR(6),
		IN I_EH7_CISECISE CHAR(4),
		IN I_EH7_CEH7TOMB CHAR(1), 
		IN I_EH7_CEH7TRIB CHAR(4),
		IN I_EH7_CEH7ATRI CHAR(4),
		IN I_EH7_PEH7PROG VARCHAR(10),
		OUT O_ROWINI INTEGER,
		OUT O_ROWEND INTEGER,
		OUT O_TOTROWS INTEGER,
		OUT O_TOTPAGES SMALLINT 
*/
		//inizio LP PG21XX04 Leak
		CallableStatement callableStatement = null;
		//fine LP PG21XX04 Leak
		try	{
			//CallableStatement callableStatement = prepareCall(PYEH7SP_LST_NOTE);
			//inizio LP PG21XX04 Leak
			//CallableStatement callableStatement = prepareCall(Routines.EH7_NOTE_DOLIST.routine());
			callableStatement = prepareCall(Routines.EH7_NOTE_DOLIST.routine());
			//fine LP PG21XX04 Leak
			callableStatement.setInt(1, pageNumber);
			callableStatement.setInt(2, rowsPerPage);
			callableStatement.setString(3, ordine);
			callableStatement.setString(4, dto.getNumeroDocumento());
			callableStatement.setString(5, dto.getCodiceUtente());
			callableStatement.setString(6, dto.getProgrFlusso());
			callableStatement.setString(7, dto.getProgrServ());
			callableStatement.setString(8, dto.getCodiceEnte());
			callableStatement.setString(9, dto.getTipoUfficio());
			callableStatement.setString(10, dto.getCodiceUfficio());
			callableStatement.setString(11, dto.getImpostaServizio());
			callableStatement.setString(12, dto.getCodTomb());
			callableStatement.setString(13, dto.getCodTrib());
			callableStatement.setString(14, dto.getAnnoTrib());			
			callableStatement.setString(15, dto.getProgrTrib());			
			/* we register row start */
			callableStatement.registerOutParameter(16, Types.INTEGER);
			/* we register row end */
			callableStatement.registerOutParameter(17, Types.INTEGER);
			/* we register total rows */
			callableStatement.registerOutParameter(18, Types.INTEGER);
			/* we register total pages */
			callableStatement.registerOutParameter(19, Types.SMALLINT);
			/* we execute procedure */
			if (callableStatement.execute()) {
				this.loadWebRowSets(callableStatement);
				/* we register page info */
				registerPageInfo(rowsPerPage, pageNumber, callableStatement.getInt(16), callableStatement.getInt(17), 
								 callableStatement.getInt(18), callableStatement.getInt(19));
				dto.setPageInfo(getPageInfo());
				dto.setListXml(getWebRowSetXml(EntrateBancaDatiDao.IDX_DOLIST_LISTA));
			}
		} catch (SQLException x) {
			throw new DaoException(x);
		} catch (IllegalArgumentException x) {
			throw new DaoException(x);
		} catch (HelperException x) {
			throw new DaoException(x);
		} finally {
			//inizio LP PG21XX04 Leak
			if (callableStatement != null) {
				try {
					callableStatement.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			//fine LP PG21XX04 Leak
		}
		return dto;
	}

	public EntrateTributoDettaglio getDettaglioTributo(EntrateTributoDettaglio dto) throws DaoException 
	{
		//CallableStatement callableStatement = null;
		/*	IN I_EH7_CEH7NDOC VARCHAR(20),
	IN I_EH7_CUTECUTE CHAR(5),
	IN I_EH7_PEH7FLUS BIGINT,
	IN I_EH7_TEH7SERV CHAR(2),
	IN I_EH7_CANECENT CHAR(5),
	IN I_EH7_TANETUFF CHAR(5),
	IN I_EH7_CANECUFF CHAR(6),
	IN I_EH7_CISECISE CHAR(4),
	IN I_EH7_CEH7TOMB CHAR(1), 
	IN I_EH7_CEH7TRIB CHAR(4),
	IN I_EH7_CEH7ATRI CHAR(4),
	IN I_EH7_PEH7PROG INT
*/
		//inizio LP PG21XX04 Leak
		CallableStatement callableStatement = null;
		//fine LP PG21XX04 Leak
		try	{
//			CallableStatement callableStatement = prepareCall(PYEH7SP_SEL);
			//inizio LP PG21XX04 Leak
			//CallableStatement callableStatement = prepareCall(Routines.EH7_DODETAIL.routine());
			callableStatement = prepareCall(Routines.EH7_DODETAIL.routine());
			//fine LP PG21XX04 Leak
			callableStatement.setString(1, dto.getNumeroDocumento()!=null?dto.getNumeroDocumento():"");
			callableStatement.setString(2, dto.getCodiceUtente()!=null?dto.getCodiceUtente():"");
			callableStatement.setLong(3, dto.getProgrFlusso()!=null?Long.parseLong(dto.getProgrFlusso()):0);
			callableStatement.setString(4, dto.getProgrServ()!=null?dto.getProgrServ():"");
			callableStatement.setString(5, dto.getCodiceEnte()!=null?dto.getCodiceEnte():"");
			callableStatement.setString(6, dto.getTipoUfficio()!=null?dto.getTipoUfficio():"");
			callableStatement.setString(7, dto.getCodiceUfficio()!=null?dto.getCodiceUfficio():"");
			callableStatement.setString(8, dto.getImpostaServizio()!=null?dto.getImpostaServizio():"");
			callableStatement.setString(9, dto.getCodTomb()!=null?dto.getCodTomb():"");
			callableStatement.setString(10, dto.getCodTrib()!=null?dto.getCodTrib():"");
			callableStatement.setString(11, dto.getAnnoTrib()!=null?dto.getAnnoTrib():"");			
			callableStatement.setString(12, dto.getProgrTrib()!=null?dto.getProgrTrib():"");			
			/* we execute procedure */
			if (callableStatement.execute()) {
				this.loadWebRowSets(callableStatement);
				dto.setListXml(getWebRowSetXml(RiversamentoDao.IDX_DOLIST_LISTA)); 
			}
		} catch (SQLException x) {
			throw new DaoException(x);
		} catch (IllegalArgumentException x) {
			throw new DaoException(x);
		} catch (HelperException x) {
			throw new DaoException(x);
		} finally {
			//inizio LP PG21XX04 Leak
			if (callableStatement != null) {
				try {
					callableStatement.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			//fine LP PG21XX04 Leak
		}
		return dto;
	}

	public EntrateScadenzePage getListaScadenze(EntrateScadenzePage dto, String ordine, int rowsPerPage, int pageNumber) throws DaoException 
	{
		//CallableStatement callableStatement = null;
		//inizio LP PG21XX04 Leak
		CallableStatement callableStatement = null;
		//fine LP PG21XX04 Leak
		try	{
//			CallableStatement callableStatement = prepareCall(PYEH2SP_LST);
			//inizio LP PG21XX04 Leak
			//CallableStatement callableStatement = prepareCall(Routines.EH2_DOLIST.routine());
			callableStatement = prepareCall(Routines.EH2_DOLIST.routine());
			//fine LP PG21XX04 Leak
			callableStatement.setInt(1, pageNumber);
			callableStatement.setInt(2, rowsPerPage);
			callableStatement.setString(3, ordine);
			callableStatement.setString(4, dto.getCodiceSocieta());
			callableStatement.setString(5, dto.getCodiceUtente());
			callableStatement.setString(6, dto.getCodiceEnte());
			callableStatement.setString(7, dto.getImpostaServizio());
			callableStatement.setString(8, dto.getTipoUfficio());
			callableStatement.setString(9, dto.getCodiceUfficio());
			callableStatement.setString(10, dto.getNumeroDocumento());
			/* we register row start */
			callableStatement.registerOutParameter(11, Types.INTEGER);
			/* we register row end */
			callableStatement.registerOutParameter(12, Types.INTEGER);
			/* we register total rows */
			callableStatement.registerOutParameter(13, Types.INTEGER);
			/* we register total pages */
			callableStatement.registerOutParameter(14, Types.SMALLINT);
			/* we execute procedure */
			if (callableStatement.execute()) {
				this.loadWebRowSets(callableStatement);
				/* we register page info */
				registerPageInfo(rowsPerPage, pageNumber, callableStatement.getInt(11), callableStatement.getInt(12), 
								 callableStatement.getInt(13), callableStatement.getInt(14));
				dto.setPageInfo(getPageInfo());
				dto.setListXml(getWebRowSetXml(EntrateBancaDatiDao.IDX_DOLIST_LISTA));
			}
		} catch (SQLException x) {
			throw new DaoException(x);
		} catch (IllegalArgumentException x) {
			throw new DaoException(x);
		} catch (HelperException x) {
			throw new DaoException(x);
		} finally {
			//inizio LP PG21XX04 Leak
			if (callableStatement != null) {
				try {
					callableStatement.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			//fine LP PG21XX04 Leak
		}
		return dto;
	}

	public EntratePagamentiDocumentiPage getListaPagamentiDocumenti(EntratePagamentiDocumentiPage dto, String ordine, int rowsPerPage, int pageNumber) throws DaoException 
	{
		//CallableStatement callableStatement = null;
		//inizio LP PG21XX04 Leak
		CallableStatement callableStatement = null;
		//fine LP PG21XX04 Leak
		try	{
//			CallableStatement callableStatement = prepareCall(PYEH3SP_LST_DOC);
			//inizio LP PG21XX04 Leak
			//CallableStatement callableStatement = prepareCall(Routines.EH3_DOC_DOLIST.routine());
			callableStatement = prepareCall(Routines.EH3_DOC_DOLIST.routine());
			//fine LP PG21XX04 Leak
			callableStatement.setInt(1, pageNumber);
			callableStatement.setInt(2, rowsPerPage);
			callableStatement.setString(3, ordine);
			callableStatement.setString(4, dto.getCodiceSocieta());
			callableStatement.setString(5, dto.getCodiceUtente());
			callableStatement.setString(6, dto.getCodiceEnte());
			callableStatement.setString(7, dto.getImpostaServizio());
			callableStatement.setString(8, dto.getTipoUfficio());
			callableStatement.setString(9, dto.getCodiceUfficio());
			callableStatement.setString(10, dto.getNumeroDocumento());
			/* we register row start */
			callableStatement.registerOutParameter(11, Types.INTEGER);
			/* we register row end */
			callableStatement.registerOutParameter(12, Types.INTEGER);
			/* we register total rows */
			callableStatement.registerOutParameter(13, Types.INTEGER);
			/* we register total pages */
			callableStatement.registerOutParameter(14, Types.SMALLINT);
			/* we execute procedure */
			if (callableStatement.execute()) {
				this.loadWebRowSets(callableStatement);
				/* we register page info */
				registerPageInfo(rowsPerPage, pageNumber, callableStatement.getInt(11), callableStatement.getInt(12), 
								 callableStatement.getInt(13), callableStatement.getInt(14));
				dto.setPageInfo(getPageInfo());
				dto.setListXml(getWebRowSetXml(EntrateBancaDatiDao.IDX_DOLIST_LISTA));
			}
		} catch (SQLException x) {
			throw new DaoException(x);
		} catch (IllegalArgumentException x) {
			throw new DaoException(x);
		} catch (HelperException x) {
			throw new DaoException(x);
		} finally {
			//inizio LP PG21XX04 Leak
			if (callableStatement != null) {
				try {
					callableStatement.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			//fine LP PG21XX04 Leak
		}
		return dto;
	}

	public EntrateDocumentiPage getListaDocumenti(EntrateDocumentiPage dto, String ordine, int rowsPerPage, int pageNumber) throws DaoException 
	{
		//CallableStatement callableStatement = null;
		//inizio LP PG21XX04 Leak
		CallableStatement callableStatement = null;
		//fine LP PG21XX04 Leak
		try	{
			/*IN I_PAGENO SMALLINT,
			IN I_ROWSPERPAGE SMALLINT,
			IN I_ORDER VARCHAR(64),
			
			IN I_EH1_CSOCCSOC CHAR(5),
			IN I_EH1_CUTECUTE CHAR(5),
			IN I_ANE_KANEKENT CHAR(10),
			IN I_EH1_CISECISE CHAR(4),
			IN I_EH1_CEH1CFIS VARCHAR(16),
			IN I_EH1_CEH1ADOC CHAR(4),
			IN I_EH1_CEH1EMIS CHAR(10),
			IN I_EH1_TANETUFF CHAR(1),
			IN I_EH1_CANECUFF CHAR(6),
			IN I_EH1_CEH1NDOC VARCHAR(20),
			IN I_EH1_CTSECTSE CHAR(3),
			IN I_EH7_STATO_DOC CHAR(1),
			IN I_EH1_TEH1SOSP CHAR(1),
			IN I_EH1_FEH1PROC CHAR(1),
			OUT O_SELECT VARCHAR(2000),
			OUT O_ROWINI INTEGER,
			OUT O_ROWEND INTEGER,
			OUT O_TOTROWS INTEGER,
			OUT O_TOTPAGES SMALLINT*/
			//callableStatement = prepareCall(PYECTSP_LST);		
			//inizio LP PG21XX04 Leak
			//CallableStatement callableStatement = prepareCall(Routines.PYEH1SP_LST.routine());
			callableStatement = prepareCall(Routines.PYEH1SP_LST.routine());
			//fine LP PG21XX04 Leak
			callableStatement.setInt(1, pageNumber);
			callableStatement.setInt(2, rowsPerPage);
			callableStatement.setString(3, ordine);
			callableStatement.setString(4, dto.getCodiceSocieta());
			callableStatement.setString(5, dto.getCodiceUtente());
			callableStatement.setString(6, dto.getCodiceEnte());
			callableStatement.setString(7, dto.getImpostaServizio());
			callableStatement.setString(8, dto.getCodiceFiscale());
			callableStatement.setString(9, dto.getAnnoEmissione());
			callableStatement.setString(10, dto.getNumeroEmissione());
			callableStatement.setString(11, dto.getTipoUfficio());
			callableStatement.setString(12, dto.getCodiceUfficio());
			callableStatement.setString(13, dto.getNumeroDocumento());
			callableStatement.setString(14, dto.getTipologiaServizio());
			callableStatement.setString(15, dto.getStatoDocumento());
			callableStatement.setString(16, dto.getStatoSospensione());
			callableStatement.setString(17, dto.getStatoProcedure());
			callableStatement.setString(18, dto.getNumeroBollettino());
			callableStatement.setString(19, dto.getNumeroIUV());
			callableStatement.registerOutParameter(20,Types.VARCHAR);
			/* we register row start */
			callableStatement.registerOutParameter(21, Types.INTEGER);
			/* we register row end */
			callableStatement.registerOutParameter(22, Types.INTEGER);
			/* we register total rows */
			callableStatement.registerOutParameter(23, Types.INTEGER);
			/* we register total pages */
			callableStatement.registerOutParameter(24, Types.SMALLINT);
			/* we execute procedure */
			if (callableStatement.execute()) {
				this.loadWebRowSets(callableStatement);
				/* we register page info */
				registerPageInfo(rowsPerPage, pageNumber, callableStatement.getInt(21), callableStatement.getInt(22), 
								 callableStatement.getInt(23), callableStatement.getInt(24));
				dto.setPageInfo(getPageInfo());
				dto.setListXml(getWebRowSetXml(EntrateBancaDatiDao.IDX_DOLIST_LISTA));
			}
		} catch (SQLException x) {
			throw new DaoException(x);
		} catch (IllegalArgumentException x) {
			throw new DaoException(x);
		} catch (HelperException x) {
			throw new DaoException(x);
		} finally {
			//inizio LP PG21XX04 Leak
			if (callableStatement != null) {
				try {
					callableStatement.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			//fine LP PG21XX04 Leak
		}
		return dto;
	}

	public EntrateDocumentiPage getDataUltimoAggiornamento(EntrateDocumentiPage dto) throws DaoException 
	{
		try	{
			dto.setDataUltimoAgg(getDataUltimoAggiornamento(dto.getCodiceSocieta(),dto.getCodiceUtente(),dto.getCodiceEnte()));
		} catch (IllegalArgumentException x) {
			throw new DaoException(x);
		} finally {
		}
		return dto;
	}

	public java.sql.Date getDataUltimoAggiornamento(String codiceSocieta,String codiceUtente,String codiceEnte) throws DaoException 
	{
		//inizio LP PG21XX04 Leak
		CallableStatement callableStatement = null;
		ResultSet data = null;
		//fine LP PG21XX04 Leak
		try	{
//			CallableStatement callableStatement = prepareCall(PYELGSP_SEL_DATE);
			//inizio LP PG21XX04 Leak
			//CallableStatement callableStatement = prepareCall(Routines.ELG_SEL_DATE.routine());
			callableStatement = prepareCall(Routines.ELG_SEL_DATE.routine());
			//fine LP PG21XX04 Leak
			//callableStatement = prepareCall(PYECTSP_LST);		
			callableStatement.setString(1, codiceSocieta);
			callableStatement.setString(2, codiceUtente);
			callableStatement.setString(3, codiceEnte);
			/* we execute procedure */
			if (callableStatement.execute()) {				
				//inizio LP PG21XX04 Leak
				//callableStatement.getResultSet().next();
				//return callableStatement.getResultSet().getDate(1);
				data = callableStatement.getResultSet();
				data.next();
				return data.getDate(1);
				//fine LP PG21XX04 Leak
			}
		} catch (SQLException x) {
			throw new DaoException(x);
		} catch (IllegalArgumentException x) {
			throw new DaoException(x);
		} catch (HelperException x) {
			throw new DaoException(x);
		} finally {
			//inizio LP PG21XX04 Leak
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

	public EntrateDocumentiPage getStatisticheDocumenti(EntrateDocumentiPage dto) throws DaoException 
	{
		//inizio LP PG21XX04 Leak
		CallableStatement callableStatement = null;
		//fine LP PG21XX04 Leak
		try	{
//			CallableStatement callableStatement = prepareCall(PYEH1SP_STAT);
			//inizio LP PG21XX04 Leak
			//CallableStatement callableStatement = prepareCall(Routines.EH1_STAT.routine());
			callableStatement = prepareCall(Routines.EH1_STAT.routine());
			//fine LP PG21XX04 Leak
			//callableStatement = prepareCall(PYECTSP_LST);		
			callableStatement.setString(1, dto.getCodiceSocieta());
			callableStatement.setString(2, dto.getCodiceUtente());
			callableStatement.setString(3, dto.getCodiceEnte());
			callableStatement.setString(4, dto.getImpostaServizio());
			callableStatement.setString(5, dto.getCodiceFiscale());
			callableStatement.setString(6, dto.getAnnoEmissione());
			callableStatement.setString(7, dto.getNumeroEmissione());
			callableStatement.setString(8, dto.getTipoUfficio());
			callableStatement.setString(9, dto.getCodiceUfficio());
			callableStatement.setString(10, dto.getNumeroDocumento());
			callableStatement.setString(11, dto.getTipologiaServizio());
			callableStatement.setString(12, dto.getStatoDocumento());
			callableStatement.setString(13, dto.getStatoSospensione());
			callableStatement.setString(14, dto.getStatoProcedure());
			callableStatement.setString(15, dto.getProgrFlusso());
			callableStatement.setString(16, dto.getChiaveTipoServ());
			callableStatement.setString(17, dto.getChiaveCodiceEnte());
//			callableStatement.setString(18, dto.getCodiceTomb());
			callableStatement.registerOutParameter(18,Types.DECIMAL);
			callableStatement.registerOutParameter(19,Types.DECIMAL);
			callableStatement.registerOutParameter(20,Types.DECIMAL);
			callableStatement.registerOutParameter(21,Types.DECIMAL);
			callableStatement.registerOutParameter(22,Types.DECIMAL);
			callableStatement.registerOutParameter(23,Types.DECIMAL);
			callableStatement.registerOutParameter(24,Types.DECIMAL);
			callableStatement.registerOutParameter(25,Types.DECIMAL);
			callableStatement.execute();
			dto.setTotcarico(callableStatement.getBigDecimal(18)!=null?callableStatement.getBigDecimal(18):new BigDecimal(0));
			dto.setTotrendicontato(callableStatement.getBigDecimal(19)!=null?callableStatement.getBigDecimal(19):new BigDecimal(0));
			dto.setTotdimcarico(callableStatement.getBigDecimal(20)!=null?callableStatement.getBigDecimal(20):new BigDecimal(0));
			dto.setTotriscosso(callableStatement.getBigDecimal(21)!=null?callableStatement.getBigDecimal(21):new BigDecimal(0));
			dto.setTotrimborso(callableStatement.getBigDecimal(22)!=null?callableStatement.getBigDecimal(22):new BigDecimal(0));
			dto.setTotfincarico(callableStatement.getBigDecimal(23)!=null?callableStatement.getBigDecimal(23):new BigDecimal(0));
			dto.setTotresiduo(callableStatement.getBigDecimal(24)!=null?callableStatement.getBigDecimal(24):new BigDecimal(0));
			dto.setTotResScaduto(callableStatement.getBigDecimal(25)!=null?callableStatement.getBigDecimal(25):new BigDecimal(0));
		} catch (SQLException x) {
			throw new DaoException(x);
		} catch (IllegalArgumentException x) {
			throw new DaoException(x);
		} catch (HelperException x) {
			throw new DaoException(x);
		} finally {
			//inizio LP PG21XX04 Leak
			if (callableStatement != null) {
				try {
					callableStatement.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			//fine LP PG21XX04 Leak
		}
		return dto;
	}

	public String getListaUffImpositore(String  codiceSocieta, String codiceEnte, String codiceUtente) throws DaoException 
	{
		//inizio LP PG21XX04 Leak
		CallableStatement callableStatement = null;
		//fine LP PG21XX04 Leak
		try	{
			//CallableStatement callableStatement = prepareCall(PYANESP_LST_DDL_UFF);
			//inizio LP PG21XX04 Leak
			//CallableStatement callableStatement = prepareCall(Routines.ANE_DDL_UFF.routine());
			callableStatement = prepareCall(Routines.ANE_DDL_UFF.routine());
			//fine LP PG21XX04 Leak
			//callableStatement = prepareCall(PYECTSP_LST);		
			callableStatement.setString(1, codiceSocieta);
			callableStatement.setString(2, codiceEnte);
			callableStatement.setString(3, codiceUtente);
			if (callableStatement.execute()) {
				this.loadWebRowSets(callableStatement);
				return getWebRowSetXml(EntrateBancaDatiDao.IDX_DOLIST_LISTA);
			}
		} catch (SQLException x) {
			throw new DaoException(x);
		} catch (IllegalArgumentException x) {
			throw new DaoException(x);
		} catch (HelperException x) {
			throw new DaoException(x);
		} finally {
			//inizio LP PG21XX04 Leak
			if (callableStatement != null) {
				try {
					callableStatement.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			//fine LP PG21XX04 Leak
		}
		return "";
	}

	public String getListaImpostaServizio(String codiceSocieta, String codiceUtente, String codiceEnte, String tipoServizio) throws DaoException 
	{
		//inizio LP PG21XX04 Leak
		CallableStatement callableStatement = null;
		//fine LP PG21XX04 Leak
		try	{
			//CallableStatement callableStatement = prepareCall(Routines.ISE_DDL.routine());
			//inizio LP PG21XX04 Leak
			//CallableStatement callableStatement = prepareCall(Routines.EH6_ISE_DDL.routine());
			callableStatement = prepareCall(Routines.EH6_ISE_DDL.routine());
			//fine LP PG21XX04 Leak
			callableStatement.setString(1, codiceSocieta);
			callableStatement.setString(2, codiceUtente);
			callableStatement.setString(3, codiceEnte);
			callableStatement.setString(4, tipoServizio);
			if (callableStatement.execute()) {
				this.loadWebRowSets(callableStatement);
				return getWebRowSetXml(EntrateBancaDatiDao.IDX_DOLIST_LISTA);
			}
		} catch (SQLException x) {
			throw new DaoException(x);
		} catch (IllegalArgumentException x) {
			throw new DaoException(x);
		} catch (HelperException x) {
			throw new DaoException(x);
		} finally {
			//inizio LP PG21XX04 Leak
			if (callableStatement != null) {
				try {
					callableStatement.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			//fine LP PG21XX04 Leak
		}
		return "";
	}

	public EntrateDocumentoDettaglio getDettaglioDocumento(EntrateDocumentiPage dtoIn) throws DaoException 
	{
		//inizio LP PG21XX04 Leak
		CallableStatement callableStatement = null;
	    ResultSet rs = null;
		//fine LP PG21XX04 Leak
		EntrateDocumentoDettaglio dto = new EntrateDocumentoDettaglio();
		try	{
			//dto.setDocumentiTot(getStatisticheDocumenti(dtoIn));
//			CallableStatement callableStatement = prepareCall(PYEH1SP_SEL);
			//inizio LP PG21XX04 Leak
			//CallableStatement callableStatement = prepareCall(Routines.EH1_DODETAIL.routine());
			callableStatement = prepareCall(Routines.EH1_DODETAIL.routine());
			//fine LP PG21XX04 Leak
			//callableStatement = prepareCall(PYECTSP_LST);		
			callableStatement.setString(1, dtoIn.getNumeroDocumento()!=null?dtoIn.getNumeroDocumento():"");
			callableStatement.setString(2, dtoIn.getCodiceUtente()!=null?dtoIn.getCodiceUtente():"");
			callableStatement.setLong(3, dtoIn.getProgrFlusso() == null || dtoIn.getProgrFlusso().equals("") ? 0:new Long(dtoIn.getProgrFlusso()));
			callableStatement.setString(4, dtoIn.getChiaveTipoServ()!=null?dtoIn.getChiaveTipoServ():"");
			callableStatement.setString(5, dtoIn.getChiaveCodiceEnte()!=null?dtoIn.getChiaveCodiceEnte():"");
			callableStatement.setString(6, dtoIn.getTipoUfficio()!=null?dtoIn.getTipoUfficio():"");
			callableStatement.setString(7, dtoIn.getCodiceUfficio()!=null?dtoIn.getCodiceUfficio():"");
			callableStatement.setString(8, dtoIn.getImpostaServizio()!=null?dtoIn.getImpostaServizio():"");
			callableStatement.setString(9, dtoIn.getCodiceTomb()!=null?dtoIn.getCodiceTomb():"");
			callableStatement.registerOutParameter(10,Types.DECIMAL);
			if(callableStatement.execute()){
				//this.loadWebRowSets(callableStatement);
				//dto.setListXml(getWebRowSetXml(EntrateBancaDatiDao.IDX_DOLIST_LISTA));
				//inizio LP PG21XX04 Leak
				//ResultSet rs = callableStatement.getResultSet();
				rs = callableStatement.getResultSet();
				//fine LP PG21XX04 Leak
				if(rs.next()){
					dto.setImpostaServizio(rs.getString(1));
			    	dto.setUfficioImpositore(rs.getString(2));
			    	dto.setAnnoEmissione(rs.getString(3));
			    	dto.setNumeroEmissione(rs.getString(4));
			    	dto.setTipologiaServizio(rs.getString(5));
			    	dto.setDocRendicontato(rs.getString(6));
			    	dto.setCodiceFiscale(rs.getString(7));
			    	dto.setNumeroDocumento(rs.getString(8));
			    	dto.setNumeroRate(rs.getString(13));
	        		dto.setScadenzaPrimaRata(rs.getDate(14));
	        		dto.setDataNotifica(rs.getDate(10));
		        	dto.setSospensione(rs.getString(9));
		        	dto.setProcEsecutive(rs.getString(11));
			    	dto.setNumeroDocumentoColl(rs.getString(12));
					dto.setIntMora(rs.getBigDecimal(15));
					dto.setTotEccedenza(rs.getBigDecimal(16));
					dto.setDescSocieta(rs.getString(17));
					dto.setDescUtente(rs.getString(18));
					dto.setDescEnte(rs.getString(19));
					dto.setNumeroBollettino(rs.getString(28));
					dto.setNumeroIUV(rs.getString(29));
					dtoIn.setTotcarico(rs.getBigDecimal(20)== null?new java.math.BigDecimal(0):rs.getBigDecimal(20));
					dtoIn.setTotriscosso(rs.getBigDecimal(21)== null?new java.math.BigDecimal(0):rs.getBigDecimal(21));
					dtoIn.setTotdimcarico(rs.getBigDecimal(22)== null?new java.math.BigDecimal(0):rs.getBigDecimal(22));
					dtoIn.setTotrimborso(rs.getBigDecimal(23)== null?new java.math.BigDecimal(0):rs.getBigDecimal(23));
					dtoIn.setTotResScaduto(callableStatement.getBigDecimal(10));
					dtoIn.setCodiceSocieta(rs.getString(24));
					dtoIn.setCodiceUtente(rs.getString(25));
					dtoIn.setCodiceEnte(rs.getString(26));
					dtoIn.setTipoServizio(rs.getString(27));
					dto.setDocumentiTot(dtoIn);
				}					
			}
		} catch (SQLException x) {
			throw new DaoException(x);
		} catch (IllegalArgumentException x) {
			throw new DaoException(x);
		} catch (HelperException x) {
			throw new DaoException(x);
		} finally {
			//inizio LP PG21XX04 Leak
			if (rs != null) {
				try {
					rs.close();
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
		return dto;
	}

	public Documento getDettaglioDocumento2(String societa,
                                          String numeroDocumento,
                                          String codiceUtente,
                                          String progrFlusso,
                                          String chiaveTipoServ,
                                          String chiaveCodUtenteEnte,
                                          String tipoUfficio,
                                          String codiceUfficio,
                                          String impostaServizio,
                                          String codiceTomb) throws DaoException 
	{
	//inizio LP 20240921 - PGNTECCSV-10
		return getDettaglioDocumento2Batch(true, true,
				societa, numeroDocumento, codiceUtente, progrFlusso,
				chiaveTipoServ, chiaveCodUtenteEnte, tipoUfficio, codiceUfficio,
				impostaServizio, codiceTomb);
	}

	public Documento getDettaglioDocumento2Batch(boolean bFlagUpodateAutocommit, boolean bCloseStat,
		String societa,
        String numeroDocumento,
        String codiceUtente,
        String progrFlusso,
        String chiaveTipoServ,
        String chiaveCodUtenteEnte,
        String tipoUfficio,
        String codiceUfficio,
        String impostaServizio,
        String codiceTomb) throws DaoException
	//fine LP 20240921 - PGNTECCSV-10
	{
	//inizio LP PG21XX04 Leak
	CallableStatement callableStatement = null;
    ResultSet rs = null;
    ResultSet rs2 = null; 
	//fine LP PG21XX04 Leak
    Documento dto = new Documento();
    try {
    	//dto.setDocumentiTot(getStatisticheDocumenti(dtoIn));
    	//CallableStatement callableStatement = prepareCall(PYEH1SP_SEL);
    	//inizio LP PG21XX04 Leak
    	//CallableStatement callableStatement = prepareCall("PYEH1SP_SEL2");
		//inizio LP 20240921 - PGNTECCSV-10
		//callableStatement = prepareCall("PYEH1SP_SEL2");
		callableStatement = prepareCall(bFlagUpodateAutocommit, "PYEH1SP_SEL2");
		//fine LP 20240921 - PGNTECCSV-10
		//fine LP PG21XX04 Leak
		//callableStatement = prepareCall(PYECTSP_LST);   
		callableStatement.setString(1, societa!=null?societa:"");
		callableStatement.setString(2, numeroDocumento!=null?numeroDocumento:"");
		callableStatement.setString(3, codiceUtente!=null?codiceUtente:"");
		callableStatement.setLong(4, progrFlusso == null || progrFlusso.equals("")?0:new Long(progrFlusso));
		callableStatement.setString(5, chiaveTipoServ!=null?chiaveTipoServ:"");
		callableStatement.setString(6, chiaveCodUtenteEnte!=null?chiaveCodUtenteEnte:"");
		callableStatement.setString(7, tipoUfficio!=null?tipoUfficio:"");
		callableStatement.setString(8, codiceUfficio!=null?codiceUfficio:"");
		callableStatement.setString(9, impostaServizio!=null?impostaServizio:"");
		callableStatement.setString(10, codiceTomb!=null?codiceTomb:"");
		if(callableStatement.execute()){
			//inizio LP PG21XX04 Leak
	        //ResultSet rs = callableStatement.getResultSet();
	        rs = callableStatement.getResultSet();
			//fine LP PG21XX04 Leak
			if (rs.next()){
	          dto.setCodImpostaServizio(rs.getString(1));
	          dto.setDescImpostaServizio(rs.getString(2));
	          dto.setAnnoEmissione(rs.getString(3));
	          dto.setNumeroEmissione(rs.getString(4));
	          dto.setCodTipologiaServizio(rs.getString(5));
	          dto.setDescTipologiaServizio(rs.getString(6));
	          dto.setNumeroDocumento(rs.getString(7));
	          dto.setDataNotifica(rs.getString(8));
	          dto.setImpBollettinoTotaleDocumento(rs.getBigDecimal(9));
	          dto.setNumeroBollettinoPagoPA(rs.getString(10));
	          dto.setIbanAccredito(rs.getString(11));
	          dto.setFlagFatturazioneElettronica(rs.getString(12));
	          dto.setIdentificativoUnivocoVersamento(rs.getString(13));
	          dto.setAnaFiscale(rs.getString(14));
	          dto.setAnaDenom(rs.getString(15));
	          dto.setAnaTipoAnag(rs.getString(16));
	          dto.setAnBelfNascita(rs.getString(17));
	          dto.setAnaDataNascita(rs.getString(18));
	          dto.setAnaStato(rs.getString(19));
	          dto.setAnaIndirizzo(rs.getString(20));
	          dto.setAnaFiscaleAlt(rs.getString(21));
	          dto.setAnaMail(rs.getString(22));
	          dto.setAnaMailPec(rs.getString(23));
	          dto.setProvinciaNascita(rs.getString(24));
	          dto.setProvinciaFiscale(rs.getString(25));
	          dto.setCodEnte(rs.getString(26));
	          dto.setFlagGenerazioneIUV(rs.getString(27));
	          dto.setFlagStampaAvviso(rs.getString(28));
	          dto.setIdDominio(rs.getString(29));
	          dto.setAuxDigit(rs.getString(30));
	          dto.setApplCode(rs.getString(31));
	          dto.setSegrCode(rs.getString(32));
	          dto.setIbanAppoggio(rs.getString(33));
	          dto.setCarattServizio(rs.getString(34));
	          dto.setCausale(rs.getString(35));
	          dto.setNomeFlusso(rs.getString(36));
	          dto.setTassonomia(rs.getString(37)); //inizio LP PG200360
	          if (callableStatement.getMoreResults()){
	    		//inizio LP PG21XX04 Leak
	            //ResultSet rs2 = callableStatement.getResultSet(); 
	            rs2 = callableStatement.getResultSet(); 
	    		//fine LP PG21XX04 Leak
	            ArrayList<Scadenza> arrSca = new ArrayList<Scadenza>();
	            //inizio LP PG210130
	            //Scadenza sca = new Scadenza();     
	            Scadenza sca = null;     
	            //fine LP PG210130
	            while (rs2.next()){
	              sca = new Scadenza(); 
	              sca.setNumeroRata(rs2.getInt(1));
	              sca.setDataScadenzaRata(rs2.getString(2));
	              sca.setNumeroBollettinoPagoPA(rs2.getString(3));
	              sca.setImpBollettinoRata(rs2.getBigDecimal(4));
	              sca.setIdentificativoUnivocoVersamento(rs2.getString(5));
	              arrSca.add(sca);
	            }       
	            dto.setScad(arrSca);
	          }         
			}       
		}
    } catch (SQLException x) {
      throw new DaoException(x);
    } catch (IllegalArgumentException x) {
      throw new DaoException(x);
    } catch (HelperException x) {
      throw new DaoException(x);
    } finally {
		//inizio LP PG21XX04 Leak
		if (rs2 != null) {
			try {
				rs2.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		//inizio LP 20240921 - PGNTECCSV-10
		if(bCloseStat) {
		//fine LP 20240921 - PGNTECCSV-10
			if (callableStatement != null) {
				try {
					callableStatement.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			callableStatement = null;
		//inizio LP 20240921 - PGNTECCSV-10
		}
		//fine LP 20240921 - PGNTECCSV-10
		//fine LP PG21XX04 Leak
    }
    return dto;
  }

	public EntrateDocumentiPage getListaEmissioni(EntrateDocumentiPage dto, String ordine, int rowsPerPage, int pageNumber) throws DaoException 
	{
		//CallableStatement callableStatement = null;
		//inizio LP PG21XX04 Leak
		CallableStatement callableStatement = null;
		//fine LP PG21XX04 Leak
		try	{

			/*IN I_PAGENO SMALLINT,
			IN I_ROWSPERPAGE SMALLINT,
			IN I_ORDER VARCHAR(64),
			IN I_EH1_CSOCCSOC CHAR(5),
			IN I_EH1_CUTECUTE CHAR(5),
			IN I_ANE_KANEKENT CHAR(10),
			IN I_EH1_CISECISE CHAR(4),
			IN I_EH1_CEH1CFIS VARCHAR(16),
			IN I_EH1_CEH1ADOC CHAR(4),
			IN I_EH1_CEH1EMIS CHAR(10),
			IN I_EH1_TANETUFF CHAR(1),
			IN I_EH1_CANECUFF CHAR(6),
			IN I_EH1_CEH1NDOC VARCHAR(20),
			IN I_EH1_CTSECTSE CHAR(3),
			IN I_EH7_STATO_DOC CHAR(1),
			IN I_EH1_TEH1SOSP CHAR(1),
			IN I_EH1_FEH1PROC CHAR(1),
			OUT O_SELECT VARCHAR(2000),
			OUT O_ROWINI INTEGER,
			OUT O_ROWEND INTEGER,
			OUT O_TOTROWS INTEGER,
			OUT O_TOTPAGES SMALLINT*/
			/*
			IN I_EH1_CSOCCSOC CHAR(5),
			IN I_EH1_CUTECUTE CHAR(5),
			IN I_ANE_KANEKENT CHAR(10),
			IN I_EH1_CISECISE CHAR(4),
			IN I_EH1_CEH1ADOC CHAR(4),
			IN I_EH1_CEH1EMIS CHAR(10),
			IN I_EH1_TANETUFF CHAR(1),
			IN I_EH1_CANECUFF CHAR(6),
			IN I_EH1_CTSECTSE CHAR(3),
			*/
			//callableStatement = prepareCall(PYECTSP_LST);		
//			CallableStatement callableStatement = prepareCall(PYEH1SP_LST_EMIS);
			//inizio LP PG21XX04 Leak
			//CallableStatement callableStatement = prepareCall(Routines.EH1_EMI_DOLIST.routine());
			callableStatement = prepareCall(Routines.EH1_EMI_DOLIST.routine());
			//fine LP PG21XX04 Leak
			callableStatement.setInt(1, pageNumber);
			callableStatement.setInt(2, rowsPerPage);
			callableStatement.setString(3, ordine);
			callableStatement.setString(4, dto.getCodiceSocieta());
			callableStatement.setString(5, dto.getCodiceUtente());
			callableStatement.setString(6, dto.getCodiceEnte());
			callableStatement.setString(7, dto.getImpostaServizio());
			callableStatement.setString(8, dto.getAnnoEmissione());
			callableStatement.setString(9, dto.getNumeroEmissione());
			callableStatement.setString(10, dto.getTipoUfficio());
			callableStatement.setString(11, dto.getCodiceUfficio());
			callableStatement.setString(12, dto.getTipologiaServizio());
			callableStatement.registerOutParameter(13,Types.VARCHAR);
			/* we register row start */
			callableStatement.registerOutParameter(14, Types.INTEGER);
			/* we register row end */
			callableStatement.registerOutParameter(15, Types.INTEGER);
			/* we register total rows */
			callableStatement.registerOutParameter(16, Types.INTEGER);
			/* we register total pages */
			callableStatement.registerOutParameter(17, Types.SMALLINT);
			/* we execute procedure */
			if (callableStatement.execute()) {
				this.loadWebRowSets(callableStatement);
				/* we register page info */
				registerPageInfo(rowsPerPage, pageNumber, callableStatement.getInt(14), callableStatement.getInt(15), 
								 callableStatement.getInt(16), callableStatement.getInt(17));
				dto.setPageInfo(getPageInfo());
				dto.setListXml(getWebRowSetXml(EntrateBancaDatiDao.IDX_DOLIST_LISTA));
			}
		} catch (SQLException x) {
			throw new DaoException(x);
		} catch (IllegalArgumentException x) {
			throw new DaoException(x);
		} catch (HelperException x) {
			throw new DaoException(x);
		} finally {
			//inizio LP PG21XX04 Leak
			if (callableStatement != null) {
				try {
					callableStatement.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			//fine LP PG21XX04 Leak
		}
		return dto;
	}

	public EntrateDocumentiPage getStatisticheEmissioni(EntrateDocumentiPage dto) throws DaoException 
	{
		/*
		IN I_EH1_CSOCCSOC CHAR(5),
		IN I_EH1_CUTECUTE CHAR(5),
		IN I_ANE_KANEKENT CHAR(10),
		IN I_EH1_CISECISE CHAR(4),
		IN I_EH1_CEH1ADOC CHAR(4),
		IN I_EH1_CEH1EMIS CHAR(10),
		IN I_EH1_TANETUFF CHAR(1),
		IN I_EH1_CANECUFF CHAR(6),
		IN I_EH1_CTSECTSE CHAR(3),
		OUT O_TOT_CAR DECIMAL(15 , 2),
		OUT O_TOT_REND DECIMAL(15 , 2),
		OUT O_TOT_DIM_CAR DECIMAL(15 , 2),
		OUT O_TOT_RISC DECIMAL(15 , 2),
		OUT O_TOT_RIMB DECIMAL(15 , 2),
		OUT O_TOT_RESIDUO DECIMAL(15 , 2)
		*/
		//inizio LP PG21XX04 Leak
		CallableStatement callableStatement = null;
		//fine LP PG21XX04 Leak
		try	{
//			CallableStatement callableStatement = prepareCall(PYEH1SP_STAT_EMIS);
			//inizio LP PG21XX04 Leak
			//CallableStatement callableStatement = prepareCall(Routines.EH1_EMI_STAT.routine());
			callableStatement = prepareCall(Routines.EH1_EMI_STAT.routine());
			//fine LP PG21XX04 Leak
			//callableStatement = prepareCall(PYECTSP_LST);		
			callableStatement.setString(1, dto.getCodiceSocieta());
			callableStatement.setString(2, dto.getCodiceUtente());
			callableStatement.setString(3, dto.getCodiceEnte());
			callableStatement.setString(4, dto.getImpostaServizio());
			callableStatement.setString(5, dto.getAnnoEmissione());
			callableStatement.setString(6, dto.getNumeroEmissione());
			callableStatement.setString(7, dto.getTipoUfficio());
			callableStatement.setString(8, dto.getCodiceUfficio());
			callableStatement.setString(9, dto.getTipologiaServizio());
			callableStatement.registerOutParameter(10,Types.DECIMAL);
			callableStatement.registerOutParameter(11,Types.DECIMAL);
			callableStatement.registerOutParameter(12,Types.DECIMAL);
			callableStatement.registerOutParameter(13,Types.DECIMAL);
			callableStatement.registerOutParameter(14,Types.DECIMAL);
			callableStatement.registerOutParameter(15,Types.DECIMAL);
			callableStatement.registerOutParameter(16,Types.DECIMAL);
			callableStatement.execute();
			dto.setTotcarico(callableStatement.getBigDecimal(10));
			dto.setTotrendicontato(callableStatement.getBigDecimal(11));
			dto.setTotdimcarico(callableStatement.getBigDecimal(12));
			dto.setTotriscosso(callableStatement.getBigDecimal(13));
			dto.setTotrimborso(callableStatement.getBigDecimal(14));
			dto.setTotresiduo(callableStatement.getBigDecimal(15));
			dto.setTotResScaduto(callableStatement.getBigDecimal(16));
		} catch (SQLException x) {
			throw new DaoException(x);
		} catch (IllegalArgumentException x) {
			throw new DaoException(x);
		} catch (HelperException x) {
			throw new DaoException(x);
		} finally {
			//inizio LP PG21XX04 Leak
			if (callableStatement != null) {
				try {
					callableStatement.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			//fine LP PG21XX04 Leak
		}
		return dto;
	}
	
	public EntrateDocumentoDettaglio getDettaglioEmissione(EntrateDocumentiPage dtoIn) throws DaoException 
	{
		//inizio LP PG21XX04 Leak
		CallableStatement callableStatement = null;
		ResultSet rs = null;
		//fine LP PG21XX04 Leak
		EntrateDocumentoDettaglio dto = new EntrateDocumentoDettaglio();
		try	{
			//dto.setDocumentiTot(getStatisticheDocumenti(dtoIn));
//			CallableStatement callableStatement = prepareCall(PYEH1SP_SEL_EMIS);
			//inizio LP PG21XX04 Leak
			//CallableStatement callableStatement = prepareCall(Routines.EH1_EMI_DODETAIL.routine());
			callableStatement = prepareCall(Routines.EH1_EMI_DODETAIL.routine());
			//fine LP PG21XX04 Leak
			//callableStatement = prepareCall(PYECTSP_LST);		
			callableStatement.setString(1, dtoIn.getCodiceUtente()!=null?dtoIn.getCodiceUtente():"");
			callableStatement.setString(2, dtoIn.getChiaveCodiceEnte()!=null?dtoIn.getChiaveCodiceEnte():"");
			callableStatement.setString(3, dtoIn.getImpostaServizio()!=null?dtoIn.getImpostaServizio():"");
			callableStatement.setString(4, dtoIn.getAnnoEmissione()!=null?dtoIn.getAnnoEmissione():"");
			callableStatement.setString(5, dtoIn.getNumeroEmissione()!=null?dtoIn.getNumeroEmissione():"");
			callableStatement.setString(6, dtoIn.getTipoUfficio()!=null?dtoIn.getTipoUfficio():"");
			callableStatement.setString(7, dtoIn.getCodiceUfficio()!=null?dtoIn.getCodiceUfficio():"");
			callableStatement.registerOutParameter(8,Types.DECIMAL);
			if(callableStatement.execute()){
				//this.loadWebRowSets(callableStatement);
				//dto.setListXml(getWebRowSetXml(EntrateBancaDatiDao.IDX_DOLIST_LISTA));
				//inizio LP PG21XX04 Leak
				//ResultSet rs = callableStatement.getResultSet();
				rs = callableStatement.getResultSet();
				//fine LP PG21XX04 Leak
				if(rs.next()){
					dto.setImpostaServizio(rs.getString(1));
			    	dto.setUfficioImpositore(rs.getString(2));
			    	dto.setAnnoEmissione(rs.getString(3));
			    	dto.setNumeroEmissione(rs.getString(4));
			    	dto.setTipologiaServizio(rs.getString(5));
			    	dto.setNumDocumentiEmis(rs.getString(6));
			    	dto.setNumDocumentiRendEmis(rs.getString(7));
					dto.setDescSocieta(rs.getString(8));
					dto.setDescUtente(rs.getString(9));
					dto.setDescEnte(rs.getString(10));
					dtoIn.setTotcarico(rs.getBigDecimal(11)== null?new java.math.BigDecimal(0):rs.getBigDecimal(11));
					dtoIn.setTotriscosso(rs.getBigDecimal(12)== null?new java.math.BigDecimal(0):rs.getBigDecimal(12));
					dtoIn.setTotdimcarico(rs.getBigDecimal(13)== null?new java.math.BigDecimal(0):rs.getBigDecimal(13));
					dtoIn.setTotrimborso(rs.getBigDecimal(14)== null?new java.math.BigDecimal(0):rs.getBigDecimal(14));
					dtoIn.setTotcaricoRendicontato(rs.getBigDecimal(15)== null?new java.math.BigDecimal(0):rs.getBigDecimal(15));
					dtoIn.setTotriscossoRendicontato(rs.getBigDecimal(16)== null?new java.math.BigDecimal(0):rs.getBigDecimal(16));
					dtoIn.setTotdimcaricoRendicontato(rs.getBigDecimal(17)== null?new java.math.BigDecimal(0):rs.getBigDecimal(17));
					dtoIn.setTotrimborsoRendicontato(rs.getBigDecimal(18)== null?new java.math.BigDecimal(0):rs.getBigDecimal(18));
					dtoIn.setTotResScaduto(callableStatement.getBigDecimal(8));
					dto.setDocumentiTot(dtoIn);
				}					
			}
		} catch (SQLException x) {
			throw new DaoException(x);
		} catch (IllegalArgumentException x) {
			throw new DaoException(x);
		} catch (HelperException x) {
			throw new DaoException(x);
		} finally {
			//inizio LP PG21XX04 Leak
			if (rs != null) {
				try {
					rs.close();
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
		return dto;
	}

	//PG170070 GG 20170530 - inizio
	public String getListaTipologiaServizio(String codiceSocieta, String codiceUtente, String codiceEnte, String tipoServizio) throws DaoException 
	{
		CallableStatement callableStatement = null;
//		ResultSet data = null;
		try	{
			callableStatement = prepareCall(Routines.EH6_TSE_DDL.routine());
			callableStatement.setString(1, codiceSocieta);
			callableStatement.setString(2, codiceUtente);
			callableStatement.setString(3, codiceEnte);
			callableStatement.setString(4, tipoServizio);
//			if(callableStatement.execute()) {
//				data = callableStatement.getResultSet();
//				loadWebRowSet(data);
//				return getWebRowSetXml();
//			}
//			return null;
			if (callableStatement.execute()) {
				this.loadWebRowSets(callableStatement);
				return getWebRowSetXml(EntrateBancaDatiDao.IDX_DOLIST_LISTA);
			}
		} catch (SQLException x) {
			throw new DaoException(x);
		} catch (IllegalArgumentException x) {
			throw new DaoException(x);
		} catch (HelperException x) {
			throw new DaoException(x);
		} finally {
//			DaoUtil.closeResultSet(data);
//			DaoUtil.closeStatement(callableStatement);
			//inizio LP PG21XX04 Leak
			if (callableStatement != null) {
				try {
					callableStatement.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			//fine LP PG21XX04 Leak
		}
		return "";
	}
	//PG170070 GG 20170530 - fine
}
