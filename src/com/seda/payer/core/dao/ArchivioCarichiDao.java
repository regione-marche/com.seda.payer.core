package com.seda.payer.core.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import com.seda.data.helper.HelperException;
import com.seda.payer.core.bean.ArchivioCarichiAnagrafica;
import com.seda.payer.core.bean.ArchivioCarichiCoda;
import com.seda.payer.core.bean.ArchivioCarichiDocumento;
import com.seda.payer.core.bean.ArchivioCarichiLogFlussi;
import com.seda.payer.core.bean.ArchivioCarichiMovimento;
import com.seda.payer.core.bean.ArchivioCarichiRuolo;
import com.seda.payer.core.bean.ArchivioCarichiScadenza;
import com.seda.payer.core.bean.ArchivioCarichiTesta;
import com.seda.payer.core.bean.ArchivioCarichiTributo;
import com.seda.payer.core.bean.DatiFlussoIO;
import com.seda.payer.core.bean.DatiMailGeos;
import com.seda.payer.core.bean.DettaglioFlussoOttico;
import com.seda.payer.core.exception.DaoException;
import com.seda.payer.core.handler.BaseDaoHandler;

public class ArchivioCarichiDao extends BaseDaoHandler {
	
	public ArchivioCarichiDao(Connection connection, String schema) {
		super(connection, schema);
	}

	//PYEH1SP_SE2
	//inizio LP 20240828 - PGNTACWS-22
	public ArchivioCarichiDocumento getDocumento(ArchivioCarichiDocumento dto) throws DaoException {
		return getDocumentoTail(true, dto);
	}

