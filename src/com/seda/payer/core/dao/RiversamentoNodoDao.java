package com.seda.payer.core.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import com.seda.data.helper.HelperException;
import com.seda.payer.core.bean.AvanzamentoStato;
import com.seda.payer.core.bean.RiversamentoNodo;
import com.seda.payer.core.bean.RiversamentoNodoPage;
import com.seda.payer.core.bean.RiversamentoNodoDetailPage;
import com.seda.payer.core.bean.RiversamentoNodoReport;
import com.seda.payer.core.bean.RiversamentoNodoUpdate;
import com.seda.payer.core.exception.DaoException;
import com.seda.payer.core.handler.BaseDaoHandler;
import com.seda.payer.core.messages.Messages;

public class RiversamentoNodoDao extends BaseDaoHandler {

	public RiversamentoNodoDao(Connection connection, String schema) {
		super(connection, schema);
	}

	public RiversamentoNodoPage getRiversamentiNodo(RiversamentoNodoPage dto, String ordine) throws DaoException {
		
		return getRiversamentiNodo(dto, ordine, 0, 0);
	}

	public RiversamentoNodoPage getRiversamentiNodo(RiversamentoNodoPage dto, String ordine, int rowsPerPage, int pageNumber) throws DaoException {

			if (rowsPerPage <= 0)
				throw new IllegalArgumentException(Messages.INVALID_PARAMETER.format("rowsPerPage"));

			if (pageNumber <= 0)
				throw new IllegalArgumentException(Messages.INVALID_PARAMETER.format("pageNumber"));
 
			dto = getLista(dto, ordine, rowsPerPage, pageNumber);
			dto = getStatistiche(dto);
			return dto;

	}

	public RiversamentoNodoDetailPage getDettaglioRiv(RiversamentoNodoDetailPage dto, String ordine) throws DaoException {
		
		return getDettaglioRiv(dto, ordine, 0, 0);
	}

