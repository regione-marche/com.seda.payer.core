package com.seda.payer.core.bean;

import java.math.BigDecimal;
import java.util.Date;

public class ArchivioCarichiDocumento {
	//PYEH1TB
	
	private Long progressivoFlusso;
	private String tipoRecord;
	private String codiceUtente;
	private Date dataCreazioneFlusso;
	private String tipoServizio;
	private String codiceEnte;
	private String tipoUfficio;
	private String codiceUfficio;
	private String impostaServizio;
    private String numeroDocumento;
    private String codiceFiscale;
    private String tipoDocumentoRuoli;
    private String flagRiforma;
    private String annoEmissione;
    private String numeroEmissione;
    private String idCartellaMinisteriale;
    private String flagRendicontato;
    private Date dataNotifica;
    private String tipoDocumentoEntrate;
    private String numeroBollettinoPagoPA;
    private BigDecimal impBollettinoTotaleDocumento;
    private String codiceEnteEntrate;
    private String flagSospensione;
    private String flagProcEsecutive;
    private String flagCartellaInps;
    private String flagCartellaMaggiorRateazione;
    private String codiceTipologiaServizio;
    private String tombstoned;
    private String ibanAccredito;
    private String flagFatturazioneElettronica;
    private String identificativoUnivocoVersamento;
    private String ibanAppoggio;
    private String tassonomia; //PG200360 LP

    public ArchivioCarichiDocumento() {
    }
    
	public ArchivioCarichiDocumento(Long progressivoFlusso, String tipoRecord,
			String codiceUtente, Date dataCreazioneFlusso,
			String tipoServizio, String codiceEnte, String tipoUfficio,
			String codiceUfficio, String impostaServizio,
			String numeroDocumento, String codiceFiscale,
			String tipoDocumentoRuoli, String flagRiforma,
			String annoEmissione, String numeroEmissione,
			String idCartellaMinisteriale, String flagRendicontato,
			Date dataNotifica, String tipoDocumentoEntrate,
			String numeroBollettinoPagoPA,
			BigDecimal impBollettinoTotaleDocumento, String codiceEnteEntrate,
			String flagSospensione, String flagProcEsecutive,
			String flagCartellaInps, String flagCartellaMaggiorRateazione,
			String codiceTipologiaServizio, String tombstoned,
			String ibanAccredito, String flagFatturazioneElettronica,
			String identificativoUnivocoVersamento, String ibanAppoggio
	        , String tassonomia //PG200360 LP
	) {        
		super();
		this.progressivoFlusso = progressivoFlusso;
		this.tipoRecord = tipoRecord;
		this.codiceUtente = codiceUtente;
		this.dataCreazioneFlusso = dataCreazioneFlusso;
		this.tipoServizio = tipoServizio;
		this.codiceEnte = codiceEnte;
		this.tipoUfficio = tipoUfficio;
		this.codiceUfficio = codiceUfficio;
		this.impostaServizio = impostaServizio;
		this.numeroDocumento = numeroDocumento;
		this.codiceFiscale = codiceFiscale;
		this.tipoDocumentoRuoli = tipoDocumentoRuoli;
		this.flagRiforma = flagRiforma;
		this.annoEmissione = annoEmissione;
		this.numeroEmissione = numeroEmissione;
		this.idCartellaMinisteriale = idCartellaMinisteriale;
		this.flagRendicontato = flagRendicontato;
		this.dataNotifica = dataNotifica;
		this.tipoDocumentoEntrate = tipoDocumentoEntrate;
		this.numeroBollettinoPagoPA = numeroBollettinoPagoPA;
		this.impBollettinoTotaleDocumento = impBollettinoTotaleDocumento;
		this.codiceEnteEntrate = codiceEnteEntrate;
		this.flagSospensione = flagSospensione;
		this.flagProcEsecutive = flagProcEsecutive;
		this.flagCartellaInps = flagCartellaInps;
		this.flagCartellaMaggiorRateazione = flagCartellaMaggiorRateazione;
		this.codiceTipologiaServizio = codiceTipologiaServizio;
		this.tombstoned = tombstoned;
		this.ibanAccredito = ibanAccredito;
		this.flagFatturazioneElettronica = flagFatturazioneElettronica;
		this.identificativoUnivocoVersamento = identificativoUnivocoVersamento;
		this.ibanAppoggio = ibanAppoggio;
	}



	public Long getProgressivoFlusso() {
		return progressivoFlusso;
	}

	public void setProgressivoFlusso(Long progressivoFlusso) {
		this.progressivoFlusso = progressivoFlusso;
	}

	public String getTipoRecord() {
		return tipoRecord;
	}

	public void setTipoRecord(String tipoRecord) {
		this.tipoRecord = tipoRecord;
	}

	public String getCodiceUtente() {
		return codiceUtente;
	}

	public void setCodiceUtente(String codiceUtente) {
		this.codiceUtente = codiceUtente;
	}

	public Date getDataCreazioneFlusso() {
		return dataCreazioneFlusso;
	}

	public void setDataCreazioneFlusso(Date dataCreazioneFlusso) {
		this.dataCreazioneFlusso = dataCreazioneFlusso;
	}

	public String getTipoServizio() {
		return tipoServizio;
	}

	public void setTipoServizio(String tipoServizio) {
		this.tipoServizio = tipoServizio;
	}

	public String getCodiceEnte() {
		return codiceEnte;
	}

	public void setCodiceEnte(String codiceEnte) {
		this.codiceEnte = codiceEnte;
	}

	public String getTipoUfficio() {
		return tipoUfficio;
	}

	public void setTipoUfficio(String tipoUfficio) {
		this.tipoUfficio = tipoUfficio;
	}

	public String getCodiceUfficio() {
		return codiceUfficio;
	}