	public ArchivioCarichiDocumento getDocumentoTail(boolean bFlagUpdateAutocommit, ArchivioCarichiDocumento dto) throws DaoException {
	//fine LP 20240828 - PGNTACWS-22
		CallableStatement callableStatement = null;
		//inizio LP PG21XX04 Leak
		ResultSet rs = null;
		//fine LP PG21XX04 Leak
		try	{		
			//inizio LP 20240828 - PGNTACWS-22
			//callableStatement = prepareCall(Routines.PYEH1SP_SE2.routine());
			callableStatement = prepareCall(bFlagUpdateAutocommit, Routines.PYEH1SP_SE2.routine());
			//fine LP 20240828 - PGNTACWS-22
			callableStatement.setString(1, dto.getCodiceUtente());
			callableStatement.setString(2, dto.getTipoServizio());
			callableStatement.setString(3, dto.getCodiceEnte());			
			callableStatement.setString(4, dto.getTipoUfficio());
			callableStatement.setString(5, dto.getCodiceUfficio());
			callableStatement.setString(6, dto.getImpostaServizio());
			callableStatement.setString(7, dto.getNumeroDocumento());
			/* we execute procedure */
			if (callableStatement.execute()) 
			{
				//inizio LP PG21XX04 Leak
				//ResultSet rs = callableStatement.getResultSet();
				rs = callableStatement.getResultSet();
				//fine LP PG21XX04 Leak
				if(rs.next()){
					dto.setProgressivoFlusso(rs.getLong(1));
					dto.setTipoRecord(rs.getString(2));
					dto.setDataCreazioneFlusso(java.sql.Date.valueOf(rs.getString(4)));
					dto.setCodiceFiscale(rs.getString(11));
					dto.setTipoDocumentoRuoli(rs.getString(12));
					dto.setFlagRiforma(rs.getString(13));
					dto.setAnnoEmissione(rs.getString(14));
					dto.setNumeroEmissione(rs.getString(15));
					dto.setIdCartellaMinisteriale(rs.getString(16));
					dto.setFlagRendicontato(rs.getString(17));
					dto.setDataNotifica(java.sql.Date.valueOf(rs.getString(18)));
					dto.setTipoDocumentoEntrate(rs.getString(19));
					dto.setNumeroBollettinoPagoPA(rs.getString(20));
					dto.setImpBollettinoTotaleDocumento(rs.getBigDecimal(21));
					dto.setCodiceEnteEntrate(rs.getString(22));
					dto.setFlagSospensione(rs.getString(23));
					dto.setFlagProcEsecutive(rs.getString(24));
					dto.setFlagCartellaInps(rs.getString(25));
					dto.setFlagCartellaMaggiorRateazione(rs.getString(26));
					dto.setCodiceTipologiaServizio(rs.getString(27));
					dto.setTombstoned(rs.getString(28));
					dto.setIbanAccredito(rs.getString(29));
					dto.setFlagFatturazioneElettronica(rs.getString(30));
					dto.setIdentificativoUnivocoVersamento(rs.getString(31));
					dto.setTassonomia(rs.getString(32)); //PG200360 LP
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
			//closeCallableStatement(callableStatement);
			if(rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(callableStatement != null) {
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
	
	//PYEH8SP_SE2
	public ArchivioCarichiAnagrafica getAnagrafica(ArchivioCarichiAnagrafica dto) throws DaoException {
		CallableStatement callableStatement = null;
		//inizio LP PG21XX04 Leak
		ResultSet rs = null;
		//fine LP PG21XX04 Leak
		try	{		
			callableStatement = prepareCall(Routines.PYEH8SP_SE2.routine());
			callableStatement.setString(1, dto.getCodiceUtente());
			callableStatement.setString(2, dto.getTipoServizio());
			callableStatement.setString(3, dto.getCodiceEnte());			
			callableStatement.setString(4, dto.getTipoUfficio());
			callableStatement.setString(5, dto.getCodiceUfficio());
			callableStatement.setString(6, dto.getImpostaServizio());
			callableStatement.setString(7, dto.getCodiceFiscale());
			/* we execute procedure */
			if (callableStatement.execute()) 
			{
				//inizio LP PG21XX04 Leak
				//ResultSet rs = callableStatement.getResultSet();
				rs = callableStatement.getResultSet();
				//fine LP PG21XX04 Leak
				if(rs.next()){
					dto.setProgressivoFlusso(rs.getLong(1));
					dto.setTipoRecord(rs.getString(2));
					dto.setDataCreazioneFlusso(java.sql.Date.valueOf(rs.getString(4)));
					dto.setDenominazione(rs.getString(11));
					dto.setTipoAnagrafica(rs.getString(12));
					dto.setCodiceBelfioreComuneNascita(rs.getString(13));
					dto.setDataNascita(java.sql.Date.valueOf(rs.getString(14)));
					dto.setStatusAnagrafica(rs.getString(15));
					dto.setIndirizzoFiscale(rs.getString(16));
					dto.setCodiceBelfioreComuneFiscale(rs.getString(17));
					dto.setTombstoned(rs.getString(18));
					dto.setEmail(rs.getString(19));
					dto.setEmailPec(rs.getString(20));
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
			//closeCallableStatement(callableStatement);
			if(rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(callableStatement != null) {
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
	
	//PYEH1SP_SEL3
	public DatiMailGeos getAnagMail(DatiMailGeos dto) throws DaoException {
		CallableStatement callableStatement = null;
		//inizio LP PG21XX04 Leak
		ResultSet rs = null;
		//fine LP PG21XX04 Leak
		try	{		
			callableStatement = prepareCall(Routines.PYEH1SP_SEL3.routine());
			callableStatement.setString(1, dto.getCutecute());
			callableStatement.setString(2, dto.getSocieta());
			callableStatement.setString(3, dto.getEnte());			
			callableStatement.setString(4, dto.getNumeroDoc());
			callableStatement.setString(5, dto.getCodFiscale());
			/* we execute procedure */
			if (callableStatement.execute()) 
			{
				//inizio LP PG21XX04 Leak
				//ResultSet rs = callableStatement.getResultSet();
				rs = callableStatement.getResultSet();
				//fine LP PG21XX04 Leak
				if(rs.next()){
					dto.setAnnoDocumento(rs.getString(1));
					dto.setDescrizioneImpostServizio(rs.getString(2));
					dto.setImportoBollettino(rs.getBigDecimal(3));
					dto.setDataScadenzaUltRata(rs.getString(4));
					dto.setMail(rs.getString(5));
					dto.setMailPec(rs.getString(6));
					//inizio LP PG190410
					dto.setCodiceImpostaServizio(rs.getString(7));
					//fine LP PG190410
					dto.setDenominazione(rs.getString(8));
					dto.setCodiceAvviso(rs.getString(9));
					dto.setNumeroRata(rs.getInt(10));
					dto.setNumeroTotRate(rs.getInt(11));
					dto.setCausale(rs.getString(12));
					dto.setDescrizioneEnte((rs.getString(13)).replace(",", ""));
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
			//closeCallableStatement(callableStatement);
			if(rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(callableStatement != null) {
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
	//PYEH6SP_SE2
	public ArchivioCarichiRuolo getRuolo(ArchivioCarichiRuolo dto) throws DaoException {
		CallableStatement callableStatement = null;
		//inizio LP PG21XX04 Leak
		ResultSet rs = null;
		//fine LP PG21XX04 Leak
		try	{		
			callableStatement = prepareCall(Routines.PYEH6SP_SE2.routine());
			callableStatement.setString(1, dto.getCodiceUtente());
			callableStatement.setString(2, dto.getTipoServizio());
			callableStatement.setString(3, dto.getCodiceEnte());			
			callableStatement.setString(4, dto.getTipoUfficio());
			callableStatement.setString(5, dto.getCodiceUfficio());
			callableStatement.setString(6, dto.getImpostaServizio());
			/* we execute procedure */
			if (callableStatement.execute()) 
			{
				//inizio LP PG21XX04 Leak
				//ResultSet rs = callableStatement.getResultSet();
				rs = callableStatement.getResultSet();
				//fine LP PG21XX04 Leak
				if(rs.next()){
					dto.setProgressivoFlusso(rs.getLong(1));
					dto.setTipoRecord(rs.getString(2));
					dto.setDataCreazioneFlusso(java.sql.Date.valueOf(rs.getString(4)));
					dto.setDescImpostaServizio(rs.getString(10));
					dto.setCodTipologiaServizio(rs.getString(11));
					dto.setDescTipologiaServizio(rs.getString(12));
					dto.setTombstoned(rs.getString(13));
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
			//closeCallableStatement(callableStatement);
			if(rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(callableStatement != null) {
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
	
	//PYEH2SP_SE2
	public ArchivioCarichiScadenza getScadenza(ArchivioCarichiScadenza dto) throws DaoException {
		CallableStatement callableStatement = null;
		//inizio LP PG21XX04 Leak
		ResultSet rs = null;
		//fine LP PG21XX04 Leak
		try	{		
			callableStatement = prepareCall(Routines.PYEH2SP_SE2.routine());
			callableStatement.setString(1, dto.getCodiceUtente());
			callableStatement.setString(2, dto.getTipoServizio());
			callableStatement.setString(3, dto.getCodiceEnte());			
			callableStatement.setString(4, dto.getTipoUfficio());
			callableStatement.setString(5, dto.getCodiceUfficio());
			callableStatement.setString(6, dto.getImpostaServizio());
			callableStatement.setString(7, dto.getNumeroDocumento());
			callableStatement.setInt(8, dto.getNumeroRata());
			/* we execute procedure */
			if (callableStatement.execute()) 
			{
				//inizio LP PG21XX04 Leak
				//ResultSet rs = callableStatement.getResultSet();
				rs = callableStatement.getResultSet();
				//fine LP PG21XX04 Leak
				if(rs.next()){
					dto.setProgressivoFlusso(rs.getLong(1));
					dto.setTipoRecord(rs.getString(2));
					dto.setDataCreazioneFlusso(java.sql.Date.valueOf(rs.getString(4)));
					dto.setImpDovutoDirittiNotifica(rs.getBigDecimal(11));
					dto.setImpPagatoTributi(rs.getBigDecimal(12));
					dto.setDataScadenzaRata(java.sql.Date.valueOf(rs.getString(13)));
					dto.setNumeroBollettino(rs.getString(15));
					dto.setImpBollettinoRata(rs.getBigDecimal(16));
					dto.setImpMora(rs.getBigDecimal(17));
					dto.setImpAggioCoattivoDovutoResiduoEntroScadenza(rs.getBigDecimal(18));
					dto.setImpAggioCoattivoOrigEntroScadenza(rs.getBigDecimal(19));
					dto.setImpAggioCoattivoDovutoResiduoOltreScadenza(rs.getBigDecimal(20));
					dto.setImpAggioCoattivoPagato(rs.getBigDecimal(21));
					dto.setImpDovutoDirittiNotifica(rs.getBigDecimal(22));
					dto.setImpPagatoDirittiNotifica(rs.getBigDecimal(23));
					dto.setTombstoned(rs.getString(24));
					dto.setIdentificativoUnivocoVersamento(rs.getString(25));
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
			//closeCallableStatement(callableStatement);
			if(rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(callableStatement != null) {
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
	
	//inizio LP PG210130
	//PYEHDSP_SE2
	/*
	public ArchivioCarichiDettaglioPagamento getDettaglioPagamento(ArchivioCarichiDettaglioPagamento dto) throws DaoException {
		CallableStatement callableStatement = null;
		ResultSet rs = null;
		try	{		
			callableStatement = prepareCall("PYEHDSP_SE2");
			callableStatement.setString(1, dto.getCodiceUtente());
			callableStatement.setString(2, dto.getTipoServizio());
			callableStatement.setString(3, dto.getCodiceEnte());			
			callableStatement.setString(4, dto.getTipoUfficio());
			callableStatement.setString(5, dto.getCodiceUfficio());
			callableStatement.setString(6, dto.getImpostaServizio());
			callableStatement.setString(7, dto.getNumeroDocumento());
			callableStatement.setString(8, dto.getNumeroBollettino());
			callableStatement.setString(9, dto.getIdDominio());
			callableStatement.setString(10, dto.getIbanBancario());
			if(dto.getIbanPostale() != null)
				callableStatement.setString(11, dto.getIbanPostale().trim());
			else
				callableStatement.setString(11, "");
			if (callableStatement.execute()) 
			{
				rs = callableStatement.getResultSet();
				if(rs.next()){
					dto = new ArchivioCarichiDettaglioPagamento(rs);
				}
			}
		} catch (SQLException x) {
			throw new DaoException(x);
		} catch (IllegalArgumentException x) {
			throw new DaoException(x);
		} catch (HelperException x) {
			throw new DaoException(x);
		} finally {
			if(rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(callableStatement != null) {
				try {
					callableStatement.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return dto;
	}

	//PYEHCSP_SE2
	public ArchivioCarichiDettaglioContabile getDettaglioContabile(ArchivioCarichiDettaglioContabile dto) throws DaoException {
		CallableStatement callableStatement = null;
		ResultSet rs = null;
		try	{		
			callableStatement = prepareCall("PYEHDCP_SE2");
			callableStatement.setString(1, dto.getCodiceUtente());
			callableStatement.setString(2, dto.getTipoServizio());
			callableStatement.setString(3, dto.getCodiceEnte());			
			callableStatement.setString(4, dto.getTipoUfficio());
			callableStatement.setString(5, dto.getCodiceUfficio());
			callableStatement.setString(6, dto.getImpostaServizio());
			callableStatement.setString(7, dto.getNumeroDocumento());
			callableStatement.setString(8, dto.getNumeroBollettino());
			callableStatement.setString(9, dto.get);
			callableStatement.setString(9, dto.getCodiceContabilita());
			if (callableStatement.execute()) 
			{
				rs = callableStatement.getResultSet();
				if(rs.next()){
					dto = new ArchivioCarichiDettaglioContabile(rs);
				}
			}
		} catch (SQLException x) {
			throw new DaoException(x);
		} catch (IllegalArgumentException x) {
			throw new DaoException(x);
		} catch (HelperException x) {
			throw new DaoException(x);
		} finally {
			if(rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(callableStatement != null) {
				try {
					callableStatement.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return dto;
	}
	*/

	public String getCodiceFiscaleEnte(String cutecute, String codiceEnte, String tipoUfficio, String codiceUfficio) throws DaoException
	{
		String codiceFisEnte = "";
		CallableStatement callableStatement = null;
		ResultSet rs = null;
		try	{
			callableStatement = prepareCall("PYANESP_SEL_ENT3");
			callableStatement.setString(1, cutecute);
			callableStatement.setString(2, codiceEnte);
			callableStatement.setString(3, tipoUfficio);			
			callableStatement.setString(4, codiceUfficio);
			if (callableStatement.execute()) {
				rs = callableStatement.getResultSet();
				if(rs.next()) {
					codiceFisEnte = rs.getString(1);
					//inizio LP PG210130 Step04
					if(codiceFisEnte != null)
						codiceFisEnte = codiceFisEnte.trim();
					//fine LP PG210130 Step04
				}
			}
		} catch (SQLException x) {
			throw new DaoException(x);
		} catch (IllegalArgumentException x) {
			throw new DaoException(x);
		} catch (HelperException x) {
			throw new DaoException(x);
		} finally {
			if(rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(callableStatement != null) {
				try {
					callableStatement.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return codiceFisEnte;
	}
	
//inizio LP PG210130 Step02
	public String getKeyEnteEC(String idDominio) throws Exception
	{
		String keyEnte = null;
		ResultSet listanagEnte = null;
		CallableStatement callableStatement = null;
		try{
			callableStatement = prepareCall(Routines.PYANESP_SEL_DOMINIO.routine());
			callableStatement.setString(1, idDominio);
			callableStatement.execute();
			listanagEnte = callableStatement.getResultSet();
			
			if (listanagEnte.next()) {
				keyEnte = listanagEnte.getString("ANE_KANEKENT");
				//inizio LP PG210130 Step04
				if(keyEnte != null)
					keyEnte = keyEnte.trim();
				//fine LP PG210130 Step04
				//listanagEnte.getString("ANE_CANECENT"),
				//listanagEnte.getString("ANE_TANETUFF"),
				//listanagEnte.getString("ANE_CANECUFF"),
				//listanagEnte.getString("ANE_DANEDENT"));
			}	
		}catch (SQLException e) {
			throw new Exception(e);
		} catch (IllegalArgumentException e) {
			throw new Exception(e);
		} catch (HelperException e) {
			throw new Exception(e);
		} finally {
			if (callableStatement != null) {
				try {
					callableStatement.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return keyEnte;
	}

//	public void doDeleteArchivioCarichiEHDEHC(String codiceUtente, String tipoServizio, String codiceEnte, String tipoUfficio, String codiceUfficio, String impostaServizio, String numeroDocumento) throws DaoException {
//		CallableStatement callableStatement = null;	
//		try {
//			callableStatement = prepareCall("PYEHXSP_DC_DELETE");
//			callableStatement.setString(1, codiceUtente);
//			callableStatement.setString(2, tipoServizio);
//			callableStatement.setString(3, codiceEnte);
//			callableStatement.setString(4, tipoUfficio);
//			callableStatement.setString(5, codiceUfficio);
//			callableStatement.setString(6, impostaServizio);
//			callableStatement.setString(7, numeroDocumento);
//			callableStatement.execute();
//		} catch (SQLException x) {
//			throw new DaoException(x);
//		} catch (IllegalArgumentException x) {
//			throw new DaoException(x);
//		} catch (HelperException x) {
//			throw new DaoException(x);
//		} finally {
//			if(callableStatement != null) {
//				try {
//					callableStatement.close();
//				} catch (SQLException e) {
//					e.printStackTrace();
//				}
//			}
//		}	
//	}
//
//	public void updateImportoScadenza(ArchivioCarichiScadenza dto) throws DaoException {
//		CallableStatement callableStatement = null;
//		try	{		
//			callableStatement = prepareCall("PYEH2SP_UPD_IMP");
//			callableStatement.setLong(1, dto.getProgressivoFlusso());
//			callableStatement.setString(2, dto.getCodiceUtente());
//			callableStatement.setString(3, dto.getTipoServizio());
//			callableStatement.setString(4, dto.getCodiceEnte());			
//			callableStatement.setString(5, dto.getTipoUfficio());
//			callableStatement.setString(6, dto.getCodiceUfficio());
//			callableStatement.setString(7, dto.getImpostaServizio());
//			callableStatement.setString(8, dto.getNumeroDocumento());
//			callableStatement.setInt(9, dto.getNumeroRata());
//			callableStatement.setString(10, dto.getNumeroBollettino());
//			callableStatement.setBigDecimal(11, dto.getImpBollettinoRata());
//			/* we execute procedure */
//			callableStatement.execute();
//
//		} catch (SQLException x) {
//			throw new DaoException(x);
//		} catch (IllegalArgumentException x) {
//			throw new DaoException(x);
//		} catch (HelperException x) {
//			throw new DaoException(x);
//		} finally {
//			if(callableStatement != null) {
//				try {
//					callableStatement.close();
//				} catch (SQLException e) {
//					e.printStackTrace();
//				}
//			}
//		}
//	}
//fine LP PG210130 Step02
	//fine LP PG210130
	
	//PYEH7SP_SE2
	//inizio LP 20240828 - PGNTACWS-22
	public ArchivioCarichiTributo getTributo(ArchivioCarichiTributo dto) throws DaoException {
		return getTributoTail(true, dto);
	}

	public ArchivioCarichiTributo getTributoTail(boolean bFlagUpdateAutocommit, ArchivioCarichiTributo dto) throws DaoException {
	//fine LP 20240828 - PGNTACWS-22
		CallableStatement callableStatement = null;
		//inizio LP PG21XX04 Leak
		ResultSet rs = null;
		//fine LP PG21XX04 Leak
		try	{		
			//inizio LP 20240828 - PGNTACWS-22
			//callableStatement = prepareCall(Routines.PYEH7SP_SE2.routine());
			callableStatement = prepareCall(bFlagUpdateAutocommit, Routines.PYEH7SP_SE2.routine());
			//fine LP 20240828 - PGNTACWS-22
			callableStatement.setString(1, dto.getCodiceUtente());
			callableStatement.setString(2, dto.getTipoServizio());
			callableStatement.setString(3, dto.getCodiceEnte());			
			callableStatement.setString(4, dto.getTipoUfficio());
			callableStatement.setString(5, dto.getCodiceUfficio());
			callableStatement.setString(6, dto.getImpostaServizio());
			callableStatement.setString(7, dto.getNumeroDocumento());
			callableStatement.setString(8, dto.getCodiceTributo());
			callableStatement.setString(9, dto.getAnnoTributo());
			callableStatement.setInt(10, dto.getProgressivoTributo());
			/* we execute procedure */
			if (callableStatement.execute()) 
			{
				//inizio LP PG21XX04 Leak
				//ResultSet rs = callableStatement.getResultSet();
				rs = callableStatement.getResultSet();
				//fine LP PG21XX04 Leak
				if(rs.next()){
					dto.setProgressivoFlusso(rs.getLong(1));
					dto.setTipoRecord(rs.getString(2));
					dto.setDataCreazioneFlusso(java.sql.Date.valueOf(rs.getString(4)));
					dto.setTipoTributo(rs.getString(14));
					dto.setImpTributo(rs.getBigDecimal(15));
					dto.setImpPagatoCompresiSgravi(rs.getBigDecimal(16));
					dto.setNoteTributo(rs.getString(17));
					dto.setTombstoned(rs.getString(18));
				}
			}
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
			//closeCallableStatement(callableStatement);
			if(rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(callableStatement != null) {
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
	
	//PYEH3SP_SE2
	public ArchivioCarichiMovimento getMovimento(ArchivioCarichiMovimento dto) throws DaoException {
		CallableStatement callableStatement = null;
		//inizio LP PG21XX04 Leak
		ResultSet rs = null;
		//fine LP PG21XX04 Leak
		try	{		
			callableStatement = prepareCall(Routines.PYEH3SP_SE2.routine());
			callableStatement.setString(1, dto.getCodiceUtente());
			callableStatement.setString(2, dto.getTipoServizio());
			callableStatement.setString(3, dto.getCodiceEnte());			
			callableStatement.setString(4, dto.getTipoUfficio());
			callableStatement.setString(5, dto.getCodiceUfficio());
			callableStatement.setString(6, dto.getImpostaServizio());
			callableStatement.setString(7, dto.getNumeroDocumento());
			callableStatement.setInt(8, dto.getProgressivoPagamento());
			callableStatement.setString(9, dto.getTipoMovimento());
			
			/* we execute procedure */
			if (callableStatement.execute()) 
			{
				//inizio LP PG21XX04 Leak
				//ResultSet rs = callableStatement.getResultSet();
				rs = callableStatement.getResultSet();
				//fine LP PG21XX04 Leak
				if(rs.next()){
					dto.setProgressivoFlusso(rs.getLong(1));
					dto.setTipoRecord(rs.getString(2));
					dto.setDataCreazioneFlusso(rs.getDate(4));
					dto.setProgressivoPagamento(rs.getInt(11));
					dto.setTipoMovimento(rs.getString(12));
					dto.setDataMovimento(rs.getDate(13));
					dto.setCausaleMovimento(rs.getString(14));
					dto.setSegno(rs.getString(15));
					dto.setImpPagatoSuTributoCompresoIntMR(rs.getBigDecimal(16));
					dto.setImpPagatoDirittiNotifica(rs.getBigDecimal(17));
					dto.setImpPagatoAggioCoattivo(rs.getBigDecimal(18));
					dto.setImpPagatoAltreSpese(rs.getBigDecimal(19));
					dto.setImpPagatoIntMora(rs.getBigDecimal(20));
					dto.setNumeroRate(rs.getInt(21));
					dto.setPrimaRata(rs.getInt(22));
					dto.setCanalePagamento(rs.getString(23));
					dto.setTipoPagamento(rs.getString(24));
					dto.setTombstoned(rs.getString(25));
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
			//closeCallableStatement(callableStatement);
			if(rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(callableStatement != null) {
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
	
	//PYEH1SP_DE2
	public String deleteDocumento (ArchivioCarichiDocumento dto) throws DaoException {
		String retMessage = "";
		CallableStatement callableStatement = null;
		try	{		
			callableStatement = prepareCall(Routines.PYEH1SP_DE2.routine());
			callableStatement.setString(1, dto.getCodiceUtente());
			callableStatement.setString(2, dto.getTipoServizio());
			callableStatement.setString(3, dto.getCodiceEnte());			
			callableStatement.setString(4, dto.getTipoUfficio());
			callableStatement.setString(5, dto.getCodiceUfficio());
			callableStatement.setString(6, dto.getImpostaServizio());
			callableStatement.setString(7, dto.getNumeroDocumento());
			callableStatement.registerOutParameter(8, Types.VARCHAR);
			/* we execute procedure */
			callableStatement.execute(); 
			retMessage = callableStatement.getString(8);
		} catch (SQLException x) {
			throw new DaoException(x);
		} catch (IllegalArgumentException x) {
			throw new DaoException(x);
		} catch (HelperException x) {
			throw new DaoException(x);
		} finally {
			//inizio LP PG21XX04 Leak
			//closeCallableStatement(callableStatement);
			if(callableStatement != null) {
				try {
					callableStatement.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			//fine LP PG21XX04 Leak
		}
		return retMessage;
	}
	
	//PYEH6SP_UP2
	public void updateRuolo (ArchivioCarichiRuolo dto) throws DaoException {
		CallableStatement callableStatement = null;
		try	{		
			callableStatement = prepareCall(Routines.PYEH6SP_UP2.routine());
			callableStatement.setLong(1, dto.getProgressivoFlusso());
			callableStatement.setString(2, dto.getTipoRecord());
			callableStatement.setString(3, dto.getCodiceUtente());
			callableStatement.setDate(4, new java.sql.Date(dto.getDataCreazioneFlusso().getTime()));
			callableStatement.setString(5, dto.getTipoServizio());
			callableStatement.setString(6, dto.getCodiceEnte());			
			callableStatement.setString(7, dto.getTipoUfficio());
			callableStatement.setString(8, dto.getCodiceUfficio());
			callableStatement.setString(9, dto.getImpostaServizio());
			callableStatement.setString(10, dto.getDescImpostaServizio());
			callableStatement.setString(11, dto.getCodTipologiaServizio());
			callableStatement.setString(12, dto.getDescTipologiaServizio());
			callableStatement.setString(13, dto.getTombstoned());
			
			/* we execute procedure */
			callableStatement.execute();

		} catch (SQLException x) {
			throw new DaoException(x);
		} catch (IllegalArgumentException x) {
			throw new DaoException(x);
		} catch (HelperException x) {
			throw new DaoException(x);
		} finally {
			//inizio LP PG21XX04 Leak
			//closeCallableStatement(callableStatement);
			if(callableStatement != null) {
				try {
					callableStatement.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			//fine LP PG21XX04 Leak
		}
	}
	
	//PYEH8SP_UP2
	public void updateAnagrafica (ArchivioCarichiAnagrafica dto) throws DaoException {
		CallableStatement callableStatement = null;
		try	{		
			callableStatement = prepareCall(Routines.PYEH8SP_UP2.routine());
			callableStatement.setLong(1, dto.getProgressivoFlusso());
			callableStatement.setString(2, dto.getTipoRecord());
			callableStatement.setString(3, dto.getCodiceUtente());
			callableStatement.setDate(4, new java.sql.Date(dto.getDataCreazioneFlusso().getTime()));
			callableStatement.setString(5, dto.getTipoServizio());
			callableStatement.setString(6, dto.getCodiceEnte());			
			callableStatement.setString(7, dto.getTipoUfficio());
			callableStatement.setString(8, dto.getCodiceUfficio());
			callableStatement.setString(9, dto.getImpostaServizio());
			callableStatement.setString(10, dto.getCodiceFiscale());
			callableStatement.setString(11, dto.getDenominazione());
			callableStatement.setString(12, dto.getTipoAnagrafica());
			callableStatement.setString(13, dto.getCodiceBelfioreComuneNascita());
			callableStatement.setDate(14, new java.sql.Date(dto.getDataNascita().getTime()));
			callableStatement.setString(15, dto.getStatusAnagrafica());
			callableStatement.setString(16, dto.getIndirizzoFiscale());
			callableStatement.setString(17, dto.getCodiceBelfioreComuneFiscale());
			callableStatement.setString(18, dto.getTombstoned());
			callableStatement.setString(19, dto.getEmail());
			callableStatement.setString(20, dto.getEmailPec());
						
			/* we execute procedure */
			callableStatement.execute();

		} catch (SQLException x) {
			throw new DaoException(x);
		} catch (IllegalArgumentException x) {
			throw new DaoException(x);
		} catch (HelperException x) {
			throw new DaoException(x);
		} finally {
			//inizio LP PG21XX04 Leak
			//closeCallableStatement(callableStatement);
			if(callableStatement != null) {
				try {
					callableStatement.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			//fine LP PG21XX04 Leak
		}
	}
	
	//PYEHXSP_DIS
	//inizio LP 20240828 - PGNTACWS-22
	public void applicaDiscarico(ArchivioCarichiTributo dto) throws DaoException {
		applicaDiscaricoTail(true, dto);
	}

	public void applicaDiscaricoTail(boolean bFlagUpdateAutocommit, ArchivioCarichiTributo dto) throws DaoException {
	//fine LP 20240828 - PGNTACWS-22
		CallableStatement callableStatement = null;
		try	{		
			//inizio LP 20240828 - PGNTACWS-22
			//callableStatement = prepareCall(Routines.PYEHXSP_DIS.routine());
			callableStatement = prepareCall(bFlagUpdateAutocommit, Routines.PYEHXSP_DIS.routine());
			//fine LP 20240828 - PGNTACWS-22
			callableStatement.setLong(1, dto.getProgressivoFlusso());
			callableStatement.setString(2, dto.getCodiceUtente());
			callableStatement.setString(3, dto.getTipoServizio());
			callableStatement.setString(4, dto.getCodiceEnte());			
			callableStatement.setString(5, dto.getTipoUfficio());
			callableStatement.setString(6, dto.getCodiceUfficio());
			callableStatement.setString(7, dto.getImpostaServizio());
			callableStatement.setString(8, dto.getNumeroDocumento());
			callableStatement.setString(9, dto.getCodiceTributo());
			callableStatement.setString(10, dto.getAnnoTributo());
			callableStatement.setInt(11, dto.getProgressivoTributo());
			callableStatement.setBigDecimal(12, dto.getImpPagatoCompresiSgravi());
					
			/* we execute procedure */
			callableStatement.execute();

		} catch (SQLException x) {
			throw new DaoException(x);
		} catch (IllegalArgumentException x) {
			throw new DaoException(x);
		} catch (HelperException x) {
			throw new DaoException(x);
		} finally {
			//inizio LP PG21XX04 Leak
			//closeCallableStatement(callableStatement);
			if(callableStatement != null) {
				try {
					callableStatement.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			//fine LP PG21XX04 Leak
		}
	}
	
	public void doRollbackArchivioCarichi(String codiceUtente, String tipoServizio, String codiceEnte, String tipoUfficio, String codiceUfficio, String impostaServizio, String numeroDocumento) throws DaoException {
		CallableStatement callableStatement = null;	
		try {
			callableStatement = prepareCall(Routines.EHX_DOACROLLBACK.routine());
			callableStatement.setString(1, codiceUtente);
			callableStatement.setString(2, tipoServizio);
			callableStatement.setString(3, codiceEnte);
			callableStatement.setString(4, tipoUfficio);
			callableStatement.setString(5, codiceUfficio);
			callableStatement.setString(6, impostaServizio);
			callableStatement.setString(7, numeroDocumento);
			callableStatement.execute();
		} catch (SQLException x) {
			throw new DaoException(x);
		} catch (IllegalArgumentException x) {
			throw new DaoException(x);
		} catch (HelperException x) {
			throw new DaoException(x);
		} finally {
			//inizio LP PG21XX04 Leak
			//closeCallableStatement(callableStatement);
			if(callableStatement != null) {
				try {
					callableStatement.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			//fine LP PG21XX04 Leak
		}	
	}
	
	//PYEH3SP_SEL_PPAG
	//inizio LP 20240828 - PGNTACWS-22
	public int getProgressivoPagamento(ArchivioCarichiDocumento dto) throws DaoException {
		return getProgressivoPagamentoTail(true, dto);
	}

	public int getProgressivoPagamentoTail(boolean bFlagUpdateAutocommit, ArchivioCarichiDocumento dto) throws DaoException {
	//fine LP 20240828 - PGNTACWS-22
		int progressivoPagamento = 0;
		CallableStatement callableStatement = null;
		try	{		
			//inizio LP 20240828 - PGNTACWS-22
			//callableStatement = prepareCall(Routines.PYEH3SP_SEL_PPAG.routine());
			callableStatement = prepareCall(bFlagUpdateAutocommit, Routines.PYEH3SP_SEL_PPAG.routine());
			//fine LP 20240828 - PGNTACWS-22
			callableStatement.setString(1, dto.getCodiceUtente());
			callableStatement.setString(2, dto.getTipoServizio());
			callableStatement.setString(3, dto.getCodiceEnte());			
			callableStatement.setString(4, dto.getTipoUfficio());
			callableStatement.setString(5, dto.getCodiceUfficio());
			callableStatement.setString(6, dto.getImpostaServizio());
			callableStatement.setString(7, dto.getNumeroDocumento());
			callableStatement.registerOutParameter(8, Types.INTEGER);
			
			/* we execute procedure */
			callableStatement.execute();
			progressivoPagamento = callableStatement.getInt(8);
		} catch (SQLException x) {
			throw new DaoException(x);
		} catch (IllegalArgumentException x) {
			throw new DaoException(x);
		} catch (HelperException x) {
			throw new DaoException(x);
		} finally {
			//inizio LP PG21XX04 Leak
			//closeCallableStatement(callableStatement);
			if(callableStatement != null) {
				try {
					callableStatement.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			//fine LP PG21XX04 Leak
		}
		
		return progressivoPagamento;
	}
	
	public String getProgressivoIuv(String codiceEnte, String auxDigit, int dayOfYear) throws DaoException {
		CallableStatement callableStatement = null;
		try	{
			callableStatement = prepareCall(Routines.PYPRGSP_NXT.routine());
			callableStatement.setString(1, codiceEnte);
			callableStatement.setString(2, auxDigit);
			callableStatement.setInt(3, dayOfYear);
			callableStatement.registerOutParameter(4, Types.INTEGER);
			
			callableStatement.execute(); 
			Integer progressivo=callableStatement.getInt(4);
			return String.valueOf(progressivo);
		} catch (SQLException x) {
			throw new DaoException(x);
		} catch (IllegalArgumentException x) {
			throw new DaoException(x);
		} catch (HelperException x) {
			throw new DaoException(x);
		} finally {
			//inizio LP PG21XX04 Leak
			//closeCallableStatement(callableStatement);
			if(callableStatement != null) {
				try {
					callableStatement.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			//fine LP PG21XX04 Leak
		}
	}
	
	public int checkExistIUV(String codiceEnte, String identificativoUnivocoVersamento, String numeroDocumento, Integer numeroRata, String flagOperazione) throws DaoException {
		CallableStatement callableStatement = null;
		try	{
			callableStatement = prepareCall(Routines.PYEHXSP_CHK_IUV.routine());
			callableStatement.setString(1, codiceEnte);
			callableStatement.setString(2, identificativoUnivocoVersamento);
			callableStatement.setString(3, numeroDocumento);
			callableStatement.setInt(4, numeroRata);
			callableStatement.setString(5, flagOperazione);
			callableStatement.registerOutParameter(6, Types.INTEGER);
			
			callableStatement.execute(); 
			int count=callableStatement.getInt(6);
			return count;
		} catch (SQLException x) {
			throw new DaoException(x);
		} catch (IllegalArgumentException x) {
			throw new DaoException(x);
		} catch (HelperException x) {
			throw new DaoException(x);
		} finally {
			//inizio LP PG21XX04 Leak
			//closeCallableStatement(callableStatement);
			if(callableStatement != null) {
				try {
					callableStatement.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			//fine LP PG21XX04 Leak
		}
	}

	public boolean checkExistTassonomia(String tassonomia) throws DaoException {
		CallableStatement callableStatement = null;
		try	{
			callableStatement = prepareCall(Routines.PYTASSP_CHK.routine());
			callableStatement.setString(1, tassonomia);
			callableStatement.registerOutParameter(2, Types.INTEGER);
			
			callableStatement.execute(); 
			int count = callableStatement.getInt(2);
			return (count == 1);
		} catch (SQLException x) {
			throw new DaoException(x);
		} catch (IllegalArgumentException x) {
			throw new DaoException(x);
		} catch (HelperException x) {
			throw new DaoException(x);
		} finally {
			//inizio LP PG21XX04 Leak
			//closeCallableStatement(callableStatement);
			if(callableStatement != null) {
				try {
					callableStatement.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			//fine LP PG21XX04 Leak
		}
	}

	public String getFlagElabStampaAvviso(String identificativoFlusso) throws DaoException {
		CallableStatement callableStatement = null;
		try	{
			callableStatement = prepareCall(Routines.PYELGSP_SEL_FELGESTA.routine());
			callableStatement.setString(1, identificativoFlusso);
			callableStatement.registerOutParameter(2, Types.CHAR);
			
			callableStatement.execute(); 
			String flagElabStampaAvviso=callableStatement.getString(2);
			return flagElabStampaAvviso.trim();
		} catch (SQLException x) {
			throw new DaoException(x);
		} catch (IllegalArgumentException x) {
			throw new DaoException(x);
		} catch (HelperException x) {
			throw new DaoException(x);
		} finally {
			//inizio LP PG21XX04 Leak
			//closeCallableStatement(callableStatement);
			if(callableStatement != null) {
				try {
					callableStatement.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			//fine LP PG21XX04 Leak
		}
	}
	
	//PYEH6SP_SE2
	public ArchivioCarichiLogFlussi getLogFlusso(String identificativoFlusso) throws DaoException {
		ArchivioCarichiLogFlussi dto = null;
		CallableStatement callableStatement = null;
		//inizio LP PG21XX04 Leak
		ResultSet rs = null;
		//fine LP PG21XX04 Leak
		try	{		
			callableStatement = prepareCall(Routines.PYELGSP_SE2.routine());
			callableStatement.setString(1, identificativoFlusso);
			
			/* we execute procedure */
			if (callableStatement.execute()) 
			{
				//inizio LP PG21XX04 Leak
				//ResultSet rs = callableStatement.getResultSet();
				rs = callableStatement.getResultSet();
				//fine LP PG21XX04 Leak
				if(rs.next()){
					dto = new ArchivioCarichiLogFlussi();
					dto.setProgressivoFlusso(rs.getLong(1));
					dto.setCodiceUtente(rs.getString(2));
					dto.setDataCreazioneFlusso(java.sql.Date.valueOf(rs.getString(3)));
					dto.setProgressivoFlussoInGiornata(rs.getInt(4));
					dto.setProceduraGestione(rs.getString(5));
					dto.setTipoServizio(rs.getString(6));
					dto.setIdentificativoFlusso(rs.getString(7));
					dto.setDataInizioElabFlusso(rs.getTimestamp(8));
					dto.setDataFineElabFlusso(rs.getTimestamp(9));
					dto.setFlagElaborazioneFlusso(rs.getString(10));
					dto.setFlagElaborazioneStampaAvviso(rs.getString(11));
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
			//closeCallableStatement(callableStatement);
			if(rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(callableStatement != null) {
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
	
	//PYEH0SP_SE2
	public ArchivioCarichiTesta getTesta(ArchivioCarichiTesta dto) throws DaoException {
		CallableStatement callableStatement =null;
		//inizio LP PG21XX04 Leak
		ResultSet rs = null;
		//fine LP PG21XX04 Leak
		try	{		
			callableStatement = prepareCall(Routines.PYEH0SP_SE2.routine());
			callableStatement.setLong(1, dto.getProgressivoFlusso());
			/* we execute procedure */
			if (callableStatement.execute()) 
			{
				//inizio LP PG21XX04 Leak
				//ResultSet rs = callableStatement.getResultSet();
				rs = callableStatement.getResultSet();
				//fine LP PG21XX04 Leak
				if(rs.next()){
					dto.setTipoRecord(rs.getString(2));
					dto.setDataCreazioneFlusso(java.sql.Date.valueOf(rs.getString(4)));
					dto.setProceduraGestione(rs.getString(5));
					dto.setTipoServizio(rs.getString(6));
					dto.setTombstoned(rs.getString(7));
					dto.setFlagStampaAvviso(rs.getString(8));
					dto.setFlagGenerazioneIUV(rs.getString(9));
					dto.setIdDominio(rs.getString(10));
					dto.setAuxDigit(rs.getString(11));
					dto.setApplicationCode(rs.getString(12));
					dto.setSegregationCode(rs.getString(13));
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
			//closeCallableStatement(callableStatement);
			if(rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(callableStatement != null) {
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
	
	//PYEH9SP_SE2
	public ArchivioCarichiCoda getCoda(ArchivioCarichiCoda dto) throws DaoException {
		CallableStatement callableStatement =null;
		//inizio LP PG21XX04 Leak
		ResultSet rs = null;
		//fine LP PG21XX04 Leak
		try	{		
			callableStatement = prepareCall(Routines.PYEH9SP_SE2.routine());
			callableStatement.setLong(1, dto.getProgressivoFlusso());
			/* we execute procedure */
			if (callableStatement.execute()) 
			{
				//inizio LP PG21XX04 Leak
				//ResultSet rs = callableStatement.getResultSet();
				rs = callableStatement.getResultSet();
				//fine LP PG21XX04 Leak
				if(rs.next()){
					dto.setTipoRecord(rs.getString(2));
					dto.setDataCreazioneFlusso(java.sql.Date.valueOf(rs.getString(4)));
					dto.setTipoServizio(rs.getString(5));
					dto.setNumeroRecordFlusso(rs.getInt(6));
					dto.setTombstoned(rs.getString(7));
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
			//closeCallableStatement(callableStatement);
			if(rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(callableStatement != null) {
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
	
	//PYEH0SP_UP2
	public void updateTesta (ArchivioCarichiTesta dto) throws DaoException {
		CallableStatement callableStatement = null;
		try	{		
			callableStatement = prepareCall(Routines.PYEH0SP_UP2.routine());
			callableStatement.setLong(1, dto.getProgressivoFlusso());
			callableStatement.setString(2, dto.getTipoRecord());
			callableStatement.setString(3, dto.getCodiceUtente());
			callableStatement.setDate(4, new java.sql.Date(dto.getDataCreazioneFlusso().getTime()));
			callableStatement.setString(5, dto.getProceduraGestione());
			callableStatement.setString(6, dto.getTipoServizio());
			callableStatement.setString(7, dto.getTombstoned());
			callableStatement.setString(8, dto.getFlagStampaAvviso());			
			callableStatement.setString(9, dto.getFlagGenerazioneIUV());
			callableStatement.setString(10, dto.getIdDominio());
			callableStatement.setString(11, dto.getAuxDigit());
			callableStatement.setString(12, dto.getApplicationCode());
			callableStatement.setString(13, dto.getSegregationCode());
			
			/* we execute procedure */
			callableStatement.execute();

		} catch (SQLException x) {
			throw new DaoException(x);
		} catch (IllegalArgumentException x) {
			throw new DaoException(x);
		} catch (HelperException x) {
			throw new DaoException(x);
		} finally {
			//inizio LP PG21XX04 Leak
			//closeCallableStatement(callableStatement);
			if(callableStatement != null) {
				try {
					callableStatement.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			//fine LP PG21XX04 Leak
		}
	}
	
	//PYEH9SP_UP2
	public void updateCoda (ArchivioCarichiCoda dto) throws DaoException {
		CallableStatement callableStatement = null;
		try	{		
			callableStatement = prepareCall(Routines.PYEH9SP_UP2.routine());
			callableStatement.setLong(1, dto.getProgressivoFlusso());
			callableStatement.setString(2, dto.getTipoRecord());
			callableStatement.setString(3, dto.getCodiceUtente());
			callableStatement.setDate(4, new java.sql.Date(dto.getDataCreazioneFlusso().getTime()));
			callableStatement.setString(5, dto.getTipoServizio());
			callableStatement.setInt(6, dto.getNumeroRecordFlusso());
			callableStatement.setString(7, dto.getTombstoned());
			
			/* we execute procedure */
			callableStatement.execute();

		} catch (SQLException x) {
			throw new DaoException(x);
		} catch (IllegalArgumentException x) {
			throw new DaoException(x);
		} catch (HelperException x) {
			throw new DaoException(x);
		} finally {
			//inizio LP PG21XX04 Leak
			//closeCallableStatement(callableStatement);
			if(callableStatement != null) {
				try {
					callableStatement.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			//fine LP PG21XX04 Leak
		}
	}
	
	//Inserimento pagamento ed aggiornamento importo pagato su avviso e scadenze
	public String inserisciPagamentoEcDifferito(String codTransazione) throws DaoException {
		CallableStatement callableStatement = null;
		String codiceRitorno = "";
		System.out.println("inizio inserisciPagamentoEcDifferito dentro core");
		try {
			callableStatement = prepareCall(Routines.PYEHXSP_PAG.routine());
			callableStatement.setString(1, codTransazione); 
			callableStatement.execute();
		} catch (SQLException x) {
			throw new DaoException(x);
		} catch (IllegalArgumentException x) {
			throw new DaoException(x);
		} catch (HelperException x) {
			throw new DaoException(x);
		} catch (Throwable x) {
			System.out.println("errore = " + x.getMessage());
			throw new DaoException(x);
		} finally {
			//inizio LP PG21XX04 Leak
			//closeCallableStatement(callableStatement);
			if(callableStatement != null) {
				try {
					callableStatement.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			//fine LP PG21XX04 Leak
		}
		System.out.println("fine inserisciPagamentoEcDifferito codiceRitorno = " + codiceRitorno);
		return codiceRitorno; //Da verificare
	}
	
	//inizio LP PG190220
	//Annulla pagamento ed aggiornamento importo pagato su avviso e scadenze
	public boolean annullaPagamentoEcDifferito(String codTransazione) throws DaoException {
		CallableStatement callableStatement = null;
		boolean bOk = false;

		try {
			callableStatement = prepareCall("PYEHXSP_ANN");
			callableStatement.setString(1, codTransazione); 
			callableStatement.execute();
			bOk = true;
		} catch (SQLException x) {
			throw new DaoException(x);
		} catch (IllegalArgumentException x) {
			throw new DaoException(x);
		} catch (HelperException x) {
			throw new DaoException(x);
		} finally {
			//inizio LP PG21XX04 Leak
			//closeCallableStatement(callableStatement);
			if(callableStatement != null) {
				try {
					callableStatement.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			//fine LP PG21XX04 Leak
		}
		return bOk;
	}
	//fine LP PG190220
	
	//Verifica presenza in PYELGTB di flussi con identico nome in date differenti 
	public int getProgFlussoPerNomeAltraData(String nomeFile, Date dataFlusso) throws DaoException {
		CallableStatement callableStatement = null;
		
		try {
			callableStatement = prepareCall(Routines.ELG_CHECK_PELGFLUS_BY_NAME.routine());
			callableStatement.setString(1, nomeFile);
			callableStatement.setDate(2, dataFlusso);
			callableStatement.registerOutParameter(3, Types.INTEGER);
			callableStatement.execute();
			int i = callableStatement.getInt(3);
			return i;
		} catch (SQLException x) {
			throw new DaoException(x);
		} catch (IllegalArgumentException x) {
			throw new DaoException(x);
		} catch (HelperException x) {
			throw new DaoException(x);
		} finally {
			//inizio LP PG21XX04 Leak
			//closeCallableStatement(callableStatement);
			if(callableStatement != null) {
				try {
					callableStatement.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			//fine LP PG21XX04 Leak
		}
	}
	
	//29012021 GG - inserita verifica su documento in stampa/stampato
	public String getFlagStampa(ArchivioCarichiDocumento dto) throws DaoException {
		CallableStatement callableStatement = null;
		try	{
			callableStatement = prepareCall(Routines.PYEH1SP_SEL_FELGESTA.routine());
			callableStatement.setString(1, dto.getCodiceUtente());
			callableStatement.setString(2, dto.getTipoServizio());
			callableStatement.setString(3, dto.getCodiceEnte());			
			callableStatement.setString(4, dto.getTipoUfficio());
			callableStatement.setString(5, dto.getCodiceUfficio());
			callableStatement.setString(6, dto.getImpostaServizio());
			callableStatement.setString(7, dto.getNumeroDocumento());
			callableStatement.registerOutParameter(8, Types.CHAR);
			
			callableStatement.execute(); 
			String flagStampa=callableStatement.getString(8);
			return flagStampa.trim();
		} catch (SQLException x) {
			throw new DaoException(x);
		} catch (IllegalArgumentException x) {
			throw new DaoException(x);
		} catch (HelperException x) {
			throw new DaoException(x);
		} finally {
			//inizio LP PG21XX04 Leak
			//closeCallableStatement(callableStatement);
			if(callableStatement != null) {
				try {
					callableStatement.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			//fine LP PG21XX04 Leak
		}
	}
	
	public List<DatiFlussoIO> doList(DettaglioFlussoOttico dettaglioFlussoOttico) throws DaoException {
		CallableStatement callableStatement = null;
		//inizio LP PG21XX04 Leak
		ResultSet data = null;
		//fine LP PG21XX04 Leak
		List<DatiFlussoIO> list = null;
		try	{
			// we prepare callableStatement
			callableStatement = prepareCall(Routines.PYEHXSP_FIO.routine());
			callableStatement.setString(1, dettaglioFlussoOttico.getCodiceSocieta());
			callableStatement.setString(2, dettaglioFlussoOttico.getCodiceUtente());
			callableStatement.setString(3, dettaglioFlussoOttico.getCodiceEnte());	
			callableStatement.setString(4, dettaglioFlussoOttico.getNumeroDocumento());
			callableStatement.setString(5, dettaglioFlussoOttico.getCodicFiscaleDebitore());
			callableStatement.setString(6, "");	//Qui dovrò mettere codiceimpostaservizio
			
			list = new ArrayList<DatiFlussoIO>();
			// we execute callableStatement
			if (callableStatement.execute()) {
				//inizio LP PG21XX04 Leak
				//ResultSet data = callableStatement.getResultSet();
				data = callableStatement.getResultSet();
				//fine LP PG21XX04 Leak
				if (data.next()) {
					do {
						list.add(new DatiFlussoIO(data));
					} while (data.next());
				}
				return list; 
			}
		} catch (SQLException x) {
			throw new DaoException(x);
		} catch (IllegalArgumentException x) {
			throw new DaoException(x);
		} catch (HelperException x) {
			throw new DaoException(x);
		} finally {
			//inizio LP PG21XX04 Leak
			//closeCallableStatement(callableStatement);
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
		return list;
	}
	
	//inizio LP 20240828 - PGNTACWS-22
	//private void closeCallableStatement(CallableStatement callableStatement)
	//{
	//	if (callableStatement != null)
	//		DAOHelper.closeIgnoringException(callableStatement);
	//}
	//fine LP 20240828 - PGNTACWS-22

	// inizio SR PGNTCORE-17
	public void doRollbackArchivioCarichiELG(String fileNameToElab, int progressivoFlusso) throws DaoException {
		CallableStatement callableStatement = null;	
		try {
			callableStatement = prepareCall(Routines.PYELGSP_DEL.routine());
			callableStatement.setString(1, fileNameToElab);
			callableStatement.setLong(2, progressivoFlusso);
			callableStatement.execute();
		} catch (SQLException x) {
			throw new DaoException(x);
		} catch (IllegalArgumentException x) {
			throw new DaoException(x);
		} catch (HelperException x) {
			throw new DaoException(x);
		} finally {
			if(callableStatement != null) {
				try {
					callableStatement.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}	
	}
	//fine SR PGNTCORE-17

	
}