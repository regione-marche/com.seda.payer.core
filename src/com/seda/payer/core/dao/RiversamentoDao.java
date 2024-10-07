//da com.seda.payer.core.dao;
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
import com.seda.payer.core.bean.Riversamento;
import com.seda.payer.core.bean.RiversamentoPage;
import com.seda.payer.core.bean.RiversamentoDetailPage;
import com.seda.payer.core.bean.RiversamentoReport;
import com.seda.payer.core.bean.RiversamentoUpdate;
import com.seda.payer.core.exception.DaoException;
import com.seda.payer.core.handler.BaseDaoHandler;
import com.seda.payer.core.messages.Messages;

public class RiversamentoDao extends BaseDaoHandler {

	/*
	private String dataCreazioneForDB;
	private String nomeSupporto;
	private String codiceUnivoco;
	*/
	public RiversamentoDao(Connection connection, String schema) {
		super(connection, schema);
	}

	public RiversamentoPage getRiversamenti(RiversamentoPage dto, String ordine) throws DaoException {
		
		return getRiversamenti(dto, ordine, 0, 0);
	}

	public RiversamentoPage getRiversamenti(RiversamentoPage dto, String ordine, int rowsPerPage, int pageNumber) throws DaoException {
		if (rowsPerPage <= 0)
			throw new IllegalArgumentException(Messages.INVALID_PARAMETER.format("rowsPerPage"));
		if (pageNumber <= 0)
			throw new IllegalArgumentException(Messages.INVALID_PARAMETER.format("pageNumber"));
		dto = getLista(dto, ordine, rowsPerPage, pageNumber);
		dto = getStatistiche(dto);
		return dto;
	}

	public RiversamentoDetailPage getDettaglioRiv(RiversamentoDetailPage dto, String ordine) throws DaoException {
		return getDettaglioRiv(dto, ordine, 0, 0);
	}

