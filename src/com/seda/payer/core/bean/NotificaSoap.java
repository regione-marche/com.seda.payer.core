package com.seda.payer.core.bean;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

public class NotificaSoap implements Serializable{

	// YLM PG22XX07 INI
//	NEX_KTRAKTRA chiaveTransazione
//	NEX_CSOCCSOC codiceSocieta
//	SOC_DSOCDSOC descrizioneSocieta
//	NEX_CUTECUTE codiceUtente
//	UTE_DUTEDUTE descrizioneUtente
//	NEX_KANEKENT codiceEnte
//	ANE_DANEDENT descrizioneEnte
//	NEX_CNEXPORT urlPortale
//	NEX_CNEXNDOC numeroDocumento
//	NEX_CNEXNAVV numeroAvvisoPagoPA
//	NEX_CNEXCFIS codiceFiscale
//	NEX_GNEXDNOT dataNotifica
//	NEX_GNEXDRNO dataRispostaNotifica
//	NEX_CNEXUESI esito
//	NEX_INEXIMPO importoPagato
//	NEX_GNEXDPAG dataPagamento
//	NEX_CNEXRXML xmlRicevuta
//	NEX_NNEXCORR numeroTentativiInvioNotifica
//	NEX_KTDTKTDT chiaveDettaglioTransazione
//	NEX_NNEXCOAN numeroTentativiInvioNotificaAnnulloTecnico
//	NEX_GNEXDIAN dataInvioNotificaAnnulloTecnico
//	NEX_GNEXDRAN dataRispostaNotificaAnnulloTecnico
//	NEX_CNEXUEAN ultimoEsitoNotificaAnnullo
//	NEX_CNEXRRAN xmlRichiestaRevoca
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final String chiaveTransazione;
	private final String codiceSocieta;
	private final String descrizioneSocieta;
	private final String codiceUtente;
	private final String descrizioneUtente;
	private final String codiceEnte;
	private final String descrizioneEnte;
	private final String urlPortale;
	private final String numeroDocumento;
	private final String numeroAvvisoPagoPA;
	private final String codiceFiscale;
	private final Timestamp dataNotifica;
	private final Timestamp dataRispostaNotifica;
	private final String esito;	
	private final BigDecimal importoPagato;
	private final Timestamp dataPagamento;
	private final String xmlRicevuta;
	private final int numeroTentativiInvioNotifica;
	private final String chiaveDettaglioTransazione;
	private final int numeroTentativiInvioNotificaAnnulloTecnico;
	private final Timestamp dataInvioNotificaAnnulloTecnico;
	private final Timestamp dataRispostaNotificaAnnulloTecnico;
	private final String ultimoEsitoNotificaAnnullo;
	private final String xmlRichiestaRevoca;
	
	public NotificaSoap(ResultSet rs) throws SQLException {
		
		this.chiaveTransazione= rs.getString("NEX_KTRAKTRA");
		this.codiceSocieta= rs.getString("NEX_CSOCCSOC");
		this.descrizioneSocieta= rs.getString("SOC_DSOCDSOC");
		this.codiceUtente= rs.getString("NEX_CUTECUTE");
		this.descrizioneUtente= rs.getString("UTE_DUTEDUTE");
		this.codiceEnte= rs.getString("NEX_KANEKENT");
		this.descrizioneEnte= rs.getString("ANE_DANEDENT");
		this.urlPortale= rs.getString("NEX_CNEXPORT");
		this.numeroDocumento= rs.getString("NEX_CNEXNDOC");
		this.numeroAvvisoPagoPA= rs.getString("NEX_CNEXNAVV");
		this.codiceFiscale= rs.getString("NEX_CNEXCFIS");
		this.dataNotifica= rs.getTimestamp("NEX_GNEXDNOT");
		this.dataRispostaNotifica= rs.getTimestamp("NEX_GNEXDRNO");
		this.esito= rs.getString("NEX_CNEXUESI");
		this.importoPagato= rs.getBigDecimal("NEX_INEXIMPO");
		this.dataPagamento= rs.getTimestamp("NEX_GNEXDPAG");
		this.xmlRicevuta= rs.getString("NEX_CNEXRXML");
		this.numeroTentativiInvioNotifica= rs.getInt("NEX_NNEXCORR");
		this.chiaveDettaglioTransazione= rs.getString("NEX_KTDTKTDT");
		this.numeroTentativiInvioNotificaAnnulloTecnico= rs.getInt("NEX_NNEXCOAN");
		this.dataInvioNotificaAnnulloTecnico= rs.getTimestamp("NEX_GNEXDIAN");
		this.dataRispostaNotificaAnnulloTecnico= rs.getTimestamp("NEX_GNEXDRAN");
		this.ultimoEsitoNotificaAnnullo= rs.getString("NEX_CNEXUEAN");
		this.xmlRichiestaRevoca= rs.getString("NEX_CNEXRRAN");
	}
	