	public RiversamentoNodoDetailPage getDettaglioRiv(RiversamentoNodoDetailPage dto, String ordine, int rowsPerPage, int pageNumber) throws DaoException {

		if (rowsPerPage <= 0)
			throw new IllegalArgumentException(Messages.INVALID_PARAMETER.format("rowsPerPage"));

		if (pageNumber <= 0)
			throw new IllegalArgumentException(Messages.INVALID_PARAMETER.format("pageNumber"));

	  	dto = getListaDettagli(dto, ordine, rowsPerPage, pageNumber);
		dto = getStatisticheDettagli(dto);
		return dto;

}

// NUOVO metodo parametrizzato
	@SuppressWarnings("unchecked")
	public ArrayList elaboraNuoviRiversamentiNodo(String flagTranRiversabili, String elencoFestivita, String separatoreElencoFest) throws DaoException 
	{
		CallableStatement callableStatement = null;
		ArrayList messaggiLog = null;
		//inizio LP PG21XX04 Leak
		ResultSet risultato = null;
		//fine LP PG21XX04 Leak
		try	{

			//lista riversamenti
			//callableStatement = prepareCall("PYREVSP_ALGREV_CREAREVSP_NODO");
			callableStatement = prepareCall(Routines.REV_ALGRIV2_NODO.routine());
			callableStatement.setString(1, flagTranRiversabili);
			callableStatement.setString(2, elencoFestivita);
			callableStatement.setString(3, separatoreElencoFest);
			callableStatement.registerOutParameter(4, Types.INTEGER);

            messaggiLog = new ArrayList();
			if (callableStatement.execute()) 
			{
				//commit();
				//inizio LP PG21XX04 Leak
				//ResultSet risultato = callableStatement.getResultSet();
				risultato = callableStatement.getResultSet();
				//fine LP PG21XX04 Leak
				while (risultato.next())
				{
					messaggiLog.add(risultato.getString(1));
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
			//closeConnection(callableStatement);
			if (risultato != null) {
				try {
					risultato.close();
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
		return messaggiLog;
	}
	
	
	@SuppressWarnings("unchecked")
	public ArrayList certificazioneRiversamentiNodo() throws DaoException 
	{
		ArrayList messaggiLog = null;
		CallableStatement callableStatement = null;
		//inizio LP PG21XX04 Leak
		ResultSet risultato = null;
		//fine LP PG21XX04 Leak
		try	{

			//lista riversamenti

/*			
//			callableStatement = prepareCall("PYALGCERSP_CERTIFICA");
			callableStatement = prepareCall(Routines.REV_ALGCER.routine());
*/
//			callableStatement = prepareCall("PYREVSP_ALGCER_CERIFICA");
			callableStatement = prepareCall(Routines.REV_ALGCER2.routine());
			callableStatement.registerOutParameter(1, Types.INTEGER);
			
            messaggiLog = new ArrayList();
			if (callableStatement.execute()) 
			{
				//inizio LP PG21XX04 Leak
				//ResultSet risultato = callableStatement.getResultSet();
				risultato = callableStatement.getResultSet();
				//fine LP PG21XX04 Leak
				while (risultato.next())
				{
					messaggiLog.add(risultato.getString(1));
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
			//closeConnection(callableStatement);
			if (risultato != null) {
				try {
					risultato.close();
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
		return messaggiLog;
	}

	public RiversamentoNodoPage getLista(RiversamentoNodoPage dto, String ordine, int rowsPerPage, int pageNumber) throws DaoException {
		CallableStatement callableStatement = null;

		try	{

			//lista riversamenti
//			callableStatement = prepareCall("PYREVSP_LST");
			callableStatement = prepareCall(Routines.REV_DOLIST.routine());

			
			callableStatement.setString(1, dto.getCodiceSocieta());
			callableStatement.setString(2, dto.getCodiceBelfiore());
			callableStatement.setString(3, dto.getCodiceUtente());
			callableStatement.setString(4, dto.getDataRiversamentoDa());
			callableStatement.setString(5, dto.getDataRiversamentoA());

			callableStatement.setString(6, dto.getEnteBeneficiario());
			callableStatement.setString(7, dto.getTipoRendicontazione());
			callableStatement.setString(8, dto.getTipoRiversamento());
			callableStatement.setString(9, dto.getStatoRiversamento());

			callableStatement.setString(10, ordine);
			callableStatement.setInt(11, rowsPerPage);
			callableStatement.setInt(12, pageNumber);

			/* we register row start */
			callableStatement.registerOutParameter(13, Types.INTEGER);
			/* we register row end */
			callableStatement.registerOutParameter(14, Types.INTEGER);
			/* we register total rows */
			callableStatement.registerOutParameter(15, Types.INTEGER);
			/* we register total pages */
			callableStatement.registerOutParameter(16, Types.SMALLINT);

			callableStatement.registerOutParameter(17, Types.INTEGER);
			
			/* we execute procedure */
			if (callableStatement.execute()) 
			{
				this.loadWebRowSets(callableStatement);
				/* we register page info */
				registerPageInfo(rowsPerPage, pageNumber, callableStatement.getInt(13), callableStatement.getInt(14), 
								 callableStatement.getInt(15), callableStatement.getInt(16));
				dto.setPageInfo(getPageInfo());
				dto.setListXml(getWebRowSetXml(RiversamentoNodoDao.IDX_DOLIST_LISTA));

				dto.setNumeroRivFuturi(callableStatement.getInt(17));
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
		
		return dto;
	}

	
	public RiversamentoNodoPage getStatistiche(RiversamentoNodoPage dto) throws DaoException {
		CallableStatement callableStatement = null;
		//inizio LP PG21XX04 Leak
		ResultSet risultato = null;
		//fine LP PG21XX04 Leak
		try	{

			//lista riversamenti
			//callableStatement = prepareCall("PYREVSP_STAT");
			callableStatement = prepareCall(Routines.REV_DOSTAT.routine());

			callableStatement.setString(1, dto.getCodiceSocieta());
			callableStatement.setString(2, dto.getCodiceBelfiore());
			callableStatement.setString(3, dto.getCodiceUtente());
			callableStatement.setString(4, dto.getDataRiversamentoDa());
			callableStatement.setString(5, dto.getDataRiversamentoA());

			callableStatement.setString(6, dto.getEnteBeneficiario());
			callableStatement.setString(7, dto.getTipoRendicontazione());
			callableStatement.setString(8, dto.getTipoRiversamento());
			callableStatement.setString(9, dto.getStatoRiversamento());



			if (callableStatement.execute()) 
			{
				//inizio LP PG21XX04 Leak
				//ResultSet risultato = callableStatement.getResultSet();
				risultato = callableStatement.getResultSet();
				//fine LP PG21XX04 Leak
				risultato.next();

				
				dto.setTotImportoContribuenti(risultato.getBigDecimal(1));  //TOT_IREVTOTA IMPORTO
				dto.setTotImportoCittadino(risultato.getBigDecimal(2));     //TOT_IREVCONC 
				dto.setTotCommissioniGateway(risultato.getBigDecimal(3));   //TOT_IREVCGTW BANCA
				dto.setTotSpeseNotifica(risultato.getBigDecimal(4));        //TOT_IREVSPES 
				dto.setTotCommissioneGestore(risultato.getBigDecimal(5));   //TOT_IREVGESC
				dto.setTotImportoRiversare(risultato.getBigDecimal(6));     //TOT_IREVIREV_RIV
				dto.setTotImportoRecuperare(risultato.getBigDecimal(7));     //TOT_IREVIREV_REC
				
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
			if (risultato != null) {
				try {
					risultato.close();
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

	
	public RiversamentoNodoDetailPage getListaDettagli(RiversamentoNodoDetailPage dto, String ordine, int rowsPerPage, int pageNumber) throws DaoException {
		CallableStatement callableStatement = null;
		try	{

			//lista riversamenti
			//			CallableStatement callableStatement = prepareCall(Routines.REV_DOLIST.routine());
			
			callableStatement = null;
			if (dto.getTipoDettaglio().equals("R"))
//				callableStatement = prepareCall("PYREDSP_LST");
				callableStatement = prepareCall(Routines.RED_DOLIST.routine());
			else
//				callableStatement = prepareCall("PYRIVSP_LST");
				callableStatement = prepareCall(Routines.RIV_DOLIST.routine());
				
			
			callableStatement.setString(1, dto.getCodiceSocieta());
			callableStatement.setString(2, dto.getCodiceUtente());
			callableStatement.setString(3, dto.getDataRiversamento());
			callableStatement.setString(4, dto.getEnteBeneficiario());
			callableStatement.setString(5, dto.getTipoRendicontazione());
			callableStatement.setString(6, dto.getTipoRiversamento());

			callableStatement.setString(7, ordine);
			callableStatement.setInt(8, rowsPerPage);
			callableStatement.setInt(9, pageNumber);

			/* we register row start */
			callableStatement.registerOutParameter(10, Types.INTEGER);
			/* we register row end */
			callableStatement.registerOutParameter(11, Types.INTEGER);
			/* we register total rows */
			callableStatement.registerOutParameter(12, Types.INTEGER);
			/* we register total pages */
			callableStatement.registerOutParameter(13, Types.SMALLINT);
			/* we execute procedure */
			if (callableStatement.execute()) 
			{
				this.loadWebRowSets(callableStatement);
				/* we register page info */
				registerPageInfo(rowsPerPage, pageNumber, callableStatement.getInt(10), callableStatement.getInt(11), 
								 callableStatement.getInt(12), callableStatement.getInt(13));
				dto.setPageInfo(getPageInfo());
				dto.setListXml(getWebRowSetXml(RiversamentoNodoDao.IDX_DOLIST_LISTA));
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
		
		return dto;
	}

	
	public RiversamentoNodoDetailPage getStatisticheDettagli(RiversamentoNodoDetailPage dto) throws DaoException {
		CallableStatement callableStatement = null;
		//inizio LP PG21XX04 Leak
		ResultSet risultato = null;
		//fine LP PG21XX04 Leak
		try	{

			//lista riversamenti
			//			CallableStatement callableStatement = prepareCall(Routines.REV_DOLIST.routine());
			
			callableStatement = null;
			if (dto.getTipoDettaglio().equals("R"))
//				callableStatement = prepareCall("PYREDSP_STAT");
				callableStatement = prepareCall(Routines.RED_DOLISTSTAT.routine());
			else
//				callableStatement = prepareCall("PYRIVSP_STAT");
				callableStatement = prepareCall(Routines.RIV_DOLISTSTAT.routine());

			callableStatement.setString(1, dto.getCodiceSocieta());
			callableStatement.setString(2, dto.getCodiceUtente());
			callableStatement.setString(3, dto.getDataRiversamento());
			callableStatement.setString(4, dto.getEnteBeneficiario());
			callableStatement.setString(5, dto.getTipoRendicontazione());
			callableStatement.setString(6, dto.getTipoRiversamento());
			
			if (callableStatement.execute()) 
			{
				//inizio LP PG21XX04 Leak
				//ResultSet risultato = callableStatement.getResultSet();
				risultato = callableStatement.getResultSet();
				//fine LP PG21XX04 Leak
				risultato.next();


				if (dto.getTipoDettaglio().equals("R"))
				{
					dto.setTotImportoContribuenti(risultato.getBigDecimal("TOT_IREDTOTA"));  //TOT_IREVTOTA IMPORTO
					dto.setTotImportoCittadino(risultato.getBigDecimal("TOT_IREDCONC"));     //TOT_IREVCONC 
					dto.setTotCommissioniGateway(risultato.getBigDecimal("TOT_IREDCGTW"));   //TOT_IREVCGTW BANCA
					dto.setTotSpeseNotifica(risultato.getBigDecimal("TOT_IREDSPES"));        //TOT_IREVSPES 
					dto.setTotCommissioneGestore(risultato.getBigDecimal("TOT_IREDGESC"));   //TOT_IREVGESC	
				}
				else
				{
					dto.setTransazioni(risultato.getInt("TOT_TRANSAZIONI"));  //Totale transazioni
					dto.setTotImportoContribuenti(risultato.getBigDecimal("TOT_IBOLTOTA"));  //TOT_IREVTOTA IMPORTO
					dto.setTotImportoCittadino(risultato.getBigDecimal("TOT_IBOLCONC"));     //TOT_IREVCONC 
					dto.setTotCommissioniGateway(risultato.getBigDecimal("TOT_IBOLCGTW"));   //TOT_IREVCGTW BANCA
					dto.setTotSpeseNotifica(risultato.getBigDecimal("TOT_IBOLSPES"));        //TOT_IREVSPES 
					dto.setTotCommissioneGestore(risultato.getBigDecimal("TOT_IBOLGESC"));   //TOT_IREVGESC
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
			//closeConnection(callableStatement);
			if (risultato != null) {
				try {
					risultato.close();
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
	
	
	public RiversamentoNodoUpdate aggiornamentoStatoRiversamentoNodo(RiversamentoNodoUpdate dto) throws DaoException {
		//inizio LP PG21XX04 Leak
		CallableStatement callableStatement = null;
		//fine LP PG21XX04 Leak
		try {
//			CallableStatement callableStatement = prepareCall("PYREVSP_UPD_STA");
			//inizio LP PG21XX04 Leak
			//CallableStatement callableStatement = prepareCall(Routines.REV_DOUPDATESTAT.routine());
			callableStatement = prepareCall(Routines.REV_DOUPDATESTAT.routine());
			//fine LP PG21XX04 Leak
			callableStatement.setString(1, dto.getCodiceSocieta());
			callableStatement.setString(2, dto.getCodiceUtente());
			callableStatement.setString(3, dto.getDataRiversamento());
			callableStatement.setString(4, dto.getEnteBeneficiario());
			callableStatement.setString(5, dto.getTipoRiversamento());
			callableStatement.setString(6, dto.getTipoRendicontazione());
			callableStatement.setString(7, dto.getStatoRiversamento());
			callableStatement.setString(8, dto.getOperatore());
			callableStatement.setString(9, dto.getNota());
			
			callableStatement.registerOutParameter(10, Types.VARCHAR );
			callableStatement.registerOutParameter(11, Types.VARCHAR);
//			callableStatement.registerOutParameter(12, Types.VARCHAR);
			
			callableStatement.execute();
			dto.setRetCode(callableStatement.getString(10));
			dto.setRetMessage(callableStatement.getString(11));
			dto.setMessage("");
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
			throw new DaoException(e);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DaoException(e);
		} catch (HelperException e) {
			e.printStackTrace();
			throw new DaoException(e);
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
		return dto;
	}
	
	@SuppressWarnings("unchecked")
	public ArrayList getRiversamentiNodoDaStampare() throws DaoException {
		ArrayList riversamenti = new ArrayList();
		CallableStatement callableStatement = null;
		ResultSet data = null;
		try {
//			callableStatement = prepareCall("PYREVSP_EXE");
			callableStatement = prepareCall(Routines.REV_DOEXEC.routine());
			if(callableStatement.execute()) {
				data = callableStatement.getResultSet();
			}
			int position = 0;
			while (data.next()) {
				RiversamentoNodo riversamentoNodo = new RiversamentoNodo();
//				codice società
				riversamentoNodo.setREV_COSCCSOC(data.getString("REV_COSCCSOC"));
//				codice utente
				riversamentoNodo.setREV_CUTECUTE(data.getString("REV_CUTECUTE"));
//				data riversamentoNodo
				riversamentoNodo.setREV_GREVGDAT(data.getTimestamp("REV_GREVGDAT"));
//				codice ente beneficiario
				riversamentoNodo.setREV_KANEKANE_BEN(data.getString("REV_KANEKANE_BEN"));
				riversamentoNodo.setREV_FREVTIPO(data.getString("REV_FREVTIPO"));
				riversamentoNodo.setREV_FREVRIVE(data.getString("REV_FREVRIVE"));
				riversamentoNodo.setREV_DREVDOWN(data.getString("REV_DREVDOWN"));
				riversamentoNodo.setREV_DREVREPO(data.getString("REV_DREVREPO"));
//				tipo ufficio
				riversamentoNodo.setANE_CANECENT(data.getString("ANE_CANECENT"));
				//				tipo ufficio
				riversamentoNodo.setANE_TANETUFF(data.getString("ANE_TANETUFF"));
//				codice uffico
				riversamentoNodo.setANE_CANECUFF(data.getString("ANE_CANECUFF"));
				riversamentoNodo.setBEN_TBENFCSV(data.getString("BEN_TBENFCSV"));
				
				riversamenti.add(position, riversamentoNodo);
				position++;
			}
		} catch (Exception e) {
			e.printStackTrace();
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
//		finally  {
//			DaoUtil.closeResultSet(data);
//			DaoUtil.closeStatement(callableStatement);
//		}
		return riversamenti;
	} 

	
	public ResultSet getRiversamentoNodoDetailDC(String rev_cosccsoc, String rev_cutecute, 
			Date rev_grevgdat, String rev_kanekane_ben, 
            String rev_frevtipo, String rev_frevrive) throws DaoException {

		CallableStatement callableStatement = null;
		ResultSet data = null;
		try 
		{
//				callableStatement = prepareCall("PYENSSP_PDF_DC");
			    callableStatement = prepareCall(Routines.REV_DOPDF_DC.routine());
				callableStatement.setString(1, rev_cosccsoc);
				callableStatement.setString(2, rev_cutecute);
				DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
	    		String data_format = formatter.format(rev_grevgdat);
				callableStatement.setString(3, data_format);
				callableStatement.setString(4, rev_kanekane_ben);
				callableStatement.setString(5, rev_frevtipo);
				callableStatement.setString(6, rev_frevrive);
				if(callableStatement.execute()) 
				{
					data = callableStatement.getResultSet();
				}
		
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			throw new DaoException(e);
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
		return data;
	}

	public RiversamentoNodo getRiversamentoNodoDetail(String rev_cosccsoc, String rev_cutecute, 
			String rev_grevgdat, String rev_kanekane_ben, 
            String rev_frevtipo, String rev_frevrive) throws DaoException {

		CallableStatement callableStatement = null;
		//inizio LP PG21XX04 Leak
		ResultSet data = null;
		//fine LP PG21XX04 Leak
		RiversamentoNodo riversamentoNodo = null;
		try 
		{
//				callableStatement = prepareCall("PYREVSP_SEL");
			    callableStatement = prepareCall(Routines.REV_DODETAIL.routine());
				callableStatement.setString(1, rev_cosccsoc);
				callableStatement.setString(2, rev_cutecute);
				callableStatement.setString(3, rev_grevgdat);
				callableStatement.setString(4, rev_kanekane_ben);
				callableStatement.setString(5, rev_frevtipo);
				callableStatement.setString(6, rev_frevrive);
				if (callableStatement.execute()) 
				{
					//inizio LP PG21XX04 Leak
					//ResultSet data = callableStatement.getResultSet();
					data = callableStatement.getResultSet();
					//fine LP PG21XX04 Leak
					if (data.next())
					{
						riversamentoNodo = new RiversamentoNodo();
						riversamentoNodo.setREV_IREVIREV(data.getBigDecimal("REV_IREVIREV"));
					}
				}
		
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
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
		return riversamentoNodo;
	}
	
	public boolean isInvioMailBen(String rev_cosccsoc, String rev_cutecute, 
			String rev_grevgdat, String rev_kanekane_ben, 
            String rev_frevtipo, String rev_frevrive) throws DaoException {

		CallableStatement callableStatement = null;
		boolean ris = false;
		try 
		{
//				callableStatement = prepareCall("PYREVSP_CHECK_MAIL");
			    callableStatement = prepareCall(Routines.REV_CHECK_MAIL.routine());
				callableStatement.setString(1, rev_cosccsoc);
				callableStatement.setString(2, rev_cutecute);
				callableStatement.setString(3, rev_grevgdat);
				callableStatement.setString(4, rev_kanekane_ben);
				callableStatement.setString(5, rev_frevtipo);
				callableStatement.setString(6, rev_frevrive);
				callableStatement.registerOutParameter(7, Types.VARCHAR);
				callableStatement.execute(); 
				
				if (callableStatement.getString(7).equals("Y"))
						ris = true;
		
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			throw new DaoException(e);
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
		return ris;
	}
	
	public ResultSet getRiversamentoNodoCSV(String rev_cosccsoc, String rev_cutecute, 
			Date rev_grevgdat, String rev_kanekane_ben, 
            String rev_frevtipo, String rev_frevrive) throws DaoException {

		CallableStatement callableStatement = null;
		ResultSet data = null;
		try 
		{
//				callableStatement = prepareCall("PYENSSP_CSV");
			    callableStatement = prepareCall(Routines.REV_DOCSV.routine());
				callableStatement.setString(1, rev_cosccsoc);
				callableStatement.setString(2, rev_cutecute);
				DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
	    		String data_format = formatter.format(rev_grevgdat);
				callableStatement.setString(3, data_format);
				callableStatement.setString(4, rev_kanekane_ben);
				callableStatement.setString(5, rev_frevtipo);
				callableStatement.setString(6, rev_frevrive);
				if(callableStatement.execute()) 
				{
					data = callableStatement.getResultSet();
				}
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			throw new DaoException(e);
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
		return data;
	}
	
	
	public RiversamentoNodo getRiversamentoNodo(String codiceSocieta, String codiceUtente,String dataRiversamento, String codiceBeneficiario, String tipoRendicondazione, String strumentoRendicondazione) throws DaoException {
		RiversamentoNodo riversamentoNodo = new RiversamentoNodo();
		CallableStatement callableStatement = null;
		ResultSet data = null;
		try {
//			callableStatement = prepareCall("PYREVTB_SEARCH");
			callableStatement = prepareCall(Routines.REV_DOSEARCH.routine());
			callableStatement.setString(1, codiceSocieta);
			callableStatement.setString(2, codiceUtente);
			callableStatement.setString(3, dataRiversamento.substring(0, 10));
			callableStatement.setString(4, codiceBeneficiario);
			callableStatement.setString(5, strumentoRendicondazione);
			callableStatement.setString(6, tipoRendicondazione);
			if(callableStatement.execute()) {
				data = callableStatement.getResultSet();
			}
			if (data.next()) {
//				codice società
				riversamentoNodo.setREV_COSCCSOC(data.getString("REV_COSCCSOC"));
//				codice utente
				riversamentoNodo.setREV_CUTECUTE(data.getString("REV_CUTECUTE"));
//				data riversamentoNodo
				riversamentoNodo.setREV_GREVGDAT(data.getTimestamp("REV_GREVGDAT"));
//				codice ente beneficiario
				riversamentoNodo.setREV_KANEKANE_BEN(data.getString("REV_KANEKANE_BEN"));
				riversamentoNodo.setREV_FREVTIPO(data.getString("REV_FREVTIPO"));
				riversamentoNodo.setREV_FREVRIVE(data.getString("REV_FREVRIVE"));
 
				riversamentoNodo.setREV_DREVDOWN(data.getString("REV_DREVDOWN"));
				riversamentoNodo.setREV_DREVREPO(data.getString("REV_DREVREPO"));
//				tipo ufficio
				riversamentoNodo.setANE_CANECENT(data.getString("ANE_CANECENT"));
//				tipo ufficio
				riversamentoNodo.setANE_TANETUFF(data.getString("ANE_TANETUFF"));
//				codice ufficio
				riversamentoNodo.setANE_CANECUFF(data.getString("ANE_CANECUFF"));

//              file CBI
				riversamentoNodo.setCBI_DCBIFILE(data.getString("CBI_DCBIFILE"));
				
				riversamentoNodo.setBEN_TBENFCSV(data.getString("BEN_TBENFCSV"));
			}
		} catch (Exception e) {
			e.printStackTrace();
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
/*
		finally  {
			DaoUtil.closeResultSet(data);
			DaoUtil.closeStatement(callableStatement);
		}
*/
		return riversamentoNodo;
	}
	
	
	public void updateRiversamentoNodo(String tipo, String fileName,RiversamentoNodo riversamentoNodo) throws DaoException{
		CallableStatement callableStatement = null;
		try {
//			callableStatement = prepareCall("PYREVSP_UPDFILE");
			callableStatement = prepareCall(Routines.REV_DOUPDFILE.routine());
			callableStatement.setString(1, riversamentoNodo.getREV_COSCCSOC());
			callableStatement.setString(2, riversamentoNodo.getREV_CUTECUTE());
			DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
    		Date data = (Date) riversamentoNodo.getREV_GREVGDAT();
    		String data_format = formatter.format(data);
			callableStatement.setString(3, data_format);
			callableStatement.setString(4, riversamentoNodo.getREV_KANEKANE_BEN());
			callableStatement.setString(5, riversamentoNodo.getREV_FREVTIPO());
			callableStatement.setString(6, riversamentoNodo.getREV_FREVRIVE());
			if ("pdf".equals(tipo)) {
				callableStatement.setString(7, fileName);
				callableStatement.setString(8, null);
			} else {
				callableStatement.setString(7, null);
				callableStatement.setString(8, fileName);
			}
			callableStatement.execute();
		} catch(Exception e) {
			e.printStackTrace();
			throw new DaoException(e);
		} finally  {
			//inizio LP PG21XX04 Leak
			//DaoUtil.closeStatement(callableStatement);
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
	
	@SuppressWarnings("unchecked")
	public ArrayList getRiversamentiNuoviFlussiCBI(String orarioLimite) throws DaoException {
		ArrayList riversamentiNuoviFlussiCBI = new ArrayList();
		CallableStatement callableStatement = null;
		ResultSet data = null;
		try {
//			callableStatement = prepareCall("PYRNF_CBI");
			callableStatement = prepareCall(Routines.REV_DORIVNF.routine());
			callableStatement.setString(1, orarioLimite);
			if(callableStatement.execute()) {
				data = callableStatement.getResultSet();
			}
			int position = 0;
			while (data.next()) {
				RiversamentoNodo riversamentoNuoviFlussiCBI = new RiversamentoNodo();
//				codice società
				riversamentoNuoviFlussiCBI.setREV_COSCCSOC(data.getString("REV_COSCCSOC"));
//				codice utente
				riversamentoNuoviFlussiCBI.setREV_CUTECUTE(data.getString("REV_CUTECUTE"));
//				data riversamento
				riversamentoNuoviFlussiCBI.setREV_GREVGDAT(data.getDate("REV_GREVGDAT"));
//				codice anagrafica beneficiario
				riversamentoNuoviFlussiCBI.setREV_KANEKANE_BEN(data.getString("REV_KANEKANE_BEN"));
//				codice ente beneficiario
				riversamentoNuoviFlussiCBI.setANE_CANECENT(data.getString("ANE_CANECENT"));
				//				chiave flusso CBI
				riversamentoNuoviFlussiCBI.setREV_FREVTIPO(data.getString("REV_FREVTIPO"));
				
				riversamentoNuoviFlussiCBI.setREV_FREVRIVE(data.getString("REV_FREVRIVE"));
//				tipo ufficio
				riversamentoNuoviFlussiCBI.setANE_TANETUFF(data.getString("ANE_TANETUFF"));
//				codice uffico
				riversamentoNuoviFlussiCBI.setANE_CANECUFF(data.getString("ANE_CANECUFF"));
				riversamentiNuoviFlussiCBI.add(position, riversamentoNuoviFlussiCBI);
				position++;
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new DaoException(e);
		} finally  {
			//inizio LP PG21XX04 Leak
			//DaoUtil.closeResultSet(data);
			//DaoUtil.closeStatement(callableStatement);
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
		return riversamentiNuoviFlussiCBI;
	}

	public RiversamentoNodo getInfoCBI(RiversamentoNodo riversamentoNodo) throws DaoException {
		ResultSet dati = null;
		RiversamentoNodo risultato = new RiversamentoNodo();
		CallableStatement callableStatement = null;
		try {
//			callableStatement = prepareCall("PYENFSP_CBI");
			callableStatement = prepareCall(Routines.REV_DOELANF.routine());
			callableStatement.setString(1, riversamentoNodo.getREV_COSCCSOC());
			callableStatement.setString(2, riversamentoNodo.getREV_CUTECUTE());
			DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
    		Date data = (Date) riversamentoNodo.getREV_GREVGDAT();
    		String data_format = formatter.format(data);
			callableStatement.setString(3, data_format);
			callableStatement.setString(4, riversamentoNodo.getREV_KANEKANE_BEN());
			callableStatement.setString(5, riversamentoNodo.getREV_FREVTIPO());
			callableStatement.setString(6, riversamentoNodo.getREV_FREVRIVE());
			if (callableStatement.execute()) 
			{
				dati = callableStatement.getResultSet();
			}
			while (dati.next()) {
				risultato.setUTE_CUTECSIA(dati.getString("UTE_CUTECSIA"));
				risultato.setUTE_CUTEIBAN(dati.getString("UTE_CUTEIBAN"));
				risultato.setNAME_SUPPORT(dati.getString("UTE_CUTECSIA")+dati.getString("NAME_SUPPORT"));
				risultato.setREV_IREVIREV(dati.getBigDecimal("REV_IREVIREV"));
				risultato.setBEN_CBENIBAN(dati.getString("BEN_CBENIBAN"));
				risultato.setUTE_DUTEDUTE(dati.getString("UTE_DUTEDUTE"));
				risultato.setUTE_CUTECFIS(dati.getString("UTE_CUTECFIS"));
				risultato.setANE_DANEDENT(dati.getString("ANE_DANEDENT"));
				risultato.setBEN_CBENCFIS(dati.getString("BEN_CBENCFIS"));
				risultato.setREV_COSCCSOC(dati.getString("REV_COSCCSOC"));
				risultato.setREV_CUTECUTE(dati.getString("REV_CUTECUTE"));
				risultato.setREV_GREVGDAT(dati.getDate("REV_GREVGDAT"));
				risultato.setREV_KANEKANE_BEN(riversamentoNodo.getREV_KANEKANE_BEN());
				risultato.setREV_FREVTIPO(riversamentoNodo.getREV_FREVTIPO());
				risultato.setREV_FREVRIVE(riversamentoNodo.getREV_FREVRIVE());
				risultato.setANE_CANECENT(dati.getString("ANE_CANECENT"));
				risultato.setANE_TANETUFF(dati.getString("ANE_TANETUFF"));
				risultato.setANE_CANECUFF(dati.getString("ANE_CANECUFF"));
				risultato.setUTE_DUTEUFTP(dati.getString("UTE_DUTEUFTP"));
				risultato.setUTE_DUTEPFTP(dati.getString("UTE_DUTEPFTP"));
				risultato.setUTE_DUTESFTP(dati.getString("UTE_DUTESFTP"));
				risultato.setUTE_DUTERDIR(dati.getString("UTE_DUTERDIR"));
				risultato.setUTE_FUTEAFTP(dati.getString("UTE_FUTEAFTP"));

				risultato.setUTE_DUTEORDI(dati.getString("UTE_DUTEORDI"));

				//120090
				risultato.setBEN_DBENBENE(dati.getString("BEN_DBENBENE"));
				//fine 120090
			}
//			record = creaRecord(risultato);
		} catch (Exception e) {
			e.printStackTrace();
			throw new DaoException(e);
		} finally  {
			//inizio LP PG21XX04 Leak
			//DaoUtil.closeResultSet(dati);
			//DaoUtil.closeStatement(callableStatement);
			if (dati != null) {
				try {
					dati.close();
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
		return risultato;
	}
	
	
	public void inserisciFlussoProdotto(RiversamentoNodo risultato, String nomeFile, String dataCreazioneForDB, String nomeSupporto, String codiceUnivoco) throws DaoException {
		//inizio LP PG21XX04 Leak
		CallableStatement callableStatement = null;
		//fine LP PG21XX04 Leak
			try {
//				CallableStatement callableStatement_1 = prepareCall("PYREVCBISP_UPDATE");				
				//inizio LP PG21XX04 Leak
				//CallableStatement callableStatement = prepareCall(Routines.REV_DOUPDREVCBI.routine());
				callableStatement = prepareCall(Routines.REV_DOUPDREVCBI.routine());
				//fine LP PG21XX04 Leak
				callableStatement.setString(1, "CBI");
				callableStatement.setString(2, risultato.getREV_COSCCSOC());
				callableStatement.setString(3, risultato.getREV_CUTECUTE());
				callableStatement.setString(4, ""+risultato.getREV_GREVGDAT());
				callableStatement.setString(5, risultato.getREV_KANEKANE_BEN());
				callableStatement.setString(6, risultato.getREV_FREVTIPO());
				callableStatement.setString(7, risultato.getREV_FREVRIVE());
				callableStatement.setString(8, dataCreazioneForDB);
				callableStatement.setString(9, "PC");
				callableStatement.setString(10, nomeSupporto);
				callableStatement.setString(11, codiceUnivoco);
				callableStatement.setString(12, nomeFile);
				callableStatement.setString(13, "");
				String date = "1000-01-01";
				callableStatement.setString(14,date);
				callableStatement.setString(15,"");
				callableStatement.setString(16, "SedaBatchRiveFlussiCBI");
				callableStatement.execute();
				DaoUtil.closeStatement(callableStatement);
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
				throw new DaoException(e);
			} catch (SQLException e) {
				e.printStackTrace();
				throw new DaoException(e);
			} catch (HelperException e) {
				e.printStackTrace();
				throw new DaoException(e);
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

	
	public int selectRiversamentoNodoByCBI(String codiceUnivoco, RiversamentoNodo riversamentoNodo) throws DaoException 
	{
		int risultato;
		//inizio LP PG21XX04 Leak
		CallableStatement callableStatement = null;
		//fine LP PG21XX04 Leak
		try {
//			CallableStatement callableStatement = prepareCall("PYREVSP_SEL_BY_CBI");
			//inizio LP PG21XX04 Leak
			//CallableStatement callableStatement = prepareCall(Routines.REV_SEL_BY_CBI.routine());
			callableStatement = prepareCall(Routines.REV_SEL_BY_CBI.routine());
			//fine LP PG21XX04 Leak
			callableStatement.setString(1, codiceUnivoco);
			callableStatement.registerOutParameter(2, Types.INTEGER);	
			callableStatement.execute();
	   		risultato = callableStatement.getInt(2);
	   		
	   		if (risultato==1)
	   		{
	   			ResultSet res = callableStatement.getResultSet();
	   			res.next();
	   			riversamentoNodo.setREV_COSCCSOC(res.getString("REV_COSCCSOC"));
	   			riversamentoNodo.setSOC_DSOCDSOC(res.getString("SOC_DSOCDSOC"));
	   			riversamentoNodo.setREV_CUTECUTE(res.getString("REV_CUTECUTE"));
	   			riversamentoNodo.setUTE_DUTEDUTE(res.getString("UTE_DUTEDUTE"));
	   			riversamentoNodo.setANE_CANECENT(res.getString("ANE_CANECENT"));
	   			riversamentoNodo.setANE_DANEDENT(res.getString("ANE_DANEDENT"));
	   			riversamentoNodo.setANE_CANECUFF(res.getString("ANE_CANECUFF"));
	   			riversamentoNodo.setANE_DANEDUFF(res.getString("ANE_DANEDUFF"));
	   			riversamentoNodo.setANE_TANETUFF(res.getString("ANE_TANETUFF"));
	   			riversamentoNodo.setREV_FREVTIPO_D(res.getString("REV_FREVTIPO_D"));
	   			riversamentoNodo.setREV_FREVRIVE_D(res.getString("REV_FREVRIVE_D"));
	   			riversamentoNodo.setREV_FREVSTAT_D(res.getString("REV_FREVSTAT_D"));
	   			riversamentoNodo.setREV_GREVGDAT(res.getTimestamp("REV_GREVGDAT"));
	   			res.close();
	   		}
	   		
		} catch (Exception e) {
			e.printStackTrace();
			throw new DaoException(e);
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
	
	
	public int updateFlussoEsitiCBI(String codiceUnivoco,String tipoAnomalia,Date dataSupporto,String nomeSupporto) throws DaoException {
//		boolean operazioneEseguita = false;
		int risultato;
		//inizio LP PG21XX04 Leak
		CallableStatement callableStatement = null;
		//fine LP PG21XX04 Leak
		try {
//			CallableStatement callableStatement = prepareCall("PYCBITB_UPD");
			//inizio LP PG21XX04 Leak
			//CallableStatement callableStatement = prepareCall(Routines.CBI_DOUPDATE.routine());
			callableStatement = prepareCall(Routines.CBI_DOUPDATE.routine());
			//fine LP PG21XX04 Leak
			callableStatement.setString(1, codiceUnivoco);
			callableStatement.setString(2, tipoAnomalia);
			java.sql.Date sqlDate = new java.sql.Date(dataSupporto.getTime());
			callableStatement.setDate(3, sqlDate);
			callableStatement.setString(4, nomeSupporto);
			callableStatement.setString(5, "SedaBatchRiveEsitiCBI");
			callableStatement.registerOutParameter(6, Types.INTEGER);	
			callableStatement.registerOutParameter(7, Types.VARCHAR);	
			callableStatement.execute();
	   		risultato = callableStatement.getInt(6);
//			operazioneEseguita = true;
		} catch (Exception e) {
			e.printStackTrace();
			throw new DaoException(e);
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
//		return operazioneEseguita;
		return risultato;
	}
	
	//inizio LP PG21XX04 Leak
	//private void closeConnection(CallableStatement callableStatement)
	//{
	//	if (callableStatement != null)
	//		DAOHelper.closeIgnoringException(callableStatement);
	//}
	//fine LP PG21XX04 Leak

// DOM
	
	/*
	 		IN I_REV_COSCCSOC CHAR(5), -- codice società
		IN I_REV_CUTECUTE CHAR(5), -- codice utente
		IN I_REV_GREVGDAT CHAR(10), -- codice data riversamento
		IN I_REV_KANEKANE_BEN CHAR(10), -- codice ente beneficiario
		IN I_REV_FREVTIPO CHAR(1), -- codice tipo riversamento
		IN I_REV_FREVRIVE CHAR(1), -- codice tipo rendicondazione
		IN I_REV_FREVRCBI CHAR(1), -- flag CBI 
		IN I_REV_FREVOPE VARCHAR(50), -- codice operatore
		
		OUT O_CODE INTEGER

	 */
	public RiversamentoNodoUpdate aggiornamentoFlagCBI(RiversamentoNodoUpdate dto) throws DaoException {
		int code = 0;
		//inizio LP PG21XX04 Leak
		CallableStatement callableStatement = null;
		//fine LP PG21XX04 Leak
		try {
			
//			CallableStatement callableStatement = prepareCall("PYREVSP_UPD_FCBI");
			//inizio LP PG21XX04 Leak
			//CallableStatement callableStatement = prepareCall(Routines.REV_UPD_FCBI.routine());
			callableStatement = prepareCall(Routines.REV_UPD_FCBI.routine());
			//fine LP PG21XX04 Leak
			callableStatement.setString(1, dto.getCodiceSocieta());
			callableStatement.setString(2, dto.getCodiceUtente());
			callableStatement.setString(3, dto.getDataRiversamento());
			callableStatement.setString(4, dto.getEnteBeneficiario());
			callableStatement.setString(5, dto.getTipoRiversamento());
			callableStatement.setString(6, dto.getTipoRendicontazione());
			callableStatement.setString(7, dto.getFlagCBI());
			callableStatement.setString(8, dto.getOperatore());
			
			callableStatement.registerOutParameter(9, Types.INTEGER);

			callableStatement.execute();
			
			code = callableStatement.getInt(9);

			dto.setNumRows(code);
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
			throw new DaoException(e);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DaoException(e);
		} catch (HelperException e) {
			e.printStackTrace();
			throw new DaoException(e);
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
		return dto;
	}
	
// DOM
	//TODO cambio da ANEIMP_DODDLIST a ANE_ALL_DODDLIST - cambiare nome anche alla filiera dei metodi
	public String getListaUtentiImpositoriXml_DDL
	(
			String codiceSocieta, 
			String siglaProvincia, 
			String codiceEnte,
			String codiceUtente
	) throws DaoException	{
		CallableStatement callableStatement = null;
		ResultSet data = null;
		try {
			callableStatement = prepareCall(Routines.ANE_ALL_DODDLIST.routine());
			callableStatement.setString(1, codiceSocieta == null ? "" :codiceSocieta);
			callableStatement.setString(2, siglaProvincia == null ? "" :siglaProvincia);
			callableStatement.setString(3, codiceEnte == null ? "" :codiceEnte);
			callableStatement.setString(4, codiceUtente == null ? "" :codiceUtente);
			callableStatement.registerOutParameter(5, Types.VARCHAR);
			if(callableStatement.execute()) {
				data = callableStatement.getResultSet();
				loadWebRowSet(data);
				return getWebRowSetXml();
			}
			return null;
		}
		catch (Exception e) {
			throw new DaoException(e);
		}
		finally {
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
	
	@SuppressWarnings("unchecked")
	public AvanzamentoStato avanzamentoStato(AvanzamentoStato dto) throws DaoException {
		//inizio LP PG21XX04 Leak
		CallableStatement callableStatement = null;
		ResultSet risultato = null;
		//fine LP PG21XX04 Leak
		try {
			
//			CallableStatement callableStatement = prepareCall("PYREVSP_MEGAB");
			//inizio LP PG21XX04 Leak
			//CallableStatement callableStatement = prepareCall(Routines.REV_MEGAB.routine());
			callableStatement = prepareCall(Routines.REV_MEGAB.routine());
			//fine LP PG21XX04 Leak

			callableStatement.setString(1, dto.getCodiceSocieta());
			callableStatement.setString(2, dto.getCodiceBelfiore());
			callableStatement.setString(3, dto.getCodiceUtente());
			callableStatement.setString(4, dto.getDataRiversamentoDa());
			callableStatement.setString(5, dto.getDataRiversamentoA());

			callableStatement.setString(6, dto.getEnteBeneficiario());
			callableStatement.setString(7, dto.getTipoRendicontazione());
			callableStatement.setString(8, dto.getTipoRiversamento());
			callableStatement.setString(9, dto.getStatoRiversamento());

			callableStatement.setString(10, dto.getOrder());
			callableStatement.setInt(11, dto.getPageInfo().getRowsPerPage());
			callableStatement.setInt(12, dto.getPageInfo().getPageNumber());

			callableStatement.setString(13, dto.getOperatore());
			callableStatement.setString(14, dto.getFlagTipoRicerca());
			callableStatement.setInt(15, dto.getFlagAVStato());

			callableStatement.registerOutParameter(16, Types.INTEGER);
			callableStatement.registerOutParameter(17, Types.VARCHAR);
			callableStatement.registerOutParameter(18, Types.INTEGER);
			callableStatement.registerOutParameter(19, Types.INTEGER);
			callableStatement.registerOutParameter(20, Types.INTEGER);

			callableStatement.registerOutParameter(21, Types.INTEGER);
			callableStatement.registerOutParameter(22, Types.DECIMAL);

			ArrayList messaggiLog = dto.getLog();
			
			if (callableStatement.execute()) 
			{
				//inizio LP PG21XX04 Leak
				//ResultSet risultato = callableStatement.getResultSet();
				risultato = callableStatement.getResultSet();
				//fine LP PG21XX04 Leak
				while (risultato.next())
				{
					messaggiLog.add(risultato.getString(1));
				}
			}
			
			dto.setCode(callableStatement.getInt(16));
			dto.setMessage(callableStatement.getString(17));
			dto.setRivAnalizzati(callableStatement.getInt(18));
			dto.setRivAvanzati(callableStatement.getInt(19));
			dto.setRivNAvanzati(callableStatement.getInt(20));

			dto.setRivPrenotati(callableStatement.getInt(21));
			dto.setRivTotPrenotato(callableStatement.getBigDecimal(22));
			
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
			throw new DaoException(e);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DaoException(e);
		} catch (HelperException e) {
			e.printStackTrace();
			throw new DaoException(e);
		}
		//inizio LP PG21XX04 Leak
		finally {
			if (risultato != null) {
				try {
					risultato.close();
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
		return dto;
	}

	public ArrayList<RiversamentoNodoReport> elaboraReportCommissioni(String dataIn, String dataFin) throws DaoException {
		
		ArrayList<RiversamentoNodoReport> arrayReport = new ArrayList<RiversamentoNodoReport>();
		CallableStatement callableStatement = null;
		ResultSet data = null;
		try {
			callableStatement = prepareCall(Routines.REV_REPORT_CSV.routine());
			DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
			java.sql.Date data_format = null;
			data_format = new java.sql.Date(formatter.parse(dataIn).getTime());
    		callableStatement.setDate(1, data_format);
    		java.sql.Date data_format_fin = null;
			data_format_fin = new java.sql.Date(formatter.parse(dataFin).getTime());
			callableStatement.setDate(2, data_format_fin);
			if(callableStatement.execute()) {
				data = callableStatement.getResultSet();
			}
			int position = 0;
			while (data.next()) {
				RiversamentoNodoReport report = new RiversamentoNodoReport();
//				codice società
				report.setCodiceSocieta(data.getString(1));
				report.setCodiceUtente(data.getString(2));
				report.setDataRiversamento(data.getDate(3));
				report.setEnteBeneficiario(data.getString(4));
				report.setTipoUfficioBeneficiario(data.getString(5));
				report.setCodiceUfficioBeneficiario(data.getString(6));
				report.setDescrEnteBeneficiario(data.getString(7));
				report.setDescrUfficioBeneficiario(data.getString(8));
				
				report.setEnteImpositore(data.getString(9));
				report.setTipoUfficioImpositore(data.getString(10));
				report.setCodiceUfficioImpositore(data.getString(11));
				report.setDescrEnteImpositore(data.getString(12));
				report.setDescrUfficioImpositore(data.getString(13));
				report.setTipologiaServizio(data.getString(14));
				report.setDenominazioneAnagrafica(data.getString(15));
				report.setIndirizzo(data.getString(16));
				report.setGatewayPagamento(data.getString(17));
				report.setDataPagamento(data.getTimestamp(18));
				report.setCodiceFiscale(data.getString(19));
				report.setBollettino(data.getString(20));
				report.setStrumentoPagamento(data.getString(21));
				report.setDocumento(data.getString(22));
				report.setVerbale(data.getString(23));
				report.setDataVerbale(data.getTimestamp(24));
				report.setTarga(data.getString(25));
				report.setCategoriaVeicolo(data.getString(26));
				report.setScadenza(data.getTimestamp(27));
				report.setImportoPagato(data.getBigDecimal(28));
				report.setCommissioneGateway(data.getBigDecimal(29));
				report.setSpeseNotifica(data.getBigDecimal(30));
				report.setCommissioniGestore(data.getBigDecimal(31));
				report.setImportoRiversato(data.getBigDecimal(32));
				report.setChiaveTransazione(data.getString(33));
				report.setAnnoRiferimento(data.getString(34));
				report.setCanaleIncasso(data.getString(35));
				report.setCommissioniDaRecuperare(data.getBigDecimal(36));
				report.setCausale(data.getString(37));
				
				arrayReport.add(position, report);
				position++;
			}
		}
		catch (Exception e) 
		{
			e.printStackTrace();
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
		return arrayReport;
	}
	
}