	public RiversamentoDetailPage getDettaglioRiv(RiversamentoDetailPage dto, String ordine, int rowsPerPage, int pageNumber) throws DaoException {
		if (rowsPerPage <= 0)
			throw new IllegalArgumentException(Messages.INVALID_PARAMETER.format("rowsPerPage"));
		if (pageNumber <= 0)
			throw new IllegalArgumentException(Messages.INVALID_PARAMETER.format("pageNumber"));
	  	dto = getListaDettagli(dto, ordine, rowsPerPage, pageNumber);
		dto = getStatisticheDettagli(dto);
		return dto;
	}
	
/*	
	public ArrayList elaboraNuoviRiversamenti() throws DaoException 
	{
		CallableStatement callableStatement = null;
		ArrayList messaggiLog = null;
		
		try	{

			//lista riversamenti
			//callableStatement = prepareCall("PYALGREVSP_CREAREVSP");
			callableStatement = prepareCall(Routines.REV_ALGRIV.routine());
			callableStatement.registerOutParameter(1, Types.INTEGER);

            messaggiLog = new ArrayList();
			if (callableStatement.execute()) 
			{
				//commit();
				ResultSet risultato = callableStatement.getResultSet();
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
			closeConnection(callableStatement);
		}
		return messaggiLog;
	}
*/	

// NUOVO metodo parametrizzato
	@SuppressWarnings("unchecked")
	public ArrayList elaboraNuoviRiversamenti(String flagTranRiversabili, String elencoFestivita, String separatoreElencoFest) throws DaoException 
	{
		CallableStatement callableStatement = null;
		ArrayList messaggiLog = null;
		//inizio LP PG21XX04 Leak
		ResultSet risultato = null;
		//fine LP PG21XX04 Leak
		try	{

			//lista riversamenti
			//callableStatement = prepareCall("PYALGREVSP_CREAREVSP");
			callableStatement = prepareCall(Routines.REV_ALGRIV2.routine());
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
	public ArrayList certificazioneRiversamenti() throws DaoException 
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

	public RiversamentoPage getLista(RiversamentoPage dto, String ordine, int rowsPerPage, int pageNumber) throws DaoException {
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
				dto.setListXml(getWebRowSetXml(RiversamentoDao.IDX_DOLIST_LISTA));

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

	
	public RiversamentoPage getStatistiche(RiversamentoPage dto) throws DaoException {
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

	
	public RiversamentoDetailPage getListaDettagli(RiversamentoDetailPage dto, String ordine, int rowsPerPage, int pageNumber) throws DaoException {
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

	
	public RiversamentoDetailPage getStatisticheDettagli(RiversamentoDetailPage dto) throws DaoException {
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
	
	
	public RiversamentoUpdate aggiornamentoStatoRiversamento(RiversamentoUpdate dto) throws DaoException {
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
	public ArrayList getRiversamentiDaStampare() throws DaoException {
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
				Riversamento riversamento = new Riversamento();
//				codice società
				riversamento.setREV_COSCCSOC(data.getString("REV_COSCCSOC"));
//				codice utente
				riversamento.setREV_CUTECUTE(data.getString("REV_CUTECUTE"));
//				data riversamento
				riversamento.setREV_GREVGDAT(data.getTimestamp("REV_GREVGDAT"));
//				codice ente beneficiario
				riversamento.setREV_KANEKANE_BEN(data.getString("REV_KANEKANE_BEN"));
				riversamento.setREV_FREVTIPO(data.getString("REV_FREVTIPO"));
				riversamento.setREV_FREVRIVE(data.getString("REV_FREVRIVE"));
				riversamento.setREV_DREVDOWN(data.getString("REV_DREVDOWN"));
				riversamento.setREV_DREVREPO(data.getString("REV_DREVREPO"));
//				tipo ufficio
				riversamento.setANE_CANECENT(data.getString("ANE_CANECENT"));
				//				tipo ufficio
				riversamento.setANE_TANETUFF(data.getString("ANE_TANETUFF"));
//				codice uffico
				riversamento.setANE_CANECUFF(data.getString("ANE_CANECUFF"));
				riversamento.setBEN_TBENFCSV(data.getString("BEN_TBENFCSV"));
				
				riversamenti.add(position, riversamento);
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

	
	public ResultSet getRiversamentoDetailDC(String rev_cosccsoc, String rev_cutecute, 
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

	public Riversamento getRiversamentoDetail(String rev_cosccsoc, String rev_cutecute, 
			String rev_grevgdat, String rev_kanekane_ben, 
            String rev_frevtipo, String rev_frevrive) throws DaoException {

		CallableStatement callableStatement = null;
		//inizio LP PG21XX04 Leak
		ResultSet data = null;
		//fine LP PG21XX04 Leak
		Riversamento riversamento = null;
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
						riversamento = new Riversamento();
						riversamento.setREV_IREVIREV(data.getBigDecimal("REV_IREVIREV"));
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
		return riversamento;
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
	
	public ResultSet getRiversamentoCSV(String rev_cosccsoc, String rev_cutecute, 
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
	
	
	public Riversamento getRiversamento(String codiceSocieta, String codiceUtente,String dataRiversamento, String codiceBeneficiario, String tipoRendicondazione, String strumentoRendicondazione) throws DaoException {
		Riversamento riversamento = new Riversamento();
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
				riversamento.setREV_COSCCSOC(data.getString("REV_COSCCSOC"));
//				codice utente
				riversamento.setREV_CUTECUTE(data.getString("REV_CUTECUTE"));
//				data riversamento
				riversamento.setREV_GREVGDAT(data.getTimestamp("REV_GREVGDAT"));
//				codice ente beneficiario
				riversamento.setREV_KANEKANE_BEN(data.getString("REV_KANEKANE_BEN"));
				riversamento.setREV_FREVTIPO(data.getString("REV_FREVTIPO"));
				riversamento.setREV_FREVRIVE(data.getString("REV_FREVRIVE"));
 
				riversamento.setREV_DREVDOWN(data.getString("REV_DREVDOWN"));
				riversamento.setREV_DREVREPO(data.getString("REV_DREVREPO"));
//				tipo ufficio
				riversamento.setANE_CANECENT(data.getString("ANE_CANECENT"));
//				tipo ufficio
				riversamento.setANE_TANETUFF(data.getString("ANE_TANETUFF"));
//				codice ufficio
				riversamento.setANE_CANECUFF(data.getString("ANE_CANECUFF"));

//              file CBI
				riversamento.setCBI_DCBIFILE(data.getString("CBI_DCBIFILE"));
				
				riversamento.setBEN_TBENFCSV(data.getString("BEN_TBENFCSV"));
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
		return riversamento;
	}
	
	
	public void updateRiversamento(String tipo, String fileName,Riversamento riversamento) throws DaoException{
		CallableStatement callableStatement = null;
		try {
//			callableStatement = prepareCall("PYREVSP_UPDFILE");
			callableStatement = prepareCall(Routines.REV_DOUPDFILE.routine());
			callableStatement.setString(1, riversamento.getREV_COSCCSOC());
			callableStatement.setString(2, riversamento.getREV_CUTECUTE());
			DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
    		Date data = (Date) riversamento.getREV_GREVGDAT();
    		String data_format = formatter.format(data);
			callableStatement.setString(3, data_format);
			callableStatement.setString(4, riversamento.getREV_KANEKANE_BEN());
			callableStatement.setString(5, riversamento.getREV_FREVTIPO());
			callableStatement.setString(6, riversamento.getREV_FREVRIVE());
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
				Riversamento riversamentoNuoviFlussiCBI = new Riversamento();
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
	
/*	
	public void doSaveCBI(String path,String nomeFile, Riversamento riversamento) throws DaoException {
		ArrayList record = new ArrayList();
		ResultSet dati = null;
		Riversamento risultato = new Riversamento();
		CallableStatement callableStatement = null;
		try {
//			callableStatement = prepareCall("PYENFSP_CBI");
			callableStatement = prepareCall(Routines.REV_DOELANF.routine());
			callableStatement.setString(1, riversamento.getREV_COSCCSOC());
			callableStatement.setString(2, riversamento.getREV_CUTECUTE());
			DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
    		Date data = (Date) riversamento.getREV_GREVGDAT();
    		String data_format = formatter.format(data);
			callableStatement.setString(3, data_format);
			callableStatement.setString(4, riversamento.getREV_KANEKANE_BEN());
			callableStatement.setString(5, riversamento.getREV_FREVTIPO());
			callableStatement.setString(6, riversamento.getREV_FREVRIVE());
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
				risultato.setREV_KANEKANE_BEN(riversamento.getREV_KANEKANE_BEN());
				risultato.setREV_FREVTIPO(riversamento.getREV_FREVTIPO());
				risultato.setREV_FREVRIVE(riversamento.getREV_FREVRIVE());
				risultato.setANE_CANECENT(dati.getString("ANE_CANECENT"));
				risultato.setANE_TANETUFF(dati.getString("ANE_TANETUFF"));
				risultato.setANE_CANECUFF(dati.getString("ANE_CANECUFF"));
				risultato.setUTE_DUTEUFTP(dati.getString("UTE_DUTEUFTP"));
				risultato.setUTE_DUTEPFTP(dati.getString("UTE_DUTEPFTP"));
				risultato.setUTE_DUTESFTP(dati.getString("UTE_DUTESFTP"));
				risultato.setUTE_DUTERDIR(dati.getString("UTE_DUTERDIR"));
				risultato.setUTE_FUTEAFTP(dati.getString("UTE_FUTEAFTP"));
			}
			record = creaRecord(risultato);
			creaFileFlussoCBI(path,nomeFile,record);
			inviaFlussoCBI(risultato, path,nomeFile);
			inserisciFlussoProdotto(risultato,nomeFile);
		} catch (Exception e) {
			e.printStackTrace();
			throw new DaoException(e);
		} finally  {
			DaoUtil.closeResultSet(dati);
			DaoUtil.closeStatement(callableStatement);
		}
	}
*/	

	public Riversamento getInfoCBI(Riversamento riversamento) throws DaoException {
		ResultSet dati = null;
		Riversamento risultato = new Riversamento();
		CallableStatement callableStatement = null;
		try {
//			callableStatement = prepareCall("PYENFSP_CBI");
			callableStatement = prepareCall(Routines.REV_DOELANF.routine());
			callableStatement.setString(1, riversamento.getREV_COSCCSOC());
			callableStatement.setString(2, riversamento.getREV_CUTECUTE());
			DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
    		Date data = (Date) riversamento.getREV_GREVGDAT();
    		String data_format = formatter.format(data);
			callableStatement.setString(3, data_format);
			callableStatement.setString(4, riversamento.getREV_KANEKANE_BEN());
			callableStatement.setString(5, riversamento.getREV_FREVTIPO());
			callableStatement.setString(6, riversamento.getREV_FREVRIVE());
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
				risultato.setREV_KANEKANE_BEN(riversamento.getREV_KANEKANE_BEN());
				risultato.setREV_FREVTIPO(riversamento.getREV_FREVTIPO());
				risultato.setREV_FREVRIVE(riversamento.getREV_FREVRIVE());
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
	
	
	public void inserisciFlussoProdotto(Riversamento risultato, String nomeFile, String dataCreazioneForDB, String nomeSupporto, String codiceUnivoco) throws DaoException {
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
				//inizio LP PG21XX04 Leak
				//DaoUtil.closeStatement(callableStatement);
				//fine LP PG21XX04 Leak
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

	
	public int selectRiversamentoByCBI(String codiceUnivoco, Riversamento riversamento) throws DaoException 
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
	   			riversamento.setREV_COSCCSOC(res.getString("REV_COSCCSOC"));
	   			riversamento.setSOC_DSOCDSOC(res.getString("SOC_DSOCDSOC"));
	   			riversamento.setREV_CUTECUTE(res.getString("REV_CUTECUTE"));
	   			riversamento.setUTE_DUTEDUTE(res.getString("UTE_DUTEDUTE"));
	   			riversamento.setANE_CANECENT(res.getString("ANE_CANECENT"));
	   			riversamento.setANE_DANEDENT(res.getString("ANE_DANEDENT"));
	   			riversamento.setANE_CANECUFF(res.getString("ANE_CANECUFF"));
	   			riversamento.setANE_DANEDUFF(res.getString("ANE_DANEDUFF"));
	   			riversamento.setANE_TANETUFF(res.getString("ANE_TANETUFF"));
	   			riversamento.setREV_FREVTIPO_D(res.getString("REV_FREVTIPO_D"));
	   			riversamento.setREV_FREVRIVE_D(res.getString("REV_FREVRIVE_D"));
	   			riversamento.setREV_FREVSTAT_D(res.getString("REV_FREVSTAT_D"));
	   			riversamento.setREV_GREVGDAT(res.getTimestamp("REV_GREVGDAT"));
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

	
	
	
	
	
	/*	
	
	
	public boolean inviaFlussoCBI(Riversamento risultato,String path,String fileName) throws DaoException 
	{
		boolean ret = false;
		try {
			String szFilename = path + fileName;
			if (risultato.getUTE_FUTEAFTP().equals("Y"))
				ret = sendFTP(	risultato.getUTE_DUTESFTP(), 
								risultato.getUTE_DUTEUFTP(), 
								risultato.getUTE_DUTEPFTP(), 
								risultato.getUTE_DUTERDIR(), 
								szFilename, fileName);
		} catch (Exception e) {
			e.printStackTrace();
//			throw new DaoException(e);
		}
		
		return ret; 
	}

	
	private boolean sendFTP(String ftpServer, String ftpUser, String ftpPassword,
			 String ftpFolder, String outFilename,String szRemoteFileName) throws IOException{
		boolean sendFtpOk = true;
		FTPClient ftpClient = new FTPClient();
		ftpClient.connect(ftpServer);
		
		if (ftpClient.login(ftpUser,ftpPassword))
		{
			ftpClient.setFileType(FTPClient.BINARY_FILE_TYPE);
			
			ftpClient.cwd(ftpFolder);
			
			if (FTPReply.isPositiveCompletion(ftpClient.getReplyCode()))
			{
				File file = new File(outFilename);
				FileInputStream fis = new FileInputStream(file);
				ftpClient.pasv();
				if (FTPReply.isPositiveCompletion(ftpClient.getReplyCode()))
				{
					if(!ftpClient.storeFile(szRemoteFileName, fis))
					{
						sendFtpOk = false;
					}
				}
				else
					sendFtpOk = false;
				fis.close();
			}
			else
				sendFtpOk = false;
		}
		else
			sendFtpOk = false;
		ftpClient.logout();
		return sendFtpOk;
	}
	
	

	private void creaFileFlussoCBI(String path, String nomeFile, ArrayList record) throws DaoException {
		String filePath = path + nomeFile;
		FileOutputStream file;
		try {
			file = new FileOutputStream(filePath);
			PrintStream output = new PrintStream(file);
			for (int i=0;i<record.size();i++) {
				String record_flusso = (String) record.get(i);
				output.println(record_flusso);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			throw new DaoException(e);
		}
	}

	private ArrayList creaRecord(Riversamento risultato) throws SQLException {
		ArrayList record = new ArrayList();
//		RECORD PC
		String fillerPC1 = "",fillerPC2 = "";
		String tipoRecord = "PC";
		String mittente = risultato.getUTE_CUTECSIA();
		String ricevente = risultato.getUTE_CUTEIBAN().substring(5, 10);
		
		DateFormat dataFormat = new SimpleDateFormat("ddMMyy");
		DateFormat dataFormat_for_DB = new SimpleDateFormat("yyyy-MM-dd");
		Date currentData = new Date();
		String dataCreazione = dataFormat.format(currentData);
		dataCreazioneForDB = dataFormat_for_DB.format(currentData);
		nomeSupporto = risultato.getNAME_SUPPORT();
		String codiceDivisa = "E";
		fillerPC1 = fill("",74);
		fillerPC2 = fill("",6);
		String recordPC = " " + tipoRecord + mittente + ricevente + dataCreazione + nomeSupporto + fillerPC1 + codiceDivisa + fillerPC2;
		record.add(recordPC);
		
//		RECORD 10
		String filler101 = "",filler102 = "";
		tipoRecord = "10";
		String numeroProgressivo = "0000001";
		String causale = "48000";
		String importo = fill(""+(risultato.getREV_IREVIREV().multiply(new BigDecimal("100"))).intValue(),13);
		String segno = "+";
		String CIN = risultato.getUTE_CUTEIBAN().substring(0,1);
		String codiceBanca = risultato.getUTE_CUTEIBAN().substring(1,6);
		String CAB = risultato.getUTE_CUTEIBAN().substring(6,11);
		String conto = fill(risultato.getUTE_CUTEIBAN().substring(11),12);
		// codice divisa come record PC
		filler101 = fill("",18);
		filler102 = fill("",50);
		String record10 = " " + tipoRecord + numeroProgressivo + filler101 + causale + importo + segno + codiceBanca + CAB + conto + filler102 + codiceDivisa;
		record.add(record10);
		
//		RECORD 16
		String filler16 = "";
		tipoRecord = "16";
		// numero progressivo = tipo 10
		String IBAN_U = risultato.getUTE_CUTEIBAN();
		String paese_u = IBAN_U.substring(0,2);
		String check_digit_u = IBAN_U.substring(2,4);
		String CIN_U = IBAN_U.substring(4,5);
		String codiceBanca_u = IBAN_U.substring(5,10); //ABI
		String CAB_U = IBAN_U.substring(10,15);
		String conto_u = fill(IBAN_U.substring(15),12);
		filler16 = fill("",83);
		String record16 = " " + tipoRecord + numeroProgressivo + paese_u + check_digit_u + CIN_U + codiceBanca_u + CAB_U + conto_u + filler16;
		record.add(record16);
		
//		RECORD 17
		String filler17;
		tipoRecord = "17";
		// numero progressivo = tipo 10
		String IBAN_B = risultato.getBEN_CBENIBAN();
		String paese_b = IBAN_B.substring(0,2);
		String check_digit_b = IBAN_B.substring(2,4);
		String CIN_B = IBAN_B.substring(4,5);
		String codiceBanca_b = IBAN_B.substring(5,10); //ABI
		String CAB_B = IBAN_B.substring(10,15);
		String conto_b = fill(IBAN_B.substring(15),12);
		filler17 = fill("",83);
		String record17 = " " + tipoRecord + numeroProgressivo + paese_b + check_digit_b + CIN_B + codiceBanca_b + CAB_B + conto_b + filler17;
		record.add(record17);
		
//		RECORD 20
		String filler201 = "", filler202 = "";
		tipoRecord = "20";
		// numero progressivo = tipo 10
		String denominazioneAzienda = fill(risultato.getUTE_DUTEDUTE(),30);
		String codiceFiscale = risultato.getUTE_CUTECFIS();
		filler201 = fill("",60);
		filler202 = fill("",4);
		String record20 = " " + tipoRecord + numeroProgressivo + denominazioneAzienda + filler201 + codiceFiscale + filler202;
		record.add(record20);
		
//		RECORD 30
		String filler30 = "";
		tipoRecord = "30";
		// numero progressivo = tipo 10
		String descrBeneficiario = risultato.getANE_DANEDENT();
		String segmento1 = "", segmento2 = "", segmento3 = "";
		if (descrBeneficiario.length()>30) {
			segmento1 = descrBeneficiario.substring(0, 30);
			if (descrBeneficiario.length()>60 ) {
				segmento2 = descrBeneficiario.substring(30, 60);
				if (descrBeneficiario.length()>90)
					segmento3 = descrBeneficiario.substring(60, 90);
				else {
					segmento3 = fill(descrBeneficiario.substring(60),30);
				}
			} else {
				segmento2 = fill(descrBeneficiario.substring(30),30);
				segmento3 = fill("",30);
			}
		} else {
			segmento1 = fill(descrBeneficiario,30);
			segmento2 = fill("",30);
			segmento3 = fill("",30);
		}
		
		String check_digit = risultato.getBEN_CBENCFIS();
		filler30 = fill("",4);
		String record30 = " " + tipoRecord + numeroProgressivo + segmento1 + segmento2 + segmento3 + check_digit + filler30;
		record.add(record30);
		
//		RECORD 50
		String filler50 = "";
		tipoRecord = "50";
		DateFormat dataFormat_2 = new SimpleDateFormat("dd/MM/yyyy");
		Date rev_grevgdat = risultato.getREV_GREVGDAT();
		String data_riversamento = dataFormat_2.format(rev_grevgdat);
		String ente_beneficiario = fill(risultato.getANE_CANECENT(),5) + "/" + fill(risultato.getANE_TANETUFF(),1) + "/" + fill(risultato.getANE_CANECUFF(),6); 
		String dicitura = "Bonifico per riversamento del " + data_riversamento + " relativo all'ente beneficiario " + ente_beneficiario;
		// numero progressivo = tipo 10
		if (dicitura.length()>30) {
			segmento1 = dicitura.substring(0, 30);
			if (dicitura.length()>60 ) {
				segmento2 = dicitura.substring(30, 60);
				if (descrBeneficiario.length()>90)
					segmento3 = dicitura.substring(60, 90);
				else {
					segmento3 = fill(dicitura.substring(60),30);
				}
			} else {
				segmento2 = fill(dicitura.substring(30),30);
				segmento3 = fill("",30);
			}
		} else {
			segmento1 = fill(dicitura,30);
			segmento2 = fill("",30);
			segmento3 = fill("",30);
		}
		filler50 = fill("",20);
		String record50 = " " + tipoRecord + numeroProgressivo + segmento1 + segmento2 + segmento3 + filler50;
		record.add(record50);
		
//		RECORD 70
		String filler701 = "",filler702 = "";
		tipoRecord = "70";
		// numero progressivo = tipo 10
		codiceUnivoco = risultato.getREV_COSCCSOC() + risultato.getREV_CUTECUTE() + risultato.getNAME_SUPPORT();
		filler701 = fill("",60);
		filler702 = fill("",20);
		String record70 = " " + tipoRecord + numeroProgressivo + filler701 + codiceUnivoco + filler702;
		record.add(record70);
		
//		RECORD EF
		String fillerEF1 = "",fillerEF2 = "", fillerEF3 = "";
		tipoRecord = "EF";
		String numeroDisposizioni = "0000001";
		String totaleImportoNegativo = "000000000000000";
		String numeroRecord = "0000009";
		fillerEF1 = fill("",6);
		fillerEF2 = fill("",24);
		String recordEF = " " + tipoRecord + mittente + ricevente + dataCreazione + nomeSupporto + fillerEF1 + numeroDisposizioni + totaleImportoNegativo + fill(importo,15) + numeroRecord + fillerEF2 + codiceDivisa;
		record.add(recordEF);
		
		return record;
	}

	private String fill(String stringa, int i) {
		if (i<stringa.length()) 
			return stringa.substring(0,i);
		else if (i>stringa.length()){
			for (int j=stringa.length();j<i;j++)
				stringa = stringa.concat(" ");
		}
		return stringa;
	}

	private String empty(String contenuto) {
		if ( contenuto == null ) {
			return "";
		} else {
			return contenuto;
		}
	}
*/	
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
	public RiversamentoUpdate aggiornamentoFlagCBI(RiversamentoUpdate dto) throws DaoException {
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

	public ArrayList<RiversamentoReport> elaboraReportCommissioni(String dataIn, String dataFin) throws DaoException {
		
		ArrayList<RiversamentoReport> arrayReport = new ArrayList<RiversamentoReport>();
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
				RiversamentoReport report = new RiversamentoReport();
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