	public NotificaSoap() {this.chiaveTransazione = "";
	this.codiceSocieta = "";
	this.descrizioneSocieta = "";
	this.codiceUtente = "";
	this.descrizioneUtente = "";
	this.codiceEnte = "";
	this.descrizioneEnte = "";
	this.urlPortale = "";
	this.numeroDocumento = "";
	this.numeroAvvisoPagoPA = "";
	this.codiceFiscale = "";
	this.dataNotifica = null;
	this.dataRispostaNotifica = null;
	this.esito = "";
	this.importoPagato = null;
	this.dataPagamento = null;
	this.xmlRicevuta = "";
	this.numeroTentativiInvioNotifica = 0;
	this.chiaveDettaglioTransazione = "";
	this.numeroTentativiInvioNotificaAnnulloTecnico = 0;
	this.dataInvioNotificaAnnulloTecnico = null;
	this.dataRispostaNotificaAnnulloTecnico = null;
	this.ultimoEsitoNotificaAnnullo = "";
	this.xmlRichiestaRevoca = "";
	}

	/**
	 * @return the chiaveTransazione
	 */
	public String getChiaveTransazione() {
		return chiaveTransazione;
	}

	/**
	 * @return the codiceSocieta
	 */
	public String getCodiceSocieta() {
		return codiceSocieta;
	}

	/**
	 * @return the descrizioneSocieta
	 */
	public String getDescrizioneSocieta() {
		return descrizioneSocieta;
	}

	/**
	 * @return the codiceUtente
	 */
	public String getCodiceUtente() {
		return codiceUtente;
	}

	/**
	 * @return the descrizioneUtente
	 */
	public String getDescrizioneUtente() {
		return descrizioneUtente;
	}

	/**
	 * @return the codiceEnte
	 */
	public String getCodiceEnte() {
		return codiceEnte;
	}

	/**
	 * @return the descrizioneEnte
	 */
	public String getDescrizioneEnte() {
		return descrizioneEnte;
	}

	/**
	 * @return the urlPortale
	 */
	public String getUrlPortale() {
		return urlPortale;
	}

	/**
	 * @return the numeroDocumento
	 */
	public String getNumeroDocumento() {
		return numeroDocumento;
	}

	/**
	 * @return the numeroAvvisoPagoPA
	 */
	public String getNumeroAvvisoPagoPA() {
		return numeroAvvisoPagoPA;
	}

	/**
	 * @return the codiceFiscale
	 */
	public String getCodiceFiscale() {
		return codiceFiscale;
	}

	/**
	 * @return the dataNotifica
	 */
	public Timestamp getDataNotifica() {
		return dataNotifica;
	}

	/**
	 * @return the dataRispostaNotifica
	 */
	public Timestamp getDataRispostaNotifica() {
		return dataRispostaNotifica;
	}

	/**
	 * @return the esito
	 */
	public String getEsito() {
		return esito;
	}

	/**
	 * @return the importoPagato
	 */
	public BigDecimal getImportoPagato() {
		return importoPagato;
	}

	/**
	 * @return the dataPagamento
	 */
	public Timestamp getDataPagamento() {
		return dataPagamento;
	}

	/**
	 * @return the xmlRicevuta
	 */
	public String getXmlRicevuta() {
		return xmlRicevuta;
	}

	/**
	 * @return the numeroTentativiInvioNotifica
	 */
	public int getNumeroTentativiInvioNotifica() {
		return numeroTentativiInvioNotifica;
	}

	/**
	 * @return the chiaveDettaglioTransazione
	 */
	public String getChiaveDettaglioTransazione() {
		return chiaveDettaglioTransazione;
	}

	/**
	 * @return the numeroTentativiInvioNotificaAnnulloTecnico
	 */
	public int getNumeroTentativiInvioNotificaAnnulloTecnico() {
		return numeroTentativiInvioNotificaAnnulloTecnico;
	}

	/**
	 * @return the dataInvioNotificaAnnulloTecnico
	 */
	public Timestamp getDataInvioNotificaAnnulloTecnico() {
		return dataInvioNotificaAnnulloTecnico;
	}

	/**
	 * @return the dataRispostaNotificaAnnulloTecnico
	 */
	public Timestamp getDataRispostaNotificaAnnulloTecnico() {
		return dataRispostaNotificaAnnulloTecnico;
	}

	/**
	 * @return the ultimoEsitoNotificaAnnullo
	 */
	public String getUltimoEsitoNotificaAnnullo() {
		return ultimoEsitoNotificaAnnullo;
	}

	/**
	 * @return the xmlRichiestaRevoca
	 */
	public String getXmlRichiestaRevoca() {
		return xmlRichiestaRevoca;
	}
	

	// YLM PG22XX07 FINE
	
}