	public void setCodiceUfficio(String codiceUfficio) {
		this.codiceUfficio = codiceUfficio;
	}

	public String getImpostaServizio() {
		return impostaServizio;
	}

	public void setImpostaServizio(String impostaServizio) {
		this.impostaServizio = impostaServizio;
	}

	public String getNumeroDocumento() {
		return numeroDocumento;
	}

	public void setNumeroDocumento(String numeroDocumento) {
		this.numeroDocumento = numeroDocumento;
	}

	public String getCodiceFiscale() {
		return codiceFiscale;
	}

	public void setCodiceFiscale(String codiceFiscale) {
		this.codiceFiscale = codiceFiscale;
	}

	public String getTipoDocumentoRuoli() {
		return tipoDocumentoRuoli;
	}

	public void setTipoDocumentoRuoli(String tipoDocumentoRuoli) {
		this.tipoDocumentoRuoli = tipoDocumentoRuoli;
	}

	public String getFlagRiforma() {
		return flagRiforma;
	}

	public void setFlagRiforma(String flagRiforma) {
		this.flagRiforma = flagRiforma;
	}

	public String getAnnoEmissione() {
		return annoEmissione;
	}

	public void setAnnoEmissione(String annoEmissione) {
		this.annoEmissione = annoEmissione;
	}

	public String getNumeroEmissione() {
		return numeroEmissione;
	}

	public void setNumeroEmissione(String numeroEmissione) {
		this.numeroEmissione = numeroEmissione;
	}

	public String getIdCartellaMinisteriale() {
		return idCartellaMinisteriale;
	}

	public void setIdCartellaMinisteriale(String idCartellaMinisteriale) {
		this.idCartellaMinisteriale = idCartellaMinisteriale;
	}

	public String getFlagRendicontato() {
		return flagRendicontato;
	}

	public void setFlagRendicontato(String flagRendicontato) {
		this.flagRendicontato = flagRendicontato;
	}

	public Date getDataNotifica() {
		return dataNotifica;
	}

	public void setDataNotifica(Date dataNotifica) {
		this.dataNotifica = dataNotifica;
	}

	public String getTipoDocumentoEntrate() {
		return tipoDocumentoEntrate;
	}

	public void setTipoDocumentoEntrate(String tipoDocumentoEntrate) {
		this.tipoDocumentoEntrate = tipoDocumentoEntrate;
	}

	public String getNumeroBollettinoPagoPA() {
		return numeroBollettinoPagoPA;
	}

	public void setNumeroBollettinoPagoPA(String numeroBollettinoPagoPA) {
		this.numeroBollettinoPagoPA = numeroBollettinoPagoPA;
	}

	public BigDecimal getImpBollettinoTotaleDocumento() {
		return impBollettinoTotaleDocumento;
	}

	public void setImpBollettinoTotaleDocumento(
			BigDecimal impBollettinoTotaleDocumento) {
		this.impBollettinoTotaleDocumento = impBollettinoTotaleDocumento;
	}

	public String getCodiceEnteEntrate() {
		return codiceEnteEntrate;
	}

	public void setCodiceEnteEntrate(String codiceEnteEntrate) {
		this.codiceEnteEntrate = codiceEnteEntrate;
	}

	public String getFlagSospensione() {
		return flagSospensione;
	}

	public void setFlagSospensione(String flagSospensione) {
		this.flagSospensione = flagSospensione;
	}

	public String getFlagProcEsecutive() {
		return flagProcEsecutive;
	}

	public void setFlagProcEsecutive(String flagProcEsecutive) {
		this.flagProcEsecutive = flagProcEsecutive;
	}

	public String getFlagCartellaInps() {
		return flagCartellaInps;
	}

	public void setFlagCartellaInps(String flagCartellaInps) {
		this.flagCartellaInps = flagCartellaInps;
	}

	public String getFlagCartellaMaggiorRateazione() {
		return flagCartellaMaggiorRateazione;
	}

	public void setFlagCartellaMaggiorRateazione(
			String flagCartellaMaggiorRateazione) {
		this.flagCartellaMaggiorRateazione = flagCartellaMaggiorRateazione;
	}

	public String getCodiceTipologiaServizio() {
		return codiceTipologiaServizio;
	}

	public void setCodiceTipologiaServizio(String codiceTipologiaServizio) {
		this.codiceTipologiaServizio = codiceTipologiaServizio;
	}

	public String getTombstoned() {
		return tombstoned;
	}

	public void setTombstoned(String tombstoned) {
		this.tombstoned = tombstoned;
	}

	public String getIbanAccredito() {
		return ibanAccredito;
	}

	public void setIbanAccredito(String ibanAccredito) {
		this.ibanAccredito = ibanAccredito;
	}

	public String getFlagFatturazioneElettronica() {
		return flagFatturazioneElettronica;
	}

	public void setFlagFatturazioneElettronica(String flagFatturazioneElettronica) {
		this.flagFatturazioneElettronica = flagFatturazioneElettronica;
	}

	public String getIdentificativoUnivocoVersamento() {
		return identificativoUnivocoVersamento;
	}

	public void setIdentificativoUnivocoVersamento(
			String identificativoUnivocoVersamento) {
		this.identificativoUnivocoVersamento = identificativoUnivocoVersamento;
	}
	
	public String getIbanAppoggio() {
		return ibanAppoggio;
	}

	public void setIbanAppoggio(String ibanAppoggio) {
		this.ibanAppoggio = ibanAppoggio;
	}

	//inizio LP PG200360
	public String getTassonomia() {
		return tassonomia;
	}

	public void setTassonomia(String tassonomia) {
		this.tassonomia = tassonomia;
	}
	//fine LP PG200360
}
