package com.seda.payer.core.recupera.iuv.bean;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;

import com.seda.data.dao.ModelAttributes;

public class PosizioneIuv extends ModelAttributes implements Serializable{

	
	private static final long serialVersionUID = 1L;
	
	private String identificativoDominio;
	private String codiceEnte;
	private String codiceIuv;
	private String identificativoFlusso;
	private Date dataCreazione;	//date
	private String tipoRecord;
	private String identificativoDocumento;
	private String numeroRata;
	private Date dataScadenza;	//date 
	private String codiceFiscale;
	private BigDecimal importo;	//decimal(15,0)
	private String denominazioneDebitore;
	private String indirizzoContribuente;
	private String localitaContribuente;
	private String provinciaContribuente;
	private String flagAnnullamento;
	private Timestamp dataAggiornamento;	//datetime	//TODO da verificare
	private String codiceIban;
	private String codiceIuv_Iuv;
	// PG200120 introduzione flagPagamento per controllo pagamento eseguito
	private String flagPagato;
	private String codiceIban2;
	private String causale;
	private String cespite;
	private String annoRif;
	private String codiceUtente;
	private String codiceSocieta;
	private String chiaveEnte;
	private String tipologiaServizio;
	//inizio LP PG200370
	private String tassonomia;
	//fine LP PG200370
	//inizio SB PG210170
	private String targa;
	private String dataScadenzaVerbale;
	private String numeroVerbale;
	private String numeroBollettinoCDS;
	//fine SB PG210170
	
	public PosizioneIuv(){
	}
	
	public PosizioneIuv(String identificativoDominio, String codiceEnte,
			String codiceIuv, String identificativoFlusso, Date dataCreazione,
			String tipoRecord, String identificativoDocumento,
			String numeroRata, Date dataScadenza, String codiceFiscale,
			BigDecimal importo, String denominazioneDebitore,
			String indirizzoContribuente, String localitaContribuente,
			String provinciaContribuente, String flagAnnullamento,
			Timestamp dataAggiornamento, String codiceIban, String codiceIuv_Iuv, String flagPagato) {
		super();
		this.identificativoDominio = identificativoDominio;
		this.codiceEnte = codiceEnte;
		this.codiceIuv = codiceIuv;
		this.identificativoFlusso = identificativoFlusso;
		this.dataCreazione = dataCreazione;
		this.tipoRecord = tipoRecord;
		this.identificativoDocumento = identificativoDocumento;
		this.numeroRata = numeroRata;
		this.dataScadenza = dataScadenza;
		this.codiceFiscale = codiceFiscale;
		this.importo = importo;
		this.denominazioneDebitore = denominazioneDebitore;
		this.indirizzoContribuente = indirizzoContribuente;
		this.localitaContribuente = localitaContribuente;
		this.provinciaContribuente = provinciaContribuente;
		this.flagAnnullamento = flagAnnullamento;
		this.dataAggiornamento = dataAggiornamento;
		this.codiceIban = codiceIban;
		this.codiceIuv_Iuv = codiceIuv_Iuv;
		// PG200120 introduzione flagPagamento per controllo pagamento eseguito
		this.flagPagato = flagPagato;
	}

