package com.seda.payer.core.bean;

import java.io.Serializable;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class ConfigurazioneBlackBoxPos implements Serializable{
	
private static final long serialVersionUID = 1L;
	
	private String codiceIdentificativoDominio;
	private String codiceEnte;
	private String numeroAvviso;
	private String codiceIdentificativoFlusso;
	private Calendar dataCreazione;
	private String tipoRecord;
	private String codiceIdentificativoDocumento;
	private String numeroRata;
	private Calendar dataScadenza;
	private String codiceFiscale;
	private Double importo;
	private String denominazioneDebitore;
	private String indirizzoContribuente;
	private String localitaContribuente;
	private String provinciaContribuente;
	private String flagAnnullamento;
	private Calendar dataAggiornamentoRecord;
	private String codiceIbanAccredito;
	private String codiceIuv;
	private String flagPagato;
	private Double importoPagato;
	private String codiceTipologiaServizio;
	private Calendar dataPagamento;
	private int annoRiferimento;
	
	private String codiceIBAN2;
	private String causaleServizio;
	private String cespite;
	private String annoRif;
	private String cittaCAP;
	private String codiceUtente;
	private String codiceSocieta;
	private String chiaveEnte;
	//inizio LP PG200370
	private String tassonomia;
	//fine LP PG200370
	
	//inizio SB PG210170
	private String targa;
	private String dataVerbale;
	private String numeroVerbale;
	private String numBollettinoCDS;

	private String flagJppa;
	//fine SB PG210170
	
	public ConfigurazioneBlackBoxPos() {}
	
	public ConfigurazioneBlackBoxPos(ResultSet data) throws SQLException
    {
    	if (data == null)
    		return;
    	setCodiceIdentificativoDominio(data.getString("DOC_CDOCCIDD"));
    	setCodiceEnte(data.getString("DOC_CDOCCENT"));
    	setNumeroAvviso(data.getString("DOC_CDOCCIUV"));
    	setCodiceIdentificativoFlusso(data.getString("DOC_CDOCCIDF"));
    	setDataCreazione(data.getDate("DOC_GDOCDTCR")!=null?sqlDateToCalendar(data.getDate("DOC_GDOCDTCR").toString()):null);
    	setTipoRecord(data.getString("DOC_CDOCCTPR"));
    	setCodiceIdentificativoDocumento(data.getString("DOC_CDOCCDOC"));
    	setNumeroRata(data.getString("DOC_CDOCNRAT"));
    	setDataScadenza(data.getDate("DOC_GDOCSCAD")!=null?sqlDateToCalendar(data.getDate("DOC_GDOCSCAD").toString()):null);
    	setCodiceFiscale(data.getString("DOC_CDOCCFIS"));
    	setImporto(data.getDouble("DOC_IDOCIIMP"));
    	setDenominazioneDebitore(data.getString("DOC_DDOCDENO"));
    	setIndirizzoContribuente(data.getString("DOC_DDOCINDI"));
    	setLocalitaContribuente(data.getString("DOC_DDOCCITT"));
    	setProvinciaContribuente(data.getString("DOC_CDOCPROV"));
    	setFlagAnnullamento(data.getString("DOC_FDOCFANN"));
    	setDataAggiornamentoRecord(data.getDate("DOC_GDOCDAGG")!=null?sqlDateToCalendar(data.getDate("DOC_GDOCDAGG").toString()):null);
    	setCodiceIbanAccredito(data.getString("DOC_CDOCIBAN"));
    	setCodiceIuv(data.getString("DOC_CDOCCIUV_IUV"));
    	setFlagPagato(data.getString("DOC_FDOCFPAG"));
    	setImportoPagato(data.getDouble("DOC_IDOCIIPG"));
    	setCodiceTipologiaServizio(data.getString("DOC_CDOCCTSE"));
    	setDataPagamento(data.getDate("DOC_GDOCDPAG")!=null?sqlDateToCalendar(data.getDate("DOC_GDOCDPAG").toString()):null);
    	setCodiceIBAN2(data.getString("DOC_CDOCIBA2"));
    	setCausaleServizio(data.getString("DOC_DDOCCAUS"));
    	setCespite(data.getString("DOC_DDOCCESP"));
    	setAnnoRif(data.getString("DOC_DDOCANNO"));
    	setCittaCAP(data.getString("DOC_CDOCCCAP"));
    	//inizio LP PG200370
    	setTassonomia(data.getString("DOC_CDOCTASS"));
    	//fine LP PG200370
    	// SR PGNTCORE-11 inizio
    	setFlagJppa(data.getString("DOC_FDOCJPPA"));
    	// SR PGNTCORE-11 fine
    }
	
	
	public ConfigurazioneBlackBoxPos(
			String codiceIdentificativoDominio,
			String codiceEnte,
			String numeroAvviso,
			String codiceIdentificativoFlusso,
			Calendar dataCreazione,
			String tipoRecord,
			String codiceIdentificativoDocumento,
			String numeroRata,
			Calendar dataScadenza,
			String codiceFiscale,
			Double importo,
			String denominazioneDebitore,
			String indirizzoContribuente,
			String localitaContribuente,
			String provinciaContribuente,
			String flagAnnullamento,
			Calendar dataAggiornamentoRecord,
			String codiceIbanAccredito,
			String codiceIuv,
			String flagPagato,
			Double importoPagato,
			String codiceTipologiaServizio,
			Calendar dataPagamento
			, String tassonomia //LP PG200370
			) {
		super();
		this.codiceIdentificativoDominio = codiceIdentificativoDominio;
		this.codiceEnte = codiceEnte;
		this.numeroAvviso=numeroAvviso;
		this.codiceIdentificativoFlusso = codiceIdentificativoFlusso;
		this.dataCreazione = dataCreazione;
		this.tipoRecord = tipoRecord;
		this.codiceIdentificativoDocumento = codiceIdentificativoDocumento;
		this.numeroRata = numeroRata;
		this.dataScadenza = dataScadenza;
		this.codiceFiscale = codiceFiscale;
		this.importo = importo;
		this.denominazioneDebitore = denominazioneDebitore;
		this.indirizzoContribuente = indirizzoContribuente;
		this.localitaContribuente = localitaContribuente;
		this.provinciaContribuente = provinciaContribuente;
		this.flagAnnullamento = flagAnnullamento;
		this.dataAggiornamentoRecord = dataAggiornamentoRecord;
		this.codiceIbanAccredito = codiceIbanAccredito;
		this.codiceIuv=codiceIuv;
		this.flagPagato = flagPagato;
		this.importoPagato = importoPagato;
		this.codiceTipologiaServizio=codiceTipologiaServizio;
		this.dataPagamento=dataPagamento;
		this.tassonomia = tassonomia; //LP PG200370
	}
	
	public ConfigurazioneBlackBoxPos(
			String codiceIdentificativoDominio,
			String codiceEnte,
			String numeroAvviso,
			String codiceIdentificativoFlusso,
			Calendar dataCreazione,
			String tipoRecord,
			String codiceIdentificativoDocumento,
			String numeroRata,
			Calendar dataScadenza,
			String codiceFiscale,
			Double importo,
			String denominazioneDebitore,
			String indirizzoContribuente,
			String localitaContribuente,
			String provinciaContribuente,
			String flagAnnullamento,
			Calendar dataAggiornamentoRecord,
			String codiceIbanAccredito,
			String codiceIuv,
			String flagPagato,
			Double importoPagato,
			String codiceTipologiaServizio,
			Calendar dataPagamento,
			String codiceIBAN2,
			String causaleServizio,
			String cespite,
			String annoRif,
			String cittaCAP,
			String codiceUtente,
			String codiceSocieta,
			String chiaveEnte
			, String tassonomia //LP PG200370
			) {
		super();
		this.codiceIdentificativoDominio = codiceIdentificativoDominio;
		this.codiceEnte = codiceEnte;
		this.numeroAvviso=numeroAvviso;
		this.codiceIdentificativoFlusso = codiceIdentificativoFlusso;
		this.dataCreazione = dataCreazione;
		this.tipoRecord = tipoRecord;
		this.codiceIdentificativoDocumento = codiceIdentificativoDocumento;
		this.numeroRata = numeroRata;
		this.dataScadenza = dataScadenza;
		this.codiceFiscale = codiceFiscale;
		this.importo = importo;
		this.denominazioneDebitore = denominazioneDebitore;
		this.indirizzoContribuente = indirizzoContribuente;
		this.localitaContribuente = localitaContribuente;
		this.provinciaContribuente = provinciaContribuente;
		this.flagAnnullamento = flagAnnullamento;
		this.dataAggiornamentoRecord = dataAggiornamentoRecord;
		this.codiceIbanAccredito = codiceIbanAccredito;
		this.codiceIuv=codiceIuv;
		this.flagPagato = flagPagato;
		this.importoPagato = importoPagato;
		this.codiceTipologiaServizio=codiceTipologiaServizio;
		this.dataPagamento=dataPagamento;
		this.codiceIBAN2=codiceIBAN2;
		this.causaleServizio=causaleServizio;
		this.cespite=cespite;
		this.annoRif=annoRif;
		this.cittaCAP=cittaCAP;
		this.codiceUtente=codiceUtente;
		this.codiceSocieta=codiceSocieta;
		this.chiaveEnte=chiaveEnte;
		this.tassonomia = tassonomia; //LP PG200370
		this.flagJppa = "N"; // SR PGNTCORE-11 
	}
	
	//inizio SB PG210170
	//Costruttore per Tipo CDS
	public ConfigurazioneBlackBoxPos(
			String codiceIdentificativoDominio,
			String codiceEnte,
			String numeroAvviso,
			String codiceIdentificativoFlusso,
			Calendar dataCreazione,
			String tipoRecord,
			String codiceIdentificativoDocumento,
			String numeroRata,
			Calendar dataScadenza,
			String codiceFiscale,
			Double importo,
			String denominazioneDebitore,
			String indirizzoContribuente,
			String localitaContribuente,
			String provinciaContribuente,
			String flagAnnullamento,
			Calendar dataAggiornamentoRecord,
			String codiceIbanAccredito,
			String codiceIuv,
			String flagPagato,
			Double importoPagato,
			String codiceTipologiaServizio,
			Calendar dataPagamento,
			String codiceIBAN2,
			String causaleServizio,
			String cespite,
			String annoRif,
			String cittaCAP,
			String codiceUtente,
			String codiceSocieta,
			String chiaveEnte
			, String tassonomia //LP PG200370
			, String targa
			, String dataVerbale
			, String numeroVerbale
			, String numBollettinoCDS
			) {
		super();
		this.codiceIdentificativoDominio = codiceIdentificativoDominio;
		this.codiceEnte = codiceEnte;
		this.numeroAvviso=numeroAvviso;
		this.codiceIdentificativoFlusso = codiceIdentificativoFlusso;
		this.dataCreazione = dataCreazione;
		this.tipoRecord = tipoRecord;
		this.codiceIdentificativoDocumento = codiceIdentificativoDocumento;
		this.numeroRata = numeroRata;
		this.dataScadenza = dataScadenza;
		this.codiceFiscale = codiceFiscale;
		this.importo = importo;
		this.denominazioneDebitore = denominazioneDebitore;
		this.indirizzoContribuente = indirizzoContribuente;
		this.localitaContribuente = localitaContribuente;
		this.provinciaContribuente = provinciaContribuente;
		this.flagAnnullamento = flagAnnullamento;
		this.dataAggiornamentoRecord = dataAggiornamentoRecord;
		this.codiceIbanAccredito = codiceIbanAccredito;
		this.codiceIuv=codiceIuv;
		this.flagPagato = flagPagato;
		this.importoPagato = importoPagato;
		this.codiceTipologiaServizio=codiceTipologiaServizio;
		this.dataPagamento=dataPagamento;
		this.codiceIBAN2=codiceIBAN2;
		this.causaleServizio=causaleServizio;
		this.cespite=cespite;
		this.annoRif=annoRif;
		this.cittaCAP=cittaCAP;
		this.codiceUtente=codiceUtente;
		this.codiceSocieta=codiceSocieta;
		this.chiaveEnte=chiaveEnte;
		this.tassonomia = tassonomia; //LP PG200370
		this.targa = targa;
		this.dataVerbale = dataVerbale;
		this.numeroVerbale = numeroVerbale;
		this.numBollettinoCDS = numBollettinoCDS;
	}
	//fine SB PG210170


	public String getCodiceIdentificativoDominio() {
		return codiceIdentificativoDominio;
	}
	public void setCodiceIdentificativoDominio(String codiceIdentificativoDominio) {
		this.codiceIdentificativoDominio = codiceIdentificativoDominio;
	}
	public String getCodiceEnte() {
		return codiceEnte;
	}
	public void setCodiceEnte(String codiceEnte) {
		this.codiceEnte = codiceEnte;
	}
	public String getNumeroAvviso() {
		return numeroAvviso;
	}
	public void setNumeroAvviso(String numeroAvviso) {
		this.numeroAvviso = numeroAvviso;
	}
	public String getCodiceIdentificativoFlusso() {
		return codiceIdentificativoFlusso;
	}
	public void setCodiceIdentificativoFlusso(String codiceIdentificativoFlusso) {
		this.codiceIdentificativoFlusso = codiceIdentificativoFlusso;
	}
	public Calendar getDataCreazione() {
		return dataCreazione;
	}
	public void setDataCreazione(Calendar dataCreazione) {
		this.dataCreazione = dataCreazione;
	}
	public String getTipoRecord() {
		return tipoRecord;
	}
	public void setTipoRecord(String tipoRecord) {
		this.tipoRecord = tipoRecord;
	}
	public String getCodiceIdentificativoDocumento() {
		return codiceIdentificativoDocumento;
	}
	public void setCodiceIdentificativoDocumento(String codiceIdentificativoDocumento) {
		this.codiceIdentificativoDocumento = codiceIdentificativoDocumento;
	}
	public String getNumeroRata() {
		return numeroRata;
	}
	public void setNumeroRata(String numeroRata) {
		this.numeroRata = numeroRata;
	}
	public Calendar getDataScadenza() {
		return dataScadenza;
	}
	public void setDataScadenza(Calendar dataScadenza) {
		this.dataScadenza = dataScadenza;
	}
	public String getCodiceFiscale() {
		return codiceFiscale;
	}
	public void setCodiceFiscale(String codiceFiscale) {
		this.codiceFiscale = codiceFiscale;
	}
	public Double getImporto() {
		return importo;
	}
	public void setImporto(Double importo) {
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
	public Calendar getDataAggiornamentoRecord() {
		return dataAggiornamentoRecord;
	}
	public void setDataAggiornamentoRecord(Calendar dataAggiornamentoRecord) {
		this.dataAggiornamentoRecord = dataAggiornamentoRecord;
	}
	public String getCodiceIbanAccredito() {
		return codiceIbanAccredito;
	}
	public void setCodiceIbanAccredito(String codiceIbanAccredito) {
		this.codiceIbanAccredito = codiceIbanAccredito;
	}
	public String getCodiceIuv() {
		return codiceIuv;
	}
	public void setCodiceIuv(String codiceIuv) {
		this.codiceIuv = codiceIuv;
	}
	public String getFlagPagato() {
		return flagPagato;
	}
	public void setFlagPagato(String flagPagato) {
		this.flagPagato = flagPagato;
	}
	public Double getImportoPagato() {
		return importoPagato;
	}
	public void setImportoPagato(Double importoPagato) {
		this.importoPagato = importoPagato;
	}
	public String getCodiceTipologiaServizio() {
		return codiceTipologiaServizio;
	}
	public void setCodiceTipologiaServizio(String codiceTipologiaServizio) {
		this.codiceTipologiaServizio = codiceTipologiaServizio;
	}
	public Calendar getDataPagamento() {
		return dataPagamento;
	}
	public void setDataPagamento(Calendar dataPagamento) {
		this.dataPagamento = dataPagamento;
	}
	public int getAnnoRiferimento() {
		return annoRiferimento;
	}
	public void setAnnoRiferimento(int annoRiferimento) {
		this.annoRiferimento = annoRiferimento;
	}
	
	
	private Calendar sqlDateToCalendar(String sqlDateToString) {
		Date date = Date.valueOf(sqlDateToString);
		Calendar cal = new GregorianCalendar();
		cal.setTime(date);
		return cal;
	}

	public String getCodiceIBAN2() {
		return codiceIBAN2;
	}

	public void setCodiceIBAN2(String codiceIBAN2) {
		this.codiceIBAN2 = codiceIBAN2;
	}

	public String getCausaleServizio() {
		return causaleServizio;
	}

	public void setCausaleServizio(String causaleServizio) {
		this.causaleServizio = causaleServizio;
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

	public String getCittaCAP() {
		return cittaCAP;
	}

	public void setCittaCAP(String cittaCAP) {
		this.cittaCAP = cittaCAP;
	}

	public String getCodiceUtente() {
		return codiceUtente;
	}

	public void setCodiceUtente(String codiceUtente) {
		this.codiceUtente = codiceUtente;
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

	public void setTarga(String targa) {
		this.targa = targa;
	}

	public String getDataVerbale() {
		return dataVerbale;
	}

	public void setDataVerbale(String dataVerbale) {
		this.dataVerbale = dataVerbale;
	}

	public String getNumeroVerbale() {
		return numeroVerbale;
	}

	public String getNumBollettinoCDS() {
		return numBollettinoCDS;
	}

	public void setNumeroVerbale(String numeroVerbale) {
		this.numeroVerbale = numeroVerbale;
	}

	public void setNumBollettinoCDS(String numBollettinoCDS) {
		this.numBollettinoCDS = numBollettinoCDS;
	}

	// SR PGNTCORE-11 inizio
	public void setFlagJppa(String flagJppa) {
		this.flagJppa = flagJppa;
	}
	
	public String getFlagJppa() {
		return this.flagJppa;
	}
	// fine

}