	public PosizioneIuv(String identificativoDominio, String codiceEnte,
			String codiceIuv, String identificativoFlusso, Date dataCreazione,
			String tipoRecord, String identificativoDocumento,
			String numeroRata, Date dataScadenza, String codiceFiscale,
			BigDecimal importo, String denominazioneDebitore,
			String indirizzoContribuente, String localitaContribuente,
			String provinciaContribuente, String flagAnnullamento,
			Timestamp dataAggiornamento, String codiceIban, String codiceIuv_Iuv, String flagPagato,
		//inizio LP PG200370
			//String codiceIban2, String causale, String cespite, String annoRif, String codiceUtente, String codiceSocieta, String chiaveEnte, String tipologiaServizio) {
			String codiceIban2, String causale, String cespite, String annoRif, String codiceUtente, String codiceSocieta, String chiaveEnte, String tipologiaServizio
			, String tassonomia, String targa, String dataScadenzaVerbale, String numeroVerbale, String numeroBollettinoCDS
		) {
		//fine LP PG200370
		super();
		this.identificativoDominio = identificativoDominio;
		this.codiceEnte = codiceEnte;
		this.codiceIuv = codiceIuv;
		this.identificativoFlusso = identificativoFlusso;
		this.dataCreazione = dataCreazione;
		this.tipoRecord = tipoRecord;
		this.identificativoDocumento = identificativoDocumento;
		this.numeroRata = numeroRata;
		this.dataScadenza = dataScadenza;
		this.codiceFiscale = codiceFiscale;
		this.importo = importo;
		this.denominazioneDebitore = denominazioneDebitore;
		this.indirizzoContribuente = indirizzoContribuente;
		this.localitaContribuente = localitaContribuente;
		this.provinciaContribuente = provinciaContribuente;
		this.flagAnnullamento = flagAnnullamento;
		this.dataAggiornamento = dataAggiornamento;
		this.codiceIban = codiceIban;
		this.codiceIuv_Iuv = codiceIuv_Iuv;
		// PG200120 introduzione flagPagamento per controllo pagamento eseguito
		this.flagPagato = flagPagato;
		this.codiceIban2 = codiceIban2;
		this.causale = causale;
		this.cespite = cespite;
		this.annoRif = annoRif;
		this.codiceUtente = codiceUtente;
		this.codiceSocieta = codiceSocieta;
		this.chiaveEnte = chiaveEnte;
		this.tipologiaServizio = tipologiaServizio;
		//inizio LP PG200370
		this.tassonomia = tassonomia;
		//fine LP PG200370
		//inizio SB PG210170
		this.targa = targa;
		this.dataScadenzaVerbale = dataScadenzaVerbale;
		this.numeroBollettinoCDS = numeroBollettinoCDS;
		this.numeroVerbale = numeroVerbale;
		//fine SB PG210170
	}

	public String getIdentificativoDominio() {
		return identificativoDominio;
	}

	public void setIdentificativoDominio(String identificativoDominio) {
		this.identificativoDominio = identificativoDominio;
	}

	public String getCodiceEnte() {
		return codiceEnte;
	}

	public void setCodiceEnte(String codiceEnte) {
		this.codiceEnte = codiceEnte;
	}

	public String getCodiceIuv() {
		return codiceIuv;
	}

	public void setCodiceIuv(String codiceIuv) {
		this.codiceIuv = codiceIuv;
	}

	public String getIdentificativoFlusso() {
		return identificativoFlusso;
	}

	public void setIdentificativoFlusso(String identificativoFlusso) {
		this.identificativoFlusso = identificativoFlusso;
	}

	public Date getDataCreazione() {
		return dataCreazione;
	}

	public void setDataCreazione(Date dataCreazione) {
		this.dataCreazione = dataCreazione;
	}

	public String getTipoRecord() {
		return tipoRecord;
	}

	public void setTipoRecord(String tipoRecord) {
		this.tipoRecord = tipoRecord;
	}

	public String getIdentificativoDocumento() {
		return identificativoDocumento;
	}

	public void setIdentificativoDocumento(String identificativoDocumento) {
		this.identificativoDocumento = identificativoDocumento;
	}

	public String getNumeroRata() {
		return numeroRata;
	}

	public void setNumeroRata(String numeroRata) {
		this.numeroRata = numeroRata;
	}

	public Date getDataScadenza() {
		return dataScadenza;
	}

	public void setDataScadenza(Date dataScadenza) {
		this.dataScadenza = dataScadenza;
	}

	public String getCodiceFiscale() {
		return codiceFiscale;
	}

	public void setCodiceFiscale(String codiceFiscale) {
		this.codiceFiscale = codiceFiscale;
	}

	public BigDecimal getImporto() {
		return importo;
	}

	public void setImporto(BigDecimal importo) {
		this.importo = importo;
	}

	public String getDenominazioneDebitore() {
		return denominazioneDebitore;
	}

	public void setDenominazioneDebitore(String denominazioneDebitore) {
		this.denominazioneDebitore = denominazioneDebitore;
	}

	public String getIndirizzoContribuente() {
		return indirizzoContribuente;
	}

	public void setIndirizzoContribuente(String indirizzoContribuente) {
		this.indirizzoContribuente = indirizzoContribuente;
	}

	public String getLocalitaContribuente() {
		return localitaContribuente;
	}

	public void setLocalitaContribuente(String localitaContribuente) {
		this.localitaContribuente = localitaContribuente;
	}

	public String getProvinciaContribuente() {
		return provinciaContribuente;
	}

	public void setProvinciaContribuente(String provinciaContribuente) {
		this.provinciaContribuente = provinciaContribuente;
	}

	public String getFlagAnnullamento() {
		return flagAnnullamento;
	}

	public void setFlagAnnullamento(String flagAnnullamento) {
		this.flagAnnullamento = flagAnnullamento;
	}

	public Timestamp getDataAggiornamento() {
		return dataAggiornamento;
	}

	public void setDataAggiornamento(Timestamp dataAggiornamento) {
		this.dataAggiornamento = dataAggiornamento;
	}

	public String getCodiceIban() {
		return codiceIban;
	}

	public void setCodiceIban(String codiceIban) {
		this.codiceIban = codiceIban;
	}

	public String getCodiceIuv_Iuv() {
		return codiceIuv_Iuv;
	}

	public void setCodiceIuv_Iuv(String codiceIuvIuv) {
		codiceIuv_Iuv = codiceIuvIuv;
	}
	
	public String getFlagPagato() {
		return flagPagato;
	}

	public void setFlagPagato(String flagPagato) {
		this.flagPagato = flagPagato;
	}


	@Override
	public String toString() {
		return "PosizioneIuv [codiceEnte=" + codiceEnte + ", codiceFiscale="
				+ codiceFiscale + ", codiceIban=" + codiceIban + ", codiceIuv=" + codiceIuv
				+ ", codiceIuv_Iuv=" + codiceIuv_Iuv
				+ ", dataAggiornamento=" + dataAggiornamento
				+ ", dataCreazione=" + dataCreazione + ", dataScadenza="
				+ dataScadenza + ", denominazioneDebitore="
				+ denominazioneDebitore + ", flagAnnullamento="
				+ flagAnnullamento + ", identificativoDocumento="
				+ identificativoDocumento + ", identificativoDominio="
				+ identificativoDominio + ", identificativoFlusso="
				+ identificativoFlusso + ", importo=" + importo
				+ ", indirizzoContribuente=" + indirizzoContribuente
				+ ", localitaContribuente=" + localitaContribuente
				+ ", numeroRata=" + numeroRata + ", provinciaContribuente="
				+ provinciaContribuente + ", tipoRecord=" + tipoRecord + ", codiceIban2="
				+ codiceIban2 + ", causale=" + causale + ", cespite=" + cespite
				+ ", anno=" + annoRif + "]";
	}

	public String getCodiceIban2() {
		return codiceIban2;
	}

	public void setCodiceIban2(String codiceIban2) {
		this.codiceIban2 = codiceIban2;
	}

	public String getCausale() {
		return causale;
	}

	public void setCausale(String causale) {
		this.causale = causale;
	}

	public String getCespite() {
		return cespite;
	}

	public void setCespite(String cespite) {
		this.cespite = cespite;
	}

	public String getAnnoRif() {
		return annoRif;
	}

	public void setAnnoRif(String annoRif) {
		this.annoRif = annoRif;
	}

	public String getCodiceUtente() {
		return codiceUtente;
	}

	public void setCodiceUtente(String codiceUtente) {
		this.codiceUtente = codiceUtente;
	}

	public String getTipologiaServizio() {
		return tipologiaServizio;
	}

	public void setTipologiaServizio(String tipologiaServizio) {
		this.tipologiaServizio = tipologiaServizio;
	}

	public String getCodiceSocieta() {
		return codiceSocieta;
	}

	public void setCodiceSocieta(String codiceSocieta) {
		this.codiceSocieta = codiceSocieta;
	}

	public String getChiaveEnte() {
		return chiaveEnte;
	}

	public void setChiaveEnte(String chiaveEnte) {
		this.chiaveEnte = chiaveEnte;
	}

	//inizio LP PG200370
	public String getTassonomia() {
		return tassonomia;
	}

	public void setTassonomia(String tassonomia) {
		this.tassonomia = tassonomia;
	}
	//fine LP PG200370

	public String getTarga() {
		return targa;
	}

	public String getDataScadenzaVerbale() {
		return dataScadenzaVerbale;
	}

	public String getNumeroVerbale() {
		return numeroVerbale;
	}

	public String getNumeroBollettinoCDS() {
		return numeroBollettinoCDS;
	}

	public void setTarga(String targa) {
		this.targa = targa;
	}

	public void setDataScadenzaVerbale(String dataScadenzaVerbale) {
		this.dataScadenzaVerbale = dataScadenzaVerbale;
	}

	public void setNumeroVerbale(String numeroVerbale) {
		this.numeroVerbale = numeroVerbale;
	}

	public void setNumeroBollettinoCDS(String numeroBollettinoCDS) {
		this.numeroBollettinoCDS = numeroBollettinoCDS;
	}
}
